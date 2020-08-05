package com.bazl.clims.model.po;


import com.bazl.clims.utils.JsonDatetimeSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * lims_person_info
 * 被鉴定人信息表
 *  Created by hj on 2018/12/25
 */
public class LimsPersonInfo {
    /**
     * 人员信息主键id
     */
    private String personId;

    /**
     * 案件信息主键id
     */
    private String caseId;

    /**
     * 委托信息主键id
     */
    private String consignmentId;

    /**
     * 人员编号
     */
    private String personNo;

    /**
     * 指纹编号
     */
    private String fingerprintNo;

    /**
     * 人员姓名
     */
    private String personName;

    /**
     * 人员类型
     */
    private String personType;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date createDatetime;

    /**
     * 创建人
     */
    private String createPerson;

    /**
     *更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date updateDatetime;

    /**
     * 更新人
     */
    private String updatePerson;

    /**
     * 删除标记
     */
    private String deleteFlag;

    /**
     * 删除时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date deleteDatetime;

    /**
     * 删除人
     */
    private String deletePerson;

    /**
     * 删除原因
     */
    private String deleteReason;

    /**
     * 备注
     */
    private String personRemark;

    /**
     * 人员详细表主键id
     */
    private String personDetailId;

    //人员性别
    private String personGender;
    //人员年龄
    private Short perosnAge;
    //人员身份证号
    private String personIdCard;
    //有无身份证号标识
    private String idCardFlag;
    //无身份证号描述
    private String noIdCardDesc;
    //亲缘关系
    private String relationType;

    //样本类型
    private String sampleType;
    //样本名称
    private String sampleName;
    //样本描述
    private String sampleDesc;
    //样本包装
    private String samplePacking;
    //提取时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date extractDatetime;
    //提取方法
    private String extractMethod;
    //送检目的
    private String samplePurpose;
    //人员地址
    private String personCurrentAddress;
    //人员身高
    private Double personHeight;
    //人员体重
    private Double personWeight;

    //样本id
    private String sampleId;

    //人员照片
    private String personFrontPicture;

    //人员照片存储路径
    private String personFrontPicturePath;

    //样本照片
    private String sampleDnaPicture;

    //样本照片路径
    private String sampleDnaPicturePath;

    private String personTypeName;

    private String relationTypeName;

    private String personGenderName;

    private String isRetain;

    private String refuseReason;

    private String sampleStatus;

    private String sampleCarrier;
    //人员样本list
    private List<LimsSampleInfoDna> sampleInfoDnaList;

    //在逃人员名称
    private String fugitivesName;
    //在逃人员身份证号
    private String fugitivesCard;
    /** 在逃编号 */
    private String fugitiveNo;

    //目标样本id
    private String targetPersonId;

    public List<LimsSampleInfoDna> getSampleInfoDnaList() {
        return sampleInfoDnaList;
    }

    public void setSampleInfoDnaList(List<LimsSampleInfoDna> sampleInfoDnaList) {
        this.sampleInfoDnaList = sampleInfoDnaList;
    }

    public String getSampleCarrier() {
        return sampleCarrier;
    }

    public void setSampleCarrier(String sampleCarrier) {
        this.sampleCarrier = sampleCarrier;
    }

    public String getIsRetain() {
        return isRetain;
    }

    public void setIsRetain(String isRetain) {
        this.isRetain = isRetain;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }

    public String getSampleStatus() {
        return sampleStatus;
    }

    public void setSampleStatus(String sampleStatus) {
        this.sampleStatus = sampleStatus;
    }

    public String getPersonGenderName() {
        return personGenderName;
    }

    public void setPersonGenderName(String personGenderName) {
        this.personGenderName = personGenderName;
    }

    public String getRelationTypeName() {
        return relationTypeName;
    }

    public void setRelationTypeName(String relationTypeName) {
        this.relationTypeName = relationTypeName;
    }

    public String getPersonTypeName() {
        return personTypeName;
    }

    public void setPersonTypeName(String personTypeName) {
        this.personTypeName = personTypeName;
    }

    public String getSampleDnaPicture() {
        return sampleDnaPicture;
    }

    public void setSampleDnaPicture(String sampleDnaPicture) {
        this.sampleDnaPicture = sampleDnaPicture;
    }

    public String getSampleDnaPicturePath() {
        return sampleDnaPicturePath;
    }

    public void setSampleDnaPicturePath(String sampleDnaPicturePath) {
        this.sampleDnaPicturePath = sampleDnaPicturePath;
    }

    public String getPersonFrontPicturePath() {
        return personFrontPicturePath;
    }

