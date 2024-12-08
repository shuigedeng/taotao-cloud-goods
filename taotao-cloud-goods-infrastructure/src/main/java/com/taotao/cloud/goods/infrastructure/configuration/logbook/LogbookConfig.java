package com.taotao.cloud.goods.infrastructure.configuration.logbook;

import org.springframework.context.annotation.Bean;

import java.io.IOException;

public class LogbookConfig {

	//@Bean
	//public Logbook logbook() {
	//	return Logbook.builder()
	//		.condition(Conditions.exclude(Conditions.requestTo("/users/*"),
	//			Conditions.contentType("application/json")))
	//		.sink(new DefaultSink(new DefaultHttpLogFormatter(), new DefaultHttpLogWriter()))
	//		.sink(new Sink() {
	//			@Override
	//			public boolean isActive() {
	//				return Sink.super.isActive();
	//			}
	//
	//			@Override
	//			public void write(Precorrelation precorrelation, HttpRequest request)
	//				throws IOException {
	//
	//			}
	//
	//			@Override
	//			public void write(Correlation correlation, HttpRequest request,
	//							  HttpResponse response)
	//				throws IOException {
	//
	//			}
	//
	//			@Override
	//			public void writeBoth(Correlation correlation, HttpRequest request,
	//								  HttpResponse response)
	//				throws IOException {
	//				System.err.println("==============================");
	//				System.err.println("request header:\t" + request.getHeaders());
	//				System.err.println("request body:\t" + request.getBodyAsString());
	//				System.out.println();
	//				System.err.println("response header:\t" + response.getHeaders());
	//				System.err.println("response body:\t" + response.getBodyAsString());
	//				System.err.println("==============================");
	//			}
	//		})
	//		.build();
	//}
	//
	//;
}
