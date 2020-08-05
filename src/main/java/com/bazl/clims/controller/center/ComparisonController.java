package com.bazl.clims.controller.center;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.bazl.clims.common.Constants;
import com.bazl.clims.compare.GeneMixCompareUtil;
import com.bazl.clims.compare.GeneRelativeCompareUtil;
import com.bazl.clims.compare.GeneSameCompareUtil;
import com.bazl.clims.compare.relative.po.ParentageMatchResult;
import com.bazl.clims.compare.relative.po.ParentageMatchResultRecord;
import com.bazl.clims.controller.BaseController;
import com.bazl.clims.dao.BlendRelationMapper;
import com.bazl.clims.datamodel.CaseCompareResultGroup;
import com.bazl.clims.datamodel.CaseCompareResultInfoModel;
import com.bazl.clims.model.DictItem;
import com.bazl.clims.model.LoaUserInfo;
import com.bazl.clims.model.ToConfigure;
import com.bazl.clims.model.po.*;
import com.bazl.clims.model.vo.AmPersonalInfoVo;
import com.bazl.clims.model.vo.MatchSameResultVo;
import com.bazl.clims.service.*;
import com.bazl.clims.utils.CommonUtils;
import com.bazl.clims.utils.DataFormat;
import com.bazl.clims.utils.DictUtil;
import com.bazl.clims.utils.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

import static com.bazl.clims.controller.center.QueryCompareResultController.distinctByKey;

/**
 * @author wanghaiyang
 * @date 2019/4/10.
 */
@Controller
@RequestMapping("/center")
public class ComparisonController extends BaseController {

