package com.bazl.clims.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色信息表
 * LOA_ROLE
 * Created by Administrator on 2018/12/21.
 */
public class LoaRole implements Serializable{
    /**
     * 主键ID
     */
    private String roleId;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色级别
     */
    private String roleLevel;
    /**
     * 创建时间
     */
    private Date createDatetime;
    /**
     * 创建人
     */
    private String createPerson;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(String roleLevel) {
        this.roleLevel = roleLevel == null ? null : roleLevel.trim();
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