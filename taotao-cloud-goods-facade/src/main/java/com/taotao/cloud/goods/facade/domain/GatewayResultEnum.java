package com.taotao.cloud.goods.facade.domain;

public enum GatewayResultEnum{
    GATEWAY_ERROR("F900001","网关异常"),
    /*客户系统*/
    AUTH_C0DE_ERROR("301007","短信验证码错误"),
    AUTH_303001(  "303001", "身份证影像模糊,请重新上传"),
    AUTH_303002("303002","证件照片对比失败,请重新上传"),
    AUTH_303003("303003","人脸活体照片对比失败,请重试"),
    AUTH_303004(  "303004", "身份证影像模糊,请重新上传"),
    AUTH_303005("C0E_200401_511","证件已过期"),
    AUTH_303006("C0E_200409_000010","商户号和商户侧会员号关系已存在"),
    AUTH_303007("C0E_200409_010002","该商户下注册账号已达到最大注册数量"),
	;
	private String code;
    private String desc;
    GatewayResultEnum( String code, String desc ) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
