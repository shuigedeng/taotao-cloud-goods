package com.taotao.cloud.goods.application.flow.flow;

import com.alibaba.fastjson2.JSON;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

@Slf4j
public class FlowRegister {

	/**
	 * TABLE_TYPE
	 */
	public static final String BUSINESS_TYPE = "BUSINESS_TYPE";
	public static final String BUSINESS_SUB_TYPE = "BUSINESS_SUB_TYPE";
	public static final String BUSINESS_CODE = "BUSINESS_CODE";
	/**
	 * BUSINESS_SUB_TYPE
	 */
	public static final String BUSINESS_SUB_TYPE_COMMON = "COMMON";
	/**
	 * BUSINESS_CODE_COMMON
	 */
	public static final String BUSINESS_CODE_COMMON = "COMMON";
	/**
	 * table
	 */
	private Map<String, Table<Table>> businessTypeTable = new HashMap<>();
	/**
	 * flowEventRecordInfoService
	 */
	private FlowEventRecordInfoInterface flowEventRecordInfoService;

	/**
	 *
	 */
	public FlowRegister( FlowEventRecordInfoInterface flowEventRecordInfoService ) {
		this.flowEventRecordInfoService = flowEventRecordInfoService;
	}

	/**
	 * businessTypeMap
	 */
	private static Map<String, String> businessTypeMap = new ConcurrentHashMap<>();

	/**
	 * businessSubType
	 */
	private static Map<String, String> businessSubTypeMap = new ConcurrentHashMap<>();

	/**
	 * businessCodeMap
	 */
	private static Map<String, String> businessCodeMap = new ConcurrentHashMap<>();

	/**
	 * fLowNodeNameMap
	 */
	private static Map<String, String> flowNodeNameMap = new ConcurrentHashMap<>();

	/**
	 * fLowNodeChannelMap
	 */
	private static Map<String, String> flowNodeChannelMap = new ConcurrentHashMap<>();

	/**
	 * rootFlowDescMap
	 */
	private static Map<String, String> rootFlowDescMap = new ConcurrentHashMap<>();

	public void register( FlowRegisterInfo flowRegisterInfo, FlowTree flowTree ) {
		// 1. 参数非空校验
		Objects.requireNonNull(flowTree);
		Objects.requireNonNull(flowRegisterInfo);

		// 2. 构建流程标识并自检
		String flowIdentity = flowRegisterInfo.getBusinessType() + ":"
			+ flowRegisterInfo.getBusinessSubType() + ":"
			+ flowRegisterInfo.getBusinessCode();
		String checkResult = flowTree.selfCheck(flowIdentity, new HashSet<>());
		if (!StringUtils.isEmpty(checkResult)) {
			throw new IllegalArgumentException("FlowRegister.register,check fail, failMsg:" + checkResult);
		}

		// 3. 获取并校验业务类型
		String businessType = flowRegisterInfo.getBusinessType();
		if (StringUtils.isEmpty(businessType)) {
			throw new IllegalArgumentException("FlowRegister.register,businessType is empty");
		}

		// 4. 获取业务子类型（为空则使用默认值）
		String businessSubType = flowRegisterInfo.getBusinessSubType();
		if (StringUtils.isEmpty(businessSubType)) {
			log.info("FlowRegister.register,businessSubType is empty,flowRegisterInfo:{{}}", flowRegisterInfo);
			businessSubType = BUSINESS_SUB_TYPE_COMMON;
		}

		// 5. 获取并校验业务编码
		String businessCode = flowRegisterInfo.getBusinessCode();
		if (StringUtils.isEmpty(businessCode)) {
			throw new IllegalArgumentException("FlowRegister.register,businessCode is empty");
		}

		// 6. 注册到三层Map结构：业务类型 -> 业务子类型 -> 业务编码 -> FlowTree
		Table<Table> businessTypeTable = this.businessTypeTable.computeIfAbsent(businessType,
			k -> new Table<>(BUSINESS_TYPE));
		Table<FlowTree> businessSubTypeTable = businessTypeTable.add(businessSubType,
			new Table<FlowTree>(BUSINESS_SUB_TYPE));
		businessSubTypeTable.add(businessCode, flowTree);

		// 7. 存储业务类型描述（用于展示）
		String businessTypeDesc = flowRegisterInfo.getBusinessTypeDesc();
		if (StringUtils.isEmpty(businessTypeDesc)) {
			businessTypeDesc = flowRegisterInfo.getBusinessType();
		}
		businessTypeMap.put(flowRegisterInfo.getBusinessType(), businessTypeDesc);

		// 业务描述-业务子类型
		String businessSubTypeDesc = flowRegisterInfo.getBusinessSubTypeDesc();
		if (StringUtils.isEmpty(businessSubTypeDesc)) {
			businessSubTypeDesc = flowRegisterInfo.getBusinessSubType();
		}
		if (BUSINESS_SUB_TYPE_COMMON.equals(businessSubTypeDesc)) {
			businessSubTypeDesc = "通用";
		}
		businessSubTypeMap.put(flowRegisterInfo.getBusinessSubType(), businessSubTypeDesc);
// 业务描述-业务编码
		String businessCodeDesc = flowRegisterInfo.getBusinessCodeDesc();
		if (StringUtils.isEmpty(businessCodeDesc)) {
			businessCodeDesc = flowRegisterInfo.getBusinessCode();
		}
		if (BUSINESS_CODE_COMMON.equals(businessCodeDesc)) {
			businessCodeDesc = "通用";
		}
		businessCodeMap.put(flowRegisterInfo.getBusinessCode(), businessCodeDesc);


	}

