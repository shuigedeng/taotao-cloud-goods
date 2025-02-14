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

package com.taotao.cloud.goods.application.service;

import com.taotao.boot.ddd.model.application.service.CommandService;

/**
 * 店铺商品分类业务层
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 17:01:05
 */
public interface StoreGoodsLabelCommandService extends CommandService {


	/**
	 * 添加商品分类
	 *
	 * @param storeGoodsLabelPO 店铺商品分类
	 * @return 店铺商品分类
	 */
//	boolean addStoreGoodsLabel(StoreGoodsLabelPO storeGoodsLabelPO);

	/**
	 * 修改商品分类
	 *
	 * @param storeGoodsLabelPO 店铺商品分类
	 * @return 店铺商品分类
	 */
//	boolean editStoreGoodsLabel(StoreGoodsLabelPO storeGoodsLabelPO);

	/**
	 * 删除商品分类
	 *
	 * @param storeLabelId 店铺 分类 ID
	 */
	boolean removeStoreGoodsLabel(Long storeLabelId);
}
