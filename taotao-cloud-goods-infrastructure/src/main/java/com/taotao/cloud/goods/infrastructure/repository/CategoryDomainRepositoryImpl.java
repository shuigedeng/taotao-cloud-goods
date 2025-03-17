package com.taotao.cloud.goods.infrastructure.repository;

import com.taotao.boot.data.datasource.tx.TransactionalUtil;
import com.taotao.boot.data.mybatis.utils.MybatisUtil;
import com.taotao.boot.ddd.model.types.BizId;
import com.taotao.boot.ddd.util.validation.Validates;
import com.taotao.cloud.goods.domain.category.aggregate.CategoryAgg;
import com.taotao.cloud.goods.domain.category.repository.CategoryDomainRepository;
import com.taotao.cloud.goods.infrastructure.persistent.mapper.GoodsMapper;
import com.taotao.cloud.goods.infrastructure.persistent.mapper.GoodsSkuMapper;
import com.taotao.cloud.goods.infrastructure.persistent.repository.cls.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryDomainRepositoryImpl implements CategoryDomainRepository {


	private final TransactionalUtil transactionalUtil;
	private final MybatisUtil mybatisUtil;
	private final GoodsMapper goodsMapper;
	private final GoodsSkuMapper goodsSkuMapper;
	private final CategoryRepository categoryRepository;

	@Override
	public void create(CategoryAgg categoryEntity) {

	}

	@Override
	public void modify(CategoryAgg categoryEntity) {

	}

	@Override
	public void remove(Long[] ids) {

	}

	@Override
	public boolean isSatisfiedBy(BizId categoryId) {
		//验证分类ID是否存在
		Boolean existParentId = this.categoryRepository.existsById(categoryId.getId());
		Validates.isTrue(existParentId, "category id does not exist");
		return true;
	}
}
