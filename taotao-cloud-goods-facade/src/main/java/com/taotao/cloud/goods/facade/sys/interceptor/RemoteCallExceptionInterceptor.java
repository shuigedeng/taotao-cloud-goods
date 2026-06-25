package com.taotao.cloud.goods.facade.sys.interceptor;


import com.taotao.boot.client.gateway.exception.RemoteCallErrorType;
import com.taotao.boot.client.gateway.exception.RemoteCallException;
import com.taotao.boot.client.gateway.interceptor.GatewayPostInterceptor;
import com.taotao.boot.client.gateway.model.GatewayContext;
import com.taotao.boot.client.gateway.model.GatewayRequest;
import com.taotao.boot.client.gateway.model.GatewayResponse;
import org.apache.rocketmq.remoting.rpc.RpcException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * @author 1
 */
public class RemoteCallExceptionInterceptor<T> implements GatewayPostInterceptor<T> {

	@SuppressWarnings("rawtypes")
	private static final RemoteCallExceptionInterceptor INSTANCE = new RemoteCallExceptionInterceptor();

	private RemoteCallExceptionInterceptor() {
	}

	@SuppressWarnings("unchecked")
	public static <T> RemoteCallExceptionInterceptor<T> getInstance() {
		return (RemoteCallExceptionInterceptor<T>) INSTANCE;
	}


	@Override
	public void postIntercept( GatewayResponse<T> response, GatewayContext context ) {
		context.setCatchedException(handleRemoteException(response, context));
	}

	@Override
	public boolean postShouldFilter( GatewayContext gatewayContext ) {
		return gatewayContext.getCatchedException() != null;
	}

	/**
	 * 处理远程调用异常 统一封装 Dubbo RpcException、RestTemplate 异常等
	 */
	private Exception handleRemoteException(  GatewayResponse<?> response, GatewayContext context ) {
		Exception e = context.getCatchedException();

//		String targetService = gatewayRouter != null ? gatewayRouter.getClass().getSimpleName() : "Unknown";
//		String targetMethod = description != null ? description : "Unknown";
//		Object requestParams = request != null ? request.getParam() : null;
		String targetService="Unknown";
		String targetMethod="Unknown";
		Object requestParams="Unknown";
		// 1. 如果已经是 RemoteCallException，直接返回
		if (e instanceof RemoteCallException) {
			return e;
		}

		// 2. 处理 Dubbo RpcException
//		if (e instanceof RpcException) {
//			return handleDubboException((RpcException) e, targetService, targetMethod, requestParams);
//		}

		// 3. 处理 RestTemplate 异常
		if (e instanceof RestClientException) {
			return handleRestTemplateException((RestClientException) e, targetService, targetMethod, requestParams);
		}

		// 5. 处理标准网络异常
		if (e instanceof SocketTimeoutException ||
			e instanceof ConnectException ||
			e instanceof UnknownHostException ||
			e instanceof IOException) {
			return handleNetworkException(e, targetService, targetMethod, requestParams);
		}

		// 6. 其他未知异常
		return RemoteCallException.builder()
			.errorType(RemoteCallErrorType.UNKNOWN_ERROR)
			.errorCode("GATEWAY_UNKNOWN_ERROR")
			.errorMessage("未知异常: " + e.getMessage())
			.targetService(targetService)
			.targetMethod(targetMethod)
			.requestParams(requestParams)
			.originalException(e)
			.build();
	}


