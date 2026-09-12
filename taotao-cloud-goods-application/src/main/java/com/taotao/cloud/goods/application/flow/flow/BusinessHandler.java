package com.taotao.cloud.goods.application.flow.flow;

import com.taotao.cloud.goods.application.flow.flow.helper.Info;
import com.taotao.cloud.goods.application.flow.flow.treeflow.context.FlowContext;

/**
 * BusinessHandler
 */
public interface BusinessHandler extends Info {

	/**
	 * 业务回调
	 *
	 * @param flowContext 流程上下文
	 */
	void callBack( FlowContext flowContext );

	/**
	 * 获取业务名称
	 *
	 * @return 业务名称
	 */
	String businessName();  // 移到接口内部
}
