package com.bazl.clims.datamodel;

import java.util.Date;
import java.util.List;

/**
 * @author wanghaiyang
 * @date 2019/5/10.
 */
public class MatchSameResultModel {

    private Date sourceDate;

    private String sourceSampleNo;

    private String sourceEvidenceNo;

    private String sourceEvidenceName;

    private String sourceEvidenceId;

    private Double probability;

    private Integer sameCount;

    private List<TargetSameModel> targetSameModelList;

    public Date getSourceDate() {
        return sourceDate;
    }

    public void setSourceDate(Date sourceDate) {
        this.sourceDate = sourceDate;
    }

    public String getSourceSampleNo() {
        return sourceSampleNo;
    }

    public void setSourceSampleNo(String sourceSampleNo) {
        this.sourceSampleNo = sourceSampleNo;
    }

    public String getSourceEvidenceNo() {
        return sourceEvidenceNo;
    }

    public void setSourceEvidenceNo(String sourceEvidenceNo) {
        this.sourceEvidenceNo = sourceEvidenceNo;
    }

    public String getSourceEvidenceName() {
        return sourceEvidenceName;
    }

    public void setSourceEvidenceName(String sourceEvidenceName) {
        this.sourceEvidenceName = sourceEvidenceName;
    }

    public String getSourceEvidenceId() {
        return sourceEvidenceId;
    }

    public void setSourceEvidenceId(String sourceEvidenceId) {
        this.sourceEvidenceId = sourceEvidenceId;
    }

    public Double getProbability() {
        return probability;
    }

    public void setProbability(Double probability) {
        this.probability = probability;
    }

    public Integer getSameCount() {
        return sameCount;
    }

    public void setSameCount(Integer sameCount) {
        this.sameCount = sameCount;
    }

    public List<TargetSameModel> getTargetSameModelList() {
        return targetSameModelList;
    }

    public void setTargetSameModelList(List<TargetSameModel> targetSameModelList) {
        this.targetSameModelList = targetSameModelList;
    }
}
