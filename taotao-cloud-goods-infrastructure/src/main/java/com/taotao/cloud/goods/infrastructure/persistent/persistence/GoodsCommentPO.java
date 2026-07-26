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
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;

/**
 * 商品评论表
 *
 * <p>todo 暂时未用 需要添加注释
 *
 * @author shuigedeng
 * @since 2020/4/30 16:06
 */
@Setter
@Getter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(
        name = GoodsCommentPO.TABLE_NAME,
        indexes = {
            @Index(name = "idx_create_time", columnList = "`create_time`"),
        })
@TableName(GoodsCommentPO.TABLE_NAME)
@org.springframework.data.relational.core.mapping.Table(name = GoodsCommentPO.TABLE_NAME)
public class GoodsCommentPO extends BasePO<GoodsCommentPO> {

    public static final String TABLE_NAME = "ttc_goods_comment";

    @Column(name = "`goods_spec_ame`", columnDefinition = "varchar(255) not null comment '会员ID'")
    private String goodsSpecName;

    @Column(name = "`mall_id`", columnDefinition = "bigint not null comment '会员ID'")
    private Long mallId;

    @Column(name = "`scene_id`", columnDefinition = "bigint not null comment '会员ID'")
    private Long sceneId;

    @Column(name = "`customer_id`", columnDefinition = "bigint not null comment '会员ID'")
    private Long customerId;

    @Column(name = "`member_nick`", columnDefinition = "varchar(255) not null comment '会员ID'")
    private String memberNick;

    @Column(name = "`member_avatar`", columnDefinition = "varchar(255) not null comment '会员ID'")
    private String memberAvatar;

    @Column(name = "`order_code`", columnDefinition = "varchar(255) not null comment '会员ID'")
    private String orderCode;

    @Column(name = "`type`", columnDefinition = "int not null comment '会员ID'")
    private Integer type;

    @Column(name = "`rank`", columnDefinition = "int not null comment '会员ID'")
    private Integer rank;

    @Column(name = "`has_image`", columnDefinition = "int not null comment '会员ID'")
    private Integer hasImage;

    @Column(name = "`comment_pic_id`", columnDefinition = "bigint not null comment '会员ID'")
    private Long commentPicId;

    @Column(name = "`has_sen_word`", columnDefinition = "int not null comment '会员ID'")
    private Integer hasSenWord;

    @Column(name = "`origin_content`", columnDefinition = "varchar(255) not null comment '会员ID'")
    private String originContent;

    @Column(name = "`filter_content`", columnDefinition = "varchar(255) not null comment '会员ID'")
    private String filterContent;

    @Column(name = "`op_type`", columnDefinition = "int not null comment '会员ID'")
    private Integer opType;

    @Column(name = "`reply_status`", columnDefinition = "int not null comment '会员ID'")
    private Integer replyStatus;

    @Column(name = "`reply_content`", columnDefinition = "varchar(255) not null comment '会员ID'")
    private String replyContent;

    @Column(name = "`reply_ori_content`", columnDefinition = "varchar(255) not null comment '会员ID'")
    private String replyOriContent;

    @Column(name = "`reply_time`", columnDefinition = "datetime not null comment '会员ID'")
    private LocalDateTime replyTime;

    @Column(name = "`reply_user_id`", columnDefinition = "bigint not null comment '会员ID'")
    private Long replyUserId;

    @Column(name = "`reply_pic_id`", columnDefinition = "bigint not null comment '会员ID'")
    private Long replyPicId;

    @Column(name = "`has_add`", columnDefinition = "int not null comment '会员ID'")
    private Integer hasAdd;

    @Column(name = "`after_days`", columnDefinition = "int not null comment '会员ID'")
    private Integer afterDays;

    @Column(name = "`append_time`", columnDefinition = "datetime not null comment '会员ID'")
    private LocalDateTime appendTime;

