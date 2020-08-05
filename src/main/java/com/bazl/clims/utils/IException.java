package com.bazl.clims.utils;



/**
 * 协议参数接收异常
 */
public class IException extends Exception{



	private long perrorcode;
	private String perrormessage;
	
	public IException(long perrorcode){
		this.perrorcode = perrorcode;
		this.perrormessage = ErrorMsgManager.GetErrorMsg(perrorcode);
	}
	
	public IException(long perrorcode, String perrormessage){
		this.perrorcode = perrorcode;
		this.perrormessage = perrormessage;
	}

	

	public long getPerrorcode() {
		return perrorcode;
	}



	public void setPerrorcode(long perrorcode) {
		this.perrorcode = perrorcode;
	}



	public String getPerrormessage() {
		return perrormessage;
	}

	public void setPerrormessage(String perrormessage) {
		this.perrormessage = perrormessage;
	}
	
	
}
