package com.bazl.clims.controller.center;

import com.alibaba.fastjson.JSON;
import com.bazl.clims.common.Constants;
import com.bazl.clims.controller.BaseController;
import com.bazl.clims.model.DictItem;
import com.bazl.clims.model.LoaUserInfo;
import com.bazl.clims.model.PageInfo;
import com.bazl.clims.model.bo.LabListModel;
import com.bazl.clims.model.bo.SyLiistModel;
import com.bazl.clims.model.po.*;
import com.bazl.clims.model.vo.AmPersonalInfoVo;
import com.bazl.clims.model.vo.LabAnalysisInfoVo;
import com.bazl.clims.model.vo.LabTaskInfoVo;
import com.bazl.clims.service.*;
import com.bazl.clims.utils.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.bazl.clims.utils.DateUtils.addDays;

/**
 * Created by Sun on 2018/12/20.
 */
@Controller
@RequestMapping("/center")
public class TestProcessController extends BaseController{

    @Autowired
    AmPersonalInfoService amPersonalInfoService;
    @Autowired
    LimsCaseInfoService limsCaseInfoService;
    @Autowired
    LimsSampleInfoDnaService limsSampleInfoDnaService;
    @Autowired
    LabTaskInfoService labTaskInfoService;
    @Autowired
    LabExtractInfoService labExtractInfoService;
    @Autowired
    LabExtractSampleService labExtractSampleService;
    @Autowired
    LabPcrInfoService labPcrInfoService;
    @Autowired
    LabPcrSampleService labPcrSampleService;
    @Autowired
    LabSyInfoService labSyInfoService;
    @Autowired
    LabSySampleService labSySampleService;
    @Autowired
    EquipmentNameInfoService equipmentNameInfoService;
    @Autowired
    EquipmentTypeInfoService equipmentTypeInfoService;
    @Autowired
    PanelService panelService;
    @Autowired
    DictItemService dictItemService;
    @Autowired
    LabExtractKitService labExtractKitService;
    @Autowired
    SeqNoGenerateService seqNoGenerateService;
    /*@Autowired
    LabPuzzleMiddleService labPuzzleMiddleService;*/
    @Autowired
    DownloadFileUtils downloadFileUtils;
    @Autowired
    OrgInfoService orgInfoService;
    @Autowired
    ReagentInfoService reagentInfoService;
    @Autowired
    LabAnalysisInfoService labAnalysisInfoService;
    @Autowired
    LimsSampleGeneService limsSampleGeneService;
    @Autowired
    ExperimentalParameterService experimentalParameterService;

    @Autowired
    UseInstrumentsService useInstrumentsService;

    @Autowired
    ReagentOutgoInfoService reagentOutgoInfoService;

    @Autowired
    QcNoSettingsService  qcNoSettingsService;

//    @Value("${routine_extraction_records_no}")
//    private String routineExtractionNo;

//    @Value("${seminal_extraction_no}")
//    private String seminalExtractionNo;
//
//    @Value("${electrophoretic_recording_no}")
//    private String electrophoreticRecordingNo;
//
//    @Value("${electrophoretic_automatic_no}")
//    private String electrophoretic_automatic_no;

    @Value("${inspectCourseUrl}")
    private String inspectCourseUrl;

    @RequestMapping("/testInspectCourse")
    public ModelAndView testInspectCourse(HttpServletRequest request,PageInfo pageInfo, LabTaskInfoVo labTaskInfoVo, String taskStartDatetim, String taskEndDatetim) throws ParseException, Exception{
        ModelAndView view = new ModelAndView();
        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if (operateUser == null) {
            view.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return view;
        }
        String realIP = IpAddressUtils.getRealIP();//获得本机IP
        view.addObject("inspectCourseUrl",inspectCourseUrl);
        view.addObject("ip", realIP);
        view.addObject("operateUser", operateUser);
        view.setViewName("manualExtraction/testInspectCourse");
        return view;
    }

    @RequestMapping("/testProcess")
    public ModelAndView testProcess(PageInfo pageInfo, LabTaskInfoVo labTaskInfoVo, String taskStartDatetim, String taskEndDatetim) throws ParseException {
        ModelAndView view = new ModelAndView();
        view.setViewName("testProcess/testProcess");

        /*if(taskStartDatetim != null && taskStartDatetim.trim() != "" && taskEndDatetim != null && taskEndDatetim.trim() != ""){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            labTaskInfoVo.getEntity().setTaskStartDatetime(simpleDateFormat.parse(taskStartDatetim));
            labTaskInfoVo.getEntity().setTaskEndDatetime(simpleDateFormat.parse(taskEndDatetim));
        }*/


        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();

        if (operateUser == null) {
            view.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return view;
        }
        //获取单位信息
        OrgInfo orgInfo = orgInfoService.selectByPrimaryKey(operateUser.getOrgId());


        String userOrgId = operateUser.getOrgId();
        //将当前用户org_id设置进query
        if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
            userOrgId = "110230000000";
        }
        labTaskInfoVo.getEntity().setDelegateOrgCode(userOrgId);
        /**
         * 获取实验人员信息
         */
        List<AmPersonalInfoVo> amPersonalInfoVoList = amPersonalInfoService.queryAmPersonalInfoVoList(operateUser.getOrgId());

        if (null != labTaskInfoVo.getEntity().getTaskEndDatetime()) {//解决时间无时分秒造成的00:00:00查询
            labTaskInfoVo.getEntity().setTaskEndDatetime(addDays(labTaskInfoVo.getEntity().getTaskEndDatetime(), 1));//增加一天
        }

        /** 获取任务列表 */
        List<LabTaskInfoVo> LabTaskInfoVolist = labTaskInfoService.selectAll(labTaskInfoVo, pageInfo);
        int caseInfoCount = labTaskInfoService.selectVOCount(labTaskInfoVo);
        if (null != labTaskInfoVo.getEntity().getTaskEndDatetime()) {//解决时间无时分秒造成的00:00:00查询
            labTaskInfoVo.getEntity().setTaskEndDatetime(addDays(labTaskInfoVo.getEntity().getTaskEndDatetime(), -1));//减一天
        }
        if (ListUtils.isNotNullAndEmptyList(LabTaskInfoVolist)) {
            for (LabTaskInfoVo ltifVo : LabTaskInfoVolist) {
                String taskId = ltifVo.getEntity().getTaskId();
                String taskStage = ltifVo.getEntity().getTaskStage();
                if (Constants.EXTRACT_PHASE.equals(taskStage)) {
                    //提取任务检材数
                    List<LabExtractInfo> labExtractInfoList = labExtractInfoService.selectByTaskId(taskId);
                    if (ListUtils.isNotNullAndEmptyList(labExtractInfoList)) {
                        LabExtractInfo labExtractInfo = labExtractInfoList.get(0);
                        if (labExtractInfo != null) {
                            List<LabExtractSample> labExtractSampleList = labExtractSampleService.selectByExtractId(labExtractInfo.getExtractId());
                            if (ListUtils.isNotNullAndEmptyList(labExtractSampleList)) {
                                ltifVo.getEntity().setSampleCount(labExtractSampleList.size());
                            }
                        }
                    }
                } else if (Constants.PCR_PHASE.equals(taskStage)) {
                    //扩增阶段检材数
                    List<LabPcrInfo> labPcrInfoList = labPcrInfoService.selectByTaskId(taskId);
                    if (ListUtils.isNotNullAndEmptyList(labPcrInfoList)) {
                        LabPcrInfo labPcrInfo = labPcrInfoList.get(0);
                        if (labPcrInfo != null) {
                            List<LabPcrSample> labPcrSamples = labPcrSampleService.selectByPcrId(labPcrInfo.getPcrId());
                            int sampleCount = labPcrSamples.size();
                            /*if (sampleCount == 0){
                                sampleCount = PCRCount(labPcrInfo);
                            }*/
                            //在扩增拼板任务表中查询检材数
                            ltifVo.getEntity().setSampleCount(sampleCount);
                        }
                    }

                } else if (Constants.SY_PHASE.equals(taskStage)) {
                    //电泳阶段检材数
                    List<LabSyInfo> labSyInfoList = labSyInfoService.selectByTaskId(taskId);
                    if (ListUtils.isNotNullAndEmptyList(labSyInfoList)) {
                        LabSyInfo labSyInfo = labSyInfoList.get(0);
                        if (labSyInfo != null) {
                            List<LabSySample> labSySamples = labSySampleService.selectBySyId(labSyInfo.getSyId());
                            int sampleCount = labSySamples.size();
                            /*if (sampleCount == 0){·
                                sampleCount = SYcount(labSyInfo);
                            }*/
                            ltifVo.getEntity().setSampleCount(sampleCount);
                        }
                    } else {
                        //手动提取到扩增拼板，到电泳阶段是其中一个任务是没有电泳任务的
                        List<LabPcrInfo> labPcrInfoList = labPcrInfoService.selectByTaskId(taskId);
                        if (ListUtils.isNotNullAndEmptyList(labPcrInfoList)) {
                            LabPcrInfo labPcrInfo = labPcrInfoList.get(0);
                            /*if (labPcrInfo != null){
                                //从拼板任务表查询有检材数据的板任务
                                LabPuzzleMiddle labPuzzleMiddle = labPuzzleMiddleService.selectByPcrId(labPcrInfo.getPcrId());
                                if (labPuzzleMiddle != null){
                                    List<LabSyInfo> labSyInfos = labSyInfoService.selectByTaskId(labPuzzleMiddle.getTaskId());
                                    if (ListUtils.isNotNullAndEmptyList(labSyInfos)){
                                        List<LabSySample> labSySamples = labSySampleService.selectBySyId(labSyInfos.get(0).getSyId());
                                        int sampleCount = labSySamples.size();
                                        ltifVo.getEntity().setSampleCount(sampleCount);
                                    }

                                }
                            }*/
                        }
                    }
                }
            }
        }

        LabTaskInfo labTaskInfo = new LabTaskInfo();
        labTaskInfo.setTaskStatus("0");

        view.addObject("labTaskInfo", labTaskInfo);
        view.addObject("labTaskInfoVo", labTaskInfoVo);
        view.addObject("operateUser", operateUser);
        view.addObject("amPersonalInfoVoList", amPersonalInfoVoList);
        view.addObject("labTaskInfoList", LabTaskInfoVolist);
        view.addObject("pageInfo", pageInfo.updatePageInfo(caseInfoCount));
        view.addObject("taskStartDatetime", taskStartDatetim);
        view.addObject("taskEndDatetime", taskEndDatetim);

