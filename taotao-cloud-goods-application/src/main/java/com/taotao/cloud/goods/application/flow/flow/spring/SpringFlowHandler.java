package com.taotao.cloud.goods.application.flow.flow.spring;

import com.taotao.cloud.goods.application.flow.flow.FlowCenter;
import com.taotao.cloud.goods.application.flow.flow.FlowEventInfo;
import com.taotao.cloud.goods.application.flow.flow.FlowHandler;
import com.taotao.cloud.goods.application.flow.flow.FlowHandlerInfo;
import org.eclipse.jdt.internal.compiler.flow.FlowInfo;

@Slf4j
public class SpringFlowHandler {
    private final Map<String, FlowHandler> flowHandlerMap = new ConcurrentHashMap<>();
    private final FlowCenter flowCenter;

    public SpringFlowHandler(FlowCenter flowCenter) {
        this.flowCenter = flowCenter;
    }

    public void registerProxy(FlowHandlerInfoProxy proxy, FlowHandler.Handler handler) {
        String channel = proxy.getChannel();
        FlowHandler flowHandler = flowHandlerMap.computeIfAbsent(channel, ch -> {
            FlowHandler newHandler = new FlowHandler(flowCenter) {
                @Override
                public String channelName() {
                    return ch;
                }
            };
            flowCenter.registerFlowHandler(newHandler);  // 注册到 FlowCenter
            log.info("SpringFlowHandler created FlowHandler for channel: {}", ch);
            return newHandler;
        });

        List<FlowHandlerInfo> infoList = proxy.getFlowHandlerInfoList();
        if (null != infoList && !infoList.isEmpty()) {
            for (FlowHandlerInfo info : infoList) {
                flowHandler.register(info, handler);
                log.info("SpringFlowHandler registered handler for: {}", info);
            }
        }
    }

	public FlowHandler.Handler getFlowHandler( FlowEventInfo flowEventInfo) {
		FlowInfo currentFlow = flowEventInfo.getCurrentFlow();
		String channel = currentFlow.getChannel();
		FlowHandler flowHandler = flowHandlerMap.get(channel);
		if (null == flowHandler) {
			return null;
		}

		FlowHandlerInfo flowHandlerInfo = new FlowHandlerInfo();
		flowHandlerInfo.setBusinessType(flowEventInfo.getBusinessType());
		flowHandlerInfo.setBusinessSubType(flowEventInfo.getBusinessSubType());
		flowHandlerInfo.setBusinessCode(flowEventInfo.getBusinessCode());
		flowHandlerInfo.setFlowEvent(currentFlow.getName());
		return flowHandler.getHandler(flowHandlerInfo);
	}
}
