package com.bazl.clims.model.vo;

import com.bazl.clims.model.po.LimsSampleGene;

import java.io.Serializable;

/**
 * @author wanghaiyang
 * @date 2019/2/27.
 */
public class LimsSampleGeneVo extends AbstractBaseVo<LimsSampleGene> implements Serializable {

    public LimsSampleGeneVo() {
        super();
        this.entity = new LimsSampleGene();
    }

    public LimsSampleGeneVo(LimsSampleGene entity) {
        super();
        this.entity = entity;
    }

    /** 样本编号 */
    private String sampleNo;

    /** 样本类型名称 */
    private String sampleTypeName;

    /** 样本名称 */
    private String sampleName;

    /** 颜色标记 */
    private String colorMark;

    /**关联检材编号*/
    private String relationSampleNo;
    /**是否关联*/
    private String isRelation;
    /**检材来源*/
    private String sampleSource;


    public String getRelationSampleNo() {
        return relationSampleNo;
    }

    public void setRelationSampleNo(String relationSampleNo) {
        this.relationSampleNo = relationSampleNo;
    }

    public String getIsRelation() {
        return isRelation;
    }

    public void setIsRelation(String isRelation) {
        this.isRelation = isRelation;
    }

    public String getSampleSource() {
        return sampleSource;
    }

    public void setSampleSource(String sampleSource) {
        this.sampleSource = sampleSource;
    }

    public String getSampleNo() {
        return sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }

    public String getSampleTypeName() {
        return sampleTypeName;
    }

    public void setSampleTypeName(String sampleTypeName) {
        this.sampleTypeName = sampleTypeName;
    }

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public String getColorMark() {
        return colorMark;
    }

    public void setColorMark(String colorMark) {
        this.colorMark = colorMark;
    }
}
