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

import com.taotao.boot.common.model.ddd.types.Command;
import io.soabase.recordbuilder.core.RecordBuilder;
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

import org.hibernate.validator.constraints.Length;

/**
 * 商品编辑DTO
 */
@RecordBuilder
public record GoodsAddCommand(@Schema(description = "商品id") Long goodsId,
							  @Schema(description = "商品价格") @NotNull(message = "商品价格不能为空") @Min(value = 0, message = "商品价格不能为负数") @Max(value = 99999999, message = "商品价格不能超过99999999") BigDecimal price,
							  @Schema(description = "分类path") String categoryPath,
							  @Schema(description = "店铺分类id") @Size(max = 200, message = "选择了太多店铺分类") String storeCategoryPath,
							  @Schema(description = "品牌id") @Min(value = 0, message = "品牌值不正确") Long brandId,
							  @Schema(description = "商品名称") @NotEmpty(message = "商品名称不能为空") @Length(max = 50, message = "商品名称不能超过50个字符") String goodsName,
							  @Schema(description = "详情") String intro,
							  @Schema(description = "商品移动端详情") String mobileIntro,
							  @Schema(description = "库存") @Min(value = 0, message = "库存不能为负数") @Max(value = 99999999, message = "库存不能超过99999999") Integer quantity,
							  @Schema(description = "是否立即发布") Boolean release,
							  @Schema(description = "是否是推荐商品") Boolean recommend,
							  @Schema(description = "商品参数") List<GoodsParamsAddCommand> goodsParamsAddCmdList,
							  @Schema(description = "商品图片") List<String> goodsGalleryList,
							  @Schema(description = "运费模板id,不需要运费模板时值是0") @NotNull(message = "运费模板不能为空，没有运费模板时，传值0") @Min(value = 0, message = "运费模板值不正确") Long templateId,
							  @Schema(description = "sku列表") @Valid List<Map<String, Object>> skuList,
							  @Schema(description = "卖点") String sellingPoint,
							  @Schema(description = "销售模式") String salesModel,
							  @Schema(description = "是否有规格", hidden = true) String haveSpec,
							  @Schema(description = "销售模式") String goodsUnit,
							  @Schema(description = "商品描述") String info,
							  @Schema(description = "是否重新生成sku数据") Boolean regeneratorSkuFlag,
							  @Schema(description = "商品类型") String goodsType,
							  @Schema(description = "商品视频") String goodsVideo) implements Command {

	@Serial
	private static final long serialVersionUID = -509667581371776913L;

}
