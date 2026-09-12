package com.taotao.cloud.goods.application.flow.flow.treeflow.channel;

import com.taotao.cloud.goods.application.flow.flow.treeflow.FlowCenter;
import com.taotao.cloud.goods.application.flow.flow.treeflow.FlowHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ManualHandler extends FlowHandler {

    /**
     * @param flowCenter
     */
    public ManualHandler(FlowCenter flowCenter) {
        super(flowCenter);
    }

    @Override
    public String channelName() {
        return ChannelType.MANUAL;
    }
}
