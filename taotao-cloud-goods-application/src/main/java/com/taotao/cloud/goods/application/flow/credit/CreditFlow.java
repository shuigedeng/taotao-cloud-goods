package com.taotao.cloud.goods.application.flow.credit;

import com.taotao.boot.common.support.pipeline.pipeline.PipelineBuilder;
import com.taotao.cloud.goods.application.flow.credit.nodes.Test1PipelineNode;
import com.taotao.cloud.goods.application.flow.credit.nodes.TestPipelineNode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
//@AllArgsConstructor
public class CreditFlow {

	static void main() {
		CreditFlow flow = new CreditFlow();
		flow.start();
	}

//	private final Test1PipelineNode  test1PipelineNode;
//	private final TestPipelineNode  testPipelineNode;
	public void start() {
		CreditContext creditContext = new CreditContext();
		CreditContext resultCreditContext = new PipelineBuilder<CreditContext>()
			.name("xxx")
			.add(new Test1PipelineNode())
			.add(new TestPipelineNode())
			.build()
			.execute(creditContext)
			.getData();
	}
}
