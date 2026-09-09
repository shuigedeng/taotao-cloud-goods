package com.taotao.cloud.goods.application.flow.flow.spring;

import com.taotao.cloud.goods.application.flow.flow.FlowHandlerInfo;
import lombok.Data;

import java.util.List;

@Data
public class FlowHandlerInfoProxy {
    /**
     * channel
     */
    private String channel;
    /**
     * FlowHandlerInfo
     */
    private List<FlowHandlerInfo> flowHandlerInfoList;
}
