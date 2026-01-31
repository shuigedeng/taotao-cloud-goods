package com.taotao.cloud.goods.application.dto.own.category.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * CategoryBrandsCommand 类
 *
 * @author shuigedeng
 * @version 2022.05
 * @since 2026/1/31
 */
@Data
public class CategoryBrandsCommand {

	private Long categoryId;
	private List<Long> categoryBrands;
}
