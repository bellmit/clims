package com.bazl.clims.model.po;

import com.bazl.clims.utils.JsonDatetimeSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author wanghaiyang
 * @date 2019/3/11.
 */
public class LabSyInfo {

    /** 主键ID */
    private String syId;

    /** 任务主键ID */
    private String taskId;

    /** 上样实验编号 */
    private String syNo;

    /** 上样板号 */
    private String boardNo;

    /** 上样开始时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date syStartDatetime;

    /** 上样结束时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date syEndDatetime;

    /** 上样人1 */
    private String syPerson1;

    /** 上样人2 */
    private String syPerson2;

    /** 上样参数 */
    private String syString;

    /** 电泳仪 */
    private String elecInstrument;

    /** 检材数 */
    private Short sampleCount;

    /** 创建时间 */
    private Date createDatetime;

    /** 创建人 */
    private String createPerson;

    /** 更新时间 */
    private Date updateDatetime;

    /** 更新人 */
    private String updatePerson;

    /**产物*/
    private String product;

    /**甲酰胺*/
    private String formamide;

    /**内标ul*/
    private String internalStandardUl;

    /**内标*/
    private String internalStandard;

    /* 上样状态 */
    private String syStage;

    /* 单位id */
    private String orgId;

    /* 业务字段 */
    private String delegateOrgCode;

    public String getSyStage() {
        return syStage;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public void setSyStage(String syStage) {
        this.syStage = syStage;
    }

    public String getDelegateOrgCode() {
        return delegateOrgCode;
    }

    public void setDelegateOrgCode(String delegateOrgCode) {
        this.delegateOrgCode = delegateOrgCode;
    }

    public String getSyId() {
        return syId;
    }

    public void setSyId(String syId) {
        this.syId = syId == null ? null : syId.trim();
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    public String getSyNo() {
        return syNo;
    }

    public void setSyNo(String syNo) {
        this.syNo = syNo == null ? null : syNo.trim();
    }

    public String getBoardNo() {
        return boardNo;
    }

    public void setBoardNo(String boardNo) {
        this.boardNo = boardNo == null ? null : boardNo.trim();
    }

    public Date getSyStartDatetime() {
        return syStartDatetime;
    }

    public void setSyStartDatetime(Date syStartDatetime) {
        this.syStartDatetime = syStartDatetime;
    }

    public Date getSyEndDatetime() {
        return syEndDatetime;
    }
    
    public void setSyEndDatetime(Date syEndDatetime) {
        this.syEndDatetime = syEndDatetime;
    }

    public String getSyPerson1() {
        return syPerson1;
    }

    public void setSyPerson1(String syPerson1) {
        this.syPerson1 = syPerson1 == null ? null : syPerson1.trim();
    }

    public String getSyPerson2() {
        return syPerson2;
    }

    public void setSyPerson2(String syPerson2) {
        this.syPerson2 = syPerson2 == null ? null : syPerson2.trim();
    }

    public String getSyString() {
        return syString;
    }

    public void setSyString(String syString) {
        this.syString = syString == null ? null : syString.trim();
    }

    public String getElecInstrument() {
        return elecInstrument;
    }

    public void setElecInstrument(String elecInstrument) {
        this.elecInstrument = elecInstrument == null ? null : elecInstrument.trim();
    }

    public Short getSampleCount() {
        return sampleCount;
    }

    public void setSampleCount(Short sampleCount) {
        this.sampleCount = sampleCount;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson == null ? null : createPerson.trim();
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public String getUpdatePerson() {
        return updatePerson;
    }

    public void setUpdatePerson(String updatePerson) {
        this.updatePerson = updatePerson == null ? null : updatePerson.trim();
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getFormamide() {
        return formamide;
    }

    public void setFormamide(String formamide) {
        this.formamide = formamide;
    }

    public String getInternalStandardUl() {
        return internalStandardUl;
    }

    public void setInternalStandardUl(String internalStandardUl) {
        this.internalStandardUl = internalStandardUl;
    }

    public String getInternalStandard() {
        return internalStandard;
    }

    public void setInternalStandard(String internalStandard) {
        this.internalStandard = internalStandard;
    }
}