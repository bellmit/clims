package com.bazl.clims.model.po;

import java.util.Date;

public class CompetencePost {
    private String id;

    private String personalId;

    private String positionalTitle;

    private Date acquisitionTime;

    private Date periodValidity;

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

    public String getPositionalTitle() {
        return positionalTitle;
    }

    public void setPositionalTitle(String positionalTitle) {
        this.positionalTitle = positionalTitle == null ? null : positionalTitle.trim();
    }

    public Date getAcquisitionTime() {
        return acquisitionTime;
    }

    public void setAcquisitionTime(Date acquisitionTime) {
        this.acquisitionTime = acquisitionTime;
    }

    public Date getPeriodValidity() {
        return periodValidity;
    }

    public void setPeriodValidity(Date periodValidity) {
        this.periodValidity = periodValidity;
    }
}