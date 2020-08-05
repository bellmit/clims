package com.bazl.clims.dao;

import com.bazl.clims.model.po.MatchSameResult;
import com.bazl.clims.model.vo.LimsCaseInfoVo;
import com.bazl.clims.model.vo.MatchSameResultVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MatchSameResultMapper {
    int deleteByPrimaryKey(String id);

    int insert(MatchSameResult record);

    MatchSameResult selectByPrimaryKey(String id);

    List<MatchSameResult> selectAll();

    int updateByPrimaryKey(MatchSameResult record);

    List<MatchSameResult> selectBySampleId(@Param("sampleAid")String sampleAid, @Param("sampleBid")String sampleBid);

    List<MatchSameResult> selectByOneSampleId(@Param("sampleid")String sampleid);

    List<MatchSameResult> selectBySampleIdAndGroupId(MatchSameResult matchSameResult);

    List<MatchSameResultVo> selectGroupAll(MatchSameResultVo matchSameResultVo);
    /**
     * 串并案
     * @return
     */
    List<MatchSameResultVo>  selectStrandGroupAll(MatchSameResultVo matchSameResultVo);
    /**
     * 串并案
     * @param matchSameResultVo
     * @return
     */
    int selectStrandVOCount(MatchSameResultVo matchSameResultVo);
    int selectVOCount(MatchSameResultVo matchSameResultVo);

    public List<MatchSameResultVo> selectListByGroupId(MatchSameResultVo matchSameResultVo);

    List<MatchSameResult> selectByGroupId(MatchSameResult matchSameResult);

    List<MatchSameResultVo> selectMatchResultList(MatchSameResultVo matchSameResultVo);

    int selectMatchResultCount(MatchSameResultVo matchSameResultVo);

    List<MatchSameResult> chuanbingdan(String groupId);

    void relieveGroupRelevance(MatchSameResult matchSameResult);

    void relieveRelevance(MatchSameResult matchSameResult);

    void confirmCompareGroup(MatchSameResult matchSameResult);

    void confirmCompare(MatchSameResult matchSameResult);

    MatchSameResult selectById(String id);

    int selectByGroupIdCount(MatchSameResult matchSameResult);

    /**
     * 根据条件查询列表信息
     * @param matchSameResult
     * @return
     */
    public List<MatchSameResult> selectList(MatchSameResult matchSameResult);

    public List<MatchSameResult> selectChuanBinBList(MatchSameResult matchSameResult);
    /*
    *   案件比中统计
    * */
    int selectMathCount(LimsCaseInfoVo query);
    /*
    *   检材比中统计
    * */
    int selectSampleCount(LimsCaseInfoVo query);
}