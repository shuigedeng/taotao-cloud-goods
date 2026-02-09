package com.taotao.cloud.goods.facade.sys;

import com.taotao.cloud.sys.api.inner.query.DictQueryApi;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ServiceClientFactory {
    
    private final Map<String, Object> clientCache = new ConcurrentHashMap<>();
    private final RestClient.Builder restClientBuilder;
    private final HttpServiceProxyFactory.Builder factoryBuilder;
    
    public ServiceClientFactory(@LoadBalanced RestClient.Builder restClientBuilder) {
        this.restClientBuilder = restClientBuilder;
        this.factoryBuilder = HttpServiceProxyFactory
            .builderFor(RestClientAdapter.create(restClientBuilder.build()));
    }
    
    @SuppressWarnings("unchecked")
    public <T> T getClient(Class<T> clientType, String serviceName) {
        String cacheKey = serviceName + ":" + clientType.getName();
        
        return (T) clientCache.computeIfAbsent(cacheKey, key -> {
            RestClient restClient = restClientBuilder
                .baseUrl("http://" + serviceName)
                .build();
                
            HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builderFor(RestClientAdapter.create(restClient))
                .build();
                
            return factory.createClient(clientType);
        });
    }
    
    // 使用示例
    public DictQueryApi getDictQueryApi() {
        return getClient(DictQueryApi.class, "taotao-cloud-sys");
    }
    
    public UserApi getUserApi() {
        return getClient(UserApi.class, "taotao-cloud-user");
    }
}
