package com.bazl.clims.model.po;

import com.bazl.clims.utils.JsonDatetimeSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: chenzeliang
 * @Date: 2020/3/30 17:12
 * @Version: 1.0
 */
public class SampleInfo {
    /** 主键id */
    private String id;

    /** 样本表主键ID */
    private String sampleTableId;

    /** 提取表主键ID */
    private String extractPlateId;

    /** 扩增表主键ID */
    private String pcrPlateId;

    /** 电泳表主键ID */
    private String syPlateId;

    /** 样本板序号 */
    private String samplePlateSort;

    /** 提取板序号 */
    private String extractPlateSort;

    /** 扩增板序号 */
    private String pcrPlateSort;

    /** 电泳板序号 */
    private String syPlateSort;

    /** 样本板孔位序号 */
    private Integer sampleLocationSort;

    /** 提取板孔位序号 */
    private Integer extractLocationSort;

    /** 扩增板孔位序号 */
    private Integer pcrLocationSort;

    /** 电泳板孔位序号 */
    private Integer syLocationSort;

    /** 样本板孔位 */
    private String samplePlateLocation;

    /** 提取板孔位 */
    private String extractPlateLocation;

    /** 扩增板孔位 */
    private String pcrPlateLocation;

    /** 电泳板孔位 */
    private String syPlateLocation;

    /** 确证方法 */
    private String confirmatoryMethod;

    /** 预实验方法 */
    private String preExperimentalMethod;

    /** 检材转移方法 */
    private String sampleTransferMethod;

    /** 洗脱体积 */
    private String elution;

    private String elutionName;

    /** 板号 */
    private String boardNo;

    /** 删除标记，0：未删除；1：已删除 */
    private String deleteFlag;

    /** 删除时间 */
    private Date deleteDatetime;

    /** 创建人 */
    private String createPerson;

    /** 创建时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd ")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date createDatetime;

    /** 操作人 */
    private String operationPerson;

    /** 操作时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd ")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date operationDatetime;

    /** 样本性质 */
    private String sampleProperty;

    /** 样本编号 */
    private String sampleNo;

    private int count;

    private String param;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSampleTableId() {
        return sampleTableId;
    }

    public void setSampleTableId(String sampleTableId) {
        this.sampleTableId = sampleTableId;
    }

    public String getExtractPlateId() {
        return extractPlateId;
    }

    public void setExtractPlateId(String extractPlateId) {
        this.extractPlateId = extractPlateId;
    }

    public String getPcrPlateId() {
        return pcrPlateId;
    }

    public void setPcrPlateId(String pcrPlateId) {
        this.pcrPlateId = pcrPlateId;
    }

    public String getSyPlateId() {
        return syPlateId;
    }

    public void setSyPlateId(String syPlateId) {
        this.syPlateId = syPlateId;
    }

    public String getSamplePlateSort() {
        return samplePlateSort;
    }

    public void setSamplePlateSort(String samplePlateSort) {
        this.samplePlateSort = samplePlateSort;
    }

    public String getExtractPlateSort() {
        return extractPlateSort;
    }

    public void setExtractPlateSort(String extractPlateSort) {
        this.extractPlateSort = extractPlateSort;
    }

    public String getPcrPlateSort() {
        return pcrPlateSort;
    }

    public void setPcrPlateSort(String pcrPlateSort) {
        this.pcrPlateSort = pcrPlateSort;
    }

    public String getSyPlateSort() {
        return syPlateSort;
    }

    public void setSyPlateSort(String syPlateSort) {
        this.syPlateSort = syPlateSort;
    }

    public Integer getSampleLocationSort() {
        return sampleLocationSort;
    }

    public void setSampleLocationSort(Integer sampleLocationSort) {
        this.sampleLocationSort = sampleLocationSort;
    }

    public Integer getExtractLocationSort() {
        return extractLocationSort;
    }

    public void setExtractLocationSort(Integer extractLocationSort) {
        this.extractLocationSort = extractLocationSort;
    }

    public Integer getPcrLocationSort() {
        return pcrLocationSort;
    }

    public void setPcrLocationSort(Integer pcrLocationSort) {
        this.pcrLocationSort = pcrLocationSort;
    }

    public Integer getSyLocationSort() {
        return syLocationSort;
    }

    public void setSyLocationSort(Integer syLocationSort) {
        this.syLocationSort = syLocationSort;
    }

    public String getSamplePlateLocation() {
        return samplePlateLocation;
    }

    public void setSamplePlateLocation(String samplePlateLocation) {
        this.samplePlateLocation = samplePlateLocation;
    }

    public String getExtractPlateLocation() {
        return extractPlateLocation;
    }

    public void setExtractPlateLocation(String extractPlateLocation) {
        this.extractPlateLocation = extractPlateLocation;
    }

    public String getPcrPlateLocation() {
        return pcrPlateLocation;
    }

    public void setPcrPlateLocation(String pcrPlateLocation) {
        this.pcrPlateLocation = pcrPlateLocation;
    }

    public String getSyPlateLocation() {
        return syPlateLocation;
    }

    public void setSyPlateLocation(String syPlateLocation) {
        this.syPlateLocation = syPlateLocation;
    }

    public String getConfirmatoryMethod() {
        return confirmatoryMethod;
    }

    public void setConfirmatoryMethod(String confirmatoryMethod) {
        this.confirmatoryMethod = confirmatoryMethod;
    }

    public String getPreExperimentalMethod() {
        return preExperimentalMethod;
    }

    public void setPreExperimentalMethod(String preExperimentalMethod) {
        this.preExperimentalMethod = preExperimentalMethod;
    }

    public String getSampleTransferMethod() {
        return sampleTransferMethod;
    }

    public void setSampleTransferMethod(String sampleTransferMethod) {
        this.sampleTransferMethod = sampleTransferMethod;
    }

    public String getElution() {
        return elution;
    }

    public void setElution(String elution) {
        this.elution = elution;
    }

    public String getElutionName() {
        return elutionName;
    }

    public void setElutionName(String elutionName) {
        this.elutionName = elutionName;
    }

    public String getBoardNo() {
        return boardNo;
    }

    public void setBoardNo(String boardNo) {
        this.boardNo = boardNo;
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

    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getOperationPerson() {
        return operationPerson;
    }

    public void setOperationPerson(String operationPerson) {
        this.operationPerson = operationPerson;
    }

    public Date getOperationDatetime() {
        return operationDatetime;
    }

    public void setOperationDatetime(Date operationDatetime) {
        this.operationDatetime = operationDatetime;
    }

    public String getSampleProperty() {
        return sampleProperty;
    }

    public void setSampleProperty(String sampleProperty) {
        this.sampleProperty = sampleProperty;
    }

    public String getSampleNo() {
        return sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}
