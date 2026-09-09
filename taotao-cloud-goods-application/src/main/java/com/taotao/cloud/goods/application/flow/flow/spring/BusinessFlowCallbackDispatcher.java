package com.taotao.cloud.goods.application.flow.flow.spring;

import com.taotao.cloud.goods.application.flow.flow.CallBackConsumerInfo;
import com.taotao.cloud.goods.application.flow.flow.CallbackConsumer;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.RunnableWrapper;
import org.springframework.context.event.EventListener;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

@Slf4j
public class BusinessFlowCallbackDispatcher {

    private static final String COMMON_BUSINESS_SUB_TYPE = "COMMON";

    private static Map<String, CallbackConsumer> callbackConsumerMap = new ConcurrentHashMap<>();

    private static ThreadPoolExecutor threadPoolExecutor;

    private static AtomicBoolean start = new AtomicBoolean(false);

    private static ThreadPoolExecutor defaultThreadPoolExecutor;

    private static Consumer<BusinessFlowCallbackEvent> lessPeekConsumer = null;

    private static ThreadPoolExecutor lessPeekThreadPoolExecutor;

    private static Map<String, ThreadPoolExecutor> opServerExecMap = new ConcurrentHashMap<>();

    static {
        defaultThreadPoolExecutor = new ThreadPoolExecutor(
            50,
            50,
            3000L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(1024),
            r -> new Thread(r, "下游回调默认线程")
        );

        lessPeekThreadPoolExecutor = new ThreadPoolExecutor(
            50,
            50,
            3000L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(1024),
            r -> new Thread(r, "下游回调削峰线程")
        );
    }

	/**
	 * 安装削峰消费者
	 *
	 * @param consumer 削峰消费者
	 * @throws NullPointerException 如果 consumer 为 null
	 */
	public static void installLessPeekConsumer(Consumer<BusinessFlowCallbackEvent> consumer) {
		Objects.requireNonNull(consumer, "lessPeekConsumer must not be null");
		lessPeekConsumer = consumer;
		log.info("LessPeekConsumer installed: {}", consumer);
	}

	/**
	 * 安装异步执行器
	 *
	 * @param opName 操作名称
	 * @param executor 异步执行器
	 * @throws IllegalArgumentException 如果 opName 为空
	 * @throws IllegalStateException 如果该操作名称已注册
	 */
	public static void installAsyncExe(String opName, ThreadPoolExecutor executor) {
		if (!StringUtils.hasText(opName)) {
			throw new IllegalArgumentException("opName is empty");
		}
		Objects.requireNonNull(executor, "asyncExe must not be null");

		ThreadPoolExecutor existing = opServerExecMap.putIfAbsent(opName, executor);
		if (null != existing) {
			throw new IllegalStateException(String.format("AsyncExe already exists for opName: %s", opName));
		}
		log.info("AsyncExe installed for opName: {}", opName);
	}


	/**
	 * 注册回调消费者
	 *
	 * @param callBackConsumerInfo 回调消费者信息
	 * @param consumer 回调消费者
	 */
	public static void registerConsumer( CallBackConsumerInfo callBackConsumerInfo, CallbackConsumer consumer) {
		if (!start.get()) {
			log.warn("BusinessFlowCallbackDispatcher.registerConsumer, dispatcher not started, consumer: {}", callBackConsumerInfo);
			return;
		}

		// 建议包含 event 字段
		String key = String.join(":",
			callBackConsumerInfo.getInvokeOpName(),
			callBackConsumerInfo.getTradeType(),
			callBackConsumerInfo.getTradeSubType(),
			StringUtils.defaultIfEmpty(callBackConsumerInfo.getEvent(), "default")
		);

		CallbackConsumer existing = callbackConsumerMap.putIfAbsent(key, consumer);
		if (null != existing) {
			log.warn("BusinessFlowCallbackDispatcher.registerConsumer, consumer already exists for key: {}, existing: {}",
				key, existing);
		} else {
			log.info("BusinessFlowCallbackDispatcher.registerConsumer, registered consumer for key: {}", key);
		}
	}

	/**
	 * @return 所有已注册消费者的 Key 列表
	 */
	public static String info() {
		if (callbackConsumerMap.isEmpty()) {
			return "empty";
		}
		return callbackConsumerMap.keySet().toString();
	}

