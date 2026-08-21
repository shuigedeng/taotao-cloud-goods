package com.taotao.cloud.goods.application.dto.goods.command;

import com.taotao.cloud.goods.domain.event.GoodsCreatedEvent;
import lombok.Builder;
import lombok.Data;

/**
 * 商品创建‑事件后置处理命令
 * <p>【仅内部事件专用】只能由 AuthChangeSpringEventListener 调用传入，禁止其他业务入口调用
 * </p>
 */
@Data
@Builder
public class GoodsCreatedHandleCommand {

    /**
     * 原始商品创建领域事件
     */
    private GoodsCreatedEvent goodsCreatedEvent;
}
