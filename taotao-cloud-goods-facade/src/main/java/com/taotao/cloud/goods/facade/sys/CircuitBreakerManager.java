package com.taotao.cloud.goods.facade.sys;

import com.taotao.cloud.sys.api.inner.query.DictQueryApi;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.stereotype.Component;

@Component
public class CircuitBreakerManager {
    
    private final CircuitBreakerRegistry circuitBreakerRegistry;
    
    public CircuitBreakerManager(CircuitBreakerRegistry circuitBreakerRegistry) {
        this.circuitBreakerRegistry = circuitBreakerRegistry;
    }
    
    public <T> T executeWithCircuitBreaker(String serviceName, 
            String methodName, Supplier<T> supplier) {
        
        CircuitBreaker circuitBreaker = circuitBreakerRegistry
            .circuitBreaker(serviceName, methodName);
            
        Supplier<T> decoratedSupplier = CircuitBreaker
            .decorateSupplier(circuitBreaker, supplier);
            
        try {
            return decoratedSupplier.get();
        } catch (Exception e) {
            // 可以在这里添加降级逻辑
            throw new ServiceCallException("服务调用失败: " + e.getMessage(), e);
        }
    }
}

// 使用方式
@Service
public class DictService {
    
    private final DictQueryApi dictQueryApi;
    private final CircuitBreakerManager circuitBreakerManager;
    
    public DictQueryApiResponse getDictByCode(String code) {
        return circuitBreakerManager.executeWithCircuitBreaker(
            "taotao-cloud-sys",
            "queryByCode",
            () -> {
                DictQueryApiRequest request = new DictQueryApiRequest();
                request.setCode(code);
                Response<DictQueryApiResponse> response = dictQueryApi
                    .queryByCode(new Request<>(request));
                    
                if (response.isSuccess()) {
                    return response.getData();
                } else {
                    throw new BusinessException("查询失败: " + response.getMessage());
                }
            }
        );
    }
}
