/*
 * Copyright (c) 2020-2030, Shuigedeng (981376577@qq.com & https://blog.taotaocloud.top/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.taotao.cloud.goods.application.service;

import com.taotao.boot.ddd.model.application.service.CommandService;
import com.taotao.cloud.goods.application.dto.goods.cmmond.CustomWordsAddCmd;
import com.taotao.cloud.goods.application.dto.goods.cmmond.CustomWordsUpdateCmd;
import com.taotao.cloud.goods.infrastructure.persistent.persistence.CustomWordsPO;

import java.util.List;

/**
 * 自定义分词业务层
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 16:59:50
 */
public interface CustomWordsCommandService  extends CommandService {

	/**
	 * 自定义分词部署替换
	 *
	 * @return 替换的内容
	 */
	String deploy();


	/**
	 * 添加自定义分词
	 *
	 * @param customWordsAddCmd 自定义分词信息
	 * @return 是否添加成功
	 */
	boolean addCustomWords(CustomWordsAddCmd customWordsAddCmd);


	/**
	 * 修改自定义分词
	 *
	 * @param customWordsUpdateCmd 自定义分词信息
	 * @return 是否修改成功
	 */
	boolean updateCustomWords(CustomWordsUpdateCmd customWordsUpdateCmd);

	/**
	 * 删除自定义分词
	 *
	 * @param id 自定义分词id
	 * @return 是否删除成功
	 */
	boolean deleteCustomWords(String id);

	/**
	 * 根据名字批量删除
	 *
	 * @param names 名称列表
	 * @return 是否删除成功
	 */
	boolean deleteBathByName(List<String> names);

	/**
	 * 批量插入自定义分词
	 *
	 * @param customWordsPOList 自定义分词列表
	 * @return 受影响行数
	 */
	long insertBatchCustomWords(List<CustomWordsPO> customWordsPOList);


}
