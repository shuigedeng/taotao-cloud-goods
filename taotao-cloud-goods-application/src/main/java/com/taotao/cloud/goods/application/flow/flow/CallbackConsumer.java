package com.taotao.cloud.goods.application.flow.flow;

public interface CallbackConsumer<T> {
    /**
     * @param t
     */
    void consumer(T t);
}
