package com.taotao.cloud.goods.application.flow.flow;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.skywalking.apm.toolkit.trace.RunnableWrapper;

import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Slf4j
public class FlowCenter {

	private FlowRegister register;
	private FlowEventRecordInfoInterface flowEventRecordInfoInterface;
	private ThreadPoolExecutor threadPoolExecutor;
	private Map<String /*channel*/, FlowHandler> flowHandlerTable = new HashMap<>();
	private Map<String /*businessType*/, BusinessHandler> businessHandlerTable = new HashMap<>();
	private ParseAdviser parseAdviser;

	/**
	 * ParseAdviser
	 */
	public interface ParseAdviser {

		/**
		 * advise
		 */
		Advice advise( FlowEventRecordInfo flowEventRecordInfo );

		/**
		 * Advise
		 */
		@Data
		class Advise {

			/**
			 * code
			 */
			public static final String NORMAL = "NORMAL"; // 正常
			public static final String BREAK = "BREAK"; // 退出

			/**
			 * code
			 */
			private String code;
		}
	}

	/**
	 *
	 */
	public String flowHandlerInfo() {
		if (null != flowHandlerTable && flowHandlerTable.size() > 0) {
			StringBuilder sb = new StringBuilder();
			flowHandlerTable.forEach(( k, v ) -> sb.append(k).append(":").append(v.info()).append(";"));
			return sb.toString();
		}
		return "empty";
	}

	/**
	 *
	 */
	public String flowBusinessHandlerInfo() {
		if (null != businessHandlerTable && businessHandlerTable.size() > 0) {
			StringBuilder sb = new StringBuilder();
			businessHandlerTable.forEach(( k, v ) -> sb.append(k).append(":").append(v.info()).append(";"));
			return sb.toString();
		}
		return "empty";
	}

	public FlowCenter( FlowRegister register, FlowEventRecordInfoInterface flowEventRecordInfoInterface,
		ThreadPoolExecutor threadPoolExecutor ) {
		Assert.assertNotNull(register, "FlowCenter.FlowCenter,注册中心为null");
		Assert.assertNotNull(flowEventRecordInfoInterface, "FlowCenter.FlowCenter,事件记录服务为null");
		this.register = register;
		this.flowEventRecordInfoInterface = flowEventRecordInfoInterface;
		this.threadPoolExecutor = threadPoolExecutor;
	}

	/**
	 * parseAdviser
	 */
	public void installParseAdviser( ParseAdviser parseAdviser ) {
		this.parseAdviser = parseAdviser;
	}

	public void flow( FlowContext flowContext, Supplier<Boolean> launchSupplier ) {
		String businessSubType = flowContext.getBusinessSubType();
		if (StringUtils.isEmpty(businessSubType)) {
			flowContext.setBusinessSubType(FlowRegister.BUSINESS_SUB_TYPE_COMMON);
		}

		String businessCode = flowContext.getBusinessCode();
		if (StringUtils.isEmpty(businessCode)) {
			flowContext.setBusinessCode(FlowRegister.BUSINESS_CODE_COMMON);
		}

		// 获取业务对应流程
		Flow flow = this.findFlow(flowContext);
		if (null == flow) {
			log.error(
				"业务流程不存在，业务类型：[{}]，业务子类型：[{}]，业务编码：[{}]",
				flowContext.getBusinessType(),
				flowContext.getBusinessSubType(),
				flowContext.getBusinessCode()
			);
			throw new RuntimeException("业务流程不存在");
		}

		String rootFlowDescJsonDesc = FlowRegister.getRootFlowDescJsonDesc(flow);
		// 流程发起
		FlowRecordInfo flowRecordInfo = flowEventRecordInfoInterface.launch(
			flowContext.getMainRecordId(),
			flowContext.getTradeNo(),
			flowContext.getBusinessType(),
			flowContext.getBusinessSubType(),
			flowContext.getBusinessCode(),
			rootFlowDescJsonDesc,
			flowContext.getSplitNo(),
			launchSupplier);

		if (null == flowRecordInfo) {
			log.warn("FlowCenter.flow,失败,flowContext:{{}}", flowContext);
			return;
		}

		this.doFlow(flow, flowContext, flowContext.getFlowEventRecordInfo());
	}

