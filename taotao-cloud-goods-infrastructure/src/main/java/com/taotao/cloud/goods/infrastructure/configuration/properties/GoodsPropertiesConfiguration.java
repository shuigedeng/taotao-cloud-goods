package com.taotao.cloud.goods.infrastructure.configuration.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * GoodsPropertiesConfiguration 类
 *
 * @author shuigedeng
 * @version 2022.05
 * @since 2026/8/29
 */
@Configuration
@EnableConfigurationProperties({ChannelProperties.class,
	GoodsProperties.class,
	LinkProperties.class,
	PaymentProperties.class
})
public class GoodsPropertiesConfiguration {

}
