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

package com.taotao.cloud.goods.domain.goodstag.factory;

/**
 * 商品标签创建工厂<br/>
 * 注：领域对象创建工厂，强调初始创建领域对象的操作（区别于技术层面的构造函数）
 *
 * @author luohq
 * @date 2023-08-03
 */
public class GoodsTagFactory {

    /**
     * 创建商品标签
     *
     * @param tagName 商品标签名称
     * @param tagDesc 商品标签描述
     * @return 初始商品标签
     */
    // public static GoodsTagAgg createGoodsTag(String tagName, String tagDesc) {
    //    return new GoodsTagAgg(
    //            BizId.of(IdGenUtils.nextDigitalId()),
    //            tagName,
    //            tagDesc
    //    );
    // }
}
