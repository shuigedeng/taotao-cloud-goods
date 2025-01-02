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

package com.taotao.cloud.goods.application.service.impl;

import com.taotao.boot.web.base.service.impl.BaseSuperServiceImpl;
import com.taotao.cloud.goods.application.service.StudioCommodityCommandService;
import com.taotao.cloud.goods.infrastructure.persistent.mapper.IStudioCommodityMapper;
import com.taotao.cloud.goods.infrastructure.persistent.po.StudioCommodityPO;
import com.taotao.cloud.goods.infrastructure.persistent.repository.cls.StudioCommodityRepository;
import com.taotao.cloud.goods.infrastructure.persistent.repository.inf.IStudioCommodityRepository;
import org.springframework.stereotype.Service;

/**
 * 直播间-商品关联业务层实现
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 17:03:01
 */
@Service
public class StudioCommodityQueryServiceImpl
	extends BaseSuperServiceImpl<
	StudioCommodityPO, Long, IStudioCommodityMapper, StudioCommodityRepository, IStudioCommodityRepository>
	implements StudioCommodityCommandService {

}