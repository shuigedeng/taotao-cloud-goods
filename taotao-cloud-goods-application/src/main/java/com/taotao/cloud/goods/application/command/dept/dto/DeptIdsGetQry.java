
package com.taotao.cloud.goods.application.command.dept.dto;

import com.taotao.boot.ddd.model.application.CommonCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "DeptIdsGetQry", description = "查看部门IDS命令请求")
public class DeptIdsGetQry extends CommonCommand {

	@Schema(name = "roleId", description = "角色ID")
	private Long roleId;

}
