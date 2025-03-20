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

package com.taotao.cloud.goods.application.dto.goods.query;

import com.taotao.boot.common.model.PageQuery;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.ToString;

/**
 * 商品分页查询
 *
 * @author luohq
 * @date 2022-11-27 19:07
 */
@Data
@ToString(callSuper = true)
public class GoodsPageQuery extends PageQuery {
    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品分类ID
     */
    private Long categoryId;

    /**
     * 起始商品价格
     */
    private BigDecimal startGoodsPrice;

    /**
     * 终止商品价格
     */
    private BigDecimal endGoodsPrice;

    /**
     * 商品状态(10已上架, 20已下架)
     */
    private Integer goodsStatus;

    /**
     * 过期时间（在此之前过期）
     */
    private LocalDate beforeExpirationDate;
    /**
     * 起始创建时间
     */
    private LocalDateTime createTimeStart;

    /**
     * 结束创建时间
     */
    private LocalDateTime createTimeEnd;
}
