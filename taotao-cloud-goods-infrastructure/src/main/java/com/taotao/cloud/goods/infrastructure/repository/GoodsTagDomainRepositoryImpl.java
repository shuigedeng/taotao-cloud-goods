package com.taotao.cloud.goods.infrastructure.repository;

import com.taotao.boot.data.datasource.tx.TransactionalUtil;
import com.taotao.boot.data.mybatis.utils.MybatisUtil;
import com.taotao.boot.ddd.model.types.BizId;
import com.taotao.boot.ddd.util.validation.Validates;
import com.taotao.cloud.goods.domain.goodstag.aggregate.GoodsTagAgg;
import com.taotao.cloud.goods.domain.goodstag.repository.GoodsTagDomainRepository;
import com.taotao.cloud.goods.infrastructure.persistent.mapper.GoodsMapper;
import com.taotao.cloud.goods.infrastructure.persistent.mapper.GoodsSkuMapper;
import com.taotao.cloud.goods.infrastructure.persistent.repository.cls.GoodsTagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

@Service
@AllArgsConstructor
public class GoodsTagDomainRepositoryImpl implements GoodsTagDomainRepository {


	private final TransactionalUtil transactionalUtil;
	private final MybatisUtil mybatisUtil;
	private final GoodsMapper goodsMapper;
	private final GoodsSkuMapper goodsSkuMapper;
	private final GoodsTagRepository goodsTagRepository;

	@Override
	public void create(GoodsTagAgg dept) {

	}

	@Override
	public void modify(GoodsTagAgg dept) {

	}

	@Override
	public void remove(Long[] ids) {

	}

	@Override
	public boolean isSatisfiedBy(Collection<BizId> goodsTagIds) {
		//若标签ID集合为空，则直接验证通过
		if (Objects.isNull(goodsTagIds) || goodsTagIds.isEmpty()) {
			return true;
		}
		//查询存在的标签数量
		Integer existGoodsTagCount = this.goodsTagRepository.countByIdIn(goodsTagIds);
		Validates.isTrue(existGoodsTagCount.equals(goodsTagIds.size()), "goods tags don't exist");
		return true;
	}
}
