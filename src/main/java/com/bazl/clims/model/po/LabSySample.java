package com.bazl.clims.model.po;

import java.util.Date;

/**
 * @author wanghaiyang
 * @date 2019/3/11.
 */
public class LabSySample  implements Comparable<LabSySample>{

    /** 主键ID */
    private String id;

    /** 主键ID */
    private String syId;

    /** 主键ID */
    private String sampleId;

    /** 板孔位置 */
    private String samplePostion;

    /** 主键ID */
    private String standardFlag;

    /** 创建时间 */
    private Date createDatetime;

    /** 创建人 */
    private String createPerson;

    /** 更新时间 */
    private Date updateDatetime;

    /** 更新人 */
    private String updatePerson;

    private String sampleNo;

    private String sampleName;

    private String sampleType;

    private String caseId;

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSyId() {
        return syId;
    }

    public void setSyId(String syId) {
        this.syId = syId == null ? null : syId.trim();
    }

    public String getSampleId() {
        return sampleId;
    }

    public void setSampleId(String sampleId) {
        this.sampleId = sampleId == null ? null : sampleId.trim();
    }

    public String getSamplePostion() {
        return samplePostion;
    }

    public void setSamplePostion(String samplePostion) {
        this.samplePostion = samplePostion == null ? null : samplePostion.trim();
    }

    public String getStandardFlag() {
        return standardFlag;
    }

    public void setStandardFlag(String standardFlag) {
        this.standardFlag = standardFlag == null ? null : standardFlag.trim();
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

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public String getUpdatePerson() {
        return updatePerson;
    }

    public void setUpdatePerson(String updatePerson) {
        this.updatePerson = updatePerson == null ? null : updatePerson.trim();
    }

    public String getSampleNo() {
        return sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public String getSampleType() {
        return sampleType;
    }

    public void setSampleType(String sampleType) {
        this.sampleType = sampleType;
    }

    @Override
    public int compareTo(LabSySample o) {
        String samplePostion = o.samplePostion;
        String substring = samplePostion.substring(1);
        String substring1 = samplePostion.substring(0, 1);
        String sub = this.samplePostion.substring(1);
        String sub1 = this.samplePostion.substring(0, 1);
        if (substring1.equals(sub1)){//判断字母是否相等
            return this.samplePostion.substring(1).compareTo(samplePostion.substring(1));
        }else if (sub.equals(substring)){//判断数字是否相等
            return this.samplePostion.compareTo(o.samplePostion);
        }
        return this.samplePostion.substring(1).compareTo(samplePostion.substring(1));
    }
}