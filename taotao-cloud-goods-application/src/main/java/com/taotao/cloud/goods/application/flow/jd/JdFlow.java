package com.taotao.cloud.goods.application.flow.jd;

import com.jd.easyflow.flow.engine.FlowContext;
import com.jd.easyflow.flow.engine.FlowEngine;
import com.jd.easyflow.flow.engine.FlowParam;
import com.jd.easyflow.flow.engine.FlowResult;
import com.jd.easyflow.flow.engine.builder.FlowParamBuilder;
import com.jd.easyflow.flow.engine.impl.FlowContextImpl;
import com.jd.easyflow.flow.engine.impl.FlowEngineImpl;
import com.jd.easyflow.flow.model.Flow;
import com.jd.easyflow.flow.model.FlowNode;
import com.jd.easyflow.flow.model.NodeContextAccessor;
import com.jd.easyflow.flow.model.action.ExecutorNodeAction;
import com.jd.easyflow.flow.model.action.FlowNodeAction;
import com.jd.easyflow.flow.model.builder.FlowBuilder;
import com.taotao.cloud.goods.application.support.context.GoodsContext;

public class JdFlow {
	static void main() {
		Flow flow = FlowBuilder.create("test", "test")
//			.setFlowPostHandler(null)
//			.setFlowPreHandler(null)
			.addNode("node1", new ExecutorNodeAction((nc, c) -> {
				GoodsContext param = c.getParam().getParam();
				System.out.println("asdfasdf");
				NodeContextAccessor.setNextNodeIds(nc, new String[]{"node2"});
				return nc;
			}))
			.addNode("node2", new ExecutorNodeAction((nc, c) -> {
				GoodsContext param = c.getParam().getParam();
				c.getResult().addResult(param);
				System.out.println("ttttttt");
				return nc;
			}))
			.buildAndInit();

		FlowParam flowParam = FlowParamBuilder.create("test", "node1")
			.paramObject(new GoodsContext())
			.build();

		FlowEngineImpl flowEngine = new FlowEngineImpl();
		flowEngine.addFlow(flow);
		flowEngine.init();

		FlowResult execute = flowEngine.execute(flowParam);
		System.out.println("----");
	}
}
