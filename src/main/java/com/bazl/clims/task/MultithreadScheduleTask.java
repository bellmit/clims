package com.bazl.clims.task;

import com.bazl.clims.common.Constants;
import com.bazl.clims.common.FeedBackXckyConstants;
import com.bazl.clims.compare.GeneRelativeCompareUtil;
import com.bazl.clims.compare.GeneSameCompareUtil;
import com.bazl.clims.compare.relative.po.ParentageMatchResult;
import com.bazl.clims.controller.center.MobileNewsController;
import com.bazl.clims.dao.LimsConsignmentInfoMapper;
import com.bazl.clims.dao.QueueDetailMapper;
import com.bazl.clims.dao.QueueSampleMapper;
import com.bazl.clims.model.po.*;
import com.bazl.clims.service.*;
import com.bazl.clims.utils.ListUtils;
import com.bazl.clims.utils.UuidUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by Sun on 2019/4/9.
 */
//@Component注解用于对那些比较中立的类进行注释；
@Component
//@EnableAsync        // 2.开启多线程
public class MultithreadScheduleTask {
    /**
     * 日志对象
     */
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    LimsCaseInfoService limsCaseInfoService;
    @Autowired
    QueueSampleService queueSampleService;
    @Autowired
    MatchAuditedGeneService matchAuditedGeneService;
    @Autowired
    LimsSampleInfoDnaService sampleInfoDnaService;
    @Autowired
    MatchSameResultService matchSameResultService;
    @Autowired
    GeneSameCompareUtil geneSameCompareUtil;
    @Autowired
    GeneRelativeCompareUtil geneRelativeCompareUtil;
    @Autowired
    MatchRelativeLibService matchRelativeLibService;
    @Autowired
    MatchRelativeResultService matchRelativeResultService;
    @Autowired
    QueueDetailMapper queueDetailMapper;
    @Autowired
    QueueSampleMapper queueSampleMapper;
    @Autowired
    AmPersonalInfoService amPersonalInfoService;
    @Autowired
    LimsConsignmentInfoMapper limsConsignmentInfoMapper;
    @Autowired
    MobileNewsController mobileNewsController;


    @Value("${minSameCount}")
    private int minSameCount;
    @Value("${localComparTaskActived}")
    private boolean localComparTaskActived;

