package com.bazl.clims.model.bo;

import com.bazl.clims.model.po.LimsCaseInfo;
import com.bazl.clims.model.po.LimsConsignmentInfo;
import com.bazl.clims.model.po.LimsPersonInfo;
import com.bazl.clims.model.po.LimsSampleInfoDna;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018-06-24.
 */
public class DelegateDataModel implements Serializable {

    private LimsConsignmentInfo consignatioInfo;

    private LimsCaseInfo caseInfoDna;

    private List<LimsPersonInfo> limsPersonInfoList;

    private List<LimsSampleInfoDna> sampleInfoDnaList;

    public LimsConsignmentInfo getConsignatioInfo() {
        return consignatioInfo;
    }

    public void setConsignatioInfo(LimsConsignmentInfo consignatioInfo) {
        this.consignatioInfo = consignatioInfo;
    }

    public LimsCaseInfo getCaseInfoDna() {
        return caseInfoDna;
    }

    public void setCaseInfoDna(LimsCaseInfo caseInfoDna) {
        this.caseInfoDna = caseInfoDna;
    }

    public List<LimsPersonInfo> getLimsPersonInfoList() {
        return limsPersonInfoList;
    }

    public void setLimsPersonInfoList(List<LimsPersonInfo> limsPersonInfoList) {
        this.limsPersonInfoList = limsPersonInfoList;
    }

    public List<LimsSampleInfoDna> getSampleInfoDnaList() {
        return sampleInfoDnaList;
    }

    public void setSampleInfoDnaList(List<LimsSampleInfoDna> sampleInfoDnaList) {
        this.sampleInfoDnaList = sampleInfoDnaList;
    }
}
