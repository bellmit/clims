package com.bazl.clims.model.po;

import com.bazl.clims.model.vo.AbstractBaseVo;

/**
 * 手工提取  使用仪器表
 */
public class UseInstruments {
    /**
     * 主键id
     */
    private String id;

    /**
     * 方法
     */
    private String methodName;

    /**
     * 离1 使用仪器
     */
    private String leave;

    /**
     * 离2 使用仪器
     */
    private String leavetwo;

    /**
     * 浴1 使用仪器
     */
    private String bath;

    /**
     * 浴2 使用仪器
     */
    private String bathtwo;

    /**
     * 干1 使用仪器
     */
    private String dry;

    /**
     * 干2 使用仪器
     */
    private String drytwo;

    /**摇 使用仪器*/
    private String shake;

    /**震 使用仪器*/
    private String earthquake;

    /**
     * 分局code
     * @return
     */
    private String orgCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName == null ? null : methodName.trim();
    }

    public String getLeave() {
        return leave;
    }

    public void setLeave(String leave) {
        this.leave = leave == null ? null : leave.trim();
    }

    public String getLeavetwo() {
        return leavetwo;
    }

    public void setLeavetwo(String leavetwo) {
        this.leavetwo = leavetwo == null ? null : leavetwo.trim();
    }

    public String getBath() {
        return bath;
    }

    public void setBath(String bath) {
        this.bath = bath == null ? null : bath.trim();
    }

    public String getBathtwo() {
        return bathtwo;
    }

    public void setBathtwo(String bathtwo) {
        this.bathtwo = bathtwo == null ? null : bathtwo.trim();
    }

    public String getDry() {
        return dry;
    }

    public void setDry(String dry) {
        this.dry = dry == null ? null : dry.trim();
    }

    public String getDrytwo() {
        return drytwo;
    }

    public void setDrytwo(String drytwo) {
        this.drytwo = drytwo == null ? null : drytwo.trim();
    }

    public String getShake() {
        return shake;
    }

    public void setShake(String shake) {
        this.shake = shake == null ? null : shake.trim();
    }

    public String getEarthquake() {
        return earthquake;
    }

    public void setEarthquake(String earthquake) {
        this.earthquake = earthquake == null ? null : earthquake.trim();
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }
}