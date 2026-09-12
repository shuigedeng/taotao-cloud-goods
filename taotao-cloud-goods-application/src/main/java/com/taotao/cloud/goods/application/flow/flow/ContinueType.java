package com.taotao.cloud.goods.application.flow.flow;

import org.apache.commons.lang3.StringUtils;

/**
 * ContinueType
 */
public enum ContinueType {
	END, // 流程终止
	CONTINUE, // 流程继续
	RETRY; // 节点重试

	public static ContinueType valueOfName(String name) {
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
};