	public static void downCallBack(BusinessFlowCallbackEvent callbackEvent) {
		String businessType = callbackEvent.getBusinessType();
		String businessSubType = callbackEvent.getBusinessSubType();
		String invokeOpName = callbackEvent.getGatewayInvokeOpName();
		final CallbackConsumer[] consumer = {null};
		consumer[0] = callbackConsumerMap.get(String.join(":", invokeOpName, businessType, businessSubType));
		if (null == consumer[0]) {
			consumer[0] = callbackConsumerMap.get(String.join(":", invokeOpName, businessType, COMMON_BUSINESS_SUB_TYPE));
			if (null == consumer[0]) {
				log.error("BusinessFlowCallbackDispatcher.callBack,不支持回调，invokeOpName: {}, businessType: {}, businessSubType: {}",
					invokeOpName,
					businessType,
					businessSubType);
				return;
			}
		}
		try {
			if (!StringUtils.isEmpty(invokeOpName)) {
				ThreadPoolExecutor opNameExec = opServerExecMap.get(invokeOpName);
				if (null != opNameExec) {
					opNameExec.execute(RunnableWrapper.of(() -> {
						try {
							consumer[0].consumer(callbackEvent.getGatewayInvokerRes());
						} catch (Exception e) {
							log.error("异常1: ", e);
						}
					}));
					return;
				}
			}
			if (null == threadPoolExecutor) {
				defaultThreadPoolExecutor.execute(RunnableWrapper.of(() -> {
					try {
						consumer[0].consumer(callbackEvent.getGatewayInvokerRes());
					} catch (Exception e) {
						log.error("异常2: ", e);
					}
				}));
			} else {
				threadPoolExecutor.execute(RunnableWrapper.of(() -> {
					try {
						consumer[0].consumer(callbackEvent.getGatewayInvokerRes());
					} catch (Exception e) {
						log.error("异常3: ", e);
					}
				}));
			}
		}catch (Exception e) {
			log.error("BusinessFlowCallbackDispatcher::callBack,异常: ", e);
			if (e instanceof RejectedExecutionException) {
				// 削峰
				if (null != lessPeekConsumer) {
					log.error("BusinessFlowCallbackDispatcher::callBack,削峰");
					lessPeekThreadPoolExecutor.execute(RunnableWrapper.of(() -> {
						try {
							lessPeekConsumer.accept(callbackEvent);
						} catch (Exception e1) {
							log.error("异常4: ", e1);
						}
					}));
				} else {
					throw e;
				}
			} else {
				throw e;
			}
		}

	}

	private static CallbackConsumer findConsumer(String invokeOpName, String businessType, String businessSubType) {
		// 精确匹配
		String key = String.join(":", invokeOpName, businessType, businessSubType);
		CallbackConsumer consumer = callbackConsumerMap.get(key);
		if (null != consumer) {
			return consumer;
		}
		// 降级匹配
		key = String.join(":", invokeOpName, businessType, COMMON_BUSINESS_SUB_TYPE);
		return callbackConsumerMap.get(key);
	}

	/**
	 * 业务异步回调监听
	 *
	 * @param callbackEvent 回调事件
	 */
	@EventListener(BusinessFlowCallbackEvent.class)
	public void callback(BusinessFlowCallbackEvent callbackEvent) {
		downCallBack(callbackEvent);  // 修正方法名
	}

	/**
	 * 初始化回调分发器
	 *
	 * @param executor 线程池（可为 null）
	 */
	public static synchronized void init(ThreadPoolExecutor executor) {
		if (start.compareAndSet(false, true)) {
			threadPoolExecutor = executor;
			log.info("BusinessFlowCallbackDispatcher initialized with executor: {}", executor);
		}
	}

	/**
	 * 获取回调消费者（支持降级）
	 *
	 * @param info 回调消费者信息
	 * @return 回调消费者，未找到返回 null
	 */
	public static CallbackConsumer getConsumer(CallBackConsumerInfo info) {
		// 使用与 registerConsumer 相同的分隔符
		String key = String.join(":",
			info.getInvokeOpName(),
			info.getTradeType(),
			info.getTradeSubType());
		CallbackConsumer consumer = callbackConsumerMap.get(key);
		if (null != consumer) {
			return consumer;
		}
		// 降级匹配
		key = String.join(":",
			info.getInvokeOpName(),
			info.getTradeType(),
			COMMON_BUSINESS_SUB_TYPE);
		return callbackConsumerMap.get(key);
	}
}
