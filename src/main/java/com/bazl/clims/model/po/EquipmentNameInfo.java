package com.bazl.clims.model.po;

import java.util.Date;

/**
 * @author wanghaiyang
 * @date 2019/3/11.
 */
public class EquipmentNameInfo {

    /** 主键ID */
    private String id;

    /** 关联设备类型业务ID */
    private String equipmentTypeId;

    /** 设备编号 */
    private String equipmentNo;

    /** 设备名称 */
    private String equipmentName;

    /** 创建人 */
    private String createPerson;

    /** 创建时间 */
    private Date createDatetime;

    /** 更新人 */
    private String updatePerson;

    /** 更新时间 */
    private Date updateDatetime;

    /** 单位主键ID */
    private String orgId;

    //阶段类型   1:提取 2:扩增 3:电泳 4:分析
    private String phase;


    /**
     * 业务字段
     * 设备类型信息表设备类型编号
     */
    private String equipmentTypeNo;

    public String getEquipmentTypeNo() {
        return equipmentTypeNo;
    }

    public void setEquipmentTypeNo(String equipmentTypeNo) {
        this.equipmentTypeNo = equipmentTypeNo;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEquipmentTypeId() {
        return equipmentTypeId;
    }

    public void setEquipmentTypeId(String equipmentTypeId) {
        this.equipmentTypeId = equipmentTypeId;
    }

    public String getEquipmentNo() {
        return equipmentNo;
    }

    public void setEquipmentNo(String equipmentNo) {
        this.equipmentNo = equipmentNo;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    public String getUpdatePerson() {
        return updatePerson;
    }

    public void setUpdatePerson(String updatePerson) {
        this.updatePerson = updatePerson;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }
}