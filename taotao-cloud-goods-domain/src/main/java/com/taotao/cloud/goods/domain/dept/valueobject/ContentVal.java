package com.taotao.cloud.goods.domain.dept.valueobject;

import com.alibaba.fastjson.JSON;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import org.springframework.util.StringUtils;

@Getter
public class ContentVal {
	private int tpl;
	private String content;

	private final static Map<MessageCategoryVal, Integer> TPL_MAPS;

	static {
		TPL_MAPS = new HashMap<>();
		TPL_MAPS.put(MessageCategoryVal.CREATED_ORDER, Tpl.CREATED_ORDER);
		TPL_MAPS.put(MessageCategoryVal.PAID, Tpl.PAID);
	}

	public ContentVal(MessageCategoryVal category, String content) {
		if (MessageCategoryVal.CHAT.sameValueAs(category)) {
			constructByParse(content);
		} else {
			constructByCategory(category, content);
		}
	}

	/**
	 * 通过分析content完成创建
	 *
	 * @param content
	 */
	private void constructByParse(String content) {
		//这里仅是简单判断，实际场景下根据业务需求处理，比如是否需要考虑支持在一个内容中匹配多个链接的情况
		boolean isLink = StringUtils.substringMatch(content, 0, "https://") || StringUtils.substringMatch(content, 0, "http://");
		if (isLink) {
			this.tpl = Tpl.LINK;
			Map<String, String> contentMap = new HashMap<>(2);
			contentMap.put("link", content);
			//通过接口或者网页抓取
			contentMap.put("title", "title");
			contentMap.put("describe", "describe");
			this.content = JSON.toJSONString(contentMap);
		} else {
			this.tpl = Tpl.TEXT;
			this.content = content;
		}
	}

	/**
	 * 通过分类创建消息内容
	 * 系统分类通常情况下，1个分类就是一个模板，比如创建订单、已付款等
	 *
	 * @param category
	 * @param content
	 */
	private void constructByCategory(MessageCategoryVal category, String content) {
		this.tpl = TPL_MAPS.get(category);
		this.content = content;
	}

	/**
	 * 模板ID
	 */
	public interface Tpl {
		int TEXT = 0;
		int LINK = 1;
		int CREATED_ORDER = 2;
		int PAID = 3;
	}
}
