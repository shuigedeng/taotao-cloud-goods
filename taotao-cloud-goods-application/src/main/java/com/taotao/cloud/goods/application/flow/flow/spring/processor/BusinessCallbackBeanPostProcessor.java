package com.taotao.cloud.goods.application.flow.flow.spring.processor;

import com.taotao.boot.common.utils.lang.StringUtils;
import com.taotao.cloud.goods.application.flow.flow.PlugException;
import com.taotao.cloud.goods.application.flow.flow.callback.CallBackConsumerInfo;
import com.taotao.cloud.goods.application.flow.flow.callback.CallbackConsumer;
import com.taotao.cloud.goods.application.flow.flow.callback.GatewayInvokerRes;
import com.taotao.cloud.goods.application.flow.flow.ann.BusinessCallbackInst;
import com.taotao.cloud.goods.application.flow.flow.spring.service.BusinessFlowCallbackDispatcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
public class BusinessCallbackBeanPostProcessor implements BeanPostProcessor, ApplicationContextAware {
    private ApplicationContext applicationContext;
    private volatile boolean initialized = false;

    @Override
    public void setApplicationContext( ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
		Map<String, CallbackConsumer> callbackConsumerMap = this.applicationContext.getBeansOfType(CallbackConsumer.class);
		if(callbackConsumerMap.size() >  0){
			callbackConsumerMap.forEach((k, v)->{
				BusinessCallbackInst annotation = v.getClass().getAnnotation(BusinessCallbackInst.class);
				if (null != annotation) {
					CallBackConsumerInfo info = new CallBackConsumerInfo();
					info.setTradeType(annotation.tradeType());
					info.setTradeSubType(annotation.tradeSubType());

					BusinessFlowCallbackDispatcher.registerConsumer(info, v);
				}
			});
		}

    }


	private void installCallbackConsumer(CallBackConsumerInfo info,CallbackConsumer<?> consumer) {
		this.initCallbackDispatcher();
		BusinessFlowCallbackDispatcher.registerConsumer(info, consumer);
	}

    private void initCallbackDispatcher() {
        ThreadPoolExecutor bean = null;
//        String property = applicationContext.getEnvironment()
//            .getProperty(PluginUtil.PLUGIN_FLOW_CALLBACK_DISPATCHER_THREAD);
//        if (StringUtils.hasText(property)) {
//            bean = applicationContext.getBean(property, ThreadPoolExecutor.class);
//        }
        BusinessFlowCallbackDispatcher.init(bean);
    }

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		Method[] declaredMethods = bean.getClass().getDeclaredMethods();
		for (Method declaredMethod : declaredMethods) {
			BusinessCallbackInst annotation = declaredMethod.getAnnotation(BusinessCallbackInst.class);
			if (null != annotation) {
				CallbackConsumer<GatewayInvokerRes> callbackConsumer = res -> {
					try {
						declaredMethod.invoke(bean, res);
					} catch (Exception e) {
						throw new PlugException("Callback method invocation failed: " + declaredMethod.getName(), e);
					}
				};
				CallBackConsumerInfo info = new CallBackConsumerInfo();
				info.setTradeType(annotation.tradeType());
				info.setTradeSubType(annotation.tradeSubType());
				// 需要传入 CallbackConsumer 参数
				installCallbackConsumer(info, callbackConsumer);
			}
		}
		return bean;
	}
}