        return view;
    }

    //从扩增阶段查询建材数
    /*public int PCRCount(LabPcrInfo labPcrInfo){
        int sampleCount = 0;
        LabPuzzleMiddle labPuzzleMiddle = labPuzzleMiddleService.selectByPcrId(labPcrInfo.getPcrId());
        if (labPuzzleMiddle != null){
            List<LabPcrInfo> labPcrInfoList = labPcrInfoService.selectByTaskId(labPuzzleMiddle.getTaskId());
            LabPcrInfo pcrInfo = labPcrInfoList.get(0);
            if (pcrInfo != null){
                List<LabPcrSample> labPcrSamples = labPcrSampleService.selectByPcrId(pcrInfo.getPcrId());
                sampleCount = labPcrSamples.size();
            }
        }
        return sampleCount;
    }*/

    //电泳夹断查询检材数
    /*public int SYcount(LabSyInfo labSyInfo){
        int sampleCount = 0;
        LabPuzzleMiddle labPuzzleMiddle = labPuzzleMiddleService.selectBySyId(labSyInfo.getSyId());
        if (labPuzzleMiddle != null){
            List<LabSyInfo> labSyInfos = labSyInfoService.selectByTaskId(labPuzzleMiddle.getTaskId());
            LabSyInfo syInfo = labSyInfos.get(0);
            if (syInfo != null){
                List<LabSySample> labSySamples = labSySampleService.selectBySyId(syInfo.getSyId());
                sampleCount = labSySamples.size();
            }
        }
        return sampleCount;
    }*/

    //跳转自动检材页面
    @RequestMapping("/extractTest")
    public ModelAndView extractTest(PageInfo pageInfo) {
        ModelAndView view = new ModelAndView();

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if (operateUser == null) {
            view.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return view;
        }

        view.setViewName("testProcess/extractTest");

        return view;
    }

    @RequestMapping(value = "/getExtractSampleList", method = RequestMethod.POST)
    @ResponseBody
    public List<LimsSampleInfoDna> getExtractSampleList(HttpServletRequest request, HttpServletResponse response, String caseNo, String sampleNo) throws ParseException {
        List<LimsSampleInfoDna> list = new ArrayList<>();
        if (StringUtils.isNotBlank(caseNo)) {
            List<LimsSampleInfoDna> limsSampleInfoDnaList = limsSampleInfoDnaService.selectByCaseNo(caseNo.trim());
            list.addAll(limsSampleInfoDnaList);
        }
        if (StringUtils.isNotBlank(sampleNo)) {
            List<LimsSampleInfoDna> limsSampleInfoDnaList = limsSampleInfoDnaService.selectBySampleNo(sampleNo.trim());
            list.addAll(limsSampleInfoDnaList);
        }

        return list;
    }

    @RequestMapping(value = "/uploadCsvFile", method = RequestMethod.POST)
    public ModelAndView uploadCsvFile(MultipartHttpServletRequest request, HttpServletResponse response,
                                      @RequestParam(value = "csvFile", required = false) MultipartFile csvFile) throws IOException {
        ModelAndView modelAndView = new ModelAndView();

        List<LimsSampleInfoDna> limsSampleInfoDnaList = null;

        try {
            limsSampleInfoDnaList = FileParserUtils.getDataFromFile(csvFile.getInputStream(), csvFile.getOriginalFilename());
        } catch (Exception ex) {
            logger.debug("导入失败" + ex.getMessage());
        }

        if (ListUtils.isNullOrEmptyList(limsSampleInfoDnaList)) {
            modelAndView.addObject("errMsg", "csv文件中未检索到数据！");
            return modelAndView;
        }

        List<LimsSampleInfoDna> sampleInfoDnaList = null;
        Set<String> sampleType = new LinkedHashSet<>();
        List<LimsSampleInfoDna> limsSampleInfoDnas = new ArrayList<>();
        for (LimsSampleInfoDna lsid : limsSampleInfoDnaList) {
            if (StringUtils.isNotBlank(lsid.getSampleNo())) {
                sampleInfoDnaList = limsSampleInfoDnaService.selectBySampleNo(lsid.getSampleNo().trim());
                if (ListUtils.isNotNullAndEmptyList(sampleInfoDnaList)) {
                    for (LimsSampleInfoDna lsi : sampleInfoDnaList) {
                        lsi.setPosition(lsid.getPosition());
                        if (!sampleType.contains(lsi.getSampleType())) {
                            sampleType.add(lsi.getSampleTypeName());
                        }
                    }

                    limsSampleInfoDnas.addAll(sampleInfoDnaList);
                }
            }
        }

        //遍历各种检材数量
        if (ListUtils.isNotNullAndEmptyList(limsSampleInfoDnas)) {
            Map<String, Integer> map = new HashMap<>();
            List<String> typeList = new ArrayList<>();
            for (String type : sampleType) {
                int count = 0;
                for (LimsSampleInfoDna ls : limsSampleInfoDnas) {
                    if (ls.getSampleTypeName().equals(type)) {
                        count++;
                    }
                }
                map.put(type, count);
                typeList.add(type);
            }
            modelAndView.addObject("map", map);
            modelAndView.addObject("typeList", typeList);
        }

        String boardNo = "";
        if (StringUtils.isNotBlank(csvFile.getOriginalFilename())) {
            boardNo = csvFile.getOriginalFilename().substring(0, csvFile.getOriginalFilename().lastIndexOf("."));
        }

        modelAndView.addObject("boardNo", boardNo);
        modelAndView.addObject("sampleCount", limsSampleInfoDnas.size());
        modelAndView.addObject("sampleInfoDnaList", limsSampleInfoDnas);
        modelAndView.setViewName("testProcess/extractTest");
        return modelAndView;
    }

    /**
     * 进入实验页面
     *
     * @param request
     * @param session
     * @param boardNo
     * @return
     */
    @RequestMapping("/extractionExperiment")
    public ModelAndView extractionExperiment(HttpServletRequest request, HttpSession session, String boardNo) {
        ModelAndView view = InitializationData.initDictList();


        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if (operateUser == null) {
            view.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return view;
        }
        String userOrgId = operateUser.getOrgId();
        //将当前用户org_id设置进query
        if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
            userOrgId = "110230000000";
        }

        List<LabExtractSample> labExtractSampleList = new ArrayList<>();

        if (StringUtils.isNotBlank(boardNo)) {
            try {
                boardNo = URLDecoder.decode(boardNo, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            List<LimsSampleInfoDna> limsSampleInfoDnaList = (List<LimsSampleInfoDna>) session.getAttribute("limsSampleInfoDnaList");

            for (LimsSampleInfoDna lsid : limsSampleInfoDnaList) {
                LabExtractSample labExtractSample = new LabExtractSample();

                labExtractSample.setSampleId(lsid.getSampleId());
                labExtractSample.setSampleNo(lsid.getSampleNo());
                labExtractSample.setSampleName(lsid.getSampleName());
                labExtractSample.setSamplePostion(lsid.getPosition());
                labExtractSample.setSampleType(lsid.getSampleType());
                labExtractSample.setExtractMethod("D");
                UseInstruments useInstrumentsEntity = new UseInstruments();
                useInstrumentsEntity.setMethodName("D");
                useInstrumentsEntity.setOrgCode(operateUser.getOrgId());
                List<UseInstruments> useInstrumentsList = useInstrumentsService.findByExtractMentod(useInstrumentsEntity);
                if (useInstrumentsList.size() > 0) {
                    UseInstruments useInstruments = useInstrumentsList.get(0);
                    labExtractSample.setLeave(useInstruments.getLeave());
                    labExtractSample.setLeaveTwo(useInstruments.getLeavetwo());
                    labExtractSample.setBath(useInstruments.getBath());
                    labExtractSample.setBathTwo(useInstruments.getBathtwo());
                    labExtractSample.setDry(useInstruments.getDry());
                    labExtractSample.setDryTwo(useInstruments.getDrytwo());
                    labExtractSample.setShake(useInstruments.getShake());
                    labExtractSample.setEarthquake(useInstruments.getEarthquake());
                }

                labExtractSampleList.add(labExtractSample);
            }
            //位置排序
            Collections.sort(labExtractSampleList);
        }

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间


        AmPersonalInfo amPersonalInfo = amPersonalInfoService.queryAmPersonalInfo(operateUser.getPersonalId());

        //设备选择
//        List<EquipmentNameInfo> equipmentNameInfoList = equipmentNameInfoService.selectList(userOrgId, Constants.EXPERIMENTAL_STAGE_01);

        EquipmentTypeInfo equipmentTypeInfo = new EquipmentTypeInfo();
        equipmentTypeInfo.setExperimentalStage(Constants.EXPERIMENTAL_STAGE_01);
        equipmentTypeInfo.setOrgId(userOrgId);
        List<EquipmentTypeInfo> equipmentTypeInfoList = equipmentTypeInfoService.selectLabEquipmentTypeList(equipmentTypeInfo);
        List<EquipmentNameInfo> equipmentNameInfoList = new ArrayList<>();
        List<EquipmentNameInfo> equipmentNameInfoList2 = new ArrayList<>();
        if (equipmentTypeInfoList.size() > 0) {
            equipmentNameInfoList2 = equipmentNameInfoService.selectEquipmentTypeId(equipmentTypeInfoList.get(0).getId());
            if (equipmentNameInfoList2.size() > 0) {
                equipmentNameInfoList2 = equipmentNameInfoService.selectEquipmentTypeId(equipmentTypeInfoList.get(0).getId());
                if (equipmentNameInfoList2.size() > 0) {
                    for (int j = 0; j < equipmentNameInfoList2.size(); j++) {
                        if ("博坤工作站".equals(equipmentNameInfoList2.get(j).getEquipmentName())) {
                            equipmentNameInfoList.add(equipmentNameInfoList2.get(j));
                        }
                    }
                }
            }
        }

        //获取试剂信息
        ReagentInfo reagentInfo = new ReagentInfo();
        reagentInfo.setExperimentalStage(Constants.EXPERIMENTAL_STAGE_01);
        reagentInfo.setOrgId(userOrgId);
        List<ReagentInfo> reagentInfoList = new ArrayList<>();
        List<ReagentInfo> reagentInfoList2 = reagentInfoService.selectList(reagentInfo);
        if (ListUtils.isNotNullAndEmptyList(reagentInfoList2)) {
            for (int i = 0; i < reagentInfoList2.size(); i++) {
                ReagentOutgoInfo reagentOutgoInfo = new ReagentOutgoInfo();
                reagentOutgoInfo.setReagentId(reagentInfoList2.get(i).getId());
                reagentOutgoInfo.setOrgId(reagentInfoList2.get(i).getOrgId());
                List<ReagentOutgoInfo> reagentOutgoInfoList = reagentOutgoInfoService.selectreagentOutgoList(reagentOutgoInfo);
                if (reagentOutgoInfoList.size() > 0) {
                    reagentInfoList2.get(i).setBatchNumber(reagentOutgoInfoList.get(0).getBatchNumber());
                    reagentInfoList2.get(i).setValidityTime(reagentOutgoInfoList.get(0).getEffectiveDatetime());
                }
                if ("WD24".equals(reagentInfoList2.get(i).getReagentName()) || "WD48".equals(reagentInfoList2.get(i).getReagentName()) || "WD72".equals(reagentInfoList2.get(i).getReagentName()) || "WD96".equals(reagentInfoList2.get(i).getReagentName())) {
                    reagentInfoList.add(reagentInfoList2.get(i));
                }
            }
        }


        view.addObject("reagentInfoList", reagentInfoList);
        view.addObject("equipmentNameInfoList", equipmentNameInfoList);
        view.addObject("operateUser", amPersonalInfo.getFullName());
        view.addObject("extractionExperimentDate", date);
        view.addObject("operateType", Constants.OPERATE_TYPE_ADD);
        view.addObject("boardNo", boardNo);
        view.addObject("labExtractSampleList", labExtractSampleList);
        view.setViewName("testProcess/extractionExperiment");
        return view;
    }

    /**
     * 把检材列表放入session中
     *
     * @param request
     * @param session
     * @param limsSampleInfoDnaList
     * @return
     */
    @RequestMapping(value = "/saveExtractioListSession", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> saveExtractioListSession(HttpServletRequest request, HttpSession session, String inspectionType, String extractionMode,
                                                        @RequestBody List<LimsSampleInfoDna> limsSampleInfoDnaList) {
        Map<String, Object> map = new HashMap<>();
        try {
            if (ListUtils.isNotNullAndEmptyList(limsSampleInfoDnaList)) {
                session.setAttribute("limsSampleInfoDnaList", limsSampleInfoDnaList);
                session.removeAttribute("inspectionType");
                session.removeAttribute("extractionMode");
                session.setAttribute("inspectionType", inspectionType);
                session.setAttribute("extractionMode", extractionMode);
                map.put("success", true);
            } else {
                map.put("success", false);
            }
        } catch (Exception e) {
            logger.error("saveExtractioListSession error!", e);
            map.put("success", false);
        }

        return map;
    }

    /**
     * 保存提取实验记录
     *
     * @param request
     * @param boardNo
     * @param operateType
     * @param labExtractSampleList
     * @return
     */
    @RequestMapping(value = "/saveExtractionExperiment", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> saveExtractionExperiment(HttpServletRequest request, String boardNo, String operateType,
                                                        String extractDatetimes, String extractPerson1, HttpSession session,
                                                        String equipment, String kit, String extractionDate,
                                                        @RequestBody List<LabExtractSample> labExtractSampleList) {
        Map<String, Object> map = new HashMap<>();
        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();

        //获取当前用户的id
        String userOrgId = operateUser.getOrgId();
        if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
            userOrgId = "110230000000";
        }
        //获取单位信息
        OrgInfo orgInfo = orgInfoService.selectByPrimaryKey(userOrgId);

        try {
            if (ListUtils.isNotNullAndEmptyList(labExtractSampleList)) {
                if (StringUtils.isNotBlank(operateType) && operateType.equals(Constants.OPERATE_TYPE_ADD)) {
                    //获取任务编号
                    String taskNo = null;
                    String extractNo = null;
                    if (StringUtils.isNotBlank(userOrgId)) {
                        taskNo = seqNoGenerateService.getNextTastNoVal("-", userOrgId);
                        extractNo = seqNoGenerateService.getNextExtractNoVal("-", userOrgId);
                    } else {
                        taskNo = seqNoGenerateService.getNextTastNoVal("-", null);
                        extractNo = seqNoGenerateService.getNextExtractNoVal("-", userOrgId);
                    }

                    /** 插入任务记录 */
                    LabTaskInfo labTaskInfo = new LabTaskInfo();
                    labTaskInfo.setTaskId(UUID.randomUUID().toString());
                    labTaskInfo.setTaskNo(taskNo);
                    labTaskInfo.setBoardNo(boardNo);
                    labTaskInfo.setTaskStatus(null);

                    labTaskInfo.setInspectionType((String) session.getAttribute("inspectionType"));
                    labTaskInfo.setExtractionMode((String) session.getAttribute("extractionMode"));

                    labTaskInfo.setTaskStage(Constants.EXTRACT_PHASE);
                    if (operateUser != null) {
                        labTaskInfo.setTaskPerson(operateUser.getPersonalId());
                        labTaskInfo.setCreatePerson(operateUser.getPersonalId());
                    }
                    labTaskInfo.setCreateDatetime(new Date());
                    labTaskInfo.setTaskStartDatetime(new Date());
                    labTaskInfo.setTaskEndDatetime(new Date());
                    labTaskInfo.setDeleteFlag(Constants.DELETE_FLAG_0);

                    if (orgInfo != null) {
                        labTaskInfo.setDelegateOrgCode(userOrgId);
                        labTaskInfo.setDelegateOrgName(orgInfo.getOrgName());
                    }

                    try {
                        labTaskInfoService.insert(labTaskInfo);
                    } catch (Exception e) {
                        logger.info("---插入任务记录id为[" + labTaskInfo.getTaskId() + "]的信息失败！---");
                        logger.error("插入任务记录失败！", e);
                        throw e;
                    }

                    /** 插入提取任务记录 */
                    LabExtractInfo labExtractInfo = new LabExtractInfo();
                    labExtractInfo.setExtractId(UUID.randomUUID().toString());
                    labExtractInfo.setTaskId(labTaskInfo.getTaskId());
                    labExtractInfo.setExtractNo(extractNo);
                    labExtractInfo.setBoardNo(boardNo);

                    labExtractInfo.setExtractPerson1(extractPerson1);
                    /*if (extractionDate != null && extractionDate != "") {
                        labExtractInfo.setExtractDatetime(DateUtils.stringToDate(extractionDate, "yyyy-MM-dd"));
                    } else {
                        labExtractInfo.setExtractDatetime(new Date());
                    }*/
                    if (extractDatetimes != null && extractDatetimes != "") {
                        labExtractInfo.setCreateDatetime(DateUtils.stringToDate(extractDatetimes, "yyyy-MM-dd"));
                        labExtractInfo.setExtractDatetime(DateUtils.stringToDate(extractDatetimes, "yyyy-MM-dd"));
                    } else {
                        labExtractInfo.setCreateDatetime(new Date());
                        labExtractInfo.setExtractDatetime(new Date());
                    }
                    labExtractInfo.setEquipment(equipment);
                    labExtractInfo.setKit(kit);

                    labExtractInfo.setSampleCount((short) labExtractSampleList.size());
                    if (operateUser != null) {
                        labExtractInfo.setCreatePerson(operateUser.getLoginName());
                        labExtractInfo.setOrgId(userOrgId);
                    }
                    labExtractInfo.setDeleteFlag(Constants.DELETE_FLAG_0);

                    try {
                        labExtractInfoService.insert(labExtractInfo);
                    } catch (Exception e) {
                        logger.info("---插入提取任务记录id为[" + labExtractInfo.getExtractId() + "]的信息失败！---");
                        logger.error("插入提取任务记录失败！", e);
                        throw e;
                    }

                    /** 插入提取检材记录 */
                    for (LabExtractSample les : labExtractSampleList) {
                        les.setId(UUID.randomUUID().toString());
                        les.setExtractId(labExtractInfo.getExtractId());
                        les.setCreateDatetime(new Date());
                        if (operateUser != null) {
                            les.setCreatePerson(operateUser.getLoginName());
                        }
                        try {
                            labExtractSampleService.insert(les);
                        } catch (Exception e) {
                            logger.info("---插入提取检材记录id为[" + les.getId() + "]的信息失败！---");
                            logger.error("插入提取检材记录失败！", e);
                            throw e;
                        }
                    }

                    map.put("extractId", labExtractInfo.getExtractId());
                } else {
                    String extractId = null;
                    /** 更新提取检材记录 */
                    for (LabExtractSample les : labExtractSampleList) {

                        LabExtractSample labExtractSample = labExtractSampleService.selectByPrimaryKey(les.getId());

                        //新值赋到旧值
                        labExtractSample.setTransferMethod(les.getTransferMethod());
                        labExtractSample.setExtractPart(les.getExtractPart());
                        labExtractSample.setExtractMethod(les.getExtractMethod());

                        labExtractSample.setLeave(les.getLeave());
                        labExtractSample.setBath(les.getBath());
                        labExtractSample.setDry(les.getDry());
                        labExtractSample.setShake(les.getShake());
                        labExtractSample.setEarthquake(les.getEarthquake());

                        labExtractSample.setUpdateDatetime(new Date());
                        if (operateUser != null) {
                            labExtractSample.setUpdatePerosn(operateUser.getLoginName());
                        }
                        extractId = labExtractSample.getExtractId();
                        try {
                            labExtractSampleService.updateByPrimaryKey(labExtractSample);
                        } catch (Exception e) {
                            logger.info("---更新提取检材记录id为[" + labExtractSample.getId() + "]的信息失败！---");
                            logger.error("更新提取检材记录失败！", e);
                            throw e;
                        }
                    }
                    LabExtractInfo labExtractInfo = labExtractInfoService.selectByPrimaryKey(extractId);
                    labExtractInfo.setKit(kit);
                    labExtractInfo.setEquipment(equipment);
                    labExtractInfo.setExtractPerson1(extractPerson1);
                    labExtractInfo.setBoardNo(boardNo);
                    labExtractInfo.setUpdateDatetime(new Date());
                    labExtractInfo.setCreateDatetime(DateUtils.stringToDate(extractDatetimes, "yyyy-MM-dd"));
                    labExtractInfo.setExtractDatetime(DateUtils.stringToDate(extractDatetimes, "yyyy-MM-dd"));
                    labExtractInfoService.updateByPrimaryKey(labExtractInfo);
                    map.put("extractId", extractId);
                }
                map.put("success", true);
            } else {
                map.put("success", false);
            }
        } catch (Exception e) {
            logger.error("saveExtractionExperiment error!", e);
            map.put("success", false);
        }

        return map;
    }

    /**
     * 根据id删除提取检材记录信息
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/deleteLabExtractSample")
    @ResponseBody
    public Map<String, Object> deleteLabExtractSample(HttpServletRequest request, String id) {
        Map<String, Object> result = new HashMap<String, Object>();

        LabExtractSample labExtractSample = labExtractSampleService.selectByPrimaryKey(id);
        if (labExtractSample != null) {
            try {
                result.put("extractId", labExtractSample.getExtractId());
                labExtractSampleService.deleteByPrimaryKey(id);
            } catch (Exception e) {
                logger.info("---删除提取检材记录id为[" + id + "]的信息失败！---");
                logger.error("删除提取检材记录失败！", e);
                result.put("success", false);
                throw e;
            }

            LabExtractInfo labExtractInfo = labExtractInfoService.selectByPrimaryKey(labExtractSample.getExtractId());
            try {
                LabExtractInfo lei = new LabExtractInfo();
                LocalBeanUtils.copyPropertiesIgnoreNull(labExtractInfo, lei);
                List<LabExtractSample> labExtractSampleList = labExtractSampleService.selectByExtractId(labExtractSample.getExtractId());

                lei.setSampleCount((short) labExtractSampleList.size());
                labExtractInfoService.updateByPrimaryKey(lei);
            } catch (Exception e) {
                logger.info("---更新提取检材数量id为[" + id + "]的信息失败！---");
                logger.error("更新提取检材数量失败！", e);
                result.put("success", false);
                throw e;
            }

            result.put("success", true);
        }

        return result;
    }

    /**
     * 刷新提取实验页面
     *
     * @param request
     * @param extractId
     * @return
     */
    @RequestMapping("/refreshExtractionExperiment")
    public ModelAndView refreshExtractionExperiment(HttpServletRequest request, String extractId) {
        ModelAndView modelAndView = InitializationData.initDictList();

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if (operateUser == null) {
            modelAndView.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return modelAndView;
        }
        //获取当前用户的id
        String userOrgId = operateUser.getOrgId();
        //将当前用户org_id设置进query
        if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
            userOrgId = "110230000000";
        }


        LabExtractInfo labExtractInfo = labExtractInfoService.selectByPrimaryKey(extractId);
        //板号
        String boardNo = null;
        if (labExtractInfo != null) {
            boardNo = labExtractInfo.getBoardNo();
        }

        List<LabExtractSample> labExtractSampleList = labExtractSampleService.selectByExtractId(extractId);
        if (labExtractSampleList.size() > 0) {
            for (int j = 0; j < labExtractSampleList.size(); j++) {
                if (StringUtils.isNotBlank(labExtractSampleList.get(j).getExtractMethod())) {
                    UseInstruments useInstrumentsEntity = new UseInstruments();
                    if (StringUtils.isNotBlank(labExtractSampleList.get(j).getExtractMethod())) {
                        useInstrumentsEntity.setMethodName(labExtractSampleList.get(j).getExtractMethod());
                    } else {
                        useInstrumentsEntity.setMethodName("D");
                    }
                    useInstrumentsEntity.setOrgCode(operateUser.getOrgId());
                    List<UseInstruments> useInstrumentsList = useInstrumentsService.findByExtractMentod(useInstrumentsEntity);
                    if (useInstrumentsList.size() > 0) {
                        UseInstruments useInstruments = useInstrumentsList.get(0);
                        labExtractSampleList.get(j).setLeave(useInstruments.getLeave());
                        labExtractSampleList.get(j).setLeaveTwo(useInstruments.getLeavetwo());
                        labExtractSampleList.get(j).setBath(useInstruments.getBath());
                        labExtractSampleList.get(j).setBathTwo(useInstruments.getBathtwo());
                        labExtractSampleList.get(j).setDry(useInstruments.getDry());
                        labExtractSampleList.get(j).setDryTwo(useInstruments.getDrytwo());
                        labExtractSampleList.get(j).setShake(useInstruments.getShake());
                        labExtractSampleList.get(j).setEarthquake(useInstruments.getEarthquake());
                    }
                }
            }
            //位置排序
            Collections.sort(labExtractSampleList);
        }


        modelAndView.addObject("extractId", extractId);
        modelAndView.addObject("boardNo", boardNo);

        modelAndView.addObject("operateUser", labExtractInfo.getExtractPerson1());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        modelAndView.addObject("extractionExperimentDate", simpleDateFormat.format(labExtractInfo.getCreateDatetime()));
//        modelAndView.addObject("extractionDates", simpleDateFormat.format(labExtractInfo.getExtractDatetime()));

        //设备选择
//        List<EquipmentNameInfo> equipmentNameInfoList = equipmentNameInfoService.selectList(userOrgId, Constants.EXPERIMENTAL_STAGE_01);

        EquipmentTypeInfo equipmentTypeInfo = new EquipmentTypeInfo();
        equipmentTypeInfo.setExperimentalStage(Constants.EXPERIMENTAL_STAGE_01);
        equipmentTypeInfo.setOrgId(operateUser.getOrgId());

//        List<EquipmentTypeInfo> equipmentNameInfoList = equipmentTypeInfoService.selectLabEquipmentTypeList(equipmentTypeInfo);
        List<EquipmentTypeInfo> equipmentTypeInfoList = equipmentTypeInfoService.selectLabEquipmentTypeList(equipmentTypeInfo);
        List<EquipmentNameInfo> equipmentNameInfoList = new ArrayList<>();
        List<EquipmentNameInfo> equipmentNameInfoList2 = new ArrayList<>();
        if (equipmentTypeInfoList.size() > 0) {
            equipmentNameInfoList2 = equipmentNameInfoService.selectEquipmentTypeId(equipmentTypeInfoList.get(0).getId());
            if (equipmentNameInfoList2.size() > 0) {
                equipmentNameInfoList2 = equipmentNameInfoService.selectEquipmentTypeId(equipmentTypeInfoList.get(0).getId());
                if (equipmentNameInfoList2.size() > 0) {
                    for (int j = 0; j < equipmentNameInfoList2.size(); j++) {
                        if ("博坤工作站".equals(equipmentNameInfoList2.get(j).getEquipmentName())) {
                            equipmentNameInfoList.add(equipmentNameInfoList2.get(j));
                        }
                    }
                }
            }

        }

        String[] equipment = null;
        if (labExtractInfo != null && labExtractInfo.getEquipment() != null) {
            equipment = labExtractInfo.getEquipment().split(",");
        }
        modelAndView.addObject("equipment", equipment);
        modelAndView.addObject("equipmentNameInfoList", equipmentNameInfoList);

        //试剂信息
        ReagentInfo reagentInfo = new ReagentInfo();
        reagentInfo.setExperimentalStage(Constants.EXPERIMENTAL_STAGE_01);
        reagentInfo.setOrgId(userOrgId);
        List<ReagentInfo> reagentInfoList = new ArrayList<>();
        List<ReagentInfo> reagentInfoList2 = reagentInfoService.selectList(reagentInfo);
        if (ListUtils.isNotNullAndEmptyList(reagentInfoList2)) {
            for (int i = 0; i < reagentInfoList2.size(); i++) {
                ReagentOutgoInfo reagentOutgoInfo = new ReagentOutgoInfo();
                reagentOutgoInfo.setReagentId(reagentInfoList2.get(i).getId());
                reagentOutgoInfo.setOrgId(reagentInfoList2.get(i).getOrgId());
                List<ReagentOutgoInfo> reagentOutgoInfoList = reagentOutgoInfoService.selectreagentOutgoList(reagentOutgoInfo);
                if (reagentOutgoInfoList.size() > 0) {
                    reagentInfoList2.get(i).setBatchNumber(reagentOutgoInfoList.get(0).getBatchNumber());
                    reagentInfoList2.get(i).setValidityTime(reagentOutgoInfoList.get(0).getEffectiveDatetime());
                }
                if ("WD24".equals(reagentInfoList2.get(i).getReagentName()) || "WD48".equals(reagentInfoList2.get(i).getReagentName()) || "WD72".equals(reagentInfoList2.get(i).getReagentName()) || "WD96".equals(reagentInfoList2.get(i).getReagentName())) {
                    reagentInfoList.add(reagentInfoList2.get(i));
                }
            }
        }


        modelAndView.addObject("reagentInfoList", reagentInfoList);

        String[] panelKit = null;
        if (labExtractInfo != null && labExtractInfo.getKit() != null) {
            panelKit = labExtractInfo.getKit().split(",");
        }
        modelAndView.addObject("panelKit", panelKit);
        //试剂盒回显
        List<ReagentInfo> selectList = new ArrayList<>();
        if (panelKit != null) {
            for (int i = 0; i < panelKit.length; i++) {
                for (int j = 0; j < reagentInfoList.size(); j++) {
                    if (panelKit[i].equals(reagentInfoList.get(j).getId())) {
                        selectList.add(reagentInfoList.get(j));
                    }
                }
            }
        }
        modelAndView.addObject("selectList", selectList);

        modelAndView.addObject("operateType", Constants.OPERATE_TYPE_EDIT);
        modelAndView.addObject("labExtractSampleList", labExtractSampleList);
        modelAndView.setViewName("testProcess/extractionExperiment");

        return modelAndView;
    }


    /**
     * 进入实验页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/enterExperiment")
    public ModelAndView enterExperiment(HttpServletRequest request, String taskId, String taskStage) {
        ModelAndView modelAndView = InitializationData.initDictList();

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if (operateUser == null) {
            modelAndView.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return modelAndView;
        }
        //获取当前用户的id
        String userOrgId = operateUser.getOrgId();
        //将当前用户org_id设置进query
        if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
            userOrgId = "110230000000";
        }

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间
        if (StringUtils.isNotBlank(taskStage)) {
            if (taskStage.equals(Constants.EXTRACT_PHASE)) {
                //进入提取实验页面
                List<LabExtractInfo> labExtractInfoList = labExtractInfoService.selectByTaskId(taskId);

                if (ListUtils.isNotNullAndEmptyList(labExtractInfoList)) {
                    LabExtractInfo labExtractInfo = labExtractInfoList.get(0);

                    //提取阶段时间
                    if (labExtractInfo != null && labExtractInfo.getExtractDatetime() != null && labExtractInfo.getCreateDatetime() != null) {
                        modelAndView.addObject("extractionExperimentDate", df.format(labExtractInfo.getCreateDatetime()));
                        modelAndView.addObject("extractionDates", df.format(labExtractInfo.getExtractDatetime()));
                    } else {
                        modelAndView.addObject("extractionExperimentDate", date);
                        modelAndView.addObject("extractionDates", date);
                    }

                    List<LabExtractSample> labExtractSampleList = labExtractSampleService.selectByExtractId(labExtractInfo.getExtractId());
                    if (labExtractSampleList.size() > 0) {
                        for (int i = 0; i < labExtractSampleList.size(); i++) {
                            UseInstruments useInstrumentsEntity = new UseInstruments();
                            useInstrumentsEntity.setMethodName("D");
                            useInstrumentsEntity.setOrgCode(operateUser.getOrgId());
                            List<UseInstruments> useInstrumentsList = useInstrumentsService.findByExtractMentod(useInstrumentsEntity);
                            if (useInstrumentsList.size() > 0) {
                                UseInstruments useInstruments = useInstrumentsList.get(0);
                                labExtractSampleList.get(i).setLeave(useInstruments.getLeave());
                                labExtractSampleList.get(i).setLeaveTwo(useInstruments.getLeavetwo());
                                labExtractSampleList.get(i).setBath(useInstruments.getBath());
                                labExtractSampleList.get(i).setBathTwo(useInstruments.getBathtwo());
                                labExtractSampleList.get(i).setDry(useInstruments.getDry());
                                labExtractSampleList.get(i).setDryTwo(useInstruments.getDrytwo());
                                labExtractSampleList.get(i).setShake(useInstruments.getShake());
                                labExtractSampleList.get(i).setEarthquake(useInstruments.getEarthquake());
                            }
                        }
                    }


//                    List<EquipmentNameInfo> equipmentNameInfoList = equipmentNameInfoService.selectList(userOrgId, Constants.EXPERIMENTAL_STAGE_01);

                    //设备选择
                    EquipmentTypeInfo equipmentTypeInfo = new EquipmentTypeInfo();
                    equipmentTypeInfo.setExperimentalStage(Constants.EXPERIMENTAL_STAGE_01);
                    equipmentTypeInfo.setOrgId(operateUser.getOrgId());
//                    List<EquipmentTypeInfo> equipmentNameInfoList = equipmentTypeInfoService.selectLabEquipmentTypeList(equipmentTypeInfo);

                    List<EquipmentTypeInfo> equipmentTypeInfoList = equipmentTypeInfoService.selectLabEquipmentTypeList(equipmentTypeInfo);
                    List<EquipmentNameInfo> equipmentNameInfoList = new ArrayList<>();
                    List<EquipmentNameInfo> equipmentNameInfoList2 = new ArrayList<>();
                    if (equipmentTypeInfoList.size() > 0) {
                        equipmentNameInfoList2 = equipmentNameInfoService.selectEquipmentTypeId(equipmentTypeInfoList.get(0).getId());
                        if (equipmentNameInfoList2.size() > 0) {
                            for (int j = 0; j < equipmentNameInfoList2.size(); j++) {
                                if ("博坤工作站".equals(equipmentNameInfoList2.get(j).getEquipmentName())) {
                                    equipmentNameInfoList.add(equipmentNameInfoList2.get(j));
                                }
                            }
                        }
                    }

                    String[] equipment = null;
                    if (labExtractInfo != null && labExtractInfo.getEquipment() != null) {
                        equipment = labExtractInfo.getEquipment().split(",");
                    }

                    //获取试剂信息
                    ReagentInfo reagentInfo = new ReagentInfo();
                    reagentInfo.setExperimentalStage(Constants.EXPERIMENTAL_STAGE_01);
                    reagentInfo.setOrgId(userOrgId);
                    List<ReagentInfo> reagentInfoList = new ArrayList<>();
                    List<ReagentInfo> reagentInfoList2 = reagentInfoService.selectList(reagentInfo);
                    if (ListUtils.isNotNullAndEmptyList(reagentInfoList2)) {
                        for (int i = 0; i < reagentInfoList2.size(); i++) {
                            ReagentOutgoInfo reagentOutgoInfo = new ReagentOutgoInfo();
                            reagentOutgoInfo.setReagentId(reagentInfoList2.get(i).getId());
                            reagentOutgoInfo.setOrgId(reagentInfoList2.get(i).getOrgId());
                            List<ReagentOutgoInfo> reagentOutgoInfoList = reagentOutgoInfoService.selectreagentOutgoList(reagentOutgoInfo);
                            if (reagentOutgoInfoList.size() > 0) {
                                reagentInfoList2.get(i).setBatchNumber(reagentOutgoInfoList.get(0).getBatchNumber());
                                reagentInfoList2.get(i).setValidityTime(reagentOutgoInfoList.get(0).getEffectiveDatetime());
                            }
                            if ("WD24".equals(reagentInfoList2.get(i).getReagentName()) || "WD48".equals(reagentInfoList2.get(i).getReagentName()) || "WD72".equals(reagentInfoList2.get(i).getReagentName()) || "WD96".equals(reagentInfoList2.get(i).getReagentName())) {
                                reagentInfoList.add(reagentInfoList2.get(i));
                            }
                        }
                    }
                    modelAndView.addObject("reagentInfoList", reagentInfoList);
                    String[] panelKit = null;
                    if (labExtractInfo != null && labExtractInfo.getKit() != null) {
                        panelKit = labExtractInfo.getKit().split(",");
                    }

                    //试剂批号回显
                    List<ReagentInfo> selectList = new ArrayList<>();
                    if (panelKit != null) {
                        for (int i = 0; i < panelKit.length; i++) {
                            for (int j = 0; j < reagentInfoList.size(); j++) {
                                if (panelKit[i].equals(reagentInfoList.get(j).getId())) {
                                    selectList.add(reagentInfoList.get(j));
                                }
                            }
                        }
                    }
                    //位置排序
                    Collections.sort(labExtractSampleList);
                    modelAndView.addObject("selectList", selectList);

                    modelAndView.addObject("panelKit", panelKit);
                    modelAndView.addObject("equipmentNameInfoList", equipmentNameInfoList);
                    modelAndView.addObject("extractId", labExtractInfo.getExtractId());
                    modelAndView.addObject("boardNo", labExtractInfo.getBoardNo());
                    modelAndView.addObject("operateUser", labExtractInfo.getExtractPerson1());
                    modelAndView.addObject("equipment", equipment);
                    modelAndView.addObject("operateType", Constants.OPERATE_TYPE_EDIT);
                    modelAndView.addObject("labExtractSampleList", labExtractSampleList);
                }
                modelAndView.setViewName("testProcess/extractionExperiment");
            } else if (taskStage.equals(Constants.PCR_PHASE)) {

                //进入扩增实验页面
                List<LabPcrInfo> labPcrInfoList = labPcrInfoService.selectByTaskId(taskId);
                if (ListUtils.isNotNullAndEmptyList(labPcrInfoList)) {
                    LabPcrInfo labPcrInfo = labPcrInfoList.get(0);
                    modelAndView.addObject("pcrId", labPcrInfo.getPcrId());
                    //通过pcrid查询扩增板任务
                    /*LabPuzzleMiddle labPuzzleMiddle = labPuzzleMiddleService.selectByPcrId(labPcrInfo.getPcrId());
                    if (labPuzzleMiddle != null){
                        List<LabPcrInfo> pcrInfos = labPcrInfoService.selectByTaskId(labPuzzleMiddle.getTaskId());
                        if (ListUtils.isNotNullAndEmptyList(pcrInfos)){
                           labPcrInfo = pcrInfos.get(0);
                        }
                    }*/
                    List<LabPcrSample> labPcrSampleList = labPcrSampleService.selectByPcrId(labPcrInfo.getPcrId());
                    List<LabPcrInfo> labPcrInfolist = labPcrInfoService.selectByTaskId(taskId);


                    //扩增阶段时间
                    if (labPcrInfo != null && labPcrInfo.getCreateDatetime() != null) {
                        modelAndView.addObject("date", df.format(labPcrInfo.getPcrEndDatetime()));
                        //提取阶段时间
                        List<LabExtractInfo> labExtractInfo = labExtractInfoService.selectByTaskId(taskId);
                        if (labExtractInfo != null && labExtractInfo.size() != 0 && labExtractInfo.get(0).getExtractDatetime() != null) {
                            modelAndView.addObject("extractionExperimentDate", df.format(labExtractInfo.get(0).getExtractDatetime()));
                        } else {
                            modelAndView.addObject("extractionExperimentDate", date);
                        }
                    } else {
                        modelAndView.addObject("date", date);
                        modelAndView.addObject("extractionExperimentDate", date);
                    }

                    //扩增显示的时间
                    LabPcrInfo labPcrInfo1 = labPcrInfoService.selectByTaskId(taskId).get(0);
                    if (labPcrInfo1 != null && labPcrInfo1.getPcrEndDatetime() != null) {
                        modelAndView.addObject("pcrexperimentDate", df.format(labPcrInfo1.getPcrEndDatetime()));

                    } else {
                        modelAndView.addObject("pcrexperimentDate", date);
                    }

                    //扩增板检材显示
                    List<Map<String, Object>> tempList = TestProcessUtils.pcrBoardSort(labPcrSampleList);

                    //位置排序
                    if (ListUtils.isNotNullAndEmptyList(labPcrSampleList)) {
                        Collections.sort(labPcrSampleList);
                    }

                    EquipmentNameInfo equipmentNameInfo = new EquipmentNameInfo();
                    equipmentNameInfo.setOrgId(userOrgId);
                    equipmentNameInfo.setPhase(Constants.EXPERIMENTAL_STAGE_02);//阶段

                    //扩增仪
//                    equipmentNameInfo.setEquipmentNo("KZY");
                    List<EquipmentNameInfo> panelListKZY = equipmentNameInfoService.selectEquipmentNameInfoListByTypeNo(equipmentNameInfo);

                    //试剂盒
                    ReagentInfo reagentInfo = new ReagentInfo();
                    reagentInfo.setExperimentalStage(Constants.EXPERIMENTAL_STAGE_02);
                    reagentInfo.setOrgId(userOrgId);
                    List<ReagentInfo> reagentInfoList = reagentInfoService.selectList(reagentInfo);
                    //获取当前时间
                    modelAndView.addObject("date", date);
                    modelAndView.addObject("boardNo", labPcrInfo.getBoardNo());
                    modelAndView.addObject("taskId", taskId);
                    modelAndView.addObject("operateType", Constants.OPERATE_TYPE_EDIT);
                    modelAndView.addObject("labPcrSampleList", labPcrSampleList);
                    modelAndView.addObject("tempList", tempList);
                    modelAndView.addObject("labPcrInfo", labPcrInfolist.get(0));
                    modelAndView.addObject("operateUser", labPcrInfolist.get(0).getPcrPerson1());
                    modelAndView.addObject("panelListKZY", panelListKZY);
                    modelAndView.addObject("reagentInfoList", reagentInfoList);
                } else {
                    //扩增板检材显示
                    List<Map<String, Object>> tempList = TestProcessUtils.pcrBoardSort(new ArrayList<>());
                    modelAndView.addObject("tempList", tempList);

                    EquipmentNameInfo equipmentNameInfo = new EquipmentNameInfo();
                    equipmentNameInfo.setPhase(Constants.EXPERIMENTAL_STAGE_02);//阶段
                    equipmentNameInfo.setOrgId(userOrgId);

                    //扩增仪
                    equipmentNameInfo.setEquipmentNo("KZY");
                    List<EquipmentNameInfo> panelListKZY = equipmentNameInfoService.selectEquipmentNameInfoListByTypeNo(equipmentNameInfo);
                    //试剂盒
                    ReagentInfo reagentInfo = new ReagentInfo();
                    reagentInfo.setExperimentalStage(Constants.EXPERIMENTAL_STAGE_02);
                    reagentInfo.setOrgId(userOrgId);
                    List<ReagentInfo> reagentInfoList = reagentInfoService.selectList(reagentInfo);
                    modelAndView.addObject("panelListKZY", panelListKZY);
                    modelAndView.addObject("reagentInfoList", reagentInfoList);
                }
                modelAndView.setViewName("testProcess/pcrExperiment");
            } else if (taskStage.equals(Constants.SY_PHASE)) {
                EquipmentNameInfo equipmentNameInfo = new EquipmentNameInfo();
                equipmentNameInfo.setPhase(Constants.EXPERIMENTAL_STAGE_03);//阶段
                equipmentNameInfo.setOrgId(userOrgId);
                //进入电泳实验页面
                List<LabSyInfo> labSyInfoList = labSyInfoService.selectByTaskId(taskId);

                if (labSyInfoList.size() == 0) {
                    //扩增要拼的板没有电泳任务，从扩增拼板任务表里查被拼板的任务
                    List<LabPcrInfo> labPcrInfoList = labPcrInfoService.selectByTaskId(taskId);
                    if (ListUtils.isNotNullAndEmptyList(labPcrInfoList)) {
                        LabPcrInfo labPcrInfo = labPcrInfoList.get(0);
                        /*LabPuzzleMiddle labPuzzleMiddle = labPuzzleMiddleService.selectByPcrId(labPcrInfo.getPcrId());
                        if (labPuzzleMiddle != null){
                            labSyInfoList = labSyInfoService.selectByTaskId(labPuzzleMiddle.getTaskId());
                            labSyInfoList = getLabSyInfoList(labSyInfoList, labPuzzleMiddle.getTaskId());
                        }else {
                            labSyInfoList = getLabSyInfoList(labSyInfoList, taskId);
                        }*/
                    }
                }

                if (ListUtils.isNotNullAndEmptyList(labSyInfoList)) {
                    LabSyInfo labSyInfo = labSyInfoList.get(0);
                    modelAndView.addObject("syId", labSyInfo.getSyId());
                    //因为电泳阶段拼板的板下没有检材数据，所以从拼板任务表获取被拼板信息
                    /*LabPuzzleMiddle labPuzzleMiddle = labPuzzleMiddleService.selectBySyId(labSyInfo.getSyId());
                    if (labPuzzleMiddle != null){
                        List<LabSyInfo> labSyInfos = labSyInfoService.selectByTaskId(labPuzzleMiddle.getTaskId());
                        if (ListUtils.isNotNullAndEmptyList(labSyInfos)){
                            labSyInfo = labSyInfos.get(0);
                        }else {

                        }
                    }*/

                    List<LabSySample> labSySampleList = labSySampleService.selectBySyId(labSyInfo.getSyId());

                    //扩增阶段时间
                    List<LabPcrInfo> labPcrInfoL = labPcrInfoService.selectByTaskId(taskId);
                    LabPcrInfo labPcrInfo = null;
                    if (labPcrInfoL.size() != 0) {
                        labPcrInfo = labPcrInfoL.get(0);
                    }

                    if (labPcrInfoL.size() != 0 && labPcrInfo != null && labPcrInfo.getPcrEndDatetime() != null) {
                        modelAndView.addObject("pcrexperimentDate", df.format(labPcrInfo.getPcrEndDatetime()));
                        //提取阶段时间
                        List<LabExtractInfo> labExtractInfo = labExtractInfoService.selectByTaskId(taskId);
                        if (labExtractInfo != null && labExtractInfo.size() != 0 && labExtractInfo.get(0).getExtractDatetime() != null) {
                            modelAndView.addObject("extractionExperimentDate", df.format(labExtractInfo.get(0).getExtractDatetime()));
                        } else {
                            modelAndView.addObject("extractionExperimentDate", date);
                        }
                    } else {
                        modelAndView.addObject("pcrexperimentDate", date);
                        modelAndView.addObject("extractionExperimentDate", date);
                    }

                    //电泳板检材显示
                    List<Map<String, Object>> tempList = TestProcessUtils.syBoardSort(labSySampleList);
                    modelAndView.addObject("tempList", tempList);
                    //位置排序
                    if (ListUtils.isNotNullAndEmptyList(labSySampleList)) {
                        Collections.sort(labSySampleList);
                    }

                    if (labSyInfo != null && labSyInfo.getSyEndDatetime() != null) {
                        modelAndView.addObject("syExperimentDate", df.format(labSyInfo.getSyEndDatetime()));
                    } else {
                        modelAndView.addObject("syExperimentDate", date);
                    }

                    //电泳仪
                    equipmentNameInfo.setEquipmentNo("DYY");
                    List<EquipmentNameInfo> panelListDYY = equipmentNameInfoService.selectEquipmentNameInfoListByTypeNo(equipmentNameInfo);

                    if (labSyInfo != null) {
                        if (StringUtils.isNotBlank(labSyInfo.getSyString())) {
                            Map<String, Object> mapSyString = (Map) JSON.parse(labSyInfo.getSyString());
                            modelAndView.addObject("mapSyString", mapSyString);
                        }
                    }

                    modelAndView.addObject("panelListDYY", panelListDYY);
                    modelAndView.addObject("boardNo", labSyInfo.getBoardNo());
                    modelAndView.addObject("taskId", taskId);
                    modelAndView.addObject("operateUser", labSyInfo.getSyPerson1());
                    modelAndView.addObject("operateType", Constants.OPERATE_TYPE_EDIT);
                    modelAndView.addObject("labSySampleList", labSySampleList);
                    modelAndView.addObject("labSyInfo", labSyInfoList.get(0));
                }
                modelAndView.setViewName("testProcess/syExperiment");
            } else if (taskStage.equals(Constants.ANALYSIS_PHASE)) {
                //进入分析实验页面

                modelAndView.setViewName("testProcess/analysisExperiment");
            }
        }
        return modelAndView;
    }

    //从拼板中查找电泳检材
    /*public List getLabSyInfoList(List<LabSyInfo> labSyInfoList,String taskid){
        if (labSyInfoList.size() == 0){
            List<LabPuzzleMiddle> labPuzzleMiddles = labPuzzleMiddleService.selectByTaskId(taskid);
            if (ListUtils.isNotNullAndEmptyList(labPuzzleMiddles)){
                for (LabPuzzleMiddle labPuzzle : labPuzzleMiddles){
                    LabPcrInfo labPcrInfo = labPcrInfoService.selectByPrimaryKey(labPuzzle.getPcrId());
                    if (labPcrInfo != null){
                        List<LabSyInfo> labSyInfos = labSyInfoService.selectByTaskId(labPcrInfo.getTaskId());
                        if (ListUtils.isNotNullAndEmptyList(labSyInfos)){
                            labSyInfoList = labSyInfos;
                        }
                    }
                }
            }
        }
        return labSyInfoList;
    }*/

    /**
     * 进入扩增实验页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/pcrExperiment")
    public ModelAndView pcrExperiment(HttpServletRequest request, String extractId) {
        ModelAndView modelAndView = InitializationData.initDictList();

        LabExtractInfo labExtractInfo = labExtractInfoService.selectByPrimaryKey(extractId);
        String boardNo = null;
        String taskId = null;
        List<LabPcrSample> labPcrSampleList = new ArrayList<>();
        List<LabPcrInfo> labPcrInfoList = null;
        if (labExtractInfo != null) {
            boardNo = labExtractInfo.getBoardNo();
            taskId = labExtractInfo.getTaskId();
            labPcrInfoList = labPcrInfoService.selectByTaskId(labExtractInfo.getTaskId());
            if (ListUtils.isNotNullAndEmptyList(labPcrInfoList)) {
                LabPcrInfo labPcrInfo = labPcrInfoList.get(0);
                if (labPcrInfo != null) {
                    List<LabPcrSample> pcrSampleList = labPcrSampleService.selectByPcrId(labPcrInfo.getPcrId());
                    labPcrSampleList.addAll(pcrSampleList);
                }
                modelAndView.addObject("labPcrInfo", labPcrInfo);
                modelAndView.addObject("pcrId", labPcrInfo.getPcrId());
            } else {
                //查询提取检材信息
                List<LabExtractSample> labExtractSampleList = labExtractSampleService.selectByExtractId(extractId);
                if (ListUtils.isNotNullAndEmptyList(labExtractSampleList)) {
                    for (LabExtractSample les : labExtractSampleList) {
                        LabPcrSample labPcrSample = new LabPcrSample();
                        labPcrSample.setSampleId(les.getSampleId());
                        labPcrSample.setSampleNo(les.getSampleNo());
                        labPcrSample.setSampleName(les.getSampleName());
                        labPcrSample.setSampleType(les.getSampleType());
                        labPcrSample.setSamplePostion(les.getSamplePostion());

                        labPcrSampleList.add(labPcrSample);
                    }
                }
            }

            //扩增板检材显示
            List<Map<String, Object>> tempList = TestProcessUtils.pcrBoardSort(labPcrSampleList);
            modelAndView.addObject("tempList", tempList);

            //位置排序
            if (ListUtils.isNotNullAndEmptyList(labPcrSampleList)) {
                Collections.sort(labPcrSampleList);
            }
        }

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if (operateUser == null) {
            modelAndView.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return modelAndView;
        }
        //获取当前用户的id
        String userOrgId = operateUser.getOrgId();
        //将当前用户org_id设置进query
        if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
            userOrgId = "110230000000";
        }
        AmPersonalInfo amPersonalInfo = amPersonalInfoService.queryAmPersonalInfo(operateUser.getPersonalId());
        modelAndView.addObject("operateUser", amPersonalInfo.getFullName());

        //获取当前时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间
        modelAndView.addObject("pcrexperimentDate", date);

        modelAndView.addObject("labPcrInfo.pcrEndDatetime", date);

        //提取时间
        if (labExtractInfo != null && labExtractInfo.getExtractDatetime() != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            modelAndView.addObject("extractionExperimentDate", simpleDateFormat.format(labExtractInfo.getExtractDatetime()));
        } else {
            modelAndView.addObject("extractionExperimentDate", date);
        }

        EquipmentNameInfo equipmentNameInfo = new EquipmentNameInfo();
        equipmentNameInfo.setOrgId(userOrgId);

        //扩增仪
        equipmentNameInfo.setEquipmentNo("KZY");
        List<EquipmentNameInfo> panelListKZY = equipmentNameInfoService.selectEquipmentNameInfoListByTypeNo(equipmentNameInfo);

        //试剂盒
        ReagentInfo reagentInfo = new ReagentInfo();
        reagentInfo.setExperimentalStage(Constants.EXPERIMENTAL_STAGE_02);
        reagentInfo.setOrgId(userOrgId);
        List<ReagentInfo> reagentInfoList = reagentInfoService.selectList(reagentInfo);

        modelAndView.addObject("labPcrSampleList", labPcrSampleList);
        modelAndView.addObject("boardNo", boardNo);
        modelAndView.addObject("taskId", taskId);
        if (labPcrInfoList != null && labPcrInfoList.size() != 0) {
            modelAndView.addObject("operateType", Constants.OPERATE_TYPE_EDIT);
        } else {
            modelAndView.addObject("operateType", Constants.OPERATE_TYPE_ADD);
        }
        modelAndView.addObject("panelListKZY", panelListKZY);
        modelAndView.addObject("reagentInfoList", reagentInfoList);
        modelAndView.setViewName("testProcess/pcrExperiment");

        return modelAndView;
    }


    /**
     * 保存扩增任务记录
     *
     * @param request
     * @param taskId
     * @param operateType
     * @param labPcrInfo
     * @return
     */
    @RequestMapping(value = "/savePcrInfo", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> savePcrInfo(HttpServletRequest request, String taskId, String operateType,
                                           String pcrEndDatetime, HttpSession session,
                                           @RequestBody LabPcrInfo labPcrInfo) throws ParseException {

        Map<String, Object> map = new HashMap<>();
        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        //获取当前用户的id
        String userOrgId = operateUser.getOrgId();
        //将当前用户org_id设置进query
        if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
            userOrgId = "110230000000";
        }
        LabTaskInfo labTaskInfo = labTaskInfoService.selectByPrimaryKey(taskId);

        if (labTaskInfo != null) {
            if (StringUtils.isNotBlank(operateType) && operateType.equals(Constants.OPERATE_TYPE_ADD)) {
                //添加试验阶段
                LabTaskInfo lti = new LabTaskInfo();
                lti.setInspectionType((String) session.getAttribute("inspectionType"));
                lti.setExtractionMode((String) session.getAttribute("extractionMode"));
                LocalBeanUtils.copyPropertiesIgnoreNull(labTaskInfo, lti);
                lti.setTaskStage(Constants.PCR_PHASE);
                lti.setDelegateOrgCode(userOrgId);
                lti.setTaskEndDatetime(new Date());
                if (labPcrInfo.getBoardNo() != null) {
                    lti.setBoardNo(labPcrInfo.getBoardNo());
                }
                try {
                    labTaskInfoService.updateByPrimaryKey(lti);
                } catch (Exception e) {
                    logger.info("---更新任务记录id为[" + lti.getTaskId() + "]的试验阶段失败！---");
                    logger.error("更新任务记录试验阶段失败！", e);
                    map.put("success", false);
                    throw e;
                }

                /** 插入扩增任务记录 */
                labPcrInfo.setPcrId(UUID.randomUUID().toString());
                labPcrInfo.setTaskId(labTaskInfo.getTaskId());
                labPcrInfo.setPcrNo(seqNoGenerateService.getNextPcrNoVal("-", userOrgId));

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                labPcrInfo.setPcrEndDatetime(simpleDateFormat.parse(pcrEndDatetime));

                if (labTaskInfo.getBoardNo() != null && labTaskInfo.getBoardNo().trim() != "") {
                    labPcrInfo.setBoardNo(labTaskInfo.getBoardNo());
                }
                if (operateUser != null) {
                    labPcrInfo.setCreatePerson(operateUser.getLoginName());
                    labPcrInfo.setOrgId(userOrgId);
                }
                labPcrInfo.setCreateDatetime(new Date());

                try {
                    labPcrInfoService.insert(labPcrInfo);
                } catch (Exception e) {
                    logger.info("---插入扩增任务记录id为[" + labPcrInfo.getPcrId() + "]的信息失败！---");
                    logger.error("插入扩增任务记录失败！", e);
                    map.put("success", false);
                    throw e;
                }
            } else {
                LabPcrInfo pcrInfo = labPcrInfoService.selectByPrimaryKey(labPcrInfo.getPcrId());
                pcrInfo.setPcrPerson1(labPcrInfo.getPcrPerson1());
                pcrInfo.setPcrReagent(labPcrInfo.getPcrReagent());
                pcrInfo.setPcrInstrument(labPcrInfo.getPcrInstrument());
                pcrInfo.setPcrSystem(labPcrInfo.getPcrSystem());
                pcrInfo.setBatchb(labPcrInfo.getBatchb());
                pcrInfo.setValidity(labPcrInfo.getValidity());
                pcrInfo.setPcrProgram(labPcrInfo.getPcrProgram());
                pcrInfo.setSampleCount(labPcrInfo.getSampleCount());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                pcrInfo.setPcrEndDatetime(simpleDateFormat.parse(pcrEndDatetime));
                try {
                    if (operateUser != null) {
                        pcrInfo.setUpdatePerson(operateUser.getLoginName());
                        pcrInfo.setOrgId(userOrgId);
                    }
                    pcrInfo.setUpdateDatetime(new Date());
                    labPcrInfoService.updateByPrimaryKey(pcrInfo);
                } catch (Exception e) {
                    logger.info("---更新扩增任务记录id为[" + labPcrInfo.getPcrId() + "]的信息失败！---");
                    logger.error("更新扩增任务记录失败！", e);
                    map.put("success", false);
                    throw e;
                }
                //更改实验任务数量
                labTaskInfo.setSampleCount(labPcrInfo.getSampleCount());
                try {
                    labTaskInfoService.updateByPrimaryKey(labTaskInfo);
                } catch (Exception e) {
                    logger.info("---更新任务记录id为[" + labTaskInfo.getTaskId() + "]的试验阶段失败！---");
                    logger.error("更新任务记录试验阶段失败！", e);
                    map.put("success", false);
                    throw e;
                }

            }

            map.put("pcrId", labPcrInfo.getPcrId());
            map.put("success", true);
        } else {
            map.put("success", false);
        }

        return map;
    }

    /**
     * 保存扩增实验记录
     *
     * @param request
     * @param taskId
     * @param operateType
     * @param pcrId
     * @param labPcrSampleList
     * @return
     */
    @RequestMapping(value = "/savePcrExperiment", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> savePcrExperiment(HttpServletRequest request, String taskId, String operateType, String pcrId,
                                                 @RequestBody List<LabPcrSample> labPcrSampleList) {
        Map<String, Object> map = new HashMap<>();
        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        //获取当前用户的id
        String userOrgId = operateUser.getOrgId();
        //将当前用户org_id设置进query
        if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
            userOrgId = "110230000000";
        }
        //获取被拼板的id将拼板的数据添加进去
        List<LabPcrInfo> labPcrInfoList = labPcrInfoService.selectByTaskId(taskId);
        LabPcrInfo labPcrInfo = null;
        if (ListUtils.isNotNullAndEmptyList(labPcrInfoList)) {
            List<LabPcrSample> labPcrSamples = labPcrSampleService.selectByPcrId(labPcrInfoList.get(0).getPcrId());
            if (labPcrSamples.size() > 0) {
                labPcrInfo = labPcrInfoList.get(0);
            }/*else {
                LabPuzzleMiddle labPuzzleMiddle = labPuzzleMiddleService.selectByPcrId(labPcrInfoList.get(0).getPcrId());
                if (labPuzzleMiddle != null){
                    labPcrInfoList = labPcrInfoService.selectByTaskId(labPuzzleMiddle.getTaskId());
                    labPcrInfo = labPcrInfoList.get(0);
                }
            }*/
        }
        try {
            if (ListUtils.isNotNullAndEmptyList(labPcrSampleList)) {
                if (StringUtils.isNotBlank(operateType) && operateType.equals(Constants.OPERATE_TYPE_ADD)) {
                    /** 插入扩增检材记录 */
                    for (LabPcrSample lps : labPcrSampleList) {
                        lps.setId(UUID.randomUUID().toString());
                        lps.setCreateDatetime(new Date());
                        List<LimsSampleInfoDna> limsSampleInfoDnaList = limsSampleInfoDnaService.selectBySampleId(lps.getSampleId());
                        if(limsSampleInfoDnaList.size() >0){
                            if("03".equals(limsSampleInfoDnaList.get(0).getSampleType())){
                                LabPcrInfo pcrInfo = labPcrInfoService.selectByPrimaryKey(lps.getPcrId());
                                if(pcrInfo != null){
                                    if("PP21".equals(pcrInfo.getPcrReagent())){
                                        lps.setPrimer("2.0");
                                        lps.setBuffer("2.0");
                                        lps.setTemplate("1.0");
                                        lps.setH2o("5.0");
                                    }else{
                                        lps.setPrimer("2.0");
                                        lps.setBuffer("4.0");
                                        lps.setTemplate("4.0");
                                        lps.setH2o("X");
                                    }
                                }
                            }else{
                                lps.setPrimer("2.0");
                                lps.setBuffer("4.0");
                                lps.setTemplate("1.0");
                                lps.setH2o("3.0");
                            }
                        }
                        if (operateUser != null) {
                            lps.setCreatePerson(operateUser.getLoginName());
                        }
                        if (labPcrInfo != null) {
                            lps.setPcrId(labPcrInfo.getPcrId());
                        }
                        try {
                            labPcrSampleService.insert(lps);
                        } catch (Exception e) {
                            logger.info("---插入扩增检材记录id为[" + lps.getId() + "]的信息失败！---");
                            logger.error("插入扩增检材记录失败！", e);
                            map.put("success", false);
                            throw e;
                        }
                    }
                    /*puzzleInfo(taskId,pcrId);*/
                } else {
                    /** 更新扩增检材记录 */
                    for (LabPcrSample lps : labPcrSampleList) {
                        LabPcrSample labPcrSample = labPcrSampleService.selectByPrimaryKey(lps.getId());
                        List<LimsSampleInfoDna> limsSampleInfoDnaList = limsSampleInfoDnaService.selectBySampleId(lps.getSampleId());
                        if(limsSampleInfoDnaList.size() >0){
                            if("03".equals(limsSampleInfoDnaList.get(0).getSampleType())){
                                LabPcrInfo pcrInfo = labPcrInfoService.selectByPrimaryKey(lps.getPcrId());
                                if(pcrInfo != null){
                                    if("PP21".equals(pcrInfo.getPcrReagent())){
                                        labPcrSample.setPrimer("2.0");
                                        labPcrSample.setBuffer("2.0");
                                        labPcrSample.setTemplate("1.0");
                                        labPcrSample.setH2o("5.0");
                                    }else{
                                        labPcrSample.setPrimer("2.0");
                                        labPcrSample.setBuffer("4.0");
                                        labPcrSample.setTemplate("4.0");
                                        labPcrSample.setH2o("X");
                                    }
                                }
                            }else{
                                labPcrSample.setPrimer("2.0");
                                labPcrSample.setBuffer("4.0");
                                labPcrSample.setTemplate("1.0");
                                labPcrSample.setH2o("3.0");
                            }

                        }
                        //新值赋到旧值
//                        labPcrSample.setPrimer(lps.getPrimer());
//                        labPcrSample.setBuffer(lps.getBuffer());
//                        labPcrSample.setTemplate(lps.getTemplate());
                        labPcrSample.setTaqe(lps.getTaqe());
                        labPcrSample.setDntp(lps.getDntp());
//                        labPcrSample.setH2o(lps.getH2o());
                        labPcrSample.setMgcl2(lps.getMgcl2());
                        labPcrSample.setUpdateDatetime(new Date());
                        if (operateUser != null) {
                            labPcrSample.setUpdatePerson(operateUser.getLoginName());
                        }

                        try {
                            labPcrSampleService.updateByPrimaryKey(labPcrSample);
                        } catch (Exception e) {
                            logger.info("---更新扩增检材记录id为[" + labPcrSample.getId() + "]的信息失败！---");
                            logger.error("更新扩增材记录失败！", e);
                            map.put("success", false);
                            throw e;
                        }
                    }
                }
                map.put("success", true);
            } else {
                map.put("success", false);
            }
        } catch (Exception e) {
            logger.error("savePcrExperiment error!", e);
            map.put("success", false);
        }

        return map;
    }

    //生成扩增拼板任务
    /*public void puzzleInfo(String taskId,String pcrId){
        if(taskId != null && taskId != "" && taskId != "undefined"){
            List<LabPcrInfo> labPcrInfoList = labPcrInfoService.selectByTaskId(taskId);
            List<LabPcrSample> labPcrSampleList = null;
            if (labPcrInfoList.size() != 0 && labPcrInfoList != null){
                for (LabPcrInfo labPcrInfo : labPcrInfoList){
                    labPcrSampleList = labPcrSampleService.selectByPcrId(labPcrInfo.getPcrId());
                }
            }
            if (pcrId != null && pcrId != "" && pcrId != "undefined"){
                List<LabPcrSample> pcrSampleList1 = labPcrSampleService.selectByPcrId(pcrId);
                if (pcrSampleList1 .size()==0){
                    if (labPcrSampleList.size() != 0 && labPcrSampleList != null){
                        LabPuzzleMiddle labPuzzleMiddle = new LabPuzzleMiddle();
                        labPuzzleMiddle.setId(UUID.randomUUID().toString());
                        labPuzzleMiddle.setTaskId(taskId);
                        labPuzzleMiddle.setPcrId(pcrId);
                        try {
                            labPuzzleMiddleService.insert(labPuzzleMiddle);
                        }catch (Exception e) {
                            logger.info("---添加扩增拼板任务失败！---");
                            logger.error("添加扩增拼板任务失败！", e);
                            throw e;
                        }
                    }
                }
            }
        }

    }*/

    /**
     * 刷新扩增实验页面
     *
     * @param request
     * @param pcrId
     * @return
     */
    @RequestMapping("/refreshPcrExperiment")
    public ModelAndView refreshPcrExperiment(HttpServletRequest request, String pcrId) {
        ModelAndView modelAndView = InitializationData.initDictList();

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();

        if (operateUser == null) {
            modelAndView.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return modelAndView;
        }
        //获取当前用户的id
        String userOrgId = operateUser.getOrgId();
        //将当前用户org_id设置进query
        if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
            userOrgId = "110230000000";
        }


        LabPcrInfo labPcrInfo = labPcrInfoService.selectByPrimaryKey(pcrId);
        String boardNo = null;
        String taskId = null;
        if (labPcrInfo != null) {
            boardNo = labPcrInfo.getBoardNo();
            taskId = labPcrInfo.getTaskId();
        }

        List<LabPcrSample> labPcrSampleList = labPcrSampleService.selectByPcrId(pcrId);
        /*if(ListUtils.isNullOrEmptyList(labPcrSampleList)){
            LabPuzzleMiddle labPuzzleMiddle = labPuzzleMiddleService.selectByPcrId(pcrId);
            if (labPuzzleMiddle != null){
                List<LabPcrInfo> labPcrInfoList = labPcrInfoService.selectByTaskId(labPuzzleMiddle.getTaskId());
                if(ListUtils.isNotNullAndEmptyList(labPcrInfoList)){
                    labPcrSampleList = labPcrSampleService.selectByPcrId(labPcrInfoList.get(0).getPcrId());
                }
            }
        }*/
        //刷新扩增板检材显示
        List<Map<String, Object>> tempList = TestProcessUtils.pcrBoardSort(labPcrSampleList);

        //位置排序
        if (ListUtils.isNotNullAndEmptyList(labPcrSampleList)) {
            Collections.sort(labPcrSampleList);
        }

