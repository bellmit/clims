package com.bazl.clims.service.impl;

import com.bazl.clims.dao.LimsSampleGeneMapper;
import com.bazl.clims.model.po.LimsSampleGene;
import com.bazl.clims.model.vo.LimsSampleGeneVo;
import com.bazl.clims.service.LimsSampleGeneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2019/2/26.
 */
@Service
public class LimsSampleGeneServiceImpl implements LimsSampleGeneService {

    @Autowired
    LimsSampleGeneMapper limsSampleGeneMapper;

    @Override
    public int deleteByPrimaryKey(String geneId) {
        return limsSampleGeneMapper.deleteByPrimaryKey(geneId);
    }

    @Override
    public int insert(LimsSampleGene record) {
        return limsSampleGeneMapper.insert(record);
    }

    @Override
    public LimsSampleGene selectByPrimaryKey(String geneId) {
        return limsSampleGeneMapper.selectByPrimaryKey(geneId);
    }

    @Override
    public List<LimsSampleGene> selectAll() {
        return limsSampleGeneMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(LimsSampleGene record) {
        return limsSampleGeneMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<LimsSampleGene> selectListByCaseId (String caseId) {
        return limsSampleGeneMapper.selectListByCaseId(caseId);
    }

    @Override
    public List<LimsSampleGeneVo> selecVOtList (LimsSampleGeneVo limsSampleGeneVo) {
        return limsSampleGeneMapper.selecVOtList(limsSampleGeneVo);
    }

    @Override
    public int updateBySampleId(String sampleId) {
        return limsSampleGeneMapper.updateBySampleId(sampleId);
    }

    @Override
    public void updateAuditStatus(LimsSampleGene limsSampleGene) {
        limsSampleGeneMapper.updateAuditStatus(limsSampleGene);
    }

    /**
     * 删除标记
     * @param limsSampleGene
     */
    @Override
    public void updateDeleteFlag(LimsSampleGene limsSampleGene) {
        limsSampleGeneMapper.updateDeleteFlag(limsSampleGene);
    }

    /**
     * 更新复检状态
     * @param limsSampleGene
     */
    @Override
    public void updateReviewStatus(LimsSampleGene limsSampleGene) {
        limsSampleGeneMapper.updateReviewStatus(limsSampleGene);
    }

    @Override
    public List<LimsSampleGene> selectAuditedBySampleId(String sampleId) {
        return limsSampleGeneMapper.selectAuditedBySampleId(sampleId);
    }

    @Override
    public List<LimsSampleGene> selectListBySampleId(String sampleId) {
        return limsSampleGeneMapper.selectListBySampleId(sampleId);
    }

    @Override
    public List<LimsSampleGene> selectListByAnalysisInfoId(String analysisInfoId) {
        return limsSampleGeneMapper.selectListByAnalysisInfoId(analysisInfoId);
    }

    @Override
    public List<LimsSampleGene> selectByGeneList(LimsSampleGene limsSampleGene) {
        return limsSampleGeneMapper.selectByGeneList(limsSampleGene);
    }
}
