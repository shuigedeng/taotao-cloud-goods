package com.taotao.cloud.goods.domain.goods.factory;


import com.taotao.boot.ddd.model.types.BizId;
import com.taotao.cloud.goods.domain.goods.aggregate.GoodsAgg;
import com.taotao.cloud.goods.domain.goods.valueobject.GoodsName;
import com.taotao.cloud.goods.domain.goods.valueobject.GoodsSpec;
import com.taotao.cloud.goods.domain.goods.valueobject.GoodsStatus;
import java.util.Set;

/**
 * 商品创建工厂<br/> 注：领域对象创建工厂，强调初始创建领域对象的操作（区别于技术层面的构造函数）
 */
public class GoodsFactory {


	/**
	 * 创建初始商品
	 *
	 * @param categoryId 商品分类ID
	 * @param goodsName  商品名称
	 * @param goodsSpec  商品规格
	 * @param goodsPrice 商品价格
	 * @return 初始商品
	 */
	//public static GoodsAgg createGoods(BizId categoryId, GoodsName goodsName, GoodsSpec goodsSpec,
	//	Price goodsPrice, Set<BizId> tagIds) {
	//	return new GoodsAgg(
	//		BizId.of(IdGenUtils.nextDigitalId()),
	//		categoryId,
	//		goodsName,
	//		goodsSpec,
	//		goodsPrice,
	//		GoodsStatus.UNSHELVED,
	//		tagIds
	//	);
	//}
}
