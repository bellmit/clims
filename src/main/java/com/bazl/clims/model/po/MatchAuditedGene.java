package com.bazl.clims.model.po;

import java.util.Date;
import java.util.List;

public class MatchAuditedGene {

    /**审核的基因id*/
    private String auditedGeneId;

    /**检材id*/
    private String sampleId;

    /**试剂盒id*/
    private String panelId;

    /**基因类型*/
    private String geneType;

    /**基因型*/
    private String geneInfo;

    /**基因数量*/
    private Short geneCount;

    /**图谱*/
    private String imagePath;

    /**创建时间*/
    private Date createDatetime;

    /**创建人*/
    private String createPerson;

    /**更新时间*/
    private Date updateDatetime;

    /**更新人*/
    private String updatePerson;

    private String caseId;

    private String sampleFlag;

    private String personType;

    private String sampleNo;

    private String sampleName;

    private String sampleTypeName;

    private String totalProbability;

    private String sameCount;

    private String diffCount;

    private String boardNo;

    private String referenceId;

    /** 种族主键ID */
    private String comparePopulationId;

    /** 匹配下限 */
    private Integer matchLimit;

    /** 容差 */
    private Integer tolerance;

    private List<String> geneTypeList;

    private String evidenceNo;

    private String orgCode;

    private String panelName;

    private String invalidStatus;
    private String sampleId1;
    private String sampleId2;
    /**基因型*/
    private String geneInfo1;
    private String geneInfo2;

    private String groupId;

    public String getGeneInfo1() {
        return geneInfo1;
    }

    public void setGeneInfo1(String geneInfo1) {
        this.geneInfo1 = geneInfo1;
    }

    public String getGeneInfo2() {
        return geneInfo2;
    }

    public void setGeneInfo2(String geneInfo2) {
        this.geneInfo2 = geneInfo2;
    }

    public String getSampleId1() {
        return sampleId1;
    }

    public void setSampleId1(String sampleId1) {
        this.sampleId1 = sampleId1;
    }

    public String getSampleId2() {
        return sampleId2;
    }

    public void setSampleId2(String sampleId2) {
        this.sampleId2 = sampleId2;
    }

    public String getAuditedGeneId() {
        return auditedGeneId;
    }

    public void setAuditedGeneId(String auditedGeneId) {
        this.auditedGeneId = auditedGeneId == null ? null : auditedGeneId.trim();
    }

    public String getSampleId() {
        return sampleId;
    }

    public void setSampleId(String sampleId) {
        this.sampleId = sampleId == null ? null : sampleId.trim();
    }

    public String getPanelId() {
        return panelId;
    }

    public void setPanelId(String panelId) {
        this.panelId = panelId == null ? null : panelId.trim();
    }

    public String getGeneType() {
        return geneType;
    }

    public void setGeneType(String geneType) {
        this.geneType = geneType == null ? null : geneType.trim();
    }

    public String getGeneInfo() {
        return geneInfo;
    }

    public void setGeneInfo(String geneInfo) {
        this.geneInfo = geneInfo == null ? null : geneInfo.trim();
    }

    public Short getGeneCount() {
        return geneCount;
    }

    public void setGeneCount(Short geneCount) {
        this.geneCount = geneCount;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath == null ? null : imagePath.trim();
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

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getSampleFlag() {
        return sampleFlag;
    }

    public void setSampleFlag(String sampleFlag) {
        this.sampleFlag = sampleFlag;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public String getSampleNo() {
        return sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public String getSampleTypeName() {
        return sampleTypeName;
    }

    public void setSampleTypeName(String sampleTypeName) {
        this.sampleTypeName = sampleTypeName;
    }

    public String getTotalProbability() {
        return totalProbability;
    }

    public void setTotalProbability(String totalProbability) {
        this.totalProbability = totalProbability;
    }

    public String getSameCount() {
        return sameCount;
    }

    public void setSameCount(String sameCount) {
        this.sameCount = sameCount;
    }

    public String getDiffCount() {
        return diffCount;
    }

    public void setDiffCount(String diffCount) {
        this.diffCount = diffCount;
    }

    public String getBoardNo() {
        return boardNo;
    }

    public void setBoardNo(String boardNo) {
        this.boardNo = boardNo;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getComparePopulationId() {
        return comparePopulationId;
    }

    public void setComparePopulationId(String comparePopulationId) {
        this.comparePopulationId = comparePopulationId;
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

    public List<String> getGeneTypeList() {
        return geneTypeList;
    }

    public void setGeneTypeList(List<String> geneTypeList) {
        this.geneTypeList = geneTypeList;
    }

    public String getEvidenceNo() {
        return evidenceNo;
    }

    public void setEvidenceNo(String evidenceNo) {
        this.evidenceNo = evidenceNo;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getPanelName() {
        return panelName;
    }

    public void setPanelName(String panelName) {
        this.panelName = panelName;
    }

    public String getInvalidStatus() {
        return invalidStatus;
    }

    public void setInvalidStatus(String invalidStatus) {
        this.invalidStatus = invalidStatus;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}