package com.bazl.clims.model.po;

import java.util.Date;

public class AmPersonalCertificate {
    private String certificateId;

    private String personalId;

    private String certificateSn;

    private String certificateIssuedby;

    private Date certificateStarttime;

    private Date certificateEndtime;

    private Date createDatetime;

    private String createPerson;

    private String updatePerson;

    private Date updateDatetime;

    private String deletePerson;

    private Date deleteDatetime;

    private String certificatePictureurl;

    private String certificateName;

    private String certificatePicture;

    public String getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(String certificateId) {
        this.certificateId = certificateId == null ? null : certificateId.trim();
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId == null ? null : personalId.trim();
    }

    public String getCertificateSn() {
        return certificateSn;
    }

    public void setCertificateSn(String certificateSn) {
        this.certificateSn = certificateSn == null ? null : certificateSn.trim();
    }

    public String getCertificateIssuedby() {
        return certificateIssuedby;
    }

    public void setCertificateIssuedby(String certificateIssuedby) {
        this.certificateIssuedby = certificateIssuedby == null ? null : certificateIssuedby.trim();
    }

    public Date getCertificateStarttime() {
        return certificateStarttime;
    }

    public void setCertificateStarttime(Date certificateStarttime) {
        this.certificateStarttime = certificateStarttime;
    }

    public Date getCertificateEndtime() {
        return certificateEndtime;
    }

    public void setCertificateEndtime(Date certificateEndtime) {
        this.certificateEndtime = certificateEndtime;
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

    public String getCertificatePictureurl() {
        return certificatePictureurl;
    }

    public void setCertificatePictureurl(String certificatePictureurl) {
        this.certificatePictureurl = certificatePictureurl == null ? null : certificatePictureurl.trim();
    }

    public String getCertificateName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName == null ? null : certificateName.trim();
    }

    public String getCertificatePicture() {
        return certificatePicture;
    }

    public void setCertificatePicture(String certificatePicture) {
        this.certificatePicture = certificatePicture == null ? null : certificatePicture.trim();
    }
}