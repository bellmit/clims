package com.bazl.clims.model.po;

import java.util.Date;

public class AmPaperInfo {
    private String id;

    private String personalId;

    private String paperSubject;

    private String paperContent;

    private Date publishDatetime;

    private Date createDatetime;

    private String createPerson;

    private Date updateDatetime;

    private String updatePerson;

    private String deleteFlag;

    private Date deleteDatetime;

    private String deletePerson;

    private String paperIssue;

    private String issueName;

    private String paperKind;

    private String paperRank;

    private Long writerOrder;

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

    public String getPaperSubject() {
        return paperSubject;
    }

    public void setPaperSubject(String paperSubject) {
        this.paperSubject = paperSubject == null ? null : paperSubject.trim();
    }

    public String getPaperContent() {
        return paperContent;
    }

    public void setPaperContent(String paperContent) {
        this.paperContent = paperContent == null ? null : paperContent.trim();
    }

    public Date getPublishDatetime() {
        return publishDatetime;
    }

    public void setPublishDatetime(Date publishDatetime) {
        this.publishDatetime = publishDatetime;
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

    public String getPaperIssue() {
        return paperIssue;
    }

    public void setPaperIssue(String paperIssue) {
        this.paperIssue = paperIssue == null ? null : paperIssue.trim();
    }

    public String getIssueName() {
        return issueName;
    }

    public void setIssueName(String issueName) {
        this.issueName = issueName == null ? null : issueName.trim();
    }

    public String getPaperKind() {
        return paperKind;
    }

    public void setPaperKind(String paperKind) {
        this.paperKind = paperKind == null ? null : paperKind.trim();
    }

    public String getPaperRank() {
        return paperRank;
    }

    public void setPaperRank(String paperRank) {
        this.paperRank = paperRank == null ? null : paperRank.trim();
    }

    public Long getWriterOrder() {
        return writerOrder;
    }

    public void setWriterOrder(Long writerOrder) {
        this.writerOrder = writerOrder;
    }
}