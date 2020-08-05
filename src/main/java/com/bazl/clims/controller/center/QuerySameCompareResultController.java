package com.bazl.clims.controller.center;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bazl.clims.common.Constants;
import com.bazl.clims.compare.GeneRelativeCompareUtil;
import com.bazl.clims.compare.GeneSameCompareUtil;
import com.bazl.clims.compare.relative.po.ParentageMatchResult;
import com.bazl.clims.compare.relative.po.ParentageMatchResultRecord;
import com.bazl.clims.controller.BaseController;
import com.bazl.clims.dao.LogRecordMapper;
import com.bazl.clims.dao.MobileNewsMapper;
import com.bazl.clims.model.DictItem;
import com.bazl.clims.model.LoaUserInfo;
import com.bazl.clims.model.PageInfo;
import com.bazl.clims.model.po.*;
import com.bazl.clims.model.vo.LimsCaseInfoVo;
import com.bazl.clims.model.vo.MatchRelativeResultVo;
import com.bazl.clims.model.vo.MatchSameResultVo;
import com.bazl.clims.service.*;
import com.bazl.clims.utils.*;
import com.bazl.clims.webservice.basicInfo.ArrayOfString;
import com.bazl.clims.webservice.basicInfo.ArrayOfWSPanel;
import com.bazl.clims.webservice.basicInfo.BasicInfoPortType;
import com.bazl.clims.webservice.basicInfo.WSPanel;
import com.bazl.clims.webservice.caseSampleLib.CaseSampleLibPortType;
import com.bazl.clims.webservice.caseSampleLib.SampleNoDomain;
import com.bazl.clims.webservice.domain.CaseParentageMatchResult;
import com.bazl.clims.webservice.domain.LibMatchResultGroupDomain;
import com.bazl.clims.webservice.domain.TotalRelativeMatchResult;
import com.bazl.clims.webservice.services.CaseInfo;
import com.bazl.clims.webservice.services.QueryDataServicePortType;
import com.bazl.clims.webservice.util.MarkerAlleleUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by Sun on 2018/12/20.
 */
@RestController
@RequestMapping("/queryCompareResult")
public class QuerySameCompareResultController extends BaseController{

    @Autowired
    DictItemService dictItemService;

    @Autowired
    MatchSameResultService matchSameResultService;

    @Autowired
    MatchRelativeResultService matchRelativeResultService;

    @Autowired
    OrgInfoService orgInfoService;

    @Autowired
    LimsCaseInfoService limsCaseInfoService;

    @Autowired
    LimsConsignmentInfoService limsConsignmentInfoService;

    @Autowired
    LimsSampleInfoDnaService limsSampleInfoDnaService;

    @Autowired
    AmPersonalInfoService amPersonalInfoService;

    @Autowired
    MatchAuditedGeneService matchAuditedGeneService;

    @Autowired
    GeneSameCompareUtil geneSameCompareUtil;

    @Autowired
    GeneRelativeCompareUtil geneRelativeCompareUtil;

    @Autowired
    MobileNewsMapper mobileNewsMapper;

    @Autowired
    LogRecordMapper logRecordMapper;

    @Autowired
    WzService wzService;

    @Autowired
    QueryDataServicePortType queryDataService;

    @Autowired
    BasicInfoPortType basicInfoWebService;

    @Autowired
    CaseSampleLibPortType caseSampleLibService;

    @Value("${Is_App_Inform}")
    private int isAppInform;
    @Value("${Is_App_Url}")
    private String AppUrl;


    /**
     * 查看同一比对结果
     * @return
     */
    @RequestMapping("/querySameCompareResultDataList")
    public ModelAndView querySameCompareResultDataList(HttpServletRequest request, MatchSameResultVo matchResult, PageInfo pageInfo){
        ModelAndView view = new ModelAndView();

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo loaUserInfo = (LoaUserInfo) subject.getPrincipal();
        if(loaUserInfo == null){
            view.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return  view;
        }
        try {
            //查询受理单位
            List<OrgInfo> acceptOrgList = orgInfoService.selectAcceptOrgList();
            //获取当前登录用户
            //获取当前用户的id
            String userOrgId = loaUserInfo.getOrgId();
            //将当前用户org_id设置进query
            if(StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")){
                userOrgId = "110230000000";
            }
            matchResult.setUserOrdId(userOrgId);
            if(StringUtils.isNotBlank(userOrgId)){
                matchResult.setAcceptOrgId(userOrgId);
            }

            String orgId = StringUtils.isBlank(loaUserInfo.getSubOrgId()) ? loaUserInfo.getOrgId() : loaUserInfo.getSubOrgId();

            //查询委托单位（分局）
            String orgParentsId = "110000000000";
            List<OrgInfo> orgInfoList = orgInfoService.selectDelegateByParentsId(orgParentsId);
            view.addObject("orgInfoList", orgInfoList);

            //入库类型
            DictItem dictItem = new DictItem();
            dictItem.setDictTypeCode(Constants.INSTORED_TYPE);
            List<DictItem> dictSampleEntryTypeList = DictUtil.getDictList(dictItem);
            view.addObject("dictSampleEntryTypeList", dictSampleEntryTypeList);

            //比对类型
            dictItem.setDictTypeCode(Constants.COMPARISON_TYPE);
            List<DictItem> comparisonTypeList = DictUtil.getDictList(dictItem);
            view.addObject("comparisonTypeList", comparisonTypeList);

            //比对类别
            List<DictItem> comparisonCategoryList1 = new LinkedList<>();
            dictItem.setDictTypeCode(Constants.COMPARISON_CATEGORY);
            List<DictItem> comparisonCategoryList = DictUtil.getDictList(dictItem);
            for(DictItem dictItem1:comparisonCategoryList){
                if(("05").equals(dictItem1.getDictCode())){
                    comparisonCategoryList1.add(dictItem1);
                }else if(("03").equals(dictItem1.getDictCode())){
                    comparisonCategoryList1.add(dictItem1);
                }else if(("04").equals(dictItem1.getDictCode())){
                    comparisonCategoryList1.add(dictItem1);
                }else if(("02").equals(dictItem1.getDictCode())){
                    comparisonCategoryList1.add(dictItem1);
                }
            }
            view.addObject("comparisonCategoryList", comparisonCategoryList1);

            //状态
            List<DictItem> compareStatusList1 = new LinkedList<>();
            dictItem.setDictTypeCode(Constants.COMPARE_STATUS);
            List<DictItem> compareStatusList = DictUtil.getDictList(dictItem);
            for(DictItem dictItem1:compareStatusList){
                if(("0").equals(dictItem1.getDictCode())){
                    compareStatusList1.add(dictItem1);
                }else if(("2").equals(dictItem1.getDictCode())){
                    compareStatusList1.add(dictItem1);
                }else if(("3").equals(dictItem1.getDictCode())){
                    compareStatusList1.add(dictItem1);
                }
            }
            view.addObject("compareStatusList", compareStatusList1);

            MatchSameResultVo matchResult2 = new MatchSameResultVo();
            //matchResult2.setDelegateOrgCode(orgId);
            //查询条件
            matchResult2.setUserOrdId(userOrgId);
            if (StringUtils.isNotBlank(matchResult.getCaseName())) {
                matchResult2.setCaseName(matchResult.getCaseName().trim());
            }else{
                matchResult2.setCaseName(null);
            }
            //案件id
            if (StringUtils.isBlank(matchResult.getCaseId())) {
                matchResult2.setCaseId(null);
            } else {
                matchResult2.setCaseId(matchResult.getCaseId().trim());
            }
            if (StringUtils.isNotBlank(matchResult.getSampleName())) {
                matchResult2.setSampleName(matchResult.getSampleName().trim());
            }else{
                matchResult2.setSampleName(null);
            }
            if (StringUtils.isNotBlank(matchResult.getSampleNo())) {
                matchResult2.setSampleNo(matchResult.getSampleNo().trim());
            }else{
                matchResult2.setSampleNo(null);
            }
            if(StringUtils.isNotBlank(matchResult.getInstoredType())){
                matchResult2.setInstoredType(matchResult.getInstoredType().trim());
            }else{
                matchResult2.setInstoredType(null);
            }

            if(StringUtils.isNotBlank(matchResult.getComparisonCategory())){
                if(("05").equals(matchResult.getComparisonCategory())){
                    matchResult2.setComparisonCategory(null);
                }else{
                    matchResult2.setComparisonCategory(matchResult.getComparisonCategory().trim());
                }
            }else{
                  matchResult2.setComparisonCategory("05");
                  matchResult.setComparisonCategory("05");
            }

            if(StringUtils.isNotBlank(matchResult.getDelegateOrgName())){
                matchResult2.setDelegateOrgName(matchResult.getDelegateOrgName().trim());
            }else{
                matchResult2.setDelegateOrgName(null);
            }

            if(StringUtils.isNotBlank(matchResult.getCaseNo())){
                matchResult2.setCaseNo(matchResult.getCaseNo().trim());
            }else{
                matchResult2.setCaseNo(null);
            }

            if(null != matchResult.getCreateStartDatetime()){
                matchResult2.setCreateStartDatetime(matchResult.getCreateStartDatetime());
            }else{
                matchResult2.setCreateStartDatetime(null);
            }

            if(null != matchResult.getCreateEndDatetime()){
                matchResult2.setCreateEndDatetime(matchResult.getCreateEndDatetime());
            }else{
                matchResult2.setCreateEndDatetime(null);
            }

            if(null != matchResult.getEntity().getSameCount()){
                matchResult2.getEntity().setSameCount(matchResult.getEntity().getSameCount());
            }else{
                matchResult2.getEntity().setSameCount(null);
            }

            if(StringUtils.isNotBlank(matchResult.getEntity().getCompareStatus())){
                matchResult2.getEntity().setCompareStatus(matchResult.getEntity().getCompareStatus());
            }else{
                matchResult2.getEntity().setCompareStatus("0");
            }

            Map<String, Object> result = new HashMap<>();
            //查询所有组id
            List<MatchSameResultVo> matchCaseResultList = matchSameResultService.selectGroupAll(matchResult2, pageInfo);
            int totalCnt = matchSameResultService.selectListCount(matchResult2);
            for (MatchSameResultVo matchSameResultVo : matchCaseResultList) {
                MatchSameResultVo matchResult1 = new MatchSameResultVo();
                if(StringUtils.isNotBlank(matchSameResultVo.getEntity().getGroupId())){
                    matchResult1.getEntity().setGroupId(matchSameResultVo.getEntity().getGroupId());
                    //条件查询
                    //案件id
                    if (StringUtils.isBlank(matchResult.getCaseId())) {
                        matchResult1.setCaseId(null);
                    } else {
                        matchResult1.setCaseId(matchResult.getCaseId().trim());
                    }
                    if (StringUtils.isNotBlank(matchResult.getCaseName())) {
                        matchResult1.setCaseName(matchResult.getCaseName().trim());
                    }else{
                        matchResult1.setCaseName(null);
                    }
                    if (StringUtils.isNotBlank(matchResult.getSampleName())) {
                        matchResult1.setSampleName(matchResult.getSampleName().trim());
                    }else{
                        matchResult1.setSampleName(null);
                    }
                    if (StringUtils.isNotBlank(matchResult.getSampleNo())) {
                        matchResult1.setSampleNo(matchResult.getSampleNo().trim());
                    }else{
                        matchResult1.setSampleNo(null);
                    }
                    if(StringUtils.isNotBlank(matchResult.getInstoredType())){
                        matchResult1.setInstoredType(matchResult.getInstoredType().trim());
                    }else{
                        matchResult1.setInstoredType(null);
                    }

                     if(StringUtils.isNotBlank(matchResult.getComparisonCategory())){
                        if(("05").equals(matchResult.getComparisonCategory())){
                            matchResult1.setComparisonCategory(null);
                        }else{
                            matchResult1.setComparisonCategory(matchResult.getComparisonCategory().trim());
                        }
                    }else{
                        matchResult1.setComparisonCategory("05");
                         matchResult.setComparisonCategory("05");
                     }

                    if(StringUtils.isNotBlank(matchResult.getDelegateOrgName())){
                        matchResult1.setDelegateOrgName(matchResult.getDelegateOrgName().trim());
                    }else{
                        matchResult1.setDelegateOrgName(null);
                    }

                    if(StringUtils.isNotBlank(matchResult.getCaseNo())){
                        matchResult1.setCaseNo(matchResult.getCaseNo().trim());
                    }else{
                        matchResult1.setCaseNo(null);
                    }

                    if(StringUtils.isNotBlank(matchResult.getEntity().getCompareStatus())){
                        matchResult1.getEntity().setCompareStatus(matchResult.getEntity().getCompareStatus());
                    }else{
                        matchResult1.getEntity().setCompareStatus("0");
                    }
                    //根据组id查询比对结果
                    List<MatchSameResultVo> matchResultList = matchSameResultService.selectListByGroupId(matchResult1);

                    //去重
                    List<MatchSameResultVo> collect = matchResultList.stream().filter(distinctByKey(MatchSameResultVo::getSampleNo)).collect(Collectors.toList());

                    //循环遍历list获取入库样本类型
                    for (int i = 0; i < collect.size(); i++) {
                        for (DictItem dictSampleEntryType : dictSampleEntryTypeList) {
                            if (collect.get(i).getInstoredType() != null) {
                                if (collect.get(i).getInstoredType().equals(dictSampleEntryType.getDictCode())) {
                                    collect.get(i).setInstoredType(dictSampleEntryType.getDictName());
                                }
                            }
                        }
                    }
                    result.put(matchSameResultVo.getEntity().getGroupId(), collect);
                }
            }
            view.addObject("orgInfoList", orgInfoList);
            view.addObject("acceptOrgList", acceptOrgList);
            view.addObject("userOrgId", userOrgId);
            view.addObject("matchCaseResultList", matchCaseResultList);
            view.addObject("matchResultList", result);
            view.addObject("pageInfo", pageInfo.updatePageInfo(totalCnt));
            view.addObject("comparisonCategory", matchResult.getComparisonCategory());
            view.addObject("matchResult", matchResult);
            view.setViewName("query/queryCompareResultList");
        }catch (Exception e){
            logger.info("查看同一比对结果:"+e);
        }
        return view;
    }