	public FlowTree find( FlowTreeFindInfo flowFindInfo ) {
		Objects.requireNonNull(flowFindInfo);
		String businessType = flowFindInfo.getBusinessType();
		if (StringUtils.isEmpty(businessType)) {
			throw new IllegalArgumentException("FlowRegister.find,businessType is empty");
		}
		String businessSubType = flowFindInfo.getBusinessSubType();
		if (StringUtils.isEmpty(businessSubType)) {
			throw new IllegalArgumentException("FlowRegister.find,businessSubType is empty");
		}
		String businessCode = flowFindInfo.getBusinessCode();
		if (StringUtils.isEmpty(businessCode)) {
			throw new IllegalArgumentException("FlowRegister.find,businessCode is empty");
		}
		Table<Table> businessTypeTable = this.businessTypeTable.get(businessType);
		if (null == businessTypeTable) {
			throw new IllegalArgumentException(
				"FlowRegister.find,businessTypeTable is null,flowFindInfo is" + flowFindInfo);
		}
		Table<FlowTree> businessSubTypeTable = businessTypeTable.get(businessSubType);
		if (null == businessSubTypeTable) {
			businessSubTypeTable = businessTypeTable.get(FlowRegister.BUSINESS_SUB_TYPE_COMMON);
			if (null == businessSubTypeTable) {
				throw new IllegalArgumentException(
					"FlowRegister.find,businessSubTypeTable is null,flowFindInfo is" + flowFindInfo);
			}
		}
		FlowTree flowTree = businessSubTypeTable.get(businessCode);
		if (null == flowTree) {
			flowTree = businessSubTypeTable.get(FlowRegister.BUSINESS_CODE_COMMON);
			if (null == flowTree) {
				throw new IllegalArgumentException("FlowRegister.find,flowTree is null,flowFindInfo is" + flowFindInfo);
			}
		}
		return flowTree;
	}

	public FlowTreeBuilder flowTreeRoot( FlowInfo root ) {
		// 流程节点描述-名称
		String name = root.getName();
		String nameDesc = root.getNodeDesc();
		if (StringUtils.isEmpty(nameDesc)) {
			nameDesc = name;
		}

		flowNodeNameMap.put(name, nameDesc);

		// 流程节点描述-渠道
		String channel = root.getChannel();
		String channelDesc = root.getChannelDesc();
		if (StringUtils.isEmpty(channelDesc)) {
			channelDesc = channel;
		}

		flowNodeChannelMap.put(channel, channelDesc);
		return new FlowTreeBuilder().flowTree(root, false);
	}

	/**
	 * 成功流程节点，该节点流程必须成功之后，才能执行下一流程
	 */
	public FlowTreeBuilder successFlowTreeRoot( FlowInfo root ) {
		// 流程节点描述-名称
		String name = root.getName();
		String nameDesc = root.getNodeDesc();
		if (StringUtils.isEmpty(nameDesc)) {
			nameDesc = name;
		}

		flowNodeNameMap.put(name, nameDesc);
		// 流程节点描述-渠道
		String channel = root.getChannel();
		String channelDesc = root.getChannelDesc();
		if (StringUtils.isEmpty(channelDesc)) {
			channelDesc = channel;
		}

		flowNodeChannelMap.put(channel, channelDesc);
		return new FlowTreeBuilder().successFlowTree(root, false);
	}

