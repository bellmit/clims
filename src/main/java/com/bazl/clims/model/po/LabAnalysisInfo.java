package com.bazl.clims.model.po;

import com.bazl.clims.utils.JsonDatetimeSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author wanghaiyang
 * @date 2019/3/27.
 */
public class LabAnalysisInfo {

    /** 主键ID */
    private String id;

    /** 板号 */
    private String boardNo;

    /** 分析人 */
    private String analysisPerson;

    /** 分析时间 */
    private Date analysisDatetime;

    /** 结束时间 */
    private Date endDatetime;

    /** 测序仪 */
    private String machineNo;

    /** 泳道模式 */
    private String runModule;

    /**  */
    private String power;

    /** 试剂盒 */
    private String kitName;

    /** 分析文件路径 */
    private String dataFilePath;

    /** 检材数量 */
    private String sampleCount;

    /** 审核数量 */
    private String reviewCount;

    /** 错误数量 */
    private String errorCount;

    /** 警告数量 */
    private String warningCount;

    /** ladder参照样本 */
    private String ladderStatus;

    /** 阳性参照样本 */
    private String positiveStatus;

    /** 阴性参照样本 */
    private String negativeStatus;

    /** 更新人 */
    private String lastUpdator;

    /** 更新时间 */
    private Date updateDatetime;

    /** 审核状态 */
    private String auditStatus;

    /** 审核人 */
    private String auditor;

    /** 审核时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date auditDatetime;

    /** 备注 */
    private String comments;

    /**  */
    private String currentStr;

    /**  */
    private String isLims;

    /** 是否锁，0：未锁，1：锁住 */
    private String isLock;

    /**  */
    private String dataFrom;

    /**
     * 受理单位编号/
     */
    private String delegateOrgCode;

    /**
     *受理单位名称/
     */
    private String delegateOrgName;

    /**
     * 创建人
     * @return
     */
    private String createPerson;

    /**
     * 创建时间
     * @return
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date createDatetime;

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
        this.createPerson = createPerson;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBoardNo() {
        return boardNo;
    }

    public void setBoardNo(String boardNo) {
        this.boardNo = boardNo == null ? null : boardNo.trim();
    }

    public String getAnalysisPerson() {
        return analysisPerson;
    }

    public void setAnalysisPerson(String analysisPerson) {
        this.analysisPerson = analysisPerson == null ? null : analysisPerson.trim();
    }

    public Date getAnalysisDatetime() {
        return analysisDatetime;
    }

    public void setAnalysisDatetime(Date analysisDatetime) {
        this.analysisDatetime = analysisDatetime;
    }

    public Date getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(Date endDatetime) {
        this.endDatetime = endDatetime;
    }

    public String getMachineNo() {
        return machineNo;
    }

    public void setMachineNo(String machineNo) {
        this.machineNo = machineNo == null ? null : machineNo.trim();
    }

    public String getRunModule() {
        return runModule;
    }

    public void setRunModule(String runModule) {
        this.runModule = runModule == null ? null : runModule.trim();
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power == null ? null : power.trim();
    }

    public String getKitName() {
        return kitName;
    }

    public void setKitName(String kitName) {
        this.kitName = kitName == null ? null : kitName.trim();
    }

    public String getDataFilePath() {
        return dataFilePath;
    }

    public void setDataFilePath(String dataFilePath) {
        this.dataFilePath = dataFilePath == null ? null : dataFilePath.trim();
    }

    public String getSampleCount() {
        return sampleCount;
    }

    public void setSampleCount(String sampleCount) {
        this.sampleCount = sampleCount == null ? null : sampleCount.trim();
    }

    public String getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(String reviewCount) {
        this.reviewCount = reviewCount == null ? null : reviewCount.trim();
    }

    public String getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(String errorCount) {
        this.errorCount = errorCount == null ? null : errorCount.trim();
    }

    public String getWarningCount() {
        return warningCount;
    }

    public void setWarningCount(String warningCount) {
        this.warningCount = warningCount == null ? null : warningCount.trim();
    }

    public String getLadderStatus() {
        return ladderStatus;
    }

    public void setLadderStatus(String ladderStatus) {
        this.ladderStatus = ladderStatus == null ? null : ladderStatus.trim();
    }

    public String getPositiveStatus() {
        return positiveStatus;
    }

    public void setPositiveStatus(String positiveStatus) {
        this.positiveStatus = positiveStatus == null ? null : positiveStatus.trim();
    }

    public String getNegativeStatus() {
        return negativeStatus;
    }

    public void setNegativeStatus(String negativeStatus) {
        this.negativeStatus = negativeStatus == null ? null : negativeStatus.trim();
    }

    public String getLastUpdator() {
        return lastUpdator;
    }

    public void setLastUpdator(String lastUpdator) {
        this.lastUpdator = lastUpdator == null ? null : lastUpdator.trim();
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus == null ? null : auditStatus.trim();
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor == null ? null : auditor.trim();
    }

    public Date getAuditDatetime() {
        return auditDatetime;
    }

    public void setAuditDatetime(Date auditDatetime) {
        this.auditDatetime = auditDatetime;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments == null ? null : comments.trim();
    }

    public String getCurrentStr() {
        return currentStr;
    }

    public void setCurrentStr(String currentStr) {
        this.currentStr = currentStr == null ? null : currentStr.trim();
    }

    public String getIsLims() {
        return isLims;
    }

    public void setIsLims(String isLims) {
        this.isLims = isLims == null ? null : isLims.trim();
    }

    public String getIsLock() {
        return isLock;
    }

    public void setIsLock(String isLock) {
        this.isLock = isLock == null ? null : isLock.trim();
    }

    public String getDataFrom() {
        return dataFrom;
    }

    public void setDataFrom(String dataFrom) {
        this.dataFrom = dataFrom == null ? null : dataFrom.trim();
    }

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
}