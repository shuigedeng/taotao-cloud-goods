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

import com.taotao.boot.ddd.model.domain.ValueObject;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * 商品重量
 *
 */
public class GoodsWeight implements ValueObject {
    /**
     * 重量值
     */
    @NotNull @PositiveOrZero private BigDecimal weight;

    /**
     * 重量单位
     */
    @NotNull private WeightUnit unit;

    GoodsWeight() {}

    GoodsWeight(BigDecimal weight, WeightUnit unit) {
        this.weight = weight;
        this.unit = unit;
        this.validateSelf();
    }

    private GoodsWeight(Builder builder) {
        weight = builder.weight;
        unit = builder.unit;
        this.validateSelf();
    }

    public static GoodsWeight of(BigDecimal weight, WeightUnit unit) {
        return new GoodsWeight(weight, unit);
    }

    public static Builder builder() {
        return new Builder();
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public WeightUnit getUnit() {
        return unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GoodsWeight that = (GoodsWeight) o;
        return Objects.equals(weight, that.weight) && unit == that.unit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight, unit);
    }

    @Override
    public String toString() {
        return "GoodsWeight{" + "weight=" + weight + ", unit=" + unit + '}';
    }

    @Override
    public boolean sameValueAs(Object other) {
        return false;
    }

    public static final class Builder {
        private BigDecimal weight;
        private WeightUnit unit;

        private Builder() {}

        public Builder weight(BigDecimal val) {
            weight = val;
            return this;
        }

        public Builder unit(WeightUnit val) {
            unit = val;
            return this;
        }

        public GoodsWeight build() {
            return new GoodsWeight(this);
        }
    }
}
