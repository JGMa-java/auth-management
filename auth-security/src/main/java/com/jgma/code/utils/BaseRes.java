package com.jgma.code.utils;

import java.io.Serializable;

public class BaseRes implements Serializable {

	private static final long serialVersionUID = 5023007363066294795L;

	public int retCode;
	public String message;
	public Object data;
	
	public BaseRes() {
		this(RetCode.RET_OK,"",null);
	}
	
	public BaseRes(int retCode) {
		this(retCode, "", null);
	}
	
	public BaseRes(Object data) {
		this(RetCode.RET_OK, "", data);
	}
	
	public BaseRes(int retCode, String msg) {
		this(retCode, msg, null);
	}
	
	public BaseRes(int retCode, String message, Object data) {
		this.retCode = retCode;
		this.message = message;
		this.data = data;
	}

	public int getRetCode() {
		return retCode;
	}

	public void setRetCode(int retCode) {
		this.retCode = retCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static BaseRes ok(int retCode ,String msg) {
		BaseRes res = new BaseRes(retCode,msg);
		return res;
	}

	public static BaseRes ok() {
		BaseRes res = new BaseRes(RetCode.RET_OK,"success");
		return res;
	}
	public BaseRes put(Object o) {
		this.data = o;
		return this;
	}
	public static BaseRes err(int retCode ,String msg) {
		BaseRes res = new BaseRes(retCode,msg);
		return res;
	}
	public static BaseRes err(String msg) {
		BaseRes res = new BaseRes(RetCode.RET_ERROR,msg);
		return res;
	}
}