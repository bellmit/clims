package com.bazl.clims.model.vo;

import com.bazl.clims.model.po.ComporeRelativeQueue;

import java.io.Serializable;

/**
 * Created by admin on 2019/8/6.
 */
public class ComporeRelativeQueueVo extends AbstractBaseVo<ComporeRelativeQueue> implements Serializable {

    public ComporeRelativeQueueVo() {
        super();
        this.entity = new ComporeRelativeQueue();
    }

    public ComporeRelativeQueueVo(ComporeRelativeQueue entity) {
        super();
        this.entity = entity;
    }

    private String sampleAName;

    private String sampleANo;

    private String sampleBName;

    private String sampleBNo;

    private String sampleNo;

    private String sampleName;

    public String getSampleAName() {
        return sampleAName;
    }

    public void setSampleAName(String sampleAName) {
        this.sampleAName = sampleAName;
    }

    public String getSampleANo() {
        return sampleANo;
    }

    public void setSampleANo(String sampleANo) {
        this.sampleANo = sampleANo;
    }

    public String getSampleBName() {
        return sampleBName;
    }

    public void setSampleBName(String sampleBName) {
        this.sampleBName = sampleBName;
    }

    public String getSampleBNo() {
        return sampleBNo;
    }

    public void setSampleBNo(String sampleBNo) {
        this.sampleBNo = sampleBNo;
    }

    public String getSampleNo() {
        return sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }
}
