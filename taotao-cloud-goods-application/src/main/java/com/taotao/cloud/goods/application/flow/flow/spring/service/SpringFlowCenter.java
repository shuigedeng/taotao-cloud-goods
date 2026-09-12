package com.taotao.cloud.goods.application.flow.flow.spring.service;

import com.taotao.cloud.goods.application.flow.flow.treeflow.FlowCenter;
import com.taotao.cloud.goods.application.flow.flow.treeflow.service.FlowEventRecordInfoInterface;
import com.taotao.cloud.goods.application.flow.flow.treeflow.FlowRegister;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.concurrent.ThreadPoolExecutor;

public class SpringFlowCenter extends FlowCenter implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    public SpringFlowCenter( FlowRegister register, FlowEventRecordInfoInterface flowEventRecordInfoInterface,
                            ThreadPoolExecutor threadPoolExecutor) {
        super(register, flowEventRecordInfoInterface, threadPoolExecutor);
    }

    @Override
    public void setApplicationContext( ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
//        String property = applicationContext.getEnvironment().getProperty(PluginUtil.PLUGIN_FLOW_TREE_FLOW_EXECUTE_THREAD);
        ThreadPoolExecutor threadPoolExecutor = null;
//        if (null != property && !property.isEmpty()) {
//            threadPoolExecutor = applicationContext.getBean(ThreadPoolExecutor.class, property);
//        }
		super.installExecuteThreadPool(threadPoolExecutor);
	}
}
