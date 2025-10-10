package com.taotao.cloud.goods.facade.domain;

import lombok.Data;

import java.util.LinkedList;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class GatewayInvokeTemplate<P, R> {
	private LinkedList<GatewayPreInterceptor<P>> preInterceptors;
	private LinkedList<GatewayPostInterceptor<R>> postInterceptors;
	private GatewayRouter<P> gatewayRouter;
	private String description;

	public GatewayInvokeTemplate(LinkedList<GatewayPreInterceptor<P>> preInterceptors,
								 LinkedList<GatewayPostInterceptor<R>> postInterceptors,
								 GatewayRouter<P> gatewayRouter,
								 String description) {
		this.preInterceptors = preInterceptors;
		this.postInterceptors = postInterceptors;
		this.gatewayRouter = gatewayRouter;
		this.description = description;
		this.preInterceptors.addLast(LogInterceptor.instance);
		this.preInterceptors.addLast(TimeElapseInterceptor.instance);
		this.postInterceptors.addFirst(LogInterceptor.instance);
		this.postInterceptors.addFirst(TimeElapseInterceptor.instance);
		this.postInterceptors.addLast(ExceptionProcessFilter.instance);
	}

	public GatewayInvokeTemplate(LinkedList<GatewayPreInterceptor<P>> preInterceptors,
								 LinkedList<GatewayPostInterceptor<R>> postInterceptors,
								 GatewayRouter<P> gatewayRouter,
								 String description, Object ext) {
		this.preInterceptors = preInterceptors;
		this.postInterceptors = postInterceptors;
		this.gatewayRouter = gatewayRouter;
		this.description = description;
		this.preInterceptors.addLast(LogExtInterceptor.instance);
		this.preInterceptors.addLast(TimeElapseInterceptor.instance);
		this.postInterceptors.addFirst(LogExtInterceptor.instance);
		this.postInterceptors.addFirst(TimeElapseInterceptor.instance);
		this.postInterceptors.addLast(ExceptionProcessFilter.instance);

	}

	public GatewayResponse<R> invoke(GatewayRequest<P> request) {
		GatewayContext context = new GatewayContext();
		context.setDescription(description);
		context.setTraceId(request.getGatewayRecord().getTraceId());
		context.setGatewayRecord(request.getGatewayRecord());
		context.setTradeNo(request.getGatewayRecord().getTradeNo());
		context.setRequest(request.getParam());
		GatewayResponse<R> responseHolder = new GatewayResponse<>();
		responseHolder.setGatewayRecord(request.getGatewayRecord());
		try {
			doPre(request, context);
		} catch (Exception e) {
			context.setCatchedException(e);
			context.setReachedRoute(false);
			doPost(responseHolder, context);
			return responseHolder;
		}
		context.setReachedRoute(true);
		try {
			Object result = gatewayRouter.execute(request.getParam());
			context.setRawResponse(result);
		} catch (Exception e) {
			context.setCatchedException(e);
		} finally {
			doPost(responseHolder, context);
			//return responseHolder;
			return responseHolder;
		}
	}

	private void doPre(GatewayRequest<P> request, GatewayContext context) {
		for (GatewayPreInterceptor<P> interceptor : preInterceptors) {
			interceptor.intercept(request, context);
		}
	}


	private void doPost(GatewayResponse<R> response, GatewayContext context) {
		for (GatewayPostInterceptor<R> interceptor : postInterceptors) {
			try {
				if (interceptor.shouldFilter(context)) {
					interceptor.intercept(response, context);
				}
			} catch (Exception e) {
				if (context.getCatchedException() == null) {
					context.setCatchedException(e);
				} else {
					log.error("Duplicate catch exception", e);
				}
			}
		}
	}
}
