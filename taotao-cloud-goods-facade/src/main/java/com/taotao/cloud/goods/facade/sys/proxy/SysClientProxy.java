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

package com.taotao.cloud.goods.facade.sys.proxy;

import com.taotao.boot.common.model.FeignRequest;
import com.taotao.boot.common.model.FeignResponse;
import com.taotao.boot.common.model.RpcRequest;
import com.taotao.boot.common.model.RpcResponse;
import com.taotao.cloud.goods.facade.sys.adapter.SysClientAdapter;
import com.taotao.cloud.goods.facade.sys.vo.DictVO;
import com.taotao.cloud.sys.api.dubbo.DictRpcService;
import com.taotao.cloud.sys.api.dubbo.request.DictQueryRpcRequest;
import com.taotao.cloud.sys.api.dubbo.response.DictRpcResponse;
import com.taotao.cloud.sys.api.feign.DictApi;
import com.taotao.cloud.sys.api.feign.request.DictQueryApiRequest;
import com.taotao.cloud.sys.api.feign.response.DictApiResponse;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SysClientProxy {

	@Autowired
	private DictApi dictApi;

	@DubboReference(version = "2025.11", protocol = "tri",parameters = {
		"clientHost", "192.168.218.1"  // 设置客户端主机
	})
	private DictRpcService dictRpcService;

	@Autowired
	private SysClientAdapter sysClientAdapter;

	// 查询用户
	public DictVO findByCode() {
		FeignRequest<DictQueryApiRequest> dictQueryApiRequest =
			FeignRequest.<DictQueryApiRequest>builder().data(new DictQueryApiRequest()).build();

		FeignResponse<DictApiResponse> dictApiResponse = dictApi.findByCode(dictQueryApiRequest);

//		DictQueryRpcRequest dictQueryRpcRequest = new DictQueryRpcRequest();
//		dictQueryRpcRequest.setCode("123");
//		RpcResponse<DictRpcResponse> dictRpcResponse = dictRpcService.findByCode(RpcRequest.success(dictQueryRpcRequest));

		return sysClientAdapter.convert(dictApiResponse.getData());
	}
}
