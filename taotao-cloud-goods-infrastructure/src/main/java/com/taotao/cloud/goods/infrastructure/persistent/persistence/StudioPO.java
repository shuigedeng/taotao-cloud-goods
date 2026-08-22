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

package com.taotao.cloud.goods.infrastructure.persistent.persistence;

import com.baomidou.mybatisplus.annotation.TableName;
import com.taotao.boot.webagg.entity.BasePO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;

/**
 * 小程序直播间表
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-20 16:59:38
 * @since 2021/5/17 9:47 上午
 */
@Setter
@Getter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(
        name = StudioPO.TABLE_NAME,
        indexes = {
            @Index(name = "idx_create_time", columnList = "`create_time`"),
        })
@TableName(StudioPO.TABLE_NAME)
@org.springframework.data.relational.core.mapping.Table(name = StudioPO.TABLE_NAME)
public class StudioPO extends BasePO<StudioPO> {

    public static final String TABLE_NAME = "ttc_studio";

    /** 直播间名字，最短3个汉字，最长17个汉字，1个汉字相当于2个字符 */
    @Column(name = "`name`", columnDefinition = "varchar(255) not null comment '直播间名字'")
    private String name;

    /**
     * 背景图，填入mediaID（mediaID获取后，三天内有效）；图片mediaID的获取，请参考以下文档： <a
     * href="https://developers.weixin.qq.com/doc/offiaccount/Asset_Management/New_temporary_materials.html">https://developers.weixin.qq.com/doc/offiaccount/Asset_Management/New_temporary_materials.html</a>；直播间背景图，图片规则：建议像素1080*1920，大小不超过2M
     */
    @Column(name = "`cover_img`", columnDefinition = "varchar(255) not null comment '背景图'")
    private String coverImg;

    /** 直播计划开始时间（开播时间需要在当前时间的10分钟后 并且 开始时间不能在 6 个月后） */
    @Column(name = "`start_time`", columnDefinition = "varchar(255) not null comment '开始时间'")
    private String startTime;

    /** 直播计划结束时间（开播时间和结束时间间隔不得短于30分钟，不得超过24小时） */
    @Column(name = "`end_time`", columnDefinition = "varchar(255) not null comment '结束时间'")
    private String endTime;

    /** 主播昵称，最短2个汉字，最长15个汉字，1个汉字相当于2个字符 */
    @Column(name = "`anchor_name`", columnDefinition = "varchar(255) not null comment '主播昵称'")
    private String anchorName;

    /**
     * 主播微信号，如果未实名认证，需要先前往“小程序直播”小程序进行实名验证,
     * 小程序二维码链接：https://res.wx.qq.com/op_res/9rSix1dhHfK4rR049JL0PHJ7TpOvkuZ3mE0z7Ou_Etvjf-w1J_jVX0rZqeStLfwh
     */
    @Column(name = "`anchor_wechat`", columnDefinition = "varchar(255) not null comment '主播微信号'")
    private String anchorWechat;

    /**
     * 分享图，填入mediaID（mediaID获取后，三天内有效）；图片mediaID的获取，请参考以下文档：
     * https://developers.weixin.qq.com/doc/offiaccount/Asset_Management/New_temporary_materials.html；直播间分享图，图片规则：建议像素800*2550，大小不超过1M；
     */
    @Column(name = "`share_img`", columnDefinition = "varchar(255) not null comment '分享图'")
    private String shareImg;

    /**
     * 购物直播频道封面图，填入mediaID（mediaID获取后，三天内有效）；图片mediaID的获取，请参考以下文档：
     * https://developers.weixin.qq.com/doc/offiaccount/Asset_Management/New_temporary_materials.html;
     * 购物直播频道封面图，图片规则：建议像素800*800，大小不超过100KB；
     */
    @Column(name = "`feeds_img`", columnDefinition = "varchar(255) not null comment '封面图'")
    private String feedsImg;

    /** 回放视频链接 */
    @Column(name = "`media_url`", columnDefinition = "varchar(255) not null comment '回放视频链接'")
    private String mediaUrl;

    /** 房间ID */
    @Column(name = "`room_id`", columnDefinition = "int not null comment '房间ID'")
    private Integer roomId;

    /** 小程序直播码 */
    @Column(name = "`qr_code_url`", columnDefinition = "varchar(255) not null comment '小程序直播码'")
    private String qrCodeUrl;

