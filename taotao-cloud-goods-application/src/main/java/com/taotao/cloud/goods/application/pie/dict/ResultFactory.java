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

import com.taotao.boot.common.support.pie.OutboundFactory;
import com.taotao.cloud.goods.application.context.DeptContext;
import com.taotao.cloud.goods.application.context.DictContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 出参工厂
 */
public class ResultFactory implements OutboundFactory {

    private Logger logger = LoggerFactory.getLogger(ResultFactory.class);

    @Override
    public Object newInstance() {
		DictContext result = new DictContext();
		return result;
    }
}
