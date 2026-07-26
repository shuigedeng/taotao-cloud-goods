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

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.http.HtmlUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.taotao.boot.webagg.entity.BasePO;
import com.taotao.cloud.goods.common.enums.GoodsAuthEnum;
import com.taotao.cloud.goods.common.enums.GoodsTypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.math.BigDecimal;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;

/**
 * 商品表
 *
 * @author shuigedeng
 * @version 2021.10
 * @since 2022-03-11 17:21:04
 */
@Setter
@Getter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(
	name = GoodsPO.TABLE_NAME,
	uniqueConstraints = {
		@UniqueConstraint(name = "uniq_goods_no", columnNames = "goods_no"),
	},
	indexes = {
		@Index(name = "idx_goods_name", columnList = "goods_name"),
		@Index(name = "idx_gn_bi_gu", columnList = "goods_name,brand_id,goods_unit"),
		@Index(name = "idx_create_time", columnList = "`create_time`"),
	})
@TableName(GoodsPO.TABLE_NAME)
@org.springframework.data.relational.core.mapping.Table(name = GoodsPO.TABLE_NAME)
public class GoodsPO extends BasePO<GoodsPO> {

	public static final String TABLE_NAME = "ttc_goods";

	/**
	 * 商品名称
	 */
	@Column(name = "`goods_no`", columnDefinition = "varchar(255) not null comment '商品编码'")
	private String goodsNo;

	/**
	 * 商品名称
	 */
	@Column(name = "`goods_name`", columnDefinition = "varchar(255) not null comment '商品名称'")
	private String goodsName;

	/**
	 * 商品价格
	 */
	@Column(name = "`price`", columnDefinition = "decimal(10,2) not null comment '商品价格'")
	private BigDecimal price;

	/**
	 * 品牌id
	 */
	@Column(name = "`brand_id`", columnDefinition = "bigint not null comment '品牌id'")
	private Long brandId;

	/**
	 * 分类path
	 */
	@Column(name = "`category_path`", columnDefinition = "varchar(255) not null comment '分类path'")
	private String categoryPath;

	/**
	 * 计量单位
	 */
	@Column(name = "`goods_unit`", columnDefinition = "varchar(255) not null comment '计量单位'")
	private String goodsUnit;

	/**
	 * 商品卖点太长，不能超过60个字符
	 */
	@Column(name = "`selling_point`", columnDefinition = "varchar(255) not null comment '商品卖点'")
	private String sellingPoint;

	/**
	 * 上架状态
	 */
	@Column(name = "`market_enable`", columnDefinition = "varchar(255) not null comment '上架状态'")
	private String marketEnable;

	/**
	 * 详情
	 */
	@Column(name = "`intro`", columnDefinition = "mediumtext not null comment '详情'")
	private String intro;

	/**
	 * 购买数量
	 */
	@Column(name = "`buy_count`", columnDefinition = "int not null default 0 comment '购买数量'")
	private Integer buyCount;

	/**
	 * 库存
	 */
	@Column(name = "`quantity`", columnDefinition = "int not null default 0 comment '库存'")
	private Integer quantity;

	/**
	 * 商品好评率
	 */
	@Column(name = "`grade`", columnDefinition = "decimal(10,2) null comment '商品好评率'")
	private BigDecimal grade;

	/**
	 * 缩略图路径
	 */
	@Column(name = "`thumbnail`", columnDefinition = "varchar(255) not null comment '缩略图路径'")
	private String thumbnail;

	/**
	 * 小图路径
	 */
	@Column(name = "`small`", columnDefinition = "varchar(255) not null comment '小图路径'")
	private String small;

	/**
	 * 原图路径
	 */
	@Column(name = "`original`", columnDefinition = "varchar(255) not null comment '原图路径'")
	private String original;

	/**
	 * 店铺分类id
	 */
	@Column(
		name = "`store_category_path`",
		columnDefinition = "varchar(255) not null comment '店铺分类id'")
	private String storeCategoryPath;

	/**
	 * 评论数量
	 */
	@Column(name = "`comment_num`", columnDefinition = "int null default 0 comment '评论数量'")
	private Integer commentNum;

