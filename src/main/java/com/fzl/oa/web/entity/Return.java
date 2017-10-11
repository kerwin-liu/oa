package com.fzl.oa.web.entity;

public class Return {
	public static final int CODE_SUCCESS = 1;
	public static final int CODE_FAIL = 2;
	
	private int code;
	private String msg;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