    /**
     * 本地库比对，同一比对task
     *
     * @author sunweiqiang
     * @date 2019/4/9
     */
    //@Async
//    @Scheduled(fixedDelay = 60000)  //间隔1分钟
    public void first() throws InterruptedException {
        if(!localComparTaskActived){
            logger.info("*********本地库同一比对定时任务未开启！**********" + System.currentTimeMillis());
            return;
        }else {
            List<QueueSample> queueSamples = queueSampleService.selectBySize(Constants.QUEUE_STATUS_0, Constants.QUEUE_TYPE_1, 10);
            if (ListUtils.isNotNullAndEmptyList(queueSamples)) {
                logger.error("#####本地库同一比对定时任务开始 : " + LocalDateTime.now().toLocalTime());
                for (QueueSample queueSample : queueSamples) {
                    List<LimsSampleInfoDna> sampleInfoDnas = sampleInfoDnaService.selectBySampleNoAlreadyStorage(queueSample.getForeignId());
                    LimsSampleInfoDna sampleInfoA = ListUtils.isNullOrEmptyList(sampleInfoDnas) ? null : sampleInfoDnas.get(0);
                    if (sampleInfoA == null) {
                        continue;
                    }
                    List<MatchAuditedGene> genes = matchAuditedGeneService.selectBySampleId(sampleInfoA.getSampleId());
                    LimsCaseInfo caseInfoA = limsCaseInfoService.selectByCaseId(sampleInfoA.getCaseId());
                    for (MatchAuditedGene gene : genes) {
                        if (gene != null && StringUtils.isNotBlank(gene.getGeneInfo())) {
                            List<MatchSameResult> resultOldsa = matchSameResultService.selectByOneSampleId(sampleInfoA.getSampleId());
                            //存储样本的比中结果
                            List<Map<String, Object>> list = new ArrayList<>();
                            List<MatchSameResult> resultOlda = matchSameResultService.selectByOneSampleId(sampleInfoA.getSampleId());
                            String groupId = ListUtils.isNullOrEmptyList(resultOlda) ? null : (resultOlda.get(0) == null ? null : resultOlda.get(0).getGroupId());

                            int num = 0;
                            boolean flag = true;
                            while (flag) {
                                List<MatchAuditedGene> geneList = matchAuditedGeneService.selectGeneByPage(num, 10000, null);
                                num++;
                                if (ListUtils.isNullOrEmptyList(geneList)) {
                                    flag = false;
                                    continue;
                                }
                                for (MatchAuditedGene auditedGene : geneList) {
                                    //比对时间排除自身
                                    if (!auditedGene.getSampleId().equals(gene.getSampleId())) {
                                        //判断是否是入库检材
                                        if (!isStored(auditedGene.getSampleId())) {
                                            continue;
                                        }
                                        //进行同一比对
                                        Map<String, Object> result = geneSameCompareUtil.compare(gene.getGeneInfo(), auditedGene.getGeneInfo(), minSameCount);

                                        if (result == null) {
                                            continue;
                                        }

                                        if ((Boolean) result.get("matched")) {
                                            LimsSampleInfoDna sampleInfoB = sampleInfoDnaService.selectSampleInfoDnaById(auditedGene.getSampleId());
                                            LimsCaseInfo caseInfoB = limsCaseInfoService.selectByCaseId(sampleInfoB.getCaseId());
//                                      insertMatchSameResult(sampleInfoA, sampleInfoB, caseInfoA, caseInfoB, result);
                                            //先将比中结果保存到list，方便groupid计算
                                            //并且取得groupid
                                            Map<String, Object> map = new HashMap<>();
                                            map.put("sampleInfoA", sampleInfoA);
                                            map.put("sampleInfoB", sampleInfoB);
                                            map.put("caseInfoA", caseInfoA);
                                            map.put("caseInfoB", caseInfoB);
                                            map.put("result", result);
                                            if (sampleInfoA.getSampleFlag().equals("0") && sampleInfoB.getSampleFlag().equals("0")) {
                                                map.put("matchType", Constants.SAME_MATCH_WW);
                                                //现场物证检材比中其他样本时，写入QUEUE_TYPE='26', STATUS='0'的队列数据，
                                                // 同时子表QUEUE_DETAIL写入比中的物证检材数据，标记该现场案件中物证有比中；
                                                insertQueue(sampleInfoA);
                                                insertQueue(sampleInfoB);
                                                logger.error("现场物证检材比中其他样本时，写入队列" + sampleInfoA.getSampleNo() + sampleInfoB.getSampleNo());

                                            } else if (sampleInfoA.getSampleFlag().equals("1") && sampleInfoB.getSampleFlag().equals("1")) {
                                                map.put("matchType", Constants.SAME_MATCH_RR);
                                            } else {
                                                map.put("matchType", Constants.SAME_MATCH_RW);
                                                //现场物证检材比中其他样本时，写入QUEUE_TYPE='26', STATUS='0'的队列数据，
                                                // 同时子表QUEUE_DETAIL写入比中的物证检材数据，标记该现场案件中物证有比中；
                                                insertQueue(sampleInfoA);
                                                insertQueue(sampleInfoB);
                                                logger.error("现场物证检材比中其他样本时，写入队列" + sampleInfoA.getSampleNo() + sampleInfoB.getSampleNo());
                                            }

                                            list.add(map);
                                            if (groupId == null) {
                                                List<MatchSameResult> resultOldb = matchSameResultService.selectByOneSampleId(sampleInfoB.getSampleId());
                                                groupId = ListUtils.isNullOrEmptyList(resultOldb) ? null : (resultOldb.get(0) == null ? null : resultOldb.get(0).getGroupId());
                                            }
                                        }
                                    }
                                }
                            }
                            //本案比中结果保存
                            if (ListUtils.isNotNullAndEmptyList(list)) {
                                for (Map<String, Object> map : list) {
                                    insertMatchSameResult((LimsSampleInfoDna) map.get("sampleInfoA"), (LimsSampleInfoDna) map.get("sampleInfoB"),
                                            (LimsCaseInfo) map.get("caseInfoA"), (LimsCaseInfo) map.get("caseInfoB"), (Map<String, Object>) map.get("result"), groupId, (String) map.get("matchType"));
                                }
                            }
                        }
                    }
                    //将队列状态置为已处理状态，将状态置为1，参与亲缘比对
                    queueSampleService.updateStatusByPrimaryKey(Constants.QUEUE_STATUS_1, queueSample.getId());
                }
                logger.error("#####本地库同一比对定时任务结束 : " + LocalDateTime.now().toLocalTime());
            }
        }
    }

