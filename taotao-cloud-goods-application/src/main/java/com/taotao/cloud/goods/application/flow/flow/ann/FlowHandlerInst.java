package com.taotao.cloud.goods.application.flow.flow.ann;

import com.taotao.cloud.goods.application.flow.flow.FlowRegister;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface FlowHandlerInst {
    /**
     * @return
     */
    String channel();

    /**
     * @return
     */
    String[] businessType();

    /**
     * @return
     */
    String businessSubType() default FlowRegister.BUSINESS_SUB_TYPE_COMMON;

    /**
     * @return
     */
    String businessCode() default FlowRegister.BUSINESS_CODE_COMMON;

    /**
     * @return
     */
    String[] flowEvent();
}
