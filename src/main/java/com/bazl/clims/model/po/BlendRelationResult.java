package com.bazl.clims.model.po;

public class BlendRelationResult {
    private String id;

    private String blendId;

    private String sampleName;

    private String contributionName;

    private String contributionName2;

    private String testkitName;
    private String sampleId1;
    private String sampleId2;

    public String getSampleId1() {
        return sampleId1;
    }

    public void setSampleId1(String sampleId1) {
        this.sampleId1 = sampleId1;
    }

    public String getSampleId2() {
        return sampleId2;
    }

    public void setSampleId2(String sampleId2) {
        this.sampleId2 = sampleId2;
    }

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBlendId() {
        return blendId;
    }

    public void setBlendId(String blendId) {
        this.blendId = blendId == null ? null : blendId.trim();
    }



    public String getContributionName() {
        return contributionName;
    }

    public void setContributionName(String contributionName) {
        this.contributionName = contributionName == null ? null : contributionName.trim();
    }


    public String getContributionName2() {
        return contributionName2;
    }

    public void setContributionName2(String contributionName2) {
        this.contributionName2 = contributionName2 == null ? null : contributionName2.trim();
    }

    public String getTestkitName() {
        return testkitName;
    }

    public void setTestkitName(String testkitName) {
        this.testkitName = testkitName == null ? null : testkitName.trim();
    }
}