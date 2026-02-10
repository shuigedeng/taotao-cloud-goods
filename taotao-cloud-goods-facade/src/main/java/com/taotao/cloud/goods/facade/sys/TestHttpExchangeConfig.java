package com.taotao.cloud.goods.facade.sys;

import com.taotao.cloud.sys.api.inner.query.DictQueryApi;
import io.opentelemetry.api.OpenTelemetry;
import org.springframework.boot.http.client.autoconfigure.HttpClientProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.client.support.RestClientHttpServiceGroupConfigurer;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import org.springframework.web.service.registry.AbstractHttpServiceRegistrar;
import org.springframework.web.service.registry.ImportHttpServices;

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
@Import(TestHttpExchangeConfig.SysServiceRegistrar.class)
public class TestHttpExchangeConfig {

	public static class SysServiceRegistrar extends AbstractHttpServiceRegistrar {

		@Override
		protected void registerHttpServices( GroupRegistry registry, AnnotationMetadata importingClassMetadata ) {
			registry.forGroup("sys")
				.detectInBasePackages("com.taotao.cloud.sys.api.inner.query");

		}
	}

	@Bean
	public RestClientHttpServiceGroupConfigurer globalGroupConfigurer() {
		return groups -> {
			groups.filterByName("sys").forEachClient((_, builder) -> {
				builder
					.baseUrl("lb://taotao-cloud-sys")
					.defaultHeader("Authorization", "Bearer 1111")
					.defaultStatusHandler(HttpStatusCode::isError, (request, response) -> {
						// 自定义错误处理
						String errorBody = new String(response.getBody().readAllBytes());
						throw new ServiceCallException(
							"调用服务失败: " + response.getStatusCode() + " - " + errorBody
						);
					})
					.build();
			});

		};
	}

//	private final Environment environment;
//	private final OpenTelemetry openTelemetry;
//private HttpClientProperties httpClientProperties;
//	public TestHttpExchangeConfig( Environment environment, OpenTelemetry openTelemetry ) {
//		this.environment = environment;
//		this.openTelemetry = openTelemetry;
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
//	@Bean
//	public HttpServiceProxyFactoryCustomizer restClientCustomizer() {
//		return factory -> {
//			// 可以添加额外的自定义配置
//			factory.setBlockTimeout(Duration.ofSeconds(30));
//		};
//	}


}
