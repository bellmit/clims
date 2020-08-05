package com.bazl.clims.model.vo;

import com.bazl.clims.model.po.SampleInfo;

import java.io.Serializable;

/**
 * @Author: chnezeliang
 * @Date: 2020/3/30 17:13
 * @Version: 1.0
 */
public class SampleInfoVo extends AbstractBaseVo<SampleInfo> implements Serializable {
    public SampleInfoVo() {
        this.entity = new SampleInfo();
    }

    public SampleInfoVo(SampleInfo entity) {
        this.entity = entity;
    }

    private String holeNum;

    private String confirmatoryMethodName;

    private String preExperimentalMethodName;

    private String sampleTransferMethodName;

    private String elutionName;

    private String samplePropertyName;

    private String boardNoName;

    private String tabValue;

    private String stage;

    public String getHoleNum() {
        return holeNum;
    }

    public void setHoleNum(String holeNum) {
        this.holeNum = holeNum;
    }

    public String getConfirmatoryMethodName() {
        return confirmatoryMethodName;
    }

    public void setConfirmatoryMethodName(String confirmatoryMethodName) {
        this.confirmatoryMethodName = confirmatoryMethodName;
    }

    public String getPreExperimentalMethodName() {
        return preExperimentalMethodName;
    }

    public void setPreExperimentalMethodName(String preExperimentalMethodName) {
        this.preExperimentalMethodName = preExperimentalMethodName;
    }

    public String getSampleTransferMethodName() {
        return sampleTransferMethodName;
    }

    public void setSampleTransferMethodName(String sampleTransferMethodName) {
        this.sampleTransferMethodName = sampleTransferMethodName;
    }

    public String getElutionName() {
        return elutionName;
    }

    public void setElutionName(String elutionName) {
        this.elutionName = elutionName;
    }

    public String getSamplePropertyName() {
        return samplePropertyName;
    }

    public void setSamplePropertyName(String samplePropertyName) {
        this.samplePropertyName = samplePropertyName;
    }

    public String getBoardNoName() {
        return boardNoName;
    }

    public void setBoardNoName(String boardNoName) {
        this.boardNoName = boardNoName;
    }

    public String getTabValue() {
        return tabValue;
    }

    public void setTabValue(String tabValue) {
        this.tabValue = tabValue;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }
}
