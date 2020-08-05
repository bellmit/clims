package com.bazl.clims.model.po;

import java.util.Date;

public class AmProficiencyTesting {
    private String id;

    private String testingYear;

    private String testingMajor;

    private Date testingDatetime;

    private String testingOrganizer;

    private String testingPerson;

    private String createPerson;

    private String updatePerson;

    private String deletePerson;

    private Date deleteDatetime;

    private Date createDatetime;

    private Date updateDatetime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTestingYear() {
        return testingYear;
    }

    public void setTestingYear(String testingYear) {
        this.testingYear = testingYear == null ? null : testingYear.trim();
    }

    public String getTestingMajor() {
        return testingMajor;
    }

    public void setTestingMajor(String testingMajor) {
        this.testingMajor = testingMajor == null ? null : testingMajor.trim();
    }

    public Date getTestingDatetime() {
        return testingDatetime;
    }

    public void setTestingDatetime(Date testingDatetime) {
        this.testingDatetime = testingDatetime;
    }

    public String getTestingOrganizer() {
        return testingOrganizer;
    }

    public void setTestingOrganizer(String testingOrganizer) {
        this.testingOrganizer = testingOrganizer == null ? null : testingOrganizer.trim();
    }

    public String getTestingPerson() {
        return testingPerson;
    }

    public void setTestingPerson(String testingPerson) {
        this.testingPerson = testingPerson == null ? null : testingPerson.trim();
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

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }
}