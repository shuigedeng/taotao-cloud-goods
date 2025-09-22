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

import com.taotao.boot.ddd.model.domain.ValueObject;
import jakarta.validation.constraints.NotBlank;
import java.util.Objects;
import org.hibernate.validator.constraints.Length;

/**
 * 分类名称
 *
 * @author luohq
 * @date 2023-01-04 13:21
 */
public class CategoryName implements ValueObject {

    /**
     * 分类名称
     */
    @NotBlank
    @Length(min = 1, max = 64)
    private String value;

    CategoryName() {}

    CategoryName(String value) {
        this.value = value;
        this.validateSelf();
    }

    public static CategoryName of(String categoryName) {
        return new CategoryName(categoryName);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CategoryName that = (CategoryName) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "CategoryName{" + "value='" + value + '\'' + '}';
    }

    @Override
    public boolean sameValueAs(Object other) {
        return false;
    }
}