	/**
	 * 处理 RestTemplate 异常
	 */
	private Exception handleRestTemplateException( RestClientException e, String targetService,
		String targetMethod, Object requestParams ) {
		// 1. ResourceAccessException - 网络异常
		if (e instanceof ResourceAccessException) {
			return handleResourceAccessException((ResourceAccessException) e, targetService,
				targetMethod, requestParams);
		}

		// 2. HttpClientErrorException - 4xx 客户端错误
		if (e instanceof HttpClientErrorException) {
			HttpClientErrorException httpEx = (HttpClientErrorException) e;
			int statusCode = httpEx.getStatusCode().value();
			String responseBody = httpEx.getResponseBodyAsString();

			return RemoteCallException.builder()
				.errorType(RemoteCallErrorType.BUSINESS_ERROR)
				.errorCode("HTTP_CLIENT_ERROR_" + statusCode)
				.errorMessage(String.format("HTTP客户端错误 %d: %s", statusCode,
					httpEx.getStatusText()))
				.targetService(targetService)
				.targetMethod(targetMethod)
				.requestParams(requestParams)
				.httpStatusCode(statusCode)
				.responseResult(responseBody)
				.originalException(e)
				.build();
		}

		// 3. HttpServerErrorException - 5xx 服务端错误
		if (e instanceof HttpServerErrorException) {
			HttpServerErrorException httpEx = (HttpServerErrorException) e;
			int statusCode = httpEx.getStatusCode().value();
			String responseBody = httpEx.getResponseBodyAsString();

			// 判断是否为网络问题（504、503、502）
			boolean isNetworkError = statusCode == 504 || statusCode == 503 || statusCode == 502;

			return RemoteCallException.builder()
				.errorType(isNetworkError ?
					RemoteCallErrorType.NETWORK_ERROR :
					RemoteCallErrorType.BUSINESS_ERROR)
				.errorCode("HTTP_SERVER_ERROR_" + statusCode)
				.errorMessage(String.format("HTTP服务端错误 %d: %s", statusCode,
					httpEx.getStatusText()))
				.targetService(targetService)
				.targetMethod(targetMethod)
				.requestParams(requestParams)
				.httpStatusCode(statusCode)
				.responseResult(responseBody)
				.originalException(e)
				.build();
		}

		// 4. HttpMessageConversionException - 消息转换异常
//		if (e instanceof HttpMessageConversionException) {
//			return RemoteCallException.builder()
//				.errorType(RemoteCallErrorType.BUSINESS_ERROR)
//				.errorCode("HTTP_CONVERSION_ERROR")
//				.errorMessage("HTTP消息转换异常: " + e.getMessage())
//				.targetService(targetService)
//				.targetMethod(targetMethod)
//				.requestParams(requestParams)
//				.originalException(e)
//				.build();
//		}

		// 5. 其他 RestClient 异常
		return RemoteCallException.builder()
			.errorType(RemoteCallErrorType.NETWORK_ERROR)
			.errorCode("REST_CLIENT_ERROR")
			.errorMessage("RestClient异常: " + e.getMessage())
			.targetService(targetService)
			.targetMethod(targetMethod)
			.requestParams(requestParams)
			.originalException(e)
			.build();
	}

	/**
	 * 处理 ResourceAccessException（网络访问异常）
	 */
	private Exception handleResourceAccessException( ResourceAccessException e, String targetService,
		String targetMethod, Object requestParams ) {
		String errorCode = "NETWORK_ERROR";
		String errorMessage = "网络访问异常";
		String errorSubType = "UNKNOWN";

		// 获取根异常
		Throwable cause = e.getCause();
		if (cause != null) {
			if (cause instanceof SocketTimeoutException) {
				errorCode = "NETWORK_TIMEOUT";
				errorMessage = "网络超时: " + cause.getMessage();
				errorSubType = "TIMEOUT";
			} else if (cause instanceof ConnectException) {
				errorCode = "CONNECTION_REFUSED";
				errorMessage = "连接被拒绝: " + cause.getMessage();
				errorSubType = "CONNECTION_REFUSED";
			} else if (cause instanceof UnknownHostException) {
				errorCode = "HOST_UNREACHABLE";
				errorMessage = "主机不可达: " + cause.getMessage();
				errorSubType = "HOST_UNREACHABLE";
			} else if (cause instanceof IOException) {
				errorCode = "IO_ERROR";
				errorMessage = "IO异常: " + cause.getMessage();
				errorSubType = "IO_ERROR";
			}
		}

		return RemoteCallException.builder()
			.errorType(RemoteCallErrorType.NETWORK_ERROR)
			.errorCode(errorCode)
			.errorMessage(errorMessage)
			.targetService(targetService)
			.targetMethod(targetMethod)
			.requestParams(requestParams)
			.originalException(e)
			.errorSubType(errorSubType)
			.addExtension("networkErrorType", errorSubType)
			.build();
	}

