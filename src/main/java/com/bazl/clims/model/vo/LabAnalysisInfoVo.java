package com.bazl.clims.model.vo;

import com.bazl.clims.model.po.LabAnalysisInfo;
import com.bazl.clims.model.po.LimsCaseInfo;
import com.bazl.clims.utils.JsonDatetimeSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/1/4.
 */
public class LabAnalysisInfoVo extends AbstractBaseVo<LabAnalysisInfo> implements Serializable {

    public LabAnalysisInfoVo() {
        super();
        this.entity = new LabAnalysisInfo();
    }

    public LabAnalysisInfoVo(LabAnalysisInfo entity) {
        super();
        this.entity = entity;
    }

    //创建变量，存入当前用户的orgId，和委托表accept_org_id进行判断
    private String userOrdId;

    //未审核送件数
    private String notReviewCount;

    //审核开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date auditStartDatetime;

    //审核结束时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date auditEndDatetime;
    /*检材编号*/
    private String SampleNo;

    public Date getAuditStartDatetime() {
        return auditStartDatetime;
    }

    public void setAuditStartDatetime(Date auditStartDatetime) {
        this.auditStartDatetime = auditStartDatetime;
    }

    public Date getAuditEndDatetime() {
        return auditEndDatetime;
    }

    public void setAuditEndDatetime(Date auditEndDatetime) {
        this.auditEndDatetime = auditEndDatetime;
    }

    public String getNotReviewCount() {
        return notReviewCount;
    }

    public void setNotReviewCount(String notReviewCount) {
        this.notReviewCount = notReviewCount;
    }

    public String getUserOrdId() {
        return userOrdId;
    }

    public void setUserOrdId(String userOrdId) {
        this.userOrdId = userOrdId;
    }

    public String getSampleNo() {
        return SampleNo;
    }

    public void setSampleNo(String sampleNo) {
        SampleNo = sampleNo;
    }
}
