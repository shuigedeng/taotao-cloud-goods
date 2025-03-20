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

package com.taotao.cloud.goods.application.service.query;

import com.taotao.boot.ddd.model.application.service.QueryService;

/**
 * 自定义分词业务层
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 16:59:50
 */
public interface CustomWordsQueryService extends QueryService {

    /**
     * 是否存在分词
     *
     * @param words 分词
     * @return 是否存在
     */
    boolean existWords(String words);

    /// **
    // * 分页查询自定义分词
    // *
    // * @param words  分词
    // * @param pageVo 分页信息
    // * @return 自定义分词分页信息
    // */
    // IPage<CustomWordsPO> getCustomWordsByPage(String words, PageVO pageVo);
}