	/**
	 * 处理标准网络异常
	 */
	private Exception handleNetworkException( Exception e, String targetService,
		String targetMethod, Object requestParams ) {
		String errorCode;
		String errorMessage;
		String errorSubType;

		if (e instanceof SocketTimeoutException) {
			errorCode = "NETWORK_TIMEOUT";
			errorMessage = "网络超时: " + e.getMessage();
			errorSubType = "TIMEOUT";
		} else if (e instanceof ConnectException) {
			errorCode = "CONNECTION_REFUSED";
			errorMessage = "连接被拒绝: " + e.getMessage();
			errorSubType = "CONNECTION_REFUSED";
		} else if (e instanceof UnknownHostException) {
			errorCode = "HOST_UNREACHABLE";
			errorMessage = "主机不可达: " + e.getMessage();
			errorSubType = "HOST_UNREACHABLE";
		} else if (e instanceof IOException) {
			errorCode = "IO_ERROR";
			errorMessage = "IO异常: " + e.getMessage();
			errorSubType = "IO_ERROR";
		} else {
			errorCode = "NETWORK_ERROR";
			errorMessage = "网络异常: " + e.getMessage();
			errorSubType = "UNKNOWN";
		}

		return RemoteCallException.builder()
			.errorType(RemoteCallErrorType.NETWORK_ERROR)
			.errorCode(errorCode)
			.errorMessage(errorMessage)
			.targetService(targetService)
			.targetMethod(targetMethod)
			.requestParams(requestParams)
			.originalException(e)
			.errorSubType(errorSubType)
			.addExtension("networkErrorType", errorSubType)
			.build();
	}

	/**
	 * 处理 Dubbo RpcException
	 */
//	private Exception handleDubboException( RpcException e, String targetService,
//		String targetMethod, Object requestParams ) {
//		RemoteCallErrorType errorType;
//		String errorCode;
//		String errorMessage;
//
//		// 判断异常类型
//		if (e.isTimeout()) {
//			errorType = RemoteCallErrorType.NETWORK_ERROR;
//			errorCode = "DUBBO_TIMEOUT";
//			errorMessage = "Dubbo调用超时: " + e.getMessage();
//		} else if (e.isNetwork()) {
//			errorType = RemoteCallErrorType.NETWORK_ERROR;
//			errorCode = "DUBBO_NETWORK_ERROR";
//			errorMessage = "Dubbo网络异常: " + e.getMessage();
//		} else if (e.isBiz()) {
//			errorType = RemoteCallErrorType.BUSINESS_ERROR;
//			errorCode = "DUBBO_BIZ_ERROR";
//			errorMessage = "Dubbo业务异常: " + e.getMessage();
//		} else if (e.isSerialization()) {
//			errorType = RemoteCallErrorType.BUSINESS_ERROR;
//			errorCode = "DUBBO_SERIALIZE_ERROR";
//			errorMessage = "Dubbo序列化异常: " + e.getMessage();
//		} else {
//			errorType = RemoteCallErrorType.NETWORK_ERROR;
//			errorCode = "DUBBO_UNKNOWN_ERROR";
//			errorMessage = "Dubbo未知异常: " + e.getMessage();
//		}
//
//		// 提取服务端信息（如果有）
//		String serverIp = null;
//		Integer serverPort = null;
//		if (e.getCause() != null && e.getCause() instanceof java.net.ConnectException) {
//			// 从异常信息中提取IP和端口
//			String msg = e.getMessage();
//			if (msg != null) {
//				java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(
//					"(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}):(\\d+)");
//				java.util.regex.Matcher matcher = pattern.matcher(msg);
//				if (matcher.find()) {
//					serverIp = matcher.group(1);
//					serverPort = Integer.parseInt(matcher.group(2));
//				}
//			}
//		}
//
//		// 使用 DubboCallException
//		return DubboCallException.dubboBuilder()
//			.errorType(errorType)
//			.errorCode(errorCode)
//			.errorMessage(errorMessage)
//			.targetService(targetService)
//			.targetMethod(targetMethod)
//			.requestParams(requestParams)
//			.originalException(e)
//			.dubboErrorCode(String.valueOf(e.getCode()))
//			.serverIp(serverIp)
//			.serverPort(serverPort)
//			.build();
//	}
}
