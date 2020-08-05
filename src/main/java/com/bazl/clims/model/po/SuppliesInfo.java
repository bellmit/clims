package com.bazl.clims.model.po;

import java.util.Date;

public class SuppliesInfo {
    private String id;

    private String suppliesName;

    private String suppliesNo;

    private String experimentalStage;

    private String suppliesBrand;

    private String suppliesModel;

    private String suppliesPrice;

    private String suppliesFirm;

    private String remark;

    private String createPerson;

    private Date createDatetime;

    private String updatePerson;

    private Date updateDatetime;

    private String orgId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSuppliesName() {
        return suppliesName;
    }

    public void setSuppliesName(String suppliesName) {
        this.suppliesName = suppliesName == null ? null : suppliesName.trim();
    }

    public String getSuppliesNo() {
        return suppliesNo;
    }

    public void setSuppliesNo(String suppliesNo) {
        this.suppliesNo = suppliesNo == null ? null : suppliesNo.trim();
    }

    public String getExperimentalStage() {
        return experimentalStage;
    }

    public void setExperimentalStage(String experimentalStage) {
        this.experimentalStage = experimentalStage == null ? null : experimentalStage.trim();
    }

    public String getSuppliesBrand() {
        return suppliesBrand;
    }

    public void setSuppliesBrand(String suppliesBrand) {
        this.suppliesBrand = suppliesBrand == null ? null : suppliesBrand.trim();
    }

    public String getSuppliesModel() {
        return suppliesModel;
    }

    public void setSuppliesModel(String suppliesModel) {
        this.suppliesModel = suppliesModel == null ? null : suppliesModel.trim();
    }

    public String getSuppliesPrice() {
        return suppliesPrice;
    }

    public void setSuppliesPrice(String suppliesPrice) {
        this.suppliesPrice = suppliesPrice == null ? null : suppliesPrice.trim();
    }

    public String getSuppliesFirm() {
        return suppliesFirm;
    }

    public void setSuppliesFirm(String suppliesFirm) {
        this.suppliesFirm = suppliesFirm == null ? null : suppliesFirm.trim();
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

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }
}