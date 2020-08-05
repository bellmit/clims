package com.bazl.clims.helper.codis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *@author wanghaiyang
 * @date 2019/5/14.
 */
public class CodisContentModel implements Serializable {
    private String sampleNo;

    private String importFlag;

    private String failedReason;

    private String geneInfo;

    private String geneid;

    public String getGeneid() {
        return geneid;
    }

    public void setGeneid(String geneid) {
        this.geneid = geneid;
    }

    public String getSampleNo() {
        return sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }

    public String getImportFlag() {
        return importFlag;
    }

    public void setImportFlag(String importFlag) {
        this.importFlag = importFlag;
    }

    public String getFailedReason() {
        return failedReason;
    }

    public void setFailedReason(String failedReason) {
        this.failedReason = failedReason;
    }

    public String getGeneInfo() {
        return geneInfo;
    }

    public void setGeneInfo(String geneInfo) {
        this.geneInfo = geneInfo;
    }
}
