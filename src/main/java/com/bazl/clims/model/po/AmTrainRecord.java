package com.bazl.clims.model.po;

import java.util.Date;

public class AmTrainRecord {
    private String id;

    private String personalId;

    private Date trainStartDatetime;

    private Date trainEndDatetime;

    private String trainAddress;

    private String trainContent;

    private String trainSpeaker;

    private String trainLevel;

    private Date createDatetime;

    private String createPerson;

    private Date updateDatetime;

    private String updatePerson;

    private String deleteFlag;

    private Date deleteDatetime;

    private String deletePerson;

    private String trainingSubject;

    private String trainingParty;

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

    public Date getTrainStartDatetime() {
        return trainStartDatetime;
    }

    public void setTrainStartDatetime(Date trainStartDatetime) {
        this.trainStartDatetime = trainStartDatetime;
    }

    public Date getTrainEndDatetime() {
        return trainEndDatetime;
    }

    public void setTrainEndDatetime(Date trainEndDatetime) {
        this.trainEndDatetime = trainEndDatetime;
    }

    public String getTrainAddress() {
        return trainAddress;
    }

    public void setTrainAddress(String trainAddress) {
        this.trainAddress = trainAddress == null ? null : trainAddress.trim();
    }

    public String getTrainContent() {
        return trainContent;
    }

    public void setTrainContent(String trainContent) {
        this.trainContent = trainContent == null ? null : trainContent.trim();
    }

    public String getTrainSpeaker() {
        return trainSpeaker;
    }

    public void setTrainSpeaker(String trainSpeaker) {
        this.trainSpeaker = trainSpeaker == null ? null : trainSpeaker.trim();
    }

    public String getTrainLevel() {
        return trainLevel;
    }

    public void setTrainLevel(String trainLevel) {
        this.trainLevel = trainLevel == null ? null : trainLevel.trim();
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

    public String getTrainingSubject() {
        return trainingSubject;
    }

    public void setTrainingSubject(String trainingSubject) {
        this.trainingSubject = trainingSubject == null ? null : trainingSubject.trim();
    }

    public String getTrainingParty() {
        return trainingParty;
    }

    public void setTrainingParty(String trainingParty) {
        this.trainingParty = trainingParty == null ? null : trainingParty.trim();
    }
}