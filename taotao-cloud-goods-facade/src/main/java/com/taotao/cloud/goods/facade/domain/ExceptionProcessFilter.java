package com.taotao.cloud.goods.facade.domain;

@slf4j
public class ExceptionProcessFilter implements GatewayPostInterceptor{
    public static ExceptionProcessFilter instance = new ExceptionProcessFilter();
    @Override
    public void intercept( GatewayResponse response,GatewayContext context) {
		log.error("Gateway traceId:{}, description:{}", context.getGatewayRecord().getTraceId(),
			context.getDescription(), context.getCatchedException());
		if (context.isReachedRoute()) {
			response.setStatus(GatewayResponseStatus.P);
		} else {
			response.setStatus(GatewayResponseStatus.F);
			response.setFailCode("999999");
		}
	}
    @Override
    public boolean shouldFilter( GatewayContext gatewayContext ) {
		return gatewayContext.getCatchedException() != null;
	}

}