	/**
	 * getFlows
	 */
	public List<String> getFlows( String linkChat ) {
		List<String> flows = new ArrayList<>();
		this.businessTypeTable.forEach(( k, v ) -> {
			List<String> tables = v.tables(linkChat);
			for (String table : tables) {
				flows.add(k + linkChat + table);
			}
		});
		return flows;
	}


	/**
	 * FlowTreeDesc
	 */
	@Data
	public static class FlowTreeDesc implements Serializable {

		private String conditionDesc;
		private String node;
		private String channel;
		private String nodeDesc;
		private String channelDesc;
		private String nodeType;
		private String nodeMode;
		private List<FlowTreeDesc> flowTreeDescList;
	}

	/**
	 * getFlows
	 */
	private static FlowTreeDesc getRootFlowDesc( Flow flow ) {
		FlowTreeDesc flowTreeDesc = buildFlowTreeDesc(flow);
		flowTreeDesc.nodeMode = "root";
		return flowTreeDesc;
	}

	/**
	 * getFlows
	 */
	public static String getRootFlowDescJsonDesc( Flow flow ) {
		if (null == flow) {
			return null;
		}

		String rootFlowDescCacheKey = flow.name() + ": " + flow.type();
		String flowDescCache = rootFlowDescMap.get(rootFlowDescCacheKey);
		if (null == flowDescCache) {
			FlowTreeDesc flowTreeDesc = buildFlowTreeDesc(flow);
			if (null != flowTreeDesc) {
				flowDescCache = JSON.toJSONString(flowTreeDesc);
				rootFlowDescMap.put(rootFlowDescCacheKey, flowDescCache);
			}
		}

		return flowDescCache;
	}

	private static FlowTreeDesc buildFlowTreeDesc( Flow flow ) {
		FlowTreeDesc flowTreeDesc = new FlowTreeDesc();
		flowTreeDesc.node = flow.name().getName();
		flowTreeDesc.channel = flow.name().getChannel();
		flowTreeDesc.nodeDesc = flowNodeNameMap.get(flowTreeDesc.node);
		flowTreeDesc.channelDesc = flowNodeChannelMap.get(flowTreeDesc.channel);
		flowTreeDesc.nodeType = flow.type();
		if (flow instanceof VirtualFlow) {
			flowTreeDesc.nodeMode = "VirtualNode";
		} else if (flow instanceof FlowTree) {
			FlowTree flowTree = (FlowTree) flow;
			List<FlowTree.PredictConditionFlow> flowTreeList = flowTree.getFlowTreeList();
			if (null != flowTreeList && flowTreeList.size() > 0) {
				flowTreeDesc.flowTreeDescList = new ArrayList<>(flowTreeList.size());
				for (FlowTree.PredictConditionFlow predictConditionFlow : flowTreeList) {
					Flow predictConditionFlow = predictConditionFlow.getFlow();
					FlowTreeDesc childrenFlowTreeDesc = buildFlowTreeDesc(predictConditionFlow);
					Predicate<FlowContext> predicate = predictConditionFlow.getPredicate();
					if (predicate instanceof FlowTree.PredicateWithDesc) {
						FlowTree.PredicateWithDesc predicateWithDesc = (FlowTree.PredicateWithDesc) predicate;
						childrenFlowTreeDesc.conditionDesc = predicateWithDesc.desc();
					}
					flowTreeDesc.flowTreeDescList.add(childrenFlowTreeDesc);
				}
			}
			return flowTreeDesc;
		}
		return null;
	}

	/**
	 *
	 */
	public String getFlowDesc( String flowStr, String splitChart ) {
		String[] split = flowStr.split(splitChart);
		return businessTypeMap.get(split[0]) + splitChart + businessSubTypeMap.get(split[1]) + splitChart
			+ businessCodeMap.get(split[2]);
	}

	public class FlowTreeBuilder {

