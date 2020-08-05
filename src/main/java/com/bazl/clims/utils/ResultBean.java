package com.bazl.clims.utils;

import java.io.Serializable;

/**
 * @author zzl
 * @date 2017-10-18
 * @version 1.0
 * @param
 */
public class ResultBean implements Serializable {

	private static final long serialVersionUID = -8363923199593511398L;

	public static final long CODE_SUCCESS = 1;
	public static final long CODE_ERROR = 0;
	public static final String NAME_SUCCESS = "成功";

	private long code;
	private long errorCode;
	private Object data;
	private String errorMessage;

	public ResultBean() {
	}

	public ResultBean(long code, long errorCode, Object data, String errorMessage) {
		super();
		this.code = code;
		this.errorCode = errorCode;
		this.data = data;
		this.errorMessage = errorMessage;
	}

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public long getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(long errorCode) {
		this.errorCode = errorCode;
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

	@Override
	public String toString() {
		return "ResultBean [code=" + code + ", errorCode=" + errorCode + ", data=" + data + ", errorMessage=" + errorMessage + "]";
	}

}
