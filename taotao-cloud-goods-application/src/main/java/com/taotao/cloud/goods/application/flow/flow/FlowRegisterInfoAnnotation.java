package com.taotao.cloud.goods.application.flow.flow;

import com.taotao.cloud.goods.application.flow.flow.treeflow.FlowRegister;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE})
public @interface FlowRegisterInfoAnnotation {
    /**
    * 业务类型
    */
    String businessType();
    /**
    * 业务子类型
    */
    String businessSubType() default FlowRegister.BUSINESS_SUB_TYPE_COMMON;
    /**
    * 业务编码
    */
    String businessCode() default FlowRegister.BUSINESS_CODE_COMMON;
}
