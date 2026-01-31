package com.taotao.cloud.goods.facade.credit.interceptor;

import com.taotao.boot.ddd.gateway.interceptor.GatewayPostInterceptor;
import com.taotao.boot.ddd.gateway.model.GatewayContext;
import com.taotao.boot.ddd.gateway.model.GatewayResponse;

/**
 * CreditInterceptor
 *
 * @author shuigedeng
 * @version 2026.03
 * @since 2025-12-19 09:30:45
 */
public class CreditInterceptor<T> implements GatewayPostInterceptor<T> {

    @Override
    public void intercept( GatewayResponse<T> response, GatewayContext context ) {

    }

    @Override
    public boolean shouldFilter( GatewayContext context ) {
        return context.getCatchedException() != null;
    }
}
