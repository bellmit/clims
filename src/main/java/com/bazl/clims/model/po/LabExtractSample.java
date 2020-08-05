package com.bazl.clims.model.po;

import com.alibaba.druid.sql.visitor.functions.If;

import java.util.Date;

/**
 * @author wanghaiyang
 * @date 2019/3/8.
 */
public class LabExtractSample implements Comparable<LabExtractSample> {

    /** 主键ID */
    private String id;

    /** 提取信息主键ID */
    private String extractId;

    /** 检材主键ID */
    private String sampleId;

    /** 转移方法 */
    private String transferMethod;

    /** 提取方法 */
    private String extractMethod;

    /** 预实验方法 */
    private String pretestMethod;

    /** 确证实验方法 */
    private String contestMethod;

    /** 提取参数 */
    private String extractString;

    /** 提取部位 */
    private String extractPart;

    /** 确证实验结果 */
    private String contestResult;

    /** 预实验结果 */
    private String pretestResult;

    /** 提取试剂 */
    private String extractReagent;

    /** 试剂用量 */
    private String reagentDosage;

    /** 板孔位置 */
    private String samplePostion;

    /** 创建时间 */
    private Date createDatetime;

    /** 创建人 */
    private String createPerson;

    /** 更新时间 */
    private Date updateDatetime;

    /** 更新人 */
    private String updatePerosn;

    private String sampleNo;

    private String sampleName;

    private String sampleType;

    /**离 使用仪器*/
    private String leave;

    /**离2 使用仪器*/
    private String leaveTwo;

    /**浴 使用仪器*/
    private String bath;

    /**浴2 使用仪器*/
    private String bathTwo;

    /**干 使用仪器*/
    private String dry;

    /**干2 使用仪器*/
    private String dryTwo;

    /**摇 使用仪器*/
    private String shake;

    /**震 使用仪器*/
    private String earthquake;

    /** LimsSampleInfoDna */
    private LimsSampleInfoDna limsSampleInfoDna;

    private String TES;
    private String SDS;
    private String PK;
    private boolean isOK;
    private boolean isTwe;
    private String TES1;
    private String SDS1;
    private String chelex;
    private String PK1;
    private String IMDTT;
    private String purification;

    private String caseId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExtractId() {
        return extractId;
    }

    public void setExtractId(String extractId) {
        this.extractId = extractId;
    }

    public String getSampleId() {
        return sampleId;
    }

    public void setSampleId(String sampleId) {
        this.sampleId = sampleId;
    }

    public String getTransferMethod() {
        return transferMethod;
    }

    public void setTransferMethod(String transferMethod) {
        this.transferMethod = transferMethod;
    }

    public String getExtractMethod() {
        return extractMethod;
    }

    public void setExtractMethod(String extractMethod) {
        this.extractMethod = extractMethod;
    }

    public String getPretestMethod() {
        return pretestMethod;
    }

    public void setPretestMethod(String pretestMethod) {
        this.pretestMethod = pretestMethod;
    }

    public String getContestMethod() {
        return contestMethod;
    }

    public void setContestMethod(String contestMethod) {
        this.contestMethod = contestMethod;
    }

    public String getExtractString() {
        return extractString;
    }

    public void setExtractString(String extractString) {
        this.extractString = extractString;
    }

    public String getExtractPart() {
        return extractPart;
    }

    public void setExtractPart(String extractPart) {
        this.extractPart = extractPart;
    }

    public String getContestResult() {
        return contestResult;
    }

    public void setContestResult(String contestResult) {
        this.contestResult = contestResult;
    }

    public String getPretestResult() {
        return pretestResult;
    }

    public void setPretestResult(String pretestResult) {
        this.pretestResult = pretestResult;
    }

    public String getExtractReagent() {
        return extractReagent;
    }

    public void setExtractReagent(String extractReagent) {
        this.extractReagent = extractReagent;
    }

    public String getReagentDosage() {
        return reagentDosage;
    }

    public void setReagentDosage(String reagentDosage) {
        this.reagentDosage = reagentDosage;
    }

