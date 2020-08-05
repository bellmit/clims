package com.bazl.clims.model.po;

import com.bazl.clims.utils.JsonDatetimeSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 委托信息表
 * lims_consignment_info
 * Created by hj on 2018/12/24
 */
public class LimsConsignmentInfo {

    /**
     * 主键id
     */
    private String consignmentId;

    /**
     * 案件ID
     */
    private String caseId;

    /**
     * 服务器编号
     */
    private String serverNo;

    /**
     * 委托编号
     */
    private String consignmentNo;

    /**
     *补送标记
     */
    private String appendFlag;

    /**
     * 委托单位编号
     */
    private String delegateOrgCode;

    /**
     *委托单位名称
     */
    private String delegateOrgName;

    /**
     * 委托人1 id
     */
    private String delegator1Id;

    /**
     * 委托人2 id
     */
    private String delegator2Id;

    /**
     * 委托人1 姓名
     */
    private String delegator1Name;

    /**
     * 委托人2 x姓名
     */
    private String delegator2Name;

    /**
     * 委托人1职务
     */
    private String delegator1Position;

    /**
     * 委托人2职务
     */
    private String delegator2Position;

    /**
     * 委托人1证件类型
     */
    private String delegator1PaperworkType;

    /**
     * 委托人2证件类型
     */
    private String delegator2PaperworkType;

    /**
     *委托人1证件号
     */
    private String delegator1PaperworkNo;

    /**
     * 委托人2证件号
     */
    private String delegator2PaperworkNo;

    /**
     *委托人1电话
     */
    private String delegator1PhoneNumber;

    /**
     * 委托人2电话
     */
    private String delegator2PhoneNumber;

    /**
     * 委托时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date delegateDatetime;

    /**
     * 通讯地址
     */
    private String delegateOrgAddress;

    /**
     * 邮政编码
     */
    private String delegateOrgZipCode;

    /**
     * 传真号码
     */
    private String delegateOrgFaxNo;

    /**
     *鉴定要求
     */
    private String identifyRequirement;

    /**
     * 原鉴定情况
     */
    private String preIdentifyDesc;

    /**
     * 重新鉴定原因
     */
    private String reidentifyReason;

    /**
     * 重新鉴定次数
     */
    private Short reidentifyCount;

    /**
     * 委托状态
     */
    private String status;

    /**
     * 鉴定中心主键id
     */
    private String acceptOrgId;

    /**
     * 鉴定类别
     */
    private String identifyType;

    /**
     *鉴定项目
     */
    private String identifyItem;

    /**
     * 受理人主键id
     */
    private String acceptorId;

    /**
     * 受理时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date acceptDatetime;

    /**
     * 回退原因
     */
    private String refuseReason;

    /**
     * 回退时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date refuseDatetime;

    /**
     * 回退人
     */
    private String refusePerson;

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
     *更新人
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
     * 是否转送标识
     */
    private String transferFlag;

    /**
     * 转送案件的原专业consignment_id
     */
    private String transferBaseId;

    /**
     * 所属辖区code
     */
    private String areaOrgCode;
    private String areaOrgName;

    /**
     * 签名照片
     */
    private String autographPicture;
    /**
     * 签名照片路径
     */
    private String autographPicturePath;

    /**
     * 取走人
     */
    private String takePerson;

    public String getTakePerson() {
        return takePerson;
    }

    public void setTakePerson(String takePerson) {
        this.takePerson = takePerson;
    }

    public String getAutographPicture() {
        return autographPicture;
    }

    public void setAutographPicture(String autographPicture) {
        this.autographPicture = autographPicture;
    }

    public String getAutographPicturePath() {
        return autographPicturePath;
    }

    public void setAutographPicturePath(String autographPicturePath) {
        this.autographPicturePath = autographPicturePath;
    }

    public String getAreaOrgCode() {
        return areaOrgCode;
    }

