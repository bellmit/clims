package com.bazl.clims.model;

import com.bazl.clims.model.po.AmPersonalInfo;

import java.io.Serializable;
import java.util.List;

/**
 * 用户信息表
 * LOA_USER_INFO
 * Created by Administrator on 2018/12/21.
 */
public class LoaUserInfo implements Serializable{
    /**
     * 用户主键ID
     */
    private String userId;
    /**
     * 人员主键ID
     */
    private String personalId;
    /**
     * 登录名
     */
    private String loginName;
    /**
     * 登录密码
     */
    private String loginPassword;
    /**
     * 用户类型
     */
    private String userType;
    /**
     * 用户级别
     */
    private String userLevel;
    /**
     * 单位主键ID
     */
    private String orgId;
    /**
     * 是否有效
     */
    private String activeFlag;

    private List<LoaRole> roles;

    private AmPersonalInfo amPersonalInfo;
    private String statusId;//案件登记状态

    private String subOrgId;

    private String queryFlag;

    public String getQueryFlag() {
        return queryFlag;
    }

    public void setQueryFlag(String queryFlag) {
        this.queryFlag = queryFlag;
    }

    public String getSubOrgId() {
        return subOrgId;
    }

    public void setSubOrgId(String subOrgId) {
        this.subOrgId = subOrgId;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId == null ? null : personalId.trim();
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword == null ? null : loginPassword.trim();
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel == null ? null : userLevel.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(String activeFlag) {
        this.activeFlag = activeFlag;
    }

    public List<LoaRole> getRoles() {
        return roles;
    }

    public void setRoles(List<LoaRole> roles) {
        this.roles = roles;
    }

    public AmPersonalInfo getAmPersonalInfo() {
        return amPersonalInfo;
    }

    public void setAmPersonalInfo(AmPersonalInfo amPersonalInfo) {
        this.amPersonalInfo = amPersonalInfo;
    }
}