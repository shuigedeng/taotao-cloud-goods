package com.taotao.cloud.goods.application.flow.flow;

import com.taotao.cloud.goods.application.flow.flow.spring.service.SpringManualHandler;
import com.taotao.cloud.goods.application.flow.flow.treeflow.FlowCenter;
import com.taotao.cloud.goods.application.flow.flow.treeflow.FlowHandler;
import com.taotao.cloud.goods.application.flow.flow.treeflow.FlowRegister;
import com.taotao.cloud.goods.application.flow.flow.treeflow.context.FlowContext;
import com.taotao.cloud.goods.application.flow.flow.treeflow.info.*;
import com.taotao.cloud.goods.application.flow.flow.treeflow.service.FlowEventRecordInfoInterface;

import java.util.List;
import java.util.function.Supplier;

public class FlowTest {
	static void main() {

		FlowEventRecordInfoInterface  flowEventRecordInfoInterface = new FlowEventRecordInfoInterfaceImpl();
		FlowRegister flowRegister = new FlowRegister(flowEventRecordInfoInterface);

		flowRegister.register(FlowRegisterInfo.registerInfo("1","2","3"),
			flowRegister.flowTreeRoot(FlowInfo.node("4","manual"))
				.success().successFlowTree(FlowInfo.node("5","manual"))
				.build().get());

		FlowCenter flowCenter = new FlowCenter(flowRegister, flowEventRecordInfoInterface,null);
		SpringManualHandler springManualHandler = new SpringManualHandler(flowCenter);
		FlowHandlerInfo flowHandlerInfo = new FlowHandlerInfo();
		flowHandlerInfo.setBusinessType("1");
		flowHandlerInfo.setBusinessSubType("2");
		flowHandlerInfo.setBusinessCode("3");
		flowHandlerInfo.setFlowEvent("4");
		springManualHandler.register(flowHandlerInfo, new FlowHandler.Handler() {
			@Override
			public void handle(FlowContext flowContext) {
				System.out.println("44444");
//				flowContext.getFlowEventRecordInfo().setStatus("S");
			}

			@Override
			public String info() {
				return "";
			}
		});

		FlowHandlerInfo flowHandlerInfo1 = new FlowHandlerInfo();
		flowHandlerInfo1.setBusinessType("1");
		flowHandlerInfo1.setBusinessSubType("2");
		flowHandlerInfo1.setBusinessCode("3");
		flowHandlerInfo1.setFlowEvent("5");
		springManualHandler.register(flowHandlerInfo1, new FlowHandler.Handler() {
			@Override
			public void handle(FlowContext flowContext) {
				System.out.println("555555");
				flowContext.getFlowEventRecordInfo().setStatus("S");
			}

			@Override
			public String info() {
				return "";
			}
		});

//		flowCenter.registerFlowHandler(springManualHandler);

		FlowEventRecordInfo flowEventRecordInfo = new FlowEventRecordInfo();
		flowEventRecordInfo.setBusinessType("1");
		flowEventRecordInfo.setBusinessSubType("2");
		flowEventRecordInfo.setBusinessCode("3");
		FlowInfo flowInfo = new FlowInfo("4","manual");
		flowEventRecordInfo.setCurrentFlow(flowInfo);
		FlowContext flowContext = new FlowContext(flowEventRecordInfo);
		flowCenter.flow(flowContext, ()->true);
		System.out.println("asdfas");
	}

	public static class FlowEventRecordInfoInterfaceImpl implements FlowEventRecordInfoInterface {

		@Override
		public List<FlowEventRecordInfo> getByParentId(String parentId, String splitNo) {
			return List.of();
		}

		@Override
		public String nextRecordId() {
			return "";
		}

		@Override
		public FlowEventRecordInfo getEventByRecordId(String recordId, String splitNo) {
			return null;
		}

		@Override
		public boolean flowComplete(FlowEventRecordInfo flowEventRecordInfo) {
			return true;
		}

		@Override
		public boolean flow(FlowEventRecordInfo currentFlowEventRecordInfo, List<FlowEventRecordInfo> nextFlowEventRecordInfoL) {
			return true;
		}

		@Override
		public boolean failRetry(FlowEventRecordInfo failFlowEventRecordInfo, FlowEventRecordInfo retryFlowEventRecordInfo) {
			return true;
		}

		@Override
		public FlowRecordInfo getFlowRecord(String recordId, String splitNo) {
			return null;
		}

		@Override
		public boolean flowEventComplete(FlowEventRecordInfo flowEventRecordInfo) {
			return true;
		}

		@Override
		public FlowRecordInfo launch(String recordId, String tradeNo, String businessType, String businessSubType, String businessCode, String flowDescStr, String splitNo, Supplier<Boolean> launchSupplier) {
			FlowRecordInfo flowRecordInfo = new FlowRecordInfo();
			flowRecordInfo.setBusinessType("1");
			flowRecordInfo.setBusinessSubType("2");
			flowRecordInfo.setBusinessCode("3");
			return flowRecordInfo;
		}

		@Override
		public List<FlowEventRecordInfo> getEventsByTradeNo(String tradeNo, String splitNo) {
			return List.of();
		}

		@Override
		public FlowEventRecordInfo getLatestEvent(String mainRecordId, String businessType, String businessSubType, String businessCode, String event, String splitNo) {
			return null;
		}

		@Override
		public boolean flowContinueStart(String currentStatus, String continueStatus, String recordId, String splitNo) {
			return true;
		}

		@Override
		public boolean flowBreak(FlowEventRecordInfo flowEventRecordInfo) {
			return true;
		}
	}
}
