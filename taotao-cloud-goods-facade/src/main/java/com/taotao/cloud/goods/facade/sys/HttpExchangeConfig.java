package com.taotao.cloud.goods.facade.sys;

import com.taotao.boot.common.constant.CommonConstants;
import com.taotao.boot.common.utils.servlet.TraceUtils;
import com.taotao.boot.ddd.domain.support.TraceUtil;
import com.taotao.boot.web.httpexchange.HttpServiceProxyFactoryCustomizer;
import com.taotao.cloud.sys.api.inner.query.DictQueryApi;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.propagation.TextMapPropagator;
import io.opentelemetry.context.propagation.TextMapSetter;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.netty.http.client.HttpClient;

import java.io.IOException;
import java.time.Duration;


/**
 * HttpExantge 类
 *
 * @author shuigedeng
 * @version 2022.05
 * @since 2026/1/10
 */
@Configuration
public class HttpExchangeConfig {

	private final OpenTelemetry openTelemetry;

	public HttpExchangeConfig( OpenTelemetry openTelemetry ) {
		this.openTelemetry = openTelemetry;
	}
//	@Bean
//	public ClientHttpRequestFactory clientHttpRequestFactory() {
//		HttpClient httpClient = HttpClient.create()
//			.responseTimeout(Duration.ofMillis(properties.getReadTimeout()))
//			.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, properties.getConnectTimeout())
//			.doOnConnected(conn -> conn
//				.addHandlerLast(new ReadTimeoutHandler(properties.getReadTimeout() / 1000))
//				.addHandlerLast(new ConnectTimeoutHandler(properties.getConnectTimeout() / 1000)));
//
//		return new ReactorClientHttpConnector(httpClient);
//	}
	@Bean
	@LoadBalanced
	public RestClient.Builder loadBalancedRestClientBuilder() {
		return RestClient.builder();
//			.requestFactory(clientHttpRequestFactory());
	}

	@Bean
	public DictQueryApi jsonPlaceholderClient(@LoadBalanced RestClient.Builder loadBalancedRestClientBuilder) {
		RestClient restClient = loadBalancedRestClientBuilder
//			.baseUrl("taotao-cloud-sys")
//			.defaultHeaders(this::injectTraceContext)
			.requestInterceptor(new ClientHttpRequestInterceptor() {
				@Override
				public ClientHttpResponse intercept( HttpRequest request, byte[] body,
					ClientHttpRequestExecution execution ) throws IOException {
					Context currentContext = Context.current();

					TextMapPropagator propagator = openTelemetry.getPropagators().getTextMapPropagator();

					TextMapSetter<HttpRequest> setter = new TextMapSetter<>() {
						@Override
						public void set(HttpRequest carrier, String key, String value) {
							if (carrier != null) {
								carrier.getHeaders().set(key, value); // 👈 关键：设置 header
							}
						}
					};

					propagator.inject(currentContext, request, setter);

					request.getHeaders().add(CommonConstants.TTC_TRACE_ID,TraceUtils.getTtcTraceId());
					request.getHeaders().add(CommonConstants.TTC_FROM_INNER,"true");

					return execution.execute(request, body);
				}
			})
			//.defaultHeaders(headers -> headers.setBearerAuth("token"))
			.defaultStatusHandler(HttpStatusCode::isError, (request, response) -> {
				// 自定义错误处理
				String errorBody = new String(response.getBody().readAllBytes());
				throw new ServiceCallException(
					"调用服务失败: " + response.getStatusCode() + " - " + errorBody
				);
			})
			.build();

		HttpServiceProxyFactory factory = HttpServiceProxyFactory
			.builderFor(RestClientAdapter.create(restClient))
//			.furtherCustomize(restClientCustomizer())
			.build();

		return factory.createClient(DictQueryApi.class);
	}
//	@Bean
//	public HttpServiceProxyFactoryCustomizer restClientCustomizer() {
//		return factory -> {
//			// 可以添加额外的自定义配置
//			factory.setBlockTimeout(Duration.ofSeconds(30));
//		};
//	}
}
