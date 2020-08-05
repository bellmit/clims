package com.bazl.clims.service.impl;

import com.bazl.clims.dao.MatchRelativeResultMapper;
import com.bazl.clims.model.PageInfo;
import com.bazl.clims.model.po.MatchRelativeResult;
import com.bazl.clims.model.vo.MatchRelativeResultVo;
import com.bazl.clims.model.vo.MatchSameResultVo;
import com.bazl.clims.service.MatchRelativeResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Sun on 2019/4/15.
 */
@Service
public class MatchRelativeResultServiceImpl extends BaseService implements MatchRelativeResultService {

    @Autowired
    MatchRelativeResultMapper matchRelativeResultMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return matchRelativeResultMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(MatchRelativeResult record) {
        return matchRelativeResultMapper.insert(record);
    }

    @Override
    public MatchRelativeResult selectByPrimaryKey(String id) {
        return matchRelativeResultMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<MatchRelativeResult> selectAll() {
        return matchRelativeResultMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(MatchRelativeResult record) {
        return matchRelativeResultMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<MatchRelativeResult> selectBySampleId(String sampleaid, String samplebid, String samplecid) {
        return matchRelativeResultMapper.selectBySampleId(sampleaid, samplebid, samplecid);
    }

    @Override
    public List<MatchRelativeResultVo> selectMatchRelativeResultList(MatchRelativeResultVo matchRelativeResultVo, PageInfo pageInfo) {
        List<MatchRelativeResultVo> matchRelativeResultVosList = null;
        int pageNo;
        int pageSize;
        try {
            pageNo = pageInfo.getPage();
            pageSize = pageInfo.getEvePageRecordCnt();
            matchRelativeResultVo.setOffset((pageNo - 1) * pageSize);
            matchRelativeResultVo.setRows(matchRelativeResultVo.getOffset() + pageSize);

            matchRelativeResultVosList = matchRelativeResultMapper.selectMatchRelativeResultList(matchRelativeResultVo);
        } catch(Exception ex) {
            logger.info("查询亲缘失败"+ex);
            return null;
        }

        return matchRelativeResultVosList;
    }

    @Override
    public int selectMatchRelativeResultCount(MatchRelativeResultVo matchRelativeResultVo) {
        return matchRelativeResultMapper.selectMatchRelativeResultCount(matchRelativeResultVo);
    }

    @Override
    public MatchRelativeResult selectByGroupId(MatchRelativeResult matchRelativeResult) {
        return matchRelativeResultMapper.selectByGroupId(matchRelativeResult);
    }

    /**
     * 解除亲缘关联
     * @param matchRelativeResult
     * @return
     */
    @Override
    public void relieveRelativeGroupRelevance(MatchRelativeResult matchRelativeResult) {
        matchRelativeResultMapper.relieveRelativeGroupRelevance(matchRelativeResult);
    }

    /**
     * 确认亲缘比中（组）
     * @param matchRelativeResult
     */
    @Override
    public void confirmRelativeCompareGroup(MatchRelativeResult matchRelativeResult) {
        matchRelativeResultMapper.confirmRelativeCompareGroup(matchRelativeResult);
    }

    /**
     * 确认亲缘比中
     * @param matchRelativeResult
     */
    @Override
    public void confirmRelativeCompare(MatchRelativeResult matchRelativeResult) {
        matchRelativeResultMapper.confirmRelativeCompare(matchRelativeResult);
    }

    @Override
    public MatchRelativeResult selectById(String id) {
        return matchRelativeResultMapper.selectById(id);
    }

    @Override
    public void relieveRelativeRelevance(MatchRelativeResult matchRelativeResult) {
        matchRelativeResultMapper.relieveRelativeRelevance(matchRelativeResult);
    }

    @Override
    public List<MatchRelativeResult> selectList(MatchRelativeResult matchRelativeResult) {
        return matchRelativeResultMapper.selectList(matchRelativeResult);
    }

    @Override
    public List<MatchRelativeResult> selectListByGroupId(String groupId) {
        return matchRelativeResultMapper.selectListByGroupId(groupId);
    }

}
