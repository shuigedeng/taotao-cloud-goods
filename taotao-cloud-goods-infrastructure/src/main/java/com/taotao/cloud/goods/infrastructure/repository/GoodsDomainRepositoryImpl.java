package com.taotao.cloud.goods.infrastructure.repository;

import com.taotao.boot.data.datasource.tx.TransactionalUtil;
import com.taotao.boot.data.mybatis.utils.MybatisUtil;
import com.taotao.cloud.goods.domain.goods.aggregate.GoodsAgg;
import com.taotao.cloud.goods.domain.goods.repository.GoodsDomainRepository;
import com.taotao.cloud.goods.infrastructure.persistent.mapper.GoodsMapper;
import com.taotao.cloud.goods.infrastructure.persistent.mapper.GoodsSkuMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GoodsDomainRepositoryImpl implements GoodsDomainRepository {


	private final TransactionalUtil transactionalUtil;
	private final MybatisUtil mybatisUtil;
	private final GoodsMapper goodsMapper;
	private final GoodsSkuMapper goodsSkuMapper;

	@Override
	public void create(GoodsAgg dept) {

	}

	@Override
	public void modify(GoodsAgg dept) {

	}

	@Override
	public void remove(Long[] ids) {

	}
}
