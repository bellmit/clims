package com.bazl.clims.service.impl;

import com.bazl.clims.dao.ReviewQueueSampleMapper;
import com.bazl.clims.model.PageInfo;
import com.bazl.clims.model.po.ReviewQueueSample;
import com.bazl.clims.model.vo.ReviewQueueSampleVo;
import com.bazl.clims.service.ReviewQueueSampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2019/3/8.
 */
@Service
public class ReviewQueueSampleServiceImpl  extends BaseService implements ReviewQueueSampleService {

    @Autowired
    ReviewQueueSampleMapper reviewQueueSampleMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return reviewQueueSampleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ReviewQueueSample record) {
        return reviewQueueSampleMapper.insert(record);
    }

    @Override
    public ReviewQueueSample selectByPrimaryKey(String id) {
        return reviewQueueSampleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ReviewQueueSample> selectAll() {
        return reviewQueueSampleMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(ReviewQueueSample record) {
        return reviewQueueSampleMapper.updateByPrimaryKey(record);
    }

    /**
     * 查询待复检检材列表
     * @param query
     * @param pageInfo
     * @return
     */
    @Override
    public List<ReviewQueueSampleVo> selectReviewQueueSampleList(ReviewQueueSampleVo query, PageInfo pageInfo) {
        List<ReviewQueueSampleVo> reviewQueueSampleList = null;
        pageInfo.setEvePageRecordCnt(88);
        int pageNo;
        int pageSize;
        try {
            pageNo = pageInfo.getPage();
            pageSize = pageInfo.getEvePageRecordCnt();
            query.setOffset((pageNo - 1) * pageSize);
            query.setRows(query.getOffset() + pageSize);

            reviewQueueSampleList = reviewQueueSampleMapper.selectVOPaginationList(query);
        } catch(Exception ex) {
            logger.info("查询待复检检材列表报错："+ex);
            return null;
        }
        return reviewQueueSampleList;
    }

    @Override
    public int selectReviewQueueSampleCount(ReviewQueueSampleVo query) {
        return reviewQueueSampleMapper.selectReviewQueueSampleCount(query);
    }

    /**
     * 更新复检检材状态为已复检
     * @param reviewQueueSample
     */
    @Override
    public void updateReviewSampleStatus(ReviewQueueSample reviewQueueSample){
        reviewQueueSampleMapper.updateReviewSampleStatus(reviewQueueSample);
    }

    @Override
    public void deleteByGeneId(String geneId){
        reviewQueueSampleMapper.deleteByGeneId(geneId);
    }

    @Override
    public List<ReviewQueueSampleVo> queryList(ReviewQueueSampleVo query,PageInfo pageInfo) {
        List<ReviewQueueSampleVo> reviewQueueSampleList = null;
        pageInfo.setEvePageRecordCnt(88);
        int pageNo;
        int pageSize;
        try {
            pageNo = pageInfo.getPage();
            pageSize = pageInfo.getEvePageRecordCnt();
            query.setOffset((pageNo - 1) * pageSize);
            query.setRows(query.getOffset() + pageSize);

            reviewQueueSampleList = reviewQueueSampleMapper.queryList(query);;
        } catch(Exception ex) {
            logger.info("查询待复检检材列表报错："+ex);
            return null;
        }
        return reviewQueueSampleList;
    }

}
