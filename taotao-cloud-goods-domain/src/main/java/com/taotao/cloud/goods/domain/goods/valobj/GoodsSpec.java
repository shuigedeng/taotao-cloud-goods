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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.Objects;
import org.hibernate.validator.constraints.Length;

/**
 * 商品规格
 *
 */
public class GoodsSpec implements ValueObject {

    /**
     * 生产日期
     */
    @NotNull
    @PastOrPresent
    private LocalDate manufactureDate;

    /**
     * 过期日期
     */
    @NotNull
    private LocalDate expirationDate;

    /**
     * 商品重量
     */
    @NotNull
    private GoodsWeight goodsWeight;

    /**
     * 商品介绍
     */
    @NotBlank
    @Length(min = 1, max = 1024)
    private String goodsDesc;

    GoodsSpec() {}

    private GoodsSpec(Builder builder) {
        manufactureDate = builder.manufactureDate;
        expirationDate = builder.expirationDate;
        goodsWeight = builder.goodsWeight;
        goodsDesc = builder.goodsDesc;
        this.validateSelf();
    }

    public static Builder builder() {
        return new Builder();
    }

    public LocalDate getManufactureDate() {
        return manufactureDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public GoodsWeight getGoodsWeight() {
        return goodsWeight;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GoodsSpec goodsSpec = (GoodsSpec) o;
        return Objects.equals(manufactureDate, goodsSpec.manufactureDate)
                && Objects.equals(expirationDate, goodsSpec.expirationDate)
                && Objects.equals(goodsWeight, goodsSpec.goodsWeight)
                && Objects.equals(goodsDesc, goodsSpec.goodsDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(manufactureDate, expirationDate, goodsWeight, goodsDesc);
    }

    @Override
    public String toString() {
        return "GoodsSpec{" + "manufactureDate="
                + manufactureDate + ", expirationDate="
                + expirationDate + ", goodsWeight="
                + goodsWeight + ", goodsDesc='"
                + goodsDesc + '\'' + '}';
    }

    @Override
    public boolean sameValueAs(Object other) {
        return false;
    }

    public static final class Builder {
        private LocalDate manufactureDate;
        private LocalDate expirationDate;
        private GoodsWeight goodsWeight;
        private String goodsDesc;

        private Builder() {}

        public Builder manufactureDate(LocalDate val) {
            manufactureDate = val;
            return this;
        }

        public Builder expirationDate(LocalDate val) {
            expirationDate = val;
            return this;
        }

        public Builder goodsWeight(GoodsWeight val) {
            goodsWeight = val;
            return this;
        }

        public Builder goodsDesc(String val) {
            goodsDesc = val;
            return this;
        }

        public GoodsSpec build() {
            return new GoodsSpec(this);
        }
    }
}
