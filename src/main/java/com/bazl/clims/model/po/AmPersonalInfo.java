package com.bazl.clims.model.po;

import com.bazl.clims.utils.JsonDatetimeSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 人员信息表
 * AM_PERSONAL_INFO
 * Created by Administrator on 2018/12/21.
 */

public class AmPersonalInfo implements Serializable{

    /**
     * 用户主键id
     */
    private String personalId;
    /**
     * 照片
     */
    private String picture;
    /**
     * 姓名
     */
    private String fullName;
    /**
     * 性别
     */
    private String gender;
    /**
     * 出生日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date bornDate;
    /**
     * 身份证号
     */
    private String idCard;
    /**
     * 级别
     */
    private String level;
    /**
     * 民族
     */
    private String race;
    /**
     * 政治面貌
     */
    private String politicalOutlook;
    /**
     * 户籍所在地
     */
    private String residenceAddress;
    /**
     * 职务
     */
    private String position;
    /**
     * 警号
     */
    private String policeNo;
    /**
     * 证件类型
     */
    private String paperworkType;
    /**
     * 证件号
     */
    private String paperworkNo	;
    /**
     * 电话号码
     */
    private String phoneNumber;
    /**
     * 用户所属单位主键编号
     */
    private String orgId;
    /**
     * 用户所属单位电话
     */
    private String orgPhone;
    /**
     * 专业技术任职资格等级
     */
    private String qualificationLevel;
    /**
     * 参加工作时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date startWorkingHours;
    /**
     * 入警时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date alarmTime;
    /**
     * 最高职称
     */
    private String highestTitle;
    /**
     * 职称获取时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date titleGainTime;
    /**
     * 职称有效期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date titleValidity;
    /**
     * 职称照片（限制500k）
     */
    private String titlePicture;
    /**
     * 现从事的警务工作
     */
    private String workNow;
    /**
     * 从事相关工作年限
     */
    private String workYears;
    /**
     * 专业考试通过年度
     */
    private String majorAdoptYears;
    /**
     * 专业考试分数
     */
    private String majorAdoptScore;
    /**
     * 鉴定人资质
     */
    private String appraiserAptitude;

    /**
     * 业务字段 登录名
     */
    private String loginName;
    /**
     * 业务字段 登录密码
     */
    private String loginPassword;
    /**
     * 业务字段权限名称
     */
    private String roleName;
    /**
     * 业务字段权限Id
     */
    private String roleId;
    /**
     * 业务字段用户是否有效ACTIVE_FLAG
     */
    private String activeFlag;

    public String getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(String activeFlag) {
        this.activeFlag = activeFlag;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getPoliticalOutlook() {
        return politicalOutlook;
    }

    public void setPoliticalOutlook(String politicalOutlook) {
        this.politicalOutlook = politicalOutlook;
    }

    public String getResidenceAddress() {
        return residenceAddress;
    }

    public void setResidenceAddress(String residenceAddress) {
        this.residenceAddress = residenceAddress;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPoliceNo() {
        return policeNo;
    }

    public void setPoliceNo(String policeNo) {
        this.policeNo = policeNo;
    }

    public String getPaperworkType() {
        return paperworkType;
    }

    public void setPaperworkType(String paperworkType) {
        this.paperworkType = paperworkType;
    }

    public String getPaperworkNo() {
        return paperworkNo;
    }

    public void setPaperworkNo(String paperworkNo) {
        this.paperworkNo = paperworkNo;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgPhone() {
        return orgPhone;
    }

    public void setOrgPhone(String orgPhone) {
        this.orgPhone = orgPhone;
    }

    public String getQualificationLevel() {
        return qualificationLevel;
    }

    public void setQualificationLevel(String qualificationLevel) {
        this.qualificationLevel = qualificationLevel;
    }

    public Date getStartWorkingHours() {
        return startWorkingHours;
    }

    public void setStartWorkingHours(Date startWorkingHours) {
        this.startWorkingHours = startWorkingHours;
    }

    public Date getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(Date alarmTime) {
        this.alarmTime = alarmTime;
    }

    public String getHighestTitle() {
        return highestTitle;
    }

    public void setHighestTitle(String highestTitle) {
        this.highestTitle = highestTitle;
    }

    public Date getTitleGainTime() {
        return titleGainTime;
    }

    public void setTitleGainTime(Date titleGainTime) {
        this.titleGainTime = titleGainTime;
    }

    public Date getTitleValidity() {
        return titleValidity;
    }

    public void setTitleValidity(Date titleValidity) {
        this.titleValidity = titleValidity;
    }

    public String getTitlePicture() {
        return titlePicture;
    }

    public void setTitlePicture(String titlePicture) {
        this.titlePicture = titlePicture;
    }

    public String getWorkNow() {
        return workNow;
    }

    public void setWorkNow(String workNow) {
        this.workNow = workNow;
    }

    public String getWorkYears() {
        return workYears;
    }

    public void setWorkYears(String workYears) {
        this.workYears = workYears;
    }

    public String getMajorAdoptYears() {
        return majorAdoptYears;
    }

    public void setMajorAdoptYears(String majorAdoptYears) {
        this.majorAdoptYears = majorAdoptYears;
    }

    public String getMajorAdoptScore() {
        return majorAdoptScore;
    }

    public void setMajorAdoptScore(String majorAdoptScore) {
        this.majorAdoptScore = majorAdoptScore;
    }

    public String getAppraiserAptitude() {
        return appraiserAptitude;
    }

    public void setAppraiserAptitude(String appraiserAptitude) {
        this.appraiserAptitude = appraiserAptitude;
    }
}