    /** 店铺ID */
    @Column(name = "`store_id`", columnDefinition = "varchar(255) not null comment '店铺ID'")
    private Long storeId;

    /** 直播间商品数量 */
    @Column(name = "`room_goods_num`", columnDefinition = "int not null comment '直播间商品数量'")
    private Integer roomGoodsNum;

    /** 直播间商品(最多展示两个商品：name/goodsImage) */
    @Column(
            name = "`room_goods_list`",
            columnDefinition = "varchar(255) not null comment '直播间商品(最多展示两个商品：name/goodsImage)'")
    private String roomGoodsList;

    /** 推荐直播间 */
    @Column(name = "`recommend`", columnDefinition = "boolean not null comment '推荐直播间'")
    private Boolean recommend;

    /** 直播间状态 */
    @Column(name = "`status`", columnDefinition = "varchar(255) not null comment '直播间状态'")
    private String status;

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     *
     * @param name name
     * @since 2022.03
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getCoverImg() {
        return coverImg;
    }

    /**
     * 设置
     *
     * @param coverImg coverImg
     * @since 2022.03
     */
    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * 设置
     *
     * @param startTime startTime
     * @since 2022.03
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * 设置
     *
     * @param endTime endTime
     * @since 2022.03
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getAnchorName() {
        return anchorName;
    }

    /**
     * 设置
     *
     * @param anchorName anchorName
     * @since 2022.03
     */
    public void setAnchorName(String anchorName) {
        this.anchorName = anchorName;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getAnchorWechat() {
        return anchorWechat;
    }

    /**
     * 设置
     *
     * @param anchorWechat anchorWechat
     * @since 2022.03
     */
    public void setAnchorWechat(String anchorWechat) {
        this.anchorWechat = anchorWechat;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getShareImg() {
        return shareImg;
    }

    /**
     * 设置
     *
     * @param shareImg shareImg
     * @since 2022.03
     */
    public void setShareImg(String shareImg) {
        this.shareImg = shareImg;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getFeedsImg() {
        return feedsImg;
    }

    /**
     * 设置
     *
     * @param feedsImg feedsImg
     * @since 2022.03
     */
    public void setFeedsImg(String feedsImg) {
        this.feedsImg = feedsImg;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getMediaUrl() {
        return mediaUrl;
    }

    /**
     * 设置
     *
     * @param mediaUrl mediaUrl
     * @since 2022.03
     */
    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    /**
     * 获取
     *
     * @return 结果数量
     * @since 2022.03
     */
    public Integer getRoomId() {
        return roomId;
    }

    /**
     * 设置
     *
     * @param roomId roomId
     * @since 2022.03
     */
    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getQrCodeUrl() {
        return qrCodeUrl;
    }

    /**
     * 设置
     *
     * @param qrCodeUrl qrCodeUrl
     * @since 2022.03
     */
    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
    }

    /**
     * 获取
     *
     * @return 结果数量
     * @since 2022.03
     */
    public Long getStoreId() {
        return storeId;
    }

    /**
     * 设置
     *
     * @param storeId storeId
     * @since 2022.03
     */
    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    /**
     * 获取
     *
     * @return 结果数量
     * @since 2022.03
     */
    public Integer getRoomGoodsNum() {
        return roomGoodsNum;
    }

    /**
     * 设置
     *
     * @param roomGoodsNum roomGoodsNum
     * @since 2022.03
     */
    public void setRoomGoodsNum(Integer roomGoodsNum) {
        this.roomGoodsNum = roomGoodsNum;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getRoomGoodsList() {
        return roomGoodsList;
    }

    /**
     * 设置
     *
     * @param roomGoodsList roomGoodsList
     * @since 2022.03
     */
    public void setRoomGoodsList(String roomGoodsList) {
        this.roomGoodsList = roomGoodsList;
    }

    /**
     * 获取
     *
     * @return 是否成功
     * @since 2022.03
     */
    public Boolean getRecommend() {
        return recommend;
    }

    /**
     * 设置
     *
     * @param recommend recommend
     * @since 2022.03
     */
    public void setRecommend(Boolean recommend) {
        this.recommend = recommend;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置
     *
     * @param status status
     * @since 2022.03
     */
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        StudioPO studioPO = (StudioPO) o;
        return getId() != null && Objects.equals(getId(), studioPO.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
