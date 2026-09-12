package com.taotao.cloud.goods.application.flow.flow.spring.service;

import com.taotao.cloud.goods.application.flow.flow.treeflow.FlowCenter;
import com.taotao.cloud.goods.application.flow.flow.treeflow.channel.ManualHandler;

public class SpringManualHandler extends ManualHandler {
    /**
     * SpringManualHandler
     * @param flowCenter
     */
    public SpringManualHandler( FlowCenter flowCenter) {
        super(flowCenter);
    }
}
