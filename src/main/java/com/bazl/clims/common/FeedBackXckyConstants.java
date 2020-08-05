package com.bazl.clims.common;

/**
 * @author
 * 反馈现勘状态常量定义
 * Created by lizhihua on 2019-05-13.
 */
public class FeedBackXckyConstants {
    /**
     * 已委托
     */
    public static final String QUEUE_TYPE_CONSIGNMENT_COMMISSIONED = "20";

    /**
     * 委托已受理
     */
    public static final String QUEUE_TYPE_CONSIGNMENT_ACCEPTED = "21";

    /**
     * 物证已受理
     */
    public static final String QUEUE_TYPE_EVIDENCE_ACCEPTED = "22";

    /**
     * 物证已拒绝
     */
    public static final String QUEUE_TYPE_EVIDENCE_REFUSED = "23";

    /**
     * 物证已检出
     */
    public static final String QUEUE_TYPE_EVIDENCE_GENE = "24";

    /**
     * 物证未检出
     */
    public static final String QUEUE_TYPE_EVIDENCE_NOGENE = "25";

    /**
     * 物证比中
     */
    public static final String QUEUE_TYPE_EVIDENCE_MATCHED = "26";


    /**
     * 待处理
     */
    public static final String QUEUE_STATUS_PENDING = "0";

    /**
     * 处理中
     */
    public static final String QUEUE_STATUS_EXECUTING = "1";

    /**
     * 处理成功
     */
    public static final String QUEUE_STATUS_SUCCEED = "2";

    /**
     * 处理失败
     */
    public static final String QUEUE_STATUS_FAILED = "-1";
}
