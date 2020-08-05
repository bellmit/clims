package com.bazl.clims.controller.center;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bazl.clims.common.Constants;
import com.bazl.clims.common.FeedBackXckyConstants;
import com.bazl.clims.compare.GeneSameCompareUtil;
import com.bazl.clims.controller.BaseController;
import com.bazl.clims.dao.LimsConsignmentInfoMapper;
import com.bazl.clims.dao.QueueDetailMapper;
import com.bazl.clims.dao.QueueSampleMapper;
import com.bazl.clims.helper.codis.CodisFileParser;
import com.bazl.clims.model.LoaUserInfo;
import com.bazl.clims.model.PageInfo;
import com.bazl.clims.model.po.*;
import com.bazl.clims.model.vo.AmPersonalInfoVo;
import com.bazl.clims.model.vo.LabAnalysisInfoVo;
import com.bazl.clims.model.vo.LimsCaseInfoVo;
import com.bazl.clims.model.vo.LimsSampleGeneVo;
import com.bazl.clims.service.*;
import com.bazl.clims.utils.InitializationData;
import com.bazl.clims.utils.IpAddressUtils;
import com.bazl.clims.utils.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

import static com.bazl.clims.utils.DateUtils.addDays;

/**
 * Created by Sun on 2018/12/20.
 */
@Controller
@RequestMapping("/center")
public class TestToReviewController extends BaseController {

    @Value("${ftpUser}")
    private String ftpUser;
    @Value("${ftpPassword}")
    private String ftpPassword;
    @Autowired
    LimsCaseInfoService limsCaseInfoService;
    @Autowired
    OrgInfoService orgInfoService;
    @Autowired
    LimsSampleGeneService limsSampleGeneService;
    @Autowired
    LimsConsignmentInfoService limsConsignmentInfoService;
    @Autowired
    MatchAuditedGeneService matchAuditedGeneService;
    @Autowired
    LabAnalysisInfoService labAnalysisInfoService;
    @Autowired
    AmPersonalInfoService amPersonalInfoService;
    @Autowired
    DictItemService dictItemService;
    @Autowired
    ReviewQueueSampleService reviewQueueSampleService;
    @Autowired
    LimsSampleInfoDnaService limsSampleInfoDnaService;
    @Autowired
    QueueDetailMapper queueDetailMapper;
    @Autowired
    QueueSampleMapper queueSampleMapper;
    @Autowired
    GeneSameCompareUtil geneSameCompareUtil;

    @Autowired
    LimsConsignmentInfoMapper limsConsignmentInfoMapper;

    /**
     * 进入分析
     *
     * @param request
     * @return
     */
    @RequestMapping("/analysisExperiment")
    public ModelAndView analysisExperiment(HttpServletRequest request, LabAnalysisInfoVo query, PageInfo pageInfo) {
        ModelAndView modelAndView = InitializationData.initDictList();

        try {

            query = resetLabAnalysisQuery(query);

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

            query.getEntity().setDataFilePath(Constants.FLAG_TRUE);
//            List<LabAnalysisInfoVo> labAnalysisInfoVos=new ArrayList<LabAnalysisInfoVo>();
            //根据检材编号得到板号
            /*if (null != query.getSampleNo()){
                List<LimsSampleInfoDna> limsSampleInfoDnas = limsSampleInfoDnaService.selectBySampleNo(query.getSampleNo());
                for (LimsSampleInfoDna limsSampleInfoDna: limsSampleInfoDnas){
                    String sampleId = limsSampleInfoDna.getSampleId();
                    List<LimsSampleGene> limsSampleGeneList = limsSampleGeneService.selectListBySampleId(sampleId);
                    for (int i=0; i<limsSampleGeneList.size();i++){
                        String boardNo = limsSampleGeneList.get(i).getBoardNo();
                        LabAnalysisInfoVo labAnalysisInfoVo=new LabAnalysisInfoVo();
                        labAnalysisInfoVo.getEntity().setBoardNo(boardNo);
                        labAnalysisInfoVo.setUserOrdId(loaUserInfo.getOrgId());
                        labAnalysisInfoVos= labAnalysisInfoService.selectVOByBoardNo(labAnalysisInfoVo);
                    }
                }
            }*/

            //查询上样板列表
            List<LabAnalysisInfoVo> labAnalysisInfoList = labAnalysisInfoService.selectLabAnalysisInfoList(query, pageInfo);
            //得到了装有板号的  list
            //根据板号查任务
            /*for (int i=0; i<labAnalysisInfoVos.size(); i++){
                labAnalysisInfoList.add(labAnalysisInfoVos.get(i));
            }*/
            if (null != labAnalysisInfoList && labAnalysisInfoList.size() > 0) {
                for (LabAnalysisInfoVo labAnalysisInfoVo : labAnalysisInfoList) {
                    if (StringUtils.isNotBlank(labAnalysisInfoVo.getEntity().getReviewCount())) {
                        int sampleCount = Integer.parseInt(labAnalysisInfoVo.getEntity().getSampleCount());
                        int reviewCount = Integer.parseInt(labAnalysisInfoVo.getEntity().getReviewCount());
                        int notReviewCount = sampleCount - reviewCount;
                        labAnalysisInfoVo.setNotReviewCount(String.valueOf(notReviewCount));
                    }
                    LimsSampleGeneVo limsSampleGeneVo = new LimsSampleGeneVo();
                    limsSampleGeneVo.getEntity().setLabAnalysisInfoId(labAnalysisInfoVo.getEntity().getId());
                    List<LimsSampleGeneVo> limsSampleGeneVoList = limsSampleGeneService.selecVOtList(limsSampleGeneVo);
                    labAnalysisInfoVo.getEntity().setSampleCount(String.valueOf(limsSampleGeneVoList.size()));
                }
            }


            int labAnalysisInfoCount = labAnalysisInfoService.selectLabAnalysisInfoCount(query);

            //获取审核人信息
            List<AmPersonalInfoVo> amPersonalInfoVoList = amPersonalInfoService.queryAmPersonalInfoVoList(loaUserInfo.getOrgId());

            //获取分析人信息
//            List<LabAnalysisInfo> labAnalysisInfos = labAnalysisInfoService.selectAll();

            modelAndView.addObject("query", query);
            modelAndView.addObject("labAnalysisInfoList", labAnalysisInfoList);
            modelAndView.addObject("amPersonalInfoVoList", amPersonalInfoVoList);
            modelAndView.addObject("pageInfo", pageInfo.updatePageInfo(labAnalysisInfoCount));
//            modelAndView.addObject("labAnalysisInfos", labAnalysisInfos);
        } catch (Exception ex) {
            logger.info("查询失败:" + ex);
        }

        modelAndView.setViewName("testToReview/analysisList");
        return modelAndView;
    }


