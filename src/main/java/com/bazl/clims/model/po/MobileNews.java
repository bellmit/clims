package com.bazl.clims.model.po;

import com.bazl.clims.model.vo.AbstractBaseVo;

import java.io.Serializable;

public class MobileNews  extends AbstractBaseVo<MobileNews> implements Serializable {
    private String id;

    private String caseId;

    private String title;

    private String content;

    private Integer state;

    private String createDatetime;

    private String updateDatetime;

    private int type;

    private String userid;

    private String username;

    private String userOrg;

    private Integer messageType;

    @Override
    public String toString() {
        return "MobileNews{" +
                "id='" + id + '\'' +
                ", caseId='" + caseId + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", state=" + state +
                ", createDatetime='" + createDatetime + '\'' +
                ", updateDatetime='" + updateDatetime + '\'' +
                ", type=" + type +
                ", userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                ", userOrg='" + userOrg + '\'' +
                ", messageType='" + messageType + '\'' +
                '}';
    }

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public String getUserOrg() {
        return userOrg;
    }

    public void setUserOrg(String userOrg) {
        this.userOrg = userOrg;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(String createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(String updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}