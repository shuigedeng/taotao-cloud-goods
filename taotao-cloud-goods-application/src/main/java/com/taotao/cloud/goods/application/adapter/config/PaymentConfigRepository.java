package com.taotao.cloud.goods.application.adapter.config;

import java.math.BigDecimal;

public interface PaymentConfigRepository {
    int getMaxRetryTimes();
    BigDecimal getDiscountRate();
}