    /**
     * 进入分析实验页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/enterAnalysis")
    public ModelAndView enterAnalysis(HttpServletRequest request, String id) {
        ModelAndView modelAndView = InitializationData.initDictList();

        //源样本
        List<LimsSampleGeneVo> sourceLimsGeneVoSample = new ArrayList<>();
        List<LimsSampleGeneVo> codeLimsGeneVoSample = new ArrayList<>();

        LimsSampleGeneVo limsSampleGeneVo = new LimsSampleGeneVo();
        limsSampleGeneVo.getEntity().setLabAnalysisInfoId(id);
        List<LimsSampleGeneVo> limsSampleGeneVoList = limsSampleGeneService.selecVOtList(limsSampleGeneVo);
        for (LimsSampleGeneVo sampleGeneVo : limsSampleGeneVoList) {
            if (sampleGeneVo.getSampleSource().equals("0")){
                sourceLimsGeneVoSample.add(sampleGeneVo);
            }
            if (sampleGeneVo.getSampleSource().equals("1")){
                codeLimsGeneVoSample.add(sampleGeneVo);
            }
        }
        LabAnalysisInfo labAnalysisInfo = labAnalysisInfoService.selectByPrimaryKey(id);

        /*LimsSampleGeneVo limsSampleGeneVoBean = new LimsSampleGeneVo();
        limsSampleGeneVoBean.getEntity().setBoardNo(labAnalysisInfo.getBoardNo());
        List<LimsSampleGeneVo> noAnalysisSumList = limsSampleGeneService.selecVOtList(limsSampleGeneVoBean);
        if(noAnalysisSumList.size() > limsSampleGeneVoList.size()){
            modelAndView.addObject("noAnalysisSum",noAnalysisSumList.size() - limsSampleGeneVoList.size());
        }else{
            modelAndView.addObject("noAnalysisSum",0);
        }*/

