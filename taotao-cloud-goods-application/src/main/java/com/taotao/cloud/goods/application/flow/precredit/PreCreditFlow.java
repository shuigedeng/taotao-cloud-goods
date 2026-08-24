package com.taotao.cloud.goods.application.flow.precredit;

import com.taotao.boot.flowengine.flow.FlowDefinition;
import com.taotao.boot.flowengine.flow.FlowDsl;
import com.taotao.cloud.goods.application.dto.goods.result.GoodsResult;
import com.taotao.cloud.goods.application.dto.goods.result.GoodsResultBuilder;
import org.springframework.stereotype.Service;

@Service
public class PreCreditFlow implements FlowDsl<PreCreditContext, GoodsResult> {

	static void main() {
		PreCreditFlow preCreditFlow = new PreCreditFlow();
		preCreditFlow.start();
	}
	public  void start(){
		PreCreditContext preCreditContext = new PreCreditContext();
		preCreditContext.setGoodsResult(GoodsResultBuilder.builder().build());
		GoodsResult result = this.definition().execute(preCreditContext).getResult();
	}
	@Override
	public FlowDefinition<PreCreditContext, GoodsResult> definition() {
//		return start("xxxx")
//			.step("xxx").invoke(this::test)
//			.step("xxx").predicate(ctx -> ctx.isCredit()).invoke(this::test1)
//			.step("xxx").end(this::test3)
//			.build();

		return start("start")
			.step("step1").invoke(this::step1)
			.step("steppredicate").predicate(ctx -> !ctx.isCredit()).invoke(this::steppredicate)
			.step("steppconditional").conditional()
			.when("steppconditional1", ctx-> !ctx.isCredit()).step("steppconditional2").invoke(this::steppconditional2).close()
			.otherwise("steppotherwise").step("steppotherwise1").invoke(this::steppotherwise1).close()
			.finish()
			.step("stepend")
			.end(this::stepend)
			.build();
	}

	public void step1(PreCreditContext  creditContext) {
		System.out.println("step1");
	}


	public void steppredicate(PreCreditContext  creditContext) {
		System.out.println("steppredicate");
	}

	public void steppconditional2(PreCreditContext  creditContext) {
		System.out.println("steppconditional2");
	}

	public void steppotherwise1(PreCreditContext  creditContext) {
		System.out.println("steppotherwise1");
	}

	public GoodsResult stepend(PreCreditContext  creditContext) {
		System.out.println("stepend");
	return creditContext.getGoodsResult();
	}
}
