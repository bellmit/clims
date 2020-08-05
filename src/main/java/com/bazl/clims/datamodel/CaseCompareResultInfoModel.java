package com.bazl.clims.datamodel;

import com.bazl.clims.model.po.CompareRelativeResult;
import com.bazl.clims.model.po.CompareSameResult;
import com.bazl.clims.model.po.LimsConsignmentInfo;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2019/4/19.
 */
public class CaseCompareResultInfoModel {

    //同一匹配下限
    private int minSameCaseCount;

    //混合匹配下限
    private int minSameMixCount;

    //亲缘匹配下限
    private int relativeSameCount;

    //亲缘容差数
    private int halfDiffCount;

    //委托信息
    private LimsConsignmentInfo limsConsignmentInfo;

    //同一比对列表
    private List<CompareSameResult> compareSameResultList;

    //亲缘比对列表
    private List<CompareRelativeResult> compareRelativeResultList;

    public int getMinSameCaseCount() {
        return minSameCaseCount;
    }

    public void setMinSameCaseCount(int minSameCaseCount) {
        this.minSameCaseCount = minSameCaseCount;
    }

    public int getMinSameMixCount() {
        return minSameMixCount;
    }

    public void setMinSameMixCount(int minSameMixCount) {
        this.minSameMixCount = minSameMixCount;
    }

    public int getRelativeSameCount() {
        return relativeSameCount;
    }

    public void setRelativeSameCount(int relativeSameCount) {
        this.relativeSameCount = relativeSameCount;
    }

    public int getHalfDiffCount() {
        return halfDiffCount;
    }

    public void setHalfDiffCount(int halfDiffCount) {
        this.halfDiffCount = halfDiffCount;
    }

    public LimsConsignmentInfo getLimsConsignmentInfo() {
        return limsConsignmentInfo;
    }

    public void setLimsConsignmentInfo(LimsConsignmentInfo limsConsignmentInfo) {
        this.limsConsignmentInfo = limsConsignmentInfo;
    }

    public List<CompareSameResult> getCompareSameResultList() {
        return compareSameResultList;
    }

    public void setCompareSameResultList(List<CompareSameResult> compareSameResultList) {
        this.compareSameResultList = compareSameResultList;
    }

    public List<CompareRelativeResult> getCompareRelativeResultList() {
        return compareRelativeResultList;
    }

    public void setCompareRelativeResultList(List<CompareRelativeResult> compareRelativeResultList) {
        this.compareRelativeResultList = compareRelativeResultList;
    }
}
