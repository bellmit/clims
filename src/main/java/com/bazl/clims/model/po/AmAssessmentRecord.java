package com.bazl.clims.model.po;

import java.util.Date;

public class AmAssessmentRecord {
    private String id;

    private String orgId;

    private String assessmentName;

    private String assessmentContent;

    private String assessmentResult;

    private String assessmentStandard;

    private String assessmentDeduction;

    private Date assessmentDatetime;

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

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getAssessmentName() {
        return assessmentName;
    }

    public void setAssessmentName(String assessmentName) {
        this.assessmentName = assessmentName == null ? null : assessmentName.trim();
    }

    public String getAssessmentContent() {
        return assessmentContent;
    }

    public void setAssessmentContent(String assessmentContent) {
        this.assessmentContent = assessmentContent == null ? null : assessmentContent.trim();
    }

    public String getAssessmentResult() {
        return assessmentResult;
    }

    public void setAssessmentResult(String assessmentResult) {
        this.assessmentResult = assessmentResult == null ? null : assessmentResult.trim();
    }

    public String getAssessmentStandard() {
        return assessmentStandard;
    }

    public void setAssessmentStandard(String assessmentStandard) {
        this.assessmentStandard = assessmentStandard == null ? null : assessmentStandard.trim();
    }

    public String getAssessmentDeduction() {
        return assessmentDeduction;
    }

    public void setAssessmentDeduction(String assessmentDeduction) {
        this.assessmentDeduction = assessmentDeduction == null ? null : assessmentDeduction.trim();
    }

    public Date getAssessmentDatetime() {
        return assessmentDatetime;
    }

    public void setAssessmentDatetime(Date assessmentDatetime) {
        this.assessmentDatetime = assessmentDatetime;
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