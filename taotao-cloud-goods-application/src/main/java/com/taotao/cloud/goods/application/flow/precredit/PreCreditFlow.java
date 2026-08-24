package com.taotao.cloud.goods.application.flow.precredit;

import com.taotao.boot.flowengine.flow.FlowDefinition;
import com.taotao.boot.flowengine.flow.FlowDsl;
import com.taotao.cloud.goods.application.dto.goods.result.GoodsResult;
import com.taotao.cloud.goods.application.dto.goods.result.GoodsResultBuilder;
import org.springframework.stereotype.Service;

@Service
public class PreCreditFlow implements FlowDsl<PreCreditContext, GoodsResult> {

	public void start(){
		PreCreditContext preCreditContext = new PreCreditContext();
		preCreditContext.setGoodsResult(GoodsResultBuilder.builder().build());
		GoodsResult result = this.definition().execute(preCreditContext).getResult();
	}
	@Override
	public FlowDefinition<PreCreditContext, GoodsResult> definition() {
		return start("xxxx")
			.step("xxx").invoke(this::test)
			.step("xxx").predicate(ctx -> ctx.isCredit()).invoke(this::test1)
			.step("xxx").end(this::test3)
			.build();

		return start("xxxx")
			.step("xxx").invoke(this::test)
			.step("xxx").predicate(ctx -> ctx.isCredit()).invoke(this::test1)
			.step("xx").conditional()
			.when("xx", ctx-> ctx.isCredit()).step("xx").invoke(this::test).close()
			.otherwise("sdfas").step("xx").invoke(this::test).close()
			.finish()
			.step("xx")
			.end(this::test3)
			.build();
	}

	public void test(PreCreditContext  creditContext) {

	}


	public void test1(PreCreditContext  creditContext) {

	}

	public GoodsResult test3(PreCreditContext  creditContext) {
	return creditContext.getGoodsResult();
	}
}
