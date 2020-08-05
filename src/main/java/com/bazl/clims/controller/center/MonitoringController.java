package com.bazl.clims.controller.center;

import com.bazl.clims.common.Constants;
import com.bazl.clims.model.LoaUserInfo;
import com.bazl.clims.model.PageInfo;
import com.bazl.clims.model.po.LimsConsignmentInfo;
import com.bazl.clims.model.po.LimsSampleInfoDna;
import com.bazl.clims.model.po.QueueSample;
import com.bazl.clims.model.vo.*;
import com.bazl.clims.service.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by chaiyajie on 2019/4/17.
 *      入库监控
 */

@Controller
@RequestMapping("/center")
public class MonitoringController {
    @Autowired
    QueueSampleService queueSampleService;

    @Autowired
    LimsCaseInfoService limsCaseInfoService;

    @Autowired
    LimsConsignmentInfoService limsConsignmentInfoService;

    @Autowired
    LimsSampleInfoDnaService limsSampleInfoDnaService;

    @Autowired
    AmPersonalInfoService amPersonalInfoService;

//    @Autowired
//    ComporeSameQueueService comporeSameQueueService;
//
//    @Autowired
//    ComporeRelativeQueueService comporeRelativeQueueService;

    //入本地库
    @RequestMapping("/localMonitoring")
    public ModelAndView localMonitoring(HttpServletRequest request, QueueSampleVo query, PageInfo pageInfo) {
        ModelAndView view = new ModelAndView();
//        query = resetCaseInfoQuery(query);
        List<QueueSampleVo> queueSampleList = queueSampleService.selectFindAll(query,pageInfo);

        int mainCaseCnt = queueSampleService.selectVOCount(query);
        view.addObject("mainPageInfo",pageInfo.updatePageInfo(mainCaseCnt) );
        view.addObject("query", query);
        view.addObject("queueSampleList", queueSampleList);
        view.setViewName("monitoring/localStorage");
        return view;
    }