    public void setPersonFrontPicturePath(String personFrontPicturePath) {
        this.personFrontPicturePath = personFrontPicturePath;
    }

    public String getPersonFrontPicture() {
        return personFrontPicture;
    }

    public void setPersonFrontPicture(String personFrontPicture) {
        this.personFrontPicture = personFrontPicture;
    }

    public Double getPersonWeight() {
        return personWeight;
    }

    public void setPersonWeight(Double personWeight) {
        this.personWeight = personWeight;
    }

    public String getSampleId() {
        return sampleId;
    }

    public void setSampleId(String sampleId) {
        this.sampleId = sampleId;
    }

    public String getPersonCurrentAddress() {
        return personCurrentAddress;
    }

    public void setPersonCurrentAddress(String personCurrentAddress) {
        this.personCurrentAddress = personCurrentAddress;
    }

    public Double getPersonHeight() {
        return personHeight;
    }

    public void setPersonHeight(Double personHeight) {
        this.personHeight = personHeight;
    }

    public String getSampleType() {
        return sampleType;
    }

    public void setSampleType(String sampleType) {
        this.sampleType = sampleType;
    }

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public String getSampleDesc() {
        return sampleDesc;
    }

    public void setSampleDesc(String sampleDesc) {
        this.sampleDesc = sampleDesc;
    }

    public String getSamplePacking() {
        return samplePacking;
    }

    public void setSamplePacking(String samplePacking) {
        this.samplePacking = samplePacking;
    }

    public Date getExtractDatetime() {
        return extractDatetime;
    }

    public void setExtractDatetime(Date extractDatetime) {
        this.extractDatetime = extractDatetime;
    }

    public String getExtractMethod() {
        return extractMethod;
    }

    public void setExtractMethod(String extractMethod) {
        this.extractMethod = extractMethod;
    }

    public String getSamplePurpose() {
        return samplePurpose;
    }

    public void setSamplePurpose(String samplePurpose) {
        this.samplePurpose = samplePurpose;
    }

    public String getPersonGender() {
        return personGender;
    }

    public void setPersonGender(String personGender) {
        this.personGender = personGender;
    }

    public Short getPerosnAge() {
        return perosnAge;
    }

    public void setPerosnAge(Short perosnAge) {
        this.perosnAge = perosnAge;
    }

    public String getPersonIdCard() {
        return personIdCard;
    }

    public void setPersonIdCard(String personIdCard) {
        this.personIdCard = personIdCard;
    }

    public String getIdCardFlag() {
        return idCardFlag;
    }

    public void setIdCardFlag(String idCardFlag) {
        this.idCardFlag = idCardFlag;
    }

    public String getNoIdCardDesc() {
        return noIdCardDesc;
    }

    public void setNoIdCardDesc(String noIdCardDesc) {
        this.noIdCardDesc = noIdCardDesc;
    }

    public String getRelationType() {
        return relationType;
    }

    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getConsignmentId() {
        return consignmentId;
    }

    public void setConsignmentId(String consignmentId) {
        this.consignmentId = consignmentId;
    }

    public String getPersonNo() {
        return personNo;
    }

    public void setPersonNo(String personNo) {
        this.personNo = personNo;
    }

    public String getFingerprintNo() {
        return fingerprintNo;
    }

    public void setFingerprintNo(String fingerprintNo) {
        this.fingerprintNo = fingerprintNo;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
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
        this.createPerson = createPerson;
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
        this.updatePerson = updatePerson;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
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
        this.deletePerson = deletePerson;
    }

    public String getDeleteReason() {
        return deleteReason;
    }

    public void setDeleteReason(String deleteReason) {
        this.deleteReason = deleteReason;
    }

    public String getPersonRemark() {
        return personRemark;
    }

    public void setPersonRemark(String personRemark) {
        this.personRemark = personRemark;
    }

    public String getPersonDetailId() {
        return personDetailId;
    }

    public void setPersonDetailId(String personDetailId) {
        this.personDetailId = personDetailId;
    }

    public String getFugitivesName() {
        return fugitivesName;
    }

    public void setFugitivesName(String fugitivesName) {
        this.fugitivesName = fugitivesName;
    }

    public String getFugitivesCard() {
        return fugitivesCard;
    }

    public void setFugitivesCard(String fugitivesCard) {
        this.fugitivesCard = fugitivesCard;
    }

    public String getFugitiveNo() {
        return fugitiveNo;
    }

    public void setFugitiveNo(String fugitiveNo) {
        this.fugitiveNo = fugitiveNo;
    }

    public String getTargetPersonId() {
        return targetPersonId;
    }

    public void setTargetPersonId(String targetPersonId) {
        this.targetPersonId = targetPersonId;
    }
}
