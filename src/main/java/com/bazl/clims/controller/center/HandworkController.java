package com.bazl.clims.controller.center;

import com.alibaba.fastjson.JSON;
import com.bazl.clims.common.Constants;
import com.bazl.clims.controller.BaseController;
import com.bazl.clims.datamodel.PcrRecordDataModel;
import com.bazl.clims.datamodel.SyRecordDataModel;
import com.bazl.clims.model.LoaUserInfo;
import com.bazl.clims.model.PageInfo;
import com.bazl.clims.model.po.*;
import com.bazl.clims.model.vo.LabExtractInfoVo;
import com.bazl.clims.model.vo.LabPcrInfoVo;
import com.bazl.clims.model.vo.LabSyInfoVo;
import com.bazl.clims.service.*;
import com.bazl.clims.service.impl.LabExtractInfoServiceImpl;
import com.bazl.clims.utils.DateUtils;
import com.bazl.clims.utils.InitializationData;
import com.bazl.clims.utils.ListUtils;
import com.bazl.clims.utils.TestProcessUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.*;

import static com.bazl.clims.utils.DateUtils.addDays;

/**
 * Created by Administrator on 2019/5/9.
 *  手工提取控制层
 */

@Controller
@RequestMapping("/handwork")
public class HandworkController extends BaseController {

    @Autowired
    LabPcrInfoService labPcrInfoService;
    @Autowired
    LabPcrSampleService labPcrSampleService;
    @Autowired
    LimsSampleInfoDnaService limsSampleInfoDnaService;
    @Autowired
    LabExtractInfoServiceImpl labExtractInfoService;
    @Autowired
    LabExtractSampleService labExtractSampleService;
    @Autowired
    OrgInfoService orgInfoService;
    @Autowired
    EquipmentInfoService equipmentInfoService;
    @Autowired
    EquipmentNameInfoService equipmentNameInfoService;
    @Autowired
    ReagentInfoService reagentInfoService;
    @Autowired
    SeqNoGenerateService seqNoGenerateService;
    @Autowired
    AmPersonalInfoService amPersonalInfoService;
    @Autowired
    LabSyInfoService labSyInfoService;
    @Autowired
    LabSySampleService labSySampleService;
    @Autowired
    ExperimentalParameterService experimentalParameterService;
    @Autowired
    ReagentOutgoInfoService reagentOutgoInfoService;
    @Autowired
    UseInstrumentsService useInstrumentsService;
    @Autowired
    EquipmentTypeInfoService equipmentTypeInfoService;



    //手工提取任务页面
    @RequestMapping("/manualExtraction")
    public ModelAndView manualExtraction(HttpServletRequest request, LabExtractInfoVo labExtractInfoVo, String createDatetime, PageInfo pageInfo) throws ParseException {
        ModelAndView view = new ModelAndView();

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if(operateUser == null){
            view.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return  view;
        }

        //设置提取人的所属单位为当前登录人单位
        //获取当前用户的id
        String userOrgId = operateUser.getOrgId();
        //将当前用户org_id设置进query
        if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
            userOrgId = "110230000000";
        }
        labExtractInfoVo.getEntity().setOrgId(userOrgId);

        //获取所属单位名称
        List<OrgInfo> orgInfos = orgInfoService.selectAll();
        view.addObject("orgInfo", orgInfos);

        if (null != labExtractInfoVo.getAcceptEndDatetime()){//解决时间无时分秒造成的00:00:00查询
            labExtractInfoVo.setAcceptEndDatetime(addDays(labExtractInfoVo.getAcceptEndDatetime(),1));//增加一天
        }
        List<LabExtractInfoVo> labExtractInfoVoList = labExtractInfoService.selectVoList(labExtractInfoVo, pageInfo);
        int totalCount = labExtractInfoService.selectVOCount(labExtractInfoVo);
        if (null != labExtractInfoVo.getAcceptEndDatetime()){//解决时间无时分秒造成的00:00:00查询
            labExtractInfoVo.setAcceptEndDatetime(addDays(labExtractInfoVo.getAcceptEndDatetime(),-1));//减一天
        }
        view.addObject("labExtractInfoVos", labExtractInfoVoList);
        view.addObject("query", labExtractInfoVo);
        view.addObject("pageInfo", pageInfo.updatePageInfo(totalCount));
        view.setViewName("manualExtraction/manualExtraction");