    /**
     * 查看跨区同一比对结果
     * @return
     */
    @RequestMapping("/querySameAreaSameCompareResultList")
    public String queryAreaSameCompareResultList(HttpServletRequest request, MatchSameResultVo matchResult, PageInfo pageInfo,@RequestBody String afferentJson){
        String result = null;
        ModelAndView view = new ModelAndView();
        LoaUserInfo loaUserInfo = new LoaUserInfo();
        pageInfo.setEvePageRecordCnt1(5);
        JSONObject jsonObject = JSON.parseObject(afferentJson);
        loaUserInfo.setOrgId(jsonObject.get("orgId").toString());
        pageInfo.setPage(Integer.parseInt(jsonObject.get("page").toString()));
        String JSONStr = jsonObject.getString("matchResult").toString();
        matchResult = JSON.parseObject(JSONStr, MatchSameResultVo.class);
        //获取当前登录人信息
        /*Subject subject = SecurityUtils.getSubject();
        LoaUserInfo loaUserInfo = (LoaUserInfo) subject.getPrincipal();*/

        if(loaUserInfo == null){
            view.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return  null;
        }
        try {
            //查询受理单位
            List<OrgInfo> acceptOrgList = orgInfoService.selectAcceptOrgList();
            //获取当前登录用户
            //获取当前用户的id
            String userOrgId = loaUserInfo.getOrgId();
            //将当前用户org_id设置进query
            if(StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")){
                userOrgId = "110230000000";
            }
            matchResult.setUserOrdId(userOrgId);
            if(StringUtils.isNotBlank(userOrgId)){
                matchResult.setAcceptOrgId(userOrgId);
            }

            //查询委托单位（分局）
            String orgParentsId = "110000000000";
            List<OrgInfo> orgInfoList = orgInfoService.selectDelegateByParentsId(orgParentsId);
            view.addObject("orgInfoList", orgInfoList);

            //入库类型
            DictItem dictItem = new DictItem();
            dictItem.setDictTypeCode(Constants.INSTORED_TYPE);
            List<DictItem> dictSampleEntryTypeList = DictUtil.getDictList(dictItem);
            view.addObject("dictSampleEntryTypeList", dictSampleEntryTypeList);

            //比对类型
            dictItem.setDictTypeCode(Constants.COMPARISON_TYPE);
            List<DictItem> comparisonTypeList = DictUtil.getDictList(dictItem);
            view.addObject("comparisonTypeList", comparisonTypeList);

            //比对类别
            List<DictItem> comparisonCategoryList1 = new LinkedList<>();
            dictItem.setDictTypeCode(Constants.COMPARISON_CATEGORY);
            List<DictItem> comparisonCategoryList = DictUtil.getDictList(dictItem);
            for(DictItem dictItem1:comparisonCategoryList){
                if(("05").equals(dictItem1.getDictCode())){
                    comparisonCategoryList1.add(dictItem1);
                }else if(("03").equals(dictItem1.getDictCode())){
                    comparisonCategoryList1.add(dictItem1);
                }else if(("04").equals(dictItem1.getDictCode())){
                    comparisonCategoryList1.add(dictItem1);
                }else if(("02").equals(dictItem1.getDictCode())){
                    comparisonCategoryList1.add(dictItem1);
                }
            }
            view.addObject("comparisonCategoryList", comparisonCategoryList1);

            //状态
            List<DictItem> compareStatusList1 = new LinkedList<>();
            dictItem.setDictTypeCode(Constants.COMPARE_STATUS);
            List<DictItem> compareStatusList = DictUtil.getDictList(dictItem);
            for(DictItem dictItem1:compareStatusList){
                if(("0").equals(dictItem1.getDictCode())){
                    compareStatusList1.add(dictItem1);
                }else if(("2").equals(dictItem1.getDictCode())){
                    compareStatusList1.add(dictItem1);
                }else if(("3").equals(dictItem1.getDictCode())){
                    compareStatusList1.add(dictItem1);
                }
            }
            view.addObject("compareStatusList", compareStatusList1);

            matchResult = initMatchResult(matchResult);

            String instoredType = null;

            if (StringUtils.isNotBlank(matchResult.getInstoredType())) {
                Integer instore = TransferHelper.convertToInstoredType(matchResult.getInstoredType());
                if (instore != null) {
                    instoredType = String.valueOf(instore);
                }
            }

            //转换比对结果类别
            String groupType = TransferHelper.convertToComparisonCategory(matchResult.getComparisonCategory());

            String matchTimeStart = null;
            if (matchResult.getCreateStartDatetime() != null) {
                matchTimeStart = DateUtils.dateToString(matchResult.getCreateStartDatetime(), DateUtils.HOUR_MINUTES_SECOND);
            }
            String matchTimeEnd = null;
            if (matchResult.getCreateEndDatetime() != null) {
                matchTimeEnd  = DateUtils.dateToString(matchResult.getCreateEndDatetime(), DateUtils.HOUR_MINUTES_SECOND);
            }

            String delegateOrgCode = null;
            if (StringUtils.isNotBlank(userOrgId) && userOrgId.length() > 6) {
                delegateOrgCode = userOrgId.substring(0, 6);
            }

            String caseId = "";
            if (StringUtils.isNotBlank(matchResult.getCaseNo())) {
                LimsCaseInfoVo limsCaseInfoVo = new LimsCaseInfoVo();
                limsCaseInfoVo.getEntity().setCaseNo(matchResult.getCaseNo());
                List<LimsCaseInfoVo> caseInfoVoList = limsCaseInfoService.selectVOCaseInfoList(limsCaseInfoVo);
                if (ListUtils.isNotNullAndEmptyList(caseInfoVoList)) {
                    for (LimsCaseInfoVo lciVo : caseInfoVoList) {
                        caseId += "'" + lciVo.getEntity().getCaseId() + "'" + ",";
                    }
                    if(caseId.length() > 0) {
                        caseId = caseId.substring(0,caseId.length() -1);
                    }
                }
            }

            List<LibMatchResultGroupDomain> matchResultGroupList = wzService.libMatchRecordGroupList(matchResult.getSampleNo(),
                    matchResult.getSampleName(), matchResult.getCaseName(), instoredType,
                    Constants.MATCH_TYPE_1, matchResult.getEntity().getCompareStatus(),null, groupType, null,matchTimeStart,matchTimeEnd,
                    delegateOrgCode, caseId, null, pageInfo.getPage(), pageInfo.getEvePageRecordCnt1());

            int recordCount = 0;
            if (ListUtils.isNotNullAndEmptyList(matchResultGroupList)) {
                logger.info("串并案页面查询跳出，查询结果过滤开始...");
                for(int i=0;i<matchResultGroupList.size();i++){
                    LibMatchResultGroupDomain libMatchResultGroupDomain = (LibMatchResultGroupDomain)matchResultGroupList.get(i);
                    if (libMatchResultGroupDomain != null) {
                        List<Map<String,String>> sampleList= (List<Map<String,String>>) libMatchResultGroupDomain.getSampleList();
                        List<Map<String, Object>> caseSnList = new ArrayList<Map<String, Object>>();
                        int levelflag = 0;
                        for (Map<String,String> samplemap : sampleList) {
                            Map<String,String> map = (Map<String,String>)samplemap;
                            samplemap.getClass().cast(map);
                            String instoreType = null;
                            for (DictItem di: dictSampleEntryTypeList) {
                                if(samplemap.get("sampletype")!=null){
                                    Integer dictCode = TransferHelper.convertToInstoredType(di.getDictCode());
                                    if (dictCode != null) {
                                        instoreType = String.valueOf(dictCode);
                                    }
                                    if(StringUtils.isNotBlank(instoreType)){
                                        if (instoreType.equals(samplemap.get("sampletype").toString())) {
                                            samplemap.put("sampleTypeName", di.getDictName());
                                            break;
                                        }
                                    }
                                }
                            }

                            recordCount = libMatchResultGroupDomain.getRowCnt();

                            if (samplemap.get("caseid") != null) {
                                Map<String, Object> caseSnMap = new HashMap<String, Object>();
                                CaseInfo caseInfo = queryDataService.getCaseInfoByCaseId(samplemap.get("caseid"));
                                if (caseInfo != null) {
                                    String caseSn = String.valueOf(caseInfo.getCaseSn().getValue());
                                    if (StringUtils.isNotBlank(caseSn)) {
                                        caseSnMap.put("caseId", samplemap.get("caseid"));
                                        caseSnMap.put("caseSn", caseSn);
                                        if(!caseSnList.contains(caseSnMap))
                                            caseSnList.add(caseSnMap);
                                        samplemap.put("caseHandleType", String.valueOf(caseInfo.getCaseHandleType().getValue()));
                                        samplemap.put("caseBaseId", String.valueOf(caseInfo.getCaseBaseId().getValue()));
                                        samplemap.put("isCaseAppend", String.valueOf(caseInfo.getHasAppend().intValue()));
                                        String orgName = String.valueOf(caseInfo.getDelegateOrgName().getValue());
                                        if (StringUtils.isBlank(orgName)) {
                                            LimsConsignmentInfo limsConsignmentInfo = limsConsignmentInfoService.selectByCaseNo(caseSn.trim());
                                            if (limsConsignmentInfo != null) {
                                                orgName = limsConsignmentInfo.getDelegateOrgName();
                                            }
                                        }
                                        samplemap.put("delegateOrgName", orgName);
                                        samplemap.put("delegateOrgCode", String.valueOf(caseInfo.getDelegateOrgCode().getValue()));
                                    }
                                }
                            }
                        }

                        libMatchResultGroupDomain.setCaseSnList(caseSnList);
                    }
                }
                logger.info("串并案页面查询结束，返回！");
            }

            JSONObject obj = new JSONObject();
            obj.put("userOrgId",userOrgId);
            obj.put("matchResultGroupList",matchResultGroupList);
            obj.put("pageInfo",pageInfo.updatePageInfo(recordCount));
            obj.put("comparisonCategory",matchResult.getComparisonCategory());
            obj.put("matchResult",matchResult);
            result = obj.toString();
        }catch (Exception e){
            logger.info("查看跨区比对结果:"+e);
        }
        return result;
    }

    /**
     * 查看跨区同一比对结果详情
     * @param matchResult
     * @param pageInfo
     * @return
     */
    @RequestMapping("/querySameAreaSameCompareResultDetail")
    public String queryAreaSameCompareResultDetail(MatchSameResultVo matchResult, PageInfo pageInfo,@RequestBody String afferentJson) {
        ModelAndView view = new ModelAndView();
        String result = null;
        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo loaUserInfo = new LoaUserInfo();

        pageInfo.setEvePageRecordCnt1(5);
        JSONObject jsonObject = JSON.parseObject(afferentJson);
        loaUserInfo.setOrgId(jsonObject.get("orgId").toString());
        pageInfo.setPage(Integer.parseInt(jsonObject.get("page").toString()));
        String JSONStr = jsonObject.getString("matchResult").toString();
        matchResult = JSON.parseObject(JSONStr, MatchSameResultVo.class);

        //LoaUserInfo loaUserInfo = (LoaUserInfo) subject.getPrincipal();
        if(loaUserInfo == null){
            view.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return  result;
        }

        try {
            List<AmPersonalInfo> amPersonalInfoList = amPersonalInfoService.queryAmPersonalInfoListOrgId(loaUserInfo.getOrgId());

            //入库类型
            DictItem dictItem = new DictItem();
            dictItem.setDictTypeCode(Constants.INSTORED_TYPE);
            List<DictItem> dictSampleEntryTypeList = DictUtil.getDictList(dictItem);
            view.addObject("dictSampleEntryTypeList", dictSampleEntryTypeList);

            //状态
            List<DictItem> compareStatusList1 = new LinkedList<>();
            dictItem.setDictTypeCode(Constants.COMPARE_STATUS);
            List<DictItem> compareStatusList = DictUtil.getDictList(dictItem);
            for(DictItem dictItem1:compareStatusList){
                if(("0").equals(dictItem1.getDictCode())){
                    compareStatusList1.add(dictItem1);
                }else if(("2").equals(dictItem1.getDictCode())){
                    compareStatusList1.add(dictItem1);
                }else if(("3").equals(dictItem1.getDictCode())){
                    compareStatusList1.add(dictItem1);
                }
            }
            view.addObject("compareStatusList", compareStatusList1);

            matchResult = initMatchResult(matchResult);

            String instoredType = null;

            if (StringUtils.isNotBlank(matchResult.getInstoredType())) {
                Integer instore = TransferHelper.convertToInstoredType(matchResult.getInstoredType());
                if (instore != null) {
                    instoredType = String.valueOf(instore);
                }
            }

            List<Map<String, Object>> matchResultList = wzService.libMatchRecordList(matchResult.getEntity().getCompareStatus(),
                    matchResult.getSampleNo(), null, matchResult.getCaseName(), matchResult.getSampleName(), matchResult.getOperator(),
                    instoredType, matchResult.getGroupId(),pageInfo.getPage(), pageInfo.getEvePageRecordCnt());

            int recordCount = 0;
            if (ListUtils.isNotNullAndEmptyList(matchResultList)) {
                for (Map<String, Object> map : matchResultList) {
                    String instoreType = null;
                    for (DictItem di : dictSampleEntryTypeList) {
                        if(map.get("targetSampleType")!=null){
                            Integer dictCode = TransferHelper.convertToInstoredType(di.getDictCode());
                            if (dictCode != null) {
                                instoreType = String.valueOf(dictCode);
                            }
                            if(StringUtils.isNotBlank(instoreType)){
                                if (instoreType.equals(map.get("targetSampleType").toString())) {
                                    map.put("targetSampleTypeName", di.getDictName());
                                    break;
                                }
                            }
                        }

                    }
                    if(map.get("recordCount")!=null) {
                        recordCount = Integer.parseInt(map.get("recordCount").toString());
                    }
                }
            }
			
            JSONObject obj = new JSONObject();

            obj.put("amPersonalInfoList",amPersonalInfoList);
            obj.put("matchResultList",matchResultList);
            obj.put("matchResult",matchResult);
            obj.put("pageInfo",pageInfo.updatePageInfo(recordCount));
            result = obj.toString();
        }catch (Exception e) {
            logger.info("查看同一比对结果详细报错:"+e);
        }

        return result;
    }

    /**
     * 查看跨区同一本地库比对结果详细单
     * @param request
     * @param matchResultVo
     * @param pageInfo
     * @return
     */
    @RequestMapping("/querySameAreaSameCompareCondition")
    public String queryAreaSameCompareCondition(HttpServletRequest request, MatchSameResultVo matchResultVo, PageInfo pageInfo, @RequestBody String afferentJson) {
        ModelAndView modelAndView = new ModelAndView();
        String result = null;
        pageInfo.setEvePageRecordCnt1(5);
        JSONObject jsonObject = JSON.parseObject(afferentJson);
        pageInfo.setPage(Integer.parseInt(jsonObject.get("page").toString()));
        String JSONStr = jsonObject.getString("matchResultVo").toString();
        matchResultVo = JSON.parseObject(JSONStr, MatchSameResultVo.class);

        matchResultVo = initMatchResult(matchResultVo);

        String instoredType = null;

        if (StringUtils.isNotBlank(matchResultVo.getInstoredType())) {
            Integer instore = TransferHelper.convertToInstoredType(matchResultVo.getInstoredType());
            if (instore != null) {
                instoredType = String.valueOf(instore);
            }
        }

        List<Map<String, Object>> matchResultList = wzService.libMatchRecordList(matchResultVo.getEntity().getCompareStatus(),
                matchResultVo.getSampleNo(), matchResultVo.getMatchId(), matchResultVo.getCaseName(), matchResultVo.getSampleName(),
                matchResultVo.getOperator(), instoredType, matchResultVo.getGroupId(),pageInfo.getPage(), pageInfo.getEvePageRecordCnt());

        modelAndView = getResultModelAndView(matchResultList, matchResultVo.getMatchId(), matchResultVo.getPanel());

        JSONObject obj = new JSONObject();
        obj.put("modelAndView",modelAndView);
        obj.put("matchResultVo",matchResultVo);
        result = obj.toString();
        /*modelAndView.addObject("matchResultVo", matchResultVo);
        modelAndView.setViewName("query/viewAreaCompareCondition");*/
        return result;
    }

    public ModelAndView getResultModelAndView(List<Map<String, Object>> matchResultList, String matchId, String panel) {

        ModelAndView modelAndView = new ModelAndView();

        //入库类型
        DictItem dictItem = new DictItem();
        dictItem.setDictTypeCode(Constants.INSTORED_TYPE);
        List<DictItem> dictSampleEntryTypeList = DictUtil.getDictList(dictItem);

        int recordCount = 0;

        if (ListUtils.isNotNullAndEmptyList(matchResultList)) {
            for (Map<String, Object> map : matchResultList) {
                String instoreType = null;
                for (DictItem di : dictSampleEntryTypeList) {
                    if(map.get("targetSampleType")!=null){
                        Integer dictCode = TransferHelper.convertToInstoredType(di.getDictCode());
                        if (dictCode != null) {
                            instoreType = String.valueOf(dictCode);
                        }
                        if(StringUtils.isNotBlank(instoreType)){
                            if (instoreType.equals(map.get("targetSampleType").toString())) {
                                map.put("targetSampleTypeName", di.getDictName());
                                break;
                            }
                        }
                    }

                }
                if(map.get("recordCount")!=null) {
                    recordCount = Integer.parseInt(map.get("recordCount").toString());
                }
            }

            List<Map<String, Object>> panelList = panelList_forward();
            modelAndView.addObject("panelList", panelList);
            Map<String, Object> matchResult = null;
            for (Map<String, Object> map : matchResultList) {
                if (StringUtils.isNotBlank(matchId) &&
                        map.get("matchId").equals(matchId)) {
                    matchResult = map;
                    break;
                }
            }

            if (matchResult != null) {
                String sampleAId = matchResult.get("sampleAId").toString();
                String sampleBId = matchResult.get("sampleBId").toString();
                String sampleCId = matchResult.get("sampleCId")==null?null:matchResult.get("sampleCId").toString();

                Map<String,Object> SampleA = (Map<String, Object>) matchResult.get("genoTypeA");
                Map<String,Object> SampleB = (Map<String, Object>) matchResult.get("genoTypeB");
                Map<String,Object> SampleC = (Map<String, Object>) matchResult.get("genoTypeC");

                Map<String,Object> sampleABC = new HashMap<String,Object>();
                sampleABC.put(sampleAId, SampleA);
                sampleABC.put(sampleBId, SampleB);
                if(sampleCId != null) {
                    sampleABC.put(sampleCId, SampleC);
                }

                String panelName = "";
                if (matchResult.get("panelA") != null) {
                    panelName = matchResult.get("panelA").toString();
                } else if (matchResult.get("panelB") != null) {
                    panelName = matchResult.get("panelB").toString();
                } else if (matchResult.get("panelC") != null) {
                    panelName = matchResult.get("panelC").toString();
                }
                if(panelName == null || panelName.equals("")) {
                    if (panelList.get(0) != null) {
                        panelName = panelList.get(0).get("panelName").toString();
                    }
                }

                if (panel == null || panel.equals("")) {
                    panel = panelName;
                }
                String panelId = null;
                for(Map<String,Object> map : panelList){
                    if(map.get("panelName").equals(panel)){
                        panelId = map.get("panelId").toString();
                    }
                }

                if(panelId == null || panelId.equals("")) {
                    panelId = panelList.get(0).get("panelId").toString();
                }

                List<String> markerOfPanel = markersOfPanel_forward(panelId);

                Map<String,String> nationalLibNoMap = getNationalLibNo(sampleAId, sampleBId, sampleCId);
                matchResult.put("sampleANo", nationalLibNoMap.get(sampleAId));
                matchResult.put("sampleBNo", nationalLibNoMap.get(sampleBId));
                matchResult.put("sampleCNo", nationalLibNoMap.get(sampleCId));

                //同一匹配
                if(matchResult.get("matchType").toString().equals("同一匹配")) {
                    Map<String,Object> markerRates = (Map<String, Object>) matchResult.get("markerRates");
                    List<Map<String, Object>> piOfMarkers = getSameMatchMarkerRateList(SampleA, SampleB, SampleC, markerRates);
                    matchResult.put("piOfMarkers", MarkerAlleleUtil.arraySortAlleleList(piOfMarkers, markerOfPanel));
                }else { //亲缘匹配
                    TotalRelativeMatchResult totalRelativeMatchResult = wzService.getLibMatchResult(matchId,2);
                    if(totalRelativeMatchResult != null){
                        if(totalRelativeMatchResult.getFBarcode() !=null || totalRelativeMatchResult.getMBarcode() != null || totalRelativeMatchResult.getCBarcode() != null){
                            Map<String, Object> fMarker = (Map<String, Object>) sampleABC.get(totalRelativeMatchResult.getFBarcode());
                            Map<String, Object> mMarker = (Map<String, Object>) sampleABC.get(totalRelativeMatchResult.getMBarcode());
                            Map<String, Object> zMarker = (Map<String, Object>) sampleABC.get(totalRelativeMatchResult.getCBarcode());

                            List<Map<String,Object>> piOfMarkers = getRelativeMarkerRateList(fMarker, mMarker, zMarker,totalRelativeMatchResult);
                            Map<String, Object> matchCountAndTotalPi = getRelativeTotalPiAndMatchCount(totalRelativeMatchResult);

                            matchResult.put("piOfMarkers", MarkerAlleleUtil.arraySortAlleleList(piOfMarkers, markerOfPanel));
                            matchResult.put("matchCountAndTotalPi", matchCountAndTotalPi);
                            matchResult.put("fSampleName", getSampelName(totalRelativeMatchResult.getFBarcode(), matchResult));
                            matchResult.put("mSampleName", getSampelName(totalRelativeMatchResult.getMBarcode(), matchResult));
                            matchResult.put("cSampleName", getSampelName(totalRelativeMatchResult.getCBarcode(), matchResult));
                            matchResult.put("fBarcode", totalRelativeMatchResult.getFBarcode());
                            matchResult.put("mBarcode", totalRelativeMatchResult.getMBarcode());
                            matchResult.put("cBarcode", totalRelativeMatchResult.getCBarcode());
                        }
                    }
                }

                modelAndView.addObject("matchResult", matchResult);
            }
        }

        return modelAndView;
    }


