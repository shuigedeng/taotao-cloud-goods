package com.taotao.cloud.goods.facade.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GatewayRequest<T> {
    private T param;
    private GatewayRecord gatewayRecord;
}
