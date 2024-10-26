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

package com.taotao.cloud.goods.facade.assembler;

import org.springframework.beans.BeanUtils;

/**
 * 装配工厂
 */
public class AssemblerFactory {
    /**
     * 装配工厂 指定任何装配过程 执行装配
     *
     * @param assembler 装配
     * @param source    S对象
     * @param target    T对象
     */
    public <S, T> void assemble(Assembler<S, T> assembler, S source, T target) {
        assembler.assemble(source, target);
    }

    /**
     * 装配工厂 指定任何装配过程 执行转换 S -> T
     *
     * @param assembler 装配
     * @param source    S对象
     * @param type      T Class
     * @return 转换出 T新对象
     */
    public <S, T> T convert(Assembler<S, T> assembler, S source, Class<T> type) {
        T target = BeanUtils.instantiateClass(type);
        assemble(assembler, source, target);
        return target;
    }
}
