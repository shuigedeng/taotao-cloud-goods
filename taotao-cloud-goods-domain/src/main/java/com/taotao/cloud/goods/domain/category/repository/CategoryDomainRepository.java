package com.taotao.cloud.goods.domain.category.repository;

import com.taotao.boot.ddd.model.domain.repository.DomainRepository;
import com.taotao.cloud.goods.domain.category.entity.Category;

public interface CategoryDomainRepository extends DomainRepository {

	/**
	 * 新增部门.
	 *
	 * @param categoryEntity 部门对象
	 */
	void create(Category categoryEntity);

	/**
	 * 修改部门.
	 *
	 * @param categoryEntity 部门对象
	 */
	void modify(Category categoryEntity);

	/**
	 * 根据ID删除部门.
	 *
	 * @param ids IDS
	 */
	void remove(Long[] ids);
}
