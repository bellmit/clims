package com.bazl.clims.model.vo;

import com.bazl.clims.model.po.EquipmentTypeInfo;
import com.bazl.clims.model.po.LimsCaseInfo;
import com.bazl.clims.utils.JsonDatetimeSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/1/4.
 */
public class EquipmentTypeInfoVo extends AbstractBaseVo<EquipmentTypeInfo> implements Serializable {

    public EquipmentTypeInfoVo() {
        super();
        this.entity = new EquipmentTypeInfo();
    }

    public EquipmentTypeInfoVo(EquipmentTypeInfo entity) {
        super();
        this.entity = entity;
    }

    private String experimentalStageName;

    public String getExperimentalStageName() {
        return experimentalStageName;
    }

    public void setExperimentalStageName(String experimentalStageName) {
        this.experimentalStageName = experimentalStageName;
    }
}
