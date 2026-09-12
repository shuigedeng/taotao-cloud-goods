package com.taotao.cloud.goods.application.flow.flow.treeflow.context;

public interface FlowBusinessContext {
    /**
     * flowComplete
     */
    @Deprecated
    void flowComplete();

    /**
     * 流程完成
     * @return
     */
    @Deprecated
    boolean flowCompleted();
}
