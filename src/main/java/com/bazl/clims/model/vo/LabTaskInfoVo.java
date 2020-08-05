package com.bazl.clims.model.vo;

import com.bazl.clims.model.po.LabTaskInfo;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/1/4.
 */
public class LabTaskInfoVo extends AbstractBaseVo<LabTaskInfo> implements Serializable {

    public LabTaskInfoVo() {
        super();
        this.entity = new LabTaskInfo();
    }

    public LabTaskInfoVo(LabTaskInfo entity) {
        super();
        this.entity = entity;
    }

}
