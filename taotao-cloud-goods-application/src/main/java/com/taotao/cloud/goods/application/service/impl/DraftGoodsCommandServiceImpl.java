/*
 * Copyright (c) 2020-2030, Shuigedeng (981376577@qq.com & https://blog.taotaocloud.top/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.taotao.cloud.goods.application.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taotao.cloud.goods.application.command.draft.dto.DraftGoodsPageQry;
import com.taotao.cloud.goods.application.command.draft.dto.DraftGoodsSkuParamsAddCmd;
import com.taotao.cloud.goods.application.command.goods.dto.GoodsParamsAddCmd;
import com.taotao.cloud.goods.application.convert.DraftGoodsConvert;
import com.taotao.cloud.goods.application.service.CategoryCommandService;
import com.taotao.cloud.goods.application.service.DraftGoodsCommandService;
import com.taotao.cloud.goods.application.service.GoodsGalleryCommandService;
import com.taotao.cloud.goods.application.service.GoodsSkuCommandService;
import com.taotao.cloud.goods.infrastructure.persistent.mapper.IDraftGoodsMapper;
import com.taotao.cloud.goods.infrastructure.persistent.po.DraftGoodsPO;
import com.taotao.cloud.goods.infrastructure.persistent.po.GoodsGalleryPO;
import com.taotao.cloud.goods.infrastructure.persistent.po.GoodsSkuPO;
import com.taotao.cloud.goods.infrastructure.persistent.repository.cls.DraftGoodsRepository;
import com.taotao.cloud.goods.infrastructure.persistent.repository.inf.IDraftGoodsRepository;
import com.taotao.boot.web.base.service.impl.BaseSuperServiceImpl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 草稿商品业务层实现
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 17:02:24
 */
