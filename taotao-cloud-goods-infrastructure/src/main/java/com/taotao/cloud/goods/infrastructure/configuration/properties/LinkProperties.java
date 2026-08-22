package com.taotao.cloud.goods.infrastructure.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * LinkProperties
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2025-12-19 09:30:45
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "taotao.cloud.goods.link")
@EnableConfigurationProperties({LinkProperties.class})
public class LinkProperties {
private String link;
}

