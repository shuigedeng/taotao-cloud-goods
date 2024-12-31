
package com.taotao.cloud.goods.application.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taotao.boot.ddd.model.application.AppServiceImpl;
import com.taotao.cloud.goods.application.dto.dict.clientobject.DictCO;
import com.taotao.cloud.goods.application.dto.dict.clientobject.OptionCO;
import com.taotao.cloud.goods.application.dto.dict.commond.DictDeleteCmd;
import com.taotao.cloud.goods.application.dto.dict.commond.DictInsertCmd;
import com.taotao.cloud.goods.application.dto.dict.commond.DictUpdateCmd;
import com.taotao.cloud.goods.application.dto.dict.query.DictGetQry;
import com.taotao.cloud.goods.application.dto.dict.query.DictListQry;
import com.taotao.cloud.goods.application.dto.dict.query.DictOptionListQry;
import com.taotao.cloud.goods.application.executor.dict.commond.DictDeleteCmdExe;
import com.taotao.cloud.goods.application.executor.dict.commond.DictInsertCmdExe;
import com.taotao.cloud.goods.application.executor.dict.commond.DictUpdateCmdExe;
import com.taotao.cloud.goods.application.executor.dict.query.DictGetQryExe;
import com.taotao.cloud.goods.application.executor.dict.query.DictListQryExe;
import com.taotao.cloud.goods.application.executor.dict.query.DictOptionListQryExe;
import com.taotao.cloud.goods.application.service.DictCommandAppService;
import com.taotao.cloud.goods.application.service.DictQueryAppService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 字典管理.
 */
@Service
@RequiredArgsConstructor
public class DictQueryAppServiceImpl extends AppServiceImpl implements DictQueryAppService {

	private final DictInsertCmdExe dictInsertCmdExe;

	private final DictUpdateCmdExe dictUpdateCmdExe;

	private final DictDeleteCmdExe dictDeleteCmdExe;

	private final DictOptionListQryExe dictOptionListQryExe;

	private final DictGetQryExe dictGetQryExe;

	private final DictListQryExe dictListQryExe;

	/**
	 * 根据ID查看字典.
	 *
	 * @param qry 根据ID查看字典参数
	 * @return 字典
	 */
	@Override
	public DictCO getById(DictGetQry qry) {
		return dictGetQryExe.execute(qry);
	}

	/**
	 * 查询字典下拉框选择项列表.
	 *
	 * @param qry 查询字典下拉框选择项列表参数
	 * @return 字典下拉框选择项列表
	 */
	@Override
	public List<OptionCO> optionList(DictOptionListQry qry) {
		return dictOptionListQryExe.execute(qry);
	}

	/**
	 * 查询字典列表.
	 *
	 * @param qry 查询字典列表参数
	 * @return 字典列表
	 */
	@Override
	public IPage<DictCO> list(DictListQry qry) {
		return dictListQryExe.execute(qry);
	}


}
