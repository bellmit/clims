package com.bazl.clims.model.po;

/**
 * @author wanghaiyang
 * @date 2019/3/11.
 */
public class Examiner {

    /** 主键ID */
    private String id;

    /** 检验人1 */
    private String inspector1;

    /** 检验人2 */
    private String inspector2;

    /**检验人3*/
    private String inspector3;

    /**检验人一职称*/
    private String titleOne;

    /**检验人二职称*/
    private String titleTwo;

    /**检验人三职称*/
    private String titleThree;

    /**案件id*/
    private String caseId;

    public String getTitleOne() {
        return titleOne;
    }

    public void setTitleOne(String titleOne) {
        this.titleOne = titleOne;
    }

    public String getTitleTwo() {
        return titleTwo;
    }

    public void setTitleTwo(String titleTwo) {
        this.titleTwo = titleTwo;
    }

    public String getTitleThree() {
        return titleThree;
    }

    public void setTitleThree(String titleThree) {
        this.titleThree = titleThree;
    }

    private String inspector1Name;

    private String inspector2Name;

    private String inspector3Name;

    private  String phone1;
    private  String phone2;
    private  String phone3;

    @Override
    public String toString() {
        return "Examiner{" +
                "id='" + id + '\'' +
                ", inspector1='" + inspector1 + '\'' +
                ", inspector2='" + inspector2 + '\'' +
                ", inspector3='" + inspector3 + '\'' +
                ", caseId='" + caseId + '\'' +
                ", inspector1Name='" + inspector1Name + '\'' +
                ", inspector2Name='" + inspector2Name + '\'' +
                ", inspector3Name='" + inspector3Name + '\'' +
                ", phone1='" + phone1 + '\'' +
                ", phone2='" + phone2 + '\'' +
                ", phone3='" + phone3 + '\'' +
                '}';
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getPhone3() {
        return phone3;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInspector1() {
        return inspector1;
    }

    public void setInspector1(String inspector1) {
        this.inspector1 = inspector1;
    }

    public String getInspector2() {
        return inspector2;
    }

    public void setInspector2(String inspector2) {
        this.inspector2 = inspector2;
    }

    public String getInspector3() {
        return inspector3;
    }

    public void setInspector3(String inspector3) {
        this.inspector3 = inspector3;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getInspector1Name() {
        return inspector1Name;
    }

    public void setInspector1Name(String inspector1Name) {
        this.inspector1Name = inspector1Name;
    }

    public String getInspector2Name() {
        return inspector2Name;
    }

    public void setInspector2Name(String inspector2Name) {
        this.inspector2Name = inspector2Name;
    }

    public String getInspector3Name() {
        return inspector3Name;
    }

    public void setInspector3Name(String inspector3Name) {
        this.inspector3Name = inspector3Name;
    }
}