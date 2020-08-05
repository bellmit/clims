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
public class LabPcrInfo {

    /** 主键ID */
    private String pcrId;

    /** 任务主键ID */
    private String taskId;

    /** 扩增编号 */
    private String pcrNo;

    /** 扩增板号 */
    private String boardNo;

    /** 扩增程序号 */
    private String pcrProgram;

    /** 扩增仪器号 */
    private String pcrInstrument;

    /** 扩增体系 */
    private String pcrSystem;

    /** 扩增试剂盒 */
    private String pcrReagent;

    /** 扩增循环数 */
    private Short pcrLoopCount;

    /** 温度 */
    private String pcrTemperature;

    /** 湿度 */
    private String pcrHumidity;


    /** 扩增开始时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date pcrStartDatetime;

    /** 扩增结束时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date pcrEndDatetime;

    /** 扩增人1 */
    private String pcrPerson1;

    /** 扩增人2 */
    private String pcrPerson2;

    /** 样本数 */
    private Short sampleCount;

    /** 创建时间 */
    /** 扩增结束时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date createDatetime;

    /** 创建人 */
    private String createPerson;

    /** 更新时间 */
    private Date updateDatetime;

    /** 更新人 */
    private String updatePerson;

    /* 批号 */
    private String batchb;

    /* 有效期 */
    private String validity;

    /* 扩增状态 */
    private String pcrStage;

    /* 单位id */
    private String orgId;

    /* 业务字段 */
    private String delegateOrgCode;

    /**
     * 任务开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date acceptStartDatetime;

    /**
     * 任务终止时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date acceptEndDatetime;




    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getPcrStage() {
        return pcrStage;
    }

    public void setPcrStage(String pcrStage) {
        this.pcrStage = pcrStage;
    }

    public String getDelegateOrgCode() {
        return delegateOrgCode;
    }

    public void setDelegateOrgCode(String delegateOrgCode) {
        this.delegateOrgCode = delegateOrgCode;
    }

    public String getBatchb() {
        return batchb;
    }

    public void setBatchb(String batchb) {
        this.batchb = batchb;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public String getPcrId() {
        return pcrId;
    }

    public void setPcrId(String pcrId) {
        this.pcrId = pcrId == null ? null : pcrId.trim();
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    public String getPcrNo() {
        return pcrNo;
    }

    public void setPcrNo(String pcrNo) {
        this.pcrNo = pcrNo == null ? null : pcrNo.trim();
    }

    public String getBoardNo() {
        return boardNo;
    }

    public void setBoardNo(String boardNo) {
        this.boardNo = boardNo == null ? null : boardNo.trim();
    }

    public String getPcrProgram() {
        return pcrProgram;
    }

    public void setPcrProgram(String pcrProgram) {
        this.pcrProgram = pcrProgram == null ? null : pcrProgram.trim();
    }

    public String getPcrInstrument() {
        return pcrInstrument;
    }

    public void setPcrInstrument(String pcrInstrument) {
        this.pcrInstrument = pcrInstrument == null ? null : pcrInstrument.trim();
    }

    public String getPcrSystem() {
        return pcrSystem;
    }

    public void setPcrSystem(String pcrSystem) {
        this.pcrSystem = pcrSystem == null ? null : pcrSystem.trim();
    }

    public String getPcrReagent() {
        return pcrReagent;
    }

    public void setPcrReagent(String pcrReagent) {
        this.pcrReagent = pcrReagent == null ? null : pcrReagent.trim();
    }

    public Short getPcrLoopCount() {
        return pcrLoopCount;
    }

    public void setPcrLoopCount(Short pcrLoopCount) {
        this.pcrLoopCount = pcrLoopCount;
    }

    public String getPcrTemperature() {
        return pcrTemperature;
    }

    public void setPcrTemperature(String pcrTemperature) {
        this.pcrTemperature = pcrTemperature == null ? null : pcrTemperature.trim();
    }

    public String getPcrHumidity() {
        return pcrHumidity;
    }

    public void setPcrHumidity(String pcrHumidity) {
        this.pcrHumidity = pcrHumidity == null ? null : pcrHumidity.trim();
    }

    public Date getPcrStartDatetime() {
        return pcrStartDatetime;
    }

    public void setPcrStartDatetime(Date pcrStartDatetime) {
        this.pcrStartDatetime = pcrStartDatetime;
    }

    public Date getPcrEndDatetime() {
        return pcrEndDatetime;
    }

    public void setPcrEndDatetime(Date pcrEndDatetime) {
        this.pcrEndDatetime = pcrEndDatetime;
    }

    public String getPcrPerson1() {
        return pcrPerson1;
    }

    public void setPcrPerson1(String pcrPerson1) {
        this.pcrPerson1 = pcrPerson1 == null ? null : pcrPerson1.trim();
    }

    public String getPcrPerson2() {
        return pcrPerson2;
    }

    public void setPcrPerson2(String pcrPerson2) {
        this.pcrPerson2 = pcrPerson2 == null ? null : pcrPerson2.trim();
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

    public Date getAcceptStartDatetime() {
        return acceptStartDatetime;
    }

    public void setAcceptStartDatetime(Date acceptStartDatetime) {
        this.acceptStartDatetime = acceptStartDatetime;
    }

    public Date getAcceptEndDatetime() {
        return acceptEndDatetime;
    }

    public void setAcceptEndDatetime(Date acceptEndDatetime) {
        this.acceptEndDatetime = acceptEndDatetime;
    }
}