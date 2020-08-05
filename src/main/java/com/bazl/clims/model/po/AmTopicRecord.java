package com.bazl.clims.model.po;

import java.util.Date;

public class AmTopicRecord {
    private String id;

    private String personalId;

    private Date topicStartDatetime;

    private Date topicEndDatetime;

    private String topicLevel;

    private String topicCharge;

    private String ranking;

    private Date createDatetime;

    private String createPerson;

    private Date updateDatetime;

    private String updatePerson;

    private String deleteFlag;

    private Date deleteDatetime;

    private String deletePerson;

    private String topicName;

    private String awardsRank;

    private String awards;

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

    public Date getTopicStartDatetime() {
        return topicStartDatetime;
    }

    public void setTopicStartDatetime(Date topicStartDatetime) {
        this.topicStartDatetime = topicStartDatetime;
    }

    public Date getTopicEndDatetime() {
        return topicEndDatetime;
    }

    public void setTopicEndDatetime(Date topicEndDatetime) {
        this.topicEndDatetime = topicEndDatetime;
    }

    public String getTopicLevel() {
        return topicLevel;
    }

    public void setTopicLevel(String topicLevel) {
        this.topicLevel = topicLevel == null ? null : topicLevel.trim();
    }

    public String getTopicCharge() {
        return topicCharge;
    }

    public void setTopicCharge(String topicCharge) {
        this.topicCharge = topicCharge == null ? null : topicCharge.trim();
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking == null ? null : ranking.trim();
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

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName == null ? null : topicName.trim();
    }

    public String getAwardsRank() {
        return awardsRank;
    }

    public void setAwardsRank(String awardsRank) {
        this.awardsRank = awardsRank == null ? null : awardsRank.trim();
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards == null ? null : awards.trim();
    }
}