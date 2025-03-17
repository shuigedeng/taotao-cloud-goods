package com.taotao.cloud.goods.domain.category.repository;

import com.taotao.boot.ddd.model.domain.repository.DomainRepository;
import com.taotao.boot.ddd.model.types.BizId;
import com.taotao.cloud.goods.domain.category.aggregate.CategoryAgg;

public interface CategoryDomainRepository extends DomainRepository {

	/**
	 * 新增部门.
	 *
	 * @param categoryEntity 部门对象
	 */
	void create(CategoryAgg categoryEntity);

	/**
	 * 修改部门.
	 *
	 * @param categoryEntity 部门对象
	 */
	void modify(CategoryAgg categoryEntity);

	/**
	 * 根据ID删除部门.
	 *
	 * @param ids IDS
	 */
	void remove(Long[] ids);

	boolean isSatisfiedBy(BizId categoryId);
}
