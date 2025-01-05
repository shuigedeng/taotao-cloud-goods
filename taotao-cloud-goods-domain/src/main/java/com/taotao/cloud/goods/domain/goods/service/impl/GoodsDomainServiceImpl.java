package com.taotao.cloud.goods.domain.goods.service.impl;

import com.taotao.cloud.goods.domain.goods.aggregate.GoodsAgg;
import com.taotao.cloud.goods.domain.goods.repository.GoodsDomainRepository;
import com.taotao.cloud.goods.domain.goods.service.GoodsDomainService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GoodsDomainServiceImpl implements GoodsDomainService {

	private GoodsDomainRepository deptDomainRepository;

	@Override
	public void create(GoodsAgg dept) {

	}

	@Override
	public void remove(Long[] ids) {

	}
}

