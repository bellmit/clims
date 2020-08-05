package com.bazl.clims.dao;

import com.bazl.clims.model.po.MatchAuditedGene;
import com.bazl.clims.model.vo.MatchAuditedGeneVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author chaiyajie
 * @date 2019/3/26
 */

@Repository
public interface MatchAuditedGeneMapper {

    /**
     * 根据id删除信息
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入信息
     * @param record
     * @return
     */
    int insert(MatchAuditedGene record);

    /**
     * 根据id查询信息
     */
    MatchAuditedGene selectByPrimaryKey(String id);

    /**
     * 查询所有的信息
     * @return
     */
    List<MatchAuditedGene> selectAll();

    /**
     * 更新信息
     * @param record
     * @return
     */
    int updateByPrimaryKey(MatchAuditedGene record);

    /**
     * 根据检材编号SampleId查询信息
     */
    List<MatchAuditedGene> selectBySampleId(String sampleId);

    /**
     * 根据案件id查询信息
     * @param caseId
     * @return
     */
    public List<MatchAuditedGene> selectByCaseId(String caseId);


    /**
     * 根据条件查询信息
     * @param matchAuditedGene
     * @return
     */
    public List<MatchAuditedGene> selectByMatchAuditedGene(MatchAuditedGene matchAuditedGene);

    public List<MatchAuditedGene> selectGeneByPage(@Param("startRum")int startRum, @Param("endRum")int endRum, @Param("sampleFlag")String sampleFlag);


    /**
     * 根据编号查询信息
     * 根据编号查询信息
     * @param sampleNo
     * @return
     */
    public List<MatchAuditedGene> selectListBySampleNo(String sampleNo);

    /**
     * 根据检材id更新信息
     * @param matchAuditedGene
     */
    public int updateBySampleId(MatchAuditedGene matchAuditedGene);

    /**
     * 查询已检出基因型
     * @return
     */
    List<MatchAuditedGene> selectDetectedMatchAuditedGene();

    /**
     * 混合库获取混合样本信息
     * @return
     */
    List<MatchAuditedGene> selectByMatchAuditedGeneList(MatchAuditedGeneVo query);
}