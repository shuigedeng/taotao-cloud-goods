package com.taotao.cloud.goods.facade.gateway.model;

@FunctionalInterface
public interface GatewayRouter<P> {
    Object execute( P param );
}
