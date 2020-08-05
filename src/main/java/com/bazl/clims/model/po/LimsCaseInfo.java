package com.bazl.clims.model.po;

import com.bazl.clims.utils.JsonDatetimeSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * lims_case_info
 * 案件信息表DNA
 *  Created by hj on 2018/12/24
 */
public class LimsCaseInfo {

    /**
     * 主键id
     */
    private String caseId;

    /**
     * 案件实验室编号
     */
    private String caseNo;

    /**
     * 专业类型
     */
    private String majorType;

    /**
     * 专业
     */
    private String majorNo;

    /**
     * 案件现勘编号
     */
    private String caseXkNo;

    /**
     * 案件名称
     */
    private String caseName;

    /**
     * 案件简要案情
     */
    private String caseBrief;

    /**
     * 案件案发地点
     */
    private String caseLocation;

    /**
     * 案件案发时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date caseDatetime;

    /**
     * 案件类型
     */
    private String caseType;

    /**
     * 案件性质
     */
    private String caseProperty;

    /**
     * 案件级别
     */
    private String caseLevel;

    /**
     * 案件备注
     */
    private String caseRemark;

    /**
     * 案件是否加急标记
     */
    private String caseUrgentFlag;

    /**
     * 案件状态
     */
    private String caseStatus;

    /**
     * 是否有补送标记
     */
    private String hasAppendFlag;

    /**
     *第一检验人
     */
    private String firstChecker;

    /**
     * 服务器
     */
    private String serverNo;

    /**
     * 入库状态
     */
    private String instoredFlag;

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
     * 删除状态标记
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
     * 是否命案标识
     */
    private String isHomicideFlag;

    /**
     * 是否刑事标识
     */
    private String isCriminalFlag;

    /**
     * 结果查询编号
     */
    private String resultQuerySn;

    /**
     * 结果查询验证码
     */
    private String resultQueryCaptcha;

    private String xkANo;

    private String consignationXkNo;

    /**
     * 业务字段接收前台传来选中下载中的code标识
     */
    private String codeParm;
    /**
     * 业务字段接收前台传来选中下载中的consignmentId委托id
     */
    private String consignmentId;
    /**
     * 业务字段接收前台传来的压缩包路径
     */
    private String zipFilePath;

    /**
     * 委托状态（区分现场非现场  0.现场 1.非现场）
     */
    private String entrustStatus;

    /**
     *入国家库系统编号
     */
    private String gjkSYSNo;

    /**
     * 入库时间
     */
    private Date instoredDatetime;

    private String acceptorName;

    public String getEntrustStatus() {
        return entrustStatus;
    }

    public void setEntrustStatus(String entrustStatus) {
        this.entrustStatus = entrustStatus;
    }


    public String getZipFilePath() {
        return zipFilePath;
    }

    public void setZipFilePath(String zipFilePath) {
        this.zipFilePath = zipFilePath;
    }

    public String getConsignmentId() {
        return consignmentId;
    }

    public void setConsignmentId(String consignmentId) {
        this.consignmentId = consignmentId;
    }

    public String getCodeParm() {
        return codeParm;
    }

    public void setCodeParm(String codeParm) {
        this.codeParm = codeParm;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getMajorType() {
        return majorType;
    }

    public void setMajorType(String majorType) {
        this.majorType = majorType;
    }

    public String getMajorNo() {
        return majorNo;
    }

    public void setMajorNo(String majorNo) {
        this.majorNo = majorNo;
    }

    public String getCaseXkNo() {
        return caseXkNo;
    }

    public void setCaseXkNo(String caseXkNo) {
        this.caseXkNo = caseXkNo;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getCaseBrief() {
        return caseBrief;
    }

    public void setCaseBrief(String caseBrief) {
        this.caseBrief = caseBrief;
    }

    public String getCaseLocation() {
        return caseLocation;
    }

    public void setCaseLocation(String caseLocation) {
        this.caseLocation = caseLocation;
    }

    public Date getCaseDatetime() {
        return caseDatetime;
    }

    public void setCaseDatetime(Date caseDatetime) {
        this.caseDatetime = caseDatetime;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String getCaseProperty() {
        return caseProperty;
    }

    public void setCaseProperty(String caseProperty) {
        this.caseProperty = caseProperty;
    }

    public String getCaseLevel() {
        return caseLevel;
    }

    public void setCaseLevel(String caseLevel) {
        this.caseLevel = caseLevel;
    }

    public String getCaseRemark() {
        return caseRemark;
    }

    public void setCaseRemark(String caseRemark) {
        this.caseRemark = caseRemark;
    }

    public String getCaseUrgentFlag() {
        return caseUrgentFlag;
    }

    public void setCaseUrgentFlag(String caseUrgentFlag) {
        this.caseUrgentFlag = caseUrgentFlag;
    }

    public String getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

    public String getHasAppendFlag() {
        return hasAppendFlag;
    }

    public void setHasAppendFlag(String hasAppendFlag) {
        this.hasAppendFlag = hasAppendFlag;
    }

    public String getFirstChecker() {
        return firstChecker;
    }

    public void setFirstChecker(String firstChecker) {
        this.firstChecker = firstChecker;
    }

    public String getServerNo() {
        return serverNo;
    }

    public void setServerNo(String serverNo) {
        this.serverNo = serverNo;
    }

    public String getInstoredFlag() {
        return instoredFlag;
    }

    public void setInstoredFlag(String instoredFlag) {
        this.instoredFlag = instoredFlag;
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

    public String getIsHomicideFlag() {
        return isHomicideFlag;
    }

    public void setIsHomicideFlag(String isHomicideFlag) {
        this.isHomicideFlag = isHomicideFlag;
    }

    public String getIsCriminalFlag() {
        return isCriminalFlag;
    }

    public void setIsCriminalFlag(String isCriminalFlag) {
        this.isCriminalFlag = isCriminalFlag;
    }

    public String getResultQuerySn() {
        return resultQuerySn;
    }

    public void setResultQuerySn(String resultQuerySn) {
        this.resultQuerySn = resultQuerySn;
    }

    public String getResultQueryCaptcha() {
        return resultQueryCaptcha;
    }

    public void setResultQueryCaptcha(String resultQueryCaptcha) {
        this.resultQueryCaptcha = resultQueryCaptcha;
    }

    public String getXkANo() {
        return xkANo;
    }

    public void setXkANo(String xkANo) {
        this.xkANo = xkANo;
    }

    public String getConsignationXkNo() {
        return consignationXkNo;
    }

    public void setConsignationXkNo(String consignationXkNo) {
        this.consignationXkNo = consignationXkNo;
    }

    public String getGjkSYSNo() {
        return gjkSYSNo;
    }

    public void setGjkSYSNo(String gjkSYSNo) {
        this.gjkSYSNo = gjkSYSNo;
    }

    public Date getInstoredDatetime() {
        return instoredDatetime;
    }

    public void setInstoredDatetime(Date instoredDatetime) {
        this.instoredDatetime = instoredDatetime;
    }

    public String getAcceptorName() {
        return acceptorName;
    }

    public void setAcceptorName(String acceptorName) {
        this.acceptorName = acceptorName;
    }
}



