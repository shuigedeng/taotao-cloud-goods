package com.taotao.cloud.goods.application.flow.credit.nodes;

import com.taotao.boot.common.support.pipeline.pipeline.PipelineContext;
import com.taotao.boot.common.support.pipeline.pipeline.PipelineException;
import com.taotao.boot.common.support.pipeline.pipeline.PipelineNode;
import com.taotao.cloud.goods.application.flow.credit.CreditContext;
import org.springframework.stereotype.Service;

@Service
public class TestPipelineNode implements PipelineNode<CreditContext> {
	@Override
	public void execute(PipelineContext<CreditContext> context) throws PipelineException {
		CreditContext creditContext = context.getData();
		System.out.println("TestPipelineNode");
	}
}
