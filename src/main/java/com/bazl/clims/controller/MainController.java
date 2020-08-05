package com.bazl.clims.controller;

import com.bazl.clims.common.Constants;
import com.bazl.clims.model.LoaUserInfo;
import com.bazl.clims.model.vo.LimsCaseInfoVo;
import com.bazl.clims.service.LimsCaseInfoService;
import com.bazl.clims.service.LimsSampleInfoDnaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sun on 2018/12/20.
 */
@Controller
@RequestMapping("/main")
public class MainController extends BaseController{

    @Autowired
    LimsCaseInfoService caseInfoService;

    @Autowired
    LimsSampleInfoDnaService limsSampleInfoDnaService;

    @RequestMapping("/home")
    public ModelAndView home(){
        //设置保留位数
        DecimalFormat df = new DecimalFormat("0.00");
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMaximumFractionDigits(2);

        ModelAndView view = new ModelAndView();
        view.setViewName("home");

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if (operateUser == null) {
            view.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return view;
        }

            //法医中心账号登录查询
            if("110023000004".equals(operateUser.getOrgId())){
                //查询待送检状态案件数
                int cnt1 = caseInfoService.selectCountByCaseStatus(Constants.CASE_STATUS_01, "110230000000");
                view.addObject("cnt1", cnt1);

                //查询待检验数
                int cnt2 = limsSampleInfoDnaService.selectNotCount("110230000000");
                view.addObject("cnt2", cnt2);

                //查询所有案件数
                int cnt9 = caseInfoService.selectCountByCaseStatus(null,"110230000000");
                view.addObject("cnt9", cnt9);

                //查询检材数
                LimsCaseInfoVo query1 = new LimsCaseInfoVo();
                if (operateUser.getUserType().equals("2")){
                    query1.setAcceptOrgId("110230000000");
                }else{
                    query1.setAcceptorId(operateUser.getPersonalId());
                }
                int snt1 = limsSampleInfoDnaService.selectCount(query1);
                view.addObject("snt1", snt1);

                //查询检出检材数
                LimsCaseInfoVo query = new LimsCaseInfoVo();
                if (operateUser.getUserType().equals("2")){
                    query.setAcceptOrgId(operateUser.getOrgId());
                }else{
                    query.setAcceptorId(operateUser.getPersonalId());
                }
                int snt2 = limsSampleInfoDnaService.selectAuditCount(query);
                view.addObject("snt2", snt2);

                //检出率
                float detectionRate = 0;
                if (0 != snt1) {
                    detectionRate = (float) ((double) snt2 / (double) snt1);
                }
                view.addObject("snt3", nf.format(Double.valueOf(df.format(detectionRate))));

                //根据年份获取各个月份的案件数
                List<HashMap<String, String>> caseList = caseInfoService.selectCountGroupProperty("110230000000");
                ObjectMapper objectMapper = new ObjectMapper();
                String caseJson = "";
                try {
                    caseJson = objectMapper.writeValueAsString(caseList);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                view.addObject("caseJson", caseJson);

                //根据年份获取各个月份的检材数
                List<HashMap<String, String>> sampleList = limsSampleInfoDnaService.selectCountGroupSampleType("110230000000");
                String sampleJson = "";
                try {
                    sampleJson = objectMapper.writeValueAsString(sampleList);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                view.addObject("sampleJson", sampleJson);
            }else{
                //查询待领取状态案件数
                int cnt1 = caseInfoService.selectCountByCaseStatus(Constants.CASE_STATUS_01, operateUser.getOrgId());
                view.addObject("cnt1", cnt1);

                //查询待检验数
                int cnt2 = limsSampleInfoDnaService.selectNotCount(operateUser.getOrgId());
                view.addObject("cnt2", cnt2);

                //查询所有案件数
                int cnt9 = caseInfoService.selectCountByCaseStatus(null,operateUser.getOrgId());
                view.addObject("cnt9", cnt9);

                //查询受理检材数
                LimsCaseInfoVo query1 = new LimsCaseInfoVo();
                if (operateUser.getUserType().equals("2")){
                    query1.setAcceptOrgId(operateUser.getOrgId());
                }else{
                    query1.setAcceptorId(operateUser.getPersonalId());
                }
                int snt1 = limsSampleInfoDnaService.selectAcceptCount(query1);
                view.addObject("snt1", snt1);

                //查询检出检材数
                LimsCaseInfoVo query = new LimsCaseInfoVo();
                if (operateUser.getUserType().equals("2")){
                    //query.setAcceptorId(operateUser.getPersonalId());
                    query.setAcceptOrgId(operateUser.getOrgId());
                }else{
                    query.setAcceptorId(operateUser.getPersonalId());
                }
                int snt2 = limsSampleInfoDnaService.selectAuditCount(query);
                view.addObject("snt2", snt2);

                //检出率
                float detectionRate = 0;
                if (0 != snt1) {
                    detectionRate = (float) ((double) snt2 / (double) snt1);
                }
                view.addObject("snt3", nf.format(Double.valueOf(df.format(detectionRate))));

                //根据年份获取各个月份的案件数
                List<HashMap<String, String>> caseList = caseInfoService.selectCountGroupProperty(operateUser.getOrgId());
                ObjectMapper objectMapper = new ObjectMapper();
                String caseJson = "";
                try {
                    caseJson = objectMapper.writeValueAsString(caseList);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                view.addObject("caseJson", caseJson);

                //根据年份获取各个月份的检材数
                List<HashMap<String, String>> sampleList = limsSampleInfoDnaService.selectCountGroupSampleType(operateUser.getOrgId());
                String sampleJson = "";
                try {
                    sampleJson = objectMapper.writeValueAsString(sampleList);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                view.addObject("sampleJson", sampleJson);

            }

        return view;
    }

    /**
     * 根据年份查询统计数据
     * @param year
     * @return
     */
    @RequestMapping("/getCount")
    @ResponseBody
    public Map<String,Object> getCount(String year){
        Map<String,Object> map = new HashMap<>();
        try {
            //根据年份获取各个月份的案件数
            HashMap<String, String> monthMap = caseInfoService.selectMonthCountByYear(year);
            map.put("monthMap", monthMap);
            map.put("code", 0);
            map.put("message", "获取成功！");
        }catch(Exception ex){
            logger.info("获取失败"+ex);
            map.put("code", 1);
            map.put("message", "获取失败！");
        }
        return map;
    }

}
