package com.bazl.clims.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 实验室信息实体类
 */
public class LaboratoryInfo {
    /**
     * 主键id
     */
    private String id;
    /**
     * 实验室名称
     */
    private String labName;
    /**
     * 实验室完成案件总量
     */
    private Integer labTatalCase;
    /**
     *实验室温度
     */
    private String labTemperature;
    /**
     *实验室湿度
     */
    private String labHumidity;
    /**
     *实验室成立时间
     */
    private String labSetupTime;
    /**
     *实验室人员数量
     */
    private String labPersonnel;
    /**
     *实验室简介
     */
    private String labIntroduction;
    /**
     *区域ID
     */
    private String orgId;
    /**
     *区域IP段
     */
    private String ipSegment;


    private List<String> nocaseNumStr=new ArrayList<>();
    private List<String> caseNumberStr=new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    public Integer getLabTatalCase() {
        return labTatalCase;
    }

    public void setLabTatalCase(Integer labTatalCase) {
        this.labTatalCase = labTatalCase;
    }

    public String getLabTemperature() {
        return labTemperature;
    }

    public void setLabTemperature(String labTemperature) {
        this.labTemperature = labTemperature;
    }

    public String getLabHumidity() {
        return labHumidity;
    }

    public void setLabHumidity(String labHumidity) {
        this.labHumidity = labHumidity;
    }

    public String getLabSetupTime() {
        return labSetupTime;
    }

    public void setLabSetupTime(String labSetupTime) {
        this.labSetupTime = labSetupTime;
    }

    public String getLabPersonnel() {
        return labPersonnel;
    }

    public void setLabPersonnel(String labPersonnel) {
        this.labPersonnel = labPersonnel;
    }

    public String getLabIntroduction() {
        return labIntroduction;
    }

    public void setLabIntroduction(String labIntroduction) {
        this.labIntroduction = labIntroduction;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getIpSegment() {
        return ipSegment;
    }

    public void setIpSegment(String ipSegment) {
        this.ipSegment = ipSegment;
    }

    public List<String> getNocaseNumStr() {
        return nocaseNumStr;
    }

    public void setNocaseNumStr(List<String> nocaseNumStr) {
        this.nocaseNumStr = nocaseNumStr;
    }

    public List<String> getCaseNumberStr() {
        return caseNumberStr;
    }

    public void setCaseNumberStr(List<String> caseNumberStr) {
        this.caseNumberStr = caseNumberStr;
    }
}
