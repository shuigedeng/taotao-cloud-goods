package com.taotao.cloud.goods.application.flow.flow.spring;

import com.taotao.boot.common.utils.lang.StringUtils;
import com.taotao.cloud.goods.application.flow.flow.*;
import com.taotao.cloud.goods.application.flow.flow.ann.CallbackFlowCenterInst;
import com.taotao.cloud.goods.application.flow.flow.ann.FlowNodeHandlerAsync;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class BusinessFlowCallbackBeanPostProcessor implements BeanPostProcessor, ApplicationContextAware {
    private static Map<Class, FlowTraceExtract> flowTraceExtractMap = new ConcurrentHashMap<>();
    private ApplicationContext applicationContext;
    SpringFlowCenter flowCenter;

    @Override
    public void setApplicationContext( ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        this.flowCenter = applicationContext.getBean(SpringFlowCenter.class);
        Map<String, CallbackConsumerFlowCenter> gatewayResCallbackConsumerMap = this.applicationContext.getBeansOfType(CallbackConsumerFlowCenter.class);
        if (gatewayResCallbackConsumerMap.size() > 0) {
            gatewayResCallbackConsumerMap.forEach((k, v) -> {
                CallbackFlowCenterInst gatewayCallbackInst = v.getClass().getNameAnnotation(CallbackFlowCenterInst.class);
                if (null != gatewayCallbackInst) {
                    String[] businessTypeS = gatewayCallbackInst.tradeType();
                    for (String businessType : businessTypeS) {
                        String[] tradeSubTypeS = gatewayCallbackInst.tradeSubType();
                        for (String tradeSubType : tradeSubTypeS) {
                            CallBackConsumerInfo callbackConsumerInfo = new CallBackConsumerInfo();
                            callbackConsumerInfo.setInvokeOpName(gatewayCallbackInst.invokeOpName());
                            callbackConsumerInfo.setTradeType(businessType);
                            callbackConsumerInfo.setTradeSubType(tradeSubType);
                            callbackConsumerInfo.setEvent(gatewayCallbackInst.event());
                            installCallbackFlowCenter(callbackConsumerInfo, v);
                        }
                    }
                }
            });

			FlowNodeHandlerAsync flowNodeHandlerAsync = v.getClass().getAnnotation(FlowNodeHandlerAsync.class);
			if (null != flowNodeHandlerAsync) {
				CallBackConsumerInfo callbackConsumerInfo = new CallBackConsumerInfo();
				callbackConsumerInfo.setInvokeOpName(flowNodeHandlerAsync.flowInfo().channel());
				callbackConsumerInfo.setTradeType(flowNodeHandlerAsync.flowRegisterInfo().businessType());
				callbackConsumerInfo.setTradeSubType(flowNodeHandlerAsync.flowRegisterInfo().businessSubType());
				callbackConsumerInfo.setEvent(flowNodeHandlerAsync.flowInfo().name());
				installCallbackFlowCenter(callbackConsumerInfo, v);
			}

			Map<String, FlowTraceExtract> flowTraceExtractMap = this.applicationContext.getBeansOfType(FlowTraceExtract.class);
			if (flowTraceExtractMap.size() > 0) {
				flowTraceExtractMap.forEach((k, v) -> {
					Method[] declaredMethods = v.getClass().getDeclaredMethods();
					for (Method declaredMethod : declaredMethods) {
						String name = declaredMethod.getName();
						if (name.equals("extract")) {
							Class<?>[] parameterTypes = declaredMethod.getParameterTypes();
							if (1 == parameterTypes.length) {
								installFlowTraceIdExtract(GatewayInvokerRes.class, new FlowTraceExtract() {
									@Override
									public String extract(Object obj) {
										if (obj instanceof GatewayInvokerRes) {
											return ((GatewayInvokerRes<?>) obj).getFlowTraceId();
										}
										return null;
									}

									@Override
									public String splitNo(Object obj) {
										if (obj instanceof GatewayInvokerRes) {
											return ((GatewayInvokerRes<?>) obj).getSplitNo();
										}
										return null;
									}
								});
							}
						}
					}
				});
			}

        }


    }
	private static void installFlowTraceIdExtract(Class aClass, FlowTraceExtract flowTraceExtract) {
		flowTraceExtractMap.put(aClass, flowTraceExtract);
	}

	private synchronized void installCallbackFlowCenter(CallBackConsumerInfo info,
		CallbackConsumerFlowCenter consumer) {
		this.initCallbackDispatcher();

		CallbackConsumer existing = BusinessFlowCallbackDispatcher.getConsumer(info);
		if (null == existing) {
			EventRouteCallbackConsumer routeConsumer = new EventRouteCallbackConsumer();
			String event = info.getEvent();
			if (StringUtils.isEmpty(event)) {
				routeConsumer.setEmptyEventCallbackConsumer(
					new EmptyEventCallbackConsumer(info, consumer));
			} else {
				routeConsumer.getEventRouter().put(event,
					new EventCallbackConsumer(info, consumer));
			}
			this.installCallbackConsumer(info, routeConsumer);
		} else {
			if (existing instanceof EventRouteCallbackConsumer) {
				EventRouteCallbackConsumer routeConsumer = (EventRouteCallbackConsumer) existing;
				String event = info.getEvent();
				if (StringUtils.isEmpty(event)) {
					routeConsumer.setEmptyEventCallbackConsumer(
						new EmptyEventCallbackConsumer(info, consumer));
				} else {
					routeConsumer.getEventRouter().put(event,
						new EventCallbackConsumer(info, consumer));
				}
			} else {
				log.error("consumer instance error, consumer: {}", existing);
				throw new RuntimeException("consumer instance error");
			}
		}
	}

	private class EmptyEventCallbackConsumer implements CallbackConsumer<Object> {

		private CallBackConsumerInfo callbackConsumerInfo;
		private CallbackConsumerFlowCenter gatewayResCallbackConsumer;

		public EmptyEventCallbackConsumer(CallBackConsumerInfo callbackConsumerInfo,
			CallbackConsumerFlowCenter gatewayResCallbackConsumer) {
			this.callbackConsumerInfo = callbackConsumerInfo;
			this.gatewayResCallbackConsumer = gatewayResCallbackConsumer;
		}

		@Override
		public void consumer(Object obj) {
			// 空事件时直接消费
			gatewayResCallbackConsumer.consumer(obj);
			log.info("EmptyEventCallbackConsumer consumed: {}", callbackConsumerInfo);
		}
		@Override
		public void consumer(Object obj) {
			if (!(obj instanceof EventCallbackReq)) {
				log.warn("EmptyEventCallbackConsumer.consumer, obj is not EventCallbackReq: {}", obj);
				return;
			}

			EventCallbackReq eventCallbackReq = (EventCallbackReq) obj;
			Object callbackReq = eventCallbackReq.getCallbackReq();
			FlowContext flowContext = eventCallbackReq.getFlowContext();
			FlowEventRecordInfo flowEventRecordInfo = flowContext.getFlowEventRecordInfo();

			if (!callbackMatch(flowEventRecordInfo, callbackConsumerInfo)) {
				log.warn("EmptyEventCallbackConsumer.consumer, flowEventRecordInfo not match, flow: {}, callbackConsumer: {}",
					flowEventRecordInfo, callbackConsumerInfo);
				return;
			}

			log.info("EmptyEventCallbackConsumer.consumer, 下游异步回调, 回调参数: {}", callbackReq);

			CallbackFlowCenterRes callbackFlowCenterRes = new CallbackFlowCenterRes();
			callbackFlowCenterRes.setFlowContext(eventCallbackReq.getFlowContext());
			callbackFlowCenterRes.setRes(eventCallbackReq.getCallbackReq());

			gatewayResCallbackConsumer.consumer(callbackFlowCenterRes);
			if (!callbackFlowCenterRes.isFlowBreakMark()) {
				flowCenter.asyncResponse(eventCallbackReq.getFlowContext());
			}
		}

	}
	/**
	 * eventCallbackConsumer
	 */
	private class EventCallbackConsumer implements CallbackConsumer<Object> {
		/**
		 * callbackConsumerInfo
		 */
		private CallBackConsumerInfo callbackConsumerInfo;
		/**
		 * gatewayResCallbackConsumer
		 */
		private CallbackConsumerFlowCenter gatewayResCallbackConsumer;

		/**
		 * @param callbackConsumerInfo
		 * @param gatewayResCallbackConsumer
		 */
		public EventCallbackConsumer(CallBackConsumerInfo callbackConsumerInfo, CallbackConsumerFlowCenter gatewayResCallbackConsumer) {
			this.callbackConsumerInfo = callbackConsumerInfo;
			this.gatewayResCallbackConsumer = gatewayResCallbackConsumer;
		}

		@Override
		public void consumer(Object o) {
			if (!(o instanceof EventCallbackReq)) {
				log.warn("EventCallbackConsumer.consumer, obj is not EventCallbackReq: {}", o);
				return;
			}

			EventCallbackReq eventCallbackReq = (EventCallbackReq) o;

			// 添加事件匹配检查
			String expectedEvent = callbackConsumerInfo.getEvent();
			String actualEvent = eventCallbackReq.getEvent();
			if (!expectedEvent.equals(actualEvent)) {
				log.warn("EventCallbackConsumer.consumer, event not match, expected: {}, actual: {}",
					expectedEvent, actualEvent);
				return;
			}

			Object callbackReq = eventCallbackReq.getCallbackReq();
			FlowContext flowContext = eventCallbackReq.getFlowContext();
			FlowEventRecordInfo flowEventRecordInfo = flowContext.getFlowEventRecordInfo();

			if (!callBackMatch(flowEventRecordInfo, callbackConsumerInfo)) {
				log.warn("EventCallbackConsumer.consumer, flowEventRecordInfo not match, flow: {}, callbackConsumer: {}",
					flowEventRecordInfo, callbackConsumerInfo);
				return;
			}

			log.info("EventCallbackConsumer.consumer，下游异步回调，回调参数：{}", callbackReq);

			CallbackFlowCenterRes callbackFlowCenterRes = new CallbackFlowCenterRes();
			callbackFlowCenterRes.setFlowContext(eventCallbackReq.getFlowContext());
			callbackFlowCenterRes.setRes(eventCallbackReq.getCallbackReq());

			gatewayResCallbackConsumer.consumer(callbackFlowCenterRes);
			if (!callbackFlowCenterRes.isFlowBreakMark()) {
				flowCenter.asyncResponse(eventCallbackReq.getFlowContext());
			}
		}
	}
	/**
	 * EventCallbackReq
	 */
	@Data
	private static class EventCallbackReq<T> {
		private FlowContext flowContext;
		private T callbackReq;
	}
	/**
	 * EventRouteCallbackConsumer
	 */
	@Data
	private class EventRouteCallbackConsumer implements CallbackConsumer<Object> {
		/**
		 * eventRouter
		 */
		private Map<String, CallbackConsumer<Object>> eventRouter = new ConcurrentHashMap<>();
		/**
		 * emptyEventCallbackConsumer
		 */
		private CallbackConsumer<Object> emptyEventCallbackConsumer;

		@Override
		public void consumer(Object o) {
			if (null == o) {
				log.warn("EventRouteCallbackConsumer.consumer, obj is null");
				return;
			}

			FlowTraceExtract extractor = flowTraceExtractMap.get(o.getClass());
			if (null == extractor) {
				log.warn("EventRouteCallbackConsumer.consumer, no FlowTraceExtract found for class: {}", o.getClass());
				return;
			}

			String flowTraceId = extractor.extract(o);
			if (StringUtils.isEmpty(flowTraceId)) {
				log.warn("EventRouteCallbackConsumer.consumer, flowTraceId is empty");
				return;
			}

			String splitNo = extractor.splitNo(o);
			FlowEventRecordInfo recordInfo = flowCenter.getFlowEventRecordInfo(flowTraceId, splitNo);
			if (null == recordInfo) {
				log.warn("EventRouteCallbackConsumer.consumer, flowEventRecordInfo is null, flowTraceId: {}", flowTraceId);
				return;
			}

			String event = recordInfo.getCurrentFlow().getName();
			CallbackConsumer<Object> consumer = eventRouter.get(event);

			EventCallbackReq req = new EventCallbackReq();
			req.flowContext = new FlowContext<>(recordInfo);
			req.callbackReq = o;

			if (null == consumer) {
				if (null != emptyEventCallbackConsumer) {
					emptyEventCallbackConsumer.consumer(req);
				} else {
					log.warn("EventRouteCallbackConsumer.consumer, no consumer found for event: {}", event);
				}
			} else {
				consumer.consumer(req);
			}
		}



		/**
		 * 检查流程事件是否匹配消费者信息中的事件
		 *
		 * @param flowEventRecordInfo 流程事件记录
		 * @param callBackConsumerInfo 回调消费者信息
		 * @return true 匹配，false 不匹配
		 */
		private boolean eventMatch(FlowEventRecordInfo flowEventRecordInfo, CallBackConsumerInfo callBackConsumerInfo) {
			if (null == flowEventRecordInfo || null == callBackConsumerInfo) {
				return false;
			}
			String event = callBackConsumerInfo.getEvent();
			if (StringUtils.isEmpty(event)) {
				return true;
			}
			FlowInfo currentFlow = flowEventRecordInfo.getCurrentFlow();
			if (null == currentFlow) {
				return false;
			}
			return event.equals(currentFlow.getName());
		}

		/**
		 * @param flowEventRecordInfo 流程事件记录
		 * @param callbackConsumerInfo 回调消费者信息
		 * @return true 匹配，false 不匹配
		 */
		private boolean callBackMatch(FlowEventRecordInfo flowEventRecordInfo, CallBackConsumerInfo callbackConsumerInfo) {
			if (null == flowEventRecordInfo || null == callbackConsumerInfo) {
				return false;
			}

			FlowInfo currentFlow = flowEventRecordInfo.getCurrentFlow();
			if (null == currentFlow) {
				return false;
			}

			String businessType = flowEventRecordInfo.getBusinessType();
			String businessSubType = flowEventRecordInfo.getBusinessSubType();

			// 校验 invokeOpName
			if (!callbackConsumerInfo.getInvokeOpName().equals(currentFlow.getChannel())) {
				return false;
			}

			// 校验 tradeType
			if (!businessType.equals(callbackConsumerInfo.getTradeType())) {
				return false;
			}

			// 校验 tradeSubType（支持 COMMON 通配）
			String expectedSubType = callbackConsumerInfo.getTradeSubType();
			if (!businessSubType.equals(expectedSubType)
				&& !BusinessFlowCallbackDispatcher.COMMON_BUSINESS_SUB_TYPE.equals(expectedSubType)) {
				return false;
			}

			return true;
		}

		/**
		 * @param callbackConsumerInfo 回调消费者信息
		 * @param callbackConsumer 回调消费者
		 */
		private void installCallbackConsumer(CallBackConsumerInfo callbackConsumerInfo, CallbackConsumer callbackConsumer) {
			this.initCallbackDispatcher();
			BusinessFlowCallbackDispatcher.registerConsumer(callbackConsumerInfo, callbackConsumer);
		}

		/**
		 * initCallbackDispatcher
		 */

	}

	private synchronized void initCallbackDispatcher() {
		if (BusinessFlowCallbackDispatcher.isInitialized()) {
			return;  // 已初始化，跳过
		}

		ThreadPoolExecutor bean = null;
		String property = applicationContext.getEnvironment()
			.getProperty(PluginUtil.PLUGIN_FLOW_CALLBACK_DISPATCHER_THREAD);

		if (StringUtils.hasText(property)) {
			try {
				bean = applicationContext.getBean(property, ThreadPoolExecutor.class);
			} catch (BeansException e) {
				log.warn("Failed to get ThreadPoolExecutor bean: {}, use default", property, e);
			}
		}

		BusinessFlowCallbackDispatcher.init(bean);
	}
	@Nullable
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		Method[] declaredMethods = bean.getClass().getDeclaredMethods();
		for (Method declaredMethod : declaredMethods) {
			CallbackFlowCenterInst annotation = declaredMethod.getAnnotation(CallbackFlowCenterInst.class);
			if (null != annotation) {
				CallbackConsumerFlowCenter callbackConsumer = res -> {
					try {
						declaredMethod.invoke(bean, res);
					} catch (Exception e) {
						throw new PlugException("Callback method invocation failed: " + declaredMethod.getName(), e);
					}
				};

				for (String businessType : annotation.tradeType()) {
					for (String tradeSubType : annotation.tradeSubType()) {
						CallBackConsumerInfo info = new CallBackConsumerInfo();
						info.setInvokeOpName(annotation.invokeOpName());
						info.setTradeType(businessType);
						info.setTradeSubType(tradeSubType);
						info.setEvent(annotation.event());
						installCallbackFlowCenter(info, callbackConsumer);
					}
				}
				FlowNodeHandlerAsync asyncAnnotation = declaredMethod.getAnnotation(FlowNodeHandlerAsync.class);
				if (null != asyncAnnotation) {
					CallBackConsumerInfo info = new CallBackConsumerInfo();
					info.setInvokeOpName(asyncAnnotation.flowInfo().channel());
					info.setTradeType(asyncAnnotation.flowRegisterInfo().businessType());
					info.setTradeSubType(asyncAnnotation.flowRegisterInfo().businessSubType());
					info.setEvent(asyncAnnotation.flowInfo().name());

					installCallbackFlowCenter(info, (CallbackFlowCenterRes res) -> {
						try {
							declaredMethod.invoke(bean, res);
						} catch (Exception e) {
							throw new PlugException("FlowNodeHandlerAsync callback failed: " + declaredMethod.getName(), e);
						}
					});
				}
				return bean;

			}
		}
		return bean;
	}

}
