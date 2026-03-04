package com.taotao.cloud.goods.application.dto.own.goods.command;

import com.taotao.boot.common.model.ddd.types.Command;
import io.soabase.recordbuilder.core.RecordBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.io.Serial;

/**
 * UnderCommand 类
 *
 * @author shuigedeng
 * @version 2022.05
 * @since 2026/1/31
 */
@Schema(description = "商品下架Command")
@RecordBuilder
public record UnderCommand(@Schema(description = "商品id") @NotNull(message = "商品id不能为空") Long goodsId,
						   @Schema(description = "下架原因") @NotBlank(message = "下架原因不能为空") String reason)
	implements Command {

	@Serial
	private static final long serialVersionUID = 3829199991161122317L;

	@Override
	public void check() throws MethodArgumentNotValidException, ConstraintViolationException {
		System.out.println("=================");
	}
}
