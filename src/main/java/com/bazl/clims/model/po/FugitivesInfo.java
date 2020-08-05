package com.bazl.clims.model.po;

import java.io.Serializable;
import java.util.Date;

/**
 * @author huawei
 * @date 2020/6/15.
 */
public class FugitivesInfo implements Serializable{

    private static final long serialVersionUID = -6625634964567004965L;

    /** 主键id */
    private String id;

    /** 人员名称 */
    private String involvedNo;

    /** 人员名称 */
    private String involvedName;

    /** 人员名称 */
    private String personName;

    /** 人员类型 */
    private String personType;

    /** 人员性别 */
    private String personGender;

    /** 人员年龄 */
    private String personAge;

    /** 人员身份证号 */
    private String personCard;

    /** 在逃编号 */
    private String fugitiveNo;

    /** 创建时间 */
    private Date createDatetime;

    /** 创建人 */
    private String createPerson;

    /** 更新时间 */
    private Date updateDatetime;

    /** 更新人 */
    private String updatePerson;

    /** 删除标记：0，未删除；1，已删除 */
    private String deleteFlag;

    /** 删除时间 */
    private Date deleteDatetime;

    /** 删除人 */
    private String deletePerson;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInvolvedNo() {
        return involvedNo;
    }

    public void setInvolvedNo(String involvedNo) {
        this.involvedNo = involvedNo;
    }

    public String getInvolvedName() {
        return involvedName;
    }

    public void setInvolvedName(String involvedName) {
        this.involvedName = involvedName;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public String getPersonGender() {
        return personGender;
    }

    public void setPersonGender(String personGender) {
        this.personGender = personGender;
    }

    public String getPersonAge() {
        return personAge;
    }

    public void setPersonAge(String personAge) {
        this.personAge = personAge;
    }

    public String getPersonCard() {
        return personCard;
    }

    public void setPersonCard(String personCard) {
        this.personCard = personCard;
    }

    public String getFugitiveNo() {
        return fugitiveNo;
    }

    public void setFugitiveNo(String fugitiveNo) {
        this.fugitiveNo = fugitiveNo;
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
        this.createPerson = createPerson;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public String getUpdatePerson() {
        return updatePerson;
    }

    public void setUpdatePerson(String updatePerson) {
        this.updatePerson = updatePerson;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Date getDeleteDatetime() {
        return deleteDatetime;
    }

    public void setDeleteDatetime(Date deleteDatetime) {
        this.deleteDatetime = deleteDatetime;
    }

    public String getDeletePerson() {
        return deletePerson;
    }

    public void setDeletePerson(String deletePerson) {
        this.deletePerson = deletePerson;
    }
}