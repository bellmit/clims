package com.bazl.clims.model.po;

public class caseFtpUrl {
    private String id; //主键

    private String caseId;//案件id

    private int type;//鉴定书管理类型1:卷皮,2:目录,3:委托书,4:聘书,5:检材流转记录表,6:预实验记录,7:受理确认书,8:提取记录,9:扩增记录,10:电泳分离记录,11:图谱,12:入库单,13:鉴定书,14:照片

    private String ftpUrl;//ftpUrl

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId == null ? null : caseId.trim();
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getFtpUrl() {
        return ftpUrl;
    }

    public void setFtpUrl(String ftpUrl) {
        this.ftpUrl = ftpUrl == null ? null : ftpUrl.trim();
    }
}