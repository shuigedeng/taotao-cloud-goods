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

package com.taotao.cloud.goods.application.pie.goods;

import com.taotao.boot.common.support.pie.BootStrap;
import com.taotao.cloud.goods.application.context.GoodsContext;
import com.taotao.cloud.goods.application.pie.category.handlers.ArticleModifyContentHandler;
import com.taotao.cloud.goods.application.pie.category.handlers.ArticleModifyTitleHandler;
import com.taotao.cloud.goods.application.pie.category.handlers.CheckParameterHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeptModifyCBootstrap {

    private static final Logger logger = LoggerFactory.getLogger(DeptModifyCBootstrap.class);

    public void test1() {
        // 入参
		GoodsContext dto = new GoodsContext();
        // 创建引导类
        BootStrap bootStrap = new BootStrap();

		GoodsContext result = (GoodsContext) bootStrap
                .inboundParameter(dto) // 入参
                .outboundFactory(new ResultFactory()) // 出参工厂
                .channel(new DeptModifyChannel()) // 自定义channel
                .addChannelHandlerAtLast("checkParameter", new CheckParameterHandler()) // 第一个handler
                .addChannelHandlerAtLast("modifyTitle", new ArticleModifyTitleHandler()) // 第二个handler
                .addChannelHandlerAtLast("modifyContent", new ArticleModifyContentHandler()) // 第三个handler
                .process(); // 执行
        // result为执行结果
        //logger.info("result:code={},msg={}", result.getDeptModifyCmd(), result.getDeptAgg());
    }
}
