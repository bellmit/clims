package com.bazl.clims.model.po;

import com.bazl.clims.utils.JsonDatetimeSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author wanghaiyang
 * @date 2019/3/8.
 */
public class LabTaskInfo {

    /** 主键ID */
    private String taskId;

    /** 实验任务编号 */
    private String taskNo;

    /** 实验状态 */
    private String taskStatus;

    /** 实验阶段 */
    private String taskStage;

    /** 实验人 */
    private String taskPerson;

    /** 实验开始时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date taskStartDatetime;

    /** 实验结束时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date taskEndDatetime;

    /** 创建时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date createDatetime;

    /** 创建人 */
    private String createPerson;

    /** 更新时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date updateDatetime;

    /** 更新人 */
    private String updatePerson;

    /** 删除状态 */
    private String deleteFlag;

    /** 删除时间 */
    private Date deleteDatetime;

    /** 删除人 */
    private String deletePerson;

    /** 板号 */
    private String boardNo;

    /**检验类型*/
    private String inspectionType;

    /**提取模式*/
    private String extractionMode;

    /**物证检材信息表*/
    private LimsSampleInfoDna limsSampleInfoDna;

    /**案件信息表DNA*/
    private LimsCaseInfo limsCaseInfo;

    /**
     * 受理单位
     * */
    private String delegateOrgCode;

    /**受理单位名称*/
    private String delegateOrgName;

    /**检材数量*/
    private int sampleCount;

    public String getDelegateOrgCode() {
        return delegateOrgCode;
    }

    public void setDelegateOrgCode(String delegateOrgCode) {
        this.delegateOrgCode = delegateOrgCode;
    }

    public String getDelegateOrgName() {
        return delegateOrgName;
    }

    public void setDelegateOrgName(String delegateOrgName) {
        this.delegateOrgName = delegateOrgName;
    }

    public int getSampleCount() {
        return sampleCount;
    }

    public void setSampleCount(int sampleCount) {
        this.sampleCount = sampleCount;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    public String getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo == null ? null : taskNo.trim();
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus == null ? null : taskStatus.trim();
    }

    public String getTaskStage() {
        return taskStage;
    }

    public void setTaskStage(String taskStage) {
        this.taskStage = taskStage == null ? null : taskStage.trim();
    }

    public String getTaskPerson() {
        return taskPerson;
    }

    public void setTaskPerson(String taskPerson) {
        this.taskPerson = taskPerson == null ? null : taskPerson.trim();
    }

    public Date getTaskStartDatetime() {
        return taskStartDatetime;
    }

    public void setTaskStartDatetime(Date taskStartDatetime) {
        this.taskStartDatetime = taskStartDatetime;
    }

    public Date getTaskEndDatetime() {
        return taskEndDatetime;
    }

    public void setTaskEndDatetime(Date taskEndDatetime) {
        this.taskEndDatetime = taskEndDatetime;
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

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag == null ? null : deleteFlag.trim();
    }

    public Date getDeleteDatetime() {
        return deleteDatetime;
    }

    public void setDeleteDatetime(Date deleteDatetime) {
        this.deleteDatetime = deleteDatetime;
    }

    public String getDeletePerson() {
        return deletePerson;
    }

    public void setDeletePerson(String deletePerson) {
        this.deletePerson = deletePerson == null ? null : deletePerson.trim();
    }

    public String getBoardNo() {
        return boardNo;
    }

    public void setBoardNo(String boardNo) {
        this.boardNo = boardNo;
    }

    public void setInspectionType(String inspectionType) {
        this.inspectionType = inspectionType;
    }

    public String getInspectionType() {
        return inspectionType;
    }

    public String getExtractionMode() {
        return extractionMode;
    }

    public void setExtractionMode(String extractionMode) {
        this.extractionMode = extractionMode;
    }

    public LimsSampleInfoDna getLimsSampleInfoDna() {
        return limsSampleInfoDna;
    }

    public void setLimsSampleInfoDna(LimsSampleInfoDna limsSampleInfoDna) {
        this.limsSampleInfoDna = limsSampleInfoDna;
    }

    public LimsCaseInfo getLimsCaseInfo() {
        return limsCaseInfo;
    }

    public void setLimsCaseInfo(LimsCaseInfo limsCaseInfo) {
        this.limsCaseInfo = limsCaseInfo;
    }
}