package com.taotao.cloud.goods.application.flow.usecredit;

import com.taotao.boot.flowengine.easywork.context.WorkContext;
import com.taotao.boot.flowengine.easywork.flow.SequentialFlow;
import com.taotao.boot.flowengine.easywork.predicate.WorkReportPredicate;
import com.taotao.cloud.goods.application.flow.usecredit.works.PrintMessageWork;

import static com.taotao.boot.flowengine.easywork.enignee.WorkFlowEngineImpl.aNewWorkFlowEngine;
import static com.taotao.boot.flowengine.easywork.flow.ConditionalFlow.aNewConditionalFlow;
import static com.taotao.boot.flowengine.easywork.flow.ParallelFlow.aNewParallelFlow;
import static com.taotao.boot.flowengine.easywork.flow.RepeatFlow.aNewRepeatFlow;
import static com.taotao.boot.flowengine.easywork.flow.SequentialFlow.aNewSequentialFlow;

public class UseCreditFlow {

    public  void start() {
        PrintMessageWork work1 = new PrintMessageWork("foo");
        PrintMessageWork work2 = new PrintMessageWork("hello");
        PrintMessageWork work3 = new PrintMessageWork("world");
        PrintMessageWork work4 = new PrintMessageWork("ok");
        PrintMessageWork work5 = new PrintMessageWork("nok");

        WorkContext workContext = new WorkContext();


        SequentialFlow flow = aNewSequentialFlow(
                aNewRepeatFlow(work1).times(3),
                aNewConditionalFlow(aNewParallelFlow(work2,work3)).when(WorkReportPredicate.COMPLETED, work4, work5)
        ).named("sequential");

        aNewWorkFlowEngine().run(flow, workContext);

		System.out.println("asdfasdfsf");

    }
}