    @Column(name = "`status`", columnDefinition = "int not null comment '会员ID'")
    private Integer status;

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getGoodsSpecName() {
        return goodsSpecName;
    }

    /**
     * 设置
     *
     * @param goodsSpecName goodsSpecName
     * @return 无返回值
     * @since 2022.03
     */
    public void setGoodsSpecName(String goodsSpecName) {
        this.goodsSpecName = goodsSpecName;
    }

    /**
     * 获取
     *
     * @return 结果数量
     * @since 2022.03
     */
    public Long getMallId() {
        return mallId;
    }

    /**
     * 设置
     *
     * @param mallId mallId
     * @return 无返回值
     * @since 2022.03
     */
    public void setMallId(Long mallId) {
        this.mallId = mallId;
    }

    /**
     * 获取
     *
     * @return 结果数量
     * @since 2022.03
     */
    public Long getSceneId() {
        return sceneId;
    }

    /**
     * 设置
     *
     * @param sceneId sceneId
     * @return 无返回值
     * @since 2022.03
     */
    public void setSceneId(Long sceneId) {
        this.sceneId = sceneId;
    }

    /**
     * 获取
     *
     * @return 结果数量
     * @since 2022.03
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * 设置
     *
     * @param customerId customerId
     * @return 无返回值
     * @since 2022.03
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getMemberNick() {
        return memberNick;
    }

    /**
     * 设置
     *
     * @param memberNick memberNick
     * @return 无返回值
     * @since 2022.03
     */
    public void setMemberNick(String memberNick) {
        this.memberNick = memberNick;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getMemberAvatar() {
        return memberAvatar;
    }

    /**
     * 设置
     *
     * @param memberAvatar memberAvatar
     * @return 无返回值
     * @since 2022.03
     */
    public void setMemberAvatar(String memberAvatar) {
        this.memberAvatar = memberAvatar;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * 设置
     *
     * @param orderCode orderCode
     * @return 无返回值
     * @since 2022.03
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    /**
     * 获取
     *
     * @return 结果数量
     * @since 2022.03
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置
     *
     * @param type type
     * @return 无返回值
     * @since 2022.03
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取
     *
     * @return 结果数量
     * @since 2022.03
     */
    public Integer getRank() {
        return rank;
    }

    /**
     * 设置
     *
     * @param rank rank
     * @return 无返回值
     * @since 2022.03
     */
    public void setRank(Integer rank) {
        this.rank = rank;
    }

    /**
     * 获取
     *
     * @return 结果数量
     * @since 2022.03
     */
    public Integer getHasImage() {
        return hasImage;
    }

    /**
     * 设置
     *
     * @param hasImage hasImage
     * @return 无返回值
     * @since 2022.03
     */
    public void setHasImage(Integer hasImage) {
        this.hasImage = hasImage;
    }

    /**
     * 获取
     *
     * @return 结果数量
     * @since 2022.03
     */
    public Long getCommentPicId() {
        return commentPicId;
    }

    /**
     * 设置
     *
     * @param commentPicId commentPicId
     * @return 无返回值
     * @since 2022.03
     */
    public void setCommentPicId(Long commentPicId) {
        this.commentPicId = commentPicId;
    }

    /**
     * 获取
     *
     * @return 结果数量
     * @since 2022.03
     */
    public Integer getHasSenWord() {
        return hasSenWord;
    }

    /**
     * 设置
     *
     * @param hasSenWord hasSenWord
     * @return 无返回值
     * @since 2022.03
     */
    public void setHasSenWord(Integer hasSenWord) {
        this.hasSenWord = hasSenWord;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getOriginContent() {
        return originContent;
    }

    /**
     * 设置
     *
     * @param originContent originContent
     * @return 无返回值
     * @since 2022.03
     */
    public void setOriginContent(String originContent) {
        this.originContent = originContent;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getFilterContent() {
        return filterContent;
    }

    /**
     * 设置
     *
     * @param filterContent filterContent
     * @return 无返回值
     * @since 2022.03
     */
    public void setFilterContent(String filterContent) {
        this.filterContent = filterContent;
    }

    /**
     * 获取
     *
     * @return 结果数量
     * @since 2022.03
     */
    public Integer getOpType() {
        return opType;
    }

    /**
     * 设置
     *
     * @param opType opType
     * @return 无返回值
     * @since 2022.03
     */
    public void setOpType(Integer opType) {
        this.opType = opType;
    }

    /**
     * 获取
     *
     * @return 结果数量
     * @since 2022.03
     */
    public Integer getReplyStatus() {
        return replyStatus;
    }

    /**
     * 设置
     *
     * @param replyStatus replyStatus
     * @return 无返回值
     * @since 2022.03
     */
    public void setReplyStatus(Integer replyStatus) {
        this.replyStatus = replyStatus;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getReplyContent() {
        return replyContent;
    }

    /**
     * 设置
     *
     * @param replyContent replyContent
     * @return 无返回值
     * @since 2022.03
     */
    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getReplyOriContent() {
        return replyOriContent;
    }

    /**
     * 设置
     *
     * @param replyOriContent replyOriContent
     * @return 无返回值
     * @since 2022.03
     */
    public void setReplyOriContent(String replyOriContent) {
        this.replyOriContent = replyOriContent;
    }

    /**
     * 获取
     *
     * @return LocalDateTime
     * @since 2022.03
     */
    public LocalDateTime getReplyTime() {
        return replyTime;
    }

    /**
     * 设置
     *
     * @param replyTime replyTime
     * @return 无返回值
     * @since 2022.03
     */
    public void setReplyTime(LocalDateTime replyTime) {
        this.replyTime = replyTime;
    }

    /**
     * 获取
     *
     * @return 结果数量
     * @since 2022.03
     */
    public Long getReplyUserId() {
        return replyUserId;
    }

    /**
     * 设置
     *
     * @param replyUserId replyUserId
     * @return 无返回值
     * @since 2022.03
     */
    public void setReplyUserId(Long replyUserId) {
        this.replyUserId = replyUserId;
    }

    /**
     * 获取
     *
     * @return 结果数量
     * @since 2022.03
     */
    public Long getReplyPicId() {
        return replyPicId;
    }

    /**
     * 设置
     *
     * @param replyPicId replyPicId
     * @return 无返回值
     * @since 2022.03
     */
    public void setReplyPicId(Long replyPicId) {
        this.replyPicId = replyPicId;
    }

    /**
     * 获取
     *
     * @return 结果数量
     * @since 2022.03
     */
    public Integer getHasAdd() {
        return hasAdd;
    }

    /**
     * 设置
     *
     * @param hasAdd hasAdd
     * @return 无返回值
     * @since 2022.03
     */
    public void setHasAdd(Integer hasAdd) {
        this.hasAdd = hasAdd;
    }

    /**
     * 获取
     *
     * @return 结果数量
     * @since 2022.03
     */
    public Integer getAfterDays() {
        return afterDays;
    }

    /**
     * 设置
     *
     * @param afterDays afterDays
     * @return 无返回值
     * @since 2022.03
     */
    public void setAfterDays(Integer afterDays) {
        this.afterDays = afterDays;
    }

    /**
     * 获取
     *
     * @return LocalDateTime
     * @since 2022.03
     */
    public LocalDateTime getAppendTime() {
        return appendTime;
    }

    /**
     * 设置
     *
     * @param appendTime appendTime
     * @return 无返回值
     * @since 2022.03
     */
    public void setAppendTime(LocalDateTime appendTime) {
        this.appendTime = appendTime;
    }

    /**
     * 获取
     *
     * @return 结果数量
     * @since 2022.03
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置
     *
     * @param status status
     * @return 无返回值
     * @since 2022.03
     */
    public void setStatus(Integer status) {
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
        GoodsCommentPO that = (GoodsCommentPO) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
