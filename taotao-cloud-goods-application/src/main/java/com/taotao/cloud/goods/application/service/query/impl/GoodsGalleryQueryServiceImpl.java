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

package com.taotao.cloud.goods.application.service.query.impl;

import com.taotao.cloud.goods.application.service.query.GoodsGalleryQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 商品相册接口实现
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 17:02:38
 */
@AllArgsConstructor
@Service
public class GoodsGalleryQueryServiceImpl implements GoodsGalleryQueryService {
    //	@Override
    //	public GoodsGalleryPO getGoodsGallery(String origin) {
    //		return null;
    //	}
    //
    //	@Override
    //	public List<GoodsGalleryPO> goodsGalleryList(Long goodsId) {
    //		return List.of();
    //	}

    /// **
    // * 设置
    // */
    // @Autowired
    // private IFeignSettingApi settingApi;
    //
    // @Override
    // @Transactional(rollbackFor = Exception.class)
    // public boolean add(List<String> goodsGalleryList, Long goodsId) {
    //	// 删除原来商品相册信息
    //	this.baseMapper.delete(new UpdateWrapper<GoodsGalleryPO>().eq("goods_id", goodsId));
    //	// 确定好图片选择器后进行处理
    //	int i = 0;
    //	for (String origin : goodsGalleryList) {
    //		// 获取带所有缩略的相册
    //		GoodsGalleryPO galley = this.getGoodsGallery(origin);
    //		galley.setGoodsId(goodsId);
    //		// 默认第一个为默认图片
    //		galley.setIsDefault(i == 0 ? 1 : 0);
    //		i++;
    //		this.baseMapper.insert(galley);
    //	}
    //	return true;
    // }
    //
    // @Override
    // public GoodsGalleryPO getGoodsGallery(String origin) {
    //	GoodsGalleryPO goodsGalleryPO = new GoodsGalleryPO();
    //	// 获取商品系统配置决定是否审核
    //	GoodsSettingCO goodsSetting = settingApi.getGoodsSetting(SettingCategoryEnum.GOODS_SETTING.name());
    //	// 缩略图
    //	String thumbnail = FileUtils.getUrl(
    //		origin, goodsSetting.getAbbreviationPictureWidth(), goodsSetting.getAbbreviationPictureHeight());
    //	// 小图
    //	String small =
    //		FileUtils.getUrl(origin, goodsSetting.getSmallPictureWidth(), goodsSetting.getSmallPictureHeight());
    //	// 赋值
    //	goodsGalleryPO.setSmall(small);
    //	goodsGalleryPO.setThumbnail(thumbnail);
    //	goodsGalleryPO.setOriginal(origin);
    //	return goodsGalleryPO;
    // }
    //
    // @Override
    // public List<GoodsGalleryPO> goodsGalleryList(Long goodsId) {
    //	// 根据商品id查询商品相册
    //	LambdaQueryWrapper<GoodsGalleryPO> queryWrapper = Wrappers.lambdaQuery();
    //	queryWrapper.eq(GoodsGalleryPO::getGoodsId, goodsId);
    //	return this.list(queryWrapper);
    // }
}