		/**
		 * FlowTree
		 */
		protected FlowTree flowTree;
		/**
		 * flowTreeConditionBuilderList
		 */
		List<FlowTreeConditionBuilder> flowTreeConditionBuilderList = new ArrayList<>();
		/**
		 * flowTreeParallelBuilderList
		 */
		List<FlowTreeParallelBuilder> flowTreeParallelBuilderList = new ArrayList<>();

		/**
		 * 私有化
		 */
		private FlowTreeBuilder() {
		}

		/**
		 * flowInfo
		 */
		public FlowTreeBuilder processComplete() {
			return this.condition(
					flowContext -> flowContext.getFlowEventRecordInfo().getStatus().equals(BusinessFlowStatus.P))
				.flowComplete();
		}


		public FlowTreeBuilder flowComplete() {
			this.flowTree = new FlowCompleteTree(() -> flowEventRecordInfoService);
			return this;
		}

		/**
		 * flowInfo
		 */
		public FlowTreeBuilder flowTree( FlowInfo flowInfo, boolean async ) {
			// 流程节点描述-名称
			String name = flowInfo.getName();
			String nameDesc = flowInfo.getNameDesc();
			if (StringUtils.isEmpty(nameDesc)) {
				nameDesc = name;
			}

			flowNodeNameMap.put(name, nameDesc);

			// 流程节点描述-渠道
			String channel = flowInfo.getChannel();
			String channelDesc = flowInfo.getChannelDesc();
			if (StringUtils.isEmpty(channelDesc)) {
				channelDesc = channel;
			}

			flowNodeChannelMap.put(channel, channelDesc);
			if (async) {
				this.flowTree = new AsyncFlowTree(flowInfo, () -> flowEventRecordInfoService);
			} else {
				this.flowTree = new FlowTree(flowInfo, () -> flowEventRecordInfoService);
			}
			return this;
		}


		/**
		 * flowInfo
		 */
		public FlowTreeBuilder flowTree( FlowInfo flowInfo ) {
			// 流程节点描述-名称
			String name = flowInfo.getName();
			String nameDesc = flowInfo.getNameDesc();
			if (StringUtils.isEmpty(nameDesc)) {
				nameDesc = name;
			}

			flowNodeNameMap.put(name, nameDesc);
			// 流程节点描述-渠道
			String channel = flowInfo.getChannel();
			String channelDesc = flowInfo.getChannelDesc();
			if (StringUtils.isEmpty(channelDesc)) {
				channelDesc = channel;
			}

			flowNodeChannelMap.put(channel, channelDesc);
			this.flowTree = new FlowTree(flowInfo, () -> flowEventRecordInfoService);
			return this;
		}


		public FlowTreeBuilder parallelFlowTree( FlowInfo flowInfo, boolean async ) {
			// 流程节点描述-名称
			String name = flowInfo.getName();
			String nameDesc = flowInfo.getNodeDesc();  // 修正：getUserNameDesc() → getNodeDesc()
			if (StringUtils.isEmpty(nameDesc)) {
				nameDesc = name;
			}

			flowNodeNameMap.put(name, nameDesc);
			// 流程节点描述-渠道
			String channel = flowInfo.getChannel();
			String channelDesc = flowInfo.getChannelDesc();
			if (StringUtils.isEmpty(channelDesc)) {
				channelDesc = channel;
			}

			flowNodeChannelMap.put(channel, channelDesc);
			if (async) {
				this.flowTree = new ParallelFlowTree(flowInfo, () -> flowEventRecordInfoService);
			} else {
				this.flowTree = new AsyncParallelFlowTree(flowInfo, () -> flowEventRecordInfoService);
			}
			return this;  // 移入方法体内
		}  // 补上闭合括号

		public FlowTreeBuilder successEndFlowTree( FlowInfo flowInfo, boolean async ) {
			// 流程节点描述-名称
			String name = flowInfo.getName();
			String nameDesc = flowInfo.getNamedesc();
			if (StringUtils.isEmpty(nameDesc)) {
				nameDesc = name;
			}
			flowNodeNameMap.put(name, nameDesc);
			// 流程节点描述-渠道
			String channel = flowInfo.getChannel();
			String channelDesc = flowInfo.getChannelDesc();
			if (StringUtils.isEmpty(channelDesc)) {
				channelDesc = channel;
			}
			flowNodeChannelMap.put(channel, channelDesc);
			if (async) {
				this.flowTree = new AsyncSuccessEndFlowTree(flowInfo, () -> flowEventRecordInfoService);
			} else {
				this.flowTree = new SuccessEndFlowTree(flowInfo, () -> flowEventRecordInfoService);
			}
			return this;
		}

