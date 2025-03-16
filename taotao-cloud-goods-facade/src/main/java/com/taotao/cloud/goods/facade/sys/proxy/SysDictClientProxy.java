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

import com.taotao.cloud.goods.facade.sys.adapter.SysClientAdapter;
import com.taotao.cloud.goods.facade.sys.vo.DictVO;
import com.taotao.cloud.sys.api.dubbo.DictRpcService;
import com.taotao.cloud.sys.api.dubbo.response.DictRpcResponse;
import com.taotao.cloud.sys.api.feign.DictApi;
import com.taotao.cloud.sys.api.feign.response.DictApiResponse;
import jakarta.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SysDictClientProxy {

    @Autowired
    private DictApi dictApi;
	@DubboReference
	private DictRpcService dictRpcService;

    @Resource
    private SysClientAdapter sysClientAdapter;

    // 查询用户
    public DictVO findByCode() {
		DictApiResponse dictApiResponse = dictApi.findByCode("abcd");

		DictRpcResponse dictRpcResponse = dictRpcService.findByCode(123);

        return sysClientAdapter.convert(dictApiResponse);
    }
}
