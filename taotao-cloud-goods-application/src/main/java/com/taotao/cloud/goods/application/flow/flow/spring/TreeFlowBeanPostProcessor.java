package com.taotao.cloud.goods.application.flow.flow.spring;

import com.taotao.cloud.goods.application.flow.flow.*;
import com.taotao.cloud.goods.application.flow.flow.ann.FlowBusinessHandlerInst;
import com.taotao.cloud.goods.application.flow.flow.ann.FlowHandlerInst;
import com.taotao.cloud.goods.application.flow.flow.ann.FlowNodeHandler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
public class TreeFlowBeanPostProcessor implements BeanPostProcessor, ApplicationContextAware {
    /**
     * ApplicationContext
     */
    private ApplicationContext applicationContext;
    /**
     * springFlowHandler
     */
    private SpringFlowHandler springFlowHandler;
    /**
     * springFlowCenter
     */
    private SpringFlowCenter springFlowCenter;

	@Override
	public void setApplicationContext( ApplicationContext applicationContext) throws BeansException {

		this.applicationContext = applicationContext;
		this.springFlowerHandler = applicationContext.getBean(SpringFlowerHandler.class);
		this.springFlowerCenter = applicationContext.getBean(SpringFlowerCenter.class);

		Map<String, FlowHandler.Handler> flowHandlerMap = this.applicationContext.getBeansOfType(FlowHandler.Handler.class);
		if (flowHandlerMap.size() > 0) {
			flowHandlerMap.forEach((k, v) -> {
				FlowHandlerInst annotation = v.getClass().getAnnotation(FlowHandlerInst.class);
				if (null != annotation) {
					String[] flowEvents = annotation.flowEvent();
					List<FlowHandlerInfo> flowHandlerInfoList = new ArrayList<>(flowEvents.length);
					for (String flowEvent : flowEvents) {
						String[] businessTypeS = annotation.businessType();
						for (String businessType : businessTypeS) {
							FlowHandlerInfo flowHandlerInfo = new FlowHandlerInfo();
							flowHandlerInfo.setBusinessType(businessType);
							flowHandlerInfo.setBusinessSubType(annotation.businessSubType());
							flowHandlerInfo.setBusinessCode(annotation.businessCode());
							flowHandlerInfo.setFlowEvent(flowEvent);
							flowHandlerInfoList.add(flowHandlerInfo);
						}
					}
					this.installFlowHandler(annotation.channel(), flowHandlerInfoList, v);
					FlowNodeHandler flowNodeHandler = v.getClass().getAnnotation(FlowNodeHandler.class);

					if (null != flowNodeHandler) {
						FlowRegisterInfoAnnotation flowRegisterInfoAnnotation = flowNodeHandler.flowRegisterInfo();
						String businessType = flowRegisterInfoAnnotation.businessType();
						String businessSubType = flowRegisterInfoAnnotation.businessSubType();
						String businessCode = flowRegisterInfoAnnotation.businessCode();
						FlowInfoAnnotation flowInfoAnnotation = flowNodeHandler.flowInfo();
						String name = flowInfoAnnotation.name();
						String channel = flowInfoAnnotation.channel();
						List<FlowHandlerInfo> singleFlowHandlerInfo = new ArrayList<>();
						FlowHandlerInfo flowHandlerInfo = new FlowHandlerInfo();
						singleFlowHandlerInfo.add(flowHandlerInfo);
						flowHandlerInfo.setBusinessType(businessType);
						flowHandlerInfo.setBusinessSubType(businessSubType);
						flowHandlerInfo.setBusinessCode(businessCode);
						flowHandlerInfo.setFlowEvent(name);
						this.installFlowHandler(channel, singleFlowHandlerInfo, v);
					}

					Map<String, FlowCenter.BusinessHandler> businessHandlerMap = this.applicationContext.getBeansOfType(FlowCenter.BusinessHandler.class);
					if (businessHandlerMap.size() > 0) {
						businessHandlerMap.forEach((k, v) -> this.springFlowCenter.registerBusinessHandlerHandler(v));
					}
				}
			});
		}
	}

