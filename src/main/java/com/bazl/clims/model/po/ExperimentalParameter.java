package com.bazl.clims.model.po;

import java.util.Date;

/**
 * @author wanghaiyang
 * @date 2019/5/21.
 */
public class ExperimentalParameter {

    /** 主键ID */
    private String id;

    /** 试剂盒名称 */
    private String panelName;

    /** 实验阶段 */
    private String experimentalStage;

    /** 实验类型 */
    private String experimentalType;

    /** 参数名称 */
    private String parameterName;

    /** 参数值 */
    private String parameterValue;

    /** 受理单位主键id */
    private String orgId;

    /** 描述 */
    private String remark;

    /** 创建人 */
    private String createPerson;

    /** 创建时间 */
    private Date createDatetime;

    /** 更新人 */
    private String updatePerson;

    /** 更新时间 */
    private Date updateDatetime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPanelName() {
        return panelName;
    }

    public void setPanelName(String panelName) {
        this.panelName = panelName == null ? null : panelName.trim();
    }

    public String getExperimentalStage() {
        return experimentalStage;
    }

    public void setExperimentalStage(String experimentalStage) {
        this.experimentalStage = experimentalStage == null ? null : experimentalStage.trim();
    }

    public String getExperimentalType() {
        return experimentalType;
    }

    public void setExperimentalType(String experimentalType) {
        this.experimentalType = experimentalType == null ? null : experimentalType.trim();
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName == null ? null : parameterName.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson == null ? null : createPerson.trim();
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getUpdatePerson() {
        return updatePerson;
    }

    public void setUpdatePerson(String updatePerson) {
        this.updatePerson = updatePerson == null ? null : updatePerson.trim();
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public String getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue == null ? null : parameterValue.trim();
    }
}