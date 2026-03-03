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

package com.taotao.cloud.goods.application.dto.own.goods.command;

import com.taotao.boot.common.model.ddd.types.Command;;
import com.taotao.cloud.goods.api.enums.GoodsTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serial;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import lombok.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

/** 商品编辑DTO */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
@AllArgsConstructor
@NoArgsConstructor
public class GoodsAddCommand implements Command {

    @Serial private static final long serialVersionUID = -509667581371776913L;

    @Schema(description = "商品id")
    private Long goodsId;

    @NotNull(message = "商品价格不能为空")
    @Min(value = 0, message = "商品价格不能为负数")
    @Max(value = 99999999, message = "商品价格不能超过99999999")
    @Schema(description = "商品价格", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal price;

    @Schema(description = "分类path")
    private String categoryPath;

    @Size(max = 200, message = "选择了太多店铺分类")
    @Schema(description = "店铺分类id", requiredMode = Schema.RequiredMode.REQUIRED)
    private String storeCategoryPath;

    @Min(value = 0, message = "品牌值不正确")
    @Schema(description = "品牌id")
    private Long brandId;

    @NotEmpty(message = "商品名称不能为空")
    @Length(max = 50, message = "商品名称不能超过50个字符")
    @Schema(description = "商品名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String goodsName;

    @Schema(description = "详情")
    private String intro;

    @Schema(description = "商品移动端详情")
    private String mobileIntro;

    @Min(value = 0, message = "库存不能为负数")
    @Max(value = 99999999, message = "库存不能超过99999999")
    @Schema(description = "库存")
    private Integer quantity;

    @Schema(description = "是否立即发布")
    private Boolean release;

    @Schema(description = "是否是推荐商品")
    private Boolean recommend;

    @Schema(description = "商品参数")
    private List<GoodsParamsAddCommand> goodsParamsAddCmdList;

    @Schema(description = "商品图片")
    private List<String> goodsGalleryList;

    @NotNull(message = "运费模板不能为空，没有运费模板时，传值0")
    @Min(value = 0, message = "运费模板值不正确")
    @Schema(description = "运费模板id,不需要运费模板时值是0", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long templateId;

    @Valid
    @Schema(description = "sku列表")
    private List<Map<String, Object>> skuList;

    @Schema(description = "卖点")
    private String sellingPoint;

    @Schema(description = "销售模式", requiredMode = Schema.RequiredMode.REQUIRED)
    private String salesModel;

    @Schema(description = "是否有规格", hidden = true)
    private String haveSpec;

    @Schema(description = "销售模式", requiredMode = Schema.RequiredMode.REQUIRED)
    private String goodsUnit;

    @Schema(description = "商品描述")
    private String info;

    @Schema(description = "是否重新生成sku数据")
    private Boolean regeneratorSkuFlag;

    /**
     * @see GoodsTypeEnum
     */
    @Schema(description = "商品类型")
    // @EnumValue(strValues = {"PHYSICAL_GOODS", "VIRTUAL_GOODS", "E_COUPON"}, message =
    // "商品类型参数值错误")
    private String goodsType;

    @Schema(description = "商品视频")
    private String goodsVideo;

    public String getGoodsName() {
        // 对商品对名称做一个极限处理。这里没有用xss过滤是因为xss过滤为全局过滤，影响很大。
        // 业务中，全局代码中只有商品名称不能拥有英文逗号，是由于商品名称存在一个数据库联合查询，结果要根据逗号分组
        return goodsName.replace(",", "");
    }

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId( Long goodsId ) {
		this.goodsId = goodsId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice( BigDecimal price ) {
		this.price = price;
	}

	public String getCategoryPath() {
		return categoryPath;
	}

	public void setCategoryPath( String categoryPath ) {
		this.categoryPath = categoryPath;
	}

	public String getStoreCategoryPath() {
		return storeCategoryPath;
	}

	public void setStoreCategoryPath( String storeCategoryPath ) {
		this.storeCategoryPath = storeCategoryPath;
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId( Long brandId ) {
		this.brandId = brandId;
	}

	public void setGoodsName( String goodsName ) {
		this.goodsName = goodsName;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro( String intro ) {
		this.intro = intro;
	}

	public String getMobileIntro() {
		return mobileIntro;
	}

	public void setMobileIntro( String mobileIntro ) {
		this.mobileIntro = mobileIntro;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity( Integer quantity ) {
		this.quantity = quantity;
	}

	public Boolean getRelease() {
		return release;
	}

	public void setRelease( Boolean release ) {
		this.release = release;
	}

	public Boolean getRecommend() {
		return recommend;
	}

	public void setRecommend( Boolean recommend ) {
		this.recommend = recommend;
	}

	public List<GoodsParamsAddCommand> getGoodsParamsAddCmdList() {
		return goodsParamsAddCmdList;
	}

	public void setGoodsParamsAddCmdList(
		List<GoodsParamsAddCommand> goodsParamsAddCmdList ) {
		this.goodsParamsAddCmdList = goodsParamsAddCmdList;
	}

	public List<String> getGoodsGalleryList() {
		return goodsGalleryList;
	}

	public void setGoodsGalleryList( List<String> goodsGalleryList ) {
		this.goodsGalleryList = goodsGalleryList;
	}

	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId( Long templateId ) {
		this.templateId = templateId;
	}

	public List<Map<String, Object>> getSkuList() {
		return skuList;
	}

	public void setSkuList( List<Map<String, Object>> skuList ) {
		this.skuList = skuList;
	}

	public String getSellingPoint() {
		return sellingPoint;
	}

	public void setSellingPoint( String sellingPoint ) {
		this.sellingPoint = sellingPoint;
	}

	public String getSalesModel() {
		return salesModel;
	}

	public void setSalesModel( String salesModel ) {
		this.salesModel = salesModel;
	}

	public String getHaveSpec() {
		return haveSpec;
	}

	public void setHaveSpec( String haveSpec ) {
		this.haveSpec = haveSpec;
	}

	public String getGoodsUnit() {
		return goodsUnit;
	}

	public void setGoodsUnit( String goodsUnit ) {
		this.goodsUnit = goodsUnit;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo( String info ) {
		this.info = info;
	}

	public Boolean getRegeneratorSkuFlag() {
		return regeneratorSkuFlag;
	}

	public void setRegeneratorSkuFlag( Boolean regeneratorSkuFlag ) {
		this.regeneratorSkuFlag = regeneratorSkuFlag;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType( String goodsType ) {
		this.goodsType = goodsType;
	}

	public String getGoodsVideo() {
		return goodsVideo;
	}

	public void setGoodsVideo( String goodsVideo ) {
		this.goodsVideo = goodsVideo;
	}
}
