package com.bazl.clims.model.po;

import com.bazl.clims.model.vo.AbstractBaseVo;
import com.bazl.clims.utils.JsonDatetimeSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 试剂出入库信息表
 * 表名：reagent_outgo_info
 * @author hj
 *  * @date 2019/4/1
 */
public class ReagentOutgoInfo extends AbstractBaseVo<ReagentOutgoInfo> {
    /**
     * 主键id
     */
    private String id;

    /**
     *关联试剂表业务id
     */
    private String reagentId;

    /**
     * 条码号
     */
    private String barcode;

    /**
     *记录类型
     * 0：入库  1：出库
     */
    private String recordType;

    /**
     * 出（入）库数量
     */
    private String storageNum;

    /**
     * 出（入）库人
     */
    private String storagePerson;

    /**
     * 出（入）时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date storageDatetime;

    /**
     *有效日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date effectiveDatetime;

    /**
     * 出（入）描述
     */
    private String storageRemark;

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

    /** 单位主键ID */
    private String orgId;

    //业务字段

    /**
     * 试剂名称
     */
    private String reagentName;

    /**
     * 编号
     */
    private String reagentNo;

    /**
     * 实验阶段
     */
    private String experimentalStage;

    /**
     * 品牌
     */
    private String reagentBrand;

    /**
     * 型号
     */
    private String reagentModel;

    /**
     * 价格
     */
    private String reagentPrice;

    /**
     * 厂商
     */
    private String reagentFirm;


    /**
     * 批号
     * @return
     */
    private String batchNumber;

    public String getReagentNo() {
        return reagentNo;
    }

    public void setReagentNo(String reagentNo) {
        this.reagentNo = reagentNo;
    }

    public String getExperimentalStage() {
        return experimentalStage;
    }

    public void setExperimentalStage(String experimentalStage) {
        this.experimentalStage = experimentalStage;
    }

    public String getReagentBrand() {
        return reagentBrand;
    }

    public void setReagentBrand(String reagentBrand) {
        this.reagentBrand = reagentBrand;
    }

    public String getReagentModel() {
        return reagentModel;
    }

    public void setReagentModel(String reagentModel) {
        this.reagentModel = reagentModel;
    }

    public String getReagentPrice() {
        return reagentPrice;
    }

    public void setReagentPrice(String reagentPrice) {
        this.reagentPrice = reagentPrice;
    }

    public String getReagentFirm() {
        return reagentFirm;
    }

    public void setReagentFirm(String reagentFirm) {
        this.reagentFirm = reagentFirm;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

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

    public String getReagentName() {
        return reagentName;
    }

    public void setReagentName(String reagentName) {
        this.reagentName = reagentName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReagentId() {
        return reagentId;
    }

    public void setReagentId(String reagentId) {
        this.reagentId = reagentId;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public String getStorageNum() {
        return storageNum;
    }

    public void setStorageNum(String storageNum) {
        this.storageNum = storageNum;
    }

    public String getStoragePerson() {
        return storagePerson;
    }

    public void setStoragePerson(String storagePerson) {
        this.storagePerson = storagePerson;
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
        this.storageRemark = storageRemark;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }
}
