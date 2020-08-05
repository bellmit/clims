package com.bazl.clims.controller.center;

import com.bazl.clims.common.Constants;
import com.bazl.clims.controller.BaseController;
import com.bazl.clims.model.DictItem;
import com.bazl.clims.model.LoaUserInfo;
import com.bazl.clims.model.PageInfo;
import com.bazl.clims.model.po.*;
import com.bazl.clims.model.vo.AmPersonalInfoVo;
import com.bazl.clims.model.vo.LimsCaseInfoVo;
import com.bazl.clims.service.*;
import com.bazl.clims.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static com.bazl.clims.utils.DateUtils.addDays;

/**
 * @author wanghaiyang
 * @date 2019/2/21.
 */
@Controller
@RequestMapping("/center")
public class IdentifyBookController extends BaseController {

    @Autowired
    LimsCaseInfoService limsCaseInfoService;
    @Autowired
    OrgInfoService orgInfoService;
    @Autowired
    LimsConsignmentInfoService limsConsignmentInfoService;
    @Autowired
    DownloadFileUtils downloadFileUtils;
    @Autowired
    AmPersonalInfoService amPersonalInfoService;
    @Autowired
    ExaminerService examinerService;
    @Autowired
    LimsPersonInfoService limsPersonInfoService;
    @Autowired
    LimsSampleInfoDnaService limsSampleInfoDnaService;


    /**
     * 鉴定书管理
     *
     * @param request
     * @param query
     * @param pageInfo
     * @return
     */
    @RequestMapping("/appraisalManage")
    public ModelAndView caseManage(HttpServletRequest request, LimsCaseInfoVo query, PageInfo pageInfo) {
        ModelAndView view = new ModelAndView();

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if (operateUser == null) {
            view.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return view;
        }

        try {
            query = InitializationData.resetCaseInfoQuery(query);

            //查询委托单位（分局）
            String orgParentsId = "110000000000";
            List<OrgInfo> orgInfoList = orgInfoService.selectDelegateByParentsId(orgParentsId);

            //获取当前用户的id
            String userOrgId = operateUser.getOrgId();
            //将当前用户org_id设置进query
            if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
                userOrgId = "110230000000";
            }
            query.setUserOrdId(userOrgId);
            query.setAcceptOrgId(userOrgId);

            //获取受理人信息
            List<AmPersonalInfoVo> amPersonalInfoVoList = amPersonalInfoService.queryAmPersonalInfoVoList(operateUser.getOrgId());

            //判断受理人id是否为空
            if (StringUtils.isNotBlank(query.getAcceptorId())) {
                //查询全部
                if (Constants.SELECT_ACCEPTOR_ID.equals(query.getAcceptorId())) {
                    query.setAcceptorId(null);
                } else {
                    query.setAcceptorId(query.getAcceptorId());
                }
            } else {

                if (!CollectionUtils.isEmpty(amPersonalInfoVoList)) {
                    for (AmPersonalInfoVo amPersonalInfoVo : amPersonalInfoVoList) {
                        if (amPersonalInfoVo.getEntity().getPersonalId().equals(operateUser.getPersonalId())) {
                            query.setAcceptorId(operateUser.getPersonalId());
                            break;
                        }
                    }
                }
            }

            //案件状态在检验和已完成
            query.setCaseStatusList(Arrays.asList(Constants.CASE_STATUS_02, Constants.CASE_STATUS_03));
            //不补送
            query.setAppendFlag(Constants.APPEND_FLAG_0);

            //排序条件设置
            query.setOrderByClause("lci.ACCEPT_DATETIME desc nulls last,lc.case_no desc");
            //查询主案件信息
            if (null != query.getDelegateEndDatetime()) {//解决时间无时分秒造成的00:00:00查询
                query.setDelegateEndDatetime(addDays(query.getDelegateEndDatetime(), 1));//增加一天
            }
            List<LimsCaseInfoVo> caseInfoList = limsCaseInfoService.selectCaseInfoList(query, pageInfo);

            //查询案件数量
            int caseInfoCount = limsCaseInfoService.selectVOCount(query);
            if (null != query.getDelegateEndDatetime()) {//解决时间无时分秒造成的00:00:00查询
                query.setDelegateEndDatetime(addDays(query.getDelegateEndDatetime(), -1));//减少一天
            }
            view = InitializationData.initDictList();
            view.addObject("query", query);
            view.addObject("caseInfoList", caseInfoList);
            view.addObject("orgInfoList", orgInfoList);
            view.addObject("amPersonalInfoVoList", amPersonalInfoVoList);
            view.addObject("pageInfo", pageInfo.updatePageInfo(caseInfoCount));
        } catch (Exception ex) {
            logger.info("查询失败:" + ex);
        }

