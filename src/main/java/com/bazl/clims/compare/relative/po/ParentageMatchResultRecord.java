package com.bazl.clims.compare.relative.po;

import java.util.Map;

/**
 * Created by Sun on 2019/4/2.
 */
public class ParentageMatchResultRecord {
    private int matchCount;
    private int diffCount;
    private boolean successful;
    private double pi;
    private Map<String, Double> piOfMarker;
    private String failReason;

    public ParentageMatchResultRecord() {
    }

    public String getFailReason() {
        return this.failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public int getMatchCount() {
        return this.matchCount;
    }

    public void setMatchCount(int matchCount) {
        this.matchCount = matchCount;
    }

    public boolean isSuccessful() {
        return this.successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public double getPi() {
        return this.pi;
    }

    public void setPi(double pi) {
        this.pi = pi;
    }

    public Map<String, Double> getPiOfMarker() {
        return this.piOfMarker;
    }

    public void setPiOfMarker(Map<String, Double> piOfMarker) {
        this.piOfMarker = piOfMarker;
    }

    public int getDiffCount() {
        return this.diffCount;
    }

    public void setDiffCount(int diffCount) {
        this.diffCount = diffCount;
    }
}
