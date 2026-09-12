package com.taotao.cloud.goods.application.flow.flow.treeflow.info;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class FlowTreeFindInfo extends FlowRegisterInfo {
    /**
     * @param businessType
     * @param businessSubType
     * @param businessCode
     */
    public FlowTreeFindInfo(String businessType, String businessSubType, String businessCode) {
        super(businessType, businessSubType, businessCode);
    }
}
