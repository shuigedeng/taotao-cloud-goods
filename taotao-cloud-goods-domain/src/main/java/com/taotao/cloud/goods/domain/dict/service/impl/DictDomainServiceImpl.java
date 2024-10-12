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

package com.taotao.cloud.goods.domain.dict.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taotao.boot.common.model.PageQuery;
import com.taotao.cloud.goods.domain.dict.entity.DictEntity;
import com.taotao.cloud.goods.domain.dict.service.DictDomainService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DictDomainServiceImpl implements DictDomainService {

    //	@Autowired
    //	private  DictRepository dictRepository;

    @Override
    public Boolean insert(DictEntity dictEntity) {
        return null;
    }

    @Override
    public Boolean update(DictEntity dictEntity) {
        return null;
    }

    @Override
    public DictEntity getById(Long id) {
        return null;
    }

    @Override
    public Boolean deleteById(Long id) {
        return null;
    }

    @Override
    public IPage<DictEntity> list(DictEntity dictEntity, PageQuery pageQuery) {
        //		return dictRepository.list(dictEntity, pageQuery);
        return null;
    }
}
