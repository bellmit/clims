package com.bazl.clims.model.po;

import java.util.Date;

public class PoliceExperience {
    private String id;

    private String personalId;

    private Date startTime;

    private Date endTime;

    private String whereWork;

    private String duties;

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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getWhereWork() {
        return whereWork;
    }

    public void setWhereWork(String whereWork) {
        this.whereWork = whereWork == null ? null : whereWork.trim();
    }

    public String getDuties() {
        return duties;
    }

    public void setDuties(String duties) {
        this.duties = duties == null ? null : duties.trim();
    }
}