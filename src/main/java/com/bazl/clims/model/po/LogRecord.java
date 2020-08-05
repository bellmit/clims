package com.bazl.clims.model.po;

public class LogRecord
{
    private String FWBS;//服务标识

    private String XXCZRY_XM;//信息操作人员_姓名

    private String XXCZRY_GMSFHM;//信息操作人员_编号

    private String XXCZRY_GAJGJGDM;//信息操作人员_机构代码

    private String FWQQ_RQSJ;//服务请求_日期时间

    private String FWQQ_BWBH;//报文编号

    private String FFBS;//方法标识

    private String BWLYDKH;//报文来源端口

    private FWQQ_NR FWQQ_NR;//服务请求_内容

    private String FWQQZ_ZCXX;//服务请求者_注册信息

    private String FWQQ_SJSJLX;//服务请求_审计事件类型

    private String BWLYIPDZ;//报文来源IP地址

    private String FWQQSB_BH;//服务请求设备_编号

    private String YYSBS;//运营商标识

    public String getFWBS() {
        return FWBS;
    }

    public void setFWBS(String FWBS) {
        this.FWBS = FWBS;
    }

    public String getXXCZRY_XM() {
        return XXCZRY_XM;
    }

    public void setXXCZRY_XM(String XXCZRY_XM) {
        this.XXCZRY_XM = XXCZRY_XM;
    }

    public String getXXCZRY_GMSFHM() {
        return XXCZRY_GMSFHM;
    }

    public void setXXCZRY_GMSFHM(String XXCZRY_GMSFHM) {
        this.XXCZRY_GMSFHM = XXCZRY_GMSFHM;
    }

    public String getXXCZRY_GAJGJGDM() {
        return XXCZRY_GAJGJGDM;
    }

    public void setXXCZRY_GAJGJGDM(String XXCZRY_GAJGJGDM) {
        this.XXCZRY_GAJGJGDM = XXCZRY_GAJGJGDM;
    }

    public String getFWQQ_RQSJ() {
        return FWQQ_RQSJ;
    }

    public void setFWQQ_RQSJ(String FWQQ_RQSJ) {
        this.FWQQ_RQSJ = FWQQ_RQSJ;
    }

    public String getFWQQ_BWBH() {
        return FWQQ_BWBH;
    }

    public void setFWQQ_BWBH(String FWQQ_BWBH) {
        this.FWQQ_BWBH = FWQQ_BWBH;
    }

    public String getFFBS() {
        return FFBS;
    }

    public void setFFBS(String FFBS) {
        this.FFBS = FFBS;
    }

    public String getBWLYDKH() {
        return BWLYDKH;
    }

    public void setBWLYDKH(String BWLYDKH) {
        this.BWLYDKH = BWLYDKH;
    }

    public com.bazl.clims.model.po.FWQQ_NR getFWQQ_NR() {
        return FWQQ_NR;
    }

    public void setFWQQ_NR(com.bazl.clims.model.po.FWQQ_NR FWQQ_NR) {
        this.FWQQ_NR = FWQQ_NR;
    }

    public String getFWQQZ_ZCXX() {
        return FWQQZ_ZCXX;
    }

    public void setFWQQZ_ZCXX(String FWQQZ_ZCXX) {
        this.FWQQZ_ZCXX = FWQQZ_ZCXX;
    }

    public String getFWQQ_SJSJLX() {
        return FWQQ_SJSJLX;
    }

    public void setFWQQ_SJSJLX(String FWQQ_SJSJLX) {
        this.FWQQ_SJSJLX = FWQQ_SJSJLX;
    }

    public String getBWLYIPDZ() {
        return BWLYIPDZ;
    }

    public void setBWLYIPDZ(String BWLYIPDZ) {
        this.BWLYIPDZ = BWLYIPDZ;
    }

    public String getFWQQSB_BH() {
        return FWQQSB_BH;
    }

    public void setFWQQSB_BH(String FWQQSB_BH) {
        this.FWQQSB_BH = FWQQSB_BH;
    }

    public String getYYSBS() {
        return YYSBS;
    }

    public void setYYSBS(String YYSBS) {
        this.YYSBS = YYSBS;
    }

    @Override
    public String toString() {
        return "LogRecord{" +
                "FWBS='" + FWBS + '\'' +
                ", XXCZRY_XM='" + XXCZRY_XM + '\'' +
                ", XXCZRY_GMSFHM='" + XXCZRY_GMSFHM + '\'' +
                ", XXCZRY_GAJGJGDM='" + XXCZRY_GAJGJGDM + '\'' +
                ", FWQQ_RQSJ='" + FWQQ_RQSJ + '\'' +
                ", FWQQ_BWBH='" + FWQQ_BWBH + '\'' +
                ", FFBS='" + FFBS + '\'' +
                ", BWLYDKH='" + BWLYDKH + '\'' +
                ", FWQQ_NR=" + FWQQ_NR +
                ", FWQQZ_ZCXX='" + FWQQZ_ZCXX + '\'' +
                ", FWQQ_SJSJLX='" + FWQQ_SJSJLX + '\'' +
                ", BWLYIPDZ='" + BWLYIPDZ + '\'' +
                ", FWQQSB_BH='" + FWQQSB_BH + '\'' +
                ", YYSBS='" + YYSBS + '\'' +
                '}';
    }
}
