package com.taotao.cloud.goods.domain.goodstag.service.impl;

import com.taotao.cloud.goods.domain.goodstag.aggregate.GoodsTagAgg;
import com.taotao.cloud.goods.domain.goodstag.repository.GoodsTagDomainRepository;
import com.taotao.cloud.goods.domain.goodstag.service.GoodsTagDomainService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GoodsTagDomainServiceImpl implements GoodsTagDomainService {

	private GoodsTagDomainRepository deptDomainRepository;

	@Override
	public void create(GoodsTagAgg dept) {

	}

	@Override
	public void remove(Long[] ids) {

	}
}
