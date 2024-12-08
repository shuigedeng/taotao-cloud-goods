package com.taotao.cloud.goods.domain.dept.val;

import com.taotao.boot.ddd.model.domain.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MessageCategoryVal implements ValueObject<MessageCategoryVal> {
	/**
	 * 聊天消息
	 */
	CHAT(0),
	/**
	 * 新订单
	 */
	CREATED_ORDER(1),
	/**
	 * 买家已付款
	 */
	PAID(2);
	private final int code;

	/**
	 * 通过分类编码获取消息分类
	 *
	 * @param code
	 * @return
	 */
	public static MessageCategoryVal getByCode(int code) {
		for (MessageCategoryVal category : MessageCategoryVal.values()) {
			if (category.getCode() == code) {
				return category;
			}
		}
		return null;
	}

	@Override
	public boolean sameValueAs(MessageCategoryVal other) {
		return other != null && other.getCode() == this.code;
	}
}
