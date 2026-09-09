package com.taotao.cloud.goods.application.flow.flow;

import java.util.concurrent.TimeUnit;

public interface BusinessLocker<E> {
    /**
     * 尝试锁定业务对象
     *
     * @param e 业务对象
     * @return true 锁定成功，false 锁定失败（已被其他线程锁定）
     */
    boolean lock(E e);

    /**
     * 解锁业务对象
     *
     * @param e 业务对象
     */
    void unlock(E e);

    /**
     * 尝试锁定业务对象（带超时）
     */
    default boolean tryLock(E e, long timeout, TimeUnit unit) {
        return lock(e);  // 默认实现
    }
}
