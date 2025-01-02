package com.taotao.cloud.goods.domain.goodstag.factory;


import com.taotao.boot.ddd.model.types.BizId;
import com.taotao.cloud.goods.domain.goodstag.aggregate.GoodsTagAgg;

/**
 * 商品标签创建工厂<br/>
 * 注：领域对象创建工厂，强调初始创建领域对象的操作（区别于技术层面的构造函数）
 *
 * @author luohq
 * @date 2023-08-03
 */
public class GoodsTagFactory {

    /**
     * 创建商品标签
     *
     * @param tagName 商品标签名称
     * @param tagDesc 商品标签描述
     * @return 初始商品标签
     */
    public static GoodsTagAgg createGoodsTag(String tagName, String tagDesc) {
        return new GoodsTagAgg(
                BizId.of(IdGenUtils.nextDigitalId()),
                tagName,
                tagDesc
        );
    }
}