    private List<Map<String, Object>> panelList_forward() {
        ArrayOfWSPanel aos = basicInfoWebService.getPanelList();
        List<WSPanel> wsList = aos.getWSPanel();
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        for (WSPanel w : wsList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("panelId", w.getPanelID().getValue());
            map.put("panelName", w.getPanelName().getValue());
            mapList.add(map);
        }
        return mapList;
    }

    private List<String> markersOfPanel_forward(String name) {
        List<String> result = new ArrayList<String>();
        ArrayOfString aos = basicInfoWebService.getMarkersOfPanel(name);
        if (aos != null) {
            for (String s : aos.getString()) {
                result.add(s);
            }
        }
        return result;
    }


    private Map<String,String> getNationalLibNo(String sampleAId,String sampleBId,String sampleCId) {
        String sampleIdStr = "";
        if (sampleAId != null
                && !"".equals(sampleAId))
            sampleIdStr += "'" + sampleAId + "'";

        if (sampleBId != null
                && !"".equals(sampleBId))
            sampleIdStr += ",'" + sampleBId + "'";

        if (sampleCId != null
                && !"".equals(sampleCId))
            sampleIdStr += ",'" + sampleCId + "'";

        Map<String,String> samplenoMap = getSamplenos(sampleIdStr);

        return samplenoMap;
    }

    /**
     * 根据条码号 获取 sampleno
     * @param sampleIdStr
     * @return
     */
    public Map<String,String> getSamplenos(String sampleIdStr) {
        Map<String,String> result = new HashMap<String,String>();
        List<SampleNoDomain> sampleInfoList = caseSampleLibService.getSamples(sampleIdStr).getSampleNoDomain();
        for(SampleNoDomain sampleInfo : sampleInfoList) {
            result.put(sampleInfo.getSampleId().getValue(), sampleInfo.getSampleNo().getValue());
        }
        return result;
    }


    /**
     * 获取亲缘比对每个marker的似然比 和 allele值
     * @param fMarker
     * @param mMarker
     * @param zMarker
     * @param totalRelativeMatchResult
     * @return
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getRelativeMarkerRateList(Map<String, Object> fMarker, Map<String, Object> mMarker, Map<String, Object> zMarker,TotalRelativeMatchResult totalRelativeMatchResult ) {
        List<Map<String, Object>> piOfMarkers = new ArrayList<Map<String,Object>>();

        CaseParentageMatchResult result = totalRelativeMatchResult.getResult();
        CaseParentageMatchResult mResult = totalRelativeMatchResult.getMResult();
        CaseParentageMatchResult fResult = totalRelativeMatchResult.getFResult();
        List<String> markerOfPanel = new ArrayList<String>();
        for(String markerName : fMarker.keySet())
        {
            if(!markerOfPanel.contains(markerName))
                markerOfPanel.add(markerName);
        }
        for(String markerName : mMarker.keySet())
        {
            if(!markerOfPanel.contains(markerName))
                markerOfPanel.add(markerName);
        }
        for(String markerName : zMarker.keySet())
        {
            if(!markerOfPanel.contains(markerName))
                markerOfPanel.add(markerName);
        }

        for (String markerName : markerOfPanel) {
            Map<String, Object> map = new HashMap<String, Object>();

            Map<String, Object> piOfF = (Map) fResult.getMarkerRates();
            Map<String, Object> piOfM = (Map) mResult.getMarkerRates();
            Map<String, Object> piofTotal = (Map) result.getMarkerRates();

            map.put("markerName", markerName);
            map.put("alleleF", fMarker.get(markerName));
            map.put("alleleM", mMarker.get(markerName));
            map.put("alleleZ", zMarker.get(markerName));

            if (piofTotal != null && piofTotal.size() > 0) {
                Object obj = piofTotal.get(markerName);
                if (obj != null) {
                    Double pi = (Double) obj;
                    map.put("pi", pi);
                    if (pi.isNaN())
                        map.put("noMatched", true);
                }
            }

            if (piOfF != null && piOfF.size() > 0) {
                Object obj = piOfF.get(markerName);
                if (obj != null) {
                    Double pi = (Double) obj;
                    map.put("piF", pi);
                    if (pi.isNaN())
                        map.put("noMatchedF", true);
                }
            }

            if (piOfM != null && piOfM.size() > 0) {
                Object obj = piOfM.get(markerName);

                if (obj != null) {
                    Double pi = (Double) obj;
                    map.put("piM", pi);
                    if (pi.isNaN())
                        map.put("noMatchedM", true);
                }
            }
            piOfMarkers.add(map);
        }

        return piOfMarkers;
    }
    /**
     * 获取亲缘比对总似然比、比中个数
     * @return
     */
    private Map<String, Object> getRelativeTotalPiAndMatchCount(TotalRelativeMatchResult totalRelativeMatchResult) {
        CaseParentageMatchResult result = totalRelativeMatchResult.getResult();
        CaseParentageMatchResult mResult = totalRelativeMatchResult.getMResult();
        CaseParentageMatchResult fResult = totalRelativeMatchResult.getFResult();
        Map<String, Object> matchCountAndTotalPi = new HashMap<String, Object>();
        matchCountAndTotalPi.put("matchCount", result.getMatchCount());
        matchCountAndTotalPi.put("fMatchCount", fResult.getMatchCount());
        matchCountAndTotalPi.put("mMatchCount", mResult.getMatchCount());
        matchCountAndTotalPi.put("totalPi", result.getTotalPossibility());
        matchCountAndTotalPi.put("fTotalPi", fResult.getTotalPossibility());
        matchCountAndTotalPi.put("mTotalPi", mResult.getTotalPossibility());
        return matchCountAndTotalPi;

    }

    /**
     * 获取同一比对每个marker的似然比 和 allele值
     * @param SampleA
     * @param SampleB
     * @param SampleC
     * @param markerRates
     * @return
     */
    private List<Map<String, Object>> getSameMatchMarkerRateList(Map<String, Object> SampleA, Map<String, Object> SampleB, Map<String, Object> SampleC, Map<String, Object> markerRates) {
        List<Map<String, Object>> piOfMarkers = new ArrayList<Map<String,Object>>();
        for(String markerName : markerRates.keySet())
        {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("markerName", markerName);
            map.put("alleleA", SampleA == null ? "" : SampleA.get(markerName));
            map.put("alleleB", SampleB == null ?"": SampleB.get(markerName));
            if(SampleC != null)
                map.put("alleleC", SampleC.get(markerName));
            map.put("pi", markerRates.get(markerName));
            piOfMarkers.add(map);
        }
        return piOfMarkers;
    }

    private String getSampelName(String barcode, Map<String, Object> matchResult) {
        String sampleAId = matchResult.get("sampleAId").toString();
        String sampleBId = matchResult.get("sampleBId").toString();
        String sampleCId = matchResult.get("sampleCId")==null?null:matchResult.get("sampleCId").toString();
        String result = "";
        if(sampleAId.equals(barcode))
            result =  matchResult.get("sampleAName") == null?"":matchResult.get("sampleAName").toString();

        if(sampleBId.equals(barcode))
            result =  matchResult.get("sampleBName") == null?"":matchResult.get("sampleBName").toString();

        if(sampleCId.equals(barcode))
            result =  matchResult.get("sampleCName") == null?"":matchResult.get("sampleCName").toString();

        return result;
    }

