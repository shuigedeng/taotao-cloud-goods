package com.taotao.cloud.goods.application.dto.own.goods.command;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * UnderCommand 类
 *
 * @author shuigedeng
 * @version 2022.05
 * @since 2026/1/31
 */
@Data
public class UnderCommand {
	private Long goodsId;
	private String reason ;
}
