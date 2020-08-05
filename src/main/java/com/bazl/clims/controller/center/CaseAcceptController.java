package com.bazl.clims.controller.center;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bazl.clims.common.Constants;
import com.bazl.clims.controller.BaseController;
import com.bazl.clims.dao.LogRecordMapper;
import com.bazl.clims.dao.MobileNewsMapper;
import com.bazl.clims.model.DictItem;
import com.bazl.clims.model.LoaUserInfo;
import com.bazl.clims.model.bo.DelegateDataModel;
import com.bazl.clims.model.po.*;
import com.bazl.clims.model.vo.FugitivesInfoVo;
import com.bazl.clims.service.*;
import com.bazl.clims.utils.*;
import com.sun.jna.Library;
import com.sun.jna.Native;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by Sun on 2018/12/19.
 */
@Controller
@RequestMapping("/center")
public class CaseAcceptController extends BaseController {

    @Autowired
    LimsConsignmentInfoService limsConsignmentInfoService;

    @Autowired
    LimsCaseInfoService limsCaseInfoService;

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

    @Autowired
    OrgInfoService orgInfoService;

    @Autowired
    FileArchivesInfoService fileArchivesInfoService;

    @Autowired
    DownloadFileUtils downloadFileUtils;

    @Autowired
    MobileNewsMapper mobileNewsMapper;

    @Autowired
    LogRecordMapper logRecordMapper;

    @Autowired
    ExperimentalParameterService experimentalParameterService;

    @Autowired
    CompareSameResultService compareSameResultService;

    @Autowired
    CompareRelativeResultService compareRelativeResultService;

    @Autowired
    MatchSameResultService matchSameResultService;

    @Autowired
    MatchRelativeResultService matchRelativeResultService;
    @Autowired
    FugitivesInfoService fugitivesInfoService;

    @Value("${proxyImg}")
    private String proxyImg;
    @Value("${appointImg}")
    private String appointImg;
    @Value("${ftpIp}")
    private String ftpIp;
    @Value("${ftpPort}")
    private String ftpPort;
    @Value("${ftpProxyImg}")
    private String ftpProxyImg;
    @Value("${ftpAppointImg}")
    private String ftpAppointImg;
    @Value("${ftpUser}")
    private String ftpUser;
    @Value("${ftpPassword}")
    private String ftpPassword;
    @Value("${ftpFilePath}")
    private String ftpFilePath;
    //    @Value("${printUrl}")
//    private String printUrl;
    @Value("${page}")
    private String page;
    @Value("${bendiFilePath}")
    private String bendiFilePath;

    @Value("${Is_App_Inform}")
    private int isAppInform;
    @Value("${Is_App_Url}")
    private String AppUrl;
    @Value("${ftpCaseFilePath}")
    private String ftpCaseFilePath;

    @RequestMapping("/upload")
    public ModelAndView upload(HttpServletRequest request, String caseId, String consignmentId) {
        ModelAndView view = new ModelAndView();
        try {
            view.addObject("caseId", caseId);
            view.addObject("consignmentId", consignmentId);
        } catch (Exception ex) {
            logger.info("查询失败:" + ex);
        }
        view.setViewName("accept/upload");
        return view;
    }

    @ResponseBody
    @RequestMapping("/uploadImg")
    public Map uploadImg(@RequestParam(value = "paramsData") String paramsData) {
        Map result = new HashMap<>();
        try {
            JSONObject str = JSON.parseObject(paramsData);
            List<FileArchivesInfo> fileArchivesInfoList = new ArrayList<>();
            //解析前台传来的数据
            if (str.containsKey("fileArchivesInfoList")) {
                fileArchivesInfoList = (List<FileArchivesInfo>) JSON.parseArray(str.get("fileArchivesInfoList").toString(), FileArchivesInfo.class);
                for (FileArchivesInfo fileArchivesInfo : fileArchivesInfoList) {
                    fileArchivesInfo.setId(UUID.randomUUID().toString());
                    if (fileArchivesInfo.getArchivesType().equals(Constants.ARCHIVES_TYPE_PROXY)) {
                        //对委托书照片进行上传
                        String personFrontPicturePath = ImgUtils.generateImage(fileArchivesInfo.getArchivesInfoPicture(), proxyImg);
                        String personFrontPicturePathFtp = UplodFtpUtils.uploadFtpFile(ftpIp, ftpPort, ftpUser, ftpPassword, ftpProxyImg, personFrontPicturePath);
                        fileArchivesInfo.setArchivesInfoPath(personFrontPicturePathFtp == null ? "" : personFrontPicturePathFtp);
                    } else if (fileArchivesInfo.getArchivesType().equals(Constants.ARCHIVES_TYPE_APPOINT)) {
                        //对委托书照片进行上传
                        String personFrontPicturePath = ImgUtils.generateImage(fileArchivesInfo.getArchivesInfoPicture(), appointImg);
                        String personFrontPicturePathFtp = UplodFtpUtils.uploadFtpFile(ftpIp, ftpPort, ftpUser, ftpPassword, ftpAppointImg, personFrontPicturePath);
                        fileArchivesInfo.setArchivesInfoPath(personFrontPicturePathFtp == null ? "" : personFrontPicturePathFtp);
                    }

                    fileArchivesInfoService.insert(fileArchivesInfo);
                }
            }
        } catch (Exception ex) {
            logger.info("上传文书失败:" + ex);
            result.put("result", false);
            result.put("message", ex.getMessage());
            return result;
        }
        result.put("result", true);
        return result;
    }

