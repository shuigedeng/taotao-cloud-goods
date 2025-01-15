package com.taotao.cloud.goods.infrastructure.dataparam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taotao.boot.common.model.PageQuery;
import com.taotao.cloud.goods.infrastructure.persistent.persistence.BrandPO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
public class BrandPageParam extends PageQuery{

	//品牌名称
	private String name;


	public static BrandPageParamBuilder builder() {
		return new BrandPageParamBuilder();
	}

	public static final class BrandPageParamBuilder {

		private String name;
		private PageQuery pageQuery;

		private BrandPageParamBuilder() {
		}

		public BrandPageParamBuilder name(String name) {
			this.name = name;
			return this;
		}

		public BrandPageParamBuilder pageQuery(PageQuery pageQuery) {
			this.pageQuery = pageQuery;
			return this;
		}

		public BrandPageParam build() {
			BrandPageParam brandPageParam = new BrandPageParam();
			brandPageParam.setName(name);
			brandPageParam.setCurrentPage(pageQuery.getCurrentPage());
			brandPageParam.setPageSize(pageQuery.getPageSize());
			brandPageParam.setSort(pageQuery.getSort());
			brandPageParam.setOrder(pageQuery.getOrder());
			return brandPageParam;
		}
	}
}
