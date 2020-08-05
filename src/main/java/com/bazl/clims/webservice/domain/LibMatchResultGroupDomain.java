package com.bazl.clims.webservice.domain;

import java.util.List;
import java.util.Map;

public class LibMatchResultGroupDomain {
	/**
	 * 组ID
	 */
	private String groupId;

	private String filePath;

	private boolean generatedWord;

	private String matchType;

	private String matchCnt;
	
	private String lastMatchTime;
	
	/**
	 * 样本信息列表
	 */
	private List<Map<String, String>> sampleList;

	/**
	 * 案件编号列表
	 */
	private List<Map<String, Object>> caseSnList;

	/**
	 * 记录总行数
	 */
	private int rowCnt;
	
	private int levelflag;//用于判断这组数据中是否有属于法医中心的样本 


	public int getLevelflag() {
		return levelflag;
	}

	public void setLevelflag(int levelflag) {
		this.levelflag = levelflag;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public int getRowCnt() {
		return rowCnt;
	}

	public void setRowCnt(int rowCnt) {
		this.rowCnt = rowCnt;
	}

	public List<Map<String, String>> getSampleList() {
		return sampleList;
	}

	public void setSampleList(List<Map<String, String>> sampleList) {
		this.sampleList = sampleList;
	}

	public List<Map<String, Object>> getCaseSnList() {
		return caseSnList;
	}

	public void setCaseSnList(List<Map<String, Object>> caseSnList) {
		this.caseSnList = caseSnList;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public boolean isGeneratedWord() {
		return generatedWord;
	}

	public void setGeneratedWord(boolean generatedWord) {
		this.generatedWord = generatedWord;
	}

	public String getMatchType() {
		return matchType;
	}

	public void setMatchType(String matchType) {
		this.matchType = matchType;
	}

	public String getMatchCnt() {
		return matchCnt;
	}

	public void setMatchCnt(String matchCnt) {
		this.matchCnt = matchCnt;
	}

	public String getLastMatchTime() {
		return lastMatchTime;
	}

	public void setLastMatchTime(String lastMatchTime) {
		this.lastMatchTime = lastMatchTime;
	}

}
