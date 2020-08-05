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
public class EquipmentTypeInfo {

    /** 主键ID */
    private String id;

    /** 设备类型名称 */
    private String equipmentTypeName;

    /** 设备类型编号 **/
    private String equipmentTypeNo;

    /** 试验阶段 */
    private String experimentalStage;

    /** 设备蓝色预警 */
    private String useBlueWarn;

    /** 设备红色预警 */
    private String useRedWarn;

    /** 修理蓝色警告 */
    private String repairBlueWarn;

    /** 修理红色警告 */
    private String repairRedWarn;

    /** 设备类型信息描述 **/
    private String remark;

    /** 创建人 **/
    private String createPerson;

    /** 创建时间 **/
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date createDatetime;

    /** 更新人 **/
    private String updatePerson;

    /** 更新时间 **/
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date updateDatetime;

    private String experimentalStageName;

    /** 单位主键ID */
    private String orgId;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getExperimentalStageName() {
        return experimentalStageName;
    }

    public void setExperimentalStageName(String experimentalStageName) {
        this.experimentalStageName = experimentalStageName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEquipmentTypeName() {
        return equipmentTypeName;
    }

    public void setEquipmentTypeName(String equipmentTypeName) {
        this.equipmentTypeName = equipmentTypeName;
    }

    public String getEquipmentTypeNo() {
        return equipmentTypeNo;
    }

    public void setEquipmentTypeNo(String equipmentTypeNo) {
        this.equipmentTypeNo = equipmentTypeNo;
    }

    public String getExperimentalStage() {
        return experimentalStage;
    }

    public void setExperimentalStage(String experimentalStage) {
        this.experimentalStage = experimentalStage;
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