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
import com.bazl.clims.utils.DateUtils;
import com.bazl.clims.utils.DictUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.*;

import static com.bazl.clims.utils.DateUtils.addDays;

/**
 * Created by Sun on 2018/12/19.
 */
@Controller
@RequestMapping("/center")
public class WaitAcceptController extends BaseController {

    @Autowired
    LimsConsignmentInfoService limsConsignmentInfoService;

    @Autowired
    LimsCaseInfoService limsCaseInfoService;
    @Autowired
    OrgInfoService orgInfoService;

    @Autowired
    PersonDetailService personDetailService;

    @Autowired
    LimsPersonInfoService limsPersonInfoService;

    @Autowired
    LimsSampleInfoDnaService limsSampleInfoDnaService;

    @Autowired
    LimsPerosnRelationService limsPerosnRelationService;

    @Autowired
    AmPersonalInfoService amPersonalInfoService;

    /**
     * 待受理案件
     *
     * @param request
     * @param query
     * @param pageInfo
     * @return
     */
    @RequestMapping("/waitAccept")
    public ModelAndView waitAccept(HttpServletRequest request, LimsCaseInfoVo query,int visitNum, PageInfo pageInfo) {
        ModelAndView view = new ModelAndView();
        try {
            if (query!=null) {
                query = resetCaseInfoQuery(query);
            }else{
                query = new LimsCaseInfoVo();
            }
            //案件状态_未受理
            query.setStatus(Constants.CASE_STATUS_01);
            //查询委托单位（分局）
            String orgParentsId = "110000000000";
            List<OrgInfo> orgInfoList = orgInfoService.selectDelegateByParentsId(orgParentsId);

            //获取当前登录用户
            Subject subject = SecurityUtils.getSubject();
            LoaUserInfo loaUserInfo = (LoaUserInfo) subject.getPrincipal();
            //获取当前用户的id
            String userOrgId = loaUserInfo.getOrgId();
            //将当前用户org_id设置进query
            if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
                userOrgId = "110230000000";
            }
            query.setUserOrdId(userOrgId);
            if (StringUtils.isBlank(query.getUserOrdId())) {
                query.setUserOrdId(orgInfoService.getUseridByOrgid(loaUserInfo.getUserId()));
            }

            //系统当前时间，72小时之前的时间
            Date date = new Date();
            Calendar calendar = new GregorianCalendar();
            calendar.add(Calendar.DATE, -3);
            date = calendar.getTime();

            //设置系统当前时间 ,如果选择时间大于超时时间，则更改为超时时间
            Date delegateStartDatetimeOld = query.getDelegateStartDatetime();
            if (query.getDelegateStartDatetime() == null || (query.getDelegateStartDatetime() != null && query.getDelegateStartDatetime().compareTo(date) < 0)) {
                query.setDelegateStartDatetime(date);
            }

            //查询主案件待受理数据
            query.setAppendFlag(Constants.APPEND_FLAG_0);
            //排序条件设置
            query.setOrderByClause("lci.DELEGATE_DATETIME desc");
            List<LimsCaseInfoVo> mainCaseInfoList = limsCaseInfoService.selectCaseInfoList(query, pageInfo);
            int mainCaseCnt = limsCaseInfoService.selectVOCount(query);
            view = initDictList();
            view.addObject("mainCaseInfoList", mainCaseInfoList);
            view.addObject("mainPageInfo", pageInfo.updatePageInfo(mainCaseCnt));
            //查询补送案件待受理数据
//            query.setAppendFlag(Constants.APPEND_FLAG_1);
//            List<LimsCaseInfoVo> reCaseInfoList = limsCaseInfoService.selectCaseInfoList(query, pageInfo);
//            int reCaseCnt = limsCaseInfoService.selectVOCount(query);
//            view.addObject("reCaseInfoList", reCaseInfoList);
            view.addObject("orgInfoList", orgInfoList);

            query.setDelegateStartDatetime(delegateStartDatetimeOld);
            view.addObject("query", query);
            view.addObject("loaUserInfo",loaUserInfo);
            view.addObject("visitNum",visitNum);
        } catch (Exception ex) {
            logger.info("查询失败:" + ex);
        }
        view.setViewName("accept/waitAccept");
        return view;
    }

    /**
     * 补送待受理案件
     *
     * @param request
     * @param query
     * @param pageInfo
     * @return
     */
    @RequestMapping("/bringWaitAccept")
    public ModelAndView bringWaitAccept(HttpServletRequest request, LimsCaseInfoVo query,int visitNum, PageInfo pageInfo) {
        ModelAndView view = new ModelAndView();
        try {
            query = resetCaseInfoQuery(query);
            query.setStatus(Constants.CASE_STATUS_01);
            //查询委托单位（分局）
            String orgParentsId = "110000000000";
            List<OrgInfo> orgInfoList = orgInfoService.selectDelegateByParentsId(orgParentsId);

            //获取当前登录用户
            Subject subject = SecurityUtils.getSubject();
            LoaUserInfo loaUserInfo = (LoaUserInfo) subject.getPrincipal();
            //获取当前用户的id
            String userOrgId = loaUserInfo.getOrgId();
            //将当前用户org_id设置进query
            if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
                userOrgId = "110230000000";
            }
            query.setUserOrdId(userOrgId);

            //系统当前时间，72小时之前的时间
            Date date = new Date();
            Calendar calendar = new GregorianCalendar();
            calendar.add(Calendar.DATE, -3);
            date = calendar.getTime();

            //设置系统当前时间 ,如果选择时间大于超时时间，则更改为超时时间
            Date delegateStartDatetimeOld = query.getDelegateStartDatetime();
            if (query.getDelegateStartDatetime() == null || (query.getDelegateStartDatetime() != null && query.getDelegateStartDatetime().compareTo(date) < 0)) {
                query.setDelegateStartDatetime(date);
            }

            //查询主案件待受理数据
//            query.setAppendFlag(Constants.APPEND_FLAG_0);
//            //排序条件设置
//            query.setOrderByClause("lci.DELEGATE_DATETIME desc");
//            List<LimsCaseInfoVo> mainCaseInfoList = limsCaseInfoService.selectCaseInfoList(query, pageInfo);
//            int mainCaseCnt = limsCaseInfoService.selectVOCount(query);

//            view.addObject("mainCaseInfoList", mainCaseInfoList);
//            view.addObject("mainPageInfo",pageInfo.updatePageInfo(mainCaseCnt) );
            //查询补送案件待受理数据
            query.setAppendFlag(Constants.APPEND_FLAG_1);
            List<LimsCaseInfoVo> reCaseInfoList = limsCaseInfoService.selectCaseInfoList(query, pageInfo);
            view = initDictList();
            int reCaseCnt = limsCaseInfoService.selectVOCount(query);
            view.addObject("reCaseInfoList", reCaseInfoList);
            view.addObject("reCasePageINfo", pageInfo.updatePageInfo(reCaseCnt));
            view.addObject("orgInfoList", orgInfoList);

            query.setDelegateStartDatetime(delegateStartDatetimeOld);
            view.addObject("query", query);
            view.addObject("loaUserInfo",loaUserInfo);
            view.addObject("visitNum",visitNum);
        } catch (Exception ex) {
            logger.info("查询失败:" + ex);
        }
        view.setViewName("accept/bringWaitAccept");
        return view;
    }


    /**
     * 超时未送检
     *
     * @param request
     * @param query
     * @param pageInfo
     * @return
     */
    @RequestMapping("/overtimeAccept")
    public ModelAndView overtimeAccept(HttpServletRequest request, LimsCaseInfoVo query,int visitNum, PageInfo pageInfo) {
        ModelAndView view = new ModelAndView();
        try {
            query = resetCaseInfoQuery(query);
            query.setStatus(Constants.CASE_STATUS_01);
            //查询委托单位（分局）
            String orgParentsId = "110000000000";
            List<OrgInfo> orgInfoList = orgInfoService.selectDelegateByParentsId(orgParentsId);

            //获取当前登录用户
            Subject subject = SecurityUtils.getSubject();
            LoaUserInfo loaUserInfo = (LoaUserInfo) subject.getPrincipal();
            //获取当前用户的id
            String userOrgId = loaUserInfo.getOrgId();
            //将当前用户org_id设置进query
            if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
                userOrgId = "110230000000";
            }
            query.setUserOrdId(userOrgId);

            //系统当前时间，72小时之前的时间
            Date date = new Date();
            Calendar calendar = new GregorianCalendar();
            calendar.add(Calendar.DATE, -3);
            date = calendar.getTime();

            //设置系统当前时间 ,如果选择时间大于超时时间，则更改为超时时间
            Date delegateEndDatetimeOld = query.getDelegateEndDatetime();
            if (query.getDelegateEndDatetime() == null || (query.getDelegateEndDatetime() != null && query.getDelegateEndDatetime().compareTo(date) > 0)) {
                query.setDelegateEndDatetime(date);
            }

            //排序条件设置
            query.setOrderByClause("lci.DELEGATE_DATETIME desc");
            //查询查询与补送数据
            List<LimsCaseInfoVo> caseInfoList = limsCaseInfoService.selectCaseInfoList(query, pageInfo);
            int caseInfoCount = limsCaseInfoService.selectVOCount(query);
            query.setDelegateEndDatetime(delegateEndDatetimeOld);
            view = initDictList();
            view.addObject("query", query);
            view.addObject("caseInfoList", caseInfoList);
            view.addObject("orgInfoList", orgInfoList);
            view.addObject("pageInfo", pageInfo.updatePageInfo(caseInfoCount));
            view.addObject("loaUserInfo",loaUserInfo);
            view.addObject("visitNum",visitNum);
        } catch (Exception ex) {
            logger.info("查询失败:" + ex);
        }
        view.setViewName("accept/overAccept");
        return view;
    }

    /**
     * 案件管理
     *
     * @param request
     * @param query
     * @param pageInfo
     * @return
     */
    @RequestMapping("/caseOffManage")
    public ModelAndView caseOffManage(HttpServletRequest request, LimsCaseInfoVo query, PageInfo pageInfo) {
        ModelAndView view = new ModelAndView();
        try {
            query = resetCaseInfoQuery(query);
            //查询委托单位（分局）
            String orgParentsId = "110000000000";
            List<OrgInfo> orgInfoList = orgInfoService.selectDelegateByParentsId(orgParentsId);

            DictItem dictItem = new DictItem();

            //查询案件类型
            dictItem.setDictTypeCode(Constants.CASE_TYPE);
            List<DictItem> caseTypeList = DictUtil.getDictList(dictItem);
            view.addObject("caseTypeList", caseTypeList);

            //案件状态CASE_STATUS
            dictItem.setDictTypeCode(Constants.CASE_STATUS);
            List<DictItem> caseStatusList = DictUtil.getDictList(dictItem);
            view.addObject("caseStatusList", caseStatusList);

            //载体
            dictItem.setDictTypeCode(Constants.SAMPLE_CARRIER);
            List<DictItem> sampleCarrierList = DictUtil.getDictList(dictItem);
            view.addObject("sampleCarrierList", sampleCarrierList);

            //获取当前登录用户
            Subject subject = SecurityUtils.getSubject();
            LoaUserInfo loaUserInfo = (LoaUserInfo) subject.getPrincipal();
            //获取当前用户的id
            String userOrgId = loaUserInfo.getOrgId();
            //将当前用户org_id设置进query
            if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
                userOrgId = "110230000000";
            }
            query.setUserOrdId(userOrgId);
            query.setStatus(Constants.CASE_STATUS_01);
            query.setQueryType(Constants.QUERY_TYPE_01);

            //查询查询与补送数据
            List<LimsCaseInfoVo> caseInfoList = limsCaseInfoService.selectCaseInfoList(query, pageInfo);
            if (null != caseInfoList && caseInfoList.size() > 0) {
                for (LimsCaseInfoVo limsCaseInfoVo : caseInfoList) {
                    if (StringUtils.isNotBlank(limsCaseInfoVo.getStatus()) && ("01").equals(limsCaseInfoVo.getStatus())) {
                        limsCaseInfoVo.setStatus("未受理");
                    } else if (StringUtils.isNotBlank(limsCaseInfoVo.getStatus()) && ("02").equals(limsCaseInfoVo.getStatus())) {
                        limsCaseInfoVo.setStatus("在检验");
                    }
                }
            }
            int caseInfoCount = limsCaseInfoService.selectVOCount(query);
            view = initDictList();
            view.addObject("query", query);
            view.addObject("caseInfoList", caseInfoList);
            view.addObject("orgInfoList", orgInfoList);
            view.addObject("pageInfo", pageInfo.updatePageInfo(caseInfoCount));
        } catch (Exception ex) {
            logger.info("查询失败:" + ex);
        }
        view.setViewName("accept/caseOffManage");
        return view;
    }

    @RequestMapping("/offCaseReg")
    public ModelAndView offCaseReg(HttpServletRequest request, String caseId, String consignmentId) {
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
            List<DictItem> casePropertyList1 = new LinkedList<>();
            dictItem.setDictTypeCode(Constants.CASE_PROPERTY);
            List<DictItem> casePropertyList = DictUtil.getDictList(dictItem);
            for (DictItem dictItem1 : casePropertyList) {
                if (("06").equals(dictItem1.getDictCode())) {
                    casePropertyList1.add(dictItem1);
                } else if (("09").equals(dictItem1.getDictCode())) {
                    casePropertyList1.add(dictItem1);
                } else if (("10").equals(dictItem1.getDictCode())) {
                    casePropertyList1.add(dictItem1);
                } else if (("11").equals(dictItem1.getDictCode())) {
                    casePropertyList1.add(dictItem1);
                } else if (("19").equals(dictItem1.getDictCode())) {
                    casePropertyList1.add(dictItem1);
                } else if (("20").equals(dictItem1.getDictCode())) {
                    casePropertyList1.add(dictItem1);
                }
            }
            modelAndView.addObject("casePropertyList", casePropertyList1);

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
            if (StringUtils.isBlank(operateUser.getOrgId()) || operateUser.getOrgId().contains("110023")) {
                orgInfoList = orgInfoService.selectAll();
                personalInfoList = amPersonalInfoService.queryAmPersonalInfoLIst(null);
            } else {
                orgInfoList = orgInfoService.selectDelegateByParentsId(operateUser.getOrgId());
                personalInfoList = amPersonalInfoService.queryAmPersonalInfoLIst(operateUser.getOrgId());
            }
            //获取委托人管理数据
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
            List<LimsSampleInfoDna> sampleInfoDnaList = limsSampleInfoDnaService.selectByCaseIdAndRy(sampleInfoDna);
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
            List<LimsSampleInfoDna> sampleInfoDnaList1 = limsSampleInfoDnaService.selectByCaseIdAndWz(sampleInfoDna);

            //查询委托的单位code和名称
            OrgInfo subOrgInfo = new OrgInfo();
            subOrgInfo.setOrgCode(consignatioInfo.getDelegateOrgCode());
            subOrgInfo.setOrgName(consignatioInfo.getDelegateOrgName());
            modelAndView.addObject("subOrgInfo", subOrgInfo);


            //查询分局单位
            OrgInfo orgInfo = orgInfoService.selectByPrimaryKey(operateUser.getOrgId());
            modelAndView.addObject("orgInfo", orgInfo);
            //查询法医中心
            OrgInfo forensicCenterorg = orgInfoService.selectByPrimaryKey(Constants.FORENSIC_CENTER_ORG_ID);
            modelAndView.addObject("forensicCenterorg", forensicCenterorg);
            //创建orgInfoList点击鉴定中心的选择，可以多选
            List<OrgInfo> orgInfos = new ArrayList<>();
            if (StringUtils.isNotBlank(orgInfo.getOrgQualification())) {
                orgInfos.add(orgInfo);
                orgInfos.add(forensicCenterorg);
            }
            modelAndView.addObject("orgInfos", orgInfos);

            modelAndView.addObject("consignatioInfo", consignatioInfo);
            modelAndView.addObject("limsCaseInfo", limsCaseInfo);
            modelAndView.addObject("limsPersonInfoList", limsPersonInfoList);
            modelAndView.addObject("sampleInfoDnaList", sampleInfoDnaList);
            modelAndView.addObject("limsSampleInfoList", sampleInfoDnaList1);
        } catch (Exception ex) {
            logger.info("查询失败:" + ex);
        }
        modelAndView.setViewName("accept/offCaseReg");
        return modelAndView;
    }

    /**
     * 案件管理
     *
     * @param request
     * @param query
     * @param pageInfo
     * @return
     */
    @RequestMapping("/caseManage")
    @SuppressWarnings("all")
    public ModelAndView caseManage(HttpServletRequest request, LimsCaseInfoVo query, PageInfo pageInfo) {
        ModelAndView view = new ModelAndView();
        try {

            //query.setStatus(Constants.CASE_STATUS_02);
            if (query !=null) {
                query = resetCaseInfoQuery(query);
            }else {
                query = new LimsCaseInfoVo();
            }
            //查询受理单位
            List<OrgInfo> acceptOrgList = orgInfoService.selectAcceptOrgList();

            DictItem dictItem = new DictItem();

            //查询案件类型
            dictItem.setDictTypeCode(Constants.CASE_TYPE);
            List<DictItem> caseTypeList = DictUtil.getDictList(dictItem);
            view.addObject("caseTypeList", caseTypeList);

            //案件状态CASE_STATUS
            dictItem.setDictTypeCode(Constants.CASE_STATUS);
            List<DictItem> caseStatusList = DictUtil.getDictList(dictItem);
            view.addObject("caseStatusList", caseStatusList);

            //载体
            dictItem.setDictTypeCode(Constants.SAMPLE_CARRIER);
            List<DictItem> sampleCarrierList = DictUtil.getDictList(dictItem);
            view.addObject("sampleCarrierList", sampleCarrierList);

            //获取当前登录用户
            Subject subject = SecurityUtils.getSubject();
            LoaUserInfo loaUserInfo = (LoaUserInfo) subject.getPrincipal();
            //获取当前用户的id
            String userOrgId = loaUserInfo.getOrgId();
            //将当前用户org_id设置进query
            if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
                userOrgId = "110230000000";
            }
            query.setUserOrdId(userOrgId);

            //查询委托单位（分局）
            String orgParentsId = "110000000000";
            if(StringUtils.isNotBlank(userOrgId)){
                orgParentsId = userOrgId;
            }
            List<OrgInfo> orgInfoList = orgInfoService.selectDelegateByParentsId(orgParentsId);


            if(StringUtils.isNotBlank(query.getAcceptOrgId())){
                query.setAcceptOrgId(query.getAcceptOrgId());
            }else{
                query.setAcceptOrgId(userOrgId);
            }

            //获取受理人信息
            List<AmPersonalInfoVo> amPersonalInfoVoList = amPersonalInfoService.queryAmPersonalInfoVoList(loaUserInfo.getOrgId());

            //判断受理人id是否为空
            if (StringUtils.isNotBlank(query.getAcceptorId())) {
                //查询全部
                if(Constants.SELECT_ACCEPTOR_ID.equals(query.getAcceptorId())){
                    query.setAcceptorId(null);
                }else {
                    query.setAcceptorId(query.getAcceptorId());
                }

            }else {

                if (!CollectionUtils.isEmpty(amPersonalInfoVoList)) {
                    for (AmPersonalInfoVo amPersonalInfoVo : amPersonalInfoVoList) {
                        if (amPersonalInfoVo.getEntity().getPersonalId().equals(loaUserInfo.getPersonalId())) {
                            query.setAcceptorId(loaUserInfo.getPersonalId());
                            break;
                        }
                    }
                }
            }

            //排序条件设置
            if("1".equals(query.getAppendFlag())){
//                query.setOrderByClause("lci.ACCEPT_DATETIME desc");
                query.setOrderByClause("lci.ACCEPT_DATETIME desc");
            }else{
                query.setOrderByClause("lc.case_no desc nulls last, lci.ACCEPT_DATETIME asc");
            }


            if (null != query.getDelegateEndDatetime()) {//解决时间无时分秒造成的00:00:00查询
                query.setDelegateEndDatetime(addDays(query.getDelegateEndDatetime(), 1));//增加一天
            }
            if (null != query.getAcceptEndDatetime()) {//解决时间无时分秒造成的00:00:00查询
                query.setAcceptEndDatetime(addDays(query.getAcceptEndDatetime(), 1));//增加一天
            }
            //查询查询与补送数据
            List<LimsCaseInfoVo> caseInfoList = limsCaseInfoService.selectCaseInfoList(query, pageInfo);
            if (null != query.getDelegateEndDatetime()) {//解决时间无时分秒造成的00:00:00查询
                query.setDelegateEndDatetime(addDays(query.getDelegateEndDatetime(), -1));//减少一天
            }
            if (null != query.getAcceptEndDatetime()) {//解决时间无时分秒造成的00:00:00查询
                query.setAcceptEndDatetime(addDays(query.getAcceptEndDatetime(), -1));//减少一天
            }
            if (null != caseInfoList && caseInfoList.size() > 0) {
                for (LimsCaseInfoVo limsCaseInfoVo : caseInfoList) {
                    if (StringUtils.isNotBlank(limsCaseInfoVo.getStatus()) && ("01").equals(limsCaseInfoVo.getStatus())) {
                        limsCaseInfoVo.setStatus("未受理");
                    } else if (StringUtils.isNotBlank(limsCaseInfoVo.getStatus()) && ("02").equals(limsCaseInfoVo.getStatus())) {
                        limsCaseInfoVo.setStatus("在检验");
                    } else if (StringUtils.isNotBlank(limsCaseInfoVo.getStatus()) && ("03").equals(limsCaseInfoVo.getStatus())) {
                        limsCaseInfoVo.setStatus("已完成");
                    } else if (StringUtils.isNotBlank(limsCaseInfoVo.getStatus()) && ("04").equals(limsCaseInfoVo.getStatus())) {
                        limsCaseInfoVo.setStatus("已退案");
                    }
                    if (!CollectionUtils.isEmpty(amPersonalInfoVoList)) {
                        for (AmPersonalInfoVo amPersonalInfoVo : amPersonalInfoVoList) {
                            if (amPersonalInfoVo.getEntity().getPersonalId().equals(limsCaseInfoVo.getAcceptorId())) {
                                limsCaseInfoVo.setAcceptorName(amPersonalInfoVo.getEntity().getFullName());
                                break;
                            }
                        }
                    }
                    if (!CollectionUtils.isEmpty(amPersonalInfoVoList)) {
                        for (AmPersonalInfoVo amPersonalInfoVo : amPersonalInfoVoList) {
                            if (amPersonalInfoVo.getEntity().getPersonalId().equals(limsCaseInfoVo.getEntity().getFirstChecker())) {
                                limsCaseInfoVo.setFirstCheckerName(amPersonalInfoVo.getEntity().getFullName());
                                break;
                            }
                        }
                    }

                }
            }

            int caseInfoCount = limsCaseInfoService.selectVOCount(query);

            //点击查询按钮 查询案件数  补送数  样本数  人员数
            assert caseInfoList != null;
            if(StringUtils.isNotBlank(query.getQueryFlag()) && query.getQueryFlag().equals(Constants.APPEND_FLAG_1) && caseInfoList.size()>0){
                List<LimsCaseInfoVo> limsCaseInfoVoList = limsCaseInfoService.selectVOPaginationExportList(query);
                query.setCaseCnt(caseInfoCount);
                for(LimsCaseInfoVo caseInfo:limsCaseInfoVoList){
                    List<LimsConsignmentInfo> limsConsignmentInfoList = limsConsignmentInfoService.selectByCaseId(caseInfo.getEntity().getCaseId());
                    query.setAppendCnt(limsConsignmentInfoList.size()-1+query.getAppendCnt());

                    LimsSampleInfoDna limsSampleInfo = new LimsSampleInfoDna();
                    limsSampleInfo.setCaseId(caseInfo.getEntity().getCaseId());
                    limsSampleInfo.setSampleFlag(Constants.SAMPLE_FLAG_0);
                    query.setSampleCnt(limsSampleInfoDnaService.selectSampleCountByCaseId(limsSampleInfo)+query.getSampleCnt());

                    limsSampleInfo.setSampleFlag(Constants.SAMPLE_FLAG_1);
                    query.setPersonSampleCnt(limsSampleInfoDnaService.selectSampleCountByCaseId(limsSampleInfo)+query.getPersonSampleCnt());
                }
            }


            view = initDictList();
            view.addObject("query", query);
            view.addObject("caseInfoList", caseInfoList);
            view.addObject("orgInfoList", orgInfoList);
            view.addObject("appendFlag", query.getAppendFlag());
            view.addObject("acceptOrgList", acceptOrgList);
            view.addObject("userOrgId", userOrgId);
            view.addObject("amPersonalInfoVoList", amPersonalInfoVoList);
            view.addObject("pageInfo", pageInfo.updatePageInfo(caseInfoCount));
        } catch (Exception ex) {
            logger.info("查询失败:" + ex);
        }
        view.setViewName("accept/caseManage");
        return view;
    }


    private LimsCaseInfoVo resetCaseInfoQuery(LimsCaseInfoVo query) throws ParseException {
        //案件状态
        if (StringUtils.isBlank(query.getEntity().getCaseStatus())) {
            query.getEntity().setCaseStatus(null);
        } else {
            query.getEntity().setCaseStatus(query.getEntity().getCaseStatus());
        }
        //现堪编号A号
        if (StringUtils.isBlank(query.getEntity().getXkANo())) {
            query.getEntity().setXkANo(null);
        } else {
            query.getEntity().setXkANo(query.getEntity().getXkANo());
        }
        //案件名称
        if (StringUtils.isBlank(query.getEntity().getCaseName())) {
            query.getEntity().setCaseName(null);
        } else {
            query.getEntity().setCaseName(query.getEntity().getCaseName().trim());
        }
        //委托单位机构编码
        if (StringUtils.isBlank(query.getDelegateOrgCode())) {
            query.setDelegateOrgCode(null);
        } else {
            query.setDelegateOrgCode(query.getDelegateOrgCode().trim());
        }
        //案件编号
        if (StringUtils.isBlank(query.getEntity().getCaseNo())) {
            query.getEntity().setCaseNo(null);
        } else {
            query.getEntity().setCaseNo(query.getEntity().getCaseNo().trim());
        }
        //现勘编号
        if (StringUtils.isBlank(query.getEntity().getCaseXkNo())) {
            query.getEntity().setCaseXkNo(null);
        } else {
            query.getEntity().setCaseXkNo(query.getEntity().getCaseXkNo().trim());
        }
        //案件性质
        if (StringUtils.isBlank(query.getEntity().getCaseProperty())) {
            query.getEntity().setCaseProperty(null);
        } else {
            query.getEntity().setCaseProperty(query.getEntity().getCaseProperty().trim());
        }
        //案件类型
        if (StringUtils.isBlank(query.getEntity().getCaseType())) {
            query.getEntity().setCaseType(null);
        } else {
            query.getEntity().setCaseType(query.getEntity().getCaseType().trim());
        }
        //委托人姓名
        if (StringUtils.isBlank(query.getDelegator1Name())) {
            query.setDelegator1Name(null);
        } else {
            query.setDelegator1Name(query.getDelegator1Name().trim());
        }
        //开始委托时间
        if (null == query.getDelegateStartDatetime()) {
            query.setDelegateStartDatetime(null);
        } else {
            query.setDelegateStartDatetime(query.getDelegateStartDatetime());
        }
        //结束委托时间
        if (null == query.getDelegateEndDatetime()) {
            query.setDelegateEndDatetime(null);
        } else {
            query.setDelegateEndDatetime(query.getDelegateEndDatetime());
        }
        //案件状态
        if (query.getStatus() == null) {
            query.setStatus("02");
        } else if (query.getStatus() == "") {
            query.setStatus(null);
        } else {
            query.setStatus(query.getStatus());
        }
        //人员姓名
        if (StringUtils.isBlank(query.getPersonName())) {
            query.setPersonName(null);
        } else {
            query.setPersonName(query.getPersonName().trim());
        }
        //人员身份证号码
        if (StringUtils.isBlank(query.getPersonIdCard())) {
            query.setPersonIdCard(null);
        } else {
            query.setPersonIdCard(query.getPersonIdCard().trim());
        }
        //受理人
        if (StringUtils.isBlank(query.getAcceptorId())) {
            query.setAcceptorId(null);
        } else {
            query.setAcceptorId(query.getAcceptorId().trim());
        }
        //受理结束时间
        if (query.getDelegateEndDatetime() == null) {
            query.setDelegateEndDatetime(null);
        } else {
            String oldTime = DateUtils.dateToString(query.getDelegateEndDatetime(), DateUtils.DATE);
            Date newTime = DateUtils.stringToDate(oldTime + " 23:59:59",DateUtils.MIN );
            query.setDelegateEndDatetime(newTime);
        }
        //样本编号
        if (StringUtils.isBlank(query.getSampleNo())) {
            query.setSampleNo(null);
        }else {
            query.setSampleNo(query.getSampleNo().trim());
        }
        //样本名称
        if (StringUtils.isBlank(query.getSampleName())) {
            query.setSampleName(null);
        }else {
            query.setSampleName(query.getSampleName().trim());
        }
        //人员姓名
        if (StringUtils.isBlank(query.getPersonName())){
            query.setPersonName(null);
        }else {
            query.setPersonName(query.getPersonName().trim());
        }
        //人员身份证号码
        if (StringUtils.isBlank(query.getPersonIdCard())){
            query.setPersonIdCard(null);
        }else {
            query.setPersonIdCard(query.getPersonIdCard().trim());
        }
        return query;
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
