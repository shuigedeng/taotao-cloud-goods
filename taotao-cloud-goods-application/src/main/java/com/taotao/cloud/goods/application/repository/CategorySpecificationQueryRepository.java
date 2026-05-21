package com.taotao.cloud.goods.application.repository;

import com.taotao.cloud.goods.application.dto.specification.result.SpecificationResult;

import java.util.List;

/**
 * CategorySpecificationQ 类
 *
 * @author shuigedeng
 * @version 2022.05
 * @since 2026/4/12
 */
public interface CategorySpecificationQueryRepository {

	List<SpecificationResult> queryCategorySpecList( Long categoryId );
}
