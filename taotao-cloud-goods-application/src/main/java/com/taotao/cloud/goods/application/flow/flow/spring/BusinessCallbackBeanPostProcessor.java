package com.taotao.cloud.goods.application.flow.flow.spring;

import com.taotao.cloud.goods.application.flow.flow.CallBackConsumerInfo;
import com.taotao.cloud.goods.application.flow.flow.CallbackConsumer;
import com.taotao.cloud.goods.application.flow.flow.GatewayInvokerRes;
import com.taotao.cloud.goods.application.flow.flow.ann.BusinessCallbackInst;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@Slf4j
public class BusinessCallbackBeanPostProcessor implements BeanPostProcessor, ApplicationContextAware {
    private ApplicationContext applicationContext;
    private volatile boolean initialized = false;

    @Override
    public void setApplicationContext( ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        // 使用 ApplicationListener 或 @PostConstruct 替代在 setApplicationContext 中处理
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof CallbackConsumer) {
            processCallbackConsumer((CallbackConsumer) bean);
        }
        return bean;
    }

    private void processCallbackConsumer(CallbackConsumer<?> consumer) {
        BusinessCallbackInst annotation = consumer.getClass().getAnnotation(BusinessCallbackInst.class);
        if (null != annotation) {
            CallBackConsumerInfo info = new CallBackConsumerInfo();
            info.setTradeType(annotation.tradeType());
            info.setTradeSubType(annotation.tradeSubType());

            synchronized (this) {
                if (!initialized) {
                    initCallbackDispatcher();
                    initialized = true;
                }
            }
            BusinessFlowCallbackDispatcher.registerConsumer(info, consumer);
        }
    }

    private void initCallbackDispatcher() {
        ThreadPoolExecutor bean = null;
        String property = applicationContext.getEnvironment()
            .getProperty(PluginUtil.PLUGIN_FLOW_CALLBACK_DISPATCHER_THREAD);
        if (StringUtils.hasText(property)) {
            bean = applicationContext.getBean(property, ThreadPoolExecutor.class);
        }
        BusinessFlowCallbackDispatcher.init(bean);
    }

	@Nullable
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
