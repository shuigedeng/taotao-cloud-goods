package com.taotao.cloud.goods.infrastructure.adapter;

import com.taotao.cloud.goods.application.adapter.PaymentConfigPort;
import com.taotao.cloud.goods.infrastructure.configuration.properties.PaymentProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class PaymentConfigPortImpl implements PaymentConfigPort {
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
