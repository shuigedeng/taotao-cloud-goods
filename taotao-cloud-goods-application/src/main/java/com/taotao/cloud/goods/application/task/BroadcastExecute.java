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

package com.taotao.cloud.goods.application.task;

import com.taotao.boot.common.utils.log.LogUtils;
import com.taotao.boot.job.xxl.timetask.EveryHourExecute;
import com.taotao.cloud.goods.application.service.command.CommodityCommandService;
import com.xxl.job.core.context.XxlJobHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 小程序直播状态获取
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 16:54:43
 */
@Component
@RequiredArgsConstructor
public class BroadcastExecute implements EveryHourExecute {

    /**
     * 商品服务
     */
    private final CommodityCommandService commodityService;

    /**
     * 执行
     */
    @Override
    public void execute() {
        // 同步直播商品状态
        long jobId = XxlJobHelper.getJobId();
        String jobParam = XxlJobHelper.getJobParam();
        long shardIndex = XxlJobHelper.getShardIndex();
        long shardTotal = XxlJobHelper.getShardTotal();
        String jobLogFileName = XxlJobHelper.getJobLogFileName();

        XxlJobHelper.log(
                "XXL-JOB, successsssssssssss, jobId: {}, jobParam: {}, shardIndex:{}, shardTotal:{}, jobLogFileName:{}",
                jobId,
                jobParam,
                shardIndex,
                shardTotal,
                jobLogFileName);

        LogUtils.info(
                "=============xxljob succcccccccccccccc, jobId: {}, jobParam: {}, shardIndex:{}, shardTotal:{}, jobLogFileName:{}",
                jobId,
                jobParam,
                shardIndex,
                shardTotal,
                jobLogFileName);
    }
}