        return view;
    }

    /**
     * 根据提取任务id查询检材实验信息
     * @param request
     * @param extractId
     * @return
     */
    @RequestMapping("/enterManualExtraction")
    public ModelAndView enterManualExtraction(HttpServletRequest request, String extractId) {
        ModelAndView modelAndView = InitializationData.initDictList();

         //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if(operateUser == null){
            modelAndView.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return  modelAndView;
        }
        String userOrgId = operateUser.getOrgId();
        //将当前用户org_id设置进query
        if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
            operateUser.setOrgId("110230000000");
        }
        LabExtractInfo labExtractInfo = labExtractInfoService.selectByPrimaryKey(extractId);
        if (labExtractInfo != null) {

           //设备选择
//            EquipmentNameInfo equipmentNameInfo = new EquipmentNameInfo();
//            equipmentNameInfo.setPhase(Constants.EXPERIMENTAL_STAGE_01);//阶段
//            equipmentNameInfo.setOrgId(operateUser.getOrgId());
//            equipmentNameInfo.setEquipmentNo("SB");
//            List<EquipmentNameInfo> equipmentNameInfoList = equipmentNameInfoService.selectEquipmentNameInfoListByTypeNo(equipmentNameInfo);
            EquipmentTypeInfo equipmentTypeInfo = new EquipmentTypeInfo();
            equipmentTypeInfo.setExperimentalStage(Constants.EXPERIMENTAL_STAGE_01);
            equipmentTypeInfo.setOrgId(operateUser.getOrgId());

            List<EquipmentTypeInfo> equipmentTypeInfoList = equipmentTypeInfoService.selectLabEquipmentTypeList(equipmentTypeInfo);
            List<EquipmentNameInfo> equipmentNameInfoList = new ArrayList<>();
            if(equipmentTypeInfoList.size()>0){
                equipmentNameInfoList = equipmentNameInfoService.selectEquipmentTypeId(equipmentTypeInfoList.get(0).getId());
            }

            //试剂信息
            ReagentInfo reagentInfo = new ReagentInfo();
            reagentInfo.setExperimentalStage(Constants.EXPERIMENTAL_STAGE_01);
            reagentInfo.setOrgId(operateUser.getOrgId());
            List<ReagentInfo> reagentInfoList = reagentInfoService.selectList(reagentInfo);
            if (ListUtils.isNotNullAndEmptyList(reagentInfoList)){
                for(int i=0;i< reagentInfoList.size();i++){
                    ReagentOutgoInfo reagentOutgoInfo = new ReagentOutgoInfo();
                    reagentOutgoInfo.setReagentId(reagentInfoList.get(i).getId());
                    reagentOutgoInfo.setOrgId(reagentInfoList.get(i).getOrgId());
                    List<ReagentOutgoInfo> reagentOutgoInfoList = reagentOutgoInfoService.selectreagentOutgoList(reagentOutgoInfo);
                    if(reagentOutgoInfoList.size() >0){
                        reagentInfoList.get(i).setBatchNumber(reagentOutgoInfoList.get(0).getBatchNumber());
                        reagentInfoList.get(i).setValidityTime(reagentOutgoInfoList.get(0).getEffectiveDatetime());
                    }
                }
            }

            List<LabExtractSample> labExtractSampleList = labExtractSampleService.selectByExtractId(labExtractInfo.getExtractId());
            if(labExtractSampleList.size() > 0){
                for(int j=0;j< labExtractSampleList.size();j++){
                    if(StringUtils.isNotBlank(labExtractSampleList.get(j).getExtractMethod())){
                        UseInstruments useInstrumentsEntity = new UseInstruments();
                        if(StringUtils.isNotBlank(labExtractSampleList.get(j).getExtractMethod())){
                            useInstrumentsEntity.setMethodName(labExtractSampleList.get(j).getExtractMethod());
                        }else{
                            useInstrumentsEntity.setMethodName("A");
                        }
                        useInstrumentsEntity.setOrgCode(operateUser.getOrgId());
                        List<UseInstruments> useInstrumentsList = useInstrumentsService.findByExtractMentod(useInstrumentsEntity);
                        if(useInstrumentsList.size() > 0){
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
            if (labExtractSample != null) {
                if(labExtractSample.getSampleType().equals("01") || labExtractSample.getSampleType().equals("03") || labExtractSample.getSampleType().equals("04") || labExtractSample.getSampleType().equals("08")){
                    modelAndView.addObject("labExtractSampleListRoutine", labExtractSampleList);
                    //选择设备
                    String[] equipment = null;
                    if(labExtractInfo != null && labExtractInfo.getEquipment() != null){
                        equipment = labExtractInfo.getEquipment().split(",");
                    }
                    modelAndView.addObject("equipment", equipment);
                    //选择试剂盒
                    String[] panelKit = null;
                    if(labExtractInfo != null && labExtractInfo.getKit() != null){
                        panelKit = labExtractInfo.getKit().split(",");
                    }
                    //试剂批号回显
                    List<ReagentInfo> selectList = new ArrayList<>();
                    if(panelKit != null){
                        for (int k = 0; k < panelKit.length; k++) {
                            for (int j = 0; j < reagentInfoList.size(); j++){
                                if (panelKit[k].equals(reagentInfoList.get(j).getId())) {
                                    selectList.add(reagentInfoList.get(j));
                                }
                            }
                        }
                    }
                    modelAndView.addObject("panelKit",panelKit);
                    modelAndView.addObject("selectList",selectList);
                    modelAndView.addObject("routine_extract_extractId", labExtractInfo.getExtractId());
                    modelAndView.addObject("routine_extract_extractPerson1", labExtractInfo.getExtractPerson1());
//                    modelAndView.addObject("routine_extract_extractionExperimentDate", DateUtils.dateToString(labExtractInfo.getExtractDatetime(),"yyyy-MM-dd HH:mm"));
                    modelAndView.addObject("routine_extract_extractionExperimentDate", DateUtils.dateToString(labExtractInfo.getExtractDatetime(),"yyyy-MM-dd"));
//                    modelAndView.addObject("routine_extract_extractionCreateDate",DateUtils.dateToString(labExtractInfo.getCreateDatetime(),"yyyy-MM-dd HH:mm"));
                    if(labExtractInfo.getExtractDatetime()!=null){
                        modelAndView.addObject("routine_extract_extractionCreateDate",DateUtils.dateToString(labExtractInfo.getExtractDatetime(),"yyyy-MM-dd"));

                    }else {
                        modelAndView.addObject("routine_extract_extractionCreateDate",DateUtils.dateToString(new Date(),"yyyy-MM-dd"));
                    }
                }else if(labExtractSample.getSampleType().equals("02")){
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
                    if(labExtractInfo != null && labExtractInfo.getEquipment() != null){
                        equipment = labExtractInfo.getEquipment().split(",");
                    }
                    modelAndView.addObject("equipmentTwe", equipment);
                    //选择试剂盒
                    String[] panelKit = null;
                    if(labExtractInfo != null && labExtractInfo.getKit() != null){
                        panelKit = labExtractInfo.getKit().split(",");
                    }
                    //试剂批号回显
                    List<ReagentInfo> selectList = new ArrayList<>();
                    if(panelKit != null){
                        for (int k = 0; k < panelKit.length; k++) {
                            for (int j = 0; j < reagentInfoList.size(); j++){
                                if (panelKit[k].equals(reagentInfoList.get(j).getId())) {
                                    selectList.add(reagentInfoList.get(j));
                                }
                            }
                        }
                    }
                    modelAndView.addObject("panelKitTwe",panelKit);
                    modelAndView.addObject("selectListTwe",selectList);
                    modelAndView.addObject("spot_extract_extractId", labExtractInfo.getExtractId());
                    modelAndView.addObject("spot_extract_extractPerson1", labExtractInfo.getExtractPerson1());
                    modelAndView.addObject("spot_extract_extractionExperimentDate", DateUtils.dateToString(labExtractInfo.getExtractDatetime(),"yyyy-MM-dd"));
                    if(labExtractInfo.getExtractDatetime()!=null){
                        modelAndView.addObject("spot_extract_extractionCreateDate",DateUtils.dateToString(labExtractInfo.getExtractDatetime(),"yyyy-MM-dd"));

                    }else {
                        modelAndView.addObject("spot_extract_extractionCreateDate",DateUtils.dateToString(new Date(),"yyyy-MM-dd"));

                    }
//                    modelAndView.addObject("spot_extract_extractionExperimentDate", DateUtils.dateToString(labExtractInfo.getExtractDatetime(),"yyyy-MM-dd HH:mm"));
//                    modelAndView.addObject("spot_extract_extractionCreateDate",DateUtils.dateToString(labExtractInfo.getCreateDatetime(),"yyyy-MM-dd HH:mm"));
                }else if(labExtractSample.getSampleType().equals("05") || labExtractSample.getSampleType().equals("06") || labExtractSample.getSampleType().equals("07") || labExtractSample.getSampleType().equals("09")){
                    modelAndView.addObject("labExtractSampleListSpecial", labExtractSampleList);
                    modelAndView.addObject("special_extract_extractId", labExtractInfo.getExtractId());
                    modelAndView.addObject("special_extract_extractPerson1", labExtractInfo.getExtractPerson1());
                    modelAndView.addObject("special_extract_extractionExperimentDate", DateUtils.dateToString(labExtractInfo.getExtractDatetime(),"yyyy-MM-dd"));
                    modelAndView.addObject("special_extract_extractionCreateDate",DateUtils.dateToString(labExtractInfo.getCreateDatetime(),"yyyy-MM-dd"));
/*
                    modelAndView.addObject("special_extract_extractionExperimentDate", DateUtils.dateToString(labExtractInfo.getExtractDatetime(),"yyyy-MM-dd HH:mm"));
                    modelAndView.addObject("special_extract_extractionCreateDate",DateUtils.dateToString(labExtractInfo.getCreateDatetime(),"yyyy-MM-dd HH:mm"));
*/
                }else if(labExtractSample.getSampleType().equals("99")){
                    modelAndView.addObject("labExtractSampleListOther", labExtractSampleList);
                    modelAndView.addObject("other_extract_extractId", labExtractInfo.getExtractId());
                    modelAndView.addObject("other_extract_extractPerson1", labExtractInfo.getExtractPerson1());
                    modelAndView.addObject("other_extract_extractionExperimentDate", DateUtils.dateToString(labExtractInfo.getExtractDatetime(),"yyyy-MM-dd"));
                    modelAndView.addObject("other_extract_extractionCreateDate",DateUtils.dateToString(labExtractInfo.getCreateDatetime(),"yyyy-MM-dd"));
/*
                    modelAndView.addObject("other_extract_extractionExperimentDate", DateUtils.dateToString(labExtractInfo.getExtractDatetime(),"yyyy-MM-dd HH:mm"));
                    modelAndView.addObject("other_extract_extractionCreateDate",DateUtils.dateToString(labExtractInfo.getCreateDatetime(),"yyyy-MM-dd HH:mm"));
*/
                }
            }

            modelAndView.addObject("extractionExperimentDate", DateUtils.dateToString(labExtractInfo.getExtractDatetime(),"yyyy-MM-dd"));
//            modelAndView.addObject("extractionExperimentDate", DateUtils.dateToString(labExtractInfo.getExtractDatetime(),"yyyy-MM-dd HH:mm"));
            modelAndView.addObject("operateType", Constants.OPERATE_TYPE_EDIT);
            modelAndView.addObject("taskId", labExtractInfo.getTaskId());
            modelAndView.addObject("extractStage", labExtractInfo.getExtractStage());
            modelAndView.addObject("reagentInfoList",reagentInfoList);
            modelAndView.addObject("labExtractInfo",labExtractInfo);
            modelAndView.addObject("equipmentNameInfoList", equipmentNameInfoList);
        }

        modelAndView.setViewName("manualExtraction/manualExtractionExperiment");

        return modelAndView;
    }

    /**
     * 进入手动提取实验页面
     * @param request
     * @return
     */
    @RequestMapping("/manualExtractionExperiment")
    public ModelAndView manualExtractionExperiment(HttpServletRequest request, HttpSession session){
        ModelAndView modelAndView = InitializationData.initDictList();

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if(operateUser == null){
            modelAndView.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return  modelAndView;
        }
        String userOrgId = operateUser.getOrgId();
        //将当前用户org_id设置进query
        if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
            operateUser.setOrgId("110230000000");
        }
        AmPersonalInfo amPersonalInfo =amPersonalInfoService.queryAmPersonalInfo(operateUser.getPersonalId());
        modelAndView.addObject("operateUser",amPersonalInfo.getFullName());
        modelAndView.addObject("routine_extract_extractPerson1",amPersonalInfo.getFullName());
        modelAndView.addObject("spot_extract_extractPerson1",amPersonalInfo.getFullName());
        modelAndView.addObject("special_extract_extractPerson1",amPersonalInfo.getFullName());
        modelAndView.addObject("other_extract_extractPerson1",amPersonalInfo.getFullName());

        List<LimsSampleInfoDna> limsSampleInfoDnaList = (List<LimsSampleInfoDna>)session.getAttribute("limsSampleInfoDnaList");
        List<LabExtractSample> labExtractSampleListRoutine = new ArrayList<>();
        List<LabExtractSample> labExtractSampleListSpot = new ArrayList<>();
        List<LabExtractSample> labExtractSampleListSpecial = new ArrayList<>();
        List<LabExtractSample> labExtractSampleListOther = new ArrayList<>();
        for (LimsSampleInfoDna lsid : limsSampleInfoDnaList) {
            LabExtractSample labExtractSample = new LabExtractSample();
            if(lsid.getSampleType().equals("01") || lsid.getSampleType().equals("03") || lsid.getSampleType().equals("04") || lsid.getSampleType().equals("08") || lsid.getSampleType().equals("21")){
                labExtractSample.setSampleNo(lsid.getSampleNo());
                labExtractSample.setSampleId(lsid.getSampleId());
                labExtractSample.setSampleName(lsid.getSampleName());
                labExtractSample.setSampleType(lsid.getSampleType());
                UseInstruments useInstrumentsEntity = new UseInstruments();
                useInstrumentsEntity.setMethodName("A");
                useInstrumentsEntity.setOrgCode(operateUser.getOrgId());
                List<UseInstruments> useInstrumentsList = useInstrumentsService.findByExtractMentod(useInstrumentsEntity);
                if(useInstrumentsList.size() > 0){
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


                labExtractSampleListRoutine.add(labExtractSample);
            }else if(lsid.getSampleType().equals("02")){
                labExtractSample.setSampleNo(lsid.getSampleNo());
                labExtractSample.setSampleId(lsid.getSampleId());
                labExtractSample.setSampleName(lsid.getSampleName());
                labExtractSample.setSampleType(lsid.getSampleType());
                labExtractSampleListSpot.add(labExtractSample);
            }else if(lsid.getSampleType().equals("05") || lsid.getSampleType().equals("06") || lsid.getSampleType().equals("07") || lsid.getSampleType().equals("09")){
                labExtractSample.setSampleNo(lsid.getSampleNo());
                labExtractSample.setSampleId(lsid.getSampleId());
                labExtractSample.setSampleName(lsid.getSampleName());
                labExtractSample.setSampleType(lsid.getSampleType());
                labExtractSampleListSpecial.add(labExtractSample);
            }else if(lsid.getSampleType().equals("99")){
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
        Map<String,Object> map = goTime(null);
        modelAndView.addObject("extractionExperimentDate",map.get("extractionExperimentDate"));
        //常规提取时间
        modelAndView.addObject("routine_extract_extractionExperimentDate",map.get("extractionExperimentDate"));
        modelAndView.addObject("routine_extract_extractionCreateDate",map.get("extractionCreateDate"));
        //精斑提取时间
        modelAndView.addObject("spot_extract_extractionExperimentDate",map.get("extractionExperimentDate"));
        modelAndView.addObject("spot_extract_extractionCreateDate",map.get("extractionCreateDate"));
        //特殊提取时间
        modelAndView.addObject("special_extract_extractionExperimentDate",map.get("extractionExperimentDate"));
        modelAndView.addObject("special_extract_extractionCreateDate",map.get("extractionCreateDate"));
        //其他提取时间
        modelAndView.addObject("other_extract_extractionExperimentDate",map.get("extractionExperimentDate"));
        modelAndView.addObject("other_extract_extractionCreateDate",map.get("extractionCreateDate"));

        //设备选择
        EquipmentNameInfo equipmentNameInfo = new EquipmentNameInfo();
        equipmentNameInfo.setPhase(Constants.EXPERIMENTAL_STAGE_01);//阶段
        equipmentNameInfo.setOrgId(operateUser.getOrgId());
        equipmentNameInfo.setEquipmentNo("SB");
//        List<EquipmentNameInfo> equipmentNameInfoList = equipmentNameInfoService.selectEquipmentNameInfoListByTypeNo(equipmentNameInfo);
        EquipmentTypeInfo equipmentTypeInfo = new EquipmentTypeInfo();
        equipmentTypeInfo.setExperimentalStage(Constants.EXPERIMENTAL_STAGE_01);
        equipmentTypeInfo.setOrgId(operateUser.getOrgId());

        List<EquipmentTypeInfo> equipmentTypeInfoList = equipmentTypeInfoService.selectLabEquipmentTypeList(equipmentTypeInfo);
        List<EquipmentNameInfo> equipmentNameInfoList = new ArrayList<>();
        if(equipmentTypeInfoList.size()>0){
            equipmentNameInfoList = equipmentNameInfoService.selectEquipmentTypeId(equipmentTypeInfoList.get(0).getId());
        }


        modelAndView.addObject("equipmentNameInfoList", equipmentNameInfoList);

        //试剂信息
        ReagentInfo reagentInfo = new ReagentInfo();
        reagentInfo.setExperimentalStage(Constants.EXPERIMENTAL_STAGE_01);
        reagentInfo.setOrgId(operateUser.getOrgId());
        List<ReagentInfo> reagentInfoList = reagentInfoService.selectList(reagentInfo);
        if (ListUtils.isNotNullAndEmptyList(reagentInfoList)){
            for(int i=0;i< reagentInfoList.size();i++){
                ReagentOutgoInfo reagentOutgoInfo = new ReagentOutgoInfo();
                reagentOutgoInfo.setReagentId(reagentInfoList.get(i).getId());
                reagentOutgoInfo.setOrgId(reagentInfoList.get(i).getOrgId());
                List<ReagentOutgoInfo> reagentOutgoInfoList = reagentOutgoInfoService.selectreagentOutgoList(reagentOutgoInfo);
                if(reagentOutgoInfoList.size() >0){
                    reagentInfoList.get(i).setBatchNumber(reagentOutgoInfoList.get(0).getBatchNumber());
                    reagentInfoList.get(i).setValidityTime(reagentOutgoInfoList.get(0).getEffectiveDatetime());
                }
            }
        }
        modelAndView.addObject("reagentInfoList",reagentInfoList);

        modelAndView.addObject("labExtractSampleListRoutine",labExtractSampleListRoutine);
        modelAndView.addObject("labExtractSampleListSpot",labExtractSampleListSpot);
        modelAndView.addObject("labExtractSampleListSpecial",labExtractSampleListSpecial);
        modelAndView.addObject("labExtractSampleListOther",labExtractSampleListOther);
        modelAndView.addObject("operateType", Constants.OPERATE_TYPE_ADD);
        modelAndView.addObject("extractStage", "0");
        modelAndView.setViewName("manualExtraction/manualExtractionExperiment");
        return modelAndView;
    }

    /**
     * 检验过程时间
     * @param taskId
     * @return
     */
    public Map<String,Object> goTime(String taskId){

        Map<String,Object> map = new HashMap<>();
        //提取时间
        List<LabExtractInfo> labExtractInfoList = labExtractInfoService.selectByTaskId(taskId);
        if(labExtractInfoList.size() != 0 && labExtractInfoList.get(0).getExtractDatetime() != null){
            map.put("extractionExperimentDate", DateUtils.dateToString(labExtractInfoList.get(0).getExtractDatetime(),"yyyy-MM-dd"));
            map.put("extractionCreateDate",DateUtils.dateToString(labExtractInfoList.get(0).getCreateDatetime(),"yyyy-MM-dd"));
        }else{
            map.put("extractionExperimentDate", DateUtils.dateToString(new Date(),"yyyy-MM-dd"));
            map.put("extractionCreateDate",DateUtils.dateToString(new Date(),"yyyy-MM-dd"));
        }
        //扩增时间
        List<LabPcrInfo> labPcrInfoList = labPcrInfoService.selectByTaskId(taskId);
        if(labPcrInfoList.size() != 0 && labPcrInfoList.get(0).getPcrEndDatetime() != null){
            map.put("pcrexperimentDate", DateUtils.dateToString(labPcrInfoList.get(0).getPcrEndDatetime(),"yyyy-MM-dd"));
        }else{
            map.put("pcrexperimentDate", DateUtils.dateToString(new Date(),"yyyy-MM-dd"));
        }
        //电泳时间
        List<LabSyInfo> labSyInfoList = labSyInfoService.selectByTaskId(taskId);
        if(labSyInfoList.size() != 0 && labSyInfoList.get(0).getSyEndDatetime() != null){
            map.put("syExperimentDate", DateUtils.dateToString(labSyInfoList.get(0).getSyEndDatetime(),"yyyy-MM-dd"));
        }else{
            map.put("syExperimentDate", DateUtils.dateToString(new Date(),"yyyy-MM-dd"));
        }
        return map;
    }

    //扩增任务页面
    @RequestMapping("/manualPcr")
    public ModelAndView manualPcr(HttpServletRequest request, LabPcrInfoVo labPcrInfoVo,PageInfo pageInfo) throws ParseException {
        ModelAndView view = new ModelAndView();
        view.setViewName("manualExtraction/manualPcr");

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();

        if(operateUser == null){
            view.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return  view;
        }

        //获取当前用户的id
        String userOrgId = operateUser.getOrgId();
        //将当前用户org_id设置进query
        if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
            userOrgId = "110230000000";
        }

        //查看扩增任务列表
        labPcrInfoVo.getEntity().setOrgId(userOrgId);
        if (null != labPcrInfoVo.getAcceptEndDatetime()){//解决时间无时分秒造成的00:00:00查询
            labPcrInfoVo.setAcceptEndDatetime(addDays(labPcrInfoVo.getAcceptEndDatetime(),1));//增加一天
        }
        List<LabPcrInfoVo> labPcrInfoVos = labPcrInfoService.selectVoList(labPcrInfoVo, pageInfo);
        int totalCount = labPcrInfoService.selectVOCount(labPcrInfoVo);
        if (null != labPcrInfoVo.getAcceptEndDatetime()){//解决时间无时分秒造成的00:00:00查询
            labPcrInfoVo.setAcceptEndDatetime(addDays(labPcrInfoVo.getAcceptEndDatetime(),-1));//减一天
        }
        view.addObject("pageInfo", pageInfo.updatePageInfo(totalCount));
        view.addObject("query", labPcrInfoVo);
        view.addObject("labPcrInfoVos",labPcrInfoVos);

        return view;
    }

    //扩增孔板阶段
    @RequestMapping("/pcrPuzzleExperiment")
    public ModelAndView pcrPuzzleExperiment() {
        ModelAndView view = new ModelAndView();
        //扩增板检材显示
        List<LabPcrSample> labPcrSampleList = new ArrayList<>();
        List<Map<String, Object>> tempList = TestProcessUtils.pcrBoardSort(labPcrSampleList);

        view.addObject("tempList", tempList);
        view.setViewName("manualExtraction/pcrPuzzleExperiment");

        return view;
    }

    //查询检材
    @RequestMapping("/selectSampleNo")
    @ResponseBody
    public Map<String,Object> selectSampleNo(String sampleNo) {
        Map<String, Object> map = new HashMap<>();

        try {
            List<LimsSampleInfoDna> limsSampleInfoDnas = limsSampleInfoDnaService.selectBySampleNo(sampleNo);
            if (ListUtils.isNotNullAndEmptyList(limsSampleInfoDnas)){
                LimsSampleInfoDna limsSampleInfoDna = limsSampleInfoDnas.get(0);
                if (limsSampleInfoDna != null){
                    map.put("limsSampleInfoDna",limsSampleInfoDna);
                    map.put("sampleNo",sampleNo);
                    map.put("success",true);
                }else {
                    map.put("success",false);
                }
            }else {
                map.put("success",false);
            }

        }catch (Exception e) {
            logger.info("扩增板查询检材信息");
            logger.error("扩增板查询检材信息失败！", e);
            map.put("success",false);
            throw e;
        }
        return map;
    }

    //扩增实验阶段
    @RequestMapping("/pcrExperiment")
    public ModelAndView pcrExperiment(String pcrId) {
        ModelAndView view = InitializationData.initDictList();
        view.setViewName("manualExtraction/pcrExperiment");
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
            operateUser.setOrgId("110230000000");
        }
        AmPersonalInfo amPersonalInfo = amPersonalInfoService.queryAmPersonalInfo(operateUser.getPersonalId());

        LabPcrInfo labPcrInfo = labPcrInfoService.selectByPrimaryKey(pcrId);
        if (labPcrInfo != null) {
            if (labPcrInfo.getPcrEndDatetime() == null) {
                labPcrInfo.setPcrEndDatetime(new Date());
            }
            if (StringUtils.isBlank(labPcrInfo.getPcrPerson1())) {
                labPcrInfo.setPcrPerson1(amPersonalInfo.getFullName());
            }
        }

        //检材信息列表
        List<LabPcrSample> labPcrSampleList = labPcrSampleService.selectByPcrId(pcrId);

        //位置排序
        if (ListUtils.isNotNullAndEmptyList(labPcrSampleList)){
            Collections.sort(labPcrSampleList);
        }

        //显示设备仪器
        EquipmentNameInfo equipmentNameInfo = new EquipmentNameInfo();
        equipmentNameInfo.setOrgId(operateUser.getOrgId());
        equipmentNameInfo.setPhase(Constants.EXPERIMENTAL_STAGE_02);//阶段
        //扩增仪
//        equipmentNameInfo.setEquipmentNo("KZY");
        List<EquipmentNameInfo> panelListKZY = equipmentNameInfoService.selectEquipmentNameInfoListByTypeNo(equipmentNameInfo);

        //试剂盒
        ReagentInfo reagentInfo = new ReagentInfo();
        reagentInfo.setExperimentalStage(Constants.EXPERIMENTAL_STAGE_02);
        reagentInfo.setOrgId(operateUser.getOrgId());
        List<ReagentInfo> reagentInfoList = reagentInfoService.selectList(reagentInfo);

        view.addObject("panelListKZY", panelListKZY);
        view.addObject("reagentInfoList", reagentInfoList);
//        view.addObject("panelListKZTX", panelListKZTX);
        view.addObject("labPcrSampleList",labPcrSampleList);
        view.addObject("labPcrInfo",labPcrInfo);
        return view;
    }

    //进入复检扩增实验页面
    @RequestMapping("/reinspectionPcrExperiment")
    public ModelAndView reinspectionPcrExperiment(HttpSession session) {
        ModelAndView view = InitializationData.initDictList();
        view.setViewName("manualExtraction/pcrExperiment");

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
            operateUser.setOrgId("110230000000");
        }

        //登录人信息
        AmPersonalInfo amPersonalInfo = amPersonalInfoService.queryAmPersonalInfo(operateUser.getPersonalId());

        //待复检的检材信息
        List<LimsSampleInfoDna> limsSampleInfoDnaList = (List<LimsSampleInfoDna>)session.getAttribute("limsSampleInfoDnaList");
        //检材信息列表
        List<LabPcrSample> labPcrSampleList = new ArrayList<>();
        if (ListUtils.isNotNullAndEmptyList(limsSampleInfoDnaList)){
            for (int i = 0; i<limsSampleInfoDnaList.size();i++){
                LabPcrSample labPcrSample = new LabPcrSample();
                labPcrSample.setSampleId(limsSampleInfoDnaList.get(i).getSampleId());
                labPcrSample.setSampleNo(limsSampleInfoDnaList.get(i).getSampleNo());
                labPcrSample.setSampleName(limsSampleInfoDnaList.get(i).getSampleName());
                labPcrSample.setSampleType(limsSampleInfoDnaList.get(i).getSampleType());
                labPcrSample.setSamplePostion(Constants.SYTABLE_POSTION_ARR_VER[i+4]);
                labPcrSampleList.add(labPcrSample);
            }
        }

        //页面显示扩增任务数据
        LabPcrInfo labPcrInfo = new LabPcrInfo();
        labPcrInfo.setPcrEndDatetime(new Date());
        if (StringUtils.isBlank(labPcrInfo.getPcrPerson1())) {
            labPcrInfo.setPcrPerson1(amPersonalInfo.getFullName());
        }

        //显示设备仪器
        EquipmentNameInfo equipmentNameInfo = new EquipmentNameInfo();
        equipmentNameInfo.setOrgId(operateUser.getOrgId());
        equipmentNameInfo.setPhase(Constants.EXPERIMENTAL_STAGE_02);//阶段
        //扩增仪
        equipmentNameInfo.setEquipmentNo("KZY");
        List<EquipmentNameInfo> panelListKZY = equipmentNameInfoService.selectEquipmentNameInfoListByTypeNo(equipmentNameInfo);
        //扩增体系
        equipmentNameInfo.setEquipmentNo("KZTX");
        List<EquipmentNameInfo> panelListKZTX = equipmentNameInfoService.selectEquipmentNameInfoListByTypeNo(equipmentNameInfo);
        //试剂盒
        ReagentInfo reagentInfo = new ReagentInfo();
        reagentInfo.setExperimentalStage(Constants.EXPERIMENTAL_STAGE_02);
        reagentInfo.setOrgId(operateUser.getOrgId());
        List<ReagentInfo> reagentInfoList = reagentInfoService.selectList(reagentInfo);

        view.addObject("panelListKZY", panelListKZY);
        view.addObject("reagentInfoList", reagentInfoList);
        view.addObject("panelListKZTX", panelListKZTX);
        view.addObject("labPcrSampleList",labPcrSampleList);
        view.addObject("labPcrInfo",labPcrInfo);
        view.addObject("reinspectionId","1");
        return view;
    }

    /**
     * 扩增检材添加
     * @param request
     * @return
     */
    @RequestMapping("/addConsume")
    @ResponseBody
    public Map<String,Object> addConsume(HttpServletRequest request, String sampleNo,String pcrId) {
        Map<String, Object> map = new HashMap<>();
        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();

        List<LimsSampleInfoDna> limsSampleInfoDnas = limsSampleInfoDnaService.selectBySampleNo(sampleNo);
        if (ListUtils.isNotNullAndEmptyList(limsSampleInfoDnas)){
            LimsSampleInfoDna limsSampleInfoDna = limsSampleInfoDnas.get(0);
//            if (limsSampleInfoDna != null){
                LabPcrSample labPcrSample = new LabPcrSample();
//                labPcrSample.setId(UUID.randomUUID().toString());
//                labPcrSample.setPcrId(pcrId);
//                labPcrSample.setCreateDatetime(new Date());
//                if (operateUser != null){
//                    labPcrSample.setCreatePerson(operateUser.getLoginName());
//                }
                labPcrSample.setSampleId(limsSampleInfoDna.getSampleId());
                labPcrSample.setSampleName(limsSampleInfoDna.getSampleName());
                labPcrSample.setSampleNo(limsSampleInfoDna.getSampleNo());
                labPcrSample.setSampleType(limsSampleInfoDna.getSampleType());
//                try {
//                    labPcrSampleService.insert(labPcrSample);
//                }catch (Exception e) {
//                    logger.info("---插入扩增检材记录id为[" + labPcrSample.getId() + "]的信息失败！---");
//                    logger.error("插入扩增检材记录失败！", e);
//                    map.put("success",false);
//                    throw e;
//                }
                //页面显示检材类型名称
                labPcrSample.setSampleType(limsSampleInfoDna.getSampleTypeName());
                map.put("labPcrSample",labPcrSample);
                map.put("pcrId",pcrId);
                map.put("success",true);
//            }
        }else {
            map.put("success",false);
        }
        return map;
    }

    //查询扩增板号是否存在
    @RequestMapping(value = "/repeatingByBoarsNo", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String,Object> repeatingByBoarsNo(@RequestBody LabPcrInfo labPcrInfo) {
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
        labPcrInfo.setOrgId(userOrgId);

        //查询数据库是否有相同的板号
        List<LabPcrInfo> labPcrInfoList = labPcrInfoService.selectRepeatingByBoarsNo(labPcrInfo);
        if (ListUtils.isNotNullAndEmptyList(labPcrInfoList)){
            map.put("success",false);
        } else {
            map.put("success",true);
        }

        return map;
    }

    /**
     * 保存扩增任务记录
    * @param request
    * @param pcrRecordDataModel
    * @return
            */
    @RequestMapping(value = "/savePcrInfo", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String,Object> savePcrInfo(HttpServletRequest request, @RequestBody PcrRecordDataModel pcrRecordDataModel) {

        Map<String, Object> map = new HashMap<>();

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();

        LabPcrInfo labPcrInfo = pcrRecordDataModel.getLabPcrInfo();

        List<LabPcrSample> labPcrSampleList = pcrRecordDataModel.getLabPcrSampleList();

        //获取当前用户的id
        String userOrgId = operateUser.getOrgId();
        //将当前用户org_id设置进query
        if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
            userOrgId = "110230000000";
        }

        if (labPcrInfo != null) {
            if(StringUtils.isNotBlank(labPcrInfo.getPcrId())){
                //修改数据
                LabPcrInfo pcrInfo = labPcrInfoService.selectByPrimaryKey(labPcrInfo.getPcrId());
                pcrInfo.setPcrPerson1(labPcrInfo.getPcrPerson1());
                pcrInfo.setPcrReagent(labPcrInfo.getPcrReagent());
                pcrInfo.setPcrEndDatetime(labPcrInfo.getPcrEndDatetime());
                pcrInfo.setPcrInstrument(labPcrInfo.getPcrInstrument());
                pcrInfo.setPcrSystem(labPcrInfo.getPcrSystem());
                pcrInfo.setBatchb(labPcrInfo.getBatchb());
                pcrInfo.setValidity(labPcrInfo.getValidity());
                pcrInfo.setPcrProgram(labPcrInfo.getPcrProgram());
                pcrInfo.setSampleCount(labPcrInfo.getSampleCount());
                pcrInfo.setPcrStage(labPcrInfo.getPcrStage());
                try {
                    if (operateUser != null) {
                        pcrInfo.setUpdatePerson(operateUser.getLoginName());
                    }
                    pcrInfo.setUpdateDatetime(new Date());
                    labPcrInfoService.updateByPrimaryKey(pcrInfo);
                }catch (Exception e) {
                    logger.info("----更新扩增任务记录id为[" + labPcrInfo.getPcrId() + "]的信息失败！---");
                    logger.error("更新扩增任务记录失败！", e);
                    map.put("success",false);
                    throw e;
                }
            }else{
                //添加数据
                /** 插入扩增任务记录 */
                labPcrInfo.setPcrId(UUID.randomUUID().toString());
                labPcrInfo.setPcrNo(seqNoGenerateService.getNextPcrNoVal("-", userOrgId));
                if (operateUser != null) {
                    labPcrInfo.setCreatePerson(operateUser.getLoginName());
                    labPcrInfo.setOrgId(userOrgId);
                }
                labPcrInfo.setSampleCount((short)labPcrSampleList.size());
                labPcrInfo.setCreateDatetime(new Date());

                try {
                    labPcrInfoService.insert(labPcrInfo);
                }catch (Exception e) {
                    logger.info("----插入扩增任务记录id为[" + labPcrInfo.getPcrId() + "]的信息失败！---");
                    logger.error("插入扩增任务记录失败！", e);
                    map.put("success",false);
                    throw e;
                }
            }
        }

        if (ListUtils.isNotNullAndEmptyList(labPcrSampleList)) {
            for (LabPcrSample lps : labPcrSampleList) {
                if (StringUtils.isNotBlank(lps.getId())) {
                    LabPcrSample labPcrSample = labPcrSampleService.selectByPrimaryKey(lps.getId());
                    //新值赋到旧值
                    labPcrSample.setSamplePostion(lps.getSamplePostion());
                    labPcrSample.setPrimer(lps.getPrimer());
                    labPcrSample.setBuffer(lps.getBuffer());
                    labPcrSample.setTemplate(lps.getTemplate());
                    labPcrSample.setTaqe(lps.getTaqe());
                    labPcrSample.setDntp(lps.getDntp());
                    labPcrSample.setH2o(lps.getH2o());
                    labPcrSample.setMgcl2(lps.getMgcl2());
                    labPcrSample.setUpdateDatetime(new Date());
                    if (operateUser != null) {
                        labPcrSample.setUpdatePerson(operateUser.getLoginName());
                    }

                    try {
                        labPcrSampleService.updateByPrimaryKey(labPcrSample);
                    }catch (Exception e) {
                        logger.info("----更新扩增检材实验记录pcrId为[" + labPcrSample.getId() + "]的信息失败！---");
                        logger.error("更新扩增材实验记录失败！", e);
                        map.put("success",false);
                        throw e;
                    }
                }else {
                    lps.setId(UUID.randomUUID().toString());
                    lps.setPcrId(labPcrInfo.getPcrId());
                    lps.setCreateDatetime(new Date());
                    if (operateUser != null) {
                        lps.setCreatePerson(operateUser.getLoginName());
                    }
                    try {
                        labPcrSampleService.insert(lps);
                    }catch (Exception e) {
                        logger.info("----插入扩增检材实验记录pcrId为[" + lps.getId() + "]的信息失败！----");
                        logger.error("插入扩增检材实验记录失败！", e);
                        map.put("success",false);
                        throw e;
                    }
                }
            }
        }

        if (StringUtils.isNotBlank(labPcrInfo.getPcrStage()) && labPcrInfo.getPcrStage().equals(Constants.FLAG_TRUE)) {

            //电泳检材实验数据来源是从扩增实验获取，故直接创建了电泳任务
            LabSyInfo labSyInfo = new LabSyInfo();

            labSyInfo.setSyId(UUID.randomUUID().toString());
            labSyInfo.setSyNo(seqNoGenerateService.getNextSyNoVal("-", userOrgId));
            labSyInfo.setBoardNo(labPcrInfo.getBoardNo());
            labSyInfo.setSyStage(Constants.FLAG_FALSE);
            if (operateUser != null) {
                labSyInfo.setCreatePerson(operateUser.getLoginName());
                labSyInfo.setOrgId(userOrgId);
            }
            labSyInfo.setSampleCount((short)labPcrSampleList.size());
            labSyInfo.setCreateDatetime(new Date());

            try {
                labSyInfoService.insert(labSyInfo);
            }catch (Exception e) {
                logger.info("----插入电泳任务记录id为[" + labSyInfo.getSyId() + "]的信息失败！---");
                logger.error("插入电泳任务记录失败！", e);
                map.put("success",false);
                throw e;
            }

            //插入电泳检材
            if (ListUtils.isNotNullAndEmptyList(labPcrSampleList)) {
                for (LabPcrSample lps : labPcrSampleList) {
                    LabSySample labSySample = new LabSySample();
                    labSySample.setId(UUID.randomUUID().toString());
                    labSySample.setSyId(labSyInfo.getSyId());
                    labSySample.setSampleId(lps.getSampleId());
                    labSySample.setSamplePostion(lps.getSamplePostion());
                    if (operateUser != null) {
                        labSySample.setCreatePerson(operateUser.getLoginName());
                    }
                    labSySample.setCreateDatetime(new Date());

                    try {
                        labSySampleService.insert(labSySample);
                    }catch (Exception e) {
                        logger.info("----插入电泳检材任务记录id为[" + labSyInfo.getSyId() + "]的信息失败！---");
                        logger.error("插入电泳检材任务记录失败！", e);
                        map.put("success",false);
                        throw e;
                    }
                }
            }
        }

        map.put("pcrId",labPcrInfo.getPcrId());
        map.put("success",true);
        return map;
    }

    //电泳任务页面
    @RequestMapping("/manualSy")
    public ModelAndView manualSy(HttpServletRequest request, LabSyInfoVo labSyInfoVo, PageInfo pageInfo) {
        ModelAndView view = new ModelAndView();

        //获取当前登录用户
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo loaUserInfo = (LoaUserInfo) subject.getPrincipal();
        //获取当前用户的id
        String userOrgId = loaUserInfo.getOrgId();
        //将当前用户org_id设置进query
        if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
            userOrgId = "110230000000";
        }
        labSyInfoVo.getEntity().setOrgId(userOrgId);


        if (null != labSyInfoVo.getAcceptEndDatetime()){//解决时间无时分秒造成的00:00:00查询
            labSyInfoVo.setAcceptEndDatetime(addDays(labSyInfoVo.getAcceptEndDatetime(),1));//增加一天
        }
        List<LabSyInfoVo> labSyInfoVoList = labSyInfoService.selectVOPaginationList(labSyInfoVo, pageInfo);
        int totalCnt = labSyInfoService.selectVOCnt(labSyInfoVo);
        if (null != labSyInfoVo.getAcceptEndDatetime()){//解决时间无时分秒造成的00:00:00查询
            labSyInfoVo.setAcceptEndDatetime(addDays(labSyInfoVo.getAcceptEndDatetime(),-1));//减一天
        }

        view.addObject("labSyInfoVo", labSyInfoVo);
        view.addObject("labSyInfoVoList", labSyInfoVoList);
        view.addObject("pageInfo", pageInfo.updatePageInfo(totalCnt));
        view.setViewName("manualExtraction/manualSy");

        return view;
    }

    //电泳孔板阶段
    @RequestMapping("/syPuzzleExperiment")
    public ModelAndView syPuzzleExperiment() {
        ModelAndView view = new ModelAndView();
        //扩增板检材显示
        List<LabSySample> labSySampleList = new ArrayList<>();
        List<Map<String, Object>> tempList = TestProcessUtils.syBoardSort(labSySampleList);

        view.addObject("tempList", tempList);
        view.setViewName("manualExtraction/syPuzzleExperiment");

        return view;
    }

    //电泳阶段板号去重
    @RequestMapping(value="/selectBoardNoIsExist")
    @ResponseBody
    public Map<String, Object> selectDelagatorQuery(HttpServletRequest request, @RequestBody LabSyInfo labSyInfo) {
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
        labSyInfo.setOrgId(userOrgId);

        //查询数据库是否有相同的板号
        List<LabSyInfo> labPcrInfoList = labSyInfoService.selectRepeatingByBoarsNo(labSyInfo);
        if (ListUtils.isNotNullAndEmptyList(labPcrInfoList)){
            map.put("success",false);
        } else {
            map.put("success",true);
        }

        return map;
    }
    /*@RequestMapping(value="/selectBoardNoIsExist")
    @ResponseBody
    public Map<String, Object> selectDelagatorQuery(HttpServletRequest request, String boardNo) {
        Map<String, Object> result = new HashMap<>();

        //获取当前登录用户
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo loaUserInfo = (LoaUserInfo) subject.getPrincipal();
        //获取当前用户的id
        String userOrgId = loaUserInfo.getOrgId();
        //将当前用户org_id设置进query
        if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
            userOrgId = "110230000000";
        }
        LabSyInfo labSyInfo = new LabSyInfo();
        labSyInfo.setBoardNo(boardNo);
        labSyInfo.setOrgId(userOrgId);
        try {
            List<LabSyInfo> labSyInfos = labSyInfoService.selectAll();
            for (int i = 0; i <labSyInfos.size() ; i++) {
                LabSyInfo labSyInfo1 = labSyInfos.get(i);
                if(labSyInfo1.getBoardNo().equals(boardNo)){
                    result.put("success","重复");
                    return result;
                }
            }
            labSyInfo.setBoardNo(boardNo);
            List<LabSyInfo> labSyInfoList = labSyInfoService.selectList(labSyInfo);
            if (ListUtils.isNullOrEmptyList(labSyInfoList)) {
                result.put("success", true);
            }
        }catch (Exception e) {
            logger.error("查询电泳板号是否重复失败！");
            result.put("success",false);
            throw e;
        }

        return result;
    }*/


    /**
     * 电泳检材添加
     * @param request
     * @return
     */
    @RequestMapping("/addLoadSample")
    @ResponseBody
    public Map<String,Object> addLoadSample(HttpServletRequest request, String sampleNo,String syId) {
        Map<String, Object> map = new HashMap<>();
        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();

        List<LimsSampleInfoDna> limsSampleInfoDnas = limsSampleInfoDnaService.selectBySampleNo(sampleNo);
        if (ListUtils.isNotNullAndEmptyList(limsSampleInfoDnas)){
            LimsSampleInfoDna limsSampleInfoDna = limsSampleInfoDnas.get(0);
            LabSySample labSySample = new LabSySample();
            labSySample.setSampleId(limsSampleInfoDna.getSampleId());
            labSySample.setSampleName(limsSampleInfoDna.getSampleName());
            labSySample.setSampleNo(limsSampleInfoDna.getSampleNo());
            labSySample.setSampleType(limsSampleInfoDna.getSampleType());
            labSySample.setSampleType(limsSampleInfoDna.getSampleTypeName());
            map.put("labSySample",labSySample);
            map.put("syId",syId);
            map.put("success",true);
        }else {
            map.put("success",false);
        }
        return map;
    }


    //电泳实验阶段
    @RequestMapping("/syExperiment")
    public ModelAndView syExperiment(String syId) {
        ModelAndView view = InitializationData.initDictList();

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if(operateUser == null){
            view.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return  view;
        }
        AmPersonalInfo amPersonalInfo = amPersonalInfoService.queryAmPersonalInfo(operateUser.getPersonalId());

        LabSyInfo labSyInfo = labSyInfoService.selectByPrimaryKey(syId);
        if (labSyInfo != null) {
            if (labSyInfo.getSyEndDatetime() == null) {
                labSyInfo.setSyEndDatetime(new Date());
            }
            if (StringUtils.isBlank(labSyInfo.getSyPerson1())) {
                labSyInfo.setSyPerson1(amPersonalInfo.getFullName());
            }
            if (StringUtils.isNotBlank(labSyInfo.getSyString())) {
                Map<String, Object> mapSyString = (Map)JSON.parse(labSyInfo.getSyString());
                view.addObject("mapSyString", mapSyString);
            }
        }
        List<LabSySample> labSySampleList = labSySampleService.selectBySyId(syId);

        //位置排序
        if (ListUtils.isNotNullAndEmptyList(labSySampleList)){
            Collections.sort(labSySampleList);
        }

        //获取当前用户的id
        String userOrgId = operateUser.getOrgId();
        //将当前用户org_id设置进query
        if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
            userOrgId = "110230000000";
        }

//        EquipmentNameInfo equipmentNameInfo = new EquipmentNameInfo();
//        //阶段
//        equipmentNameInfo.setPhase(Constants.EXPERIMENTAL_STAGE_03);
//        equipmentNameInfo.setOrgId(userOrgId);

//        equipmentNameInfo.setEquipmentNo("DYY");
//        List<EquipmentNameInfo> panelListDYY = equipmentNameInfoService.selectEquipmentNameInfoListByTypeNo(equipmentNameInfo);

        EquipmentTypeInfo equipmentTypeInfo = new EquipmentTypeInfo();
        equipmentTypeInfo.setExperimentalStage(Constants.EXPERIMENTAL_STAGE_03);
        equipmentTypeInfo.setOrgId(operateUser.getOrgId());
        List<EquipmentTypeInfo> equipmentTypeInfoList = equipmentTypeInfoService.selectLabEquipmentTypeList(equipmentTypeInfo);
        List<EquipmentNameInfo> panelListDYY = new ArrayList<>();
        if(equipmentTypeInfoList.size()>0){
            panelListDYY = equipmentNameInfoService.selectEquipmentTypeId(equipmentTypeInfoList.get(0).getId());
        }


        view.addObject("panelListDYY", panelListDYY);
        view.addObject("labSyInfo", labSyInfo);
        view.addObject("labSySampleList", labSySampleList);
        view.setViewName("manualExtraction/syExperiment");

        return view;
    }

    //待复检电泳实验阶段
    @RequestMapping("/reinspectionSyExperiment")
    public ModelAndView reinspectionSyExperiment(HttpSession session) {
        ModelAndView view = InitializationData.initDictList();

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if(operateUser == null){
            view.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return  view;
        }

        //获取当前用户的id
        String userOrgId = operateUser.getOrgId();
        //将当前用户org_id设置进query
        if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
            userOrgId = "110230000000";
        }
        AmPersonalInfo amPersonalInfo = amPersonalInfoService.queryAmPersonalInfo(operateUser.getPersonalId());

        //待复检任务信息
        LabSyInfo labSyInfo = new LabSyInfo();
        labSyInfo.setSyEndDatetime(new Date());
        if (StringUtils.isBlank(labSyInfo.getSyPerson1())) {
            labSyInfo.setSyPerson1(amPersonalInfo.getFullName());
        }

        //待复检电泳检材信息
        List<LabSySample> labSySampleList = new ArrayList<>();
        List<LimsSampleInfoDna> limsSampleInfoDnaList = (List<LimsSampleInfoDna>)session.getAttribute("limsSampleInfoDnaList");
        if (ListUtils.isNotNullAndEmptyList(limsSampleInfoDnaList)){
            for (int i = 0; i<limsSampleInfoDnaList.size();i++){
                LabSySample labSySample = new LabSySample();
                labSySample.setSampleId(limsSampleInfoDnaList.get(i).getSampleId());
                labSySample.setSampleNo(limsSampleInfoDnaList.get(i).getSampleNo());
                labSySample.setSampleName(limsSampleInfoDnaList.get(i).getSampleName());
                labSySample.setSampleType(limsSampleInfoDnaList.get(i).getSampleType());
                labSySample.setSamplePostion(Constants.SYTABLE_POSTION_ARR_VER[i+4]);
                labSySampleList.add(labSySample);
            }
        }



        EquipmentNameInfo equipmentNameInfo = new EquipmentNameInfo();
        //阶段
        equipmentNameInfo.setPhase(Constants.EXPERIMENTAL_STAGE_03);
        equipmentNameInfo.setOrgId(userOrgId);

        equipmentNameInfo.setEquipmentNo("DYY");
        List<EquipmentNameInfo> panelListDYY = equipmentNameInfoService.selectEquipmentNameInfoListByTypeNo(equipmentNameInfo);

        view.addObject("panelListDYY", panelListDYY);
        view.addObject("labSyInfo", labSyInfo);
        view.addObject("labSySampleList", labSySampleList);
        view.addObject("reinspectionId","1");
        view.setViewName("manualExtraction/syExperiment");

        return view;
    }

    /**
     * 保存上样任务记录
     * @return
     */
    @RequestMapping(value = "/saveSyInfo", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String,Object> saveSyInfo(HttpServletRequest request, @RequestBody SyRecordDataModel syRecordDataModel) {

        Map<String, Object> map = new HashMap<>();
        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();

        LabSyInfo labSyInfo = syRecordDataModel.getLabSyInfo();
        List<LabSySample> labSySampleList = syRecordDataModel.getLabSySampleList();

        String userOrgId = operateUser.getOrgId();
        //将当前用户org_id设置进query
        if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
            userOrgId = "110230000000";
        }

        if (labSyInfo != null) {
            if (StringUtils.isNotBlank(labSyInfo.getSyId())){
                //修改数据
                LabSyInfo syInfo = labSyInfoService.selectByPrimaryKey(labSyInfo.getSyId());
                syInfo.setSyPerson1(labSyInfo.getSyPerson1());
                syInfo.setSampleCount((short)labSySampleList.size());
                syInfo.setSyString(labSyInfo.getSyString());
                syInfo.setElecInstrument(labSyInfo.getElecInstrument());
                syInfo.setProduct(labSyInfo.getProduct());
                syInfo.setFormamide(labSyInfo.getFormamide());
                syInfo.setInternalStandardUl(labSyInfo.getInternalStandardUl());
                syInfo.setInternalStandard(labSyInfo.getInternalStandard());
                syInfo.setSampleCount(labSyInfo.getSampleCount());
                syInfo.setSyStage(labSyInfo.getSyStage());
                syInfo.setSyEndDatetime(labSyInfo.getSyEndDatetime());
                syInfo.setUpdateDatetime(new Date());
                try {
                    if (operateUser != null) {
                        syInfo.setUpdatePerson(operateUser.getLoginName());
                        syInfo.setOrgId(userOrgId);
                    }
                    labSyInfoService.updateByPrimaryKey(syInfo );
                }catch (Exception e) {
                    logger.info("---更新上样任务记录id为[" + labSyInfo.getSyId() + "]的信息失败！---");
                    logger.error("更新上样任务记录失败！", e);
                    map.put("success",false);
                    throw e;
                }

            }else{
                //添加数据
                labSyInfo.setSyId(UUID.randomUUID().toString());
                labSyInfo.setSyNo(seqNoGenerateService.getNextSyNoVal("-", userOrgId));
                labSyInfo.setSampleCount((short)labSySampleList.size());
                //创建时间
                labSyInfo.setCreateDatetime(new Date());
                if (operateUser != null) {
                    labSyInfo.setCreatePerson(operateUser.getLoginName());
                    labSyInfo.setOrgId(userOrgId);
                }
                try {
                    labSyInfoService.insert(labSyInfo);
                }catch (Exception e) {
                    logger.info("----插入上样任务记录id为[" + labSyInfo.getSyId() + "]的信息失败！----");
                    logger.error("插入上样任务记录失败！", e);
                    map.put("success",false);
                    throw e;
                }
            }
        }

        if (ListUtils.isNotNullAndEmptyList(labSySampleList)) {
            for (LabSySample lss : labSySampleList) {
                if (StringUtils.isNotBlank(lss.getId())) {
                    LabSySample labSySample = labSySampleService.selectByPrimaryKey(lss.getId());
                    //新值赋到旧值
                    labSySample.setSamplePostion(lss.getSamplePostion());
                    labSySample.setUpdateDatetime(new Date());
                    if (operateUser != null) {
                        labSySample.setUpdatePerson(operateUser.getLoginName());
                    }
                    try {
                        labSySampleService.updateByPrimaryKey(labSySample);
                    }catch (Exception e) {
                        logger.info("----更新上样检材记录id为[" + labSySample.getId() + "]的信息失败！-----");
                        logger.error("更新上样材记录失败！", e);
                        map.put("success",false);
                        throw e;
                    }
                }else {
                    lss.setId(UUID.randomUUID().toString());
                    lss.setSyId(labSyInfo.getSyId());
                    if (operateUser != null) {
                        lss.setCreatePerson(operateUser.getLoginName());
                    }
                    lss.setCreateDatetime(new Date());

                    try {
                        labSySampleService.insert(lss);
                    }catch (Exception e) {
                        logger.info("----插入电泳检材任务记录id为[" + labSyInfo.getSyId() + "]的信息失败！----");
                        logger.error("插入电泳检材任务记录失败！", e);
                        map.put("success",false);
                        throw e;
                    }
                }
            }
        }

        map.put("syId", labSyInfo.getSyId());
        map.put("success",true);

        return map;
    }

    /**
     * 根据试剂盒查询相应的扩增体系
     * @param experimentalParameter
     * @return
     */
    @RequestMapping(value = "/selectPcrSystem", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String,Object> selectPcrSystem(@RequestBody ExperimentalParameter experimentalParameter, String operate) {
        Map<String, Object> map = new HashMap<>();

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if(operateUser == null){
            map.put("date", "redirect:/login.html?timeoutFlag=1");
            return  map;
        }
        try {
            experimentalParameter.setExperimentalStage(Constants.EXPERIMENTAL_STAGE_02);
            experimentalParameter.setExperimentalType(Constants.EXPERIMENTAL_PCR_SYSTEM);
            String userOrgId = operateUser.getOrgId();
            //将当前用户org_id设置进query
            if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
                userOrgId = "110230000000";
            }
            experimentalParameter.setOrgId(userOrgId);
            if (StringUtils.isBlank(experimentalParameter.getParameterName())) {
                experimentalParameter.setParameterName(null);
            }
            List<ExperimentalParameter> experimentalParameterList = experimentalParameterService.selectList(experimentalParameter);
            if (StringUtils.isBlank(operate)) {
                if (ListUtils.isNotNullAndEmptyList(experimentalParameterList)) {
                    ExperimentalParameter parameter = experimentalParameterList.get(0);
                    String parameterValue = parameter.getParameterValue();

                    Map<String, Object> mapParameter = (Map) JSON.parse(parameterValue);
                    map.put("mapParameter",mapParameter);
                }
            }
            map.put("experimentalParameterList",experimentalParameterList);
            map.put("success",true);
        }catch (Exception e) {
            logger.error("根据试剂盒查询相应的扩增体系失败！", e);
            map.put("success",false);
            throw e;
        }

        return map;
    }

    /**
     * 根据试剂盒查询相应的电泳参数
     * @param experimentalParameter
     * @return
     */
    @RequestMapping(value = "/selectSyKit", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String,Object> selectSyKit(@RequestBody ExperimentalParameter experimentalParameter, String operate) {
        Map<String, Object> map = new HashMap<>();

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if(operateUser == null){
            map.put("date", "redirect:/login.html?timeoutFlag=1");
            return  map;
        }
        try {
            experimentalParameter.setExperimentalStage(Constants.EXPERIMENTAL_STAGE_03);
            experimentalParameter.setExperimentalType(Constants.EXPERIMENTAL_KIT_PARAMETERS);
            String userOrgId = operateUser.getOrgId();
            //将当前用户org_id设置进query
            if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
                userOrgId = "110230000000";
            }
            experimentalParameter.setOrgId(userOrgId);

            List<ExperimentalParameter> experimentalParameterList = experimentalParameterService.selectList(experimentalParameter);
            if (ListUtils.isNotNullAndEmptyList(experimentalParameterList)) {
                ExperimentalParameter parameter = experimentalParameterList.get(0);
                String parameterValue = parameter.getParameterValue();

                Map<String, Object> mapParameter = (Map) JSON.parse(parameterValue);
                map.put("mapParameter",mapParameter);
            }
            map.put("success",true);
        }catch (Exception e) {
            logger.error("根据试剂盒查询相应的扩增体系失败！", e);
            map.put("success",false);
            throw e;
        }

        return map;
    }

}
