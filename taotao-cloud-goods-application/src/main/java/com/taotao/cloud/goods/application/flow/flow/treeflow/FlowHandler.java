package com.taotao.cloud.goods.application.flow.flow.treeflow;

import com.taotao.cloud.goods.application.flow.flow.treeflow.context.FlowContext;
import com.taotao.cloud.goods.application.flow.flow.treeflow.info.FlowEventRecordInfo;
import com.taotao.cloud.goods.application.flow.flow.treeflow.info.FlowHandlerInfo;
import com.taotao.cloud.goods.application.flow.flow.helper.Info;
import com.taotao.cloud.goods.application.flow.flow.helper.Table;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class FlowHandler {
    /**
     * FlowCenter
     */
    private FlowCenter flowCenter;

    /**
     * tradeTable
     */
    private Map<String, Table<Table>> tradeTable = new HashMap<>();  // 修正泛型

    /**
     * @param flowCenter
     */
    public FlowHandler(FlowCenter flowCenter) {
        this.flowCenter = flowCenter;
        this.flowCenter.registerFlowHandler(this);
    }


    /**
     * @param flowContext
     */
    public void handle(FlowContext flowContext) {
        FlowEventRecordInfo flowEventRecordInfo = flowContext.getFlowEventRecordInfo();
        FlowHandlerInfo flowHandlerInfo = new FlowHandlerInfo();
        flowHandlerInfo.setBusinessType(flowEventRecordInfo.getBusinessType());
        flowHandlerInfo.setBusinessSubType(flowEventRecordInfo.getBusinessSubType());
        flowHandlerInfo.setBusinessCode(flowEventRecordInfo.getBusinessCode());
        flowHandlerInfo.setFlowEvent(flowEventRecordInfo.getCurrentFlow().getName());
        Handler handler = this.getHandler(flowHandlerInfo);
        handler.handle(flowContext);
    }

	public Handler getHandler(FlowHandlerInfo flowHandlerInfo) {
		// 1. 精确匹配
		Handler handler = this.doGet(flowHandlerInfo);
		if (null != handler) {
			return handler;
		}

		FlowHandlerInfo fallbackInfo = new FlowHandlerInfo();
		fallbackInfo.setBusinessType(flowHandlerInfo.getBusinessType());
		fallbackInfo.setFlowEvent(flowHandlerInfo.getFlowEvent());
		// 2. 业务子类型降级为 COMMON
		fallbackInfo.setBusinessSubType(FlowRegister.BUSINESS_SUB_TYPE_COMMON);
		fallbackInfo.setBusinessCode(flowHandlerInfo.getBusinessCode());
		handler = this.doGet(fallbackInfo);
		if (null != handler) {
			return handler;
		}

		// 3. 业务编码降级为 COMMON
		fallbackInfo.setBusinessSubType(flowHandlerInfo.getBusinessSubType());
		fallbackInfo.setBusinessCode(FlowRegister.BUSINESS_CODE_COMMON);
		handler = this.doGet(fallbackInfo);
		if (null != handler) {
			return handler;
		}

		// 4. 全降级
		fallbackInfo.setBusinessSubType(FlowRegister.BUSINESS_SUB_TYPE_COMMON);
		fallbackInfo.setBusinessCode(FlowRegister.BUSINESS_CODE_COMMON);
		return this.doGet(fallbackInfo);
	}

	private Handler doGet(FlowHandlerInfo flowHandlerInfo) {
		String businessType = flowHandlerInfo.getBusinessType();
		Table<Table> businessTypeTable = this.tradeTable.get(businessType);
		if (null == businessTypeTable) {
			return null;
		}

		String businessSubType = flowHandlerInfo.getBusinessSubType();
		Table<Table> businessSubTypeTable = businessTypeTable.get(businessSubType);
		if (null == businessSubTypeTable) {
			return null;
		}

		String businessCode = flowHandlerInfo.getBusinessCode();
		Table<Handler> businessCodeTable = businessSubTypeTable.get(businessCode);
		if (null == businessCodeTable) {
			return null;
		}

		String flowEvent = flowHandlerInfo.getFlowEvent();
		return businessCodeTable.get(flowEvent);
	}

	/**
	 * 获取渠道名称
	 */
	public abstract String channelName();

	/**
	 * 回调处理
	 */
	public void callback(FlowContext flowContext) {
		// 异步响应回调
		flowCenter.asyncResponse(flowContext);  // 修正命名
	}

	public void register(FlowHandlerInfo flowHandlerInfo, Handler handler) {  // 重命名避免歧义
		Objects.requireNonNull(flowHandlerInfo);
		Objects.requireNonNull(handler);

		String businessType = flowHandlerInfo.getBusinessType();
		if (StringUtils.isEmpty(businessType)) {
			throw new IllegalArgumentException("FlowHandler register businessType is empty");
		}
		String businessSubType = flowHandlerInfo.getBusinessSubType();
		if (StringUtils.isEmpty(businessSubType)) {
			throw new IllegalArgumentException("FlowHandler register businessSubType is empty");
		}
		String businessCode = flowHandlerInfo.getBusinessCode();
		if (StringUtils.isEmpty(businessCode)) {
			throw new IllegalArgumentException("FlowHandler register businessCode is empty");
		}
		String flowEvent = flowHandlerInfo.getFlowEvent();
		if (StringUtils.isEmpty(flowEvent)) {
			throw new IllegalArgumentException("FlowHandler register flowEvent is empty");
		}

		// businessTypeTable
		Table<Table> businessTypeTable = this.tradeTable.computeIfAbsent(businessType,
			k -> new Table<>(FlowRegister.BUSINESS_TYPE));
		// businessSubTypeTable
		Table<Table> businessSubTypeTable = businessTypeTable.add(businessSubType,
			new Table<>(FlowRegister.BUSINESS_SUB_TYPE));  // 修正泛型
		// businessCodeTable
		Table<Handler> businessCodeTable = businessSubTypeTable.add(businessCode,
			new Table<>(FlowRegister.BUSINESS_CODE));  // 修正泛型
		// businessEventHandler
		businessCodeTable.add(flowEvent, handler);
	}

	/**
	 * 根据记录ID和分库编号获取流程事件记录信息
	 *
	 * @param recordId 记录ID
	 * @param splitNo 分库编号
	 * @return FlowEventRecordInfo 或 null
	 */
	public FlowEventRecordInfo getFlowEventRecordInfo(String recordId, String splitNo) {
		if (StringUtils.isEmpty(recordId)) {
			return null;
		}
		if (null == flowCenter) {
			throw new IllegalStateException("flowCenter is not initialized");
		}
		return flowCenter.getFlowEventRecordInfo(recordId, splitNo);
	}

	/**
	 * 获取 FlowCenter 实例
	 *
	 * @return FlowCenter
	 */
	public FlowCenter flowCenter() {
		return this.flowCenter;
	}

	/**
	 * 获取 tradeTable 的摘要信息
	 *
	 * @return 摘要字符串
	 */
	public String info() {
		if (null == tradeTable || tradeTable.isEmpty()) {
			return "empty";
		}
		StringBuilder sb = new StringBuilder();
		tradeTable.forEach((k, v) -> {
			if (null != v) {
				sb.append(k).append(":").append(v.info()).append(";");
			}
		});
		return sb.toString();
	}

	/**
	 * Handler
	 */
	public interface Handler extends Info {
		/**
		 * @param flowContext
		 */
		void handle(FlowContext flowContext);
	}
}