    @RequestMapping("/caseAcceptDetails")
    @SuppressWarnings("all")
    public ModelAndView caseAcceptDetails(HttpServletRequest request, String caseId, String consignmentId) {
        ModelAndView modelAndView = initDictList();

        String entrustStatus = null;
        try {
            //获取当前登录人信息
            Subject subject = SecurityUtils.getSubject();
            LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
            if (operateUser == null) {
                modelAndView.setViewName("redirect:/login.html?timeoutFlag=1");
                return modelAndView;
            }

            //根据委托id查询委托信息
            LimsConsignmentInfo consignatioInfo = limsConsignmentInfoService.selectByConsignmentId(consignmentId);
            //根据案件id查询案件信息
            LimsCaseInfo limsCaseInfo = limsCaseInfoService.selectByCaseId(caseId);
            //委托状态
            if (limsCaseInfo != null) {
                entrustStatus = limsCaseInfo.getEntrustStatus();
            }
            if (consignatioInfo.getAcceptDatetime() != null){
                modelAndView.addObject("caseStatus", "1");
            }
            /*if (limsCaseInfo.getHasAppendFlag().equals("0")){
                if (limsCaseInfo.getCaseStatus().equals("02") || limsCaseInfo.getCaseStatus().equals("03")){
                    modelAndView.addObject("caseStatus", "1");
                }
            }else{
                if (consignatioInfo.getAcceptDatetime() != null){
                    modelAndView.addObject("caseStatus", "1");
                }
            }*/

            //显示进度条
            //受理时间
            if(consignatioInfo != null){
                Date acceptDatetime = consignatioInfo.getAcceptDatetime();
                modelAndView.addObject("acceptDatetime", acceptDatetime);
            }

            //分局
            List<OrgInfo> orgInfoList = new ArrayList<>();
            List<AmPersonalInfo> personalInfoList = new ArrayList<>();
            if (StringUtils.isBlank(operateUser.getOrgId()) || operateUser.getOrgId().contains("110023") || operateUser.getOrgId().contains("11023") || operateUser.getOrgId().contains("110115")) {
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
            //判断是不是在逃人员案件受理
            if (Constants.ENTRUST_STATUS_2.equals(entrustStatus)) {
                //区分出在逃人员和在逃人员亲属
                if (ListUtils.isNotNullAndEmptyList(limsPersonInfoList)) {
                    List<LimsPersonInfo> fugitivesInfoList = new ArrayList<>();
                    List<LimsPersonInfo> fugitivesInfoRelationList = new ArrayList<>();
                    for (LimsPersonInfo lpi : limsPersonInfoList) {
                        if (Constants.PERSON_TYPE_07.equals(lpi.getPersonType())) {
                            fugitivesInfoList.add(lpi);
                        }else if (Constants.PERSON_TYPE_08.equals(lpi.getPersonType())) {
                            //判断目标样本id是否存在
                            if (StringUtils.isNotBlank(lpi.getTargetPersonId())) {
                                FugitivesInfo fugitivesInfo = fugitivesInfoService.selectByPrimaryKey(lpi.getTargetPersonId());
                                //判断在逃人员信息是否存在
                                if (fugitivesInfo != null) {
                                    lpi.setFugitivesName(fugitivesInfo.getPersonName());
                                    lpi.setFugitivesCard(fugitivesInfo.getPersonCard());
                                    lpi.setFugitiveNo(fugitivesInfo.getFugitiveNo());
                                }
                            }
                            fugitivesInfoRelationList.add(lpi);
                        }
                    }
                    modelAndView.addObject("fugitivesInfoList",fugitivesInfoList);
                    modelAndView.addObject("fugitivesInfoRelationList",fugitivesInfoRelationList);
                }
            }

            //根据案件id查询样本信息
            LimsSampleInfoDna sampleInfoDna = new LimsSampleInfoDna();
            sampleInfoDna.setCaseId(caseId);
            sampleInfoDna.setConsignmentId(consignmentId);
            LinkedList<LimsSampleInfoDna> sampleInfoDnaList = limsSampleInfoDnaService.selectByCaseIdAndRy(sampleInfoDna);
            if (null != limsPersonInfoList && limsPersonInfoList.size() > 0 && null != sampleInfoDnaList && sampleInfoDnaList.size() > 0) {
                for (LimsPersonInfo limsPersonInfo1 : limsPersonInfoList) {
                    for (LimsSampleInfoDna sampleInfoDna2 : sampleInfoDnaList) {
                        //如果人员的ID 与样本的LinkID相同时,根据样本的linkID查询人员信息，并获取人员类型
                        if (limsPersonInfo1.getPersonId().equals(sampleInfoDna2.getLinkId())) {
                            if (sampleInfoDna2!=null){
                                LimsPersonInfo  person = limsPersonInfoService.selectPersonInfoById(sampleInfoDna2.getLinkId());
                                if (person!=null&&StringUtils.isNotBlank(person.getPersonType())){
                                    if (person.getPersonType().equals(Constants.PERSON_TYPE_03)){
                                        sampleInfoDna2.setCoreVictimStats(Constants.CORE_VICTIM_STATS_TEST);
                                    }else{
                                        sampleInfoDna2.setCoreVictimStats(Constants.CORE_VICTIM_STATS_FLASE);
                                    }
                                }
                            }
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
        //判断是不是在逃人员案件受理
        if (Constants.ENTRUST_STATUS_2.equals(entrustStatus)) {
            modelAndView.setViewName("accept/caseFugitivesDetails");
        }else {
            modelAndView.setViewName("accept/caseDetails");
        }
        return modelAndView;

    }


    /**
     * 在逃人员查询
     * @param request
     * @param searchFugitives
     * @return
     */
    @RequestMapping("/fugitivesSearch")
    @ResponseBody
    public Map<String, Object> fugitivesSearch(HttpServletRequest request, String searchFugitives) {
        Map<String, Object> result = new HashMap<>();

        try {
            List<FugitivesInfoVo> fugitivesInfoVoList = fugitivesInfoService.selectFugitivesList(searchFugitives.trim());
            result.put("fugitivesInfoVoList", fugitivesInfoVoList);
            result.put("success", true);
        }catch (Exception e) {
            e.getStackTrace();
            logger.error("查询失败！", e);
            result.put("success", false);
        }

        return result;
    }

    @RequestMapping("/caseDetails")
    public ModelAndView caseDetails(HttpServletRequest request, String caseId, String consignmentId) {
        ModelAndView modelAndView = initDictList();

        try {
            //获取当前登录人信息
            Subject subject = SecurityUtils.getSubject();
            LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
            if (operateUser == null) {
                modelAndView.setViewName("redirect:/login.html?timeoutFlag=1");
                return modelAndView;
            }

            //根据委托id查询委托信息
            LimsConsignmentInfo consignatioInfo = limsConsignmentInfoService.selectByConsignmentId(consignmentId);
            //根据案件id查询案件信息
            LimsCaseInfo limsCaseInfo = limsCaseInfoService.selectByCaseId(caseId);
            if (consignatioInfo.getAcceptDatetime() != null){
                modelAndView.addObject("caseStatus", "1");
            }
            /*if (limsCaseInfo.getHasAppendFlag().equals("0")){
                if (limsCaseInfo.getCaseStatus().equals("02") || limsCaseInfo.getCaseStatus().equals("03")){
                    modelAndView.addObject("caseStatus", "1");
                }
            }else{
                if (consignatioInfo.getAcceptDatetime() != null){
                    modelAndView.addObject("caseStatus", "1");
                }
            }*/


            //显示进度条
            //受理时间
            if(consignatioInfo != null){
                Date acceptDatetime = consignatioInfo.getAcceptDatetime();
                modelAndView.addObject("acceptDatetime", acceptDatetime);
            }

            //本案比对
            List<CompareSameResult> compareSameResults = compareSameResultService.selectListByCaseId(caseId);
            if (ListUtils.isNotNullAndEmptyList(compareSameResults)){
                CompareSameResult compareSameResult = compareSameResults.get(0);
                Date createDatetime = compareSameResult.getCreateDatetime();
                modelAndView.addObject("createDatetime", createDatetime);
                modelAndView.addObject("comparison", "1");
            }else {
                List<CompareRelativeResult> compareRelativeResult = compareRelativeResultService.selectListByCaseId(caseId);
                if (ListUtils.isNotNullAndEmptyList(compareRelativeResult)){
                    CompareRelativeResult compareRelative = compareRelativeResult.get(0);
                    Date createDatetime = compareRelative.getCreateDatetime();
                    modelAndView.addObject("createDatetime", createDatetime);
                    modelAndView.addObject("comparison", "1");
                }
            }
            //入库比对
            MatchSameResult sameResult = new MatchSameResult();
            sameResult.setCaseaId(caseId);
            List<MatchSameResult> matchSameResults = matchSameResultService.selectList(sameResult);
            if (ListUtils.isNotNullAndEmptyList(matchSameResults)){
                MatchSameResult matchSameResult = matchSameResults.get(0);
                Date instoredDatetime = matchSameResult.getCreateDatetime();
                modelAndView.addObject("instoredDatetime", instoredDatetime);
                modelAndView.addObject("instored", "1");
            }else {
                sameResult.setCasebId(caseId);
                List<MatchSameResult> matchSameResultList = matchSameResultService.selectList(sameResult);
                if (ListUtils.isNotNullAndEmptyList(matchSameResultList)){
                    MatchSameResult matchSameResult = matchSameResultList.get(0);
                    Date instoredDatetime = matchSameResult.getCreateDatetime();
                    modelAndView.addObject("instoredDatetime", instoredDatetime);
                    modelAndView.addObject("instored", "1");
                }
            }
            MatchRelativeResult relative = new MatchRelativeResult();
            relative.setCaseaId(caseId);
            List<MatchRelativeResult> matchRelativeResults = matchRelativeResultService.selectList(relative);
            if (ListUtils.isNotNullAndEmptyList(matchRelativeResults)){
                MatchRelativeResult relativeResult = matchRelativeResults.get(0);
                Date instoredDatetime = relativeResult.getCreateDatetime();
                modelAndView.addObject("instoredDatetime", instoredDatetime);
                modelAndView.addObject("instored", "1");
            }else {
                relative.setCasebId(caseId);
                List<MatchRelativeResult> matchRelativeResultList = matchRelativeResultService.selectList(relative);
                if (ListUtils.isNotNullAndEmptyList(matchRelativeResultList)){
                    MatchRelativeResult relativeResult = matchRelativeResultList.get(0);
                    Date instoredDatetime = relativeResult.getCreateDatetime();
                    modelAndView.addObject("instoredDatetime", instoredDatetime);
                    modelAndView.addObject("instored", "1");
                }
            }

            //分局
            List<OrgInfo> orgInfoList = new ArrayList<>();
            List<AmPersonalInfo> personalInfoList = new ArrayList<>();
            if (StringUtils.isBlank(operateUser.getOrgId()) || operateUser.getOrgId().contains("110023") || operateUser.getOrgId().contains("11023") || operateUser.getOrgId().contains("110115")) {
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
        modelAndView.setViewName("accept/caseDetails");
        return modelAndView;
    }

    /**
     * 在检案件列表-案件打印
     * @param request
     * @param caseId
     * @param consignmentId
     * @return
     */
    @RequestMapping("/print")
    public ModelAndView print(HttpServletRequest request, String caseId, String consignmentId) {
        ModelAndView view = new ModelAndView();
        List<LimsSampleInfoDna> limsSampleInfoDnaList = limsSampleInfoDnaService.selectsampleListRefuseConsignmentId(consignmentId);
        List<LimsSampleInfoDna> limsPersonInfoDnaList = limsSampleInfoDnaService.selectPersonListRefuseConsignmentId(consignmentId);

        LimsCaseInfo caseInfo = limsCaseInfoService.selectByCaseId(caseId);

        DictItem sampleTypeDictItem = new DictItem();
        sampleTypeDictItem.setDictTypeCode(Constants.SAMPLE_TYPE);
        List<DictItem> sampleTypeList = DictUtil.getDictList(sampleTypeDictItem);

        for (LimsSampleInfoDna limsSampleInfoDna : limsSampleInfoDnaList) {
            for (DictItem sampleType : sampleTypeList) {
                if (sampleType.getDictCode().equals(limsSampleInfoDna.getSampleType())) {
                    limsSampleInfoDna.setSampleTypeName(sampleType.getDictName());
                }
            }
        }

        for (LimsSampleInfoDna limsPersonInfoDna : limsPersonInfoDnaList) {
            for (DictItem sampleType : sampleTypeList) {
                if (sampleType.getDictCode().equals(limsPersonInfoDna.getSampleType())) {
                    limsPersonInfoDna.setSampleTypeName(sampleType.getDictName());
                }
            }
        }


        try {
            view.addObject("caseId", caseId);
            view.addObject("caseInfo", caseInfo);
            view.addObject("consignmentId", consignmentId);
            view.addObject("limsSampleInfoDnaList", limsSampleInfoDnaList);
            view.addObject("limsPersonInfoDnaList", limsPersonInfoDnaList);
            view.addObject("ipAddress", IpAddressUtils.getIpAddr(request));
        } catch (Exception ex) {
            logger.info("查询失败:" + ex);
        }
        view.setViewName("accept/print");
        return view;
    }

    /**
     * 委托确认书下载
     *
     * @param request
     * @param response
     * @param consignmentId
     * @throws ParseException
     */
    @RequestMapping("/acceptDoc")
    public void acceptDoc(HttpServletRequest request, HttpServletResponse response, String consignmentId) throws ParseException {
        downloadFileUtils.generateAndDownload(request, response, consignmentId);
    }


    String writeFilePath = null;

    //List<File> fileList = new ArrayList<File>();
    @RequestMapping(value = "/printAllAceptDoc", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> printAllAceptDoc(HttpServletRequest request, HttpServletResponse response, String consignmentId, String preAceptFlag) throws ParseException {
        Map<String, Object> result = new HashMap<String, Object>();
        List<File> fileList = new ArrayList<File>();
        String acceptFilePath = null;
        String preFilePath = null;
        String circulationFilePath = null;
        String warehouseReceipt = null;
        acceptFilePath = acceptZipDownload(request, response, consignmentId);


        if(null != consignmentId){
            LimsCaseInfo caseInfo = limsCaseInfoService.selectByConsignmentId(consignmentId);
            if(null != caseInfo.getCaseId()){
                warehouseReceipt =  generateWarehouseReceipt(request, response, caseInfo.getCaseId(),consignmentId);
            }
        }

        String fullPrint = "2";
        if (!"1".equals(preAceptFlag)) {//所有检材均未填写预实验结果的情况,是否生成 1是不生成
            preFilePath = preAceptDocZip(request, response, consignmentId,fullPrint);
        }
        String s = this.circulationRecordIsRtain(request, response, consignmentId);
        if ("1".equals(s)) {
            circulationFilePath = circulationRecordZip(request, response, consignmentId);
        }
        if (StringUtils.isNotBlank(acceptFilePath)) {
            fileList.add(new File(acceptFilePath));
        }

        if (StringUtils.isNotBlank(warehouseReceipt)) {
            fileList.add(new File(warehouseReceipt));
        }

        if (StringUtils.isNotBlank(preFilePath)) {
            fileList.add(new File(preFilePath));
        }
        if (StringUtils.isNotBlank(circulationFilePath)) {
            fileList.add(new File(circulationFilePath));
        }

        String zipName = "myfile" + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".zip";
        FileOutputStream fos = null;
        ZipOutputStream zos = null;
        writeFilePath = bendiFilePath;
        try {
            writeFilePath = makePathFile(writeFilePath) + zipName;
            File zipFile = new File(writeFilePath);
            if (!zipFile.exists()) {
                zipFile.createNewFile();
            }
            //创建文件输出流
            fos = new FileOutputStream(zipFile);
            /**打包的方法我们会用到ZipOutputStream这样一个输出流,
             * 所以这里我们把输出流转换一下*/
            zos = new ZipOutputStream(fos);
            /**这个方法接受的就是一个所要打包文件的集合，
             * 还有一个ZipOutputStream*/
            zipFiles(fileList, zos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭流
            try {
                if (null != zos) {
                    zos.close();
                }
                if (null != fos) {
                    fos.close();
                }

                for (int i = 0; i < fileList.size(); i++) {
                    File file = (File) fileList.get(i);
                    if (file.exists()) {
                        file.delete();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        result.put("success", writeFilePath);

        return result;
    }

    /**
     * 把接受的全部文件打成压缩包
     */
    public static void zipFiles(List files, ZipOutputStream outputStream) {
        int size = files.size();
        for (int i = 0; i < size; i++) {
            File file = (File) files.get(i);
            zipFile(file, outputStream);
        }
    }

    /**
     * 根据输入的文件与输出流对文件进行打包
     */
    public static void zipFile(File inputFile, ZipOutputStream ouputStream) {
        try {
            if (inputFile.exists()) {
                /**如果是目录的话这里是不采取操作的，
                 * 至于目录的打包正在研究中*/
                if (inputFile.isFile()) {
                    FileInputStream IN = new FileInputStream(inputFile);
                    BufferedInputStream bins = new BufferedInputStream(IN, 512);
                    //org.apache.tools.zip.ZipEntry
                    ZipEntry entry = new ZipEntry(inputFile.getName());
                    ouputStream.putNextEntry(entry);
                    // 向压缩文件中输出数据
                    int nNumber;
                    byte[] buffer = new byte[512];
                    while ((nNumber = bins.read(buffer)) != -1) {
                        ouputStream.write(buffer, 0, nNumber);
                    }
                    // 关闭创建的流对象
                    bins.close();
                    IN.close();
                } else {
                    try {
                        File[] files = inputFile.listFiles();
                        for (int i = 0; i < files.length; i++) {
                            zipFile(files[i], ouputStream);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/downloadFile")
    public void downloadFile(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        try {
            File file = new File(String.valueOf(writeFilePath));
            if (file.exists()) {
                InputStream ins = new FileInputStream(String.valueOf(writeFilePath));
                // 放到缓冲流里面
                BufferedInputStream bins = new BufferedInputStream(ins);
                // 获取文件输出IO流
                OutputStream outs = response.getOutputStream();
                BufferedOutputStream bouts = new BufferedOutputStream(outs);
                // 设置response内容的类型
                response.setContentType("application/x-download");
                // 设置头部信息
                response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(file.getName(), "UTF-8"));
                int bytesRead = 0;
                byte[] buffer = new byte[8192];
                // 开始向网络传输文件流
                while ((bytesRead = bins.read(buffer, 0, 8192)) != -1) {
                    bouts.write(buffer, 0, bytesRead);
                }
                // 这里一定要调用flush()方法
                bouts.flush();
                ins.close();
                bins.close();
                outs.close();
                bouts.close();
            }
        } catch (IOException e) {
            logger.error("文件下载出错", e);
        }

    }

    public String acceptZipDownload(HttpServletRequest request, HttpServletResponse response,
                                    String consignmentId) throws ParseException {
        String filePathName = null;
        LimsCaseInfo caseInfo = limsCaseInfoService.selectByConsignmentId(consignmentId);
        LimsConsignmentInfo consignment = limsConsignmentInfoService.selectByConsignmentId(consignmentId);
        List<LimsSampleInfoDna> limsSampleInfoDnaList = limsSampleInfoDnaService.selectListByConsignmentId(consignmentId);
        AmPersonalInfo amPersonalInfo1 = amPersonalInfoService.selectByPersonalId(consignment.getDelegator1Id());
        AmPersonalInfo amPersonalInfo2 = amPersonalInfoService.selectByPersonalId(consignment.getDelegator2Id());
        AmPersonalInfo acceptPersonInfo = new AmPersonalInfo();
        if (consignment.getAcceptorId() != null) {
            acceptPersonInfo = amPersonalInfoService.selectByPersonalId(consignment.getAcceptorId());
        }
        DictItem posItionDictItem = new DictItem();
        posItionDictItem.setDictTypeCode(Constants.POSITION_LIST);
        List<DictItem> posItionList = DictUtil.getDictList(posItionDictItem);
        if (posItionList != null && !posItionList.isEmpty()) {
            for (DictItem posItion : posItionList) {
                if (null != amPersonalInfo1) {
                    if (posItion.getDictCode().equals(amPersonalInfo1.getPosition())) {
                        consignment.setDelegator1Position(posItion.getDictName());
                    }
                }
                if (null != amPersonalInfo2) {
                    if (posItion.getDictCode().equals(amPersonalInfo2.getPosition())) {
                        consignment.setDelegator2Position(posItion.getDictName());
                    }
                }

            }

        }

        DictItem dictItem = new DictItem();
        dictItem.setDictTypeCode(Constants.CASE_PROPERTY);
        List<DictItem> casePropertyList = DictUtil.getDictList(dictItem);

        for (DictItem caseProperty : casePropertyList) {
            if (caseProperty.getDictCode().equals(caseInfo.getCaseProperty())) {
                caseInfo.setCaseProperty(caseProperty.getDictName());
            }
        }

        DictItem identificationDictItem = new DictItem();
        identificationDictItem.setDictTypeCode(Constants.IDENTIFICATION_TYPE);
        List<DictItem> identificationList = DictUtil.getDictList(identificationDictItem);

//        for (DictItem identification : identificationList) {
//            if (identification.getDictCode().equals(consignment.getIdentifyRequirement())) {
//                consignment.setIdentifyRequirement(identification.getDictName());
//            }
//        }

        if (Constants.IDENTIFYREQUIREMENT_01.equals(consignment.getIdentifyRequirement())) {
            consignment.setIdentifyRequirement("同一鉴定");
        } else if (Constants.IDENTIFYREQUIREMENT_02.equals(consignment.getIdentifyRequirement())) {
            consignment.setIdentifyRequirement("亲缘鉴定");
        } else if (Constants.IDENTIFYREQUIREMENT_03.equals(consignment.getIdentifyRequirement())) {
            consignment.setIdentifyRequirement("同一鉴定，亲缘鉴定");
        }

        Map<String, Object> params = new HashMap<String, Object>();
        String caseNo = caseInfo.getCaseNo();
        if (caseNo == null || caseNo == "") {
            params.put("year", "");
            params.put("no", "");
        } else {
            if (caseNo.contains("-")) {
                int length = caseNo.split("-").length;
                if (length > 2) {
                    params.put("year", (caseNo.split("-")[1]));
                    params.put("no", (caseNo.split("-")[2]));
                } else {
                    params.put("year", (caseNo.split("-")[0]));
                    params.put("no", (caseNo.split("-")[1]));
                }
            } else {
                params.put("year", caseNo.substring(0, 4));
                params.put("no", caseNo.substring(4, caseNo.length()));
            }
        }
        if (caseInfo.getCaseNo() != null) {
            params.put("caseNo", StringUtils.isBlank(caseInfo.getCaseNo()) ? "" : caseInfo.getCaseNo());
        }
        params.put("consignmentNo", StringUtils.isBlank(consignment.getConsignmentNo()) ? "" : consignment.getConsignmentNo());
        params.put("caseXkNo", StringUtils.isBlank(caseInfo.getCaseXkNo()) ? "" : caseInfo.getCaseXkNo());
        params.put("acceptor", StringUtils.isBlank(consignment.getRefusePerson()) ? "" : consignment.getRefusePerson());
        //委托单位
        params.put("delegateOrgName", StringUtils.isBlank(consignment.getDelegateOrgName()) ? "" : consignment.getDelegateOrgName());
        //受理时间
        if (consignment.getAcceptDatetime() != null) {
            params.put("acceptDate", DateUtils.dateToString(consignment.getAcceptDatetime(), "yyyy年MM月dd日"));
            params.put("acceptDate2", DateUtils.dateToString(consignment.getAcceptDatetime(), "yyyy年MM月dd日 HH:mm"));
        }
        //受理人
        if (acceptPersonInfo != null) {
            params.put("acceptPerson", StringUtils.isBlank(acceptPersonInfo.getFullName()) ? "" : acceptPersonInfo.getFullName());
            params.put("acceptPersonPhone", StringUtils.isBlank(acceptPersonInfo.getPhoneNumber()) ? "" : acceptPersonInfo.getPhoneNumber());
        }
        //鉴定要求
        params.put("identifyRequirement", StringUtils.isBlank(consignment.getIdentifyRequirement()) ? "" : consignment.getIdentifyRequirement());
        //委托人1
        params.put("delegator1name", StringUtils.isBlank(consignment.getDelegator1Name()) ? "" : consignment.getDelegator1Name());
        params.put("delegator1Position", StringUtils.isBlank(consignment.getDelegator1Position()) ? "" : consignment.getDelegator1Position());
        params.put("delegator1Cno", StringUtils.isBlank(consignment.getDelegator1PaperworkNo()) ? "" : consignment.getDelegator1PaperworkNo());
        params.put("delegator1Phone", StringUtils.isBlank(consignment.getDelegator1PhoneNumber()) ? "" : consignment.getDelegator1PhoneNumber());
        //委托人2
        params.put("delegator2name", StringUtils.isBlank(consignment.getDelegator2Name()) ? "" : consignment.getDelegator2Name());
        params.put("delegator2Position", StringUtils.isBlank(consignment.getDelegator2Position()) ? "" : consignment.getDelegator2Position());
        params.put("delegator2Cno", StringUtils.isBlank(consignment.getDelegator2PaperworkNo()) ? "" : consignment.getDelegator2PaperworkNo());
        params.put("delegator2Phone", StringUtils.isBlank(consignment.getDelegator2PhoneNumber()) ? "" : consignment.getDelegator2PhoneNumber());
        //案件名称
        params.put("caseName", StringUtils.isBlank(caseInfo.getCaseName()) ? "" : caseInfo.getCaseName());
        //案件号（K号）
        params.put("caseNo", StringUtils.isBlank(caseInfo.getCaseNo()) ? "" : caseInfo.getCaseNo());
        //简要案情
        params.put("caseBrief", StringUtils.isBlank(caseInfo.getCaseBrief()) ? "" : caseInfo.getCaseBrief());
        //案件性质
        params.put("caseProperty", StringUtils.isBlank(caseInfo.getCaseProperty()) ? "" : caseInfo.getCaseProperty());

        params.put("xkANo", StringUtils.isBlank(caseInfo.getXkANo()) ? "" : caseInfo.getXkANo());

        //送检样本
        for (LimsSampleInfoDna sampleInfo : limsSampleInfoDnaList) {
            sampleInfo.setSampleNo(StringUtils.isBlank(sampleInfo.getSampleNo()) ? "" : sampleInfo.getSampleNo());
            sampleInfo.setSampleName(StringUtils.isBlank(sampleInfo.getSampleName()) ? "" : sampleInfo.getSampleName());
            sampleInfo.setSampleDesc(StringUtils.isBlank(sampleInfo.getSampleDesc()) ? "" : sampleInfo.getSampleDesc());

            DictItem dictsamplePackling = new DictItem();
            dictsamplePackling.setDictTypeCode(Constants.PACK_METHOD);
            List<DictItem> samplePacklingList = DictUtil.getDictList(dictsamplePackling);
            for (DictItem samplePackling : samplePacklingList) {
                if (sampleInfo.getSamplePacking().equals(samplePackling.getDictCode())) {
                    sampleInfo.setSamplePackingName(samplePackling.getDictName());
                }
            }
            sampleInfo.setSamplePackingName(StringUtils.isBlank(sampleInfo.getSamplePackingName()) ? "" : sampleInfo.getSamplePackingName());
            DictItem dictsampleType = new DictItem();
            dictsampleType.setDictTypeCode(Constants.SAMPLE_TYPE);
            List<DictItem> sampleTypeList = DictUtil.getDictList(dictsampleType);
            for (DictItem sampleType : sampleTypeList) {
                if (sampleInfo.getSampleType().equals(sampleType.getDictCode())) {
                    sampleInfo.setSampleTypeName(sampleType.getDictName());
                }
            }
            sampleInfo.setSampleTypeName(StringUtils.isBlank(sampleInfo.getSampleTypeName()) ? "" : sampleInfo.getSampleTypeName());
            if (sampleInfo.getExtractDatetime() != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String extracTime = sdf.format(sampleInfo.getExtractDatetime());
                sampleInfo.setExtractTime(extracTime);
            }
            sampleInfo.setSampleStatus("已受理");
            if (sampleInfo.getSampleRemark() != null) {
                sampleInfo.setSampleRemark(StringUtils.isBlank(sampleInfo.getSampleRemark()) ? "" : sampleInfo.getSampleRemark());
            }

        }

        if (limsSampleInfoDnaList.size() < 11) {
            int num = 11 - limsSampleInfoDnaList.size();
            LimsSampleInfoDna tmpSample = null;
            for (int i = 0; i < num; i++) {
                tmpSample = new LimsSampleInfoDna();
                tmpSample.setSampleNo("");
                tmpSample.setSampleName("");
                tmpSample.setSampleDesc("");
                tmpSample.setSampleTypeName("");
                tmpSample.setSamplePackingName("");
                tmpSample.setExtractTime("");
                tmpSample.setSampleStatus("");
                tmpSample.setSampleRemark("");
                limsSampleInfoDnaList.add(tmpSample);
            }
        }

        params.put("sampleList", limsSampleInfoDnaList);


        params.put("orgCode", StringUtils.isBlank(consignment.getAcceptOrgId()) ? "" : consignment.getAcceptOrgId());
        params.put("barcode1", BarcodeUtil.generateFile(StringUtils.isBlank(caseInfo.getCaseNo()) ? "" : StringUtils.substringBefore(caseInfo.getCaseNo(), "-"), "barcode1.png"));

        params.put("barcode2", BarcodeUtil.generateFile(StringUtils.isBlank(caseInfo.getCaseNo()) ? "" : StringUtils.substringAfter(caseInfo.getCaseNo(), "-"), "barcode2.png"));


        if (StringUtils.isNotEmpty(consignment.getAutographPicture())) {
            params.put("image", consignment.getAutographPicture().replace("data:image/png;base64,", ""));
        } else {
            params.put("image", "");
            params.put("nullImage", "____________________________");
        }
        params.put("currentDay", DateUtils.dateToString(consignment.getAcceptDatetime(), "yyyy-MM-dd"));
        params.put("takePerson", consignment.getTakePerson());
        String orgQualification = orgInfoService.selectLabNameById(consignment.getAcceptOrgId());
        params.put("orgQualification", StringUtils.isBlank(orgQualification) ? "" : orgQualification);
        params.put("orgQNo", Constants.selectOrgQNo(consignment.getAcceptOrgId()));
        params.put("appendFlag", StringUtils.isBlank(consignment.getAppendFlag()) ? "" : consignment.getAppendFlag());
        if (caseInfo.getFirstChecker() != null) {
            AmPersonalInfo acceptPersonInfo12 = amPersonalInfoService.selectByPersonalId(caseInfo.getFirstChecker());
            params.put("firstChecker", StringUtils.isBlank(acceptPersonInfo12.getFullName()) ? "" : acceptPersonInfo12.getFullName());

        }
        Writer out = null;
        try {
            Configuration cfg = new Configuration();
            cfg.setDefaultEncoding("UTF-8");
            cfg.setClassForTemplateLoading(this.getClass(), "/templates");
            //获取模板
            Template tmp = cfg.getTemplate("acceptInfo.ftl", "UTF-8");

            //String filename = "-鉴定事项确认书" + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".doc";
            String filename = "-confirmationIdentification" + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".doc";

            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(getGeneratePath(caseInfo, filename)), "UTF-8"));
            tmp.process(params, out);

            filePathName = getGeneratePath(caseInfo, filename);
        } catch (Exception ex) {
            logger.error("", ex);
        } finally {
            if (out != null) {
                try {
                    //关闭输出文件流  上传文件到FTP
                    PrintFtpFileUtils.uploadFtpFile(ftpIp, ftpPort, ftpUser, ftpPassword, ftpFilePath, filePathName, request, page);
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return filePathName;
    }


    public String preAceptDocZip(HttpServletRequest request, HttpServletResponse response,
                                 String consignmentId,String fullPrint) throws ParseException {
        String filePathName = null;
        LimsCaseInfo caseInfo = limsCaseInfoService.selectByConsignmentId(consignmentId);
        LimsConsignmentInfo consignment = limsConsignmentInfoService.selectByConsignmentId(consignmentId);
        List<LimsSampleInfoDna> limsSampleInfoDnaList = limsSampleInfoDnaService.selectListByConsignmentId(consignmentId);
        String path = "";
        AmPersonalInfo acceptPersonInfo = new AmPersonalInfo();
        if (consignment.getAcceptorId() != null) {
            acceptPersonInfo = amPersonalInfoService.selectByPersonalId(consignment.getAcceptorId());
        }

        Map<String, Object> params = new HashMap<String, Object>();
        String caseNo = caseInfo.getCaseNo();
        if (caseNo == null || caseNo == "") {
            params.put("year", "");
            params.put("no", "");
        } else {
            if (caseNo.contains("-")) {
                int length = caseNo.split("-").length;
                if (length > 2) {
                    params.put("year", (caseNo.split("-")[1]));
                    params.put("no", (caseNo.split("-")[2]));
                } else {
                    params.put("year", (caseNo.split("-")[0]));
                    params.put("no", (caseNo.split("-")[1]));
                }
            } else {
                params.put("year", caseNo.substring(0, 4));
                params.put("no", caseNo.substring(4, caseNo.length()));
            }
        }
        if (acceptPersonInfo != null) {
            params.put("acceptName", StringUtils.isBlank(acceptPersonInfo.getFullName()) ? "" : acceptPersonInfo.getFullName());
        }
        if (consignment.getAcceptDatetime() != null) {
            params.put("acceptDate", DateUtils.dateToString(consignment.getAcceptDatetime(), "yyyy-MM-dd"));
        }

        //页眉
        if (consignment != null) {
            if (StringUtils.isNotBlank(consignment.getAcceptOrgId())) {
                params.put("orgQNo", Constants.selectOrgQNo(consignment.getAcceptOrgId()));
                String orgQualification = orgInfoService.selectLabNameById(consignment.getAcceptOrgId());
                params.put("orgQualification", StringUtils.isBlank(orgQualification) ? "" : orgQualification);
            }
        }
        //送检样本
        for (LimsSampleInfoDna sampleInfo : limsSampleInfoDnaList) {
            sampleInfo.setSampleNo(StringUtils.isBlank(sampleInfo.getSampleNo()) ? "" : sampleInfo.getSampleNo());
            sampleInfo.setSampleName(StringUtils.isBlank(sampleInfo.getSampleName()) ? "" : sampleInfo.getSampleName());
            sampleInfo.setPreMethod1Result(StringUtils.isBlank(sampleInfo.getPreMethod1Result()) ? "" : sampleInfo.getPreMethod1Result());
            sampleInfo.setPreMethod2Result(StringUtils.isBlank(sampleInfo.getPreMethod2Result()) ? "" : sampleInfo.getPreMethod2Result());
            sampleInfo.setPreMethod3Result(StringUtils.isBlank(sampleInfo.getPreMethod3Result()) ? "" : sampleInfo.getPreMethod3Result());

            DictItem dictsampleType = new DictItem();
            dictsampleType.setDictTypeCode(Constants.SAMPLE_TYPE);
            List<DictItem> sampleTypeList = DictUtil.getDictList(dictsampleType);
            for (DictItem sampleType : sampleTypeList) {
                if (sampleInfo.getSampleType().equals(sampleType.getDictCode())) {
                    sampleInfo.setSampleTypeName(sampleType.getDictName());
                }
            }
            if (sampleInfo.getSampleRemark() != null) {
                sampleInfo.setSampleRemark(StringUtils.isBlank(sampleInfo.getSampleRemark()) ? "" : sampleInfo.getSampleRemark());
            }

        }

        params.put("sampleList", limsSampleInfoDnaList);

        Writer out = null;
        try {
            Configuration cfg = new Configuration();
            cfg.setDefaultEncoding("UTF-8");
            cfg.setClassForTemplateLoading(this.getClass(), "/templates");
            //获取模板
            Template tmp = cfg.getTemplate("preAccept.ftl", "UTF-8");

            // String filename = "-预实验检验记录表" + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".doc";
            String filename = "-preExperimentalRecord" + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".doc";

            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(getGeneratePath(caseInfo, filename)), "UTF-8"));
            tmp.process(params, out);

            filePathName = getGeneratePath(caseInfo, filename);
        } catch (Exception ex) {
            logger.error("", ex);
        } finally {
            if (out != null) {
                try {
                    //上传文件到ftp服务器

                    if("2".equals(fullPrint)){
                        path = CasePrintFtpFileUtils.uploadFtpFileNew(ftpIp, ftpPort, ftpUser, ftpPassword, ftpCaseFilePath, filePathName, request, page,fullPrint);
                        System.out.println(path.toString());
                    }else{
                        CasePrintFtpFileUtils.uploadFtpFileNew(ftpIp, ftpPort, ftpUser, ftpPassword, ftpFilePath, filePathName, request, page,fullPrint);
                    }
//                    PrintFtpFileUtils.uploadFtpFile(ftpIp, ftpPort, ftpUser, ftpPassword, ftpFilePath, filePathName, request, page);
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return path;
    }

    public String circulationRecordZip(HttpServletRequest request, HttpServletResponse response,
                                       String consignmentId) throws ParseException {
        String filePathName = null;
        LimsCaseInfo caseInfo = limsCaseInfoService.selectByConsignmentId(consignmentId);
        LimsConsignmentInfo consignment = limsConsignmentInfoService.selectByConsignmentId(consignmentId);
        List<LimsSampleInfoDna> limsSampleInfoDnaList = limsSampleInfoDnaService.selectSampleListByConsignmentId(consignmentId);


        DictItem identificationDictItem = new DictItem();
        identificationDictItem.setDictTypeCode(Constants.IDENTIFICATION_TYPE);
        List<DictItem> identificationList = DictUtil.getDictList(identificationDictItem);

        for (DictItem identification : identificationList) {
            if (identification.getDictCode().equals(consignment.getIdentifyRequirement())) {
                consignment.setIdentifyRequirement(identification.getDictName());
            }
        }

        Map<String, Object> params = new HashMap<String, Object>();
        String caseNo = caseInfo.getCaseNo();
        if (caseNo == null || caseNo == "") {
            params.put("year", "");
            params.put("no", "");
        } else {
            if (caseNo.contains("-")) {
                int length = caseNo.split("-").length;
                if (length > 2) {
                    params.put("year", (caseNo.split("-")[1]));
                    params.put("no", (caseNo.split("-")[2]));
                } else {
                    params.put("year", (caseNo.split("-")[0]));
                    params.put("no", (caseNo.split("-")[1]));
                }
            } else {
                params.put("year", caseNo.substring(0, 4));
                params.put("no", caseNo.substring(4, caseNo.length()));
            }
        }
        if (caseInfo.getCaseNo() != null) {
            params.put("caseNo", StringUtils.isBlank(caseInfo.getCaseNo()) ? "" : caseInfo.getCaseNo());
        }
        params.put("consignmentNo", StringUtils.isBlank(consignment.getConsignmentNo()) ? "" : consignment.getConsignmentNo());
        //页眉
        if (consignment != null) {
            if (StringUtils.isNotBlank(consignment.getAcceptOrgId())) {
                params.put("orgQNo", Constants.selectOrgQNo(consignment.getAcceptOrgId()));
                String orgQualification = orgInfoService.selectLabNameById(consignment.getAcceptOrgId());
                params.put("orgQualification", StringUtils.isBlank(orgQualification) ? "" : orgQualification);
            }
        }

        //送检样本
        for (LimsSampleInfoDna sampleInfo : limsSampleInfoDnaList) {
            sampleInfo.setSampleNo(StringUtils.isBlank(sampleInfo.getSampleNo()) ? "" : sampleInfo.getSampleNo());
            sampleInfo.setSampleName(StringUtils.isBlank(sampleInfo.getSampleName()) ? "" : sampleInfo.getSampleName());
            sampleInfo.setSampleDesc(StringUtils.isBlank(sampleInfo.getSampleDesc()) ? "" : sampleInfo.getSampleDesc());

            DictItem dictsamplePackling = new DictItem();
            dictsamplePackling.setDictTypeCode(Constants.PACK_METHOD);
            List<DictItem> samplePacklingList = DictUtil.getDictList(dictsamplePackling);
            for (DictItem samplePackling : samplePacklingList) {
                if (sampleInfo.getSamplePacking().equals(samplePackling.getDictCode())) {
                    sampleInfo.setSamplePackingName(samplePackling.getDictName());
                }
            }
            sampleInfo.setSamplePackingName(StringUtils.isBlank(sampleInfo.getSamplePackingName()) ? "" : sampleInfo.getSamplePackingName());
            DictItem dictsampleType = new DictItem();
            dictsampleType.setDictTypeCode(Constants.SAMPLE_TYPE);
            List<DictItem> sampleTypeList = DictUtil.getDictList(dictsampleType);
            for (DictItem sampleType : sampleTypeList) {
                if (sampleInfo.getSampleType().equals(sampleType.getDictCode())) {
                    sampleInfo.setSampleTypeName(sampleType.getDictName());
                }
            }

            if (sampleInfo.getExtractDatetime() != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String extracTime = sdf.format(sampleInfo.getExtractDatetime());
                sampleInfo.setExtractTime(extracTime);
            }

            sampleInfo.setSampleStatus("已受理");
            if (sampleInfo.getSampleRemark() != null) {
                sampleInfo.setSampleRemark(StringUtils.isBlank(sampleInfo.getSampleRemark()) ? "" : sampleInfo.getSampleRemark());
            }
        }

        params.put("sampleList", limsSampleInfoDnaList);

        params.put("currentDay", DateUtils.dateToString(new Date(), "yyyy-MM-dd"));

        if (StringUtils.isNotEmpty(consignment.getAutographPicture())) {
            params.put("image", consignment.getAutographPicture().replace("data:image/png;base64,", ""));
        }

        Writer out = null;
        try {
            Configuration cfg = new Configuration();
            cfg.setDefaultEncoding("UTF-8");
            cfg.setClassForTemplateLoading(this.getClass(), "/templates");
            //获取模板
            Template tmp = cfg.getTemplate("circulationRecord.ftl", "UTF-8");

           /* String filename = "-样本检材流转表" + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".doc";*/
            String filename = "-samplematerialRecord" + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".doc";

            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(getGeneratePath(caseInfo, filename)), "UTF-8"));
            tmp.process(params, out);

            filePathName = getGeneratePath(caseInfo, filename);
        } catch (Exception ex) {
            logger.error("", ex);
        } finally {
            if (out != null) {
                try {
                    //上传文件到ftp服务器
                    PrintFtpFileUtils.uploadFtpFile(ftpIp, ftpPort, ftpUser, ftpPassword, ftpFilePath, filePathName, request, page);
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return filePathName;
    }

    /**
     * 入库单
     * @param request
     * @param response
     * @param caseId
     */
    public String generateWarehouseReceipt(HttpServletRequest request, HttpServletResponse response, String caseId,String consignmentId) {
        Map<String, Object> params = new HashMap<>();
        String filePathName = null;
        LimsCaseInfo caseInfo = limsCaseInfoService.selectByCaseId(caseId);
        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        String userOrgId = operateUser.getOrgId();
        //将当前用户org_id设置进query
        if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
            userOrgId = "110230000000";
        }

        //页眉
        ExperimentalParameter exp = new ExperimentalParameter();
        exp.setOrgId(userOrgId);
        exp.setExperimentalType("IN_STORAGE");//常规提取
        List<ExperimentalParameter> experimentalParameters = experimentalParameterService.selectList(exp);
        if (ListUtils.isNotNullAndEmptyList(experimentalParameters)) {
            ExperimentalParameter experimentalParameter = experimentalParameters.get(0);
            String orgNameSp = experimentalParameter.getParameterName();
            params.put("orgNameSp", StringUtils.isBlank(orgNameSp) ? "" : orgNameSp);
        }

        //查询当前人员信息
        AmPersonalInfo amPersonalInfo = amPersonalInfoService.queryAmPersonalInfo(operateUser.getPersonalId());

        params.put("entryName", amPersonalInfo.getFullName());

        LimsCaseInfo limsCaseInfo = limsCaseInfoService.selectByCaseId(caseId);
        params.put("limsCaseInfo", limsCaseInfo);

        Date instoreDate = new Date();

        List<LimsConsignmentInfo> limsConsignmentInfoList = limsConsignmentInfoService.selectByCaseId(caseId);
        if (ListUtils.isNotNullAndEmptyList(limsConsignmentInfoList)) {
            LimsConsignmentInfo consignmentInfo = limsConsignmentInfoList.get(0);
            if (consignmentInfo != null && consignmentInfo.getAcceptDatetime() != null) {
                instoreDate = consignmentInfo.getAcceptDatetime();
            }
        }

        List<LimsSampleInfoDna> limsSampleInfoDnaList = limsSampleInfoDnaService.selectListByCaseIdInstored(caseId);
        List<Map<String, Object>> newSampleInfoList = new ArrayList<>();
        if (ListUtils.isNotNullAndEmptyList(limsSampleInfoDnaList)) {
            LimsSampleInfoDna sampleInfoDna = limsSampleInfoDnaList.get(0);
            if (sampleInfoDna != null && sampleInfoDna.getInstoredDatetime() != null) {
                instoreDate = sampleInfoDna.getInstoredDatetime();
            }

            List<LimsSampleInfoDna> newSampleList = new ArrayList<>();
            //现场物证
            newSampleList = getListByInstoredType(limsSampleInfoDnaList, Constants.INSTORED_TYPE_01);
            if (ListUtils.isNotNullAndEmptyList(newSampleList)) {
                Map<String, Object> resultMap = new LinkedHashMap<>();
                resultMap.put("instoredTypeName", "现场物证");
                resultMap.put("newSampleList", newSampleList);
                newSampleInfoList.add(resultMap);
            }
            //混合物证
            newSampleList = getListByInstoredType(limsSampleInfoDnaList, Constants.INSTORED_TYPE_02);
            if (ListUtils.isNotNullAndEmptyList(newSampleList)) {
                Map<String, Object> resultMap = new LinkedHashMap<>();
                resultMap.put("instoredTypeName", "混合物证");
                resultMap.put("newSampleList", newSampleList);
                newSampleInfoList.add(resultMap);
            }
            //违法犯罪人员
            newSampleList = getListByInstoredType(limsSampleInfoDnaList, Constants.INSTORED_TYPE_03);
            if (ListUtils.isNotNullAndEmptyList(newSampleList)) {
                Map<String, Object> resultMap = new LinkedHashMap<>();
                resultMap.put("instoredTypeName", "违法犯罪人员");
                resultMap.put("newSampleList", newSampleList);
                newSampleInfoList.add(resultMap);
            }
            //嫌疑人
            newSampleList = getListByInstoredType(limsSampleInfoDnaList, Constants.INSTORED_TYPE_04);
            if (ListUtils.isNotNullAndEmptyList(newSampleList)) {
                Map<String, Object> resultMap = new LinkedHashMap<>();
                resultMap.put("instoredTypeName", "嫌疑人");
                resultMap.put("newSampleList", newSampleList);
                newSampleInfoList.add(resultMap);
            }
            //受害人
            newSampleList = getListByInstoredType(limsSampleInfoDnaList, Constants.INSTORED_TYPE_05);
            if (ListUtils.isNotNullAndEmptyList(newSampleList)) {
                Map<String, Object> resultMap = new LinkedHashMap<>();
                resultMap.put("instoredTypeName", "受害人");
                resultMap.put("newSampleList", newSampleList);
                newSampleInfoList.add(resultMap);
            }
            //失踪人口
            newSampleList = getListByInstoredType(limsSampleInfoDnaList, Constants.INSTORED_TYPE_06);
            if (ListUtils.isNotNullAndEmptyList(newSampleList)) {
                Map<String, Object> resultMap = new LinkedHashMap<>();
                resultMap.put("instoredTypeName", "失踪人口");
                resultMap.put("newSampleList", newSampleList);
                newSampleInfoList.add(resultMap);
            }
            //无名尸
            newSampleList = getListByInstoredType(limsSampleInfoDnaList, Constants.INSTORED_TYPE_07);
            if (ListUtils.isNotNullAndEmptyList(newSampleList)) {
                Map<String, Object> resultMap = new LinkedHashMap<>();
                resultMap.put("instoredTypeName", "无名尸");
                resultMap.put("newSampleList", newSampleList);
                newSampleInfoList.add(resultMap);
            }
            //嫌疑人亲属
            newSampleList = getListByInstoredType(limsSampleInfoDnaList, Constants.INSTORED_TYPE_08);
            if (ListUtils.isNotNullAndEmptyList(newSampleList)) {
                Map<String, Object> resultMap = new LinkedHashMap<>();
                resultMap.put("instoredTypeName", "嫌疑人亲属");
                resultMap.put("newSampleList", newSampleList);
                newSampleInfoList.add(resultMap);
            }
            //受害人亲属
            newSampleList = getListByInstoredType(limsSampleInfoDnaList, Constants.INSTORED_TYPE_09);
            if (ListUtils.isNotNullAndEmptyList(newSampleList)) {
                Map<String, Object> resultMap = new LinkedHashMap<>();
                resultMap.put("instoredTypeName", "受害人亲属");
                resultMap.put("newSampleList", newSampleList);
                newSampleInfoList.add(resultMap);
            }
            //失踪人口亲属
            newSampleList = getListByInstoredType(limsSampleInfoDnaList, Constants.INSTORED_TYPE_10);
            if (ListUtils.isNotNullAndEmptyList(newSampleList)) {
                Map<String, Object> resultMap = new LinkedHashMap<>();
                resultMap.put("instoredTypeName", "失踪人口亲属");
                resultMap.put("newSampleList", newSampleList);
                newSampleInfoList.add(resultMap);
            }
            //基础库
            newSampleList = getListByInstoredType(limsSampleInfoDnaList, Constants.INSTORED_TYPE_11);
            if (ListUtils.isNotNullAndEmptyList(newSampleList)) {
                Map<String, Object> resultMap = new LinkedHashMap<>();
                resultMap.put("instoredTypeName", "基础库");
                resultMap.put("newSampleList", newSampleList);
                newSampleInfoList.add(resultMap);
            }
            //YSTR
            newSampleList = getListByInstoredType(limsSampleInfoDnaList, Constants.INSTORED_TYPE_12);
            if (ListUtils.isNotNullAndEmptyList(newSampleList)) {
                Map<String, Object> resultMap = new LinkedHashMap<>();
                resultMap.put("instoredTypeName", "YSTR");
                resultMap.put("newSampleList", newSampleList);
                newSampleInfoList.add(resultMap);
            }
        }

        params.put("instoreDate", instoreDate);
        params.put("newSampleInfoList", newSampleInfoList);

        Writer out = null;
        try {
            Configuration cfg = new Configuration();
            cfg.setDefaultEncoding("UTF-8");
            cfg.setClassForTemplateLoading(this.getClass(), "/templates");
            //获取模板
            Template tmp = cfg.getTemplate("inboundOrder.ftl", "UTF-8");

            String filename = "-入库单" + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".doc";

            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(getGeneratePath(caseInfo, filename)), "UTF-8"));
            tmp.process(params, out);

            filePathName = getGeneratePath(caseInfo, filename);
        } catch (Exception ex) {
            logger.error("", ex);
        } finally {
            if (out != null) {
                try {
                    //上传文件到ftp服务器
                    PrintFtpFileUtils.uploadFtpFile(ftpIp, ftpPort, ftpUser, ftpPassword, ftpFilePath, filePathName, request, page);
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return filePathName;
    }

    public List<LimsSampleInfoDna> getListByInstoredType(List<LimsSampleInfoDna> sampleInfoDnaList, String instoredType) {
        List<LimsSampleInfoDna> sampleList = new ArrayList<>();

        for (LimsSampleInfoDna lsid : sampleInfoDnaList) {
            if (StringUtils.isNotBlank(lsid.getInstoredType()) && instoredType.equals(lsid.getInstoredType())) {
                sampleList.add(lsid);
            }
        }

        return sampleList;
    }

    private String getGeneratePath(LimsCaseInfo caseInfo, String filename) {
        String writeFilePath = bendiFilePath;
        writeFilePath = makePathFile(writeFilePath);

        String writeFileName = (StringUtils.isBlank(caseInfo.getCaseNo()) ? "" : caseInfo.getCaseNo()) + filename;

        String writeFile = "";
        if (writeFilePath.endsWith("\\") || writeFilePath.endsWith("/")) {
            writeFile += writeFilePath + writeFileName;
        } else {
            writeFile = writeFilePath + System.getProperty("file.separator") + writeFileName;
        }
        return writeFile;
    }

    /**
     * 验证目录是否存在，如果不存在，则创建对应目录。
     *
     * @param filePathName
     * @return
     */
    private static String makePathFile(String filePathName) {
        File pathFile = new File(filePathName);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }
        return pathFile + "\\\\";
    }


    /**
     * 下载预检验表
     *
     * @param request
     * @param response
     * @param consignmentId
     * @throws ParseException
     */
    @RequestMapping("/preAceptDoc")
    public void preAcept(HttpServletRequest request, HttpServletResponse response, String consignmentId) throws ParseException {
        downloadFileUtils.preAceptDoc(request, response, consignmentId);
    }

    List<LimsSampleInfoDna> SampleInfoDnaList = new ArrayList<>();

    /**
     * 在检案件管理 下载预实验表
     * @param request
     * @param response
     * @param
     * @throws ParseException
     */
    @RequestMapping(value = "/inspectionpreAceptDoc", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> inspectionpreAceptDoc(HttpServletRequest request, HttpServletResponse response, @RequestBody DelegateDataModel delegateDataModel) throws ParseException {
        Map<String, Object> result = new HashMap<>();
        try {
            if(delegateDataModel.getSampleInfoDnaList().size()>0){
                SampleInfoDnaList = delegateDataModel.getSampleInfoDnaList();
                /**
                 *更新预实验记录
                 */
                for(LimsSampleInfoDna sampleInfo:SampleInfoDnaList){
                    if(StringUtils.isNotBlank(sampleInfo.getSampleId())){
                        try {
                            limsSampleInfoDnaService.updatePre(sampleInfo);
                        }catch (Exception e){
                            e.printStackTrace();
                            logger.error("更新预实验记录出错！");
                        }
                    }
                }
                result.put("success",true);
            }else{
                result.put("success",false);
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("下载预实验记录表错误！");
            result.put("success",false);
        }
        return result;
    }

    @RequestMapping("/downloadPreAceptDoc")
    public void downloadPreAcept(HttpServletRequest request, HttpServletResponse response, String consignmentId) throws ParseException {
        downloadFileUtils.inspectionpreAceptDoc(request, response, SampleInfoDnaList,consignmentId);
    }

    @RequestMapping(value = "/preAceptDocIsNull", method = RequestMethod.POST)
    @ResponseBody
    public String preAceptDocIsNull(HttpServletRequest request, HttpServletResponse response, String consignmentId) throws ParseException {
        List<LimsSampleInfoDna> limsSampleInfoDnaList = limsSampleInfoDnaService.selectListByConsignmentId(consignmentId);
        String flag = "0";
        if (limsSampleInfoDnaList != null && !limsSampleInfoDnaList.isEmpty()) {
            for (LimsSampleInfoDna limsSampleInfoDna : limsSampleInfoDnaList) {
                String preMethod1Result = limsSampleInfoDna.getPreMethod1Result();
                String preMethod2Result = limsSampleInfoDna.getPreMethod2Result();
                String preMethod3Result = limsSampleInfoDna.getPreMethod3Result();
                if (StringUtils.isNotEmpty(preMethod1Result)) {
                    flag = "1";
                    break;
                }
                if (StringUtils.isNotEmpty(preMethod2Result)) {
                    flag = "1";
                    break;
                }
                if (StringUtils.isNotEmpty(preMethod3Result)) {
                    flag = "1";
                    break;
                }
            }
        }
        return flag;
    }


    /**
     * 下载样本流转记录表
     *
     * @param request
     * @param response
     * @param consignmentId
     * @throws ParseException
     */
    @RequestMapping("/circulationRecord")
    public void circulationRecord(HttpServletRequest request, HttpServletResponse response,
                                  String consignmentId) throws ParseException {
        downloadFileUtils.circulationRecord(request, response, consignmentId);
    }

    @RequestMapping(value = "/circulationRecordIsRtain", method = RequestMethod.POST)
    @ResponseBody
    public String circulationRecordIsRtain(HttpServletRequest request, HttpServletResponse response, String consignmentId) throws ParseException {
        List<LimsSampleInfoDna> limsSampleInfoDnaList = limsSampleInfoDnaService.selectListByConsignmentId(consignmentId);
        String flag = "0";
        if (limsSampleInfoDnaList != null && !limsSampleInfoDnaList.isEmpty()) {
            for (LimsSampleInfoDna limsSampleInfoDna : limsSampleInfoDnaList) {
                if (limsSampleInfoDna.getSampleFlag().equals("0")) {//物证
                    if (StringUtils.isNotEmpty(limsSampleInfoDna.getIsRetain())) {
                        if (!"0".equals(limsSampleInfoDna.getIsRetain())) {//是否留存
                            flag = "1";
                        }
                    }
                }
            }
        }
        return flag;
    }

    /*@RequestMapping("/barCodePrint")
    public void barCodePrint(HttpServletRequest request, HttpServletResponse response, String consignmentId) throws ParseException {
        barCodePrint("BJ19PDC0100004");
    }*/


    @RequestMapping(value = "/barCodePrint", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> sampleBarCodePrint(HttpServletRequest request, @RequestParam(value = "paramsData") String paramsData, String personPrintCnt, String samplePrintCnt) throws ParseException {
        Map<String, Object> map = new HashMap();

        JSONObject str = JSON.parseObject(paramsData);
        List<LimsPersonInfo> limsPersonInfoList = new ArrayList<>();
        List<LimsSampleInfoDna> sampleInfoDnaList = new ArrayList<>();
        if (str.containsKey("limsPersonInfoList")) {
            limsPersonInfoList = (List<LimsPersonInfo>) JSON.parseArray(str.get("limsPersonInfoList").toString(), LimsPersonInfo.class);
        }
        if (str.containsKey("limsSampleInfoDnaList")) {
            sampleInfoDnaList = (List<LimsSampleInfoDna>) JSON.parseArray(str.get("limsSampleInfoDnaList").toString(), LimsSampleInfoDna.class);
        }

        try {
            if (limsPersonInfoList.size() > 0) {
                for (LimsPersonInfo limsPersonInfo : limsPersonInfoList) {
                    LimsSampleInfoDna limsSampleInfoDna = limsSampleInfoDnaService.selectSampleInfoDnaById(limsPersonInfo.getPersonId());
                    barCodePrint(request, limsSampleInfoDna.getSampleNo(), personPrintCnt);
                }
            }
            if (sampleInfoDnaList.size() > 0) {
                for (LimsSampleInfoDna sampleInfoDna : sampleInfoDnaList) {
                    LimsSampleInfoDna limsSampleInfoDna = limsSampleInfoDnaService.selectSampleInfoDnaById(sampleInfoDna.getSampleId());
                    barCodePrint(request, limsSampleInfoDna.getSampleNo(), samplePrintCnt);
                }
            }
        } catch (Exception ex) {
            logger.error("打印条码错误", ex);
        }

        return map;
    }


    public void barCodePrint(HttpServletRequest request, String sampleNo, String printCnt) throws ParseException {
        String clientIp = IpAddressUtils.getIpAddr(request);
        String path = "\\\\" + clientIp + "\\Gprinter GP-1224T";
        TscLibDll.INSTANCE.openport(path);
        System.setProperty("jna.encoding", "GBK");
        System.loadLibrary("TSCLIB");
        //TscLibDll.INSTANCE.sendcommand("REM ***** This is a test by JAVA. *****");
        TscLibDll.INSTANCE.setup("50", "18", "4", "15", "0", "2", "0");
        TscLibDll.INSTANCE.clearbuffer();
        TscLibDll.INSTANCE.barcode("60", "20", "128", "50", "1", "0", "2", "6", sampleNo);

        TscLibDll.INSTANCE.printlabel("1", printCnt);
        TscLibDll.INSTANCE.closeport();
    }

    public interface TscLibDll extends Library {

        TscLibDll INSTANCE = (TscLibDll) Native.loadLibrary("TSCLIB", TscLibDll.class);

        int about();

        int openport(String pirnterName);

        int closeport();

        int sendcommand(String printerCommand);

        int setup(String width, String height, String speed, String density, String sensor, String vertical, String offset);

        int downloadpcx(String filename, String image_name);

        int barcode(String x, String y, String type, String height, String readable, String rotation, String narrow, String wide, String code);

        int printerfont(String x, String y, String fonttype, String rotation, String xmul, String ymul, String text);

        int clearbuffer();

        int printlabel(String set, String copy);

        int formfeed();

        int nobackfeed();

        int windowsfont(int x, int y, int fontheight, int rotation, int fontstyle, int fontunderline, String szFaceName, String content);
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

        //人员类型
        dictItem.setDictTypeCode(Constants.PERSON_TYPE);
        List<DictItem> personTypeList = DictUtil.getDictList(dictItem);
        modelAndView.addObject("personTypeList", personTypeList);


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

    /**
     * 查询委托人信息
     *
     * @param parentId
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryAmPersonalInfo")
    public AmPersonalInfo getCountyRegionListByParentIds(String parentId) {
        AmPersonalInfo amPersonalInfo = new AmPersonalInfo();
        try {
            if (StringUtils.isNotBlank(parentId)) {
                //查询委托人信息
                amPersonalInfo = amPersonalInfoService.queryAmPersonalInfo(parentId);
            }
        } catch (Exception ex) {
            logger.info("查询委托人信息失败" + ex);
        }
        return amPersonalInfo;
    }

    /**
     * 确认受理
     *
     * @param paramsData
     * @return
     */
    @RequestMapping(value = "/submitAcceptCase", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> submitAcceptCase(@RequestParam(value = "paramsData") String paramsData,HttpServletRequest request) {
        Map<String, Object> map = new HashMap();
        String url = "";
        try {
            JSONObject str = JSON.parseObject(paramsData);
            //委托信息
            LimsConsignmentInfo consignatioInfo = new LimsConsignmentInfo();
            //案件信息
            LimsCaseInfo caseInfoDna = new LimsCaseInfo();
            //被鉴定人信息已经对应的样本信息
            List<LimsPersonInfo> limsPersonInfoList = new ArrayList<>();
            //物证检材信息
            List<LimsSampleInfoDna> sampleInfoDnaList = new LinkedList<>();

            String personIds = "";
            String sampleIdWzs = "";
            String sampleIds = "";

            //解析前台传来的数据
            if (str.containsKey("personIds")) {
                personIds = str.get("personIds").toString();
            }
            if (str.containsKey("sampleIdWzs")) {
                sampleIdWzs = str.get("sampleIdWzs").toString();
            }
            if (str.containsKey("sampleIds")) {
                sampleIds = str.get("sampleIds").toString();
            }

            if (str.containsKey("consignmentInfo")) {
                consignatioInfo = JSON.parseObject(str.get("consignmentInfo").toString(), LimsConsignmentInfo.class);
            }

            if (str.containsKey("caseInfoDna")) {
                caseInfoDna = JSON.parseObject(str.get("caseInfoDna").toString(), LimsCaseInfo.class);
            }

            if (str.containsKey("limsPersonInfoList")) {
                limsPersonInfoList = (List<LimsPersonInfo>) JSON.parseArray(str.get("limsPersonInfoList").toString(), LimsPersonInfo.class);
                if (!CollectionUtils.isEmpty(limsPersonInfoList)) {
                    //确定受理 修改未受理检材状态为已受理  01-->02
                    for (LimsPersonInfo limsPersonInfo : limsPersonInfoList) {
                        List<LimsSampleInfoDna> sampleInfoDnaList1 = limsPersonInfo.getSampleInfoDnaList();
                        if (!CollectionUtils.isEmpty(sampleInfoDnaList1)) {
                            for (LimsSampleInfoDna limsSampleInfoDna : sampleInfoDnaList1) {
                                if (limsSampleInfoDna.getSampleStatus().equals("01")) {
                                    limsSampleInfoDna.setSampleStatus("02");
                                }
                            }
                        }
                    }
                }
            }

            if (str.containsKey("sampleInfoDnaList")) {
                sampleInfoDnaList = (List<LimsSampleInfoDna>) JSON.parseArray(str.get("sampleInfoDnaList").toString(), LimsSampleInfoDna.class);
                if (!CollectionUtils.isEmpty(sampleInfoDnaList)) {
                    //确定受理 修改未受理检材状态为已受理  01-->02
                    for (LimsSampleInfoDna limsSampleInfoDna : sampleInfoDnaList) {
                        if (limsSampleInfoDna.getSampleStatus().equals("01")) {
                            limsSampleInfoDna.setSampleStatus("02");
                        }
                    }
                }
            }

//            List<LimsSampleInfoDna> limsSampleInfoDnaList = sortListMapString(sampleInfoDnaList);
            DelegateDataModel delegateDataModel = new DelegateDataModel();
            delegateDataModel.setCaseInfoDna(caseInfoDna);
            delegateDataModel.setLimsPersonInfoList(limsPersonInfoList);
//            delegateDataModel.setSampleInfoDnaList(sampleInfoDnaList);
            delegateDataModel.setSampleInfoDnaList(sampleInfoDnaList);
            delegateDataModel.setConsignatioInfo(consignatioInfo);

            //获取当前登录人信息
            Subject subject = SecurityUtils.getSubject();
            LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
            if (operateUser == null) {
                map.put("success", false);
                map.put(url, "/login.html?timeoutFlag=1");
                return map;
            }


            //添加确认受理信息
            limsCaseInfoService.submitAcceptCase(delegateDataModel, operateUser, personIds, sampleIdWzs, sampleIds);
            map.put("success", true);

            if (1 == isAppInform){ //1开启 0默认关闭
                String date = DateUtils.dateToString(new Date(),"yyyy-MM-dd HH:mm:ss");
                CallThirdpartyInfo bean = new CallThirdpartyInfo();
                //发起人信息
                bean.setUserid(operateUser.getUserId());
                bean.setUserName(operateUser.getLoginName());
                bean.setOrgid(operateUser.getOrgId());
                bean.setIp(IpAddressUtils.getIpAddr(request));
                //接收人
                List<String> phones = new ArrayList<>();
                if (null != consignatioInfo.getDelegator1PhoneNumber())
                    phones.add(consignatioInfo.getDelegator1PhoneNumber());
                if (null != consignatioInfo.getDelegator2PhoneNumber())
                    phones.add(consignatioInfo.getDelegator2PhoneNumber());
                bean.setPhones(phones);
                String countName = consignatioInfo.getDelegator1Name()+","+consignatioInfo.getDelegator2Name();
                ArrayList<MobileNews> mobileNewsList = new ArrayList<>();
                MobileNews count = new MobileNews();
                count.setId(UuidUtil.generateUUID());
                count.setTitle(caseInfoDna.getCaseName());
                count.setContent(CallThirdpartyTool.Content(1,countName,consignatioInfo.getDelegateOrgName(),null));
                count.setState(0);
                count.setCreateDatetime(date);
                count.setUpdateDatetime(date);
                count.setType(1);
                count.setUserid(consignatioInfo.getDelegator1Id());
                count.setUsername(consignatioInfo.getDelegator1Name());
                count.setUserOrg(consignatioInfo.getDelegateOrgCode());
                count.setCaseId(consignatioInfo.getCaseId());
                count.setMessageType(1);
                MobileNews count2 = new MobileNews();
                count2.setId(UuidUtil.generateUUID());
                count2.setTitle(caseInfoDna.getCaseName());
                count2.setContent(CallThirdpartyTool.Content(1,countName,consignatioInfo.getDelegateOrgName(),null));
                count2.setState(0);
                count2.setCreateDatetime(date);
                count2.setUpdateDatetime(date);
                count2.setType(1);
                count2.setUserid(consignatioInfo.getDelegator2Id());
                count2.setUsername(consignatioInfo.getDelegator2Name());
                count2.setUserOrg(consignatioInfo.getDelegateOrgCode());
                count2.setCaseId(consignatioInfo.getCaseId());
                count2.setMessageType(1);
                MobileNews count3 = new MobileNews();
                count3.setId(UuidUtil.generateUUID());
                count3.setTitle(caseInfoDna.getCaseName());
                count3.setContent(CallThirdpartyTool.Content(1,countName,consignatioInfo.getDelegateOrgName(),null));
                count3.setState(0);
                count3.setCreateDatetime(date);
                count3.setUpdateDatetime(date);
                count3.setType(1);
                count3.setUserid("pc");
                count3.setUserOrg(consignatioInfo.getDelegateOrgCode());
                count3.setCaseId(consignatioInfo.getCaseId());
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

        } catch (Exception ex) {
            map.put("error", false);
            logger.error("确认受理保存失败" + ex);
        }
        return map;
    }


//    private static List<LimsSampleInfoDna> sortListMapString(List<LimsSampleInfoDna> list) {
//
//        Collections.sort(list, new Comparator<LimsSampleInfoDna>() {
//            public int compare(LimsSampleInfoDna o1, LimsSampleInfoDna o2) {
//                return o1.getEvidenceNo().toString().compareTo(
//                        o2.getEvidenceNo().toString());
//        }
//        });
//        return list;
//    }


    /**
     * dna非案件委托登记
     *
     * @return
     */
    @RequestMapping(value = "/nonDnaReg", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public ModelAndView nonDnaReg(HttpSession session) {
        ModelAndView view = new ModelAndView();

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        operateUser.setStatusId("1");

        //查询委托单位
        OrgInfo orgInfo = orgInfoService.selectByPrimaryKey(operateUser.getOrgId());
        view.addObject("orgInfo", orgInfo);
        //查询法医中心
        OrgInfo forensicCenterorg = orgInfoService.selectByPrimaryKey(Constants.FORENSIC_CENTER_ORG_ID);
        view.addObject("forensicCenterorg", forensicCenterorg);
        //创建orgInfoList点击鉴定中心的选择，可以多选
        List<OrgInfo> orgInfos = new ArrayList<>();
        if (orgInfo != null) {
            if (StringUtils.isNotBlank(orgInfo.getOrgQualification())) {
                orgInfos.add(orgInfo);
                orgInfos.add(forensicCenterorg);
            }

        }
        view.addObject("orgInfos", orgInfos);
        //分局
        List<OrgInfo> orgInfoList2 = null;
        List<AmPersonalInfo> personalInfoList = null;
        if (StringUtils.isBlank(operateUser.getOrgId()) || operateUser.getOrgId().contains("110023") || operateUser.getOrgId().contains("11023")) {
            orgInfoList2 = orgInfoService.selectAll();
            //获取委托人管理数据
            personalInfoList = amPersonalInfoService.queryAmPersonalInfoLIst(null);
        } else {
            orgInfoList2 = orgInfoService.selectDelegateByParentsId(operateUser.getOrgId());
            //获取委托人管理数据
            personalInfoList = amPersonalInfoService.queryAmPersonalInfoLIst(operateUser.getOrgId());
        }
//        List<OrgInfo> orgInfoList2 = orgInfoService.selectDelegateByParentsId(operateUser.getOrgId());
        view.addObject("orgInfoList2", orgInfoList2);

        System.out.println(personalInfoList);
        view.addObject("personalInfoList", personalInfoList);

        DictItem dictItem = new DictItem();
        //案件类型
        dictItem.setDictTypeCode(Constants.CASE_TYPE);
        List<DictItem> caseTypeList = DictUtil.getDictList(dictItem);
        view.addObject("caseTypeList", caseTypeList);

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

        view.addObject("casePropertyList", casePropertyList1);

        //案件级别
        dictItem.setDictTypeCode(Constants.CASE_LEVEL);
        List<DictItem> caseLevelList = DictUtil.getDictList(dictItem);
        view.addObject("caseLevelList", caseLevelList);

        //人员类型
        dictItem.setDictTypeCode(Constants.PERSON_TYPE);
        List<DictItem> personTypeList = DictUtil.getDictList(dictItem);
        view.addObject("personTypeList", personTypeList);

        //性别
        dictItem.setDictTypeCode(Constants.GENDER);
        List<DictItem> genderList = DictUtil.getDictList(dictItem);
        view.addObject("genderList", genderList);

        //检材类型
        dictItem.setDictTypeCode(Constants.SAMPLE_TYPE);
        List<DictItem> sampleTypeList = DictUtil.getDictList(dictItem);
        view.addObject("sampleTypeList", sampleTypeList);

        //包装方法PACK_METHOD
        dictItem.setDictTypeCode(Constants.PACK_METHOD);
        List<DictItem> packMethodList = DictUtil.getDictList(dictItem);
        view.addObject("packMethodList", packMethodList);

        //案件状态CASE_STATUS
        dictItem.setDictTypeCode(Constants.CASE_STATUS);
        List<DictItem> caseStatusList = DictUtil.getDictList(dictItem);
        view.addObject("caseStatusList", caseStatusList);

        //职务
        dictItem.setDictTypeCode(Constants.POSITION_LIST);
        List<DictItem> positionList = DictUtil.getDictList(dictItem);
        view.addObject("positionList", positionList);

        //亲缘关系
        dictItem.setDictTypeCode(Constants.RELATION_TYPE);
        List<DictItem> relationTypeList = DictUtil.getDictList(dictItem);
        view.addObject("relationTypeList", relationTypeList);

        //提取方法
        dictItem.setDictTypeCode(Constants.EXTRACT_METHOD);
        List<DictItem> extractMethodList = DictUtil.getDictList(dictItem);
        view.addObject("extractMethodList", extractMethodList);

        //载体
        dictItem.setDictTypeCode(Constants.SAMPLE_CARRIER);
        List<DictItem> sampleCarrierList = DictUtil.getDictList(dictItem);
        Collections.sort(sampleCarrierList);
        view.addObject("sampleCarrierList", sampleCarrierList);
        session.setAttribute("user", operateUser);

        LimsConsignmentInfo limsConsignmentInfo = new LimsConsignmentInfo();
        if (limsConsignmentInfo.getConsignmentNo() == null) {
            limsConsignmentInfo.setConsignmentNo("委托书编号自动生成");
            view.addObject("consignatioInfo", limsConsignmentInfo);
        }
        view.setViewName("accept/nonDnaReg");
        return view;
    }


    /**
     * 添加非案件委托信息
     *
     * @param paramsData
     * @return
     */
    @RequestMapping(value = "/submitNonDelegate", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> submitNonDelegate(@RequestParam(value = "paramsData") String paramsData) {
        Map<String, Object> map = new HashMap();
        String url = "";
        try {
            JSONObject str = JSON.parseObject(paramsData);
            //委托信息
            LimsConsignmentInfo consignatioInfo = new LimsConsignmentInfo();
            //案件信息
            LimsCaseInfo caseInfoDna = new LimsCaseInfo();
            //被鉴定人信息已经对应的样本信息
            List<LimsPersonInfo> limsPersonInfoList = new ArrayList<>();

            //物证检材信息
            List<LimsSampleInfoDna> sampleInfoDnaList = new ArrayList<>();

            String personIds = "";
            String sampleIds = "";
            String evaluationCenterId = "";

            //解析前台传来的数据
            if (str.containsKey("evaluationCenter")) {
                evaluationCenterId = str.get("evaluationCenter").toString();
            }
            if (str.containsKey("personIds")) {
                personIds = str.get("personIds").toString();
            }
            if (str.containsKey("sampleIds")) {
                sampleIds = str.get("sampleIds").toString();
            }

            if (str.containsKey("consignmentInfo")) {
                consignatioInfo = JSON.parseObject(str.get("consignmentInfo").toString(), LimsConsignmentInfo.class);
            }

            if (str.containsKey("caseInfoDna")) {
                caseInfoDna = JSON.parseObject(str.get("caseInfoDna").toString(), LimsCaseInfo.class);
            }

            if (str.containsKey("limsPersonInfoList")) {
                limsPersonInfoList = (List<LimsPersonInfo>) JSON.parseArray(str.get("limsPersonInfoList").toString(), LimsPersonInfo.class);
            }


            if (str.containsKey("sampleInfoDnaList")) {
                sampleInfoDnaList = (List<LimsSampleInfoDna>) JSON.parseArray(str.get("sampleInfoDnaList").toString(), LimsSampleInfoDna.class);
            }

            DelegateDataModel delegateDataModel = new DelegateDataModel();
            delegateDataModel.setCaseInfoDna(caseInfoDna);
            delegateDataModel.setLimsPersonInfoList(limsPersonInfoList);
            delegateDataModel.setConsignatioInfo(consignatioInfo);

            delegateDataModel.setSampleInfoDnaList(sampleInfoDnaList);

            //获取当前登录人信息
            Subject subject = SecurityUtils.getSubject();
            LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
            if (operateUser == null) {
                map.put("success", false);
                map.put(url, "/login.html?timeoutFlag=1");
                return map;
            }

            //添加委托信息
            Map<String, String> result = limsConsignmentInfoService.submitNonDelegate(delegateDataModel, operateUser, personIds, sampleIds, evaluationCenterId);
            map.put("success", true);
            map.put("caseId", result == null ? "" : (result.get("caseId") == null ? "" : result.get("caseId")));
            map.put("consignmentId", result == null ? "" : (result.get("consignmentId") == null ? "" : result.get("consignmentId")));
        } catch (Exception ex) {
            map.put("error", false);
            logger.error("非案件委托登记保存失败" + ex.getMessage());
        }
        return map;
    }

    /**
     * 修改案件失败
     *
     * @param paramsData
     * @return
     */
    @RequestMapping(value = "/updateCase", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> updateCase(@RequestParam(value = "paramsData") String paramsData) {
        Map<String, Object> map = new HashMap();
        String url = "";
        try {
            JSONObject str = JSON.parseObject(paramsData);
            //委托信息
            LimsConsignmentInfo consignatioInfo = new LimsConsignmentInfo();
            //案件信息
            LimsCaseInfo caseInfoDna = new LimsCaseInfo();
            //被鉴定人信息已经对应的样本信息
            List<LimsPersonInfo> limsPersonInfoList = new ArrayList<>();
            //物证检材信息
            List<LimsSampleInfoDna> sampleInfoDnaList = new ArrayList<>();

            String personIds = "";
            String sampleIdWzs = "";
            String sampleIds = "";

            //解析前台传来的数据
            if (str.containsKey("personIds")) {
                personIds = str.get("personIds").toString();
            }
            if (str.containsKey("sampleIdWzs")) {
                sampleIdWzs = str.get("sampleIdWzs").toString();
            }
            if (str.containsKey("sampleIds")) {
                sampleIds = str.get("sampleIds").toString();
            }

            if (str.containsKey("consignmentInfo")) {
                consignatioInfo = JSON.parseObject(str.get("consignmentInfo").toString(), LimsConsignmentInfo.class);
            }

            if (str.containsKey("caseInfoDna")) {
                caseInfoDna = JSON.parseObject(str.get("caseInfoDna").toString(), LimsCaseInfo.class);
            }

            if (str.containsKey("limsPersonInfoList")) {
                limsPersonInfoList = (List<LimsPersonInfo>) JSON.parseArray(str.get("limsPersonInfoList").toString(), LimsPersonInfo.class);
            }

            if (str.containsKey("sampleInfoDnaList")) {
                sampleInfoDnaList = (List<LimsSampleInfoDna>) JSON.parseArray(str.get("sampleInfoDnaList").toString(), LimsSampleInfoDna.class);
            }

            DelegateDataModel delegateDataModel = new DelegateDataModel();
            delegateDataModel.setCaseInfoDna(caseInfoDna);
            delegateDataModel.setLimsPersonInfoList(limsPersonInfoList);
            delegateDataModel.setSampleInfoDnaList(sampleInfoDnaList);
            delegateDataModel.setConsignatioInfo(consignatioInfo);

            //获取当前登录人信息
            Subject subject = SecurityUtils.getSubject();
            LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
            if (operateUser == null) {
                map.put("success", false);
                map.put(url, "/login.html?timeoutFlag=1");
                return map;
            }

            limsCaseInfoService.updateCase(delegateDataModel, operateUser, personIds, sampleIdWzs, sampleIds);
            map.put("success", true);
        } catch (Exception ex) {
            map.put("error", false);
            logger.error("修改案件失败" + ex);
        }
        return map;
    }

    @RequestMapping("/exportDocument")
    public void exportDocument(HttpServletRequest request, HttpServletResponse response) {
        String contextPath = request.getServletContext().getRealPath("/WEB-INF/SignBazl");
        String fileName = Constants.SignBazl;
        Path path = Paths.get(contextPath, fileName);
        if (Files.exists(path)) {
            output(fileName, path, response);
        }
    }

    private void output(String fileName, Path file, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);//类型

        try {
            Files.copy(file, response.getOutputStream());
        } catch (IOException ex) {
            logger.error("下载错误", ex);
        }
    }
}