//        modelAndView.addObject("operateUser", labPcrInfo.getPcrPerson1());

        //显示时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        modelAndView.addObject("pcrexperimentDate", simpleDateFormat.format(labPcrInfo.getPcrEndDatetime()));

        //提取时间
        List<LabExtractInfo> labExtractInfoL = labExtractInfoService.selectByTaskId(labPcrInfo.getTaskId());
        LabExtractInfo labExtractInfo = null;
        if (labExtractInfoL.size() != 0) {
            labExtractInfo = labExtractInfoL.get(0);
        }

        if (labExtractInfoL.size() != 0 && labExtractInfo != null && labExtractInfo.getExtractDatetime() != null) {
            modelAndView.addObject("extractionExperimentDate", simpleDateFormat.format(labExtractInfo.getExtractDatetime()));
        } else {
            //获取当前时间
            String date = simpleDateFormat.format(new Date());// new Date()为获取当前系统时间
            modelAndView.addObject("extractionExperimentDate", date);
        }

        EquipmentNameInfo equipmentNameInfo = new EquipmentNameInfo();
        equipmentNameInfo.setOrgId(userOrgId);

        //扩增仪
        equipmentNameInfo.setEquipmentNo("KZY");
        List<EquipmentNameInfo> panelListKZY = equipmentNameInfoService.selectEquipmentNameInfoListByTypeNo(equipmentNameInfo);
        //试剂盒
        ReagentInfo reagentInfo = new ReagentInfo();
        reagentInfo.setExperimentalStage(Constants.EXPERIMENTAL_STAGE_02);
        reagentInfo.setOrgId(userOrgId);
        List<ReagentInfo> reagentInfoList = reagentInfoService.selectList(reagentInfo);

        modelAndView.addObject("pcrId", pcrId);
        modelAndView.addObject("boardNo", boardNo);
        modelAndView.addObject("taskId", taskId);
        modelAndView.addObject("operateType", Constants.OPERATE_TYPE_EDIT);
        modelAndView.addObject("labPcrSampleList", labPcrSampleList);
        modelAndView.addObject("labPcrInfo", labPcrInfo);
        modelAndView.addObject("operateUser", labPcrInfo.getPcrPerson1());
        modelAndView.addObject("tempList", tempList);
        modelAndView.addObject("reagentInfoList", reagentInfoList);
        modelAndView.addObject("panelListKZY", panelListKZY);
        modelAndView.setViewName("testProcess/pcrExperiment");

        return modelAndView;
    }


    /**
     * 根据id删除扩增检材记录信息
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/deleteLabPcrSample")
    @ResponseBody
    public Map<String, Object> deleteLabPcrSample(HttpServletRequest request, String id) {
        Map<String, Object> result = new HashMap<String, Object>();

        LabPcrSample labPcrSample = labPcrSampleService.selectByPrimaryKey(id);
        if (labPcrSample != null) {
            try {
                result.put("pcrId", labPcrSample.getPcrId());
                labPcrSampleService.deleteByPrimaryKey(id);
            } catch (Exception e) {
                logger.info("---删除扩增检材记录id为[" + id + "]的信息失败！---");
                logger.error("删除扩增检材记录失败！", e);
                result.put("success", false);
                throw e;
            }

            LabPcrInfo labPcrInfo = labPcrInfoService.selectByPrimaryKey(labPcrSample.getPcrId());

            try {
                LabPcrInfo lpi = new LabPcrInfo();

                LocalBeanUtils.copyPropertiesIgnoreNull(labPcrInfo, lpi);
                List<LabPcrSample> labPcrSampleList = labPcrSampleService.selectByPcrId(labPcrSample.getPcrId());

                lpi.setSampleCount((short) labPcrSampleList.size());
                labPcrInfoService.updateByPrimaryKey(lpi);
            } catch (Exception e) {
                logger.info("---更新扩增检材数量id为[" + id + "]的信息失败！---");
                logger.error("更新扩增检材数量失败！", e);
                result.put("success", false);
                throw e;
            }

            result.put("success", true);
        }

        return result;
    }

    /**
     * 进入电泳实验页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/syExperiment")
    public ModelAndView syExperiment(HttpServletRequest request, String pcrId) {
        ModelAndView modelAndView = InitializationData.initDictList();

        LabPcrInfo labPcrInfo = labPcrInfoService.selectByPrimaryKey(pcrId);
        String boardNo = null;
        String taskId = null;
        List<LabSySample> labSySampleList = new ArrayList<>();
        List<LabSyInfo> labSyInfosList = null;
        if (labPcrInfo != null) {
            boardNo = labPcrInfo.getBoardNo();
            taskId = labPcrInfo.getTaskId();
            labSyInfosList = labSyInfoService.selectByTaskId(labPcrInfo.getTaskId());
            if (ListUtils.isNotNullAndEmptyList(labSyInfosList)) {
                LabSyInfo labSyInfo = labSyInfosList.get(0);
                if (labSyInfo != null) {
                    List<LabSySample> labSySamples = labSySampleService.selectBySyId(labSyInfo.getSyId());
                    labSySampleList.addAll(labSySamples);
                }
                modelAndView.addObject("labSyInfo", labSyInfo);
                modelAndView.addObject("syId", labSyInfo.getSyId());
            } else {
                //查询扩增检材信息
                List<LabPcrSample> labPcrSamples = labPcrSampleService.selectByPcrId(pcrId);
                /*if (ListUtils.isNullOrEmptyList(labPcrSamples)) {
                    LabPuzzleMiddle labPuzzleMiddle = labPuzzleMiddleService.selectByPcrId(pcrId);
                    if (labPuzzleMiddle != null){
                        List<LabPcrInfo> labPcrInfoList = labPcrInfoService.selectByTaskId(labPuzzleMiddle.getTaskId());
                        if (ListUtils.isNotNullAndEmptyList(labPcrInfoList)){
                            labPcrSamples = labPcrSampleService.selectByPcrId(labPcrInfoList.get(0).getPcrId());
                        }
                    }
                }*/
                for (LabPcrSample lps : labPcrSamples) {
                    LabSySample labSySample = new LabSySample();
                    labSySample.setSampleId(lps.getSampleId());
                    labSySample.setSampleNo(lps.getSampleNo());
                    labSySample.setSampleName(lps.getSampleName());
                    labSySample.setSampleType(lps.getSampleType());
                    labSySample.setSamplePostion(lps.getSamplePostion());
                    labSySampleList.add(labSySample);
                }
            }

            //电泳板检材显示
            List<Map<String, Object>> tempList = TestProcessUtils.syBoardSort(labSySampleList);
            modelAndView.addObject("tempList", tempList);

            //位置排序
            if (ListUtils.isNotNullAndEmptyList(labSySampleList)) {
                Collections.sort(labSySampleList);
            }
        }


        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if (operateUser == null) {
            modelAndView.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return modelAndView;
        }
        //获取当前用户的id
        String userOrgId = operateUser.getOrgId();
        //将当前用户org_id设置进query
        if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
            userOrgId = "110230000000";
        }
        AmPersonalInfo amPersonalInfo = amPersonalInfoService.queryAmPersonalInfo(operateUser.getPersonalId());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间

        List<LabSyInfo> labSyInfo = labSyInfoService.selectByTaskId(labPcrInfo.getTaskId());
        if (labSyInfo.size() != 0 && labSyInfo.get(0).getSyEndDatetime() != null) {
            modelAndView.addObject("syExperimentDate", df.format(labSyInfo.get(0).getSyEndDatetime()));
        } else {
            modelAndView.addObject("syExperimentDate", date);
        }

        modelAndView.addObject("operateUser", amPersonalInfo.getFullName());

        //扩增阶段时间
        if (labPcrInfo != null && labPcrInfo.getPcrEndDatetime() != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            modelAndView.addObject("pcrexperimentDate", simpleDateFormat.format(labPcrInfo.getPcrEndDatetime()));
            //提取阶段时间
            List<LabExtractInfo> labExtractInfo = labExtractInfoService.selectByTaskId(labPcrInfo.getTaskId());
            if (labExtractInfo != null && labExtractInfo.size() != 0 && labExtractInfo.get(0).getExtractDatetime() != null) {
                modelAndView.addObject("extractionExperimentDate", simpleDateFormat.format(labExtractInfo.get(0).getExtractDatetime()));
            } else {
                modelAndView.addObject("extractionExperimentDate", date);
            }
        } else {
            modelAndView.addObject("pcrexperimentDate", date);
            modelAndView.addObject("extractionExperimentDate", date);
        }

        //更改状态
        if (labSyInfosList != null && labSyInfosList.size() != 0) {
            modelAndView.addObject("operateType", Constants.OPERATE_TYPE_EDIT);
        } else {
            modelAndView.addObject("operateType", Constants.OPERATE_TYPE_ADD);
        }