    @Autowired
    LimsCaseInfoService limsCaseInfoService;
    @Autowired
    LimsSampleInfoDnaService limsSampleInfoDnaService;
    @Autowired
    MatchAuditedGeneService matchAuditedGeneService;
    @Autowired
    LimsSampleGeneService limsSampleGeneService;
    @Autowired
    GeneSameCompareUtil geneSameCompareUtil;
    @Autowired
    GeneMixCompareUtil geneMixCompareUtil;
    @Autowired
    GeneRelativeCompareUtil geneRelativeCompareUtil;
    @Autowired
    RaceService raceService;
    @Autowired
    AmPersonalInfoService amPersonalInfoService;
    @Autowired
    CompareSameResultService compareSameResultService;
    @Autowired
    CompareRelativeResultService compareRelativeResultService;
    @Autowired
    ExaminerService examinerService;
    @Autowired
    PanelService panelService;
    @Autowired
    private ToConfigure bean;
    @Autowired
    BlendRelationMapper blendRelationMapper;
    /**
     * 进入本案比对页面
     * @param request
     * @return
     */
    @RequestMapping("/enterCaseCompare")
    public ModelAndView enterCaseCompare(HttpServletRequest request, String caseId, String consignmentId,
                                         Integer matchLimit, Integer mixMatchLimit,
                                         String acceptDatetime,Integer relativeSameCount,
                                         Integer halfDiffCount, String isAgainMatch,String acceptorId) {
        ModelAndView view = new ModelAndView();

        if (matchLimit == null) {
            matchLimit = 0;
            view = geneSameCompareUtil.getInitData();
        }else {
            view.addObject("minSameCaseCount", matchLimit);
        }
        if (mixMatchLimit == null) {
            mixMatchLimit = 0;
            view = geneSameCompareUtil.getInitData();
        }else {
            view.addObject("minSameMixCount", mixMatchLimit);
        }
        if (relativeSameCount != null) {
            view.addObject("minSameRelationCount", relativeSameCount);
        }
        if (halfDiffCount != null) {
            view.addObject("halfDiffCount", halfDiffCount);
        }



        MatchAuditedGene matchAuditedGene = new MatchAuditedGene();
        matchAuditedGene.setCaseId(caseId);
        matchAuditedGene.setGeneType(Constants.GENE_NORMAL);
        //查询审核过的常染色体基因信息
        List<MatchAuditedGene> matchGeneList = matchAuditedGeneService.selectByMatchAuditedGene(matchAuditedGene);
        //去除基因座个数小于8的分型
        List<MatchAuditedGene> auditedGeneList = new ArrayList<>();
        if (ListUtils.isNotNullAndEmptyList(matchGeneList)) {
            for (MatchAuditedGene mag : matchGeneList) {
                if (StringUtils.isNotBlank(mag.getGeneInfo())) {
                    List<String> markerList = geneSameCompareUtil.getMarker(mag.getGeneInfo());
                    //审核通过的检材且基因座少于8个时，表示未检出
                    //TODO  放到前端由用户配置
                    if(markerList.size() >= 8){
                        auditedGeneList.add(mag);
                    }
                }
            }
        }

        matchAuditedGene.setGeneType(Constants.GENE_MIXED);
        //查询审核过混合的基因信息
        List<MatchAuditedGene> mixMatchGeneList = matchAuditedGeneService.selectByMatchAuditedGene(matchAuditedGene);
        //去除基因座个数小于8的分型
        List<MatchAuditedGene> mixAuditedGeneList = new ArrayList<>();
        if (ListUtils.isNotNullAndEmptyList(mixMatchGeneList)) {
            for (MatchAuditedGene mag : mixMatchGeneList) {
                if (StringUtils.isNotBlank(mag.getGeneInfo())) {
                    List<String> markerList = geneSameCompareUtil.getMarker(mag.getGeneInfo());
                    //审核通过的检材且基因座少于8个时，表示未检出
                    //TODO  放到前端由用户配置
                    if(markerList.size() >= 8){
                        mixAuditedGeneList.add(mag);
                    }
                }
            }
        }

        //受害人基因信息
        List<MatchAuditedGene> victimAuditedGeneList = new ArrayList<>();
        //嫌疑人基因信息
        List<MatchAuditedGene> suspectsAuditedGeneList = new ArrayList<>();
        //其他人员基因信息
        List<MatchAuditedGene> otherAuditedGeneList = new ArrayList<>();
        //同一基因型的物证信息
        List<MatchAuditedGene> evidenceAuditedGeneList = new ArrayList<>();
        if (ListUtils.isNotNullAndEmptyList(auditedGeneList)) {
            for (MatchAuditedGene msg : auditedGeneList) {
                if (Constants.GENE_NORMAL.equals(msg.getGeneType())) {
                    if (Constants.SAMPLE_FLAG_0.equals(msg.getSampleFlag())) {
                        //同一基因型的物证信息
                        evidenceAuditedGeneList.add(msg);
                    }else {
                        if (Constants.PERSON_TYPE_03.equals(msg.getPersonType())) {
                            //受害人基因信息
                            victimAuditedGeneList.add(msg);
                        }else if (Constants.PERSON_TYPE_01.equals(msg.getPersonType())) {
                            //嫌疑人基因信息
                            suspectsAuditedGeneList.add(msg);
                        }else {
                            //其他人员基因信息
                            otherAuditedGeneList.add(msg);
                        }
                    }
                }
            }
        }

        List<CaseCompareResultGroup> matchedGroupList = new ArrayList<>();
        try {
            //受害人与物比中分组
            if (ListUtils.isNotNullAndEmptyList(victimAuditedGeneList)) {
                List<CaseCompareResultGroup> victimGroupList = geneSameCompareUtil.doCompare(victimAuditedGeneList, evidenceAuditedGeneList, Constants.REFERENCE_TYPE_OWNER, matchLimit);
                if (ListUtils.isNotNullAndEmptyList(victimGroupList)) {
                    matchedGroupList.addAll(victimGroupList);
                }
            }
            //嫌疑人与物比中分组
            if (ListUtils.isNotNullAndEmptyList(suspectsAuditedGeneList)) {
                List<CaseCompareResultGroup> suspectsGroupList = geneSameCompareUtil.doCompare(suspectsAuditedGeneList, evidenceAuditedGeneList, Constants.REFERENCE_TYPE_SUSPECTS, matchLimit);
                if (ListUtils.isNotNullAndEmptyList(suspectsGroupList)) {
                    matchedGroupList.addAll(suspectsGroupList);
                }
            }
            //其他人员与物比中分组
            if (ListUtils.isNotNullAndEmptyList(otherAuditedGeneList)) {
                List<CaseCompareResultGroup> otherGroupList = geneSameCompareUtil.doCompare(otherAuditedGeneList, evidenceAuditedGeneList, Constants.REFERENCE_TYPE_OTHER_PEOPLE, matchLimit);
                if (ListUtils.isNotNullAndEmptyList(otherGroupList)) {
                    matchedGroupList.addAll(otherGroupList);
                }
            }
            //物与物比中分组
            if (ListUtils.isNotNullAndEmptyList(evidenceAuditedGeneList)) {
                List<MatchAuditedGene> newAuditedGeneList = new ArrayList<>();
                for (MatchAuditedGene auditedGene : evidenceAuditedGeneList) {
                    boolean isExists = false;
                    if (ListUtils.isNotNullAndEmptyList(matchedGroupList)) {
                        for (int i = 0; i < matchedGroupList.size(); i++) {
                            CaseCompareResultGroup caseCompareResultGroup = matchedGroupList.get(i);
                            if (caseCompareResultGroup != null) {
                                List<MatchAuditedGene> matchAuditedGeneList = caseCompareResultGroup.getMatchedSampleList();
                                for (MatchAuditedGene mag : matchAuditedGeneList) {
                                    if (auditedGene.getSampleId().equals(mag.getSampleId())) {
                                        isExists = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }

                    if (!isExists) {
                        newAuditedGeneList.add(auditedGene);
                    }
                }
                List<CaseCompareResultGroup> evidenceGroupList = geneSameCompareUtil.doCompare(newAuditedGeneList, newAuditedGeneList, Constants.REFERENCE_TYPE_EVIDENCE, matchLimit);
                //判断物物比中分组中跟其他分组是否有相同检材并进行合并
                if (ListUtils.isNotNullAndEmptyList(evidenceGroupList)) {
                    matchedGroupList.addAll(evidenceGroupList);
                }
            }
        }catch (Exception e) {
            logger.error("同型比中分组报错！" + e.getMessage());
            e.getStackTrace();
        }

        //查询未检出检材信息
        Set<LimsSampleInfoDna> notDetectedList = new LinkedHashSet<>();
        List<LimsSampleInfoDna> sampleInfoDnaList = limsSampleInfoDnaService.selectByCaseId(caseId);
        List<LimsSampleGene> limsSampleGeneList = limsSampleGeneService.selectListByCaseId(caseId);
        /*boolean hasResult;
          for(LimsSampleInfoDna sampleInfoDna : sampleInfoDnaList){
            hasResult = false;
            for(LimsSampleGene sampleGene : limsSampleGeneList){
                if(sampleInfoDna.getSampleId().equals(sampleGene.getSampleId()) && StringUtils.isNotBlank(sampleGene.getGeneInfo())){
                    hasResult = true;
                    break;
                }
            }

            if(!hasResult) {
                notDetectedList.add(sampleInfoDna);
            }
        }*/
        //顺义新需求所有的检材都是审核通过的
        LimsSampleInfoDna  sampleInfoDna = new LimsSampleInfoDna();
        for(LimsSampleGene sampleGene : limsSampleGeneList){
            sampleInfoDna = limsSampleInfoDnaService.selectSampleInfoDnaById(sampleGene.getSampleId());
            sampleInfoDna.setAuditedGeneId(sampleGene.getGeneId());
            MatchAuditedGene auditedGene = matchAuditedGeneService.selectByPrimaryKey(sampleGene.getGeneId());
            if (auditedGene != null) {
                //判断此检材是否标记成无效检材,0：有效，1：无效
                if (Constants.FLAG_TRUE.equals(auditedGene.getInvalidStatus())) {
                    notDetectedList.add(sampleInfoDna);
                }else {
                    if (StringUtils.isNotBlank(sampleGene.getGeneInfo())) {
                        List<String> markerList = geneSameCompareUtil.getMarker(sampleGene.getGeneInfo());
                        //审核通过的检材且基因座少于8个时，表示未检出
                        if(markerList.size() < 8){
                            notDetectedList.add(sampleInfoDna);
                        }
                    }else {
                        notDetectedList.add(sampleInfoDna);
                    }
                }
            }
        }

        //混合检材分组
        List<CaseCompareResultGroup> mixGroupList = new ArrayList<>();
        List<CaseCompareResultGroup> newMixGroupList = new ArrayList<>();
        List<CaseCompareResultGroup> newMixGroupList2 = new ArrayList<>();
        List<CaseCompareResultGroup>  newMixGroupList4 = new ArrayList<>();
        try {
            mixGroupList = geneMixCompareUtil.mixDoCompare(mixAuditedGeneList, auditedGeneList, mixMatchLimit);

            List<MatchAuditedGene> matchedSampleList1 = new ArrayList<>();


            for (CaseCompareResultGroup caseCompareResultGroup : matchedGroupList) {
                MatchAuditedGene matchAuditedGene1 = caseCompareResultGroup.getMatchedSampleList().get(0);
                List<MatchAuditedGene> matchedSampleList = caseCompareResultGroup.getMatchedSampleList();

                for (MatchAuditedGene auditedGene : matchedSampleList) {
                    if (!auditedGene.getSampleNo().equals(matchAuditedGene1.getSampleNo())){
                        matchedSampleList1.add(auditedGene);
                    }
                }
            }
//            if (mixGroupList.size() <=1){
//                for (CaseCompareResultGroup caseCompareResultGroup : mixGroupList) {
//                    List<MatchAuditedGene> list = new ArrayList<>();
//                    List<MatchAuditedGene> mixMatchedSampleList = caseCompareResultGroup.getMixMatchedSampleList();
//                    for (MatchAuditedGene auditedGene : mixMatchedSampleList) {
//                        for (MatchAuditedGene auditedGene1 : matchedSampleList1) {
//                            if (!auditedGene.getSampleNo().equals(auditedGene1.getSampleNo())){
//                                list.add(auditedGene);
//                            }
//                        }
//                    }
//                    caseCompareResultGroup.setMixMatchedSampleList(list);
//                    newMixGroupList2.add(caseCompareResultGroup);
//                }
//            }else if (mixGroupList.size() >1 && mixGroupList.size() <=5){
//                for (CaseCompareResultGroup caseCompareResultGroup : mixGroupList) {
//                    List<MatchAuditedGene> list = new ArrayList<>();
//                    List<MatchAuditedGene> mixMatchedSampleList = caseCompareResultGroup.getMixMatchedSampleList();
//                    for (MatchAuditedGene auditedGene : mixMatchedSampleList) {
////                        for (MatchAuditedGene auditedGene1 : matchedSampleList1) {
////                            if (!auditedGene.getSampleNo().equals(auditedGene1.getSampleNo())){
////                                list.add(auditedGene);
////                            }
////                        }
//                        if (!matchedSampleList1.contains(auditedGene)){
//                            list.add(auditedGene);
//                        }
//                    }
//                    List<MatchAuditedGene> collect1 = list.stream().filter(distinctByKey(MatchAuditedGene::getSampleNo)).collect(Collectors.toList());
//                    if (newMixGroupList4.size() == 0){
//                        caseCompareResultGroup.setMixMatchedSampleList(collect1);
//                        newMixGroupList4.add(caseCompareResultGroup);
//                    }
//                    for (CaseCompareResultGroup caseCompareResultGroup2 : newMixGroupList4) {
//                        if (newMixGroupList4.size() != 0 && !caseCompareResultGroup2.getMixMatchedSampleList().contains(collect1.get(0))){
//                            caseCompareResultGroup.setMixMatchedSampleList(collect1);
//                            newMixGroupList4.add(caseCompareResultGroup);
//                        }
//                    }
//
//
//                }
//            }
//            else{

                    for (CaseCompareResultGroup caseCompareResultGroup : mixGroupList) {
                        List<MatchAuditedGene> mixMatchedSampleList = caseCompareResultGroup.getMixMatchedSampleList();
                        List<MatchAuditedGene> mixMatchedSampleList2 = new ArrayList<>();
                        for (MatchAuditedGene auditedGene : mixMatchedSampleList) {
                            if (!matchedSampleList1.contains(auditedGene))
                                mixMatchedSampleList2.add(auditedGene);

                        }
                        List<MatchAuditedGene> collect1 = mixMatchedSampleList2.stream().filter(distinctByKey(MatchAuditedGene::getSampleNo)).collect(Collectors.toList());

                            caseCompareResultGroup.setMixMatchedSampleList(collect1);
                            newMixGroupList2.add(caseCompareResultGroup);


                }
                newMixGroupList2 = geneMixCompareUtil.removeSameGroup2(newMixGroupList2);

                List<CaseCompareResultGroup> newMixGroupList3 = newMixGroupList2;

                for (CaseCompareResultGroup caseCompareResultGroup : newMixGroupList2) {
                    if (caseCompareResultGroup.getMixMatchedSampleList().size() >2){
                        newMixGroupList4.add(caseCompareResultGroup);
                    }
                }

                ArrayList<String> list = new ArrayList<>();

                for (CaseCompareResultGroup caseCompareResultGroup : newMixGroupList4) {
                    list.add(caseCompareResultGroup.getMixMatchedSampleList().get(0).getSampleNo());
                }

                for (CaseCompareResultGroup caseCompareResultGroup2 : newMixGroupList2) {
                    if (!list.contains(caseCompareResultGroup2.getMixMatchedSampleList().get(0).getSampleNo())){
                        newMixGroupList4.add(caseCompareResultGroup2);
                    }
                }
//            }


        }catch (Exception e) {
            logger.error("混合检材分组报错！" + e.getMessage());
            e.getStackTrace();
        }

        List<CaseCompareResultGroup> allGroupList = new ArrayList<>();
        allGroupList.addAll(matchedGroupList);
        allGroupList.addAll(mixGroupList);

        List<MatchAuditedGene> allAuditedGeneList = new ArrayList<>();
        allAuditedGeneList.addAll(auditedGeneList);
        allAuditedGeneList.addAll(mixAuditedGeneList);

        //查询种族信息
        List<Race> raceList = raceService.selectAll();

        //查询同一比中信息
        List<CompareSameResult> compareSameResultList = compareSameResultService.selectListByCaseId(caseId);
        //此案件同一比中信息存在并且没有重新比对时
        if (ListUtils.isNotNullAndEmptyList(matchedGroupList)) {
            for (CaseCompareResultGroup ccrg : matchedGroupList) {
                List<MatchAuditedGene> matchedSampleList = ccrg.getMatchedSampleList();
                for (MatchAuditedGene mag : matchedSampleList) {
                    String referenceId = mag.getReferenceId();
                    if (matchedSampleList.get(0) != null) {
                        if (StringUtils.isEmpty(referenceId)) {
                            referenceId = matchedSampleList.get(0).getSampleNo();
                        }
                    }
                    mag.setReferenceId(referenceId);
                    LimsSampleGene sampleGene = limsSampleGeneService.selectByPrimaryKey(mag.getAuditedGeneId());
                    if (sampleGene != null) {
                        mag.setPanelName(sampleGene.getPanelName());
                    }
                    //此案件同一比中信息存在并且没有重新比对时
                    if (ListUtils.isNotNullAndEmptyList(compareSameResultList) && !Constants.FLAG_TRUE.equals(isAgainMatch)) {
                        for (CompareSameResult csr : compareSameResultList) {
                            if (StringUtils.isNotBlank(csr.getSampleNo()) && csr.getSampleNo().equals(mag.getSampleNo())) {
                                if (csr.getCompareGeneSum() != null) {
                                    mag.setSameCount(csr.getCompareGeneSum().toString());
                                }
                                mag.setTotalProbability(csr.getCompareTotalProbability());
                                mag.setComparePopulationId(csr.getComparePopulationId());
                                mag.setMatchLimit(csr.getMatchLimit());
                                mag.setTolerance(mag.getTolerance());
                            }
                        }
                    }
                }
            }
        }

        //查询亲缘比中信息
        List<CompareRelativeResult> compareRelativeResultList = compareRelativeResultService.selectListByCaseId(caseId);
        //根据编号赋值
        for (CompareRelativeResult crr : compareRelativeResultList) {
            List<MatchAuditedGene> geneList = null;
            for (LimsSampleInfoDna lsid : sampleInfoDnaList) {
                if (StringUtils.isNotBlank(crr.getFatherSampleNo())) {
                    if (crr.getFatherSampleNo().equals(lsid.getSampleNo())) {
                        crr.setFatherSampleName(lsid.getSampleName());
                        crr.setFatherSampleId(lsid.getSampleId());
                        geneList = matchAuditedGeneService.selectBySampleId(lsid.getSampleId());
                        if (ListUtils.isNotNullAndEmptyList(geneList) && geneList.get(0) != null) {
                            crr.setFatherGeneId(geneList.get(0).getAuditedGeneId());
                            LimsSampleGene sampleGene = limsSampleGeneService.selectByPrimaryKey(geneList.get(0).getAuditedGeneId());
                            if (sampleGene != null) {
                                crr.setReagentNameF(sampleGene.getPanelName());
                            }
                        }
                    }
                }

                if (StringUtils.isNotBlank(crr.getMotherSampleNo())) {
                    if (crr.getMotherSampleNo().equals(lsid.getSampleNo())) {
                        crr.setMotherSampleName(lsid.getSampleName());
                        crr.setMotherSampleId(lsid.getSampleId());
                        geneList = matchAuditedGeneService.selectBySampleId(lsid.getSampleId());
                        if (ListUtils.isNotNullAndEmptyList(geneList) && geneList.get(0) != null) {
                            crr.setMotherGeneId(geneList.get(0).getAuditedGeneId());
                            LimsSampleGene sampleGene = limsSampleGeneService.selectByPrimaryKey(geneList.get(0).getAuditedGeneId());
                            if (sampleGene != null) {
                                crr.setReagentNameM(sampleGene.getPanelName());
                            }
                        }
                    }
                }

                if (StringUtils.isNotBlank(crr.getChildSampleNo())) {
                    if (crr.getChildSampleNo().equals(lsid.getSampleNo())) {
                        crr.setChildSampleName(lsid.getSampleName());
                        crr.setChildSampleId(lsid.getSampleId());
                        geneList = matchAuditedGeneService.selectBySampleId(lsid.getSampleId());
                        if (ListUtils.isNotNullAndEmptyList(geneList) && geneList.get(0) != null) {
                            crr.setChildGeneId(geneList.get(0).getAuditedGeneId());
                            LimsSampleGene sampleGene = limsSampleGeneService.selectByPrimaryKey(geneList.get(0).getAuditedGeneId());
                            if (sampleGene != null) {
                                crr.setReagentNameC(sampleGene.getPanelName());
                            }
                        }
                    }
                }
            }

            int matchedMode = Integer.valueOf(StringUtils.isBlank(crr.getMatchMode()) ? "0" : crr.getMatchMode());

            if (matchedMode == Constants.MATCH_MODE_1) {
                crr.setBacgroundF("green");
                crr.setBacgroundM("green");
            }else if (matchedMode == Constants.MATCH_MODE_2) {
                crr.setBacgroundF("green");
                crr.setBacgroundM("red");
            }else if (matchedMode == Constants.MATCH_MODE_3) {
                crr.setBacgroundF("red");
                crr.setBacgroundM("green");
            }else if (matchedMode == Constants.MATCH_MODE_4) {
                crr.setBacgroundF("red");
                crr.setBacgroundM("red");
            }else {
                crr.setBacgroundF("red");
                crr.setBacgroundM("red");
            }
        }

        //无比中检材信息
        Set<MatchAuditedGene> notMatchedGeneList = new LinkedHashSet<>();
        try {
            boolean hasMatched;
            for(MatchAuditedGene msg : allAuditedGeneList){
                hasMatched = false;
                for(CaseCompareResultGroup group : allGroupList){
                    List<MatchAuditedGene> allMatchedSampleList = new ArrayList<>();
                    if (ListUtils.isNotNullAndEmptyList(group.getMatchedSampleList())) {
                        allMatchedSampleList.addAll(group.getMatchedSampleList());
                    }
                    if (ListUtils.isNotNullAndEmptyList(group.getMixMatchedSampleList())) {
                        allMatchedSampleList.addAll(group.getMixMatchedSampleList());
                    }
                    for(MatchAuditedGene matched : allMatchedSampleList){
                        if(matched.getAuditedGeneId().equals(msg.getAuditedGeneId())){
                            hasMatched = true;
                            break;
                        }
                    }
                    if(hasMatched) { break; }
                }


                if(!hasMatched && !isInRelative(msg.getSampleNo(), compareRelativeResultList) && !isInNotDetected(msg.getSampleNo(), notDetectedList)) {
                    notMatchedGeneList.add(msg);
                }
            }
        }catch (Exception e) {
            logger.error("无比中检材分组报错！" + e.getMessage());
            e.getStackTrace();
        }

        //获取当前登录用户
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if(operateUser == null){
            view.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return  view;
        }

        //获取受理人信息
        List<AmPersonalInfoVo> amPersonalInfoVoList = amPersonalInfoService.queryAmPersonalInfoVoList(operateUser.getOrgId());
        view.addObject("amPersonalInfoList",amPersonalInfoVoList);

        //获取签字人信息
        List<Examiner> examinerList = examinerService.selectListByCaseId(caseId);
        Examiner examiner = new Examiner();
        if (ListUtils.isNotNullAndEmptyList(examinerList)) {
            examiner = examinerList.get(0);
        }

        //获取当前案件下的受理人的id
        if("0".equals(bean.getLimsEdition())){
            if (StringUtils.isBlank(examiner.getInspector1())) {
                examiner.setInspector1(defaultInspector(bean.getInspector1(), amPersonalInfoVoList));
                examiner.setTitleOne(bean.getInspector1Post());
            }
            if (StringUtils.isBlank(examiner.getInspector2())) {
                examiner.setInspector2(defaultInspector(bean.getInspector2(), amPersonalInfoVoList));
                examiner.setTitleTwo(bean.getInspector2Post());
            }
            if (StringUtils.isBlank(examiner.getInspector3())) {
                examiner.setInspector3(defaultInspector(bean.getInspector3(), amPersonalInfoVoList));
                examiner.setTitleThree(bean.getInspector3Post());
            }
        }

        //获取技术任职职称
        DictItem dictItem = new DictItem();
        dictItem.setDictTypeCode(Constants.TECHNICAL_TITLES);
        List<DictItem> titlesList = DictUtil.getDictList(dictItem);
        view.addObject("titlesList", titlesList);

        LimsCaseInfo limsCaseInfo = limsCaseInfoService.selectByCaseId(caseId);

        view.addObject("caseId", caseId);
        view.addObject("limsCaseInfo", limsCaseInfo);
        view.addObject("consignmentId", consignmentId);
        view.addObject("notDetectedList", notDetectedList);
        view.addObject("matchedGroupList", matchedGroupList);
        view.addObject("notMatchedGeneList", notMatchedGeneList);
//        view.addObject("mixGroupList", mixGroupList);
        view.addObject("mixGroupList", newMixGroupList4);
        view.addObject("raceList", raceList);
        view.addObject("auditedGeneList", auditedGeneList);
        view.addObject("compareRelativeResultList", compareRelativeResultList);
        view.addObject("relationCount", compareRelativeResultList.size());
        view.addObject("acceptDatetime", acceptDatetime);
        view.addObject("examiner", examiner);
        view.setViewName("caseCompare/enterCaseCompare");
        return view;
    }

    /**
     * 亲缘比对
     * @param request
     * @param relationCompare
     * @return
     */
    @RequestMapping(value = "/realtionCompareTo", method = RequestMethod.POST, produces="application/json; charset=utf-8")
    @ResponseBody
    public List<Map<String, Object>> realtionCompareTo(HttpServletRequest request, @RequestBody RelationCompare relationCompare) {
        List<Map<String, Object>> resultList = new ArrayList<>();

        MatchAuditedGene FsampleGene = null;
        String fatherGeneInfo = null;
        if (StringUtils.isNotBlank(relationCompare.getFatherSampleNo())) {
            List<MatchAuditedGene> fatherGeneList = matchAuditedGeneService.selectListBySampleNo(relationCompare.getFatherSampleNo());
            if (ListUtils.isNotNullAndEmptyList(fatherGeneList)) {
                FsampleGene = fatherGeneList.get(0);
                if (FsampleGene != null) {
                    fatherGeneInfo = FsampleGene.getGeneInfo();
                }
            }
        }

        MatchAuditedGene MsampleGene = null;
        String motherGeneInfo = null;
        if (StringUtils.isNotBlank(relationCompare.getMotherSampleNo())) {
            List<MatchAuditedGene> motherGeneList = matchAuditedGeneService.selectListBySampleNo(relationCompare.getMotherSampleNo());
            if (ListUtils.isNotNullAndEmptyList(motherGeneList)) {
                MsampleGene = motherGeneList.get(0);
                if (MsampleGene != null) {
                    motherGeneInfo = MsampleGene.getGeneInfo();
                }
            }
        }

        MatchAuditedGene ZsampleGene = null;
        String childGeneInfo = null;
        String[] zBarcodeArr = relationCompare.getChildSampleNoStr().substring(0, relationCompare.getChildSampleNoStr().length() - 1).split(",");
        for (int i = 0; i < zBarcodeArr.length; i ++) {
            List<MatchAuditedGene> childrenGeneList = matchAuditedGeneService.selectListBySampleNo(zBarcodeArr[i].trim());
            if (ListUtils.isNotNullAndEmptyList(childrenGeneList)) {
                ZsampleGene = childrenGeneList.get(0);
                if (ZsampleGene != null) {
                    childGeneInfo = ZsampleGene.getGeneInfo();
                }
            }

            CompareRelativeResult compareRelativeResult = new CompareRelativeResult();
            compareRelativeResult.setMatchLimit(relationCompare.getMatchRelationLimit());
            compareRelativeResult.setTolerance(relationCompare.getHalfDiffCount());
            resultList.add(geneRelativeCompareUtil.relationCompare(fatherGeneInfo, motherGeneInfo, childGeneInfo, compareRelativeResult));
        }

        return resultList;
    }

    /**
     * 更新基因分型为无效检材
     * @param request
     * @param matchAuditedGeneList
     * @return
     */
    @RequestMapping(value="/updateInvalidStatus", method = RequestMethod.POST, produces="application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> updateInvalidStatus(HttpServletRequest request, @RequestBody List<MatchAuditedGene> matchAuditedGeneList) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            if (ListUtils.isNotNullAndEmptyList(matchAuditedGeneList)) {
                for (MatchAuditedGene mag : matchAuditedGeneList) {
                    MatchAuditedGene auditedGene = matchAuditedGeneService.selectByPrimaryKey(mag.getAuditedGeneId());
                    auditedGene.setInvalidStatus(mag.getInvalidStatus());
                    matchAuditedGeneService.updateByPrimaryKey(auditedGene);
                }
            }
            result.put("success", true);
        }catch (Exception e) {
            result.put("success", false);
            logger.error("更新基因分型为无效检材报错！" + e.getMessage());
            e.getStackTrace();
        }

        return result;
    }

    /**
     * 保存比对结果
     * @param request
     * @param caseCompareResultInfoModel
     * @return
     */
    @RequestMapping(value="/saveComparisonResult", method = RequestMethod.POST, produces="application/json; charset=utf-8")
    @ResponseBody
    public Map<String,Object> saveComparisonResult(HttpServletRequest request, @RequestBody CaseCompareResultInfoModel caseCompareResultInfoModel){
        Map<String, Object> result = new HashMap<String, Object>();

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if (operateUser == null) {
            result.put("success", false);
            result.put("", "/login.html?timeoutFlag=1");
            return result;
        }

        //查询当前人员信息
        AmPersonalInfo amPersonalInfo = amPersonalInfoService.queryAmPersonalInfo(operateUser.getPersonalId());

        boolean sameFlag = false;
        try {
            sameFlag = saveSameCompareResult(caseCompareResultInfoModel, amPersonalInfo);
            result.put("sameFlag", sameFlag);
        }catch (Exception e) {
            result.put("sameFlag", false);
            logger.error("保存同一比对结果报错！" + e.getMessage());
            e.getStackTrace();
        }


        boolean relationFlag = false;
        try {
            relationFlag = saveRelationCompareResult(caseCompareResultInfoModel, amPersonalInfo);
            result.put("relationFlag", relationFlag);
        }catch (Exception e) {
            result.put("relationFlag", false);
            logger.error("保存亲缘比对结果报错！" + e.getMessage());
            e.getStackTrace();
        }

        return result;
    }

    /**
     * 保存同一比对结果
     * @param caseCompareResultInfoModel
     * @return
     */
    public boolean saveSameCompareResult(CaseCompareResultInfoModel caseCompareResultInfoModel, AmPersonalInfo amPersonalInfo) {
        boolean flag = true;

        try {
            List<CompareSameResult> compareSameResultList = caseCompareResultInfoModel.getCompareSameResultList();
            if (caseCompareResultInfoModel != null && ListUtils.isNotNullAndEmptyList(compareSameResultList)) {
                LimsConsignmentInfo limsConsignmentInfo = caseCompareResultInfoModel.getLimsConsignmentInfo();

                //根据条件删除之前的结果信息
                if (limsConsignmentInfo != null) {
                    compareSameResultService.deleteByCaseId(limsConsignmentInfo.getCaseId());
                }

                CompareSameResult compareSameResult = compareSameResultList.get(0);
                String referenceId = null;
                if (compareSameResult != null) {
                    referenceId = compareSameResult.getReferenceId();
                    if (StringUtils.isEmpty(referenceId)) {
                        referenceId = compareSameResult.getSampleNo();
                    }
                }

                for (CompareSameResult csr : compareSameResultList) {
                    csr.setId(UUID.randomUUID().toString());
                    if (amPersonalInfo != null) {
                        csr.setCreatePerson(amPersonalInfo.getFullName());
                    }
                    csr.setCreateDatetime(new Date());
                    if (StringUtils.isBlank(csr.getSampleNo())) {
                        csr.setSampleNo(referenceId);
                    }
                    if (StringUtils.isBlank(csr.getReferenceId())) {
                        csr.setReferenceId(referenceId);
                    }

                    //保存比对结果信息
                    compareSameResultService.insert(csr);
                }
            }
        }catch (Exception e) {
            logger.error("保存同一比对结果报错！" + e.getMessage());
            flag = false;
            e.getStackTrace();
        }

        return flag;
    }

    /**
     * 保存亲缘比对结果
     * @param caseCompareResultInfoModel
     * @param amPersonalInfo
     * @return
     */
    public boolean saveRelationCompareResult(CaseCompareResultInfoModel caseCompareResultInfoModel, AmPersonalInfo amPersonalInfo) {
        boolean flag = true;

        try {
            List<CompareRelativeResult> compareRelativeResultList = caseCompareResultInfoModel.getCompareRelativeResultList();
            if (caseCompareResultInfoModel != null && ListUtils.isNotNullAndEmptyList(compareRelativeResultList)) {

                LimsConsignmentInfo limsConsignmentInfo = caseCompareResultInfoModel.getLimsConsignmentInfo();

                //根据条件删除之前的结果信息
                if (limsConsignmentInfo != null) {
                    compareRelativeResultService.deleteByCaseId(limsConsignmentInfo.getCaseId());
                }
                for (CompareRelativeResult crr : compareRelativeResultList) {
                    if (StringUtils.isNotBlank(crr.getCaseId())) {
                        crr.setId(UUID.randomUUID().toString());
                        if (amPersonalInfo != null) {
                            crr.setCreatePerson(amPersonalInfo.getFullName());
                        }
                        crr.setCreateDatetime(new Date());

                        //保存比对结果信息
                        compareRelativeResultService.insert(crr);
                    }
                }
            }
        }catch (Exception e) {
            logger.error("保存亲缘比对结果报错！" + e.getMessage());
            flag = false;
            e.getStackTrace();
        }

        return flag;
    }

    /**
     * 查看同一比对结果
     * @param request
     * @param compareSameResult
     * @return
     */
    @RequestMapping(value="/viewCompareResult")
    @ResponseBody
    public ModelAndView viewCompareResult(HttpServletRequest request, CompareSameResult compareSameResult) {
        Map<String, Object> result = new HashMap<>();
        ModelAndView modelAndView = new ModelAndView();
        try {
            if (compareSameResult != null) {
                String referenceId = compareSameResult.getReferenceId();
                String sampleNo = compareSameResult.getSampleNo();
                List<MatchAuditedGene> referenceAuditedGeneList = matchAuditedGeneService.selectListBySampleNo(referenceId);
                List<MatchAuditedGene> auditedGeneList = matchAuditedGeneService.selectListBySampleNo(sampleNo);

                if (ListUtils.isNotNullAndEmptyList(referenceAuditedGeneList) && ListUtils.isNotNullAndEmptyList(auditedGeneList)) {
                    MatchAuditedGene referenceAuditedGene = referenceAuditedGeneList.get(0);
                    MatchAuditedGene auditedGene = auditedGeneList.get(0);

                    String geneInfos1 = referenceAuditedGene.getGeneInfo();
                    if (StringUtils.isNotBlank(referenceAuditedGene.getPanelName()) && CommonUtils.contain(referenceAuditedGene.getPanelName(), "identifiler")) {
                        geneInfos1 = geneSameCompareUtil.getGeneInfoList(Constants.IdentifilerPlusList, geneInfos1);
                    }
                    String geneInfos2 = auditedGene.getGeneInfo();
                    if (StringUtils.isNotBlank(auditedGene.getPanelName()) && CommonUtils.contain(auditedGene.getPanelName(), "identifiler")) {
                        geneInfos2 = geneSameCompareUtil.getGeneInfoList(Constants.IdentifilerPlusList, geneInfos2);
                    }
                    Map<String, List<String>> referenceGeneInfo = (Map) JSON.parse(geneInfos1,Feature.OrderedField);
                    Map<String, List<String>> geneInfo = (Map) JSON.parse(geneInfos2,Feature.OrderedField);
                    if (referenceGeneInfo != null && geneInfo != null) {
                        if (compareSameResult.getMatchLimit() == null) {
                            compareSameResult.setMatchLimit(0);
                        }
                        result = geneSameCompareUtil.compareResult(referenceGeneInfo, geneInfo, compareSameResult.getMatchLimit());
                    }

                    Double fProb = (Double)result.get("probability");
                    String str = DataFormat.formatDecimal(fProb == null?0.0:fProb, 3, 1, true);
                    modelAndView.addObject("mapList", result);
                    modelAndView.addObject("probability", str);
                    modelAndView.addObject("referenceAuditedGene", referenceAuditedGene);
                    modelAndView.addObject("auditedGene", auditedGene);
                    modelAndView.addObject("success", true);
                    modelAndView.setViewName("caseCompare/fromComparison");
                }else {
                    modelAndView.addObject("success", false);
                }
            }else {
                modelAndView.addObject("success", false);
            }
        }catch (Exception e) {
            logger.error("查看同一比对结果报错！" + e.getMessage());
            modelAndView.addObject("success", false);
            e.getStackTrace();
        }

        return modelAndView;
    }

    /**
     * 查看亲缘比对结果
     * @param request
     * @param compareRelativeResult
     * @return
     */
    @RequestMapping(value="/viewRelationCompareResult")
    public ModelAndView viewRelationCompareResult(HttpServletRequest request, CompareRelativeResult compareRelativeResult) {
        ModelAndView modelAndView = new ModelAndView();

        try{
            MatchAuditedGene FsampleGene = null;
            String fatherGeneInfo = null;
            if (StringUtils.isNotBlank(compareRelativeResult.getFatherSampleNo())) {
                List<MatchAuditedGene> fatherGeneList = matchAuditedGeneService.selectListBySampleNo(compareRelativeResult.getFatherSampleNo());
                if (ListUtils.isNotNullAndEmptyList(fatherGeneList)) {
                    FsampleGene = fatherGeneList.get(0);
                    if (FsampleGene != null) {
                        fatherGeneInfo = FsampleGene.getGeneInfo();
                        modelAndView.addObject("fatherSampleNo", FsampleGene.getSampleNo());
                    }
                }
            }

            MatchAuditedGene MsampleGene = null;
            String motherGeneInfo = null;
            if (StringUtils.isNotBlank(compareRelativeResult.getMotherSampleNo())) {
                List<MatchAuditedGene> motherGeneList = matchAuditedGeneService.selectListBySampleNo(compareRelativeResult.getMotherSampleNo());
                if (ListUtils.isNotNullAndEmptyList(motherGeneList)) {
                    MsampleGene = motherGeneList.get(0);
                    if (MsampleGene != null) {
                        motherGeneInfo = MsampleGene.getGeneInfo();
                        modelAndView.addObject("motherSampleNo", MsampleGene.getSampleNo());
                    }
                }
            }

            MatchAuditedGene ZsampleGene = null;
            String childGeneInfo = null;
            if (StringUtils.isNotBlank(compareRelativeResult.getChildSampleNo())) {
                List<MatchAuditedGene> childGeneList = matchAuditedGeneService.selectListBySampleNo(compareRelativeResult.getChildSampleNo());
                if (ListUtils.isNotNullAndEmptyList(childGeneList)) {
                    ZsampleGene = childGeneList.get(0);
                    if (ZsampleGene != null) {
                        childGeneInfo = ZsampleGene.getGeneInfo();
                        modelAndView.addObject("childSampleNo", ZsampleGene.getSampleNo());
                    }
                }
            }

            List<Map<String, Object>> resultList = new ArrayList<>();
            resultList.add(geneRelativeCompareUtil.relationCompare(fatherGeneInfo, motherGeneInfo, childGeneInfo, compareRelativeResult));

            Map<String, List<String>> fatherResult = null;
            try {
//                fatherResult =(Map) JSON.parse(fatherGeneInfo);

                String geneInfos1 = fatherGeneInfo;
                if (StringUtils.isNotBlank(FsampleGene.getPanelName()) && CommonUtils.contain(FsampleGene.getPanelName(), "identifiler")) {
                    geneInfos1 = geneSameCompareUtil.getGeneInfoList(Constants.IdentifilerPlusList, geneInfos1);
                }
                fatherResult = (Map) JSON.parse(geneInfos1, Feature.OrderedField);
                System.out.println(fatherResult);
            } catch (Exception ex) {
                logger.error("解析父亲基因分型信息错误！", ex);
            }

            Map<String, List<String>> motherResult = null;
            try {
//                motherResult =(Map) JSON.parse(motherGeneInfo);
                String geneInfos2 = motherGeneInfo;
                if (StringUtils.isNotBlank(MsampleGene.getPanelName()) && CommonUtils.contain(MsampleGene.getPanelName(), "identifiler")) {
                    geneInfos2 = geneSameCompareUtil.getGeneInfoList(Constants.IdentifilerPlusList, geneInfos2);
                }
                motherResult = (Map) JSON.parse(geneInfos2, Feature.OrderedField);
                System.out.println(motherResult);


            } catch (Exception ex) {
                logger.error("解析母亲基因分型信息错误！", ex);
            }

            Map<String, List<String>> childResult = null;
            try {
//                childResult =(Map) JSON.parse(childGeneInfo);

                String geneInfos3 = childGeneInfo;
                if (StringUtils.isNotBlank(ZsampleGene.getPanelName()) && CommonUtils.contain(ZsampleGene.getPanelName(), "identifiler")) {
                    geneInfos3 = geneSameCompareUtil.getGeneInfoList(Constants.IdentifilerPlusList, geneInfos3);
                }
                childResult = (Map) JSON.parse(geneInfos3, Feature.OrderedField);
                System.out.println(childResult);
            } catch (Exception ex) {
                logger.error("解析孩子基因分型信息错误！", ex);
            }

            List<Map<String, Object>> groupList = new ArrayList<>();
            if (ListUtils.isNotNullAndEmptyList(resultList)) {
                for (Map<String,Object> result : resultList) {
                    ParentageMatchResult parentageMatchResult = (ParentageMatchResult)result.get("parentageMatchResult");

                    if (parentageMatchResult != null) {
                        ParentageMatchResultRecord totalResult = parentageMatchResult.getResult();
                        ParentageMatchResultRecord afResult = parentageMatchResult.getResultOfAf();
                        ParentageMatchResultRecord mResult = parentageMatchResult.getResultOfM();

                        for (Map.Entry<String, List<String>> entry : childResult.entrySet()) {
                            Map<String, Object> map = new LinkedHashMap<>();
                            String markerName = entry.getKey();
                            List<String> cAlleleList = entry.getValue();
                            map.put("markerName", markerName);
                            String cGene =  geneRelativeCompareUtil.getAllele(cAlleleList);

                            map.put("cGene", cGene);
                            Map<String, Double> cMarker = totalResult.getPiOfMarker();
                            String cPiStr = "";
                            if (cMarker.containsKey(markerName)) {
                                Double piDouble = cMarker.get(markerName);
                                if (!Double.isNaN(piDouble)) {
                                    cPiStr = DataFormat.formatDecimal(piDouble == 0.0?0.0:piDouble, 3, 1, true);
                                }
                            }
                            map.put("cPiStr", cPiStr);
                            modelAndView.addObject("cMatchCount", totalResult.getMatchCount());
                            Double totalPi = totalResult.getPi();
                            String totalPiStr = "";
                            if (!Double.isNaN(totalPi)) {
                                totalPiStr =  DataFormat.formatDecimal(totalPi == 0.0?0.0:totalPi, 3, 1, true);
                            }
                            modelAndView.addObject("totalPiStr", totalPiStr);

                            String fGene = "";
                            String fPiStr = "";
                            if (fatherResult != null) {
                                if (fatherResult.containsKey(markerName)) {
                                    List<String> fAlleleList = fatherResult.get(markerName);
                                    if (ListUtils.isNotNullAndEmptyList(fAlleleList)) {
                                        fGene = geneRelativeCompareUtil.getAllele(fAlleleList);

                                    }
                                    Map<String, Double> fMarker = afResult.getPiOfMarker();
                                    if (fMarker.containsKey(markerName)) {
                                        Double piDouble = fMarker.get(markerName);
                                        if (!Double.isNaN(piDouble)) {
                                            fPiStr = DataFormat.formatDecimal(piDouble == 0.0 ? 0.0 : piDouble, 3, 1, true);
                                        }
                                    }
                                }
                            }
                            map.put("fGene", fGene);
                            map.put("fPiStr", fPiStr);
                            modelAndView.addObject("fMatchCount", afResult.getMatchCount());
                            Double fTotalPi = afResult.getPi();
                            String fTotalPiStr = "";
                            if (!Double.isNaN(fTotalPi)  && Math.abs(fTotalPi - 1.0d ) > 0d) {
                                fTotalPiStr =  DataFormat.formatDecimal(fTotalPi == 0.0?0.0:fTotalPi, 3, 1, true);
                            }
                            modelAndView.addObject("fTotalPiStr", fTotalPiStr);

                            String mGene = "";
                            String mPiStr = "";
                            if (motherResult != null) {
                                if (motherResult.containsKey(markerName)) {
                                    List<String> mAlleleList = motherResult.get(markerName);
                                    if (ListUtils.isNotNullAndEmptyList(mAlleleList)) {
                                        mGene = geneRelativeCompareUtil.getAllele(mAlleleList);
                                    }
                                    Map<String, Double> mMarker = mResult.getPiOfMarker();
                                    if (mMarker.containsKey(markerName)) {
                                        Double piDouble = mMarker.get(markerName);
                                        if (!Double.isNaN(piDouble)) {
                                            mPiStr = DataFormat.formatDecimal(piDouble == 0.0?0.0:piDouble, 3, 1, true);
                                        }
                                    }
                                }
                            }

                            map.put("mPiStr", mPiStr);
                            map.put("mGene", mGene);
                            modelAndView.addObject("mMatchCount", mResult.getMatchCount());
                            Double mTotalPi = mResult.getPi();
                            String mTotalPiStr = "";
                            if (!Double.isNaN(mTotalPi) && Math.abs(mTotalPi - 1.0d ) > 0d) {
                                mTotalPiStr =  DataFormat.formatDecimal(mTotalPi == 0.0?0.0:mTotalPi, 3, 1, true);
                            }
                            modelAndView.addObject("mTotalPiStr", mTotalPiStr);

                            boolean isMatch = true;
                            if (ListUtils.isNotNullAndEmptyList(cAlleleList) && cAlleleList.size() == 2) {
                                //判断父、母亲父基因位点与孩子其中一位位点是否比中
                                String allele1 = cAlleleList.get(0).toString();
                                String allele2 = cAlleleList.get(1).toString();
                                if (StringUtils.isNotBlank(fGene) && StringUtils.isNotBlank(mGene)) {
                                    if (fGene.contains(allele1) && mGene.contains(allele2)) {
                                        isMatch = true;
                                    }else if (fGene.contains(allele2) && mGene.contains(allele1)) {
                                        isMatch = true;
                                    }else {
                                        isMatch = false;
                                    }
                                }else if (StringUtils.isNotBlank(fGene) && StringUtils.isBlank(mGene)) {
                                    if (!fGene.contains(allele1) && !fGene.contains(allele2) ) {
                                        isMatch = false;
                                    }
                                }else if (StringUtils.isBlank(fGene) && StringUtils.isNotBlank(mGene)) {
                                    if (!mGene.contains(allele1) && !mGene.contains(allele2)) {
                                        isMatch = false;
                                    }
                                }
                            }

                            if (!isMatch) {
                                map.put("type", "abnormal");
                            }

                            groupList.add(map);
                        }
                    }
                }
            }

            LimsCaseInfo limsCaseInfo = limsCaseInfoService.selectByCaseId(compareRelativeResult.getCaseId());

            modelAndView.addObject("groupList", groupList);
            modelAndView.addObject("limsCaseInfo", limsCaseInfo);
            modelAndView.setViewName("caseCompare/relationComparison");
        }catch (Exception e) {
            logger.error("查看亲缘比对结果报错！" + e.getMessage());
            e.getStackTrace();
        }

        return modelAndView;
    }

    //查看混合比对详情
    @RequestMapping("/details")
    public ModelAndView details(HttpServletRequest request, String caseId, String consignmentId, Integer mixMatchLimit, Integer groupId,String sampleNo) {
        ModelAndView view = new ModelAndView();

        //获取当前登录用户
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo loaUserInfo = (LoaUserInfo) subject.getPrincipal();


        MatchAuditedGene matchAuditedGene = new MatchAuditedGene();
        matchAuditedGene.setCaseId(caseId);
        matchAuditedGene.setGeneType(Constants.GENE_NORMAL);
        //查询审核过的常染色体基因信息
        List<MatchAuditedGene> auditedGeneList = matchAuditedGeneService.selectByMatchAuditedGene(matchAuditedGene);

        matchAuditedGene.setGeneType(Constants.GENE_MIXED);
        //查询审核过混合的基因信息
        List<MatchAuditedGene> mixAuditedGeneList = matchAuditedGeneService.selectByMatchAuditedGene(matchAuditedGene);

        //混合检材分组
        List<CaseCompareResultGroup> mixGroupList = new ArrayList<>();
        List<CaseCompareResultGroup> newMixGroupList2 = new ArrayList<>();
        List<CaseCompareResultGroup> newMixGroupList3 = new ArrayList<>();



        List<CompareSameResult> compareSameResults = compareSameResultService.selectListByCaseId(caseId);
        List<String> compareSameResults2 = new ArrayList<>();

        try {
            mixMatchLimit=0;
            mixGroupList = geneMixCompareUtil.mixDoCompare(mixAuditedGeneList, auditedGeneList, mixMatchLimit);

            for (CompareSameResult compareSameResult : compareSameResults) {
                if (!compareSameResult.getSampleNo().equals(compareSameResult.getReferenceId())){
                    compareSameResults2.add(compareSameResult.getSampleNo());
                }
            }


            for (CaseCompareResultGroup caseCompareResultGroup : mixGroupList) {
                List<MatchAuditedGene> matchedSampleList1 = new ArrayList<>();
                if (caseCompareResultGroup.getMixMatchedSampleList().get(0).getSampleNo().equals(sampleNo)){
                    List<MatchAuditedGene> matchedSampleList = caseCompareResultGroup.getMixMatchedSampleList();

                    for (MatchAuditedGene auditedGene : matchedSampleList) {
                        if (!compareSameResults2.contains(auditedGene.getSampleNo())){
                            matchedSampleList1.add(auditedGene);
                        }
                    }


                    caseCompareResultGroup.setMixMatchedSampleList(matchedSampleList1);
                    newMixGroupList2.add(caseCompareResultGroup);
                }

            }
            List<MatchAuditedGene> matchAuditedGeneList = new ArrayList<>();
            Set<MatchAuditedGene> notDetectedList = new LinkedHashSet<>();



            for (CaseCompareResultGroup caseCompareResultGroup : newMixGroupList2) {

                List<MatchAuditedGene> mixMatchedSampleList = caseCompareResultGroup.getMixMatchedSampleList();
                for (MatchAuditedGene auditedGene : mixMatchedSampleList) {

                    if (StringUtils.isNotBlank(auditedGene.getGeneInfo())) {
                        List<String> markerList = geneSameCompareUtil.getMarker(auditedGene.getGeneInfo());
                        //审核通过的检材且基因座少于8个时，表示未检出
                        if(markerList.size() < 8){
                            notDetectedList.add(auditedGene);
                        }else {
                            if(!matchAuditedGeneList.contains(auditedGene)){
                                matchAuditedGeneList.add(auditedGene);
                            }
                        }
                    }else {
                        notDetectedList.add(auditedGene);
                    }

                }
                if (newMixGroupList3.size() == 0){
                    CaseCompareResultGroup caseCompareResultGroup1 = new CaseCompareResultGroup();
                    caseCompareResultGroup1 =caseCompareResultGroup;
                    caseCompareResultGroup1.setMixMatchedSampleList(matchAuditedGeneList);
                    newMixGroupList3.add(caseCompareResultGroup1);
                }
                    if (!caseCompareResultGroup.getMixMatchedSampleList().containsAll(notDetectedList)){


                    if (!newMixGroupList3.get(0).getMixMatchedSampleList().containsAll(caseCompareResultGroup.getMixMatchedSampleList())){
                        CaseCompareResultGroup caseCompareResultGroup1 = new CaseCompareResultGroup();
                        caseCompareResultGroup1 =caseCompareResultGroup;
                        caseCompareResultGroup1.setMixMatchedSampleList(matchAuditedGeneList);
                        newMixGroupList3.add(caseCompareResultGroup1);
                    }
                    }
            }


        }catch (Exception e) {
            logger.error("混合检材分组报错！" + e.getMessage());
            e.getStackTrace();
        }

        List<MatchAuditedGene> contributorList1 = new ArrayList<>();
        List<MatchAuditedGene> contributorList2 = new ArrayList<>();
        MatchAuditedGene contributor3 = null;
        MatchAuditedGene contributor4 = null;
        List<String> geneList1 = new ArrayList<>();
        List<String> geneList2 = new ArrayList<>();
        MatchAuditedGene mixAuditedGene = new MatchAuditedGene();
        MatchAuditedGene contributor1Gene = null;
        MatchAuditedGene contributor2Gene = null;
        List<Map<String, Object>> resultList = new ArrayList<>();
//        if (groupId != null) {
            if (ListUtils.isNotNullAndEmptyList(newMixGroupList3)) {
                for (CaseCompareResultGroup ccrg : newMixGroupList3) {
//                    if (groupId.intValue() == ccrg.getGroupId()) {
                        List<MatchAuditedGene> matchAuditedGeneList = ccrg.getMixMatchedSampleList();
                        for (int i = 0;i < matchAuditedGeneList.size(); i++) {
                            MatchAuditedGene auditedGene = matchAuditedGeneList.get(i);
                            mixAuditedGene = matchAuditedGeneList.get(0);
                            if (auditedGene != null) {
                                //获取贡献者1名称
                                if (i != 0) {
                                    contributorList1.add(auditedGene);
                                    contributor1Gene = auditedGene;
                                }
//                                if (i == 2) {
//                                    contributorList2.add(auditedGene.getSampleName());
//                                    contributor2Gene = auditedGene;
//                                }
                            }
                        }
                        break;
//                    }
                }
            }


//        }
        //试剂盒
        List<Panel> panels = panelService.selectAll();

        LimsCaseInfo limsCaseInfo = limsCaseInfoService.selectByCaseId(caseId);
        if (mixAuditedGene != null){
            BlendRelationResult blendRelationResult = blendRelationMapper.selectById(mixAuditedGene.getSampleId());
            if (blendRelationResult!= null){
                contributor3 = new MatchAuditedGene();

                contributor3.setSampleId(blendRelationResult.getBlendId());
                contributor3.setSampleName(blendRelationResult.getContributionName());
                contributor3.setSampleId1(blendRelationResult.getSampleId1());
                contributor3.setSampleId2(blendRelationResult.getSampleId2());

                contributor4 = new MatchAuditedGene();
                contributor4.setSampleId(blendRelationResult.getBlendId());
                contributor4.setSampleName(blendRelationResult.getContributionName2());
                contributor4.setSampleId1(blendRelationResult.getSampleId1());
                contributor4.setSampleId2(blendRelationResult.getSampleId2());

                for (Panel panel : panels) {
                    if (panel.getPanelname().equals(blendRelationResult.getTestkitName())){
                        panels.clear();
                        panels.add(panel);
                        break;
                    }
                }
                if (StringUtils.isNotBlank(blendRelationResult.getSampleId1())) {
                    List<MatchAuditedGene> matchAuditedGenes = matchAuditedGeneService.selectBySampleId(blendRelationResult.getSampleId1());
                    List<String> marker = geneSameCompareUtil.getMarker(matchAuditedGenes.get(0).getGeneInfo());
                    for (String s : marker) {
                        String geneStr = geneSameCompareUtil.analysisGeneStr(s, matchAuditedGenes.get(0).getGeneInfo());
                        geneList1.add(geneStr);
                    }
                }
                if (StringUtils.isNotBlank(blendRelationResult.getSampleId2())){

                    List<MatchAuditedGene> matchAuditedGenes2 = matchAuditedGeneService.selectBySampleId(blendRelationResult.getSampleId2());
                    List<String> marker2 = geneSameCompareUtil.getMarker(matchAuditedGenes2.get(0).getGeneInfo());
                    for (String s : marker2) {
                        String geneStr = geneSameCompareUtil.analysisGeneStr(s, matchAuditedGenes2.get(0).getGeneInfo());
                        geneList2.add(geneStr);
                    }
                }

            }
        }
        //获取混合与贡献者的基因座和基因位点
        resultList = geneMixCompareUtil.getGeneList(mixAuditedGene, contributor1Gene, contributor2Gene);
        view.addObject("resultList", resultList);
        contributorList2=contributorList1;
        if (panels.size() == 1){
            view.addObject("hiddenButton", 0);
        }
        view.addObject("panels", panels);

        view.addObject("geneList1", geneList1);
        view.addObject("geneList2", geneList2);
        view.addObject("mixGroupList", newMixGroupList3);
        view.addObject("limsCaseInfo", limsCaseInfo);
        view.addObject("mixAuditedGene", mixAuditedGene);
        view.addObject("contributorList1", contributorList1);
        view.addObject("contributorList2", contributorList2);
        view.addObject("contributor3", contributor3);
        view.addObject("contributor4", contributor4);
        view.addObject("caseId", caseId);
        view.addObject("consignmentId", consignmentId);
        view.addObject("mixMatchLimit", mixMatchLimit);
        view.addObject("fullName", loaUserInfo.getAmPersonalInfo().getFullName());
        view.setViewName("caseCompare/caseComparison");
        return view;
    }

    private String defaultInspector(String inspector, List<AmPersonalInfoVo> amPersonalInfoVoList) {
        String personalId = null;

        if (StringUtils.isNotBlank(inspector) && ListUtils.isNotNullAndEmptyList(amPersonalInfoVoList)) {
            for (AmPersonalInfoVo apiv : amPersonalInfoVoList) {
                if (inspector.equals(apiv.getEntity().getFullName())) {
                    personalId = apiv.getEntity().getPersonalId();
                    break;
                }
            }
        }

        return personalId;
    }

    /**
     * 判断此检材是否已经在亲缘比对中
     * @param sampleNo
     * @param relativeResultList
     * @return
     */
    public boolean isInRelative(String sampleNo, List<CompareRelativeResult> relativeResultList) {
        boolean flag = false;

        if (StringUtils.isNotBlank(sampleNo) && ListUtils.isNotNullAndEmptyList(relativeResultList)) {
            for (CompareRelativeResult crr : relativeResultList) {
                if (sampleNo.equals(crr.getFatherSampleNo()) || sampleNo.equals(crr.getMotherSampleNo())
                        || sampleNo.equals(crr.getChildSampleNo())) {
                    flag = true;
                    break;
                }
            }
        }

        return flag;
    }

    /**
     * 判断此检材是否是未检出检材
     * @param sampleNo
     * @param notDetectedList
     * @return
     */
    public boolean isInNotDetected(String sampleNo, Set<LimsSampleInfoDna> notDetectedList) {
        boolean flag = false;

        if (StringUtils.isNotBlank(sampleNo)) {
            for (LimsSampleInfoDna sampleInfoDna : notDetectedList) {
                if (sampleNo.equals(sampleInfoDna.getSampleNo()) && StringUtils.isNotBlank(sampleInfoDna.getSampleId())) {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    @RequestMapping("/saveBlendRelation")
    @ResponseBody
    public Map<String, Object> saveBlendRelation(BlendRelationResult blendRelationResult) {
        Map<String, Object> result = new HashMap<>();

        try {
            if (blendRelationResult != null && StringUtils.isNotBlank(blendRelationResult.getBlendId())){
                BlendRelationResult blendRelationResult1 = blendRelationMapper.selectById(blendRelationResult.getBlendId());
                if (blendRelationResult1!=null){
                    blendRelationResult.setId(blendRelationResult1.getId());
                    int update =  blendRelationMapper.update(blendRelationResult);
                    if (update != 0){
                        result.put("success", true);
                    }
                }else{
                    String string = UUID.randomUUID().toString();
                    blendRelationResult.setId(string);
                    int insert = blendRelationMapper.insert(blendRelationResult);
                    if (insert != 0){
                        result.put("success", true);
                    }
                }
            }else {
                result.put("success", false);
            }
        }catch (Exception e) {
            logger.error("保存混合关系结果报错！" + e.getMessage());
            result.put("success", false);
            e.getStackTrace();
        }

        return result;
    }
    @RequestMapping("/selectMatchAuditedGene")
    @ResponseBody
    public Map<String, Object> selectMatchAuditedGene(String sampleid) {
        Map<String, Object> result = new HashMap<>();

        try {
           if (StringUtils.isNotBlank(sampleid)){
               List<String> geneList = new ArrayList<>();
               List<MatchAuditedGene> matchAuditedGenes = matchAuditedGeneService.selectBySampleId(sampleid);
               List<String> marker = geneSameCompareUtil.getMarker(matchAuditedGenes.get(0).getGeneInfo());
               for (String s : marker) {
                   String geneStr = geneSameCompareUtil.analysisGeneStr(s, matchAuditedGenes.get(0).getGeneInfo());
                   geneList.add(geneStr);
               }

               System.out.println(marker);
               result.put("resultList",marker);
               result.put("geneList",geneList);
           }



        }catch (Exception e) {
            logger.error("保存混合关系结果报错！" + e.getMessage());
            result.put("success", false);
            e.getStackTrace();
        }

        return result;
    }



}
