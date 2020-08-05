package com.bazl.clims.model.vo;


import com.bazl.clims.model.po.EquipmentRepairInfo;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/1/4.
 */
public class EquipmentRepairInfoVo extends AbstractBaseVo<EquipmentRepairInfo> implements Serializable {

    public EquipmentRepairInfoVo() {
        super();
        this.entity = new EquipmentRepairInfo();
    }

    public EquipmentRepairInfoVo(EquipmentRepairInfo entity) {
        super();
        this.entity = entity;
    }

    private String equipmentNo;

    private String equipmentName;

    private String equipmentStatus;

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

    public String getEquipmentStatus() {
        return equipmentStatus;
    }

    public void setEquipmentStatus(String equipmentStatus) {
        this.equipmentStatus = equipmentStatus;
    }
}
