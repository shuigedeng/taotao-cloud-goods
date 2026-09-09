package com.taotao.cloud.goods.application.flow.flow;

import lombok.Data;

@Data
public class FlowRegisterInfo {
    /**
     * businessType
     */
    private String businessType;
    /**
     * businessSubType
     */
    private String businessSubType;
    /**
     * businessCode
     */
    private String businessCode;
    /**
     * businessTypeDesc
     */
    private String businessTypeDesc;
    /**
     * businessSubTypeDesc
     */
    private String businessSubTypeDesc;
    /**
     * businessCodeDesc
     */
    private String businessCodeDesc;

	/**
	 * @param businessType
	 * @param businessSubType
	 */
	public FlowRegisterInfo(String businessType, String businessSubType) {
		this.businessType = businessType;
		this.businessSubType = businessSubType;
		this.businessCode = FlowRegister.BUSINESS_CODE_COMMON;
	}

	/**
	 * @param businessType
	 * @param businessSubType
	 * @param businessCode
	 */
	public FlowRegisterInfo(String businessType, String businessSubType, String businessCode) {
		this.businessType = businessType;
		this.businessSubType = businessSubType;
		this.businessCode = businessCode;
	}

	/**
	 * @param businessType
	 */
	public FlowRegisterInfo(String businessType) {
		this.businessType = businessType;
		this.businessSubType = FlowRegister.BUSINESS_SUB_TYPE_COMMON;
		this.businessCode = FlowRegister.BUSINESS_CODE_COMMON;
	}
	/**
	 * FlowRegisterInfo
	 */
	private FlowRegisterInfo() {}

	public static FlowRegisterInfo valueOf(String... str) {
		FlowRegisterInfo flowRegisterInfo = new FlowRegisterInfo();
		flowRegisterInfo.businessType = str[0];
		if (str.length == 1) {
			flowRegisterInfo.businessSubType = FlowRegister.BUSINESS_SUB_TYPE_COMMON;
			flowRegisterInfo.businessCode = FlowRegister.BUSINESS_CODE_COMMON;
			return flowRegisterInfo;
		}

		if (str.length == 2) {
			flowRegisterInfo.businessTypeDesc = str[1];
			flowRegisterInfo.businessSubType = FlowRegister.BUSINESS_SUB_TYPE_COMMON;
			flowRegisterInfo.businessCode = FlowRegister.BUSINESS_CODE_COMMON;
			return flowRegisterInfo;
		}

		if (str.length == 3) {
			flowRegisterInfo.businessTypeDesc = str[1];
			flowRegisterInfo.businessSubType = str[2];
			flowRegisterInfo.businessCode = FlowRegister.BUSINESS_CODE_COMMON;
			return flowRegisterInfo;
		}

		if (str.length == 4) {
			flowRegisterInfo.businessTypeDesc = str[1];
			flowRegisterInfo.businessSubType = str[2];
			flowRegisterInfo.businessCode = FlowRegister.BUSINESS_CODE_COMMON;
			return flowRegisterInfo;
		}
		if (str.length == 5) {
			flowRegisterInfo.businessTypeDesc = str[1];
			flowRegisterInfo.businessSubType = str[2];
			flowRegisterInfo.businessTypeDesc = str[3];
			flowRegisterInfo.businessCode = str[4];
			return flowRegisterInfo;
		}

		flowRegisterInfo.businessTypeDesc = str[1];
		flowRegisterInfo.businessSubType = str[2];
		flowRegisterInfo.businessTypeDesc = str[3];
		flowRegisterInfo.businessCode = str[4];
		flowRegisterInfo.businessCodeDesc = str[5];
		return flowRegisterInfo;
	}
	public static FlowRegisterInfo registerInfo(String businessType, String businessSubType, String businessCode) {
		FlowRegisterInfo flowRegisterInfo = new FlowRegisterInfo();
		flowRegisterInfo.businessType = businessType;
		flowRegisterInfo.businessSubType = businessSubType;
		flowRegisterInfo.businessCode = businessCode;
		return flowRegisterInfo;
	}

	public static FlowRegisterInfo registerInfo(String businessType, String businessSubType) {
		FlowRegisterInfo flowRegisterInfo = new FlowRegisterInfo();
		flowRegisterInfo.businessType = businessType;
		flowRegisterInfo.businessSubType = businessSubType;
		flowRegisterInfo.businessCode = FlowRegister.BUSINESS_CODE_COMMON;
		return flowRegisterInfo;
	}

	public static FlowRegisterInfo registerInfo(String businessType) {
		FlowRegisterInfo flowRegisterInfo = new FlowRegisterInfo();
		flowRegisterInfo.businessType = businessType;
		flowRegisterInfo.businessSubType = FlowRegister.BUSINESS_SUB_TYPE_COMMON;
		flowRegisterInfo.businessCode = FlowRegister.BUSINESS_CODE_COMMON;
		return flowRegisterInfo;
	}
}
