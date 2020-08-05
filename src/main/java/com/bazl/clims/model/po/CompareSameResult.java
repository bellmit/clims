package com.bazl.clims.model.po;

import java.util.Date;

/**
 * @author wanghaiyang
 * @date 2019/4/19.
 */
public class CompareSameResult {

    /** 主键ID */
    private String id;

    /** 案件主键ID */
    private String caseId;

    /** 试剂盒主键ID */
    private String panelId;

    /** 比对参照 */
    private String referenceId;

    /** 检材编号 */
    private String sampleNo;

    /** 比中检材编号的板号 */
    private String compareBoardNo;

    /** 比中数量 */
    private Integer compareGeneSum;

    /** PI值 */
    private String compareTotalProbability;

    /** 种族主键ID */
    private String comparePopulationId;

    /** 匹配下限 */
    private Integer matchLimit;

    /** 容差 */
    private Integer tolerance;

    /** 创建时间 */
    private Date createDatetime;

    /** 创建人 */
    private String createPerson;

    /** 更新时间 */
    private Date updateDatetime;

    /** 更新人 */
    private String updatePerson;

    private String sampleFlag;

    private String sampleName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId == null ? null : caseId.trim();
    }

    public String getPanelId() {
        return panelId;
    }

    public void setPanelId(String panelId) {
        this.panelId = panelId == null ? null : panelId.trim();
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId == null ? null : referenceId.trim();
    }

    public String getSampleNo() {
        return sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo == null ? null : sampleNo.trim();
    }

    public String getCompareBoardNo() {
        return compareBoardNo;
    }

    public void setCompareBoardNo(String compareBoardNo) {
        this.compareBoardNo = compareBoardNo == null ? null : compareBoardNo.trim();
    }

    public Integer getCompareGeneSum() {
        return compareGeneSum;
    }

    public void setCompareGeneSum(Integer compareGeneSum) {
        this.compareGeneSum = compareGeneSum;
    }

    public String getCompareTotalProbability() {
        return compareTotalProbability;
    }

    public void setCompareTotalProbability(String compareTotalProbability) {
        this.compareTotalProbability = compareTotalProbability == null ? null : compareTotalProbability.trim();
    }

    public String getComparePopulationId() {
        return comparePopulationId;
    }

    public void setComparePopulationId(String comparePopulationId) {
        this.comparePopulationId = comparePopulationId == null ? null : comparePopulationId.trim();
    }

    public Integer getMatchLimit() {
        return matchLimit;
    }

    public void setMatchLimit(Integer matchLimit) {
        this.matchLimit = matchLimit;
    }

    public Integer getTolerance() {
        return tolerance;
    }

    public void setTolerance(Integer tolerance) {
        this.tolerance = tolerance;
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

    public String getSampleFlag() {
        return sampleFlag;
    }

    public void setSampleFlag(String sampleFlag) {
        this.sampleFlag = sampleFlag;
    }

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }
}