package com.bazl.clims.model.vo;

import com.bazl.clims.model.po.MatchSameResult;
import com.bazl.clims.utils.JsonDatetimeSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/1/4.
 */
public class MatchSameResultVo extends AbstractBaseVo<MatchSameResult> implements Serializable {

    public MatchSameResultVo() {
        super();
        this.entity = new MatchSameResult();
    }

    public MatchSameResultVo(MatchSameResult entity) {
        super();
        this.entity = entity;
    }


    private String caseName;

    private String sampleName;

    private String sampleNo;

    private String sampleFlag;

    private String delegateOrgName;

    private String instoredType;

    private String comparisonType;

    private String comparisonCategory;

    private List categoryList;

    private String caseNo;

    private String delegateOrgCode;

    private String caseId;

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

    private String groupId;

    private String operator;

    private int levelflag;

    private String matchId;

    private String panel;

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

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getDelegateOrgCode() {
        return delegateOrgCode;
    }

    public void setDelegateOrgCode(String delegateOrgCode) {
        this.delegateOrgCode = delegateOrgCode;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
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

    public String getSampleFlag() {
        return sampleFlag;
    }

    public void setSampleFlag(String sampleFlag) {
        this.sampleFlag = sampleFlag;
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

    public List getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List categoryList) {
        this.categoryList = categoryList;
    }
}
