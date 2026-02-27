package com.taotao.cloud.goods.application.dto.own.goods.command;

import com.taotao.boot.ddd.model.application.dto.MarkerCommand;
import jakarta.validation.ConstraintViolationException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.io.Serial;

/**
 * UnderCommand 类
 *
 * @author shuigedeng
 * @version 2022.05
 * @since 2026/1/31
 */
public record UnderCommand(Long goodsId, String reason) implements MarkerCommand {
	@Serial
	private static final long serialVersionUID = -8823775607604091035L;

	@Override
	public void check() throws MethodArgumentNotValidException, ConstraintViolationException {
		System.out.println("========");
		MarkerCommand.super.check();
	}
}