    private void insertQueue(LimsSampleInfoDna sampleInfo) {
        QueueSample queueSampleThan = new QueueSample();
        String qsId = UUID.randomUUID().toString();
        queueSampleThan.setId(qsId);
        queueSampleThan.setForeignId(sampleInfo.getCaseId());
        queueSampleThan.setCreateDatetime(new Date());// new Date()为获取当前系统时间
        queueSampleThan.setQueueType(FeedBackXckyConstants.QUEUE_TYPE_EVIDENCE_MATCHED);//物证比中26
        queueSampleThan.setStatus(0);
        if (sampleInfo != null && StringUtils.isNotEmpty(sampleInfo.getEvidenceNo())) {
            LimsConsignmentInfo limsConsignmentInfos = limsConsignmentInfoMapper.selectByConsignmentId(sampleInfo.getConsignmentId());
            if (limsConsignmentInfos != null) {
                queueSampleThan.setServerNo(limsConsignmentInfos.getAcceptOrgId());
            }
            queueSampleMapper.insertQueueSample(queueSampleThan);
            QueueDetail queueDetail = new QueueDetail();
            String queueDetailId = UUID.randomUUID().toString();
            queueDetail.setId(queueDetailId);
            if (queueSampleThan != null) {
                queueDetail.setQueueId(queueSampleThan.getId());
            }
            queueDetail.setSampleId(sampleInfo.getSampleId());
            queueDetail.setSampleNo(sampleInfo.getSampleNo());
            queueDetail.setConsignmentId(sampleInfo.getCaseId());
            queueDetail.setCreateDatetime(new Date());
            queueDetailMapper.insert(queueDetail);
        }
    }

