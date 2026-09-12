package com.taotao.cloud.goods.application.flow.flow.callback;

public interface CallbackConsumer<T> {
    /**
     * @param t
     */
    void consumer(T t);
}
