package com.bazl.clims.model.vo;

import com.bazl.clims.model.po.LimsSampleInfoDna;

import java.io.Serializable;

/**
 * Created by sxr on 2019/3/28.
 */
public class LimsSampleInfoDnaVo extends AbstractBaseVo<LimsSampleInfoDna> implements Serializable {

    public LimsSampleInfoDnaVo() {
        super();
        this.entity = new LimsSampleInfoDna();
    }

    public LimsSampleInfoDnaVo(LimsSampleInfoDna entity) {
        super();
        this.entity = entity;
    }

    private String boardPosition;

    private String acceptOrgId;

    public String getBoardPosition() {
        return boardPosition;
    }

    public void setBoardPosition(String boardPosition) {
        this.boardPosition = boardPosition;
    }

    public String getAcceptOrgId() {
        return acceptOrgId;
    }

    public void setAcceptOrgId(String acceptOrgId) {
        this.acceptOrgId = acceptOrgId;
    }
}
