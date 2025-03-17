package com.taotao.cloud.goods.application.dto.goods.cmmond;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

/**
 * 创建商品Command
 *
 */
@Data
public class GoodsCreateCommand {

    /**
     * 所属分类ID
     */
    private Long categoryId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 生产日期
     */
    private LocalDate manufactureDate;

    /**
     * 过期日期
     */
    private LocalDate expirationDate;

    /**
     * 商品重量
     */
    private BigDecimal goodsWeight;

    /**
     * 商品重量单位
     */
    private String goodsWeightUnit;

    /**
     * 商品介绍
     */
    private String goodsDesc;

    /**
     * 商品价格
     */
    private BigDecimal goodsPrice;

    /**
     * 商品标签ID集合
     */
    private Set<Long> tagIds;

    /**
     * 创建商品
     *
     * @return 商品
     */
//    public GoodsAgg createGoods() {
//        return GoodsFactory.createGoods(
//                BizId.fromValue(this.getCategoryId()),
//                GoodsName.of(this.getGoodsName()),
//                GoodsSpec.builder()
//                        .manufactureDate(this.getManufactureDate())
//                        .expirationDate(this.getExpirationDate())
//                        .goodsWeight(GoodsWeight.of(this.getGoodsWeight(), WeightUnit.of(this.getGoodsWeightUnit())))
//                        .goodsDesc(this.getGoodsDesc())
//                        .build(),
//                Price.of(this.getGoodsPrice()),
//                BizId.fromNullableValues(tagIds)
//        );
//
//    }
}
