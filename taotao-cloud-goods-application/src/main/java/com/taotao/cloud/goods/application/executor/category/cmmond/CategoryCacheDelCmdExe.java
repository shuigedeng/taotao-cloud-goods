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

package com.taotao.cloud.goods.application.executor.category.cmmond;

import static com.taotao.boot.common.enums.CachePrefixEnum.CATEGORY;

import com.taotao.boot.cache.redis.repository.RedisRepository;
import com.taotao.boot.ddd.model.application.executor.Executor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 删除部门执行器.
 */
@Component
@RequiredArgsConstructor
public class CategoryCacheDelCmdExe extends Executor {

    private final RedisRepository redisRepository;

    /**
     * 清除缓存
     */
    public void removeCache() {
        redisRepository.del(CATEGORY.getPrefix());
    }
}
