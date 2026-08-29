package com.taotao.cloud.goods.infrastructure.configuration.properties;

import com.taotao.boot.lock.autoconfigure.properties.LockProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * ChannelProperties
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2025-12-19 09:30:45
 */
@Data
@ConfigurationProperties(prefix = "taotao.cloud.goods.channel")
public class ChannelProperties {

	private int id;
}
