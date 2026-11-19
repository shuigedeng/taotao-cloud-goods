package com.taotao.cloud.goods.api.dto;

import com.taotao.boot.common.model.ddd.types.MarkerResponse;
import lombok.Data;

import java.io.Serial;
import java.time.LocalDate;

/**
 * @author shuigedeng
 */
public final class BaseResponse {

	@Data
	public static class CreditLimitInfo implements MarkerResponse {

		@Serial
		private static final long serialVersionUID = -4132785717179910025L;
		private LocalDate startDate;
		private LocalDate endDate;
	}

	@Data
	public static class AdjustLimitInfo implements MarkerResponse {

		@Serial
		private static final long serialVersionUID = -4132785717179910025L;
		private LocalDate startDate;
		private LocalDate endDate;
	}

	@Data
	public static class CapitalInfo implements MarkerResponse {

		@Serial
		private static final long serialVersionUID = -4132785717179910025L;
		private LocalDate startDate;
		private LocalDate endDate;
	}
}