	/**
	 * @param flowHandlerInfo
	 * @param flowHandler
	 */
	private void installFlowHandler(String channel, List<FlowHandlerInfo> flowHandlerInfo, FlowHandler.Handler flowHandler) {
		FlowHandlerInfoProxy flowHandlerInfoProxy = new FlowHandlerInfoProxy();
		flowHandlerInfoProxy.setChannel(channel);
		flowHandlerInfoProxy.setFlowHandlerInfoList(flowHandlerInfo);
		springFlowHandler.registerProxy(flowHandlerInfoProxy, flowHandler);
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		Method[] declaredMethods = bean.getClass().getDeclaredMethods();
		List<Method> flowHandlerMethodList = new ArrayList<>();
		List<Method> businessHandlerMethodList = new ArrayList<>();
		List<Method> flowNodeHandlerMethodList = new ArrayList<>();

		for (Method declaredMethod : declaredMethods) {
			if (null != annotation) {
				flowHandlerMethodList.add(declaredMethod);
				FlowBusinessHandlerInst flowBusinessHandlerInst = declaredMethod.getAnnotation(FlowBusinessHandlerInst.class);
				if (null != flowBusinessHandlerInst) {
					businessHandlerMethodList.add(declaredMethod);
					FlowNodeHandler flowNodeHandler = declaredMethod.getAnnotation(FlowNodeHandler.class);
					if (null != flowNodeHandler) {
						flowNodeHandlerMethodList.add(declaredMethod);
					}
				}
			}
		}
		if (flowHandlerMethodList.size() > 0) {
			for (Method method : flowHandlerMethodList) {
				FlowHandlerInst annotation = method.getAnnotation(FlowHandlerInst.class);
				String[] flowEvents = annotation.flowEvent();
				List<FlowHandlerInfo> flowHandlerInfoList = new ArrayList<>(flowEvents.length);
				FlowHandlerAdapter flowHandlerAdapter = new FlowHandlerAdapter(annotation, method, bean);
				for (String flowEvent : flowEvents) {
					String[] businessTypes = annotation.businessType();
					for (String businessType : businessTypes) {
						FlowHandlerInfo flowHandlerInfo = new FlowHandlerInfo();
						flowHandlerInfo.setBusinessType(businessType);
						flowHandlerInfo.setBusinessSubType(annotation.businessSubType());
						flowHandlerInfo.setBusinessCode(annotation.businessCode());
						flowHandlerInfo.setFlowEvent(flowEvent);
						flowHandlerInfoList.add(flowHandlerInfo);
					}
				}
				installFlowHandler(annotation.channel(), flowHandlerInfoList, flowHandlerAdapter);
			}
		}

		if (businessHandlerMethodList.size() > 0) {
			for (Method method : businessHandlerMethodList) {
				FlowBusinessHandlerInst flowBusinessHandlerInst = method.getAnnotation(FlowBusinessHandlerInst.class);
				String[] businessTypes = flowBusinessHandlerInst.businessType();
				for (String businessType : businessTypes) {
					this.springFlowCenter.registerBusinessHandlerHandler(new FlowBusinessHandlerAdapter(flowBusinessHandlerInst, businessType, bean, method));
				}
			}
		}

		if (flowNodeHandlerMethodList.size() > 0) {
			for (Method method : flowNodeHandlerMethodList) {
				FlowNodeHandler flowNodeHandler = method.getAnnotation(FlowNodeHandler.class);
				FlowRegisterInfoAnnotation flowRegisterInfoAnnotation = flowNodeHandler.flowRegisterInfo();
				List<FlowHandlerInfo> flowHandlerInfoList = new ArrayList<>();
				FlowHandlerInfo flowHandlerInfo = new FlowHandlerInfo();
				flowHandlerInfoList.add(flowHandlerInfo);
				flowHandlerInfo.setBusinessType(flowRegisterInfoAnnotation.businessType());
				flowHandlerInfo.setBusinessSubType(flowRegisterInfoAnnotation.businessSubType());
				flowHandlerInfo.setBusinessCode(flowRegisterInfoAnnotation.businessCode());
				FlowInfoAnnotation flowInfoAnnotation = flowNodeHandler.flowInfo();
				flowHandlerInfo.setFlowEvent(flowInfoAnnotation.name());
				installFlowHandler(flowInfoAnnotation.channel(), flowHandlerInfoList, new FlowNodeHandlerAdapter(flowNodeHandler, method, bean));
			}
		}

		return bean;
	}