	/**
	 * 卖家id
	 */
	@Column(name = "`store_id`", columnDefinition = "bigint not null comment '会员卖家id'")
	private Long storeId;

	/**
	 * 卖家名字
	 */
	@Column(name = "`store_name`", columnDefinition = "varchar(255) not null comment '卖家名字'")
	private String storeName;

	/**
	 * 运费模板id
	 */
	@Column(name = "`template_id`", columnDefinition = "bigint not null comment '运费模板id'")
	private Long templateId;

	/**
	 * 审核状态
	 *
	 * @see GoodsAuthEnum
	 */
	@Column(name = "`auth_flag`", columnDefinition = "varchar(255) not null comment '审核状态'")
	private String authFlag;

	/**
	 * 审核信息
	 */
	@Column(name = "`auth_message`", columnDefinition = "varchar(255) null comment '审核信息'")
	private String authMessage;

	/**
	 * 下架原因
	 */
	@Column(name = "`under_message`", columnDefinition = "varchar(255) null comment '下架原因'")
	private String underMessage;

	/**
	 * 是否自营
	 */
	@Column(
		name = "`self_operated`",
		columnDefinition = "boolean not null default false comment '是否自营'")
	private Boolean selfOperated;

	/**
	 * 商品移动端详情
	 */
	@Column(name = "`mobile_intro`", columnDefinition = "mediumtext not null comment '商品移动端详情'")
	private String mobileIntro;

	/**
	 * 商品视频
	 */
	@Column(name = "`goods_video`", columnDefinition = "varchar(255) not null comment '商品视频'")
	private String goodsVideo;

	/**
	 * 是否为推荐商品
	 */
	@Column(
		name = "`recommend`",
		columnDefinition = "boolean not null default false comment '是否为推荐商品'")
	private Boolean recommend;

	/**
	 * 销售模式
	 */
	@Column(name = "`sales_model`", columnDefinition = "varchar(255) not null comment '销售模式'")
	private String salesModel;

	/**
	 * 商品海报id
	 */
	@Column(name = "`poster_pic_id`", columnDefinition = "bigint null comment '商品海报id'")
	private Long posterPicId;

	/**
	 * 商品类型
	 *
	 * @see GoodsTypeEnum
	 */
	@Column(name = "`goods_type`", columnDefinition = "varchar(255) not null comment '商品类型'")
	private String goodsType;

	/**
	 * 商品参数json
	 */
	//@Type(JsonType.class)
	@TableField(typeHandler = FastjsonTypeHandler.class)
	@Column(name = "`params`", columnDefinition = "json not null comment '商品参数json'")
	private String params;

	/**
	 * 获取
	 *
	 * @return 字符串
	 * @since 2022.03
	 */

	public String getGoodsNo() {
		return goodsNo;
	}

