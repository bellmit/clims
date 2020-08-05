package com.bazl.clims.compare.relative.po;

/**
 * Created by Sun on 2019/4/2.
 */
public class ParentageMatchResult {
    private ParentageMatchResultRecord result;
    private ParentageMatchResultRecord resultOfM;
    private ParentageMatchResultRecord resultOfAf;

    public ParentageMatchResult() {
    }

    public ParentageMatchResultRecord getResult() {
        return this.result;
    }

    public void setResult(ParentageMatchResultRecord result) {
        this.result = result;
    }

    public ParentageMatchResultRecord getResultOfM() {
        return this.resultOfM;
    }

    public void setResultOfM(ParentageMatchResultRecord resultOfM) {
        this.resultOfM = resultOfM;
    }

    public ParentageMatchResultRecord getResultOfAf() {
        return this.resultOfAf;
    }

    public void setResultOfAf(ParentageMatchResultRecord resultOfAf) {
        this.resultOfAf = resultOfAf;
    }
}
