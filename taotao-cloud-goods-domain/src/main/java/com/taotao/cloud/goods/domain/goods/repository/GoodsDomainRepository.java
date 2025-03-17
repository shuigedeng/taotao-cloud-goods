package com.taotao.cloud.goods.domain.goods.repository;

import com.taotao.boot.ddd.model.domain.repository.DomainRepository;
import com.taotao.boot.ddd.model.types.BizId;
import com.taotao.cloud.goods.domain.goods.aggregate.GoodsAgg;
import com.taotao.cloud.goods.domain.goods.valobj.GoodsStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

public interface GoodsDomainRepository extends DomainRepository {

	void save(GoodsAgg goods);

	/**
	 * 查询指定商品ID集合对应的商品数量
	 *
	 * @param ids 商品ID集合
	 * @return 存在的商品数量
	 */
	Integer countByIdIn(Collection<BizId> ids);

	/**
	 * 分类下是否存在商品
	 *
	 * @param categoryIds 分类ID列表
	 * @return 是否存在
	 */
	Boolean existsByCategoryIdIn(Collection<BizId> categoryIds);

	/**
	 * 是否存在指定状态（上架、下架）的商品
	 *
	 * @param goodsIds    商品ID列表
	 * @param goodsStatus 商品状态
	 * @return 是否存在
	 */
	Boolean existsByIdInAndGoodsStatus(Collection<BizId> goodsIds, GoodsStatus goodsStatus);

	/**
	 * 是否存在已上架的商品
	 *
	 * @param goodsIds 商品ID列表
	 * @return 是否存在
	 */
	Boolean existsShelvedGoodsByIdIn(Collection<BizId> goodsIds);

	/**
	 * 批量更新商品状态
	 *
	 * @param goodsIds    商品ID列表
	 * @param goodsStatus 商品状态
	 */
	void batchModifyGoodsStatus(Collection<BizId> goodsIds, GoodsStatus goodsStatus);

	/**
	 * 根据ID查询商品详情
	 *
	 * @param id 商品ID
	 * @return 商品详情
	 */
	GoodsAgg findGoodsWithCNameById(Long id);


	/**
	 * 标签下是否存在商品
	 *
	 * @param tagIds 标签ID列表
	 * @return 是否存在
	 */
	Boolean existsByTagIds(Collection<Long> tagIds);


	/**
	 * 查询商品分页列表<br/>
	 * 注：此处Pageable参数中的Sort排序不生效
	 *
	 * @param goodsName
	 * @param categoryId
	 * @param startGoodsPrice
	 * @param endGoodsPrice
	 * @param goodsStatus
	 * @param beforeExpirationDate
	 * @param createTimeStart
	 * @param createTimeEnd
	 * @param pageable
	 * @return
	 */
	Page<GoodsAgg> findGoodsWithCNamePageJpa(String goodsName,
											 Long categoryId,
											 BigDecimal startGoodsPrice,
											 BigDecimal endGoodsPrice,
											 Integer goodsStatus,
											 LocalDate beforeExpirationDate,
											 LocalDateTime createTimeStart,
											 LocalDateTime createTimeEnd,
											 Pageable pageable);


}
