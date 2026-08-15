package com.taotao.cloud.goods.infrastructure.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

/**
 * PaymentProperties 类
 *
 * @author shuigedeng
 * @version 2022.05
 * @since 2026/8/15
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "taotao.cloud.goods.payment")
public class PaymentProperties {

	public int getMaxRetryTimes() {
		return 0;
	}

	public BigDecimal getDiscountRate() {
		return null;
	}
}
