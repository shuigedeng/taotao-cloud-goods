package com.taotao.cloud.goods.application.flow.flow;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE})
public @interface FlowInfoAnnotation {
    /**
    * 名称
    *
    * @return
    */
    String name();

    /**
    * 渠道
    *
    * @return
    */
    String channel();
}