        modelAndView.addObject("limsSampleGeneVoList", limsSampleGeneVoList);
        modelAndView.addObject("sourceLimsGeneVoSample", sourceLimsGeneVoSample);
        modelAndView.addObject("codeLimsGeneVoSample", codeLimsGeneVoSample);
        modelAndView.addObject("labAnalysisInfo", labAnalysisInfo);
        modelAndView.addObject("ipAddr", IpAddressUtils.getIpAddr(request));
        modelAndView.addObject("ftpUser", ftpUser);
        modelAndView.addObject("ftpPassword", ftpPassword);
        modelAndView.setViewName("testToReview/analysisExperiment");
        return modelAndView;
    }

    /**
     * 检验复核
     *
     * @param request
     * @param query
     * @param pageInfo
     * @return
     */
    @RequestMapping("/testToReview")
    public ModelAndView testToReview(HttpServletRequest request, LabAnalysisInfoVo query, PageInfo pageInfo) {
        ModelAndView view = new ModelAndView();

        try {

            query = resetLabAnalysisQuery(query);

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

            if (null != query.getAuditEndDatetime()){//解决时间无时分秒造成的00:00:00查询
                query.setAuditEndDatetime(addDays(query.getAuditEndDatetime(),1));//增加一天
            }
            List<LabAnalysisInfoVo> labAnalysisInfoVos=new ArrayList<LabAnalysisInfoVo>();
            //根据检材编号得到板号
            /*if (null != query.getSampleNo()){
                List<LimsSampleInfoDna> limsSampleInfoDnas = limsSampleInfoDnaService.selectBySampleNo(query.getSampleNo());
                for (LimsSampleInfoDna limsSampleInfoDna: limsSampleInfoDnas){
                    String sampleId = limsSampleInfoDna.getSampleId();
                    List<LimsSampleGene> limsSampleGeneList = limsSampleGeneService.selectListBySampleId(sampleId);
                    for (int i=0; i<limsSampleGeneList.size();i++){
                        String boardNo = limsSampleGeneList.get(i).getBoardNo();
                        LabAnalysisInfoVo labAnalysisInfoVo=new LabAnalysisInfoVo();
                        labAnalysisInfoVo.getEntity().setBoardNo(boardNo);
                        labAnalysisInfoVo.setUserOrdId(loaUserInfo.getOrgId());
                        labAnalysisInfoVos= labAnalysisInfoService.selectVOByBoardNo(labAnalysisInfoVo);
                    }
                }
            }*/

            //查询上样板列表
            List<LabAnalysisInfoVo> labAnalysisInfoList = labAnalysisInfoService.selectLabAnalysisInfoList(query, pageInfo);
            //检材个数
            if (ListUtils.isNotNullAndEmptyList(labAnalysisInfoList)){
                for (LabAnalysisInfoVo labAnalysisInfoVo : labAnalysisInfoList) {
                    LimsSampleGeneVo limsSampleGeneVo = new LimsSampleGeneVo();
                    limsSampleGeneVo.getEntity().setLabAnalysisInfoId(labAnalysisInfoVo.getEntity().getId());
                    List<LimsSampleGeneVo> limsSampleGeneVoList = limsSampleGeneService.selecVOtList(limsSampleGeneVo);
                    labAnalysisInfoVo.getEntity().setSampleCount(String.valueOf(limsSampleGeneVoList.size()));
                }
            }
            //得到了装有板号的  list
            //根据板号查任务
            /*for (int i=0; i<labAnalysisInfoVos.size(); i++){
                labAnalysisInfoList.add(labAnalysisInfoVos.get(i));
            }*/
            /*
            if (null != labAnalysisInfoList && labAnalysisInfoList.size() > 0) {
                for (LabAnalysisInfoVo labAnalysisInfoVo : labAnalysisInfoList) {
                    int reviewCount = 0;
                    List<LimsSampleGene> sampleGeneList = limsSampleGeneService.selectListByAnalysisInfoId(labAnalysisInfoVo.getEntity().getId());
                    if (ListUtils.isNotNullAndEmptyList(sampleGeneList)) {
                        for (LimsSampleGene sampleGene : sampleGeneList) {
                            if (sampleGene.getAuditStatus().equals("1")) {
                                reviewCount++;
                            }
                        }
                        labAnalysisInfoVo.getEntity().setReviewCount(Integer.toString(reviewCount));
                        if(labAnalysisInfoVo.getEntity().getSampleCount().equals("0")){
                            labAnalysisInfoVo.getEntity().setSampleCount(Integer.toString(sampleGeneList.size()));
                        }

                    }
                    if (StringUtils.isNotBlank(labAnalysisInfoVo.getEntity().getReviewCount())) {
                        int sampleCount = Integer.parseInt(labAnalysisInfoVo.getEntity().getSampleCount());
                        reviewCount = Integer.parseInt(labAnalysisInfoVo.getEntity().getReviewCount());
                        int notReviewCount = sampleCount - reviewCount;
                        labAnalysisInfoVo.setNotReviewCount(String.valueOf(notReviewCount));
                        labAnalysisInfoVo.getEntity().setAuditStatus(Constants.AUDIT_STATUS_1);
                        labAnalysisInfoService.updateuSatus(labAnalysisInfoVo.getEntity());
                    }

                }
            }
            */
            //显示条数
            int labAnalysisInfoCount = labAnalysisInfoService.selectLabAnalysisInfoCount(query);
            if (null != query.getAuditEndDatetime()){//解决时间无时分秒造成的00:00:00查询
                query.setAuditEndDatetime(addDays(query.getAuditEndDatetime(),-1));//减一天
            }
            //获取审核人信息
            List<AmPersonalInfoVo> amPersonalInfoVoList = amPersonalInfoService.queryAmPersonalInfoVoList(loaUserInfo.getOrgId());

            view = InitializationData.initDictList();
            view.addObject("query", query);
            view.addObject("labAnalysisInfoList", labAnalysisInfoList);
            view.addObject("amPersonalInfoVoList", amPersonalInfoVoList);
            view.addObject("pageInfo", pageInfo.updatePageInfo(labAnalysisInfoCount));
        } catch (Exception ex) {
            logger.info("查询失败:" + ex);
        }

        view.setViewName("testToReview/testToReview");
        return view;
    }

    @RequestMapping("/reviewDetails")
    public ModelAndView reviewDetails(HttpServletRequest request, String id) {
        ModelAndView modelAndView = new ModelAndView();

        try {
            List<List<Map<String, String>>> barList = new ArrayList<List<Map<String, String>>>();
            //根据id查询基因型信息
            LimsSampleGeneVo limsSampleGeneVo = new LimsSampleGeneVo();
            limsSampleGeneVo.getEntity().setLabAnalysisInfoId(id);
            limsSampleGeneVo.getEntity().setDeleteFlag("0");
            List<LimsSampleGeneVo> limsSampleGeneVoList = limsSampleGeneService.selecVOtList(limsSampleGeneVo);
            if (limsSampleGeneVoList.size() < 97) {
                for (LimsSampleGeneVo lsgVO : limsSampleGeneVoList) {

                    if(StringUtils.isNotBlank(lsgVO.getEntity().getGeneInfo())) {
                        //转换基因格式  进行判断
                        Map<String, List<String>> srcResult = (Map) JSON.parse(lsgVO.getEntity().getGeneInfo());

                        //判断无基因型
                        if (srcResult.size() == 0 && !lsgVO.getEntity().getAuditStatus().equals(Constants.QUESTION_TYPES_05)) {
                            lsgVO.setColorMark(Constants.QUESTION_TYPES_05);
                            continue;
                        }
                    }else{
                        //顺义新需求，无基因型的也可以进行审核通过

//                        lsgVO.getEntity().setAuditStatus(Constants.QUESTION_TYPES_05);
                        continue;
                    }

                    //判断此检材与本板其他检材比中
                    if (isExistMatch(lsgVO, limsSampleGeneVoList) && !lsgVO.getEntity().getAuditStatus().equals(Constants.QUESTION_TYPES_06)) {
                        lsgVO.setColorMark(Constants.QUESTION_TYPES_06);
                    }

//                    //判断无基因型
//                    if (StringUtils.isBlank(lsgVO.getEntity().getGeneInfo()) && !lsgVO.getEntity().getAuditStatus().equals(Constants.QUESTION_TYPES_05)) {
//                        lsgVO.setColorMark(Constants.QUESTION_TYPES_05);
//                    }
//                    //判断此检材与本板其他检材比中
//                    if (isExistMatch(lsgVO, limsSampleGeneVoList) && !lsgVO.getEntity().getAuditStatus().equals(Constants.QUESTION_TYPES_06)) {
//                        lsgVO.setColorMark(Constants.QUESTION_TYPES_06);
//                    }

                }

                for (int i = limsSampleGeneVoList.size(); i < 96; i++) {
                    LimsSampleGeneVo sampleGeneVo = new LimsSampleGeneVo();
                    limsSampleGeneVoList.add(sampleGeneVo);
                }
            }
            List<List<LimsSampleGeneVo>> lists = splitList(limsSampleGeneVoList, 24);
            List<LimsSampleGeneVo> limsSampleGeneVo1 = lists.get(0);
            List<LimsSampleGeneVo> limsSampleGeneVo2 = lists.get(1);
            List<LimsSampleGeneVo> limsSampleGeneVo3 = lists.get(2);
            List<LimsSampleGeneVo> limsSampleGeneVo4 = lists.get(3);

            modelAndView.addObject("limsSampleGeneVo1", limsSampleGeneVo1);
            modelAndView.addObject("limsSampleGeneVo2", limsSampleGeneVo2);
            modelAndView.addObject("limsSampleGeneVo3", limsSampleGeneVo3);
            modelAndView.addObject("limsSampleGeneVo4", limsSampleGeneVo4);
            modelAndView.addObject("limsSampleGeneVoList", limsSampleGeneVoList);
            modelAndView.addObject("id", id);
        } catch (Exception e) {
            logger.error("复核失败!", e);
        }

        modelAndView.setViewName("testToReview/reviewDetails");
        return modelAndView;
    }

    /**
     * 判断是否存在相同基因型
     *
     * @param limsSampleGeneVo
     * @param limsSampleGeneVoList
     * @return
     */
    public boolean isExistMatch(LimsSampleGeneVo limsSampleGeneVo, List<LimsSampleGeneVo> limsSampleGeneVoList) {
        boolean flag = false;

        if(StringUtils.isBlank(limsSampleGeneVo.getEntity().getGeneInfo())){
            return false;
        }

        String sampleNo = limsSampleGeneVo.getSampleNo();
        if (StringUtils.isNotBlank(sampleNo)) {
            for (LimsSampleGeneVo lsgVO : limsSampleGeneVoList) {
                if(StringUtils.isBlank(lsgVO.getEntity().getGeneInfo())){
                    continue;
                }

                //除去相同检材
//                if (!sampleNo.equals(lsgVO.getSampleNo())&& !limsSampleGeneVo.getEntity().getCaseId().equals(lsgVO.getEntity().getCaseId())) {
                if (!sampleNo.equals(lsgVO.getSampleNo())) {
                    flag = CodisFileParser.compareGeneInfo(limsSampleGeneVo.getEntity().getGeneInfo(), lsgVO.getEntity().getGeneInfo());
                    break;
                }
            }
        }

        return flag;
    }

    /**
     * 根据图片路径查看照片
     *
     * @param request
     * @param response
     * @param imagePath
     */
    @RequestMapping("/viewImage")
    public void viewImage(HttpServletRequest request, HttpServletResponse response, String imagePath) {
        FileInputStream fis = null;
        OutputStream os = null;
        try {
            if (StringUtils.isNotBlank(imagePath)) {
                try {
                    imagePath = URLDecoder.decode(imagePath, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            fis = new FileInputStream(imagePath);
            os = response.getOutputStream();
            int count = 0;
            byte[] buffer = new byte[1024 * 8];
            while ((count = fis.read(buffer)) != -1) {
                os.write(buffer, 0, count);
                os.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            fis.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除标记
     *
     * @return
     */
    @RequestMapping(value = "/removeProblem", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> removeProblem(HttpServletRequest request, String geneId) {
        Map<String, Object> map = new HashMap<>();

        try {
            //获取当前登录人信息
            Subject subject = SecurityUtils.getSubject();
            LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
            if (null == operateUser) {
                map.put("date", "redirect:/login.html?timeoutFlag=1");
                return map;
            }
            LimsSampleGene limsSampleGene = new LimsSampleGene();
            limsSampleGene.setGeneId(geneId);
            limsSampleGene.setAuditStatus(Constants.QUESTION_TYPES_01);
            limsSampleGeneService.updateDeleteFlag(limsSampleGene);
            map.put("success", true);
        } catch (Exception e) {
            logger.error("deleteFlag删除标记 error!", e);
            map.put("success", false);
        }

        return map;
    }

    /**
     * 问题标记
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/questionFlag", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> questionFlag(@RequestParam(value = "params") String params) {
        Map<String, Object> returnMap = new HashMap();

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if (operateUser == null) {
            returnMap.put("date", "redirect:/login.html?timeoutFlag=1");
            return returnMap;
        }
        try {
            JSONObject str = JSON.parseObject(params);
            List<LimsSampleGene> limsSampleGeneList = new ArrayList<>();
            if (str.containsKey("limsSampleGeneArr")) {
                limsSampleGeneList = (List<LimsSampleGene>) JSON.parseArray(str.get("limsSampleGeneArr").toString(), LimsSampleGene.class);
            }
            //遍历前台传来的要分配权限的数据
            for (LimsSampleGene limsSampleGene : limsSampleGeneList) {
                LimsSampleGene limsSampleGene1 = new LimsSampleGene();
                limsSampleGene1.setGeneId(limsSampleGene.getGeneId());
                limsSampleGene1.setAuditStatus(Constants.QUESTION_TYPES_07);
                limsSampleGeneService.updateDeleteFlag(limsSampleGene1);
            }
            returnMap.put("success", true);
        } catch (Exception ex) {
            logger.info("复检失败:" + ex);
            returnMap.put("success", false);
        }

        return returnMap;
    }


    /**
     * 确认审核并上报
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/confirmAuditAndReport", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> confirmAuditAndReport(HttpServletRequest request, @RequestParam(value = "params") String params, String id) {
        Map<String, Object> returnMap = new HashMap<>();

        try {
            //获取当前登录人信息
            Subject subject = SecurityUtils.getSubject();
            LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
            if (null == operateUser) {
                returnMap.put("date", "redirect:/login.html?timeoutFlag=1");
                return returnMap;
            }

            String userOrgId = operateUser.getOrgId();
            //将当前用户org_id设置进query
            if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
                userOrgId = "110230000000";
            }

            //查询当前人员信息
            AmPersonalInfo amPersonalInfo = amPersonalInfoService.queryAmPersonalInfo(operateUser.getPersonalId());

            JSONObject str = JSON.parseObject(params);
            List<LimsSampleGene> limsSampleGeneList = new ArrayList<>();
            if (str.containsKey("limsSampleGeneArr")) {
                limsSampleGeneList = (List<LimsSampleGene>) JSON.parseArray(str.get("limsSampleGeneArr").toString(), LimsSampleGene.class);
                if (null != limsSampleGeneList && limsSampleGeneList.size() > 0) {
                    for (LimsSampleGene limsSampleGene : limsSampleGeneList) {
                        //更新基因型表审核状态为已审核
                        LimsSampleGene limsSampleGene1 = limsSampleGeneService.selectByPrimaryKey(limsSampleGene.getGeneId());
                        String sampleId = limsSampleGene1.getSampleId();
                        List<LimsSampleGene> sampleGeneList = limsSampleGeneService.selectListBySampleId(sampleId);
                        for (LimsSampleGene lsg : sampleGeneList) {
                            if (lsg.getAuditStatus().equals(Constants.AUDIT_STATUS_1)) {
                                lsg.setAuditor(operateUser.getLoginName());
                                lsg.setAuditStatus(Constants.AUDIT_STATUS_0);
                                lsg.setAuditDatetime(new Date());
                                lsg.setUpdateDatetime(new Date());
                                lsg.setUpdatePerson(operateUser.getLoginName());
                                limsSampleGeneService.updateAuditStatus(lsg);
                            }
                        }
                        //todo
                        if (StringUtils.isNotBlank(limsSampleGene1.getGeneInfo())) {
                            List<String> markerList = geneSameCompareUtil.getMarker(limsSampleGene1.getGeneInfo());
                            if (markerList.size()!=0){
                                for (String s : markerList) {
                                    String[] split = geneSameCompareUtil.analysisGeneStr(s, limsSampleGene1.getGeneInfo()).split("/");
                                    //审核通过的检材且基因座少于8个时，表示未检出
                                    if(split.length > 2){
                                        limsSampleGene1.setGeneType("4");
                                        break;
                                    }else{
                                        limsSampleGene1.setGeneType("0");
                                    }
                                }

                            }

                        }
                        limsSampleGene1.setAuditor(operateUser.getLoginName());
                        limsSampleGene1.setAuditStatus(Constants.AUDIT_STATUS_1);
                        limsSampleGene1.setAuditDatetime(new Date());
                        limsSampleGene1.setUpdateDatetime(new Date());
                        limsSampleGene1.setUpdatePerson(operateUser.getLoginName());
                        limsSampleGene1.setGeneId(limsSampleGene.getGeneId());
                        limsSampleGeneService.updateAuditStatus(limsSampleGene1);
                        List<MatchAuditedGene> matchAuditedGeneList = matchAuditedGeneService.selectBySampleId(limsSampleGene1.getSampleId());
                        if (ListUtils.isNotNullAndEmptyList(matchAuditedGeneList)) {
                            //修改数据
                            MatchAuditedGene matchAuditedGene = matchAuditedGeneList.get(0);
                            matchAuditedGene.setAuditedGeneId(limsSampleGene1.getGeneId());
                            matchAuditedGene.setSampleId(limsSampleGene1.getSampleId());
                            matchAuditedGene.setPanelId(limsSampleGene1.getPanelId());
                            matchAuditedGene.setGeneType(limsSampleGene1.getGeneType());
                            matchAuditedGene.setGeneInfo(limsSampleGene1.getGeneInfo());
                            if(limsSampleGene1.getGeneInfo()!= null){
                                int count = geneSameCompareUtil.getMarker(limsSampleGene1.getGeneInfo()).size();
                                matchAuditedGene.setGeneCount((short)count);
                            }else{
                                matchAuditedGene.setGeneCount((short) 0);
                            }
                            matchAuditedGene.setImagePath(limsSampleGene1.getImagePath());
                            matchAuditedGeneService.updateBySampleId(matchAuditedGene);

                        } else {
                            //已审核的 插入基因列表
                            MatchAuditedGene matchAuditedGene = new MatchAuditedGene();
                            matchAuditedGene.setAuditedGeneId(limsSampleGene1.getGeneId());
                            matchAuditedGene.setSampleId(limsSampleGene1.getSampleId());
                            matchAuditedGene.setPanelId(limsSampleGene1.getPanelId());
                            matchAuditedGene.setGeneType(limsSampleGene1.getGeneType());
                            matchAuditedGene.setGeneInfo(limsSampleGene1.getGeneInfo());
                            if(limsSampleGene1.getGeneInfo()!= null){
                                int count = geneSameCompareUtil.getMarker(limsSampleGene1.getGeneInfo()).size();
                                matchAuditedGene.setGeneCount((short)count);
                            }else{
                                matchAuditedGene.setGeneCount((short) 0);
                            }
                            matchAuditedGene.setImagePath(limsSampleGene1.getImagePath());
                            matchAuditedGene.setCreateDatetime(new Date());
                            matchAuditedGene.setCreatePerson(operateUser.getLoginName());
                            matchAuditedGeneService.insert(matchAuditedGene);
                        }

                        //写入队列表，检出基因分型时，写 入QUEUE_TYPE='24', STATUS='0'的队列数据，同时子表QUEUE_DETAIL写入已检出的物证检材数据（有W号检材），标记该现场案件中物证已检出
                        List<LimsSampleInfoDna> limsSampleInfoDna = limsSampleInfoDnaService.selectBySampleId(limsSampleGene1.getSampleId());
                        if (limsSampleInfoDna.size() > 0) {
                            if (limsSampleInfoDna.get(0) != null) {
                                LimsConsignmentInfo limsConsignmentInfo = limsConsignmentInfoMapper.selectByConsignmentId(limsSampleInfoDna.get(0).getConsignmentId());
                                QueueSample queueSample = new QueueSample();
                                String qsId = UUID.randomUUID().toString();
                                queueSample.setId(qsId);
                                queueSample.setForeignId(limsSampleInfoDna.get(0).getCaseId());
                                queueSample.setCreateDatetime(new Date());// new Date()为获取当前系统时间
                                queueSample.setQueueType(FeedBackXckyConstants.QUEUE_TYPE_EVIDENCE_GENE);//受理时队列类型
                                queueSample.setStatus(0);
                                if(limsConsignmentInfo != null){
                                    queueSample.setServerNo(limsConsignmentInfo.getAcceptOrgId());
                                }
                                queueSampleMapper.insertQueueSample(queueSample);
                                if (StringUtils.isNotEmpty(limsSampleInfoDna.get(0).getEvidenceNo())) {
                                    QueueDetail queueDetail = new QueueDetail();
                                    String queueDetailId = UUID.randomUUID().toString();
                                    queueDetail.setId(queueDetailId);
                                    if (queueSample != null) {
                                        queueDetail.setQueueId(queueSample.getId());
                                    }
                                    queueDetail.setSampleId(limsSampleGene1.getSampleId());
                                    queueDetail.setSampleNo(limsSampleInfoDna.get(0).getSampleNo());
                                    queueDetail.setConsignmentId(limsSampleInfoDna.get(0).getCaseId());
                                    queueDetail.setCreateDatetime(new Date());
                                    queueDetailMapper.insert(queueDetail);
                                }
                            }
                        }
                    }


                    //更新分析任务表审核状态
                    LabAnalysisInfo labAnalysisInfo = labAnalysisInfoService.selectByPrimaryKey(id);
                    if (labAnalysisInfo != null) {
                        if (StringUtils.isNotBlank(labAnalysisInfo.getReviewCount()) && Integer.parseInt(labAnalysisInfo.getReviewCount()) == 0) {
                            labAnalysisInfo.setId(id);
                            labAnalysisInfo.setAuditStatus(Constants.AUDIT_STATUS_0);
                            labAnalysisInfo.setAuditDatetime(new Date());
                            labAnalysisInfo.setUpdateDatetime(new Date());
                            labAnalysisInfo.setReviewCount(Integer.toString(limsSampleGeneList.size()));
                            labAnalysisInfo.setAuditor(amPersonalInfo.getFullName());
                            labAnalysisInfo.setLastUpdator(operateUser.getLoginName());
                            labAnalysisInfoService.updateuSatus(labAnalysisInfo);
                        }else {
                            labAnalysisInfo.setId(id);
                            labAnalysisInfo.setAuditStatus(Constants.AUDIT_STATUS_1);
                            labAnalysisInfo.setAuditor(amPersonalInfo.getFullName());
                            labAnalysisInfo.setAuditDatetime(new Date());
                            labAnalysisInfo.setReviewCount(Integer.toString(limsSampleGeneList.size()));
                            labAnalysisInfo.setUpdateDatetime(new Date());
                            labAnalysisInfo.setLastUpdator(operateUser.getLoginName());
                            labAnalysisInfoService.updateuSatus(labAnalysisInfo);
                        }
                    }
                }
            }
            if (str.containsKey("Issue")) {
                limsSampleGeneList = (List<LimsSampleGene>) JSON.parseArray(str.get("Issue").toString(), LimsSampleGene.class);
                if (null != limsSampleGeneList && limsSampleGeneList.size() > 0) {
                    for (LimsSampleGene limsSampleGene : limsSampleGeneList) {
                        ReviewQueueSample reviewQueueSample = new ReviewQueueSample();
                        reviewQueueSample.setId(UUID.randomUUID().toString());
                        LimsSampleGene lsg = limsSampleGeneService.selectByPrimaryKey(limsSampleGene.getGeneId());
                        if (lsg != null) {
                            reviewQueueSample.setSampleId(lsg.getSampleId());
                        }
                        reviewQueueSample.setReviewSampleStatus("0");
                        reviewQueueSample.setCreateDatetime(new Date());
                        reviewQueueSample.setCreatePerson(operateUser.getLoginName());
                        reviewQueueSample.setGeneId(limsSampleGene.getGeneId());
                        reviewQueueSample.setOrgId(userOrgId);
                        reviewQueueSampleService.insert(reviewQueueSample);
                    }

                }

            }

        } catch (Exception e) {
            logger.info("确认审核并上报失败:" + e);
        }

        return returnMap;
    }

    /**
     * 按指定大小，分隔集合，将集合按规定个数分为n个部分
     *
     * @param list
     * @param len
     * @return
     */
    public static List<List<LimsSampleGeneVo>> splitList(List<LimsSampleGeneVo> list, int len) {
        if (list == null || list.size() == 0 || len < 1) {
            return null;
        }

        List<List<LimsSampleGeneVo>> result = new ArrayList<List<LimsSampleGeneVo>>();


        int size = list.size();
        int count = (size + len - 1) / len;


        for (int i = 0; i < count; i++) {
            List<LimsSampleGeneVo> subList = list.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
            result.add(subList);
        }
        return result;
    }

    public static LabAnalysisInfoVo resetLabAnalysisQuery(LabAnalysisInfoVo query) {
        if (null == query.getAuditStartDatetime()) {
            query.setAuditStartDatetime(null);
        } else {
            query.setAuditStartDatetime(query.getAuditStartDatetime());
        }

        if (null == query.getAuditEndDatetime()) {
            query.setAuditEndDatetime(null);
        } else {
            query.setAuditEndDatetime(query.getAuditEndDatetime());
        }

        if (StringUtils.isBlank(query.getEntity().getAuditStatus())) {
            query.getEntity().setAuditStatus(null);
        } else {
            query.getEntity().setAuditStatus(query.getEntity().getAuditStatus().trim());
        }

        if (StringUtils.isBlank(query.getEntity().getAuditor())) {
            query.getEntity().setAuditor(null);
        } else {
            query.getEntity().setAuditor(query.getEntity().getAuditor().trim());
        }

        if (StringUtils.isBlank(query.getEntity().getBoardNo())) {
            query.getEntity().setBoardNo(null);
        } else {
            query.getEntity().setBoardNo(query.getEntity().getBoardNo().trim());
        }

        if (StringUtils.isBlank(query.getSampleNo())) {
            query.setSampleNo(null);
        } else {
            query.setSampleNo(query.getSampleNo().trim());
        }

        if (StringUtils.isBlank(query.getEntity().getReviewCount())) {
            query.getEntity().setReviewCount(null);
        } else {
            query.getEntity().setReviewCount(query.getEntity().getReviewCount().trim());
        }

        if (StringUtils.isBlank(query.getEntity().getAnalysisPerson())) {
            query.getEntity().setAnalysisPerson(null);
        } else {
            query.getEntity().setAnalysisPerson(query.getEntity().getAnalysisPerson().trim());
        }

        return query;
    }

    /**
     * 复检
     *
     * @param request
     * @param params
     * @return
     */
    @RequestMapping(value = "/recheck", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> recheck(HttpServletRequest request, @RequestParam(value = "params") String params) {
        Map<String, Object> map = new HashMap<>();

        try {
            //获取当前登录人信息
            Subject subject = SecurityUtils.getSubject();
            LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
            if (null == operateUser) {
                map.put("date", "redirect:/login.html?timeoutFlag=1");
                return map;
            }

            JSONObject str = JSON.parseObject(params);
            List<LimsSampleGene> limsSampleGeneList = new ArrayList<>();
            if (str.containsKey("limsSampleGeneArr")) {
                limsSampleGeneList = (List<LimsSampleGene>) JSON.parseArray(str.get("limsSampleGeneArr").toString(), LimsSampleGene.class);
            }
            List list = new ArrayList<>();
            //遍历前台传来的要分配权限的数据
            for (LimsSampleGene limsSampleGene : limsSampleGeneList) {
                LimsSampleGene limsSampleGene1 = limsSampleGeneService.selectByPrimaryKey(limsSampleGene.getGeneId());
                limsSampleGene1.setUpdateDatetime(new Date());
                limsSampleGene1.setUpdatePerson(operateUser.getLoginName());
                limsSampleGene1.setReviewStatus("1");
                limsSampleGene1.setGeneId(limsSampleGene.getGeneId());
                //更新复检状态
                limsSampleGeneService.updateReviewStatus(limsSampleGene1);
                //新增复检队列表
                ReviewQueueSample reviewQueueSample = new ReviewQueueSample();
                reviewQueueSample.setId(UUID.randomUUID().toString());
                reviewQueueSample.setSampleId(limsSampleGene1.getSampleId());
                reviewQueueSample.setReviewSampleStatus("1");
                reviewQueueSample.setCreateDatetime(new Date());
                reviewQueueSample.setCreatePerson(operateUser.getLoginName());
                reviewQueueSample.setGeneId(limsSampleGene1.getGeneId());
                reviewQueueSampleService.insert(reviewQueueSample);
                map.put("success", true);
                list.add(reviewQueueSample.getId() + "," + limsSampleGene1.getBoardPosition() + "," + limsSampleGene1.getBoardNo());
            }
            map.put("date", list);
        } catch (Exception e) {
            logger.error("updateAuditStaus error!", e);
            map.put("success", false);
        }

        return map;
    }

    /**
     * 删除复检
     *
     * @param request
     * @param geneId
     * @return
     */
    @RequestMapping(value = "/deleteReviewQueueSample", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> deleteReviewQueueSample(HttpServletRequest request, String geneId) {
        Map<String, Object> map = new HashMap<>();

        try {
            //获取当前登录人信息
            Subject subject = SecurityUtils.getSubject();
            LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
            if (null == operateUser) {
                map.put("date", "redirect:/login.html?timeoutFlag=1");
                return map;
            }
            //以逗号分隔gened
            String[] geneIdArr = geneId.split(",");
            //for循环更新基因表复检状态
            for (String geneIds : geneIdArr) {
                LimsSampleGene limsSampleGene = limsSampleGeneService.selectByPrimaryKey(geneIds);
                limsSampleGene.setReviewStatus("0");
                limsSampleGeneService.updateByPrimaryKey(limsSampleGene);
                //删除复检队列表
                reviewQueueSampleService.deleteByGeneId(geneIds);
            }
            map.put("success", true);
        } catch (Exception e) {
            logger.error("删除复检 error!", e);
            map.put("success", false);
        }

        return map;
    }

    /**
     * 判断分析文件的编辑状态
     * @param request
     * @param labAnalysisInfoId
     * @return
     */
    @RequestMapping(value = "/analysisResultFileEditStatus", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> analysisResultFileEditStatus(HttpServletRequest request, String labAnalysisInfoId) {
        Map<String, Object> map = new HashMap<>();

        try {
            //获取当前登录人信息
            Subject subject = SecurityUtils.getSubject();
            LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
            if (null == operateUser) {
                map.put("date", "redirect:/login.html?timeoutFlag=1");
                return map;
            }
            LabAnalysisInfo labAnalysisInfo = labAnalysisInfoService.selectByPrimaryKey(labAnalysisInfoId);
            map.put("isLock", labAnalysisInfo.getIsLock());
            map.put("success", true);
        } catch (Exception e) {
            logger.error("判断分析文件的编辑状态 error!", e);
            map.put("success", false);
        }

        return map;
    }


    @RequestMapping(value = "/updateSampleRelation", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> updateSampleRelation(HttpServletRequest request, String codeSampleNos,String sampleNo,String isRelation) {
        Map<String, Object> map = new HashMap<>();

        try {
            if (StringUtils.isNotBlank(codeSampleNos) && StringUtils.isNotBlank(sampleNo) && StringUtils.isNotBlank(isRelation)){
                String[] split = codeSampleNos.split(",");
                for (String s : split) {
                    LimsSampleInfoDna limsSampleInfoDna = new LimsSampleInfoDna();
                    limsSampleInfoDna.setSampleNo(s);
                    limsSampleInfoDna.setRelationSampleNo(sampleNo);
                    limsSampleInfoDna.setIsRelation(isRelation);
                    limsSampleInfoDnaService.updateSamples(limsSampleInfoDna);
                }
                for (String s : split) {
                    LimsSampleInfoDna limsSampleInfoDna = new LimsSampleInfoDna();
                    limsSampleInfoDna.setSampleNo(sampleNo);
                    limsSampleInfoDna.setRelationSampleNo(s);
                    limsSampleInfoDna.setIsRelation(isRelation);
                    int i = limsSampleInfoDnaService.updateSamples(limsSampleInfoDna);
                    if (i!=0){
                        map.put("success", true);
                    }else {
                        map.put("success", false);
                    }
                }
            }
        } catch (Exception e) {
            map.put("success", false);
        }

        return map;
    }

    //解除关联
    @RequestMapping(value = "/updateCodeSampleRelation", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> updateCodeSampleRelation(HttpServletRequest request, String codeSampleNos,String isRelation) {
        Map<String, Object> map = new HashMap<>();

        try {
            if (StringUtils.isNotBlank(codeSampleNos)  && StringUtils.isNotBlank(isRelation)){
                String[] split = codeSampleNos.split(",");
                for (String s : split) {
                    List<LimsSampleInfoDna> limsSampleInfoDnas = limsSampleInfoDnaService.selectBySampleNo(s);
                    LimsSampleInfoDna limsSampleInfoDna = new LimsSampleInfoDna();
                    limsSampleInfoDna.setSampleNo(limsSampleInfoDnas.get(0).getRelationSampleNo());
                    limsSampleInfoDna.setRelationSampleNo("");
                    limsSampleInfoDna.setIsRelation(isRelation);
                    limsSampleInfoDnaService.updateSamples(limsSampleInfoDna);
                    limsSampleInfoDna.setSampleNo(s);
                    limsSampleInfoDna.setRelationSampleNo("");
                    limsSampleInfoDna.setIsRelation(isRelation);
                    int i = limsSampleInfoDnaService.updateSamples(limsSampleInfoDna);
                    if (i!=0){
                        map.put("success", true);
                    }else {
                        map.put("success", false);
                    }
                }
            }
        } catch (Exception e) {
            map.put("success", false);
        }

        return map;
    }




}
