package com.taotao.cloud.goods.facade.sys;

import com.taotao.cloud.sys.api.inner.dto.request.DictQueryApiRequest;
import com.taotao.cloud.sys.api.inner.dto.response.DictQueryApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.concurrent.CompletableFuture;

@Component
public class ServiceInvoker {
    
    private final RestClient.Builder restClientBuilder;
    private final ObjectMapper objectMapper;
    
    public ServiceInvoker(@LoadBalanced RestClient.Builder restClientBuilder,
                         ObjectMapper objectMapper) {
        this.restClientBuilder = restClientBuilder;
        this.objectMapper = objectMapper;
    }
    
    public <T> T post(String serviceName, String path, Object request, 
                     Class<T> responseType) {
        RestClient restClient = restClientBuilder
            .baseUrl("http://" + serviceName)
            .build();
        
        return restClient.post()
            .uri(path)
            .contentType(MediaType.APPLICATION_JSON)
            .body(request)
            .retrieve()
            .body(responseType);
    }
    
    public <T> T get(String serviceName, String path, 
                    Class<T> responseType, Map<String, Object> params) {
        RestClient restClient = restClientBuilder
            .baseUrl("http://" + serviceName)
            .build();
            
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath(path);
        if (params != null) {
            params.forEach(builder::queryParam);
        }
        
        return restClient.get()
            .uri(builder.build().toUriString())
            .retrieve()
            .body(responseType);
    }
    
    // 异步调用
    public <T> CompletableFuture<T> postAsync(String serviceName, String path,
                                             Object request, Class<T> responseType) {
        return CompletableFuture.supplyAsync(() -> 
            post(serviceName, path, request, responseType)
        );
    }
}

// 使用示例
@Service
public class BusinessService {
    
    @Autowired
    private ServiceInvoker serviceInvoker;
    
    public DictQueryApiResponse getDict(String code) {
        DictQueryApiRequest request = new DictQueryApiRequest();
        request.setCode(code);
        
        return serviceInvoker.post(
            "taotao-cloud-sys",
            "/inner/sys/dict/query/code",
            new Request<>(request),
            Response.class
        ).getData();
    }
}
