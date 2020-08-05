package com.bazl.clims.model.po;

public class MatchRelativeLib {
    private String id;

    private String sampleaId;

    private String samplebId;

    private String instoredType;

    private String sampleaRole;

    private String samplebRole;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSampleaId() {
        return sampleaId;
    }

    public void setSampleaId(String sampleaId) {
        this.sampleaId = sampleaId == null ? null : sampleaId.trim();
    }

    public String getSamplebId() {
        return samplebId;
    }

    public void setSamplebId(String samplebId) {
        this.samplebId = samplebId == null ? null : samplebId.trim();
    }

    public String getInstoredType() {
        return instoredType;
    }

    public void setInstoredType(String instoredType) {
        this.instoredType = instoredType == null ? null : instoredType.trim();
    }

    public String getSampleaRole() {
        return sampleaRole;
    }

    public void setSampleaRole(String sampleaRole) {
        this.sampleaRole = sampleaRole;
    }

    public String getSamplebRole() {
        return samplebRole;
    }

    public void setSamplebRole(String samplebRole) {
        this.samplebRole = samplebRole;
    }
}