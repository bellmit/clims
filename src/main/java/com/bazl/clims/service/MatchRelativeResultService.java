package com.bazl.clims.service;

import com.bazl.clims.model.PageInfo;
import com.bazl.clims.model.po.MatchRelativeResult;
import com.bazl.clims.model.vo.MatchRelativeResultVo;

import java.util.List;

/**
 * Created by Sun on 2019/4/15.
 */
public interface MatchRelativeResultService {

    public int deleteByPrimaryKey(String id);

    public int insert(MatchRelativeResult record);

    public MatchRelativeResult selectByPrimaryKey(String id);

    public List<MatchRelativeResult> selectAll();

    public int updateByPrimaryKey(MatchRelativeResult record);

    public List<MatchRelativeResult> selectBySampleId(String sampleaid, String samplebid, String samplecid);

    public List<MatchRelativeResultVo> selectMatchRelativeResultList(MatchRelativeResultVo matchRelativeResultVo, PageInfo pageInfo);

    public int selectMatchRelativeResultCount(MatchRelativeResultVo matchRelativeResultVo);

    public MatchRelativeResult selectByGroupId(MatchRelativeResult matchRelativeResult);

    public void relieveRelativeGroupRelevance(MatchRelativeResult matchRelativeResult);

    public void confirmRelativeCompareGroup(MatchRelativeResult matchRelativeResult);

    public void confirmRelativeCompare(MatchRelativeResult matchRelativeResult);

    public MatchRelativeResult selectById(String id);

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
