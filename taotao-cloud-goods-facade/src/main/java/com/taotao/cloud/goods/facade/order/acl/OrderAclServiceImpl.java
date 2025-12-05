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

package com.taotao.cloud.goods.facade.order.acl;

import com.taotao.boot.ddd.acl.BaseAclService;
import com.taotao.boot.ddd.gateway.model.GatewayResponse;
import com.taotao.cloud.goods.application.acl.SysAclService;
import com.taotao.cloud.goods.application.dto.sys.req.DictReq;
import com.taotao.cloud.goods.application.dto.sys.res.DictRes;
import com.taotao.cloud.goods.facade.assembler.SysFacadeAssembler;
import com.taotao.cloud.goods.facade.order.invoker.OrderInvoker;
import com.taotao.cloud.sys.api.client.response.DictApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class OrderAclServiceImpl extends BaseAclService  {
//
//	@Override
//	public DictRes findByCode(DictReq dictReq) {
////		GatewayResponse<DictApiResponse> gatewayResponse = sysInvoker.findByCode(makeRequest(facadeAssembler.toReq(dictReq)));
////		DictApiResponse result = this.getResult(gatewayResponse);
//
//
////		DictApiResponse dictApiResponse = dictClientProxy.findByCode();
////		return facadeAssembler.toRes(null);
//		return null;
//	}
}
