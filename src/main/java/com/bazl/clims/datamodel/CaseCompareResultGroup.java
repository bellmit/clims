package com.bazl.clims.datamodel;

import com.bazl.clims.model.po.MatchAuditedGene;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2019/4/3.
 */
public class CaseCompareResultGroup {

    private int groupId;

    private String referenceType;

    private List<MatchAuditedGene> matchedSampleList;

    private List<MatchAuditedGene> mixMatchedSampleList;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getReferenceType() {
        return referenceType;
    }

    public void setReferenceType(String referenceType) {
        this.referenceType = referenceType;
    }

    public List<MatchAuditedGene> getMatchedSampleList() {
        return matchedSampleList;
    }

    public void setMatchedSampleList(List<MatchAuditedGene> matchedSampleList) {
        this.matchedSampleList = matchedSampleList;
    }

    public List<MatchAuditedGene> getMixMatchedSampleList() {
        return mixMatchedSampleList;
    }

    public void setMixMatchedSampleList(List<MatchAuditedGene> mixMatchedSampleList) {
        this.mixMatchedSampleList = mixMatchedSampleList;
    }
}
