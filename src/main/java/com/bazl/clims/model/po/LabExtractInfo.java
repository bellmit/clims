package com.bazl.clims.model.po;

import com.bazl.clims.utils.JsonDatetimeSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author wanghaiyang
 * @date 2019/3/8.
 */
public class LabExtractInfo {

    /** 主键ID */
    private String extractId;

    /** 任务ID */
    private String taskId;

    /** 提取记录编号 */
    private String extractNo;

    /** 板号 */
    private String boardNo;

    /** 样本数量 */
    private Short sampleCount;

    /** 提取方法 */
    private String extractMethod;

    /** 提取时间 */
    private Date extractDatetime;

    /** 提取人1 */
    private String extractPerson1;

    /** 提取人2 */
    private String extractPerson2;

    /** 创建时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date createDatetime;

    /** 创建人 */
    private String createPerson;

    /** 更新时间 */
    private Date updateDatetime;

    /** 更新人 */
    private String updatePerson;

    /** 删除标记 */
    private String deleteFlag;

    /** 删除时间 */
    private Date deleteDatetime;

    /** 删除人 */
    private String deletePerson;

    /**试剂盒  ,分割*/
    private String kit;

    /**设备  ,分割*/
    private String equipment;

    /*类型*/
    private String extractStage;

    private String orgId;

    private String digestionTemperature;

    private String digestionTime;

    public String getExtractId() {
        return extractId;
    }

    public void setExtractId(String extractId) {
        this.extractId = extractId == null ? null : extractId.trim();
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    public String getExtractNo() {
        return extractNo;
    }

    public void setExtractNo(String extractNo) {
        this.extractNo = extractNo == null ? null : extractNo.trim();
    }

    public String getBoardNo() {
        return boardNo;
    }

    public void setBoardNo(String boardNo) {
        this.boardNo = boardNo == null ? null : boardNo.trim();
    }

    public Short getSampleCount() {
        return sampleCount;
    }

    public void setSampleCount(Short sampleCount) {
        this.sampleCount = sampleCount;
    }

    public String getExtractMethod() {
        return extractMethod;
    }

    public void setExtractMethod(String extractMethod) {
        this.extractMethod = extractMethod == null ? null : extractMethod.trim();
    }

    public Date getExtractDatetime() {
        return extractDatetime;
    }

    public void setExtractDatetime(Date extractDatetime) {
        this.extractDatetime = extractDatetime;
    }

    public String getExtractPerson1() {
        return extractPerson1;
    }

    public void setExtractPerson1(String extractPerson1) {
        this.extractPerson1 = extractPerson1 == null ? null : extractPerson1.trim();
    }

    public String getExtractPerson2() {
        return extractPerson2;
    }

    public void setExtractPerson2(String extractPerson2) {
        this.extractPerson2 = extractPerson2 == null ? null : extractPerson2.trim();
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

    public String getKit() {
        return kit;
    }

    public void setKit(String kit) {
        this.kit = kit;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getExtractStage() {
        return extractStage;
    }

    public void setExtractStage(String extractStage) {
        this.extractStage = extractStage;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getDigestionTemperature() {
        return digestionTemperature;
    }

    public void setDigestionTemperature(String digestionTemperature) {
        this.digestionTemperature = digestionTemperature;
    }

    public String getDigestionTime() {
        return digestionTime;
    }

    public void setDigestionTime(String digestionTime) {
        this.digestionTime = digestionTime;
    }
}