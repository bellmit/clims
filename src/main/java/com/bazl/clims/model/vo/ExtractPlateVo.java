package com.bazl.clims.model.vo;

import com.bazl.clims.model.po.ExtractPlate;

import java.io.Serializable;

/**
 * @Author: chenzeliang
 * @Date: 2020/4/2 15:33
 * @Version: 1.0
 */
public class ExtractPlateVo extends AbstractBaseVo<ExtractPlate> implements Serializable {
    public ExtractPlateVo() {
        this.entity = new ExtractPlate();
    }

    public ExtractPlateVo(ExtractPlate entity) {
        this.entity = entity;
    }

    private String pcrPlateName;

    private String sampleTableId;

    private String sampleNo;

    public String getPcrPlateName() {
        return pcrPlateName;
    }

    public void setPcrPlateName(String pcrPlateName) {
        this.pcrPlateName = pcrPlateName;
    }

    public String getSampleTableId() {
        return sampleTableId;
    }

    public void setSampleTableId(String sampleTableId) {
        this.sampleTableId = sampleTableId;
    }

    public String getSampleNo() {
        return sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }
}
