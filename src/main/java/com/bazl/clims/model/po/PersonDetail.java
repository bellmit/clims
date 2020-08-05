package com.bazl.clims.model.po;

import com.bazl.clims.utils.JsonDatetimeSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 人员详情表
 * person_detail
 *  Created by hj on 2018/12/25
 */
public class PersonDetail {

    /**
     * 主键id
     */
    private String personDetailId;

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
     * 人员别名
     */
    private String personAlias;

    /**
     * 人员类型
     */
    private String personType;

    /**
     * 人员性别
     */
    private String personGender;

    /**
     * 人员年龄
     */
    private Short perosnAge;

    /**
     * 人员身高
     */
    private Double personHeight;

    /**
     * 人员体重
     */
    private Double personWeight;

    /**
     * 体表体征
     */
    private String personBodyFeature;

    /**
     * 是否有身份证标识
     */
    private String idCardFlag;

    /**
     * 人员身份证
     */
    private String personIdCard;

    /**
     * 无身份证描述
     */
    private String noIdCardDesc;

    /**
     * 人员现住址
     */
    private String personCurrentAddress;

    /**
     * 人员户籍地址
     */
    private String personResidenceAddress;

    /**
     * 人员民族
     */
    private String personRace;

    /**
     * 人员国籍
     */
    private String personNationality;

    /**
     * 生存状态
     */
    private String existFlag;

    /**
     * 人员出生日期
     */
    private Date personBornDate;

    /**
     * 人员死亡日期
     */
    private Date personDeathDate;

    /**
     * 人员正面照片
     */
    private String personFrontPicture;

    /**
     * 人员侧面照片
     */
    private String personSidePicture;

    /**
     * 备注
     */
    private String personRemark;

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
     * 更新时间
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

    private String personFrontPicturePath;

    public String getPersonFrontPicturePath() {
        return personFrontPicturePath;
    }

    public void setPersonFrontPicturePath(String personFrontPicturePath) {
        this.personFrontPicturePath = personFrontPicturePath;
    }

    public String getPersonDetailId() {
        return personDetailId;
    }

    public void setPersonDetailId(String personDetailId) {
        this.personDetailId = personDetailId;
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

    public String getPersonAlias() {
        return personAlias;
    }

    public void setPersonAlias(String personAlias) {
        this.personAlias = personAlias;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
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

    public Double getPersonHeight() {
        return personHeight;
    }

    public void setPersonHeight(Double personHeight) {
        this.personHeight = personHeight;
    }

    public Double getPersonWeight() {
        return personWeight;
    }

    public void setPersonWeight(Double personWeight) {
        this.personWeight = personWeight;
    }

    public String getPersonBodyFeature() {
        return personBodyFeature;
    }

    public void setPersonBodyFeature(String personBodyFeature) {
        this.personBodyFeature = personBodyFeature;
    }

    public String getIdCardFlag() {
        return idCardFlag;
    }

    public void setIdCardFlag(String idCardFlag) {
        this.idCardFlag = idCardFlag;
    }

    public String getPersonIdCard() {
        return personIdCard;
    }

    public void setPersonIdCard(String personIdCard) {
        this.personIdCard = personIdCard;
    }

    public String getNoIdCardDesc() {
        return noIdCardDesc;
    }

    public void setNoIdCardDesc(String noIdCardDesc) {
        this.noIdCardDesc = noIdCardDesc;
    }

    public String getPersonCurrentAddress() {
        return personCurrentAddress;
    }

    public void setPersonCurrentAddress(String personCurrentAddress) {
        this.personCurrentAddress = personCurrentAddress;
    }

    public String getPersonResidenceAddress() {
        return personResidenceAddress;
    }

    public void setPersonResidenceAddress(String personResidenceAddress) {
        this.personResidenceAddress = personResidenceAddress;
    }

    public String getPersonRace() {
        return personRace;
    }

    public void setPersonRace(String personRace) {
        this.personRace = personRace;
    }

    public String getPersonNationality() {
        return personNationality;
    }

    public void setPersonNationality(String personNationality) {
        this.personNationality = personNationality;
    }

    public String getExistFlag() {
        return existFlag;
    }

    public void setExistFlag(String existFlag) {
        this.existFlag = existFlag;
    }

    public Date getPersonBornDate() {
        return personBornDate;
    }

    public void setPersonBornDate(Date personBornDate) {
        this.personBornDate = personBornDate;
    }

    public Date getPersonDeathDate() {
        return personDeathDate;
    }

    public void setPersonDeathDate(Date personDeathDate) {
        this.personDeathDate = personDeathDate;
    }

    public String getPersonFrontPicture() {
        return personFrontPicture;
    }

    public void setPersonFrontPicture(String personFrontPicture) {
        this.personFrontPicture = personFrontPicture;
    }

    public String getPersonSidePicture() {
        return personSidePicture;
    }

    public void setPersonSidePicture(String personSidePicture) {
        this.personSidePicture = personSidePicture;
    }

    public String getPersonRemark() {
        return personRemark;
    }

    public void setPersonRemark(String personRemark) {
        this.personRemark = personRemark;
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
}
