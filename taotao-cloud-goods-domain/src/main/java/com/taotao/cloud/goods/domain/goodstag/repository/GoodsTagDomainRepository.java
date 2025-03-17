package com.taotao.cloud.goods.domain.goodstag.repository;

import com.taotao.boot.ddd.model.domain.repository.DomainRepository;
import com.taotao.boot.ddd.model.types.BizId;
import com.taotao.cloud.goods.domain.goodstag.aggregate.GoodsTagAgg;

import java.util.Collection;

public interface GoodsTagDomainRepository extends DomainRepository {
	/**
	 * 新增部门.
	 *
	 * @param dept 部门对象
	 */
	void create(GoodsTagAgg dept);

	/**
	 * 修改部门.
	 *
	 * @param dept 部门对象
	 */
	void modify(GoodsTagAgg dept);

	/**
	 * 根据ID删除部门.
	 *
	 * @param ids IDS
	 */
	void remove(Long[] ids);

	boolean isSatisfiedBy(Collection<BizId> goodsTagIds);
}
