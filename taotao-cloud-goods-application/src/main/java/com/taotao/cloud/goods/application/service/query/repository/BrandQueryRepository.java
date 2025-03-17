package com.taotao.cloud.goods.application.service.query.repository;

import com.taotao.cloud.goods.application.dto.brand.clientobject.BrandCO;

public interface BrandQueryRepository {

	BrandCO getById(Long id);

}
