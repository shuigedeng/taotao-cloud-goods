package com.taotao.cloud.goods.facade.sys;

import com.taotao.boot.common.constant.CommonConstants;
import com.taotao.boot.common.utils.servlet.TraceUtils;
import com.taotao.boot.ddd.domain.support.TraceUtil;
import com.taotao.cloud.sys.api.inner.query.DictQueryApi;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.propagation.TextMapPropagator;
import io.opentelemetry.context.propagation.TextMapSetter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.io.IOException;


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

	@Bean
	public DictQueryApi jsonPlaceholderClient() {
		RestClient restClient = RestClient.builder()
			.baseUrl("http://192.168.218.1:33331")
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

					return execution.execute(request, body);
				}
			})
			//.defaultHeaders(headers -> headers.setBearerAuth("token"))
			.build();

		HttpServiceProxyFactory factory = HttpServiceProxyFactory
			.builderFor(RestClientAdapter.create(restClient))
			.build();

		return factory.createClient(DictQueryApi.class);
	}
}
