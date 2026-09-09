package com.taotao.cloud.goods.application.flow.flow;

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