    //入本国家库
    @RequestMapping("/stateMonitoring")
    public ModelAndView stateMonitoring(HttpServletRequest request, LimsCaseInfoVo query, PageInfo pageInfo) {
        ModelAndView view = new ModelAndView();
        query = resetCaseInfoQuery(query);

        if (StringUtils.isBlank(query.getQueueType())) {
            query.setQueueType(Constants.QUEUE_TYPE_CASE_TRANSFER);
        }else {
            query.setQueueType(query.getQueueType());
        }
        //获取当前登录用户
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo loaUserInfo = (LoaUserInfo) subject.getPrincipal();
        //获取当前用户的id
        String userOrgId = loaUserInfo.getOrgId();
        //将当前用户org_id设置进query
        if(StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")){
            userOrgId = "110230000000";
        }
        query.setUserOrdId(userOrgId);
        query.setAcceptOrgId(userOrgId);

        //获取受理人信息
        List<AmPersonalInfoVo> amPersonalInfoVoList = amPersonalInfoService.queryAmPersonalInfoVoList(loaUserInfo.getOrgId());

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
                    if (amPersonalInfoVo.getEntity().getPersonalId().equals(loaUserInfo.getPersonalId())) {
                        query.setAcceptorId(loaUserInfo.getPersonalId());
                        break;
                    }
                }
            }
        }
        
        //排序条件设置
        query.setOrderByClause("lc.case_no desc nulls last, lci.ACCEPT_DATETIME asc");

        List<LimsCaseInfoVo> limsCaseInfoList = limsCaseInfoService.selectFindstate(query,pageInfo);

        int k=0;
        if(limsCaseInfoList.size() > 0 ){
            for(int i=0;i < limsCaseInfoList.size(); i++){
                String caseId = limsCaseInfoList.get(i).getEntity().getCaseId();
                int backUpTotal = 0;
                int backSuccesstotal = 0;
                int backFailTotal = 0;
                int backUp = 0;
                int backSuccess = 0;
                int backFail = 0;
                if (query.getQueueType().equals(Constants.QUEUE_TYPE_CASE_TRANSFER)) {
                    //查询入库样本总数  案件id查询
                    int backUpNum_15 = queueSampleService.selectBsckUpCount(caseId,userOrgId);
                    backUp = backUpNum_15;

                    //查询入库成功样本总数  案件id查询
                    int backSuccessNum_15 = queueSampleService.selectBackSuccess(caseId,userOrgId);
                    backSuccess = backSuccessNum_15;

                    //查询入库失败样本总数  案件id查询
                    int backFailNum_15 = queueSampleService.selectBackFailCount(caseId,userOrgId);
                    backFail = backFailNum_15;

                }else {
                    int backUpNum_16 = queueSampleService.selectNewBsckUpCount(caseId,userOrgId);
                    backUp = backUpNum_16;

                    int backSuccessNum_16 = queueSampleService.selectNewBackSuccess(caseId,userOrgId);
                    backSuccess = backSuccessNum_16;

                    int backFailNum_16 = queueSampleService.selectNewBackFailCount(caseId,userOrgId);
                    backFail = backFailNum_16;
                }

                backUpTotal += backUp;
                backSuccesstotal += backSuccess;
                backFailTotal += backFail;
                if(backUpTotal>0){
                    k=k+1;
                }
                //入库样本总数
                limsCaseInfoList.get(i).setBackUpCount(backUpTotal);
                //入库成功样本总数
                limsCaseInfoList.get(i).setBackSuccessCount(backSuccesstotal);
                //入库失败样本总数
                limsCaseInfoList.get(i).setBackFailCount(backFailTotal);
                //等待入库样本总数
                limsCaseInfoList.get(i).setBackUpWaitForCount(backUpTotal-backFailTotal-backSuccesstotal);

                Date date = new Date();
                Calendar calendar = new GregorianCalendar();
                calendar.add(Calendar.DATE, -1);
                date = calendar.getTime();
                //队列15超过24小时标红
                List<QueueSample> queueSampleList = queueSampleService.selectQueueByCaseId(caseId);
                if(queueSampleList.size() >0){
                    //系统当前时间，24小时之前的时间
//                    if (queueSampleList.get(0).getCreateDatetime() != null && queueSampleList.get(0).getCreateDatetime().compareTo(date) > 0) {
                    if (queueSampleList.get(0).getCreateDatetime() != null) {
                        if(queueSampleList.get(0).getCreateDatetime().getTime() > date.getTime()){
                            limsCaseInfoList.get(i).getEntity().setGjkSYSNo("A");
                        }
                    }
                    System.out.println("++++++++"+date);
                }
                //队列16超过24小时标红
                List<LimsSampleInfoDna> limsSampleInfoDnaList = limsSampleInfoDnaService.selectByCaseId(caseId);
                if(limsSampleInfoDnaList.size()>0){
                    for(int j=0;j<limsSampleInfoDnaList.size();j++){
                        List<QueueSample> queueSampleList16 = queueSampleService.selectQueueBySampleNo(limsSampleInfoDnaList.get(j).getSampleNo());
                        if(queueSampleList16.size()>0){
                            if(queueSampleList16.get(0).getCreateDatetime().getTime() > date.getTime()){
                                limsCaseInfoList.get(i).getEntity().setGjkSYSNo("A");
                            }
                        }
                    }
                }
            }
        }
        Collections.sort(limsCaseInfoList, new SortByEvidenceNo());

//        List<LimsCaseInfoVo> limsCaseInfoList = new ArrayList<>();
//        if(limsCaseInfoList2.size() > 0){
//            for(int j=0; j < limsCaseInfoList2.size();j++){
//                if(limsCaseInfoList2.get(j).getBackUpCount() >0){
//                    limsCaseInfoList.add(limsCaseInfoList2.get(j));
//                }
//            }
//        }

        int mainStateCnt = limsCaseInfoService.selectStateCount(query);
