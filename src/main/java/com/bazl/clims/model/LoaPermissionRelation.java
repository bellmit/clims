package com.bazl.clims.model;

/**
 * 角色权限系表
 * LOA_PERMISSION_RELATION
 * Created by Administrator on 2018/12/21.
 */
public class LoaPermissionRelation {
    /**
     * 主键ID
     */
    private String id;
    /**
     * 角色主键ID
     */
    private String roleId;
    /**
     * 菜单主键ID
     */
    private String permissionId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId == null ? null : permissionId.trim();
    }
}