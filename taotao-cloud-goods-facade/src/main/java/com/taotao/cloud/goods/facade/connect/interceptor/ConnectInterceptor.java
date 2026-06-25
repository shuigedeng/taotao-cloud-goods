package com.taotao.cloud.goods.facade.connect.interceptor;

import com.taotao.boot.client.gateway.model.GatewayResponse;
import com.taotao.boot.client.gateway.interceptor.GatewayPostInterceptor;
import com.taotao.boot.client.gateway.model.GatewayContext;

/**
 * ConnectInterceptor
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2025-12-19 09:30:45
 */
public class ConnectInterceptor<T> implements GatewayPostInterceptor<T> {

    @Override
    public void postIntercept( GatewayResponse<T> response, GatewayContext context ) {

    }

    @Override
    public boolean postShouldFilter( GatewayContext context ) {
        return context.getCatchedException() != null;
    }
}
