package com.bazl.clims.model.vo;

import com.bazl.clims.model.po.LimsSampleGene;
import com.bazl.clims.model.po.LimsSampleInfoDna;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: chenzeliang
 * @Date: 2020/4/7 16:53
 * @Version: 1.0
 */
public class AddSampleVo implements Serializable {
    /** 板号 */
    private String boardNo;
    /*检材数量*/
    private int sampleCount;
    /*检材类型*/
    private String sampleType;
    /*检材信息集合*/
    private List<LimsSampleInfoDna> limsSampleInfoDnaList;
    /*分型信息集合*/
    private List<LimsSampleGene> LimsSampleGeneList;
    /*实验室id*/
    private String orgId;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getBoardNo() {
        return boardNo;
    }

    public void setBoardNo(String boardNo) {
        this.boardNo = boardNo;
    }

    public int getSampleCount() {
        return sampleCount;
    }

    public void setSampleCount(int sampleCount) {
        this.sampleCount = sampleCount;
    }

    public String getSampleType() {
        return sampleType;
    }

    public void setSampleType(String sampleType) {
        this.sampleType = sampleType;
    }

    public List<LimsSampleInfoDna> getLimsSampleInfoDnaList() {
        return limsSampleInfoDnaList;
    }

    public void setLimsSampleInfoDnaList(List<LimsSampleInfoDna> limsSampleInfoDnaList) {
        this.limsSampleInfoDnaList = limsSampleInfoDnaList;
    }

    public List<LimsSampleGene> getLimsSampleGeneList() {
        return LimsSampleGeneList;
    }

    public void setLimsSampleGeneList(List<LimsSampleGene> limsSampleGeneList) {
        LimsSampleGeneList = limsSampleGeneList;
    }
}