		public FlowTreeBuilder successEndFlowTree( FlowInfo flowInfo ) {
			// 流程节点描述-名称
			String name = flowInfo.getName();
			String nameDesc = flowInfo.getNameDesc();
			if (StringUtils.isEmpty(nameDesc)) {
				nameDesc = name;
			}

			flowNodeNameMap.put(name, nameDesc);

			// 流程节点描述-渠道
			String channel = flowInfo.getChannel();
			String channelDesc = flowInfo.getChannelDesc();
			if (StringUtils.isEmpty(channelDesc)) {
				channelDesc = channel;
			}

			flowNodeChannelMap.put(channel, channelDesc);
			this.flowTree = new SuccessEndFlowTree(flowInfo, () -> flowEventRecordInfoService);
			return this;
		}

		public FlowTreeBuilder successFlowTree( FlowInfo flowInfo, boolean async ) {
			// 流程节点描述-名称
			String name = flowInfo.getName();
			String nameDesc = flowInfo.getNodeDesc();  // 修正
			if (StringUtils.isEmpty(nameDesc)) {
				nameDesc = name;
			}

			flowNodeNameMap.put(name, nameDesc);

			// 流程节点描述-渠道
			String channel = flowInfo.getChannel();
			String channelDesc = flowInfo.getChannelDesc();  // 修正
			if (StringUtils.isEmpty(channelDesc)) {
				channelDesc = channel;
			}

			flowNodeChannelMap.put(channel, channelDesc);
			if (async) {
				this.flowTree = new AsyncSuccessFlowTree(flowInfo, () -> flowEventRecordInfoService);
			} else {
				this.flowTree = new SuccessFlowTree(flowInfo, () -> flowEventRecordInfoService);
			}
			return this;
		}


		public FlowTreeBuilder successFlowTree( FlowInfo flowInfo ) {
			// 流程节点描述-名称
			String name = flowInfo.getName();
			String nameDesc = flowInfo.getNameDesc();
			if (StringUtils.isEmpty(nameDesc)) {
				nameDesc = name;
			}
			flowNodeNameMap.put(name, nameDesc);  // 移出 if 块

			// 流程节点描述-渠道
			String channel = flowInfo.getChannel();
			String channelDesc = flowInfo.getChannelDesc();
			if (StringUtils.isEmpty(channelDesc)) {
				channelDesc = channel;
			}
			flowNodeChannelMap.put(channel, channelDesc);  // 移出 if 块

			this.flowTree = new SuccessFlowTree(flowInfo, () -> flowEventRecordInfoService);
			return this;
		}

		public FlowTreeBuilder virtualFlow( FlowInfo flowInfo, String type, boolean async ) {
			// 流程节点描述-名称
			String name = flowInfo.getName();
			String nameDesc = flowInfo.getNodeDesc();  // 修正：使用正确的描述字段
			if (StringUtils.isEmpty(nameDesc)) {
				nameDesc = name;
			}

			flowNodeNameMap.put(name, nameDesc);
			// 流程节点描述-渠道
			String channel = flowInfo.getChannel();
			String channelDesc = flowInfo.getChannelDesc();  // 修正：使用正确的描述字段
			if (StringUtils.isEmpty(channelDesc)) {
				channelDesc = channel;
			}

			flowNodeChannelMap.put(channel, channelDesc);
			if (async) {
				this.flowTree = new AsyncVirtualFlow(flowInfo, () -> flowEventRecordInfoService, type);
			} else {
				this.flowTree = new VirtualFlow(flowInfo, () -> flowEventRecordInfoService, type);
			}
			return this;  // 移入方法体内
		}  // 补上闭合括号

		public FlowTreeBuilder virtualFlow( FlowInfo flowInfo, String type ) {
			// 流程节点描述-名称
			String name = flowInfo.getName();
			String nameDesc = flowInfo.getNodeDesc();
			if (StringUtils.isEmpty(nameDesc)) {
				nameDesc = name;
			}

			flowNodeNameMap.put(name, nameDesc);
			// 流程节点描述-渠道
			String channel = flowInfo.getChannel();
			String channelDesc = flowInfo.getChannelDesc();
			if (StringUtils.isEmpty(channelDesc)) {
				channelDesc = channel;
			}

			flowNodeChannelMap.put(channel, channelDesc);
			return this.virtualFlow(flowInfo, type, false);
		}

