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

package com.taotao.cloud.goods.infrastructure.data.dataparam;

import com.taotao.boot.common.model.ddd.query.PageQuery;
import lombok.*;
import lombok.EqualsAndHashCode;

/**
 * BrandPageParam
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2025-12-19 09:30:45
 */
@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@ToString
public class BrandPageParam extends PageQuery {

    // 品牌名称
    private String name;


    public static final class BrandPageParamBuilder {

        private String name;
        private PageQuery pageQuery;




        /**
         * 品牌页码参数Builder
         *
         * @return 无返回值
         * @since 2022.03
         */

        private BrandPageParamBuilder() {
        }




        /**
         * 设置名称
         *
         * @param name 名称
         * @return 品牌页码参数Builder
         * @since 2022.03
         */

        public BrandPageParamBuilder name( String name ) {
            this.name = name;
            return this;
        }




        /**
         * 分页查询
         *
         * @param pageQuery 页码Query
         * @return 品牌页码参数Builder
         * @since 2022.03
         */

        public BrandPageParamBuilder pageQuery( PageQuery pageQuery ) {
            this.pageQuery = pageQuery;
            return this;
        }




        /**
         * 构建
         *
         * @return 品牌页码参数
         * @since 2022.03
         */

        public BrandPageParam build() {
            BrandPageParam brandPageParam = new BrandPageParam();
            brandPageParam.setName(name);
            brandPageParam.setCurrentPage(pageQuery.getCurrentPage());
            brandPageParam.setPageSize(pageQuery.getPageSize());
            brandPageParam.setSort(pageQuery.getSort());
            brandPageParam.setOrder(pageQuery.getOrder());
            return brandPageParam;
        }
    }
}
