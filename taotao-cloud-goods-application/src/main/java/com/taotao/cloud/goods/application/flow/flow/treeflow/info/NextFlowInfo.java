package com.taotao.cloud.goods.application.flow.flow.treeflow.info;

import com.taotao.cloud.goods.application.flow.flow.treeflow.flow.Flow;
import lombok.Data;

@Data
public class NextFlowInfo {
    /**
     * mark
     */
    private static final String END = "END";
    private static final String NO_END = "NO_END";

    /**
     * Flow
     */
    private Flow nextFlow;

    /**
     * mark
     */
    private String mark;

    /**
     * @return
     */
    public boolean isEnd() { return mark.equals(END); }

    /**
     * @param flow
     * @return
     */
    public NextFlowInfo nextFlow(Flow flow) {
        this.mark = NO_END;
        this.nextFlow = flow;
        return this;
    }
	/**
	 * @return
	 */
	public NextFlowInfo end() {
		this.mark = END;
		return this;
	}

	/**
	 * @return
	 */
	public NextFlowInfo noEnd() {
		this.mark = NO_END;
		return this;
	}
}
