package com.taotao.cloud.goods.api.dto;

import com.taotao.boot.common.model.ddd.types.MarkerResponse;
import com.taotao.boot.common.model.ddd.types.Query;
import lombok.Data;

import java.io.Serial;
import java.time.LocalDate;

/**
 * @author shuigedeng
 */
public final class BaseQuery {


	@Data
	public static class CreditLimitInfo implements Query {

		@Serial
		private static final long serialVersionUID = -4132785717179910025L;
		private LocalDate startDate;
		private LocalDate endDate;
	}

	@Data
	public static class AdjustLimitInfo implements Query {

		@Serial
		private static final long serialVersionUID = -4132785717179910025L;
		private LocalDate startDate;
		private LocalDate endDate;
	}

	@Data
	public static class CapitalInfo implements Query {

		@Serial
		private static final long serialVersionUID = -4132785717179910025L;
		private LocalDate startDate;
		private LocalDate endDate;
	}
}
