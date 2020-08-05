package com.bazl.clims.controller.center;

import com.bazl.clims.common.Constants;
import com.bazl.clims.controller.BaseController;
import com.bazl.clims.helper.codis.CodisContentModel;
import com.bazl.clims.helper.codis.CodisFileParser;
import com.bazl.clims.model.LoaUserInfo;
import com.bazl.clims.model.po.*;
import com.bazl.clims.model.vo.LabAnalysisInfoVo;
import com.bazl.clims.service.*;
import com.bazl.clims.utils.ListUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

/**
 *@author wanghaiyang
 * @date 2019/5/14.
 */
@Controller
@RequestMapping("/center")
public class CodisController extends BaseController {

    @Autowired
    LimsSampleInfoDnaService limsSampleInfoDnaService;
    @Autowired
    LimsSampleGeneService limsSampleGeneService;
    @Autowired
    LabAnalysisInfoService labAnalysisInfoService;
    @Autowired
    AmPersonalInfoService amPersonalInfoService;
    @Autowired
    PanelService panelService;

    public static String geneid;

    @RequestMapping("/codisImport")
    public ModelAndView codisImport(HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("testToReview/importCodis");

        //试剂盒
        List<Panel> panels = panelService.selectAll();
        modelAndView.addObject("panels",panels);

        return modelAndView;
    }

