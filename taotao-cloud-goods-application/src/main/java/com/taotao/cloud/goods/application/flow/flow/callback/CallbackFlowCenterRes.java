package com.taotao.cloud.goods.application.flow.flow.callback;

import com.taotao.cloud.goods.application.flow.flow.treeflow.context.FlowBusinessContext;
import com.taotao.cloud.goods.application.flow.flow.treeflow.context.FlowContext;
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
