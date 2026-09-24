package com.taotao.cloud.goods.common.enums;

import static com.taotao.cloud.goods.common.constant.GoodsConstants.CATEGORY_CODE;
import static com.taotao.cloud.goods.common.constant.GoodsConstants.SYSTEM_CODE;

import com.taotao.boot.common.enums.base.CodeEnum;
import com.taotao.boot.common.enums.base.CommonEnum;
import com.taotao.boot.common.model.Code;

public enum ErrorCodeEnum implements CodeEnum {

	NOTICE_NOT_EXIST(code("514001"), "当前消息模板不存在"),
	NOTICE_ERROR(code("514002"), "修改站内信异常，请稍后重试"),
	NOTICE_SEND_ERROR(code("514003"), "发送站内信异常，请检查系统日志");

	private static Code code(String code) {
		return Code.code(SYSTEM_CODE, CATEGORY_CODE).apply(code);
	}

	;
	private final Code code;

	private final String desc;

	ErrorCodeEnum(Code code, String desc) {
		this.code = code;
		this.desc = desc;
	}


	@Override
	public Code coder() {
		return code;
	}

	@Override
	public String code() {
		return code.code();
	}

	@Override
	public String desc() {
		return desc;
	}

	@Override
	public String value() {
		return "";
	}
}
