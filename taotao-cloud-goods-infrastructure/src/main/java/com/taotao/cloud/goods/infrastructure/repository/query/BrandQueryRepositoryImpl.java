package com.taotao.cloud.goods.infrastructure.repository.query;

import com.taotao.cloud.goods.application.dto.brand.clientobject.BrandCO;
import com.taotao.cloud.goods.application.service.query.repository.BrandQueryRepository;
import com.taotao.cloud.goods.infrastructure.assembler.BrandAssembler;
import com.taotao.cloud.goods.infrastructure.persistent.mapper.BrandMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BrandQueryRepositoryImpl implements BrandQueryRepository {
	private final BrandMapper brandMapper;

	@Override
	public BrandCO getById(Long id) {
		return BrandAssembler.INSTANCE.convert(brandMapper.selectById(id));
	}
}
