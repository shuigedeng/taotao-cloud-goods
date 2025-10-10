package com.taotao.cloud.goods.facade.domain;

import java.util.LinkedList;

public class GatewayInvokeBuilder<P, R> {

	private final LinkedList<GatewayPreInterceptor<P>> preInterceptors = new LinkedList<>();
	private final LinkedList<GatewayPostInterceptor<R>> postInterceptors = new LinkedList<>();
	private GatewayRouter<P> gatewayRouter;
	private String description;

	public GatewayInvokeBuilder<P, R> description(String description) {
		this.description = description;
		return this;
	}

	public GatewayInvokeBuilder<P, R> gatewayRouter(GatewayRouter<P> router) {
		this.gatewayRouter = router;
		return this;
	}

	public GatewayInvokeBuilder<P, R> addFirst(GatewayPreInterceptor<P> interceptor) {
		this.preInterceptors.addFirst(interceptor);
		return this;
	}

	public GatewayInvokeBuilder<P, R> addLast(GatewayPreInterceptor<P> interceptor) {
		this.preInterceptors.addLast(interceptor);
		return this;
	}

	public GatewayInvokeBuilder<P, R> addFirst(GatewayPostInterceptor<R> interceptor) {
		this.postInterceptors.addFirst(interceptor);
		return this;
	}

	public GatewayInvokeBuilder<P, R> addLast(GatewayPostInterceptor<R> interceptor) {
		this.postInterceptors.addLast(interceptor);
		return this;
	}

	public GatewayInvokeTemplate<P, R> build() {
		return new GatewayInvokeTemplate<>(preInterceptors, postInterceptors, gatewayRouter, description);
	}

}
