package com.taotao.cloud.goods.common.info;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class DictInfo {

	private Long id;

	/** 字典名称 */
	private String dictName;

	/** 字典编码 */
	private String dictCode;

	/** 描述 */
	private String description;

	/** 排序值 */
	private Integer sortNum;

	/** 备注信息 */
	private String remark;
}
