package com.bazl.clims.model.po;

import com.bazl.clims.utils.JsonDatetimeSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * lims_sample_info_dna
 * 物证检材信息表
 *  Created by hj on 2018/12/24
 */
public class LimsSampleInfoDna implements Serializable {

    /**
     * 检材信息主键id
     */
    private String sampleId;

    /**
     * 主键id
     */
    private String consignmentId;

    /**
     * 案件信息主键id
     */
    private String caseId;

    /**
     * 物证信息主键id
     */
    private String evidenceId;

    /**
     * 物证信息编号
     */
    private String evidenceNo;

    /**
     * 检材编号
     */
    private String sampleNo;

    /**
     * 检材名称
     */
    private String sampleName;

    /**
     * 检材类型
     */
    private String sampleType;

    /**
     * 检材描述
     */
    private String sampleDesc;

    /**
     * 检材包装
     */
    private String samplePacking;

    /**
     * 检材载体（棉签、血卡）
     */
    private String sampleCarrier;

    /**
     * 提取时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date extractDatetime;
    private String extractTime;

    /**
     * 提取人
     */
    private String extractPerson;

    /**
     * 提取方法（粘取、剪等）
     */
    private String extractMethod;

    /**
     * 提取部位
     */
    private String extractPart;

    /**
     * 检材类别（0：物证，1：人员）
     */
    private String sampleFlag;

    /**
     * 人员或物证主键id
     */
    private String linkId;

    /**
     * 送检目的
     */
    private String samplePurpose;

    /**
     * 样本状态
     * 01:待受理
     * 02:已受理
     * 03:在检验
     * 04：已完成
     *
     */
    private String sampleStatus;

    /**
     * 入库标识（0：未入库，1：已入本地库）
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
     * 备注信息
     */
    private String sampleRemark;

    /**
     * 检材类型名称
     */
    private String sampleTypeName;

    //样本照片
    private String sampleDnaPicture;
    //样本照片路径
    private String sampleDnaPicturePath;

    private String personName;

    private String extractMethodName;

    private String samplePackingName;

    private String sampleIdwz;
    /**
     * 预实验方法1结果（FOB）
     */
    private String preMethod1Result;
    /**
     * 预实验方法2结果（PSA）
     */
    private String preMethod2Result;
    /**
     * 预实验方法3结果（联苯胺）
     */
    private String preMethod3Result;

    /**
     * 是否留存
     */
    private String isRetain;
    /**
     * 拒绝原因
     */
    private String refuseReason;

    /**
     * 物证检材照片
     */
    private String sampleMaterialPicture;
    /**
     * 物证检材照片路径
     */
    private String sampleMaterialPicturePath;

    private String personType;

    private String position;

    /**
     * 入库样本类型
     */
    private String instoredType;
    /**
     * 入库样本类型名称
     */
    private String instoredTypeName;

    /**
     * 入库时间
     */
    private Date instoredDatetime;

    /**
     * 入库人
     */
    private String instoredPerson;

    /**
     * 目标样本角色
     */
    private String targetSampleRole;

    /**
     *同组样本
     */
    private String sameGroupSample;

    /**
     *同组样本角色
     */
    private String sameGroupSampleRole;

    /**
     *入国家库系统编号
     */
    private String gjkSYSNo;

    /**审核的基因id*/
    private String auditedGeneId;

    /**是否中心取*/
    private String coreTakenStats;

    /**是否事主取*/
    private String coreVictimStats;

    /**
     * 物证序号
     */
    private Integer evidenceSerialNo;

    /**孔位*/
    private String boardPosition;

    /**峰图*/
    private String imagePath;


    /**关联检材编号*/
    private String relationSampleNo;
    /**是否关联*/
    private String isRelation;
    /**检材来源*/
    private String sampleSource;

    //亲缘关系
    private String relationShip;


    public String getRelationShip() {
        return relationShip;
    }