	/**
	 * 设置
	 *
	 * @param goodsNo goodsNo
	 * @return 无返回值
	 * @since 2022.03
	 */

	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}

	/**
	 * 获取
	 *
	 * @return 字符串
	 * @since 2022.03
	 */

	public String getGoodsName() {
		return goodsName;
	}

	/**
	 * 设置
	 *
	 * @param goodsName goodsName
	 * @return 无返回值
	 * @since 2022.03
	 */

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	/**
	 * 获取
	 *
	 * @return BigDecimal
	 * @since 2022.03
	 */

	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * 设置
	 *
	 * @param price price
	 * @return 无返回值
	 * @since 2022.03
	 */

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * 获取
	 *
	 * @return 结果数量
	 * @since 2022.03
	 */

	public Long getBrandId() {
		return brandId;
	}

	/**
	 * 设置
	 *
	 * @param brandId brandId
	 * @return 无返回值
	 * @since 2022.03
	 */

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	/**
	 * 获取
	 *
	 * @return 字符串
	 * @since 2022.03
	 */

	public String getCategoryPath() {
		return categoryPath;
	}

	/**
	 * 设置
	 *
	 * @param categoryPath categoryPath
	 * @return 无返回值
	 * @since 2022.03
	 */

	public void setCategoryPath(String categoryPath) {
		this.categoryPath = categoryPath;
	}

	/**
	 * 获取
	 *
	 * @return 字符串
	 * @since 2022.03
	 */

	public String getGoodsUnit() {
		return goodsUnit;
	}

	/**
	 * 设置
	 *
	 * @param goodsUnit goodsUnit
	 * @return 无返回值
	 * @since 2022.03
	 */

	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}

	/**
	 * 获取
	 *
	 * @return 字符串
	 * @since 2022.03
	 */

	public String getSellingPoint() {
		return sellingPoint;
	}

	/**
	 * 设置
	 *
	 * @param sellingPoint sellingPoint
	 * @return 无返回值
	 * @since 2022.03
	 */

	public void setSellingPoint(String sellingPoint) {
		this.sellingPoint = sellingPoint;
	}

	/**
	 * 获取
	 *
	 * @return 字符串
	 * @since 2022.03
	 */

	public String getMarketEnable() {
		return marketEnable;
	}

	/**
	 * 设置
	 *
	 * @param marketEnable marketEnable
	 * @return 无返回值
	 * @since 2022.03
	 */

	public void setMarketEnable(String marketEnable) {
		this.marketEnable = marketEnable;
	}

	/**
	 * 设置
	 *
	 * @param intro intro
	 * @return 无返回值
	 * @since 2022.03
	 */

	public void setIntro(String intro) {
		this.intro = intro;
	}

	/**
	 * 获取
	 *
	 * @return 结果数量
	 * @since 2022.03
	 */

	public Integer getBuyCount() {
		return buyCount;
	}

	/**
	 * 设置
	 *
	 * @param buyCount buyCount
	 * @return 无返回值
	 * @since 2022.03
	 */

	public void setBuyCount(Integer buyCount) {
		this.buyCount = buyCount;
	}

	/**
	 * 获取
	 *
	 * @return 结果数量
	 * @since 2022.03
	 */

	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * 设置
	 *
	 * @param quantity quantity
	 * @return 无返回值
	 * @since 2022.03
	 */

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 * 获取
	 *
	 * @return BigDecimal
	 * @since 2022.03
	 */

	public BigDecimal getGrade() {
		return grade;
	}

	/**
	 * 设置
	 *
	 * @param grade grade
	 * @return 无返回值
	 * @since 2022.03
	 */

	public void setGrade(BigDecimal grade) {
		this.grade = grade;
	}

	/**
	 * 获取
	 *
	 * @return 字符串
	 * @since 2022.03
	 */

	public String getThumbnail() {
		return thumbnail;
	}

	/**
	 * 设置
	 *
	 * @param thumbnail thumbnail
	 * @return 无返回值
	 * @since 2022.03
	 */

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	/**
	 * 获取
	 *
	 * @return 字符串
	 * @since 2022.03
	 */

	public String getSmall() {
		return small;
	}

	/**
	 * 设置
	 *
	 * @param small small
	 * @return 无返回值
	 * @since 2022.03
	 */

	public void setSmall(String small) {
		this.small = small;
	}

	/**
	 * 获取
	 *
	 * @return 字符串
	 * @since 2022.03
	 */

	public String getOriginal() {
		return original;
	}

	/**
	 * 设置
	 *
	 * @param original original
	 * @return 无返回值
	 * @since 2022.03
	 */

	public void setOriginal(String original) {
		this.original = original;
	}

	/**
	 * 获取
	 *
	 * @return 字符串
	 * @since 2022.03
	 */

	public String getStoreCategoryPath() {
		return storeCategoryPath;
	}

	/**
	 * 设置
	 *
	 * @param storeCategoryPath storeCategoryPath
	 * @return 无返回值
	 * @since 2022.03
	 */

	public void setStoreCategoryPath(String storeCategoryPath) {
		this.storeCategoryPath = storeCategoryPath;
	}

	/**
	 * 获取
	 *
	 * @return 结果数量
	 * @since 2022.03
	 */

	public Integer getCommentNum() {
		return commentNum;
	}

	/**
	 * 设置
	 *
	 * @param commentNum commentNum
	 * @return 无返回值
	 * @since 2022.03
	 */

	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
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
	 * @return 无返回值
	 * @since 2022.03
	 */

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	/**
	 * 获取
	 *
	 * @return 字符串
	 * @since 2022.03
	 */

	public String getStoreName() {
		return storeName;
	}

	/**
	 * 设置
	 *
	 * @param storeName storeName
	 * @return 无返回值
	 * @since 2022.03
	 */

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	/**
	 * 获取
	 *
	 * @return 结果数量
	 * @since 2022.03
	 */

	public Long getTemplateId() {
		return templateId;
	}

	/**
	 * 设置
	 *
	 * @param templateId templateId
	 * @return 无返回值
	 * @since 2022.03
	 */

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	/**
	 * 获取
	 *
	 * @return 字符串
	 * @since 2022.03
	 */

	public String getAuthFlag() {
		return authFlag;
	}

	/**
	 * 设置
	 *
	 * @param authFlag authFlag
	 * @return 无返回值
	 * @since 2022.03
	 */

	public void setAuthFlag(String authFlag) {
		this.authFlag = authFlag;
	}

	/**
	 * 获取
	 *
	 * @return 字符串
	 * @since 2022.03
	 */

	public String getAuthMessage() {
		return authMessage;
	}

	/**
	 * 设置
	 *
	 * @param authMessage authMessage
	 * @return 无返回值
	 * @since 2022.03
	 */

	public void setAuthMessage(String authMessage) {
		this.authMessage = authMessage;
	}

	/**
	 * 获取
	 *
	 * @return 字符串
	 * @since 2022.03
	 */

	public String getUnderMessage() {
		return underMessage;
	}

	/**
	 * 设置
	 *
	 * @param underMessage underMessage
	 * @return 无返回值
	 * @since 2022.03
	 */

	public void setUnderMessage(String underMessage) {
		this.underMessage = underMessage;
	}

	/**
	 * 获取
	 *
	 * @return 是否成功
	 * @since 2022.03
	 */

	public Boolean getSelfOperated() {
		return selfOperated;
	}

	/**
	 * 设置
	 *
	 * @param selfOperated selfOperated
	 * @return 无返回值
	 * @since 2022.03
	 */

	public void setSelfOperated(Boolean selfOperated) {
		this.selfOperated = selfOperated;
	}

	/**
	 * 设置
	 *
	 * @param mobileIntro mobileIntro
	 * @return 无返回值
	 * @since 2022.03
	 */

	public void setMobileIntro(String mobileIntro) {
		this.mobileIntro = mobileIntro;
	}

	/**
	 * 获取
	 *
	 * @return 字符串
	 * @since 2022.03
	 */

	public String getGoodsVideo() {
		return goodsVideo;
	}

	/**
	 * 设置
	 *
	 * @param goodsVideo goodsVideo
	 * @return 无返回值
	 * @since 2022.03
	 */

	public void setGoodsVideo(String goodsVideo) {
		this.goodsVideo = goodsVideo;
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
	 * @return 无返回值
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

	public String getSalesModel() {
		return salesModel;
	}

	/**
	 * 设置
	 *
	 * @param salesModel salesModel
	 * @return 无返回值
	 * @since 2022.03
	 */

	public void setSalesModel(String salesModel) {
		this.salesModel = salesModel;
	}

	/**
	 * 获取
	 *
	 * @return 结果数量
	 * @since 2022.03
	 */

	public Long getPosterPicId() {
		return posterPicId;
	}

	/**
	 * 设置
	 *
	 * @param posterPicId posterPicId
	 * @return 无返回值
	 * @since 2022.03
	 */

	public void setPosterPicId(Long posterPicId) {
		this.posterPicId = posterPicId;
	}

	/**
	 * 获取
	 *
	 * @return 字符串
	 * @since 2022.03
	 */

	public String getGoodsType() {
		return goodsType;
	}

	/**
	 * 设置
	 *
	 * @param goodsType goodsType
	 * @return 无返回值
	 * @since 2022.03
	 */

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	/**
	 * 获取
	 *
	 * @return 字符串
	 * @since 2022.03
	 */

	public String getParams() {
		return params;
	}

	/**
	 * 设置
	 *
	 * @param params params
	 * @return 无返回值
	 * @since 2022.03
	 */

	public void setParams(String params) {
		this.params = params;
	}

	//
	// public GoodsPO(GoodsOperationDTO goodsOperationDTO) {
	//    this.goodsName = goodsOperationDTO.getGoodsName();
	//    this.categoryPath = goodsOperationDTO.getCategoryPath();
	//    this.storeCategoryPath = goodsOperationDTO.getStoreCategoryPath();
	//    this.brandId = goodsOperationDTO.getBrandId();
	//    this.templateId = goodsOperationDTO.getTemplateId();
	//    this.recommend = goodsOperationDTO.getRecommend();
	//    this.sellingPoint = goodsOperationDTO.getSellingPoint();
	//    this.salesModel = goodsOperationDTO.getSalesModel();
	//    this.goodsUnit = goodsOperationDTO.getGoodsUnit();
	//    this.intro = goodsOperationDTO.getIntro();
	//    this.mobileIntro = goodsOperationDTO.getMobileIntro();
	//    this.goodsVideo = goodsOperationDTO.getGoodsVideo();
	//    this.price = goodsOperationDTO.getPrice();
	//    if (goodsOperationDTO.getGoodsParamsDTOList() != null
	//            && goodsOperationDTO.getGoodsParamsDTOList().isEmpty()) {
	//        this.params = JSONUtil.toJsonStr(goodsOperationDTO.getGoodsParamsDTOList());
	//    }
	//
	//    // 如果立即上架则
	//    this.marketEnable = Boolean.TRUE.equals(goodsOperationDTO.getRelease())
	//            ? GoodsStatusEnum.UPPER.name()
	//            : GoodsStatusEnum.DOWN.name();
	//    this.goodsType = goodsOperationDTO.getGoodsType();
	//    this.grade = BigDecimal.valueOf(100);
	//
	//    // 循环sku，判定sku是否有效
	//    for (Map<String, Object> sku : goodsOperationDTO.getSkuList()) {
	//        // 判定参数不能为空
	//        if (sku.get("sn") == null) {
	//            throw new BusinessException(ResultEnum.GOODS_SKU_SN_ERROR);
	//        }
	//        // 商品SKU价格不能小于等于0
	//        if (StringUtils.isEmpty(sku.get("price").toString())
	//                || Convert.toBigDecimal(sku.get("price")).compareTo(BigDecimal.ZERO) <= 0) {
	//            throw new BusinessException(ResultEnum.GOODS_SKU_PRICE_ERROR);
	//        }
	//        // 商品SKU成本价不能小于等于0
	//        if (StringUtils.isEmpty(sku.get("cost").toString())
	//                || Convert.toBigDecimal(sku.get("cost")).compareTo(BigDecimal.ZERO) <= 0) {
	//            throw new BusinessException(ResultEnum.GOODS_SKU_COST_ERROR);
	//        }
	//        // 商品重量不能为负数 虚拟商品没有重量字段
	//        if (sku.containsKey("weight")
	//                && (StringUtils.isEmpty(sku.get("weight").toString())
	//                        || Convert.toBigDecimal(sku.get("weight").toString())
	//                                        .compareTo(BigDecimal.ZERO)
	//                                < 0)) {
	//            throw new BusinessException(ResultEnum.GOODS_SKU_WEIGHT_ERROR);
	//        }
	//        // 商品库存数量不能为负数
	//        if (StringUtils.isEmpty(sku.get("quantity").toString())
	//                || Convert.toInt(sku.get("quantity").toString()) < 0) {
	//            throw new BusinessException(ResultEnum.GOODS_SKU_QUANTITY_ERROR);
	//        }
	//    }
	// }

	/**
	 * 获取
	 *
	 * @return 字符串
	 * @since 2022.03
	 */

	public String getIntro() {
		if (CharSequenceUtil.isNotEmpty(intro)) {
			return HtmlUtil.unescape(intro);
		}
		return intro;
	}

	/**
	 * 获取
	 *
	 * @return 字符串
	 * @since 2022.03
	 */

	public String getMobileIntro() {
		if (CharSequenceUtil.isNotEmpty(mobileIntro)) {
			return HtmlUtil.unescape(mobileIntro);
		}
		return mobileIntro;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
			return false;
		}
		GoodsPO goods = (GoodsPO) o;
		return getId() != null && Objects.equals(getId(), goods.getId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
