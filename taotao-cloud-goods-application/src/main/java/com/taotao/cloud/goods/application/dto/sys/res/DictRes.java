package com.taotao.cloud.goods.application.dto.sys.res;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * DictRes
 *
 * @author shuigedeng
 * @version 2026.01
 * @since 2025-12-19 09:30:45
 */
@Setter
@Getter
@ToString
public class DictRes {

    private Long id;

    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 字典编码
     */
    private String dictCode;

    /**
     * 描述
     */
    private String description;

    /**
     * 排序值
     */
    private Integer sortNum;

    /**
     * 备注信息
     */
    private String remark;
}
