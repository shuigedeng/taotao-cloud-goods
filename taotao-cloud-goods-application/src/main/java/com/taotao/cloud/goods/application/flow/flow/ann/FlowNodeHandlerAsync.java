package com.taotao.cloud.goods.application.flow.flow.ann;

import com.taotao.cloud.goods.application.flow.flow.FlowInfoAnnotation;
import com.taotao.cloud.goods.application.flow.flow.FlowRegisterInfoAnnotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.PARAMETER})
public @interface FlowNodeHandlerAsync {
    /*
     * flowRegisterInfo
     * @return
     */
    FlowRegisterInfoAnnotation flowRegisterInfo();

    /*
     * flowInfo
     * @return
     */
    FlowInfoAnnotation flowInfo();
}