	private Flow findFlow( FlowContext flowContext ) {
		// flowFindInfo
		FlowTreeFindInfo flowFindInfo = new FlowTreeFindInfo(
			flowContext.getBusinessType(),
			flowContext.getBusinessSubType(),
			flowContext.getBusinessCode());

		FlowTree flowTree = register.find(flowFindInfo);
		if (null == flowTree) {
			throw new IllegalArgumentException("FlowCenter.flow, root flowTree is null flowContext :" + flowContext);
		}
		// 获取流程
		FlowEventRecordInfo flowEventRecordInfo = flowContext.getFlowEventRecordInfo();
		Flow currentFlowTree = flowTree;
		if (null == flowEventRecordInfo) {
			// firstFlow
			currentFlowTree = flowTree.firstNode();
		} else {
			Deque<FlowEventRecordInfo> backUpFlowEventRecordDeque = new ArrayDeque<>();
			Deque<FlowEventRecordInfo> flowEventDeque = flowContext.getFlowEventRecordDeque();
			while (!flowEventDeque.isEmpty() && null != currentFlowTree) {
				FlowEventRecordInfo topFlowEventRecordInfo = flowEventDeque.pop();
				// backUp
				backUpFlowEventRecordDeque.push(topFlowEventRecordInfo);
				currentFlowTree = this.recursionFind(topFlowEventRecordInfo, currentFlowTree);
			}
			// recover
			flowContext.setFlowEventRecordDeque(backUpFlowEventRecordDeque);
		}

		FlowEventInfo flowEventInfo = new FlowEventInfo();
		flowEventInfo.setRootFlow(flowEventRecordInfo.getRootFlow());
		flowEventInfo.setParentFlow(flowEventRecordInfo.getParentFlow());
		flowEventInfo.setCurrentFlow(flowEventRecordInfo.getCurrentFlow());

		currentFlowTree = this.recursionFind(flowEventInfo, currentFlowTree);

		if (null == currentFlowTree) {
			throw new IllegalArgumentException("FlowCenter.flow, flowTree is null flowContext :" + flowContext);
		}

		return currentFlowTree;
	}

	private Flow findActualFlow( String businessType, String businessSubType, String businessCode,
		FlowEventInfo flowEventInfo ) {
		FlowTreeFindInfo flowFindInfo = new FlowTreeFindInfo(
			businessType,
			businessSubType,
			businessCode);
		FlowTree flowTree = register.find(flowFindInfo);
		if (null == flowTree) {
			throw new IllegalArgumentException(String.format(
				"FlowCenter.flow::findActualFlow, flowFindInfo is null,businessType:%s,businessSubType:%s,businessCode:%s:",
				businessType, businessSubType, businessCode));
		}
		Flow actualFlowTree = this.recursionFind(flowEventInfo, flowTree);
		if (null == actualFlowTree) {
			throw new IllegalArgumentException(String.format(
				"FlowCenter.flow::findActualFlow, actualFlowTree is null,flowEventInfo.currentFlow.name:%s," +
					"flowEventInfo.currentFlow.channel:%s",
				flowEventInfo.getCurrentFlow().getName(), flowEventInfo.getCurrentFlow().getChannel()));
		}
		return actualFlowTree;
	}

	private Flow recursionFind( FlowEventInfo flowEventInfo, Flow flow ) {
		FlowSearchInfo flowSearchInfo = new FlowSearchInfo();
		FlowInfo rootFlow = new FlowInfo(flowEventInfo.getRootFlow(), "");
		flowSearchInfo.setRootFlow(rootFlow);
		flowSearchInfo.setCurrentFlow(flowEventInfo.getCurrentFlow());
		return flow.search(flowSearchInfo);
	}

	private void doFlow( Flow flowTree, FlowContext flowContext, FlowEventRecordInfo updateFlowEventRecord ) {
		FlowTreeFindInfo flowTreeFindInfo = new FlowTreeFindInfo(flowContext.getBusinessType(),
			flowContext.getBusinessSubType(), flowContext.getBusinessCode());
		List<FlowEvent<FlowEventRecordInfo>> flows = flowTree.flows(
			flowTreeFindInfo,
			flowContext.getFlowEventRecordDeque(),
			flowContext.getTradeNo(),
			flowContext.getMainRecordId());
		if (null != flows && flows.size() > 0) {
			for (FlowEvent<FlowEventRecordInfo> flow : flows) {
				flow.getFlowEventInfo().setSplitNo(flowContext.getSplitNo());
			}
			// 持久化流程
			boolean flow = flowEventRecordInfoInterface.flow(
				updateFlowEventRecord,
				flows.stream().map(FlowEvent::getFlowEventInfo).collect(Collectors.toList()));
			if (!flow) {
				log.error("FlowCenter.doFlow，流程流转失败，上下文: {{}}", flowContext);
				return;
			}
			// 过滤掉终态
			List<FlowEvent<FlowEventRecordInfo>> noCompleteL = flows.stream()
				.filter(flowEvent -> !flowEvent.getFlowEventInfo().isComplete())
				.collect(Collectors.toList());

			// flowEventL
			if (null != noCompleteL && noCompleteL.size() > 0) {
				if (flows.size() > 1 || !( noCompleteL.get(0) instanceof AsyncFlow )) {
					// 异步分发
					for (FlowEvent<FlowEventRecordInfo> flowEvent : noCompleteL) {
						this.asyncDistribute(flowEvent, flowContext);
					}
				} else {
					flowContext.setFlowEventRecordInfo(noCompleteL.get(0).getFlowEventInfo());
					this.doDistribute(noCompleteL.get(0).getFlow(), flowContext);
				}
			} else {
				throw new IllegalArgumentException(
					"FlowCenter.doFlow, flows is empty flowContext : " + flowContext + "currentFlowTree: "
						+ flowTree.name());
			}
		}

	}