    /**
     * 保存同一比对结果
     *
     * @param sampleInfoA
     * @param sampleInfoB
     * @param caseInfoA
     * @param caseInfoB
     * @param result
     */
    private void insertMatchSameResult(LimsSampleInfoDna sampleInfoA, LimsSampleInfoDna sampleInfoB, LimsCaseInfo caseInfoA, LimsCaseInfo caseInfoB, Map<String, Object> result, String groupId, String matchType) {
        if (sampleInfoA != null && sampleInfoB != null && result != null) {
            List<MatchSameResult> matchSameResultList = matchSameResultService.selectBySampleId(sampleInfoA.getSampleId(), sampleInfoB.getSampleId());
            //获取当前登录人信息
          /*  Subject subject = SecurityUtils.getSubject();
            LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
            if (operateUser == null) {
                result.put("success", false);
                result.put("", "/login.html?timeoutFlag=1");
            }

            //查询当前人员信息
            AmPersonalInfo amPersonalInfo = amPersonalInfoService.queryAmPersonalInfo(operateUser.getPersonalId());*/

            if (ListUtils.isNullOrEmptyList(matchSameResultList)) {
                if (groupId == null) {
                    groupId = UuidUtil.getOrderIdByUUId();
                }
                MatchSameResult match = new MatchSameResult();
                match.setId(UUID.randomUUID().toString());
                match.setMatchType(matchType);
                match.setGroupId(groupId);
                match.setSampleaId(sampleInfoA.getSampleId());
                match.setSamplebId(sampleInfoB.getSampleId());
                match.setSampleaNo(sampleInfoA.getSampleNo());
                match.setSamplebNo(sampleInfoB.getSampleNo());   //检材编号
                match.setSampleaName(sampleInfoA.getSampleName());
                match.setSamplebName(sampleInfoB.getSampleName());
                match.setCaseaId(caseInfoA.getCaseId());
                match.setCasebId(caseInfoB.getCaseId());  //案件
                match.setCaseaName(caseInfoA.getCaseName());
                match.setCasebName(caseInfoB.getCaseName());
                match.setSameCount(result.get("sameCount") == null ? 0 : (Integer) result.get("sameCount"));
                match.setDiffCount(result.get("diffCount") == null ? 0 : (Integer) result.get("diffCount"));
                match.setProbability((Double) result.get("probability"));
                match.setMatchString(result.get("geneLRStr") == null ? "" : (String) result.get("geneLRStr"));
                match.setCreateDatetime(new Date());
                match.setCompareStatus(Constants.COMPARE_STATUS_0);
                /*if (amPersonalInfo != null) {
                    match.setCreatePerson(amPersonalInfo.getFullName());
                }*/
                match.setDeleteFlag(Constants.DELETE_FLAG_0);
                matchSameResultService.insert(match);

                //消息通知
                LimsConsignmentInfo caseidByAppendflag = limsConsignmentInfoMapper.getCaseidByAppendflag(caseInfoA.getCaseId());
                String count = "同一比对 案件编号"+caseInfoA.getCaseNo()+" 检材"+sampleInfoA.getSampleNo()+" 已比中 "+caseInfoB.getCaseNo()+" 检材"+sampleInfoB.getSampleNo();
                mobileNewsController.insetNews(caseidByAppendflag.getAcceptorId(),"2",count,sampleInfoA.getSampleNo());
            } else {
                for (MatchSameResult match : matchSameResultList) {
                    match.setSameCount(result.get("sameCount") == null ? 0 : (Integer) result.get("sameCount"));
                    match.setDiffCount(result.get("diffCount") == null ? 0 : (Integer) result.get("diffCount"));
                    match.setProbability((Double) result.get("probability"));
                    match.setMatchString(result.get("geneLRStr") == null ? "" : (String) result.get("geneLRStr"));
                    match.setCreateDatetime(new Date());
                    /*if (amPersonalInfo != null) {
                        match.setCreatePerson(amPersonalInfo.getFullName());
                    }*/
                    if (StringUtils.isBlank(match.getCompareStatus())) {
                        match.setCompareStatus(Constants.COMPARE_STATUS_0);
                    }
                    match.setDeleteFlag(Constants.DELETE_FLAG_0);
                    matchSameResultService.updateByPrimaryKey(match);
                }
            }

        }
    }

