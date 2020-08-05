package com.bazl.clims.controller.center;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bazl.clims.common.Constants;
import com.bazl.clims.dao.CaseFtpUrlMapper;
import com.bazl.clims.model.DictItem;
import com.bazl.clims.model.LaboratoryInfo;
import com.bazl.clims.model.LoaUserInfo;
import com.bazl.clims.model.bo.LabListModel;
import com.bazl.clims.model.bo.PcrListModel;
import com.bazl.clims.model.po.*;
import com.bazl.clims.service.*;
import com.bazl.clims.utils.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.logging.log4j.util.Strings;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.BASE64Decoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipOutputStream;

/**
 * Created by Administrator on 2019/4/18.
 * SongShuai
 */
@Controller
@RequestMapping("dowmFileController")
public class DowmFileController extends CaseAcceptController {

    @Autowired
    DownloadFileUtils downloadFileUtils;
    @Autowired
    LimsSampleInfoDnaService sampleInfoDnaService;
    @Autowired
    LabPcrSampleService labPcrSampleService;
    @Autowired
    LabPcrInfoService labPcrInfoService;
    @Autowired
    LabSySampleService LabSySampleService;
    @Autowired
    LabSyInfoService labSyInfoService;
    @Autowired
    LabExtractInfoService labExtractInfoService;
    @Autowired
    EquipmentNameInfoService equipmentNameInfoService;
    @Autowired
    PanelService panelService;
    @Autowired
    LabExtractSampleService labExtractSampleService;
    @Autowired
    FileArchivesInfoService fileArchivesInfoService;
    @Autowired
    MatchAuditedGeneService matchAuditedGeneService;
    @Autowired
    LaboratoryInfoService laboratoryInfoService;
    @Autowired
    LabDetailsService labDetailsService;
    @Autowired
    ReagentInfoService reagentInfoService;
    @Autowired
    ReagentOutgoInfoService reagentOutgoInfoService;
    @Autowired
    EquipmentTypeInfoService equipmentTypeInfoService;
    @Autowired
    UseInstrumentsService useInstrumentsService;
    @Autowired
    private CaseFtpUrlMapper caseFtpUrlMapper;
    @Autowired
    QcNoSettingsService qcNoSettingsService;

    private DownloadFileUtils DownloadFileUtils;

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
    @Value("${page}")
    private String page;
    @Value("${bendiFilePath}")
    private String bendiFilePath;
    @Value("${samplePhotosRootDir}")
    private String samplePhotosRootDir;
//    @Value("${laboratory_record_cover}")
//    private String laboratoryRecordCover;  实验室记录封皮编号
//    @Value("${routine_extraction_records_no}")
//    private String routineExtractionNo;  常规提取记编号
//    @Value("${amplified_records_no}")
//    private String amplifiedRecordsNo;   扩增记录编号
    @Value("${ftpCaseFilePath}")
    private String ftpCaseFilePath;
//    @Value("${warehouse_receipt_no}")
//    private String warehouseReceiptNo; 入库单编号
//    @Value("${seminal_extraction_no}")
//    private String seminalExtractionNo;   精斑提取记编号
//    @Value("${electrophoretic_recording_no}")
//    private String electrophoreticRecordingNo; 电泳记录编号
//    @Value("${electrophoretic_automatic_no}")
//    private String electrophoretic_automatic_no;  自动提取页面，生成电泳分离记录编号


    /**
     * 获取当前用户,根据当前查询实验室详情表的信息，插入到生成的文件
     */
    public  Map<String, Object> thisUserLabDetails (){
        Map<String, Object> result = new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo user=(LoaUserInfo) subject.getPrincipal();
        //根据org_id查询实验室相关信息表
        LaboratoryInfo laboratoryInfo = new  LaboratoryInfo();
        if(Strings.isNotBlank(user.getOrgId())){
            try {
                laboratoryInfo.setOrgId(user.getOrgId());
                LaboratoryInfo laboratoryInfo1 = laboratoryInfoService.queryById(laboratoryInfo);

                //根据实验室信息表查询出实验室详情表中的所有信息例如：文件受控号，鉴定中心名称，地址等等
                if(Objects.nonNull(laboratoryInfo1)){
                    List<LabDetails>  labDetailsList  = labDetailsService.querylabDetailsList(laboratoryInfo1.getId());
                    result.put("labDetailsList",labDetailsList);
                }
            }catch (Exception ex){
                logger.error("获取当前用户所属分局实验室详情错误！", ex);
            }
        }
        return result;
    }

    /**
     * 提取、扩增、电泳记录表下载、打印页面
     * @param request
     * @param caseId
     * @return
     */
    @RequestMapping("/recordDownload")
    public ModelAndView recordDownload(HttpServletRequest request, String caseId) {
        ModelAndView modelAndView = new ModelAndView();
        //根据案件id查询提取实验信息
        List<LabExtractInfo> labExtractInfoList = labExtractInfoService.selectByCaseId(caseId);
        if (ListUtils.isNotNullAndEmptyList(labExtractInfoList)) {
            for (LabExtractInfo extractInfo : labExtractInfoList) {
                LabExtractSample extractSample = new LabExtractSample();
                extractSample.setExtractId(extractInfo.getExtractId());
                extractSample.setCaseId(caseId);
                List<LabExtractSample> extractSampleList = labExtractSampleService.selectList(extractSample);
                extractInfo.setSampleCount((short)extractSampleList.size());
            }
        }
        //根据案件id获取扩增实验信息
        List<LabPcrInfo> labPcrInfoList = labPcrInfoService.selectByCaseId(caseId);
        if (ListUtils.isNotNullAndEmptyList(labPcrInfoList)) {
            for (LabPcrInfo pcrInfo : labPcrInfoList) {
                LabPcrSample pcrSample = new LabPcrSample();
                pcrSample.setPcrId(pcrInfo.getPcrId());
                pcrSample.setCaseId(caseId);
                List<LabPcrSample> pcrSampleList = labPcrSampleService.selectList(pcrSample);
                pcrInfo.setSampleCount((short)pcrSampleList.size());
            }
        }
        //根据案件id获取电泳实验信息
        List<LabSyInfo> labSyInfoList = labSyInfoService.selectByCaseId(caseId);
        if (ListUtils.isNotNullAndEmptyList(labSyInfoList)) {
            for (LabSyInfo syInfo : labSyInfoList) {
                LabSySample sySample = new LabSySample();
                sySample.setSyId(syInfo.getSyId());
                sySample.setCaseId(caseId);
                List<LabSySample> sySampleList = LabSySampleService.selectList(sySample);
                syInfo.setSampleCount((short)sySampleList.size());
            }
        }

        modelAndView.addObject("labExtractInfoList", labExtractInfoList);
        modelAndView.addObject("labPcrInfoList", labPcrInfoList);
        modelAndView.addObject("labSyInfoList", labSyInfoList);
        modelAndView.addObject("caseId", caseId);
        modelAndView.setViewName("query/recordPage");
        return modelAndView;
    }

    /**
     * 卷皮下载
     */
    @RequestMapping("/isdowmFileVolumeBtn")
    public void isdowmFileVolumeBtn(HttpServletRequest request, HttpServletResponse response, String consignmentId) throws ParseException {
        generateVolumeload(request, response, consignmentId);
    }

    public void generateVolumeload(HttpServletRequest request, HttpServletResponse response,
                                   String consignmentId) throws ParseException {


        LimsCaseInfo caseInfo = limsCaseInfoService.selectByConsignmentId(consignmentId);
        LimsConsignmentInfo consignment = limsConsignmentInfoService.selectByConsignmentId(consignmentId);
        List<LimsSampleInfoDna> limsSampleInfoDnaList = limsSampleInfoDnaService.selectListByConsignmentId(consignmentId);

        Map<String, Object> params = new HashMap<String, Object>();

        /**
         *根据登陆用户查询实验室封皮页面--liuchang
         */
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo user=(LoaUserInfo) subject.getPrincipal();
        //根据org_id查询编码设置详情
        if(Strings.isNotBlank(user.getOrgId())) {
            List<QcNoSettings> newQcNoSettings = qcNoSettingsService.selectByLab(user.getOrgId());
            if (newQcNoSettings != null && !newQcNoSettings.isEmpty()) {
                Calendar date = Calendar.getInstance(); //获取年份
                for (QcNoSettings qcNoSettings : newQcNoSettings) {
                    if (qcNoSettings.getDictCode().equals(Constants.LABORATORY_RECORD_COVER)) {
                        String laboratoryRecordCover = qcNoSettings.getLabServerHeader() + "-" + String.valueOf(date.get(Calendar.YEAR)) + "-" + qcNoSettings.getBarcode();
                        params.put("laboratoryRecordCover", StringUtils.isBlank(laboratoryRecordCover) ? "" : laboratoryRecordCover);
                    }
                }
            }
        }

        if(caseInfo != null){
            params.put("caseNo",StringUtils.isBlank(caseInfo.getCaseNo())?"":caseInfo.getCaseNo());

            params.put("barcode1", BarcodeUtil.generateFile(StringUtils.isBlank(caseInfo.getCaseNo()) ? "" :caseInfo.getCaseNo() , "barcode1.png"));

            params.put("caseName",StringUtils.isBlank(caseInfo.getCaseName())?"":caseInfo.getCaseName());

        }
        AmPersonalInfo acceptPersonInfo = new AmPersonalInfo();
        if(consignment != null){

            //委托单位
            params.put("delegateOrgName", StringUtils.isBlank(consignment.getDelegateOrgName()) ? "" : consignment.getDelegateOrgName());
            if (consignment.getAcceptorId() != null) {
                acceptPersonInfo = amPersonalInfoService.selectByPersonalId(consignment.getAcceptorId());
            }
            //受理人
            if (acceptPersonInfo != null) {
                params.put("acceptPerson", StringUtils.isBlank(acceptPersonInfo.getFullName()) ? "" : acceptPersonInfo.getFullName());
            }

            OrgInfo orgInfo = orgInfoService.selectByPrimaryKey(consignment.getAcceptOrgId());
            if (orgInfo != null) {
                params.put("getOrgQualification", StringUtils.isBlank(orgInfo.getOrgQualification()) ? "" : orgInfo.getOrgQualification());
            } else {
                params.put("getOrgQualification", "");
            }

            if(consignment.getAcceptDatetime() != null){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String acceptDatetime = sdf.format(consignment.getAcceptDatetime());
                //2019-08-26
                System.out.println(acceptDatetime);
                String year = acceptDatetime.substring(0, 4);
                params.put("year", StringUtils.isBlank(year) ? "" : year);
                String month = acceptDatetime.substring(5,7);
                params.put("month", StringUtils.isBlank(month) ? "" : month);
                String days = acceptDatetime.substring(8);
                params.put("days", StringUtils.isBlank(days) ? "" : days);
            }
        }



        try {
            Configuration cfg = new Configuration();
            cfg.setDefaultEncoding("UTF-8");
            cfg.setClassForTemplateLoading(this.getClass(), "/templates");
            //获取模板
            String ftlName = "recordCover.ftl";

            Template tmp = cfg.getTemplate(ftlName, "UTF-8");

            String filename = "案件档案-卷皮" + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".doc";

            response.setCharacterEncoding("UTF-8");
            //文件头，导出的文件名
            response.setHeader("Content-disposition", "attachment;filename=" + new String(filename.getBytes("GBK"), "ISO-8859-1"));
            //类型
            response.setContentType("application/x-msdownload");
            tmp.process(params, response.getWriter());
        } catch (Exception ex) {
            logger.error("下载错误！", ex);
        }

    }

    /**
     * 卷皮打印
     */
    @RequestMapping("/compressedVolume")
    @ResponseBody
    public Map<String, Object> compressedVolume(HttpServletRequest request, HttpServletResponse response, String consignmentId,String flag,String fullPrint) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> params = new HashMap<String, Object>();
        Writer out = null;
        String filePathName = null;

        LimsCaseInfo caseInfo = limsCaseInfoService.selectByConsignmentId(consignmentId);
        LimsConsignmentInfo consignment = limsConsignmentInfoService.selectByConsignmentId(consignmentId);

        if(caseInfo != null){
            params.put("caseNo",StringUtils.isBlank(caseInfo.getCaseNo())?"":caseInfo.getCaseNo());

            params.put("barcode1", BarcodeUtil.generateFile(StringUtils.isBlank(caseInfo.getCaseNo()) ? "" :caseInfo.getCaseNo() , "barcode1.png"));

            params.put("caseName",StringUtils.isBlank(caseInfo.getCaseName())?"":caseInfo.getCaseName());

        }
        AmPersonalInfo acceptPersonInfo = new AmPersonalInfo();
        if(consignment != null){

            //委托单位
            params.put("delegateOrgName", StringUtils.isBlank(consignment.getDelegateOrgName()) ? "" : consignment.getDelegateOrgName());
            if (consignment.getAcceptorId() != null) {
                acceptPersonInfo = amPersonalInfoService.selectByPersonalId(consignment.getAcceptorId());
            }
            //受理人
            if (acceptPersonInfo != null) {
                params.put("acceptPerson", StringUtils.isBlank(acceptPersonInfo.getFullName()) ? "" : acceptPersonInfo.getFullName());
            }

            OrgInfo orgInfo = orgInfoService.selectByPrimaryKey(consignment.getAcceptOrgId());
            if (orgInfo != null) {
                params.put("getOrgQualification", StringUtils.isBlank(orgInfo.getOrgQualification()) ? "" : orgInfo.getOrgQualification());
            } else {
                params.put("getOrgQualification", "");
            }

            if(consignment.getAcceptDatetime() != null){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String acceptDatetime = sdf.format(consignment.getAcceptDatetime());
                //2019-08-26
                System.out.println(acceptDatetime);
                String year = acceptDatetime.substring(0, 4);
                params.put("year", StringUtils.isBlank(year) ? "" : year);
                String month = acceptDatetime.substring(5,7);
                params.put("month", StringUtils.isBlank(month) ? "" : month);
                String days = acceptDatetime.substring(8);
                params.put("days", StringUtils.isBlank(days) ? "" : days);
            }
        }
        /**
         * 根据登陆用户查询实验室封皮页面--liuchang
         */
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo user=(LoaUserInfo) subject.getPrincipal();
        if(Strings.isNotBlank(user.getOrgId())) {
            List<QcNoSettings> newQcNoSettings = qcNoSettingsService.selectByLab(user.getOrgId());
            if (newQcNoSettings != null && !newQcNoSettings.isEmpty()) {
                Calendar date = Calendar.getInstance(); //获取年份
                for (QcNoSettings qcNoSettings : newQcNoSettings) {
                    if (qcNoSettings.getDictCode().equals(Constants.LABORATORY_RECORD_COVER)) {
                        String laboratoryRecordCover = qcNoSettings.getLabServerHeader() + "-" + String.valueOf(date.get(Calendar.YEAR)) + "-" + qcNoSettings.getBarcode();
                        params.put("laboratoryRecordCover", StringUtils.isBlank(laboratoryRecordCover) ? "" : laboratoryRecordCover);
                    }
                }
            }
        }


        String path = null;

