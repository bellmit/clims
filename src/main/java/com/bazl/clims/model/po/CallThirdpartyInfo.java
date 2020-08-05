package com.bazl.clims.model.po;

import java.util.List;

public class CallThirdpartyInfo {

    private String Userid;
    private String UserName;
    private String Orgid;
    private String Orgname;
    private String ip;
    private String IdentityCard;//身份证
    private List<String> phones;//发送的手机号
    private List<MobileNews> Content; //发送内容

    @Override
    public String toString() {
        return "CallThirdpartyInfo{" +
                "Userid='" + Userid + '\'' +
                ", UserName='" + UserName + '\'' +
                ", Orgid='" + Orgid + '\'' +
                ", Orgname='" + Orgname + '\'' +
                ", ip='" + ip + '\'' +
                ", IdentityCard='" + IdentityCard + '\'' +
                ", phones=" + phones +
                ", Content=" + Content +
                '}';
    }

    public String getUserid() {
        return Userid;
    }

    public void setUserid(String userid) {
        Userid = userid;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getOrgid() {
        return Orgid;
    }

    public void setOrgid(String orgid) {
        Orgid = orgid;
    }

    public String getOrgname() {
        return Orgname;
    }

    public void setOrgname(String orgname) {
        Orgname = orgname;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIdentityCard() {
        return IdentityCard;
    }

    public void setIdentityCard(String identityCard) {
        IdentityCard = identityCard;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    public List<MobileNews> getContent() {
        return Content;
    }

    public void setContent(List<MobileNews> content) {
        Content = content;
    }
}