    /**
     * 查询跨区亲缘比对结果
     * @param request
     * @param matchResult
     * @param pageInfo
     * @return
     */
    @RequestMapping("/querySameAreaRelationCompareResultList")
    public String queryAreaRelationCompareResultList(HttpServletRequest request, MatchRelativeResultVo matchResult, PageInfo pageInfo,
                                                       String sameCount,String compareStatus,@RequestBody String afferentJson){
        ModelAndView view = new ModelAndView();
        LoaUserInfo operateUser = new LoaUserInfo();
        String result = null;
        if(StringUtils.isNoneBlank(sameCount)){
            matchResult.getEntity().setSameCount(Integer.parseInt(sameCount));
        }

        if(StringUtils.isNoneBlank(compareStatus) ){
            matchResult.getEntity().setCompareStatus(compareStatus);
        }

        pageInfo.setEvePageRecordCnt1(5);
        JSONObject jsonObject = JSON.parseObject(afferentJson);
        operateUser.setOrgId(jsonObject.get("orgId").toString());
        pageInfo.setPage(Integer.parseInt(jsonObject.get("page").toString()));
        String JSONStr = jsonObject.getString("matchResult").toString();
        matchResult = JSON.parseObject(JSONStr, MatchRelativeResultVo.class);


        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        //LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if(operateUser == null){
            view.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return  result;
        }
        try {

            //查询受理单位
            List<OrgInfo> acceptOrgList = orgInfoService.selectAcceptOrgList();
            //获取当前用户的id
            String userOrgId = operateUser.getOrgId();
            //将当前用户org_id设置进query
            if(StringUtils.isNotBlank(userOrgId)
                    && (userOrgId.contains(Constants.FORENSIC_CENTER_SHORT_ORG_ID)
                    || userOrgId.contains(Constants.FORENSIC_CENTER_SHORT_ORG_ID_BAK))){
                userOrgId = Constants.FORENSIC_CENTER_ORG_ID;
            }

            if (StringUtils.isNoneBlank(userOrgId)){
                matchResult.setUserOrdId(userOrgId);
                matchResult.setAcceptOrgId(userOrgId);
            }

            //查询委托单位（分局）
            String orgParentsId = Constants.TOP_ORG_ID;
            List<OrgInfo> orgInfoList = orgInfoService.selectDelegateByParentsId(orgParentsId);
            view.addObject("orgInfoList", orgInfoList);

            //入库类型
            DictItem dictItem = new DictItem();
            dictItem.setDictTypeCode(Constants.INSTORED_TYPE);
            List<DictItem> dictSampleEntryTypeList = DictUtil.getDictList(dictItem);
            view.addObject("dictSampleEntryTypeList", dictSampleEntryTypeList);

            //比对类型
            dictItem.setDictTypeCode(Constants.COMPARISON_TYPE);
            List<DictItem> comparisonTypeList = DictUtil.getDictList(dictItem);
            view.addObject("comparisonTypeList", comparisonTypeList);

            //比对类别
            dictItem.setDictTypeCode(Constants.COMPARISON_CATEGORY);
            List<DictItem> comparisonCategoryList = DictUtil.getDictList(dictItem);
            view.addObject("comparisonCategoryList", comparisonCategoryList);

            //状态
            List<DictItem> compareStatusList1 = new LinkedList<>();
            dictItem.setDictTypeCode(Constants.COMPARE_STATUS);
            List<DictItem> compareStatusList = DictUtil.getDictList(dictItem);
            for(DictItem dictItem1:compareStatusList){
                if(("0").equals(dictItem1.getDictCode())){
                    compareStatusList1.add(dictItem1);
                }else if(("2").equals(dictItem1.getDictCode())){
                    compareStatusList1.add(dictItem1);
                }else if(("3").equals(dictItem1.getDictCode())){
                    compareStatusList1.add(dictItem1);
                }
            }
            view.addObject("compareStatusList", compareStatusList1);

            matchResult = initMatchRelativeResult(matchResult);

            String instoredType = null;

            if (StringUtils.isNotBlank(matchResult.getInstoredType())) {
                Integer instore = TransferHelper.convertToInstoredType(matchResult.getInstoredType());
                if (instore != null) {
                    instoredType = String.valueOf(instore);
                }
            }

            //转换比对结果类别
            String groupType = TransferHelper.convertToComparisonCategory(Constants.COMPARISON_CATEGORY_01);

            String matchTimeStart = null;
            if (matchResult.getCreateStartDatetime() != null) {
                matchTimeStart = DateUtils.dateToString(matchResult.getCreateStartDatetime(), DateUtils.HOUR_MINUTES_SECOND);
            }
            String matchTimeEnd = null;
            if (matchResult.getCreateEndDatetime() != null) {
                matchTimeEnd  = DateUtils.dateToString(matchResult.getCreateEndDatetime(), DateUtils.HOUR_MINUTES_SECOND);
            }

            String delegateOrgCode = null;
            if (StringUtils.isNotBlank(userOrgId) && userOrgId.length() > 6) {
                delegateOrgCode = userOrgId.substring(0, 6);
            }

            String caseId = "";
            if (StringUtils.isNotBlank(matchResult.getCaseNo())) {
                LimsCaseInfoVo limsCaseInfoVo = new LimsCaseInfoVo();
                limsCaseInfoVo.getEntity().setCaseNo(matchResult.getCaseNo());
                List<LimsCaseInfoVo> caseInfoVoList = limsCaseInfoService.selectVOCaseInfoList(limsCaseInfoVo);
                if (ListUtils.isNotNullAndEmptyList(caseInfoVoList)) {
                    for (LimsCaseInfoVo lciVo : caseInfoVoList) {
                        caseId += "'" + lciVo.getEntity().getCaseId() + "'" + ",";
                    }
                    if(caseId.length() > 0) {
                        caseId = caseId.substring(0,caseId.length() -1);
                    }
                }
            }

            List<LibMatchResultGroupDomain> matchResultGroupList = wzService.libMatchRecordGroupList(matchResult.getSampleNo(),
                    matchResult.getSampleName(), matchResult.getCaseName(), instoredType,
                    Constants.MATCH_TYPE_0, matchResult.getEntity().getCompareStatus(),null, groupType, null,matchTimeStart,matchTimeEnd,
                    delegateOrgCode, caseId, null, pageInfo.getPage(), pageInfo.getEvePageRecordCnt1());

            int recordCount = 0;
            if (ListUtils.isNotNullAndEmptyList(matchResultGroupList)) {
                logger.info("串并案页面查询，查询结果过滤开始...");
                for(int i=0;i<matchResultGroupList.size();i++){
                    LibMatchResultGroupDomain libMatchResultGroupDomain = (LibMatchResultGroupDomain)matchResultGroupList.get(i);
                    if (libMatchResultGroupDomain != null) {
                        int levelflag = 0;
                        List<Map<String,String>> sampleList= (List<Map<String,String>>) libMatchResultGroupDomain.getSampleList();
                        List<Map<String, Object>> caseSnList = new ArrayList<Map<String, Object>>();
                        for (Map<String,String> samplemap : sampleList) {
                            Map<String,String> map = (Map<String,String>)samplemap;
                            samplemap.getClass().cast(map);
                            String instoreType = null;
                            for (DictItem di: dictSampleEntryTypeList) {
                                if(samplemap.get("sampletype")!=null){
                                    Integer dictCode = TransferHelper.convertToInstoredType(di.getDictCode());
                                    if (dictCode != null) {
                                        instoreType = String.valueOf(dictCode);
                                    }
                                    if(StringUtils.isNotBlank(instoreType)){
                                        if (instoreType.equals(samplemap.get("sampletype").toString())) {
                                            samplemap.put("sampleTypeName", di.getDictName());
                                            break;
                                        }
                                    }
                                }
                            }

                            recordCount = libMatchResultGroupDomain.getRowCnt();

                            if (samplemap.get("caseid") != null) {
                                Map<String, Object> caseSnMap = new HashMap<String, Object>();
                                CaseInfo caseInfo = queryDataService.getCaseInfoByCaseId(samplemap.get("caseid"));
                                if (caseInfo != null) {
                                    String caseSn = String.valueOf(caseInfo.getCaseSn().getValue());
                                    if (StringUtils.isNotBlank(caseSn)) {
                                        caseSnMap.put("caseId", samplemap.get("caseid"));
                                        caseSnMap.put("caseSn", caseSn);
                                        if(!caseSnList.contains(caseSnMap)) {
                                            caseSnList.add(caseSnMap);
                                        }
                                        samplemap.put("caseHandleType", String.valueOf(caseInfo.getCaseHandleType().getValue()));
                                        samplemap.put("caseBaseId", String.valueOf(caseInfo.getCaseBaseId().getValue()));
                                        samplemap.put("isCaseAppend", String.valueOf(caseInfo.getHasAppend().intValue()));
                                        String orgName = String.valueOf(caseInfo.getDelegateOrgName().getValue());
                                        if (StringUtils.isBlank(orgName)) {
                                            LimsConsignmentInfo limsConsignmentInfo = limsConsignmentInfoService.selectByCaseNo(caseSn.trim());
                                            if (limsConsignmentInfo != null) {
                                                orgName = limsConsignmentInfo.getDelegateOrgName();
                                            }
                                        }
                                        samplemap.put("delegateOrgName", orgName);
                                        samplemap.put("delegateOrgCode", String.valueOf(caseInfo.getDelegateOrgCode().getValue()));
                                    }
                                }
                            }
                        }
                        libMatchResultGroupDomain.setCaseSnList(caseSnList);
                    }
                }
                logger.info("串并案页面查询结束，返回！");
            }

            JSONObject obj = new JSONObject();
            obj.put("acceptOrgList",acceptOrgList);
            obj.put("userOrgId",userOrgId);
            obj.put("matchResultGroupList",matchResultGroupList);
            obj.put("pageInfo",pageInfo.updatePageInfo(recordCount));
            obj.put("comparisonCategory",matchResult.getComparisonCategory());
            obj.put("matchResult",matchResult);
            result = obj.toString();
        }catch (Exception e){
            logger.info("查询跨区亲缘比对结果:"+e);
        }
        return result;
    }



    /**
     * 查看跨区亲缘结果详情
     * @param matchResult
     * @param pageInfo
     * @return
     */
    @RequestMapping("/querySameAreaRelationCompareResultDetail")
    public String queryAreaRelationCompareResultDetail(MatchRelativeResultVo matchResult, PageInfo pageInfo,@RequestBody String afferentJson) {
        ModelAndView view = new ModelAndView();

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo loaUserInfo = new LoaUserInfo();
        String result = null;

        pageInfo.setEvePageRecordCnt1(5);
        JSONObject jsonObject = JSON.parseObject(afferentJson);
        loaUserInfo.setOrgId(jsonObject.get("orgId").toString());
        pageInfo.setPage(Integer.parseInt(jsonObject.get("page").toString()));
        String JSONStr = jsonObject.getString("matchResult").toString();
        matchResult = JSON.parseObject(JSONStr, MatchRelativeResultVo.class);
        //LoaUserInfo loaUserInfo = (LoaUserInfo) subject.getPrincipal();
        if(loaUserInfo == null){
            view.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return  result;
        }
        try {
            List<AmPersonalInfo> amPersonalInfoList = amPersonalInfoService.queryAmPersonalInfoListOrgId(loaUserInfo.getOrgId());

            //入库类型
            DictItem dictItem = new DictItem();
            dictItem.setDictTypeCode(Constants.INSTORED_TYPE);
            List<DictItem> dictSampleEntryTypeList = DictUtil.getDictList(dictItem);
            view.addObject("dictSampleEntryTypeList", dictSampleEntryTypeList);

            //状态
            List<DictItem> compareStatusList1 = new LinkedList<>();
            dictItem.setDictTypeCode(Constants.COMPARE_STATUS);
            List<DictItem> compareStatusList = DictUtil.getDictList(dictItem);
            for(DictItem dictItem1:compareStatusList){
                if(("0").equals(dictItem1.getDictCode())){
                    compareStatusList1.add(dictItem1);
                }else if(("2").equals(dictItem1.getDictCode())){
                    compareStatusList1.add(dictItem1);
                }else if(("3").equals(dictItem1.getDictCode())){
                    compareStatusList1.add(dictItem1);
                }
            }
            view.addObject("compareStatusList", compareStatusList1);

            matchResult = initMatchRelativeResult(matchResult);

            String instoredType = null;

            if (StringUtils.isNotBlank(matchResult.getInstoredType())) {
                Integer instore = TransferHelper.convertToInstoredType(matchResult.getInstoredType());
                if (instore != null) {
                    instoredType = String.valueOf(instore);
                }
            }

            List<Map<String, Object>> matchResultList = wzService.libMatchRecordList(matchResult.getEntity().getCompareStatus(),
                    matchResult.getSampleNo(), null, matchResult.getCaseName(), matchResult.getSampleName(), matchResult.getOperator(),
                    instoredType, matchResult.getGroupId(),pageInfo.getPage(), pageInfo.getEvePageRecordCnt());

            int recordCount = 0;
            if (ListUtils.isNotNullAndEmptyList(matchResultList)) {
                for (Map<String, Object> map : matchResultList) {
                    String instoreType = null;
                    for (DictItem di : dictSampleEntryTypeList) {
                        if(map.get("targetSampleType")!=null){
                            Integer dictCode = TransferHelper.convertToInstoredType(di.getDictCode());
                            if (dictCode != null) {
                                instoreType = String.valueOf(dictCode);
                            }
                            if(StringUtils.isNotBlank(instoreType)){
                                if (instoreType.equals(map.get("targetSampleType").toString())) {
                                    map.put("targetSampleTypeName", di.getDictName());
                                    break;
                                }
                            }
                        }

                    }
                    if(map.get("recordCount")!=null) {
                        recordCount = Integer.parseInt(map.get("recordCount").toString());
                    }
                }
            }
            JSONObject obj = new JSONObject();
            obj.put("amPersonalInfoList",amPersonalInfoList);
            obj.put("matchResultList",matchResultList);
            obj.put("matchResult",matchResult);
            obj.put("pageInfo",pageInfo.updatePageInfo(recordCount));
            result = obj.toString();
        }catch (Exception e) {
            logger.info("查看跨区亲缘比对结果详细报错:"+e);
        }

        return result;
    }



    /**
     * 查看跨区亲缘本地库比对结果详细单
     * @param request
     * @param matchResultVo
     * @param pageInfo
     * @return
     */
    @RequestMapping("/querySameAreaRelationCompareCondition")
    public String queryAreaRelationCompareCondition(HttpServletRequest request, MatchRelativeResultVo matchResultVo, PageInfo pageInfo,@RequestBody String afferentJson) {
        ModelAndView modelAndView = new ModelAndView();

        String result = null;
        pageInfo.setEvePageRecordCnt1(5);
        JSONObject jsonObject = JSON.parseObject(afferentJson);
        pageInfo.setPage(Integer.parseInt(jsonObject.get("page").toString()));
        String JSONStr = jsonObject.getString("matchResultVo").toString();
        matchResultVo = JSON.parseObject(JSONStr, MatchRelativeResultVo.class);

        matchResultVo = initMatchRelativeResult(matchResultVo);

        String instoredType = null;

        if (StringUtils.isNotBlank(matchResultVo.getInstoredType())) {
            Integer instore = TransferHelper.convertToInstoredType(matchResultVo.getInstoredType());
            if (instore != null) {
                instoredType = String.valueOf(instore);
            }
        }

        List<Map<String, Object>> matchResultList = wzService.libMatchRecordList(matchResultVo.getEntity().getCompareStatus(),
                matchResultVo.getSampleNo(), matchResultVo.getMatchId(), matchResultVo.getCaseName(), matchResultVo.getSampleName(),
                matchResultVo.getOperator(), instoredType, matchResultVo.getGroupId(),pageInfo.getPage(), pageInfo.getEvePageRecordCnt());

        modelAndView = getResultModelAndView(matchResultList, matchResultVo.getMatchId(), matchResultVo.getPanel());

        JSONObject obj = new JSONObject();
        obj.put("modelAndView",modelAndView);
        obj.put("matchResultVo",matchResultVo);
        obj.put("matchType","relative");
        result = obj.toString();

       /* modelAndView.addObject("matchResultVo", matchResultVo);
        modelAndView.addObject("matchType", "relative");
        modelAndView.setViewName("query/viewAreaCompareCondition");*/
        return result;
    }

    /**
     * 导出Excel
     * @return
     */
    @RequestMapping("/SameExportListExcel")
    public ModelAndView exportListExcel(HttpServletRequest request,HttpServletResponse response,String groupIdArr,MatchSameResultVo matchResult,PageInfo pageInfo){

        ModelAndView view = new ModelAndView();
        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo loaUserInfo = (LoaUserInfo) subject.getPrincipal();
        if(loaUserInfo == null){
            view.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return  view;
        }
        pageInfo.setEvePageRecordCnt(100000);
        try {
            String fileName="同一比对结果";

            //查询受理单位
            List<OrgInfo> acceptOrgList = orgInfoService.selectAcceptOrgList();
            //获取当前用户的id
            String userOrgId = loaUserInfo.getOrgId();
            //将当前用户org_id设置进query
            if(StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")){
                userOrgId = "110230000000";
            }
            matchResult.setUserOrdId(userOrgId);
            matchResult.setAcceptOrgId(userOrgId);

            String orgId = StringUtils.isBlank(loaUserInfo.getSubOrgId()) ? loaUserInfo.getOrgId() : loaUserInfo.getSubOrgId();

            //查询委托单位（分局）
            String orgParentsId = "110000000000";
            List<OrgInfo> orgInfoList = orgInfoService.selectDelegateByParentsId(orgParentsId);
            view.addObject("orgInfoList", orgInfoList);

            //入库类型
            DictItem dictItem = new DictItem();
            dictItem.setDictTypeCode(Constants.INSTORED_TYPE);
            List<DictItem> dictSampleEntryTypeList = DictUtil.getDictList(dictItem);
            view.addObject("dictSampleEntryTypeList", dictSampleEntryTypeList);

            //比对类型
            dictItem.setDictTypeCode(Constants.COMPARISON_TYPE);
            List<DictItem> comparisonTypeList = DictUtil.getDictList(dictItem);
            view.addObject("comparisonTypeList", comparisonTypeList);

            //比对类别
            List<DictItem> comparisonCategoryList1 = new LinkedList<>();
            dictItem.setDictTypeCode(Constants.COMPARISON_CATEGORY);
            List<DictItem> comparisonCategoryList = DictUtil.getDictList(dictItem);
            for(DictItem dictItem1:comparisonCategoryList){
                if(("05").equals(dictItem1.getDictCode())){
                    comparisonCategoryList1.add(dictItem1);
                }else if(("03").equals(dictItem1.getDictCode())){
                    comparisonCategoryList1.add(dictItem1);
                }else if(("04").equals(dictItem1.getDictCode())){
                    comparisonCategoryList1.add(dictItem1);
                }else if(("02").equals(dictItem1.getDictCode())){
                    comparisonCategoryList1.add(dictItem1);
                }
            }
            view.addObject("comparisonCategoryList", comparisonCategoryList1);

            //状态
            List<DictItem> compareStatusList1 = new LinkedList<>();
            dictItem.setDictTypeCode(Constants.COMPARE_STATUS);
            List<DictItem> compareStatusList = DictUtil.getDictList(dictItem);
            for(DictItem dictItem1:compareStatusList){
                if(("0").equals(dictItem1.getDictCode())){
                    compareStatusList1.add(dictItem1);
                }else if(("2").equals(dictItem1.getDictCode())){
                    compareStatusList1.add(dictItem1);
                }else if(("3").equals(dictItem1.getDictCode())){
                    compareStatusList1.add(dictItem1);
                }
            }
            view.addObject("compareStatusList", compareStatusList1);

            MatchSameResultVo matchResult2 = new MatchSameResultVo();
            //matchResult2.setDelegateOrgCode(orgId);
            matchResult2.setUserOrdId(userOrgId);
            //查询条件
            if (StringUtils.isNotBlank(matchResult.getCaseName())) {
                matchResult2.setCaseName(matchResult.getCaseName().trim());
            }else{
                matchResult2.setCaseName(null);
            }
            if (StringUtils.isNotBlank(matchResult.getSampleName())) {
                matchResult2.setSampleName(matchResult.getSampleName().trim());
            }else{
                matchResult2.setSampleName(null);
            }
            if (StringUtils.isNotBlank(matchResult.getSampleNo())) {
                matchResult2.setSampleNo(matchResult.getSampleNo().trim());
            }else{
                matchResult2.setSampleNo(null);
            }
            if(StringUtils.isNotBlank(matchResult.getInstoredType())){
                matchResult2.setInstoredType(matchResult.getInstoredType().trim());
            }else{
                matchResult2.setInstoredType(null);
            }

            if(StringUtils.isNotBlank(matchResult.getComparisonCategory())){
                if(("05").equals(matchResult.getComparisonCategory())){
                    matchResult2.setComparisonCategory(null);
                }else{
                    matchResult2.setComparisonCategory(matchResult.getComparisonCategory().trim());
                }
            }else{
                matchResult2.setComparisonCategory("02");
            }

            if(StringUtils.isNotBlank(matchResult.getDelegateOrgName())){
                matchResult2.setDelegateOrgName(matchResult.getDelegateOrgName().trim());
            }else{
                matchResult2.setDelegateOrgName(null);
            }

            if(StringUtils.isNotBlank(matchResult.getCaseNo())){
                matchResult2.setCaseNo(matchResult.getCaseNo().trim());
            }else{
                matchResult2.setCaseNo(null);
            }

            if(null != matchResult.getCreateStartDatetime()){
                matchResult2.setCreateStartDatetime(matchResult.getCreateStartDatetime());
            }else{
                matchResult2.setCreateStartDatetime(null);
            }

            if(null != matchResult.getCreateEndDatetime()){
                matchResult2.setCreateEndDatetime(matchResult.getCreateEndDatetime());
            }else{
                matchResult2.setCreateEndDatetime(null);
            }

            if(null != matchResult.getEntity().getSameCount()){
                matchResult2.getEntity().setSameCount(matchResult.getEntity().getSameCount());
            }else{
                matchResult2.getEntity().setSameCount(null);
            }

            if(StringUtils.isNotBlank(matchResult.getEntity().getCompareStatus())){
                matchResult2.getEntity().setCompareStatus(matchResult.getEntity().getCompareStatus());
            }else{
                matchResult2.getEntity().setCompareStatus("0");
            }

            Map<String, List> result = new HashMap<>();
            //查询所有组id
            List<MatchSameResultVo> matchCaseResultList = matchSameResultService.selectGroupAll(matchResult2, pageInfo);
            int totalCnt = matchSameResultService.selectListCount(matchResult2);
            for (MatchSameResultVo matchSameResultVo : matchCaseResultList) {
                MatchSameResultVo matchResult1 = new MatchSameResultVo();
                if(StringUtils.isNotBlank(matchSameResultVo.getEntity().getGroupId())){
                    matchResult1.getEntity().setGroupId(matchSameResultVo.getEntity().getGroupId());
                    //条件查询
                    if (StringUtils.isNotBlank(matchResult.getCaseName())) {
                        matchResult1.setCaseName(matchResult.getCaseName().trim());
                    }else{
                        matchResult1.setCaseName(null);
                    }
                    if (StringUtils.isNotBlank(matchResult.getSampleName())) {
                        matchResult1.setSampleName(matchResult.getSampleName().trim());
                    }else{
                        matchResult1.setSampleName(null);
                    }
                    if (StringUtils.isNotBlank(matchResult.getSampleNo())) {
                        matchResult1.setSampleNo(matchResult.getSampleNo().trim());
                    }else{
                        matchResult1.setSampleNo(null);
                    }
                    if(StringUtils.isNotBlank(matchResult.getInstoredType())){
                        matchResult1.setInstoredType(matchResult.getInstoredType().trim());
                    }else{
                        matchResult1.setInstoredType(null);
                    }

                    if(StringUtils.isNotBlank(matchResult.getComparisonCategory())){
                        if(("05").equals(matchResult.getComparisonCategory())){
                            matchResult1.setComparisonCategory(null);
                        }else{
                            matchResult1.setComparisonCategory(matchResult.getComparisonCategory().trim());
                        }
                    }else{
                        matchResult1.setComparisonCategory("02");
                    }

                    if(StringUtils.isNotBlank(matchResult.getDelegateOrgName())){
                        matchResult1.setDelegateOrgName(matchResult.getDelegateOrgName().trim());
                    }else{
                        matchResult1.setDelegateOrgName(null);
                    }

                    if(StringUtils.isNotBlank(matchResult.getCaseNo())){
                        matchResult1.setCaseNo(matchResult.getCaseNo().trim());
                    }else{
                        matchResult1.setCaseNo(null);
                    }

                    if(StringUtils.isNotBlank(matchResult.getEntity().getCompareStatus())){
                        matchResult1.getEntity().setCompareStatus(matchResult.getEntity().getCompareStatus());
                    }else{
                        matchResult1.getEntity().setCompareStatus("0");
                    }
                    //根据组id查询比对结果
                    List<MatchSameResultVo> matchResultList = matchSameResultService.selectListByGroupId(matchResult1);

                    //去重
                    List<MatchSameResultVo> collect = matchResultList.stream().filter(distinctByKey(MatchSameResultVo::getSampleNo)).collect(Collectors.toList());

                    //循环遍历list获取入库样本类型
                    for (int i = 0; i < collect.size(); i++) {
                        for (DictItem dictSampleEntryType : dictSampleEntryTypeList) {
                            if (collect.get(i).getInstoredType() != null) {
                                if (collect.get(i).getInstoredType().equals(dictSampleEntryType.getDictCode())) {
                                    collect.get(i).setInstoredType(dictSampleEntryType.getDictName());
                                }
                            }
                        }
                    }
                    result.put(matchSameResultVo.getEntity().getGroupId(), collect);
                }
            }



            ArrayList<MatchSameResultVo> entryList = new ArrayList<MatchSameResultVo>();
            for (Map.Entry<String, List> entry : result.entrySet()) {
                entryList.addAll(entry.getValue());
            }
            List<Map<String,Object>> list=createExcelRecord(entryList);
            String columnNames[]={"案件名称","案件破获状态","样本条码号","检材名称","入库样本类型","比对类型","所属分局"};//列名
            String keys[]    =     {"caseName","1","sampleNo","sampleName","instoredType","2","delegateOrgName"};//map中的key
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            try {
                ExcelUtil.createWorkBook(list,keys,columnNames).write(os);
            } catch (IOException e) {
                e.printStackTrace();
            }
            byte[] content = os.toByteArray();
            InputStream is = new ByteArrayInputStream(content);
            // 设置response参数，可以打开下载页面
            response.reset();
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
            ServletOutputStream out = response.getOutputStream();
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            try {
                bis = new BufferedInputStream(is);
                bos = new BufferedOutputStream(out);
                byte[] buff = new byte[2048];
                int bytesRead;
                // Simple read/write loop.
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }
            } catch (final IOException e) {
                throw e;
            } finally {
                if (bis != null)
                    bis.close();
                if (bos != null)
                    bos.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return view;
    }




    /**
     * 导出Excel
     * @return
     */
    @RequestMapping("/SameExportQyListExcel")
    public ModelAndView SameExportQyListExcel(HttpServletRequest request,HttpServletResponse response,MatchRelativeResultVo matchResult,PageInfo pageInfo){

        ModelAndView view = new ModelAndView();
        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if(operateUser == null){
            view.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return  view;
        }
        pageInfo.setEvePageRecordCnt(100000);
        try {
            //查询受理单位
            List<OrgInfo> acceptOrgList = orgInfoService.selectAcceptOrgList();
            //获取当前用户的id
            String userOrgId = operateUser.getOrgId();
            //将当前用户org_id设置进query
            if(StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")){
                userOrgId = "110230000000";
            }
            matchResult.setUserOrdId(userOrgId);
            matchResult.setAcceptOrgId(userOrgId);

            String orgId = StringUtils.isBlank(operateUser.getSubOrgId()) ? operateUser.getOrgId() : operateUser.getSubOrgId();

            //查询委托单位（分局）
            String orgParentsId = "110000000000";
            List<OrgInfo> orgInfoList = orgInfoService.selectDelegateByParentsId(orgParentsId);
            view.addObject("orgInfoList", orgInfoList);

            //入库类型
            DictItem dictItem = new DictItem();
            dictItem.setDictTypeCode(Constants.INSTORED_TYPE);
            List<DictItem> dictSampleEntryTypeList = DictUtil.getDictList(dictItem);
            view.addObject("dictSampleEntryTypeList", dictSampleEntryTypeList);

            //比对类型
            dictItem.setDictTypeCode(Constants.COMPARISON_TYPE);
            List<DictItem> comparisonTypeList = DictUtil.getDictList(dictItem);
            view.addObject("comparisonTypeList", comparisonTypeList);

            //比对类别
            dictItem.setDictTypeCode(Constants.COMPARISON_CATEGORY);
            List<DictItem> comparisonCategoryList = DictUtil.getDictList(dictItem);
            view.addObject("comparisonCategoryList", comparisonCategoryList);

            //状态
            List<DictItem> compareStatusList1 = new LinkedList<>();
            dictItem.setDictTypeCode(Constants.COMPARE_STATUS);
            List<DictItem> compareStatusList = DictUtil.getDictList(dictItem);
            for(DictItem dictItem1:compareStatusList){
                if(("0").equals(dictItem1.getDictCode())){
                    compareStatusList1.add(dictItem1);
                }else if(("2").equals(dictItem1.getDictCode())){
                    compareStatusList1.add(dictItem1);
                }else if(("3").equals(dictItem1.getDictCode())){
                    compareStatusList1.add(dictItem1);
                }
            }
            view.addObject("compareStatusList", compareStatusList1);

            MatchRelativeResultVo matchRelativeResultVo = new MatchRelativeResultVo();
            //matchRelativeResultVo.setDelegateOrgCode(orgId);
            matchRelativeResultVo.setUserOrdId(userOrgId);
            //条件查询
            if (StringUtils.isNotBlank(matchResult.getCaseName())) {
                matchRelativeResultVo.setCaseName(matchResult.getCaseName().trim());
            }else{
                matchRelativeResultVo.setCaseName(null);
            }
            if (StringUtils.isNotBlank(matchResult.getSampleName())) {
                matchRelativeResultVo.setSampleName(matchResult.getSampleName().trim());
            }else{
                matchRelativeResultVo.setSampleName(null);
            }
            if (StringUtils.isNotBlank(matchResult.getSampleNo())) {
                matchRelativeResultVo.setSampleNo(matchResult.getSampleNo().trim());
            }else{
                matchRelativeResultVo.setSampleNo(null);
            }
            if(StringUtils.isNotBlank(matchResult.getInstoredType())){
                matchRelativeResultVo.setInstoredType(matchResult.getInstoredType().trim());
            }else{
                matchRelativeResultVo.setInstoredType(null);
            }
            if(StringUtils.isNotBlank(matchResult.getCaseNo())){
                matchRelativeResultVo.setCaseNo(matchResult.getCaseNo().trim());
            }else{
                matchRelativeResultVo.setCaseNo(null);
            }

            if(null != matchResult.getCreateStartDatetime()){
                matchRelativeResultVo.setCreateStartDatetime(matchResult.getCreateStartDatetime());
            }else{
                matchRelativeResultVo.setCreateStartDatetime(null);
            }

            if(null != matchResult.getCreateEndDatetime()){
                matchRelativeResultVo.setCreateEndDatetime(matchResult.getCreateEndDatetime());
            }else{
                matchRelativeResultVo.setCreateEndDatetime(null);
            }

            if(null != matchResult.getEntity().getSameCount()){
                matchRelativeResultVo.getEntity().setSameCount(matchResult.getEntity().getSameCount());
            }else{
                matchRelativeResultVo.getEntity().setSameCount(null);
            }

            if(StringUtils.isNotBlank(matchResult.getEntity().getCompareStatus())){
                matchRelativeResultVo.getEntity().setCompareStatus(matchResult.getEntity().getCompareStatus());
            }else{
                matchRelativeResultVo.getEntity().setCompareStatus("0");
            }

            //查询比对结果
            List<MatchRelativeResultVo> matchRelativeResultList = matchRelativeResultService.selectMatchRelativeResultList(matchRelativeResultVo, pageInfo);
           /* for(MatchRelativeResultVo matchRelativeResultVo1:matchRelativeResultList) {
                //查询类别
                List<DictItem> dictItemList = dictItemService.selectListByDictTypeCode("COMPARISON_CATEGORY");
                for(DictItem dictItem1:dictItemList){
                    if(StringUtils.isNotBlank(matchRelativeResultVo1.getEntity().getMatchType())){
                        if((matchRelativeResultVo1.getEntity().getMatchType()).equals(dictItem1.getDictCode())){
                            matchRelativeResultVo1.getEntity().setMatchType(dictItem1.getDictName());
                        }
                    }
                }
            }*/
            String fileName="亲缘比对结果";

            List<Map<String,Object>> list=createQyExcelRecord(matchRelativeResultList);
            String columnNames[]={"案件名称","案件破获状态","样本条码号","检材名称","入库样本类型","比对类型","所属分局"};//列名
            String keys[]    =     {"caseName","1","sampleNo","sampleName","2","instoredType","delegateOrgName"};//map中的key
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            try {
                ExcelUtil.createWorkBook(list,keys,columnNames).write(os);
            } catch (IOException e) {
                e.printStackTrace();
            }
            byte[] content = os.toByteArray();
            InputStream is = new ByteArrayInputStream(content);
            // 设置response参数，可以打开下载页面
            response.reset();
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
            ServletOutputStream out = response.getOutputStream();
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            try {
                bis = new BufferedInputStream(is);
                bos = new BufferedOutputStream(out);
                byte[] buff = new byte[2048];
                int bytesRead;
                // Simple read/write loop.
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }
            } catch (final IOException e) {
                throw e;
            } finally {
                if (bis != null)
                    bis.close();
                if (bos != null)
                    bos.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return view;
    }


    private List<Map<String, Object>> createQyExcelRecord(List<MatchRelativeResultVo> list) {
        List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sheetName", "sheet1");
        listmap.add(map);//caseName","1","sampleNo ","sampleName","instoredType","1","delegateOrgName"
        for (int j = 0; j < list.size(); j++) {
            MatchRelativeResultVo vote = list.get(j);
            Map<String, Object> mapValue = new HashMap<String, Object>();
            mapValue.put("caseName", vote.getEntity().getCaseaName());
            mapValue.put("1", "未破获");
            mapValue.put("sampleNo", vote.getEntity().getSampleaNo());
            mapValue.put("sampleName", vote.getEntity().getSampleaName());
            if (null != vote.getInstoredaType() ){
            switch ( Integer.parseInt(vote.getInstoredaType())) {
                case 1:
                    mapValue.put("2","现场物证");break;
                case 2:
                    mapValue.put("2","混合物证");break;
                case 3:
                    mapValue.put("2","违法犯罪人员");break;
                case 4:
                    mapValue.put("2","嫌疑人");break;
                case 5:
                    mapValue.put("2","受害人");break;
                case 6:
                    mapValue.put("2","失踪人口");break;
                case 7:
                    mapValue.put("2","无名尸");break;
                case 8:
                    mapValue.put("2","嫌疑人亲属");break;
                case 9:
                    mapValue.put("2","受害人亲属");break;
                case 10:
                    mapValue.put("2","失踪人口亲属");break;
                case 11:
                    mapValue.put("2","基础库");break;
                case 12:
                    mapValue.put("2","YSTR");break;
                default:
                    mapValue.put("2","");break;
            }
            }else{
                mapValue.put("2","");
            }
            mapValue.put("instoredType", "亲缘比对");
            mapValue.put("delegateOrgName", vote.getDelegateaOrgName());
            listmap.add(mapValue);
            Map<String, Object> mapValueb = new HashMap<String, Object>();
            mapValueb.put("caseName", vote.getEntity().getCaseaName());
            mapValueb.put("1", "未破获");
            mapValueb.put("sampleNo", vote.getEntity().getSamplebNo());
            mapValueb.put("sampleName", vote.getEntity().getSamplebName());
            if (null != vote.getInstoredaType() ){
                switch ( Integer.parseInt(vote.getInstoredaType())) {
                    case 1:
                        mapValueb.put("2","现场物证");break;
                    case 2:
                        mapValueb.put("2","混合物证");break;
                    case 3:
                        mapValueb.put("2","违法犯罪人员");break;
                    case 4:
                        mapValueb.put("2","嫌疑人");break;
                    case 5:
                        mapValueb.put("2","受害人");break;
                    case 6:
                        mapValueb.put("2","失踪人口");break;
                    case 7:
                        mapValueb.put("2","无名尸");break;
                    case 8:
                        mapValueb.put("2","嫌疑人亲属");break;
                    case 9:
                        mapValueb.put("2","受害人亲属");break;
                    case 10:
                        mapValueb.put("2","失踪人口亲属");break;
                    case 11:
                        mapValueb.put("2","基础库");break;
                    case 12:
                        mapValueb.put("2","YSTR");break;
                    default:
                        mapValueb.put("2","");break;
                }
            }else{
                mapValueb.put("2","");
            }
            mapValueb.put("instoredType", "亲缘比对");
            mapValueb.put("delegateOrgName", vote.getDelegateaOrgName());
            listmap.add(mapValueb);
            Map<String, Object> mapValuec = new HashMap<String, Object>();
            mapValuec.put("caseName", vote.getEntity().getCasebName());
            mapValuec.put("1", "未破获");
            mapValuec.put("sampleNo", vote.getEntity().getSamplecNo());
            mapValuec.put("sampleName", vote.getEntity().getSamplecName());
            if (null != vote.getInstoredaType() ){
                switch ( Integer.parseInt(vote.getInstoredaType())) {
                    case 1:
                        mapValuec.put("2","现场物证");break;
                    case 2:
                        mapValuec.put("2","混合物证");break;
                    case 3:
                        mapValuec.put("2","违法犯罪人员");break;
                    case 4:
                        mapValuec.put("2","嫌疑人");break;
                    case 5:
                        mapValuec.put("2","受害人");break;
                    case 6:
                        mapValuec.put("2","失踪人口");break;
                    case 7:
                        mapValuec.put("2","无名尸");break;
                    case 8:
                        mapValuec.put("2","嫌疑人亲属");break;
                    case 9:
                        mapValuec.put("2","受害人亲属");break;
                    case 10:
                        mapValuec.put("2","失踪人口亲属");break;
                    case 11:
                        mapValuec.put("2","基础库");break;
                    case 12:
                        mapValuec.put("2","YSTR");break;
                    default:
                        mapValuec.put("2","");break;
                }
            }else{
                mapValuec.put("2","");
            }
            mapValuec.put("instoredType", "亲缘比对");
            mapValuec.put("delegateOrgName", vote.getDelegateaOrgName());
            listmap.add(mapValuec);
        }
        return listmap;
    }



    private List<Map<String, Object>> createExcelRecord(List<MatchSameResultVo> list) {
        List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sheetName", "sheet1");
        listmap.add(map);//caseName","1","sampleNo ","sampleName","instoredType","1","delegateOrgName"
        for (int j = 0; j < list.size(); j++) {
            MatchSameResultVo vote = list.get(j);
            Map<String, Object> mapValue = new HashMap<String, Object>();
            mapValue.put("caseName", vote.getCaseName());
            mapValue.put("1", "未破获");
            mapValue.put("sampleNo", vote.getSampleNo());
            mapValue.put("sampleName", vote.getSampleName());
            mapValue.put("instoredType", vote.getInstoredType());
            mapValue.put("2", vote.getEntity().getMatchType());
            mapValue.put("delegateOrgName", vote.getDelegateOrgName());
            listmap.add(mapValue);
        }
        return listmap;
    }


    /**
     * 去重
     * @param keyExtractor
     * @param <T>
     * @return
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        ConcurrentHashMap<Object, Boolean> map = new ConcurrentHashMap<>(16);
        return t -> map.putIfAbsent(keyExtractor.apply(t),Boolean.TRUE) == null;
    }
    /**
     * 查询亲缘比对结果
     * @param request
     * @param matchResult
     * @param pageInfo
     * @return
     */
    @RequestMapping("/querySameRelativeCompareResultList")
    public ModelAndView queryRelativeCompareResultList(HttpServletRequest request, MatchRelativeResultVo matchResult, PageInfo pageInfo,
                                                       String sameCount,String compareStatus){
        ModelAndView view = new ModelAndView();

        if(StringUtils.isNoneBlank(sameCount)){
            matchResult.getEntity().setSameCount(Integer.parseInt(sameCount));
        }

        if(StringUtils.isNoneBlank(compareStatus) ){
            matchResult.getEntity().setCompareStatus(compareStatus);
        }

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if(operateUser == null){
            view.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return  view;
        }
        try {

            //查询受理单位
            List<OrgInfo> acceptOrgList = orgInfoService.selectAcceptOrgList();
            //获取当前用户的id
            String userOrgId = operateUser.getOrgId();
            //将当前用户org_id设置进query
            if(StringUtils.isNotBlank(userOrgId)
                    && (userOrgId.contains(Constants.FORENSIC_CENTER_SHORT_ORG_ID)
                            || userOrgId.contains(Constants.FORENSIC_CENTER_SHORT_ORG_ID_BAK))){
                userOrgId = Constants.FORENSIC_CENTER_ORG_ID;
            }

            if (StringUtils.isNoneBlank(userOrgId)){
                matchResult.setUserOrdId(userOrgId);
                matchResult.setAcceptOrgId(userOrgId);
            }

            String orgId = StringUtils.isBlank(operateUser.getSubOrgId()) ? operateUser.getOrgId() : operateUser.getSubOrgId();

            //查询委托单位（分局）
            String orgParentsId = Constants.TOP_ORG_ID;
            List<OrgInfo> orgInfoList = orgInfoService.selectDelegateByParentsId(orgParentsId);
            view.addObject("orgInfoList", orgInfoList);

            //入库类型
            DictItem dictItem = new DictItem();
            dictItem.setDictTypeCode(Constants.INSTORED_TYPE);
            List<DictItem> dictSampleEntryTypeList = DictUtil.getDictList(dictItem);
            view.addObject("dictSampleEntryTypeList", dictSampleEntryTypeList);

            //比对类型
            dictItem.setDictTypeCode(Constants.COMPARISON_TYPE);
            List<DictItem> comparisonTypeList = DictUtil.getDictList(dictItem);
            view.addObject("comparisonTypeList", comparisonTypeList);

            //比对类别
            dictItem.setDictTypeCode(Constants.COMPARISON_CATEGORY);
            List<DictItem> comparisonCategoryList = DictUtil.getDictList(dictItem);
            view.addObject("comparisonCategoryList", comparisonCategoryList);

            //状态
            List<DictItem> compareStatusList1 = new LinkedList<>();
            dictItem.setDictTypeCode(Constants.COMPARE_STATUS);
            List<DictItem> compareStatusList = DictUtil.getDictList(dictItem);
            for(DictItem dictItem1:compareStatusList){
                if(("0").equals(dictItem1.getDictCode())){
                    compareStatusList1.add(dictItem1);
                }else if(("2").equals(dictItem1.getDictCode())){
                    compareStatusList1.add(dictItem1);
                }else if(("3").equals(dictItem1.getDictCode())){
                    compareStatusList1.add(dictItem1);
                }
            }
            view.addObject("compareStatusList", compareStatusList1);

            MatchRelativeResultVo matchRelativeResultVo = new MatchRelativeResultVo();
            //matchRelativeResultVo.setDelegateOrgCode(orgId);
            matchRelativeResultVo.setUserOrdId(userOrgId);
            //条件查询
            if (StringUtils.isNotBlank(matchResult.getCaseName())) {
                matchRelativeResultVo.setCaseName(matchResult.getCaseName().trim());
            }else{
                matchRelativeResultVo.setCaseName(null);
            }
            //案件id
            if (StringUtils.isBlank(matchResult.getCaseId())) {
                matchRelativeResultVo.setCaseId(null);
            } else {
                matchRelativeResultVo.setCaseId(matchResult.getCaseId().trim());
            }
            if (StringUtils.isNotBlank(matchResult.getSampleName())) {
                matchRelativeResultVo.setSampleName(matchResult.getSampleName().trim());
            }else{
                matchRelativeResultVo.setSampleName(null);
            }
            if (StringUtils.isNotBlank(matchResult.getSampleNo())) {
                matchRelativeResultVo.setSampleNo(matchResult.getSampleNo().trim());
            }else{
                matchRelativeResultVo.setSampleNo(null);
            }
            if(StringUtils.isNotBlank(matchResult.getInstoredType())){
                matchRelativeResultVo.setInstoredType(matchResult.getInstoredType().trim());
            }else{
                matchRelativeResultVo.setInstoredType(null);
            }
            if(StringUtils.isNotBlank(matchResult.getCaseNo())){
                matchRelativeResultVo.setCaseNo(matchResult.getCaseNo().trim());
            }else{
                matchRelativeResultVo.setCaseNo(null);
            }

            if(null != matchResult.getCreateStartDatetime()){
                matchRelativeResultVo.setCreateStartDatetime(matchResult.getCreateStartDatetime());
            }else{
                matchRelativeResultVo.setCreateStartDatetime(null);
            }

            if(null != matchResult.getCreateEndDatetime()){
                matchRelativeResultVo.setCreateEndDatetime(matchResult.getCreateEndDatetime());
            }else{
                matchRelativeResultVo.setCreateEndDatetime(null);
            }

            if(null != matchResult.getAcceptOrgId()){
                matchRelativeResultVo.setAcceptOrgId(matchResult.getAcceptOrgId());
            }else{
                matchRelativeResultVo.setAcceptOrgId(null);
            }

            if(null != matchResult.getEntity().getSameCount()){
                matchRelativeResultVo.getEntity().setSameCount(matchResult.getEntity().getSameCount());
            }else{
                matchRelativeResultVo.getEntity().setSameCount(null);
            }

            if(StringUtils.isNotBlank(matchResult.getEntity().getCompareStatus())){
                matchRelativeResultVo.getEntity().setCompareStatus(matchResult.getEntity().getCompareStatus());
            }else{
                matchRelativeResultVo.getEntity().setCompareStatus("0");
            }

            //查询比对结果
            List<MatchRelativeResultVo> matchRelativeResultList = matchRelativeResultService.selectMatchRelativeResultList(matchRelativeResultVo, pageInfo);
/*
            edit by lizhihua  20190908
            1、影响性能；2、可通过sql一次性查询出来
            if(null != matchRelativeResultList && matchRelativeResultList.size() > 0){
                for(MatchRelativeResultVo matchRelativeResultVo1:matchRelativeResultList) {
                    //查询类别
                    List<DictItem> dictItemList = dictItemService.selectListByDictTypeCode("COMPARISON_CATEGORY");
                    for(DictItem dictItem1:dictItemList){
                        if(StringUtils.isNotBlank(matchRelativeResultVo1.getEntity().getMatchType())){
                            if((matchRelativeResultVo1.getEntity().getMatchType()).equals(dictItem1.getDictCode())){
                                matchRelativeResultVo1.getEntity().setMatchType(dictItem1.getDictName());
                            }
                        }
                    }
                }
            }
*/

            int matchRelativeResultCount = matchRelativeResultService.selectMatchRelativeResultCount(matchRelativeResultVo);
            view.addObject("acceptOrgList", acceptOrgList);
            view.addObject("userOrgId", userOrgId);
            view.addObject("matchResultList1", matchRelativeResultList);
            view.addObject("pageInfo", pageInfo.updatePageInfo(matchRelativeResultCount));
            view.addObject("comparisonCategory", matchResult.getComparisonCategory());
            view.addObject("matchResult", matchResult);
            view.setViewName("query/queryRelativeCompareResultList");
        }catch (Exception e){
            logger.info("查询亲缘比对结果:"+e);
        }
        return view;
    }

    /**
     * 查看同一比对结果详情页
     * @param request
     * @return
     */
    @RequestMapping("/querySameCompareResultDetail")
    public ModelAndView queryCompareResultDetail(HttpServletRequest request, String groupId, MatchSameResult matchSameResult, PageInfo pageInfo, String groupNo){
        ModelAndView view = new ModelAndView();
        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if(operateUser == null){
            view.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return  view;
        }
        try {
            matchSameResult.setGroupId(groupId);
            if (StringUtils.isNotBlank(matchSameResult.getCaseaName())) {
                matchSameResult.setCaseaName(matchSameResult.getCaseaName().trim());
            }else{
                matchSameResult.setCaseaName(null);
            }
            if (StringUtils.isNotBlank(matchSameResult.getFirstChecker())) {
                matchSameResult.setFirstChecker(matchSameResult.getFirstChecker().trim());
            }else{
                matchSameResult.setFirstChecker(null);
            }
            if (StringUtils.isNotBlank(matchSameResult.getSampleaName())) {
                matchSameResult.setSampleaName(matchSameResult.getSampleaName().trim());
            }else{
                matchSameResult.setSampleaName(null);
            }
            List<MatchSameResult> matchResultList = matchSameResultService.selectByGroupId(matchSameResult, pageInfo);
            if(null != matchResultList && matchResultList.size()>0){
                for(MatchSameResult matchSameResult1:matchResultList){
                    if(StringUtils.isNotBlank(matchSameResult1.getFirstChecker())){
                        AmPersonalInfo amPersonalInfo = amPersonalInfoService.queryAmPersonalInfo(matchSameResult1.getFirstChecker());
                        if(null != amPersonalInfo){
                            if(StringUtils.isNotBlank(amPersonalInfo.getFullName())){
                                matchSameResult1.setFirstChecker(amPersonalInfo.getFullName());
                            }
                        }
                    }
                }
            }
            int matchResultCount = matchSameResultService.selectByGroupIdCount(matchSameResult);
            view.addObject("pageInfo", pageInfo.updatePageInfo(matchResultCount));

            //获取检验人list
            List<AmPersonalInfo> amPersonalInfoList = amPersonalInfoService.queryAmPersonalInfoListOrgId(operateUser.getOrgId());
            view.addObject("amPersonalInfoList", amPersonalInfoList);

            /*if(null != matchResultList && matchResultList.size()>0){
                view.addObject("groupId", matchResultList.get(0).getGroupId());
            }*/
            view.addObject("matchSameResult", matchSameResult);
            view.addObject("groupId", groupId);
            view.addObject("matchResultList", matchResultList);
            view.addObject("groupNo", groupNo);
            view.setViewName("query/queryCompareResult");
        }catch (Exception e){
            logger.info("查看同一比对结果详情页失败："+e);
        }

        return view;
    }

    /**
     * 查看亲缘比对结果详情页
     * @param request
     * @param groupId
     * @param matchRelativeResult
     * @return
     */
    @RequestMapping("/querySameRelativeCompareResultDetail")
    public ModelAndView queryRelativeCompareResultDetail(HttpServletRequest request, String groupId, MatchRelativeResult matchRelativeResult, String id, String groupNo){
        ModelAndView view = new ModelAndView();
        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if(operateUser == null){
            view.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return  view;
        }
        try {
            matchRelativeResult.setGroupId(groupId);
            matchRelativeResult.setId(id);
            if (StringUtils.isNotBlank(matchRelativeResult.getCaseaName())) {
                matchRelativeResult.setCaseaName(matchRelativeResult.getCaseaName().trim());
            }else{
                matchRelativeResult.setCaseaName(null);
            }
            if (StringUtils.isNotBlank(matchRelativeResult.getSampleaName())) {
                matchRelativeResult.setSampleaName(matchRelativeResult.getSampleaName().trim());
            }else{
                matchRelativeResult.setSampleaName(null);
            }
            if (StringUtils.isNotBlank(matchRelativeResult.getFirstChecker())) {
                matchRelativeResult.setFirstChecker(matchRelativeResult.getFirstChecker().trim());
            }else{
                matchRelativeResult.setFirstChecker(null);
            }

            MatchRelativeResult matchResultList = matchRelativeResultService.selectByGroupId(matchRelativeResult);

            //获取检验人list
            List<AmPersonalInfo> amPersonalInfoList = amPersonalInfoService.queryAmPersonalInfoListOrgId(operateUser.getOrgId());
            view.addObject("amPersonalInfoList", amPersonalInfoList);

            view.addObject("matchSameResult", matchRelativeResult);
            view.addObject("matchResultList", matchResultList);
            view.addObject("groupId", groupId);
            view.addObject("groupNo", groupNo);
            view.setViewName("query/queryRelativeCompareResult");
        }catch (Exception e){
            logger.info("查看亲缘比对结果详情页失败："+e);
        }

        return view;
    }

    /**
     * 生成串并单
     * @param request
     * @param response
     */
    @RequestMapping("/SameChuanbindan")
    public void chuanbindan(HttpServletRequest request, HttpServletResponse response,String groupId) {

        Map<String, Object> params = new HashMap<String, Object>();

        List<MatchSameResult> matchSameResultList = matchSameResultService.chuanbingdan(groupId);
        for (int j = 0; j < matchSameResultList.size(); j++) {
            LimsCaseInfo limsCaseInfoA = limsCaseInfoService.selectByCaseId(matchSameResultList.get(j).getCaseaId());
            String caseBriefA = "";
            if (limsCaseInfoA != null) {
                caseBriefA = limsCaseInfoA.getCaseBrief();
                if (StringUtils.isNotBlank(caseBriefA)) {
                    //替换左尖括号
                    caseBriefA = caseBriefA.replace("<", "&lt;");
                    caseBriefA = caseBriefA.replace(">", "&gt;");
                }
                limsCaseInfoA.setCaseBrief(caseBriefA);
            }
            matchSameResultList.get(j).setLimsCaseInfoA(limsCaseInfoA);
            AmPersonalInfo amPersonalInfoA = amPersonalInfoService.selectByPersonalId(limsCaseInfoA.getFirstChecker());
            matchSameResultList.get(j).setAmPersonalInfoA(amPersonalInfoA);
            List<LimsConsignmentInfo> limsConsignmentInfoAList = limsConsignmentInfoService.selectByCaseId(matchSameResultList.get(j).getCasebId());
            LimsConsignmentInfo limsConsignmentInfoA = null;
            for (int i = 0; i < limsConsignmentInfoAList.size(); i++) {
                if (limsConsignmentInfoAList.get(i).getAppendFlag().equals("0")){
                    limsConsignmentInfoA = limsConsignmentInfoAList.get(i);
                    matchSameResultList.get(j).setLimsConsignmentInfoA(limsConsignmentInfoA);
                }
            }
            LimsCaseInfo limsCaseInfoB = limsCaseInfoService.selectByCaseId(matchSameResultList.get(j).getCasebId());
            String caseBriefB = "";
            if (limsCaseInfoB != null) {
                caseBriefB = limsCaseInfoB.getCaseBrief();
                if (StringUtils.isNotBlank(caseBriefB)) {
                    //替换左尖括号
                    caseBriefB = caseBriefB.replace("<", "&lt;");
                    caseBriefB = caseBriefB.replace(">", "&gt;");
                }
                limsCaseInfoB.setCaseBrief(caseBriefB);
            }
            matchSameResultList.get(j).setLimsCaseInfoB(limsCaseInfoB);
            AmPersonalInfo amPersonalInfoB = amPersonalInfoService.selectByPersonalId(limsCaseInfoB.getFirstChecker());
            matchSameResultList.get(j).setAmPersonalInfoB(amPersonalInfoB);
            List<LimsConsignmentInfo> limsConsignmentInfoBList = limsConsignmentInfoService.selectByCaseId(matchSameResultList.get(j).getCasebId());
            LimsConsignmentInfo limsConsignmentInfoB = null;
            for (int i = 0; i < limsConsignmentInfoBList.size(); i++) {
                if(limsConsignmentInfoBList.get(i).getAppendFlag().equals("0")){
                    limsConsignmentInfoB = limsConsignmentInfoBList.get(i);
                    matchSameResultList.get(j).setLimsConsignmentInfoB(limsConsignmentInfoB);
                }
            }
        }
        params.put("matchSameResultList",matchSameResultList);

        try {

            Configuration cfg = new Configuration();
            cfg.setDefaultEncoding("UTF-8");
            cfg.setClassForTemplateLoading(this.getClass(), "/templates");
            //获取模板
            Template tmp = cfg.getTemplate("chuanbingdan.ftl", "UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + new String("-案件串并情况说明.doc".getBytes("GBK"),"ISO-8859-1"));//文件头，导出的文件名
            response.setContentType("application/x-msdownload");//类型
            tmp.process(params, response.getWriter());
        } catch (Exception ex) {
            logger.error("串并单生成失败！", ex);
        }
    }

    /**
     * 生成串并单
     * @param request
     * @param response
     */
    @RequestMapping("/SameChuanbindanrelative")
    public void chuanbindanrelative(HttpServletRequest request, HttpServletResponse response,String groupId) {
        Map<String, Object> params = new HashMap<String, Object>();

        List<Map<String, Object>> resultList = new ArrayList<>();
        List<MatchRelativeResult> matchRelativeResultList = matchRelativeResultService.selectListByGroupId(groupId);
        if (ListUtils.isNotNullAndEmptyList(matchRelativeResultList)) {
            for (MatchRelativeResult mrr : matchRelativeResultList) {
                Map<String, Object> map = new LinkedHashMap<>();
                LimsSampleInfoDna sampleInfoDnaA = limsSampleInfoDnaService.selectSampleInfoDnaById(mrr.getSampleaId());
                if (sampleInfoDnaA != null) {
                    LimsCaseInfo caseInfoA = limsCaseInfoService.selectByCaseId(sampleInfoDnaA.getCaseId());
                    String caseBriefA = "";
                    if (caseInfoA != null) {
                        map.put("caseNo", caseInfoA.getCaseNo());
                        //替换左尖括号
                        if (StringUtils.isNotBlank(caseInfoA.getCaseBrief())) {
                            caseBriefA = caseInfoA.getCaseBrief().replace("<", "&lt;");
                            caseBriefA = caseBriefA.replace(">", "&gt;");
                            //caseBriefA = caseInfoA.getCaseBrief().replace(">", "&gt;");
                        }
                        map.put("caseBrief", caseBriefA);
                        map.put("caseXkNo", caseInfoA.getCaseXkNo());
                        map.put("XkANo", caseInfoA.getXkANo());
                        AmPersonalInfo amPersonalInfo = amPersonalInfoService.selectByPersonalId(caseInfoA.getFirstChecker());
                        if (amPersonalInfo != null) {
                            map.put("person", amPersonalInfo.getFullName());
                        }
                    }
                    LimsConsignmentInfo consignmentInfoA = limsConsignmentInfoService.selectByConsignmentId(sampleInfoDnaA.getConsignmentId());
                    if (consignmentInfoA != null) {
                        map.put("delegateOrgName", consignmentInfoA.getDelegateOrgName());
                    }
                    map.put("sampleName", mrr.getSampleaName());
                    map.put("sampleNo", mrr.getSampleaNo());

                    resultList.add(map);
                }

                map = new LinkedHashMap<>();
                LimsSampleInfoDna sampleInfoDnaB = limsSampleInfoDnaService.selectSampleInfoDnaById(mrr.getSamplebId());
                if (sampleInfoDnaB != null) {
                    LimsCaseInfo caseInfoB = limsCaseInfoService.selectByCaseId(sampleInfoDnaB.getCaseId());
                    String caseBriefB = "";
                    if (caseInfoB != null) {
                        map.put("caseNo", caseInfoB.getCaseNo());
                        //替换左尖括号
                        if (StringUtils.isNotBlank(caseInfoB.getCaseBrief())) {
                            caseBriefB = caseInfoB.getCaseBrief().replace("<", "&lt;");
                            caseBriefB = caseBriefB.replace(">", "&gt;");
                            //caseBriefB = caseInfoB.getCaseBrief().replace(">", "&gt;");
                        }
                        map.put("caseBrief", caseBriefB);
                        map.put("caseXkNo", caseInfoB.getCaseXkNo());
                        map.put("XkANo", caseInfoB.getXkANo());
                        AmPersonalInfo amPersonalInfo = amPersonalInfoService.selectByPersonalId(caseInfoB.getFirstChecker());
                        if (amPersonalInfo != null) {
                            map.put("person", amPersonalInfo.getFullName());
                        }
                    }
                    LimsConsignmentInfo consignmentInfoB = limsConsignmentInfoService.selectByConsignmentId(sampleInfoDnaB.getConsignmentId());
                    if (consignmentInfoB != null) {
                        map.put("delegateOrgName", consignmentInfoB.getDelegateOrgName());
                    }
                    map.put("sampleName", mrr.getSamplebName());
                    map.put("sampleNo", mrr.getSamplebNo());

                    resultList.add(map);
                }

                map = new LinkedHashMap<>();
                LimsSampleInfoDna sampleInfoDnaC = limsSampleInfoDnaService.selectSampleInfoDnaById(mrr.getSamplecId());
                if (sampleInfoDnaC != null) {
                    LimsCaseInfo caseInfoC = limsCaseInfoService.selectByCaseId(sampleInfoDnaC.getCaseId());
                    String caseBriefC = "";
                    if (caseInfoC != null) {
                        map.put("caseNo", caseInfoC.getCaseNo());
                        //替换左尖括号
                        if (StringUtils.isNotBlank(caseInfoC.getCaseBrief())) {
                            caseBriefC = caseInfoC.getCaseBrief().replace("<", "&lt;");
                            caseBriefC = caseBriefC.replace(">", "&gt;");
                            //caseBriefC = caseInfoC.getCaseBrief().replace(">", "&gt;");
                        }
                        map.put("caseBrief", caseBriefC);
                        map.put("caseXkNo", caseInfoC.getCaseXkNo());
                        map.put("XkANo", caseInfoC.getXkANo());
                        AmPersonalInfo amPersonalInfo = amPersonalInfoService.selectByPersonalId(caseInfoC.getFirstChecker());
                        if (amPersonalInfo != null) {
                            map.put("person", amPersonalInfo.getFullName());
                        }
                    }
                    LimsConsignmentInfo consignmentInfoC = limsConsignmentInfoService.selectByConsignmentId(sampleInfoDnaC.getConsignmentId());
                    if (consignmentInfoC != null) {
                        map.put("delegateOrgName", consignmentInfoC.getDelegateOrgName());
                    }
                    map.put("sampleName", mrr.getSamplebName());
                    map.put("sampleNo", mrr.getSamplebNo());

                    resultList.add(map);
                }
            }
        }

        params.put("resultList", resultList);
        try {

            Configuration cfg = new Configuration();
            cfg.setDefaultEncoding("UTF-8");
            cfg.setClassForTemplateLoading(this.getClass(), "/templates");
            //获取模板
            Template tmp = cfg.getTemplate("chuanbingdanrelative.ftl", "UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + new String("-案件串并情况说明.doc".getBytes("GBK"),"ISO-8859-1"));//文件头，导出的文件名
            response.setContentType("application/x-msdownload");//类型
            tmp.process(params, response.getWriter());
        } catch (Exception ex) {
            logger.error("串并单生成失败！", ex);
        }
    }

    /**
     * 生成鉴定书
     * @param request
     * @param response
     */
    @RequestMapping("/SameChuanbindanJD")
    public void chuanbindanJD(HttpServletRequest request, HttpServletResponse response,String groupId) {

        Map<String, Object> params = new HashMap<String, Object>();


        try {

            Configuration cfg = new Configuration();
            cfg.setDefaultEncoding("UTF-8");
            cfg.setClassForTemplateLoading(this.getClass(), "/templates");
            //获取模板
            Template tmp = cfg.getTemplate("jiandingshu.ftl", "UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + new String("串并案鉴定书.doc".getBytes("GBK"),"ISO-8859-1"));//文件头，导出的文件名
            response.setContentType("application/x-msdownload");//类型
            tmp.process(params, response.getWriter());
        } catch (Exception ex) {
            logger.error("串并案鉴定书生成失败！", ex);
        }
    }

    /**
     * 生成资格证书
     * @param request
     * @param response
     */
    @RequestMapping("/SameChuanbindanZS")
    public void chuanbindanZS(HttpServletRequest request, HttpServletResponse response,String groupId) {

        Map<String, Object> params = new HashMap<String, Object>();


        try {

            Configuration cfg = new Configuration();
            cfg.setDefaultEncoding("UTF-8");
            cfg.setClassForTemplateLoading(this.getClass(), "/templates");
            //获取模板
            Template tmp = cfg.getTemplate("chuanbingdan.ftl", "UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + new String("-案件串并情况说明.doc".getBytes("GBK"),"ISO-8859-1"));//文件头，导出的文件名
            response.setContentType("application/x-msdownload");//类型
            tmp.process(params, response.getWriter());
        } catch (Exception ex) {
            logger.error("串并单生成失败！", ex);
        }
    }

    /**
     * 同一比对结果情况
     * @param request
     * @return
     */
    @RequestMapping("/querySameCompareCondition")
    public ModelAndView queryCompareCondition(HttpServletRequest request, String id){
        ModelAndView view = new ModelAndView();
        try {
            //根据id查询同一比对结果表
            MatchSameResult matchSameResult = matchSameResultService.selectById(id);
            CompareSameResult compareSameResult = new CompareSameResult();
            compareSameResult.setSampleNo(matchSameResult.getSamplebNo());
            compareSameResult.setMatchLimit(0);
            compareSameResult.setReferenceId(matchSameResult.getSampleaNo());
            view =  viewCompareResult(compareSameResult);
            view.addObject("matchSameResult", matchSameResult);
            view.addObject("compareSameResult", compareSameResult);
        }catch (Exception e){
            logger.info("查询同一比对结果情况失败:"+e);
        }
        view.setViewName("query/queryCompareCondition");
        return view;
    }

    public ModelAndView viewCompareResult(CompareSameResult compareSameResult) {
        ModelAndView view = new ModelAndView();

        try {
            if (compareSameResult != null) {
                String referenceId = compareSameResult.getReferenceId();
                String sampleNo = compareSameResult.getSampleNo();
                List<MatchAuditedGene> referenceAuditedGeneList = matchAuditedGeneService.selectListBySampleNo(referenceId);
                List<MatchAuditedGene> auditedGeneList = matchAuditedGeneService.selectListBySampleNo(sampleNo);

                Map<String, Object> result = new HashMap<>();
                if (ListUtils.isNotNullAndEmptyList(referenceAuditedGeneList) && ListUtils.isNotNullAndEmptyList(auditedGeneList)) {
                    MatchAuditedGene referenceAuditedGene = referenceAuditedGeneList.get(0);
                    MatchAuditedGene auditedGene = auditedGeneList.get(0);

                    Map<String, List<String>> referenceGeneInfo = (Map) JSON.parse(referenceAuditedGene.getGeneInfo());
                    Map<String, List<String>> geneInfo = (Map) JSON.parse(auditedGene.getGeneInfo());
                    if (referenceGeneInfo != null && geneInfo != null) {
                        if (compareSameResult.getMatchLimit() == null) {
                            compareSameResult.setMatchLimit(0);
                        }
                        result = geneSameCompareUtil.compareResult(referenceGeneInfo, geneInfo, compareSameResult.getMatchLimit());
                    }

                    Double fProb = (Double)result.get("probability");
                    String str = DataFormat.formatDecimal(fProb == null?0.0:fProb, 3, 1, true);
                    view.addObject("mapList", result);
                    view.addObject("probability", str);
                    view.addObject("referenceAuditedGene", referenceAuditedGene);
                    view.addObject("auditedGene", auditedGene);
                    view.addObject("success", true);
                }else {
                    view.addObject("success", false);
                }
            }else {
                view.addObject("success", false);
            }
        }catch (Exception e) {
            logger.error("查看同一比对结果报错！" + e.getMessage());
            e.getStackTrace();
        }

        return view;
    }

    /**
     * 亲缘比中结果情况
     * @param request
     * @return
     */
    @RequestMapping("/queryRelativeCompareConditionView")
    public ModelAndView queryRelativeCompareConditionView(HttpServletRequest request, String id){
        ModelAndView view = new ModelAndView();
        try {
            //根据id查询亲缘比对结果
            MatchRelativeResult matchRelativeResult = matchRelativeResultService.selectById(id);
            CompareRelativeResult compareRelativeResult = new CompareRelativeResult();
            compareRelativeResult.setFatherSampleNo(matchRelativeResult.getSampleaNo());
            compareRelativeResult.setMotherSampleNo(matchRelativeResult.getSamplebNo());
            compareRelativeResult.setChildSampleNo(matchRelativeResult.getSamplecNo());
            MatchAuditedGene FsampleGene = null;
            String fatherGeneInfo = null;
            if (StringUtils.isNotBlank(compareRelativeResult.getFatherSampleNo())) {
                List<MatchAuditedGene> fatherGeneList = matchAuditedGeneService.selectListBySampleNo(compareRelativeResult.getFatherSampleNo());
                if (ListUtils.isNotNullAndEmptyList(fatherGeneList)) {
                    FsampleGene = fatherGeneList.get(0);
                    if (FsampleGene != null) {
                        fatherGeneInfo = FsampleGene.getGeneInfo();
                        view.addObject("fatherSampleNo", FsampleGene.getSampleNo());
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
                        view.addObject("motherSampleNo", MsampleGene.getSampleNo());
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
                        view.addObject("childSampleNo", ZsampleGene.getSampleNo());
                    }
                }
            }

            List<Map<String, Object>> resultList = new ArrayList<>();
            resultList.add(geneRelativeCompareUtil.relationCompare(fatherGeneInfo, motherGeneInfo, childGeneInfo, compareRelativeResult));

            Map<String, List<String>> fatherResult = null;
            try {
                fatherResult =(Map) JSON.parse(fatherGeneInfo);
            } catch (Exception ex) {
                logger.error("解析父亲基因分型信息错误！", ex);
            }

            Map<String, List<String>> motherResult = null;
            try {
                motherResult =(Map) JSON.parse(motherGeneInfo);
            } catch (Exception ex) {
                logger.error("解析母亲基因分型信息错误！", ex);
            }

            Map<String, List<String>> childResult = null;
            try {
                childResult =(Map) JSON.parse(childGeneInfo);
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
                            view.addObject("cMatchCount", totalResult.getMatchCount());
                            Double totalPi = totalResult.getPi();
                            String totalPiStr = "";
                            if (!Double.isNaN(totalPi)) {
                                totalPiStr =  DataFormat.formatDecimal(totalPi == 0.0?0.0:totalPi, 3, 1, true);
                            }
                            view.addObject("totalPiStr", totalPiStr);

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
                            view.addObject("fMatchCount", afResult.getMatchCount());
                            Double fTotalPi = afResult.getPi();
                            String fTotalPiStr = "";
                            if (!Double.isNaN(fTotalPi)  && Math.abs(fTotalPi - 1.0d ) > 0d) {
                                fTotalPiStr =  DataFormat.formatDecimal(fTotalPi == 0.0?0.0:fTotalPi, 3, 1, true);
                            }
                            view.addObject("fTotalPiStr", fTotalPiStr);

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
                            view.addObject("mMatchCount", mResult.getMatchCount());
                            Double mTotalPi = mResult.getPi();
                            String mTotalPiStr = "";
                            if (!Double.isNaN(mTotalPi) && Math.abs(mTotalPi - 1.0d ) > 0d) {
                                mTotalPiStr =  DataFormat.formatDecimal(mTotalPi == 0.0?0.0:mTotalPi, 3, 1, true);
                            }
                            view.addObject("mTotalPiStr", mTotalPiStr);

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

            view.addObject("matchRelativeResult", matchRelativeResult);
            view.addObject("groupList", groupList);
            view.addObject("limsCaseInfo", limsCaseInfo);
        }catch (Exception e){
            logger.info("查询亲缘比中结果情况失败："+e);
        }
        view.setViewName("query/queryRelativeCompareCondition");
        return view;
    }

    /**
     * 解除同一结果多个组de关联
     * @param request
     * @return
     */
    @RequestMapping("/SamerelieveGroupsRelevance")
    @ResponseBody
    public Map<String, Object> relieveGroupsRelevance(HttpServletRequest request, String groupIdArr){
        Map<String, Object> resultMap = new HashMap<>();
        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo loaUserInfo = (LoaUserInfo) subject.getPrincipal();
        if(loaUserInfo == null){
            resultMap.put("date", "redirect:/login.html?timeoutFlag=1");
            return  resultMap;
        }
        try {
            //分割
            String[] groupIdArr1 = groupIdArr.split(",");
            for(String groupId:groupIdArr1){
                //解除关联
                MatchSameResult matchSameResult = new MatchSameResult();
                matchSameResult.setGroupId(groupId);
                matchSameResult.setUpdatePerson(loaUserInfo.getLoginName());
                matchSameResult.setUpdateDatetime(new Date());
                matchSameResultService.relieveGroupRelevance(matchSameResult);
            }
            resultMap.put("success" ,true);
        }catch (Exception e){
            resultMap.put("success" ,false);
            logger.info("解除多个组关联失败："+e);
        }
        return resultMap;
    }

    /**
     * 根据主键解除同一结果关联（单个解除关联）
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/SameRelieveRelevance")
    @ResponseBody
    public Map<String, Object> relieveRelevance(HttpServletRequest request, String id) {
        Map<String, Object> resultMap = new HashMap<>();
        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo loaUserInfo = (LoaUserInfo) subject.getPrincipal();
        if(loaUserInfo == null){
            resultMap.put("date", "redirect:/login.html?timeoutFlag=1");
            return  resultMap;
        }
        try {
            //解除关联
            MatchSameResult matchSameResult = new MatchSameResult();
            matchSameResult.setUpdateDatetime(new Date());
            matchSameResult.setUpdatePerson(loaUserInfo.getLoginName());
            matchSameResult.setId(id);
            matchSameResultService.relieveRelevance(matchSameResult);
            resultMap.put("success" ,true);
        }catch (Exception e){
            resultMap.put("success" ,false);
            logger.info("根据主键解除关联（单个解除关联）："+e);
        }
        return resultMap;
    }


    /**
     * 解除亲缘关联
     * @param request
     * @return
     */
    @RequestMapping("/SameRelieveRelativeRelevance")
    @ResponseBody
    public Map<String, Object> relieveRelativeRelevance(HttpServletRequest request, String id){
        Map<String, Object> resultMap = new HashMap<>();
        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo loaUserInfo = (LoaUserInfo) subject.getPrincipal();
        if(loaUserInfo == null){
            resultMap.put("date", "redirect:/login.html?timeoutFlag=1");
            return  resultMap;
        }
        try {
            //解除关联
            MatchRelativeResult matchRelativeResult = new MatchRelativeResult();
            matchRelativeResult.setId(id);
            matchRelativeResult.setUpdateDatetime(new Date());
            matchRelativeResult.setUpdatePerson(loaUserInfo.getLoginName());
            matchRelativeResultService.relieveRelativeRelevance(matchRelativeResult);
            resultMap.put("success" ,true);
        }catch (Exception e){
            resultMap.put("success" ,false);
            logger.info("解除关联失败："+e);
        }
        return resultMap;
    }

    /**
     * 解除亲缘多个组de关联
     * @param request
     * @return
     */
    @RequestMapping("/SameRelieveRelativeGroupsRelevance")
    @ResponseBody
    public Map<String, Object> relieveRelativeGroupsRelevance(HttpServletRequest request, String groupIdArr){
        Map<String, Object> resultMap = new HashMap<>();
        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo loaUserInfo = (LoaUserInfo) subject.getPrincipal();
        if(loaUserInfo == null){
            resultMap.put("date", "redirect:/login.html?timeoutFlag=1");
            return  resultMap;
        }
        try {
            //分割
            String[] groupIdArr1 = groupIdArr.split(",");
            for(String groupId:groupIdArr1){
                //解除关联
                MatchRelativeResult matchRelativeResult = new MatchRelativeResult();
                matchRelativeResult.setGroupId(groupId);
                matchRelativeResult.setUpdateDatetime(new Date());
                matchRelativeResult.setUpdatePerson(loaUserInfo.getLoginName());
                matchRelativeResultService.relieveRelativeGroupRelevance(matchRelativeResult);
            }
            resultMap.put("success" ,true);
        }catch (Exception e){
            resultMap.put("success" ,false);
            logger.info("解除多个组关联失败："+e);
        }
        return resultMap;
    }

    /**
     * 确认比中（组）
     * @param request
     * @param groupIdArr
     * @return
     */
    @RequestMapping("/ConfirmSameCompareGroup")
    @ResponseBody
    public Map<String, Object> confirmCompareGroup(HttpServletRequest request, String groupIdArr){
        Map<String, Object> resultMap = new HashMap<>();
        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo loaUserInfo = (LoaUserInfo) subject.getPrincipal();
        if(loaUserInfo == null){
            resultMap.put("date", "redirect:/login.html?timeoutFlag=1");
            return  resultMap;
        }
        try {
            //分割
            String[] groupIdArr1 = groupIdArr.split(",");
            for(String groupId:groupIdArr1){
                MatchSameResult matchSameResult = new MatchSameResult();
                matchSameResult.setUpdatePerson(loaUserInfo.getLoginName());
                matchSameResult.setUpdateDatetime(new Date());
                matchSameResult.setGroupId(groupId);
                matchSameResultService.confirmCompareGroup(matchSameResult);
                resultMap.put("success" ,true);
            }
        }catch (Exception e){
            resultMap.put("success" ,false);
            logger.info("确认比中失败："+e);
        }
        return resultMap;
    }

    /**
     * 确认比中
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/confirmSameCompare")
    @ResponseBody
    public Map<String, Object> confirmCompare(HttpServletRequest request, String id){
        Map<String, Object> resultMap = new HashMap<>();
        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo loaUserInfo = (LoaUserInfo) subject.getPrincipal();
        if(loaUserInfo == null){
            resultMap.put("date", "redirect:/login.html?timeoutFlag=1");
            return  resultMap;
        }
        try {
            MatchSameResult matchSameResult = new MatchSameResult();
            matchSameResult.setId(id);
            matchSameResult.setUpdatePerson(loaUserInfo.getLoginName());
            matchSameResult.setUpdateDatetime(new Date());
            matchSameResultService.confirmCompare(matchSameResult);
            resultMap.put("success" ,true);

            if (1 == isAppInform){ //1开启 0默认关闭
                MatchSameResult matchSameResult1 = matchSameResultService.selectByPrimaryKey(id);
                LimsSampleInfoDna limsSampleInfoDna = limsSampleInfoDnaService.selectSampleInfoDnaById(matchSameResult1.getSampleaId());
                LimsSampleInfoDna limsSampleInfoDnatwo = limsSampleInfoDnaService.selectSampleInfoDnaById(matchSameResult1.getSamplebId());
                LimsConsignmentInfo limsConsignmentInfo = limsConsignmentInfoService.selectByConsignmentId(limsSampleInfoDna.getConsignmentId());
                LimsConsignmentInfo limsConsignmentInfotwo = limsConsignmentInfoService.selectByConsignmentId(limsSampleInfoDnatwo.getConsignmentId());
                if (null != limsConsignmentInfo){
                    String date = DateUtils.dateToString(new Date(),"yyyy-MM-dd HH:mm:ss");
                    CallThirdpartyInfo bean = new CallThirdpartyInfo();
                    //发起人信息
                    bean.setUserid(loaUserInfo.getUserId());
                    bean.setUserName(loaUserInfo.getLoginName());
                    bean.setOrgid(loaUserInfo.getOrgId());
                    bean.setIp(IpAddressUtils.getIpAddr(request));
                    //接收人
                    List<String> phones = new ArrayList<>();
                    if (null != limsConsignmentInfo.getDelegator1PhoneNumber())
                        phones.add(limsConsignmentInfo.getDelegator1PhoneNumber());
                    if (null != limsConsignmentInfo.getDelegator2PhoneNumber())
                        phones.add(limsConsignmentInfo.getDelegator2PhoneNumber());
                    bean.setPhones(phones);
                    String countName = limsConsignmentInfo.getDelegator1Name()+","+limsConsignmentInfo.getDelegator2Name();
                    ArrayList<MobileNews> mobileNewsList = new ArrayList<>();
                    MobileNews count = new MobileNews();
                    count.setId(UuidUtil.generateUUID());
                    count.setTitle(matchSameResult1.getCaseaName());
                    count.setContent(CallThirdpartyTool.Content(6,countName,limsConsignmentInfo.getDelegateOrgName(),null));
                    count.setState(0);
                    count.setCreateDatetime(date);
                    count.setUpdateDatetime(date);
                    count.setType(6);
                    count.setUserid(limsConsignmentInfo.getDelegator1Id());
                    count.setUsername(limsConsignmentInfo.getDelegator1Name());
                    count.setUserOrg(limsConsignmentInfo.getDelegateOrgCode());
                    count.setCaseId(limsConsignmentInfo.getCaseId());
                    count.setMessageType(1);
                    MobileNews count2 = new MobileNews();
                    count2.setId(UuidUtil.generateUUID());
                    count2.setTitle(matchSameResult1.getCaseaName());
                    count2.setContent(CallThirdpartyTool.Content(6,countName,limsConsignmentInfo.getDelegateOrgName(),null));
                    count2.setState(0);
                    count2.setCreateDatetime(date);
                    count2.setUpdateDatetime(date);
                    count2.setType(6);
                    count2.setUserid(limsConsignmentInfo.getDelegator2Id());
                    count2.setUsername(limsConsignmentInfo.getDelegator2Name());
                    count2.setUserOrg(limsConsignmentInfo.getDelegateOrgCode());
                    count2.setCaseId(limsConsignmentInfo.getCaseId());
                    count2.setMessageType(1);
                    MobileNews count3 = new MobileNews();
                    count3.setId(UuidUtil.generateUUID());
                    count3.setTitle(matchSameResult1.getCaseaName());
                    count3.setContent(CallThirdpartyTool.Content(6,countName,limsConsignmentInfo.getDelegateOrgName(),null));
                    count3.setState(0);
                    count3.setCreateDatetime(date);
                    count3.setUpdateDatetime(date);
                    count3.setType(6);
                    count3.setUserid("pc");
                    count3.setUserOrg(limsConsignmentInfo.getDelegateOrgCode());
                    count3.setCaseId(limsConsignmentInfo.getCaseId());
                    count3.setMessageType(2);
                    mobileNewsList.add(count3);
                    mobileNewsList.add(count);
                    mobileNewsList.add(count2);
                    //信息存储到消息表
                    for (MobileNews MobileNews: mobileNewsList) {
                        int insert = mobileNewsMapper.insert(MobileNews);
                        logger.info("===成功存储消息:"+insert+"条.");
                    }
                    bean.setContent(mobileNewsList);
                    //第三方唤醒app
                    LogRecordInfo logRecordInfo = CallThirdpartyTool.CallThirdpartyTool(AppUrl, bean);
                    logger.info("===唤醒app结束开始存储日志");
                    logRecordMapper.insert(logRecordInfo);
                }
                if (null != limsConsignmentInfotwo){
                    String date = DateUtils.dateToString(new Date(),"yyyy-MM-dd HH:mm:ss");
                    CallThirdpartyInfo bean = new CallThirdpartyInfo();
                    //发起人信息
                    bean.setUserid(loaUserInfo.getUserId());
                    bean.setUserName(loaUserInfo.getLoginName());
                    bean.setOrgid(loaUserInfo.getOrgId());
                    bean.setIp(IpAddressUtils.getIpAddr(request));
                    //接收人
                    List<String> phones = new ArrayList<>();
                    if (null != limsConsignmentInfotwo.getDelegator1PhoneNumber())
                        phones.add(limsConsignmentInfotwo.getDelegator1PhoneNumber());
                    if (null != limsConsignmentInfotwo.getDelegator2PhoneNumber())
                        phones.add(limsConsignmentInfotwo.getDelegator2PhoneNumber());
                    bean.setPhones(phones);
                    String countName = limsConsignmentInfotwo.getDelegator1Name()+","+limsConsignmentInfotwo.getDelegator2Name();
                    ArrayList<MobileNews> mobileNewsList = new ArrayList<>();
                    MobileNews count = new MobileNews();
                    count.setId(UuidUtil.generateUUID());
                    count.setTitle(matchSameResult1.getCaseaName());
                    count.setContent(CallThirdpartyTool.Content(2,countName,limsConsignmentInfotwo.getDelegateOrgName(),null));
                    count.setState(0);
                    count.setCreateDatetime(date);
                    count.setUpdateDatetime(date);
                    count.setType(1);
                    count.setUserid(limsConsignmentInfotwo.getDelegator1Id());
                    count.setUsername(limsConsignmentInfotwo.getDelegator1Name());
                    MobileNews count2 = new MobileNews();
                    count2.setId(UuidUtil.generateUUID());
                    count2.setTitle(matchSameResult1.getCaseaName());
                    count2.setContent(CallThirdpartyTool.Content(2,countName,limsConsignmentInfotwo.getDelegateOrgName(),null));
                    count2.setState(0);
                    count2.setCreateDatetime(date);
                    count2.setUpdateDatetime(date);
                    count2.setType(1);
                    count2.setUserid(limsConsignmentInfotwo.getDelegator2Id());
                    count2.setUsername(limsConsignmentInfotwo.getDelegator2Name());
                    mobileNewsList.add(count);
                    mobileNewsList.add(count2);
                    //信息存储到消息表
                    for (MobileNews MobileNews: mobileNewsList) {
                        int insert = mobileNewsMapper.insert(MobileNews);
                        logger.info("===成功存储消息:"+insert+"条.");
                    }
                    bean.setContent(mobileNewsList);
                    //第三方唤醒app
                    LogRecordInfo logRecordInfo = CallThirdpartyTool.CallThirdpartyTool(AppUrl, bean);
                    logger.info("===唤醒app结束开始存储日志");
                    logRecordMapper.insert(logRecordInfo);
                }


            }


        }catch (Exception e){
            resultMap.put("success" ,false);
            logger.info("确认比中失败："+e);
        }
        return resultMap;
    }

    /**
     * 确认亲缘比中（组）
     * @param request
     * @param groupIdArr
     * @return
     */
    @RequestMapping("/confirmSameRelativeCompareGroup")
    @ResponseBody
    public Map<String, Object> confirmRelativeCompareGroup(HttpServletRequest request, String groupIdArr){
        Map<String, Object> resultMap = new HashMap<>();
        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo loaUserInfo = (LoaUserInfo) subject.getPrincipal();
        if(loaUserInfo == null){
            resultMap.put("date", "redirect:/login.html?timeoutFlag=1");
            return  resultMap;
        }
        try {
            //分割
            String[] groupIdArr1 = groupIdArr.split(",");
            for(String groupId:groupIdArr1){
                MatchRelativeResult matchRelativeResult = new MatchRelativeResult();
                matchRelativeResult.setGroupId(groupId);
                matchRelativeResult.setUpdatePerson(loaUserInfo.getLoginName());
                matchRelativeResult.setUpdateDatetime(new Date());
                matchRelativeResultService.confirmRelativeCompareGroup(matchRelativeResult);
                resultMap.put("success" ,true);
            }
        }catch (Exception e){
            resultMap.put("success" ,false);
            logger.info("确认亲缘比中失败："+e);
        }
        return resultMap;
    }

    /**
     * 确认亲缘比中
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/confirmSameRelativeCompare")
    @ResponseBody
    public Map<String, Object> confirmRelativeCompare(HttpServletRequest request, String id){
        Map<String, Object> resultMap = new HashMap<>();
        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo loaUserInfo = (LoaUserInfo) subject.getPrincipal();
        if(loaUserInfo == null){
            resultMap.put("date", "redirect:/login.html?timeoutFlag=1");
            return  resultMap;
        }
        try {
            MatchRelativeResult matchRelativeResult = new MatchRelativeResult();
            matchRelativeResult.setId(id);
            matchRelativeResult.setUpdatePerson(loaUserInfo.getLoginName());
            matchRelativeResult.setUpdateDatetime(new Date());
            matchRelativeResultService.confirmRelativeCompare(matchRelativeResult);
            resultMap.put("success" ,true);
        }catch (Exception e){
            resultMap.put("success" ,false);
            logger.info("确认亲缘比中失败："+e);
        }
        return resultMap;
    }

    public MatchSameResultVo initMatchResult(MatchSameResultVo matchResult) {
        if (StringUtils.isNotBlank(matchResult.getCaseName())) {
            matchResult.setCaseName(matchResult.getCaseName().trim());
        }else{
            matchResult.setCaseName(null);
        }
        //案件id
        if (StringUtils.isBlank(matchResult.getCaseId())) {
            matchResult.setCaseId(null);
        } else {
            matchResult.setCaseId(matchResult.getCaseId().trim());
        }
        if (StringUtils.isNotBlank(matchResult.getSampleName())) {
            matchResult.setSampleName(matchResult.getSampleName().trim());
        }else{
            matchResult.setSampleName(null);
        }
        if (StringUtils.isNotBlank(matchResult.getSampleNo())) {
            matchResult.setSampleNo(matchResult.getSampleNo().trim());
        }else{
            matchResult.setSampleNo(null);
        }
        if(StringUtils.isNotBlank(matchResult.getInstoredType())){
            matchResult.setInstoredType(matchResult.getInstoredType().trim());
        }else{
            matchResult.setInstoredType(null);
        }

        if(StringUtils.isNotBlank(matchResult.getComparisonCategory())){
            matchResult.setComparisonCategory(matchResult.getComparisonCategory().trim());
        }else{
            matchResult.setComparisonCategory("05");
        }

        if(StringUtils.isNotBlank(matchResult.getDelegateOrgName())){
            matchResult.setDelegateOrgName(matchResult.getDelegateOrgName().trim());
        }else{
            matchResult.setDelegateOrgName(null);
        }

        if(StringUtils.isNotBlank(matchResult.getCaseNo())){
            matchResult.setCaseNo(matchResult.getCaseNo().trim());
        }else{
            matchResult.setCaseNo(null);
        }

        if(null != matchResult.getCreateStartDatetime()){
            matchResult.setCreateStartDatetime(matchResult.getCreateStartDatetime());
        }else{
            matchResult.setCreateStartDatetime(null);
        }

        if(null != matchResult.getCreateEndDatetime()){
            matchResult.setCreateEndDatetime(matchResult.getCreateEndDatetime());
        }else{
            matchResult.setCreateEndDatetime(null);
        }

        if(null != matchResult.getEntity().getSameCount()){
            matchResult.getEntity().setSameCount(matchResult.getEntity().getSameCount());
        }else{
            matchResult.getEntity().setSameCount(null);
        }

        if(StringUtils.isNotBlank(matchResult.getEntity().getCompareStatus())){
            matchResult.getEntity().setCompareStatus(matchResult.getEntity().getCompareStatus());
        }else{
            matchResult.getEntity().setCompareStatus("0");
        }

        return matchResult;
    }

    public MatchRelativeResultVo initMatchRelativeResult (MatchRelativeResultVo matchResult) {
        if (StringUtils.isNotBlank(matchResult.getCaseName())) {
            matchResult.setCaseName(matchResult.getCaseName().trim());
        }else{
            matchResult.setCaseName(null);
        }
        //案件id
        if (StringUtils.isBlank(matchResult.getCaseId())) {
            matchResult.setCaseId(null);
        } else {
            matchResult.setCaseId(matchResult.getCaseId().trim());
        }
        if (StringUtils.isNotBlank(matchResult.getSampleName())) {
            matchResult.setSampleName(matchResult.getSampleName().trim());
        }else{
            matchResult.setSampleName(null);
        }
        if (StringUtils.isNotBlank(matchResult.getSampleNo())) {
            matchResult.setSampleNo(matchResult.getSampleNo().trim());
        }else{
            matchResult.setSampleNo(null);
        }
        if(StringUtils.isNotBlank(matchResult.getInstoredType())){
            matchResult.setInstoredType(matchResult.getInstoredType().trim());
        }else{
            matchResult.setInstoredType(null);
        }
        if(StringUtils.isNotBlank(matchResult.getCaseNo())){
            matchResult.setCaseNo(matchResult.getCaseNo().trim());
        }else{
            matchResult.setCaseNo(null);
        }

        if(null != matchResult.getCreateStartDatetime()){
            matchResult.setCreateStartDatetime(matchResult.getCreateStartDatetime());
        }else{
            matchResult.setCreateStartDatetime(null);
        }

        if(null != matchResult.getCreateEndDatetime()){
            matchResult.setCreateEndDatetime(matchResult.getCreateEndDatetime());
        }else{
            matchResult.setCreateEndDatetime(null);
        }

        if(null != matchResult.getAcceptOrgId()){
            matchResult.setAcceptOrgId(matchResult.getAcceptOrgId());
        }else{
            matchResult.setAcceptOrgId(null);
        }

        if(null != matchResult.getEntity().getSameCount()){
            matchResult.getEntity().setSameCount(matchResult.getEntity().getSameCount());
        }else{
            matchResult.getEntity().setSameCount(null);
        }

        if(StringUtils.isNotBlank(matchResult.getEntity().getCompareStatus())){
            matchResult.getEntity().setCompareStatus(matchResult.getEntity().getCompareStatus());
        }else{
            matchResult.getEntity().setCompareStatus("0");
        }

        return matchResult;
    }
}