//        EquipmentNameInfo equipmentNameInfo = new EquipmentNameInfo();
        //阶段
//        equipmentNameInfo.setPhase(Constants.EXPERIMENTAL_STAGE_03);
//        equipmentNameInfo.setOrgId(userOrgId);
//
//        equipmentNameInfo.setEquipmentNo("DYY");
//        List<EquipmentNameInfo> panelListDYY = equipmentNameInfoService.selectEquipmentNameInfoListByTypeNo(equipmentNameInfo);

        EquipmentTypeInfo equipmentTypeInfo = new EquipmentTypeInfo();
        equipmentTypeInfo.setExperimentalStage(Constants.EXPERIMENTAL_STAGE_03);
        equipmentTypeInfo.setOrgId(operateUser.getOrgId());
        List<EquipmentTypeInfo> equipmentTypeInfoList = equipmentTypeInfoService.selectLabEquipmentTypeList(equipmentTypeInfo);
        List<EquipmentNameInfo> panelListDYY = new ArrayList<>();
        if (equipmentTypeInfoList.size() > 0) {
            panelListDYY = equipmentNameInfoService.selectEquipmentTypeId(equipmentTypeInfoList.get(0).getId());
        }

        modelAndView.addObject("panelListDYY", panelListDYY);
        modelAndView.addObject("labSySampleList", labSySampleList);
        modelAndView.addObject("boardNo", boardNo);
        modelAndView.addObject("taskId", taskId);
        modelAndView.setViewName("testProcess/syExperiment");
        return modelAndView;
    }

    /**
     * 保存上样任务记录
     *
     * @param request
     * @param taskId
     * @param operateType
     * @param labSyInfo
     * @return
     */
    @RequestMapping(value = "/saveSyInfo", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> saveSyInfo(HttpServletRequest request, String taskId, String operateType,
                                          String syEndDatetime, HttpSession session,
                                          @RequestBody LabSyInfo labSyInfo) throws ParseException {

        Map<String, Object> map = new HashMap<>();
        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        //获取当前用户的id
        String userOrgId = operateUser.getOrgId();
        //将当前用户org_id设置进query
        if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
            userOrgId = "110230000000";
        }

        LabTaskInfo labTaskInfo = labTaskInfoService.selectByPrimaryKey(taskId);

        if (labTaskInfo != null) {
            if (StringUtils.isNotBlank(operateType) && operateType.equals(Constants.OPERATE_TYPE_ADD)) {

                //更新试验阶段
                LabTaskInfo lti = new LabTaskInfo();
                lti.setInspectionType((String) session.getAttribute("inspectionType"));
                lti.setExtractionMode((String) session.getAttribute("extractionMode"));
                LocalBeanUtils.copyPropertiesIgnoreNull(labTaskInfo, lti);
                lti.setTaskStage(Constants.SY_PHASE);
                lti.setDelegateOrgCode(userOrgId);
                lti.setTaskEndDatetime(new Date());
                if (labSyInfo.getBoardNo() != null) {
                    lti.setBoardNo(labSyInfo.getBoardNo());
                }
                try {
                    labTaskInfoService.updateByPrimaryKey(lti);
                } catch (Exception e) {
                    logger.info("---更新任务记录id为[" + lti.getTaskId() + "]的试验阶段失败！---");
                    logger.error("更新任务记录试验阶段失败！", e);
                    map.put("success", false);
                    throw e;
                }
                //更新扩增拼板有数据的实验阶段
               /* updateStatus(taskId);*/

                /** 插入上样任务记录 */
                labSyInfo.setSyId(UUID.randomUUID().toString());
                labSyInfo.setTaskId(labTaskInfo.getTaskId());
                labSyInfo.setSyNo(seqNoGenerateService.getNextSyNoVal("-", userOrgId));
                List<LabPcrInfo> labPcrInfoList = labPcrInfoService.selectByTaskId(labTaskInfo.getTaskId());
                if (labTaskInfo.getBoardNo() != null && labTaskInfo.getBoardNo().trim() != "") {
                    labSyInfo.setBoardNo(labTaskInfo.getBoardNo());
                } else if (labPcrInfoList.size() != 0 && labPcrInfoList != null) {
                    labSyInfo.setBoardNo(labPcrInfoList.get(0).getBoardNo());
                }
                //电泳时间
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                labSyInfo.setSyEndDatetime(simpleDateFormat.parse(syEndDatetime));
                //创建时间
                labSyInfo.setCreateDatetime(new Date());
                if (operateUser != null) {
                    labSyInfo.setCreatePerson(operateUser.getLoginName());
                    labSyInfo.setOrgId(userOrgId);
                }

                try {
                    labSyInfoService.insert(labSyInfo);
                } catch (Exception e) {
                    logger.info("---插入上样任务记录id为[" + labSyInfo.getSyId() + "]的信息失败！---");
                    logger.error("插入上样任务记录失败！", e);
                    throw e;
                }

            } else {
                LabSyInfo syInfo = labSyInfoService.selectByPrimaryKey(labSyInfo.getSyId());
                syInfo.setSyPerson1(labSyInfo.getSyPerson1());
                syInfo.setElecInstrument(labSyInfo.getElecInstrument());
                syInfo.setProduct(labSyInfo.getProduct());
                syInfo.setFormamide(labSyInfo.getFormamide());
                syInfo.setInternalStandardUl(labSyInfo.getInternalStandardUl());
                syInfo.setInternalStandard(labSyInfo.getInternalStandard());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                syInfo.setSyEndDatetime(simpleDateFormat.parse(syEndDatetime));
                syInfo.setSampleCount(labSyInfo.getSampleCount());
                syInfo.setSyString(labSyInfo.getSyString());
                syInfo.setOrgId(userOrgId);
                try {
                    if (operateUser != null) {
                        syInfo.setUpdatePerson(operateUser.getLoginName());
                        labSyInfo.setOrgId(userOrgId);
                    }
                    syInfo.setUpdateDatetime(new Date());
                    labSyInfoService.updateByPrimaryKey(syInfo);
                } catch (Exception e) {
                    logger.info("---更新上样任务记录id为[" + labSyInfo.getSyId() + "]的信息失败！---");
                    logger.error("更新上样任务记录失败！", e);
                    map.put("success", false);
                    throw e;
                }
                //更改实验任务数量
                labTaskInfo.setSampleCount(labSyInfo.getSampleCount());
                try {
                    labTaskInfoService.updateByPrimaryKey(labTaskInfo);
                } catch (Exception e) {
                    logger.info("---更新任务记录id为[" + labTaskInfo.getTaskId() + "]的试验阶段失败！---");
                    logger.error("更新任务记录试验阶段失败！", e);
                    map.put("success", false);
                    throw e;
                }
            }

            map.put("syId", labSyInfo.getSyId());
            map.put("success", true);
        } else {
            map.put("success", false);
        }

        return map;
    }

    //更新拼板实验阶段
    /*public void updateStatus(String taskId){
        //判断拼板任务表中是否有数据
        List<LabPuzzleMiddle> labPuzzleMiddles = labPuzzleMiddleService.selectByTaskId(taskId);
        if (ListUtils.isNotNullAndEmptyList(labPuzzleMiddles)){
            for (LabPuzzleMiddle labPuzzleMiddle : labPuzzleMiddles){
                LabPcrInfo labPcrInfo = labPcrInfoService.selectByPrimaryKey(labPuzzleMiddle.getPcrId());
                LabTaskInfo taskInfo = labTaskInfoService.selectByPrimaryKey(labPcrInfo.getTaskId());
                taskInfo.setTaskStage(Constants.SY_PHASE);
                try {
                    labTaskInfoService.updateByPrimaryKey(taskInfo);
                }catch (Exception e) {
                    logger.info("---更新扩增拼板试验阶段失败！---");
                    logger.error("更新扩增拼板试验阶段失败！", e);
                    throw e;
                }
            }
        }else {
            List<LabPcrInfo> labPcrInfo = labPcrInfoService.selectByTaskId(taskId);
            if (ListUtils.isNotNullAndEmptyList(labPcrInfo)){
                LabPcrInfo labPcr = labPcrInfo.get(0);
                //判断拼板任务表中是否有数据
                LabPuzzleMiddle labPuzzle = labPuzzleMiddleService.selectByPcrId(labPcr.getPcrId());
                if(labPuzzle != null){
                    //保存同一个taskid的任务
                    List<LabPuzzleMiddle> labPuzzleMiddleList = labPuzzleMiddleService.selectByTaskId(labPuzzle.getTaskId());
                    if (ListUtils.isNotNullAndEmptyList((labPuzzleMiddleList))){
                        for (LabPuzzleMiddle labPuzzleMiddle : labPuzzleMiddleList){
                            LabPcrInfo labPcrPuzzle = labPcrInfoService.selectByPrimaryKey(labPuzzleMiddle.getPcrId());
                            LabTaskInfo taskInfo = labTaskInfoService.selectByPrimaryKey(labPcrPuzzle.getTaskId());
                            taskInfo.setTaskStage(Constants.SY_PHASE);
                            try {
                                labTaskInfoService.updateByPrimaryKey(taskInfo);
                            }catch (Exception e) {
                                logger.info("---更新扩增拼板试验阶段失败！---");
                                logger.error("更新扩增拼板试验阶段失败！", e);
                                throw e;
                            }
                        }
                    }
                    //保存主的taskid的任务
                    LabTaskInfo taskInfo = labTaskInfoService.selectByPrimaryKey(labPuzzle.getTaskId());
                    taskInfo.setTaskStage(Constants.SY_PHASE);
                    try {
                        labTaskInfoService.updateByPrimaryKey(taskInfo);
                    }catch (Exception e) {
                        logger.info("---更新扩增拼板试验阶段失败！---");
                        logger.error("更新扩增拼板试验阶段失败！", e);
                        throw e;
                    }
                }
            }
        }
    }*/

    /**
     * 保存上样实验记录
     *
     * @param request
     * @param taskId
     * @param operateType
     * @param labSySampleList
     * @return
     */
    @RequestMapping(value = "/saveSyExperiment", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> saveSyExperiment(HttpServletRequest request, String taskId, String operateType, String syId,
                                                @RequestBody List<LabSySample> labSySampleList) {
        Map<String, Object> map = new HashMap<>();
        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();

        List<LabSyInfo> labSyInfos = labSyInfoService.selectByTaskId(taskId);
        LabSyInfo labSyInfo = null;
        if (ListUtils.isNotNullAndEmptyList(labSyInfos)) {
            List<LabSySample> labSySamples = labSySampleService.selectBySyId(labSyInfos.get(0).getSyId());
            if (labSySamples.size() > 0) {
                labSyInfo = labSyInfos.get(0);
            }/*else {
                LabPuzzleMiddle labPuzzleMiddle = labPuzzleMiddleService.selectBySyId(labSyInfos.get(0).getSyId());
                if (labPuzzleMiddle != null){
                    labSyInfos = labSyInfoService.selectByTaskId(labPuzzleMiddle.getTaskId());
                    labSyInfo = labSyInfos.get(0);
                }
            }*/
        }
        try {
            if (StringUtils.isNotBlank(operateType) && operateType.equals(Constants.OPERATE_TYPE_ADD)) {
                /** 插入上样检材记录 */
                for (LabSySample lss : labSySampleList) {
                    lss.setId(UUID.randomUUID().toString());
                    lss.setCreateDatetime(new Date());
                    if (operateUser != null) {
                        lss.setCreatePerson(operateUser.getLoginName());
                    }
                    if (labSyInfo != null) {
                        lss.setSyId(labSyInfo.getSyId());
                    }
                    try {
                        labSySampleService.insert(lss);
                    } catch (Exception e) {
                        logger.info("---插入上样检材记录id为[" + lss.getId() + "]的信息失败！---");
                        logger.error("插入上样检材记录失败！", e);
                        throw e;
                    }
                }
                /*puzzleSyInfo(taskId,syId);*/

            } else {
                /** 更新上样检材记录 */
                for (LabSySample lss : labSySampleList) {
                    LabSySample labSySample = labSySampleService.selectByPrimaryKey(lss.getId());
                    //新值赋到旧值

                    labSySample.setUpdateDatetime(new Date());
                    if (operateUser != null) {
                        labSySample.setUpdatePerson(operateUser.getLoginName());
                    }
                    syId = labSySample.getSyId();

                    try {
                        labSySampleService.updateByPrimaryKey(labSySample);
                    } catch (Exception e) {
                        logger.info("---更新上样检材记录id为[" + labSySample.getId() + "]的信息失败！---");
                        logger.error("更新上样材记录失败！", e);
                        throw e;
                    }
                }
                map.put("syId", syId);
            }
            map.put("success", true);
        } catch (Exception e) {
            logger.error("savePcrExperiment error!", e);
            map.put("success", false);
        }

        return map;
    }

    //生成电泳拼板任务
    /*public void puzzleSyInfo(String taskId,String syId){
        if(taskId != null && taskId != "" && taskId != "undefined"){
            //通过taskid查询电泳任务  判断是否是被拼的板
            List<LabSyInfo> labSyInfoList = labSyInfoService.selectByTaskId(taskId);
            List<LabSySample> labSySampleList = null;
            if (labSyInfoList.size() != 0 && labSyInfoList != null){
                //如果有电泳任务
                for (LabSyInfo labSyInfo : labSyInfoList){
                    //获取电泳检材样本
                    labSySampleList = labSySampleService.selectBySyId(labSyInfo.getSyId());
                }
            }
            if (syId != null && syId != "" && syId != "undefined"){
                //通过syid获取检材样本  判断是否为要拼的板
                List<LabSySample> labSySamples = labSySampleService.selectBySyId(syId);
                if (labSySamples .size()==0){
                    if (labSySampleList.size() != 0 && labSySampleList != null){
                        LabPuzzleMiddle labPuzzleMiddle = new LabPuzzleMiddle();
                        labPuzzleMiddle.setId(UUID.randomUUID().toString());
                        labPuzzleMiddle.setTaskId(taskId);
                        labPuzzleMiddle.setSyId(syId);
                        try {
                            labPuzzleMiddleService.insert(labPuzzleMiddle);
                        }catch (Exception e) {
                            logger.info("---添加扩增拼板任务失败！---");
                            logger.error("添加扩增拼板任务失败！", e);
                            throw e;
                        }
                    }
                }
            }
        }

    }*/


    /**
     * 刷新上样实验页面
     *
     * @param request
     * @param syId
     * @return
     */
    @RequestMapping("/refreshSyExperiment")
    public ModelAndView refreshSyExperiment(HttpServletRequest request, String syId) {
        ModelAndView modelAndView = InitializationData.initDictList();

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();

        String userOrgId = operateUser.getOrgId();
        //将当前用户org_id设置进query
        if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
            userOrgId = "110230000000";
        }

        LabSyInfo labSyInfo = labSyInfoService.selectByPrimaryKey(syId);
        String boardNo = null;
        String taskId = null;
        if (labSyInfo != null) {
            boardNo = labSyInfo.getBoardNo();
            taskId = labSyInfo.getTaskId();

            if (StringUtils.isNotBlank(labSyInfo.getSyString())) {
                Map<String, Object> mapSyString = (Map) JSON.parse(labSyInfo.getSyString());
                modelAndView.addObject("mapSyString", mapSyString);
            }
        }

        List<LabSySample> labSySampleList = labSySampleService.selectBySyId(syId);
        /*if(ListUtils.isNullOrEmptyList(labSySampleList)){
            LabPuzzleMiddle labPuzzleMiddle = labPuzzleMiddleService.selectBySyId(syId);
            if(labPuzzleMiddle != null){
                List<LabSyInfo> labSyInfos = labSyInfoService.selectByTaskId(labPuzzleMiddle.getTaskId());
                if (ListUtils.isNotNullAndEmptyList(labSyInfos)){
                    LabSyInfo syInfo = labSyInfos.get(0);
                    labSySampleList = labSySampleService.selectBySyId(syInfo.getSyId());
                }
            }
        }*/

        //位置排序
        if (ListUtils.isNotNullAndEmptyList(labSySampleList)) {
            Collections.sort(labSySampleList);
        }

        //电泳板检材显示
        List<Map<String, Object>> tempList = TestProcessUtils.syBoardSort(labSySampleList);
        modelAndView.addObject("tempList", tempList);

        modelAndView.addObject("syId", syId);
        modelAndView.addObject("boardNo", boardNo);

        modelAndView.addObject("operateUser", labSyInfo.getSyPerson1());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        modelAndView.addObject("syExperimentDate", simpleDateFormat.format(labSyInfo.getSyEndDatetime()));
        String date = simpleDateFormat.format(new Date());// new Date()为获取当前系统时间

        //扩增阶段时间
        List<LabPcrInfo> labPcrInfoList = labPcrInfoService.selectByTaskId(labSyInfo.getTaskId());
        LabPcrInfo labPcrInfo = null;
        if (labPcrInfoList.size() != 0) {
            labPcrInfo = labPcrInfoList.get(0);
            if (labPcrInfo != null && labPcrInfo.getCreateDatetime() != null) {
                modelAndView.addObject("pcrexperimentDate", simpleDateFormat.format(labPcrInfo.getCreateDatetime()));
                //提取阶段时间
                List<LabExtractInfo> labExtractInfo = labExtractInfoService.selectByTaskId(labPcrInfo.getTaskId());
                if (labExtractInfo != null && labExtractInfo.size() != 0 && labExtractInfo.get(0).getExtractDatetime() != null) {
                    modelAndView.addObject("extractionExperimentDate", simpleDateFormat.format(labExtractInfo.get(0).getExtractDatetime()));
                } else {
                    modelAndView.addObject("extractionExperimentDate", date);
                }
            } else {
                modelAndView.addObject("pcrexperimentDate", date);
                modelAndView.addObject("extractionExperimentDate", date);
            }
        }
        EquipmentNameInfo equipmentNameInfo = new EquipmentNameInfo();
        //阶段
        equipmentNameInfo.setPhase(Constants.EXPERIMENTAL_STAGE_03);
        equipmentNameInfo.setOrgId(userOrgId);

        equipmentNameInfo.setEquipmentNo("DYY");
        List<EquipmentNameInfo> panelListDYY = equipmentNameInfoService.selectEquipmentNameInfoListByTypeNo(equipmentNameInfo);

        modelAndView.addObject("panelListDYY", panelListDYY);
        modelAndView.addObject("taskId", taskId);
        modelAndView.addObject("operateType", Constants.OPERATE_TYPE_EDIT);
        modelAndView.addObject("labSySampleList", labSySampleList);
        modelAndView.addObject("labSyInfo", labSyInfo);
        modelAndView.setViewName("testProcess/syExperiment");

        return modelAndView;
    }


    /**
     * 根据id删除上样检材记录信息
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/deleteLabSySample")
    @ResponseBody
    public Map<String, Object> deleteLabSySample(HttpServletRequest request, String id) {
        Map<String, Object> result = new HashMap<String, Object>();

        LabSySample labSySample = labSySampleService.selectByPrimaryKey(id);
        if (labSySample != null) {
            try {
                result.put("syId", labSySample.getSyId());
                labSySampleService.deleteByPrimaryKey(id);
            } catch (Exception e) {
                logger.info("---删除上样检材记录id为[" + id + "]的信息失败！---");
                logger.error("删除上样检材记录失败！", e);
                result.put("success", false);
                throw e;
            }

            LabSyInfo labSyInfo = labSyInfoService.selectByPrimaryKey(labSySample.getSyId());

            try {
                LabSyInfo lsi = new LabSyInfo();

                LocalBeanUtils.copyPropertiesIgnoreNull(labSyInfo, lsi);
                List<LabSySample> labSySampleList = labSySampleService.selectBySyId(labSySample.getSyId());

                lsi.setSampleCount((short) labSySampleList.size());
                labSyInfoService.updateByPrimaryKey(lsi);
            } catch (Exception e) {
                logger.info("---更新上样检材数量id为[" + id + "]的信息失败！---");
                logger.error("更新上样检材数量失败！", e);
                result.put("success", false);
                throw e;
            }

            result.put("success", true);
        }

        return result;
    }

    /**
     * 进入分析实验页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/enterAnalysisExperiment")
    public ModelAndView enterAnalysisExperiment(HttpServletRequest request, String syId) {
        ModelAndView modelAndView = InitializationData.initDictList();

        modelAndView.addObject("ipAddr", IpAddressUtils.getIpAddr(request));

        LabSyInfo labSyInfo = labSyInfoService.selectByPrimaryKey(syId);

        List<LabSySample> labSySampleList = labSySampleService.selectBySyId(syId);

        modelAndView.addObject("labSySampleList", labSySampleList);
        modelAndView.addObject("labSyInfo", labSyInfo);
        modelAndView.setViewName("testProcess/analysisExperiment");
        return modelAndView;
    }

    /**
     * 查询检验任务列表页面
     *
     * @param labTaskInfo
     * @return
     */
    @RequestMapping(value = "/selectLabTaskInfo", method = RequestMethod.POST)
    public ModelAndView selectLabTaskInfo(HttpServletRequest request, LabTaskInfo labTaskInfo, String taskStartDatetim, String taskEndDatetim, PageInfo pageInfo) throws ParseException {
        ModelAndView modelAndView = InitializationData.initDictList();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        labTaskInfo.setTaskStartDatetime(simpleDateFormat.parse(taskStartDatetim));
        labTaskInfo.setTaskEndDatetime(simpleDateFormat.parse(taskEndDatetim));

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if (operateUser == null) {
            modelAndView.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return modelAndView;
        }
        //获取当前用户的id
        String userOrgId = operateUser.getOrgId();
        //将当前用户org_id设置进query
        if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
            userOrgId = "110230000000";
        }

        // 获取实验人员信息
        List<AmPersonalInfoVo> amPersonalInfoVoList = amPersonalInfoService.queryAmPersonalInfoVoList(operateUser.getOrgId());

        List<LabTaskInfo> labTaskInfoList = labTaskInfoService.selectLabTaskInfo(labTaskInfo);

        modelAndView.addObject("operateUser", operateUser);
        modelAndView.addObject("amPersonalInfoVoList", amPersonalInfoVoList);
        modelAndView.addObject("labTaskInfoList", labTaskInfoList);
        modelAndView.addObject("labTaskInfo", labTaskInfo);
        modelAndView.addObject("taskStartDatetime", taskStartDatetim);
        modelAndView.addObject("taskEndDatetime", taskEndDatetim);
        modelAndView.addObject("pageInfo", pageInfo.updatePageInfo(0));

        modelAndView.setViewName("testProcess/testProcess");
        return modelAndView;
    }

    /**
     * 进入手动提取页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/manualExtraction")
    public ModelAndView manualExtraction(HttpServletRequest request) {
        ModelAndView modelAndView = InitializationData.initDictList();

        modelAndView.setViewName("testProcess/manualExtraction");
        return modelAndView;
    }

    /**
     * 进入手动提取实验页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/manualExtractionExperiment")
    public ModelAndView manualExtractionExperiment(HttpServletRequest request, HttpSession session, String taskId) {
        ModelAndView modelAndView = InitializationData.initDictList();

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if (operateUser == null) {
            modelAndView.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return modelAndView;
        }
        //获取当前用户的id
        String userOrgId = operateUser.getOrgId();
        //将当前用户org_id设置进query
        if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
            userOrgId = "110230000000";
        }

        AmPersonalInfo amPersonalInfo = amPersonalInfoService.queryAmPersonalInfo(operateUser.getPersonalId());
        modelAndView.addObject("operateUser", amPersonalInfo.getFullName());
        modelAndView.addObject("routine_extract_extractPerson1", amPersonalInfo.getFullName());
        modelAndView.addObject("spot_extract_extractPerson1", amPersonalInfo.getFullName());
        modelAndView.addObject("special_extract_extractPerson1", amPersonalInfo.getFullName());
        modelAndView.addObject("other_extract_extractPerson1", amPersonalInfo.getFullName());

        List<LimsSampleInfoDna> limsSampleInfoDnaList = (List<LimsSampleInfoDna>) session.getAttribute("limsSampleInfoDnaList");
        List<LabExtractSample> labExtractSampleListRoutine = new ArrayList<>();
        List<LabExtractSample> labExtractSampleListSpot = new ArrayList<>();
        List<LabExtractSample> labExtractSampleListSpecial = new ArrayList<>();
        List<LabExtractSample> labExtractSampleListOther = new ArrayList<>();
        for (LimsSampleInfoDna lsid : limsSampleInfoDnaList) {
            LabExtractSample labExtractSample = new LabExtractSample();
            if (lsid.getSampleType().equals("01") || lsid.getSampleType().equals("03") || lsid.getSampleType().equals("04") || lsid.getSampleType().equals("08")) {
                labExtractSample.setSampleNo(lsid.getSampleNo());
                labExtractSample.setSampleId(lsid.getSampleId());
                labExtractSample.setSampleName(lsid.getSampleName());
                labExtractSample.setSampleType(lsid.getSampleType());
                labExtractSampleListRoutine.add(labExtractSample);
            } else if (lsid.getSampleType().equals("02")) {
                labExtractSample.setSampleNo(lsid.getSampleNo());
                labExtractSample.setSampleId(lsid.getSampleId());
                labExtractSample.setSampleName(lsid.getSampleName());
                labExtractSample.setSampleType(lsid.getSampleType());
                labExtractSampleListSpot.add(labExtractSample);
            } else if (lsid.getSampleType().equals("05") || lsid.getSampleType().equals("06") || lsid.getSampleType().equals("07") || lsid.getSampleType().equals("09")) {
                labExtractSample.setSampleNo(lsid.getSampleNo());
                labExtractSample.setSampleId(lsid.getSampleId());
                labExtractSample.setSampleName(lsid.getSampleName());
                labExtractSample.setSampleType(lsid.getSampleType());
                labExtractSampleListSpecial.add(labExtractSample);
            } else if (lsid.getSampleType().equals("99")) {
                labExtractSample.setSampleNo(lsid.getSampleNo());
                labExtractSample.setSampleId(lsid.getSampleId());
                labExtractSample.setSampleName(lsid.getSampleName());
                labExtractSample.setSampleType(lsid.getSampleType());
                labExtractSampleListOther.add(labExtractSample);
            }
        }
//        String inspectionType = (String) session.getAttribute("inspectionType");
//        String extractionMode = (String) session.getAttribute("extractionMode");

        //时间
        Map<String, Object> map = goTime(null);
        modelAndView.addObject("extractionExperimentDate", map.get("extractionExperimentDate"));
        modelAndView.addObject("routine_extract_extractionExperimentDate", map.get("extractionExperimentDate"));
        modelAndView.addObject("spot_extract_extractionExperimentDate", map.get("extractionExperimentDate"));
        modelAndView.addObject("special_extract_extractionExperimentDate", map.get("extractionExperimentDate"));
        modelAndView.addObject("other_extract_extractionExperimentDate", map.get("extractionExperimentDate"));

        //设备选择
//        List<EquipmentNameInfo> equipmentNameInfoList = equipmentNameInfoService.selectList(userOrgId, Constants.EXPERIMENTAL_STAGE_01);
//        modelAndView.addObject("equipmentNameInfoList", equipmentNameInfoList);

        EquipmentTypeInfo equipmentTypeInfo = new EquipmentTypeInfo();
        equipmentTypeInfo.setExperimentalStage(Constants.EXPERIMENTAL_STAGE_01);
        equipmentTypeInfo.setOrgId(operateUser.getOrgId());
        List<EquipmentTypeInfo> equipmentTypeInfoList = equipmentTypeInfoService.selectLabEquipmentTypeList(equipmentTypeInfo);
        List<EquipmentNameInfo> equipmentNameInfoList = new ArrayList<>();
        if (equipmentTypeInfoList.size() > 0) {
            equipmentNameInfoList = equipmentNameInfoService.selectEquipmentTypeId(equipmentTypeInfoList.get(0).getId());
        }

        //试剂信息
        ReagentInfo reagentInfo = new ReagentInfo();
        reagentInfo.setExperimentalStage(Constants.EXPERIMENTAL_STAGE_01);
        reagentInfo.setOrgId(userOrgId);
        List<ReagentInfo> reagentInfoList = reagentInfoService.selectList(reagentInfo);
        modelAndView.addObject("reagentInfoList", reagentInfoList);

        modelAndView.addObject("labExtractSampleListRoutine", labExtractSampleListRoutine);
        modelAndView.addObject("labExtractSampleListSpot", labExtractSampleListSpot);
        modelAndView.addObject("labExtractSampleListSpecial", labExtractSampleListSpecial);
        modelAndView.addObject("labExtractSampleListOther", labExtractSampleListOther);
        modelAndView.addObject("operateType", Constants.OPERATE_TYPE_ADD);
        modelAndView.addObject("taskId", taskId);
        modelAndView.setViewName("testProcess/manualExtractionExperiment");
        return modelAndView;
    }

    /**
     * 保存手动提取
     */
    @RequestMapping(value = "/saveManualExtractionExperiment", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> saveManualExtractionExperiment(HttpServletRequest request, String operateType, String extractStage,
                                                              String extractDatetimes, String extractPerson1, HttpSession session,
                                                              String equipment, String kit, String taskId, String boardNo,
                                                              @RequestBody List<LabExtractSample> labExtractSampleList) {
        Map<String, Object> map = new HashMap<>();
        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        //获取单位信息
        OrgInfo orgInfo = orgInfoService.selectByPrimaryKey(operateUser.getOrgId());
        //获取当前用户的id
        String userOrgId = operateUser.getOrgId();
        if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
            userOrgId = "110230000000";
        }

        try {
            if (ListUtils.isNotNullAndEmptyList(labExtractSampleList)) {
                if (StringUtils.isNotBlank(operateType) && operateType.equals(Constants.OPERATE_TYPE_ADD)) {
                    //获取任务编号
                    /*String taskNo = null;
                    String extractNo = null;
                    if (StringUtils.isNotBlank(userOrgId)){
                        taskNo = seqNoGenerateService.getNextTastNoVal("-", userOrgId);
                        extractNo = seqNoGenerateService.getNextExtractNoVal("-", userOrgId);
                    }else{
                        taskNo = seqNoGenerateService.getNextTastNoVal("-", null);
                        extractNo = seqNoGenerateService.getNextExtractNoVal("-", userOrgId);
                    }

                    LabTaskInfo labTaskInfo = new LabTaskInfo();
                    *//** 插入任务记录 *//*
                    labTaskInfo.setTaskId(UUID.randomUUID().toString());
                    labTaskInfo.setTaskNo(taskNo);
                    labTaskInfo.setTaskStatus(null);

                    labTaskInfo.setInspectionType((String)session.getAttribute("inspectionType"));
                    labTaskInfo.setExtractionMode((String)session.getAttribute("extractionMode"));

                    labTaskInfo.setTaskStage(Constants.EXTRACT_PHASE);
                    if (operateUser != null) {
                        labTaskInfo.setTaskPerson(operateUser.getLoginName());
                        labTaskInfo.setCreatePerson(operateUser.getLoginName());
                    }
                    labTaskInfo.setCreateDatetime(new Date());
                    labTaskInfo.setDeleteFlag(Constants.DELETE_FLAG_0);

                    if (orgInfo != null){
                        labTaskInfo.setDelegateOrgCode(orgInfo.getOrgCode());
                        labTaskInfo.setDelegateOrgName(orgInfo.getOrgName());
                    }

                    try {
                        labTaskInfoService.insert(labTaskInfo);
                    }catch (Exception e) {
                        logger.info("----插入任务记录id为[" + labTaskInfo.getTaskId() + "]的信息失败！---");
                        logger.error("插入任务记录失败！", e);
                        throw e;
                    }*/

                    /** 插入提取任务记录 */
                    LabExtractInfo labExtractInfo = new LabExtractInfo();
                    labExtractInfo.setExtractId(UUID.randomUUID().toString());
//                    labExtractInfo.setTaskId(labTaskInfo.getTaskId());
                    String extractNo = seqNoGenerateService.getNextExtractNoVal("-", userOrgId);
                    labExtractInfo.setExtractNo(extractNo);

                    labExtractInfo.setExtractPerson1(extractPerson1);
                    labExtractInfo.setCreateDatetime(new Date());
//                     labExtractInfo.setCreateDatetime(DateUtils.stringToDate(extractDatetimes,"yyyy-MM-dd HH:mm"));
                    labExtractInfo.setExtractDatetime(DateUtils.stringToDate(extractDatetimes, "yyyy-MM-dd"));
//                    labExtractInfo.setExtractDatetime(DateUtils.stringToDate(extractEndDatetimes,"yyyy-MM-dd"));
                    labExtractInfo.setEquipment(equipment);
                    labExtractInfo.setKit(kit);
                    labExtractInfo.setBoardNo(boardNo);
                    labExtractInfo.setExtractStage(extractStage);

                    labExtractInfo.setSampleCount((short) labExtractSampleList.size());
                    if (operateUser != null) {
                        labExtractInfo.setCreatePerson(operateUser.getLoginName());
                        labExtractInfo.setOrgId(userOrgId);
                    }
                    labExtractInfo.setDeleteFlag(Constants.DELETE_FLAG_0);

                    try {
                        labExtractInfoService.insert(labExtractInfo);
                    } catch (Exception e) {
                        logger.info("---插入提取任务记录id为[" + labExtractInfo.getExtractId() + "]的信息失败！---");
                        logger.error("插入提取任务记录失败！", e);
                        throw e;
                    }
                    List<LabExtractSample> idList = new ArrayList<>();
                    /** 插入提取检材记录 */
                    for (LabExtractSample les : labExtractSampleList) {
                        les.setId(UUID.randomUUID().toString());
                        les.setExtractId(labExtractInfo.getExtractId());
                        les.setCreateDatetime(new Date());
                        if (operateUser != null) {
                            les.setCreatePerson(operateUser.getLoginName());

                        }
                        idList.add(les);
                        try {
                            labExtractSampleService.insert(les);
                        } catch (Exception e) {
                            logger.info("---插入提取检材记录id为[" + les.getId() + "]的信息失败！---");
                            logger.error("插入提取检材记录失败！", e);
                            throw e;
                        }
                    }
                    map.put("idList", idList);
//                    map.put("taskId", labTaskInfo.getTaskId());
                    map.put("extractId", labExtractInfo.getExtractId());
                } else {
                    String extractId = null;
                    /** 更新提取检材记录 */
                    for (LabExtractSample les : labExtractSampleList) {

                        LabExtractSample labExtractSample = labExtractSampleService.selectByPrimaryKey(les.getId());

                        //新值赋到旧值
                        labExtractSample.setTransferMethod(les.getTransferMethod());
                        labExtractSample.setExtractPart(les.getExtractPart());
                        labExtractSample.setExtractMethod(les.getExtractMethod());

                        labExtractSample.setLeave(les.getLeave());
                        labExtractSample.setBath(les.getBath());
                        labExtractSample.setDry(les.getDry());
                        labExtractSample.setShake(les.getShake());
                        labExtractSample.setEarthquake(les.getEarthquake());

                        labExtractSample.setExtractString(les.getExtractString());

                        labExtractSample.setUpdateDatetime(new Date());
                        if (operateUser != null) {
                            labExtractSample.setUpdatePerosn(operateUser.getLoginName());
                        }
                        extractId = labExtractSample.getExtractId();
                        try {
                            labExtractSampleService.updateByPrimaryKey(labExtractSample);
                        } catch (Exception e) {
                            logger.info("---更新提取检材记录id为[" + labExtractSample.getId() + "]的信息失败！---");
                            logger.error("更新提取检材记录失败！", e);
                            throw e;
                        }
                    }
                    LabExtractInfo labExtractInfo = labExtractInfoService.selectByPrimaryKey(extractId);
                    labExtractInfo.setKit(kit);
                    labExtractInfo.setBoardNo(boardNo);
                    labExtractInfo.setEquipment(equipment);
                    labExtractInfo.setExtractPerson1(extractPerson1);
//                    labExtractInfo.setCreateDatetime(DateUtils.stringToDate(extractDatetimes,"yyyy-MM-dd HH:mm"));
                    labExtractInfo.setExtractDatetime(DateUtils.stringToDate(extractDatetimes, "yyyy-MM-dd"));
//                    labExtractInfo.setExtractDatetime(DateUtils.stringToDate(extractEndDatetimes,"yyyy-MM-dd HH:mm"));
//                    labExtractInfo.setExtractDatetime(DateUtils.stringToDate(extractEndDatetimes,"yyyy-MM-dd"));
                    labExtractInfo.setExtractStage(extractStage);
                    labExtractInfoService.updateByPrimaryKey(labExtractInfo);
                    map.put("extractId", extractId);
                    map.put("taskId", labExtractInfo.getTaskId());
                }
                map.put("extractStage", extractStage);
                map.put("success", true);
            } else {
                map.put("success", false);
            }
        } catch (Exception e) {
            logger.error("saveExtractionExperiment error!", e);
            map.put("success", false);
        }
        return map;
    }

    /**
     * 进入手动实验页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/enterManualExperiment")
    public ModelAndView enterManualExperiment(HttpServletRequest request, String taskId, String taskStage) {
        ModelAndView modelAndView = InitializationData.initDictList();

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if (operateUser == null) {
            modelAndView.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return modelAndView;
        }
        //获取当前用户的id
        String userOrgId = operateUser.getOrgId();
        //将当前用户org_id设置进query
        if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
            userOrgId = "110230000000";
        }

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间
        if (StringUtils.isNotBlank(taskStage)) {
            if (taskStage.equals(Constants.EXTRACT_PHASE)) {
                //进入提取实验页面
                List<LabExtractInfo> labExtractInfoList = labExtractInfoService.selectByTaskId(taskId);
                //设备选择
//                List<EquipmentNameInfo> equipmentNameInfoList = equipmentNameInfoService.selectList(userOrgId, Constants.EXPERIMENTAL_STAGE_01);

                EquipmentTypeInfo equipmentTypeInfo = new EquipmentTypeInfo();
                equipmentTypeInfo.setExperimentalStage(Constants.EXPERIMENTAL_STAGE_01);
                equipmentTypeInfo.setOrgId(userOrgId);
                List<EquipmentTypeInfo> equipmentTypeInfoList = equipmentTypeInfoService.selectLabEquipmentTypeList(equipmentTypeInfo);
                List<EquipmentNameInfo> equipmentNameInfoList = new ArrayList<>();
                if (equipmentTypeInfoList.size() > 0) {
                    equipmentNameInfoList = equipmentNameInfoService.selectEquipmentTypeId(equipmentTypeInfoList.get(0).getId());
                }

                //试剂信息
                ReagentInfo reagentInfo = new ReagentInfo();
                reagentInfo.setExperimentalStage(Constants.EXPERIMENTAL_STAGE_01);
                reagentInfo.setOrgId(userOrgId);
                List<ReagentInfo> reagentInfoList = reagentInfoService.selectList(reagentInfo);

                if (ListUtils.isNotNullAndEmptyList(labExtractInfoList)) {
                    for (int i = 0; i < labExtractInfoList.size(); i++) {
                        LabExtractInfo labExtractInfo = labExtractInfoList.get(i);
                        List<LabExtractSample> labExtractSampleList = labExtractSampleService.selectByExtractId(labExtractInfo.getExtractId());
                        if (labExtractSampleList.size() > 0) {
                            for (int j = 0; j < labExtractSampleList.size(); j++) {
                                if (StringUtils.isNotBlank(labExtractSampleList.get(j).getExtractMethod())) {
                                    UseInstruments useInstrumentsEntity = new UseInstruments();
                                    if (StringUtils.isNotBlank(labExtractSampleList.get(j).getExtractMethod())) {
                                        useInstrumentsEntity.setMethodName(labExtractSampleList.get(j).getExtractMethod());
                                    } else {
                                        useInstrumentsEntity.setMethodName("A");
                                    }
                                    useInstrumentsEntity.setOrgCode(operateUser.getOrgId());
                                    List<UseInstruments> useInstrumentsList = useInstrumentsService.findByExtractMentod(useInstrumentsEntity);
                                    if (useInstrumentsList.size() > 0) {
                                        UseInstruments useInstruments = useInstrumentsList.get(0);
                                        labExtractSampleList.get(j).setLeave(useInstruments.getLeave());
                                        labExtractSampleList.get(j).setLeaveTwo(useInstruments.getLeavetwo());
                                        labExtractSampleList.get(j).setBath(useInstruments.getBath());
                                        labExtractSampleList.get(j).setBathTwo(useInstruments.getBathtwo());
                                        labExtractSampleList.get(j).setDry(useInstruments.getDry());
                                        labExtractSampleList.get(j).setDryTwo(useInstruments.getDrytwo());
                                        labExtractSampleList.get(j).setShake(useInstruments.getShake());
                                        labExtractSampleList.get(j).setEarthquake(useInstruments.getEarthquake());
                                    }
                                }
                            }
                        }


                        LabExtractSample labExtractSample = labExtractSampleList.get(0);
                        String sampleType = limsSampleInfoDnaService.selectSampleInfoDnaById(labExtractSample.getSampleId()).getSampleType();
                        if (sampleType.equals("01") || sampleType.equals("03") || sampleType.equals("04") || sampleType.equals("08")) {


                            modelAndView.addObject("labExtractSampleListRoutine", labExtractSampleList);
                            //选择设备
                            String[] equipment = null;
                            if (labExtractInfo != null && labExtractInfo.getEquipment() != null) {
                                equipment = labExtractInfo.getEquipment().split(",");
                            }
                            modelAndView.addObject("equipment", equipment);
                            //选择试剂盒
                            String[] panelKit = null;
                            if (labExtractInfo != null && labExtractInfo.getKit() != null) {
                                panelKit = labExtractInfo.getKit().split(",");
                            }
                            //试剂批号回显
                            List<ReagentInfo> selectList = new ArrayList<>();
                            if (panelKit != null) {
                                for (int k = 0; k < panelKit.length; k++) {
                                    for (int j = 0; j < reagentInfoList.size(); j++) {
                                        if (panelKit[k].equals(reagentInfoList.get(j).getId())) {
                                            selectList.add(reagentInfoList.get(j));
                                        }
                                    }
                                }
                            }
                            modelAndView.addObject("panelKit", panelKit);
                            modelAndView.addObject("selectList", selectList);
                            modelAndView.addObject("routine_extract_extractId", labExtractInfo.getExtractId());
                            modelAndView.addObject("routine_extract_extractPerson1", labExtractInfo.getExtractPerson1());
                            modelAndView.addObject("routine_extract_extractionExperimentDate", DateUtils.dateToString(labExtractInfo.getExtractDatetime(), "yyyy-MM-dd HH:mm"));
                        } else if (sampleType.equals("02")) {
                            List<LabExtractSample> labExtractSampleList1 = new ArrayList<>();
                            for (int j = 0; j < labExtractSampleList.size(); j++) {
                                LabExtractSample labExtractSampleSpot = labExtractSampleList.get(j);

                                String[] spot = labExtractSampleSpot.getExtractString().split("},");
                                if(spot.length == 13){
                                    labExtractSampleSpot.setTES(spot[0]);
                                    labExtractSampleSpot.setSDS(spot[1]);
                                    labExtractSampleSpot.setPK(spot[2]);
                                    labExtractSampleSpot.setIsOK(Boolean.valueOf(spot[3]));
                                    labExtractSampleSpot.setIsTwe(Boolean.valueOf(spot[4]));
                                    labExtractSampleSpot.setTES1(spot[5]);
                                    labExtractSampleSpot.setSDS1(spot[6]);
                                    labExtractSampleSpot.setChelex(spot[7]);
                                    labExtractSampleSpot.setPK1(spot[8]);
                                    labExtractSampleSpot.setIMDTT(spot[9]);
                                    labExtractSampleSpot.setPurification(spot[10]);
                                    labExtractInfo.setDigestionTemperature(spot[11]);
                                    labExtractInfo.setDigestionTime(spot[12]);
                                }else if (spot.length == 11){
                                    labExtractSampleSpot.setTES(spot[0]);
                                    labExtractSampleSpot.setSDS(spot[1]);
                                    labExtractSampleSpot.setPK(spot[2]);
                                    labExtractSampleSpot.setIsOK(Boolean.valueOf(spot[3]));
                                    labExtractSampleSpot.setIsTwe(Boolean.valueOf(spot[4]));
                                    labExtractSampleSpot.setTES1(spot[5]);
                                    labExtractSampleSpot.setSDS1(spot[6]);
                                    labExtractSampleSpot.setChelex(spot[7]);
                                    labExtractSampleSpot.setPK1(spot[8]);
                                    labExtractSampleSpot.setIMDTT(spot[9]);
                                    labExtractSampleSpot.setPurification(spot[10]);
                                }else if (spot.length == 7){
                                    labExtractSampleSpot.setTES(spot[0]);
                                    labExtractSampleSpot.setSDS(spot[1]);
                                    labExtractSampleSpot.setPK(spot[2]);
                                    labExtractSampleSpot.setIsOK(Boolean.valueOf(spot[3]));
                                    labExtractSampleSpot.setIsTwe(Boolean.valueOf(spot[4]));
                                    labExtractInfo.setDigestionTemperature(spot[5]);
                                    labExtractInfo.setDigestionTime(spot[6]);
                                }else{
                                    labExtractSampleSpot.setTES(spot[0]);
                                    labExtractSampleSpot.setSDS(spot[1]);
                                    labExtractSampleSpot.setPK(spot[2]);
                                    labExtractSampleSpot.setIsOK(Boolean.valueOf(spot[3]));
                                    labExtractSampleSpot.setIsTwe(Boolean.valueOf(spot[4]));
                                }

                                labExtractSampleList1.add(labExtractSampleSpot);
                            }
                            modelAndView.addObject("labExtractSampleListSpot", labExtractSampleList1);
                            //选择设备
                            String[] equipment = null;
                            if (labExtractInfo != null && labExtractInfo.getEquipment() != null) {
                                equipment = labExtractInfo.getEquipment().split(",");
                            }
                            modelAndView.addObject("equipmentTwe", equipment);
                            //选择试剂盒
                            String[] panelKit = null;
                            if (labExtractInfo != null && labExtractInfo.getKit() != null) {
                                panelKit = labExtractInfo.getKit().split(",");
                            }
                            //试剂批号回显
                            List<ReagentInfo> selectList = new ArrayList<>();
                            if (panelKit != null) {
                                for (int k = 0; k < panelKit.length; k++) {
                                    for (int j = 0; j < reagentInfoList.size(); j++) {
                                        if (panelKit[k].equals(reagentInfoList.get(j).getId())) {
                                            selectList.add(reagentInfoList.get(j));
                                        }
                                    }
                                }
                            }
                            modelAndView.addObject("panelKitTwe", panelKit);
                            modelAndView.addObject("selectListTwe", selectList);
                            modelAndView.addObject("spot_extract_extractId", labExtractInfo.getExtractId());
                            modelAndView.addObject("spot_extract_extractPerson1", labExtractInfo.getExtractPerson1());
                            modelAndView.addObject("spot_extract_extractionExperimentDate", DateUtils.dateToString(labExtractInfo.getExtractDatetime(), "yyyy-MM-dd HH:mm"));
                        } else if (sampleType.equals("05") || sampleType.equals("06") || sampleType.equals("07") || sampleType.equals("09")) {
                            modelAndView.addObject("labExtractSampleListSpecial", labExtractSampleList);
                            modelAndView.addObject("special_extract_extractId", labExtractInfo.getExtractId());
                            modelAndView.addObject("special_extract_extractPerson1", labExtractInfo.getExtractPerson1());
                            modelAndView.addObject("special_extract_extractionExperimentDate", DateUtils.dateToString(labExtractInfo.getExtractDatetime(), "yyyy-MM-dd HH:mm"));
                        } else if (sampleType.equals("99")) {
                            modelAndView.addObject("labExtractSampleListOther", labExtractSampleList);
                            modelAndView.addObject("other_extract_extractId", labExtractInfo.getExtractId());
                            modelAndView.addObject("other_extract_extractPerson1", labExtractInfo.getExtractPerson1());
                            modelAndView.addObject("other_extract_extractionExperimentDate", DateUtils.dateToString(labExtractInfo.getExtractDatetime(), "yyyy-MM-dd HH:mm"));
                        }

                        Map<String, Object> map = goTime(taskId);
                        modelAndView.addObject("extractionExperimentDate", map.get("extractionExperimentDate"));
                        modelAndView.addObject("labExtractInfo", labExtractInfo);
                    }
                }
                modelAndView.addObject("operateType", Constants.OPERATE_TYPE_EDIT);
                modelAndView.addObject("taskId", taskId);
                modelAndView.addObject("reagentInfoList", reagentInfoList);
                modelAndView.addObject("equipmentNameInfoList", equipmentNameInfoList);
                modelAndView.setViewName("testProcess/manualExtractionExperiment");
            } else if (taskStage.equals(Constants.PCR_PHASE)) {
                //进入扩增实验页面
                List<LabPcrInfo> labPcrInfoList = labPcrInfoService.selectByTaskId(taskId);
                if (ListUtils.isNotNullAndEmptyList(labPcrInfoList)) {
                    LabPcrInfo labPcrInfo = labPcrInfoList.get(0);

                    List<LabPcrSample> labPcrSampleList = labPcrSampleService.selectByPcrId(labPcrInfo.getPcrId());
                    List<LabPcrInfo> labPcrInfolist = labPcrInfoService.selectByTaskId(taskId);


                    //扩增阶段时间
                    if (labPcrInfo != null && labPcrInfo.getCreateDatetime() != null) {
                        modelAndView.addObject("date", df.format(labPcrInfo.getPcrEndDatetime()));
                        //提取阶段时间
                        List<LabExtractInfo> labExtractInfo = labExtractInfoService.selectByTaskId(taskId);
                        if (labExtractInfo != null && labExtractInfo.get(0).getExtractDatetime() != null) {
                            modelAndView.addObject("extractionExperimentDate", df.format(labExtractInfo.get(0).getExtractDatetime()));
                        } else {
                            modelAndView.addObject("extractionExperimentDate", date);
                        }
                    } else {
                        modelAndView.addObject("date", date);
                        modelAndView.addObject("extractionExperimentDate", date);
                    }

                    //扩增显示的时间
                    LabPcrInfo labPcrInfo1 = labPcrInfoService.selectByTaskId(taskId).get(0);
                    if (labPcrInfo1 != null && labPcrInfo1.getPcrEndDatetime() != null) {
                        modelAndView.addObject("pcrexperimentDate", df.format(labPcrInfo1.getPcrEndDatetime()));

                    } else {
                        modelAndView.addObject("pcrexperimentDate", date);
                    }

                    //扩增板检材显示
                    List<Map<String, Object>> tempList = TestProcessUtils.pcrBoardSort(labPcrSampleList);

                    //获取当前时间
                    modelAndView.addObject("date", date);
                    modelAndView.addObject("pcrId", labPcrInfo.getPcrId());
                    modelAndView.addObject("boardNo", labPcrInfo.getBoardNo());
                    modelAndView.addObject("taskId", taskId);
                    modelAndView.addObject("operateType", Constants.OPERATE_TYPE_EDIT);
                    modelAndView.addObject("labPcrSampleList", labPcrSampleList);
                    modelAndView.addObject("tempList", tempList);
                    modelAndView.addObject("labPcrInfo", labPcrInfolist.get(0));
                    modelAndView.addObject("operateUser", labPcrInfolist.get(0).getPcrPerson1());
                }

                modelAndView.setViewName("testProcess/pcrExperiment");
            } else if (taskStage.equals(Constants.SY_PHASE)) {
                //进入电泳实验页面
                List<LabSyInfo> labSyInfoList = labSyInfoService.selectByTaskId(taskId);

                if (ListUtils.isNotNullAndEmptyList(labSyInfoList)) {
                    LabSyInfo labSyInfo = labSyInfoList.get(0);

                    List<LabSySample> labSySampleList = labSySampleService.selectBySyId(labSyInfo.getSyId());

                    //扩增阶段时间
                    LabPcrInfo labPcrInfo = labPcrInfoService.selectByTaskId(taskId).get(0);
                    if (labPcrInfo != null && labPcrInfo.getPcrEndDatetime() != null) {
                        modelAndView.addObject("pcrexperimentDate", df.format(labPcrInfo.getPcrEndDatetime()));
                        //提取阶段时间
                        List<LabExtractInfo> labExtractInfo = labExtractInfoService.selectByTaskId(taskId);
                        if (labExtractInfo != null && labExtractInfo.get(0).getExtractDatetime() != null) {
                            modelAndView.addObject("extractionExperimentDate", df.format(labExtractInfo.get(0).getExtractDatetime()));
                        } else {
                            modelAndView.addObject("extractionExperimentDate", date);
                        }
                    } else {
                        modelAndView.addObject("pcrexperimentDate", date);
                        modelAndView.addObject("extractionExperimentDate", date);
                    }

                    if (labSyInfo != null && labSyInfo.getSyEndDatetime() != null) {
                        modelAndView.addObject("syExperimentDate", df.format(labSyInfo.getSyEndDatetime()));

                    } else {
                        modelAndView.addObject("syExperimentDate", date);
                    }

                    //电泳板检材显示
                    List<Map<String, Object>> tempList = TestProcessUtils.syBoardSort(labSySampleList);
                    modelAndView.addObject("tempList", tempList);

                    EquipmentNameInfo equipmentNameInfo = new EquipmentNameInfo();
                    //阶段
                    equipmentNameInfo.setPhase(Constants.EXPERIMENTAL_STAGE_03);
                    equipmentNameInfo.setOrgId(userOrgId);

                    equipmentNameInfo.setEquipmentNo("DYY");
                    List<EquipmentNameInfo> panelListDYY = equipmentNameInfoService.selectEquipmentNameInfoListByTypeNo(equipmentNameInfo);

                    if (labSyInfo != null) {
                        if (StringUtils.isNotBlank(labSyInfo.getSyString())) {
                            Map<String, Object> mapSyString = (Map) JSON.parse(labSyInfo.getSyString());
                            modelAndView.addObject("mapSyString", mapSyString);
                        }
                    }

                    modelAndView.addObject("syId", labSyInfo.getSyId());
                    modelAndView.addObject("boardNo", labSyInfo.getBoardNo());
                    modelAndView.addObject("taskId", taskId);
                    modelAndView.addObject("operateUser", labSyInfo.getSyPerson1());
                    modelAndView.addObject("operateType", Constants.OPERATE_TYPE_EDIT);
                    modelAndView.addObject("labSySampleList", labSySampleList);
                    modelAndView.addObject("panelListDYY", panelListDYY);
                    modelAndView.addObject("labSyInfo", labSyInfoList.get(0));
                }
                modelAndView.setViewName("testProcess/syExperiment");
            } else if (taskStage.equals(Constants.ANALYSIS_PHASE)) {
                //进入分析实验页面

                modelAndView.setViewName("testProcess/analysisExperiment");
            }
        }
        return modelAndView;
    }

    /**
     * 进入扩增拼板实验页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/pcrPuzzleExperiment")
    public ModelAndView pcrPuzzleExperiment(HttpServletRequest request, String taskId) {
        ModelAndView modelAndView = InitializationData.initDictList();
        //当前登录用户
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if (operateUser == null) {
            modelAndView.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return modelAndView;
        }
        //获取当前用户的id
        String userOrgId = operateUser.getOrgId();
        //将当前用户org_id设置进query
        if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
            userOrgId = "110230000000";
        }

        AmPersonalInfo amPersonalInfo = amPersonalInfoService.queryAmPersonalInfo(operateUser.getPersonalId());
        modelAndView.addObject("operateUser", amPersonalInfo.getFullName());

        List<LabPcrInfo> labPcrInfoList = labPcrInfoService.selectByTaskId(taskId);
        if (labPcrInfoList.size() == 0) {

            List<LabExtractInfo> labExtractInfoList = labExtractInfoService.selectByTaskId(taskId);
            List<LabExtractSample> labExtractSampleList = new ArrayList<>();
            for (int i = 0; i < labExtractInfoList.size(); i++) {
                LabExtractInfo labExtractInfo = labExtractInfoList.get(i);
                labExtractSampleList.addAll(labExtractSampleService.selectByExtractId(labExtractInfo.getExtractId()));
            }
            List<LabPcrSample> labPcrSampleList = new ArrayList<>();
            for (LabExtractSample les : labExtractSampleList) {
                LabPcrSample labPcrSample = new LabPcrSample();
                labPcrSample.setSampleId(les.getSampleId());
                labPcrSample.setSampleNo(les.getSampleNo());
                labPcrSample.setSampleName(les.getSampleName());
                labPcrSample.setSampleType(les.getSampleType());
                labPcrSample.setSamplePostion(les.getSamplePostion());
                labPcrSampleList.add(labPcrSample);
            }

            //扩增板检材显示
            List<Map<String, Object>> tempList = TestProcessUtils.pcrBoardSort(labPcrSampleList);
            modelAndView.addObject("tempList", tempList);

            //PCR拼板
            LabPcrInfo pcr = new LabPcrInfo();
            pcr.setDelegateOrgCode(userOrgId);
            List<LabPcrInfo> pcrlist = labPcrInfoService.selectByBoarsNo(pcr);
            if (ListUtils.isNotNullAndEmptyList(pcrlist)) {
                for (int i = 0; i < pcrlist.size(); i++) {
                    for (int j = i + 1; j < pcrlist.size(); j++) {
                        if (pcrlist.get(i).getBoardNo().equals(pcrlist.get(j).getBoardNo())) {
                            pcrlist.remove(j);
                        }
                    }
                }

            }
            modelAndView.addObject("pcrlist", pcrlist);

            modelAndView.addObject("labPcrSampleList", labPcrSampleList);
            modelAndView.addObject("labPcrSampleListSize", labPcrSampleList.size());
            modelAndView.addObject("operateType", Constants.OPERATE_TYPE_ADD);
            modelAndView.addObject("taskId", taskId);
            modelAndView.addObject("task", taskId);

            //时间
            Map<String, Object> map = goTime(taskId);
            modelAndView.addObject("pcrexperimentDate", map.get("pcrexperimentDate"));
            modelAndView.addObject("extractionExperimentDate", map.get("extractionExperimentDate"));

            modelAndView.setViewName("testProcess/pcrPuzzleExperiment");
            return modelAndView;
        } else {
            return enterExperiment(request, taskId, "2");
        }
    }

    /**
     * 获取扩增拼板数据
     *
     * @param request
     * @return
     */
    @RequestMapping("/pcrPuzzleData")
    @ResponseBody
    public ModelAndView pcrPuzzleData(HttpServletRequest request, String boardNo, String taskId, HttpSession session) {
        ModelAndView modelAndView = InitializationData.initDictList();
        modelAndView.setViewName("testProcess/pcrPuzzleExperiment");
        //当前登录用户
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if (operateUser == null) {
            modelAndView.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return modelAndView;
        }
        //获取当前用户的id
        String userOrgId = operateUser.getOrgId();
        //将当前用户org_id设置进query
        if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
            userOrgId = "110230000000";
        }

        AmPersonalInfo amPersonalInfo = amPersonalInfoService.queryAmPersonalInfo(operateUser.getPersonalId());
        modelAndView.addObject("operateUser", amPersonalInfo.getFullName());

        List<LabPcrInfo> labPcrInfos = labPcrInfoService.selectByTaskId(taskId);
        if (labPcrInfos.size() == 0) {
            List<LabExtractInfo> labExtractInfoList = labExtractInfoService.selectByTaskId(taskId);
            int size = 0;
            if (ListUtils.isNotNullAndEmptyList(labExtractInfoList)) {
                List<LabExtractSample> labExtractSampleList = new ArrayList<>();
                for (int i = 0; i < labExtractInfoList.size(); i++) {
                    LabExtractInfo labExtractInfo = labExtractInfoList.get(i);
                    labExtractSampleList.addAll(labExtractSampleService.selectByExtractId(labExtractInfo.getExtractId()));
                }
                List<LabPcrSample> labPcrSamples = new ArrayList<>();
                for (LabExtractSample les : labExtractSampleList) {
                    LabPcrSample labPcrSample = new LabPcrSample();
                    labPcrSample.setSampleId(les.getSampleId());
                    labPcrSample.setSampleNo(les.getSampleNo());
                    labPcrSample.setSampleName(les.getSampleName());
                    labPcrSample.setSampleType(les.getSampleType());
                    labPcrSample.setSamplePostion(les.getSamplePostion());
                    labPcrSamples.add(labPcrSample);
                }
                modelAndView.addObject("labPcrSampleList", labPcrSamples);
                modelAndView.addObject("labPcrSampleListSize", labPcrSamples.size());
                size = labPcrSamples.size();
            } else {
                List<LimsSampleInfoDna> limsSampleInfoDnaList = (List<LimsSampleInfoDna>) session.getAttribute("limsSampleInfoDnaList");
                List<LabPcrSample> labPcrSampleList = new ArrayList<>();
                for (LimsSampleInfoDna les : limsSampleInfoDnaList) {
                    LabPcrSample labPcrSample = new LabPcrSample();
                    labPcrSample.setSampleId(les.getSampleId());
                    labPcrSample.setSampleNo(les.getSampleNo());
                    labPcrSample.setSampleName(les.getSampleName());
                    labPcrSample.setSampleType(les.getSampleType());
                    labPcrSampleList.add(labPcrSample);
                }
                modelAndView.addObject("labPcrSampleList", labPcrSampleList);
                modelAndView.addObject("labPcrSampleListSize", labPcrSampleList.size());
            }
            modelAndView.addObject("taskId", taskId);
            modelAndView.addObject("operateType", Constants.OPERATE_TYPE_ADD);

            //时间
            Map<String, Object> map = goTime(taskId);
            modelAndView.addObject("pcrexperimentDate", map.get("pcrexperimentDate"));
            modelAndView.addObject("extractionExperimentDate", map.get("extractionExperimentDate"));

            //获取拼板数据
            LabPcrInfo pcr = new LabPcrInfo();
            pcr.setDelegateOrgCode(userOrgId);
            List<LabPcrInfo> labPcrInfoList = labPcrInfoService.selectByBoarsNo(pcr);
            if (ListUtils.isNotNullAndEmptyList(labPcrInfoList)) {
                for (int i = 0; i < labPcrInfoList.size(); i++) {
                    for (int j = i + 1; j < labPcrInfoList.size(); j++) {
                        if (labPcrInfoList.get(i).getBoardNo().equals(labPcrInfoList.get(j).getBoardNo())) {
                            labPcrInfoList.remove(j);
                        }
                    }
                }

            }
            if (labPcrInfoList.size() != 0) {
                for (LabPcrInfo labPcrInfo : labPcrInfoList) {
                    if (labPcrInfo.getBoardNo().equals(boardNo)) {
                        List<LabPcrSample> labPcrSampleList = labPcrSampleService.selectByPcrId(labPcrInfo.getPcrId());

                        //剩余版孔数
                        int i = 88 - labPcrSampleList.size();
                        //如果添加拼板数大于剩余版孔数
                        if (i >= size) {
                            //扩增板检材显示
                            List<Map<String, Object>> tempList = TestProcessUtils.pcrBoardSort(labPcrSampleList);
                            modelAndView.addObject("tempList", tempList);
                        } else {
                            List<Map<String, Object>> tempList = TestProcessUtils.pcrBoardSort(new ArrayList<>());
                            modelAndView.addObject("tempList", tempList);
                            //获取拼板数量
                            modelAndView.addObject("many", "1");
                        }

                        //获取被拼板的taskid
                        String task = labPcrInfo.getTaskId();
                        modelAndView.addObject("task", task);

                        //PCR拼板
                        modelAndView.addObject("pcrlist", labPcrInfoList);
                        modelAndView.addObject("boardNo", boardNo);

                    }
                }
            }

            return modelAndView;

        } else {
            return enterExperiment(request, taskId, "2");
        }
    }

    /**
     * 进入复检扩增拼板实验页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/reinspectionPcrExperiment")
    public ModelAndView reinspectionPcrExperiment(HttpServletRequest request, String taskId, HttpSession session) {
        List<LabPcrInfo> labPcrInfoList = labPcrInfoService.selectByTaskId(taskId);
        if (labPcrInfoList.size() == 0) {
            ModelAndView modelAndView = InitializationData.initDictList();
            List<LimsSampleInfoDna> limsSampleInfoDnaList = (List<LimsSampleInfoDna>) session.getAttribute("limsSampleInfoDnaList");
            List<LabPcrSample> labPcrSampleList = new ArrayList<>();
            for (LimsSampleInfoDna les : limsSampleInfoDnaList) {
                LabPcrSample labPcrSample = new LabPcrSample();
                labPcrSample.setSampleId(les.getSampleId());
                labPcrSample.setSampleNo(les.getSampleNo());
                labPcrSample.setSampleName(les.getSampleName());
                labPcrSample.setSampleType(les.getSampleType());
//                labPcrSample.setSamplePostion(les.get);
                labPcrSampleList.add(labPcrSample);
            }
            //扩增板检材显示
            List<Map<String, Object>> tempList = TestProcessUtils.pcrBoardSort(labPcrSampleList);
            modelAndView.addObject("tempList", tempList);

            //当前登录用户
            Subject subject = SecurityUtils.getSubject();
            LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
            if (operateUser == null) {
                modelAndView.addObject("date", "redirect:/login.html?timeoutFlag=1");
                return modelAndView;
            }

            //获取当前用户的id
            String userOrgId = operateUser.getOrgId();
            //将当前用户org_id设置进query
            if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
                userOrgId = "110230000000";
            }

            AmPersonalInfo amPersonalInfo = amPersonalInfoService.queryAmPersonalInfo(operateUser.getPersonalId());
            modelAndView.addObject("operateUser", amPersonalInfo.getFullName());

            //PCR拼板
            LabPcrInfo pcr = new LabPcrInfo();
            pcr.setDelegateOrgCode(userOrgId);
            List<LabPcrInfo> pcrlist = labPcrInfoService.selectByBoarsNo(pcr);
            if (ListUtils.isNotNullAndEmptyList(pcrlist)) {
                for (int i = 0; i < pcrlist.size(); i++) {
                    for (int j = i + 1; j < pcrlist.size(); j++) {
                        if (pcrlist.get(i).getBoardNo().equals(pcrlist.get(j).getBoardNo())) {
                            pcrlist.remove(j);
                        }
                    }
                }

            }
            modelAndView.addObject("pcrlist", pcrlist);

            modelAndView.addObject("labPcrSampleList", labPcrSampleList);
            modelAndView.addObject("labPcrSampleListSize", labPcrSampleList.size());
            modelAndView.addObject("operateType", Constants.OPERATE_TYPE_ADD);
            modelAndView.addObject("taskId", taskId);
            modelAndView.addObject("task", taskId);

            //时间
            Map<String, Object> map = goTime(taskId);
            modelAndView.addObject("pcrexperimentDate", map.get("pcrexperimentDate"));
            modelAndView.addObject("extractionExperimentDate", map.get("extractionExperimentDate"));

            modelAndView.setViewName("testProcess/pcrPuzzleExperiment");
            return modelAndView;
        } else {
            return enterExperiment(request, taskId, "2");
        }
    }

    /**
     * 进入复检电泳拼板实验页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/reinspectionSyExperiment")
    public ModelAndView reinspectionSyExperiment(HttpServletRequest request, String taskId, HttpSession session) {
        List<LabPcrInfo> labPcrInfoList = labPcrInfoService.selectByTaskId(taskId);
        if (labPcrInfoList.size() == 0) {
            ModelAndView modelAndView = InitializationData.initDictList();
            List<LimsSampleInfoDna> limsSampleInfoDnaList = (List<LimsSampleInfoDna>) session.getAttribute("limsSampleInfoDnaList");
            List<LabSySample> labSySampleList = new ArrayList<>();
            for (LimsSampleInfoDna les : limsSampleInfoDnaList) {
                LabSySample labSySample = new LabSySample();
                labSySample.setSampleId(les.getSampleId());
                labSySample.setSampleNo(les.getSampleNo());
                labSySample.setSampleName(les.getSampleName());
                labSySample.setSampleType(les.getSampleType());
                labSySampleList.add(labSySample);
            }

            //当前登录用户
            Subject subject = SecurityUtils.getSubject();
            LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
            if (operateUser == null) {
                modelAndView.addObject("date", "redirect:/login.html?timeoutFlag=1");
                return modelAndView;
            }

            //获取当前用户的id
            String userOrgId = operateUser.getOrgId();
            //将当前用户org_id设置进query
            if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
                userOrgId = "110230000000";
            }

            AmPersonalInfo amPersonalInfo = amPersonalInfoService.queryAmPersonalInfo(operateUser.getPersonalId());
            modelAndView.addObject("operateUser", amPersonalInfo.getFullName());

            //扩增板检材显示
            List<Map<String, Object>> tempList = TestProcessUtils.syBoardSort(new ArrayList<>());
            modelAndView.addObject("tempList", tempList);

            //PCR拼板
            LabSyInfo pcr = new LabSyInfo();
            pcr.setDelegateOrgCode(userOrgId);
            List<LabSyInfo> syList = labSyInfoService.selectByBoarsNo(pcr);
            if (ListUtils.isNotNullAndEmptyList(syList)) {
                for (int i = 0; i < syList.size(); i++) {
                    for (int j = i + 1; j < syList.size(); j++) {
                        if (syList.get(i).getBoardNo().equals(syList.get(j).getBoardNo())) {
                            syList.remove(j);
                        }
                    }
                }

            }
            modelAndView.addObject("syList", syList);

            modelAndView.addObject("labSySampleList", labSySampleList);
            modelAndView.addObject("labSySampleListSize", labSySampleList.size());
            modelAndView.addObject("operateType", Constants.OPERATE_TYPE_ADD);
            modelAndView.addObject("taskId", taskId);
            modelAndView.addObject("task", taskId);

            //时间
            Map<String, Object> map = goTime(taskId);
            modelAndView.addObject("pcrexperimentDate", map.get("pcrexperimentDate"));
            modelAndView.addObject("extractionExperimentDate", map.get("extractionExperimentDate"));

            modelAndView.setViewName("testProcess/syPuzzleExperiment");
            return modelAndView;
        } else {
            return enterExperiment(request, taskId, "3");
        }
    }

    /**
     * 获取电泳拼板数据
     *
     * @param request
     * @return
     */
    @RequestMapping("/syPuzzleData")
    @ResponseBody
    public ModelAndView syPuzzleData(HttpServletRequest request, String boardNo, String taskId, HttpSession session) {
        ModelAndView modelAndView = InitializationData.initDictList();
        //当前登录用户
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if (operateUser == null) {
            modelAndView.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return modelAndView;
        }
        //获取当前用户的id
        String userOrgId = operateUser.getOrgId();
        //将当前用户org_id设置进query
        if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
            userOrgId = "110230000000";
        }

        AmPersonalInfo amPersonalInfo = amPersonalInfoService.queryAmPersonalInfo(operateUser.getPersonalId());
        modelAndView.addObject("operateUser", amPersonalInfo.getFullName());

        //通过taskid查询
        List<LabSyInfo> labSyInfos = labSyInfoService.selectByTaskId(taskId);
        if (labSyInfos.size() == 0) {
            int size = 0;
            List<LimsSampleInfoDna> limsSampleInfoDnaList = (List<LimsSampleInfoDna>) session.getAttribute("limsSampleInfoDnaList");
            List<LabSySample> labSySampleList = new ArrayList<>();
            for (LimsSampleInfoDna les : limsSampleInfoDnaList) {
                LabSySample labSySample = new LabSySample();
                labSySample.setSampleId(les.getSampleId());
                labSySample.setSampleNo(les.getSampleNo());
                labSySample.setSampleName(les.getSampleName());
                labSySample.setSampleType(les.getSampleType());
                labSySampleList.add(labSySample);
            }
            size = labSySampleList.size();
            modelAndView.addObject("labSySampleList", labSySampleList);
            modelAndView.addObject("labSySampleListSize", labSySampleList.size());
            modelAndView.addObject("taskId", taskId);
            modelAndView.addObject("operateType", Constants.OPERATE_TYPE_ADD);

            //时间
            Map<String, Object> map = goTime(taskId);
            modelAndView.addObject("pcrexperimentDate", map.get("pcrexperimentDate"));
            modelAndView.addObject("extractionExperimentDate", map.get("extractionExperimentDate"));

            //获取拼板数据
            LabSyInfo pcr = new LabSyInfo();
            pcr.setDelegateOrgCode(userOrgId);
            List<LabSyInfo> labSyInfoList = labSyInfoService.selectByBoarsNo(pcr);
            if (ListUtils.isNotNullAndEmptyList(labSyInfoList)) {
                for (int i = 0; i < labSyInfoList.size(); i++) {
                    for (int j = i + 1; j < labSyInfoList.size(); j++) {
                        if (labSyInfoList.get(i).getBoardNo().equals(labSyInfoList.get(j).getBoardNo())) {
                            labSyInfoList.remove(j);
                        }
                    }
                }

            }
            if (labSyInfoList.size() != 0) {
                for (LabSyInfo labSyInfo : labSyInfoList) {
                    if (labSyInfo.getBoardNo().equals(boardNo)) {
                        List<LabSySample> labSySamples = labSySampleService.selectBySyId(labSyInfo.getSyId());
                        //剩余版孔数
                        int i = 88 - labSySamples.size();
                        //如果添加拼板数大于剩余版孔数
                        if (i >= size) {
                            //扩增板检材显示
                            List<Map<String, Object>> tempList = TestProcessUtils.syBoardSort(labSySamples);
                            modelAndView.addObject("tempList", tempList);
                        } else {
                            List<Map<String, Object>> tempList = TestProcessUtils.syBoardSort(new ArrayList<>());
                            modelAndView.addObject("tempList", tempList);
                            //获取拼板数量
                            modelAndView.addObject("many", "1");
                        }

                        //获取被拼板的taskid
                        String task = labSyInfo.getTaskId();
                        modelAndView.addObject("task", task);

                        //PCR拼板
                        modelAndView.addObject("syList", labSyInfoList);
                        modelAndView.addObject("boardNo", boardNo);

                        modelAndView.setViewName("testProcess/syPuzzleExperiment");
                    }
                }
            }

            return modelAndView;

        } else {
            return enterExperiment(request, taskId, "2");
        }
    }


    /**
     * 生成提取实验记录表
     *
     * @param
     * @return
     */
    @RequestMapping("/recordTable")
    @SuppressWarnings("all")
    public void recordTable(HttpServletRequest request, HttpSession session, HttpServletResponse response, String extractId) {
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
        amPersonalInfo.setOrgId(operateUser.getOrgId());
        if (StringUtils.isNotBlank(amPersonalInfo.getOrgId()) && amPersonalInfo.getOrgId().contains("110023")) {
            amPersonalInfo.setOrgId("110230000000");
        }

        //获取Info信息
        LabExtractInfo labExtractInfo = labExtractInfoService.selectByPrimaryKey(extractId);
        labExtractInfo.setExtractDatetime(labExtractInfo.getCreateDatetime());
        params.put("labExtractInfo", labExtractInfo);

        //页眉分局
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

        //获取sample DNA信息
        List<LabExtractSample> labExtractSampleLists = labExtractSampleService.selectByExtractIdAndInfoDna(extractId);

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
            List<LabExtractSample> tweLabExtractSampleList = new ArrayList<>();
            List<LabExtractSample> labExtractSampleList = new ArrayList<>();
            if (bean.getLabExtractSampleList().size() > 14) {
                List<List<LabExtractSample>> listList = new ArrayList<>();
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
//                labExtractSampleList = bean.getLabExtractSampleList().subList(0, 12);
//                params.put("labExtractSampleList", labExtractSampleList);
//                params.put("listList", listList);
            } else {
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
                int longs = 14 - bean.getLabExtractSampleList().size();
                for (int i = 0; i < longs; i++) {
                    bean.getLabExtractSampleList().add(labExtractSample);
                }
                labList.add(bean);
//                params.put("labExtractSampleList", bean);
            }
        }

        params.put("allList", labList);


        /**
         * 常规提取记录--liuchang
         */
        if (StringUtils.isNotBlank(operateUser.getOrgId())) {
            List<QcNoSettings> newQcNoSettings = qcNoSettingsService.selectByLab(operateUser.getOrgId());
            Calendar date = Calendar.getInstance(); //时间插件
            if (newQcNoSettings != null && !newQcNoSettings.isEmpty()) {
                for (QcNoSettings qcNoSettings : newQcNoSettings) {
                    if (qcNoSettings.getDictCode().equals(Constants.ROUTINE_EXTRACTION_RECORDS_NO)) {
                        String routineExtractionNo = qcNoSettings.getLabServerHeader()+"-"+String.valueOf(date.get(Calendar.YEAR))+"-"+qcNoSettings.getBarcode();
                        params.put("routineExtractionNo", StringUtils.isBlank(routineExtractionNo) ? "" : routineExtractionNo);
                    }
                }
            }
        }

        try {
            Configuration cfg = new Configuration();
            cfg.setDefaultEncoding("UTF-8");
            cfg.setClassForTemplateLoading(this.getClass(), "/templates");
            //获取模板
            String ftlName = "cprecordTableAutomatic .ftl";

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

    /**
     * 生成手动取实验记录表
     *
     * @param
     * @return
     */
    @RequestMapping("/recordTableSD")
    @SuppressWarnings("all")
    public void recordTableSD(HttpServletRequest request, HttpSession session, HttpServletResponse response, String routineExtractExtractId,
                              String btnval, String extractDatetimes) {
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

        //获取Info信息
        LabExtractInfo labExtractInfo = labExtractInfoService.selectByPrimaryKey(routineExtractExtractId);
        labExtractInfo.setDeleteFlag(oraliQualification);
        if (StringUtils.isNotBlank(extractDatetimes)) {
            labExtractInfo.setExtractDatetime(DateUtils.stringToDate(extractDatetimes, "yyyy-MM-dd"));
        }else {
            if (labExtractInfo != null) {
                labExtractInfo.setExtractDatetime(labExtractInfo.getExtractDatetime());
            }
        }

        //获取设备
        List<EquipmentNameInfo> equipmentNameInfoArrayList = new ArrayList<>();
        if (labExtractInfo.getEquipment() != null) {
            String[] equipmentNo = labExtractInfo.getEquipment().split(",");
            for (int i = 0; i < equipmentNo.length; i++) {
//                List<EquipmentNameInfo> equipmentNameInfo = equipmentNameInfoService.selectEquipmentNo(equipmentNo[i]);
//                List<EquipmentNameInfo> equipmentNameInfo = equipmentNameInfoService.selectEquipmentTypeId(equipmentNo[i]);
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

        params.put("labExtractInfo", labExtractInfo);

        if (btnval.equals("1")) {
            //常规提取页眉
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

            //获取sample DNA信息
            List<LabExtractSample> labExtractSampleLists = labExtractSampleService.selectByExtractIdAndInfoDna(routineExtractExtractId);
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

/*            if(labExtractSampleLists.size()>12){
                tweLabExtractSampleList = labExtractSampleLists.subList(12,labExtractSampleLists.size());

                int sizePage = (int) Math.ceil((double)tweLabExtractSampleList.size()/ (double)21);
                for (int i = 0; i < sizePage; i++) {
                    int j;
                    if(i < sizePage-1){
                        j = (i + 1) * 21;
                    }else{
                        j = tweLabExtractSampleList.size();
                    }
                    int k = i;
                    if(i > 0 && i < sizePage){
                        k = i * 21;
                    }
                    if(j <= tweLabExtractSampleList.size()){
                        List<LabExtractSample> oneLabExtractSampleList = tweLabExtractSampleList.subList(k,j);
                        listList.add(oneLabExtractSampleList);
                    }

                }

                labExtractSampleList = labExtractSampleLists.subList(0,12);
                params.put("labExtractSampleList", labExtractSampleList);
            }else{
                int longs = 12 - labExtractSampleLists.size();
                for (int i = 0; i < longs; i++) {
                    labExtractSampleLists.add(labExtractSample);
                }
                params.put("labExtractSampleList", labExtractSampleLists);
            }*/

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
                Calendar date = Calendar.getInstance(); //获取年份
                List<QcNoSettings> qcNoSettingsList = qcNoSettingsService.selectByLab(operateUser.getOrgId());
                if (qcNoSettingsList!=null && !qcNoSettingsList.isEmpty()) {
                    for (QcNoSettings qcNoSettings : qcNoSettingsList) {
                        if (qcNoSettings.getDictCode().equals(Constants.ROUTINE_EXTRACTION_RECORDS_NO)) {
                            String routineExtractionNo = qcNoSettings.getLabServerHeader() + "-" + String.valueOf(date.get(Calendar.YEAR)) + "-" + qcNoSettings.getBarcode();
                            params.put("routineExtractionNo", StringUtils.isBlank(routineExtractionNo) ? "" : routineExtractionNo);
                        }
                    }
                }
            }

            try {
                Configuration cfg = new Configuration();
                cfg.setDefaultEncoding("UTF-8");
                cfg.setClassForTemplateLoading(this.getClass(), "/templates");
                //获取模板
                String ftlName = "recordTabletest.ftl";

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
        } else if (btnval.equals("2")) {
            if (StringUtils.isNotBlank(operateUser.getOrgId())) {
                Calendar date = Calendar.getInstance();//时间插件
                List<QcNoSettings> newQcNoSettings = qcNoSettingsService.selectByLab(operateUser.getOrgId());
                if (newQcNoSettings != null && !newQcNoSettings.isEmpty()) {
                    for (QcNoSettings qcNoSettings : newQcNoSettings) {
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
            //获取sample DNA信息
            List<LabExtractSample> labExtractSampleLists = labExtractSampleService.selectByExtractIdAndInfoDna(routineExtractExtractId);

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

                    /*for (int j = 0; j < purificationMethodList.size(); j++) {
                        if (purificationMethodList.get(j).getDictCode().equals(extractStringS[10])) {
                            labExtractSampleLists.get(i).setPurification(purificationMethodList.get(j).getDictName());
                        }
                    }*/
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
        } else if (btnval.equals("3")) {
            //页眉
            if (labExtractInfo != null) {
                if (StringUtils.isNotEmpty(labExtractInfo.getOrgId())) {
                    ExperimentalParameter exp = new ExperimentalParameter();
                    exp.setOrgId(labExtractInfo.getOrgId());
                    exp.setExperimentalType("SPECIAL");//特殊提取
                    List<ExperimentalParameter> experimentalParameters = experimentalParameterService.selectList(exp);
                    if (ListUtils.isNotNullAndEmptyList(experimentalParameters)) {
                        ExperimentalParameter experimentalParameter = experimentalParameters.get(0);
                        String orgNameSp = experimentalParameter.getParameterName();
                        params.put("orgNameSp", StringUtils.isBlank(orgNameSp) ? "" : orgNameSp);
                    }
                }
            }
            //获取sample DNA信息
            List<LabExtractSample> labExtractSampleLists = labExtractSampleService.selectByExtractIdAndInfoDna(routineExtractExtractId);
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

            if (labExtractSampleLists.size() > 24) {
                tweLabExtractSampleList = labExtractSampleLists.subList(24, labExtractSampleLists.size());

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
                        List<LabExtractSample> oneLabExtractSampleList = tweLabExtractSampleList.subList(k, j);
                        listList.add(oneLabExtractSampleList);
                    }

                }

                labExtractSampleList = labExtractSampleLists.subList(0, 24);
                params.put("labExtractSampleList", labExtractSampleList);
            } else {
                int longs = 24 - labExtractSampleLists.size();
                for (int i = 0; i < longs; i++) {
                    labExtractSampleLists.add(labExtractSample);
                }
                params.put("labExtractSampleList", labExtractSampleLists);
            }
            params.put("listList", listList);


            try {
                Configuration cfg = new Configuration();
                cfg.setDefaultEncoding("UTF-8");
                cfg.setClassForTemplateLoading(this.getClass(), "/templates");
                //获取模板
                String ftlName = "teshu.ftl";

                Template tmp = cfg.getTemplate(ftlName, "UTF-8");

                String filename = "DNA特殊提取记录表" + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".doc";

                response.setCharacterEncoding("UTF-8");
                //文件头，导出的文件名
                response.setHeader("Content-disposition", "attachment;filename=" + new String(filename.getBytes("GBK"), "ISO-8859-1"));
                //类型
                response.setContentType("application/x-msdownload");
                tmp.process(params, response.getWriter());
            } catch (Exception ex) {
                logger.error("下载错误！", ex);
            }
        } else if (btnval.equals("4")) {
            //页眉
            if (labExtractInfo != null) {
                if (StringUtils.isNotEmpty(labExtractInfo.getOrgId())) {
                    ExperimentalParameter exp = new ExperimentalParameter();
                    exp.setOrgId(labExtractInfo.getOrgId());
                    exp.setExperimentalType("OTHER");//其他提取
                    List<ExperimentalParameter> experimentalParameters = experimentalParameterService.selectList(exp);
                    if (ListUtils.isNotNullAndEmptyList(experimentalParameters)) {
                        ExperimentalParameter experimentalParameter = experimentalParameters.get(0);
                        String orgNameSp = experimentalParameter.getParameterName();
                        params.put("orgNameSp", StringUtils.isBlank(orgNameSp) ? "" : orgNameSp);
                    }
                }
            }
            //获取sample DNA信息
            List<LabExtractSample> labExtractSampleLists = labExtractSampleService.selectByExtractIdAndInfoDna(routineExtractExtractId);
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

            if (labExtractSampleLists.size() > 26) {
                tweLabExtractSampleList = labExtractSampleLists.subList(26, labExtractSampleLists.size());

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
                        List<LabExtractSample> oneLabExtractSampleList = tweLabExtractSampleList.subList(k, j);
                        listList.add(oneLabExtractSampleList);
                    }

                }

                labExtractSampleList = labExtractSampleLists.subList(0, 26);
                params.put("labExtractSampleList", labExtractSampleList);
            } else {
                int longs = 26 - labExtractSampleLists.size();
                for (int i = 0; i < longs; i++) {
                    labExtractSampleLists.add(labExtractSample);
                }
                params.put("labExtractSampleList", labExtractSampleLists);
            }
            params.put("listList", listList);


            try {
                Configuration cfg = new Configuration();
                cfg.setDefaultEncoding("UTF-8");
                cfg.setClassForTemplateLoading(this.getClass(), "/templates");
                //获取模板
                String ftlName = "qita.ftl";

                Template tmp = cfg.getTemplate(ftlName, "UTF-8");

                String filename = "DNA其他提取记录表" + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".doc";

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
     * 生成扩增实验记录表
     *
     * @param
     * @return
     */
    @RequestMapping("/pcrRecordBook")
    public void pcrRecordBook(HttpServletRequest request, HttpSession session, HttpServletResponse response, String pcrId) {
        downloadFileUtils.pcrRecordBook(request, session, response, pcrId);
    }

    /**
     * 生成上样实验记录表
     *
     * @param
     * @return
     */
    @RequestMapping("/syRecordBook")
    public void syRecordBook(HttpServletRequest request, HttpSession session, HttpServletResponse response, String syId, String taskId) {
        Map<String, Object> pcrmap = new HashMap<String, Object>();
        LabSyInfo labSyInfo = labSyInfoService.selectByPrimaryKey(syId);
        pcrmap.put("labSyInfoRecord", labSyInfo);
        List<LabSySample> labSySamplelist = labSySampleService.selectBySyId(labSyInfo.getSyId());

        //页眉
        if (labSyInfo != null) {
            if (StringUtils.isNotEmpty(labSyInfo.getOrgId())) {
                ExperimentalParameter exp = new ExperimentalParameter();
                exp.setOrgId(labSyInfo.getOrgId());
                exp.setExperimentalType("SY_RECORD");//上样记录
                List<ExperimentalParameter> experimentalParameters = experimentalParameterService.selectList(exp);
                if (ListUtils.isNotNullAndEmptyList(experimentalParameters)) {
                    ExperimentalParameter experimentalParameter = experimentalParameters.get(0);
                    String orgNameSp = experimentalParameter.getParameterName();
                    pcrmap.put("orgNameSp", StringUtils.isBlank(orgNameSp) ? "" : orgNameSp);
                }
            }

            if (StringUtils.isNotBlank(labSyInfo.getSyString())) {
                Map<String, Object> mapSyString = (Map) JSON.parse(labSyInfo.getSyString());

                labSyInfo.setInternalStandard(mapSyString.get("neiBiao").toString());
                String quantity = mapSyString.get("quantity").toString();
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

        LinkedHashSet<List<LabSySample>> allList = new LinkedHashSet<List<LabSySample>>();
        if (null != labSySamplelist) {
            for (LabSySample bean : labSySamplelist) {
                List<LabSySample> greplist = new ArrayList<LabSySample>();
                for (LabSySample bean2 : labSySamplelist) {
                    if (bean.getCaseId().equals(bean2.getCaseId())) {
                        greplist.add(bean2);
                    }
                }
                allList.add(greplist);
            }
        }

        List<SyLiistModel> syLiistModels = new ArrayList<>();
        if(allList.size()>0 && null != allList){
            for (List<LabSySample> samples : allList){
                SyLiistModel model = new SyLiistModel();
                model.setLabSySampleList(samples);
                syLiistModels.add(model);
            }
        }

        List<SyLiistModel> labList = new ArrayList<>();
        if (ListUtils.isNotNullAndEmptyList(syLiistModels)){
            for (SyLiistModel bean : syLiistModels) {
                //将数据分页
                List<LabSySample> tweLabSampleList;
//                List<LabSySample> labSampleList = new ArrayList<>();
                if (bean.getLabSySampleList().size() > 15) {
                    List<List<LabSySample>> listList = new ArrayList<>();
                    tweLabSampleList = bean.getLabSySampleList().subList(15, bean.getLabSySampleList().size());
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
                            List<LabSySample> oneLabExtractSampleList = new ArrayList<>();
                            oneLabExtractSampleList.addAll(tweLabSampleList.subList(k, j));
                            if (ListUtils.isNotNullAndEmptyList(oneLabExtractSampleList)){
                                if (oneLabExtractSampleList.size()<19){
                                    LabSySample labSySample = new LabSySample();
                                    labSySample.setSamplePostion("  ");
                                    labSySample.setSampleName("  ");
                                    labSySample.setSampleNo("  ");
                                    int size = 19 - oneLabExtractSampleList.size();
                                    for (int x = 0; x <  size ; x++) {
                                        oneLabExtractSampleList.add(labSySample);
                                    }
                                }
                            }
                            listList.add(oneLabExtractSampleList);
                            bean.setListList(listList);
                        }

                    }
                    bean.setLabSySampleList(bean.getLabSySampleList().subList(0, 15));
                    labList.add(bean);
//                    labSampleList = bean.getLabSySampleList().subList(0, 15);
//                    pcrmap.put("SySamplesList", listList);
//                    pcrmap.put("sySampleListRecord", labSampleList);
                } else {
                    LabSySample labSySample = new LabSySample();
                    labSySample.setSamplePostion("  ");
                    labSySample.setSampleName("  ");
                    labSySample.setSampleNo("  ");
//                    labSampleList.addAll(bean.getLabSySampleList());
                    int size = 15 - bean.getLabSySampleList().size();
                    for (int i = 0; i < size; i++) {
                        bean.getLabSySampleList().add(labSySample);
                    }
                    labList.add(bean);
//                    pcrmap.put("sySampleListRecord", bean);
                }
            }
        }

        pcrmap.put("allList", labList);

        /**
         * 电泳记录表--liuchang
         */
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo user=(LoaUserInfo) subject.getPrincipal();
        Calendar date = Calendar.getInstance(); //获取年份
        if (StringUtils.isNotBlank(user.getOrgId())) {
            List<QcNoSettings> newQcNoSettings = qcNoSettingsService.selectByLab(user.getOrgId());
            if (newQcNoSettings !=null && !newQcNoSettings.isEmpty()) {
                for (QcNoSettings qcNoSettings : newQcNoSettings) {
                    if (qcNoSettings.getDictCode().equals(Constants.ELECTROPHORETIC_RECORDING_NO)){ //电泳记录
                       String electrophoreticRecordingNo = qcNoSettings.getLabServerHeader() + "-" + String.valueOf(date.get(Calendar.YEAR)) +"-"+ qcNoSettings.getBarcode();
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
            String ftlName = "syRecordBook2.ftl";

            Template tmp = cfg.getTemplate(ftlName, "UTF-8");

            String filename = "-DNA上样记录表" + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".doc";

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

    /**
     * 检验过程时间
     *
     * @param taskId
     * @return
     */
    public Map<String, Object> goTime(String taskId) {

        Map<String, Object> map = new HashMap<>();
        //提取时间
        List<LabExtractInfo> labExtractInfoList = labExtractInfoService.selectByTaskId(taskId);
        if (labExtractInfoList.size() != 0 && labExtractInfoList.get(0).getExtractDatetime() != null) {
            map.put("extractionExperimentDate", DateUtils.dateToString(labExtractInfoList.get(0).getExtractDatetime(), "yyyy-MM-dd HH:mm"));
        } else {
            map.put("extractionExperimentDate", DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm"));
        }
        //扩增时间
        List<LabPcrInfo> labPcrInfoList = labPcrInfoService.selectByTaskId(taskId);
        if (labPcrInfoList.size() != 0 && labPcrInfoList.get(0).getPcrEndDatetime() != null) {
            map.put("pcrexperimentDate", DateUtils.dateToString(labPcrInfoList.get(0).getPcrEndDatetime(), "yyyy-MM-dd HH:mm"));
        } else {
            map.put("pcrexperimentDate", DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm"));
        }
        //电泳时间
        List<LabSyInfo> labSyInfoList = labSyInfoService.selectByTaskId(taskId);
        if (labSyInfoList.size() != 0 && labSyInfoList.get(0).getSyEndDatetime() != null) {
            map.put("syExperimentDate", DateUtils.dateToString(labSyInfoList.get(0).getSyEndDatetime(), "yyyy-MM-dd HH:mm"));
        } else {
            map.put("syExperimentDate", DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm"));
        }
        return map;
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

        if (StringUtils.isBlank(query.getEntity().getReviewCount())) {
            query.getEntity().setReviewCount(null);
        } else {
            query.getEntity().setReviewCount(query.getEntity().getReviewCount().trim());
        }

        return query;
    }

    /**
     * 电泳实验记录表
     *
     * @param
     * @return
     */
    @RequestMapping("/enterExperimentFtl")
    public void enterExperimentFtl(HttpServletRequest request, HttpSession session, HttpServletResponse response, String taskId) {
        Map<String, Object> params = new HashMap<String, Object>();
        DictItem dictItem = new DictItem();

        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if (operateUser == null) {
        }
        AmPersonalInfo amPersonalInfo = amPersonalInfoService.queryAmPersonalInfo(operateUser.getPersonalId());
        amPersonalInfo.setOrgId(operateUser.getOrgId());
        if (StringUtils.isNotBlank(amPersonalInfo.getOrgId()) && amPersonalInfo.getOrgId().contains("110023")) {
            amPersonalInfo.setOrgId("110230000000");
        }

        List<LabSyInfo> labSyInfoList = labSyInfoService.selectByTaskId(taskId);

        List<LabSySample> labSySampleVoList = null;
        if (ListUtils.isNotNullAndEmptyList(labSyInfoList)) {

            if (StringUtils.isNotBlank(labSyInfoList.get(0).getSyString())) {
                Map<String, Object> mapSyString = (Map) JSON.parse(labSyInfoList.get(0).getSyString());

                labSyInfoList.get(0).setInternalStandard(mapSyString.get("neiBiao").toString());
                String quantity = mapSyString.get("quantity").toString();
                //甲酰胺
                String jiaXianAnBatchCode = mapSyString.get("jiaXianAnBatchCode").toString();
                String jiaXianAnInstockDate = mapSyString.get("jiaXianAnInstockDate").toString();

                params.put("jiaXianAnBatchCode", jiaXianAnBatchCode);
                params.put("jiaXianAnInstockDate", jiaXianAnInstockDate);
                //内标
                String neiBiaoBatchCode = mapSyString.get("neiBiaoBatchCode").toString();
                String neiBiaoInstockDate = mapSyString.get("neiBiaoInstockDate").toString();

                params.put("neiBiaoBatchCode", neiBiaoBatchCode);
                params.put("neiBiaoInstockDate", neiBiaoInstockDate);
                //毛细管
                String maoXiGuanBatchCode = mapSyString.get("maoXiGuanBatchCode").toString();
                String maoXiGuanInstockDate = mapSyString.get("maoXiGuanInstockDate").toString();

                params.put("maoXiGuanBatchCode", maoXiGuanBatchCode);
                params.put("maoXiGuanInstockDate", maoXiGuanInstockDate);
                //胶液
                String jiaoYeBatchCode = mapSyString.get("jiaoYeBatchCode").toString();
                String jiaoYeInstockDate = mapSyString.get("jiaoYeInstockDate").toString();

                params.put("jiaoYeBatchCode", jiaoYeBatchCode);
                params.put("jiaoYeInstockDate", jiaoYeInstockDate);

                double quantityCount = 0;
                if (StringUtils.isNotBlank(quantity)) {
                    quantityCount = Double.valueOf(quantity);
                }
                double formamide = 0;
                if (StringUtils.isNotBlank(labSyInfoList.get(0).getFormamide())) {
                    formamide = Double.valueOf(labSyInfoList.get(0).getFormamide());
                }
                double internalStandardUl = 0;
                if (StringUtils.isNotBlank(labSyInfoList.get(0).getInternalStandardUl())) {
                    internalStandardUl = Double.valueOf(labSyInfoList.get(0).getInternalStandardUl());
                }

                params.put("formamideCount", quantityCount * formamide);
                params.put("internalCount", quantityCount * internalStandardUl);

            }
//            labSySampleVoList = labSySampleService.selectBySyId(labSyInfoList.get(0).getSyId());
        }

        //进入提取实验页面
        List<LabExtractInfo> labExtractInfoList = labExtractInfoService.selectByTaskId(taskId);
        List<LabExtractSample> labExtractSampleList = null;
        if (ListUtils.isNotNullAndEmptyList(labExtractInfoList)) {
            LabExtractInfo labExtractInfo = labExtractInfoList.get(0);
            params.put("labSyInfoRecord",  labExtractInfo);

            labExtractSampleList = labExtractSampleService.selectByExtractId(labExtractInfo.getExtractId());
            for (int i = 0; i < labExtractSampleList.size(); i++) {
                if ("A01".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("ASampleNo1", labExtractSampleList.get(i).getSampleNo());

                } else if ("A02".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("ASampleNo2", labExtractSampleList.get(i).getSampleNo());

                } else if ("A03".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("ASampleNo3", labExtractSampleList.get(i).getSampleNo());

                } else if ("A04".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("ASampleNo4", labExtractSampleList.get(i).getSampleNo());

                } else if ("A05".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("ASampleNo5", labExtractSampleList.get(i).getSampleNo());

                } else if ("A06".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("ASampleNo6", labExtractSampleList.get(i).getSampleNo());

                } else if ("A07".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("ASampleNo7", labExtractSampleList.get(i).getSampleNo());

                } else if ("A08".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("ASampleNo8", labExtractSampleList.get(i).getSampleNo());

                } else if ("A09".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("ASampleNo9", labExtractSampleList.get(i).getSampleNo());

                } else if ("A10".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("ASampleNo10", labExtractSampleList.get(i).getSampleNo());

                } else if ("A11".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("ASampleNo11", labExtractSampleList.get(i).getSampleNo());

                } else if ("A12".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("ASampleNo12", labExtractSampleList.get(i).getSampleNo());

                } else if ("B01".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("BSampleNo1", labExtractSampleList.get(i).getSampleNo());

                } else if ("B02".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("BSampleNo2", labExtractSampleList.get(i).getSampleNo());

                } else if ("B03".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("BSampleNo3", labExtractSampleList.get(i).getSampleNo());

                } else if ("B04".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("BSampleNo4", labExtractSampleList.get(i).getSampleNo());

                } else if ("B05".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("BSampleNo5", labExtractSampleList.get(i).getSampleNo());

                } else if ("B06".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("BSampleNo6", labExtractSampleList.get(i).getSampleNo());

                } else if ("B07".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("BSampleNo7", labExtractSampleList.get(i).getSampleNo());

                } else if ("B08".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("BSampleNo8", labExtractSampleList.get(i).getSampleNo());

                } else if ("B09".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("BSampleNo9", labExtractSampleList.get(i).getSampleNo());

                } else if ("B10".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("BSampleNo10", labExtractSampleList.get(i).getSampleNo());

                } else if ("B11".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("BSampleNo11", labExtractSampleList.get(i).getSampleNo());

                } else if ("B12".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("BSampleNo12", labExtractSampleList.get(i).getSampleNo());

                } else if ("C01".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("CSampleNo1", labExtractSampleList.get(i).getSampleNo());

                } else if ("C02".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("CSampleNo2", labExtractSampleList.get(i).getSampleNo());

                } else if ("C03".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("CSampleNo3", labExtractSampleList.get(i).getSampleNo());

                } else if ("C04".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("CSampleNo4", labExtractSampleList.get(i).getSampleNo());

                } else if ("C05".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("CSampleNo5", labExtractSampleList.get(i).getSampleNo());

                } else if ("C06".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("CSampleNo6", labExtractSampleList.get(i).getSampleNo());

                } else if ("C07".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("CSampleNo7", labExtractSampleList.get(i).getSampleNo());

                } else if ("C08".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("CSampleNo8", labExtractSampleList.get(i).getSampleNo());

                } else if ("C09".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("CSampleNo9", labExtractSampleList.get(i).getSampleNo());

                } else if ("C10".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("CSampleNo10", labExtractSampleList.get(i).getSampleNo());

                } else if ("C11".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("CSampleNo11", labExtractSampleList.get(i).getSampleNo());

                } else if ("C12".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("CSampleNo12", labExtractSampleList.get(i).getSampleNo());

                } else if ("D01".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("DSampleNo1", labExtractSampleList.get(i).getSampleNo());

                } else if ("D02".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("DSampleNo2", labExtractSampleList.get(i).getSampleNo());

                } else if ("D03".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("DSampleNo3", labExtractSampleList.get(i).getSampleNo());

                } else if ("D04".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("DSampleNo4", labExtractSampleList.get(i).getSampleNo());

                } else if ("D05".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("DSampleNo5", labExtractSampleList.get(i).getSampleNo());

                } else if ("D06".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("DSampleNo6", labExtractSampleList.get(i).getSampleNo());

                } else if ("D07".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("DSampleNo7", labExtractSampleList.get(i).getSampleNo());

                } else if ("D08".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("DSampleNo8", labExtractSampleList.get(i).getSampleNo());

                } else if ("D09".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("DSampleNo9", labExtractSampleList.get(i).getSampleNo());

                } else if ("D10".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("DSampleNo10", labExtractSampleList.get(i).getSampleNo());

                } else if ("D11".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("DSampleNo11", labExtractSampleList.get(i).getSampleNo());

                } else if ("D12".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("DSampleNo12", labExtractSampleList.get(i).getSampleNo());

                } else if ("E01".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("ESampleNo1", labExtractSampleList.get(i).getSampleNo());

                } else if ("E02".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("ESampleNo2", labExtractSampleList.get(i).getSampleNo());

                } else if ("E03".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("ESampleNo3", labExtractSampleList.get(i).getSampleNo());

                } else if ("E04".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("ESampleNo4", labExtractSampleList.get(i).getSampleNo());

                } else if ("E05".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("ESampleNo5", labExtractSampleList.get(i).getSampleNo());

                } else if ("E06".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("ESampleNo6", labExtractSampleList.get(i).getSampleNo());

                } else if ("E07".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("ESampleNo7", labExtractSampleList.get(i).getSampleNo());

                } else if ("E08".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("ESampleNo8", labExtractSampleList.get(i).getSampleNo());

                } else if ("E09".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("ESampleNo9", labExtractSampleList.get(i).getSampleNo());

                } else if ("E10".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("ESampleNo10", labExtractSampleList.get(i).getSampleNo());

                } else if ("E11".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("ESampleNo11", labExtractSampleList.get(i).getSampleNo());

                } else if ("E12".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("ESampleNo12", labExtractSampleList.get(i).getSampleNo());

                } else if ("F01".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("FSampleNo1", labExtractSampleList.get(i).getSampleNo());

                } else if ("F02".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("FSampleNo2", labExtractSampleList.get(i).getSampleNo());

                } else if ("F03".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("FSampleNo3", labExtractSampleList.get(i).getSampleNo());

                } else if ("F04".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("FSampleNo4", labExtractSampleList.get(i).getSampleNo());

                } else if ("F05".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("FSampleNo5", labExtractSampleList.get(i).getSampleNo());

                } else if ("F06".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("FSampleNo6", labExtractSampleList.get(i).getSampleNo());

                } else if ("F07".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("FSampleNo7", labExtractSampleList.get(i).getSampleNo());

                } else if ("F08".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("FSampleNo8", labExtractSampleList.get(i).getSampleNo());

                } else if ("F09".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("FSampleNo9", labExtractSampleList.get(i).getSampleNo());

                } else if ("F10".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("FSampleNo10", labExtractSampleList.get(i).getSampleNo());

                } else if ("F11".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("FSampleNo11", labExtractSampleList.get(i).getSampleNo());

                } else if ("F12".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("FSampleNo12", labExtractSampleList.get(i).getSampleNo());

                } else if ("G01".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("GSampleNo1", labExtractSampleList.get(i).getSampleNo());

                } else if ("G02".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("GSampleNo2", labExtractSampleList.get(i).getSampleNo());

                } else if ("G03".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("GSampleNo3", labExtractSampleList.get(i).getSampleNo());

                } else if ("G04".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("GSampleNo4", labExtractSampleList.get(i).getSampleNo());

                } else if ("G05".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("GSampleNo5", labExtractSampleList.get(i).getSampleNo());

                } else if ("G06".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("GSampleNo6", labExtractSampleList.get(i).getSampleNo());

                } else if ("G07".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("GSampleNo7", labExtractSampleList.get(i).getSampleNo());

                } else if ("G08".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("GSampleNo8", labExtractSampleList.get(i).getSampleNo());

                } else if ("G09".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("GSampleNo9", labExtractSampleList.get(i).getSampleNo());

                } else if ("G10".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("GSampleNo10", labExtractSampleList.get(i).getSampleNo());

                } else if ("G11".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("GSampleNo11", labExtractSampleList.get(i).getSampleNo());

                } else if ("G12".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("GSampleNo12", labExtractSampleList.get(i).getSampleNo());

                } else if ("H01".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("HSampleNo1", labExtractSampleList.get(i).getSampleNo());

                } else if ("H02".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("HSampleNo2", labExtractSampleList.get(i).getSampleNo());

                } else if ("H03".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("HSampleNo3", labExtractSampleList.get(i).getSampleNo());

                } else if ("H04".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("HSampleNo4", labExtractSampleList.get(i).getSampleNo());

                } else if ("H05".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("HSampleNo5", labExtractSampleList.get(i).getSampleNo());

                } else if ("H06".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("HSampleNo6", labExtractSampleList.get(i).getSampleNo());

                } else if ("H07".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("HSampleNo7", labExtractSampleList.get(i).getSampleNo());

                } else if ("H08".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("HSampleNo8", labExtractSampleList.get(i).getSampleNo());

                } else if ("H09".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("HSampleNo9", labExtractSampleList.get(i).getSampleNo());

                } else if ("H10".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("HSampleNo10", labExtractSampleList.get(i).getSampleNo());

                } else if ("H11".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("HSampleNo11", labExtractSampleList.get(i).getSampleNo());

                } else if ("H12".equals(labExtractSampleList.get(i).getSamplePostion())) {
                    params.put("HSampleNo12", labExtractSampleList.get(i).getSampleNo());

                }

            }
        }

        /**
         * 自动提取页面，生成电泳分离记录--liuchang
         */
        LoaUserInfo user=(LoaUserInfo) subject.getPrincipal();
        Calendar date = Calendar.getInstance(); //获取年份
        if (StringUtils.isNotBlank(user.getOrgId())) {
            List<QcNoSettings> newQcNoSettings = qcNoSettingsService.selectByLab(user.getOrgId());
            if (newQcNoSettings !=null &&! newQcNoSettings.isEmpty()) {
                for (QcNoSettings qcNoSettings : newQcNoSettings) {
                    if (qcNoSettings.getDictCode().equals(Constants.ELECTROPHORETIC_AUTOMATIC_NO)){ //自动提取页面，生成电泳分离记录
                        String electrophoretic = qcNoSettings.getLabServerHeader() + "-" + String.valueOf(date.get(Calendar.YEAR))+"-"+qcNoSettings.getBarcode();
                        params.put("electrophoretic_automatic_no", StringUtils.isBlank(electrophoretic) ? "" : electrophoretic);
                    }
                }
            }
        }
                try {
                    Configuration cfg = new Configuration();
                    cfg.setDefaultEncoding("UTF-8");
                    cfg.setClassForTemplateLoading(this.getClass(), "/templates");
                    //获取模板
                    String ftlName = "enterExperiment.ftl";

                    Template tmp = cfg.getTemplate(ftlName, "UTF-8");

                    String filename = "电泳分离记录单" + DateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + ".doc";

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
