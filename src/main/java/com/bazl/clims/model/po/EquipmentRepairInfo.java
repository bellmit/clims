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
public class EquipmentRepairInfo {

    /** 主键ID */
    private String id;

    /** 关联设备信息表业务ID */
    private String equipmentInfoId;

    /** 关联设备名称表业务ID **/
    private String equipmentNameId;

    /** 设备维修人员 */
    private String equipmentRepairPerson;

    /** 设备维修时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date equipmentRepairTime;

    /** 故障原因 */
    private String failureCause;

    /** 创建人 */
    private String createPerson;

    /** 创建时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date createDatetime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEquipmentInfoId() {
        return equipmentInfoId;
    }

    public void setEquipmentInfoId(String equipmentInfoId) {
        this.equipmentInfoId = equipmentInfoId;
    }

    public String getEquipmentNameId() {
        return equipmentNameId;
    }

    public void setEquipmentNameId(String equipmentNameId) {
        this.equipmentNameId = equipmentNameId;
    }

    public String getEquipmentRepairPerson() {
        return equipmentRepairPerson;
    }

    public void setEquipmentRepairPerson(String equipmentRepairPerson) {
        this.equipmentRepairPerson = equipmentRepairPerson;
    }

    public Date getEquipmentRepairTime() {
        return equipmentRepairTime;
    }

    public void setEquipmentRepairTime(Date equipmentRepairTime) {
        this.equipmentRepairTime = equipmentRepairTime;
    }

    public String getFailureCause() {
        return failureCause;
    }

    public void setFailureCause(String failureCause) {
        this.failureCause = failureCause;
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
}