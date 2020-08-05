package com.bazl.clims.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 字典信息表-子表
 * DICT_ITEM
 * Created by hj on 2018/12/23
 */
public class DictItem  implements Comparable<DictItem>,Serializable {

    /**
     * 字典信息主键id
     */
    private String dictItemId;

    /**
     * 字典类型主键id
     */
    private String dictInfoId;

    /**
     * 字典类型编号
     */
    private String dictTypeCode;

    /**
     * 字典编号
     */
    private String dictCode;

    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 字典描述
     */
    private String dictDesc;

    /**
     * 创建时间
     */
    private Date createDatetime;

    /**
     * 创建人
     */
    private String createPerson;

    public DictItem() {

    }

    public DictItem(String dictTypeCode) {
        this.dictTypeCode = dictTypeCode;
    }

    public String getDictItemId() {
        return dictItemId;
    }

    public void setDictItemId(String dictItemId) {
        this.dictItemId = dictItemId == null ? null : dictItemId.trim();
    }

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

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode == null ? null : dictCode.trim();
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName == null ? null : dictName.trim();
    }

    public String getDictDesc() {
        return dictDesc;
    }

    public void setDictDesc(String dictDesc) {
        this.dictDesc = dictDesc == null ? null : dictDesc.trim();
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

    @Override
    public int compareTo(DictItem dictItem) {
        return Integer.valueOf(this.dictCode) - Integer.valueOf(dictItem.getDictCode());// 根据dictCode升序排列，降序修改相减顺序即可
    }
}
