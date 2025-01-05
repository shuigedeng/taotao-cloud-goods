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

package com.taotao.cloud.goods.application.pie.goods.handlers;

import com.taotao.boot.common.support.pie.ChannelHandler;
import com.taotao.boot.common.support.pie.ChannelHandlerContext;
import com.taotao.cloud.goods.application.context.GoodsContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeptModifyContentHandler implements ChannelHandler {

	private Logger logger = LoggerFactory.getLogger(DeptModifyContentHandler.class);

	@Override
	public void channelProcess(ChannelHandlerContext ctx, Object in, Object out) throws Exception {

		logger.info("修改正文:进入修改正文的Handler");

		GoodsContext cmd = (GoodsContext) in;
		//logger.info("修改正文,content={}", cmd.getDeptModifyCmd().getDeptCO().getName());

		logger.info("修改正文:执行完成,即将进入下一个Handler");
		ctx.fireChannelProcess(in, out);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause, Object in, Object out)
		throws Exception {
	}
}
