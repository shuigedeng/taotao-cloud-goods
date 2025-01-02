package com.taotao.cloud.goods.domain.category.service.impl;

import com.taotao.cloud.goods.domain.category.aggregate.CategoryAgg;
import com.taotao.cloud.goods.domain.category.repository.CategoryDomainRepository;
import com.taotao.cloud.goods.domain.category.service.CategoryDomainService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryDomainServiceImpl implements CategoryDomainService {

	private CategoryDomainRepository categoryDomainRepository;

	@Override
	public void create(CategoryAgg dept) {

	}

	@Override
	public void modify(CategoryAgg dept) {

	}

	@Override
	public void remove(Long[] ids) {

	}
}
