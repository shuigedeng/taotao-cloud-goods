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

package com.taotao.cloud.goods.application.service.command.impl;

import com.taotao.boot.cache.redis.repository.RedisRepository;
import com.taotao.cloud.goods.application.service.command.StoreGoodsLabelCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 店铺商品分类业务层实现
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 17:02:58
 */
@Service
public class StoreGoodsLabelCommandServiceImpl implements StoreGoodsLabelCommandService {

    /**
     * 缓存
     */
    @Autowired private RedisRepository redisRepository;

    @Override
    public boolean removeStoreGoodsLabel(Long storeLabelId) {
        return false;
    }

    //	@Override
    //	public boolean addStoreGoodsLabel(StoreGoodsLabelPO storeGoodsLabelPO) {
    //		return false;
    //	}
    //
    //	@Override
    //	public boolean editStoreGoodsLabel(StoreGoodsLabelPO storeGoodsLabelPO) {
    //		return false;
    //	}
    //
    //	@Override
    //	public boolean removeStoreGoodsLabel(Long storeLabelId) {
    //		return false;
    //	}

    // @Override
    // @Transactional(rollbackFor = Exception.class)
    // public boolean addStoreGoodsLabel(StoreGoodsLabelPO storeGoodsLabelPO) {
    //	// 获取当前登录商家账号
    //	SecurityUser tokenUser = SecurityUtils.getCurrentUser();
    //	storeGoodsLabelPO.setStoreId(tokenUser.getStoreId());
    //	// 保存店铺分类
    //	this.save(storeGoodsLabelPO);
    //	// 清除缓存
    //	removeCache(storeGoodsLabelPO.getStoreId());
    //	return true;
    // }
    //
    // @Override
    // @Transactional(rollbackFor = Exception.class)
    // public boolean editStoreGoodsLabel(StoreGoodsLabelPO storeGoodsLabelPO) {
    //	// 修改当前店铺的商品分类
    //	SecurityUser tokenUser = SecurityUtils.getCurrentUser();
    //
    //	LambdaUpdateWrapper<StoreGoodsLabelPO> lambdaUpdateWrapper = Wrappers.lambdaUpdate();
    //	lambdaUpdateWrapper.eq(StoreGoodsLabelPO::getStoreId, tokenUser.getStoreId());
    //	lambdaUpdateWrapper.eq(StoreGoodsLabelPO::getId, storeGoodsLabelPO.getId());
    //	// 修改店铺分类
    //	this.update(storeGoodsLabelPO, lambdaUpdateWrapper);
    //	// 清除缓存
    //	removeCache(storeGoodsLabelPO.getStoreId());
    //	return true;
    // }
    //
    // @Override
    // public boolean removeStoreGoodsLabel(Long storeLabelId) {
    //	SecurityUser tokenUser = SecurityUtils.getCurrentUser();
    //	if (tokenUser == null || Objects.isNull(tokenUser.getStoreId())) {
    //		throw new BusinessException(ResultEnum.USER_NOT_LOGIN);
    //	}
    //	// 删除店铺分类
    //	this.removeById(storeLabelId);
    //
    //	// 清除缓存
    //	removeCache(tokenUser.getStoreId());
    //
    //	return true;
    // }
    //
    /// **
    // * 获取店铺商品分类列表
    // *
    // * @param storeId 店铺ID
    // * @return 店铺商品分类列表
    // */
    // private List<StoreGoodsLabelPO> list(Long storeId) {
    //	LambdaQueryWrapper<StoreGoodsLabelPO> queryWrapper = Wrappers.lambdaQuery();
    //	queryWrapper.eq(StoreGoodsLabelPO::getStoreId, storeId);
    //	queryWrapper.orderByDesc(StoreGoodsLabelPO::getSortOrder);
    //	return this.baseMapper.selectList(queryWrapper);
    // }
    //
    /// **
    // * 清除缓存
    // */
    // private void removeCache(Long storeId) {
    //	redisRepository.del(CachePrefix.STORE_CATEGORY.getPrefix() + storeId);
    // }
}
