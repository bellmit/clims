package com.bazl.clims.model.vo;


import com.bazl.clims.model.po.FugitivesInfo;

import java.io.Serializable;

/**
 * @author wanghaiyang
 * @date 2020/6/15
 */
public class FugitivesInfoVo extends AbstractBaseVo<FugitivesInfo> implements Serializable {

    public FugitivesInfoVo() {
        super();
        this.entity = new FugitivesInfo();
    }

    public FugitivesInfoVo(FugitivesInfo entity) {
        super();
        this.entity = entity;
    }

    private String personTypeName;

    private String personGenderName;

    public String getPersonTypeName() {
        return personTypeName;
    }

    public void setPersonTypeName(String personTypeName) {
        this.personTypeName = personTypeName;
    }

    public String getPersonGenderName() {
        return personGenderName;
    }

    public void setPersonGenderName(String personGenderName) {
        this.personGenderName = personGenderName;
    }
}
