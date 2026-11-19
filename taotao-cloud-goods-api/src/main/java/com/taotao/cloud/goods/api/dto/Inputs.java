package com.taotao.cloud.goods.api.dto;

import com.taotao.boot.common.model.ddd.types.Input;
import lombok.Data;

import java.io.Serial;
import java.time.LocalDate;

/**
 * @author shuigedeng
 */
public final class Inputs {

	@Data
	public static class CreditLimitInput implements Input {

		@Serial
		private static final long serialVersionUID = -4132785717179910025L;
		private LocalDate startDate;
		private LocalDate endDate;
	}

	@Data
	public static class AdjustLimitInput implements Input {

		@Serial
		private static final long serialVersionUID = -4132785717179910025L;
		private LocalDate startDate;
		private LocalDate endDate;
	}

	@Data
	public static class CapitalInput implements Input {

		@Serial
		private static final long serialVersionUID = -4132785717179910025L;
		private LocalDate startDate;
		private LocalDate endDate;
	}
}
