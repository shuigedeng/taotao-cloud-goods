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

package com.taotao.cloud.goods.application.service.query;

import com.taotao.boot.ddd.model.application.service.QueryService;
import com.taotao.cloud.goods.application.dto.store.result.StudioCommodityResult;

/**
 * 直播间业务层
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 17:01:11
 */
public interface StudioQueryService extends QueryService {

    /**
     * 获取直播间信息
     *
     * @param id 直播间ID
     * @return {@link StudioCommodityResult }
     * @since 2022-04-27 17:01:11
     */
    StudioCommodityResult getStudioVO(Long id);

    /**
     * 获取直播间回放
     *
     * @param roomId 房间ID
     * @return {@link String }
     * @since 2022-04-27 17:01:11
     */
    String getLiveInfo(Integer roomId);

    /**
     * 获取直播间列表
     *
     * @param PageQuery 分页
     * @param recommend 是否推荐
     * @param status 直播间状态
     * @return {@link IPage }<{@link StudioPO }>
     * @since 2022-04-27 17:01:12
     */
    //    IPage<StudioPO> studioList(PageQuery PageQuery, Integer recommend, String status);

}