	private static class FlowHandlerAdapter implements FlowHandler.Handler {
		private Method method;
		private Object obj;
		private FlowHandlerInst annotation;

		@Override
		public String info() {
			return new StringBuilder()
				.append("businessType")
				.append(Arrays.toString(annotation.businessType()))
				.append("businessSubType")
				.append(annotation.businessSubType())
				.append("}")
				.append("businessCode")
				.append(annotation.businessCode())
				.append("}")
				.append("flowEvent")
				.append(Arrays.toString(annotation.flowEvent()))
				.toString();
		}

		public FlowHandlerAdapter(FlowHandlerInst flowHandlerInst, Method declaredMethod, Object bean) {
			this.annotation = flowHandlerInst;
			this.method = declaredMethod;
			this.obj = bean;
		}

		@Override
		public void handle(FlowContext flowContext) {
			try {
				this.method.invoke(obj, flowContext);
			} catch (Exception e) {
				log.error("异常: ", e);
				throw new PlugException("FlowHandlerAdapter.handle", e);
			}
		}
	}

	private static class FlowBusinessHandlerAdapter implements FlowCenter.BusinessHandler {
		private FlowBusinessHandlerInst flowBusinessHandlerInst;
		private String type;
		private Object target;
		private Method method;

		public FlowBusinessHandlerAdapter(FlowBusinessHandlerInst flowBusinessHandlerInst, String type, Object target, Method method) {
			this.flowBusinessHandlerInst = flowBusinessHandlerInst;
			this.type = type;
			this.target = target;
			this.method = method;
		}

		@Override
		public String info() {
			return new StringBuilder().append("businessType")
				.append(Arrays.toString(flowBusinessHandlerInst.businessType()))
				.toString();
		}

		@Override
		public void callBack(FlowContext flowContext) {
			try {
				this.method.invoke(target, flowContext);
			} catch (Exception e) {
				log.error("异常: ", e);
				throw new PlugException("FlowBusinessHandlerAdapter.handle, 反射执行异常", e);
			}
		}

		@Override
		public String businessName() {
			return this.type;
		}
	}

	private static class FlowNodeHandlerAdapter implements FlowHandler.Handler {
		private Method method;
		private Object obj;
		private FlowNodeHandler annotation;

		@Override
		public String info() {
			return new StringBuilder()
				.append("businessType")
				.append(annotation.flowRegisterInfo().businessType())
				.append("businessSubType[")
				.append(annotation.flowRegisterInfo().businessSubType())
				.append("]")
				.append("businessCode[")
				.append(annotation.flowRegisterInfo().businessCode())
				.append("]")
				.append("flowEvent")
				.append(annotation.flowInfo().name())
				.toString();
		}

		public FlowNodeHandlerAdapter(FlowNodeHandler flowNodeHandler, Method declaredMethod, Object bean) {
			this.annotation = flowNodeHandler;
			this.method = declaredMethod;
			this.obj = bean;
		}

		@Override
		public void handle(FlowContext flowContext) {
			try {
				this.method.invoke(obj, flowContext);
			} catch (Exception e) {
				log.error("异常: ", e);
				throw new PlugException("FlowHandlerAdapter.handle", e);
			}
		}
	}
}
