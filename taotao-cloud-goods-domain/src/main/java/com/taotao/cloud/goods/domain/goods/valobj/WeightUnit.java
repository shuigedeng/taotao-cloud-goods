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

package com.taotao.cloud.goods.domain.goods.valobj;

import com.taotao.boot.ddd.model.domain.ValueObjectEnum;

/**
 * 重量单位
 */
public enum WeightUnit implements ValueObjectEnum<String> {
    // 吨
    T("t", "吨"),
    // 公斤
    KG("kg", "公斤"),
    // 克
    G("g", "克");

    /**
     * 单位值
     */
    private String value;

    /**
     * 单位描述
     */
    private String desc;

    WeightUnit(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }

    public static WeightUnit of(String value) {
        return ValueObjectEnum.of(value, WeightUnit.class);
    }

    @Override
    public boolean sameValueAs(String other) {
        return false;
    }
}
