package com.bazl.clims.service.impl;

import com.bazl.clims.dao.MatchSameResultMapper;
import com.bazl.clims.model.PageInfo;
import com.bazl.clims.model.po.MatchSameResult;
import com.bazl.clims.model.vo.LimsCaseInfoVo;
import com.bazl.clims.model.vo.MatchSameResultVo;
import com.bazl.clims.service.MatchSameResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Sun on 2019/4/12.
 */
@Service
public class MatchSameResultServiceImpl extends BaseService implements MatchSameResultService {

    @Autowired
    MatchSameResultMapper matchSameResultMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return matchSameResultMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(MatchSameResult record) {
        return matchSameResultMapper.insert(record);
    }

    @Override
    public MatchSameResult selectByPrimaryKey(String id) {
        return matchSameResultMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<MatchSameResult> selectAll() {
        return matchSameResultMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(MatchSameResult record) {
        return matchSameResultMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<MatchSameResult> selectBySampleId(String sampleAid, String sampleBid){
        return matchSameResultMapper.selectBySampleId(sampleAid, sampleBid);
    }

    @Override
    public List<MatchSameResult> selectByOneSampleId(String sampleid){
        return matchSameResultMapper.selectByOneSampleId(sampleid);
    }

    @Override
    public List<MatchSameResult> selectBySampleIdAndGroupId(MatchSameResult matchSameResult) {
        return matchSameResultMapper.selectBySampleIdAndGroupId(matchSameResult);
    }

    /**
     * 查询所有组id
     * @param matchSameResult
     * @param pageInfo
     * @return
     */
    @Override
    public List<MatchSameResultVo> selectGroupAll(MatchSameResultVo matchSameResult, PageInfo pageInfo) {
        List<MatchSameResultVo> matchSameVOList = null;
        int pageNo;
        int pageSize;
        try {
            pageNo = pageInfo.getPage();
            pageSize = pageInfo.getEvePageRecordCnt();
            matchSameResult.setOffset((pageNo - 1) * pageSize);
            matchSameResult.setRows(matchSameResult.getOffset() + pageSize);

            matchSameVOList = matchSameResultMapper.selectGroupAll(matchSameResult);
        } catch(Exception ex) {
            logger.info("查询所有组id："+ex);
            return null;
        }

        return matchSameVOList;
    }

    @Override
    public List<MatchSameResultVo> selectStrandGroupAll(MatchSameResultVo matchSameResult, PageInfo pageInfo) {
        List<MatchSameResultVo> matchSameVOList = null;
        int pageNo;
        int pageSize;
        try {
            pageNo = pageInfo.getPage();
            pageSize = pageInfo.getEvePageRecordCnt();
            matchSameResult.setOffset((pageNo - 1) * pageSize);
            matchSameResult.setRows(matchSameResult.getOffset() + pageSize);

            matchSameVOList = matchSameResultMapper.selectStrandGroupAll(matchSameResult);
        } catch(Exception ex) {
            logger.info("查询所有组id："+ex);
            return null;
        }

        return matchSameVOList;
    }

    @Override
    public int selectStrandVOCount(MatchSameResultVo matchSameResultVo) {
        return matchSameResultMapper.selectStrandVOCount(matchSameResultVo);
    }

    @Override
    public int selectListCount(MatchSameResultVo matchSameResultVo) {
        return matchSameResultMapper.selectVOCount(matchSameResultVo);
    }

    @Override
    public List<MatchSameResultVo> selectListByGroupId(MatchSameResultVo matchSameResultVo) {
        return matchSameResultMapper.selectListByGroupId(matchSameResultVo);
    }

    @Override
    public List<MatchSameResult> selectByGroupId(MatchSameResult matchSameResult, PageInfo pageInfo) {
        List<MatchSameResult> matchSameList = null;
        int pageNo;
        int pageSize;
        try {
            pageNo = pageInfo.getPage();
            pageSize = pageInfo.getEvePageRecordCnt();
            matchSameResult.setOffset((pageNo - 1) * pageSize);
            matchSameResult.setRows(matchSameResult.getOffset() + pageSize);

            matchSameList = matchSameResultMapper.selectByGroupId(matchSameResult);
        } catch(Exception ex) {
            logger.info("查询亲缘失败"+ex);
            return null;
        }
        return matchSameList;
    }

    @Override
    public List<MatchSameResultVo> selectMatchResultList(MatchSameResultVo matchSameResult, PageInfo pageInfo) {
        List<MatchSameResultVo> matchSameVOList = null;
        int pageNo;
        int pageSize;
        try {
            pageNo = pageInfo.getPage();
            pageSize = pageInfo.getEvePageRecordCnt();
            matchSameResult.setOffset((pageNo - 1) * pageSize);
            matchSameResult.setRows(matchSameResult.getOffset() + pageSize);

            matchSameVOList = matchSameResultMapper.selectMatchResultList(matchSameResult);
        } catch(Exception ex) {
            logger.info("查询亲缘失败"+ex);
            return null;
        }

        return matchSameVOList;
    }

    @Override
    public int selectMatchResultCount(MatchSameResultVo matchSameResultVo) {
        return matchSameResultMapper.selectMatchResultCount(matchSameResultVo);
    }

    @Override
    public List<MatchSameResult> chuanbingdan(String groupId) {
        return matchSameResultMapper.chuanbingdan(groupId);
    }

    /**
     * 解除关联
     * @param matchSameResult
     */
    @Override
    public void relieveGroupRelevance(MatchSameResult matchSameResult) {
        matchSameResultMapper.relieveGroupRelevance(matchSameResult);
    }

    /**
     * 根据主键解除关联（单个解除关联）
     * @param matchSameResult
     */
    @Override
    public void relieveRelevance(MatchSameResult matchSameResult) {
        matchSameResultMapper.relieveRelevance(matchSameResult);
    }

    /**
     * 确认比中（组）
     * @param matchSameResult
     */
    @Override
    public void confirmCompareGroup(MatchSameResult matchSameResult) {
        matchSameResultMapper.confirmCompareGroup(matchSameResult);
    }

    @Override
    public void confirmCompare(MatchSameResult matchSameResult) {
        matchSameResultMapper.confirmCompare(matchSameResult);
    }

    @Override
    public MatchSameResult selectById(String id) {
        return matchSameResultMapper.selectById(id);
    }

    @Override
    public int selectByGroupIdCount(MatchSameResult matchSameResult) {
        return matchSameResultMapper.selectByGroupIdCount(matchSameResult);
    }

    @Override
    public List<MatchSameResult> selectList(MatchSameResult matchSameResult) {
        return matchSameResultMapper.selectList(matchSameResult);
    }

    @Override
    public List<MatchSameResult> selectChuanBinBList(MatchSameResult matchSameResult) {
        return matchSameResultMapper.selectChuanBinBList(matchSameResult);
    }

    @Override
    public int selectMathCount(LimsCaseInfoVo query) {
        return matchSameResultMapper.selectMathCount(query);
    }

    @Override
    public int selectSampleCount(LimsCaseInfoVo query) {
        return matchSameResultMapper.selectSampleCount(query);
    }

}
