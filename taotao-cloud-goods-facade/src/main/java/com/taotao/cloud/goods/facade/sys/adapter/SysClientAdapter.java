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

package com.taotao.cloud.goods.facade.sys.adapter;

import com.taotao.cloud.goods.facade.sys.vo.DictVO;
import com.taotao.cloud.sys.api.feign.response.DictApiResponse;
import org.springframework.stereotype.Component;

@Component
public class SysClientAdapter {

    public DictVO convert(DictApiResponse dictApiResponse) {
		DictVO dictVO = new DictVO();
		dictVO.setDictName(dictApiResponse.dictName());
		dictVO.setDictCode(dictApiResponse.dictCode());
		dictVO.setRemark(dictApiResponse.remark());
		dictVO.setDescription(dictApiResponse.description());
		dictVO.setId(dictApiResponse.id());
		dictVO.setSortNum(dictApiResponse.sortNum());
        return dictVO;
    }
}
