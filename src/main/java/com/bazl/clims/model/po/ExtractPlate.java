package com.bazl.clims.model.po;

import com.bazl.clims.utils.JsonDatetimeSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: chenzeliang
 * @Date: 2020/4/2 15:32
 * @Version: 1.0
 */
public class ExtractPlate {

    /** 主键id */
    private String id;

    /** 扩增表主键ID */
    private String pcrPlateId;

    /** 提取方法 */
    private String extractMethod;

    /** 提取时间 */
    private Date extractDatetime;

    /**  */
    private String indexValue;

    /** 状态，0：未开始，1：已开始，2：已完成 */
    private String state;

    /** 是否创建：0：未创建，1：已创建 */
    private String isCreate;

    /** 板号 */
    private String boardNo;

    /** 孔数量 */
    private String holeNum;

    /** 试剂盒名称 */
    private String kitName;

    /** 设备类型 */
    private String deviceType;

    /** 扩增板名称 */
    private String pcrPlateName;

    /** 删除标记，0：未删除；1：已删除 */
    private String deleteFlag;

    /** 删除时间 */
    private Date deleteDatetime;

    /** 创建人 */
    private String createPerson;

    /** 创建时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd ")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date createDatetime;

    /** 操作人 */
    private String operationPerson;

    /** 操作时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd ")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date operationDatetime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPcrPlateId() {
        return pcrPlateId;
    }

    public void setPcrPlateId(String pcrPlateId) {
        this.pcrPlateId = pcrPlateId;
    }

    public String getExtractMethod() {
        return extractMethod;
    }

    public void setExtractMethod(String extractMethod) {
        this.extractMethod = extractMethod;
    }

    public Date getExtractDatetime() {
        return extractDatetime;
    }

    public void setExtractDatetime(Date extractDatetime) {
        this.extractDatetime = extractDatetime;
    }

    public String getIndexValue() {
        return indexValue;
    }

    public void setIndexValue(String indexValue) {
        this.indexValue = indexValue;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIsCreate() {
        return isCreate;
    }

    public void setIsCreate(String isCreate) {
        this.isCreate = isCreate;
    }

    public String getBoardNo() {
        return boardNo;
    }

    public void setBoardNo(String boardNo) {
        this.boardNo = boardNo;
    }

    public String getHoleNum() {
        return holeNum;
    }

    public void setHoleNum(String holeNum) {
        this.holeNum = holeNum;
    }

    public String getKitName() {
        return kitName;
    }

    public void setKitName(String kitName) {
        this.kitName = kitName;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getPcrPlateName() {
        return pcrPlateName;
    }

    public void setPcrPlateName(String pcrPlateName) {
        this.pcrPlateName = pcrPlateName;
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

    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getOperationPerson() {
        return operationPerson;
    }

    public void setOperationPerson(String operationPerson) {
        this.operationPerson = operationPerson;
    }

    public Date getOperationDatetime() {
        return operationDatetime;
    }

    public void setOperationDatetime(Date operationDatetime) {
        this.operationDatetime = operationDatetime;
    }
}
