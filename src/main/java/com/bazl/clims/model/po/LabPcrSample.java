package com.bazl.clims.model.po;

import java.util.Date;

/**
 * @author wanghaiyang
 * @date 2019/3/11.
 */
public class LabPcrSample implements Comparable<LabPcrSample>{

    /** 主键ID */
    private String id;

    /** 扩增记录主键ID */
    private String pcrId;

    /** 检材主键ID */
    private String sampleId;

    /** 是否标准 */
    private String standardFlag;

    /** 扩增参数 */
    private String pcrString;

    /** 板孔位置 */
    private String samplePostion;

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

    private String sampleTypeName;

    private String primer;

    private String buffer;

    private String taqe;

    private String template;

    private String h2o;

    private String mgcl2;

    private String dntp;

    private String caseId;

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getPrimer() {
        return primer;
    }

    public void setPrimer(String primer) {
        this.primer = primer;
    }

    public String getBuffer() {
        return buffer;
    }

    public void setBuffer(String buffer) {
        this.buffer = buffer;
    }

    public String getTaqe() {
        return taqe;
    }

    public void setTaqe(String taqe) {
        this.taqe = taqe;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getH2o() {
        return h2o;
    }

    public void setH2o(String h2o) {
        this.h2o = h2o;
    }

    public String getMgcl2() {
        return mgcl2;
    }

    public void setMgcl2(String mgcl2) {
        this.mgcl2 = mgcl2;
    }

    public String getDntp() {
        return dntp;
    }

    public void setDntp(String dntp) {
        this.dntp = dntp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPcrId() {
        return pcrId;
    }

    public void setPcrId(String pcrId) {
        this.pcrId = pcrId == null ? null : pcrId.trim();
    }

    public String getSampleId() {
        return sampleId;
    }

    public void setSampleId(String sampleId) {
        this.sampleId = sampleId == null ? null : sampleId.trim();
    }

    public String getStandardFlag() {
        return standardFlag;
    }

    public void setStandardFlag(String standardFlag) {
        this.standardFlag = standardFlag == null ? null : standardFlag.trim();
    }

    public String getPcrString() {
        return pcrString;
    }

    public void setPcrString(String pcrString) {
        this.pcrString = pcrString == null ? null : pcrString.trim();
    }

    public String getSamplePostion() {
        return samplePostion;
    }

    public void setSamplePostion(String samplePostion) {
        this.samplePostion = samplePostion == null ? null : samplePostion.trim();
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

    public String getSampleTypeName() {
        return sampleTypeName;
    }

    public void setSampleTypeName(String sampleTypeName) {
        this.sampleTypeName = sampleTypeName;
    }

    @Override
    public int compareTo(LabPcrSample o) {
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