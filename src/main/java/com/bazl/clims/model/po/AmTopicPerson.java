package com.bazl.clims.model.po;

import java.math.BigDecimal;
import java.util.Date;

public class AmTopicPerson {
    private String id;

    private String topicRecordId;

    private String personName;

    private BigDecimal personRanking;

    private Date createDatetime;

    private String createPerson;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTopicRecordId() {
        return topicRecordId;
    }

    public void setTopicRecordId(String topicRecordId) {
        this.topicRecordId = topicRecordId == null ? null : topicRecordId.trim();
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName == null ? null : personName.trim();
    }

    public BigDecimal getPersonRanking() {
        return personRanking;
    }

    public void setPersonRanking(BigDecimal personRanking) {
        this.personRanking = personRanking;
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
}