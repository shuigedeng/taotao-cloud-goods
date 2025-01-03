

package com.taotao.cloud.goods.application.executor.category.cmmond;

import com.taotao.boot.cache.redis.repository.RedisRepository;
import com.taotao.cloud.goods.application.service.CategoryBrandCommandService;
import com.taotao.cloud.goods.application.service.CategoryParameterGroupCommandService;
import com.taotao.cloud.goods.application.service.CategorySpecificationCommandService;
import com.taotao.cloud.goods.infrastructure.persistent.mapper.ICategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


/**
 * 删除部门执行器.
 */
@Component
@RequiredArgsConstructor
public class CategoryDelCmdExe {

	private final RedisRepository redisRepository;
	private final ICategoryMapper categoryMapper;
	private final CategoryCacheDelCmdExe categoryCacheDelCmdExe;
	/**
	 * 分类绑定参数服务
	 */
	private final CategoryParameterGroupCommandService categoryParameterGroupService;
	/**
	 * 分类规格服务
	 */
	private final CategorySpecificationCommandService categorySpecificationService;
	/**
	 * 分类品牌服务
	 */
	private final CategoryBrandCommandService categoryBrandService;

	public boolean delete(Long id) {
		this.categoryMapper.deleteById(id);

		categoryCacheDelCmdExe.removeCache();

		// 删除关联关系
		categoryParameterGroupService.deleteByCategoryId(id);
		categorySpecificationService.deleteByCategoryId(id);
		return categoryBrandService.deleteByCategoryId(id);
	}
}
