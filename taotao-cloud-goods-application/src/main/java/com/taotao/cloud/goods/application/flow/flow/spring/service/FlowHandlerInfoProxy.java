package com.taotao.cloud.goods.application.flow.flow.spring.service;

import com.taotao.cloud.goods.application.flow.flow.treeflow.info.FlowHandlerInfo;
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
