package com.taotao.cloud.goods.facade.order.invoker;

import com.taotao.boot.common.model.request.Request;
import com.taotao.boot.ddd.gateway.invoker.GatewayInvokeBuilder;
import com.taotao.boot.ddd.gateway.model.GatewayRequest;
import com.taotao.boot.ddd.gateway.model.GatewayResponse;
import com.taotao.cloud.goods.facade.order.interceptor.OrderInterceptor;
import com.taotao.cloud.sys.api.dubbo.DictRpcService;
import com.taotao.cloud.sys.api.inner.DictApi;
import com.taotao.cloud.sys.api.inner.request.DictQueryApiRequest;
import com.taotao.cloud.sys.api.inner.response.DictApiResponse;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * OrderInvoker
 *
 * @author shuigedeng
 * @version 2026.01
 * @since 2025-12-19 09:30:45
 */
@Component
@RequiredArgsConstructor
public class OrderInvoker {

}
