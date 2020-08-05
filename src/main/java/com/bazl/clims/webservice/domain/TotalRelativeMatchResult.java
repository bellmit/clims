package com.bazl.clims.webservice.domain;

public class TotalRelativeMatchResult {
	public static int MATCHMODE_UNMATCHED_FM = 0; // 表示都没匹配上
	public static int MATCHMODE_MATCHED_FM = 1; //表示匹配成功
	public static int MATCHMODE_MATCHED_F = 2; //表示只匹配父亲
	public static int MATCHMODE_MATCHED_M = 3; //表示只匹配母亲
	public static int MATCHMODE_MATCHED_FM_UNMATCHEDALL = 4; //表示单亲都匹配，双亲综合不匹配
	public static int MATCHMODE_CHILD_MIX = -1; //孩子混合样本
	public static int MATCHMODE_FATHER_MIX = -2; //父亲混合样本
	public static int MATCHMODE_MOTHER_MIX = -3; //母亲混合样本

	public int matchMode; 
	public CaseParentageMatchResult result;
	public CaseParentageMatchResult fResult;
	public CaseParentageMatchResult mResult;
	public String fBarcode;
	public String mBarcode;
	public String cBarcode;
	
	public int getMatchMode() {
		return matchMode;
	}
	public void setMatchMode(int matchMode) {
		this.matchMode = matchMode;
	}
	public CaseParentageMatchResult getResult() {
		return result;
	}
	public void setResult(CaseParentageMatchResult result) {
		this.result = result;
	}
	public CaseParentageMatchResult getFResult() {
		return fResult;
	}
	public void setFResult(CaseParentageMatchResult result) {
		fResult = result;
	}
	public CaseParentageMatchResult getMResult() {
		return mResult;
	}
	public void setMResult(CaseParentageMatchResult result) {
		mResult = result;
	}
	public String getCBarcode() {
		return cBarcode;
	}
	public void setCBarcode(String barcode) {
		cBarcode = barcode;
	}
	public String getFBarcode() {
		return fBarcode;
	}
	public void setFBarcode(String barcode) {
		fBarcode = barcode;
	}
	public String getMBarcode() {
		return mBarcode;
	}
	public void setMBarcode(String barcode) {
		mBarcode = barcode;
	}
	
}
