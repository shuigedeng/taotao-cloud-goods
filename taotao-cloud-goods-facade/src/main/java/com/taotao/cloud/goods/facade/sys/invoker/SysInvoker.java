package com.taotao.cloud.goods.facade.sys.invoker;

import com.taotao.boot.common.model.request.Request;
import com.taotao.boot.ddd.gateway.invoker.GatewayInvokeBuilder;
import com.taotao.boot.ddd.gateway.model.GatewayRequest;
import com.taotao.boot.ddd.gateway.model.GatewayResponse;
import com.taotao.cloud.goods.facade.sys.interceptor.SysInterceptor;
import com.taotao.cloud.sys.api.inner.dto.request.DictQueryApiRequest;
import com.taotao.cloud.sys.api.inner.dto.response.DictQueryApiResponse;
import com.taotao.cloud.sys.api.inner.query.DictQueryApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * SysInvoker
 *
 * @author shuigedeng
 * @version 2026.03
 * @since 2025-12-19 09:30:45
 */
@Component
@RequiredArgsConstructor
public class SysInvoker {

    private final DictQueryApi dictQueryApi;
//
//    @DubboReference
//    private final DictRpcService dictRpcService;

    public GatewayResponse<DictQueryApiResponse> findByCode( GatewayRequest<DictQueryApiRequest> gatewayRequest ) {
        return new GatewayInvokeBuilder<DictQueryApiRequest, DictQueryApiResponse>()
                .description("sys系统-字典信息查询")
                .gatewayRouter(request -> dictQueryApi.findByCode(Request.from(request)))
                .addFirst(new SysInterceptor<>())
                .build()
                .invoke(gatewayRequest);
    }


}
