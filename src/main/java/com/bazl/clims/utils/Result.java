package com.bazl.clims.utils;

import java.io.Serializable;

public class Result implements Serializable{

	private static final long serialVersionUID = -1101923199593511398L;
	
	private long code;
	private Object data;
	private String errorMessage;
	
	public Result(){}
	
	public Result(long code, Object data, String errorMessage) {
		super();
		this.code = code;
		this.data = data;
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "Result{" +
				"code=" + code +
				", data=" + data +
				", errorMessage='" + errorMessage + '\'' +
				'}';
	}

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
