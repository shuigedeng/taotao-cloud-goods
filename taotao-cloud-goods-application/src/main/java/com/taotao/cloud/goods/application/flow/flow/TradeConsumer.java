package com.taotao.cloud.goods.application.flow.flow;

import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public interface TradeConsumer<E extends TradeContext> {
    /**
     * apply
     */
    void apply(E e);

    default TradeConsumer<E> then( Predicate<E> predicate, TradeConsumer<E> next, String logPrint) {
        return this.then(predicate, next.wrapLogPrint(logPrint));
    }

    /**
     * then
     */
    default TradeConsumer<E> then(TradeConsumer<E> next, String logPrint) {
        return this.then(next.wrapLogPrint(logPrint));
    }

	default TradeConsumer<E> then(Predicate<E> predicate, TradeConsumer<E> next) {
		Objects.requireNonNull(next);
		Objects.requireNonNull(predicate);
		return (E e) -> {
			apply(e);
			if (!e.isEnd()) {
				if (predicate.test(e)) {
					next.apply(e);
				} else {
					e.end();
				}
			}
		};
	}

	default TradeConsumer<E> then(TradeConsumer<E> next) {
		Objects.requireNonNull(next);
		return (E e) -> {
			apply(e);
			if (!e.isEnd()) {
				next.apply(e);
			}
		};
	}

	default TradeConsumer<E> wrapException( BiConsumer<Throwable, E> exceptionWrapper) {
		Objects.requireNonNull(exceptionWrapper);
		return (E e) -> {
			try {
				apply(e);
			} catch (Throwable exp) {
				if (exp instanceof ExceptionHandleException) {
					throw exp;
				}
				try {
					exceptionWrapper.accept(exp, e);
				} catch (Throwable ex) {
					throw new ExceptionHandleException(ex.getMessage(), ex.getCause());
				}
			}
		};
	}

	/**
	 * 异常处理异常
	 */
	class ExceptionHandleException extends RuntimeException {
		public ExceptionHandleException(String message, Throwable cause) {
			super(message, cause);
		}
	}

	default TradeConsumer<E> wrapLock(BusinessLocker<E> locker, Consumer<E> unlockHandler) {
		Objects.requireNonNull(locker);
		Objects.requireNonNull(unlockHandler);
		return (E e) -> {
			boolean locked = locker.lock(e);  // 分离赋值
			try {
				if (locked) {
					apply(e);
				} else {
					unlockHandler.accept(e);
				}
			} finally {
				if (locked) {
					locker.unlock(e);
				}
			}
		};
	}

	default TradeConsumer<E> wrapLogPrint(String msg) {
		return (E e) -> {
			LoggerFactory.getLogger(e.getClass()).info(msg);  // 使用实际类
			apply(e);
		};
	}

	default TradeConsumer<E> when(Predicate<E> predicate) {
		Objects.requireNonNull(predicate);
		return (E e) -> {
			if (predicate.test(e)) {
				this.apply(e);
			}
		};
	}

	default <T> T applyAndConvert(E e, Function<E, T> converter) {
		Objects.requireNonNull(converter);
		this.apply(e);
		return converter.apply(e);
	}
}
