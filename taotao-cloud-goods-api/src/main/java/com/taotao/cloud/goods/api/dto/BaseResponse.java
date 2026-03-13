package com.taotao.cloud.goods.api.dto;

import com.taotao.boot.common.model.ddd.types.MarkerResponse;
import lombok.Data;

import java.io.Serial;
import java.time.LocalDate;

/**
 * @author shuigedeng
 */
public final class BaseResponse {

	public static final String LIMIT_TYPE = "01";

	public static final class Status {

		public static final String P = "P";
		public static final String S = "S";
		public static final String F = "F";
		public static final String R = "R";
		public static final String N = "N";
	}

	public static final class CustStatus {

		public static final String A = "A";
		public static final String E = "E";
	}

	public static final class SubStatus {

		public static final String SUBMIT = "1";
		public static final String WAIT = "2";
		public static final String FAIL = "3";
		public static final String SUCCESS = "4";
	}

	public static final class StepStatus {

		public static final String P = "P";
		public static final String S = "S";
		public static final String F = "F";
	}

	public static final class CreditFlg {

		public static final String Y = "Y";
		public static final String N = "N";
	}

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
