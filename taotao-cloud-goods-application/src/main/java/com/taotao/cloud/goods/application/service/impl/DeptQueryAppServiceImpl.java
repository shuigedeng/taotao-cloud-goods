
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
import com.taotao.cloud.goods.application.service.DeptQueryAppService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 部门管理.
 */
@Service
@RequiredArgsConstructor
public class DeptQueryAppServiceImpl extends AppServiceImpl  implements DeptQueryAppService {


	private final DeptListQryExe deptListQryExe;

	private final DeptCreateCmdExe deptCreateCmdExe;

	private final DeptModifyCmdExe deptModifyCmdExe;

	private final DeptRemoveCmdExe deptRemoveCmdExe;

	private final DeptGetQryExe deptGetQryExe;

	private final DeptIdsGetQryExe deptIDSGetQryExe;

	/**
	 * 查询部门列表.
	 *
	 * @param qry 查询部门列表参数
	 * @return 部门列表
	 */
	@Override
	public List<DeptCO> findList(DeptListQry qry) {
		return deptListQryExe.execute(qry);
	}

	/**
	 * 根据ID查看部门.
	 *
	 * @param qry 根据ID查看部门参数
	 * @return 部门
	 */
	@Override
	public DeptCO findById(DeptGetQry qry) {
		return deptGetQryExe.execute(qry);
	}

	/**
	 * 根据角色ID查看部门IDS.
	 *
	 * @param qry 根据角色ID查看部门IDS参数
	 * @return 部门IDS
	 */
	@Override
	public List<Long> findIds(DeptIdsGetQry qry) {
		return deptIDSGetQryExe.execute(qry);
	}

}