        view.setViewName("identifyBook/appraisalManage");
        return view;
    }

    /**
     * 查看案情详情
     */
    @RequestMapping("/vewDetailsCase")
    public ModelAndView vewDetailsCase(HttpServletRequest request, String caseId, String consignmentId) {
        ModelAndView modelAndView = initDictList();

        try {
            //获取当前登录人信息
            Subject subject = SecurityUtils.getSubject();
            LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
            if (operateUser == null) {
                modelAndView.setViewName("redirect:/login.html?timeoutFlag=1");
                return modelAndView;
            }

            DictItem dictItem = new DictItem();
            //案件类型
            dictItem.setDictTypeCode(Constants.CASE_TYPE);
            List<DictItem> caseTypeList = DictUtil.getDictList(dictItem);
            modelAndView.addObject("caseTypeList", caseTypeList);

            //案件性质
            dictItem.setDictTypeCode(Constants.CASE_PROPERTY);
            List<DictItem> casePropertyList = DictUtil.getDictList(dictItem);
            modelAndView.addObject("casePropertyList", casePropertyList);

            //案件级别
            dictItem.setDictTypeCode(Constants.CASE_LEVEL);
            List<DictItem> caseLevelList = DictUtil.getDictList(dictItem);
            modelAndView.addObject("caseLevelList", caseLevelList);

            //人员类型
            dictItem.setDictTypeCode(Constants.PERSON_TYPE);
            List<DictItem> personTypeList = DictUtil.getDictList(dictItem);
            modelAndView.addObject("personTypeList", personTypeList);

            //性别
            dictItem.setDictTypeCode(Constants.GENDER);
            List<DictItem> genderList = DictUtil.getDictList(dictItem);
            modelAndView.addObject("genderList", genderList);

            //检材类型
            dictItem.setDictTypeCode(Constants.SAMPLE_TYPE);
            List<DictItem> sampleTypeList = DictUtil.getDictList(dictItem);
            modelAndView.addObject("sampleTypeList", sampleTypeList);

            //包装方法PACK_METHOD
            dictItem.setDictTypeCode(Constants.PACK_METHOD);
            List<DictItem> packMethodList = DictUtil.getDictList(dictItem);
            modelAndView.addObject("packMethodList", packMethodList);

            //案件状态CASE_STATUS
            dictItem.setDictTypeCode(Constants.CASE_STATUS);
            List<DictItem> caseStatusList = DictUtil.getDictList(dictItem);
            modelAndView.addObject("caseStatusList", caseStatusList);

            //职务
            dictItem.setDictTypeCode(Constants.POSITION_LIST);
            List<DictItem> positionList = DictUtil.getDictList(dictItem);
            modelAndView.addObject("positionList", positionList);

            //亲缘关系
            dictItem.setDictTypeCode(Constants.RELATION_TYPE);
            List<DictItem> relationTypeList = DictUtil.getDictList(dictItem);
            modelAndView.addObject("relationTypeList", relationTypeList);

            //提取方法
            dictItem.setDictTypeCode(Constants.EXTRACT_METHOD);
            List<DictItem> extractMethodList = DictUtil.getDictList(dictItem);
            modelAndView.addObject("extractMethodList", extractMethodList);

            //载体
            dictItem.setDictTypeCode(Constants.SAMPLE_CARRIER);
            List<DictItem> sampleCarrierList = DictUtil.getDictList(dictItem);
            modelAndView.addObject("sampleCarrierList", sampleCarrierList);


            //根据委托id查询委托信息
            LimsConsignmentInfo consignatioInfo = limsConsignmentInfoService.selectByConsignmentId(consignmentId);
            //根据案件id查询案件信息
            LimsCaseInfo limsCaseInfo = limsCaseInfoService.selectByCaseId(caseId);

            //分局
            List<OrgInfo> orgInfoList = new ArrayList<>();
            List<AmPersonalInfo> personalInfoList = new ArrayList<>();
            if (StringUtils.isBlank(operateUser.getOrgId()) || operateUser.getOrgId().contains("110023") || operateUser.getOrgId().contains("11023")) {
                orgInfoList = orgInfoService.selectAll();
                personalInfoList = amPersonalInfoService.queryAmPersonalInfoLIst(null);
            } else {
                orgInfoList = orgInfoService.selectDelegateByParentsId(operateUser.getOrgId());
                personalInfoList = amPersonalInfoService.queryAmPersonalInfoLIst(operateUser.getOrgId());
            }
//            List<OrgInfo> orgInfoList = orgInfoService.selectDelegateByParentsId(operateUser.getOrgId());
            //获取委托人管理数据
//            List<AmPersonalInfo> personalInfoList = amPersonalInfoService.queryAmPersonalInfoLIst(operateUser.getOrgId());
            modelAndView.addObject("personalInfoList", personalInfoList);
            modelAndView.addObject("orgInfoList", orgInfoList);

            //根据案件id查询人员信息
            LimsPersonInfo limsPersonInfo = new LimsPersonInfo();
            limsPersonInfo.setConsignmentId(consignmentId);
            limsPersonInfo.setCaseId(caseId);
            List<LimsPersonInfo> limsPersonInfoList = limsPersonInfoService.selectByCaseIdAndConsignmentId(limsPersonInfo);
            //根据案件id查询样本信息
            LimsSampleInfoDna sampleInfoDna = new LimsSampleInfoDna();
            sampleInfoDna.setCaseId(caseId);
            sampleInfoDna.setConsignmentId(consignmentId);
            LinkedList<LimsSampleInfoDna> sampleInfoDnaList = limsSampleInfoDnaService.selectByCaseIdAndRy(sampleInfoDna);
            if (null != limsPersonInfoList && limsPersonInfoList.size() > 0 && null != sampleInfoDnaList && sampleInfoDnaList.size() > 0) {
                for (LimsPersonInfo limsPersonInfo1 : limsPersonInfoList) {
                    for (LimsSampleInfoDna sampleInfoDna2 : sampleInfoDnaList) {
                        if (limsPersonInfo1.getPersonId().equals(sampleInfoDna2.getLinkId())) {
                            sampleInfoDna2.setPersonName(limsPersonInfo1.getPersonName());
                        }
                    }
                }
            }

            //根据案件id查询物证信息
            LinkedList<LimsSampleInfoDna> sampleInfoDnaList1 = limsSampleInfoDnaService.selectByCaseIdAndWz(sampleInfoDna);

            //查询委托的单位code和名称
            OrgInfo orgInfo = new OrgInfo();
            orgInfo.setOrgCode(consignatioInfo.getDelegateOrgCode());
            orgInfo.setOrgName(consignatioInfo.getDelegateOrgName());
            modelAndView.addObject("orgInfo", orgInfo);

            modelAndView.addObject("consignatioInfo", consignatioInfo);
            modelAndView.addObject("limsCaseInfo", limsCaseInfo);
            modelAndView.addObject("limsPersonInfoList", limsPersonInfoList);
            modelAndView.addObject("sampleInfoDnaList", sampleInfoDnaList);
            modelAndView.addObject("limsSampleInfoList", sampleInfoDnaList1);
            modelAndView.addObject("ipAddr", IpAddressUtils.getIpAddr(request));
        } catch (Exception ex) {
            logger.info("查询失败:" + ex);
        }
        modelAndView.setViewName("identifyBook/vewDetailsCase");
        return modelAndView;
    }

    /**
     * 生成鉴定书
     *
     * @param request
     * @param caseId
     * @return
     */
    @RequestMapping("/generateIdentifyBook")
    @ResponseBody
    public Map<String, Object> generateIdentifyBook(HttpServletRequest request, String caseId) {
        Map<String, Object> result = new HashMap<>();
        String tandemStatus = "0";
        result = downloadFileUtils.generateIdentifyBook(request, caseId,tandemStatus);

        return result;
    }

    /**
     * 生成串并鉴定书
     *
     * @param request
     * @param caseId
     * @return
     */
    @RequestMapping("/generateChuanBingIdentifyBook")
    @ResponseBody
    public Map<String, Object> generateChuanBingIdentifyBook(HttpServletRequest request, String caseId, String groupId) {
        Map<String, Object> result = new HashMap<>();
        String tandemStatus = "1";
        result = downloadFileUtils.generateChuanBingIdentifyBook(request, caseId, groupId,tandemStatus);

        return result;
    }

    /**
     * 保存比对结果
     *
     * @param request
     * @param examiner
     * @return
     */
    @RequestMapping(value = "/saveExaminer", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> saveExaminer(HttpServletRequest request, @RequestBody Examiner examiner) {
        Map<String, Object> result = new HashMap<String, Object>();

        List<Examiner> examinerList = examinerService.selectListByCaseId(examiner.getCaseId());

        try {
            if (ListUtils.isNullOrEmptyList(examinerList)) {
                examiner.setId(UUID.randomUUID().toString());

                examinerService.insert(examiner);
            } else {
                Examiner exa = examinerList.get(0);
                exa.setInspector1(examiner.getInspector1());
                exa.setInspector2(examiner.getInspector2());
                exa.setInspector3(examiner.getInspector3());

                //法医师职称
                exa.setTitleOne(examiner.getTitleOne());
                exa.setTitleTwo(examiner.getTitleTwo());
                exa.setTitleThree(examiner.getTitleThree());

                examinerService.update(exa);
            }
            result.put("success", true);
        } catch (Exception e) {
            e.getStackTrace();
            result.put("success", false);
        }

        return result;
    }

    /**
     * 生成本案比对鉴定书
     *
     * @param request
     * @return
     */
    @RequestMapping("/generateTestimonial")
    public ModelAndView caseCompareBook(HttpServletRequest request, String caseId) {
        ModelAndView view = new ModelAndView();

        LimsCaseInfo limsCaseInfo = limsCaseInfoService.selectByCaseId(caseId);

        view.addObject("limsCaseInfo", limsCaseInfo);

        view.setViewName("identifyBook/generateTestimonial");
        return view;
    }


    /**
     * 案卷打印
     *
     * @param request
     * @param query
     * @param pageInfo
     * @return
     */
    @RequestMapping("/booksPrinted")
    public ModelAndView booksPrinted(HttpServletRequest request, LimsCaseInfoVo query, PageInfo pageInfo) {
        ModelAndView view = new ModelAndView();

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if (operateUser == null) {
            view.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return view;
        }

        try {
            query = InitializationData.resetCaseInfoQuery(query);

            //查询委托单位（分局）
            String orgParentsId = "110000000000";
            List<OrgInfo> orgInfoList = orgInfoService.selectDelegateByParentsId(orgParentsId);

            //获取当前用户的id
            String userOrgId = operateUser.getOrgId();
            //将当前用户org_id设置进query
            if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
                userOrgId = "110230000000";
            }
            query.setUserOrdId(userOrgId);
            query.setAcceptOrgId(userOrgId);

            //案件状态在检验和已完成
            query.setCaseStatusList(Arrays.asList(Constants.CASE_STATUS_02, Constants.CASE_STATUS_03));
            //不补送
            query.setAppendFlag(Constants.APPEND_FLAG_0);

            //排序条件设置
            query.setOrderByClause("lci.ACCEPT_DATETIME desc nulls last,lc.case_no desc");
            //查询主案件信息
            List<LimsCaseInfoVo> caseInfoList = limsCaseInfoService.selectCaseInfoList(query, pageInfo);
            //查询案件数量
            int caseInfoCount = limsCaseInfoService.selectVOCount(query);
            view = InitializationData.initDictList();
            view.addObject("query", query);
            view.addObject("caseInfoList", caseInfoList);
            view.addObject("orgInfoList", orgInfoList);
            view.addObject("pageInfo", pageInfo.updatePageInfo(caseInfoCount));
        } catch (Exception ex) {
            logger.info("查询失败:" + ex);
        }

        view.setViewName("identifyBook/booksPrinted");
        return view;
    }


    /**
     * 查询字典项
     *
     * @return
     */
    private ModelAndView initDictList() {
        ModelAndView modelAndView = new ModelAndView();
        /*  字典 */

        DictItem dictItem = new DictItem();
        //案件类型
        dictItem.setDictTypeCode(Constants.CASE_TYPE);
        List<DictItem> caseTypeList = DictUtil.getDictList(dictItem);

        //案件性质
        dictItem.setDictTypeCode(Constants.CASE_PROPERTY);
        List<DictItem> casePropertyList = DictUtil.getDictList(dictItem);

        //案件级别
        dictItem.setDictTypeCode(Constants.CASE_LEVEL);
        List<DictItem> caseLevelList = DictUtil.getDictList(dictItem);

        //人员类型
        dictItem.setDictTypeCode(Constants.PERSON_TYPE);
        List<DictItem> memberTypeList = DictUtil.getDictList(dictItem);

        //性别
        dictItem.setDictTypeCode(Constants.GENDER);
        List<DictItem> genderList = DictUtil.getDictList(dictItem);

        //检材类型
        dictItem.setDictTypeCode(Constants.SAMPLE_TYPE);
        List<DictItem> sampleTypeList = DictUtil.getDictList(dictItem);

        //包装方法PACK_METHOD
        dictItem.setDictTypeCode(Constants.PACK_METHOD);
        List<DictItem> packMethodList = DictUtil.getDictList(dictItem);

        //案件状态CASE_STATUS
        dictItem.setDictTypeCode(Constants.CASE_STATUS);
        List<DictItem> caseStatusList = DictUtil.getDictList(dictItem);

        dictItem.setDictTypeCode(Constants.POSITION_LIST);
        List<DictItem> positionList = DictUtil.getDictList(dictItem);

        modelAndView.addObject("caseTypeList", caseTypeList);
        modelAndView.addObject("casePropertyList", casePropertyList);
        modelAndView.addObject("caseLevelList", caseLevelList);
        modelAndView.addObject("memberTypeList", memberTypeList);
        modelAndView.addObject("sampleTypeList", sampleTypeList);
        modelAndView.addObject("genderList", genderList);
        modelAndView.addObject("packMethodList", packMethodList);
        modelAndView.addObject("caseStatusList", caseStatusList);
        modelAndView.addObject("positionList", positionList);

        return modelAndView;
    }
}
