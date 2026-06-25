package com.taotao.cloud.goods.facade.order.interceptor;

import com.taotao.boot.client.gateway.interceptor.GatewayPostInterceptor;
import com.taotao.boot.client.gateway.model.GatewayContext;
import com.taotao.boot.client.gateway.model.GatewayResponse;

/**
 * OrderInterceptor
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2025-12-19 09:30:45
 */
public class OrderInterceptor<T> implements GatewayPostInterceptor<T> {

    @Override
    public void postIntercept( GatewayResponse<T> response, GatewayContext context ) {

    }

    @Override
    public boolean postShouldFilter( GatewayContext context ) {
        return context.getCatchedException() != null;
    }
}