		public FlowTreeBuilder virtualFlow( FlowInfo flowInfo ) {
			// 流程节点描述-名称
			String name = flowInfo.getName();
			String nameDesc = flowInfo.getNodeDesc();
			if (StringUtils.isEmpty(nameDesc)) {
				nameDesc = name;
			}

			flowNodeNameMap.put(name, nameDesc);
			// 流程节点描述-渠道
			String channel = flowInfo.getChannel();
			String channelDesc = flowInfo.getChannelDesc();
			if (StringUtils.isEmpty(channelDesc)) {
				channelDesc = channel;
			}

			flowNodeChannelMap.put(channel, channelDesc);
			return this.virtualFlow(flowInfo, "tree", false);
		}

		/**
		 * condition
		 */
		public FlowTreeConditionBuilder success( Predicate<FlowContext> predicate, String desc ) {
			Objects.requireNonNull(predicate);
			return condition(flowContext -> flowContext.eventIsSuccess() && predicate.test(flowContext), predictDesc:
			"流程成功且" + desc);
		}

		public FlowTreeConditionBuilder success( Predicate<FlowContext> predicate ) {
			return success(predicate, "流程成功且其他条件满足");  // 修正
		}

		/**
		 * condition
		 */
		public FlowTreeConditionBuilder fail( Predicate<FlowContext> predicate, String desc ) {
			Objects.requireNonNull(predicate);
			return condition(
				flowContext -> flowContext.eventIsFail() && predicate.test(flowContext),
				"流程失败且" + desc  // 修正
			);
		}

		/**
		 * condition
		 */
		public FlowTreeConditionBuilder fail( Predicate<FlowContext> predicate ) {
			return fail(predicate, "流程失败且其他条件满足");  // 修正
		}

		/**
		 * condition
		 */
		public FlowTreeConditionBuilder condition( Predicate<FlowContext> predicate ) {
			return condition(predicate, "流程成功或失败且满足其他条件");  // 修正
		}

		/**
		 * condition
		 */
		public FlowTreeConditionBuilder condition( Predicate<FlowContext> predicate, String predictDesc ) {
			Objects.requireNonNull(predicate);  // 修正：移除 null()
			if (this.flowTree instanceof VirtualFlow) {
				throw new IllegalArgumentException(String.format("FlowRegister::condition,flowTree is virtual:%s",
					this.flowTree.name().flowIdentity()));
			}
			if (null != this.flowTree) {
				FlowTreeConditionBuilder flowTreeConditionBuilder = new FlowTreeConditionBuilder(this, predicate,
					predictDesc);  // 修正
				flowTreeConditionBuilderList.add(flowTreeConditionBuilder);
				return flowTreeConditionBuilder;
			}
			throw new IllegalArgumentException("(desc)FlowTreeBuilder condition, flowTree is null ");
		}

		/**
		 * 完成
		 */
		public FlowTreeConditionBuilder complete() {
			return condition(ctx -> ctx.getFlowEventRecordInfo().isComplete(), "流程成功或失败");  // 修正
		}

		/**
		 * 完成
		 */
		public FlowTreeConditionBuilder complete( Predicate<FlowContext> predicate, String desc ) {
			return condition(ctx -> ctx.getFlowEventRecordInfo().isComplete() && predicate.test(ctx),
				"流程成功或失败且" + desc);  // 修正
		}

		/**
		 * 成功
		 */
		public FlowTreeConditionBuilder success() {
			return condition(FlowContext::eventIsSuccess, "流程成功");  // 修正
		}

		/**
		 * 失败
		 */
		public FlowTreeConditionBuilder fail() {
			return condition(FlowContext::eventIsFail, "流程失败");  // 修正
		}

		/**
		 * parallel
		 */
		public FlowTreeParallelBuilder parallel( String rootFlow ) {
			if (this.flowTree != null && this.flowTree instanceof ParallelFlowTree) {
				FlowTreeParallelBuilder flowTreeParallelBuilder = new FlowTreeParallelBuilder(this);  // 修正
				flowTreeParallelBuilderList.add(flowTreeParallelBuilder);
				return flowTreeParallelBuilder;
			}
			throw new IllegalArgumentException(
				"FlowTreeBuilder parallel, flowTree is null or flowTree not instanceof ParallelFlowTree");
		}

