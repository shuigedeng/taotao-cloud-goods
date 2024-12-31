
package com.taotao.cloud.goods.application.service.impl;

import com.taotao.boot.ddd.model.application.AppServiceImpl;
import com.taotao.cloud.goods.application.dto.dept.clientobject.DeptCO;
import com.taotao.cloud.goods.application.dto.dept.cmmond.DeptCreateCmd;
import com.taotao.cloud.goods.application.dto.dept.cmmond.DeptModifyCmd;
import com.taotao.cloud.goods.application.dto.dept.cmmond.DeptRemoveCmd;
import com.taotao.cloud.goods.application.dto.dept.query.DeptGetQry;
import com.taotao.cloud.goods.application.dto.dept.query.DeptIdsGetQry;
import com.taotao.cloud.goods.application.dto.dept.query.DeptListQry;
import com.taotao.cloud.goods.application.executor.dept.cmmond.DeptCreateCmdExe;
import com.taotao.cloud.goods.application.executor.dept.cmmond.DeptModifyCmdExe;
import com.taotao.cloud.goods.application.executor.dept.cmmond.DeptRemoveCmdExe;
import com.taotao.cloud.goods.application.executor.dept.query.DeptGetQryExe;
import com.taotao.cloud.goods.application.executor.dept.query.DeptIdsGetQryExe;
import com.taotao.cloud.goods.application.executor.dept.query.DeptListQryExe;
import com.taotao.cloud.goods.application.service.DeptCommandAppService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 部门管理.
 */
@Service
@RequiredArgsConstructor
public class DeptCommandAppServiceImpl extends AppServiceImpl  implements DeptCommandAppService {


	private final DeptListQryExe deptListQryExe;

	private final DeptCreateCmdExe deptCreateCmdExe;

	private final DeptModifyCmdExe deptModifyCmdExe;

	private final DeptRemoveCmdExe deptRemoveCmdExe;

	private final DeptGetQryExe deptGetQryExe;

	private final DeptIdsGetQryExe deptIDSGetQryExe;

	/**
	 * 新增部门.
	 *
	 * @param cmd 新增部门参数
	 */
	@Override
	public void create(DeptCreateCmd cmd) {
		deptCreateCmdExe.executeVoid(cmd);
	}

	/**
	 * 修改部门.
	 *
	 * @param cmd 修改部门参数
	 */
	@Override
	public void modify(DeptModifyCmd cmd) {
		deptModifyCmdExe.executeVoid(cmd);
	}

	/**
	 * 根据ID删除部门.
	 *
	 * @param cmd 根据ID删除部门参数
	 */
	@Override
	public void remove(DeptRemoveCmd cmd) {
		deptRemoveCmdExe.executeVoid(cmd);
	}


}