@AllArgsConstructor
@Service
public class DraftGoodsCommandServiceImpl
        extends BaseSuperServiceImpl<DraftGoodsPO, Long, IDraftGoodsMapper, DraftGoodsRepository, IDraftGoodsRepository>
        implements DraftGoodsCommandService {

    /** 分类 */
    private final CategoryCommandService categoryService;
    /** 商品相册 */
    private final GoodsGalleryCommandService goodsGalleryService;
    /** 规格商品 */
    private final GoodsSkuCommandService goodsSkuService;

    @Override
    public boolean addGoodsDraft(DraftGoodsSkuParamsAddCmd draftGoods) {
        draftGoods.setGoodsGalleryListJson(JSONUtil.toJsonStr(draftGoods.getGoodsGalleryList()));
        draftGoods.setSkuListJson(JSONUtil.toJsonStr(draftGoods.getSkuList()));
        draftGoods.setGoodsParamsListJson(JSONUtil.toJsonStr(draftGoods.getGoodsParamsDTOList()));

        return this.save(DraftGoodsConvert.INSTANCE.convert(draftGoods));
    }

    @Override
    public boolean updateGoodsDraft(DraftGoodsSkuParamsAddCmd draftGoods) {
        draftGoods.setGoodsGalleryListJson(JSONUtil.toJsonStr(draftGoods.getGoodsGalleryList()));
        draftGoods.setSkuListJson(JSONUtil.toJsonStr(draftGoods.getSkuList()));
        draftGoods.setGoodsParamsListJson(JSONUtil.toJsonStr(draftGoods.getGoodsParamsDTOList()));

        DraftGoodsPO draftGoodsPO1 = DraftGoodsConvert.INSTANCE.convert(draftGoods);
        // todo 此处需要修改
        draftGoodsPO1.setId(0L);

        return this.updateById(draftGoodsPO1);
    }

    @Override
    public boolean saveGoodsDraft(DraftGoodsSkuParamsAddCmd draftGoods) {
        if (draftGoods.getGoodsGalleryList() != null
                && !draftGoods.getGoodsGalleryList().isEmpty()) {
            GoodsGalleryPO goodsGalleryPO = goodsGalleryService.getGoodsGallery(
                    draftGoods.getGoodsGalleryList().get(0));
            draftGoods.setOriginal(goodsGalleryPO.getOriginal());
            draftGoods.setSmall(goodsGalleryPO.getSmall());
            draftGoods.setThumbnail(goodsGalleryPO.getThumbnail());
        }
        draftGoods.setGoodsGalleryListJson(JSONUtil.toJsonStr(draftGoods.getGoodsGalleryList()));
        draftGoods.setSkuListJson(JSONUtil.toJsonStr(this.getGoodsSkuList(draftGoods.getSkuList())));
        draftGoods.setGoodsParamsListJson(JSONUtil.toJsonStr(draftGoods.getGoodsParamsDTOList()));

        return this.saveOrUpdate(DraftGoodsConvert.INSTANCE.convert(draftGoods));
    }

    @Override
    public boolean deleteGoodsDraft(Long id) {
        return this.removeById(id);
    }

    @Override
    public DraftGoodsSkuParamsCO getDraftGoods(Long id) {
        DraftGoodsPO draftGoodsPO = this.getById(id);
        DraftGoodsSkuParamsCO draftGoodsSkuParamsCO = new DraftGoodsSkuParamsCO();
        BeanUtil.copyProperties(draftGoodsPO, draftGoodsSkuParamsCO);

        // 商品分类名称赋值
        List<String> categoryName = new ArrayList<>();
        String[] strArray = draftGoodsPO.getCategoryPath().split(",");
        List<Category> categories = categoryService.listByIds(Arrays.asList(strArray));
        for (Category category : categories) {
            categoryName.add(category.getName());
        }
        draftGoodsSkuParamsCO.setCategoryName(categoryName);
        draftGoodsSkuParamsCO.setGoodsParamsDTOList(
                JSONUtil.toList(JSONUtil.parseArray(draftGoodsPO.getGoodsParamsListJson()), GoodsParamsAddCmd.class));
        draftGoodsSkuParamsCO.setGoodsGalleryList(
                JSONUtil.toList(JSONUtil.parseArray(draftGoodsPO.getGoodsGalleryListJson()), String.class));
        JSONArray jsonArray = JSONUtil.parseArray(draftGoodsPO.getSkuListJson());
        List<GoodsSkuPO> list = JSONUtil.toList(jsonArray, GoodsSkuPO.class);
        draftGoodsSkuParamsCO.setSkuList(goodsSkuService.getGoodsSkuCOList(list));
        return draftGoodsSkuParamsCO;
    }

    @Override
    public IPage<DraftGoodsPO> draftGoodsQueryPage(DraftGoodsPageQry searchParams) {
        return this.page(searchParams.buildMpPage(), QueryUtil.draftGoodsQueryWrapper(searchParams));
    }

    /**
     * 获取sku集合
     *
     * @param skuList sku列表
     * @return sku集合
     */
    private List<GoodsSkuPO> getGoodsSkuList(List<Map<String, Object>> skuList) {
        List<GoodsSkuPO> skus = new ArrayList<>();
        for (Map<String, Object> skuCO : skuList) {
            GoodsSkuPO add = this.add(skuCO);
            skus.add(add);
        }
        return skus;
    }

    private GoodsSkuPO add(Map<String, Object> map) {
        Map<String, Object> specMap = new HashMap<>(2);
        GoodsSkuPO sku = new GoodsSkuPO();
        for (Map.Entry<String, Object> m : map.entrySet()) {
            switch (m.getKey()) {
                case "sn" -> sku.setSn(m.getValue() != null ? m.getValue().toString() : "");
                case "cost" -> sku.setCost(Convert.toBigDecimal(m.getValue()));
                case "price" -> sku.setPrice(Convert.toBigDecimal(m.getValue()));
                case "quantity" -> sku.setQuantity(Convert.toInt(m.getValue()));
                case "weight" -> sku.setWeight(Convert.toBigDecimal(m.getValue()));
                default -> specMap.put(m.getKey(), m.getValue());
            }
        }
        sku.setSpecs(JSONUtil.toJsonStr(specMap));
        return sku;
    }
}
