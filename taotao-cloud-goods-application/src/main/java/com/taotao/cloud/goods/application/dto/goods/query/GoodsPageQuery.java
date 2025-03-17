package com.taotao.cloud.goods.application.dto.goods.query;

import com.taotao.boot.common.model.PageQuery;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 商品分页查询
 *
 * @author luohq
 * @date 2022-11-27 19:07
 */
@Data
@ToString(callSuper = true)
public class GoodsPageQuery extends PageQuery {
	/**
	 * 商品名称
	 */
	private String goodsName;

	/**
	 * 商品分类ID
	 */
	private Long categoryId;

	/**
	 * 起始商品价格
	 */
	private BigDecimal startGoodsPrice;

	/**
	 * 终止商品价格
	 */
	private BigDecimal endGoodsPrice;


	/**
	 * 商品状态(10已上架, 20已下架)
	 */
	private Integer goodsStatus;

	/**
	 * 过期时间（在此之前过期）
	 */
	private LocalDate beforeExpirationDate;
	/**
	 * 起始创建时间
	 */
	private LocalDateTime createTimeStart;

	/**
	 * 结束创建时间
	 */
	private LocalDateTime createTimeEnd;
}
