package com.bazl.clims.service;

import com.bazl.clims.model.PageInfo;
import com.bazl.clims.model.po.ReviewQueueSample;
import com.bazl.clims.model.vo.ReviewQueueSampleVo;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2019/3/27.
 */
public interface ReviewQueueSampleService {

    /**
     * 根据id删除信息
     * @param id
     * @return
     */
    public int deleteByPrimaryKey(String id);

    /**
     * 插入信息
     * @param record
     * @return
     */
    public int insert(ReviewQueueSample record);

    /**
     * 根据id查询信息
     * @param id
     * @return
     */
    public ReviewQueueSample selectByPrimaryKey(String id);

    /**
     * 查询所有的信息
     * @return
     */
    public List<ReviewQueueSample> selectAll();

    /**
     * 更新信息
     * @param record
     * @return
     */
    public int updateByPrimaryKey(ReviewQueueSample record);

    /**
     * 查询待复检检材列表
     * @param query
     * @param pageInfo
     * @return
     */
    List<ReviewQueueSampleVo> selectReviewQueueSampleList(ReviewQueueSampleVo query, PageInfo pageInfo);

    /**
     * 查询待复检检材Count
     * @param reviewQueueSample
     * @return
     */
    int selectReviewQueueSampleCount(ReviewQueueSampleVo reviewQueueSample);

    /**
     * 更新复检检材状态为已复检
     * @param reviewQueueSample
     */
    void updateReviewSampleStatus(ReviewQueueSample reviewQueueSample);

    /**
     * 根据基因id删除复检队列
     * @param geneId
     */
    public void deleteByGeneId(String geneId);

    public List<ReviewQueueSampleVo> queryList(ReviewQueueSampleVo query,PageInfo pageInfo);
}
