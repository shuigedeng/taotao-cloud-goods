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

import org.springframework.stereotype.Component;

@Component
public class SysDictClientProxy {

    //    @Autowired private DictApi dictApi;
    //
    //    @DubboReference private DictRpcService dictRpcService;
    //
    //    @Resource private SysClientAdapter sysClientAdapter;
    //
    //    // 查询用户
    //    public DictVO findByCode() {
    //        FeignRequest<DictQueryApiRequest> dictQueryApiRequest =
    //                FeignRequest.<DictQueryApiRequest>builder().data(new
    // DictQueryApiRequest()).build();
    //        FeignResponse<DictApiResponse> dictApiResponse =
    // dictApi.findByCode(dictQueryApiRequest);
    //
    //        //        DictRpcResponse dictRpcResponse = dictRpcService.findByCode(123);
    //
    //        return sysClientAdapter.convert(dictApiResponse.getData());
    //    }
}
