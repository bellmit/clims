package com.bazl.clims.service.impl;

import com.bazl.clims.dao.QueueSampleMapper;
import com.bazl.clims.model.PageInfo;
import com.bazl.clims.model.po.QueueSample;
import com.bazl.clims.model.vo.LimsCaseInfoVo;
import com.bazl.clims.model.vo.QueueSampleVo;
import com.bazl.clims.service.QueueSampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Sun on 2019/4/9.
 */
@Service
public class QueueSampleServiceImpl extends BaseService implements QueueSampleService {

    @Autowired
    QueueSampleMapper queueSampleMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return queueSampleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(QueueSample record) {
        return queueSampleMapper.insert(record);
    }

    @Override
    public QueueSample selectByPrimaryKey(String id) {
        return queueSampleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<QueueSample> selectAll() {
        return queueSampleMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(QueueSample record) {
        return queueSampleMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<QueueSample> selectBySize(String status, String queueType, int size) {
        return queueSampleMapper.selectBySize(status, queueType, size);
    }

    @Override
    public int updateStatusByPrimaryKey(String status, String queueId) {
        QueueSample record = new QueueSample();
        record.setId(queueId);
        record.setStatus(Integer.valueOf(status));
        return queueSampleMapper.updateStatusByPrimaryKey(record);
    }

    /**
     * 查询入本地库数据
     * @return
     */
    @Override
    public List<QueueSampleVo> selectFindAll(QueueSampleVo query, PageInfo pageInfo) {
        List<QueueSampleVo> queueSampleList = null;
        int pageNo;
        int pageSize;
        try {
            pageNo = pageInfo.getPage();
            pageSize = pageInfo.getEvePageRecordCnt();
            query.setOffset((pageNo - 1) * pageSize);
            query.setRows(query.getOffset() + pageSize);
            queueSampleList = queueSampleMapper.selectFindAll(query);
        } catch(Exception ex) {
            logger.info("查询入本地库数据报错："+ex);
            return null;
        }
        return queueSampleList;
    }


    @Override
    public int selectVOCount(QueueSampleVo query) {
        return queueSampleMapper.selectVOCount(query);
    }

    /**
     * 入库样本总数
     * @param consignmentId
     * @param userOrgId
     * @return
     */
    @Override
    public int selectBsckUpCount(String consignmentId,String userOrgId) {
        return queueSampleMapper.selectBsckUpByCaseId(consignmentId,userOrgId);
    }

    @Override
    public int selectNewBsckUpCount(String caseId,String userOrgId) {
        return queueSampleMapper.selectNewBsckUpCount(caseId, userOrgId);
    }

    /**
     *入库样本成功数
     * @param consignmentId
     * @param userOrgId
     * @return
     */
    @Override
    public int selectBackSuccess(String consignmentId,String userOrgId) {
        return queueSampleMapper.selectBackSuccess(consignmentId,userOrgId);
    }/**
     *入库样本成功数
     * @param caseId
     * @param userOrgId
     * @return
     */
    @Override
    public int selectNewBackSuccess(String caseId,String userOrgId) {
        return queueSampleMapper.selectNewBackSuccess(caseId,userOrgId);
    }

    /**
     * 入库样本失败数
     * @param consignmentId
     * @param userOrgId
     * @return
     */
    @Override
    public int selectBackFailCount(String consignmentId,String userOrgId) {
        return queueSampleMapper.selectBackFailCount(consignmentId,userOrgId);
    }

    /**
     * 入库样本失败数
     * @param caseId
     * @param userOrgId
     * @return
     */
    @Override
    public int selectNewBackFailCount(String caseId,String userOrgId) {
        return queueSampleMapper.selectNewBackFailCount(caseId,userOrgId);
    }

    @Override
    public List<QueueSample> selectByCaseId(String caseId) {
        return queueSampleMapper.selectByCaseId(caseId);
    }

    @Override
    public List<QueueSample> selectAccepttanceQueue(QueueSample queueSample) {
        return queueSampleMapper.selectAccepttanceQueue(queueSample);
    }

    @Override
    public List<QueueSample> selectQueueByCaseId(String caseId) {
        return queueSampleMapper.selectQueueByCaseId(caseId);
    }

    @Override
    public List<QueueSample> selectQueueBySampleNo(String sampleNo) {
        return queueSampleMapper.selectQueueBySampleNo(sampleNo);
    }


    /**
     * 查询入国家库数据
     * @param query
     * @param pageInfo
     * @return
     */
//    @Override
//    public List<LimsCaseInfoVo> selectFindstate(LimsCaseInfoVo query, PageInfo pageInfo) {
//        List<LimsCaseInfoVo> queuestateList = null;
//
//        int pageNo;
//        int pageSize;
//        try {
//            pageNo = pageInfo.getPage();
//            pageSize = pageInfo.getEvePageRecordCnt();
//            query.setOffset((pageNo - 1) * pageSize);
//            query.setRows(query.getOffset() + pageSize);
//            queuestateList = queueSampleMapper.selectFindstate(query);
//        } catch(Exception ex) {
//            logger.info("查询入国家库数据报错："+ex);
//            System.out.println("查询入国家库数据报错：");
//            return null;
//        }
//        return queuestateList;
//    }

//    @Override
//    public int selectStateCount(LimsCaseInfoVo query) {
//        return queueSampleMapper.selectStateCount(query);
//    }


}
