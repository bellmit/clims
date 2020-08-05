package com.bazl.clims.model.po;

import java.math.BigDecimal;

/**
 * @author wanghaiyang
 * @date 2019/3/29.
 */
public class SampleGeneInfo {

    /** 主键ID */
    private String id;

    /** 基因型主键ID */
    private String sampleGeneId;

    /** 基因座数目 */
    private BigDecimal locinum;

    /** 是否混合，0：不是，1：是 */
    private BigDecimal isMixture;

    /** 是否检出，0：未检出，1：检出 */
    private BigDecimal isDetected;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSampleGeneId() {
        return sampleGeneId;
    }

    public void setSampleGeneId(String sampleGeneId) {
        this.sampleGeneId = sampleGeneId == null ? null : sampleGeneId.trim();
    }

    public BigDecimal getLocinum() {
        return locinum;
    }

    public void setLocinum(BigDecimal locinum) {
        this.locinum = locinum;
    }

    public BigDecimal getIsMixture() {
        return isMixture;
    }

    public void setIsMixture(BigDecimal isMixture) {
        this.isMixture = isMixture;
    }

    public BigDecimal getIsDetected() {
        return isDetected;
    }

    public void setIsDetected(BigDecimal isDetected) {
        this.isDetected = isDetected;
    }
}