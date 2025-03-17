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

import com.taotao.cloud.goods.application.dto.store.clientobject.StudioCommodityCO;
import com.taotao.cloud.goods.application.service.query.StudioQueryService;
import org.springframework.stereotype.Service;

/**
 * 小程序直播间业务层实现
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 17:03:04
 */
@Service
public class StudioQueryServiceImpl
        implements StudioQueryService {
	@Override
	public StudioCommodityCO getStudioVO(Long id) {
		return null;
	}

	@Override
	public String getLiveInfo(Integer roomId) {
		return "";
	}

//	@Override
//	public IPage<StudioPO> studioList(PageQuery PageQuery, Integer recommend, String status) {
//		return null;
//	}

	//@Autowired
    //private WechatLivePlayerUtil wechatLivePlayerUtil;
	//
    //@Autowired
    //private StudioCommodityCommandService studioCommodityService;
	//
    //@Resource
    //private CommodityMapper commodityMapper;
	//
    //@Autowired
    //private TimeTrigger timeTrigger;
	//
    //@Autowired
    //private RocketmqCustomProperties rocketmqCustomProperties;
	//
    //@Autowired
    //private GoodsCommandService goodsService;
	//
    //@Override
    //@Transactional(rollbackFor = Exception.class)
    //public boolean create(StudioPO studioPO) {
    //    studioPO.setStoreId(SecurityUtils.getCurrentUser().getStoreId());
    //    // 创建小程序直播
    //    Map<String, String> roomMap = wechatLivePlayerUtil.create(studioPO);
    //    studioPO.setRoomId(Convert.toInt(roomMap.get("roomId")));
    //    studioPO.setQrCodeUrl(roomMap.get("qrcodeUrl"));
    //    studioPO.setStatus(StudioStatusEnum.NEW.name());
    //    // 直播间添加成功发送直播间开启、关闭延时任务
    //    if (this.save(studioPO)) {
    //        // 直播开启延时任务
    //        BroadcastMessage broadcastMessage = new BroadcastMessage(studioPO.getId(), StudioStatusEnum.START.name());
    //        TimeTriggerMsg timeTriggerMsg = new TimeTriggerMsg(
    //                TimeExecuteConstant.BROADCAST_EXECUTOR,
    //                Long.parseLong(studioPO.getStartTime()) * 1000L,
    //                broadcastMessage,
    //                DelayQueueTools.wrapperUniqueKey(DelayTypeEnums.BROADCAST, String.valueOf(
	//					studioPO.getId())),
    //                rocketmqCustomProperties.getPromotionTopic());
	//
    //        // 发送促销活动开始的延时任务
    //        this.timeTrigger.addDelay(timeTriggerMsg);
	//
    //        // 直播结束延时任务
    //        broadcastMessage = new BroadcastMessage(studioPO.getId(), StudioStatusEnum.END.name());
    //        timeTriggerMsg = new TimeTriggerMsg(
    //                TimeExecuteConstant.BROADCAST_EXECUTOR,
    //                Long.parseLong(studioPO.getEndTime()) * 1000L,
    //                broadcastMessage,
    //                DelayQueueTools.wrapperUniqueKey(DelayTypeEnums.BROADCAST, String.valueOf(
	//					studioPO.getId())),
    //                rocketmqCustomProperties.getPromotionTopic());
	//
    //        // 发送促销活动开始的延时任务
    //        this.timeTrigger.addDelay(timeTriggerMsg);
    //    }
    //    return true;
    //}
	//
    //@Override
    //@Transactional(rollbackFor = Exception.class)
    //public boolean edit(StudioPO studioPO) {
    //    StudioPO oldStudioPO = this.getById(studioPO.getId());
    //    wechatLivePlayerUtil.editRoom(studioPO);
    //    if (this.updateById(studioPO)) {
    //        // 发送更新延时任务
    //        // 直播间开始
    //        BroadcastMessage broadcastMessage = new BroadcastMessage(studioPO.getId(), StudioStatusEnum.START.name());
    //        this.timeTrigger.edit(
    //                TimeExecuteConstant.BROADCAST_EXECUTOR,
    //                broadcastMessage,
    //                Long.parseLong(oldStudioPO.getStartTime()) * 1000L,
    //                Long.parseLong(studioPO.getStartTime()) * 1000L,
    //                DelayQueueTools.wrapperUniqueKey(DelayTypeEnums.BROADCAST, String.valueOf(
	//					studioPO.getId())),
    //                DateUtils.getDelayTime(Long.parseLong(studioPO.getStartTime())),
    //                rocketmqCustomProperties.getPromotionTopic());
	//
    //        // 直播间结束
    //        broadcastMessage = new BroadcastMessage(studioPO.getId(), StudioStatusEnum.START.name());
    //        this.timeTrigger.edit(
    //                TimeExecuteConstant.BROADCAST_EXECUTOR,
    //                broadcastMessage,
    //                Long.parseLong(oldStudioPO.getEndTime()) * 1000L,
    //                Long.parseLong(studioPO.getEndTime()) * 1000L,
    //                DelayQueueTools.wrapperUniqueKey(DelayTypeEnums.BROADCAST, String.valueOf(
	//					studioPO.getId())),
    //                DateUtils.getDelayTime(Long.parseLong(studioPO.getEndTime())),
    //                rocketmqCustomProperties.getPromotionTopic());
    //    }
    //    return true;
    //}
	//
	//@Override
	//public StudioCommodityCO getStudioVO(Long id) {
	//	return null;
	//}
	//
	//@Override
    //public StudioCommodityCO getStudioCO(Long id) {
    //    StudioCommodityCO studioCommodityCO = new StudioCommodityCO();
    //    StudioPO studioPO = this.getById(id);
    //    // 获取直播间信息
    //    BeanUtils.copyProperties(studioPO, studioCommodityCO);
    //    // 获取直播间商品信息
    //    List<CommodityPO> commodities = commodityMapper.getCommodityByRoomId(studioCommodityCO.getRoomId());
    //    studioCommodityCO.setCommodityList(OrikaUtils.converts(commodities, CommodityCO.class));
    //    return studioCommodityCO;
    //}
	//
    //@Override
    //public String getLiveInfo(Integer roomId) {
    //    StudioPO studioPO = this.getByRoomId(roomId);
    //    // 获取直播间并判断回放内容是否为空，如果为空则获取直播间回放并保存
    //    if (studioPO.getMediaUrl() != null) {
    //        return studioPO.getMediaUrl();
    //    } else {
    //        String mediaUrl = wechatLivePlayerUtil.getLiveInfo(roomId);
    //        studioPO.setMediaUrl(mediaUrl);
    //        this.save(studioPO);
    //        return mediaUrl;
    //    }
    //}
	//
    //@Override
    //@Transactional(rollbackFor = Exception.class)
    //public boolean push(Integer roomId, Long goodsId, Long storeId) {
    //    // 判断直播间是否已添加商品
    //    if (studioCommodityService.getOne(new LambdaQueryWrapper<StudioCommodityPO>()
    //                    .eq(StudioCommodityPO::getRoomId, roomId)
    //                    .eq(StudioCommodityPO::getGoodsId, goodsId))
    //            != null) {
    //        throw new BusinessException(ResultEnum.STODIO_GOODS_EXIST_ERROR);
    //    }
	//
    //    GoodsPO goods = goodsService.getOne(
    //            new LambdaQueryWrapper<GoodsPO>().eq(GoodsPO::getId, goodsId).eq(GoodsPO::getStoreId, storeId));
    //    if (goods == null) {
    //        throw new BusinessException(ResultEnum.USER_AUTHORITY_ERROR);
    //    }
	//
    //    // 调用微信接口添加直播间商品并进行记录
    //    if (boolean.TRUE.equals(wechatLivePlayerUtil.pushGoods(roomId, goodsId))) {
    //        // studioCommodityService.save(new StudioCommodity(roomId, goodsId));
    //        // 添加直播间商品数量
    //        StudioPO studioPO = this.getByRoomId(roomId);
    //        studioPO.setRoomGoodsNum(
	//			studioPO.getRoomGoodsNum() != null ? studioPO.getRoomGoodsNum() + 1 : 1);
    //        // 设置直播间默认的商品（前台展示）只展示两个
    //        if (studioPO.getRoomGoodsNum() < 3) {
    //            studioPO.setRoomGoodsList(JSONUtil.toJsonStr(commodityMapper.getSimpleCommodityByRoomId(roomId)));
    //        }
    //        return this.updateById(studioPO);
    //    }
    //    return false;
    //}
	//
    //@Override
    //@Transactional(rollbackFor = Exception.class)
    //public boolean goodsDeleteInRoom(Integer roomId, Long goodsId, Long storeId) {
    //    GoodsPO goods = goodsService.getOne(
    //            new LambdaQueryWrapper<GoodsPO>().eq(GoodsPO::getId, goodsId).eq(GoodsPO::getStoreId, storeId));
    //    if (goods == null) {
    //        throw new BusinessException(ResultEnum.USER_AUTHORITY_ERROR);
    //    }
    //    // 调用微信接口删除直播间商品并进行记录
    //    if (boolean.TRUE.equals(wechatLivePlayerUtil.goodsDeleteInRoom(roomId, goodsId))) {
    //        studioCommodityService.remove(
    //                new QueryWrapper<StudioCommodityPO>().eq("room_id", roomId).eq("goods_id", goodsId));
    //        // 减少直播间商品数量
    //        StudioPO studioPO = this.getByRoomId(roomId);
    //        studioPO.setRoomGoodsNum(studioPO.getRoomGoodsNum() - 1);
    //        // 设置直播间默认的商品（前台展示）只展示两个
    //        if (studioPO.getRoomGoodsNum() < 3) {
    //            studioPO.setRoomGoodsList(JSONUtil.toJsonStr(commodityMapper.getSimpleCommodityByRoomId(roomId)));
    //        }
    //        return this.updateById(studioPO);
    //    }
    //    return false;
    //}
	//
    //@Override
    //public IPage<StudioPO> studioList(PageQuery PageQuery, Integer recommend, String status) {
    //    QueryWrapper<StudioPO> queryWrapper = new QueryWrapper<StudioPO>()
    //            .eq(recommend != null, "recommend", true)
    //            .eq(status != null, "status", status)
    //            .orderByDesc("create_time");
    //    // if (UserContext.getCurrentUser() != null && UserContext.getCurrentUser().getRole()
    //    //	.equals(UserEnums.STORE)) {
    //    //	queryWrapper.eq("store_id", UserContext.getCurrentUser().getStoreId());
    //    // }
    //    return this.page(PageQuery.buildMpPage(), queryWrapper);
    //}
	//
    //@Override
    //public boolean updateStudioStatus(BroadcastMessage broadcastMessage) {
    //    return this.update(new LambdaUpdateWrapper<StudioPO>()
    //            .eq(StudioPO::getId, broadcastMessage.getStudioId())
    //            .set(StudioPO::getStatus, broadcastMessage.getStatus()));
    //}
	//
    ///**
    // * 根据直播间ID获取直播间
    // *
    // * @param roomId 直播间ID
    // * @return 直播间
    // */
    //private StudioPO getByRoomId(Integer roomId) {
    //    return this.getOne(new LambdaQueryWrapper<StudioPO>().eq(StudioPO::getRoomId, roomId));
    //}
}
