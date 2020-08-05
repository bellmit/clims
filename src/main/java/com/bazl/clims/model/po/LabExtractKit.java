package com.bazl.clims.model.po;

import java.util.Date;

/**
 * @author wanghaiyang
 * @date 2019/3/11.
 */
public class LabExtractKit {

    /** 主键ID */
    private String kitId;

    /** 批号 */
    private String batchNumber;

    /** 有效期 */
    private String termOfValidity;

    /**试剂盒ID*/
    private String panelid;

    public String getKitId() {
        return kitId;
    }

    public void setKitId(String kitId) {
        this.kitId = kitId;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public String getTermOfValidity() {
        return termOfValidity;
    }

    public void setTermOfValidity(String termOfValidity) {
        this.termOfValidity = termOfValidity;
    }

    public String getPanelid() {
        return panelid;
    }

    public void setPanelid(String panelid) {
        this.panelid = panelid;
    }
}