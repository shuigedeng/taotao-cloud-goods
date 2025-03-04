package com.taotao.cloud.goods.domain.category.factory;


import com.taotao.boot.ddd.model.types.BizId;
import com.taotao.cloud.goods.domain.category.aggregate.CategoryAgg;
import com.taotao.cloud.goods.domain.category.valueobject.CategoryDesc;
import com.taotao.cloud.goods.domain.category.valueobject.CategoryName;
import java.time.LocalDateTime;

/**
 * 分类创建工厂<br/> 注：领域对象创建工厂，强调初始创建领域对象的操作（区别于技术层面的构造函数）
 */
public class CategoryFactory {

	/**
	 * 创建分类
	 *
	 * @param parentCategoryId 上级分类ID
	 * @param categoryName     分类名称
	 * @param categoryDesc     分类描述
	 * @return 初始分类
	 */
	//public static CategoryAgg createCategory(BizId parentCategoryId, CategoryName categoryName,
	//	CategoryDesc categoryDesc) {
	//	LocalDateTime now = LocalDateTime.now();
	//	return new CategoryAgg(
	//		BizId.of(IdGenUtils.nextDigitalId()),
	//		parentCategoryId,
	//		categoryName,
	//		categoryDesc
	//	);
	//}
}
