package com.bazl.clims.model.po;

/**
 * 单位信息表
 * ORG_INFO
 * Created by Administrator on 2018/12/21.
 */
public class OrgInfo {
    /**
     * 主键ID
     */
    private String orgId;
    /**
     * 父单位主键ID
     */
    private String parentId;
    /**
     * 单位名称
     */
    private String orgName;
    /**
     * 单位编号
     */
    private String orgCode;
    /**
     * 单位级别
     */
    private String orgLevel;
    /**
     * 单位地址
     */
    private String orgAddress;
    /**
     * 单位资质
     */
    private String orgQualification;
    /**
     * 单位邮编
     */
    private String orgZipCode;
    /**
     * 单位负责人
     */
    private String orgPrincipalPhone;
    /**
     * 单位负责人电话
     */
    private String orgPhoneNumber;
    /**
     * 单位联系人
     */
    private String orgContact;
    /**
     * 单位联系人电话
     */
    private String orgContactPhone;
    /**
     * 备注
     */
    private String ramerk;
    /**
     * 技术队长姓名
     */
    private String orgScience;
    /**
     * 技术队长电话
     */
    private String orgSciencePhone;
    /**
     *  机构鉴定资质证书路径
     */
    private String orgCertificateurl;
    /**
     *  机构鉴定资质证书名称
     */
    private String orgCertificatename;

    public String getOrgScience() {
        return orgScience;
    }

    public void setOrgScience(String orgScience) {
        this.orgScience = orgScience;
    }

    public String getOrgSciencePhone() {
        return orgSciencePhone;
    }

    public void setOrgSciencePhone(String orgSciencePhone) {
        this.orgSciencePhone = orgSciencePhone;
    }

    public String getOrgCertificateurl() {
        return orgCertificateurl;
    }

    public void setOrgCertificateurl(String orgCertificateurl) {
        this.orgCertificateurl = orgCertificateurl;
    }

    public String getOrgCertificatename() {
        return orgCertificatename;
    }

    public void setOrgCertificatename(String orgCertificatename) {
        this.orgCertificatename = orgCertificatename;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgLevel() {
        return orgLevel;
    }

    public void setOrgLevel(String orgLevel) {
        this.orgLevel = orgLevel;
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
    }

    public String getOrgQualification() {
        return orgQualification;
    }

    public void setOrgQualification(String orgQualification) {
        this.orgQualification = orgQualification;
    }

    public String getOrgZipCode() {
        return orgZipCode;
    }

    public void setOrgZipCode(String orgZipCode) {
        this.orgZipCode = orgZipCode;
    }

    public String getOrgPrincipalPhone() {
        return orgPrincipalPhone;
    }

    public void setOrgPrincipalPhone(String orgPrincipalPhone) {
        this.orgPrincipalPhone = orgPrincipalPhone;
    }

    public String getOrgPhoneNumber() {
        return orgPhoneNumber;
    }

    public void setOrgPhoneNumber(String orgPhoneNumber) {
        this.orgPhoneNumber = orgPhoneNumber;
    }

    public String getOrgContact() {
        return orgContact;
    }

    public void setOrgContact(String orgContact) {
        this.orgContact = orgContact;
    }

    public String getOrgContactPhone() {
        return orgContactPhone;
    }

    public void setOrgContactPhone(String orgContactPhone) {
        this.orgContactPhone = orgContactPhone;
    }

    public String getRamerk() {
        return ramerk;
    }

    public void setRamerk(String ramerk) {
        this.ramerk = ramerk;
    }
}