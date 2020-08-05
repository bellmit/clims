package com.bazl.clims.controller.center;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bazl.clims.controller.BaseController;
import com.bazl.clims.model.DictItem;
import com.bazl.clims.model.LoaRoleRelation;
import com.bazl.clims.model.LoaUserInfo;
import com.bazl.clims.model.PageInfo;
import com.bazl.clims.model.po.AmPersonalInfo;
import com.bazl.clims.model.po.LabTaskInfo;
import com.bazl.clims.model.po.OrgInfo;
import com.bazl.clims.model.po.ReviewQueueSample;
import com.bazl.clims.model.vo.ReviewQueueSampleVo;
import com.bazl.clims.service.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author wanghaiyang
 * @date 2019/3/27.
 */@Controller
@RequestMapping("/center")
public class PendingReviewController  extends BaseController {

    @Autowired
    ReviewQueueSampleService reviewQueueSampleService;

    @Autowired
    LabTaskInfoService labTaskInfoService;

    @Autowired
    SeqNoGenerateService seqNoGenerateService;

    @Autowired
    OrgInfoService orgInfoService;

    @Autowired
    DictItemService dictItemService;

    /**
     * 待复检检材
     * @param request
     * @return
     */
    @RequestMapping("/pendingReview")
    public ModelAndView pendingReview(HttpServletRequest request, ReviewQueueSampleVo query, PageInfo pageInfo) {
        ModelAndView view = new ModelAndView();

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if(operateUser == null){
            view.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return  view;
        }
        String userOrgId = operateUser.getOrgId();
        //将当前用户org_id设置进query
        if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
            userOrgId = "110230000000";
        }
        query.getEntity().setOrgId(userOrgId);
        try {
            List<DictItem> sampleTypeList = dictItemService.selectListByDictTypeCode("SAMPLE_TYPE");
            view.addObject("sampleTypeList", sampleTypeList);
            //查询待复检检材列表
            List<ReviewQueueSampleVo> reviewQueueSampleList = reviewQueueSampleService.selectReviewQueueSampleList(query, pageInfo);
            int reviewQueueSampleCount = reviewQueueSampleService.selectReviewQueueSampleCount(query);

            view.addObject("query", query);
            view.addObject("reviewQueueSampleList", reviewQueueSampleList);
            view.addObject("pageInfo", pageInfo.updatePageInfo(reviewQueueSampleCount));
        }catch (Exception ex){
            logger.info("待复检检材页面查询失败:"+ex);
        }

