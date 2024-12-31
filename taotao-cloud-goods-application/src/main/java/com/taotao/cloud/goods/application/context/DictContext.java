package com.taotao.cloud.goods.application.context;

import com.taotao.cloud.goods.application.dto.dept.cmmond.DeptModifyCmd;
import com.taotao.cloud.goods.domain.dept.aggregate.DeptAgg;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DictContext {

	private DeptModifyCmd deptModifyCmd;

	private DeptAgg deptAgg;
}
