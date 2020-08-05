package com.bazl.clims.webservice.domain;

import java.util.Date;

public class SubmitInfoDomain {

	private int dataType;

	/**
	 * 基因型
	 */
	private String markerElements;
	
	/**
	 * panelId
	 */
	private String panelId;

	/**
	 * panelName
	 */
	private String panelName;

	/**
	 * 案件id
	 */
	private String caseInfoId;

	/**
	 * 案件名称
	 */
	private String caseName;

	private String caseSn;
	
	private String caseType;
	
	private String delegateOrgCode;
	private String delegator;
	/**
	 * 样本名称
	 */
	private String sampleName;

	/**
	 * 样本id
	 */
	private String sampleId;

	/**
	 * 提交人
	 */
	private String operator;

	/**
	 * 提交时间
	 */
	private Date operateDate;

	/**
	 * 提交人单位
	 */
	private String operatorOrg;

	/**
	 * 简要案情
	 */
	private String caseDigest;

	/**
	 * 案件说明
	 */
	private String memo;

	/**
	 * 样本类型
	 */
	private Integer sampleType;

	/**
	 * 案件单位
	 */
	private String caseInfoOrg;
	
	/**
	 * 案件类型
	 */
	private String caseHandleType;
	
	/**
	 * 案件caseBaseInfoId
	 */
	private String caseBaseInfoId;
	
	/**
	 * dna库人员编号，或者物证编号
	 */
	private String sampleNo;
	
	private String sampleBoardBarcode;
	
	private int sampleLevel;//用于判断Lims等级
	private String caseXkSn;
	private String associateSystemName;
	private String associateSystemSn;
	private String delegateSn;
	private String xk_caseProperty;
	private String xk_caseType;
	private String caseAddress;
	private String caseAddressFullName;
	private String caseTimeStr;
	private String xkNumber;
	private String xkDelegateSN;
	private String xkAddress;
	private String cardId;
	private String sampleRelation;
	
	
	public int getSampleLevel() {
		return sampleLevel;
	}

	public void setSampleLevel(int sampleLevel) {
		this.sampleLevel = sampleLevel;
	}

	public String getSampleBoardBarcode() {
		return sampleBoardBarcode;
	}

	public void setSampleBoardBarcode(String sampleBoardBarcode) {
		this.sampleBoardBarcode = sampleBoardBarcode;
	}

	public SubmitInfoDomain() {

	}

	public String getCaseInfoId() {
		return caseInfoId;
	}

	public void setCaseInfoId(String caseInfoId) {
		this.caseInfoId = caseInfoId;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public int getDataType() {
		return dataType;
	}

	public void setDataType(int dataType) {
		this.dataType = dataType;
	}

	public String getMarkerElements() {
		return markerElements;
	}

	public void setMarkerElements(String markerElements) {
		this.markerElements = markerElements;
	}

	public String getPanelName() {
		return panelName;
	}

	public void setPanelName(String panelName) {
		this.panelName = panelName;
	}

	public String getSampleId() {
		return sampleId;
	}

	public void setSampleId(String sampleId) {
		this.sampleId = sampleId;
	}

	public String getSampleName() {
		return sampleName;
	}

	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}

	public Date getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getCaseDigest() {
		return caseDigest;
	}

	public void setCaseDigest(String caseDigest) {
		this.caseDigest = caseDigest;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getOperatorOrg() {
		return operatorOrg;
	}

	public void setOperatorOrg(String operatorOrg) {
		this.operatorOrg = operatorOrg;
	}

	public Integer getSampleType() {
		return sampleType;
	}

	public void setSampleType(Integer sampleType) {
		this.sampleType = sampleType;
	}

	public String getCaseInfoOrg() {
		return caseInfoOrg;
	}

	public void setCaseInfoOrg(String caseInfoOrg) {
		this.caseInfoOrg = caseInfoOrg;
	}

	public String getPanelId() {
		return panelId;
	}

	public void setPanelId(String panelId) {
		this.panelId = panelId;
	}

	public String getCaseSn() {
		return caseSn;
	}

	public void setCaseSn(String caseSn) {
		this.caseSn = caseSn;
	}

	public String getCaseType() {
		return caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public String getDelegateOrgCode() {
		return delegateOrgCode;
	}

	public void setDelegateOrgCode(String delegateOrgCode) {
		this.delegateOrgCode = delegateOrgCode;
	}

	public String getCaseHandleType() {
		return caseHandleType;
	}

	public void setCaseHandleType(String caseHandleType) {
		this.caseHandleType = caseHandleType;
	}

	public String getCaseBaseInfoId() {
		return caseBaseInfoId;
	}

	public void setCaseBaseInfoId(String caseBaseInfoId) {
		this.caseBaseInfoId = caseBaseInfoId;
	}

	public String getDelegator() {
		return delegator;
	}

	public void setDelegator(String delegator) {
		this.delegator = delegator;
	}

	public String getSampleNo() {
		return sampleNo;
	}

	public void setSampleNo(String sampleNo) {
		this.sampleNo = sampleNo;
	}

	public String getCaseXkSn() {
		return caseXkSn;
	}

	public void setCaseXkSn(String caseXkSn) {
		this.caseXkSn = caseXkSn;
	}

	public String getAssociateSystemName() {
		return associateSystemName;
	}

	public void setAssociateSystemName(String associateSystemName) {
		this.associateSystemName = associateSystemName;
	}

	public String getAssociateSystemSn() {
		return associateSystemSn;
	}

	public void setAssociateSystemSn(String associateSystemSn) {
		this.associateSystemSn = associateSystemSn;
	}

	public String getDelegateSn() {
		return delegateSn;
	}

	public void setDelegateSn(String delegateSn) {
		this.delegateSn = delegateSn;
	}

	public String getXk_caseProperty() {
		return xk_caseProperty;
	}

	public void setXk_caseProperty(String xk_caseProperty) {
		this.xk_caseProperty = xk_caseProperty;
	}

	public String getXk_caseType() {
		return xk_caseType;
	}

	public void setXk_caseType(String xk_caseType) {
		this.xk_caseType = xk_caseType;
	}

	public String getCaseAddress() {
		return caseAddress;
	}

	public void setCaseAddress(String caseAddress) {
		this.caseAddress = caseAddress;
	}

	public String getCaseAddressFullName() {
		return caseAddressFullName;
	}

	public void setCaseAddressFullName(String caseAddressFullName) {
		this.caseAddressFullName = caseAddressFullName;
	}

	public String getCaseTimeStr() {
		return caseTimeStr;
	}

	public void setCaseTimeStr(String caseTimeStr) {
		this.caseTimeStr = caseTimeStr;
	}

	public String getXkNumber() {
		return xkNumber;
	}

	public void setXkNumber(String xkNumber) {
		this.xkNumber = xkNumber;
	}

	public String getXkDelegateSN() {
		return xkDelegateSN;
	}

	public void setXkDelegateSN(String xkDelegateSN) {
		this.xkDelegateSN = xkDelegateSN;
	}

	public String getXkAddress() {
		return xkAddress;
	}

	public void setXkAddress(String xkAddress) {
		this.xkAddress = xkAddress;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getSampleRelation() {
		return sampleRelation;
	}

	public void setSampleRelation(String sampleRelation) {
		this.sampleRelation = sampleRelation;
	}
}
