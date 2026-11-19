package com.taotao.cloud.goods.api.dto;

import com.taotao.boot.common.model.ddd.types.Info;

/**
 * @author shuigedeng
 */
public final class Infos {

	public static final String LIMIT_TYPE = "01";

	public static final class Status implements Info {

		public static final String P = "P";
		public static final String S = "S";
		public static final String F = "F";
		public static final String R = "R";
		public static final String N = "N";
	}

	public static final class CustStatus implements Info {

		public static final String A = "A";
		public static final String E = "E";
	}

	public static final class SubStatus implements Info {

		public static final String SUBMIT = "1";
		public static final String WAIT = "2";
		public static final String FAIL = "3";
		public static final String SUCCESS = "4";
	}

	public static final class StepStatus implements Info {

		public static final String P = "P";
		public static final String S = "S";
		public static final String F = "F";
	}

	public static final class CreditFlg implements Info {

		public static final String Y = "Y";
		public static final String N = "N";
	}

}
