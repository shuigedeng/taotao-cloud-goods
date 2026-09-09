package com.taotao.cloud.goods.application.flow.flow.spring;

import com.taotao.cloud.goods.application.flow.flow.FlowCenter;
import com.taotao.cloud.goods.application.flow.flow.FlowEventRecordInfoInterface;
import com.taotao.cloud.goods.application.flow.flow.FlowRegister;
import com.taotao.cloud.goods.application.flow.flow.ManualHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingQueue;

@Configuration
@ConditionalOnProperty(prefix = PluginUtil.PLUGIN_FLOW, name = "enabled", havingValue = "true")
@ConditionalOnClass(FlowCenter.class)
@ComponentScan("com.fmFinance.creditLoan.starterFlow")
public class BusinessFlowAutoConfiguration {

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
