package com.bazl.clims.model.po;

import com.bazl.clims.model.vo.AbstractBaseVo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wanghaiyang
 * @date 2019/3/27.
 */
public class ReviewQueueSample extends AbstractBaseVo<LimsCaseInfo> implements Serializable {

    /** 主键ID */
    private String id;

    /** 检材id */
    private String sampleId;

    /** 复检检材状态，0：未复检，1：已复检 */
    private String reviewSampleStatus;

    /** 创建时间 */
    private Date createDatetime;

    /** 创建人 */
    private String createPerson;

    /** 更新时间 */
    private Date updateDatetime;

    /** 更新人 */
    private String updatePerson;

    private String taskStage;

    private String geneId;

    /**
     * 单位code
     * @return
     */
    private String orgId;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getGeneId() {
        return geneId;
    }

    public void setGeneId(String geneId) {
        this.geneId = geneId;
    }

    public String getId() {
        return id;
    }

    public String getTaskStage() {
        return taskStage;
    }

    public void setTaskStage(String taskStage) {
        this.taskStage = taskStage;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSampleId() {
        return sampleId;
    }

    public void setSampleId(String sampleId) {
        this.sampleId = sampleId == null ? null : sampleId.trim();
    }

    public String getReviewSampleStatus() {
        return reviewSampleStatus;
    }

    public void setReviewSampleStatus(String reviewSampleStatus) {
        this.reviewSampleStatus = reviewSampleStatus == null ? null : reviewSampleStatus.trim();
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
}