    public String getSamplePostion() {
        return samplePostion;
    }

    public void setSamplePostion(String samplePostion) {
        this.samplePostion = samplePostion;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public String getUpdatePerosn() {
        return updatePerosn;
    }

    public void setUpdatePerosn(String updatePerosn) {
        this.updatePerosn = updatePerosn;
    }

    public String getSampleNo() {
        return sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public String getSampleType() {
        return sampleType;
    }

    public void setSampleType(String sampleType) {
        this.sampleType = sampleType;
    }

    public String getLeave() {
        return leave;
    }

    public void setLeave(String leave) {
        this.leave = leave;
    }

    public String getBath() {
        return bath;
    }

    public void setBath(String bath) {
        this.bath = bath;
    }

    public String getDry() {
        return dry;
    }

    public void setDry(String dry) {
        this.dry = dry;
    }

    public String getShake() {
        return shake;
    }

    public void setShake(String shake) {
        this.shake = shake;
    }

    public String getEarthquake() {
        return earthquake;
    }

    public void setEarthquake(String earthquake) {
        this.earthquake = earthquake;
    }

    public LimsSampleInfoDna getLimsSampleInfoDna() {
        return limsSampleInfoDna;
    }

    public void setLimsSampleInfoDna(LimsSampleInfoDna limsSampleInfoDna) {
        this.limsSampleInfoDna = limsSampleInfoDna;
    }

    public String getTES() {
        return TES;
    }

    public void setTES(String TES) {
        this.TES = TES;
    }

    public String getSDS() {
        return SDS;
    }

    public void setSDS(String SDS) {
        this.SDS = SDS;
    }

    public String getPK() {
        return PK;
    }

    public void setPK(String PK) {
        this.PK = PK;
    }

    public boolean getIsOK() {
        return isOK;
    }

    public void setIsOK(boolean isOK) {
        this.isOK = isOK;
    }

    public boolean getIsTwe() {
        return isTwe;
    }

    public void setIsTwe(boolean isTwe) {
        this.isTwe = isTwe;
    }

    public String getTES1() {
        return TES1;
    }

    public void setTES1(String TES1) {
        this.TES1 = TES1;
    }

    public String getSDS1() {
        return SDS1;
    }

    public void setSDS1(String SDS1) {
        this.SDS1 = SDS1;
    }

    public String getChelex() {
        return chelex;
    }

    public void setChelex(String chelex) {
        this.chelex = chelex;
    }

    public String getPK1() {
        return PK1;
    }

    public void setPK1(String PK1) {
        this.PK1 = PK1;
    }

    public String getIMDTT() {
        return IMDTT;
    }

    public void setIMDTT(String IMDTT) {
        this.IMDTT = IMDTT;
    }

    public String getPurification() {
        return purification;
    }

    public void setPurification(String purification) {
        this.purification = purification;
    }

    public String getLeaveTwo() {
        return leaveTwo;
    }

    public void setLeaveTwo(String leaveTwo) {
        this.leaveTwo = leaveTwo;
    }

    public String getBathTwo() {
        return bathTwo;
    }

    public void setBathTwo(String bathTwo) {
        this.bathTwo = bathTwo;
    }

    public String getDryTwo() {
        return dryTwo;
    }

    public void setDryTwo(String dryTwo) {
        this.dryTwo = dryTwo;
    }

    @Override
    public int compareTo(LabExtractSample o) {
        String samplePostion = o.samplePostion;
        String substring = samplePostion.substring(1);
        String substring1 = samplePostion.substring(0, 1);
        String sub = this.samplePostion.substring(1);
        String sub1 = this.samplePostion.substring(0, 1);
        if (substring1.equals(sub1)){//判断字母是否相等
            return this.samplePostion.substring(1).compareTo(samplePostion.substring(1));
        }else if (sub.equals(substring)){//判断数字是否相等
            return this.samplePostion.compareTo(o.samplePostion);
        }
        return this.samplePostion.substring(1).compareTo(samplePostion.substring(1));
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }
}