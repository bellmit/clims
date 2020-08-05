package com.bazl.clims.model.po;

import java.util.Date;

/**
 * @author wanghaiyang
 * @date 2019/3/11.
 */
public class Panel {

    /** 主键ID */
    private String panelid;

    /** 名称 */
    private String panelname;

    /**  */
    private String producer;

    /**版本*/
    private int version;

    /**创建时间*/
    private Date createtime;

    /***/
    private String comments;

    /**试剂盒有效期*/
    private LabExtractKit labExtractKit;

    public String getPanelid() {
        return panelid;
    }

    public void setPanelid(String panelid) {
        this.panelid = panelid;
    }

    public String getPanelname() {
        return panelname;
    }

    public void setPanelname(String panelname) {
        this.panelname = panelname;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public LabExtractKit getLabExtractKit() {
        return labExtractKit;
    }

    public void setLabExtractKit(LabExtractKit labExtractKit) {
        this.labExtractKit = labExtractKit;
    }
}