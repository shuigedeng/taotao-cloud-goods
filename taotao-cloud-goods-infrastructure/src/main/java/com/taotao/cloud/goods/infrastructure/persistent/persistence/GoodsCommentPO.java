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
import com.taotao.boot.webagg.entity.BaseSuperEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
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
@Accessors(fluent = true)
@Entity
@Table(name = GoodsCommentPO.TABLE_NAME,
	uniqueConstraints = {
		@UniqueConstraint(name = "uniq_goods_no", columnNames = "goods_no"),
	},
	indexes = {
		@Index(name = "idx_create_date", columnList = "`create_date`"),
	})
@TableName(GoodsCommentPO.TABLE_NAME)
@org.springframework.data.relational.core.mapping.Table(name = GoodsCommentPO.TABLE_NAME)
public class GoodsCommentPO extends BaseSuperEntity<GoodsCommentPO, Long> {

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

	public String getGoodsSpecName() {
		return goodsSpecName;
	}

	public void setGoodsSpecName(String goodsSpecName) {
		this.goodsSpecName = goodsSpecName;
	}

	public Long getMallId() {
		return mallId;
	}

	public void setMallId(Long mallId) {
		this.mallId = mallId;
	}

	public Long getSceneId() {
		return sceneId;
	}

	public void setSceneId(Long sceneId) {
		this.sceneId = sceneId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getMemberNick() {
		return memberNick;
	}

	public void setMemberNick(String memberNick) {
		this.memberNick = memberNick;
	}

	public String getMemberAvatar() {
		return memberAvatar;
	}

	public void setMemberAvatar(String memberAvatar) {
		this.memberAvatar = memberAvatar;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Integer getHasImage() {
		return hasImage;
	}

	public void setHasImage(Integer hasImage) {
		this.hasImage = hasImage;
	}

	public Long getCommentPicId() {
		return commentPicId;
	}

	public void setCommentPicId(Long commentPicId) {
		this.commentPicId = commentPicId;
	}

	public Integer getHasSenWord() {
		return hasSenWord;
	}

	public void setHasSenWord(Integer hasSenWord) {
		this.hasSenWord = hasSenWord;
	}

	public String getOriginContent() {
		return originContent;
	}

	public void setOriginContent(String originContent) {
		this.originContent = originContent;
	}

	public String getFilterContent() {
		return filterContent;
	}

	public void setFilterContent(String filterContent) {
		this.filterContent = filterContent;
	}

	public Integer getOpType() {
		return opType;
	}

	public void setOpType(Integer opType) {
		this.opType = opType;
	}

	public Integer getReplyStatus() {
		return replyStatus;
	}

	public void setReplyStatus(Integer replyStatus) {
		this.replyStatus = replyStatus;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public String getReplyOriContent() {
		return replyOriContent;
	}

	public void setReplyOriContent(String replyOriContent) {
		this.replyOriContent = replyOriContent;
	}

	public LocalDateTime getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(LocalDateTime replyTime) {
		this.replyTime = replyTime;
	}

	public Long getReplyUserId() {
		return replyUserId;
	}

	public void setReplyUserId(Long replyUserId) {
		this.replyUserId = replyUserId;
	}

	public Long getReplyPicId() {
		return replyPicId;
	}

	public void setReplyPicId(Long replyPicId) {
		this.replyPicId = replyPicId;
	}

	public Integer getHasAdd() {
		return hasAdd;
	}

	public void setHasAdd(Integer hasAdd) {
		this.hasAdd = hasAdd;
	}

	public Integer getAfterDays() {
		return afterDays;
	}

	public void setAfterDays(Integer afterDays) {
		this.afterDays = afterDays;
	}

	public LocalDateTime getAppendTime() {
		return appendTime;
	}

	public void setAppendTime(LocalDateTime appendTime) {
		this.appendTime = appendTime;
	}

	public Integer getStatus() {
		return status;
	}

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
