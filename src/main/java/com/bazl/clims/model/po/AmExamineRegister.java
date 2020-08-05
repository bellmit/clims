package com.bazl.clims.model.po;

import java.util.Date;

public class AmExamineRegister {
    private String id;

    private String branchName;

    private String officeStaff;

    private String existingProblem;

    private Long deductFraction;

    private String problemtypes;

    private String personalId;

    private String personalName;

    private Date enteringTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName == null ? null : branchName.trim();
    }

    public String getOfficeStaff() {
        return officeStaff;
    }

    public void setOfficeStaff(String officeStaff) {
        this.officeStaff = officeStaff == null ? null : officeStaff.trim();
    }

    public String getExistingProblem() {
        return existingProblem;
    }

    public void setExistingProblem(String existingProblem) {
        this.existingProblem = existingProblem == null ? null : existingProblem.trim();
    }

    public Long getDeductFraction() {
        return deductFraction;
    }

    public void setDeductFraction(Long deductFraction) {
        this.deductFraction = deductFraction;
    }

    public String getProblemtypes() {
        return problemtypes;
    }

    public void setProblemtypes(String problemtypes) {
        this.problemtypes = problemtypes == null ? null : problemtypes.trim();
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId == null ? null : personalId.trim();
    }

    public String getPersonalName() {
        return personalName;
    }

    public void setPersonalName(String personalName) {
        this.personalName = personalName == null ? null : personalName.trim();
    }

    public Date getEnteringTime() {
        return enteringTime;
    }

    public void setEnteringTime(Date enteringTime) {
        this.enteringTime = enteringTime;
    }
}