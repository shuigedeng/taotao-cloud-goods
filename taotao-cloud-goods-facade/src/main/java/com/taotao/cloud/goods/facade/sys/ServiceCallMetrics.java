package com.taotao.cloud.goods.facade.sys;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ServiceCallMetrics {
    
    private final MeterRegistry meterRegistry;
    private final Map<String, Timer> timers = new ConcurrentHashMap<>();
    
    public ServiceCallMetrics(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }
    
    public Timer.Sample startTimer(String serviceName, String method) {
        return Timer.start(meterRegistry);
    }
    
    public void stopTimer(Timer.Sample sample, String serviceName, String method, boolean success) {
        String timerName = "service.call.duration";
        
        Timer timer = timers.computeIfAbsent(timerName, key ->
            Timer.builder(timerName)
                .tag("service", serviceName)
                .tag("method", method)
                .tag("result", success ? "success" : "error")
                .publishPercentiles(0.5, 0.95, 0.99)  // 50%, 95%, 99% 分位
                .register(meterRegistry)
        );
        
        sample.stop(timer);
    }
}

// 在拦截器中使用
public class MetricsRequestInterceptor implements RestClient.RequestInterceptor {
    
    private final ServiceCallMetrics metrics;
    
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, 
            ClientHttpRequestExecution execution) throws IOException {
        
        String serviceName = extractServiceName(request.getURI());
        String method = request.getMethod().name();
        
        Timer.Sample sample = metrics.startTimer(serviceName, method);
        try {
            ClientHttpResponse response = execution.execute(request, body);
            metrics.stopTimer(sample, serviceName, method, response.getStatusCode().is2xxSuccessful());
            return response;
        } catch (IOException e) {
            metrics.stopTimer(sample, serviceName, method, false);
            throw e;
        }
    }
    
    private String extractServiceName(URI uri) {
        // 从 URI 中提取服务名
        String host = uri.getHost();
        return host != null ? host : "unknown";
    }
}
