package com.bazl.clims.task;



import com.bazl.clims.common.FeedBackXckyConstants;
import com.bazl.clims.model.po.*;
import com.bazl.clims.model.vo.LimsCaseInfoVo;
import com.bazl.clims.service.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by hejia on 2019/4/15.
 */

@Component
public class ScheduledTaskQueue {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    MatchAuditedGeneService matchAuditedGeneService;

    @Autowired
    LimsCaseInfoService limsCaseInfoService;

    @Autowired
    LimsSampleInfoDnaService limsSampleInfoDnaService;

    @Autowired
    QueueSampleService queueSampleService;

    @Autowired
    QueueDetailService queueDetailService;


    /**
     * 每间隔2小时输出时间
     */
//    @Scheduled(fixedRate = 7200 * 1000)
    public void logTime() {

        //已委托案件
        List<LimsCaseInfoVo> limsCaseInfoListEntrust = limsCaseInfoService.selectCaseEntrust();
        if(limsCaseInfoListEntrust.size() > 0){
            for(int i=0; i < limsCaseInfoListEntrust.size();i++){
                //查询案件委托队列是否已存在   插入队列20
                QueueSample queueSample = new QueueSample();
                queueSample.setForeignId(limsCaseInfoListEntrust.get(i).getEntity().getCaseId());
                queueSample.setStatus(0);
                queueSample.setQueueType(FeedBackXckyConstants.QUEUE_TYPE_CONSIGNMENT_COMMISSIONED);
                List<QueueSample> QueueSampleList =queueSampleService.selectAccepttanceQueue(queueSample);
                if(QueueSampleList.size() == 0){
                    QueueSample queueSampleEntrust = new QueueSample();
                    String id = UUID.randomUUID().toString();
                    queueSampleEntrust.setId(id);
                    queueSampleEntrust.setForeignId(limsCaseInfoListEntrust.get(i).getEntity().getCaseId());
                    queueSampleEntrust.setCreateDatetime(new Date());// new Date()为获取当前系统时间
                    queueSampleEntrust.setQueueType(FeedBackXckyConstants.QUEUE_TYPE_CONSIGNMENT_COMMISSIONED);//委托时队列类型20
                    queueSampleEntrust.setStatus(0);
                    queueSampleEntrust.setServerNo(limsCaseInfoListEntrust.get(i).getUserOrdId());
                    if(StringUtils.isNotBlank(limsCaseInfoListEntrust.get(i).getEntity().getCaseXkNo())){
                        try {
                            queueSampleService.insert(queueSampleEntrust);
                        } catch (Exception ex) {
                            logger.info("委托案件成功插入队列类型20失败：" + ex);
                        }
                    }
                }
            }
        }

        //已受理
        List<LimsCaseInfoVo> limsCaseInfoList = limsCaseInfoService.selectCaseAll();
        if(limsCaseInfoList.size() > 0){
            for(int i=0; i<limsCaseInfoList.size();i++ ){

                //查询案件委托时是否已插入队列
                QueueSample queueSampleEntrust = new QueueSample();
                queueSampleEntrust.setForeignId(limsCaseInfoList.get(i).getEntity().getCaseId());
                queueSampleEntrust.setStatus(0);
                queueSampleEntrust.setQueueType("20");
                List<QueueSample> QueueSampleListEntrust =queueSampleService.selectAccepttanceQueue(queueSampleEntrust);
                if(QueueSampleListEntrust.size() == 0){
                    QueueSample queueSampleIsEntrust = new QueueSample();
                    String id = UUID.randomUUID().toString();
                    queueSampleIsEntrust.setId(id);
                    queueSampleIsEntrust.setForeignId(limsCaseInfoList.get(i).getEntity().getCaseId());
                    queueSampleIsEntrust.setCreateDatetime(new Date());// new Date()为获取当前系统时间
                    queueSampleIsEntrust.setQueueType(FeedBackXckyConstants.QUEUE_TYPE_CONSIGNMENT_COMMISSIONED);//委托时队列类型20
                    queueSampleIsEntrust.setStatus(0);
                    queueSampleIsEntrust.setServerNo(limsCaseInfoList.get(i).getUserOrdId());
                    if(StringUtils.isNotBlank(limsCaseInfoList.get(i).getEntity().getCaseXkNo())){
                        try {
                            queueSampleService.insert(queueSampleIsEntrust);
                        } catch (Exception ex) {
                            logger.info("受理案件补充委托队列类型20失败：" + ex);
                        }
                    }
                }


                //查询案件受理队列是否已存在   插入队列21
                QueueSample queueSample = new QueueSample();
                queueSample.setForeignId(limsCaseInfoList.get(i).getEntity().getCaseId());
                queueSample.setStatus(0);
                queueSample.setQueueType(FeedBackXckyConstants.QUEUE_TYPE_CONSIGNMENT_ACCEPTED);
                List<QueueSample> QueueSampleList =queueSampleService.selectAccepttanceQueue(queueSample);
                if(QueueSampleList.size() == 0){
                    QueueSample queueSample2 = new QueueSample();
                    String id = UUID.randomUUID().toString();
                    queueSample2.setId(id);
                    queueSample2.setForeignId(limsCaseInfoList.get(i).getEntity().getCaseId());
                    queueSample2.setCreateDatetime(new Date());// new Date()为获取当前系统时间
                    queueSample2.setQueueType(FeedBackXckyConstants.QUEUE_TYPE_CONSIGNMENT_ACCEPTED);//受理时队列类型FeedBackXckyConstants
                    queueSample2.setStatus(0);
                    queueSample2.setServerNo(limsCaseInfoList.get(i).getUserOrdId());

                    if(StringUtils.isNotBlank(limsCaseInfoList.get(i).getEntity().getCaseXkNo())){
                        try {
                            queueSampleService.insert(queueSample2);
                        } catch (Exception ex) {
                            logger.info("受理案件插入队列类型21失败：" + ex);
                        }

                    }
                }
                //查询案件受理队列是否已存在   插入队列22
                QueueSample queueSampleWz = new QueueSample();
                queueSampleWz.setForeignId(limsCaseInfoList.get(i).getEntity().getCaseId());
                queueSampleWz.setStatus(0);
                queueSampleWz.setQueueType(FeedBackXckyConstants.QUEUE_TYPE_EVIDENCE_ACCEPTED);
                List<QueueSample> QueueSampleListWz =queueSampleService.selectAccepttanceQueue(queueSampleWz);
                if(QueueSampleListWz.size() == 0){//队列不存在
                    QueueSample queueSample3 = new QueueSample();
                    String id = UUID.randomUUID().toString();
                    queueSample3.setId(id);
                    queueSample3.setForeignId(limsCaseInfoList.get(i).getEntity().getCaseId());
                    queueSample3.setCreateDatetime(new Date());// new Date()为获取当前系统时间
                    queueSample3.setQueueType(FeedBackXckyConstants.QUEUE_TYPE_EVIDENCE_ACCEPTED);//受理时队列类型FeedBackXckyConstants
                    queueSample3.setStatus(0);
                    queueSample3.setServerNo(limsCaseInfoList.get(i).getUserOrdId());

                    if(StringUtils.isNotBlank(limsCaseInfoList.get(i).getEntity().getCaseXkNo())){
                        try {
                            queueSampleService.insert(queueSample3);
                        } catch (Exception ex) {
                            logger.info("受理案件插入队列类型22失败：" + ex);
                        }

                    }
                    List<LimsSampleInfoDna> limsSampleInfoDnaList = limsSampleInfoDnaService.selectByCaseId(limsCaseInfoList.get(i).getEntity().getCaseId());
                    if(limsSampleInfoDnaList.size() > 0){
                        for(LimsSampleInfoDna LimsSampleInfoDnaEntity : limsSampleInfoDnaList){
                            if(StringUtils.isNotBlank(LimsSampleInfoDnaEntity.getEvidenceNo())){
                                QueueDetail queueDetailAcc = new QueueDetail();
                                String queueDetailId = UUID.randomUUID().toString();//主键
                                queueDetailAcc.setId(queueDetailId);
                                if (queueSample3 != null) {
                                    queueDetailAcc.setQueueId(queueSample3.getId());//队列主表外键
                                }
                                queueDetailAcc.setSampleId(LimsSampleInfoDnaEntity.getSampleId());//样本Id
                                queueDetailAcc.setSampleNo(LimsSampleInfoDnaEntity.getEvidenceNo());//物证样本W号
                                queueDetailAcc.setConsignmentId(LimsSampleInfoDnaEntity.getCaseId());//案件ID
                                queueDetailAcc.setCreateDatetime(new Date());//创建时间
                                try {
                                    queueDetailService.insert(queueDetailAcc);
                                } catch (Exception ex) {
                                    logger.info("插入队列22写入字表物证信息失败：" + ex);
                                }
                            }
                        }
                    }
                }

                //案件检出基因型
                //根据案件id查询物证检材
                List<LimsSampleInfoDna> LimsSampleInfoDnaList2 =  limsSampleInfoDnaService.selectMatchCaseId(limsCaseInfoList.get(i).getEntity().getCaseId());
                if(LimsSampleInfoDnaList2.size()>0){
                    for(LimsSampleInfoDna LimsSampleDnaEntity : LimsSampleInfoDnaList2){
                        if(LimsSampleDnaEntity != null){
                            if(StringUtils.isNotBlank(LimsSampleDnaEntity.getEvidenceNo()) && StringUtils.isNotBlank(LimsSampleDnaEntity.getSampleId())){
                                //根据检材id 查询检出基因型，如果有检出，就插入队列
                                List<MatchAuditedGene> matchAuditedGene = matchAuditedGeneService.selectBySampleId(LimsSampleDnaEntity.getSampleId());
                                if(matchAuditedGene.size() >0){
                                    QueueSample queueSampleQuey = new QueueSample();
                                    queueSampleQuey.setForeignId(limsCaseInfoList.get(i).getEntity().getCaseId());
                                    queueSampleQuey.setStatus(0);
                                    queueSampleQuey.setQueueType(FeedBackXckyConstants.QUEUE_TYPE_EVIDENCE_GENE);
                                    //查询案件队列是否已存在
                                    List<QueueSample> QueueSampleMatchList =queueSampleService.selectAccepttanceQueue(queueSampleQuey);
                                    if(QueueSampleMatchList.size() == 0){
                                        QueueSample queueSampleMatch = new QueueSample();
                                        String id = UUID.randomUUID().toString();
                                        queueSampleMatch.setId(id);
                                        queueSampleMatch.setForeignId(limsCaseInfoList.get(i).getEntity().getCaseId());
                                        queueSampleMatch.setCreateDatetime(new Date());// new Date()为获取当前系统时间
                                        queueSampleMatch.setQueueType(FeedBackXckyConstants.QUEUE_TYPE_EVIDENCE_GENE);//坚持基因型时队列类型24
                                        queueSampleMatch.setStatus(0);
                                        queueSampleMatch.setServerNo(limsCaseInfoList.get(i).getUserOrdId());

                                        try {
                                            queueSampleService.insert(queueSampleMatch);
                                        } catch (Exception ex) {
                                            logger.info("检验样本检出基因型插入队列类型24失败：" + ex);
                                        }
                                        QueueDetail queueDetailMatch = new QueueDetail();
                                        String queueDetailId = UUID.randomUUID().toString();//主键
                                        queueDetailMatch.setId(queueDetailId);
                                        if (queueSampleMatch != null) {
                                            queueDetailMatch.setQueueId(queueSampleMatch.getId());//队列主表外键
                                        }
                                        queueDetailMatch.setSampleId(LimsSampleDnaEntity.getSampleId());//样本Id
                                        queueDetailMatch.setSampleNo(LimsSampleDnaEntity.getEvidenceNo());//物证样本W号
                                        queueDetailMatch.setConsignmentId(LimsSampleDnaEntity.getCaseId());//案件ID
                                        queueDetailMatch.setCreateDatetime(new Date());//创建时间
                                        try {
                                            queueDetailService.insert(queueDetailMatch);
                                        } catch (Exception ex) {
                                            logger.info("插入队列24写入字表物证信息失败：" + ex);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }



        //案件已检出基因型的检材
//        List<LimsCaseInfoVo> limsCaseInfoListMatch = limsCaseInfoService.selectCaseMatch();
//        if(limsCaseInfoListMatch.size() >0){
//            for(int i=0;i < limsCaseInfoListMatch.size();i++ ){
//                //根据案件id查询物证检材
//                List<LimsSampleInfoDna> LimsSampleInfoDnaList2 =  limsSampleInfoDnaService.selectMatchCaseId(limsCaseInfoListMatch.get(i).getEntity().getCaseId());
//                if(LimsSampleInfoDnaList2.size()>0){
//                    for(LimsSampleInfoDna LimsSampleDnaEntity : LimsSampleInfoDnaList2){
//                        if(LimsSampleDnaEntity != null){
//                            if(StringUtils.isNotBlank(LimsSampleDnaEntity.getEvidenceNo()) && StringUtils.isNotBlank(LimsSampleDnaEntity.getSampleId())){
//                                //根据检材id 查询检出基因型，如果有检出，就插入队列
//                                List<MatchAuditedGene> matchAuditedGene = matchAuditedGeneService.selectBySampleId(LimsSampleDnaEntity.getSampleId());
//                                if(matchAuditedGene.size() >0){
//                                    QueueSample queueSampleQuey = new QueueSample();
//                                    queueSampleQuey.setForeignId(limsCaseInfoListMatch.get(i).getEntity().getCaseId());
//                                    queueSampleQuey.setStatus(0);
//                                    queueSampleQuey.setQueueType(FeedBackXckyConstants.QUEUE_TYPE_EVIDENCE_GENE);
//                                    //查询案件队列是否已存在
//                                    List<QueueSample> QueueSampleMatchList =queueSampleService.selectAccepttanceQueue(queueSampleQuey);
//                                    if(QueueSampleMatchList.size() == 0){
//                                        QueueSample queueSampleMatch = new QueueSample();
//                                        String id = UUID.randomUUID().toString();
//                                        queueSampleMatch.setId(id);
//                                        queueSampleMatch.setForeignId(limsCaseInfoListMatch.get(i).getEntity().getCaseId());
//                                        queueSampleMatch.setCreateDatetime(new Date());// new Date()为获取当前系统时间
//                                        queueSampleMatch.setQueueType(FeedBackXckyConstants.QUEUE_TYPE_EVIDENCE_GENE);//坚持基因型时队列类型24
//                                        queueSampleMatch.setStatus(0);
//                                        queueSampleMatch.setServerNo(limsCaseInfoListMatch.get(i).getUserOrdId());
//
//                                        try {
//                                            queueSampleService.insert(queueSampleMatch);
//                                        } catch (Exception ex) {
//                                            logger.info("检验样本检出基因型插入队列类型24失败：" + ex);
//                                        }
//                                        QueueDetail queueDetailMatch = new QueueDetail();
//                                        String queueDetailId = UUID.randomUUID().toString();//主键
//                                        queueDetailMatch.setId(queueDetailId);
//                                        if (queueSampleMatch != null) {
//                                            queueDetailMatch.setQueueId(queueSampleMatch.getId());//队列主表外键
//                                        }
//                                        queueDetailMatch.setSampleId(LimsSampleDnaEntity.getSampleId());//样本Id
//                                        queueDetailMatch.setSampleNo(LimsSampleDnaEntity.getEvidenceNo());//物证样本W号
//                                        queueDetailMatch.setConsignmentId(LimsSampleDnaEntity.getCaseId());//案件ID
//                                        queueDetailMatch.setCreateDatetime(new Date());//创建时间
//                                        try {
//                                            queueDetailService.insert(queueDetailMatch);
//                                        } catch (Exception ex) {
//                                            logger.info("插入队列24写入字表物证信息失败：" + ex);
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }


    }
}
