package com.bazl.clims.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 字典信息表
 * DICT_INFO
 * Created by hj on 2018/12/23
 */
public class DictInfo implements Serializable{
    /**
     * 字典信息主键id
     */
    private String dictInfoId;

    /**
     * 字典项类型
     */
    private String dictTypeCode;

    /**
     * 字典项名称
     */
    private String dictTypeName;

    /**
     * 创建时间
     */
    private Date createDatetime;

    /**
     * 创建人
     */
    private String createPerson;

    public String getDictInfoId() {
        return dictInfoId;
    }

    public void setDictInfoId(String dictInfoId) {
        this.dictInfoId = dictInfoId == null ? null : dictInfoId.trim();
    }

    public String getDictTypeCode() {
        return dictTypeCode;
    }

    public void setDictTypeCode(String dictTypeCode) {
        this.dictTypeCode = dictTypeCode == null ? null : dictTypeCode.trim();
    }

    public String getDictTypeName() {
        return dictTypeName;
    }

    public void setDictTypeName(String dictTypeName) {
        this.dictTypeName = dictTypeName == null ? null : dictTypeName.trim();
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
}
