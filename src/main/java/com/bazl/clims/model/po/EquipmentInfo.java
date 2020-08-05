package com.bazl.clims.model.po;

import com.bazl.clims.utils.JsonDatetimeSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author sixiru
 * @date 2019/4/9.
 */
public class EquipmentInfo {

    /** 主键ID */
    private String id;

    /** 案件名称表ID */
    private String equipmentNameId;

    private String equipmentSpecification;

    /** 设备编号 */
    private String equipmentNo;

    /** 设备状态 */
    private String equipmentStatus;

    /** 数量 */
    private String equipmentNum;

    /** 设备蓝色预警 */
    private String useBlueWarn;

    /** 设备红色预警 */
    private String useRedWarn;

    /** 修理蓝色警告 **/
    private String repairBlueWarn;

    /** 修理红色警告 **/
    private String repairRedWarn;

    private String remark;

    private String createPerson;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date createDatetime;

    private String updatePerson;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date updateDatetime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEquipmentNameId() {
        return equipmentNameId;
    }

    public void setEquipmentNameId(String equipmentNameId) {
        this.equipmentNameId = equipmentNameId;
    }

    public String getEquipmentSpecification() {
        return equipmentSpecification;
    }

    public void setEquipmentSpecification(String equipmentSpecification) {
        this.equipmentSpecification = equipmentSpecification;
    }

    public String getEquipmentNo() {
        return equipmentNo;
    }

    public void setEquipmentNo(String equipmentNo) {
        this.equipmentNo = equipmentNo;
    }

    public String getEquipmentStatus() {
        return equipmentStatus;
    }

    public void setEquipmentStatus(String equipmentStatus) {
        this.equipmentStatus = equipmentStatus;
    }

    public String getEquipmentNum() {
        return equipmentNum;
    }

    public void setEquipmentNum(String equipmentNum) {
        this.equipmentNum = equipmentNum;
    }

    public String getUseBlueWarn() {
        return useBlueWarn;
    }

    public void setUseBlueWarn(String useBlueWarn) {
        this.useBlueWarn = useBlueWarn;
    }

    public String getUseRedWarn() {
        return useRedWarn;
    }

    public void setUseRedWarn(String useRedWarn) {
        this.useRedWarn = useRedWarn;
    }

    public String getRepairBlueWarn() {
        return repairBlueWarn;
    }

    public void setRepairBlueWarn(String repairBlueWarn) {
        this.repairBlueWarn = repairBlueWarn;
    }

    public String getRepairRedWarn() {
        return repairRedWarn;
    }

    public void setRepairRedWarn(String repairRedWarn) {
        this.repairRedWarn = repairRedWarn;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getUpdatePerson() {
        return updatePerson;
    }

    public void setUpdatePerson(String updatePerson) {
        this.updatePerson = updatePerson;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }
}