package com.bazl.clims.dao;

import com.bazl.clims.model.PageInfo;
import com.bazl.clims.model.po.QueueSample;
import com.bazl.clims.model.vo.LimsCaseInfoVo;
import com.bazl.clims.model.vo.QueueSampleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QueueSampleMapper {
    int deleteByPrimaryKey(String id);

    int insert(QueueSample record);

    QueueSample selectByPrimaryKey(String id);

    List<QueueSample> selectAll();

    int updateByPrimaryKey(QueueSample record);

    List<QueueSample> selectBySize(@Param("status")String status, @Param("queueType")String queueType, @Param("size") int size);

    int updateStatusByPrimaryKey(QueueSample record);

    /**
     * 查询入本地库问题
     * @return
     */
    List<QueueSampleVo> selectFindAll(QueueSampleVo query);


    /**
     * 查询本地库数据数量
     * @param query
     * @return
     */
    int selectVOCount(QueueSampleVo query);

//    /**
//     * 查询入国家库问题
//     * @return
//     */
//    List<LimsCaseInfoVo> selectFindstate(LimsCaseInfoVo query);
//
//    /**
//     * 查询国家库数据数量
//     * @param query
//     * @return
//     */
//    int selectStateCount(LimsCaseInfoVo query);

    /**
     * 受理案件时插入一条队列数据
     * @param queueSample
     */
    void insertQueueSample(QueueSample queueSample);

    /**
     * 入库样本总数
     * @param consignmentId
     * @param userOrgId
     * @return
     */
    int selectBsckUpByCaseId(@Param("consignmentId")String consignmentId, @Param("userOrgId")String userOrgId);

    /**
     * 入库样本总数队列16
     * @param caseId
     * @return
     */
    int selectNewBsckUpCount(@Param("caseId")String caseId, @Param("userOrgId")String userOrgId);

    /**
     * 入库样本成功数
     * @param consignmentId
     * @param userOrgId
     * @return
     */
    int selectBackSuccess(@Param("consignmentId")String consignmentId, @Param("userOrgId")String userOrgId);

    /**
     * 入库样本成功数
     * @param caseId
     * @return
     */
    int selectNewBackSuccess(@Param("caseId")String caseId, @Param("userOrgId")String userOrgId);

    /**
     * 入库样本失败数
     * @param consignmentId
     * @param userOrgId
     * @return
     */
    int selectBackFailCount(@Param("consignmentId")String consignmentId, @Param("userOrgId")String userOrgId);

    /**
     * 入库样本失败数
     * @param caseId
     * @param userOrgId
     * @return
     */
    int selectNewBackFailCount(@Param("caseId")String caseId, @Param("userOrgId")String userOrgId);

    /**
     * 查询入库操作插入队列类型为15检材数据
     * @param consignmentId
     * @return
     */
    List<QueueSampleVo> selectByConsignmentId(String consignmentId);

    /**
     * 查询入库操作插入队列类型为16检材数据
     * @param sampleNo
     * @return
     */
    List<QueueSampleVo> selectBySampleNo(String sampleNo);

    List<QueueSample> selectByCaseId(String caseId);

    List<QueueSample> selectAccepttanceQueue(QueueSample queueSample);

    List<QueueSample> selectQueueByCaseId(String caseId);

    List<QueueSample> selectQueueBySampleNo(String sampleNo);
}