//        view.addObject("mainStateCnt",pageInfo.updatePageInfo(k) );
        view.addObject("mainStateCnt",pageInfo.updatePageInfo(mainStateCnt) );
        view.addObject("limsCaseInfoList", limsCaseInfoList);
        view.addObject("amPersonalInfoVoList", amPersonalInfoVoList);
        view.addObject("query", query);
        view.setViewName("monitoring/countryStorage");
        return view;
    }

    /*//同一比对队列
    @RequestMapping("/SameQueueMonitor")
    public ModelAndView SameQueueMonitor(HttpServletRequest request, ComporeSameQueueVo query, PageInfo pageInfo) {
        ModelAndView view = new ModelAndView();

        String userOrgId = query.getEntity().getLabServerNo();
        //将当前用户org_id设置进query
        if(StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")){
            userOrgId = "110230000000";
        }
        query.getEntity().setLabServerNo(userOrgId);


        List<ComporeSameQueueVo> queueVoList = comporeSameQueueService.selectVOPaginationList(query,pageInfo);
        int totalCount = comporeSameQueueService.selectVOCount(query);

        view.addObject("query", query);
        view.addObject("queueVoList", queueVoList);
        view.addObject("pageInfo", pageInfo.updatePageInfo(totalCount));
        view.setViewName("monitoring/sameQueueMonitor");
        return view;
    }
    //亲缘比对队列
    @RequestMapping("/RelativeQueueMonitor")
    public ModelAndView RelativeQueueMonitor(HttpServletRequest request, ComporeRelativeQueueVo query, PageInfo pageInfo) {
        ModelAndView view = new ModelAndView();

        String userOrgId = query.getEntity().getLabServerNo();
        //将当前用户org_id设置进query
        if(StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")){
            userOrgId = "110230000000";
        }
        query.getEntity().setLabServerNo(userOrgId);


        List<ComporeRelativeQueueVo> queueVoList = comporeRelativeQueueService.selectVOPaginationList(query, pageInfo);
        int totalCount = comporeRelativeQueueService.selectVOCount(query);

        view.addObject("query", query);
        view.addObject("queueVoList", queueVoList);
        view.addObject("pageInfo", pageInfo.updatePageInfo(totalCount));
        view.setViewName("monitoring/relativeQueueMonitor");
        return view;
    }
*/
    class SortByEvidenceNo implements Comparator {
        @Override
        public int compare(Object o1, Object o2) {
            LimsCaseInfoVo s1 = (LimsCaseInfoVo) o1;
            LimsCaseInfoVo s2 = (LimsCaseInfoVo) o2;
            return s2.getBackFailCount().compareTo(s1.getBackFailCount());
        }
    }

    private LimsCaseInfoVo resetCaseInfoQuery(LimsCaseInfoVo query) throws ParseException {

        if (StringUtils.isBlank(query.getEntity().getCaseName())) {
            query.getEntity().setCaseName(null);
        } else {
            query.getEntity().setCaseName(query.getEntity().getCaseName());
        }

        if (StringUtils.isBlank(query.getEntity().getCaseNo())) {
            query.getEntity().setCaseNo(null);
        } else {
            query.getEntity().setCaseNo(query.getEntity().getCaseNo());
        }

        if (null == query.getAcceptStartDatetime()) {
            query.setAcceptStartDatetime(null);
        }else {
            query.setAcceptStartDatetime(query.getAcceptStartDatetime());
        }

        if (null == query.getAcceptEndDatetime()) {
            query.setAcceptEndDatetime(null);
        }else {
            query.setAcceptEndDatetime(query.getAcceptEndDatetime());
        }

        if (StringUtils.isBlank(query.getAcceptorId())) {
            query.setAcceptorId(null);
        } else {
            query.setAcceptorId(query.getAcceptorId().trim());
        }
        //案件id
        if (StringUtils.isBlank(query.getCaseId())) {
            query.setCaseId(null);
        } else {
            query.setCaseId(query.getCaseId().trim());
        }
//        if (StringUtils.isBlank(query.getSampleName())) {
//            query.setSampleName(null);
//        } else {
//            query.setSampleName(query.getSampleName());
//        }
//        if (StringUtils.isBlank(query.getSampleNo())) {
//            query.setSampleNo(null);
//        } else {
//            query.setSampleNo(query.getSampleNo());
//        }


        return query;
    }

}
