package com.taotao.cloud.goods.application.flow.flow;

/**
 * FlowTraceExtract
 */
public interface FlowTraceExtract {
    /**
     * 从对象中提取流程追踪ID
     *
     * @param obj 目标对象
     * @return 流程追踪ID
     */
    String extract(Object obj);

    /**
     * 从对象中提取分片号
     *
     * @param obj 目标对象
     * @return 分片号
     */
    String splitNo(Object obj);
}
