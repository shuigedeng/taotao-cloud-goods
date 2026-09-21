package com.taotao.cloud.goods.application.adapter;

import java.math.BigDecimal;

public interface PaymentConfigPort {
    int getMaxRetryTimes();
    BigDecimal getDiscountRate();
}