        try {
            Configuration cfg = new Configuration();
            cfg.setDefaultEncoding("UTF-8");
            cfg.setClassForTemplateLoading(this.getClass(), "/templates");
            //获取模板
            String ftlName = "recordCover.ftl";

            Template tmp = cfg.getTemplate(ftlName, "UTF-8");

            String filename = UuidUtil.generateUUID()+ "-" + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".doc";

            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(getGeneratePath(caseInfo, filename)), "UTF-8"));
            tmp.process(params, out);
            out.flush();
            out.close();
            filePathName = getGeneratePath(caseInfo, filename);
            //案件打印 调用案件打印方法
            //根据flag判断是否选中的是打印按钮
            if(flag.equals("2")){
                String page="1";
                if(Strings.isNotBlank(filePathName)){
//                    this.httpLink(request,page,filePathName);

                    if("1".equals(fullPrint)){
                        path = CasePrintFtpFileUtils.uploadFtpFileNew(ftpIp, ftpPort, ftpUser, ftpPassword, ftpCaseFilePath, filePathName, request, page,fullPrint);
                        System.out.println(path.toString());
                    }else{
                        CasePrintFtpFileUtils.uploadFtpFileNew(ftpIp, ftpPort, ftpUser, ftpPassword, ftpFilePath, filePathName, request, page,fullPrint);
                    }


                }
            }
        } catch (Exception ex) {
            logger.error("下载错误！", ex);
        }

        if("1".equals(fullPrint)){
            result.put("filePathName", path);
        }else{
            result.put("filePathName", filePathName);
        }
        return result;

    }


    /**
     * 预实验记录表下载
     *
     * @param consignmentId
     * @param request
     * @param response
     * @throws ParseException
     */
    @RequestMapping("dowmFilePreliminaryExperiment")
    public void dowmFilePreliminaryExperiment(String consignmentId, HttpServletRequest request, HttpServletResponse response) throws ParseException {
        downloadFileUtils.preAceptDoc(request, response, consignmentId);
    }

    /**
     * 受理确认书下载
     *
     * @param consignmentId
     * @param request
     * @param response
     * @throws ParseException
     */
    @RequestMapping("dowmFileCommissionedToConfirm")
    public void dowmFileCommissionedToConfirm(String consignmentId, HttpServletRequest request, HttpServletResponse response) throws ParseException {
        Map<String, Object> stringObjectMap = this.thisUserLabDetails();
        downloadFileUtils.generateAndDownload(request, response, consignmentId);
    }

    /**
     * 鉴定书下载
     *
     * @param request
     * @param caseId
     * @return
     */
    @RequestMapping("/dowmFileExpertiseRepor")
    @ResponseBody
    public Map<String, Object> generateIdentifyBook(HttpServletRequest request, String caseId) {
        Map<String, Object> result = new HashMap<>();
        String tandemStatus = "0";
        result = downloadFileUtils.generateIdentifyBook(request, caseId,tandemStatus);
        return result;
    }


    /**
     * 入库单下载
     * @param request
     * @param caseId
     * @return
     */
    @RequestMapping("/generateInboundOrder")
    @ResponseBody
    public void generateInboundOrder(HttpServletRequest request, HttpServletResponse response, String caseId,String consignmentId) {

        if(null != consignmentId){
            LimsCaseInfo caseInfo = limsCaseInfoService.selectByConsignmentId(consignmentId);
            if (null != caseInfo.getCaseId()){
                caseId = caseInfo.getCaseId();
            }
        }

        downloadFileUtils.generateInboundOrder(request, response, caseId);
    }

    /**
     * 判断扩增记录是否为空
     *
     * @param request
     * @param caseId
     * @return
     */
    @RequestMapping("/isdowmFileAmplificationRecord")
    @ResponseBody
    public Map<String, Object> isdowmFileAmplificationRecord(HttpServletRequest request, HttpServletResponse response, String caseId) {
        Map<String, Object> pcrmap = new HashMap<>();
        //根据案件id查取检材
        List<LimsSampleInfoDna> limsSampleInfoDnaList = sampleInfoDnaService.selectByCaseId(caseId);
        List<LabPcrSample> pcrSampleList = new ArrayList<>();
        for (LimsSampleInfoDna lsid : limsSampleInfoDnaList) {
            List<LabPcrSample> labPcrSampleList = labPcrSampleService.selectBySampleId(lsid.getSampleId());
            //把检材的扩增信息放入到list集合中
            if (ListUtils.isNotNullAndEmptyList(labPcrSampleList)) {
                pcrSampleList.addAll(labPcrSampleList);
            }
        }

        //根据案件id获取实验信息
        List<LabPcrInfo> labPcrInfoList = labPcrInfoService.selectByCaseId(caseId);
        //如果扩增信息集合不为空
        if (pcrSampleList.size() > 0) {
            pcrmap.put("status", "success");
            pcrmap.put("labPcrInfoList", labPcrInfoList);
        } else {
            pcrmap.put("status", "error");
        }
        return pcrmap;
    }
    /**
     * 下载样本流转记录表下载
     *
     * @param request
     * @param response
     * @param consignmentId
     * @throws ParseException
     */
    @RequestMapping("/transferRecordBtn")
    public void circulationRecord(HttpServletRequest request, HttpServletResponse response,
                                  String consignmentId) throws ParseException {
        downloadFileUtils.circulationRecord(request,response,consignmentId);
    }
    /**
     * 扩增记录下载
     *
     * @param request
     * @param caseInfo
     * @return
     */
    @RequestMapping("/dowmFileAmplificationRecord")
    public void dowmFileAmplificationRecord(HttpServletRequest request, HttpServletResponse response, LimsCaseInfo caseInfo, String pcrId) {
        Map<String, Object> pcrmap = new HashMap<>();

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        String userOrgId = operateUser.getOrgId();
        //将当前用户org_id设置进query
        if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
            userOrgId = "110230000000";
        }


        List<LabPcrSample> labPcrSampleList = labPcrSampleService.selectByPcrId(pcrId);
        List<LabPcrSample> pcrSampleList = new ArrayList<>();
        int countindex = 0;
        //把检材的扩增信息放入到list集合中
        if (ListUtils.isNotNullAndEmptyList(labPcrSampleList)) {
            for (LabPcrSample pcrSample : labPcrSampleList) {
                if (caseInfo.getCaseId().equals(pcrSample.getCaseId())) {
                    pcrSampleList.add(countindex, pcrSample);
                    countindex++;
                }
            }
        }

        LabPcrInfo labPcrInfo = labPcrInfoService.selectByPrimaryKey(pcrId);

        //如果扩增信息集合不为空
        if (pcrSampleList.size() > 0) {
            //根据prcid查询扩增主表
            pcrmap.put("labPcrInfoRecord", labPcrInfo);

            if(labPcrInfo != null){
                //试剂名称
                String pcrReagent = labPcrInfo.getPcrReagent();
                //扩增体系
                String pcrSystem =labPcrInfo.getPcrSystem();

                ExperimentalParameter experimentalParameter = new ExperimentalParameter();
                //Constants.EXPERIMENTAL_STAGE_02

                experimentalParameter.setPanelName(pcrReagent);
                experimentalParameter.setParameterName(pcrSystem);
                experimentalParameter.setExperimentalStage(Constants.EXPERIMENTAL_STAGE_02);
                experimentalParameter.setOrgId(userOrgId);

                List<ExperimentalParameter> experimentalParameterlist = experimentalParameterService.selectListValue(experimentalParameter);
                if(experimentalParameterlist.size() >0){
                    String parameterValue = experimentalParameterlist.get(0).getParameterValue();
                    String panelName = experimentalParameterlist.get(0).getPanelName();

                    Map map = (Map) JSONObject.parse(parameterValue);
                    String primer = (String) map.get("Primer");
                    String buffer = (String) map.get("Buffer");
                    String taqE = (String) map.get("TaqE");
                    String template = (String) map.get("Template");
                    String h2O = (String) map.get("H2O");
                    String mgCl2 = (String) map.get("MgCl2");
                    String dNTP = (String) map.get("dNTP");
//                    pcrmap.put("primer", StringUtils.isBlank(primer) ? "" : primer);
//                    pcrmap.put("buffer", StringUtils.isBlank(buffer) ? "" : buffer);
//                    pcrmap.put("template", StringUtils.isBlank(template) ? "" : template);
//                    pcrmap.put("h2O", StringUtils.isBlank(h2O) ? "" : h2O);

                    if(StringUtils.isNotBlank(panelName)){
                        if("PP21".equals(panelName)){
                            pcrmap.put("primer", "2.0");
                            pcrmap.put("buffer", "2.0");
                            pcrmap.put("template", "1.0");
                            pcrmap.put("h2O", "5.0");

                            pcrmap.put("primer2", "2.0");
                            pcrmap.put("buffer2", "2.0");
                            pcrmap.put("template2", "X");
                            pcrmap.put("h2O2", "6.0");
                        }else{
                            pcrmap.put("primer", "2.0");
                            pcrmap.put("buffer", "4.0");
                            pcrmap.put("template", "1.0");
                            pcrmap.put("h2O", "3.0");

                            pcrmap.put("primer2", "2.0");
                            pcrmap.put("buffer2", "4.0");
                            pcrmap.put("template2", "X");
                            pcrmap.put("h2O2", "4.0");
                        }
                    }

                }
            }

            //页眉
            if(labPcrInfo != null){
                if(StringUtils.isNotEmpty(labPcrInfo.getOrgId())){
                    ExperimentalParameter exp = new ExperimentalParameter();
                    exp.setOrgId(labPcrInfo.getOrgId());
                    exp.setExperimentalType("PCR_RECORD");//扩增记录
                    List<ExperimentalParameter> experimentalParameters = experimentalParameterService.selectList(exp);
                    if(ListUtils.isNotNullAndEmptyList(experimentalParameters)){
                        ExperimentalParameter experimentalParameter = experimentalParameters.get(0);
                        String orgNameSp = experimentalParameter.getParameterName();
                        pcrmap.put("orgNameSp", StringUtils.isBlank(orgNameSp) ? "" : orgNameSp);
                    }
                }
            }

            LinkedHashSet<List<LabPcrSample>> allList = new LinkedHashSet<List<LabPcrSample>>();
            if (null != pcrSampleList){
                for (LabPcrSample bean: pcrSampleList) {
                    List<LabPcrSample> greplist = new ArrayList<LabPcrSample>();
                    for (LabPcrSample bean2: pcrSampleList){
                        if (bean.getCaseId().equals(bean2.getCaseId()) ){
                            greplist.add(bean2);
                        }
                    }
                    allList.add(greplist);
                }
            }

            List<PcrListModel> pcrListModelsList = new ArrayList<>();
            if(allList.size()>0 && null != allList){
                for (List<LabPcrSample> samples : allList){
                    PcrListModel model = new PcrListModel();
                    model.setLabPcrSampleList(samples);
                    pcrListModelsList.add(model);
                }
            }

            List<PcrListModel> labList = new ArrayList<>();
            if (ListUtils.isNotNullAndEmptyList(pcrListModelsList)){
                for (PcrListModel bean: pcrListModelsList) {
                    //将数据分页
                    List<LabPcrSample> tweLabSampleList = new ArrayList<>();
                    List<LabPcrSample> labSampleList = new ArrayList<>();
                    if (bean.getLabPcrSampleList().size() > 14) {
                        List<List<LabPcrSample>> listList = new ArrayList<>();
                        tweLabSampleList.addAll(bean.getLabPcrSampleList().subList(14, bean.getLabPcrSampleList().size()));
                        int sizePage = (int) Math.ceil((double) tweLabSampleList.size() / (double) 19);
                        for (int i = 0; i < sizePage; i++) {
                            int j;
                            if (i < sizePage - 1) {
                                j = (i + 1) * 19;
                            } else {
                                j = tweLabSampleList.size();
                            }
                            int k = i;
                            if (i > 0 && i < sizePage) {
                                k = i * 19;
                            }
                            if (j <= tweLabSampleList.size()) {
                                List<LabPcrSample> oneLabExtractSampleList = new ArrayList<>();
                                oneLabExtractSampleList.addAll(tweLabSampleList.subList(k, j));
                                if (ListUtils.isNotNullAndEmptyList(oneLabExtractSampleList)){
                                    if (oneLabExtractSampleList.size()<19){
                                        LabPcrSample labPcrSample = new LabPcrSample();
                                        labPcrSample.setPrimer("  ");
                                        labPcrSample.setBuffer("  ");
                                        labPcrSample.setTaqe("  ");
                                        labPcrSample.setTemplate("  ");
                                        labPcrSample.setH2o("  ");
                                        labPcrSample.setMgcl2("  ");
                                        labPcrSample.setDntp("  ");
                                        int size = 19 - oneLabExtractSampleList.size();
                                        for (int x = 0; x <  size ; x++) {
                                            oneLabExtractSampleList.add(labPcrSample);
                                        }
                                    }
                                }
                                listList.add(oneLabExtractSampleList);
                                bean.setListList(listList);
                            }

                        }
//                labSampleList = bean.subList(0, 14);
//                    pcrmap.put("labSampleList", listList);
//                pcrmap.put("pcrSampleListRecord", labSampleList);
                        bean.setLabPcrSampleList(bean.getLabPcrSampleList().subList(0, 14));
                        labList.add(bean);
                    } else {
                        LabPcrSample labPcrSample = new LabPcrSample();
                        labPcrSample.setPrimer("  ");
                        labPcrSample.setBuffer("  ");
                        labPcrSample.setTaqe("  ");
                        labPcrSample.setTemplate("  ");
                        labPcrSample.setH2o("  ");
                        labPcrSample.setMgcl2("  ");
                        labPcrSample.setDntp("  ");
                        labSampleList.addAll(bean.getLabPcrSampleList());
                        int size = 14 - bean.getLabPcrSampleList().size();
                        for (int i = 0; i <  size ; i++) {
                            bean.getLabPcrSampleList().add(labPcrSample);
                        }
                        labList.add(bean);
//                pcrmap.put("pcrSampleListRecord", labSampleList);
                    }
                }
            }

            pcrmap.put("allList",labList);

            /**
             * 扩增记录实验室编号--liuchang
             */
            if (StringUtils.isNotBlank(operateUser.getOrgId())) {
                List<QcNoSettings> newQcNoSettings = qcNoSettingsService.selectByLab(operateUser.getOrgId());
                if (newQcNoSettings != null && !newQcNoSettings.isEmpty()) {
                    Calendar date = Calendar.getInstance(); //获取年份
                    for (QcNoSettings qcNoSettings : newQcNoSettings) {
                        if (qcNoSettings.getDictCode().equals(Constants.AMPLIFIED_RECORDS_NO)) {
                            String amplifiedRecordsNo = qcNoSettings.getLabServerHeader() + "-" + String.valueOf(date.get(Calendar.YEAR)) + "-" + qcNoSettings.getBarcode();
                            pcrmap.put("amplifiedRecordsNo", StringUtils.isBlank(amplifiedRecordsNo) ? "" : amplifiedRecordsNo);
                        }
                    }
                }
            }
            try {
                Configuration cfg = new Configuration();
                cfg.setDefaultEncoding("UTF-8");
                cfg.setClassForTemplateLoading(this.getClass(), "/templates");
                //获取模板
                String ftlName = "pcrRecordBook.ftl";

                Template tmp = cfg.getTemplate(ftlName, "UTF-8");

                String filename = "DNA扩增记录表" + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".doc";

                response.setCharacterEncoding("UTF-8");
                //文件头，导出的文件名
                response.setHeader("Content-disposition", "attachment;filename=" + new String(filename.getBytes("GBK"), "ISO-8859-1"));
                //类型
                response.setContentType("application/x-msdownload");
                tmp.process(pcrmap, response.getWriter());
            } catch (Exception ex) {
                logger.error("下载错误！", ex);
            }

        }
    }

    /**
     * 判断电泳分离记录表是否为空
     *
     * @param request
     * @param response
     * @param caseId
     */
    @RequestMapping("/isdowmFileloadSample")
    @ResponseBody
    public Map<String, Object> isdowmFileloadSample(HttpServletRequest request, HttpServletResponse response, String caseId) {

        Map<String, Object> pcrmap = new HashMap<>();
        //根据案件id查取检材
        List<LimsSampleInfoDna> limsSampleInfoDnaList = sampleInfoDnaService.selectByCaseId(caseId);

        List<LabSySample> sySampleList = new ArrayList<>();
        int countindex = 0;
        for (LimsSampleInfoDna lsid : limsSampleInfoDnaList) {
            List<LabSySample> labSyrSampleList = LabSySampleService.selectLabSySampleBySampleId(lsid.getSampleId());
            //把检材的扩增信息放入到list集合中
            if (ListUtils.isNotNullAndEmptyList(labSyrSampleList)) {
                sySampleList.add(countindex, labSyrSampleList.get(0));
                countindex++;
            }
        }

        List<LabSyInfo> labSyInfoList = labSyInfoService.selectByCaseId(caseId);
        //如果扩增信息集合不为空
        if (sySampleList.size() > 0) {
            pcrmap.put("status", "success");
            pcrmap.put("labSyInfoList", labSyInfoList);
        } else {
            pcrmap.put("status", "error");
        }
        return pcrmap;
    }

    /**
     * 电泳分离记录表下载
     *
     * @param request
     * @param response
     * @param caseId
     */
    @RequestMapping("/dowmFileloadSample")
    public void dowmFileloadSample(HttpServletRequest request, HttpServletResponse response, String caseId, String syId) {

        Map<String, Object> pcrmap = new HashMap<>();

        List<LabSySample> sySampleList = new ArrayList<>();
        int countindex = 0;
        List<LabSySample> labSySampleList  = LabSySampleService.selectBySyId(syId);
        //把检材的扩增信息放入到list集合中
        if (ListUtils.isNotNullAndEmptyList(labSySampleList)) {
            for (LabSySample labSySample :labSySampleList) {
                if (caseId.equals(labSySample.getCaseId())) {
                    sySampleList.add(countindex, labSySample);
                    countindex++;
                }
            }
        }
        if (sySampleList.size() > 0) {
            LabSyInfo labSyInfo = labSyInfoService.selectByPrimaryKey(syId);
            pcrmap.put("labSyInfoRecord", labSyInfo);

            if (labSyInfo != null) {
                if (StringUtils.isNotBlank(labSyInfo.getSyString())) {
                    Map<String, Object> mapSyString = (Map) JSON.parse(labSyInfo.getSyString());

                    labSyInfo.setInternalStandard(mapSyString.get("neiBiao").toString());
                    String quantity = mapSyString.get("quantity").toString();
                    //甲酰胺
                    String jiaXianAnBatchCode = mapSyString.get("jiaXianAnBatchCode").toString();
                    String jiaXianAnInstockDate = mapSyString.get("jiaXianAnInstockDate").toString();

                    pcrmap.put("jiaXianAnBatchCode", jiaXianAnBatchCode);
                    pcrmap.put("jiaXianAnInstockDate", jiaXianAnInstockDate);
                    //内标
                    String neiBiaoBatchCode = mapSyString.get("neiBiaoBatchCode").toString();
                    String neiBiaoInstockDate = mapSyString.get("neiBiaoInstockDate").toString();

                    pcrmap.put("neiBiaoBatchCode", neiBiaoBatchCode);
                    pcrmap.put("neiBiaoInstockDate", neiBiaoInstockDate);
                    //毛细管
                    String maoXiGuanBatchCode = mapSyString.get("maoXiGuanBatchCode").toString();
                    String maoXiGuanInstockDate = mapSyString.get("maoXiGuanInstockDate").toString();

                    pcrmap.put("maoXiGuanBatchCode", maoXiGuanBatchCode);
                    pcrmap.put("maoXiGuanInstockDate", maoXiGuanInstockDate);
                    //胶液
                    String jiaoYeBatchCode = mapSyString.get("jiaoYeBatchCode").toString();
                    String jiaoYeInstockDate = mapSyString.get("jiaoYeInstockDate").toString();

                    pcrmap.put("jiaoYeBatchCode", jiaoYeBatchCode);
                    pcrmap.put("jiaoYeInstockDate", jiaoYeInstockDate);

                    double quantityCount = 0;
                    if (StringUtils.isNotBlank(quantity)) {
                        quantityCount = Double.valueOf(quantity);
                    }
                    double formamide = 0;
                    if (StringUtils.isNotBlank(labSyInfo.getFormamide())) {
                        formamide = Double.valueOf(labSyInfo.getFormamide());
                    }
                    double internalStandardUl = 0;
                    if (StringUtils.isNotBlank(labSyInfo.getInternalStandardUl())) {
                        internalStandardUl = Double.valueOf(labSyInfo.getInternalStandardUl());
                    }

                    pcrmap.put("formamideCount", quantityCount * formamide);
                    pcrmap.put("internalCount", quantityCount * internalStandardUl);
                }
            }
            //将数据分页
            List<LabSySample> tweLabSampleList = new ArrayList<>();
            List<LabSySample> labSampleList = new ArrayList<>();
            if (sySampleList.size() > 15) {
                List<List<LabSySample>> listList = new ArrayList<>();
                tweLabSampleList = sySampleList.subList(15, sySampleList.size());
                int sizePage = (int) Math.ceil((double) tweLabSampleList.size() / (double) 19);
                for (int i = 0; i < sizePage; i++) {
                    int j;
                    if (i < sizePage - 1) {
                        j = (i + 1) * 19;
                    } else {
                        j = tweLabSampleList.size();
                    }
                    int k = i;
                    if (i > 0 && i < sizePage) {
                        k = i * 19;
                    }
                    if (j <= tweLabSampleList.size()) {
                        List<LabSySample> oneLabExtractSampleList = tweLabSampleList.subList(k, j);
                        listList.add(oneLabExtractSampleList);
                    }

                }
                labSampleList = sySampleList.subList(0, 15);
                pcrmap.put("SySamplesList", listList);
                pcrmap.put("sySampleListRecord", labSampleList);
            } else {
                LabSySample labPcrSample = new LabSySample();
                labPcrSample.setSamplePostion("  ");
                labPcrSample.setSampleName("  ");
                labPcrSample.setSampleNo("  ");
                labSampleList.addAll(sySampleList);
                for (int i = 0; i < 15 - sySampleList.size(); i++) {
                    labSampleList.add(labPcrSample);
                }
                pcrmap.put("sySampleListRecord", labSampleList);
            }
            /**
             * 类说明：电泳记录编号--liuchang
             */
            Subject subject = SecurityUtils.getSubject();
            LoaUserInfo user=(LoaUserInfo) subject.getPrincipal();
            if (StringUtils.isNotBlank(user.getOrgId())) {
                List<QcNoSettings> newQcNoSettings = qcNoSettingsService.selectByLab(user.getOrgId());
                Calendar date = Calendar.getInstance(); //获取年份
                if (newQcNoSettings != null && !newQcNoSettings.isEmpty()) {
                    for (QcNoSettings qcNoSettings : newQcNoSettings) {
                        if (qcNoSettings.getDictCode().equals(Constants.ELECTROPHORETIC_RECORDING_NO)) {
                            String electrophoreticRecordingNo = qcNoSettings.getLabServerHeader() + "-" + String.valueOf(date.get(Calendar.YEAR)) + "-" + qcNoSettings.getBarcode();
                            pcrmap.put("electrophoreticRecordingNo", StringUtils.isBlank(electrophoreticRecordingNo) ? "" : electrophoreticRecordingNo);
                        }
                    }
                }
            }
            try {
                Configuration cfg = new Configuration();
                cfg.setDefaultEncoding("UTF-8");
                cfg.setClassForTemplateLoading(this.getClass(), "/templates");
                //获取模板
                String ftlName = "syRecordBook.ftl";

                Template tmp = cfg.getTemplate(ftlName, "UTF-8");

                String filename = "DNA上样记录表" + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".doc";

                response.setCharacterEncoding("UTF-8");
                //文件头，导出的文件名
                response.setHeader("Content-disposition", "attachment;filename=" + new String(filename.getBytes("GBK"), "ISO-8859-1"));
                //类型
                response.setContentType("application/x-msdownload");
                tmp.process(pcrmap, response.getWriter());
            } catch (Exception ex) {
                logger.error("下载错误！", ex);
            }
        }
    }

    /**
     * 判断生成提取实验记录表是否为空
     *
     * @param
     * @return
     */
    @RequestMapping("/isdowmFileExtractBtn")
    @ResponseBody
    public Map<String, Object> recordTable(HttpServletRequest request, HttpSession session, HttpServletResponse response, String caseId) {
        Map<String, Object> params = new HashMap<String, Object>();
        //根据案件id查取检材
        List<LimsSampleInfoDna> limsSampleInfoDnaList = sampleInfoDnaService.selectByCaseId(caseId);

        List<LabExtractSample> extractSampleList = new ArrayList<>();
        int countindex = 0;
        for (LimsSampleInfoDna lsid : limsSampleInfoDnaList) {
            List<LabExtractSample> labExtractSampleLists = labExtractSampleService.selectLabExtractSampleBySampleId(lsid.getSampleId());
            //把检材的扩增信息放入到list集合中
            if (ListUtils.isNotNullAndEmptyList(labExtractSampleLists)) {
                extractSampleList.add(countindex, labExtractSampleLists.get(0));
                countindex++;
            }
        }

        //根据案件id查询实验信息
        List<LabExtractInfo> labExtractInfoList = labExtractInfoService.selectByCaseId(caseId);
        //如果扩增信息集合不为空
        if (extractSampleList.size() > 0) {
            params.put("status", "success");
            params.put("labExtractInfoList", labExtractInfoList);
        } else {
            params.put("status", "error");
        }
        return params;
    }

    /**
     * 生成提取实验记录表下载
     *
     * @param
     * @return
     */
    @RequestMapping("/dowmFileExtractBtn")
    public void dowmFileExtractBtn(HttpServletRequest request, HttpSession session, HttpServletResponse response, String caseId, String extractId) {
        Map<String, Object> params = new HashMap<String, Object>();

        DictItem dictItem = new DictItem();

        //提取实验方法List
        dictItem.setDictTypeCode(Constants.EXTRACTION_TEST_METHOD);
        List<DictItem> extractTestMethodList = DictUtil.getDictList(dictItem);
        params.put("extractTestMethodList", extractTestMethodList);

        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if (operateUser == null) {

        }
        AmPersonalInfo amPersonalInfo = amPersonalInfoService.queryAmPersonalInfo(operateUser.getPersonalId());
        if (StringUtils.isNotBlank(amPersonalInfo.getOrgId()) && amPersonalInfo.getOrgId().contains("110023")) {
            amPersonalInfo.setOrgId("110230000000");
        } else {
            amPersonalInfo.setOrgId(operateUser.getOrgId());
        }

        //获取单位信息
        OrgInfo orgInfo = orgInfoService.selectByPrimaryKey(operateUser.getOrgId());
        String oraliQualification = null;
        if (operateUser.getOrgId() != null) {
            oraliQualification = orgInfo.getOrgQualification();
        }

        //获取当前用户的id
        String userOrgId = operateUser.getOrgId();
        //将当前用户org_id设置进query
        if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
            userOrgId = "110230000000";
        }


        List<LabExtractSample> extractSampleList = labExtractSampleService.selectByExtractId(extractId);
        List<LabExtractSample> labExtractSampleLists = new ArrayList<>();
        int countindex = 0;
        //把检材的提取信息放入到list集合中
        boolean isSpotRecord = false;
        if (ListUtils.isNotNullAndEmptyList(extractSampleList)) {
            String sampleType = "";
            for (LabExtractSample extractSample : extractSampleList) {
                if (caseId.equals(extractSample.getCaseId())) {
                    List<LimsSampleInfoDna> sampleInfoDnaList = limsSampleInfoDnaService.selectBySampleId(extractSample.getSampleId());
                    LimsSampleInfoDna sampleInfoDna = new LimsSampleInfoDna();
                    if (ListUtils.isNotNullAndEmptyList(sampleInfoDnaList)) {
                        sampleInfoDna = sampleInfoDnaList.get(0);
                        extractSample.setLimsSampleInfoDna(sampleInfoDna);
                        if (Constants.SAMPLE_TYPE_02.equals(sampleInfoDna.getSampleType())) {
                            sampleType += sampleInfoDna.getSampleType() + "、";
                        }
                    }
                    labExtractSampleLists.add(countindex, extractSample);
                    countindex++;
                }
            }
            //判断此记录表是否为精斑提取记录表
            if (StringUtils.isNotBlank(sampleType) && sampleType.contains("、")) {
                String[] sampleTypeStr = sampleType.split("、");
                if (sampleTypeStr.length == extractSampleList.size()) {
                    isSpotRecord = true;
                }
            }
        }

        LabExtractInfo labExtractInfo = labExtractInfoService.selectByPrimaryKey(extractId);

        //获取设备
        List<EquipmentNameInfo> equipmentNameInfoArrayList = new ArrayList<>();
        if (labExtractInfo.getEquipment() != null) {
            String[] equipmentNo = labExtractInfo.getEquipment().split(",");
            for (int i = 0; i < equipmentNo.length; i++) {
                List<EquipmentNameInfo> equipmentNameInfo = equipmentNameInfoService.selectEquipmentNo(equipmentNo[i]);

                if (equipmentNameInfo != null) {
                    for (int j = 0; j < equipmentNameInfo.size(); j++) {
                        if (equipmentNameInfo.get(j).getOrgId() == null && amPersonalInfo.getOrgId() == null) {
                            equipmentNameInfoArrayList.add(equipmentNameInfo.get(j));
                        } else if (equipmentNameInfo.get(j).getOrgId().equals(amPersonalInfo.getOrgId())) {
                            equipmentNameInfoArrayList.add(equipmentNameInfo.get(j));
                        }
                    }
                }
            }
            params.put("equipmentNameInfoArrayList", equipmentNameInfoArrayList);
        }

        //获取试剂
        List<ReagentInfo> panelList = new ArrayList<>();
        if (labExtractInfo.getKit() != null) {
            String[] kitNo = labExtractInfo.getKit().split(",");
            List<ReagentInfo> nameList = reagentInfoService.selectAll();
            for (int i = 0; i < nameList.size(); i++) {
                for (int j = 0; j < kitNo.length; j++) {
                    if (nameList.get(i).getId().equals(kitNo[j])) {
                        panelList.add(nameList.get(i));
                    }
                }
            }
            if (panelList.size() > 0) {
                for (int k = 0; k < panelList.size(); k++) {
                    ReagentOutgoInfo reagentOutgoInfo = new ReagentOutgoInfo();
                    reagentOutgoInfo.setReagentId(panelList.get(k).getId());
                    reagentOutgoInfo.setOrgId(panelList.get(k).getOrgId());
                    List<ReagentOutgoInfo> reagentOutgoInfoList = reagentOutgoInfoService.selectreagentOutgoList(reagentOutgoInfo);
                    if (reagentOutgoInfoList.size() > 0) {
                        if(null != reagentOutgoInfoList.get(0).getBatchNumber()){
                            panelList.get(k).setBatchNumber(reagentOutgoInfoList.get(0).getBatchNumber());
                        }

                        if(null != reagentOutgoInfoList.get(0).getEffectiveDatetime()){
                            panelList.get(k).setValidityTime(reagentOutgoInfoList.get(0).getEffectiveDatetime());
                        }
                    }
                }
            }
            params.put("panelList", panelList);
        }

        //试剂信息
        ReagentInfo reagentInfo = new ReagentInfo();
        reagentInfo.setExperimentalStage(Constants.EXPERIMENTAL_STAGE_01);
        reagentInfo.setOrgId(userOrgId);
        List<ReagentInfo> reagentInfoList = reagentInfoService.selectList(reagentInfo);
        params.put("reagentInfoList", reagentInfoList);

        //试剂
        if (labExtractInfo.getKit() != null && labExtractInfo.getKit() != "") {
            String[] kits = labExtractInfo.getKit().split(",");
            if( kits !=null && kits.length > 0) {

            }
        }

        //设备
        if (labExtractInfo.getEquipment() != null && labExtractInfo.getEquipment() != "") {
            String[] equi = labExtractInfo.getEquipment().split(",");
            if( equi !=null && equi.length > 0) {
                String equipment = equi[0];
                List<EquipmentTypeInfo> equipmentTypeInfoList = equipmentTypeInfoService.selectAllList();
                for (int i = 0; i < equipmentTypeInfoList.size(); i++) {
                    if (equipmentTypeInfoList.get(i).getId().equals(equipment)) {
                        labExtractInfo.setEquipment(equipmentTypeInfoList.get(i).getEquipmentTypeName());
                    }
                }
            }
        }

        labExtractInfo.setExtractDatetime(labExtractInfo.getCreateDatetime());
        params.put("labExtractInfo", labExtractInfo);

        if (labExtractInfo != null) {
            if (StringUtils.isNotEmpty(labExtractInfo.getOrgId())) {
                ExperimentalParameter exp = new ExperimentalParameter();
                exp.setOrgId(labExtractInfo.getOrgId());
                exp.setExperimentalType("CONVENTIONAL");//常规提取
                List<ExperimentalParameter> experimentalParameters = experimentalParameterService.selectList(exp);
                if (ListUtils.isNotNullAndEmptyList(experimentalParameters)) {
                    ExperimentalParameter experimentalParameter = experimentalParameters.get(0);
                    String orgNameSp = experimentalParameter.getParameterName();
                    params.put("orgNameSp", StringUtils.isBlank(orgNameSp) ? "" : orgNameSp);
                }
            }
        }


        if (labExtractSampleLists.size() > 0) {
            for (int j = 0; j < labExtractSampleLists.size(); j++) {
                if (StringUtils.isNotBlank(labExtractSampleLists.get(j).getExtractMethod())) {
                    UseInstruments useInstrumentsEntity = new UseInstruments();
                    useInstrumentsEntity.setMethodName(labExtractSampleLists.get(j).getExtractMethod());
                    useInstrumentsEntity.setOrgCode(operateUser.getOrgId());
                    List<UseInstruments> useInstrumentsList = useInstrumentsService.findByExtractMentod(useInstrumentsEntity);
                    if (useInstrumentsList.size() > 0) {
                        UseInstruments useInstruments = useInstrumentsList.get(0);
                        labExtractSampleLists.get(j).setLeave(useInstruments.getLeave());
                        labExtractSampleLists.get(j).setLeaveTwo(useInstruments.getLeavetwo());
                        labExtractSampleLists.get(j).setBath(useInstruments.getBath());
                        labExtractSampleLists.get(j).setBathTwo(useInstruments.getBathtwo());
                        labExtractSampleLists.get(j).setDry(useInstruments.getDry());
                        labExtractSampleLists.get(j).setDryTwo(useInstruments.getDrytwo());
                        labExtractSampleLists.get(j).setShake(useInstruments.getShake());
                        labExtractSampleLists.get(j).setEarthquake(useInstruments.getEarthquake());
                    }
                }
            }
        }

        List<LabExtractSample> tweLabExtractSampleList = new ArrayList<>();
        List<LabExtractSample> labExtractSampleList = new ArrayList<>();
        List<List<LabExtractSample>> listList = new ArrayList<>();
        //填充13条记录
        LabExtractSample labExtractSample = new LabExtractSample();
        labExtractSample.setExtractMethod("  ");
        labExtractSample.setLeave("  ");
        labExtractSample.setBath("  ");
        labExtractSample.setDry("  ");
        labExtractSample.setShake("  ");
        labExtractSample.setEarthquake("  ");
        labExtractSample.setLimsSampleInfoDna(new LimsSampleInfoDna());
        labExtractSample.getLimsSampleInfoDna().setSampleNo("   ");
        labExtractSample.getLimsSampleInfoDna().setSampleName("  ");

        LinkedHashSet<List<LabExtractSample>> allList = new LinkedHashSet<List<LabExtractSample>>();
                /*if (null != labExtractSampleLists) {
                    for (LabExtractSample bean : labExtractSampleLists) {
                        List<LabExtractSample> greplist = new ArrayList<LabExtractSample>();
                        for (LabExtractSample bean2 : labExtractSampleLists) {
                            if (bean.getLimsSampleInfoDna().getCaseId().equals(bean2.getLimsSampleInfoDna().getCaseId())) {
                                System.out.println("bena1" + bean.getLimsSampleInfoDna().getCaseId() + "-samp-" + bean.getId());
                                System.out.println("bena2" + bean2.getLimsSampleInfoDna().getCaseId() + "-samp-" + bean2.getId());
                                greplist.add(bean2);
                            }
                        }
                        allList.add(greplist);
                    }
                }*/
        allList.add(labExtractSampleLists);
        params.put("allList", allList);

        for (List<LabExtractSample> bean : allList) {

            if (bean.size() > 14) {
                tweLabExtractSampleList = bean.subList(14, bean.size());

                int sizePage = (int) Math.ceil((double) tweLabExtractSampleList.size() / (double) 21);
                for (int i = 0; i < sizePage; i++) {
                    int j;
                    if (i < sizePage - 1) {
                        j = (i + 1) * 21;
                    } else {
                        j = tweLabExtractSampleList.size();
                    }
                    int k = i;
                    if (i > 0 && i < sizePage) {
                        k = i * 21;
                    }
                    if (j <= tweLabExtractSampleList.size()) {
                        List<LabExtractSample> oneLabExtractSampleList = tweLabExtractSampleList.subList(k, j);
                        listList.add(oneLabExtractSampleList);
                    }

                }

                labExtractSampleList = bean.subList(0, 14);
                params.put("labExtractSampleList", labExtractSampleList);
            } else {
                int longs = 14 - bean.size();
                for (int i = 0; i < longs; i++) {
                    bean.add(labExtractSample);
                }
                params.put("labExtractSampleList", bean);
            }
        }
        params.put("listList", listList);

        //自动提取实验检材列表
        List<LabListModel> labListModelList = new ArrayList<>();
        if(allList.size()>0 && null != allList){
            for (List<LabExtractSample> samples : allList){
                LabListModel model = new LabListModel();
                model.setLabExtractSampleList(samples);
                labListModelList.add(model);
            }
        }

        List<LabListModel> labList = new ArrayList<>();
        for (LabListModel bean : labListModelList) {
            tweLabExtractSampleList = new ArrayList<>();
            labExtractSampleList = new ArrayList<>();
            if (bean.getLabExtractSampleList().size() > 14) {
                listList = new ArrayList<>();
                tweLabExtractSampleList = bean.getLabExtractSampleList().subList(14, bean.getLabExtractSampleList().size());
                int sizePage = (int) Math.ceil((double) tweLabExtractSampleList.size() / (double) 20);
                for (int i = 0; i < sizePage; i++) {
                    int j;
                    if (i < sizePage - 1) {
                        j = (i + 1) * 20;
                    } else {
                        j = tweLabExtractSampleList.size();
                    }
                    int k = i;
                    if (i > 0 && i < sizePage) {
                        k = i * 20;
                    }
                    if (j <= tweLabExtractSampleList.size()) {
                        List<LabExtractSample> oneLabExtractSampleList = new ArrayList<>();
                        oneLabExtractSampleList.addAll(tweLabExtractSampleList.subList(k, j));
                        if (ListUtils.isNotNullAndEmptyList(oneLabExtractSampleList)){
                            if (oneLabExtractSampleList.size()<20){
                                labExtractSample = new LabExtractSample();
                                labExtractSample.setExtractMethod("  ");
                                labExtractSample.setLeave("  ");
                                labExtractSample.setBath("  ");
                                labExtractSample.setDry("  ");
                                labExtractSample.setShake("  ");
                                labExtractSample.setEarthquake("  ");
                                labExtractSample.setLimsSampleInfoDna(new LimsSampleInfoDna());
                                labExtractSample.getLimsSampleInfoDna().setSampleNo("   ");
                                labExtractSample.getLimsSampleInfoDna().setSampleName("  ");
                                int size = 20 - oneLabExtractSampleList.size();
                                for (int x = 0; x <  size ; x++) {
                                    oneLabExtractSampleList.add(labExtractSample);
                                }
                            }
                        }
                        listList.add(oneLabExtractSampleList);
                        bean.setListList(listList);
                    }
                }
                bean.setLabExtractSampleList(bean.getLabExtractSampleList().subList(0, 14));
                labList.add(bean);
            } else {
                labExtractSample = new LabExtractSample();
                labExtractSample.setExtractMethod("  ");
                labExtractSample.setLeave("  ");
                labExtractSample.setBath("  ");
                labExtractSample.setDry("  ");
                labExtractSample.setShake("  ");
                labExtractSample.setEarthquake("  ");
                labExtractSample.setLimsSampleInfoDna(new LimsSampleInfoDna());
                labExtractSample.getLimsSampleInfoDna().setSampleNo("   ");
                labExtractSample.getLimsSampleInfoDna().setSampleName("  ");
                int longs = 14 - bean.getLabExtractSampleList().size();
                for (int i = 0; i < longs; i++) {
                    bean.getLabExtractSampleList().add(labExtractSample);
                }
                labList.add(bean);
            }
        }

        params.put("labList", labList);
        /**
         * 常规提取记编号--liuchang
         */
       if (StringUtils.isNotBlank(operateUser.getOrgId())) {
           List<QcNoSettings> qcNoSettingsList = qcNoSettingsService.selectByLab(operateUser.getOrgId());
           Calendar date = Calendar.getInstance(); //获取年份
           if (qcNoSettingsList != null && !qcNoSettingsList.isEmpty()) {
               for (QcNoSettings qcNoSettings : qcNoSettingsList) {
                   if (qcNoSettings.getDictCode().equals(Constants.ROUTINE_EXTRACTION_RECORDS_NO)) {
                       String routineExtractionNo = qcNoSettings.getLabServerHeader() + "-" + String.valueOf(date.get(Calendar.YEAR)) + "-" + qcNoSettings.getBarcode();
                       params.put("routineExtractionNo", StringUtils.isBlank(routineExtractionNo) ? "" : routineExtractionNo);
                   }
               }
           }
       }

        /**
         * 精斑记录表参数--liuchang
         */
        if (isSpotRecord) {
            if (StringUtils.isNotBlank(operateUser.getOrgId())) {
                List<QcNoSettings> qcNoSettingsList = qcNoSettingsService.selectByLab(operateUser.getOrgId());
                Calendar date = Calendar.getInstance(); //获取年份
                if (qcNoSettingsList != null && !qcNoSettingsList.isEmpty()) {
                    for (QcNoSettings qcNoSettings : qcNoSettingsList) {
                        if (qcNoSettings.getDictCode().equals(Constants.SEMINAL_EXTRACTION_NO)) {
                            String seminalExtractionNo = qcNoSettings.getLabServerHeader() + "-" + String.valueOf(date.get(Calendar.YEAR)) + "-" + qcNoSettings.getBarcode();
                            params.put("seminalExtractionNo", StringUtils.isBlank(seminalExtractionNo) ? "" : seminalExtractionNo);
                        }
                    }
                }
            }
            //纯化方法
            dictItem.setDictTypeCode(Constants.PURIFICATION_METHOD);
            List<DictItem> purificationMethodList = DictUtil.getDictList(dictItem);
            params.put("purificationMethodList",purificationMethodList);

            //页眉
            if (labExtractInfo != null) {
                if (StringUtils.isNotEmpty(labExtractInfo.getOrgId())) {
                    ExperimentalParameter exp = new ExperimentalParameter();
                    exp.setOrgId(labExtractInfo.getOrgId());
                    exp.setExperimentalType("FINEGROUPER");//精斑提取
                    List<ExperimentalParameter> experimentalParameters = experimentalParameterService.selectList(exp);
                    if (ListUtils.isNotNullAndEmptyList(experimentalParameters)) {
                        ExperimentalParameter experimentalParameter = experimentalParameters.get(0);
                        String orgNameSp = experimentalParameter.getParameterName();
                        params.put("orgNameSp", StringUtils.isBlank(orgNameSp) ? "" : orgNameSp);
                    }
                }
            }
            labExtractSampleLists = labExtractSampleService.selectByExtractId(extractId);
            if (ListUtils.isNotNullAndEmptyList(labExtractSampleLists)) {
                for (LabExtractSample extractSample : labExtractSampleLists) {
                    if (caseId.equals(extractSample.getCaseId())) {
                        List<LimsSampleInfoDna> sampleInfoDnaList = limsSampleInfoDnaService.selectBySampleId(extractSample.getSampleId());
                        LimsSampleInfoDna sampleInfoDna = new LimsSampleInfoDna();
                        if (ListUtils.isNotNullAndEmptyList(sampleInfoDnaList)) {
                            sampleInfoDna = sampleInfoDnaList.get(0);
                            extractSample.setLimsSampleInfoDna(sampleInfoDna);
                        }
                    }
                }
            }
            List<LabExtractSample>  extractSampleListTwo = new ArrayList<>();
            for (int i = 0; i < labExtractSampleLists.size(); i++) {
                String extractString = labExtractSampleLists.get(i).getExtractString();
                String[] extractStringS = extractString.split("},");
                labExtractSampleLists.get(i).setTES(extractStringS[0]);
                labExtractSampleLists.get(i).setSDS(extractStringS[1]);
                labExtractSampleLists.get(i).setPK(extractStringS[2]);
                labExtractSampleLists.get(i).setIsOK(Boolean.valueOf(extractStringS[3]));
                labExtractSampleLists.get(i).setIsTwe(Boolean.valueOf(extractStringS[4]));
                if (Boolean.valueOf(extractStringS[4])) {
                    labExtractSampleLists.get(i).setTES1(extractStringS[5]);
                    labExtractSampleLists.get(i).setSDS1(extractStringS[6]);
                    labExtractSampleLists.get(i).setChelex(extractStringS[7]);
                    labExtractSampleLists.get(i).setPK1(extractStringS[8]);
                    labExtractSampleLists.get(i).setIMDTT(extractStringS[9]);
                    labExtractSampleLists.get(i).setPurification(extractStringS[10]);
                    //如果存在二次消化，放到新的list中
                    extractSampleListTwo.add(labExtractSampleLists.get(i));
                }
                if (extractStringS.length == 7){
                    labExtractInfo.setDigestionTemperature(extractStringS[5]);
                    labExtractInfo.setDigestionTime(extractStringS[6]);
                }else if (extractStringS.length == 13) {
                    labExtractInfo.setDigestionTemperature(extractStringS[11]);
                    labExtractInfo.setDigestionTime(extractStringS[12]);
                }
            }

            List<Map<String, Object>> resultList = new ArrayList<>();
            //一次消化实验检材
            List<List<LabExtractSample>> mapList = new ArrayList<>();
            if (ListUtils.isNotNullAndEmptyList(labExtractSampleLists)) {
                List<LabExtractSample> strList = new ArrayList<>();
                for (int i = 0;i < labExtractSampleLists.size();i ++) {
                    strList.add(labExtractSampleLists.get(i));

                    if ((i + 1) % 7 == 0) {
                        mapList.add(strList);
                        strList = new ArrayList<>();
                    }
                }
                //余数小于7时也添加到mapList
                if (ListUtils.isNotNullAndEmptyList(strList)) {
                    LabExtractSample extractSample = new LabExtractSample();
                    for (int i = strList.size(); i < 7; i++) {
                        extractSample.setLimsSampleInfoDna(new LimsSampleInfoDna());
                        strList.add(extractSample);
                    }
                    mapList.add(strList);
                }
                params.put("mapList", mapList);
            }
            //二次消化实验检材
            List<List<LabExtractSample>> mapListTwo = new ArrayList<>();
            if (ListUtils.isNotNullAndEmptyList(extractSampleListTwo)) {
                List<LabExtractSample> strList = new ArrayList<>();
                for (int i = 0;i < extractSampleListTwo.size();i ++) {
                    strList.add(extractSampleListTwo.get(i));

                    if ((i + 1) % 7 == 0) {
                        mapListTwo.add(strList);
                        strList = new ArrayList<>();
                    }
                }
                //余数小于7时也添加到mapList
                if (ListUtils.isNotNullAndEmptyList(strList)) {
                    LabExtractSample extractSample = new LabExtractSample();
                    for (int i = strList.size(); i < 7; i++) {
                        extractSample.setLimsSampleInfoDna(new LimsSampleInfoDna());
                        strList.add(extractSample);
                    }
                    mapListTwo.add(strList);
                }
                if (ListUtils.isNotNullAndEmptyList(mapList)) {
                    List<LabExtractSample> sampleList = new ArrayList<>();
                    for (int i = mapListTwo.size();i < mapList.size();i++) {
                        LabExtractSample extractSample = new LabExtractSample();
                        for (int j = 0; j < 7; j++) {
                            extractSample.setLimsSampleInfoDna(new LimsSampleInfoDna());
                            sampleList.add(extractSample);
                        }
                        mapListTwo.add(sampleList);
                    }
                }
                params.put("mapListTwo", mapListTwo);
            }
            Map<String, Object> paramMap = new HashMap<>();
            if (ListUtils.isNotNullAndEmptyList(mapList) && ListUtils.isNotNullAndEmptyList(mapListTwo) && mapList.size() == mapListTwo.size()) {
                int count = mapList.size();
                for (int i = 0;i < count;i++) {
                    Map<String, Object> newMap = new HashMap<>();
                    newMap.putAll(params);
                    newMap.put("firstExtractSampleList", mapList.get(i));
                    newMap.put("secondExtractSampleList", mapListTwo.get(i));
                    resultList.add(newMap);
                }
            }
            paramMap.put("resultList",resultList);

            try {
                Configuration cfg = new Configuration();
                cfg.setDefaultEncoding("UTF-8");
                cfg.setClassForTemplateLoading(this.getClass(), "/templates");
                //获取模板
                String ftlName = "spermSpotRecord.ftl";

                Template tmp = cfg.getTemplate(ftlName, "UTF-8");

                String filename = "DNA精斑提取记录表" + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".doc";

                response.setCharacterEncoding("UTF-8");
                //文件头，导出的文件名
                response.setHeader("Content-disposition", "attachment;filename=" + new String(filename.getBytes("GBK"), "ISO-8859-1"));
                //类型
                response.setContentType("application/x-msdownload");
                tmp.process(paramMap, response.getWriter());
            } catch (Exception ex) {
                logger.error("下载错误！", ex);
            }
        }else {
            try {

                Configuration cfg = new Configuration();
                cfg.setDefaultEncoding("UTF-8");
                cfg.setClassForTemplateLoading(this.getClass(), "/templates");
                //获取模板
                String ftlName = "recordTabletest.ftl";

                if(labExtractSampleLists.size() >0){
                    if("D".equals(labExtractSampleLists.get(0).getExtractMethod())) {
                        ftlName ="recordTableAutomatic.ftl";
                    }
                }


                Template tmp = cfg.getTemplate(ftlName, "UTF-8");

                String filename = "DNA常规提取记录表" + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".doc";

                response.setCharacterEncoding("UTF-8");
                //文件头，导出的文件名
                response.setHeader("Content-disposition", "attachment;filename=" + new String(filename.getBytes("GBK"), "ISO-8859-1"));
                //类型
                response.setContentType("application/x-msdownload");
                tmp.process(params, response.getWriter());

            } catch (Exception ex) {
                logger.error("下载错误！", ex);
            }
        }
    }

    /**
     * 聘书下载
     *
     * @return
     */
    @RequestMapping("dowmletterBtn")
    @ResponseBody
    public Map<String, Object> isdowmletterBtn(HttpServletRequest request, HttpSession session, HttpServletResponse response, String caseId) {
        Map<String, Object> result = new HashMap<>();
        FileArchivesInfo fileArchivesInfo = fileArchivesInfoService.queryFileLetter(caseId);
        String writeFilePath = null;
        if (Objects.nonNull(fileArchivesInfo)) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                //Base64解码
                String[] split = fileArchivesInfo.getArchivesInfoPicture().split(",");
                byte[] b = decoder.decodeBuffer(split[1]);
                for (int i = 0; i < b.length; ++i) {
                    if (b[i] < 0) {//调整异常数据
                        b[i] += 256;
                    }
                }
                String filename = "聘书" + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".jpg";
                //生成jpeg图片
                String imgFilePath = SystemUtil.getSystemConfig().getProperty("DownloadPath");
                ;//新生成的图片
                writeFilePath = imgFilePath + filename;//新生成的图片
                OutputStream out = new FileOutputStream(writeFilePath);
                out.write(b);
                out.flush();
                out.close();
                result.put("filePath", writeFilePath);
            } catch (Exception e) {
                logger.error("下载聘书" + e);
            }

        } else {
            result.put("filePath", writeFilePath);
        }
        return result;
    }

    /**
     * 委托书下载
     *
     * @return
     */
    @RequestMapping("entrustBookBtn")
    @ResponseBody
    public Map<String, Object> isentrustBookBtn(HttpServletRequest request, HttpSession session, HttpServletResponse response, String caseId) {
        Map<String, Object> result = new HashMap<>();
        FileArchivesInfo fileArchivesInfo = fileArchivesInfoService.queryEntrustBook(caseId);
        String writeFilePath = null;
        if (Objects.nonNull(fileArchivesInfo)) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                //Base64解码
                String[] split = fileArchivesInfo.getArchivesInfoPicture().split(",");
                byte[] b = decoder.decodeBuffer(split[1]);
                for (int i = 0; i < b.length; ++i) {
                    if (b[i] < 0) {//调整异常数据
                        b[i] += 256;
                    }
                }
                String filename = "委托书" + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".jpg";
                //生成jpeg图片
                String imgFilePath = SystemUtil.getSystemConfig().getProperty("DownloadPath");
                ;//新生成的图片
                writeFilePath = imgFilePath + filename;//新生成的图片
                OutputStream out = new FileOutputStream(writeFilePath);
                out.write(b);
                out.flush();
                out.close();
                result.put("filePath", writeFilePath);
            } catch (Exception e) {
                logger.error("下载委托书" + e);
            }

        } else {
            result.put("filePath", writeFilePath);
        }
        return result;
    }


    /***************************************************************************************************
     * /**
     * 下载选中的文件
     *
     * @param request
     * @param response
     * @param caseInfo
     * @return
     */
    @RequestMapping(value = "/compressedFilesAll", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> compressedFilesAll(HttpServletRequest request, HttpServletResponse response, LimsCaseInfo caseInfo) throws ParseException {
        String flag = "1";
        Map<String, Object> result = new HashMap<String, Object>();
        //根据其那台传送的consignmentId委托id查询案件
        LimsCaseInfo limsCaseInfo = limsCaseInfoService.selectByConsignmentId(caseInfo.getConsignmentId());
        if (Objects.nonNull(limsCaseInfo)) {
            //前台选中下载的标识
            String codeParm = caseInfo.getCodeParm();
            //前台委托id赋值给当前案件的委托id业务字段
            limsCaseInfo.setConsignmentId(caseInfo.getConsignmentId());
            //得到选中下载文件的标识数组
            String[] split = codeParm.substring(0, codeParm.length() - 1).split(",");
            List<File> fileList = new ArrayList<File>();
            String fullPrint = "2";
            for (int i = 0; i < split.length; i++) {
                Map<String, Object> resultPs = new HashMap<String, Object>();
                if("01".equals(split[i])){
                    resultPs =  this.compressedVolume(request, response, limsCaseInfo.getConsignmentId(),flag,fullPrint);
                    if(resultPs.size() >0){
                        String filePath = resultPs.get("filePathName").toString();
                        if (StringUtils.isNotBlank(filePath)) {
                            fileList.add(new File(filePath));
                        }
                    }
                    if(resultPs == null || resultPs.size()==0){
                        String genentFile = "未生成卷皮";
                        result.put("status1",genentFile);
                    }
                }else if (split[i].equals("03")) {
                    //受理确认书
                    resultPs = this.compressedFilesSlqrs(request, response, limsCaseInfo.getConsignmentId(),flag,fullPrint);
                    if(resultPs.size() >0){
                        String filePath = resultPs.get("filePathName").toString();
                        if (StringUtils.isNotBlank(filePath)) {
                            fileList.add(new File(filePath));
                        }
                    }
                    if(resultPs == null || resultPs.size()==0 ){
                        String genentFile = "未生成鉴定事项确认书";
                        result.put("status3",genentFile);
                    }
                } else if (split[i].equals("04")) {
                    //聘书
                    resultPs = this.compressedFilesPs(request, response, limsCaseInfo,flag,fullPrint);
                    if(resultPs.size() >0){
                        String filePath = resultPs.get("filePathName").toString();
                        if (StringUtils.isNotBlank(filePath)) {
                            fileList.add(new File(filePath));
                        }
                    }
                    if(resultPs == null || resultPs.size()==0){
                        String genentFile = "未生成聘书";
                        result.put("status4",genentFile);
                    }
                } else if (split[i].equals("05")) {
                    //预实验记录表
                    resultPs = this.compressedFilesYsy(request, response, limsCaseInfo.getConsignmentId(),flag,fullPrint);
                    if(resultPs.size() >0){
                        String filePath = resultPs.get("filePathName").toString();
                        if (StringUtils.isNotBlank(filePath)) {
                            fileList.add(new File(filePath));
                        }
                    }
                    if(resultPs == null || resultPs.size()==0){
                        String genentFile = "未生成预实验记录表";
                        result.put("status5",genentFile);
                    }

                } else if (split[i].equals("06")) {
                    //提取记录
                    resultPs = this.compressedFilesTq(request, response, limsCaseInfo,flag,fullPrint);
                    if(resultPs.size() > 0){
                        String filePath = resultPs.get("filePathName").toString();
                        if (StringUtils.isNotBlank(filePath)) {
                            fileList.add(new File(filePath));
                        }
                    }
                    if(resultPs == null || resultPs.size()==0){
                        String genentFile = "未生成提取记录";
                        result.put("status6",genentFile);
                    }
                } else if (split[i].equals("07")) {
                    //扩增记录
                    resultPs = this.compressedFilesKz(request, response, limsCaseInfo,flag,fullPrint);
                    if(resultPs.size() > 0){
                        String filePath = resultPs.get("filePathName").toString();
                        if (StringUtils.isNotBlank(filePath)) {
                            fileList.add(new File(filePath));
                        }
                    }
                    if(resultPs == null || resultPs.size()==0){
                        String genentFile = "未生成扩增记录";
                        result.put("status7",genentFile);
                    }
                } else if (split[i].equals("08")) {
                    //上样记录
                    resultPs = this.compressedFilesSy(request, response, limsCaseInfo,flag,fullPrint);
                    if(resultPs.size() >0){
                        String filePath = resultPs.get("filePathName").toString();
                        if (StringUtils.isNotBlank(filePath)) {
                            fileList.add(new File(filePath));
                        }
                    }
                    if(resultPs == null || resultPs.size()==0){
                        String genentFile = "未生成上样记录";
                        result.put("status8",genentFile);
                    }

                } else if (split[i].equals("11")) {
                    //鉴定书
                    resultPs = this.compressedFilesJds(request, limsCaseInfo.getCaseId(),flag,fullPrint);
                    if(resultPs.size() > 0){
                        String filePath = resultPs.get("filePathName").toString();
                        if (StringUtils.isNotBlank(filePath)) {
                            fileList.add(new File(filePath));
                        }
                    }
                    if(resultPs == null || resultPs.size()==0){
                        String genentFile = "未生成鉴定书";
                        result.put("status11",genentFile);
                    }
                } else if (split[i].equals("13")) {
                    //检材流转记录表
                    resultPs = this.compressedFilesJclz(request, response, limsCaseInfo.getConsignmentId(),flag,fullPrint);
                    if(resultPs.size() >0){
                        String filePath = resultPs.get("filePathName").toString();
                        if (StringUtils.isNotBlank(filePath)) {
                            fileList.add(new File(filePath));
                        }
                    }
                    if(resultPs == null || resultPs.size()==0){
                        String genentFile = "未生成检材流转记录表";
                        result.put("status13",genentFile);
                    }
                } else if (split[i].equals("14")) {
                    //委托书
                    resultPs = this.compressedFilesWts(request, response, limsCaseInfo.getCaseId(),flag,fullPrint);
                    if(resultPs.size() >0){
                        if(resultPs.size() > 0){
                            String filePath = resultPs.get("filePathName").toString();
                            if (StringUtils.isNotBlank(filePath)) {
                                fileList.add(new File(filePath));
                            }
                        }
                    }
                    if(resultPs == null || resultPs.size()==0){
                        String genentFile = "未生成委托书";
                        result.put("status14",genentFile);
                    }
                }
            }
            String zipName = limsCaseInfo.getCaseNo() + "_" + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".zip";
            FileOutputStream fos = null;
            ZipOutputStream zos = null;
            writeFilePath = SystemUtil.getSystemConfig().getProperty("DownloadPath");
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
                    if (null != zos) zos.close();
                    if (null != fos) fos.close();

                    for (int i = 0; i < fileList.size(); i++) {
                        File file = (File) fileList.get(i);
                        if (file.exists())
                            file.delete();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
            result.put("success", writeFilePath);
        }
        return result;
    }
    /***************************************************************************************************
     /**
     * 打印选中的文件
     * @param request
     * @param response
     * @param caseInfo
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/printFilesAll")
    @ResponseBody
    public Map<String, Object> printFilesAll(HttpServletRequest request, HttpServletResponse response, LimsCaseInfo caseInfo) throws ParseException {
        Map<String, Object> result = new HashMap<String, Object>();
        String flag="2";
        //根据其那台传送的consignmentId委托id查询案件
        LimsCaseInfo limsCaseInfo = limsCaseInfoService.selectByConsignmentId(caseInfo.getConsignmentId());
        if (Objects.nonNull(limsCaseInfo)) {
            //前台选中下载的标识
            String codeParm = caseInfo.getCodeParm();
            //前台委托id赋值给当前案件的委托id业务字段
            limsCaseInfo.setConsignmentId(caseInfo.getConsignmentId());
            //得到选中下载文件的标识数组
            String[] split = codeParm.substring(0, codeParm.length() - 1).split(",");
            List<File> fileList = new ArrayList<File>();
            List<Object> list1 = new ArrayList<Object>();

            String fullPrint = "1";

            for (int i = 0; i < split.length; i++) {
                if("01".equals(split[i])){
                    String filePathNameOne = (String) this.compressedVolume(request, response, limsCaseInfo.getConsignmentId(),flag,fullPrint).get("filePathName");
                    list1.add(filePathNameOne);
                    caseFtpUrl caseFtpUrlEneity = new caseFtpUrl();
                    caseFtpUrlEneity.setCaseId(limsCaseInfo.getCaseId());
                    caseFtpUrlEneity.setType(1);
                    List<caseFtpUrl> caseFtpUrlList = caseFtpUrlMapper.findCaseIdByTyye(caseFtpUrlEneity);
                    if(caseFtpUrlList.size() >0){
                        String docpath="ftp://" + ftpIp + "/" + ftpCaseFilePath + "/"+filePathNameOne;
                        caseFtpUrl caseFtpUrlUpdate = new caseFtpUrl();
                        caseFtpUrlUpdate.setId(caseFtpUrlList.get(0).getId());
                        caseFtpUrlUpdate.setCaseId(caseFtpUrlList.get(0).getCaseId());
                        caseFtpUrlUpdate.setType(1);
                        caseFtpUrlUpdate.setFtpUrl(docpath);
                        caseFtpUrlMapper.updateByPrimaryKey(caseFtpUrlUpdate);

                    }else{
                        caseFtpUrl caseFtpUrls = new caseFtpUrl();
                        caseFtpUrls.setId(UUID.randomUUID().toString());
                        caseFtpUrls.setType(1);
                        caseFtpUrls.setCaseId(limsCaseInfo.getCaseId());
                        String docpath="ftp://" + ftpIp + "/" + ftpCaseFilePath + "/"+filePathNameOne;
                        caseFtpUrls.setFtpUrl(docpath);
                        caseFtpUrlMapper.insert(caseFtpUrls);
                    }

                    if(filePathNameOne == null){
                        String genentFile = "未生成卷皮";
                        result.put("status1",genentFile);
                    }
                }else if (split[i].equals("03")) {
                    //受理确认书
//                    map1.put("ompressedFilesSlqrs",this.compressedFilesSlqrs(request, response, limsCaseInfo.getConsignmentId(),flag,fullPrint));
                    String filePathName3 = (String) this.compressedFilesSlqrs(request, response, limsCaseInfo.getConsignmentId(),flag,fullPrint).get("filePathName");
                    list1.add(filePathName3);
                    caseFtpUrl caseFtpUrlEneity = new caseFtpUrl();
                    caseFtpUrlEneity.setCaseId(limsCaseInfo.getCaseId());
                    caseFtpUrlEneity.setType(3);
                    List<caseFtpUrl> caseFtpUrlList = caseFtpUrlMapper.findCaseIdByTyye(caseFtpUrlEneity);
                    if(caseFtpUrlList.size() >0){
                        String docpath="ftp://" + ftpIp + "/" + ftpCaseFilePath + "/"+filePathName3;
                        caseFtpUrl caseFtpUrlUpdate = new caseFtpUrl();
                        caseFtpUrlUpdate.setId(caseFtpUrlList.get(0).getId());
                        caseFtpUrlUpdate.setCaseId(caseFtpUrlList.get(0).getCaseId());
                        caseFtpUrlUpdate.setType(3);
                        caseFtpUrlUpdate.setFtpUrl(docpath);
                        caseFtpUrlMapper.updateByPrimaryKey(caseFtpUrlUpdate);

                    }else{
                        caseFtpUrl caseFtpUrls = new caseFtpUrl();
                        caseFtpUrls.setId(UUID.randomUUID().toString());
                        caseFtpUrls.setType(3);
                        caseFtpUrls.setCaseId(limsCaseInfo.getCaseId());
                        String docpath="ftp://" + ftpIp + "/" + ftpCaseFilePath + "/"+filePathName3;
                        caseFtpUrls.setFtpUrl(docpath);
                        caseFtpUrlMapper.insert(caseFtpUrls);
                    }
                    if(filePathName3 == null){
                        String genentFile = "未生成鉴定事项确认书";
                        result.put("status3",genentFile);
                    }

                } else if (split[i].equals("04")) {
                    //聘书
//                     this.compressedFilesPs(request, response, limsCaseInfo,flag,fullPrint);

                    String filePathName4 = (String)this.compressedFilesPs(request, response, limsCaseInfo,flag,fullPrint).get("filePathName");
                    list1.add(filePathName4);

                    caseFtpUrl caseFtpUrlEneity = new caseFtpUrl();
                    caseFtpUrlEneity.setCaseId(limsCaseInfo.getCaseId());
                    caseFtpUrlEneity.setType(4);
                    List<caseFtpUrl> caseFtpUrlList = caseFtpUrlMapper.findCaseIdByTyye(caseFtpUrlEneity);
                    if(caseFtpUrlList.size() >0){
                        String docpath="ftp://" + ftpIp + "/" + ftpCaseFilePath + "/"+filePathName4;
                        caseFtpUrl caseFtpUrlUpdate = new caseFtpUrl();
                        caseFtpUrlUpdate.setId(caseFtpUrlList.get(0).getId());
                        caseFtpUrlUpdate.setCaseId(caseFtpUrlList.get(0).getCaseId());
                        caseFtpUrlUpdate.setType(4);
                        caseFtpUrlUpdate.setFtpUrl(docpath);
                        caseFtpUrlMapper.updateByPrimaryKey(caseFtpUrlUpdate);

                    }else{
                        caseFtpUrl caseFtpUrls = new caseFtpUrl();
                        caseFtpUrls.setId(UUID.randomUUID().toString());
                        caseFtpUrls.setType(4);
                        caseFtpUrls.setCaseId(limsCaseInfo.getCaseId());
                        String docpath="ftp://" + ftpIp + "/" + ftpCaseFilePath + "/"+filePathName4;
                        caseFtpUrls.setFtpUrl(docpath);
                        caseFtpUrlMapper.insert(caseFtpUrls);
                    }
                    if(filePathName4 == null){
                        String genentFile = "未生成聘书";
                        result.put("status4",genentFile);
                    }

                } else if (split[i].equals("05")) {
                    //预实验记录表
//                   this.compressedFilesYsy(request, response, limsCaseInfo.getConsignmentId(),flag,fullPrint);
                    String filePathName5 = (String) this.compressedFilesYsy(request, response, limsCaseInfo.getConsignmentId(),flag,fullPrint).get("filePathName");
                    list1.add(filePathName5);

                    caseFtpUrl caseFtpUrlEneity = new caseFtpUrl();
                    caseFtpUrlEneity.setCaseId(limsCaseInfo.getCaseId());
                    caseFtpUrlEneity.setType(5);
                    List<caseFtpUrl> caseFtpUrlList = caseFtpUrlMapper.findCaseIdByTyye(caseFtpUrlEneity);
                    if(caseFtpUrlList.size() >0){
                        String docpath="ftp://" + ftpIp + "/" + ftpCaseFilePath + "/"+filePathName5;
                        caseFtpUrl caseFtpUrlUpdate = new caseFtpUrl();
                        caseFtpUrlUpdate.setId(caseFtpUrlList.get(0).getId());
                        caseFtpUrlUpdate.setCaseId(caseFtpUrlList.get(0).getCaseId());
                        caseFtpUrlUpdate.setType(5);
                        caseFtpUrlUpdate.setFtpUrl(docpath);
                        caseFtpUrlMapper.updateByPrimaryKey(caseFtpUrlUpdate);

                    }else{
                        caseFtpUrl caseFtpUrls = new caseFtpUrl();
                        caseFtpUrls.setId(UUID.randomUUID().toString());
                        caseFtpUrls.setType(5);
                        caseFtpUrls.setCaseId(limsCaseInfo.getCaseId());
                        String docpath="ftp://" + ftpIp + "/" + ftpCaseFilePath + "/"+filePathName5;
                        caseFtpUrls.setFtpUrl(docpath);
                        caseFtpUrlMapper.insert(caseFtpUrls);
                    }
                    if(filePathName5 == null){
                        String genentFile = "未生成预实验记录表";
                        result.put("status5",genentFile);
                    }

                } else if (split[i].equals("06")) {
                    //提取记录
//                   this.compressedFilesTq(request, response, limsCaseInfo,flag,fullPrint);
                    String filePathNames = (String) this.compressedFilesTq(request, response, limsCaseInfo,flag,fullPrint).get("filePathName");
                    list1.add(filePathNames);

                    caseFtpUrl caseFtpUrlEneity = new caseFtpUrl();
                    caseFtpUrlEneity.setCaseId(limsCaseInfo.getCaseId());
                    caseFtpUrlEneity.setType(6);
                    List<caseFtpUrl> caseFtpUrlList = caseFtpUrlMapper.findCaseIdByTyye(caseFtpUrlEneity);
                    if(caseFtpUrlList.size() >0){
                        String docpath="ftp://" + ftpIp + "/" + ftpCaseFilePath + "/"+filePathNames;
                        caseFtpUrl caseFtpUrlUpdate = new caseFtpUrl();
                        caseFtpUrlUpdate.setId(caseFtpUrlList.get(0).getId());
                        caseFtpUrlUpdate.setCaseId(caseFtpUrlList.get(0).getCaseId());
                        caseFtpUrlUpdate.setType(6);
                        caseFtpUrlUpdate.setFtpUrl(docpath);
                        caseFtpUrlMapper.updateByPrimaryKey(caseFtpUrlUpdate);

                    }else{
                        caseFtpUrl caseFtpUrls = new caseFtpUrl();
                        caseFtpUrls.setId(UUID.randomUUID().toString());
                        caseFtpUrls.setType(6);
                        caseFtpUrls.setCaseId(limsCaseInfo.getCaseId());
                        String docpath="ftp://" + ftpIp + "/" + ftpCaseFilePath + "/"+filePathNames;
                        caseFtpUrls.setFtpUrl(docpath);
                        caseFtpUrlMapper.insert(caseFtpUrls);
                    }
                    if(filePathNames == null){
                        String genentFile = "未生成提取记录";
                        result.put("status6",genentFile);
                    }
                } else if (split[i].equals("07")) {
                    //扩增记录
//                    this.compressedFilesKz(request, response, limsCaseInfo,flag,fullPrint();
                    String filePathName7 = (String) this.compressedFilesKz(request, response, limsCaseInfo,flag,fullPrint).get("filePathName");
                    list1.add(filePathName7);

                    caseFtpUrl caseFtpUrlEneity = new caseFtpUrl();
                    caseFtpUrlEneity.setCaseId(limsCaseInfo.getCaseId());
                    caseFtpUrlEneity.setType(7);
                    List<caseFtpUrl> caseFtpUrlList = caseFtpUrlMapper.findCaseIdByTyye(caseFtpUrlEneity);
                    if(caseFtpUrlList.size() >0){
                        String docpath="ftp://" + ftpIp + "/" + ftpCaseFilePath + "/"+filePathName7;
                        caseFtpUrl caseFtpUrlUpdate = new caseFtpUrl();
                        caseFtpUrlUpdate.setId(caseFtpUrlList.get(0).getId());
                        caseFtpUrlUpdate.setCaseId(caseFtpUrlList.get(0).getCaseId());
                        caseFtpUrlUpdate.setType(7);
                        caseFtpUrlUpdate.setFtpUrl(docpath);
                        caseFtpUrlMapper.updateByPrimaryKey(caseFtpUrlUpdate);

                    }else{
                        caseFtpUrl caseFtpUrls = new caseFtpUrl();
                        caseFtpUrls.setId(UUID.randomUUID().toString());
                        caseFtpUrls.setType(7);
                        caseFtpUrls.setCaseId(limsCaseInfo.getCaseId());
                        String docpath="ftp://" + ftpIp + "/" + ftpCaseFilePath + "/"+filePathName7;
                        caseFtpUrls.setFtpUrl(docpath);
                        caseFtpUrlMapper.insert(caseFtpUrls);
                    }
                    if(filePathName7 == null){
                        String genentFile = "未生成扩增记录";
                        result.put("status7",genentFile);
                    }
                } else if (split[i].equals("08")) {
                    //上样记录
//                     this.compressedFilesSy(request, response, limsCaseInfo,flag,fullPrint);
                    String filePathName8 = (String) this.compressedFilesSy(request, response, limsCaseInfo,flag,fullPrint).get("filePathName");
                    list1.add(filePathName8);

                    caseFtpUrl caseFtpUrlEneity = new caseFtpUrl();
                    caseFtpUrlEneity.setCaseId(limsCaseInfo.getCaseId());
                    caseFtpUrlEneity.setType(8);
                    List<caseFtpUrl> caseFtpUrlList = caseFtpUrlMapper.findCaseIdByTyye(caseFtpUrlEneity);
                    if(caseFtpUrlList.size() >0){
                        String docpath="ftp://" + ftpIp + "/" + ftpCaseFilePath + "/"+filePathName8;
                        caseFtpUrl caseFtpUrlUpdate = new caseFtpUrl();
                        caseFtpUrlUpdate.setId(caseFtpUrlList.get(0).getId());
                        caseFtpUrlUpdate.setCaseId(caseFtpUrlList.get(0).getCaseId());
                        caseFtpUrlUpdate.setType(8);
                        caseFtpUrlUpdate.setFtpUrl(docpath);
                        caseFtpUrlMapper.updateByPrimaryKey(caseFtpUrlUpdate);

                    }else{
                        caseFtpUrl caseFtpUrls = new caseFtpUrl();
                        caseFtpUrls.setId(UUID.randomUUID().toString());
                        caseFtpUrls.setType(8);
                        caseFtpUrls.setCaseId(limsCaseInfo.getCaseId());
                        String docpath="ftp://" + ftpIp + "/" + ftpCaseFilePath + "/"+filePathName8;
                        caseFtpUrls.setFtpUrl(docpath);
                        caseFtpUrlMapper.insert(caseFtpUrls);
                    }
                    if(filePathName8 == null){
                        String genentFile = "未生成上样记录";
                        result.put("status8",genentFile);
                    }
                } else if (split[i].equals("11")) {
                    //鉴定书
//                    this.compressedFilesJds(request, limsCaseInfo.getCaseId(),flag,fullPrint);
                    String filePathName11 = (String)this.compressedFilesJds(request, limsCaseInfo.getCaseId(),flag,fullPrint).get("filePathName");
                    list1.add(this.compressedFilesJds(request, limsCaseInfo.getCaseId(),flag,fullPrint).get("filePathName"));

                    caseFtpUrl caseFtpUrlEneity = new caseFtpUrl();
                    caseFtpUrlEneity.setCaseId(limsCaseInfo.getCaseId());
                    caseFtpUrlEneity.setType(11);
                    List<caseFtpUrl> caseFtpUrlList = caseFtpUrlMapper.findCaseIdByTyye(caseFtpUrlEneity);
                    if(caseFtpUrlList.size() >0){
                        String docpath="ftp://" + ftpIp + "/" + ftpCaseFilePath + "/"+filePathName11;
                        caseFtpUrl caseFtpUrlUpdate = new caseFtpUrl();
                        caseFtpUrlUpdate.setId(caseFtpUrlList.get(0).getId());
                        caseFtpUrlUpdate.setCaseId(caseFtpUrlList.get(0).getCaseId());
                        caseFtpUrlUpdate.setType(11);
                        caseFtpUrlUpdate.setFtpUrl(docpath);
                        caseFtpUrlMapper.updateByPrimaryKey(caseFtpUrlUpdate);

                    }else{
                        caseFtpUrl caseFtpUrls = new caseFtpUrl();
                        caseFtpUrls.setId(UUID.randomUUID().toString());
                        caseFtpUrls.setType(11);
                        caseFtpUrls.setCaseId(limsCaseInfo.getCaseId());
                        String docpath="ftp://" + ftpIp + "/" + ftpCaseFilePath + "/"+filePathName11;
                        caseFtpUrls.setFtpUrl(docpath);
                        caseFtpUrlMapper.insert(caseFtpUrls);
                    }
                    if(filePathName11 == null){
                        String genentFile = "未生成鉴定书";
                        result.put("status11",genentFile);
                    }
                }else if (split[i].equals("13")) {
                    //检材流转记录表
                    String filePathName13 = (String) this.compressedFilesJclz(request, response, limsCaseInfo.getConsignmentId(),flag,fullPrint).get("filePathName");
                    list1.add(filePathName13);

                    caseFtpUrl caseFtpUrlEneity = new caseFtpUrl();
                    caseFtpUrlEneity.setCaseId(limsCaseInfo.getCaseId());
                    caseFtpUrlEneity.setType(13);
                    List<caseFtpUrl> caseFtpUrlList = caseFtpUrlMapper.findCaseIdByTyye(caseFtpUrlEneity);
                    if(caseFtpUrlList.size() >0){
                        String docpath="ftp://" + ftpIp + "/" + ftpCaseFilePath + "/"+filePathName13;
                        caseFtpUrl caseFtpUrlUpdate = new caseFtpUrl();
                        caseFtpUrlUpdate.setId(caseFtpUrlList.get(0).getId());
                        caseFtpUrlUpdate.setCaseId(caseFtpUrlList.get(0).getCaseId());
                        caseFtpUrlUpdate.setType(13);
                        caseFtpUrlUpdate.setFtpUrl(docpath);
                        caseFtpUrlMapper.updateByPrimaryKey(caseFtpUrlUpdate);

                    }else{
                        caseFtpUrl caseFtpUrls = new caseFtpUrl();
                        caseFtpUrls.setId(UUID.randomUUID().toString());
                        caseFtpUrls.setType(13);
                        caseFtpUrls.setCaseId(limsCaseInfo.getCaseId());
                        String docpath="ftp://" + ftpIp + "/" + ftpCaseFilePath + "/"+filePathName13;
                        caseFtpUrls.setFtpUrl(docpath);
                        caseFtpUrlMapper.insert(caseFtpUrls);
                    }
                    if(filePathName13 == null){
                        String genentFile = "未生成检材流转记录表";
                        result.put("status13",genentFile);
                    }
                }else if (split[i].equals("14")) {
                    //委托书
                    String filePathName14 = (String) this.compressedFilesWts(request,response,limsCaseInfo.getCaseId(),flag,fullPrint).get("filePathName");
                    list1.add(filePathName14);

                    caseFtpUrl caseFtpUrlEneity = new caseFtpUrl();
                    caseFtpUrlEneity.setCaseId(limsCaseInfo.getCaseId());
                    caseFtpUrlEneity.setType(14);
                    List<caseFtpUrl> caseFtpUrlList = caseFtpUrlMapper.findCaseIdByTyye(caseFtpUrlEneity);
                    if(caseFtpUrlList.size() >0){
                        String docpath="ftp://" + ftpIp + "/" + ftpCaseFilePath + "/"+filePathName14;
                        caseFtpUrl caseFtpUrlUpdate = new caseFtpUrl();
                        caseFtpUrlUpdate.setId(caseFtpUrlList.get(0).getId());
                        caseFtpUrlUpdate.setCaseId(caseFtpUrlList.get(0).getCaseId());
                        caseFtpUrlUpdate.setType(14);
                        caseFtpUrlUpdate.setFtpUrl(docpath);
                        caseFtpUrlMapper.updateByPrimaryKey(caseFtpUrlUpdate);

                    }else{
                        caseFtpUrl caseFtpUrls = new caseFtpUrl();
                        caseFtpUrls.setId(UUID.randomUUID().toString());
                        caseFtpUrls.setType(14);
                        caseFtpUrls.setCaseId(limsCaseInfo.getCaseId());
                        String docpath="ftp://" + ftpIp + "/" + ftpCaseFilePath + "/"+filePathName14;
                        caseFtpUrls.setFtpUrl(docpath);
                        caseFtpUrlMapper.insert(caseFtpUrls);
                    }
                    if(filePathName14 == null){
                        String genentFile = "未生成委托书";
                        result.put("status14",genentFile);
                    }

                }else if(split[i].equals("10")){
                    String filePathName10 = (String) this.warehouseFiles(request,response,limsCaseInfo.getCaseId(),flag,fullPrint).get("filePathName");
                    list1.add(filePathName10);

                    caseFtpUrl caseFtpUrlEneity = new caseFtpUrl();
                    caseFtpUrlEneity.setCaseId(limsCaseInfo.getCaseId());
                    caseFtpUrlEneity.setType(10);
                    List<caseFtpUrl> caseFtpUrlList = caseFtpUrlMapper.findCaseIdByTyye(caseFtpUrlEneity);
                    if(caseFtpUrlList.size() >0){
                        String docpath="ftp://" + ftpIp + "/" + ftpCaseFilePath + "/"+filePathName10;
                        caseFtpUrl caseFtpUrlUpdate = new caseFtpUrl();
                        caseFtpUrlUpdate.setId(caseFtpUrlList.get(0).getId());
                        caseFtpUrlUpdate.setCaseId(caseFtpUrlList.get(0).getCaseId());
                        caseFtpUrlUpdate.setType(10);
                        caseFtpUrlUpdate.setFtpUrl(docpath);
                        caseFtpUrlMapper.updateByPrimaryKey(caseFtpUrlUpdate);

                    }else{
                        caseFtpUrl caseFtpUrls = new caseFtpUrl();
                        caseFtpUrls.setId(UUID.randomUUID().toString());
                        caseFtpUrls.setType(10);
                        caseFtpUrls.setCaseId(limsCaseInfo.getCaseId());
                        String docpath="ftp://" + ftpIp + "/" + ftpCaseFilePath + "/"+filePathName10;
                        caseFtpUrls.setFtpUrl(docpath);
                        caseFtpUrlMapper.insert(caseFtpUrls);
                    }

                    if(filePathName10 == null){
                        String genentFile = "未生成入库单";
                        result.put("status10",genentFile);
                    }

                }
            }


            String ss = "";

            if(list1.size() >0){
                for(int i =0;i < list1.size();i++){
                    ss += list1.get(i) + ",";
                }
            }

            String s = CasePrintFtpFileUtils.httpLinkNew(request, ftpIp, page, ftpUser, ftpPassword, ftpCaseFilePath,ss);
//            result.put("status","success");
        }else {
//            result.put("status","error");
        }
        return result;
    }


    @RequestMapping(value = "/printRecord", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> printRecord(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String, Object> recordMap) {
        Map<String, Object> map =  new HashMap<>();

        try {
            if (recordMap != null) {
                List<Object> fileList = new ArrayList<Object>();
                String caseId = (String) recordMap.get("caseId");
                LimsCaseInfo limsCaseInfo = new LimsCaseInfo();
                limsCaseInfo.setCaseId(caseId);
                List<Map<String, Object>> recordExtractList = (List) recordMap.get("recordExtractList");
                if (ListUtils.isNotNullAndEmptyList(recordExtractList)) {
                    for (Map<String, Object> mapExtract : recordExtractList) {
                        String filePathName = (String) this.compressedFilesTqNew(request, response, limsCaseInfo, "2", "1", (String)mapExtract.get("extractId")).get("filePathName");
                        fileList.add(filePathName);

                        caseFtpUrl caseFtpUrlEneity = new caseFtpUrl();
                        caseFtpUrlEneity.setCaseId(limsCaseInfo.getCaseId());
                        caseFtpUrlEneity.setType(6);
                        List<caseFtpUrl> caseFtpUrlList = caseFtpUrlMapper.findCaseIdByTyye(caseFtpUrlEneity);
                        if(caseFtpUrlList.size() >0){
                            String docpath="ftp://" + ftpIp + "/" + ftpCaseFilePath + "/"+filePathName;
                            caseFtpUrl caseFtpUrlUpdate = new caseFtpUrl();
                            caseFtpUrlUpdate.setId(caseFtpUrlList.get(0).getId());
                            caseFtpUrlUpdate.setCaseId(caseFtpUrlList.get(0).getCaseId());
                            caseFtpUrlUpdate.setType(6);
                            caseFtpUrlUpdate.setFtpUrl(docpath);
                            caseFtpUrlMapper.updateByPrimaryKey(caseFtpUrlUpdate);

                        }else{
                            caseFtpUrl caseFtpUrls = new caseFtpUrl();
                            caseFtpUrls.setId(UUID.randomUUID().toString());
                            caseFtpUrls.setType(6);
                            caseFtpUrls.setCaseId(limsCaseInfo.getCaseId());
                            String docpath="ftp://" + ftpIp + "/" + ftpCaseFilePath + "/"+filePathName;
                            caseFtpUrls.setFtpUrl(docpath);
                            caseFtpUrlMapper.insert(caseFtpUrls);
                        }
                    }
                }
                List<Map<String, Object>> recordPcrList = (List)recordMap.get("recordPcrList");
                if (ListUtils.isNotNullAndEmptyList(recordPcrList)) {
                    for (Map<String, Object> mapPcr : recordPcrList) {
                        String filePathName = (String) this.compressedFilesKzNew(request, response, limsCaseInfo,"2","1", (String)mapPcr.get("pcrId")).get("filePathName");
                        fileList.add(filePathName);

                        caseFtpUrl caseFtpUrlEneity = new caseFtpUrl();
                        caseFtpUrlEneity.setCaseId(limsCaseInfo.getCaseId());
                        caseFtpUrlEneity.setType(7);
                        List<caseFtpUrl> caseFtpUrlList = caseFtpUrlMapper.findCaseIdByTyye(caseFtpUrlEneity);
                        if(caseFtpUrlList.size() >0){
                            String docpath="ftp://" + ftpIp + "/" + ftpCaseFilePath + "/"+filePathName;
                            caseFtpUrl caseFtpUrlUpdate = new caseFtpUrl();
                            caseFtpUrlUpdate.setId(caseFtpUrlList.get(0).getId());
                            caseFtpUrlUpdate.setCaseId(caseFtpUrlList.get(0).getCaseId());
                            caseFtpUrlUpdate.setType(7);
                            caseFtpUrlUpdate.setFtpUrl(docpath);
                            caseFtpUrlMapper.updateByPrimaryKey(caseFtpUrlUpdate);

                        }else{
                            caseFtpUrl caseFtpUrls = new caseFtpUrl();
                            caseFtpUrls.setId(UUID.randomUUID().toString());
                            caseFtpUrls.setType(7);
                            caseFtpUrls.setCaseId(limsCaseInfo.getCaseId());
                            String docpath="ftp://" + ftpIp + "/" + ftpCaseFilePath + "/"+filePathName;
                            caseFtpUrls.setFtpUrl(docpath);
                            caseFtpUrlMapper.insert(caseFtpUrls);
                        }
                    }
                }
                List<Map<String, Object>> recordSyList = (List)recordMap.get("recordSyList");
                if (ListUtils.isNotNullAndEmptyList(recordSyList)) {
                    for (Map<String, Object> mapSy : recordSyList) {
                        String filePathName = (String) this.compressedFilesSyNew(request, response, limsCaseInfo,"2","1", (String)mapSy.get("syId")).get("filePathName");
                        fileList.add(filePathName);

                        caseFtpUrl caseFtpUrlEneity = new caseFtpUrl();
                        caseFtpUrlEneity.setCaseId(limsCaseInfo.getCaseId());
                        caseFtpUrlEneity.setType(8);
                        List<caseFtpUrl> caseFtpUrlList = caseFtpUrlMapper.findCaseIdByTyye(caseFtpUrlEneity);
                        if(caseFtpUrlList.size() >0){
                            String docpath="ftp://" + ftpIp + "/" + ftpCaseFilePath + "/"+filePathName;
                            caseFtpUrl caseFtpUrlUpdate = new caseFtpUrl();
                            caseFtpUrlUpdate.setId(caseFtpUrlList.get(0).getId());
                            caseFtpUrlUpdate.setCaseId(caseFtpUrlList.get(0).getCaseId());
                            caseFtpUrlUpdate.setType(8);
                            caseFtpUrlUpdate.setFtpUrl(docpath);
                            caseFtpUrlMapper.updateByPrimaryKey(caseFtpUrlUpdate);

                        }else{
                            caseFtpUrl caseFtpUrls = new caseFtpUrl();
                            caseFtpUrls.setId(UUID.randomUUID().toString());
                            caseFtpUrls.setType(8);
                            caseFtpUrls.setCaseId(limsCaseInfo.getCaseId());
                            String docpath="ftp://" + ftpIp + "/" + ftpCaseFilePath + "/"+filePathName;
                            caseFtpUrls.setFtpUrl(docpath);
                            caseFtpUrlMapper.insert(caseFtpUrls);
                        }
                    }
                }

                String ss = "";

                if(fileList.size() >0){
                    for(int i =0;i < fileList.size();i++){
                        ss += fileList.get(i) + ",";
                    }
                }

                String s = CasePrintFtpFileUtils.httpLinkNew(request, ftpIp, page, ftpUser, ftpPassword, ftpCaseFilePath,ss);


                map.put("success", true);
            }else {
                map.put("success", false);
            }
        }catch (Exception e) {
            e.getStackTrace();
            map.put("success", false);
        }

        return map;
    }
    /**
     * 生成提取实验记录表-压缩-打印
     *
     * @param
     * @return
     */
    @RequestMapping("/compressedFilesTq")
    @ResponseBody
    public Map<String, Object> compressedFilesTq(HttpServletRequest request, HttpServletResponse response, LimsCaseInfo caseInfo,String flag,String fullPrint) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> params = new HashMap<String, Object>();
        Writer out = null;
        String filePathName = null;


        DictItem dictItem = new DictItem();

        //提取实验方法List
        dictItem.setDictTypeCode(Constants.EXTRACTION_TEST_METHOD);
        List<DictItem> extractTestMethodList = DictUtil.getDictList(dictItem);
        params.put("extractTestMethodList", extractTestMethodList);

        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if (operateUser == null) {

        }
        AmPersonalInfo amPersonalInfo = amPersonalInfoService.queryAmPersonalInfo(operateUser.getPersonalId());
        if (StringUtils.isNotBlank(amPersonalInfo.getOrgId()) && amPersonalInfo.getOrgId().contains("110023")) {
            amPersonalInfo.setOrgId("110230000000");
        } else {
            amPersonalInfo.setOrgId(operateUser.getOrgId());
        }

        //获取单位信息
        OrgInfo orgInfo = orgInfoService.selectByPrimaryKey(operateUser.getOrgId());
        String oraliQualification = null;
        if (operateUser.getOrgId() != null) {
            oraliQualification = orgInfo.getOrgQualification();
        }

        //获取当前用户的id
        String userOrgId = operateUser.getOrgId();
        //将当前用户org_id设置进query
        if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
            userOrgId = "110230000000";
        }

        //根据案件id查取检材
        List<LimsSampleInfoDna> limsSampleInfoDnaList = sampleInfoDnaService.selectByCaseId(caseInfo.getCaseId());
        List<LabExtractSample> labExtractSampleLists = new ArrayList<>();
        int countindex = 0;
        for (LimsSampleInfoDna lsid : limsSampleInfoDnaList) {
            List<LabExtractSample> labExtractSampleList = labExtractSampleService.selectLabExtractSampleBySampleId(lsid.getSampleId());
            //把检材的扩增信息放入到list集合中
            if (ListUtils.isNotNullAndEmptyList(labExtractSampleList)) {
                labExtractSampleLists.add(countindex, labExtractSampleList.get(0));
                countindex++;
            }
        }


        String path = null;
        //获取Info信息
        LabExtractInfo labExtractInfo = new LabExtractInfo();
        if(labExtractSampleLists.size() > 0){
            labExtractInfo = labExtractInfoService.selectByPrimaryKey(labExtractSampleLists.get(0).getExtractId());
        }


        if(labExtractInfo != null){
            params.put("labExtractInfo", labExtractInfo);

            //获取设备
            List<EquipmentNameInfo> equipmentNameInfoArrayList = new ArrayList<>();
            if (labExtractInfo.getEquipment() != null) {
                String[] equipmentNo = labExtractInfo.getEquipment().split(",");
                for (int i = 0; i < equipmentNo.length; i++) {
                    List<EquipmentNameInfo> equipmentNameInfo = equipmentNameInfoService.selectEquipmentNo(equipmentNo[i]);

                    if (equipmentNameInfo != null) {
                        for (int j = 0; j < equipmentNameInfo.size(); j++) {
                            if (equipmentNameInfo.get(j).getOrgId() == null && amPersonalInfo.getOrgId() == null) {
                                equipmentNameInfoArrayList.add(equipmentNameInfo.get(j));
                            } else if (equipmentNameInfo.get(j).getOrgId().equals(amPersonalInfo.getOrgId())) {
                                equipmentNameInfoArrayList.add(equipmentNameInfo.get(j));
                            }
                        }
                    }
                }
                params.put("equipmentNameInfoArrayList", equipmentNameInfoArrayList);
            }

            //获取试剂
            List<ReagentInfo> panelList = new ArrayList<>();
            if (labExtractInfo.getKit() != null) {
                String[] kitNo = labExtractInfo.getKit().split(",");
                List<ReagentInfo> nameList = reagentInfoService.selectAll();
                for (int i = 0; i < nameList.size(); i++) {
                    for (int j = 0; j < kitNo.length; j++) {
                        if (nameList.get(i).getId().equals(kitNo[j])) {
                            panelList.add(nameList.get(i));
                        }
                    }
                }
                if (panelList.size() > 0) {
                    for (int k = 0; k < panelList.size(); k++) {
                        ReagentOutgoInfo reagentOutgoInfo = new ReagentOutgoInfo();
                        reagentOutgoInfo.setReagentId(panelList.get(k).getId());
                        reagentOutgoInfo.setOrgId(panelList.get(k).getOrgId());
                        List<ReagentOutgoInfo> reagentOutgoInfoList = reagentOutgoInfoService.selectreagentOutgoList(reagentOutgoInfo);
                        if (reagentOutgoInfoList.size() > 0) {
                            panelList.get(k).setBatchNumber(reagentOutgoInfoList.get(0).getBatchNumber());
                            panelList.get(k).setValidityTime(reagentOutgoInfoList.get(0).getEffectiveDatetime());
                        }
                    }
                }
                params.put("panelList", panelList);
            }

            //试剂信息
            ReagentInfo reagentInfo = new ReagentInfo();
            reagentInfo.setExperimentalStage(Constants.EXPERIMENTAL_STAGE_01);
            reagentInfo.setOrgId(userOrgId);
            List<ReagentInfo> reagentInfoList = reagentInfoService.selectList(reagentInfo);
            params.put("reagentInfoList", reagentInfoList);

            //试剂
            if (labExtractInfo.getKit() != null && labExtractInfo.getKit() != "") {
                String[] kits = labExtractInfo.getKit().split(",");
                if(kits.length>0){
                    String kit = kits[0];
                    List<ReagentInfo> nameList = reagentInfoService.selectAll();
                    for (int i = 0; i < nameList.size(); i++) {
                        if (nameList.get(i).getId().equals(kit)) {
                            labExtractInfo.setKit(nameList.get(i).getReagentName());
                            labExtractInfo.setUpdateDatetime(nameList.get(i).getValidityTime());
                            labExtractInfo.setUpdatePerson(nameList.get(i).getBatchNumber());
                        }
                    }

                }

            }

            //设备
            if (labExtractInfo.getEquipment() != null && labExtractInfo.getEquipment() != "") {
                String[] equi = labExtractInfo.getEquipment().split(",");
                String equipment = equi[0];
                List<EquipmentTypeInfo> equipmentTypeInfoList = equipmentTypeInfoService.selectAllList();
                for (int i = 0; i < equipmentTypeInfoList.size(); i++) {
                    if (equipmentTypeInfoList.get(i).getId().equals(equipment)) {
                        labExtractInfo.setEquipment(equipmentTypeInfoList.get(i).getEquipmentTypeName());
                    }
                }
            }

            labExtractInfo.setExtractDatetime(labExtractInfo.getCreateDatetime());
            params.put("labExtractInfo", labExtractInfo);

            if (labExtractInfo != null) {
                if (StringUtils.isNotEmpty(labExtractInfo.getOrgId())) {
                    ExperimentalParameter exp = new ExperimentalParameter();
                    exp.setOrgId(labExtractInfo.getOrgId());
                    exp.setExperimentalType("CONVENTIONAL");//常规提取
                    List<ExperimentalParameter> experimentalParameters = experimentalParameterService.selectList(exp);
                    if (ListUtils.isNotNullAndEmptyList(experimentalParameters)) {
                        ExperimentalParameter experimentalParameter = experimentalParameters.get(0);
                        String orgNameSp = experimentalParameter.getParameterName();
                        params.put("orgNameSp", StringUtils.isBlank(orgNameSp) ? "" : orgNameSp);
                    }
                }
            }

            if (labExtractSampleLists.size() > 0) {
                for (int j = 0; j < labExtractSampleLists.size(); j++) {
                    if (StringUtils.isNotBlank(labExtractSampleLists.get(j).getExtractMethod())) {
                        UseInstruments useInstrumentsEntity = new UseInstruments();
                        useInstrumentsEntity.setMethodName(labExtractSampleLists.get(j).getExtractMethod());
                        useInstrumentsEntity.setOrgCode(operateUser.getOrgId());
                        List<UseInstruments> useInstrumentsList = useInstrumentsService.findByExtractMentod(useInstrumentsEntity);
                        if (useInstrumentsList.size() > 0) {
                            UseInstruments useInstruments = useInstrumentsList.get(0);
                            labExtractSampleLists.get(j).setLeave(useInstruments.getLeave());
                            labExtractSampleLists.get(j).setLeaveTwo(useInstruments.getLeavetwo());
                            labExtractSampleLists.get(j).setBath(useInstruments.getBath());
                            labExtractSampleLists.get(j).setBathTwo(useInstruments.getBathtwo());
                            labExtractSampleLists.get(j).setDry(useInstruments.getDry());
                            labExtractSampleLists.get(j).setDryTwo(useInstruments.getDrytwo());
                            labExtractSampleLists.get(j).setShake(useInstruments.getShake());
                            labExtractSampleLists.get(j).setEarthquake(useInstruments.getEarthquake());
                        }
                    }
                }
            }

            List<LabExtractSample> tweLabExtractSampleList = new ArrayList<>();
            List<LabExtractSample> labExtractSampleList = new ArrayList<>();
            List<List<LabExtractSample>> listList = new ArrayList<>();
            //填充13条记录
            LabExtractSample labExtractSample = new LabExtractSample();
            labExtractSample.setExtractMethod("  ");
            labExtractSample.setLeave("  ");
            labExtractSample.setBath("  ");
            labExtractSample.setDry("  ");
            labExtractSample.setShake("  ");
            labExtractSample.setEarthquake("  ");
            labExtractSample.setLimsSampleInfoDna(new LimsSampleInfoDna());
            labExtractSample.getLimsSampleInfoDna().setSampleNo("   ");
            labExtractSample.getLimsSampleInfoDna().setSampleName("  ");

            LinkedHashSet<List<LabExtractSample>> allList = new LinkedHashSet<List<LabExtractSample>>();
            if (null != labExtractSampleLists) {
                for (LabExtractSample bean : labExtractSampleLists) {
                    List<LabExtractSample> greplist = new ArrayList<LabExtractSample>();
                    for (LabExtractSample bean2 : labExtractSampleLists) {
                        if (bean.getLimsSampleInfoDna().getCaseId().equals(bean2.getLimsSampleInfoDna().getCaseId())) {
                            System.out.println("bena1" + bean.getLimsSampleInfoDna().getCaseId() + "-samp-" + bean.getId());
                            System.out.println("bena2" + bean2.getLimsSampleInfoDna().getCaseId() + "-samp-" + bean2.getId());
                            greplist.add(bean2);
                        }
                    }
                    allList.add(greplist);
                }
            }
            params.put("allList", allList);

            for (List<LabExtractSample> bean : allList) {

                if (bean.size() > 14) {
                    tweLabExtractSampleList = bean.subList(14, bean.size());

                    int sizePage = (int) Math.ceil((double) tweLabExtractSampleList.size() / (double) 21);
                    for (int i = 0; i < sizePage; i++) {
                        int j;
                        if (i < sizePage - 1) {
                            j = (i + 1) * 21;
                        } else {
                            j = tweLabExtractSampleList.size();
                        }
                        int k = i;
                        if (i > 0 && i < sizePage) {
                            k = i * 21;
                        }
                        if (j <= tweLabExtractSampleList.size()) {
                            List<LabExtractSample> oneLabExtractSampleList = tweLabExtractSampleList.subList(k, j);
                            listList.add(oneLabExtractSampleList);
                        }

                    }

                    labExtractSampleList = bean.subList(0, 14);
                    params.put("labExtractSampleList", labExtractSampleList);
                } else {
                    int longs = 14 - bean.size();
                    for (int i = 0; i < longs; i++) {
                        bean.add(labExtractSample);
                    }
                    params.put("labExtractSampleList", bean);
                }


            }
            params.put("listList", listList);

            /**
             * 常规提取记编号--liuchang
             */
           if (StringUtils.isNotBlank(operateUser.getOrgId())) {
               List<QcNoSettings> newQcNoSettings = qcNoSettingsService.selectByLab(operateUser.getOrgId());
               Calendar date = Calendar.getInstance(); //获取年份
               if (newQcNoSettings != null && !newQcNoSettings.isEmpty()) {
                   for (QcNoSettings qcNoSettings : newQcNoSettings) {
                       if (qcNoSettings.getDictCode().equals(Constants.ROUTINE_EXTRACTION_RECORDS_NO)) {
                           String routineExtractionNo = qcNoSettings.getLabServerHeader() + "-" + String.valueOf(date.get(Calendar.YEAR)) + "-" + qcNoSettings.getBarcode();
                           params.put("routineExtractionNo", StringUtils.isBlank(routineExtractionNo) ? "" : routineExtractionNo);
                       }
                   }
               }
           }

//        //根据案件id查取检材
//        List<LimsSampleInfoDna> limsSampleInfoDnaList = sampleInfoDnaService.selectByCaseId(caseInfo.getCaseId());
//
//        Subject subject = SecurityUtils.getSubject();
//        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
//        if(operateUser == null){
//
//        }
//        AmPersonalInfo amPersonalInfo =amPersonalInfoService.queryAmPersonalInfo(operateUser.getPersonalId());
//
//        List<LabExtractSample> labExtractSampleLists = new ArrayList<>();
//        int countindex = 0;
//        if(limsSampleInfoDnaList.size()>0){
//            for (LimsSampleInfoDna lsid : limsSampleInfoDnaList) {
//                List<LabExtractSample> labExtractSampleList = labExtractSampleService.selectLabExtractSampleBySampleId(lsid.getSampleId());
//                //把检材的扩增信息放入到list集合中
//                if (ListUtils.isNotNullAndEmptyList(labExtractSampleList)) {
//                    labExtractSampleLists.add(countindex, labExtractSampleList.get(0));
//                    countindex++;
//                }
//            }
//            DictItem dictItem = new DictItem();
//            //提取实验方法List
//            dictItem.setDictTypeCode(Constants.EXTRACTION_TEST_METHOD);
//            List<DictItem> extractTestMethodList = DictUtil.getDictList(dictItem);
//            pcrmap.put("extractTestMethodList", extractTestMethodList);
//
//            //获取Info信息
//            LabExtractInfo labExtractInfo = labExtractInfoService.selectByPrimaryKey(labExtractSampleLists.get(0).getExtractId());
//            pcrmap.put("labExtractInfo", labExtractInfo);
//
//            //获取设备
//            List<EquipmentNameInfo> equipmentNameInfoArrayList = new ArrayList<>();
//            if (labExtractInfo.getEquipment() != null) {
//                String[] equipmentNo = labExtractInfo.getEquipment().split(",");
//                for (int i = 0; i < equipmentNo.length; i++) {
//                    List<EquipmentNameInfo> equipmentNameInfo = equipmentNameInfoService.selectEquipmentNo(equipmentNo[i]);
//                    if (equipmentNameInfo != null) {
//                        for (int j = 0; j < equipmentNameInfo.size(); j++) {
//                            if(equipmentNameInfo.get(j).getOrgId() == null && amPersonalInfo.getOrgId() == null){
//                                equipmentNameInfoArrayList.add(equipmentNameInfo.get(j));
//                            }else if(equipmentNameInfo.get(j).getOrgId().equals(amPersonalInfo.getOrgId())){
//                                equipmentNameInfoArrayList.add(equipmentNameInfo.get(j));
//                            }
//                        }
//                    }
//                }
//                pcrmap.put("equipmentNameInfoArrayList", equipmentNameInfoArrayList);
//            }
//
//            //获取试剂盒
//            List<Panel> panelList = new ArrayList<>();
//            if (labExtractInfo.getKit() != null) {
//                String[] kitNo = labExtractInfo.getKit().split(",");
//                for (int i = 0; i < kitNo.length; i++) {
//                    Panel panel = panelService.selectByPrimaryKey(kitNo[i]);
//                    panelList.add(panel);
//                }
////            params.put("panelList",panelList);
//            }
//
//
//            //获取sample DNA信息
//            List<LabExtractSample> tweLabExtractSampleList = new ArrayList<>();
//            List<LabExtractSample> labExtractSampleList = new ArrayList<>();
//            List<List<LabExtractSample>> listList = new ArrayList<>();
//            //填充13条记录
//            LabExtractSample labExtractSample = new LabExtractSample();
//            labExtractSample.setExtractMethod("  ");
//            labExtractSample.setLeave("  ");
//            labExtractSample.setBath("  ");
//            labExtractSample.setDry("  ");
//            labExtractSample.setShake("  ");
//            labExtractSample.setEarthquake("  ");
//            labExtractSample.setLimsSampleInfoDna(new LimsSampleInfoDna());
//            labExtractSample.getLimsSampleInfoDna().setSampleNo("   ");
//            labExtractSample.getLimsSampleInfoDna().setSampleName("  ");
//
//            if (labExtractSampleLists.size() > 13) {
//                tweLabExtractSampleList = labExtractSampleLists.subList(13, labExtractSampleLists.size());
//
//                int sizePage = (int) Math.ceil((double) tweLabExtractSampleList.size() / (double) 20);
//                for (int i = 0; i < sizePage; i++) {
//                    int j;
//                    if (i < sizePage - 1) {
//                        j = (i + 1) * 20;
//                    } else {
//                        j = tweLabExtractSampleList.size();
//                    }
//                    int k = i;
//                    if (i > 0 && i < sizePage) {
//                        k = i * 20;
//                    }
//                    if (j <= tweLabExtractSampleList.size()) {
//                        List<LabExtractSample> oneLabExtractSampleList = tweLabExtractSampleList.subList(k, j);
//                        listList.add(oneLabExtractSampleList);
//                    }
//
//                }
//
//                labExtractSampleList = labExtractSampleLists.subList(0, 13);
//                pcrmap.put("labExtractSampleList", labExtractSampleList);
//            } else {
//                int longs = 13 - labExtractSampleLists.size();
//                for (int i = 0; i < longs; i++) {
//                    labExtractSampleLists.add(labExtractSample);
//                }
//                pcrmap.put("labExtractSampleList", labExtractSampleLists);
//            }
//            pcrmap.put("listList", listList);



            try {
                Configuration cfg = new Configuration();
                cfg.setDefaultEncoding("UTF-8");
                cfg.setClassForTemplateLoading(this.getClass(), "/templates");
                //获取模板
                //获取模板
                String ftlName = "recordTabletest.ftl";

                if(labExtractSampleLists.size() >0){
                    if("D".equals(labExtractSampleLists.get(0).getExtractMethod())) {
                        ftlName ="recordTableAutomatic.ftl";
                    }
                }

                Template tmp = cfg.getTemplate(ftlName, "UTF-8");

                //String filename = "DNA常规提取记录表" + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".doc";
                String filename =  UuidUtil.generateUUID()+ "-" + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".doc";

                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(getGeneratePath(caseInfo, filename)), "UTF-8"));
                tmp.process(params, out);
                out.flush();
                out.close();
                filePathName = getGeneratePath(caseInfo, filename);
                //案件打印 调用案件打印方法
                //根据flag判断是否选中的是打印按钮
                if(flag.equals("2")){
                    String page="1";
                    if(Strings.isNotBlank(filePathName)){

                        if("1".equals(fullPrint)){
                            path = CasePrintFtpFileUtils.uploadFtpFileNew(ftpIp, ftpPort, ftpUser, ftpPassword, ftpCaseFilePath, filePathName, request, page,fullPrint);
                            System.out.println(path.toString());
                        }else{
                            CasePrintFtpFileUtils.uploadFtpFileNew(ftpIp, ftpPort, ftpUser, ftpPassword, ftpFilePath, filePathName, request, page,fullPrint);
                        }

//                        this.httpLink(request,page,filePathName);


                    }
                }
            } catch (Exception ex) {
                logger.error("下载错误！", ex);
            }

        }

        if("1".equals(fullPrint)){
            result.put("filePathName", path);
        }else{
            if(!"".equals(filePathName) && filePathName != null){
                result.put("filePathName", filePathName);
            }
        }
        return result;
    }

    public Map<String, Object> compressedFilesTqNew(HttpServletRequest request, HttpServletResponse response, LimsCaseInfo caseInfo,String flag,String fullPrint, String extractId) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> params = new HashMap<String, Object>();
        Writer out = null;
        String filePathName = null;


        DictItem dictItem = new DictItem();

        //提取实验方法List
        dictItem.setDictTypeCode(Constants.EXTRACTION_TEST_METHOD);
        List<DictItem> extractTestMethodList = DictUtil.getDictList(dictItem);
        params.put("extractTestMethodList", extractTestMethodList);

        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if (operateUser == null) {

        }
        AmPersonalInfo amPersonalInfo = amPersonalInfoService.queryAmPersonalInfo(operateUser.getPersonalId());
        if (StringUtils.isNotBlank(amPersonalInfo.getOrgId()) && amPersonalInfo.getOrgId().contains("110023")) {
            amPersonalInfo.setOrgId("110230000000");
        } else {
            amPersonalInfo.setOrgId(operateUser.getOrgId());
        }

        //获取单位信息
        OrgInfo orgInfo = orgInfoService.selectByPrimaryKey(operateUser.getOrgId());
        String oraliQualification = null;
        if (operateUser.getOrgId() != null) {
            oraliQualification = orgInfo.getOrgQualification();
        }

        //获取当前用户的id
        String userOrgId = operateUser.getOrgId();
        //将当前用户org_id设置进query
        if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
            userOrgId = "110230000000";
        }

        List<LabExtractSample> extractSampleList = labExtractSampleService.selectByExtractId(extractId);
        List<LabExtractSample> labExtractSampleLists = new ArrayList<>();
        int countindex = 0;
        //把检材的提取信息放入到list集合中
        boolean isSpotRecord = false;
        if (ListUtils.isNotNullAndEmptyList(extractSampleList)) {
            String sampleType = "";
            for (LabExtractSample extractSample : extractSampleList) {
                if (caseInfo.getCaseId().equals(extractSample.getCaseId())) {
                    List<LimsSampleInfoDna> sampleInfoDnaList = limsSampleInfoDnaService.selectBySampleId(extractSample.getSampleId());
                    LimsSampleInfoDna sampleInfoDna = new LimsSampleInfoDna();
                    if (ListUtils.isNotNullAndEmptyList(sampleInfoDnaList)) {
                        sampleInfoDna = sampleInfoDnaList.get(0);
                        extractSample.setLimsSampleInfoDna(sampleInfoDna);
                        if (Constants.SAMPLE_TYPE_02.equals(sampleInfoDna.getSampleType())) {
                            sampleType += sampleInfoDna.getSampleType() + "、";
                        }
                    }
                    labExtractSampleLists.add(countindex, extractSample);
                    countindex++;
                }
            }
            //判断此记录表是否为精斑提取记录表
            if (StringUtils.isNotBlank(sampleType) && sampleType.contains("、")) {
                String[] sampleTypeStr = sampleType.split("、");
                if (sampleTypeStr.length == extractSampleList.size()) {
                    isSpotRecord = true;
                }
            }
        }

        LabExtractInfo labExtractInfo = labExtractInfoService.selectByPrimaryKey(extractId);
        String path = null;
        if(labExtractInfo != null){
            params.put("labExtractInfo", labExtractInfo);

            //获取设备
            List<EquipmentNameInfo> equipmentNameInfoArrayList = new ArrayList<>();
            if (labExtractInfo.getEquipment() != null) {
                String[] equipmentNo = labExtractInfo.getEquipment().split(",");
                for (int i = 0; i < equipmentNo.length; i++) {
                    List<EquipmentNameInfo> equipmentNameInfo = equipmentNameInfoService.selectEquipmentNo(equipmentNo[i]);

                    if (equipmentNameInfo != null) {
                        for (int j = 0; j < equipmentNameInfo.size(); j++) {
                            if (equipmentNameInfo.get(j).getOrgId() == null && amPersonalInfo.getOrgId() == null) {
                                equipmentNameInfoArrayList.add(equipmentNameInfo.get(j));
                            } else if (equipmentNameInfo.get(j).getOrgId().equals(amPersonalInfo.getOrgId())) {
                                equipmentNameInfoArrayList.add(equipmentNameInfo.get(j));
                            }
                        }
                    }
                }
                params.put("equipmentNameInfoArrayList", equipmentNameInfoArrayList);
            }

            //获取试剂
            List<ReagentInfo> panelList = new ArrayList<>();
            if (labExtractInfo.getKit() != null) {
                String[] kitNo = labExtractInfo.getKit().split(",");
                List<ReagentInfo> nameList = reagentInfoService.selectAll();
                for (int i = 0; i < nameList.size(); i++) {
                    for (int j = 0; j < kitNo.length; j++) {
                        if (nameList.get(i).getId().equals(kitNo[j])) {
                            panelList.add(nameList.get(i));
                        }
                    }
                }
                if (panelList.size() > 0) {
                    for (int k = 0; k < panelList.size(); k++) {
                        ReagentOutgoInfo reagentOutgoInfo = new ReagentOutgoInfo();
                        reagentOutgoInfo.setReagentId(panelList.get(k).getId());
                        reagentOutgoInfo.setOrgId(panelList.get(k).getOrgId());
                        List<ReagentOutgoInfo> reagentOutgoInfoList = reagentOutgoInfoService.selectreagentOutgoList(reagentOutgoInfo);
                        if (reagentOutgoInfoList.size() > 0) {
                            panelList.get(k).setBatchNumber(reagentOutgoInfoList.get(0).getBatchNumber());
                            panelList.get(k).setValidityTime(reagentOutgoInfoList.get(0).getEffectiveDatetime());
                        }
                    }
                }
                params.put("panelList", panelList);
            }

            //试剂信息
            ReagentInfo reagentInfo = new ReagentInfo();
            reagentInfo.setExperimentalStage(Constants.EXPERIMENTAL_STAGE_01);
            reagentInfo.setOrgId(userOrgId);
            List<ReagentInfo> reagentInfoList = reagentInfoService.selectList(reagentInfo);
            params.put("reagentInfoList", reagentInfoList);

            //试剂
            if (labExtractInfo.getKit() != null && labExtractInfo.getKit() != "") {
                String[] kits = labExtractInfo.getKit().split(",");
                if(kits.length>0){
                    String kit = kits[0];
                    List<ReagentInfo> nameList = reagentInfoService.selectAll();
                    for (int i = 0; i < nameList.size(); i++) {
                        if (nameList.get(i).getId().equals(kit)) {
                            labExtractInfo.setKit(nameList.get(i).getReagentName());
                            labExtractInfo.setUpdateDatetime(nameList.get(i).getValidityTime());
                            labExtractInfo.setUpdatePerson(nameList.get(i).getBatchNumber());
                        }
                    }

                }

            }

            //设备
            if (labExtractInfo.getEquipment() != null && labExtractInfo.getEquipment() != "") {
                String[] equi = labExtractInfo.getEquipment().split(",");
                String equipment = equi[0];
                List<EquipmentTypeInfo> equipmentTypeInfoList = equipmentTypeInfoService.selectAllList();
                for (int i = 0; i < equipmentTypeInfoList.size(); i++) {
                    if (equipmentTypeInfoList.get(i).getId().equals(equipment)) {
                        labExtractInfo.setEquipment(equipmentTypeInfoList.get(i).getEquipmentTypeName());
                    }
                }
            }

            labExtractInfo.setExtractDatetime(labExtractInfo.getCreateDatetime());
            params.put("labExtractInfo", labExtractInfo);

            if (labExtractInfo != null) {
                if (StringUtils.isNotEmpty(labExtractInfo.getOrgId())) {
                    ExperimentalParameter exp = new ExperimentalParameter();
                    exp.setOrgId(labExtractInfo.getOrgId());
                    exp.setExperimentalType("CONVENTIONAL");//常规提取
                    List<ExperimentalParameter> experimentalParameters = experimentalParameterService.selectList(exp);
                    if (ListUtils.isNotNullAndEmptyList(experimentalParameters)) {
                        ExperimentalParameter experimentalParameter = experimentalParameters.get(0);
                        String orgNameSp = experimentalParameter.getParameterName();
                        params.put("orgNameSp", StringUtils.isBlank(orgNameSp) ? "" : orgNameSp);
                    }
                }
            }

            if (labExtractSampleLists.size() > 0) {
                for (int j = 0; j < labExtractSampleLists.size(); j++) {
                    if (StringUtils.isNotBlank(labExtractSampleLists.get(j).getExtractMethod())) {
                        UseInstruments useInstrumentsEntity = new UseInstruments();
                        useInstrumentsEntity.setMethodName(labExtractSampleLists.get(j).getExtractMethod());
                        useInstrumentsEntity.setOrgCode(operateUser.getOrgId());
                        List<UseInstruments> useInstrumentsList = useInstrumentsService.findByExtractMentod(useInstrumentsEntity);
                        if (useInstrumentsList.size() > 0) {
                            UseInstruments useInstruments = useInstrumentsList.get(0);
                            labExtractSampleLists.get(j).setLeave(useInstruments.getLeave());
                            labExtractSampleLists.get(j).setLeaveTwo(useInstruments.getLeavetwo());
                            labExtractSampleLists.get(j).setBath(useInstruments.getBath());
                            labExtractSampleLists.get(j).setBathTwo(useInstruments.getBathtwo());
                            labExtractSampleLists.get(j).setDry(useInstruments.getDry());
                            labExtractSampleLists.get(j).setDryTwo(useInstruments.getDrytwo());
                            labExtractSampleLists.get(j).setShake(useInstruments.getShake());
                            labExtractSampleLists.get(j).setEarthquake(useInstruments.getEarthquake());
                        }
                    }
                }
            }

            List<LabExtractSample> tweLabExtractSampleList = new ArrayList<>();
            List<LabExtractSample> labExtractSampleList = new ArrayList<>();
            List<List<LabExtractSample>> listList = new ArrayList<>();
            //填充13条记录
            LabExtractSample labExtractSample = new LabExtractSample();
            labExtractSample.setExtractMethod("  ");
            labExtractSample.setLeave("  ");
            labExtractSample.setBath("  ");
            labExtractSample.setDry("  ");
            labExtractSample.setShake("  ");
            labExtractSample.setEarthquake("  ");
            labExtractSample.setLimsSampleInfoDna(new LimsSampleInfoDna());
            labExtractSample.getLimsSampleInfoDna().setSampleNo("   ");
            labExtractSample.getLimsSampleInfoDna().setSampleName("  ");

            LinkedHashSet<List<LabExtractSample>> allList = new LinkedHashSet<List<LabExtractSample>>();
            /*if (null != labExtractSampleLists) {
                for (LabExtractSample bean : labExtractSampleLists) {
                    List<LabExtractSample> greplist = new ArrayList<LabExtractSample>();
                    for (LabExtractSample bean2 : labExtractSampleLists) {
                        if (bean.getLimsSampleInfoDna().getCaseId().equals(bean2.getLimsSampleInfoDna().getCaseId())) {
                            System.out.println("bena1" + bean.getLimsSampleInfoDna().getCaseId() + "-samp-" + bean.getId());
                            System.out.println("bena2" + bean2.getLimsSampleInfoDna().getCaseId() + "-samp-" + bean2.getId());
                            greplist.add(bean2);
                        }
                    }
                    allList.add(greplist);
                }
            }*/
            allList.add(labExtractSampleLists);
            params.put("allList", allList);

            for (List<LabExtractSample> bean : allList) {

                if (bean.size() > 14) {
                    tweLabExtractSampleList = bean.subList(14, bean.size());

                    int sizePage = (int) Math.ceil((double) tweLabExtractSampleList.size() / (double) 21);
                    for (int i = 0; i < sizePage; i++) {
                        int j;
                        if (i < sizePage - 1) {
                            j = (i + 1) * 21;
                        } else {
                            j = tweLabExtractSampleList.size();
                        }
                        int k = i;
                        if (i > 0 && i < sizePage) {
                            k = i * 21;
                        }
                        if (j <= tweLabExtractSampleList.size()) {
                            List<LabExtractSample> oneLabExtractSampleList = tweLabExtractSampleList.subList(k, j);
                            listList.add(oneLabExtractSampleList);
                        }

                    }

                    labExtractSampleList = bean.subList(0, 14);
                    params.put("labExtractSampleList", labExtractSampleList);
                } else {
                    int longs = 14 - bean.size();
                    for (int i = 0; i < longs; i++) {
                        bean.add(labExtractSample);
                    }
                    params.put("labExtractSampleList", bean);
                }


            }
            params.put("listList", listList);

            //自动提取实验检材列表
            List<LabListModel> labListModelList = new ArrayList<>();
            if(allList.size()>0 && null != allList){
                for (List<LabExtractSample> samples : allList){
                    LabListModel model = new LabListModel();
                    model.setLabExtractSampleList(samples);
                    labListModelList.add(model);
                }
            }

            List<LabListModel> labList = new ArrayList<>();
            for (LabListModel bean : labListModelList) {
                tweLabExtractSampleList = new ArrayList<>();
                labExtractSampleList = new ArrayList<>();
                if (bean.getLabExtractSampleList().size() > 14) {
                    listList = new ArrayList<>();
                    tweLabExtractSampleList = bean.getLabExtractSampleList().subList(14, bean.getLabExtractSampleList().size());
                    int sizePage = (int) Math.ceil((double) tweLabExtractSampleList.size() / (double) 20);
                    for (int i = 0; i < sizePage; i++) {
                        int j;
                        if (i < sizePage - 1) {
                            j = (i + 1) * 20;
                        } else {
                            j = tweLabExtractSampleList.size();
                        }
                        int k = i;
                        if (i > 0 && i < sizePage) {
                            k = i * 20;
                        }
                        if (j <= tweLabExtractSampleList.size()) {
                            List<LabExtractSample> oneLabExtractSampleList = new ArrayList<>();
                            oneLabExtractSampleList.addAll(tweLabExtractSampleList.subList(k, j));
                            if (ListUtils.isNotNullAndEmptyList(oneLabExtractSampleList)){
                                if (oneLabExtractSampleList.size()<20){
                                    labExtractSample = new LabExtractSample();
                                    labExtractSample.setExtractMethod("  ");
                                    labExtractSample.setLeave("  ");
                                    labExtractSample.setBath("  ");
                                    labExtractSample.setDry("  ");
                                    labExtractSample.setShake("  ");
                                    labExtractSample.setEarthquake("  ");
                                    labExtractSample.setLimsSampleInfoDna(new LimsSampleInfoDna());
                                    labExtractSample.getLimsSampleInfoDna().setSampleNo("   ");
                                    labExtractSample.getLimsSampleInfoDna().setSampleName("  ");
                                    int size = 20 - oneLabExtractSampleList.size();
                                    for (int x = 0; x <  size ; x++) {
                                        oneLabExtractSampleList.add(labExtractSample);
                                    }
                                }
                            }
                            listList.add(oneLabExtractSampleList);
                            bean.setListList(listList);
                        }
                    }
                    bean.setLabExtractSampleList(bean.getLabExtractSampleList().subList(0, 14));
                    labList.add(bean);
                } else {
                    labExtractSample = new LabExtractSample();
                    labExtractSample.setExtractMethod("  ");
                    labExtractSample.setLeave("  ");
                    labExtractSample.setBath("  ");
                    labExtractSample.setDry("  ");
                    labExtractSample.setShake("  ");
                    labExtractSample.setEarthquake("  ");
                    labExtractSample.setLimsSampleInfoDna(new LimsSampleInfoDna());
                    labExtractSample.getLimsSampleInfoDna().setSampleNo("   ");
                    labExtractSample.getLimsSampleInfoDna().setSampleName("  ");
                    int longs = 14 - bean.getLabExtractSampleList().size();
                    for (int i = 0; i < longs; i++) {
                        bean.getLabExtractSampleList().add(labExtractSample);
                    }
                    labList.add(bean);
                }
            }

            params.put("labList", labList);

            /**
             * 常规提取记编号--liuchang
             */
            if (StringUtils.isNotBlank(operateUser.getOrgId())) {
                List<QcNoSettings> newQcNoSettings = qcNoSettingsService.selectByLab(operateUser.getOrgId());
                Calendar date = Calendar.getInstance(); //获取年份
                if (newQcNoSettings != null && !newQcNoSettings.isEmpty()) {
                    for (QcNoSettings qcNoSettings : newQcNoSettings) {
                        if (qcNoSettings.getDictCode().equals(Constants.ROUTINE_EXTRACTION_RECORDS_NO)) {
                            String routineExtractionNo = qcNoSettings.getLabServerHeader() + "-" + String.valueOf(date.get(Calendar.YEAR)) + "-" + qcNoSettings.getBarcode();
                            params.put("routineExtractionNo", StringUtils.isBlank(routineExtractionNo) ? "" : routineExtractionNo);
                        }
                    }
                }
            }
            //精斑记录表参数
            if (isSpotRecord) {
                //纯化方法
                dictItem.setDictTypeCode(Constants.PURIFICATION_METHOD);
                List<DictItem> purificationMethodList = DictUtil.getDictList(dictItem);
                params.put("purificationMethodList",purificationMethodList);

                //页眉
                if (labExtractInfo != null) {
                    if (StringUtils.isNotEmpty(labExtractInfo.getOrgId())) {
                        ExperimentalParameter exp = new ExperimentalParameter();
                        exp.setOrgId(labExtractInfo.getOrgId());
                        exp.setExperimentalType("FINEGROUPER");//精斑提取
                        List<ExperimentalParameter> experimentalParameters = experimentalParameterService.selectList(exp);
                        if (ListUtils.isNotNullAndEmptyList(experimentalParameters)) {
                            ExperimentalParameter experimentalParameter = experimentalParameters.get(0);
                            String orgNameSp = experimentalParameter.getParameterName();
                            params.put("orgNameSp", StringUtils.isBlank(orgNameSp) ? "" : orgNameSp);
                        }
                    }
                }
                labExtractSampleLists = labExtractSampleService.selectByExtractId(extractId);
                if (ListUtils.isNotNullAndEmptyList(labExtractSampleLists)) {
                    for (LabExtractSample extractSample : labExtractSampleLists) {
                        if (caseInfo.getCaseId().equals(extractSample.getCaseId())) {
                            List<LimsSampleInfoDna> sampleInfoDnaList = limsSampleInfoDnaService.selectBySampleId(extractSample.getSampleId());
                            LimsSampleInfoDna sampleInfoDna = new LimsSampleInfoDna();
                            if (ListUtils.isNotNullAndEmptyList(sampleInfoDnaList)) {
                                sampleInfoDna = sampleInfoDnaList.get(0);
                                extractSample.setLimsSampleInfoDna(sampleInfoDna);
                            }
                        }
                    }
                }
                List<LabExtractSample>  extractSampleListTwo = new ArrayList<>();
                for (int i = 0; i < labExtractSampleLists.size(); i++) {
                    String extractString = labExtractSampleLists.get(i).getExtractString();
                    String[] extractStringS = extractString.split("},");
                    labExtractSampleLists.get(i).setTES(extractStringS[0]);
                    labExtractSampleLists.get(i).setSDS(extractStringS[1]);
                    labExtractSampleLists.get(i).setPK(extractStringS[2]);
                    labExtractSampleLists.get(i).setIsOK(Boolean.valueOf(extractStringS[3]));
                    labExtractSampleLists.get(i).setIsTwe(Boolean.valueOf(extractStringS[4]));
                    if (Boolean.valueOf(extractStringS[4])) {
                        labExtractSampleLists.get(i).setTES1(extractStringS[5]);
                        labExtractSampleLists.get(i).setSDS1(extractStringS[6]);
                        labExtractSampleLists.get(i).setChelex(extractStringS[7]);
                        labExtractSampleLists.get(i).setPK1(extractStringS[8]);
                        labExtractSampleLists.get(i).setIMDTT(extractStringS[9]);
                        labExtractSampleLists.get(i).setPurification(extractStringS[10]);
                        //如果存在二次消化，放到新的list中
                        extractSampleListTwo.add(labExtractSampleLists.get(i));
                    }
                    if (extractStringS.length == 7){
                        labExtractInfo.setDigestionTemperature(extractStringS[5]);
                        labExtractInfo.setDigestionTime(extractStringS[6]);
                    }else if (extractStringS.length == 13) {
                        labExtractInfo.setDigestionTemperature(extractStringS[11]);
                        labExtractInfo.setDigestionTime(extractStringS[12]);
                    }
                }

                List<Map<String, Object>> resultList = new ArrayList<>();
                //一次消化实验检材
                List<List<LabExtractSample>> mapList = new ArrayList<>();
                if (ListUtils.isNotNullAndEmptyList(labExtractSampleLists)) {
                    List<LabExtractSample> strList = new ArrayList<>();
                    for (int i = 0;i < labExtractSampleLists.size();i ++) {
                        strList.add(labExtractSampleLists.get(i));

                        if ((i + 1) % 7 == 0) {
                            mapList.add(strList);
                            strList = new ArrayList<>();
                        }
                    }
                    //余数小于7时也添加到mapList
                    if (ListUtils.isNotNullAndEmptyList(strList)) {
                        LabExtractSample extractSample = new LabExtractSample();
                        for (int i = strList.size(); i < 7; i++) {
                            extractSample.setLimsSampleInfoDna(new LimsSampleInfoDna());
                            strList.add(extractSample);
                        }
                        mapList.add(strList);
                    }
                    params.put("mapList", mapList);
                }
                //二次消化实验检材
                List<List<LabExtractSample>> mapListTwo = new ArrayList<>();
                if (ListUtils.isNotNullAndEmptyList(extractSampleListTwo)) {
                    List<LabExtractSample> strList = new ArrayList<>();
                    for (int i = 0;i < extractSampleListTwo.size();i ++) {
                        strList.add(extractSampleListTwo.get(i));

                        if ((i + 1) % 7 == 0) {
                            mapListTwo.add(strList);
                            strList = new ArrayList<>();
                        }
                    }
                    //余数小于7时也添加到mapList
                    if (ListUtils.isNotNullAndEmptyList(strList)) {
                        LabExtractSample extractSample = new LabExtractSample();
                        for (int i = strList.size(); i < 7; i++) {
                            extractSample.setLimsSampleInfoDna(new LimsSampleInfoDna());
                            strList.add(extractSample);
                        }
                        mapListTwo.add(strList);
                    }
                    if (ListUtils.isNotNullAndEmptyList(mapList)) {
                        List<LabExtractSample> sampleList = new ArrayList<>();
                        for (int i = mapListTwo.size();i < mapList.size();i++) {
                            LabExtractSample extractSample = new LabExtractSample();
                            for (int j = 0; j < 7; j++) {
                                extractSample.setLimsSampleInfoDna(new LimsSampleInfoDna());
                                sampleList.add(extractSample);
                            }
                            mapListTwo.add(sampleList);
                        }
                    }
                    params.put("mapListTwo", mapListTwo);
                }
                Map<String, Object> paramMap = new HashMap<>();
                if (ListUtils.isNotNullAndEmptyList(mapList) && ListUtils.isNotNullAndEmptyList(mapListTwo) && mapList.size() == mapListTwo.size()) {
                    int count = mapList.size();
                    for (int i = 0;i < count;i++) {
                        Map<String, Object> newMap = new HashMap<>();
                        newMap.putAll(params);
                        newMap.put("firstExtractSampleList", mapList.get(i));
                        newMap.put("secondExtractSampleList", mapListTwo.get(i));
                        resultList.add(newMap);
                    }
                }
                paramMap.put("resultList",resultList);
                try {
                    Configuration cfg = new Configuration();
                    cfg.setDefaultEncoding("UTF-8");
                    cfg.setClassForTemplateLoading(this.getClass(), "/templates");
                    //获取模板
                    String ftlName = "spermSpotRecord.ftl";

                    Template tmp = cfg.getTemplate(ftlName, "UTF-8");

                    String filename =  UuidUtil.generateUUID()+ "-" + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".doc";

                    out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(getGeneratePath(caseInfo, filename)), "UTF-8"));
                    tmp.process(paramMap, out);
                    out.flush();
                    out.close();
                    filePathName = getGeneratePath(caseInfo, filename);
                    //案件打印 调用案件打印方法
                    //根据flag判断是否选中的是打印按钮
                    if(flag.equals("2")){
                        String page="1";
                        if(Strings.isNotBlank(filePathName)){

                            if("1".equals(fullPrint)){
                                path = CasePrintFtpFileUtils.uploadFtpFileNew(ftpIp, ftpPort, ftpUser, ftpPassword, ftpCaseFilePath, filePathName, request, page,fullPrint);
                                System.out.println(path.toString());
                            }else{
                                CasePrintFtpFileUtils.uploadFtpFileNew(ftpIp, ftpPort, ftpUser, ftpPassword, ftpFilePath, filePathName, request, page,fullPrint);
                            }
                        }
                    }
                } catch (Exception ex) {
                    logger.error("下载错误！", ex);
                }
            }else {

                try {
                    Configuration cfg = new Configuration();
                    cfg.setDefaultEncoding("UTF-8");
                    cfg.setClassForTemplateLoading(this.getClass(), "/templates");
                    //获取模板
                    String ftlName = "recordTabletest.ftl";

                    if(labExtractSampleLists.size() >0){
                        if("D".equals(labExtractSampleLists.get(0).getExtractMethod())) {
                            ftlName ="recordTableAutomatic.ftl";
                        }
                    }

                    Template tmp = cfg.getTemplate(ftlName, "UTF-8");

                    //String filename = "DNA常规提取记录表" + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".doc";
                    String filename =  UuidUtil.generateUUID()+ "-" + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".doc";

                    out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(getGeneratePath(caseInfo, filename)), "UTF-8"));
                    tmp.process(params, out);
                    out.flush();
                    out.close();
                    filePathName = getGeneratePath(caseInfo, filename);
                    //案件打印 调用案件打印方法
                    //根据flag判断是否选中的是打印按钮
                    if(flag.equals("2")){
                        String page="1";
                        if(Strings.isNotBlank(filePathName)){

                            if("1".equals(fullPrint)){
                                path = CasePrintFtpFileUtils.uploadFtpFileNew(ftpIp, ftpPort, ftpUser, ftpPassword, ftpCaseFilePath, filePathName, request, page,fullPrint);
                                System.out.println(path.toString());
                            }else{
                                CasePrintFtpFileUtils.uploadFtpFileNew(ftpIp, ftpPort, ftpUser, ftpPassword, ftpFilePath, filePathName, request, page,fullPrint);
                            }
                        }
                    }
                } catch (Exception ex) {
                    logger.error("下载错误！", ex);
                }
            }
        }

        if("1".equals(fullPrint)){
            result.put("filePathName", path);
        }else{
            if(!"".equals(filePathName) && filePathName != null){
                result.put("filePathName", filePathName);
            }
        }
        return result;
    }

    /**
     * 扩增记录下载-压缩-打印
     *
     * @param request
     * @param caseInfo
     * @return
     */
    @RequestMapping("/compressedFilesKz")
    @ResponseBody
    public Map<String, Object> compressedFilesKz(HttpServletRequest request, HttpServletResponse response, LimsCaseInfo caseInfo,String flag,String fullPrint) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> pcrmap = new HashMap<>();
        Writer out = null;
        String filePathName = null;
        String path = null;

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        String userOrgId = operateUser.getOrgId();
        //将当前用户org_id设置进query
        if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
            userOrgId = "110230000000";
        }

        //根据案件id查取检材
        List<LimsSampleInfoDna> limsSampleInfoDnaList = sampleInfoDnaService.selectByCaseId(caseInfo.getCaseId());

        List<LabPcrSample> pcrSampleList = new ArrayList<>();
        int countindex = 0;
        for (LimsSampleInfoDna lsid : limsSampleInfoDnaList) {
            List<LabPcrSample> labPcrSampleList = labPcrSampleService.selectBySampleId(lsid.getSampleId());
            //把检材的扩增信息放入到list集合中
            if (ListUtils.isNotNullAndEmptyList(labPcrSampleList)) {
                pcrSampleList.add(countindex, labPcrSampleList.get(0));
                countindex++;
            }
        }
        //如果扩增信息集合不为空
        if (pcrSampleList.size() > 0) {
            //根据prcid查询扩增主表
            LabPcrInfo labPcrInfo = labPcrInfoService.selectByPrimaryKey(pcrSampleList.get(0).getPcrId());
            pcrmap.put("labPcrInfoRecord", labPcrInfo);

            if(labPcrInfo != null){
                //试剂名称
                String pcrReagent = labPcrInfo.getPcrReagent();
                //扩增体系
                String pcrSystem =labPcrInfo.getPcrSystem();

                ExperimentalParameter experimentalParameter = new ExperimentalParameter();
                //Constants.EXPERIMENTAL_STAGE_02

                experimentalParameter.setPanelName(pcrReagent);
                experimentalParameter.setParameterName(pcrSystem);
                experimentalParameter.setExperimentalStage(Constants.EXPERIMENTAL_STAGE_02);
                experimentalParameter.setOrgId(userOrgId);

                List<ExperimentalParameter> experimentalParameterlist = experimentalParameterService.selectListValue(experimentalParameter);
                if(experimentalParameterlist.size() >0){
                    String parameterValue = experimentalParameterlist.get(0).getParameterValue();
                    String panelName = experimentalParameterlist.get(0).getPanelName();

                    Map map = (Map) JSONObject.parse(parameterValue);
                    String primer = (String) map.get("Primer");
                    String buffer = (String) map.get("Buffer");
                    String taqE = (String) map.get("TaqE");
                    String template = (String) map.get("Template");
                    String h2O = (String) map.get("H2O");
                    String mgCl2 = (String) map.get("MgCl2");
                    String dNTP = (String) map.get("dNTP");
//                    pcrmap.put("primer", StringUtils.isBlank(primer) ? "" : primer);
//                    pcrmap.put("buffer", StringUtils.isBlank(buffer) ? "" : buffer);
//                    pcrmap.put("template", StringUtils.isBlank(template) ? "" : template);
//                    pcrmap.put("h2O", StringUtils.isBlank(h2O) ? "" : h2O);

                    if(StringUtils.isNotBlank(panelName)){
                        if("PP21".equals(panelName)){
                            pcrmap.put("primer", "2.0");
                            pcrmap.put("buffer", "2.0");
                            pcrmap.put("template", "1.0");
                            pcrmap.put("h2O", "5.0");

                            pcrmap.put("primer2", "2.0");
                            pcrmap.put("buffer2", "2.0");
                            pcrmap.put("template2", "X");
                            pcrmap.put("h2O2", "6.0");
                        }else{
                            pcrmap.put("primer", "2.0");
                            pcrmap.put("buffer", "4.0");
                            pcrmap.put("template", "1.0");
                            pcrmap.put("h2O", "3.0");

                            pcrmap.put("primer2", "2.0");
                            pcrmap.put("buffer2", "4.0");
                            pcrmap.put("template2", "X");
                            pcrmap.put("h2O2", "4.0");
                        }
                    }

                }
            }
            //将数据分页
            List<LabPcrSample> tweLabSampleList = new ArrayList<>();
            List<LabPcrSample> labSampleList = new ArrayList<>();
            if (pcrSampleList.size() > 14) {
                List<List<LabPcrSample>> listList = new ArrayList<>();
                tweLabSampleList = pcrSampleList.subList(14, pcrSampleList.size());
                int sizePage = (int) Math.ceil((double) tweLabSampleList.size() / (double) 14);
                for (int i = 0; i < sizePage; i++) {
                    int j;
                    if (i < sizePage - 1) {
                        j = (i + 1) * 14;
                    } else {
                        j = tweLabSampleList.size();
                    }
                    int k = i;
                    if (i > 0 && i < sizePage) {
                        k = i * 14;
                    }
                    if (j <= tweLabSampleList.size()) {
                        List<LabPcrSample> oneLabExtractSampleList = tweLabSampleList.subList(k, j);
                        listList.add(oneLabExtractSampleList);
                    }

                }
                labSampleList = pcrSampleList.subList(0, 14);
                pcrmap.put("labSampleList", listList);
                pcrmap.put("pcrSampleListRecord", labSampleList);
            } else {
                LabPcrSample labPcrSample = new LabPcrSample();
                labPcrSample.setPrimer("  ");
                labPcrSample.setBuffer("  ");
                labPcrSample.setTaqe("  ");
                labPcrSample.setTemplate("  ");
                labPcrSample.setH2o("  ");
                labPcrSample.setMgcl2("  ");
                labPcrSample.setDntp("  ");
                labSampleList.addAll(pcrSampleList);
                for (int i = 0; i < 14 - pcrSampleList.size(); i++) {
                    labSampleList.add(labPcrSample);
                }
                pcrmap.put("pcrSampleListRecord", labSampleList);
            }
            /**
             * 扩增记录编号--liuchang
             */
            if (StringUtils.isNotBlank(operateUser.getOrgId())) {
                List<QcNoSettings> newQcNoSettings = qcNoSettingsService.selectByLab(operateUser.getOrgId());
                Calendar date = Calendar.getInstance(); //获取年份
                if (newQcNoSettings != null && !newQcNoSettings.isEmpty()) {
                    for (QcNoSettings qcNoSettings : newQcNoSettings) {
                        if (qcNoSettings.getDictCode().equals(Constants.AMPLIFIED_RECORDS_NO)) {
                            String amplifiedRecordsNo = qcNoSettings.getLabServerHeader() + "-" + String.valueOf(date.get(Calendar.YEAR)) + "-" + qcNoSettings.getBarcode();
                            pcrmap.put("amplifiedRecordsNo", StringUtils.isBlank(amplifiedRecordsNo) ? "" : amplifiedRecordsNo);
                        }
                    }
                }
            }

            try {
                Configuration cfg = new Configuration();
                cfg.setDefaultEncoding("UTF-8");
                cfg.setClassForTemplateLoading(this.getClass(), "/templates");
                //获取模板
                String ftlName = "pcrRecordBook.ftl";

                Template tmp = cfg.getTemplate(ftlName, "UTF-8");

                String filename = null;

                //filename = "DNA扩增记录表" + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".doc";
                filename =  UuidUtil.generateUUID()+ "-" + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".doc";

                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(getGeneratePath(caseInfo, filename)), "UTF-8"));
                tmp.process(pcrmap, out);
                out.flush();
                out.close();
                filePathName = getGeneratePath(caseInfo, filename);
                //案件打印 调用案件打印方法
                //根据flag判断是否选中的是打印按钮
                if(flag.equals("2")){
                    String page="1";
                    if(Strings.isNotBlank(filePathName)){

//                        this.httpLink(request,page,filePathName);
                        if("1".equals(fullPrint)){
                            path = CasePrintFtpFileUtils.uploadFtpFileNew(ftpIp, ftpPort, ftpUser, ftpPassword, ftpCaseFilePath, filePathName, request, page,fullPrint);
                            System.out.println(path.toString());
                        }else{
                            CasePrintFtpFileUtils.uploadFtpFileNew(ftpIp, ftpPort, ftpUser, ftpPassword, ftpFilePath, filePathName, request, page,fullPrint);
                        }
                    }
                }
            } catch (Exception ex) {
                logger.error("下载错误！", ex);
            }
        }

        if("1".equals(fullPrint)){
            result.put("filePathName", path);
        }else{
            if(!"".equals(filePathName) && filePathName != null){
                result.put("filePathName", filePathName);
            }
        }


        return result;
    }

    public Map<String, Object> compressedFilesKzNew(HttpServletRequest request, HttpServletResponse response, LimsCaseInfo caseInfo,String flag,String fullPrint, String  pcrId) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> pcrmap = new HashMap<>();
        Writer out = null;
        String filePathName = null;
        String path = null;

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        String userOrgId = operateUser.getOrgId();
        //将当前用户org_id设置进query
        if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
            userOrgId = "110230000000";
        }

        List<LabPcrSample> labPcrSampleList = labPcrSampleService.selectByPcrId(pcrId);
        List<LabPcrSample> pcrSampleList = new ArrayList<>();
        int countindex = 0;
        //把检材的扩增信息放入到list集合中
        if (ListUtils.isNotNullAndEmptyList(labPcrSampleList)) {
            for (LabPcrSample pcrSample : labPcrSampleList) {
                if (caseInfo.getCaseId().equals(pcrSample.getCaseId())) {
                    pcrSampleList.add(countindex, pcrSample);
                    countindex++;
                }
            }
        }

        //如果扩增信息集合不为空
        if (pcrSampleList.size() > 0) {
            //根据prcid查询扩增主表
            LabPcrInfo labPcrInfo = labPcrInfoService.selectByPrimaryKey(pcrId);
            pcrmap.put("labPcrInfoRecord", labPcrInfo);

            if(labPcrInfo != null){
                //试剂名称
                String pcrReagent = labPcrInfo.getPcrReagent();
                //扩增体系
                String pcrSystem =labPcrInfo.getPcrSystem();

                ExperimentalParameter experimentalParameter = new ExperimentalParameter();
                //Constants.EXPERIMENTAL_STAGE_02

                experimentalParameter.setPanelName(pcrReagent);
                experimentalParameter.setParameterName(pcrSystem);
                experimentalParameter.setExperimentalStage(Constants.EXPERIMENTAL_STAGE_02);
                experimentalParameter.setOrgId(userOrgId);

                List<ExperimentalParameter> experimentalParameterlist = experimentalParameterService.selectListValue(experimentalParameter);
                if(experimentalParameterlist.size() >0){
                    String parameterValue = experimentalParameterlist.get(0).getParameterValue();
                    String panelName = experimentalParameterlist.get(0).getPanelName();

                    Map map = (Map) JSONObject.parse(parameterValue);
                    String primer = (String) map.get("Primer");
                    String buffer = (String) map.get("Buffer");
                    String taqE = (String) map.get("TaqE");
                    String template = (String) map.get("Template");
                    String h2O = (String) map.get("H2O");
                    String mgCl2 = (String) map.get("MgCl2");
                    String dNTP = (String) map.get("dNTP");
//                    pcrmap.put("primer", StringUtils.isBlank(primer) ? "" : primer);
//                    pcrmap.put("buffer", StringUtils.isBlank(buffer) ? "" : buffer);
//                    pcrmap.put("template", StringUtils.isBlank(template) ? "" : template);
//                    pcrmap.put("h2O", StringUtils.isBlank(h2O) ? "" : h2O);

                    if(StringUtils.isNotBlank(panelName)){
                        if("PP21".equals(panelName)){
                            pcrmap.put("primer", "2.0");
                            pcrmap.put("buffer", "2.0");
                            pcrmap.put("template", "1.0");
                            pcrmap.put("h2O", "5.0");

                            pcrmap.put("primer2", "2.0");
                            pcrmap.put("buffer2", "2.0");
                            pcrmap.put("template2", "X");
                            pcrmap.put("h2O2", "6.0");
                        }else{
                            pcrmap.put("primer", "2.0");
                            pcrmap.put("buffer", "4.0");
                            pcrmap.put("template", "1.0");
                            pcrmap.put("h2O", "3.0");

                            pcrmap.put("primer2", "2.0");
                            pcrmap.put("buffer2", "4.0");
                            pcrmap.put("template2", "X");
                            pcrmap.put("h2O2", "4.0");
                        }
                    }

                }
            }
            //页眉
            if(labPcrInfo != null){
                if(StringUtils.isNotEmpty(labPcrInfo.getOrgId())){
                    ExperimentalParameter exp = new ExperimentalParameter();
                    exp.setOrgId(labPcrInfo.getOrgId());
                    exp.setExperimentalType("PCR_RECORD");//扩增记录
                    List<ExperimentalParameter> experimentalParameters = experimentalParameterService.selectList(exp);
                    if(ListUtils.isNotNullAndEmptyList(experimentalParameters)){
                        ExperimentalParameter experimentalParameter = experimentalParameters.get(0);
                        String orgNameSp = experimentalParameter.getParameterName();
                        pcrmap.put("orgNameSp", StringUtils.isBlank(orgNameSp) ? "" : orgNameSp);
                    }
                }
            }

            LinkedHashSet<List<LabPcrSample>> allList = new LinkedHashSet<List<LabPcrSample>>();
            if (null != pcrSampleList){
                for (LabPcrSample bean: pcrSampleList) {
                    List<LabPcrSample> greplist = new ArrayList<LabPcrSample>();
                    for (LabPcrSample bean2: pcrSampleList){
                        if (bean.getCaseId().equals(bean2.getCaseId()) ){
                            greplist.add(bean2);
                        }
                    }
                    allList.add(greplist);
                }
            }

            List<PcrListModel> pcrListModelsList = new ArrayList<>();
            if(allList.size()>0 && null != allList){
                for (List<LabPcrSample> samples : allList){
                    PcrListModel model = new PcrListModel();
                    model.setLabPcrSampleList(samples);
                    pcrListModelsList.add(model);
                }
            }

            List<PcrListModel> labList = new ArrayList<>();
            if (ListUtils.isNotNullAndEmptyList(pcrListModelsList)){
                for (PcrListModel bean: pcrListModelsList) {
                    //将数据分页
                    List<LabPcrSample> tweLabSampleList = new ArrayList<>();
                    List<LabPcrSample> labSampleList = new ArrayList<>();
                    if (bean.getLabPcrSampleList().size() > 14) {
                        List<List<LabPcrSample>> listList = new ArrayList<>();
                        tweLabSampleList.addAll(bean.getLabPcrSampleList().subList(14, bean.getLabPcrSampleList().size()));
                        int sizePage = (int) Math.ceil((double) tweLabSampleList.size() / (double) 19);
                        for (int i = 0; i < sizePage; i++) {
                            int j;
                            if (i < sizePage - 1) {
                                j = (i + 1) * 19;
                            } else {
                                j = tweLabSampleList.size();
                            }
                            int k = i;
                            if (i > 0 && i < sizePage) {
                                k = i * 19;
                            }
                            if (j <= tweLabSampleList.size()) {
                                List<LabPcrSample> oneLabExtractSampleList = new ArrayList<>();
                                oneLabExtractSampleList.addAll(tweLabSampleList.subList(k, j));
                                if (ListUtils.isNotNullAndEmptyList(oneLabExtractSampleList)){
                                    if (oneLabExtractSampleList.size()<19){
                                        LabPcrSample labPcrSample = new LabPcrSample();
                                        labPcrSample.setPrimer("  ");
                                        labPcrSample.setBuffer("  ");
                                        labPcrSample.setTaqe("  ");
                                        labPcrSample.setTemplate("  ");
                                        labPcrSample.setH2o("  ");
                                        labPcrSample.setMgcl2("  ");
                                        labPcrSample.setDntp("  ");
                                        int size = 19 - oneLabExtractSampleList.size();
                                        for (int x = 0; x <  size ; x++) {
                                            oneLabExtractSampleList.add(labPcrSample);
                                        }
                                    }
                                }
                                listList.add(oneLabExtractSampleList);
                                bean.setListList(listList);
                            }

                        }
//                labSampleList = bean.subList(0, 14);
//                    pcrmap.put("labSampleList", listList);
//                pcrmap.put("pcrSampleListRecord", labSampleList);
                        bean.setLabPcrSampleList(bean.getLabPcrSampleList().subList(0, 14));
                        labList.add(bean);
                    } else {
                        LabPcrSample labPcrSample = new LabPcrSample();
                        labPcrSample.setPrimer("  ");
                        labPcrSample.setBuffer("  ");
                        labPcrSample.setTaqe("  ");
                        labPcrSample.setTemplate("  ");
                        labPcrSample.setH2o("  ");
                        labPcrSample.setMgcl2("  ");
                        labPcrSample.setDntp("  ");
                        labSampleList.addAll(bean.getLabPcrSampleList());
                        int size = 14 - bean.getLabPcrSampleList().size();
                        for (int i = 0; i <  size ; i++) {
                            bean.getLabPcrSampleList().add(labPcrSample);
                        }
                        labList.add(bean);
//                pcrmap.put("pcrSampleListRecord", labSampleList);
                    }
                }
            }

            pcrmap.put("allList",labList);

            /**
             * 扩增记录编号--liuchang
             */
            if (StringUtils.isNotBlank(operateUser.getOrgId())) {
                List<QcNoSettings> newQcNoSettings = qcNoSettingsService.selectByLab(operateUser.getOrgId());
                Calendar date = Calendar.getInstance(); //获取年份
                if (newQcNoSettings != null && !newQcNoSettings.isEmpty()) {
                    for (QcNoSettings qcNoSettings : newQcNoSettings) {
                        if (qcNoSettings.getDictCode().equals(Constants.AMPLIFIED_RECORDS_NO)) {
                            String amplifiedRecordsNo = qcNoSettings.getLabServerHeader() + "-" + String.valueOf(date.get(Calendar.YEAR)) + "-" + qcNoSettings.getBarcode();
                            pcrmap.put("amplifiedRecordsNo", StringUtils.isBlank(amplifiedRecordsNo) ? "" : amplifiedRecordsNo);
                        }
                    }
                }
            }

            try {
                Configuration cfg = new Configuration();
                cfg.setDefaultEncoding("UTF-8");
                cfg.setClassForTemplateLoading(this.getClass(), "/templates");
                //获取模板
                String ftlName = "pcrRecordBook.ftl";

                Template tmp = cfg.getTemplate(ftlName, "UTF-8");

                String filename = null;

                //filename = "DNA扩增记录表" + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".doc";
                filename =  UuidUtil.generateUUID()+ "-" + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".doc";

                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(getGeneratePath(caseInfo, filename)), "UTF-8"));
                tmp.process(pcrmap, out);
                out.flush();
                out.close();
                filePathName = getGeneratePath(caseInfo, filename);
                //案件打印 调用案件打印方法
                //根据flag判断是否选中的是打印按钮
                if(flag.equals("2")){
                    String page="1";
                    if(Strings.isNotBlank(filePathName)){

//                        this.httpLink(request,page,filePathName);
                        if("1".equals(fullPrint)){
                            path = CasePrintFtpFileUtils.uploadFtpFileNew(ftpIp, ftpPort, ftpUser, ftpPassword, ftpCaseFilePath, filePathName, request, page,fullPrint);
                            System.out.println(path.toString());
                        }else{
                            CasePrintFtpFileUtils.uploadFtpFileNew(ftpIp, ftpPort, ftpUser, ftpPassword, ftpFilePath, filePathName, request, page,fullPrint);
                        }
                    }
                }
            } catch (Exception ex) {
                logger.error("下载错误！", ex);
            }
        }

        if("1".equals(fullPrint)){
            result.put("filePathName", path);
        }else{
            if(!"".equals(filePathName) && filePathName != null){
                result.put("filePathName", filePathName);
            }
        }


        return result;
    }

    /**
     * 电泳分离记录表-压缩-打印
     *
     * @param request
     * @param response
     * @param caseInfo
     */
    @RequestMapping("compressedFilesSy")
    @ResponseBody
    public Map<String, Object>compressedFilesSy(HttpServletRequest request, HttpServletResponse response, LimsCaseInfo caseInfo,String flag,String fullPrint) {
        Map<String, Object> result = new HashMap<>();

        Map<String, Object> pcrmap = new HashMap<>();
        Writer out = null;
        String filePathName = null;
        String path = null;
        //根据案件id查取检材
        List<LimsSampleInfoDna> limsSampleInfoDnaList = sampleInfoDnaService.selectByCaseId(caseInfo.getCaseId());

        List<LabSySample> sySampleList = new ArrayList<>();
        int countindex = 0;
        for (LimsSampleInfoDna lsid : limsSampleInfoDnaList) {
            List<LabSySample> labSyrSampleList = LabSySampleService.selectLabSySampleBySampleId(lsid.getSampleId());
            //把检材的扩增信息放入到list集合中
            if (ListUtils.isNotNullAndEmptyList(labSyrSampleList)) {
                sySampleList.add(countindex, labSyrSampleList.get(0));
                countindex++;
            }
        }
        if (sySampleList.size() > 0) {
            LabSyInfo labSyInfo = labSyInfoService.selectByPrimaryKey(sySampleList.get(0).getSyId());
            pcrmap.put("labSyInfoRecord", labSyInfo);


            //将数据分页
            List<LabSySample> tweLabSampleList = new ArrayList<>();
            List<LabSySample> labSampleList = new ArrayList<>();
            if (sySampleList.size() > 15) {
                List<List<LabSySample>> listList = new ArrayList<>();
                tweLabSampleList = sySampleList.subList(15, sySampleList.size());
                int sizePage = (int) Math.ceil((double) tweLabSampleList.size() / (double) 19);
                for (int i = 0; i < sizePage; i++) {
                    int j;
                    if (i < sizePage - 1) {
                        j = (i + 1) * 19;
                    } else {
                        j = tweLabSampleList.size();
                    }
                    int k = i;
                    if (i > 0 && i < sizePage) {
                        k = i * 19;
                    }
                    if (j <= tweLabSampleList.size()) {
                        List<LabSySample> oneLabExtractSampleList = tweLabSampleList.subList(k, j);
                        listList.add(oneLabExtractSampleList);
                    }

                }
                labSampleList = sySampleList.subList(0, 15);
                pcrmap.put("SySamplesList", listList);
                pcrmap.put("sySampleListRecord", labSampleList);
            } else {
                LabSySample labPcrSample = new LabSySample();
                labPcrSample.setSamplePostion("  ");
                labPcrSample.setSampleName("  ");
                labPcrSample.setSampleNo("  ");
                labSampleList.addAll(sySampleList);
                for (int i = 0; i < 15 - sySampleList.size(); i++) {
                    labSampleList.add(labPcrSample);
                }
                pcrmap.put("sySampleListRecord", labSampleList);
            }


            try {
                Configuration cfg = new Configuration();
                cfg.setDefaultEncoding("UTF-8");
                cfg.setClassForTemplateLoading(this.getClass(), "/templates");
                //获取模板
                String ftlName = "syRecordBook.ftl";

                Template tmp = cfg.getTemplate(ftlName, "UTF-8");

                //String filename = "DNA上样记录表" + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".doc";
                String filename =  UuidUtil.generateUUID()+ "-" + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".doc";
                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(getGeneratePath(caseInfo, filename)), "UTF-8"));
                tmp.process(pcrmap, out);
                out.flush();
                out.close();
                filePathName = getGeneratePath(caseInfo, filename);
                //案件打印 调用案件打印方法
                //根据flag判断是否选中的是打印按钮
                if(flag.equals("2")){
                    String page="1";
                    if(Strings.isNotBlank(filePathName)){
//                        this.httpLink(request,page,filePathName);
                        if("1".equals(fullPrint)){
                            path = CasePrintFtpFileUtils.uploadFtpFileNew(ftpIp, ftpPort, ftpUser, ftpPassword, ftpCaseFilePath, filePathName, request, page,fullPrint);
                            System.out.println(path.toString());
                        }else{
                            CasePrintFtpFileUtils.uploadFtpFileNew(ftpIp, ftpPort, ftpUser, ftpPassword, ftpFilePath, filePathName, request, page,fullPrint);
                        }
                    }
                }
            } catch (Exception ex) {
                logger.error("下载错误！", ex);
            }
        }

        if("1".equals(fullPrint)){
            result.put("filePathName", path);
        }else{
            if(!"".equals(filePathName) && filePathName != null){
                result.put("filePathName", filePathName);
            }
        }

        return result;
    }

    public Map<String, Object>compressedFilesSyNew(HttpServletRequest request, HttpServletResponse response, LimsCaseInfo caseInfo,String flag,String fullPrint, String syId) {
        Map<String, Object> result = new HashMap<>();

        Map<String, Object> pcrmap = new HashMap<>();
        Writer out = null;
        String filePathName = null;
        String path = null;
        List<LabSySample> sySampleList = new ArrayList<>();
        int countindex = 0;
        List<LabSySample> labSySampleList  = LabSySampleService.selectBySyId(syId);
        //把检材的扩增信息放入到list集合中
        if (ListUtils.isNotNullAndEmptyList(labSySampleList)) {
            for (LabSySample labSySample :labSySampleList) {
                if (caseInfo.getCaseId().equals(labSySample.getCaseId())) {
                    sySampleList.add(countindex, labSySample);
                    countindex++;
                }
            }
        }

        if (sySampleList.size() > 0) {
            LabSyInfo labSyInfo = labSyInfoService.selectByPrimaryKey(syId);
            pcrmap.put("labSyInfoRecord", labSyInfo);

            if (labSyInfo != null) {
                if (StringUtils.isNotBlank(labSyInfo.getSyString())) {
                    Map<String, Object> mapSyString = (Map) JSON.parse(labSyInfo.getSyString());

                    labSyInfo.setInternalStandard(mapSyString.get("neiBiao").toString());
                    String quantity = mapSyString.get("quantity").toString();
                    //甲酰胺
                    String jiaXianAnBatchCode = mapSyString.get("jiaXianAnBatchCode").toString();
                    String jiaXianAnInstockDate = mapSyString.get("jiaXianAnInstockDate").toString();

                    pcrmap.put("jiaXianAnBatchCode", jiaXianAnBatchCode);
                    pcrmap.put("jiaXianAnInstockDate", jiaXianAnInstockDate);
                    //内标
                    String neiBiaoBatchCode = mapSyString.get("neiBiaoBatchCode").toString();
                    String neiBiaoInstockDate = mapSyString.get("neiBiaoInstockDate").toString();

                    pcrmap.put("neiBiaoBatchCode", neiBiaoBatchCode);
                    pcrmap.put("neiBiaoInstockDate", neiBiaoInstockDate);
                    //毛细管
                    String maoXiGuanBatchCode = mapSyString.get("maoXiGuanBatchCode").toString();
                    String maoXiGuanInstockDate = mapSyString.get("maoXiGuanInstockDate").toString();

                    pcrmap.put("maoXiGuanBatchCode", maoXiGuanBatchCode);
                    pcrmap.put("maoXiGuanInstockDate", maoXiGuanInstockDate);
                    //胶液
                    String jiaoYeBatchCode = mapSyString.get("jiaoYeBatchCode").toString();
                    String jiaoYeInstockDate = mapSyString.get("jiaoYeInstockDate").toString();

                    pcrmap.put("jiaoYeBatchCode", jiaoYeBatchCode);
                    pcrmap.put("jiaoYeInstockDate", jiaoYeInstockDate);

                    double quantityCount = 0;
                    if (StringUtils.isNotBlank(quantity)) {
                        quantityCount = Double.valueOf(quantity);
                    }
                    double formamide = 0;
                    if (StringUtils.isNotBlank(labSyInfo.getFormamide())) {
                        formamide = Double.valueOf(labSyInfo.getFormamide());
                    }
                    double internalStandardUl = 0;
                    if (StringUtils.isNotBlank(labSyInfo.getInternalStandardUl())) {
                        internalStandardUl = Double.valueOf(labSyInfo.getInternalStandardUl());
                    }

                    pcrmap.put("formamideCount", quantityCount * formamide);
                    pcrmap.put("internalCount", quantityCount * internalStandardUl);
                }
            }

            //将数据分页
            List<LabSySample> tweLabSampleList = new ArrayList<>();
            List<LabSySample> labSampleList = new ArrayList<>();
            if (sySampleList.size() > 15) {
                List<List<LabSySample>> listList = new ArrayList<>();
                tweLabSampleList = sySampleList.subList(15, sySampleList.size());
                int sizePage = (int) Math.ceil((double) tweLabSampleList.size() / (double) 19);
                for (int i = 0; i < sizePage; i++) {
                    int j;
                    if (i < sizePage - 1) {
                        j = (i + 1) * 19;
                    } else {
                        j = tweLabSampleList.size();
                    }
                    int k = i;
                    if (i > 0 && i < sizePage) {
                        k = i * 19;
                    }
                    if (j <= tweLabSampleList.size()) {
                        List<LabSySample> oneLabExtractSampleList = tweLabSampleList.subList(k, j);
                        listList.add(oneLabExtractSampleList);
                    }

                }
                labSampleList = sySampleList.subList(0, 15);
                pcrmap.put("SySamplesList", listList);
                pcrmap.put("sySampleListRecord", labSampleList);
            } else {
                LabSySample labPcrSample = new LabSySample();
                labPcrSample.setSamplePostion("  ");
                labPcrSample.setSampleName("  ");
                labPcrSample.setSampleNo("  ");
                labSampleList.addAll(sySampleList);
                for (int i = 0; i < 15 - sySampleList.size(); i++) {
                    labSampleList.add(labPcrSample);
                }
                pcrmap.put("sySampleListRecord", labSampleList);
            }

            /**
             *电泳记录编号--liuchang
             */
            Subject subject = SecurityUtils.getSubject();
            LoaUserInfo user=(LoaUserInfo) subject.getPrincipal();
            if (StringUtils.isNotBlank(user.getOrgId())) {
                Calendar date = Calendar.getInstance(); //获取年份
                List<QcNoSettings> newQcNoSettings = qcNoSettingsService.selectByLab(user.getOrgId());
                if (newQcNoSettings != null && !newQcNoSettings.isEmpty()) {
                    for (QcNoSettings qcNoSettings : newQcNoSettings) {
                        if (qcNoSettings.getDictCode().equals(Constants.ELECTROPHORETIC_RECORDING_NO)) {
                            String electrophoreticRecordingNo = qcNoSettings.getLabServerHeader() + "-" + String.valueOf(date.get(Calendar.YEAR)) + "-" + qcNoSettings.getBarcode();
                            pcrmap.put("electrophoreticRecordingNo", StringUtils.isBlank(electrophoreticRecordingNo) ? "" : electrophoreticRecordingNo);
                        }
                    }
                }
            }

            try {
                Configuration cfg = new Configuration();
                cfg.setDefaultEncoding("UTF-8");
                cfg.setClassForTemplateLoading(this.getClass(), "/templates");
                //获取模板
                String ftlName = "syRecordBook.ftl";

                Template tmp = cfg.getTemplate(ftlName, "UTF-8");

                //String filename = "DNA上样记录表" + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".doc";
                String filename =  UuidUtil.generateUUID()+ "-" + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".doc";
                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(getGeneratePath(caseInfo, filename)), "UTF-8"));
                tmp.process(pcrmap, out);
                out.flush();
                out.close();
                filePathName = getGeneratePath(caseInfo, filename);
                //案件打印 调用案件打印方法
                //根据flag判断是否选中的是打印按钮
                if(flag.equals("2")){
                    String page="1";
                    if(Strings.isNotBlank(filePathName)){
//                        this.httpLink(request,page,filePathName);
                        if("1".equals(fullPrint)){
                            path = CasePrintFtpFileUtils.uploadFtpFileNew(ftpIp, ftpPort, ftpUser, ftpPassword, ftpCaseFilePath, filePathName, request, page,fullPrint);
                            System.out.println(path.toString());
                        }else{
                            CasePrintFtpFileUtils.uploadFtpFileNew(ftpIp, ftpPort, ftpUser, ftpPassword, ftpFilePath, filePathName, request, page,fullPrint);
                        }
                    }
                }
            } catch (Exception ex) {
                logger.error("下载错误！", ex);
            }
        }

        if("1".equals(fullPrint)){
            result.put("filePathName", path);
        }else{
            if(!"".equals(filePathName) && filePathName != null){
                result.put("filePathName", filePathName);
            }
        }

        return result;
    }


    /**
     * 聘书下载-压缩-打印
     *
     * @return
     */
    @RequestMapping("compressedFilesPs")
    @ResponseBody
    public Map<String, Object> compressedFilesPs(HttpServletRequest request, HttpServletResponse response, LimsCaseInfo caseInfo,String flag,String fullPrint) {
        Map<String, Object> result = new HashMap<>();
        FileArchivesInfo fileArchivesInfo = fileArchivesInfoService.queryFileLetter(caseInfo.getCaseId());
        String writeFilePath = null;
        String path = null;
        String filename = null;
        if (Objects.nonNull(fileArchivesInfo)) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                //Base64解码
                String[] split = fileArchivesInfo.getArchivesInfoPicture().split(",");
                byte[] b = decoder.decodeBuffer(split[1]);
                for (int i = 0; i < b.length; ++i) {
                    if (b[i] < 0) {//调整异常数据
                        b[i] += 256;
                    }
                }
                //  filename = "聘书" + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".png";
                filename =  UuidUtil.generateUUID()+ "-" + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".png";
                //生成jpeg图片
                String imgFilePath = SystemUtil.getSystemConfig().getProperty("DownloadPath");
                ;//新生成的图片
                writeFilePath = imgFilePath + filename;//新生成的图片
                OutputStream out = new FileOutputStream(writeFilePath);
                out.write(b);
                out.flush();
                FtpUtils f = new FtpUtils(ftpIp,ftpPort,ftpUser,ftpPassword);
                if(f.open()){
                    f.upload(writeFilePath,filename,ftpFilePath);
                    f.close();
                    writeFilePath = "ftp://"+ftpIp+"/"+ftpFilePath+"/"+filename;
                }
                out.close();
                result.put("filePathName", writeFilePath);

                //案件打印 调用案件打印方法
                //根据flag判断是否选中的是打印按钮
                if(flag.equals("2")){
                    String page="1";
                    if(Strings.isNotBlank(filename)){
                        if("1".equals(fullPrint)){
                            path = CasePrintFtpFileUtils.uploadFtpFileNew(ftpIp, ftpPort, ftpUser, ftpPassword, ftpCaseFilePath, writeFilePath, request, page,fullPrint);
                            System.out.println(path.toString());
                        }else{
                            CasePrintFtpFileUtils.uploadFtpFileNew(ftpIp, ftpPort, ftpUser, ftpPassword, ftpFilePath, writeFilePath, request, page,fullPrint);
                        }


//                        this.httpLinks(request,page,writeFilePath);
                    }
                }
            } catch (Exception e) {
                logger.error("下载聘书" + e);
            }

        } else {
            if("1".equals(fullPrint)){
                result.put("filePathName", path);
            }else{
                if(!"".equals(filename) && filename != null){
                    result.put("filePathName", filename);
                }
            }

        }
        return result;
    }

    /**
     * 委托书下载
     *
     * @return
     */
    @RequestMapping("compressedFilesWts")
    @ResponseBody
    public Map<String, Object> compressedFilesWts(HttpServletRequest request, HttpServletResponse response, String caseId,String flag,String fullPrint) {
        Map<String, Object> result = new HashMap<>();
        FileArchivesInfo fileArchivesInfo = fileArchivesInfoService.queryEntrustBook(caseId);
        String writeFilePath = null;
        String path = null;
        if (Objects.nonNull(fileArchivesInfo)) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                //Base64解码
                String[] split = fileArchivesInfo.getArchivesInfoPicture().split(",");
                byte[] b = decoder.decodeBuffer(split[1]);
                for (int i = 0; i < b.length; ++i) {
                    if (b[i] < 0) {//调整异常数据
                        b[i] += 256;
                    }
                }
                // String filename = "委托书" + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".png";
                String filename =  UuidUtil.generateUUID()+ "-" + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".png";
                //生成jpeg图片
                String imgFilePath = SystemUtil.getSystemConfig().getProperty("DownloadPath");
                ;//新生成的图片
                writeFilePath = imgFilePath + filename;//新生成的图片
                OutputStream out = new FileOutputStream(writeFilePath);
                out.write(b);
                out.flush();
                FtpUtils f = new FtpUtils(ftpIp,ftpPort,ftpUser,ftpPassword);
                if(f.open()){
                    f.upload(writeFilePath,filename,ftpFilePath);
                    f.close();
                    writeFilePath = "ftp://"+ftpIp+"/"+ftpFilePath+"/"+filename;
                }
                out.close();

                //案件打印 调用案件打印方法
                //根据flag判断是否选中的是打印按钮
                if(flag.equals("2")){
                    String page="1";
//                    if(Strings.isNotBlank(filename)){
//                        this.httpLinks(request,page,writeFilePath);

                    if("1".equals(fullPrint)){
                        path = CasePrintFtpFileUtils.uploadFtpFileNew(ftpIp, ftpPort, ftpUser, ftpPassword, ftpCaseFilePath, filename, request, page,fullPrint);
                        System.out.println(path.toString());
                    }else{
                        CasePrintFtpFileUtils.uploadFtpFileNew(ftpIp, ftpPort, ftpUser, ftpPassword, ftpFilePath, filename, request, page,fullPrint);
                    }
//                    }
                }
            } catch (Exception e) {
                logger.error("下载委托书" + e);
            }

        } else {
            if("1".equals(fullPrint)){
                result.put("filePathName", path);
            }else{
                if(!"".equals(writeFilePath) && writeFilePath != null){
                    result.put("filePathName", writeFilePath);
                }
            }

        }

        return result;
    }

    /**
     * 入库单-压缩-打印
     */
    @RequestMapping("/warehouseFiles")
    @ResponseBody
    public Map<String, Object> warehouseFiles(HttpServletRequest request,HttpServletResponse response, String caseId,String flag,String fullPrint) {
        String filePath=null;
        Map<String, Object> result = new HashMap<>();

        Map<String, Object> params = new HashMap<>();

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
        if(ListUtils.isNotNullAndEmptyList(experimentalParameters)){
            ExperimentalParameter experimentalParameter = experimentalParameters.get(0);
            String orgNameSp = experimentalParameter.getParameterName();
            params.put("orgNameSp", StringUtils.isBlank(orgNameSp) ? "" : orgNameSp);
        }

        /**
         * 入库单编号--liuchang
         */
        if (StringUtils.isNotBlank(operateUser.getOrgId())){
            List<QcNoSettings> newQcNoSettings = qcNoSettingsService.selectByLab(operateUser.getOrgId());
            Calendar date = Calendar.getInstance(); //获取日期工具类
            if (newQcNoSettings!=null && !newQcNoSettings.isEmpty()){
                for (QcNoSettings qcNoSettings:newQcNoSettings){
                   if (qcNoSettings.getDictCode().equals(Constants.WAREHOUSE_RECEIPT_NO)){
                        String warehouseReceiptNo = qcNoSettings.getLabServerHeader()+"-"+String.valueOf(date.get(Calendar.YEAR))+"-"+qcNoSettings.getBarcode();
                        params.put("warehouseReceiptNo", StringUtils.isBlank(warehouseReceiptNo) ? "" : warehouseReceiptNo);
                    }
                }
            }
        }

        //查询当前人员信息
        AmPersonalInfo amPersonalInfo = amPersonalInfoService.queryAmPersonalInfo(operateUser.getPersonalId());

        params.put("entryName", amPersonalInfo.getFullName());

        LimsCaseInfo limsCaseInfo = limsCaseInfoService.selectByCaseId(caseId);
        params.put("limsCaseInfo", limsCaseInfo);
        try {
            limsCaseInfo.getGjkSYSNo().equals(null);
        } catch (Exception ex) {
            logger.error("案件未入库！", ex);
        }
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

//        try {
//            Configuration cfg = new Configuration();
//            cfg.setDefaultEncoding("UTF-8");
//            cfg.setClassForTemplateLoading(this.getClass(), "/templates");
//            //获取模板
//            Template tmp = cfg.getTemplate("inboundOrder.ftl", "UTF-8");
//
//            String filename = "-入库单" + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".doc";
//
//            response.setCharacterEncoding("UTF-8");
//            //文件头，导出的文件名
//            response.setHeader("Content-disposition", "attachment;filename=" + limsCaseInfo.getCaseNo() + new String(filename.getBytes("GBK"), "ISO-8859-1"));
//            //类型
//            response.setContentType("application/x-msdownload");
//            tmp.process(params, response.getWriter());
//        } catch (Exception ex) {
//            logger.error("下载错误！", ex);
//        }
        Writer out = null;
        try {
            Configuration cfg = new Configuration();
            cfg.setDefaultEncoding("UTF-8");
            cfg.setClassForTemplateLoading(this.getClass(), "/templates");
            //获取模板
            Template tmp = cfg.getTemplate("inboundOrder.ftl", "UTF-8");

           /* String filename = (StringUtils.isBlank(limsCaseInfo.getCaseNo()) ? "" : limsCaseInfo.getCaseNo())
                    + "-入库单"
                    + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".doc";*/

            String filename = (StringUtils.isBlank(limsCaseInfo.getCaseNo()) ? "" : limsCaseInfo.getCaseNo())
                    + "-" +  UuidUtil.generateUUID()+ "-"
                    + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".doc";

            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(GeneratePathUtil.getGeneratePath(limsCaseInfo, filename)), "UTF-8"));
            tmp.process(params, out);
            out.flush();
            out.close();
            //文件路径
            filePath = GeneratePathUtil.getGeneratePath(limsCaseInfo, filename);
            String path = null;
            //案件打印 调用案件打印方法
            //根据flag判断是否选中的是打印按钮
            if(flag.equals("2")){
                String page="1";
                if(Strings.isNotBlank(filePath)){
//                    this.httpLink(request,page,filePath);
                    if("1".equals(fullPrint)){
                        path = CasePrintFtpFileUtils.uploadFtpFileNew(ftpIp, ftpPort, ftpUser, ftpPassword, ftpCaseFilePath, filePath, request, page,fullPrint);
                        System.out.println(path.toString());
                    }else{
                        CasePrintFtpFileUtils.uploadFtpFileNew(ftpIp, ftpPort, ftpUser, ftpPassword, ftpFilePath, filePath, request, page,fullPrint);
                    }


                }
            }
            if("1".equals(fullPrint)){
                result.put("filePathName", path);
            }else{
                result.put("filePathName", filePath);
            }
            result.put("success", true);
        }catch (Exception e) {
            logger.info("生成失败:" + e);
            result.put("success", false);
            result.put("message", e.getMessage());
            return result;
        }finally {
            if(out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }



    /**
     * 鉴定书-压缩-打印
     *
     * @param request
     * @param caseId
     * @return
     */
    @RequestMapping("/compressedFilesJds")
    @ResponseBody
    public Map<String, Object> compressedFilesJds(HttpServletRequest request, String caseId,String flag,String fullPrint) {
        String filePath=null;
        Map<String, Object> result = new HashMap<>();
        //根据案件id查询案件信息
        LimsCaseInfo limsCaseInfo = limsCaseInfoService.selectByCaseId(caseId);
        //根据案件id查询委托信息
        List<LimsConsignmentInfo> limsConsignmentInfoList = limsConsignmentInfoService.selectByCaseId(caseId);
        LimsConsignmentInfo limsConsignmentInfo = null;
        //获取主案件委托信息
        for (LimsConsignmentInfo lci : limsConsignmentInfoList) {
            if (lci.getAppendFlag().equals(Constants.APPEND_FLAG_0)) {
                limsConsignmentInfo = lci;
                break;
            }
        }

        Map<String, Object> params = new HashMap<String, Object>();
        //鉴定单位
        params.put("unitName", SystemUtil.getSystemConfig().get("unitName"));
        //证书编号
        params.put("certificateNo", SystemUtil.getSystemConfig().get("certificateNo"));
        //鉴定地址
        params.put("jdsAddress", SystemUtil.getSystemConfig().get("jdsAddress"));
        //鉴定书中委托单位
        params.put("certificateDelegateOrgName", SystemUtil.getSystemConfig().get("certificateDelegateOrgName"));
        //案件信息
        params.put("limsCaseInfo", limsCaseInfo);
        //处理简要案情中使用的特殊字符，里：<>
        String caseBrief = "";
        if (limsCaseInfo != null ) {
            caseBrief = limsCaseInfo.getCaseBrief();
            if (StringUtils.isNotBlank(caseBrief)) {
                //替换左尖括号
                caseBrief = caseBrief.replace("<", "&lt;");
                caseBrief = caseBrief.replace(">", "&gt;");
            }
        }

        params.put("caseBrief", caseBrief);
        //委托信息
        params.put("limsConsignmentInfo", limsConsignmentInfo);
        params.put("limsConsignmentInfoList", limsConsignmentInfoList);
        //案件编号
        String caseSn = "";
        if (StringUtils.isNotBlank(limsCaseInfo.getCaseNo())) {
            if (limsCaseInfo.getCaseNo().length() <= 14) {
                String[] caseSnArr = limsCaseInfo.getCaseNo().split("-");
                if (caseSnArr.length > 1) {
                    caseSn = caseSnArr[1];

                    if (caseSn.toUpperCase().substring(0, 2).toString().equals("FY")) {
                        caseSn = caseSn.substring(2);
                    }
                } else {
                    caseSn = caseSnArr[0];
                }
            } else {
                caseSn = limsCaseInfo.getCaseNo().split("-")[1];
            }
        }
        params.put("caseSn", caseSn);

        //受理日期和补送受理日期
        String acceptDate = "";
        List<Map<String, Object>> sampleList = new ArrayList<>();
        int firstCount = 0;
        int secondCount = 0;
        for (LimsConsignmentInfo lci : limsConsignmentInfoList) {
            if (lci.getAcceptDatetime() != null ) {
                acceptDate += DateUtils.dateToString(lci.getAcceptDatetime(), DateUtils.DATE_SING_STR) + "、";

                List<LimsSampleInfoDna> sampleInfoDnaList = limsSampleInfoDnaService.selectByConsignmentId(lci.getConsignmentId());
                Map<String, Object> resultMap = new LinkedHashMap<>();

                resultMap.put("firstCount", firstCount + 1);
                if (sampleInfoDnaList.size() > 1) {
                    secondCount = firstCount + sampleInfoDnaList.size();
                    resultMap.put("secondCount", secondCount);
                    firstCount = secondCount;
                }

                resultMap.put("acceptAt",  DateUtils.dateToString(lci.getAcceptDatetime(), DateUtils.DATE_SING_STR));

                sampleList.add(resultMap);
            }
        }

        params.put("sampleList", sampleList);

        if (StringUtils.isNotBlank(acceptDate)) {
            acceptDate = acceptDate.substring(0, acceptDate.length() - 1);
        }

        params.put("acceptDate", acceptDate);

        //预实验检材编号
        String yuShiYanBarcode = "";
        //人血检材编号
        String renxueBarcode = "";
        //精斑检材编号
        String jingBanBarcode = "";
        //提取检材编号
        String tiquBarcode = "";
        //扩增试剂盒
        String pcrRegeant = "";
        String semenExists = "";
        //是否存在精斑
        String isSemenExists = "";
        //不存在精斑
        String noSemenExists = "";
        List<LimsSampleInfoDna> limsSampleInfoDnaList = limsSampleInfoDnaService.selectByCaseId(caseId);
        if (ListUtils.isNotNullAndEmptyList(limsSampleInfoDnaList)) {
            for (LimsSampleInfoDna lsid : limsSampleInfoDnaList) {
                if (StringUtils.isNotBlank(lsid.getSampleFlag())) {
                    if (Constants.SAMPLE_FLAG_0.equals(lsid.getSampleFlag())) {
                        if (StringUtils.isNotBlank(lsid.getSampleNo()) && lsid.getSampleNo().contains("-")) {
                            lsid.setSampleNo(lsid.getSampleNo().split("-")[1]);
                        }

                        if (StringUtils.isNotBlank(lsid.getSampleType())) {
                            if (lsid.getSampleType().equals(Constants.SAMPLE_TYPE_01)) {
                                yuShiYanBarcode = yuShiYanBarcode + lsid.getSampleNo() + "、";
                            }

                            if (lsid.getSampleType().equals(Constants.SAMPLE_TYPE_01)) {
                                renxueBarcode = renxueBarcode + lsid.getSampleNo() + "、";
                            }else if (lsid.getSampleType().equals(Constants.SAMPLE_TYPE_02)) {
                                jingBanBarcode = jingBanBarcode + lsid.getSampleNo() + "、";
                            }
                        }
                    }
                }

                List<LabPcrInfo> labPcrInfoList = labPcrInfoService.selectListBySampleId(lsid.getSampleId());
                if (ListUtils.isNotNullAndEmptyList(labPcrInfoList)) {
                    for (LabPcrInfo lpi : labPcrInfoList) {
                        if (StringUtils.isNotBlank(pcrRegeant) && pcrRegeant.equals(lpi.getPcrReagent())) {
                            pcrRegeant += lpi.getPcrReagent() + "、";
                        }else {
                            pcrRegeant = lpi.getPcrReagent();
                        }
                    }
                }

                if (StringUtils.isNotBlank(lsid.getPreMethod2Result())) {
                    if ("+".equals(lsid.getPreMethod2Result())) {
                        isSemenExists = isSemenExists + lsid.getSampleNo() + "、";
                    }else if ("-".equals(lsid.getPreMethod2Result())) {
                        noSemenExists = noSemenExists + lsid.getSampleNo() + "、";
                    }
                }
            }
        }

        params.put("limsSampleInfoDnaList", limsSampleInfoDnaList);
        //扩增试剂盒
        params.put("pcrRegeant", pcrRegeant);

        if (limsSampleInfoDnaList.size() < 10) {
            tiquBarcode = "01-0" + limsSampleInfoDnaList.size();
        }else {
            tiquBarcode = "01-" + limsSampleInfoDnaList.size();
        }

        params.put("tiquBarcode", tiquBarcode);

        //预实验
        if (StringUtils.isNotBlank(yuShiYanBarcode)) {
            yuShiYanBarcode = yuShiYanBarcode.substring(0, yuShiYanBarcode.length() - 1);
        }
        params.put("yuShiYanBarcode", yuShiYanBarcode);

        //DNA提取
        DictItem dictItem = new DictItem();
        //提取实验方法List
        dictItem.setDictTypeCode(Constants.EXTRACTION_TEST_METHOD);
        List<DictItem> extractTestMethodList = DictUtil.getDictList(dictItem);
        List<Map<String, Object>> extractList = new ArrayList<>();
        if (ListUtils.isNotNullAndEmptyList(extractTestMethodList) && ListUtils.isNotNullAndEmptyList(limsSampleInfoDnaList)) {
            for (DictItem di : extractTestMethodList) {
                Map<String, Object> map = new HashMap<>();
                String sample = "";
                for (LimsSampleInfoDna lsid : limsSampleInfoDnaList) {
                    List<LabExtractSample> labExtractSampleList = labExtractSampleService.selectLabExtractSampleBySampleId(lsid.getSampleId());
                    if (ListUtils.isNotNullAndEmptyList(labExtractSampleList)) {
                        LabExtractSample labExtractSample = labExtractSampleList.get(0);
                        if (labExtractSample != null) {
                            if (di.getDictCode().equals(labExtractSample.getExtractMethod())) {
                                sample += lsid.getSampleNo() + "、";
                            }
                        }
                    }
                }
                if (StringUtils.isNotBlank(sample)) {
                    if (sample.contains("、")) {
                        sample = sample.substring(0, sample.length() - 1);
                    }
                    map.put("extractTestMethod", di.getDictName());
                    map.put("sampleNo", sample);

                    extractList.add(map);
                }
            }
        }
        params.put("extractList", extractList);

        //精斑检材
        if (StringUtils.isNotBlank(jingBanBarcode)) {
            jingBanBarcode = jingBanBarcode.substring(0, jingBanBarcode.length() - 1);
            isSemenExists = jingBanBarcode.substring(0, jingBanBarcode.length() - 1);
            noSemenExists = jingBanBarcode.substring(0, jingBanBarcode.length() - 1);
        }
        params.put("jingBanBarcode", jingBanBarcode);
        params.put("isSemenExists", isSemenExists);
        params.put("noSemenExists", noSemenExists);

        //人血检材
        if (StringUtils.isNotBlank(renxueBarcode)) {
            renxueBarcode = renxueBarcode.substring(0, renxueBarcode.length() - 1);
        }
        params.put("renxueBarcode", renxueBarcode);

        //基因型列表
        List<MatchAuditedGene> matchAuditedGeneList = matchAuditedGeneService.selectByCaseId(caseId);

        Writer out = null;
        try {
            Configuration cfg = new Configuration();
            cfg.setDefaultEncoding("UTF-8");
            cfg.setClassForTemplateLoading(this.getClass(), "/templates");
            //获取模板
            Template tmp = cfg.getTemplate("sameCompareIdentifyBook.ftl", "UTF-8");

          /*  String filename = (StringUtils.isBlank(limsCaseInfo.getCaseNo()) ? "" : limsCaseInfo.getCaseNo())
                    + "_" + (StringUtils.isBlank(limsCaseInfo.getCaseName()) ? "" : limsCaseInfo.getCaseName()) + "_"
                    + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".doc";*/
            String filename = UuidUtil.generateUUID()+ "-" + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".doc";


            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(GeneratePathUtil.getGeneratePath(limsCaseInfo, filename)), "UTF-8"));
            tmp.process(params, out);
            out.flush();
            out.close();
            //文件路径
            filePath = GeneratePathUtil.getGeneratePath(limsCaseInfo, filename);
            String path = null;
            //案件打印 调用案件打印方法
            //根据flag判断是否选中的是打印按钮
            if(flag.equals("2")){
                String page="1";
                if(Strings.isNotBlank(filePath)){
//                    this.httpLink(request,page,filePath);
                    if("1".equals(fullPrint)){
                        path = CasePrintFtpFileUtils.uploadFtpFileNew(ftpIp, ftpPort, ftpUser, ftpPassword, ftpCaseFilePath, filePath, request, page,fullPrint);
                        System.out.println(path.toString());
                    }else{
                        CasePrintFtpFileUtils.uploadFtpFileNew(ftpIp, ftpPort, ftpUser, ftpPassword, ftpFilePath, filePath, request, page,fullPrint);
                    }


                }
            }

            if("1".equals(fullPrint)){
                result.put("filePathName", path);
            }else{
                if(!"".equals(filePath) && filePath != null){
                    result.put("filePathName", filePath);
                }
            }

            result.put("success", true);
        }catch (Exception e) {
            logger.info("生成失败:" + e);
//            result.put("success", false);
//            result.put("message", e.getMessage());
            return result;
        }finally {
            if(out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }


    /**
     * 下载受理确认书-压缩-打印
     *
     * @param request
     * @param response
     * @param consignmentId
     */
    @RequestMapping("/compressedFilesSlqrs")
    @ResponseBody
    public  Map<String, Object> compressedFilesSlqrs(HttpServletRequest request, HttpServletResponse response,
                                                     String consignmentId,String flag,String fullPrint) throws ParseException {
        Map<String, Object> result = new HashMap<>();
        Writer out = null;
        String filePathName = null;
        LimsCaseInfo caseInfo = limsCaseInfoService.selectByConsignmentId(consignmentId);
        LimsConsignmentInfo consignment = limsConsignmentInfoService.selectByConsignmentId(consignmentId);
        List<LimsSampleInfoDna> limsSampleInfoDnaList = limsSampleInfoDnaService.selectListByConsignmentId(consignmentId);
        AmPersonalInfo amPersonalInfo1 = amPersonalInfoService.selectByPersonalId(consignment.getDelegator1Id());
        AmPersonalInfo amPersonalInfo2 = amPersonalInfoService.selectByPersonalId(consignment.getDelegator2Id());

        OrgInfo orgInfo = orgInfoService.selectByPrimaryKey(consignment.getAreaOrgCode());

        AmPersonalInfo acceptPersonInfo = new AmPersonalInfo();
        if (consignment.getAcceptorId() != null) {
            acceptPersonInfo = amPersonalInfoService.selectByPersonalId(consignment.getAcceptorId());
        }
        DictItem posItionDictItem = new DictItem();
        posItionDictItem.setDictTypeCode(Constants.POSITION_LIST);
        List<DictItem> posItionList = DictUtil.getDictList(posItionDictItem);

        for (DictItem posItion : posItionList) {
            if (amPersonalInfo1 != null && posItion.getDictCode().equals(amPersonalInfo1.getPosition())) {
                consignment.setDelegator1Position(posItion.getDictName());
            }
            if (amPersonalInfo2 != null && posItion.getDictCode().equals(amPersonalInfo2.getPosition())) {
                consignment.setDelegator2Position(posItion.getDictName());
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
        if (StringUtils.isNotEmpty(consignment.getAutographPicture())) {
            params.put("image", consignment.getAutographPicture().replace("data:image/png;base64,", ""));
        } else {
            params.put("image", "");
            params.put("nullImage", "____________________________");
        }
        params.put("consignmentNo", StringUtils.isBlank(consignment.getConsignmentNo()) ? "" : consignment.getConsignmentNo());
        params.put("caseXkNo", StringUtils.isBlank(caseInfo.getCaseXkNo()) ? "" : caseInfo.getCaseXkNo());
        params.put("acceptor", StringUtils.isBlank(consignment.getRefusePerson()) ? "" : consignment.getRefusePerson());
        //委托单位
        params.put("delegateOrgName", StringUtils.isBlank(consignment.getDelegateOrgName()) ? "" : consignment.getDelegateOrgName());
        if (orgInfo != null) {
            params.put("areaOrgName", StringUtils.isBlank(orgInfo.getOrgName()) ? "" : orgInfo.getOrgName());

        } else {
            params.put("areaOrgName", "");
        }
//        params.put("areaOrgName", StringUtils.isBlank(orgInfo.getOrgName()) ? "" : orgInfo.getOrgName());
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

        boolean bloodFlag = false;
        boolean seminalFlag = false;
        //送检样本
        if (limsSampleInfoDnaList.size() > 0) {
            for (LimsSampleInfoDna sampleInfo : limsSampleInfoDnaList) {
                if (sampleInfo != null) {
                    sampleInfo.setSampleNo(StringUtils.isBlank(sampleInfo.getSampleNo()) ? "" : sampleInfo.getSampleNo());
                    sampleInfo.setSampleName(StringUtils.isBlank(sampleInfo.getSampleName()) ? "" : sampleInfo.getSampleName());
                    sampleInfo.setSampleDesc(StringUtils.isBlank(sampleInfo.getSampleDesc()) ? "" : sampleInfo.getSampleDesc());

                    DictItem dictsamplePackling = new DictItem();
                    dictsamplePackling.setDictTypeCode(Constants.PACK_METHOD);
                    List<DictItem> samplePacklingList = DictUtil.getDictList(dictsamplePackling);
                    for (DictItem samplePackling : samplePacklingList) {
                        if (StringUtils.isNotBlank(sampleInfo.getSamplePacking())) {
                            if (sampleInfo.getSamplePacking().equals(samplePackling.getDictCode())) {
                                sampleInfo.setSamplePackingName(samplePackling.getDictName());
                            }
                        }
                    }
                    sampleInfo.setSamplePackingName(StringUtils.isBlank(sampleInfo.getSamplePackingName()) ? "" : sampleInfo.getSamplePackingName());
                    DictItem dictsampleType = new DictItem();
                    dictsampleType.setDictTypeCode(Constants.SAMPLE_TYPE);
                    List<DictItem> sampleTypeList = DictUtil.getDictList(dictsampleType);
                    for (DictItem sampleType : sampleTypeList) {
                        if (StringUtils.isNotBlank(sampleInfo.getSampleType())) {
                            if (sampleInfo.getSampleType().equals(sampleType.getDictCode())) {
                                sampleInfo.setSampleTypeName(sampleType.getDictName());
                            }
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
            }

        }


        if (bloodFlag) {
            params.put("bloodFlag", "blood");
        } else {
            params.put("bloodFlag", null);
        }

        if (seminalFlag) {
            params.put("seminalFlag", "seminal");
        } else {
            params.put("seminalFlag", null);
        }

        List<List<LimsSampleInfoDna>> limsSampleInfoDataList = DownloadFileUtils.splitList(limsSampleInfoDnaList, 19);

        params.put("limsSampleInfoDataList", limsSampleInfoDataList);

        /*if (limsSampleInfoDnaList.size() < 11) {
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
        }*/

        if (StringUtils.isBlank(caseInfo.getXkANo())) {
            params.put("xkANo", "                    ");
        } else {
            params.put("xkANo", StringUtils.isBlank(caseInfo.getXkANo()) ? "" : caseInfo.getXkANo());
        }

        params.put("sampleList", limsSampleInfoDnaList);
        if (consignment.getAcceptDatetime() != null) {
            params.put("currentDay", StringUtils.isBlank(DateUtils.dateToString(consignment.getAcceptDatetime(), "yyyy-MM-dd")) ? "" : DateUtils.dateToString(consignment.getAcceptDatetime(), "yyyy-MM-dd"));
        } else {
            params.put("currentDay", "");
        }


        if (StringUtils.isBlank(consignment.getTakePerson())) {
            params.put("takePerson", "____________________________");
        } else {
            params.put("takePerson", consignment.getTakePerson());
        }

        String orgQualification = orgInfoService.selectLabNameById(consignment.getAcceptOrgId());
        params.put("orgQualification", StringUtils.isBlank(orgQualification) ? "" : orgQualification);

        params.put("orgQNo", Constants.selectOrgQNo(consignment.getAcceptOrgId()));
        params.put("orgCode", StringUtils.isBlank(consignment.getAcceptOrgId()) ? "" : consignment.getAcceptOrgId());


        params.put("barcode1", BarcodeUtil.generateFile(StringUtils.isBlank(caseInfo.getCaseNo()) ? "" : StringUtils.substringBefore(caseInfo.getCaseNo(), "-"), "barcode1.png"));

        params.put("barcode2", BarcodeUtil.generateFile(StringUtils.isBlank(caseInfo.getCaseNo()) ? "" : StringUtils.substringAfter(caseInfo.getCaseNo(), "-"), "barcode2.png"));

//        if(StringUtils.isNotBlank(caseInfo.getCaseNo())){
//            Pattern p = Pattern.compile("[\u4e00-\u9fa5]"); // 正则表达式 判断是否包含汉字
//            Matcher m = p.matcher(caseInfo.getCaseNo()); // 操作的字符串
//            boolean b = m.matches();
//            if(b){
//                params.put("barcode1","");
//            }else{
//                params.put("barcode1",BarcodeUtil.generateFile(caseInfo.getCaseNo(),"barcode1.png"));
//            }
//        }else{
//            params.put("barcode1",BarcodeUtil.generateFile(StringUtils.isBlank(caseInfo.getCaseNo()) ? "" : caseInfo.getCaseNo(), "barcode1.png"));
//        }
//        if(StringUtils.isNotBlank(consignment.getConsignmentNo())){
//            Pattern p = Pattern.compile("[\u4e00-\u9fa5]"); // 正则表达式 判断是否包含汉字
//            Matcher m = p.matcher(consignment.getConsignmentNo()); // 操作的字符串
//            boolean b = m.matches();
//            if(b){
//                params.put("barcode2","");
//            }else{
//                params.put("barcode2",BarcodeUtil.generateFile(consignment.getConsignmentNo(),"barcode2.png"));
//            }
//        }else{
//            params.put("barcode2",BarcodeUtil.generateFile(StringUtils.isBlank(consignment.getConsignmentNo()) ? "" : consignment.getConsignmentNo(), "barcode2.png"));
//        }
        String appendFlag = StringUtils.isBlank(consignment.getAppendFlag()) ? "" : consignment.getAppendFlag();
        params.put("appendFlag", appendFlag);
        if (caseInfo.getFirstChecker() != null) {
            AmPersonalInfo acceptPersonInfo12 = amPersonalInfoService.selectByPersonalId(caseInfo.getFirstChecker());
            if (acceptPersonInfo12 != null) {
                params.put("firstChecker", StringUtils.isBlank(acceptPersonInfo12.getFullName()) ? "" : acceptPersonInfo12.getFullName());
            } else {
                params.put("firstChecker", "");
            }


        }
        try {
            Configuration cfg = new Configuration();
            cfg.setDefaultEncoding("UTF-8");
            cfg.setClassForTemplateLoading(this.getClass(), "/templates");
            //获取模板
            String ftlName = "acceptInfo.ftl";
            if (StringUtils.isNotBlank(appendFlag) && appendFlag.equals("1")) {

                params.put("orgQNo", Constants.selectRepOrgQNo(consignment.getAcceptOrgId()));

                ftlName = "acceptInfoR.ftl";
            }
            Template tmp = cfg.getTemplate(ftlName, "UTF-8");

            //String filename = "-鉴定事项确认书" + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".doc";
            String filename =   UuidUtil.generateUUID() + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".doc";

            if (StringUtils.isNotBlank(appendFlag) && appendFlag.equals("1")) {
                // filename = "-补送鉴定事项确认书" + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".doc";
                filename = UuidUtil.generateUUID() + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".doc";
            }
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(getGeneratePath(caseInfo, filename)), "UTF-8"));
            tmp.process(params, out);
            out.flush();
            out.close();

            filePathName = getGeneratePath(caseInfo, filename);
            String path = "";
            //案件打印 调用案件打印方法
            //根据flag判断是否选中的是打印按钮
            if(flag.equals("2")){
                String page="1";
                if(Strings.isNotBlank(filePathName)){
                    if("1".equals(fullPrint)){
                        path = CasePrintFtpFileUtils.uploadFtpFileNew(ftpIp, ftpPort, ftpUser, ftpPassword, ftpCaseFilePath, filePathName, request, page,fullPrint);
                        System.out.println(path.toString());
                    }else{
                        CasePrintFtpFileUtils.uploadFtpFileNew(ftpIp, ftpPort, ftpUser, ftpPassword, ftpFilePath, filePathName, request, page,fullPrint);
                    }


                }
            }

            if("1".equals(fullPrint)){
                result.put("filePathName", path);
            }else{
                if(!"".equals(filePathName) && filePathName != null){
                    result.put("filePathName", filePathName);
                }
            }



        } catch (Exception ex) {
            logger.error("下载错误！", ex);
        }
        return result;
    }


    /**
     * 下载预检验表-压缩-打印
     *
     * @param request
     * @param response
     * @param consignmentId
     * @throws ParseException
     */
    @RequestMapping("compressedFilesYsy")
    @ResponseBody
    public Map<String, Object> compressedFilesYsy(HttpServletRequest request, HttpServletResponse response,
                                                  String consignmentId,String flag,String fullPrint) throws ParseException {
        Map<String, Object> result = new HashMap<>();
        Writer out = null;
        String filePathName = null;
        String path = "";

//        String acceptFilePath = null;
//        acceptFilePath = acceptZipDownload(request, response, consignmentId);
//        if (!"1".equals(flag)) {
//            flag = preAceptDocZip(request, response, consignmentId);
//        }

        LimsCaseInfo caseInfo = limsCaseInfoService.selectByConsignmentId(consignmentId);
        LimsConsignmentInfo consignment = limsConsignmentInfoService.selectByConsignmentId(consignmentId);
        List<LimsSampleInfoDna> limsSampleInfoDnaList = limsSampleInfoDnaService.selectListByConsignmentId(consignmentId);
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

        boolean bloodFlag = false;
        boolean seminalFlag = false;
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

        if (bloodFlag) {
            params.put("bloodFlag", "blood");
        } else {
            params.put("bloodFlag", null);
        }

        if (seminalFlag) {
            params.put("seminalFlag", "seminal");
        } else {
            params.put("seminalFlag", null);
        }

        params.put("sampleList", limsSampleInfoDnaList);

        try {
            Configuration cfg = new Configuration();
            cfg.setDefaultEncoding("UTF-8");
            cfg.setClassForTemplateLoading(this.getClass(), "/templates");
            //获取模板
            Template tmp = cfg.getTemplate("preAccept.ftl", "UTF-8");

            //   String filename = "-预实验检验记录表" + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".doc";
            String filename =  UuidUtil.generateUUID()+ "-" + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".doc";

            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(getGeneratePath(caseInfo, filename)), "UTF-8"));
            tmp.process(params, out);
            filePathName = getGeneratePath(caseInfo, filename);
            String preFilePath = null;
            preFilePath = preAceptDocZip(request, response, consignmentId,fullPrint);
            out.flush();
//            out.close();


            //案件打印 调用案件打印方法
            //根据flag判断是否选中的是打印按钮
            if(flag.equals("2")){
                String page="1";
                if(Strings.isNotBlank(filePathName)){
//                    this.httpLink(request,page,filePathName);

                    if("1".equals(fullPrint)){
                        path = CasePrintFtpFileUtils.uploadFtpFileNew(ftpIp, ftpPort, ftpUser, ftpPassword, ftpFilePath, filePathName, request, page,fullPrint);
                        System.out.println(path.toString());
                    }else{
                        CasePrintFtpFileUtils.uploadFtpFileNew(ftpIp, ftpPort, ftpUser, ftpPassword, ftpFilePath, filePathName, request, page,fullPrint);
                    }
                }
            }

        } catch (Exception ex) {
            logger.error("", ex);
        }
        finally {
            if (out != null) {
                try {
                    //关闭输出文件流  上传文件到FTP
//                    PrintFtpFileUtils.uploadFtpFile(ftpIp,ftpPort,ftpUser,ftpPassword,ftpFilePath,filePathName,request,page);

                    if("1".equals(fullPrint)){
                        path = CasePrintFtpFileUtils.uploadFtpFileNew(ftpIp, ftpPort, ftpUser, ftpPassword, ftpCaseFilePath, filePathName, request, page,fullPrint);
                        System.out.println(path.toString());
                    }else{
                        CasePrintFtpFileUtils.uploadFtpFileNew(ftpIp, ftpPort, ftpUser, ftpPassword, ftpFilePath, filePathName, request, page,fullPrint);
                    }

                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if("1".equals(fullPrint)){
            result.put("filePathName", path);
        }else{
            if(!"".equals(filePathName) && filePathName != null){
                result.put("filePathName", filePathName);
            }
        }


        return result;
    }

    /**
     * 下载检材流转记录表-压缩-打印
     *
     * @param request
     * @param response
     * @param consignmentId
     * @throws ParseException
     */
    @RequestMapping("/compressedFilesJclz")
    @ResponseBody
    public Map<String, Object> compressedFilesJclz(HttpServletRequest request, HttpServletResponse response,
                                                   String consignmentId,String flag,String fullPrint) throws ParseException {
        Map<String, Object> result = new HashMap<>();
        Writer out = null;
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
        if(consignment != null){
            if(StringUtils.isNotBlank(consignment.getAcceptOrgId())){
                params.put("orgQNo",Constants.selectOrgQNo(consignment.getAcceptOrgId()));
                String orgQualification = orgInfoService.selectLabNameById(consignment.getAcceptOrgId());
                params.put("orgQualification", StringUtils.isBlank(orgQualification) ? "" :orgQualification);
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
        String path = null;

        try {
            Configuration cfg = new Configuration();
            cfg.setDefaultEncoding("UTF-8");
            cfg.setClassForTemplateLoading(this.getClass(), "/templates");
            //获取模板
            Template tmp = cfg.getTemplate("circulationRecord.ftl", "UTF-8");

            //  String filename = "-样本检材流转表" + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".doc";
            String filename =  UuidUtil.generateUUID()+ "-" + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".doc";

            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(getGeneratePath(caseInfo, filename)), "UTF-8"));
            tmp.process(params, out);
            filePathName = getGeneratePath(caseInfo, filename);
            out.flush();
            out.close();
            //案件打印 调用案件打印方法
            //根据flag判断是否选中的是打印按钮
            if(flag.equals("2")){
                String page="1";
                if(Strings.isNotBlank(filePathName)){

//                    this.httpLink(request,page,filePathName);

                    if("1".equals(fullPrint)){
                        path = CasePrintFtpFileUtils.uploadFtpFileNew(ftpIp, ftpPort, ftpUser, ftpPassword, ftpCaseFilePath, filePathName, request, page,fullPrint);
                        System.out.println(path.toString());
                    }else{
                        CasePrintFtpFileUtils.uploadFtpFileNew(ftpIp, ftpPort, ftpUser, ftpPassword, ftpFilePath, filePathName, request, page,fullPrint);
                    }
                }
            }
        } catch (Exception ex) {
            logger.error("下载错误", ex);
        } finally {
//            try {
////                response.getWriter();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }

        if("1".equals(fullPrint)){
            result.put("filePathName", path);
        }else{
            if(!"".equals(filePathName) && filePathName != null){
                result.put("filePathName", filePathName);
            }
        }

        return result;
    }




    private String getGeneratePath(LimsCaseInfo caseInfo, String filename) {
        String writeFilePath = SystemUtil.getSystemConfig().getProperty("DownloadPath");
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

    private static String makePathFile(String filePathName) {
        File pathFile = new File(filePathName);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }

        return pathFile + "\\\\";
    }

    /**
     * 下载压缩包
     *
     * @param response
     * @param
     */
    @RequestMapping("/downLoadZip")
    private void downLoadZip(HttpServletRequest request, HttpServletResponse response) {
        try {
            File file = new File(String.valueOf(writeFilePath));
            if (file.exists()) {
                InputStream ins = new FileInputStream(String.valueOf(writeFilePath));
                BufferedInputStream bins = new BufferedInputStream(ins);// 放到缓冲流里面
                OutputStream outs = response.getOutputStream();// 获取文件输出IO流
                BufferedOutputStream bouts = new BufferedOutputStream(outs);
                response.setContentType("application/x-download");// 设置response内容的类型
                response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(file.getName(), "UTF-8"));// 设置头部信息
                int bytesRead = 0;
                byte[] buffer = new byte[8192];
                // 开始向网络传输文件流
                while ((bytesRead = bins.read(buffer, 0, 8192)) != -1) {
                    bouts.write(buffer, 0, bytesRead);
                }
                bouts.flush();// 这里一定要调用flush()方法
                ins.close();
                bins.close();
                outs.close();
                bouts.close();
            }
        } catch (IOException e) {
            logger.error("文件下载出错", e);
        }
    }


    private static String httpLinks(HttpServletRequest request,String page, String ftpFilePath) {
        //获取访问者的ip
        //        String url = "http://127.0.0.1:927/PrintDoc?jquest=2342";
        String url = "http://"+IpAddressUtils.getIpAddr(request)+":927/PrintDoc?jquest=2343";
        StringBuilder json = new StringBuilder();
        String result = "";
        try {
            URL u = new URL(url);
            HttpURLConnection uc = (HttpURLConnection) u.openConnection();
            // 设置请求方式为post
            uc.setRequestMethod("POST");
            //设置连接输出流为true,默认false (post 请求是以流的方式隐式的传递参数)
            uc.setDoOutput(true);
            // post请求缓存设为false
            uc.setUseCaches(false);

            // 设置该HttpURLConnection实例是否自动执行重定向
            uc.setInstanceFollowRedirects(true);

            // 设置请求头里面的各个属性 (以下为设置内容的类型,设置为经过urlEncoded编码过的from参数)
            // application/x-javascript text/xml->xml数据
            //application/x-javascript->json对象
            //application/x-www-form-urlencoded->表单数据
            //这是重点^_^
            uc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            // 建立连接 (请求未开始,直到connection.getInputStream()方法调用时才发起,以上各个参数设置需在此方法之前进行)
            uc.connect();
            // 创建输入输出流,用于往连接里面输出携带的参数,(输出内容为?后面的内容)
            DataOutputStream dataout = new DataOutputStream(uc.getOutputStream());

            String docpath=ftpFilePath;
            StringBuffer strBuf = new StringBuffer();
            strBuf.append("{\"docPath\":\"" + docpath + "\", \"page\":\"" + page+ "\"}");

            // 将参数输出到连接
            dataout.write(strBuf.toString().getBytes());
            // 输出完成后刷新并关闭流
            dataout.flush();
            dataout.close(); // 重要且易忽略步骤 (关闭流,切记!)

            BufferedReader bd = new BufferedReader(new InputStreamReader(uc.getInputStream(), "GBK"));
            String s = null;
            while ((s = bd.readLine()) != null) {
                json.append(s);
            }
            bd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        result = json.toString();
        return result;
    }

    private static String httpLink(HttpServletRequest request,String page, String ftpFilePath) {
        //获取访问者的ip
        //        String url = "http://127.0.0.1:927/PrintDoc?jquest=2342";
        String url = "http://"+IpAddressUtils.getIpAddr(request)+":927/PrintDoc?jquest=2342";
        StringBuilder json = new StringBuilder();
        String result = "";
        try {
            URL u = new URL(url);
            HttpURLConnection uc = (HttpURLConnection) u.openConnection();
            // 设置请求方式为post
            uc.setRequestMethod("POST");
            //设置连接输出流为true,默认false (post 请求是以流的方式隐式的传递参数)
            uc.setDoOutput(true);
            // post请求缓存设为false
            uc.setUseCaches(false);

            // 设置该HttpURLConnection实例是否自动执行重定向
            uc.setInstanceFollowRedirects(true);

            // 设置请求头里面的各个属性 (以下为设置内容的类型,设置为经过urlEncoded编码过的from参数)
            // application/x-javascript text/xml->xml数据
            //application/x-javascript->json对象
            //application/x-www-form-urlencoded->表单数据
            //这是重点^_^
            uc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            // 建立连接 (请求未开始,直到connection.getInputStream()方法调用时才发起,以上各个参数设置需在此方法之前进行)
            uc.connect();
            // 创建输入输出流,用于往连接里面输出携带的参数,(输出内容为?后面的内容)
            DataOutputStream dataout = new DataOutputStream(uc.getOutputStream());

            String docpath=ftpFilePath;
            StringBuffer strBuf = new StringBuffer();
            strBuf.append("{\"docPath\":\"" + docpath + "\", \"page\":\"" + page+ "\"}");

            // 将参数输出到连接
            dataout.write(strBuf.toString().getBytes());
            // 输出完成后刷新并关闭流
            dataout.flush();
            dataout.close(); // 重要且易忽略步骤 (关闭流,切记!)

            BufferedReader bd = new BufferedReader(new InputStreamReader(uc.getInputStream(), "GBK"));
            String s = null;
            while ((s = bd.readLine()) != null) {
                json.append(s);
            }
            bd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        result = json.toString();
        return result;
    }

    /**
     * 根据ftp路径查看图谱
     * @param request
     * @param response
     * @param imagePath
     */
    @RequestMapping("/viewGenePhoto")
    public void viewPhoto(HttpServletRequest request, HttpServletResponse response, String imagePath) {
        //创建客户端对象
        FTPClient ftp = new FTPClient();
        ServletOutputStream os = null;
        try {

            //连接ftp服务器
            ftp.connect(ftpIp, Integer.valueOf(ftpPort));
            //登录
            ftp.login(ftpUser, ftpPassword);
            if (StringUtils.isNotBlank(imagePath)) {
                try {
                    imagePath = URLDecoder.decode(imagePath, "UTF-8");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            os = response.getOutputStream();
            FtpDownLoadFileUtils ftpTest = new FtpDownLoadFileUtils();
            String ftpPath = imagePath.substring(0, imagePath.lastIndexOf("/") + 1);
            String remotePath = ftpPath.split(ftpIp)[1];
            String fileName = imagePath.substring(imagePath.lastIndexOf("/") + 1);
            boolean success = ftpTest.downFile(ftpIp, ftpPort, ftpUser, ftpPassword, remotePath, fileName, samplePhotosRootDir);
            if (success) {
                byte[] result = ftpTest.getImageBinary(samplePhotosRootDir, fileName);
                os.write(result);
            }
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            os.close();
            //退出FTP服务器
            ftp.logout();
            //关闭FTP连接
            ftp.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    /**
     * 资质证书照片下载
     *
     * @param request
     * @param response
     */
    @RequestMapping("/dowmFileAmpPhotos")
    @ResponseBody
    public Map<String, Object> dowmFileAmpPhotos(HttpServletRequest request, HttpServletResponse response, String consignmentId,String flag,String fullPrint) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> params = new HashMap<String, Object>();
        Writer out = null;
        String filePathName = null;

        LimsCaseInfo caseInfo = limsCaseInfoService.selectByConsignmentId(consignmentId);
        LimsConsignmentInfo consignment = limsConsignmentInfoService.selectByConsignmentId(consignmentId);

        String path = null;

        try {
            Configuration cfg = new Configuration();
            cfg.setDefaultEncoding("UTF-8");
            cfg.setClassForTemplateLoading(this.getClass(), "/templates");
            //获取模板
            String ftlName = "appraisalCertificate.ftl";

            Template tmp = cfg.getTemplate(ftlName, "UTF-8");

            String filename = UuidUtil.generateUUID()+ "-" + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".doc";

            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(getGeneratePath(caseInfo, filename)), "UTF-8"));
            tmp.process(params, out);
            out.flush();
            out.close();
            filePathName = getGeneratePath(caseInfo, filename);
            //案件打印 调用案件打印方法
            //根据flag判断是否选中的是打印按钮
            if(flag.equals("2")){
                String page="1";
                if(Strings.isNotBlank(filePathName)){
//                    this.httpLink(request,page,filePathName);

                    if("1".equals(fullPrint)){
                        path = CasePrintFtpFileUtils.uploadFtpFileNew(ftpIp, ftpPort, ftpUser, ftpPassword, ftpCaseFilePath, filePathName, request, page,fullPrint);
                        System.out.println(path.toString());
                    }else{
                        CasePrintFtpFileUtils.uploadFtpFileNew(ftpIp, ftpPort, ftpUser, ftpPassword, ftpFilePath, filePathName, request, page,fullPrint);
                    }


                }
            }
        } catch (Exception ex) {
            logger.error("下载错误！", ex);
        }

        if("1".equals(fullPrint)){
            result.put("filePathName", path);
        }else{
            result.put("filePathName", filePathName);
        }
        return result;

    }





    @RequestMapping(value = "/printFilesPhotos")
    @ResponseBody
    public Map<String, Object> printFilesPhotos(HttpServletRequest request, HttpServletResponse response, String consignmentId) throws ParseException {
        Map<String, Object> result = new HashMap<String, Object>();
        String flag="2";

        Writer out = null;
        String filePathName = null;
        List<Object> list1 = new ArrayList<Object>();

        String fullPrint = "1";

        LimsCaseInfo limsCaseInfo = new LimsCaseInfo();
        if(StringUtils.isNotBlank(consignmentId)){
            limsCaseInfo = limsCaseInfoService.selectByConsignmentId(consignmentId);
            limsCaseInfo.setConsignmentId(consignmentId);
        }
        String filePathNamePhoto = (String) this.dowmFileAmpPhotos(request, response, limsCaseInfo.getConsignmentId(),flag,fullPrint).get("filePathName");
        list1.add(filePathNamePhoto);

        String ss = "";

        if(list1.size() >0){
            for(int i =0;i < list1.size();i++){
                ss += list1.get(i) + ",";
            }
        }

        String s = CasePrintFtpFileUtils.httpLinkNew(request, ftpIp, page, ftpUser, ftpPassword, ftpCaseFilePath,ss);

        return result;
    }


}
