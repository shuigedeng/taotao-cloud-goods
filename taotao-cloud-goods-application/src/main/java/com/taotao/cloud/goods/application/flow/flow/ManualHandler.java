package com.taotao.cloud.goods.application.flow.flow;

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
