package com.bazl.clims.model;

/**
 * 用户权限关系表
 * LOA_ROLE_RELATION
 * Created by Administrator on 2018/12/21.
 */
public class LoaRoleRelation {
    /**
     * 主键Id
     */
    private String id;
    /**
     * 人员主键Id
     */
    private String userId;
    /**
     * 角色主键Id
     */
    private String roleId;

    /**
     * 角色表业务字段
     */
    private String roleLevel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(String roleLevel) {
        this.roleLevel = roleLevel;
    }
}