package com.bazl.clims.model.bo;

import java.io.Serializable;

/**
 * Created by LX on 2019/9/18.
 */
public class CaseStatisticsModel implements Serializable {
    private String name;//受理人
    private int caseCount;//案件数
    private int complement;//补送案件数
    private int instoredCase;//入库案件数
    private int mathCount;//比中案件数
    private int acceptCount;//受理检材数
    private int auditCount;//复核检材数
    private int instoredSampl;//入库检材数
    private int sampleCount;//比中检材数

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCaseCount() {
        return caseCount;
    }

    public void setCaseCount(int caseCount) {
        this.caseCount = caseCount;
    }

    public int getComplement() {
        return complement;
    }

    public void setComplement(int complement) {
        this.complement = complement;
    }

    public int getInstoredCase() {
        return instoredCase;
    }

    public void setInstoredCase(int instoredCase) {
        this.instoredCase = instoredCase;
    }

    public int getMathCount() {
        return mathCount;
    }

    public void setMathCount(int mathCount) {
        this.mathCount = mathCount;
    }

    public int getAcceptCount() {
        return acceptCount;
    }

    public void setAcceptCount(int acceptCount) {
        this.acceptCount = acceptCount;
    }

    public int getAuditCount() {
        return auditCount;
    }

    public void setAuditCount(int auditCount) {
        this.auditCount = auditCount;
    }

    public int getInstoredSampl() {
        return instoredSampl;
    }

    public void setInstoredSampl(int instoredSampl) {
        this.instoredSampl = instoredSampl;
    }

    public int getSampleCount() {
        return sampleCount;
    }

    public void setSampleCount(int sampleCount) {
        this.sampleCount = sampleCount;
    }
}
