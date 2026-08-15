package com.taotao.cloud.goods.infrastructure.adapter.config;

import com.taotao.cloud.goods.application.adapter.config.PaymentConfigRepository;
import com.taotao.cloud.goods.infrastructure.configuration.properties.PaymentProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class PaymentConfigRepositoryImpl implements PaymentConfigRepository {
    private final PaymentProperties properties;
    
    @Override
    public int getMaxRetryTimes() {
        return properties.getMaxRetryTimes();
    }
    
    @Override
    public BigDecimal getDiscountRate() {
        return properties.getDiscountRate();
    }
}
