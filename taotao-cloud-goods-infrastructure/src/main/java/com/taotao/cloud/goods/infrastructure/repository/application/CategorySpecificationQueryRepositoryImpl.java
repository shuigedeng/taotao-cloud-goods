package com.taotao.cloud.goods.infrastructure.repository.application;

import com.taotao.cloud.goods.application.dto.specification.result.SpecificationResult;
import com.taotao.cloud.goods.application.repository.CategorySpecificationQueryRepository;
import com.taotao.cloud.goods.infrastructure.assembler.SpecificationInfraAssembler;
import com.taotao.cloud.goods.infrastructure.persistent.mapper.CategorySpecificationMapper;
import com.taotao.cloud.goods.infrastructure.persistent.persistence.SpecificationPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CategorySpecificationQueryRepositoryImpl 类
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
	public List<SpecificationResult> getCategorySpecList( Long categoryId ) {

		List<SpecificationPO> categorySpecificationPO = categorySpecificationMapper.getCategorySpecList(categoryId);

		return specificationInfraAssembler.toResult(categorySpecificationPO);
	}
}
