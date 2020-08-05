package com.bazl.clims.model.po;

import java.util.Date;

/**
 * @author wanghaiyang
 * @date 2019/4/19.
 */
public class CompareRelativeResult {

    /** 主键id */
    private String id;

    /** 案件主键ID */
    private String caseId;

    /** 试剂盒主键ID */
    private String panelId;

    /** 比对参照 */
    private String referenceId;

    /** 父亲检材编号 */
    private String fatherSampleNo;

    /** 母亲检材编号 */
    private String motherSampleNo;

    /** 孩子检材编号 */
    private String childSampleNo;

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

    /** 匹配模式(0为未匹配，1为匹配成功双亲，2为只匹配父亲，3为只匹配母亲，4为单亲都匹配，双亲综合不匹配) */
    private String matchMode;

    /** 创建时间 */
    private Date createDatetime;

    /** 创建人 */
    private String createPerson;

    /** 更新时间 */
    private Date updateDatetime;

    /** 更新人 */
    private String updatePerson;

    private String comparePopulationName;

    private String reagentNameF;

    private String reagentNameM;

    private String reagentNameC;

    private String fatherSampleName;

    private String motherSampleName;

    private String childSampleName;

    private String fatherSampleId;

    private String motherSampleId;

    private String childSampleId;

    private String fatherGeneId;

    private String motherGeneId;

    private String childGeneId;

    private String bacgroundF;

    private String bacgroundM;

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

    public String getFatherSampleNo() {
        return fatherSampleNo;
    }

    public void setFatherSampleNo(String fatherSampleNo) {
        this.fatherSampleNo = fatherSampleNo == null ? null : fatherSampleNo.trim();
    }

    public String getMotherSampleNo() {
        return motherSampleNo;
    }

    public void setMotherSampleNo(String motherSampleNo) {
        this.motherSampleNo = motherSampleNo == null ? null : motherSampleNo.trim();
    }

    public String getChildSampleNo() {
        return childSampleNo;
    }

    public void setChildSampleNo(String childSampleNo) {
        this.childSampleNo = childSampleNo == null ? null : childSampleNo.trim();
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

    public String getMatchMode() {
        return matchMode;
    }

    public void setMatchMode(String matchMode) {
        this.matchMode = matchMode;
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

    public String getFatherSampleName() {
        return fatherSampleName;
    }

    public void setFatherSampleName(String fatherSampleName) {
        this.fatherSampleName = fatherSampleName;
    }

    public String getMotherSampleName() {
        return motherSampleName;
    }

    public void setMotherSampleName(String motherSampleName) {
        this.motherSampleName = motherSampleName;
    }

    public String getChildSampleName() {
        return childSampleName;
    }

    public void setChildSampleName(String childSampleName) {
        this.childSampleName = childSampleName;
    }

    public String getBacgroundF() {
        return bacgroundF;
    }

    public void setBacgroundF(String bacgroundF) {
        this.bacgroundF = bacgroundF;
    }

    public String getBacgroundM() {
        return bacgroundM;
    }

    public void setBacgroundM(String bacgroundM) {
        this.bacgroundM = bacgroundM;
    }

    public String getComparePopulationName() {
        return comparePopulationName;
    }

    public void setComparePopulationName(String comparePopulationName) {
        this.comparePopulationName = comparePopulationName;
    }

    public String getFatherSampleId() {
        return fatherSampleId;
    }

    public void setFatherSampleId(String fatherSampleId) {
        this.fatherSampleId = fatherSampleId;
    }

    public String getMotherSampleId() {
        return motherSampleId;
    }

    public void setMotherSampleId(String motherSampleId) {
        this.motherSampleId = motherSampleId;
    }

    public String getChildSampleId() {
        return childSampleId;
    }

    public void setChildSampleId(String childSampleId) {
        this.childSampleId = childSampleId;
    }

    public String getFatherGeneId() {
        return fatherGeneId;
    }

    public void setFatherGeneId(String fatherGeneId) {
        this.fatherGeneId = fatherGeneId;
    }

    public String getMotherGeneId() {
        return motherGeneId;
    }

    public void setMotherGeneId(String motherGeneId) {
        this.motherGeneId = motherGeneId;
    }

    public String getChildGeneId() {
        return childGeneId;
    }

    public void setChildGeneId(String childGeneId) {
        this.childGeneId = childGeneId;
    }

    public String getReagentNameF() {
        return reagentNameF;
    }

    public void setReagentNameF(String reagentNameF) {
        this.reagentNameF = reagentNameF;
    }

    public String getReagentNameM() {
        return reagentNameM;
    }

    public void setReagentNameM(String reagentNameM) {
        this.reagentNameM = reagentNameM;
    }

    public String getReagentNameC() {
        return reagentNameC;
    }

    public void setReagentNameC(String reagentNameC) {
        this.reagentNameC = reagentNameC;
    }
}