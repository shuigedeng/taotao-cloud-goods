

package com.taotao.cloud.goods.application.executor.category.cmmond;

import static com.taotao.boot.common.enums.CachePrefixEnum.CATEGORY;

import com.taotao.boot.cache.redis.repository.RedisRepository;
import com.taotao.cloud.goods.infrastructure.persistent.mapper.ICategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


/**
 * 删除部门执行器.
 */
@Component
@RequiredArgsConstructor
public class CategoryCacheDelCmdExe {

	private final RedisRepository redisRepository;
	private final ICategoryMapper categoryMapper;

	/**
	 * 清除缓存
	 */
	public void removeCache() {
		redisRepository.del(CATEGORY.getPrefix());
	}
}
