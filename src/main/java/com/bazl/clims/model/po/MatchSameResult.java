package com.bazl.clims.model.po;

import com.bazl.clims.model.vo.AbstractBaseVo;
import com.bazl.clims.utils.JsonDatetimeSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class MatchSameResult extends AbstractBaseVo<MatchSameResult> {
    private String id;

    private String groupId;

    private String matchType;

    private String sampleaId;

    private String samplebId;

    private String sampleaNo;

    private String samplebNo;

    private String sampleaName;

    private String samplebName;

    private String caseaId;

    private String casebId;

    private String caseaName;

    private String casebName;

    private Integer sameCount;

    private Integer diffCount;

    private Double probability;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date createDatetime;

    private String createPerson;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date updateDatetime;

    private String updatePerson;

    private String deleteFlag;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date deleteDatetime;

    private String deletePerson;

    private String matchString;

    private LimsCaseInfo limsCaseInfoA;
    private AmPersonalInfo amPersonalInfoA;
    private LimsConsignmentInfo limsConsignmentInfoA;
    private LimsCaseInfo limsCaseInfoB;
    private AmPersonalInfo amPersonalInfoB;
    private LimsConsignmentInfo limsConsignmentInfoB;

    private String firstChecker;

    private String instoredType;

    private String sampleaType;

    private String samplebType;

    private String compareStatus;

    private String acceptAName;
    private String acceptBName;

    private String acceptOrgId;

    public String getCompareStatus() {
        return compareStatus;
    }

    public void setCompareStatus(String compareStatus) {
        this.compareStatus = compareStatus;
    }

    public String getSampleaType() {
        return sampleaType;
    }

    public void setSampleaType(String sampleaType) {
        this.sampleaType = sampleaType;
    }

    public String getSamplebType() {
        return samplebType;
    }

    public void setSamplebType(String samplebType) {
        this.samplebType = samplebType;
    }

    public String getFirstChecker() {
        return firstChecker;
    }

    public void setFirstChecker(String firstChecker) {
        this.firstChecker = firstChecker;
    }

    public String getInstoredType() {
        return instoredType;
    }

    public void setInstoredType(String instoredType) {
        this.instoredType = instoredType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }

    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType == null ? null : matchType.trim();
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

    public String getSampleaNo() {
        return sampleaNo;
    }

    public void setSampleaNo(String sampleaNo) {
        this.sampleaNo = sampleaNo == null ? null : sampleaNo.trim();
    }

    public String getSamplebNo() {
        return samplebNo;
    }

    public void setSamplebNo(String samplebNo) {
        this.samplebNo = samplebNo == null ? null : samplebNo.trim();
    }

    public String getSampleaName() {
        return sampleaName;
    }

    public void setSampleaName(String sampleaName) {
        this.sampleaName = sampleaName == null ? null : sampleaName.trim();
    }

    public String getSamplebName() {
        return samplebName;
    }

    public void setSamplebName(String samplebName) {
        this.samplebName = samplebName == null ? null : samplebName.trim();
    }

    public String getCaseaId() {
        return caseaId;
    }

    public void setCaseaId(String caseaId) {
        this.caseaId = caseaId == null ? null : caseaId.trim();
    }

    public String getCasebId() {
        return casebId;
    }

    public void setCasebId(String casebId) {
        this.casebId = casebId == null ? null : casebId.trim();
    }

    public String getCaseaName() {
        return caseaName;
    }

    public void setCaseaName(String caseaName) {
        this.caseaName = caseaName == null ? null : caseaName.trim();
    }

    public String getCasebName() {
        return casebName;
    }

    public void setCasebName(String casebName) {
        this.casebName = casebName == null ? null : casebName.trim();
    }

    public Integer getSameCount() {
        return sameCount;
    }

    public void setSameCount(Integer sameCount) {
        this.sameCount = sameCount;
    }

    public Integer getDiffCount() {
        return diffCount;
    }

    public void setDiffCount(Integer diffCount) {
        this.diffCount = diffCount;
    }

    public Double getProbability() {
        return probability;
    }

    public void setProbability(Double probability) {
        this.probability = probability;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson == null ? null : createPerson.trim();
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public String getUpdatePerson() {
        return updatePerson;
    }

    public void setUpdatePerson(String updatePerson) {
        this.updatePerson = updatePerson == null ? null : updatePerson.trim();
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag == null ? null : deleteFlag.trim();
    }

    public Date getDeleteDatetime() {
        return deleteDatetime;
    }

    public void setDeleteDatetime(Date deleteDatetime) {
        this.deleteDatetime = deleteDatetime;
    }

    public String getDeletePerson() {
        return deletePerson;
    }

    public void setDeletePerson(String deletePerson) {
        this.deletePerson = deletePerson == null ? null : deletePerson.trim();
    }

    public String getMatchString() {
        return matchString;
    }

    public void setMatchString(String matchString) {
        this.matchString = matchString == null ? null : matchString.trim();
    }

    public LimsCaseInfo getLimsCaseInfoA() {
        return limsCaseInfoA;
    }

    public void setLimsCaseInfoA(LimsCaseInfo limsCaseInfoA) {
        this.limsCaseInfoA = limsCaseInfoA;
    }

    public AmPersonalInfo getAmPersonalInfoA() {
        return amPersonalInfoA;
    }

    public void setAmPersonalInfoA(AmPersonalInfo amPersonalInfoA) {
        this.amPersonalInfoA = amPersonalInfoA;
    }

    public LimsConsignmentInfo getLimsConsignmentInfoA() {
        return limsConsignmentInfoA;
    }

    public void setLimsConsignmentInfoA(LimsConsignmentInfo limsConsignmentInfoA) {
        this.limsConsignmentInfoA = limsConsignmentInfoA;
    }

    public LimsCaseInfo getLimsCaseInfoB() {
        return limsCaseInfoB;
    }

    public void setLimsCaseInfoB(LimsCaseInfo limsCaseInfoB) {
        this.limsCaseInfoB = limsCaseInfoB;
    }

    public AmPersonalInfo getAmPersonalInfoB() {
        return amPersonalInfoB;
    }

    public void setAmPersonalInfoB(AmPersonalInfo amPersonalInfoB) {
        this.amPersonalInfoB = amPersonalInfoB;
    }

    public LimsConsignmentInfo getLimsConsignmentInfoB() {
        return limsConsignmentInfoB;
    }

    public void setLimsConsignmentInfoB(LimsConsignmentInfo limsConsignmentInfoB) {
        this.limsConsignmentInfoB = limsConsignmentInfoB;
    }

    public String getAcceptAName() {
        return acceptAName;
    }

    public void setAcceptAName(String acceptAName) {
        this.acceptAName = acceptAName;
    }

    public String getAcceptBName() {
        return acceptBName;
    }

    public void setAcceptBName(String acceptBName) {
        this.acceptBName = acceptBName;
    }

    public String getAcceptOrgId() {
        return acceptOrgId;
    }

    public void setAcceptOrgId(String acceptOrgId) {
        this.acceptOrgId = acceptOrgId;
    }
}