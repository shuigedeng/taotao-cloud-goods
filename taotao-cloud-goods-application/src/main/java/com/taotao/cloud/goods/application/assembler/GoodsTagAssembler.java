package com.taotao.cloud.goods.application.assembler;


import com.taotao.cloud.goods.domain.goodstag.aggregate.GoodsTagAgg;

/**
 * GoodsTag转换器
 *
 * @author luohq
 * @date 2023-08-03
 */
public class GoodsTagAssembler {

    /**
     * 转换商品标签实体为VO
     *
     * @param goodsTag 商品标签实体
     * @return 商品标签VO
     */
    public static GoodsTagVo toGoodsTagVo(GoodsTagAgg goodsTag) {
        if (null == goodsTag) {
            return null;
        }
        GoodsTagVo goodsTagVo = new GoodsTagVo();
        goodsTagVo.setId(goodsTag.getId().getValue());
        goodsTagVo.setTagName(goodsTag.getTagName());
        goodsTagVo.setTagDesc(goodsTag.getTagDesc());
        goodsTagVo.setCreateTime(goodsTag.getCreateTime());
        goodsTagVo.setUpdateTime(goodsTag.getUpdateTime());
        return goodsTagVo;
    }
}
