package com.bazl.clims.model.po;

import com.bazl.clims.utils.JsonDatetimeSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class SuppliesOutgoInfo {
    private String id;

    private String suppliesId;

    private String recordType;

    private String storageNum;

    private String storagePerson;

    //出入库时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date storageDatetime;

    //有效时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date effectiveDatetime;

    private String storageRemark;

    private String orgId;

    //开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date storageDatetimeStart;

    //结束时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date storageDatetimeEnd;

    //业务字段
    private String suppliesName;

    private String suppliesNo;

    private String experimentalStage;

    private String suppliesBrand;

    private String suppliesModel;

    private String suppliesPrice;

    private String suppliesFirm;

    public Date getStorageDatetimeStart() {
        return storageDatetimeStart;
    }

    public void setStorageDatetimeStart(Date storageDatetimeStart) {
        this.storageDatetimeStart = storageDatetimeStart;
    }

    public Date getStorageDatetimeEnd() {
        return storageDatetimeEnd;
    }

    public void setStorageDatetimeEnd(Date storageDatetimeEnd) {
        this.storageDatetimeEnd = storageDatetimeEnd;
    }

    public String getSuppliesName() {
        return suppliesName;
    }

    public void setSuppliesName(String suppliesName) {
        this.suppliesName = suppliesName;
    }

    public String getSuppliesNo() {
        return suppliesNo;
    }

    public void setSuppliesNo(String suppliesNo) {
        this.suppliesNo = suppliesNo;
    }

    public String getExperimentalStage() {
        return experimentalStage;
    }

    public void setExperimentalStage(String experimentalStage) {
        this.experimentalStage = experimentalStage;
    }

    public String getSuppliesBrand() {
        return suppliesBrand;
    }

    public void setSuppliesBrand(String suppliesBrand) {
        this.suppliesBrand = suppliesBrand;
    }

    public String getSuppliesModel() {
        return suppliesModel;
    }

    public void setSuppliesModel(String suppliesModel) {
        this.suppliesModel = suppliesModel;
    }

    public String getSuppliesPrice() {
        return suppliesPrice;
    }

    public void setSuppliesPrice(String suppliesPrice) {
        this.suppliesPrice = suppliesPrice;
    }

    public String getSuppliesFirm() {
        return suppliesFirm;
    }

    public void setSuppliesFirm(String suppliesFirm) {
        this.suppliesFirm = suppliesFirm;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSuppliesId() {
        return suppliesId;
    }

    public void setSuppliesId(String suppliesId) {
        this.suppliesId = suppliesId == null ? null : suppliesId.trim();
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType == null ? null : recordType.trim();
    }

    public String getStorageNum() {
        return storageNum;
    }

    public void setStorageNum(String storageNum) {
        this.storageNum = storageNum == null ? null : storageNum.trim();
    }

    public String getStoragePerson() {
        return storagePerson;
    }

    public void setStoragePerson(String storagePerson) {
        this.storagePerson = storagePerson == null ? null : storagePerson.trim();
    }

    public Date getStorageDatetime() {
        return storageDatetime;
    }

    public void setStorageDatetime(Date storageDatetime) {
        this.storageDatetime = storageDatetime;
    }

    public Date getEffectiveDatetime() {
        return effectiveDatetime;
    }

    public void setEffectiveDatetime(Date effectiveDatetime) {
        this.effectiveDatetime = effectiveDatetime;
    }

    public String getStorageRemark() {
        return storageRemark;
    }

    public void setStorageRemark(String storageRemark) {
        this.storageRemark = storageRemark == null ? null : storageRemark.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }
}