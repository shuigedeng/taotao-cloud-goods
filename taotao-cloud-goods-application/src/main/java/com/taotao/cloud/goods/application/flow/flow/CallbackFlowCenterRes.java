package com.taotao.cloud.goods.application.flow.flow;

import lombok.Data;

@Data
public class CallbackFlowCenterRes<E extends FlowBusinessContext, T> {
    /**
     * FlowContext
     */
    private FlowContext<E> flowContext;
    /**
     * res
     */
    private T res;
    /**
     * flowBreakMark
     */
    private boolean flowBreakMark = false;
}