        view.setViewName("testToReview/pendingReview");
        return view;
    }

    /**
     * 复检
     * @param params
     * @return
     */
    @RequestMapping(value="/startRecheck", method = RequestMethod.POST, produces="application/json; charset=utf-8")
    @ResponseBody
    public Map<String,Object> startRecheck(@RequestParam(value = "params")String params, HttpSession session){
        Map<String,Object> returnMap=new HashMap();

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if(operateUser == null){
            returnMap.put("date", "redirect:/login.html?timeoutFlag=1");
            return  returnMap;
        }
        //获取单位信息
        OrgInfo orgInfo = orgInfoService.selectByPrimaryKey(operateUser.getOrgId());
        //获取当前用户的id
        String userOrgId = operateUser.getOrgId();
        //获取任务编号
        String taskNo = null;
        if (StringUtils.isNotBlank(userOrgId)){
            taskNo = seqNoGenerateService.getNextTastNoVal("-", userOrgId);
        }else{
            taskNo = seqNoGenerateService.getNextTastNoVal("-", null);
        }
        try {
            JSONObject str = JSON.parseObject(params);
            List<ReviewQueueSample> reviewQueueSampleList = new ArrayList<>();
            if(str.containsKey("reviewQueueSampleList")) {
                reviewQueueSampleList = (List<ReviewQueueSample>) JSON.parseArray(str.get("reviewQueueSampleList").toString(), ReviewQueueSample.class);
            }
            //遍历前台传来的要分配权限的数据
            for (ReviewQueueSample reviewQueueSample : reviewQueueSampleList) {
                //更新复检检材状态为已复检
                ReviewQueueSample reviewQueueSample1 = new ReviewQueueSample();
                reviewQueueSample1.setReviewSampleStatus("1");
                reviewQueueSample1.setId(reviewQueueSample.getId());
                reviewQueueSampleService.updateReviewSampleStatus(reviewQueueSample1);
            }
            //添加实验任务表
            /*LabTaskInfo labTaskInfo = new LabTaskInfo();
            labTaskInfo.setTaskId(UUID.randomUUID().toString());
            labTaskInfo.setTaskStage(reviewQueueSampleList.get(0).getTaskStage());
            labTaskInfo.setTaskPerson(operateUser.getLoginName());
            labTaskInfo.setTaskStartDatetime(new Date());
            labTaskInfo.setCreateDatetime(new Date());
            labTaskInfo.setCreatePerson(operateUser.getLoginName());
            labTaskInfo.setDeleteFlag("0");
            labTaskInfo.setInspectionType("1");
            labTaskInfo.setExtractionMode("0");
            labTaskInfo.setTaskNo(taskNo);
            if (orgInfo != null){
                labTaskInfo.setDelegateOrgCode(orgInfo.getOrgCode());
                labTaskInfo.setDelegateOrgName(orgInfo.getOrgName());
            }
            labTaskInfoService.insert(labTaskInfo);
//            session.setAttribute("taskId",labTaskInfo.getTaskId());
            returnMap.put("taskId",labTaskInfo.getTaskId());*/
            returnMap.put("success",true);
        }catch (Exception ex){
            logger.info("复检失败:"+ex);
            returnMap.put("success",false);
        }

        return returnMap;
    }

    /**
     * 删除待复检样本
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/deleteReviewQueueSample")
    public Map<String, Object> deleteReviewQueueSample(HttpServletRequest request, String id) {
        Map<String, Object> returnMap = new HashMap<>();

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if(operateUser == null){
            returnMap.put("date", "redirect:/login.html?timeoutFlag=1");
            return returnMap;
        }
        try {


        }catch (Exception ex){
            logger.info("删除失败:"+ex);
        }

        return returnMap;
    }

    /**
     * 待复检检材
     * @return
     */
   @RequestMapping("/querPpendingReview")
    public ModelAndView querPpendingReview(HttpServletRequest request, ReviewQueueSampleVo query, PageInfo pageInfo) {
        ModelAndView view = new ModelAndView();

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if(operateUser == null){
            view.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return  view;
        }
       String userOrgId = operateUser.getOrgId();
       //将当前用户org_id设置进query
       if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
           userOrgId = "110230000000";
       }
       query.getEntity().setOrgId(userOrgId);

        query = resetRquipmentNameInfoQuery(query);
        try {
            List<DictItem> sampleTypeList = dictItemService.selectListByDictTypeCode("SAMPLE_TYPE");
            view.addObject("sampleTypeList", sampleTypeList);
            //查询待复检检材列表
            List<ReviewQueueSampleVo> reviewQueueSampleList = reviewQueueSampleService.queryList(query,pageInfo);
            int reviewQueueSampleCount = reviewQueueSampleService.selectReviewQueueSampleCount(query);

            view.addObject("query", query);
            view.addObject("reviewQueueSampleList", reviewQueueSampleList);
            view.addObject("pageInfo", pageInfo.updatePageInfo(reviewQueueSampleCount));
        }catch (Exception ex){
            logger.info("待复检检材页面查询失败:"+ex);
            System.out.println(ex);
        }

        view.setViewName("testToReview/pendingReview");
        return view;
    }


    public static ReviewQueueSampleVo resetRquipmentNameInfoQuery(ReviewQueueSampleVo query) {
        /*if (StringUtils.isBlank(query.getEntity().getOrgId())) {
            query.getEntity().setOrgId(null);
        } else {
            query.getEntity().setOrgId(query.getEntity().getOrgId());
        }*/


        if (StringUtils.isBlank(query.getSampleNo())) {
            query.setSampleNo(null);
        } else {
            query.setSampleNo(query.getSampleNo().trim());
        }

        if (StringUtils.isBlank(query.getSampleName())) {
            query.setSampleName(null);
        } else {
            query.setSampleName(query.getSampleName().trim());
        }

        if (StringUtils.isBlank(query.getSampleType())) {
            query.setSampleType(null);
        } else {
            query.setSampleType(query.getSampleType().trim());
        }

        return query;
    }

}
