package com.bazl.clims.utils;

public class ErrorCode {
    /**
     * 输入输出异常！
     */
    public final static long IBOAN_REQJSON_IO_EXCEPTION = -10001;
    /**
     * 输入输出异常！
     */
    public final static long IBOAN_REQJSON_CHARACTERCODING_EXCEPTION = -10002;
    /**
     * 输入输出异常,协议体结构错误！
     */
    public final static long IBOAN_REQJSON_PARSEMSG_EXCEPTION = -10003;
    /**
     * 输入输出异常！
     */
    public final static long IBOAN_REQJSON_C2SBASEMSG_ISNULL = -10004;
    /**
     * 参数错误，不合理的请求协议类型！
     */
    public final static long IBOAN_REQBODY_INVALID_MSGTYPE = -10005;

    /**
     * 自定义服务相关异常！
     */
    public final static long IBOAN_SERVICE_EXCEPTION = -10006;

    /**
     * 未查询出内容！
     */
    public final static long IBOAN_SEARCH_ISNULL_EXCEPTION = -10007;

    /**
     * 数据重复！
     */
    public final static long IBOAN_REPEAT_EXCEPTION = -10008;

    /**
     * 数据不存在！
     */
    public final static long IBOAN_SAMPLE_GENE = -10009;

    /**
     * 未检测到登录用户！
     */
    public final static long NON_LOGIN_USER = -10010;

}
