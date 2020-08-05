package com.bazl.clims.model.vo;

import com.bazl.clims.model.po.MatchAuditedGene;

import java.io.Serializable;

/**
 * Created by Administrator on 2019/11/6.
 */
public class MatchAuditedGeneVo extends AbstractBaseVo<MatchAuditedGene> implements Serializable {

    public MatchAuditedGeneVo() {
        super();
        this.entity = new MatchAuditedGene();
    }

    public MatchAuditedGeneVo(MatchAuditedGene entity) {
        super();
        this.entity = entity;
    }
}
