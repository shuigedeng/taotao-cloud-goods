package com.taotao.cloud.goods.facade.domain;

@FunctionalInterface
public interface GatewayRouter<P> {
    Object execute( P param );
}