    public void setAreaOrgCode(String areaOrgCode) {
        this.areaOrgCode = areaOrgCode;
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

    public String getServerNo() {
        return serverNo;
    }

    public void setServerNo(String serverNo) {
        this.serverNo = serverNo;
    }

    public String getConsignmentNo() {
        return consignmentNo;
    }

    public void setConsignmentNo(String consignmentNo) {
        this.consignmentNo = consignmentNo;
    }

    public String getAppendFlag() {
        return appendFlag;
    }

    public void setAppendFlag(String appendFlag) {
        this.appendFlag = appendFlag;
    }

    public String getDelegateOrgCode() {
        return delegateOrgCode;
    }

    public void setDelegateOrgCode(String delegateOrgCode) {
        this.delegateOrgCode = delegateOrgCode;
    }

    public String getDelegateOrgName() {
        return delegateOrgName;
    }

    public void setDelegateOrgName(String delegateOrgName) {
        this.delegateOrgName = delegateOrgName;
    }

    public String getDelegator1Id() {
        return delegator1Id;
    }

    public void setDelegator1Id(String delegator1Id) {
        this.delegator1Id = delegator1Id;
    }

    public String getDelegator2Id() {
        return delegator2Id;
    }

    public void setDelegator2Id(String delegator2Id) {
        this.delegator2Id = delegator2Id;
    }

    public String getDelegator1Name() {
        return delegator1Name;
    }

    public void setDelegator1Name(String delegator1Name) {
        this.delegator1Name = delegator1Name;
    }

    public String getDelegator2Name() {
        return delegator2Name;
    }

    public void setDelegator2Name(String delegator2Name) {
        this.delegator2Name = delegator2Name;
    }

    public String getDelegator1Position() {
        return delegator1Position;
    }

    public void setDelegator1Position(String delegator1Position) {
        this.delegator1Position = delegator1Position;
    }

    public String getDelegator2Position() {
        return delegator2Position;
    }

    public void setDelegator2Position(String delegator2Position) {
        this.delegator2Position = delegator2Position;
    }

    public String getDelegator1PaperworkType() {
        return delegator1PaperworkType;
    }

    public void setDelegator1PaperworkType(String delegator1PaperworkType) {
        this.delegator1PaperworkType = delegator1PaperworkType;
    }

    public String getDelegator2PaperworkType() {
        return delegator2PaperworkType;
    }

    public void setDelegator2PaperworkType(String delegator2PaperworkType) {
        this.delegator2PaperworkType = delegator2PaperworkType;
    }

    public String getDelegator1PaperworkNo() {
        return delegator1PaperworkNo;
    }

    public void setDelegator1PaperworkNo(String delegator1PaperworkNo) {
        this.delegator1PaperworkNo = delegator1PaperworkNo;
    }

    public String getDelegator2PaperworkNo() {
        return delegator2PaperworkNo;
    }

    public void setDelegator2PaperworkNo(String delegator2PaperworkNo) {
        this.delegator2PaperworkNo = delegator2PaperworkNo;
    }

    public String getDelegator1PhoneNumber() {
        return delegator1PhoneNumber;
    }

    public void setDelegator1PhoneNumber(String delegator1PhoneNumber) {
        this.delegator1PhoneNumber = delegator1PhoneNumber;
    }

    public String getDelegator2PhoneNumber() {
        return delegator2PhoneNumber;
    }

    public void setDelegator2PhoneNumber(String delegator2PhoneNumber) {
        this.delegator2PhoneNumber = delegator2PhoneNumber;
    }

    public Date getDelegateDatetime() {
        return delegateDatetime;
    }

    public void setDelegateDatetime(Date delegateDatetime) {
        this.delegateDatetime = delegateDatetime;
    }

    public String getDelegateOrgAddress() {
        return delegateOrgAddress;
    }

    public void setDelegateOrgAddress(String delegateOrgAddress) {
        this.delegateOrgAddress = delegateOrgAddress;
    }

    public String getDelegateOrgZipCode() {
        return delegateOrgZipCode;
    }

    public void setDelegateOrgZipCode(String delegateOrgZipCode) {
        this.delegateOrgZipCode = delegateOrgZipCode;
    }

    public String getDelegateOrgFaxNo() {
        return delegateOrgFaxNo;
    }

    public void setDelegateOrgFaxNo(String delegateOrgFaxNo) {
        this.delegateOrgFaxNo = delegateOrgFaxNo;
    }

    public String getIdentifyRequirement() {
        return identifyRequirement;
    }

    public void setIdentifyRequirement(String identifyRequirement) {
        this.identifyRequirement = identifyRequirement;
    }

    public String getPreIdentifyDesc() {
        return preIdentifyDesc;
    }

    public void setPreIdentifyDesc(String preIdentifyDesc) {
        this.preIdentifyDesc = preIdentifyDesc;
    }

    public String getReidentifyReason() {
        return reidentifyReason;
    }

    public void setReidentifyReason(String reidentifyReason) {
        this.reidentifyReason = reidentifyReason;
    }

    public Short getReidentifyCount() {
        return reidentifyCount;
    }

    public void setReidentifyCount(Short reidentifyCount) {
        this.reidentifyCount = reidentifyCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAcceptOrgId() {
        return acceptOrgId;
    }

    public void setAcceptOrgId(String acceptOrgId) {
        this.acceptOrgId = acceptOrgId;
    }

    public String getIdentifyType() {
        return identifyType;
    }

    public void setIdentifyType(String identifyType) {
        this.identifyType = identifyType;
    }

    public String getIdentifyItem() {
        return identifyItem;
    }

    public void setIdentifyItem(String identifyItem) {
        this.identifyItem = identifyItem;
    }

    public String getAcceptorId() {
        return acceptorId;
    }

    public void setAcceptorId(String acceptorId) {
        this.acceptorId = acceptorId;
    }

    public Date getAcceptDatetime() {
        return acceptDatetime;
    }

    public void setAcceptDatetime(Date acceptDatetime) {
        this.acceptDatetime = acceptDatetime;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }

    public Date getRefuseDatetime() {
        return refuseDatetime;
    }

    public void setRefuseDatetime(Date refuseDatetime) {
        this.refuseDatetime = refuseDatetime;
    }

    public String getRefusePerson() {
        return refusePerson;
    }

    public void setRefusePerson(String refusePerson) {
        this.refusePerson = refusePerson;
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

    public String getTransferFlag() {
        return transferFlag;
    }

    public void setTransferFlag(String transferFlag) {
        this.transferFlag = transferFlag;
    }

    public String getTransferBaseId() {
        return transferBaseId;
    }

    public void setTransferBaseId(String transferBaseId) {
        this.transferBaseId = transferBaseId;
    }

    public String getAreaOrgName() {
        return areaOrgName;
    }

    public void setAreaOrgName(String areaOrgName) {
        this.areaOrgName = areaOrgName;
    }
}
