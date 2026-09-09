package com.taotao.cloud.goods.application.flow.flow.spring;

import com.taotao.cloud.goods.application.flow.flow.FlowCenter;
import com.taotao.cloud.goods.application.flow.flow.FlowEventRecordInfoInterface;
import com.taotao.cloud.goods.application.flow.flow.FlowRegister;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringFlowCenter extends FlowCenter implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    public SpringFlowCenter( FlowRegister register, FlowEventRecordInfoInterface flowEventRecordInfoInterface,
                            ThreadPoolExecutor threadPoolExecutor) {
        super(register, flowEventRecordInfoInterface, threadPoolExecutor);
    }

    @Override
    public void setApplicationContext( ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        String property = applicationContext.getEnvironment().getProperty(PluginUtil.PLUGIN_FLOW_TREE_FLOW_EXECUTE_THREAD);
        ThreadPoolExecutor threadPoolExecutor = null;
        if (null != property && property.length() > 0) {
            threadPoolExecutor = applicationContext.getBean(ThreadPoolExecutor.class, property);
            super.installExecuteThreadPool(threadPoolExecutor);
        }
    }
}
