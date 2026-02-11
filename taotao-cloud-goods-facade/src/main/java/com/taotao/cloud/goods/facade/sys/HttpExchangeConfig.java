package com.taotao.cloud.goods.facade.sys;

import com.github.lianjiatech.retrofit.spring.boot.log.LoggingInterceptor;
import com.taotao.boot.web.httpexchange.LogClientHttpRequestInterceptor;
import com.taotao.boot.web.httpexchange.TraceClientHttpRequestInterceptor;
import groovy.lang.TracingInterceptor;
import io.opentelemetry.api.OpenTelemetry;
import org.springframework.boot.restclient.RestClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientHttpServiceGroupConfigurer;
import org.springframework.web.service.registry.AbstractHttpServiceRegistrar;

import java.util.Set;

/**
 * HttpExantge 类
 *
 * @author shuigedeng
 * @version 2022.05
 * @since 2026/1/10
 */
@Configuration
@Import(HttpExchangeConfig.SysServiceRegistrar.class)
public class HttpExchangeConfig {

	public static class SysServiceRegistrar extends AbstractHttpServiceRegistrar {

		@Override
		protected void registerHttpServices( GroupRegistry registry, AnnotationMetadata importingClassMetadata ) {
			Set<String> annotationTypes = importingClassMetadata.getAnnotationTypes();

			registry.forGroup("taotao-cloud-sys")
				.detectInBasePackages("com.taotao.cloud.sys.api.inner");

			registry.forGroup("stackoverflow")
				.detectInBasePackages("com.taotao.cloud.goods.facade.stackoverflow");

			registry.forGroup("github")
				.detectInBasePackages("com.taotao.cloud.goods.facade.github");
		}
	}

	@Bean
	public RestClientCustomizer restClientCustomizer() {
		return new RestClientCustomizer() {
			@Override
			public void customize( RestClient.Builder restClientBuilder ) {

			}
		};
	}


	@Bean
	public RestClientHttpServiceGroupConfigurer sysGroupConfigurer( OpenTelemetry openTelemetry ) {
		return groups -> {
			// 1. 为所有组应用通用配置
			//groups.forEachClient((group, builder) -> {
			//	builder
			//		.defaultHeader("X-Request-ID", () -> UUID.randomUUID().toString())
			//		.defaultHeader("User-Agent", "HttpServiceGroupsDemo/1.0")
			//		.requestInterceptors(interceptors -> {
			//			interceptors.add(new LoggingInterceptor());
			//			interceptors.add(new MetricsInterceptor());
			//			interceptors.add(new TracingInterceptor());
			//		});
			//});

			// GitHub 组特定配置
			groups
				.filterByName("taotao-cloud-sys")
				.forEachClient(( group, builder ) -> {
					String name = group.name();

					builder
						.baseUrl("lb://taotao-cloud-sys")
						.defaultHeader("Authorization", "Bearer 1111")
						.requestInterceptor(new LogClientHttpRequestInterceptor())
						.requestInterceptor(new TraceClientHttpRequestInterceptor(openTelemetry))
						.defaultStatusHandler(HttpStatusCode::isError, ( request, response ) -> {
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

	@Bean
	public RestClientHttpServiceGroupConfigurer githubGroupConfigurer() {
		return groups -> {
			groups
				.filterByName("github")
				.forEachClient(( group, builder ) -> {
					String name = group.name();

					builder
						//.baseUrl("lb://taotao-cloud-sys")
						.defaultStatusHandler(HttpStatusCode::isError, ( request, response ) -> {
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

	@Bean
	public RestClientHttpServiceGroupConfigurer stackoverflowGroupConfigurer() {
		return groups -> {
			groups
				.filterByName("stackoverflow")
				.forEachClient(( group, builder ) -> {
					String name = group.name();

					builder
						//.baseUrl("lb://taotao-cloud-sys")
						//.defaultHeader("Authorization", "Bearer 1111")
						.defaultStatusHandler(HttpStatusCode::isError, ( request, response ) -> {
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

}
