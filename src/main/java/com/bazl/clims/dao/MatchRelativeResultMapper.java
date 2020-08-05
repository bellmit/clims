package com.bazl.clims.dao;

import com.bazl.clims.model.po.MatchRelativeResult;
import com.bazl.clims.model.vo.MatchRelativeResultVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MatchRelativeResultMapper {
    int deleteByPrimaryKey(String id);

    int insert(MatchRelativeResult record);

    MatchRelativeResult selectByPrimaryKey(String id);

    List<MatchRelativeResult> selectAll();

    int updateByPrimaryKey(MatchRelativeResult record);

    List<MatchRelativeResult> selectBySampleId(@Param("sampleaid") String sampleaid,@Param("samplebid") String samplebid,@Param("samplecid") String samplecid);

    List<MatchRelativeResultVo> selectMatchRelativeResultList(MatchRelativeResultVo matchRelativeResultVo);

    int selectMatchRelativeResultCount(MatchRelativeResultVo matchRelativeResultVo);

    MatchRelativeResult selectByGroupId(MatchRelativeResult matchRelativeResult);

    void relieveRelativeGroupRelevance(MatchRelativeResult matchRelativeResult);

    void confirmRelativeCompareGroup(MatchRelativeResult matchRelativeResult);

    void confirmRelativeCompare(MatchRelativeResult matchRelativeResult);

    MatchRelativeResult selectById(String id);

    void relieveRelativeRelevance(MatchRelativeResult matchRelativeResult);

    /**
     * 根据条件查询亲缘比对结果
     * @param matchRelativeResult
     * @return
     */
    public List<MatchRelativeResult> selectList(MatchRelativeResult matchRelativeResult);

    /**
     * 根据groupId查询信息
     * @param groupId
     * @return
     */
    public List<MatchRelativeResult> selectListByGroupId(String groupId);
}