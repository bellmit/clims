package com.bazl.clims.service;

import com.bazl.clims.model.PageInfo;
import com.bazl.clims.model.po.MatchSameResult;
import com.bazl.clims.model.vo.LimsCaseInfoVo;
import com.bazl.clims.model.vo.MatchSameResultVo;

import java.util.List;

/**
 * Created by Sun on 2019/4/12.
 */
public interface MatchSameResultService {

    int deleteByPrimaryKey(String id);

    int insert(MatchSameResult record);

    MatchSameResult selectByPrimaryKey(String id);

    List<MatchSameResult> selectAll();

    int updateByPrimaryKey(MatchSameResult record);

    List<MatchSameResult> selectBySampleId(String sampleAid, String sampleBid);

    List<MatchSameResult> selectByOneSampleId(String sampleid);

    List<MatchSameResult> selectBySampleIdAndGroupId(MatchSameResult matchSameResult);

    List<MatchSameResultVo> selectGroupAll(MatchSameResultVo matchSameResult, PageInfo pageInfo);
    /**
     * 串并案
     * @return
     */
    List<MatchSameResultVo>  selectStrandGroupAll(MatchSameResultVo matchSameResult, PageInfo pageInfo);

    int selectListCount(MatchSameResultVo matchSameResultVo);

    /**
     * 串并案
     * @param matchSameResultVo
     * @return
     */
    int selectStrandVOCount(MatchSameResultVo matchSameResultVo);

    List<MatchSameResultVo> selectListByGroupId(MatchSameResultVo matchSameResultVo);

    List<MatchSameResult> selectByGroupId(MatchSameResult matchSameResult, PageInfo pageInfo);

    List<MatchSameResultVo> selectMatchResultList(MatchSameResultVo matchSameResult, PageInfo pageInfo);

    int selectMatchResultCount(MatchSameResultVo matchSameResult);

    List<MatchSameResult> chuanbingdan(String groupId);

    void relieveGroupRelevance(MatchSameResult matchSameResult);

    void relieveRelevance(MatchSameResult matchSameResult);

    void confirmCompareGroup(MatchSameResult matchSameResult);

    void confirmCompare(MatchSameResult matchSameResult);

    MatchSameResult selectById(String id);

    int selectByGroupIdCount(MatchSameResult matchSameResult);

    /**
     * 根据条件查询信息
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