    //上传
    @RequestMapping(value = "/uploadCodis", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> uploadCodis(HttpServletRequest request, HttpServletResponse response,  @RequestParam("codisFile") List<MultipartFile> codisFiles, String reagentName,Integer types) {
        Map<String, Object> resultMap = new HashMap<>();

        //获取当前登录用户
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        //获取当前用户的id
        String userOrgId = operateUser.getOrgId();
        //将当前用户org_id设置进query
        if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
            userOrgId = "110230000000";
        }


        //查询当前人员信息
        AmPersonalInfo amPersonalInfo = amPersonalInfoService.queryAmPersonalInfo(operateUser.getPersonalId());

        if (StringUtils.isNotBlank(reagentName)) {
            try {
                reagentName = URLDecoder.decode(reagentName, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        int codisContentModelListSiz = 0;
        int succeedListSiz = 0;
        int failedListSiz = 0;

        List<CodisContentModel> failedList = new ArrayList<CodisContentModel>();
        List<CodisContentModel> succeedList = new ArrayList<CodisContentModel>();
        for (MultipartFile codisFile:codisFiles) {
            List<CodisContentModel> failedCountList = new ArrayList<CodisContentModel>();
            List<CodisContentModel> succeedCountList = new ArrayList<CodisContentModel>();

            String boardNo = null;
            List<CodisContentModel> codisContentModelList = null;
            try {//解析
                codisContentModelList = CodisFileParser.getDataCodisFile(codisFile.getInputStream(), codisFile.getOriginalFilename(), reagentName);
            } catch (Exception ex) {
                resultMap.put("success", false);
                resultMap.put("errMsg", ex.getMessage());
                return resultMap;
            }

            if (ListUtils.isNullOrEmptyList(codisContentModelList)) {
                resultMap.put("success", false);
                resultMap.put("errMsg", "CODIS文件中未检索到数据！");
                return resultMap;
            }

            LabAnalysisInfo labAnalysisInfo = new LabAnalysisInfo();
            labAnalysisInfo.setId(UUID.randomUUID().toString());
            if (StringUtils.isNotBlank(codisFile.getOriginalFilename())) {
                try {
                    boardNo = URLDecoder.decode(codisFile.getOriginalFilename(), "UTF-8");
                    boardNo = boardNo.substring(0,boardNo.lastIndexOf("."));
                    //判断导入文件名称再数据库板号是否存在
                  if (repetitionCodis(boardNo).get("success").equals(false)){
                        resultMap.put("success", false);
                        resultMap.put("errMsg", "CODIS文件名已存在！");
                        return resultMap;
                    }
                } catch (UnsupportedEncodingException e) {
                    resultMap.put("success", false);
                    resultMap.put("errMsg", "CODIS文件名已存在！");
                    return resultMap;
                }
            }

            List<LimsSampleInfoDna> sampleListBySampleNo = null;
            LimsSampleInfoDna tmpSampleInfo = null;
            List<LimsSampleGene> tmpSampleGeneList = null;
            for (CodisContentModel ccm : codisContentModelList) {
                sampleListBySampleNo = limsSampleInfoDnaService.selectBySampleNo(ccm.getSampleNo().trim());
                if (ListUtils.isNullOrEmptyList(sampleListBySampleNo)) {
                    ccm.setImportFlag(Constants.FLAG_FALSE);
                    ccm.setFailedReason("系统中不存在该检材编号！");
                    failedCountList.add(ccm);
                    continue;
                }

                boolean importFlag = CodisFileParser.checkValidGene(ccm.getGeneInfo());
                if (!importFlag) {
                    ccm.setImportFlag(Constants.FLAG_FALSE);
                    ccm.setFailedReason("该检材的基因型信息全为空！");
                    failedCountList.add(ccm);
                    continue;
                }

                try {
                    tmpSampleInfo = sampleListBySampleNo.get(0);
                    tmpSampleGeneList = limsSampleGeneService.selectListBySampleId(tmpSampleInfo.getSampleId());
                    if (ListUtils.isNotNullAndEmptyList(tmpSampleGeneList)) {

                        boolean flag = false;
                        String geneInfo = ccm.getGeneInfo();
                        LimsSampleGene sampleGene = null;

                        for (int j = 0; j < tmpSampleGeneList.size(); j++) {
                            sampleGene = tmpSampleGeneList.get(j);
                            if (CodisFileParser.compareGeneInfo(geneInfo, sampleGene.getGeneInfo())) {
                                ccm.setImportFlag(Constants.FLAG_FALSE);
                                ccm.setFailedReason("系统中已存在该检材编号相同的检验结果！");
                                failedCountList.add(ccm);
                                flag = false;
                                break;
                            } else {
                                flag = true;
                            }
                        }

                        if (flag) {
                            //插入数据
                            limsSampleGeneService.insert(ConvertCodisContentToSampleGene(tmpSampleInfo, ccm, reagentName, types, labAnalysisInfo));
                            ccm.setImportFlag(Constants.FLAG_TRUE);
                            ccm.setGeneid(geneid);
                            succeedCountList.add(ccm);
                        }

                    } else {
                        //插入数据
                        limsSampleGeneService.insert(ConvertCodisContentToSampleGene(tmpSampleInfo, ccm, reagentName, types, labAnalysisInfo));
                        ccm.setImportFlag(Constants.FLAG_TRUE);
                        ccm.setGeneid(geneid);
                        succeedCountList.add(ccm);
                    }
                } catch (Exception ex) {
                    ccm.setImportFlag(Constants.FLAG_FALSE);
                    ccm.setFailedReason("数据格式错误！");
                    failedCountList.add(ccm);
                }
            }

            //导入成功数为0删除信息
            if (ListUtils.isNotNullAndEmptyList(succeedCountList)) {
                try {
                    if (amPersonalInfo != null) {
                        labAnalysisInfo.setAnalysisPerson(amPersonalInfo.getFullName());
                        labAnalysisInfo.setCreatePerson(amPersonalInfo.getLoginName());
                    }
                    labAnalysisInfo.setBoardNo(boardNo);
                    labAnalysisInfo.setAuditStatus(Constants.AUDIT_STATUS_0);
                    labAnalysisInfo.setKitName(reagentName);
                    labAnalysisInfo.setSampleCount(succeedCountList.size() + "");
                    labAnalysisInfo.setErrorCount(failedCountList.size() + "");
                    labAnalysisInfo.setAnalysisDatetime(new Date());
                    labAnalysisInfo.setUpdateDatetime(new Date());
//                    labAnalysisInfo.setAuditDatetime(new Date());
                    labAnalysisInfo.setDelegateOrgCode(userOrgId);
                    //插入数据库LAB_ANALYSIS_INFO
                    labAnalysisInfoService.insert(labAnalysisInfo);
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }

            codisContentModelListSiz = codisContentModelListSiz + codisContentModelList.size();
            succeedListSiz += succeedCountList.size();
            failedListSiz += failedCountList.size();

            succeedList.addAll(succeedCountList);
            failedList.addAll(failedCountList);
        }
        resultMap.put("success", true);
        resultMap.put("sampleCountInFile", codisContentModelListSiz);
        resultMap.put("succeedCountInFile", succeedListSiz);
        resultMap.put("failedCountInFile", failedListSiz);
        resultMap.put("succeedList", succeedList);
        resultMap.put("failedList", failedList);

        return resultMap;
    }

    //板号去重
    @RequestMapping("/repetitionCodis")
    @ResponseBody
    public Map<String, Object> repetitionCodis(String boardNo) {
        Map<String, Object> map = new HashMap<>();

        //获取当前登录用户
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo loaUserInfo = (LoaUserInfo) subject.getPrincipal();
        //获取当前用户的id
        String userOrgId = loaUserInfo.getOrgId();
        //将当前用户org_id设置进query
        if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
            userOrgId = "110230000000";
        }
        LabAnalysisInfoVo labAnalysisInfoVo = new LabAnalysisInfoVo();
        labAnalysisInfoVo.getEntity().setBoardNo(boardNo);
        labAnalysisInfoVo.setUserOrdId(userOrgId);
        List<LabAnalysisInfoVo> labAnalysisInfoVos = labAnalysisInfoService.selectVOByBoardNo(labAnalysisInfoVo);
        if (ListUtils.isNotNullAndEmptyList(labAnalysisInfoVos)){
            map.put("success", false);
        }else {
            map.put("success", true);
        }

        return map;
    }


    private LimsSampleGene ConvertCodisContentToSampleGene(LimsSampleInfoDna sampleInfo, CodisContentModel codisContentModel, String reagentName,Integer types,LabAnalysisInfo labAnalysisInfo) {
        LimsSampleGene sampleGene = new LimsSampleGene();

        sampleGene.setGeneId(UUID.randomUUID().toString());
        sampleGene.setCaseId(sampleInfo.getCaseId());
        sampleGene.setConsignmentId(sampleInfo.getConsignmentId());
        sampleGene.setLabAnalysisInfoId(labAnalysisInfo.getId());
        sampleGene.setSampleId(sampleInfo.getSampleId());
        sampleGene.setGeneInfo(codisContentModel.getGeneInfo());
        sampleGene.setGeneType(CodisFileParser.isGeneType(codisContentModel.getGeneInfo(),types));
        sampleGene.setAuditStatus(Constants.FLAG_FALSE);
        sampleGene.setReviewStatus(Constants.FLAG_FALSE);
        sampleGene.setPanelName(reagentName);
        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if (operateUser != null) {
            sampleGene.setCreatePerson(operateUser.getLoginName());
        }
        sampleGene.setCreateDatetime(new Date());
        sampleGene.setDeleteFlag(Constants.FLAG_FALSE);

        geneid = sampleGene.getGeneId();
        return sampleGene;
    }

}
