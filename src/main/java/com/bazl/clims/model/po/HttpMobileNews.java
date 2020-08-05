package com.bazl.clims.model.po;

import com.bazl.clims.model.vo.AbstractBaseVo;

import java.io.Serializable;

public class HttpMobileNews implements Serializable {

    private String caseIdOrSampleNo; //案件或检材编号

    private String content; //消息内容

    private String caseId;

    private String sampleNo;

    private String type;  //1入库 2.比对

    private String userid;

    public String getSampleNo() {
        return sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }


    public String getCaseIdOrSampleNo() {
        return caseIdOrSampleNo;
    }

    public void setCaseIdOrSampleNo(String caseIdOrSampleNo) {
        this.caseIdOrSampleNo = caseIdOrSampleNo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "HttpMobileNews{" +
                "caseIdOrSampleNo='" + caseIdOrSampleNo + '\'' +
                ", content='" + content + '\'' +
                ", caseId='" + caseId + '\'' +
                ", sampleNo='" + sampleNo + '\'' +
                ", type='" + type + '\'' +
                ", userid='" + userid + '\'' +
                '}';
    }
}