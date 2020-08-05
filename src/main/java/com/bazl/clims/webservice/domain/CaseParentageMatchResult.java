package com.bazl.clims.webservice.domain;
import java.util.Map;

public class CaseParentageMatchResult {
	public int isSuccessful = 0;
	public int matchCount = 0;
	public String totalPossibility;
	public Map<String,Double> markerRates;
	public int getIsSuccessful() {
		return isSuccessful;
	}
	public void setIsSuccessful(int isSuccessful) {
		this.isSuccessful = isSuccessful;
	}
	public int getMatchCount() {
		return matchCount;
	}
	public void setMatchCount(int matchCount) {
		this.matchCount = matchCount;
	}
	public Map<String, Double> getMarkerRates() {
		return markerRates;
	}
	public void setMarkerRates(Map<String, Double> markerRates) {
		this.markerRates = markerRates;
	}
	public String getTotalPossibility() {
		return totalPossibility;
	}
	public void setTotalPossibility(String totalPossibility) {
		this.totalPossibility = totalPossibility;
	}
}
