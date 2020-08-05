package com.bazl.clims.model.po;

import java.util.Date;

public class AmLearnRecord {
    private String id;

    private String personalId;

    private Date learnStartDatetime;

    private Date learnEndDatetime;

    private String academy;

    private String major;

    private String qualification;

    private String degree;

    private String highestFlag;

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

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId == null ? null : personalId.trim();
    }

    public Date getLearnStartDatetime() {
        return learnStartDatetime;
    }

    public void setLearnStartDatetime(Date learnStartDatetime) {
        this.learnStartDatetime = learnStartDatetime;
    }

    public Date getLearnEndDatetime() {
        return learnEndDatetime;
    }

    public void setLearnEndDatetime(Date learnEndDatetime) {
        this.learnEndDatetime = learnEndDatetime;
    }

    public String getAcademy() {
        return academy;
    }

    public void setAcademy(String academy) {
        this.academy = academy == null ? null : academy.trim();
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification == null ? null : qualification.trim();
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree == null ? null : degree.trim();
    }

    public String getHighestFlag() {
        return highestFlag;
    }

    public void setHighestFlag(String highestFlag) {
        this.highestFlag = highestFlag == null ? null : highestFlag.trim();
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