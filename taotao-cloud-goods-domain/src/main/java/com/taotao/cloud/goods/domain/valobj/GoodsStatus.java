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

package com.taotao.cloud.goods.domain.valobj;

import com.taotao.boot.ddd.model.domain.ValueObjectEnum;

/**
 * 商品状态
 *
 */
public enum GoodsStatus implements ValueObjectEnum<Integer> {
    // 10 - 已上架
    SHELVED(10, "已上架"),
    // 20 - 已下架
    UNSHELVED(20, "已下架");

    /**
     * 状态编码
     */
    private Integer value;

    /**
     * 状态描述
     */
    private String desc;

    GoodsStatus(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    public static GoodsStatus of(Integer value) {
        return ValueObjectEnum.of(value, GoodsStatus.class);
    }

    @Override
    public boolean sameValueAs(Integer other) {
        return false;
    }
}
