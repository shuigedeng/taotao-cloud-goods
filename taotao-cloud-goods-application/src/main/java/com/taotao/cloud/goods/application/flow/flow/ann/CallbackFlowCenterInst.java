package com.taotao.cloud.goods.application.flow.flow.ann;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface CallbackFlowCenterInst {
    /**
     * 调用操作名称
     */
    String invokeOpName();

    /**
     * 交易类型（支持多个）
     */
    String[] tradeType();

    /**
     * 交易子类型（支持多个）
     */
    String[] tradeSubType() default {"COMMON"};

    /**
     * 事件名称
     */
    String event() default "";
}
