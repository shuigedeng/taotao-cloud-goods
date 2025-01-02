package com.taotao.cloud.goods.domain.goods.repository;

import com.taotao.boot.ddd.model.domain.repository.DomainRepository;
import com.taotao.cloud.goods.domain.goods.aggregate.GoodsAgg;

public interface GoodsDomainRepository extends DomainRepository {
	/**
	 * 新增部门.
	 *
	 * @param dept 部门对象
	 */
	void create(GoodsAgg dept);

	/**
	 * 修改部门.
	 *
	 * @param dept 部门对象
	 */
	void modify(GoodsAgg dept);

	/**
	 * 根据ID删除部门.
	 *
	 * @param ids IDS
	 */
	void remove(Long[] ids);
}
