package com.bazl.clims.model.vo;

import com.bazl.clims.model.po.EquipmentNameInfo;
import com.bazl.clims.model.po.EquipmentTypeInfo;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/1/4.
 */
public class EquipmentNameInfoVo extends AbstractBaseVo<EquipmentNameInfo> implements Serializable {

    public EquipmentNameInfoVo() {
        super();
        this.entity = new EquipmentNameInfo();
    }

    public EquipmentNameInfoVo(EquipmentNameInfo entity) {
        super();
        this.entity = entity;
    }

    private String equipmentTypeName;

    public String getEquipmentTypeName() {
        return equipmentTypeName;
    }

    public void setEquipmentTypeName(String equipmentTypeName) {
        this.equipmentTypeName = equipmentTypeName;
    }
}
