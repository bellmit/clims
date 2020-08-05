package com.bazl.clims.model.vo;

import com.bazl.clims.model.po.MatchRelativeResult;
import com.bazl.clims.model.po.MatchSameResult;
import com.bazl.clims.utils.JsonDatetimeSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/1/4.
 */
public class MatchRelativeResultVo extends AbstractBaseVo<MatchRelativeResult> implements Serializable {

    public MatchRelativeResultVo() {
        super();
        this.entity = new MatchRelativeResult();
    }

    public MatchRelativeResultVo(MatchRelativeResult entity) {
        super();
        this.entity = entity;
    }


    private String caseName;

    private String sampleName;

    private String sampleNo;

    private String delegateOrgName;

    private String instoredType;

    private String comparisonType;

    private String comparisonCategory;

    private String delegateaOrgName;

    private String delegatebOrgName;

    private String instoredaType;

    private String instoredbType;

    private String instoredcType;

    private String caseNo;

    private String delegatecOrgName;

    private String delegateOrgCode;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date createStartDatetime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+08")
    @JsonSerialize(using = JsonDatetimeSerializer.class)
    private Date createEndDatetime;

    //创建变量，存入当前用户的orgId，和委托表accept_org_id进行判断
    private String userOrdId;

    /**
     * 鉴定中心id(受理单位)
     * @return
     */
    private String acceptOrgId;

    //案件id
    private String caseId;

    private String groupId;

    private String operator;

    private int levelflag;

    private String matchId;

    private String panel;

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getUserOrdId() {
        return userOrdId;
    }

    public void setUserOrdId(String userOrdId) {
        this.userOrdId = userOrdId;
    }

    public String getAcceptOrgId() {
        return acceptOrgId;
    }

    public void setAcceptOrgId(String acceptOrgId) {
        this.acceptOrgId = acceptOrgId;
    }

    public Date getCreateStartDatetime() {
        return createStartDatetime;
    }

    public void setCreateStartDatetime(Date createStartDatetime) {
        this.createStartDatetime = createStartDatetime;
    }

    public Date getCreateEndDatetime() {
        return createEndDatetime;
    }

    public void setCreateEndDatetime(Date createEndDatetime) {
        this.createEndDatetime = createEndDatetime;
    }

    public String getDelegateOrgCode() {
        return delegateOrgCode;
    }

    public void setDelegateOrgCode(String delegateOrgCode) {
        this.delegateOrgCode = delegateOrgCode;
    }

    public String getDelegatecOrgName() {
        return delegatecOrgName;
    }

    public void setDelegatecOrgName(String delegatecOrgName) {
        this.delegatecOrgName = delegatecOrgName;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getDelegateaOrgName() {
        return delegateaOrgName;
    }

    public void setDelegateaOrgName(String delegateaOrgName) {
        this.delegateaOrgName = delegateaOrgName;
    }

    public String getDelegatebOrgName() {
        return delegatebOrgName;
    }

    public void setDelegatebOrgName(String delegatebOrgName) {
        this.delegatebOrgName = delegatebOrgName;
    }

    public String getComparisonCategory() {
        return comparisonCategory;
    }

    public void setComparisonCategory(String comparisonCategory) {
        this.comparisonCategory = comparisonCategory;
    }

    public String getComparisonType() {
        return comparisonType;
    }

    public void setComparisonType(String comparisonType) {
        this.comparisonType = comparisonType;
    }

    public String getInstoredType() {
        return instoredType;
    }

    public void setInstoredType(String instoredType) {
        this.instoredType = instoredType;
    }

    public String getDelegateOrgName() {
        return delegateOrgName;
    }

    public void setDelegateOrgName(String delegateOrgName) {
        this.delegateOrgName = delegateOrgName;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public String getSampleNo() {
        return sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }

    public String getInstoredaType() {
        return instoredaType;
    }

    public void setInstoredaType(String instoredaType) {
        this.instoredaType = instoredaType;
    }

    public String getInstoredbType() {
        return instoredbType;
    }

    public void setInstoredbType(String instoredbType) {
        this.instoredbType = instoredbType;
    }

    public String getInstoredcType() {
        return instoredcType;
    }

    public void setInstoredcType(String instoredcType) {
        this.instoredcType = instoredcType;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getLevelflag() {
        return levelflag;
    }

    public void setLevelflag(int levelflag) {
        this.levelflag = levelflag;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getPanel() {
        return panel;
    }

    public void setPanel(String panel) {
        this.panel = panel;
    }
}
