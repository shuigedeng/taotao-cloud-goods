
package com.taotao.cloud.goods.application.command.dept.dto;

import com.taotao.boot.ddd.model.application.CommonCommand;
import com.taotao.cloud.goods.application.command.dept.dto.clientobject.DeptCO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "DeptUpdateCmd", description = "修改部门命令请求")
public class DeptModifyCmd extends CommonCommand {

	@Schema(name = "deptCO", description = "部门")
	private DeptCO deptCO;

}
