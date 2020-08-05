package com.bazl.clims.service;

import com.bazl.clims.model.PageInfo;
import com.bazl.clims.model.po.QueueSample;
import com.bazl.clims.model.vo.LimsCaseInfoVo;
import com.bazl.clims.model.vo.QueueSampleVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Sun on 2019/4/9.
 */
@Repository
public interface QueueSampleService {
    int deleteByPrimaryKey(String id);

    int insert(QueueSample record);

    QueueSample selectByPrimaryKey(String id);

    List<QueueSample> selectAll();

    int updateByPrimaryKey(QueueSample record);

    List<QueueSample> selectBySize(String status, String queueType, int size);

    int updateStatusByPrimaryKey(String status, String queueId);

    /**
     * 查询入本地库数据
     * @return
     */
    List<QueueSampleVo> selectFindAll(QueueSampleVo query, PageInfo pageInfo);


    /**
     * 查询入本地库数量
     * @param query
     * @return
     */
    int selectVOCount(QueueSampleVo query);

    /**
     * 入库样本总数队列15
     * @param consignmentId
     * @return
     */
    int selectBsckUpCount(String consignmentId,String userOrgId);

    /**
     * 入库样本总数队列16
     * @param caseId
     * @return
     */
    int selectNewBsckUpCount(String caseId,String userOrgId);

    /**
     * 入库样本成功数
     * @param consignmentId
     * @return
     */
    int selectBackSuccess(String consignmentId,String userOrgId);

    /**
     * 入库样本成功数
     * @param caseId
     * @return
     */
    int selectNewBackSuccess(String caseId,String userOrgId);

    /**
     * 入库失败样本总数
     * @param consignmentId
     * @return
     */
    int selectBackFailCount(String consignmentId,String userOrgId);

    /**
     * 入库失败样本总数
     * @param caseId
     * @return
     */
    int selectNewBackFailCount(String caseId,String userOrgId);

    List<QueueSample> selectByCaseId(String caseId);

//    /**
//     * 查询入国家库数据
//     * @param query
//     * @param pageInfo
//     * @return
//     */
//    List<LimsCaseInfoVo> selectFindstate(LimsCaseInfoVo query, PageInfo pageInfo);


    /**
     * 查询案件受理队列是否已存在
     * @param queueSample
     * @return
     */
    List<QueueSample> selectAccepttanceQueue(QueueSample queueSample);


    /**
     * 查询案件是否插入队列15
     * @param caseId
     * @return
     */
    List<QueueSample> selectQueueByCaseId(String caseId);

    /**
     * 查询案件是否插入队列16
     * @param sampleNo
     * @return
     */
    List<QueueSample> selectQueueBySampleNo(String sampleNo);
}
