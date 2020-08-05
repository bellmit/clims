package com.bazl.clims.model.vo;

import com.bazl.clims.model.po.QueueSample;

import java.io.Serializable;

public class QueueSampleVo extends AbstractBaseVo<QueueSample> implements Serializable {

    public QueueSampleVo() {
        super();
        this.entity = new QueueSample();
    }

    public QueueSampleVo(QueueSample entity) {
        super();
        this.entity = entity;
    }

    /**
     * 案件名称
     * @return
     */
    private String caseName;

    /**
     * 案件编号
     * @return
     */
    private String caseNo;

    /**
     * 样本名称
     */
    private String sampleName;

    /**
     * 样本编号
     * @return
     */
    private String sampleNo;

    /**
     *板孔位置
     */
    private String boardNo;

    /**
     * 业务字段检材Id
     */
    private String sampleId;

    public String getSampleId() {
        return sampleId;
    }

    public void setSampleId(String sampleId) {
        this.sampleId = sampleId;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public String getSampleNo() {
        return sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }

    public String getBoardNo() {
        return boardNo;
    }

    public void setBoardNo(String boardNo) {
        this.boardNo = boardNo;
    }
}