    /**
     * 本地库比对，亲缘比对task
     *
     * @author sunweiqiang
     * @date 2019/4/9
     */
/*    @Async
    @Scheduled(fixedDelay = 60000)*/
    public void second() {
        List<QueueSample> queueSamples = queueSampleService.selectBySize(Constants.QUEUE_STATUS_1, Constants.QUEUE_TYPE_1, 10);
        if (ListUtils.isNotNullAndEmptyList(queueSamples)) {
            logger.error("#####本地库亲缘比对定时任务开始 : " + LocalDateTime.now().toLocalTime());
            for (QueueSample queueSample : queueSamples) {
                List<LimsSampleInfoDna> sampleInfoDnas = sampleInfoDnaService.selectBySampleNoAlreadyStorage(queueSample.getForeignId());
                LimsSampleInfoDna sampleInfoA = ListUtils.isNullOrEmptyList(sampleInfoDnas) ? null : sampleInfoDnas.get(0);
                if (sampleInfoA == null) {
                    continue;
                }
                List<MatchAuditedGene> geneAs = matchAuditedGeneService.selectBySampleId(sampleInfoA.getSampleId());
                LimsCaseInfo caseInfoA = limsCaseInfoService.selectByCaseId(sampleInfoA.getCaseId());
                //获取亲缘样本
                MatchRelativeLib lib = matchRelativeLibService.selectBySampleaId(sampleInfoA.getSampleId());

                MatchAuditedGene fatherGene = null;
                MatchAuditedGene motherGene = null;
                MatchAuditedGene childGene = null;
                String findFather = null;
                String findMother = null;
                String findChild = null;

                for (MatchAuditedGene geneA : geneAs) {
                    //判断入库时是否为亲缘入库，如果不是亲缘入库与以前亲缘入库列表进行比对
                    if (lib != null) {
                        String samplebId = lib.getSamplebId();
                        LimsSampleInfoDna sampleInfoB = sampleInfoDnaService.selectSampleInfoDnaById(samplebId);
                        if (sampleInfoB == null) {
                            continue;
                        }
                        List<MatchAuditedGene> geneBs = matchAuditedGeneService.selectBySampleId(sampleInfoB.getSampleId());
                        if (geneBs == null) {
                            continue;
                        }
                        for (MatchAuditedGene geneB : geneBs) {
                            //确定比对时关系
                            //亲缘关系为父亲（01）、丈夫（03）
                            if (StringUtils.isBlank(lib.getSampleaRole()) || StringUtils.isBlank(lib.getSamplebRole())) {
                                continue;
                            }
                            if (lib.getSampleaRole().equals("01") || lib.getSampleaRole().equals("03")) {
                                fatherGene = geneA;
                                if (lib.getSamplebRole().equals("05") || lib.getSamplebRole().equals("06")) {
                                    findMother = "findMother";
                                    childGene = geneB;
                                }
                                if (lib.getSamplebRole().equals("02") || lib.getSamplebRole().equals("04")) {
                                    motherGene = geneB;
                                    findChild = "findChild";
                                }
                            }
                            //亲缘关系为母亲（02）、妻子（04）
                            if (lib.getSampleaRole().equals("02") || lib.getSampleaRole().equals("04")) {
                                motherGene = geneA;
                                if (lib.getSamplebRole().equals("05") || lib.getSamplebRole().equals("06")) {
                                    findFather = "findFather";
                                    childGene = geneB;
                                }
                                if (lib.getSamplebRole().equals("01") || lib.getSamplebRole().equals("03")) {
                                    fatherGene = geneB;
                                    findChild = "findChild";
                                }
                            }
                            //亲缘关系为儿子（05）、女儿（06）
                            if (lib.getSampleaRole().equals("05") || lib.getSampleaRole().equals("06")) {
                                childGene = geneA;
                                if (geneB.getGeneInfo().contains("[\"X\",\"Y\"]")) {
                                    fatherGene = geneB;
                                    findMother = "findMother";
                                } else {
                                    findFather = "findFather";
                                    motherGene = geneB;
                                }
                            }

                            int num = 0;
                            boolean flag = true;
                            while (flag) {
                                List<MatchAuditedGene> geneList = matchAuditedGeneService.selectGeneByPage(num, 10000, Constants.SAMPLE_FLAG_1);
                                num++;
                                if (ListUtils.isNullOrEmptyList(geneList)) {
                                    flag = false;
                                    continue;
                                }
                                for (MatchAuditedGene auditedGene : geneList) {
                                    //判断是否是入库检材
                                    if (!isStored(auditedGene.getSampleId())) {
                                        continue;
                                    }
                                    if (!lib.getSampleaId().equals(auditedGene.getSampleId()) && !lib.getSamplebId().equals(auditedGene.getSampleId())) {
                                        if (StringUtils.isNotBlank(findFather) && findFather.equals("findFather")) {
                                            fatherGene = auditedGene;
                                        }
                                        if (StringUtils.isNotBlank(findMother) && findMother.equals("findMother")) {
                                            motherGene = auditedGene;
                                        }
                                        if (StringUtils.isNotBlank(findChild) && findChild.equals("findChild")) {
                                            childGene = auditedGene;
                                        }

                                        if (fatherGene != null && motherGene != null && childGene !=null) {
                                            //进行亲缘比对
                                            Map<String, Object> result = geneRelativeCompareUtil.compare(fatherGene.getGeneInfo(), motherGene.getGeneInfo(), childGene.getGeneInfo());
                                            if (result != null) {
                                                if ((Boolean) result.get("matched")) {
                                                    LimsSampleInfoDna sampleInfoC = sampleInfoDnaService.selectSampleInfoDnaById(auditedGene.getSampleId());
                                                    LimsCaseInfo caseInfoB = limsCaseInfoService.selectByCaseId(sampleInfoC.getCaseId());
                                                    insertMatchRelativeResult(sampleInfoA, sampleInfoB, sampleInfoC, caseInfoA, caseInfoB, result);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        int num = 0;
                        boolean flag = true;
                        while (flag) {
                            List<MatchRelativeLib> libList = matchRelativeLibService.selectLibByPage(num, 10000);
                            num++;
                            if (ListUtils.isNullOrEmptyList(libList)) {
                                flag = false;
                                continue;
                            }
                            for (MatchRelativeLib relativeLib : libList) {
                                if (!sampleInfoA.getSampleId().equals(relativeLib.getSampleaId()) && !sampleInfoA.getSampleId().equals(relativeLib.getSamplebId())) {
                                    String sampleaId = relativeLib.getSampleaId();
                                    String samplebId = relativeLib.getSamplebId();
                                    LimsSampleInfoDna sampleInfoB = sampleInfoDnaService.selectSampleInfoDnaById(sampleaId);
                                    List<MatchAuditedGene> geneBs = matchAuditedGeneService.selectBySampleId(sampleInfoB.getSampleId());

                                    LimsSampleInfoDna sampleInfoC = sampleInfoDnaService.selectSampleInfoDnaById(samplebId);
                                    List<MatchAuditedGene> geneCs = matchAuditedGeneService.selectBySampleId(sampleInfoB.getSampleId());
                                    if (ListUtils.isNullOrEmptyList(geneBs) && ListUtils.isNullOrEmptyList(geneCs)) {
                                        break;
                                    }
                                    for (MatchAuditedGene geneB : geneBs) {
                                        for (MatchAuditedGene geneC : geneCs) {
                                            //确定比对时关系
                                            //亲缘关系为父亲（01）、丈夫（03）
                                            if (StringUtils.isBlank(relativeLib.getSampleaRole()) || StringUtils.isBlank(relativeLib.getSamplebRole())) {
                                                continue;
                                            }
                                            if (relativeLib.getSampleaRole().equals("01") || relativeLib.getSampleaRole().equals("03")) {
                                                fatherGene = geneB;
                                                if (relativeLib.getSamplebRole().equals("05") || relativeLib.getSamplebRole().equals("06")) {
                                                    motherGene = geneA;
                                                    childGene = geneC;
                                                }
                                                if (relativeLib.getSamplebRole().equals("02") || relativeLib.getSamplebRole().equals("04")) {
                                                    motherGene = geneC;
                                                    childGene = geneA;
                                                }
                                            }
                                            //亲缘关系为母亲（02）、妻子（04）
                                            if (relativeLib.getSampleaRole().equals("02") || relativeLib.getSampleaRole().equals("04")) {
                                                motherGene = geneB;
                                                if (relativeLib.getSamplebRole().equals("05") || relativeLib.getSamplebRole().equals("06")) {
                                                    fatherGene = geneA;
                                                    childGene = geneC;
                                                }
                                                if (relativeLib.getSamplebRole().equals("01") || relativeLib.getSamplebRole().equals("03")) {
                                                    fatherGene = geneC;
                                                    childGene = geneA;
                                                }
                                            }
                                            //亲缘关系为儿子（05）、女儿（06）
                                            if (relativeLib.getSampleaRole().equals("05") || relativeLib.getSampleaRole().equals("06")) {
                                                childGene = geneB;
                                                if (geneC.getGeneInfo().contains("[\"X\",\"Y\"]")) {
                                                    fatherGene = geneC;
                                                    motherGene = geneA;
                                                } else {
                                                    fatherGene = geneA;
                                                    motherGene = geneC;
                                                }
                                            }
                                            if (fatherGene != null && motherGene != null && childGene != null) {
                                                //进行亲缘比对
                                                Map<String, Object> result = geneRelativeCompareUtil.compare(fatherGene.getGeneInfo(), motherGene.getGeneInfo(), childGene.getGeneInfo());
                                                if (result != null) {
                                                    if ((Boolean) result.get("matched")) {
                                                        LimsCaseInfo caseInfoB = limsCaseInfoService.selectByCaseId(sampleInfoB.getCaseId());
                                                        insertMatchRelativeResult(sampleInfoB, sampleInfoC, sampleInfoA, caseInfoB, caseInfoA, result);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                //将队列状态置为已处理状态，将状态置为2，参与亲缘比对
                queueSampleService.updateStatusByPrimaryKey(Constants.QUEUE_STATUS_2, queueSample.getId());
            }
            logger.error("#####本地库亲缘比对定时任务结束 : " + LocalDateTime.now().toLocalTime());
        }
    }

    /**
     * 保存亲缘比对结果
     *
     * @param sampleInfoA
     * @param sampleInfoB
     * @param caseInfoA
     * @param caseInfoB
     * @param result
     */
    private void insertMatchRelativeResult(LimsSampleInfoDna sampleInfoA, LimsSampleInfoDna sampleInfoB, LimsSampleInfoDna sampleInfoC, LimsCaseInfo caseInfoA, LimsCaseInfo caseInfoB, Map<String, Object> result) {

        //获取当前登录人信息
     /*   Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if (operateUser == null) {
            result.put("success", false);
            result.put("", "/login.html?timeoutFlag=1");
        }

        //查询当前人员信息
        AmPersonalInfo amPersonalInfo = amPersonalInfoService.queryAmPersonalInfo(operateUser.getPersonalId());*/

        if (sampleInfoA != null && sampleInfoB != null && result != null) {
            List<MatchRelativeResult> matchRelativeResults = matchRelativeResultService.selectBySampleId(sampleInfoA.getSampleId(), sampleInfoB.getSampleId(), sampleInfoC.getSampleId());
            if (ListUtils.isNullOrEmptyList(matchRelativeResults)) {
                MatchRelativeResult match = new MatchRelativeResult();
                match.setId(UUID.randomUUID().toString());
                match.setMatchType("");
                match.setGroupId(UuidUtil.getOrderIdByUUId());
                match.setSampleaId(sampleInfoA.getSampleId());
                match.setSamplebId(sampleInfoB.getSampleId());
                match.setSamplecId(sampleInfoC.getSampleId());
                match.setSampleaNo(sampleInfoA.getSampleNo());
                match.setSamplebNo(sampleInfoB.getSampleNo());
                match.setSamplecNo(sampleInfoC.getSampleNo());
                match.setSampleaName(sampleInfoA.getSampleName());
                match.setSamplebName(sampleInfoB.getSampleName());
                match.setSamplecName(sampleInfoC.getSampleName());
                match.setCaseaId(caseInfoA.getCaseId());
                match.setCasebId(caseInfoB.getCaseId());
                match.setCaseaName(caseInfoA.getCaseName());
                match.setCasebName(caseInfoB.getCaseName());
                match.setSameCount(result.get("sameCount") == null ? 0 : (Integer) result.get("sameCount"));
                match.setDiffCount(result.get("diffCount") == null ? 0 : (Integer) result.get("diffCount"));

                ParentageMatchResult parentageMatchResult = (ParentageMatchResult) result.get("parentageMatchResult");
                if (parentageMatchResult != null) {
                    match.setProbability(parentageMatchResult.getResult().getPi());
                    if (parentageMatchResult.getResult().getPiOfMarker() != null) {
                        ObjectMapper mapper = new ObjectMapper();
                        try {
                            String geneLRStr = mapper.writeValueAsString(parentageMatchResult.getResult().getPiOfMarker());
                            match.setMatchString(geneLRStr);
                        } catch (JsonProcessingException e) {
                            logger.error("转换基因分型比中频率信息错误！", e);
                            e.printStackTrace();
                        }
                    }

                    match.setProbabilityF(parentageMatchResult.getResultOfAf().getPi());
                    if (parentageMatchResult.getResultOfAf().getPiOfMarker() != null) {
                        ObjectMapper mapper = new ObjectMapper();
                        try {
                            String geneLRStr = mapper.writeValueAsString(parentageMatchResult.getResultOfAf().getPiOfMarker());
                            match.setMatchStringF(geneLRStr);
                        } catch (JsonProcessingException e) {
                            logger.error("转换基因分型比中频率信息错误！", e);
                            e.printStackTrace();
                        }
                    }

                    match.setProbabilityM(parentageMatchResult.getResultOfM().getPi());
                    if (parentageMatchResult.getResultOfM().getPiOfMarker() != null) {
                        ObjectMapper mapper = new ObjectMapper();
                        try {
                            String geneLRStr = mapper.writeValueAsString(parentageMatchResult.getResultOfM().getPiOfMarker());
                            match.setMatchStringM(geneLRStr);
                        } catch (JsonProcessingException e) {
                            logger.error("转换基因分型比中频率信息错误！", e);
                            e.printStackTrace();
                        }
                    }
                }

                match.setCompareStatus(Constants.COMPARE_STATUS_0);
                match.setCreateDatetime(new Date());
              /*  if (amPersonalInfo != null) {
                    match.setCreatePerson(amPersonalInfo.getFullName());
                }*/
                match.setDeleteFlag(Constants.DELETE_FLAG_0);
                matchRelativeResultService.insert(match);

                //消息通知
                LimsConsignmentInfo caseidByAppendflag = limsConsignmentInfoMapper.getCaseidByAppendflag(caseInfoA.getCaseId());
                String count = "亲缘比对 案件编号"+caseInfoA.getCaseNo()+" 检材"+sampleInfoA.getSampleNo()+"和 案件编号"+caseInfoB.getCaseNo()+" 检材"+sampleInfoB.getSampleNo()+ " 已比中 "+caseInfoB.getCaseNo()+" 检材"+sampleInfoC.getSampleNo();
                mobileNewsController.insetNews(caseidByAppendflag.getAcceptorId(),"3",count,sampleInfoA.getSampleNo());
            } else {
                for (MatchRelativeResult match : matchRelativeResults) {
                    match.setSameCount(result.get("sameCount") == null ? 0 : (Integer) result.get("sameCount"));
                    match.setDiffCount(result.get("diffCount") == null ? 0 : (Integer) result.get("diffCount"));

                    ParentageMatchResult parentageMatchResult = (ParentageMatchResult) result.get("parentageMatchResult");
                    if (parentageMatchResult != null) {
                        match.setProbability(parentageMatchResult.getResult().getPi());
                        if (parentageMatchResult.getResult().getPiOfMarker() != null) {
                            ObjectMapper mapper = new ObjectMapper();
                            try {
                                String geneLRStr = mapper.writeValueAsString(parentageMatchResult.getResult().getPiOfMarker());
                                match.setMatchString(geneLRStr);
                            } catch (JsonProcessingException e) {
                                logger.error("转换基因分型比中频率信息错误！", e);
                                e.printStackTrace();
                            }
                        }

                        match.setProbabilityF(parentageMatchResult.getResultOfAf().getPi());
                        if (parentageMatchResult.getResultOfAf().getPiOfMarker() != null) {
                            ObjectMapper mapper = new ObjectMapper();
                            try {
                                String geneLRStr = mapper.writeValueAsString(parentageMatchResult.getResultOfAf().getPiOfMarker());
                                match.setMatchStringF(geneLRStr);
                            } catch (JsonProcessingException e) {
                                logger.error("转换基因分型比中频率信息错误！", e);
                                e.printStackTrace();
                            }
                        }

                        match.setProbabilityM(parentageMatchResult.getResultOfM().getPi());
                        if (parentageMatchResult.getResultOfM().getPiOfMarker() != null) {
                            ObjectMapper mapper = new ObjectMapper();
                            try {
                                String geneLRStr = mapper.writeValueAsString(parentageMatchResult.getResultOfM().getPiOfMarker());
                                match.setMatchStringM(geneLRStr);
                            } catch (JsonProcessingException e) {
                                logger.error("转换基因分型比中频率信息错误！", e);
                                e.printStackTrace();
                            }
                        }
                    }

                    match.setCreateDatetime(new Date());
                    /*if (amPersonalInfo != null) {
                        match.setCreatePerson(amPersonalInfo.getFullName());
                    }*/
                    if (StringUtils.isBlank(match.getCompareStatus())) {
                        match.setCompareStatus(Constants.COMPARE_STATUS_0);
                    }
                    match.setDeleteFlag(Constants.DELETE_FLAG_0);
                    matchRelativeResultService.updateByPrimaryKey(match);
                }
            }

        }
    }

    /**
     * 判断检材是否入库
     * @param sampleId
     * @return
     */
    public boolean isStored(String sampleId) {
        return sampleInfoDnaService.isStoredBySampleId(sampleId);
    }

}
