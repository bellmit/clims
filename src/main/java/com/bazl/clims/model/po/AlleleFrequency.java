package com.bazl.clims.model.po;

import java.util.Date;

public class AlleleFrequency {
    private String id;

    private String raceId;

    private String raceName;

    private String markerName;

    private String alleleName;

    private Double alleleFrequency;

    private String createPerson;

    private Date createDatetime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRaceId() {
        return raceId;
    }

    public void setRaceId(String raceId) {
        this.raceId = raceId == null ? null : raceId.trim();
    }

    public String getRaceName() {
        return raceName;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName == null ? null : raceName.trim();
    }

    public String getMarkerName() {
        return markerName;
    }

    public void setMarkerName(String markerName) {
        this.markerName = markerName == null ? null : markerName.trim();
    }

    public String getAlleleName() {
        return alleleName;
    }

    public void setAlleleName(String alleleName) {
        this.alleleName = alleleName == null ? null : alleleName.trim();
    }

    public Double getAlleleFrequency() {
        return alleleFrequency;
    }

    public void setAlleleFrequency(Double alleleFrequency) {
        this.alleleFrequency = alleleFrequency;
    }

    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson == null ? null : createPerson.trim();
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }
}