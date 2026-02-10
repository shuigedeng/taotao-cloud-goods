//package com.taotao.cloud.goods.facade.sys;
//
//import com.taotao.boot.common.constant.CommonConstants;
//import com.taotao.boot.common.utils.log.LogUtils;
//import com.taotao.boot.common.utils.servlet.TraceUtils;
//import com.taotao.boot.ddd.domain.support.TraceUtil;
//import com.taotao.boot.web.httpexchange.HttpServiceProxyFactoryCustomizer;
//import com.taotao.cloud.sys.api.inner.query.DictQueryApi;
//import io.opentelemetry.api.OpenTelemetry;
//import io.opentelemetry.context.Context;
//import io.opentelemetry.context.propagation.TextMapPropagator;
//import io.opentelemetry.context.propagation.TextMapSetter;
//import org.apache.hc.core5.http.ClassicHttpRequest;
//import org.apache.hc.core5.http.ClassicHttpResponse;
//import org.apache.hc.core5.http.Header;
//import org.apache.hc.core5.http.HttpEntity;
//import org.apache.hc.core5.http.io.entity.BufferedHttpEntity;
//import org.springframework.cloud.client.loadbalancer.LoadBalanced;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpRequest;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.client.ClientHttpRequestExecution;
//import org.springframework.http.client.ClientHttpRequestFactory;
//import org.springframework.http.client.ClientHttpRequestInterceptor;
//import org.springframework.http.client.ClientHttpResponse;
//import org.springframework.http.client.reactive.ReactorClientHttpConnector;
//import org.springframework.web.client.RestClient;
//import org.springframework.web.client.support.RestClientAdapter;
//import org.springframework.web.service.invoker.HttpServiceProxyFactory;
//import org.springframework.web.service.registry.ImportHttpServices;
//import reactor.netty.http.client.HttpClient;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.time.Duration;
//import java.util.Arrays;
//import java.util.Map;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//import static org.springframework.http.HttpHeaders.formatHeaders;
//import org.springframework.boot.http.client.autoconfigure.HttpClientProperties;
//import org.springframework.boot.http.client.autoconfigure.HttpClientProperties;
//import org.springframework.boot.http.client.autoconfigure.HttpClientProperties;
//import org.springframework.boot.http.client.autoconfigure.HttpClientProperties;
///**
// * HttpExantge 类
// *
// * @author shuigedeng
// * @version 2022.05
// * @since 2026/1/10
// */
//@ImportHttpServices
//@Configuration
//public class HttpExchangeConfig {
//
//	private final Environment environment;
//	private final OpenTelemetry openTelemetry;
//private HttpClientProperties httpClientProperties;
//	public HttpExchangeConfig( Environment environment, OpenTelemetry openTelemetry ) {
//		this.environment = environment;
//		this.openTelemetry = openTelemetry;
//	}
////	@Bean
////	public ClientHttpRequestFactory clientHttpRequestFactory() {
////		HttpClient httpClient = HttpClient.create()
////			.responseTimeout(Duration.ofMillis(properties.getReadTimeout()))
////			.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, properties.getConnectTimeout())
////			.doOnConnected(conn -> conn
////				.addHandlerLast(new ReadTimeoutHandler(properties.getReadTimeout() / 1000))
////				.addHandlerLast(new ConnectTimeoutHandler(properties.getConnectTimeout() / 1000)));
////
////		return new ReactorClientHttpConnector(httpClient);
////	}
//	@Bean
//	@LoadBalanced
//	public RestClient.Builder loadBalancedRestClientBuilder() {
//		return RestClient.builder();
////			.requestFactory(clientHttpRequestFactory());
//	}
//
//	@Bean
//	public DictQueryApi jsonPlaceholderClient(@LoadBalanced RestClient.Builder loadBalancedRestClientBuilder) {
//		RestClient restClient = loadBalancedRestClientBuilder
////			.baseUrl("taotao-cloud-sys")
////			.defaultHeaders(this::injectTraceContext)
//			.requestInterceptor(new ClientHttpRequestInterceptor() {
//				@Override
//				public ClientHttpResponse intercept( HttpRequest request, byte[] body,
//					ClientHttpRequestExecution execution ) throws IOException {
//
//				}
//			})
//			//.defaultHeaders(headers -> headers.setBearerAuth("token"))
//			.defaultStatusHandler(HttpStatusCode::isError, (request, response) -> {
//				// 自定义错误处理
//				String errorBody = new String(response.getBody().readAllBytes());
//				throw new ServiceCallException(
//					"调用服务失败: " + response.getStatusCode() + " - " + errorBody
//				);
//			})
//			.build();
//
//		HttpServiceProxyFactory factory = HttpServiceProxyFactory
//			.builderFor(RestClientAdapter.create(restClient))
//			.embeddedValueResolver(environment::resolvePlaceholders)
////			.customArgumentResolver(queryObjectArgumentResolver)
////			.furtherCustomize(restClientCustomizer())
//			.build();
//
//		return factory.createClient(DictQueryApi.class);
//	}
////	@Bean
////	public HttpServiceProxyFactoryCustomizer restClientCustomizer() {
////		return factory -> {
////			// 可以添加额外的自定义配置
////			factory.setBlockTimeout(Duration.ofSeconds(30));
////		};
////	}
//
//
//}
