package com.taotao.cloud.goods.application.flow.flow;

public class PlugException extends RuntimeException{
	public PlugException(String s, Exception e) {
		super(s,e);
	}
}
