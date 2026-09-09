package com.taotao.cloud.goods.application.flow.flow;

import lombok.Data;

@Data
public class FlowInfo {
    /**
     * 名称
     */
    private String name;
    /**
     * 渠道
     */
    private String channel;
    /**
     * 名称描述
     */
    private String nameDesc;
    /**
     * 渠道描述
     */
    private String channelDesc;

    /**
     * @param name
     * @param channel
     */
    public FlowInfo(String name, String channel) {
        this.name = name;
        this.channel = channel;
    }

	/**
	 * flowIdentity
	 * @return
	 */
	public String flowIdentity() {
		return this.name + "-" + this.channel;
	}

	/**
	 * @param name
	 * @param channel
	 * @return
	 */
	public static FlowInfo node(String name, String channel) {
		return new FlowInfo(name, channel);
	}
}
