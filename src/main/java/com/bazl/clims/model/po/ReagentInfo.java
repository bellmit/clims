package com.bazl.clims.model.po;

import com.bazl.clims.model.vo.AbstractBaseVo;
import com.bazl.clims.utils.JsonDatetimeSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 试剂管理表
 * 表名：REAGENT_INFO
 * @author hj
 *  * @date 2019/4/1
 */
public class ReagentInfo extends AbstractBaseVo<ReagentInfo> {

    /**
     *主键iD
     */
    private String id;

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
     * 描述
     */
    private String remark;

    /**
     * 创建人
     */
    private String createPerson;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date createDatetime;

    /**
     * 更新人
     */
    private String updatePerson;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date updateDatetime;

    /**
     * 批号
     */
    private String batchNumber;

    /**
     * 有效期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date validityTime;

    /**
     * 试剂类型
     */
    private String reagentType;

    /**

     * 库存
     */
    private Short reagentCount;

    /** 单位主键ID */
    private String orgId;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Short getReagentCount() {
        return reagentCount;
    }

    public void setReagentCount(Short reagentCount) {
        this.reagentCount = reagentCount;
    }

    public String getReagentType() {
        return reagentType;
    }

    public void setReagentType(String reagentType) {
        this.reagentType = reagentType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getReagentName() {
        return reagentName;
    }

    public void setReagentName(String reagentName) {
        this.reagentName = reagentName == null ? null : reagentName.trim();
    }

    public String getReagentNo() {
        return reagentNo;
    }

    public void setReagentNo(String reagentNo) {
        this.reagentNo = reagentNo == null ? null : reagentNo.trim();
    }

    public String getExperimentalStage() {
        return experimentalStage;
    }

    public void setExperimentalStage(String experimentalStage) {
        this.experimentalStage = experimentalStage == null ? null : experimentalStage.trim();
    }

    public String getReagentBrand() {
        return reagentBrand;
    }

    public void setReagentBrand(String reagentBrand) {
        this.reagentBrand = reagentBrand == null ? null : reagentBrand.trim();
    }

    public String getReagentModel() {
        return reagentModel;
    }

    public void setReagentModel(String reagentModel) {
        this.reagentModel = reagentModel == null ? null : reagentModel.trim();
    }

    public String getReagentPrice() {
        return reagentPrice;
    }

    public void setReagentPrice(String reagentPrice) {
        this.reagentPrice = reagentPrice == null ? null : reagentPrice.trim();
    }

    public String getReagentFirm() {
        return reagentFirm;
    }

    public void setReagentFirm(String reagentFirm) {
        this.reagentFirm = reagentFirm == null ? null : reagentFirm.trim();
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

    public Date getCreateDatetime(String format) {
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

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber == null ? null : batchNumber.trim();
    }

    public Date getValidityTime() {
        return validityTime;
    }

    public void setValidityTime(Date validityTime) {
        this.validityTime = validityTime;
    }


}
