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

package com.taotao.cloud.goods.application.pie.dict;

import com.taotao.boot.common.pie.example3.handlers.ArticleModifyContentHandler;
import com.taotao.boot.common.pie.example3.handlers.ArticleModifyTitleHandler;
import com.taotao.boot.common.pie.example3.handlers.CheckParameterHandler;
import com.taotao.boot.common.pie.example3.handlers.ExceptionHandler;
import com.taotao.boot.common.support.pie.BootStrap;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO:Add the description of this class.
 *
 * @author: <a href=mailto:943868899@qq.com>Yujie</a>
 */
public class ArticleModifyExample3 {

    private static final Logger logger = LoggerFactory.getLogger(ArticleModifyExample3.class);

	@Test
    public void test() {
        // 入参
        ArticleTitleModifyCmd dto = new ArticleTitleModifyCmd();
        dto.setArticleId("articleId_001");
        dto.setTitle("articleId_001_title");
        dto.setContent("articleId_001_content");
        // 创建引导类
        BootStrap bootStrap = new BootStrap();

        Result result = (Result) bootStrap
                .inboundParameter(dto) // 入参
                .outboundFactory(new ResultFactory()) // 出参工厂
                .channel(new ArticleModifyChannel()) // 自定义channel
                .addChannelHandlerAtLast("checkParameter", new CheckParameterHandler()) // 第一个handler
                .addChannelHandlerAtLast("modifyTitle", new ArticleModifyTitleHandler()) // 第二个handler
                .addChannelHandlerAtLast("modifyContent", new ArticleModifyContentHandler()) // 第三个handler
                .addChannelHandlerAtLast("exception", new ExceptionHandler()) // 异常处理handler
                .process(); // 执行
        // result为执行结果
        logger.info("result:code={},msg={}", result.getCode(), result.getMsg());
    }
}
