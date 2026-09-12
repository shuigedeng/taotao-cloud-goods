package com.taotao.cloud.goods.application.flow.flow.spring;

import com.taotao.cloud.goods.application.flow.flow.spring.processor.BusinessCallbackBeanPostProcessor;
import com.taotao.cloud.goods.application.flow.flow.spring.processor.BusinessFlowCallbackBeanPostProcessor;
import com.taotao.cloud.goods.application.flow.flow.spring.processor.TreeFlowBeanPostProcessor;
import com.taotao.cloud.goods.application.flow.flow.spring.service.BusinessFlowCallbackDispatcher;
import com.taotao.cloud.goods.application.flow.flow.spring.service.SpringFlowCenter;
import com.taotao.cloud.goods.application.flow.flow.spring.service.SpringFlowHandler;
import com.taotao.cloud.goods.application.flow.flow.spring.service.SpringManualHandler;
import com.taotao.cloud.goods.application.flow.flow.treeflow.FlowCenter;
import com.taotao.cloud.goods.application.flow.flow.treeflow.service.DefaultFlowEventRecordInfoInterface;
import com.taotao.cloud.goods.application.flow.flow.treeflow.service.FlowEventRecordInfoInterface;
import com.taotao.cloud.goods.application.flow.flow.treeflow.FlowRegister;
import com.taotao.cloud.goods.application.flow.flow.treeflow.channel.ManualHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
//@ConditionalOnProperty(prefix = PluginUtil.PLUGIN_FLOW, name = "enabled", havingValue = "true")
@ConditionalOnClass(FlowCenter.class)
@ComponentScan("com.fmFinance.creditLoan.starterFlow")
public class BusinessFlowAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean
	public FlowEventRecordInfoInterface flowEventRecordInfoInterface(){
		return new DefaultFlowEventRecordInfoInterface();
	}
    @Bean
    public FlowRegister flowRegister( FlowEventRecordInfoInterface flowEventRecordInfoInterface) {
        return new FlowRegister(flowEventRecordInfoInterface);
    }

    @Bean
    public ThreadPoolExecutor threadPoolExecutor() {
        return new ThreadPoolExecutor(
            10, 20, 60, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(100),
            new ThreadPoolExecutor.CallerRunsPolicy()
        );
    }

    @Bean
    public SpringFlowCenter springFlowCenter(FlowRegister register,
											 FlowEventRecordInfoInterface flowEventRecordInfoInterface,
											 ThreadPoolExecutor threadPoolExecutor) {
        return new SpringFlowCenter(register, flowEventRecordInfoInterface, threadPoolExecutor);
    }

    @Bean
    public ManualHandler manualHandler(FlowCenter flowCenter) {
        return new SpringManualHandler(flowCenter);
    }

    @Bean
    public SpringFlowHandler springFlowHandler(FlowCenter flowCenter) {
        return new SpringFlowHandler(flowCenter);
    }

    @Bean
    public BusinessFlowCallbackDispatcher businessFlowCallbackDispatcher() {
        return new BusinessFlowCallbackDispatcher();
    }

	@Bean
	public BusinessFlowCallbackBeanPostProcessor businessFlowCallbackBeanPostProcessor() {
		return new BusinessFlowCallbackBeanPostProcessor();
	}

	@Bean
	public BusinessCallbackBeanPostProcessor businessCallbackBeanPostProcessor() {
		return new BusinessCallbackBeanPostProcessor();
	}

	/**
	 * @return
	 */
	@Bean
	public TreeFlowBeanPostProcessor treeFlowBeanPostProcessor() {
		return new TreeFlowBeanPostProcessor();
	}
}
