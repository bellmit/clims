package com.bazl.clims.model.vo;

import com.bazl.clims.model.po.LimsConsignmentInfo;
import com.bazl.clims.utils.JsonDatetimeSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/1/4.
 */
public class LimsConsignmentInfoVo extends AbstractBaseVo<LimsConsignmentInfo> implements Serializable {

    public LimsConsignmentInfoVo() {
        super();
        this.entity = new LimsConsignmentInfo();
    }

    public LimsConsignmentInfoVo(LimsConsignmentInfo entity) {
        super();
        this.entity = entity;
    }

    private String caseName;

    private String caseType;

    private String caseProperty;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date caseDatetime;

    private String casePropertyName;

    private String caseTypeName;

    private String caseNo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date delegateStartDatetime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date delegateEndDatetime;

    private String caseId;

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public Date getDelegateStartDatetime() {
        return delegateStartDatetime;
    }

    public void setDelegateStartDatetime(Date delegateStartDatetime) {
        this.delegateStartDatetime = delegateStartDatetime;
    }

    public Date getDelegateEndDatetime() {
        return delegateEndDatetime;
    }

    public void setDelegateEndDatetime(Date delegateEndDatetime) {
        this.delegateEndDatetime = delegateEndDatetime;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getCasePropertyName() {
        return casePropertyName;
    }

    public void setCasePropertyName(String casePropertyName) {
        this.casePropertyName = casePropertyName;
    }

    public String getCaseTypeName() {
        return caseTypeName;
    }

    public void setCaseTypeName(String caseTypeName) {
        this.caseTypeName = caseTypeName;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String getCaseProperty() {
        return caseProperty;
    }

    public void setCaseProperty(String caseProperty) {
        this.caseProperty = caseProperty;
    }

    public Date getCaseDatetime() {
        return caseDatetime;
    }

    public void setCaseDatetime(Date caseDatetime) {
        this.caseDatetime = caseDatetime;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }
}
