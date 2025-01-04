package com.taotao.cloud.goods.infrastructure.repository;

import com.taotao.boot.data.datasource.tx.TransactionalUtil;
import com.taotao.boot.data.mybatis.utils.MybatisUtil;
import com.taotao.cloud.goods.domain.goods.aggregate.GoodsAgg;
import com.taotao.cloud.goods.domain.goods.repository.GoodsDomainRepository;
import com.taotao.cloud.goods.domain.goodstag.aggregate.GoodsTagAgg;
import com.taotao.cloud.goods.domain.goodstag.repository.GoodsTagDomainRepository;
import com.taotao.cloud.goods.infrastructure.persistent.mapper.GoodsMapper;
import com.taotao.cloud.goods.infrastructure.persistent.mapper.GoodsSkuMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GoodsTagDomainRepositoryImpl implements GoodsTagDomainRepository {


	private final TransactionalUtil transactionalUtil;
	private final MybatisUtil mybatisUtil;
	private final GoodsMapper goodsMapper;
	private final GoodsSkuMapper goodsSkuMapper;

	@Override
	public void create(GoodsTagAgg dept) {

	}

	@Override
	public void modify(GoodsTagAgg dept) {

	}

	@Override
	public void remove(Long[] ids) {

	}
}
