

package com.taotao.cloud.goods.application.executor.category.cmmond;

import static com.taotao.boot.common.enums.CachePrefixEnum.CATEGORY;

import com.taotao.boot.cache.redis.repository.RedisRepository;
import com.taotao.boot.ddd.model.application.executor.Executor;
import com.taotao.cloud.goods.infrastructure.persistent.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


/**
 * 删除部门执行器.
 */
@Component
@RequiredArgsConstructor
public class CategoryCacheDelCmdExe extends Executor {

	private final RedisRepository redisRepository;
	private final CategoryMapper categoryMapper;

	/**
	 * 清除缓存
	 */
	public void removeCache() {
		redisRepository.del(CATEGORY.getPrefix());
	}
}
