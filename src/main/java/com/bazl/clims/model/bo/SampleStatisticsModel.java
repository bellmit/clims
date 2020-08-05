package com.bazl.clims.model.bo;

import java.io.Serializable;

/**
 * Created by LX on 2019/9/19.
 */
public class SampleStatisticsModel implements Serializable {

    private String name;//受理人
    private int sampleType01;//血样
    private int sampleType02;//精斑
    private int sampleType03;//脱落细胞
    private int sampleType04;//唾液斑
    private int sampleType05;//指甲
    private int sampleType06;//牙齿
    private int sampleType07;//骨骼
    private int sampleType08;//组织
    private int sampleType09;//毛发
    private int sampleType10;//其他
    private int sampleType21;//血痕
    private int acceptCount;//受理检材数
    private int instoredSampl;//已入库检材数

    public int getSampleType21() {
        return sampleType21;
    }

    public void setSampleType21(int sampleType21) {
        this.sampleType21 = sampleType21;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSampleType01() {
        return sampleType01;
    }

    public void setSampleType01(int sampleType01) {
        this.sampleType01 = sampleType01;
    }

    public int getSampleType02() {
        return sampleType02;
    }

    public void setSampleType02(int sampleType02) {
        this.sampleType02 = sampleType02;
    }

    public int getSampleType03() {
        return sampleType03;
    }

    public void setSampleType03(int sampleType03) {
        this.sampleType03 = sampleType03;
    }

    public int getSampleType04() {
        return sampleType04;
    }

    public void setSampleType04(int sampleType04) {
        this.sampleType04 = sampleType04;
    }

    public int getSampleType05() {
        return sampleType05;
    }

    public void setSampleType05(int sampleType05) {
        this.sampleType05 = sampleType05;
    }

    public int getSampleType06() {
        return sampleType06;
    }

    public void setSampleType06(int sampleType06) {
        this.sampleType06 = sampleType06;
    }

    public int getSampleType07() {
        return sampleType07;
    }

    public void setSampleType07(int sampleType07) {
        this.sampleType07 = sampleType07;
    }

    public int getSampleType08() {
        return sampleType08;
    }

    public void setSampleType08(int sampleType08) {
        this.sampleType08 = sampleType08;
    }

    public int getSampleType09() {
        return sampleType09;
    }

    public void setSampleType09(int sampleType09) {
        this.sampleType09 = sampleType09;
    }

    public int getSampleType10() {
        return sampleType10;
    }

    public void setSampleType10(int sampleType10) {
        this.sampleType10 = sampleType10;
    }

    public int getAcceptCount() {
        return acceptCount;
    }

    public void setAcceptCount(int acceptCount) {
        this.acceptCount = acceptCount;
    }

    public int getInstoredSampl() {
        return instoredSampl;
    }

    public void setInstoredSampl(int instoredSampl) {
        this.instoredSampl = instoredSampl;
    }
}
