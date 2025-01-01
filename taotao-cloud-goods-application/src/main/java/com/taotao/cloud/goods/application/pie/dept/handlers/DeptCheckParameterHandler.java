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

package com.taotao.cloud.goods.application.pie.dept.handlers;

import com.taotao.boot.common.support.pie.ChannelHandler;
import com.taotao.boot.common.support.pie.ChannelHandlerContext;
import com.taotao.cloud.goods.application.context.GoodsContext;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeptCheckParameterHandler implements ChannelHandler {

	private Logger logger = LoggerFactory.getLogger(DeptCheckParameterHandler.class);

	@Override
	public void channelProcess(ChannelHandlerContext ctx, Object in, Object out) throws Exception {

		logger.info("参数校验:开始执行");

		if (in instanceof GoodsContext) {
			GoodsContext cmd = (GoodsContext) in;

			String name = cmd.getDeptAgg().getDept().getName();
			Objects.requireNonNull(name, "name不能为空");

			String path = cmd.getDeptAgg().getDept().getPath();
			Objects.requireNonNull(path, "path不能为空");
		}

		logger.info("参数校验:校验通过,即将进入下一个Handler");
		ctx.fireChannelProcess(in, out);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause, Object in, Object out)
		throws Exception {
	}
}
