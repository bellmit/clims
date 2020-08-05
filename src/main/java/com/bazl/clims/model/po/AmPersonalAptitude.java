package com.bazl.clims.model.po;

import java.util.Date;

public class AmPersonalAptitude {
    private String personalId;

    private String aptitudeId;

    private String aptitudeSn;

    private String aptitudeMajor;

    private String aptitudeIssuedby;

    private Date aptitudeStarttime;

    private Date aptitudeEndtime;

    private Date createDatetime;

    private String createPerson;

    private String updatePerson;

    private Date updateDatetime;

    private String deletePerson;

    private Date deleteDatetime;

    private String aptitudePictureurl;

    private String aptitudePicture;

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId == null ? null : personalId.trim();
    }

    public String getAptitudeId() {
        return aptitudeId;
    }

    public void setAptitudeId(String aptitudeId) {
        this.aptitudeId = aptitudeId == null ? null : aptitudeId.trim();
    }

    public String getAptitudeSn() {
        return aptitudeSn;
    }

    public void setAptitudeSn(String aptitudeSn) {
        this.aptitudeSn = aptitudeSn == null ? null : aptitudeSn.trim();
    }

    public String getAptitudeMajor() {
        return aptitudeMajor;
    }

    public void setAptitudeMajor(String aptitudeMajor) {
        this.aptitudeMajor = aptitudeMajor == null ? null : aptitudeMajor.trim();
    }

    public String getAptitudeIssuedby() {
        return aptitudeIssuedby;
    }

    public void setAptitudeIssuedby(String aptitudeIssuedby) {
        this.aptitudeIssuedby = aptitudeIssuedby == null ? null : aptitudeIssuedby.trim();
    }

    public Date getAptitudeStarttime() {
        return aptitudeStarttime;
    }

    public void setAptitudeStarttime(Date aptitudeStarttime) {
        this.aptitudeStarttime = aptitudeStarttime;
    }

    public Date getAptitudeEndtime() {
        return aptitudeEndtime;
    }

    public void setAptitudeEndtime(Date aptitudeEndtime) {
        this.aptitudeEndtime = aptitudeEndtime;
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

    public String getUpdatePerson() {
        return updatePerson;
    }

    public void setUpdatePerson(String updatePerson) {
        this.updatePerson = updatePerson == null ? null : updatePerson.trim();
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public String getDeletePerson() {
        return deletePerson;
    }

    public void setDeletePerson(String deletePerson) {
        this.deletePerson = deletePerson == null ? null : deletePerson.trim();
    }

    public Date getDeleteDatetime() {
        return deleteDatetime;
    }

    public void setDeleteDatetime(Date deleteDatetime) {
        this.deleteDatetime = deleteDatetime;
    }

    public String getAptitudePictureurl() {
        return aptitudePictureurl;
    }

    public void setAptitudePictureurl(String aptitudePictureurl) {
        this.aptitudePictureurl = aptitudePictureurl == null ? null : aptitudePictureurl.trim();
    }

    public String getAptitudePicture() {
        return aptitudePicture;
    }

    public void setAptitudePicture(String aptitudePicture) {
        this.aptitudePicture = aptitudePicture == null ? null : aptitudePicture.trim();
    }
}