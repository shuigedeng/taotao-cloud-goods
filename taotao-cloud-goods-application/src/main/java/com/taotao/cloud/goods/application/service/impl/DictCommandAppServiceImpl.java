
package com.taotao.cloud.goods.application.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taotao.boot.ddd.model.application.AppServiceImpl;
import com.taotao.cloud.goods.application.dto.dict.clientobject.DictCO;
import com.taotao.cloud.goods.application.dto.dict.clientobject.OptionCO;
import com.taotao.cloud.goods.application.dto.dict.commond.DictDeleteCmd;
import com.taotao.cloud.goods.application.dto.dict.query.DictGetQry;
import com.taotao.cloud.goods.application.dto.dict.commond.DictInsertCmd;
import com.taotao.cloud.goods.application.dto.dict.query.DictListQry;
import com.taotao.cloud.goods.application.dto.dict.query.DictOptionListQry;
import com.taotao.cloud.goods.application.dto.dict.commond.DictUpdateCmd;
import com.taotao.cloud.goods.application.executor.dict.commond.DictDeleteCmdExe;
import com.taotao.cloud.goods.application.executor.dict.commond.DictInsertCmdExe;
import com.taotao.cloud.goods.application.executor.dict.commond.DictUpdateCmdExe;
import com.taotao.cloud.goods.application.executor.dict.query.DictGetQryExe;
import com.taotao.cloud.goods.application.executor.dict.query.DictListQryExe;
import com.taotao.cloud.goods.application.executor.dict.query.DictOptionListQryExe;
import com.taotao.cloud.goods.application.service.DictCommandAppService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 字典管理.
 */
@Service
@RequiredArgsConstructor
public class DictCommandAppServiceImpl extends AppServiceImpl implements DictCommandAppService {

	private final DictInsertCmdExe dictInsertCmdExe;

	private final DictUpdateCmdExe dictUpdateCmdExe;

	private final DictDeleteCmdExe dictDeleteCmdExe;

	private final DictOptionListQryExe dictOptionListQryExe;

	private final DictGetQryExe dictGetQryExe;

	private final DictListQryExe dictListQryExe;

	/**
	 * 新增字典.
	 *
	 * @param cmd 新增字典参数
	 * @return 新增结果
	 */
	@Override
	public Boolean insert(DictInsertCmd cmd) {
		return dictInsertCmdExe.execute(cmd);
	}

	/**
	 * 修改字典.
	 *
	 * @param cmd 修改字典参数
	 * @return 修改结果
	 */
	@Override
	public Boolean update(DictUpdateCmd cmd) {
		return dictUpdateCmdExe.execute(cmd);
	}

	/**
	 * 根据ID删除字典.
	 *
	 * @param cmd 根据ID删除字典参数
	 * @return 删除字典
	 */
	@Override
	public Boolean deleteById(DictDeleteCmd cmd) {
		return dictDeleteCmdExe.execute(cmd);
	}

}
