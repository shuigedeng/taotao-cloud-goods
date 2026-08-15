package com.taotao.cloud.goods.infrastructure.adapter.repository;

import com.taotao.cloud.goods.application.dto.specification.result.SpecificationResult;
import com.taotao.cloud.goods.application.adapter.repository.CategorySpecificationQueryRepository;
import com.taotao.cloud.goods.infrastructure.assembler.SpecificationInfraAssembler;
import com.taotao.cloud.goods.infrastructure.persistent.mapper.CategorySpecificationMapper;
import com.taotao.cloud.goods.infrastructure.persistent.persistence.SpecificationPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品分类规格查询仓储实现
 * <p>
 * 实现 CategorySpecificationQueryRepository 接口，提供商品分类规格的查询持久化操作
 * </p>
 *
 * @author shuigedeng
 * @version 2022.05
 * @since 2026/4/12
 */
@Service
@RequiredArgsConstructor
public class CategorySpecificationQueryRepositoryImpl implements CategorySpecificationQueryRepository {

	private final CategorySpecificationMapper categorySpecificationMapper;
	private final SpecificationInfraAssembler specificationInfraAssembler;

	@Override
	public List<SpecificationResult> queryCategorySpecList( Long categoryId ) {

		List<SpecificationPO> categorySpecificationPO = categorySpecificationMapper.selectCategorySpec(categoryId);

		return specificationInfraAssembler.toResult(categorySpecificationPO);
	}
}