		public FlowTreeBuilder build() {
			// parallel
			if (this.flowTree instanceof ParallelFlowTree) {
				for (FlowTreeParallelBuilder flowTreeParallelBuilder : flowTreeParallelBuilderList) {
					( (ParallelFlowTree) this.flowTree ).parallel(flowTreeParallelBuilder.getFlowTree());
				}
			}

			// condition
			if (this.flowTree == null) {
				throw new IllegalStateException("FlowTreeBuilder.build, flowTree is null");
			}
			for (FlowTreeConditionBuilder flowTreeConditionBuilder : flowTreeConditionBuilderList) {
				String predictDesc = flowTreeConditionBuilder.getPredictDesc();
				if (!StringUtils.isEmpty(predictDesc)) {
					flowTree.grow(flowTreeConditionBuilder.getPredicate(), predictDesc,
						flowTreeConditionBuilder.getFlowTree());
				} else {
					flowTree.grow(flowTreeConditionBuilder.getPredicate(), flowTreeConditionBuilder.getFlowTree());
				}
			}

			return this;
		}

		/**
		 * 向上回溯查找指定的流程节点
		 */
		public FlowTreeBuilder backTo( FlowInfo flowInfo ) {
			String targetIdentity = flowInfo.flowIdentity();
			FlowTreeBuilder upFlowTreeBuilder = this.build();
			if (null == upFlowTreeBuilder) {
				throw new IllegalArgumentException(
					String.format("FlowRegister::backTo,upFlowTreeBuilder is null,backToTarget:%s", targetIdentity));
			}

			FlowTree upFlowTree = upFlowTreeBuilder.getFlowTree();  // 使用 getter
			if (null == upFlowTree) {
				throw new IllegalArgumentException(
					String.format("FlowRegister::backTo,upFlowTree is null,backToTarget:%s", targetIdentity));
			}

			Flow firstNode = upFlowTree.firstNode();
			if (firstNode.name().flowIdentity().equals(targetIdentity)) {
				if (firstNode instanceof VirtualFlow) {
					throw new IllegalArgumentException(
						String.format("FlowRegister::backTo,upFlowTree is virtual,backToTarget:%s", targetIdentity));
				}
				return upFlowTreeBuilder;
			} else {
				return upFlowTreeBuilder.backTo(flowInfo);
			}
		}

		/**
		 *
		 */
		public FlowTree get() {
			return this.flowTree;
		}


	}

	/**
	 * FlowTreeConditionBuilder
	 */
	@Getter
	public class FlowTreeConditionBuilder extends FlowTreeBuilder {

		/**
		 * FlowTreeBuilder
		 */
		private FlowTreeBuilder parentFlowTreeBuilder;
		/**
		 * Predicate<List<FlowEventRecordInfo>>
		 */
		private Predicate<FlowContext> predicate;
		/**
		 * 条件描述
		 */
		private String predictDesc;

		/**
		 *
		 */
		private FlowTreeConditionBuilder( FlowTreeBuilder parentFlowTreeBuilder, Predicate<FlowContext> predicate,
			String predictDesc ) {
			this.parentFlowTreeBuilder = parentFlowTreeBuilder;
			this.predicate = predicate;
			this.predictDesc = predictDesc;
		}

		/**
		 *
		 */
		@Override
		public FlowTreeBuilder build() {
			super.build();
			return parentFlowTreeBuilder;
		}

	}

	/**
	 * FlowTreeParallelBuilder
	 */
	public class FlowTreeParallelBuilder extends FlowTreeBuilder {

		/**
		 * FlowTreeBuilder
		 */
		private FlowTreeBuilder parentFlowTreeBuilder;

		/**
		 *
		 */
		private FlowTreeParallelBuilder( FlowTreeBuilder parentFlowTreeBuilder ) {
			this.parentFlowTreeBuilder = parentFlowTreeBuilder;
		}

		/**
		 *
		 */
		@Override
		public FlowTreeBuilder build() {
			super.build();
			return parentFlowTreeBuilder;
		}
	}


}