	/**
	 *
	 */
	private void asyncDistribute( FlowEvent<FlowEventRecordInfo> flowEvent, FlowContext flowContext ) {
		FlowContext cloneFlowContext = flowContext.deepClone();
		cloneFlowContext.setFlowEventRecordInfo(flowEvent.getFlowEventInfo());
		if (null != threadPoolExecutor) {
			// 线程池
			threadPoolExecutor.execute(RunnableWrapper.of(() -> doDistribute(flowEvent.getFlow(), cloneFlowContext)));
		} else {
			new Thread(RunnableWrapper.of(() -> doDistribute(flowEvent.getFlow(), cloneFlowContext)).start();
		}
	}

	/**
	 *
	 */
	private void doDistribute( Flow flow, FlowContext flowContext ) {
		log.info("FlowCenter.doDistribute,flowEventInfo: {{}}", flowContext.getFlowEventRecordInfo());
		// 事件分发
		this.publish(flowContext);
		// 事件解析
		this.parse(flow, flowContext);
	}

	private void parse( Flow flow, FlowContext flowContext ) {
		// 事件记录
		FlowEventRecordInfo flowEventRecordInfo = flowContext.getFlowEventRecordInfo();
		if (null == flowEventRecordInfo) {
			log.warn("FlowCenter.parse, flowEventRecordInfo is null, flowContext: {{}}", flowContext);
			return;
		}

		// 流转增强
		if (null != this.parseAdviser && !BusinessFlowStatus.P.equals(
			flowContext.getFlowEventRecordInfo().getStatus())) { // 非处理中
			ParseAdviser.Advise advise = this.parseAdviser.advise(flowEventRecordInfo);
			if (null != advise && ParseAdviser.Advise.BREAK.equals(advise.code)) {
				flowEventRecordInfoInterface.flowBreak(flowEventRecordInfo);
				return;
			}
		}

		// 流转
		if (flow instanceof VirtualFlow) {
			flow = this.findFlow(flowContext);
		}

		NextFlowInfo nextFlowInfo = flow.nextFlow(flowContext);
		Flow nextFlow = nextFlowInfo.getNextFlow();
		boolean isEnd = nextFlowInfo.isEnd();
		if (null == nextFlow) {
			if (isEnd) {
				// 归并
				FlowEventRecordInfo currentEventRecordInfo = flowEventRecordInfo;
				while (null != currentEventRecordInfo && !StringUtils.isEmpty(
					currentEventRecordInfo.getParentRecordId())) {
					flowContext.getFlowEventRecordDeque().pollLast();
					// 获取流程记录
					currentEventRecordInfo = flowEventRecordInfoInterface.getEventByRecordId(
						currentEventRecordInfo.getParentRecordId(),
						currentEventRecordInfo.getSplitNo());
					flowContext.setFlowEventRecordInfo(currentEventRecordInfo);
					Flow parentFlow = this.findFlow(flowContext);
					NextFlowInfo parentNextFlowInfo = parentFlow.nextFlow(flowContext);
					if (null != ( nextFlow = parentNextFlowInfo.getNextFlow() )) {
						break;
					}
					if (!( isEnd = parentNextFlowInfo.isEnd() )) {
						break;
					}
				}
			}
		} else {
			if (nextFlow instanceof VirtualFlow) {
				FlowEventInfo flowEventInfo = new FlowEventInfo();
				flowEventInfo.setCurrentFlow(nextFlow.name());
				// 查找实体节点
				nextFlow = this.findActualFlow(
					flowContext.getBusinessType(),
					flowContext.getBusinessSubType(),
					flowContext.getBusinessCode(),
					flowEventInfo);
			}
		}

// 流程记录状态更新
		if (isEnd) {
			// 流程结束
			boolean completed = flowEventRecordInfoInterface.flowComplete(flowEventRecordInfo);
			if (!completed) {
				log.error("FlowCenter.parse,流程完成失败,flowEventRecordInfo: {{}}", flowEventRecordInfo);
			} else {
				flowContext.flowComplete();
				FlowBusinessContext orSetBusinessContext = flowContext.getOrSetBusinessContext(null);
				if (orSetBusinessContext != null) {
					orSetBusinessContext.flowComplete();
				}
			}
			return;
		}

		if (null != nextFlow) {
			// 流转
			this.doFlow(nextFlow, flowContext, flowEventRecordInfo);
			return;
		}

// 事件记录状态（处理中）
		if (!flowEventRecordInfo.isComplete()) {
			log.info("FlowCenter.parse, flowEventRecordInfo is not complete,flowEventRecordInfo: {{}}",
				flowEventRecordInfo);
			return;
		}

		boolean flowEventComplete = flowEventRecordInfoInterface.flowEventComplete(flowEventRecordInfo);

		if (!flowEventComplete) {
			log.error("FlowCenter.parse,流程事件完成失败,flowEventRecordInfo: {{}}", flowEventRecordInfo);
		}
	}

	private void publish( FlowContext flowContext ) {
		FlowEventRecordInfo flowEventRecordInfo = flowContext.getFlowEventRecordInfo();
		String channel = flowEventRecordInfo.getCurrentFlow().getChannel();
		FlowHandler flowHandler = flowHandlerTable.get(channel);
		if (null == flowHandler) {
			throw new IllegalArgumentException(
				"FlowCenter.publish,flowHandler is null,flowEventRecordInfo is " + flowEventRecordInfo);
		}
		flowHandler.handle(flowContext);
	}

	/**
	 * registerHandler
	 */
	public void registerFlowHandler( FlowHandler flowHandler ) {
		Objects.requireNonNull(flowHandler);
		if (StringUtils.isEmpty(flowHandler.channelName())) {
			throw new IllegalArgumentException(
				"FlowCenter.registerHandler,flowHandler channelName is empty,flowHandler is " + flowHandler);
		}

		FlowHandler pre = this.flowHandlerTable.putIfAbsent(flowHandler.channelName(), flowHandler);
		if (null != pre) {
			throw new IllegalArgumentException(
				"FlowCenter.registerHandler,flowHandler register is repeat,channelName is "
					+ flowHandler.channelName());
		}
	}

	public void registerBusinessHandlerHandler( BusinessHandler businessHandler ) {
		Objects.requireNonNull(businessHandler);
		if (StringUtils.isEmpty(businessHandler.businessName())) {
			throw new IllegalArgumentException(
				"FlowCenter.registerBusinessHandlerHandler, businessHandler channelName is empty,businessHandler is "
					+ businessHandler);
		}
		BusinessHandler pre = this.businessHandlerTable.putIfAbsent(businessHandler.businessName(), businessHandler);
		if (null != pre) {
			throw new IllegalArgumentException(
				"FlowCenter.registerBusinessHandlerHandler, businessHandler register is repeat,channelName is "
					+ businessHandler.businessName());
		}
	}

	/**
	 *
	 */
	private void checkOrCallbackToBusiness( FlowContext flowContext ) {
		if (flowContext.flowCompletePredict()) {
			callbackToBusiness(flowContext);
		}
	}

	public void callbackToBusiness( FlowContext flowContext ) {
		String businessType = flowContext.getBusinessType();
		BusinessHandler businessHandler = this.businessHandlerTable.get(businessType);
		if (null == businessHandler) {
			log.error("FlowCenter.callbackToBusiness,此业务类型未注册处理器，不需要执行，业务类型:{}", businessType);
			throw new RuntimeException("此业务类型未注册处理器，不需要执行通知操作!");
		}
		businessHandler.callBack(flowContext);
	}

	/**
	 * 流程结束通知
	 */
	public <E extends FlowBusinessContext> void flowCompleteNotify( String recordId, String splitNo,
		Function<FlowRecordInfo, E> function ) {
		FlowRecordInfo flowRecord = this.flowEventRecordInfoInterface.getFlowRecord(
			recordId,
			splitNo);
		if (null == flowRecord) {
			log.error("FlowCenter.flowCompleteNotify,流程记录不存在");
			return;
		}
		if (!BusinessFlowStatus.C.equals(flowRecord.getStatus())) {
			log.error("FlowCenter.flowCompleteNotify,流程未结束，flowRecord: {{}}", flowRecord);
			return;
		}
		FlowContext<E> flowContext = new FlowContext<>(flowRecord.getRecordId(), flowRecord.getTradeNo(),
			flowRecord.getSplitNo());
		flowContext.flowComplete();
		if (null != function) {
			flowContext.setBusinessContext(function.apply(flowRecord));
		}
		E orSetBusinessContext = flowContext.getOrSetBusinessContext(null);
		if (null != orSetBusinessContext) {
			orSetBusinessContext.flowComplete();
		}
		flowContext.setBusinessType(flowRecord.getBusinessType());
		flowContext.setBusinessSubType(flowRecord.getBusinessSubType());
		flowContext.setBusinessCode(flowRecord.getBusinessCode());
		BusinessHandler businessHandler = this.businessHandlerTable.get(flowContext.getBusinessType());
		businessHandler.callBack(flowContext);
	}

	/**
	 * 失败重试
	 */
	public void failRetry( String recordId, String splitNo ) {
		// 流程事件记录
		FlowEventRecordInfo failFlowEventRecordInfo = this.getFlowEventRecordInfo(recordId, splitNo);
		if (!BusinessFlowStatus.F.equals(failFlowEventRecordInfo.getStatus())) {
			log.warn("FlowCenter.failRetry,failFlowEventRecordInfo status is not fail ,failFlowEventRecordInfo:{}",
				failFlowEventRecordInfo);
			return;
		}

		String parentRecordId = failFlowEventRecordInfo.getParentRecordId();
		String parentSplitNo = failFlowEventRecordInfo.getSplitNo();

		// 获取流程节点
		FlowContext flowContext = new FlowContext(failFlowEventRecordInfo);
		while (!StringUtils.isEmpty(parentRecordId)) {
			FlowEventRecordInfo parentFlowEventRecordInfo = this.getFlowEventRecordInfo(parentRecordId, parentSplitNo);
			flowContext.getFlowEventRecordDeque().addFirst(parentFlowEventRecordInfo);
			parentRecordId = parentFlowEventRecordInfo.getParentRecordId();
		}

		Flow flow = this.findFlow(flowContext);
		if (null != flow) {
			if (flow instanceof SuccessFlow) {
				this.failFlowRetry((SuccessFlow) flow, flowContext);
			} else {
				log.warn("FlowCenter.failRetry,flow not support failRetry,flow:{{}}", flow);
			}
		} else {
			log.warn("FlowCenter.failRetry,flow not exist,flowContext:{{}}", flowContext);
		}
	}

	/**
	 *
	 */
	private void failFlowRetry( SuccessFlow flow, FlowContext flowContext ) {
		FlowEventRecordInfo failFlowEventRecordInfo = flowContext.getFlowEventRecordInfo();
		FlowEvent<FlowEventRecordInfo> flowEvent = flow.failRetry(failFlowEventRecordInfo);
		FlowEventRecordInfo retryFlowEventRecordInfo = flowEvent.getFlowEventInfo();
		boolean retry = flowEventRecordInfoInterface.failRetry(failFlowEventRecordInfo, retryFlowEventRecordInfo);
		if (retry) {
			flowContext.setFlowEventRecordInfo(retryFlowEventRecordInfo);
			// 重试
			this.doRetry(flowEvent, flowContext);
		}
	}

	public <I extends FlowBusinessContext> void manualConfirm( String recordId,
		String splitNo,
		ManualConfirmType manualConfirmType,
		I flowBusinessContext,
		Supplier<Boolean> idempotentSupplier ) {
		if (StringUtils.isEmpty(recordId) || null == manualConfirmType) {
			log.warn("FlowCenter.manualConfirm,continueFlowEventRecordInfo recordId or manualConfirmType is illegal");
			return;
		}

		FlowEventRecordInfo continueFlowEventRecordInfo = flowEventRecordInfoInterface.getEventByRecordId(
			recordId,
			splitNo
		);

		if (null == continueFlowEventRecordInfo) {
			log.warn("FlowCenter.flowContinue,continueFlowEventRecordInfo is not exist ,recordId:{{}}", recordId);
			return;
		}

		// 流程事件记录
		String status = continueFlowEventRecordInfo.getStatus();
		if (!BusinessFlowStatus.P.equals(status)) {
			log.warn("FlowCenter.flowContinue,continueFlowEventRecordInfo status is not P ,recordId:{{}},status: {{}}",
				recordId, status);
			return;
		}

		// 获取当前节点
		FlowContext<I> flowContext = new FlowContext<>(continueFlowEventRecordInfo);
		flowContext.setBusinessContext(flowBusinessContext);
		Flow flow = this.findFlow(flowContext);
		if (null == flow) {
			log.warn("FlowCenter.flowContinue,flow not exist ,continueFlowEventRecordInfo: {{}}",
				continueFlowEventRecordInfo);
			return;
		}
		switch (manualConfirmType) {
			case FAIL:
				continueFlowEventRecordInfo.setStatus(BusinessFlowStatus.F);
				break;
			case SUCCESS:
				continueFlowEventRecordInfo.setStatus(BusinessFlowStatus.S);
				break;
		}

			if (null == idempotentSupplier) {
				// 解析
				this.parse(flow, flowContext);

			} else {
				Boolean aBoolean = idempotentSupplier.get();
				if (aBoolean) {
					this.parse(flow, flowContext);
				}
			}
// 业务回调
			this.checkOrCallbackToBusiness(flowContext);
		}


	/**
	 * 断点继续
	 */
	public <I extends FlowBusinessContext> void flowContinue( String recordId, String splitNo,
		ContinueType continueType ) {
		if (StringUtils.isEmpty(recordId) || null == continueType) {
			log.warn("FlowCenter.flowContinue,continueFlowEventRecordInfo recordId or continueType is illegal");
			return;
		}

		FlowEventRecordInfo continueFlowEventRecordInfo = flowEventRecordInfoInterface.getEventByRecordId(
			recordId,
			splitNo
		);
		if (null == continueFlowEventRecordInfo) {
			log.warn("FlowCenter.flowContinue,continueFlowEventRecordInfo is not exist," +
				"recordId:{{}}),continueType:{{}}", recordId, continueType);
			return;
		}

		// 流程事件记录
		if (BusinessFlowStatus.P.equals(continueFlowEventRecordInfo.getStatus())) {
			log.warn(
				"FlowCenter.flowContinue,continueFlowEventRecordInfo status is not process ,continueFlowEventRecordInfo:{{}}",
				continueFlowEventRecordInfo);
			return;
		}

		// 获取当前节点
		FlowContext flowContext = new FlowContext<>(continueFlowEventRecordInfo);
		Flow flow = this.findFlow(flowContext);
		if (null == flow) {
			log.warn("FlowCenter.flowContinue,flow not exist ,continueFlowEventRecordInfo:{{}}",
				continueFlowEventRecordInfo);
			return;
		}

		FlowBusinessContext orSetBusinessContext = flowContext.getOrSetBusinessContext(null);

		switch (continueType) {
			case END:
				flow = this.wrapEndFlow(flow);
				// 解析
				this.parse(flow, flowContext);
				// 回调
				flowContext.flowComplete();
				if (null != orSetBusinessContext) {
					orSetBusinessContext.flowComplete();
				}
				this.callbackToBusiness(flowContext);
				break;
			case CONTINUE:
				flow = this.wrapContinueFlow(flow);
				this.parse(flow, flowContext);
				FlowRecordInfo flowRecord = flowEventRecordInfoInterface.getFlowRecord(
					continueFlowEventRecordInfo.getMainRecordId(),
					continueFlowEventRecordInfo.getSplitNo()
				);
				if (BusinessFlowStatus.C.equals(flowRecord.getStatus())) {
					// 回调
					flowContext.flowComplete();
					if (null != orSetBusinessContext) {
						orSetBusinessContext.flowComplete();
					}
					this.callbackToBusiness(flowContext);
				}
				break;
			case RETRY:
				flow = this.wrapFailRetryFlow(flow);
				this.failFlowRetry((SuccessFlow) flow, flowContext);
				break;
		}
	}

	/**
	 *
	 */
	private Flow wrapFailRetryFlow( Flow flow ) {
		return new FlowRetryFlowProxy(flow, flowEventRecordInfoInterface);
	}

	/**
	 * 幂等重试
	 */
	public void idempotentRetry( String recordId, String splitNo ) {
		// 流程事件记录
		FlowEventRecordInfo processFlowEventRecordInfo = this.getFlowEventRecordInfo(recordId, splitNo);
		if (!BusinessFlowStatus.P.equals(processFlowEventRecordInfo.getStatus())) {
			log.warn(
				"FlowCenter.idempotentRetry,processFlowEventRecordInfo status is not process,processFlowEventRecordInfo:{{}}",
				processFlowEventRecordInfo);
			return;
		}

		String parentRecordId = processFlowEventRecordInfo.getParentRecordId();
		String parentSplitNo = processFlowEventRecordInfo.getSplitNo();

		// 获取流程节点
		FlowContext flowContext = new FlowContext(processFlowEventRecordInfo);
		while (!StringUtils.isEmpty(parentRecordId)) {
			FlowEventRecordInfo parentFlowEventRecordInfo = this.getFlowEventRecordInfo(parentRecordId,
				parentSplitNo);
			flowContext.getFlowEventRecordDeque().addFirst(parentFlowEventRecordInfo);
			parentRecordId = parentFlowEventRecordInfo.getParentRecordId();
		}

		// flowEvent
		Flow flow = this.findFlow(flowContext);
		FlowEvent<FlowEventRecordInfo> flowEvent = new FlowEvent<>(flowContext);
		flowEvent.setFlowEventInfo(processFlowEventRecordInfo);
		flowEvent.setFlow(flow);

		this.doRetry(flowEvent, flowContext);
	}

	/**
	 * 重试
	 */
	private void doRetry( FlowEvent<FlowEventRecordInfo> flowEvent, FlowContext flowContext ) {
		// 分发
		this.doDistribute(flowEvent.getFlow(), flowContext);
		// 业务回调
		this.checkOrCallbackToBusiness(flowContext);
	}

	/**
	 * FlowRetryFlowProxy
	 */
	private static class FlowRetryFlowProxy implements Flow, SuccessFlow {

		/**
		 * 被代理节点
		 */
		private Flow flow;
		/**
		 * flowEventRecordInfoInterface
		 */
		private FlowEventRecordInfoInterface flowEventRecordInfoInterface;

		/**
		 *
		 */
		public FlowRetryFlowProxy( Flow flow, FlowEventRecordInfoInterface flowEventRecordInfoInterface ) {
			this.flow = flow;
			this.flowEventRecordInfoInterface = flowEventRecordInfoInterface;
		}

		public FlowEvent<FlowEventRecordInfo> failRetry( FlowEventRecordInfo failRecord ) {
			String clone = failRecord.getStatus();
			if (!BusinessFlowStatus.F.equals(clone)) {
				failRecord.setStatus(BusinessFlowStatus.F);
				boolean started = flowEventRecordInfoInterface.flowContinueStart(
					clone,
					failRecord.getStatus(),
					failRecord.getRecordId(),
					failRecord.getSplitNo()
				);
				if (!started) {
					throw new RuntimeException("熔断失败重试失败");
				}
			}

			FlowEventRecordInfo retryRecord;
			FlowEvent<FlowEventRecordInfo> flowEvent = new FlowEvent<>();
			flowEvent.setFlowEventInfo(retryRecord = new FlowEventRecordInfo());
			flowEvent.setFlow(this.getFlow());
			retryRecord.setTradeNo(failRecord.getTradeNo());
			retryRecord.setParentRecordId(failRecord.getParentRecordId());
			retryRecord.setRecordId(this.getFlow().generateRecordId());
			retryRecord.setStatus(BusinessFlowStatus.P);
			retryRecord.setBusinessType(failRecord.getBusinessType());
			retryRecord.setBusinessSubType(failRecord.getBusinessSubType());
			retryRecord.setBusinessCode(failRecord.getBusinessCode());
			retryRecord.setRootFlow(failRecord.getRootFlow());
			retryRecord.setParentFlow(failRecord.getParentFlow());
			retryRecord.setCurrentFlow(failRecord.getCurrentFlow());
			retryRecord.setType(failRecord.getType());
			retryRecord.setMainRecordId(failRecord.getMainRecordId());
			retryRecord.setSplitNo(failRecord.getSplitNo());

			return flowEvent;
		}


		/**
		 *
		 */
		@Override
		public Flow getFlow() {
			return flow;
		}

		@Override
		public FlowInfo name() {
			return flow.name();
		}

		@Override
		public Flow search( FlowSearchInfo flowSearchInfo ) {
			return flow.search(flowSearchInfo);
		}

		@Override
		public Flow firstNode() {
			return flow.firstNode();
		}

		@Override
		public NextFlowInfo nextFlow( FlowContext flowContext ) {

			return flow.nextFlow(flowContext);
		}

		@Override
		public String generateRecordId() {
			return flow.generateRecordId();
		}

		@Override
		public String type() {
			return flow.type();
		}


		private Flow wrapContinueFlow( Flow flow ) {
			return new Flow() {
				@Override
				public FlowInfo name() {
					return flow.name();
				}

				@Override
				public Flow search( FlowSearchInfo flowSearchInfo ) {
					return flow.search(flowSearchInfo);
				}

				@Override
				public Flow firstNode() {
					return null;
				}

				@Override
				public NextFlowInfo nextFlow( FlowContext flowContext ) {
					// CAS更新订单状态为处理中
					FlowEventRecordInfo flowEventRecordInfo = flowContext.getFlowEventRecordInfo();
					boolean started = flowEventRecordInfoInterface.flowContinueStart(
						flowEventRecordInfo.getStatus(),
						BusinessFlowStatus.P,
						flowEventRecordInfo.getRecordId(),
						flowEventRecordInfo.getSplitNo()
					);
					if (!started) {
						throw new RuntimeException("熔断流程继续失败");
					}
					return flow.nextFlow(flowContext);
				}

				@Override
				public String generateRecordId() {
					return flow.generateRecordId();
				}

				@Override
				public String type() {
					return flow.type();
				}
			};
		}

		private Flow wrapEndFlow( Flow flow ) {
			return new Flow() {
				@Override
				public FlowInfo name() {
					return flow.name();
				}

				@Override
				public Flow search( FlowSearchInfo flowSearchInfo ) {
					return null;
				}

				@Override
				public Flow firstNode() {
					return null;
				}

				@Override
				public NextFlowInfo nextFlow( FlowContext flowContext ) {
					// CAS更新订单状态为处理中
					FlowEventRecordInfo flowEventRecordInfo = flowContext.getFlowEventRecordInfo();
					boolean started = flowEventRecordInfoInterface.flowContinueStart(
						flowEventRecordInfo.getStatus(),
						BusinessFlowStatus.P,
						flowEventRecordInfo.getRecordId(),
						flowEventRecordInfo.getSplitNo()
					);
					if (!started) {
						throw new RuntimeException("熔断流程结束失败");
					}
					NextFlowInfo nextFlowInfo = new NextFlowInfo();
					return nextFlowInfo.end();
				}

				@Override
				public String generateRecordId() {
					return null;
				}

				@Override
				public String type() {
					return flow.type();
				}
			};
		}



		public static ContinueType valueOfName( String name ) {
			if (StringUtils.isEmpty(name)) {
				return null;
			}

			for (ContinueType continueType : ContinueType.values()) {
				if (continueType.name().equals(name)) {
					return continueType;
				}
			}

			return null;
		}



		/**
		 *
		 */
		public FlowEventRecordInfo getFlowEventRecordInfo( String recordId, String splitNo ) {
			return flowEventRecordInfoInterface.getEventByRecordId(recordId, splitNo);
		}

		/**
		 *
		 */
		public void asyncResponse( FlowContext flowContext ) {
			// parentRecordS
			String parentRecordId = flowContext.getFlowEventRecordInfo().getParentRecordId();
			String parentSplitNo = flowContext.getFlowEventRecordInfo().getSplitNo();
			while (!StringUtils.isEmpty(parentRecordId)) {
				FlowEventRecordInfo flowEventRecordInfo = this.getFlowEventRecordInfo(parentRecordId, parentSplitNo);
				flowContext.getFlowEventRecordDeque().addFirst(flowEventRecordInfo);
				parentRecordId = flowEventRecordInfo.getParentRecordId();
			}
			// 获取flow
			Flow flow = this.findFlow(flowContext);
			// 解析
			this.parse(flow, flowContext);
			// 业务回调
			this.checkOrCallbackToBusiness(flowContext);
		}

		public FlowEventRecordInfo getLatestFlowEvent(
			String mainRecordId,
			String splitNo,
			String businessType,
			String businessEvent,
			String... businessSubTypeAndBusinessCode ) {

			String businessSubType = FlowRegister.BUSINESS_SUB_TYPE_COMMON;
			String businessCode = FlowRegister.BUSINESS_CODE_COMMON;
			if (null != businessSubTypeAndBusinessCode) {
				if (businessSubTypeAndBusinessCode.length >= 1) {
					businessSubType = businessSubTypeAndBusinessCode[0];
				}
				if (businessSubTypeAndBusinessCode.length >= 2) {
					businessCode = businessSubTypeAndBusinessCode[1];
				}
			}
			return flowEventRecordInfoInterface.getLatestEvent(
				mainRecordId,
				businessType,
				businessSubType,
				businessCode,
				businessEvent,
				splitNo);
		}
	}


	/**
	 *
	 */
	public void installExecuteThreadPool( ThreadPoolExecutor threadPoolExecutor ) {
		this.threadPoolExecutor = threadPoolExecutor;
	}

}
