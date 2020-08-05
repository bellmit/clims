package com.bazl.clims.model.po;

import java.util.Date;

public class LimsEvidenceInfo {
    private String evidenceId;

    private String caseId;

    private String consignmentId;

    private String evidenceNo;

    private String evidenceName;

    private String evidenceType;

    private Short evidenceCount;

    private String evidenceUnit;

    private String evidencePacking;

    private String evidenceTraits;

    private String evidenceDesc;

    private Date createDatetime;

    private String createPerson;

    private Date updateDatetime;

    private String updatePerson;

    private String deleteFlag;

    private Date deleteDatetime;

    private String deletePerson;

    private String deleteReason;

    private String evidenceRemark;

    public String getEvidenceId() {
        return evidenceId;
    }

    public void setEvidenceId(String evidenceId) {
        this.evidenceId = evidenceId == null ? null : evidenceId.trim();
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId == null ? null : caseId.trim();
    }

    public String getConsignmentId() {
        return consignmentId;
    }

    public void setConsignmentId(String consignmentId) {
        this.consignmentId = consignmentId == null ? null : consignmentId.trim();
    }

    public String getEvidenceNo() {
        return evidenceNo;
    }

    public void setEvidenceNo(String evidenceNo) {
        this.evidenceNo = evidenceNo == null ? null : evidenceNo.trim();
    }

    public String getEvidenceName() {
        return evidenceName;
    }

    public void setEvidenceName(String evidenceName) {
        this.evidenceName = evidenceName == null ? null : evidenceName.trim();
    }

    public String getEvidenceType() {
        return evidenceType;
    }

    public void setEvidenceType(String evidenceType) {
        this.evidenceType = evidenceType == null ? null : evidenceType.trim();
    }

    public Short getEvidenceCount() {
        return evidenceCount;
    }

    public void setEvidenceCount(Short evidenceCount) {
        this.evidenceCount = evidenceCount;
    }

    public String getEvidenceUnit() {
        return evidenceUnit;
    }

    public void setEvidenceUnit(String evidenceUnit) {
        this.evidenceUnit = evidenceUnit == null ? null : evidenceUnit.trim();
    }

    public String getEvidencePacking() {
        return evidencePacking;
    }

    public void setEvidencePacking(String evidencePacking) {
        this.evidencePacking = evidencePacking == null ? null : evidencePacking.trim();
    }

    public String getEvidenceTraits() {
        return evidenceTraits;
    }

    public void setEvidenceTraits(String evidenceTraits) {
        this.evidenceTraits = evidenceTraits == null ? null : evidenceTraits.trim();
    }

    public String getEvidenceDesc() {
        return evidenceDesc;
    }

    public void setEvidenceDesc(String evidenceDesc) {
        this.evidenceDesc = evidenceDesc == null ? null : evidenceDesc.trim();
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

    public String getDeleteReason() {
        return deleteReason;
    }

    public void setDeleteReason(String deleteReason) {
        this.deleteReason = deleteReason == null ? null : deleteReason.trim();
    }

    public String getEvidenceRemark() {
        return evidenceRemark;
    }

    public void setEvidenceRemark(String evidenceRemark) {
        this.evidenceRemark = evidenceRemark == null ? null : evidenceRemark.trim();
    }
}