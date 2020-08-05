package com.bazl.clims.utils;

/**
 * @author
 * @date
 * @version 1.0
 */
public class ErrorMsgManager {

	public static String GetErrorMsg(long errorCode) {
		String _errorMsg = "";

		if (errorCode == ErrorCode.IBOAN_REQJSON_IO_EXCEPTION) {
			_errorMsg = "输入输出异常！";
		} else if (errorCode == ErrorCode.IBOAN_REQJSON_CHARACTERCODING_EXCEPTION) {
			_errorMsg = "输入输出异常！";
		} else if (errorCode == ErrorCode.IBOAN_REQJSON_PARSEMSG_EXCEPTION) {
			_errorMsg = "输入输出异常,协议体结构错误！";
		} else if (errorCode == ErrorCode.IBOAN_REQJSON_C2SBASEMSG_ISNULL) {
			_errorMsg = "输入输出异常！";
		} else if (errorCode == ErrorCode.IBOAN_REQBODY_INVALID_MSGTYPE) {
			_errorMsg = "参数错误，不合理的请求协议类型！";
		} else if (errorCode == ErrorCode.IBOAN_SERVICE_EXCEPTION) {
			_errorMsg = "自定义服务相关异常！";
		}else if (errorCode == ErrorCode.IBOAN_SAMPLE_GENE) {
			_errorMsg = "基因型信息为空！";
		}else if (errorCode == ErrorCode.NON_LOGIN_USER) {
			_errorMsg = "未检测到用户登录信息！";
		}
		return _errorMsg;
	}

}
