package com.bazl.clims.model.po;

import java.util.Date;

public class MatchCaseResult {
    private String id;

    private String caseId;

    private String groupId;

    private String matchType;

    private String sampleaId;

    private String samplebId;

    private String samplecId;

    private String sampleaNo;

    private String samplebNo;

    private String samplecNo;

    private String sampleaName;

    private String samplebName;

    private String samplecName;

    private Short sameCount;

    private Short diffCount;

    private Double probability;

    private Date createDatetime;

    private String createPerson;

    private Date updateDatetime;

    private String updatePerson;

    private String deleteFlag;

    private Date deleteDatetime;

    private String deletePerson;

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

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }

    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType == null ? null : matchType.trim();
    }

    public String getSampleaId() {
        return sampleaId;
    }

    public void setSampleaId(String sampleaId) {
        this.sampleaId = sampleaId == null ? null : sampleaId.trim();
    }

    public String getSamplebId() {
        return samplebId;
    }

    public void setSamplebId(String samplebId) {
        this.samplebId = samplebId == null ? null : samplebId.trim();
    }

    public String getSamplecId() {
        return samplecId;
    }

    public void setSamplecId(String samplecId) {
        this.samplecId = samplecId == null ? null : samplecId.trim();
    }

    public String getSampleaNo() {
        return sampleaNo;
    }

    public void setSampleaNo(String sampleaNo) {
        this.sampleaNo = sampleaNo == null ? null : sampleaNo.trim();
    }

    public String getSamplebNo() {
        return samplebNo;
    }

    public void setSamplebNo(String samplebNo) {
        this.samplebNo = samplebNo == null ? null : samplebNo.trim();
    }

    public String getSamplecNo() {
        return samplecNo;
    }

    public void setSamplecNo(String samplecNo) {
        this.samplecNo = samplecNo == null ? null : samplecNo.trim();
    }

    public String getSampleaName() {
        return sampleaName;
    }

    public void setSampleaName(String sampleaName) {
        this.sampleaName = sampleaName == null ? null : sampleaName.trim();
    }

    public String getSamplebName() {
        return samplebName;
    }

    public void setSamplebName(String samplebName) {
        this.samplebName = samplebName == null ? null : samplebName.trim();
    }

    public String getSamplecName() {
        return samplecName;
    }

    public void setSamplecName(String samplecName) {
        this.samplecName = samplecName == null ? null : samplecName.trim();
    }

    public Short getSameCount() {
        return sameCount;
    }

    public void setSameCount(Short sameCount) {
        this.sameCount = sameCount;
    }

    public Short getDiffCount() {
        return diffCount;
    }

    public void setDiffCount(Short diffCount) {
        this.diffCount = diffCount;
    }

    public Double getProbability() {
        return probability;
    }

    public void setProbability(Double probability) {
        this.probability = probability;
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
}