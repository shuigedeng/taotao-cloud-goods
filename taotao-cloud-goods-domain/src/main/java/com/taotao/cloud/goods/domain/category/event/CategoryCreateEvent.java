

package com.taotao.cloud.goods.domain.category.event;

import com.taotao.boot.ddd.model.domain.event.DomainEvent;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * 
 */
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "OperateLogEvent", description = "操作日志事件")
public class CategoryCreateEvent extends DomainEvent<Long> {

	@Serial
	private static final long serialVersionUID = -6523521638764501311L;

	@Schema(name = "name", description = "操作名称")
	private String name;

}
