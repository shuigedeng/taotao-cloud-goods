package com.taotao.cloud.goods.application.dto.own.category.command;

import lombok.Data;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * DisableCommand 类
 *
 * @author shuigedeng
 * @version 2022.05
 * @since 2026/1/31
 */
@Data
public class DisableCommand {
	private Long id;
	private Boolean
		enableOperations;
}
