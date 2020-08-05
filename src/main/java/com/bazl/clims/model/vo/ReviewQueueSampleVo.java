package com.bazl.clims.model.vo;

import com.bazl.clims.model.po.LimsCaseInfo;
import com.bazl.clims.model.po.ReviewQueueSample;
import com.bazl.clims.utils.JsonDatetimeSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/1/4.
 */
public class ReviewQueueSampleVo extends AbstractBaseVo<ReviewQueueSample> implements Serializable {

    public ReviewQueueSampleVo() {
        super();
        this.entity = new ReviewQueueSample();
    }

    public ReviewQueueSampleVo(ReviewQueueSample entity) {
        super();
        this.entity = entity;
    }

    private String sampleNo;

    private String sampleName;

    private String sampleTypeName;

    private String sampleType;

    private String taskStage;

    public String getTaskStage() {
        return taskStage;
    }

    public void setTaskStage(String taskStage) {
        this.taskStage = taskStage;
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

    public String getSampleTypeName() {
        return sampleTypeName;
    }

    public void setSampleTypeName(String sampleTypeName) {
        this.sampleTypeName = sampleTypeName;
    }

    public String getSampleType() {
        return sampleType;
    }

    public void setSampleType(String sampleType) {
        this.sampleType = sampleType;
    }
}