    public void setRelationShip(String relationShip) {
        this.relationShip = relationShip;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getRelationSampleNo() {
        return relationSampleNo;
    }

    public void setRelationSampleNo(String relationSampleNo) {
        this.relationSampleNo = relationSampleNo;
    }

    public String getIsRelation() {
        return isRelation;
    }

    public void setIsRelation(String isRelation) {
        this.isRelation = isRelation;
    }

    public String getSampleSource() {
        return sampleSource;
    }

    public void setSampleSource(String sampleSource) {
        this.sampleSource = sampleSource;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public String getSampleMaterialPicture() {
        return sampleMaterialPicture;
    }

    public void setSampleMaterialPicture(String sampleMaterialPicture) {
        this.sampleMaterialPicture = sampleMaterialPicture;
    }

    public String getSampleMaterialPicturePath() {
        return sampleMaterialPicturePath;
    }

    public void setSampleMaterialPicturePath(String sampleMaterialPicturePath) {
        this.sampleMaterialPicturePath = sampleMaterialPicturePath;
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

    public String getSampleIdwz() {
        return sampleIdwz;
    }

    public void setSampleIdwz(String sampleIdwz) {
        this.sampleIdwz = sampleIdwz;
    }

    public String getExtractMethodName() {
        return extractMethodName;
    }

    public void setExtractMethodName(String extractMethodName) {
        this.extractMethodName = extractMethodName;
    }

    public String getSamplePackingName() {
        return samplePackingName;
    }

    public void setSamplePackingName(String samplePackingName) {
        this.samplePackingName = samplePackingName;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
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

    public String getSampleTypeName() {
        return sampleTypeName;
    }

    public void setSampleTypeName(String sampleTypeName) {
        this.sampleTypeName = sampleTypeName;
    }

    public String getSampleId() {
        return sampleId;
    }

    public void setSampleId(String sampleId) {
        this.sampleId = sampleId;
    }

    public String getConsignmentId() {
        return consignmentId;
    }

    public void setConsignmentId(String consignmentId) {
        this.consignmentId = consignmentId;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getEvidenceId() {
        return evidenceId;
    }

    public void setEvidenceId(String evidenceId) {
        this.evidenceId = evidenceId;
    }

    public String getSampleNo() {
        return sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public String getSampleType() {
        return sampleType;
    }

    public void setSampleType(String sampleType) {
        this.sampleType = sampleType;
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

    public String getSampleCarrier() {
        return sampleCarrier;
    }

    public void setSampleCarrier(String sampleCarrier) {
        this.sampleCarrier = sampleCarrier;
    }

    public Date getExtractDatetime() {
        return extractDatetime;
    }

    public void setExtractDatetime(Date extractDatetime) {
        this.extractDatetime = extractDatetime;
    }

    public String getExtractPerson() {
        return extractPerson;
    }

    public void setExtractPerson(String extractPerson) {
        this.extractPerson = extractPerson;
    }

    public String getExtractMethod() {
        return extractMethod;
    }

    public void setExtractMethod(String extractMethod) {
        this.extractMethod = extractMethod;
    }

    public String getExtractPart() {
        return extractPart;
    }

    public void setExtractPart(String extractPart) {
        this.extractPart = extractPart;
    }

    public String getSampleFlag() {
        return sampleFlag;
    }

    public void setSampleFlag(String sampleFlag) {
        this.sampleFlag = sampleFlag;
    }

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    public String getSamplePurpose() {
        return samplePurpose;
    }

    public void setSamplePurpose(String samplePurpose) {
        this.samplePurpose = samplePurpose;
    }

    public String getSampleStatus() {
        return sampleStatus;
    }

    public void setSampleStatus(String sampleStatus) {
        this.sampleStatus = sampleStatus;
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

    public String getSampleRemark() {
        return sampleRemark;
    }

    public void setSampleRemark(String sampleRemark) {
        this.sampleRemark = sampleRemark;
    }

    public String getEvidenceNo() {
        return evidenceNo;
    }

    public void setEvidenceNo(String evidenceNo) {
        this.evidenceNo = evidenceNo;
    }

    public String getExtractTime() {
        return extractTime;
    }

    public void setExtractTime(String extractTime) {
        this.extractTime = extractTime;
    }

    public String getPreMethod1Result() {
        return preMethod1Result;
    }

    public void setPreMethod1Result(String preMethod1Result) {
        this.preMethod1Result = preMethod1Result;
    }

    public String getPreMethod2Result() {
        return preMethod2Result;
    }

    public void setPreMethod2Result(String preMethod2Result) {
        this.preMethod2Result = preMethod2Result;
    }

    public String getPreMethod3Result() {
        return preMethod3Result;
    }

    public void setPreMethod3Result(String preMethod3Result) {
        this.preMethod3Result = preMethod3Result;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getInstoredType() {
        return instoredType;
    }

    public void setInstoredType(String instoredType) {
        this.instoredType = instoredType;
    }

    public String getInstoredTypeName() {
        return instoredTypeName;
    }

    public void setInstoredTypeName(String instoredTypeName) {
        this.instoredTypeName = instoredTypeName;
    }

    public String getTargetSampleRole() {
        return targetSampleRole;
    }

    public void setTargetSampleRole(String targetSampleRole) {
        this.targetSampleRole = targetSampleRole;
    }

    public String getSameGroupSampleRole() {
        return sameGroupSampleRole;
    }

    public void setSameGroupSampleRole(String sameGroupSampleRole) {
        this.sameGroupSampleRole = sameGroupSampleRole;
    }

    public String getSameGroupSample() {
        return sameGroupSample;
    }

    public void setSameGroupSample(String sameGroupSample) {
        this.sameGroupSample = sameGroupSample;
    }

    public Date getInstoredDatetime() {
        return instoredDatetime;
    }

    public void setInstoredDatetime(Date instoredDatetime) {
        this.instoredDatetime = instoredDatetime;
    }

    public String getInstoredPerson() {
        return instoredPerson;
    }

    public void setInstoredPerson(String instoredPerson) {
        this.instoredPerson = instoredPerson;
    }

    public String getGjkSYSNo() {
        return gjkSYSNo;
    }

    public void setGjkSYSNo(String gjkSYSNo) {
        this.gjkSYSNo = gjkSYSNo;
    }

    public String getAuditedGeneId() {
        return auditedGeneId;
    }

    public void setAuditedGeneId(String auditedGeneId) {
        this.auditedGeneId = auditedGeneId;
    }

    public String getCoreTakenStats() {
        return coreTakenStats;
    }

    public void setCoreTakenStats(String coreTakenStats) {
        this.coreTakenStats = coreTakenStats;
    }

    public String getCoreVictimStats() {return coreVictimStats;}

    public void setCoreVictimStats(String coreVictimStats) {this.coreVictimStats = coreVictimStats;}


    public Integer getEvidenceSerialNo() {
        return evidenceSerialNo;
    }

    public void setEvidenceSerialNo(Integer evidenceSerialNo) {
        this.evidenceSerialNo = evidenceSerialNo;
    }

    public String getBoardPosition() {
        return boardPosition;
    }

    public void setBoardPosition(String boardPosition) {
        this.boardPosition = boardPosition;
    }
}



