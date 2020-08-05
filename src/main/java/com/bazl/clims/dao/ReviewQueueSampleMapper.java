package com.bazl.clims.dao;

import com.bazl.clims.model.po.ReviewQueueSample;
import com.bazl.clims.model.vo.ReviewQueueSampleVo;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2019/3/27.
 */
public interface ReviewQueueSampleMapper {

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
     * @return
     */
    public List<ReviewQueueSampleVo> selectVOPaginationList(ReviewQueueSampleVo query);

    /**
     * 查询待复检检材Count
     * @param reviewQueueSample
     * @return
     */
    public int selectReviewQueueSampleCount(ReviewQueueSampleVo reviewQueueSample);

    public void updateReviewSampleStatus(ReviewQueueSample reviewQueueSample);

    public void deleteByGeneId(String geneId);

    public List<ReviewQueueSampleVo> queryList(ReviewQueueSampleVo query);
}