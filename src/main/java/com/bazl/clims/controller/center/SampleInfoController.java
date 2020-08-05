package com.bazl.clims.controller.center;

import com.alibaba.fastjson.JSONArray;
import com.bazl.clims.common.Constants;
import com.bazl.clims.model.LoaUserInfo;
import com.bazl.clims.model.PageInfo;
import com.bazl.clims.model.po.LimsSampleGene;
import com.bazl.clims.model.po.LimsSampleInfoDna;
import com.bazl.clims.model.po.SampleInfo;
import com.bazl.clims.model.po.SampleTable;
import com.bazl.clims.model.vo.SampleInfoVo;
import com.bazl.clims.service.LimsSampleGeneService;
import com.bazl.clims.service.LimsSampleInfoDnaService;
import com.bazl.clims.service.SampleInfoService;
import com.bazl.clims.service.SampleTableService;
import com.bazl.clims.utils.*;
import com.bazl.clims.controller.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.*;


/**
 * @Author: chenzeliang
 * @Date: 2020/3/30 17:07
 * @Version: 1.0
 */
@Controller
@RequestMapping("/center/sample")
public class SampleInfoController extends BaseController {
    @Autowired
    SampleInfoService sampleInfoService;
    @Autowired
    InitializationData initializationData;
    @Autowired
    DownloadFileUtils downloadFileUtils;
    @Autowired
    SampleTableService sampleTableService;

    @Autowired
    LimsSampleInfoDnaService limsSampleInfoDnaService;

    @Autowired
    LimsSampleGeneService limsSampleGeneService;

    /**
     * 检材列表
     * @param request
     * @param query
     * @param pageInfo
     * @return
     * @throws ParseException
     */
    @RequestMapping("/sampleInfoList.html")
    public ModelAndView sampleInfoList(HttpServletRequest request, SampleInfoVo query, PageInfo pageInfo) throws ParseException {
        /*ModelAndView modelAndView = initializationData.initData(Constants.DICR_TYPE);*/
        ModelAndView modelAndView = InitializationData.initDictList();
        query = resetSampleInfoVo(query);

        List<SampleInfoVo> sampleInfoVoList = sampleInfoService.selectVoList(query, pageInfo);
        int totalCnt = sampleInfoService.selectVOCount(query);

        modelAndView.addObject("query", query);
        modelAndView.addObject("sampleInfoVoList", sampleInfoVoList);
        modelAndView.addObject("pageInfo", pageInfo.updatePageInfo(totalCnt));
        modelAndView.setViewName("center/sampleInfo/sampleInfoList");
        return modelAndView;
    }

    /**
     * 添加检材页面
     * @param request
     * @param sampleInfoVo
     * @return
     */
    @RequestMapping("/addSample.html")
    public ModelAndView addSample(HttpServletRequest request, SampleInfoVo sampleInfoVo) {
        ModelAndView modelAndView = initializationData.initData(Constants.DICR_TYPE);
        /*ModelAndView modelAndView = InitializationData.initDictList();*/
//获取当前登录用户
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo loaUserInfo = (LoaUserInfo) subject.getPrincipal();
        sampleInfoVo.getEntity().setCreatePerson(loaUserInfo.getLoginName());

        List<SampleInfoVo> sampleInfoVoList = sampleInfoService.selectVoListBySampleInfo(sampleInfoVo);

        SampleTable sampleTable = sampleTableService.selectByPrimaryKey(sampleInfoVo.getEntity().getSampleTableId());

        List<Map<String, Object>> tempList24 = new ArrayList<>();
        List<Map<String, Object>> tempList96 = new ArrayList<>();
        if (StringUtils.isNotBlank(sampleInfoVo.getHoleNum())) {
            if (sampleInfoVo.getHoleNum().equals(Constants.HOLE_NUM_24)) {
                tempList24 = TestProcessUtils.boardSort(sampleInfoVoList, Integer.parseInt(Constants.HOLE_NUM_24), sampleInfoVo, Constants.STAGE_QT);
                modelAndView.setViewName("center/sampleInfo/addSampleInfo24");
            }else {
                tempList96 = TestProcessUtils.boardSort(sampleInfoVoList, Integer.parseInt(Constants.HOLE_NUM_96), sampleInfoVo, Constants.STAGE_QT);
                modelAndView.setViewName("center/sampleInfo/addSampleInfo96");
            }
        }

        modelAndView.addObject("sampleTable", sampleTable);
        modelAndView.addObject("sampleInfoVo", sampleInfoVo);
        modelAndView.addObject("tempList24", tempList24);
        modelAndView.addObject("tempList96", tempList96);
        modelAndView.addObject("sampleInfoVoList", sampleInfoVoList);
        return modelAndView;
    }

    /**
     * 查看样本板孔位信息
     * @param request
     * @param sampleInfoVo
     * @return
     */
    @RequestMapping("/viewSampleBoard.html")
    public ModelAndView viewSampleBoard(HttpServletRequest request, SampleInfoVo sampleInfoVo, String stage) {
        ModelAndView modelAndView = initializationData.initData(Constants.DICR_TYPE);
        /*ModelAndView modelAndView = InitializationData.initDictList();*/
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo loaUserInfo = (LoaUserInfo) subject.getPrincipal();
        sampleInfoVo.getEntity().setCreatePerson(loaUserInfo.getLoginName());

        List<SampleInfoVo> sampleInfoVoList = sampleInfoService.selectVoListBySampleInfo(sampleInfoVo);
        sampleInfoVo.getEntity().setBoardNo(sampleInfoVo.getBoardNoName());

        SampleTable sampleTable = new SampleTable();

        List<Map<String, Object>> tempList24 = new ArrayList<>();
        List<Map<String, Object>> tempList96 = new ArrayList<>();
        if (StringUtils.isNotBlank(sampleInfoVo.getHoleNum())) {
            if (sampleInfoVo.getHoleNum().equals(Constants.HOLE_NUM_24)) {
                tempList24 = TestProcessUtils.boardSort(sampleInfoVoList, Integer.parseInt(Constants.HOLE_NUM_24), sampleInfoVo, stage);
                modelAndView.setViewName("center/sampleInfo/addSampleInfo24");
            }else {
                tempList96 = TestProcessUtils.boardSort(sampleInfoVoList, Integer.parseInt(Constants.HOLE_NUM_96), sampleInfoVo, stage);
                modelAndView.setViewName("center/sampleInfo/addSampleInfo96");
            }
        }

        modelAndView.addObject("sampleTable", sampleTable);
        modelAndView.addObject("sampleInfoVo", sampleInfoVo);
        modelAndView.addObject("tempList24", tempList24);
        modelAndView.addObject("tempList96", tempList96);
        modelAndView.addObject("sampleInfoVoList", sampleInfoVoList);
        return modelAndView;
    }

    /**
     * 得到样本列表信息
     * @param request
     * @param sampleInfoVo
     * @param stage
     * @return
     */
    @RequestMapping(value = "/getSampleInfoList.html", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> getSampleInfoList(HttpServletRequest request, @RequestBody SampleInfoVo sampleInfoVo, String stage) {
        Map<String,Object> result = new HashMap<>();

        try {
            Subject subject = SecurityUtils.getSubject();
            LoaUserInfo loaUserInfo = (LoaUserInfo) subject.getPrincipal();
            sampleInfoVo.getEntity().setCreatePerson(loaUserInfo.getLoginName());

            List<SampleInfoVo> sampleInfoVoList = sampleInfoService.selectVoListBySampleInfo(sampleInfoVo);
            if (ListUtils.isNotNullAndEmptyList(sampleInfoVoList) && StringUtils.isNotBlank(stage)) {
                sampleInfoVoList = TestProcessUtils.holeSort(sampleInfoVoList, stage);
            }
            SampleTable sampleTable = sampleTableService.selectByPrimaryKey(sampleInfoVo.getEntity().getSampleTableId());
            if (sampleTable == null) {
                sampleTable = new SampleTable();
                sampleTable.setIsCreate(Constants.IS_CREATE_1);
            }
            result.put("sampleInfoVoList",sampleInfoVoList);
            result.put("sampleTable",sampleTable);
            result.put("success",true);
        }catch (Exception e) {
            result.put("success",false);
            logger.error("getSampleInfoList error",e);
        }

        return result;
    }

    /**
     * 得到整板信息
     * @param request
     * @param sampleInfoVo
     * @param stage
     * @return
     */
    @RequestMapping(value = "/getAllBoardList.html", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> getAllBoardList(HttpServletRequest request, @RequestBody SampleInfoVo sampleInfoVo, String stage) {
        Map<String,Object> result = new HashMap<>();

        try {
            Subject subject = SecurityUtils.getSubject();
            LoaUserInfo loaUserInfo = (LoaUserInfo) subject.getPrincipal();
            sampleInfoVo.getEntity().setCreatePerson(loaUserInfo.getLoginName());
            List<SampleInfoVo> sampleInfoVoList = sampleInfoService.selectVoListBySampleInfo(sampleInfoVo);
            List<Map<String, Object>> allTempList = TestProcessUtils.boardSortAll(sampleInfoVoList, Integer.parseInt(Constants.HOLE_NUM_96), sampleInfoVo, stage);
            result.put("allTempList",allTempList);
            result.put("success",true);
        }catch (Exception e) {
            result.put("success",false);
            logger.error("getSampleInfoList error",e);
        }

        return result;
    }

    /**
     * 导出24孔样本表
     * @param request
     * @param response
     * @param sampleInfoVo
     */
    @RequestMapping("/exportSampleTable.html")
    public void exportSampleTable(HttpServletRequest request, HttpServletResponse response, SampleInfoVo sampleInfoVo) {

        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo loaUserInfo = (LoaUserInfo) subject.getPrincipal();
        sampleInfoVo.getEntity().setCreatePerson(loaUserInfo.getLoginName());
        List<SampleInfoVo> sampleInfoVoList = sampleInfoService.selectVoListBySampleInfo(sampleInfoVo);
        SampleTable sampleTable = sampleTableService.selectByPrimaryKey(sampleInfoVo.getEntity().getSampleTableId());

        downloadFileUtils.exportSampleTable(request, response, sampleInfoVoList, sampleTable);

    }

    /**
     * 导出检材信息
     * @param request
     * @param response
     * @param sampleInfoList
     * @return
     */
    @RequestMapping(value="/exportSampleInfoRecord.html")
    public void exportSampleInfoRecord(HttpServletRequest request, HttpServletResponse response, @RequestParam("sampleInfoList") String sampleInfoList){

        try{
            List<SampleInfo> sampleInfos = JSONArray.parseArray(sampleInfoList, SampleInfo.class);
            if (ListUtils.isNotNullAndEmptyList(sampleInfos)) {
                List<SampleInfoVo> sampleInfoVoList = new ArrayList<>();
                for (SampleInfo sampleInfo : sampleInfos) {
                    SampleInfoVo sampleInfoVo = new SampleInfoVo();
                    sampleInfoVo.getEntity().setId(sampleInfo.getId());
                    Subject subject = SecurityUtils.getSubject();
                    LoaUserInfo loaUserInfo = (LoaUserInfo) subject.getPrincipal();
                    sampleInfoVo.getEntity().setCreatePerson(loaUserInfo.getLoginName());
                    List<SampleInfoVo> sampleInfoVos = sampleInfoService.selectVoListBySampleInfo(sampleInfoVo);
                    sampleInfoVoList.addAll(sampleInfoVos);
                }
                downloadFileUtils.exportSampleInfoRecord(request, response, sampleInfoVoList);
            }
        }catch (Exception e){
            logger.error("exportSampleInfoRecord error",e);
        }

    }


    /**
     * 保存或更新样本信息
     * @param request
     * @param sampleInfoList
     * @return
     */
    @RequestMapping(value="/saveSampleInfo.html",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public Map<String,Object> saveSampleInfo(HttpServletRequest request, @RequestBody List<SampleInfo> sampleInfoList, String stage){
        Map<String,Object> result = new HashMap<>();

        try{
            if (ListUtils.isNotNullAndEmptyList(sampleInfoList)) {
                String sampleNo = null;
                String id = null;
                String elutionName = null;
                SampleInfo si = new SampleInfo();
                for (SampleInfo sampleInfo : sampleInfoList) {
                    sampleInfo = resetSampleInfo(sampleInfo);
                    Subject subject = SecurityUtils.getSubject();
                    LoaUserInfo loaUserInfo = (LoaUserInfo) subject.getPrincipal();
                    String loginName = loaUserInfo.getLoginName();
                    if(StringUtils.isBlank(sampleInfo.getId())){
                        sampleInfo.setId(UUID.randomUUID().toString());
                        sampleInfo = assignment(sampleInfo, stage);
                        sampleInfoService.insert(sampleInfo);
                    }else{
                        SampleInfo sample = sampleInfoService.selectByPrimaryKey(sampleInfo.getId());
                        if (sample != null) {
                            sample.setBoardNo(sampleInfo.getBoardNo());
                            sample.setSampleNo(sampleInfo.getSampleNo());
                            sample.setElution(sampleInfo.getElution());
                            sample.setSamplePlateSort(sampleInfo.getSamplePlateSort());
                            sample.setPreExperimentalMethod(sampleInfo.getPreExperimentalMethod());
                            sample.setConfirmatoryMethod(sampleInfo.getConfirmatoryMethod());
                            sample.setSampleTransferMethod(sampleInfo.getSampleTransferMethod());
                            sample.setSampleProperty(sampleInfo.getSampleProperty());
                            sample.setOperationPerson(loginName);
                            sample.setOperationDatetime(new Date());
                            sample = assignment(sampleInfo, stage);
                            sampleInfoService.updateByPrimaryKey(sample);
                        }
                    }

                    si = sampleInfo;
                }
                result.put("success",true);
                result.put("sampleInfo", si);
            }else {
                result.put("success","no");
            }
        }catch (Exception e){
            result.put("success",false);
            logger.error("saveSampleInfo error",e);
        }

        return result;
    }

    /**
     * 删除样本信息
     * @param request
     * @param sampleInfoList
     * @return
     */
    @RequestMapping(value="/deleteSampleInfo.html",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public Map<String,Object> deleteSampleInfo(HttpServletRequest request,@RequestBody List<SampleInfo> sampleInfoList){
        Map<String,Object> result = new HashMap<>();

        try{
            if (ListUtils.isNotNullAndEmptyList(sampleInfoList)) {
                for (SampleInfo sampleInfo : sampleInfoList) {
                    sampleInfoService.deleteFlagById(sampleInfo.getId());
                }
                result.put("success",true);
            }else {
                result.put("success","no");
            }
        }catch (Exception e){
            result.put("success",false);
            logger.error("deleteSampleInfo error",e);
        }

        return result;
    }

    public SampleInfoVo resetSampleInfoVo(SampleInfoVo query) {

        if(StringUtils.isBlank(query.getEntity().getBoardNo())){
            query.getEntity().setBoardNo(null);
        }else {
            query.getEntity().setBoardNo(query.getEntity().getBoardNo().trim());
        }

        if(StringUtils.isBlank(query.getEntity().getSampleNo())){
            query.getEntity().setSampleNo(null);
        }else {
            query.getEntity().setSampleNo(query.getEntity().getSampleNo().trim());
        }

        return query;
    }

    public SampleInfo resetSampleInfo(SampleInfo sampleInfo){
        if(StringUtils.isBlank(sampleInfo.getBoardNo())){
            sampleInfo.setBoardNo(null);
        }else {
            sampleInfo.setBoardNo(sampleInfo.getBoardNo().trim());
        }

        if(StringUtils.isBlank(sampleInfo.getSampleNo())){
            sampleInfo.setSampleNo(null);
        }else {
            sampleInfo.setSampleNo(sampleInfo.getSampleNo().trim());
        }

        if(StringUtils.isBlank(sampleInfo.getElution())){
            sampleInfo.setElution(null);
        }else {
            sampleInfo.setElution(sampleInfo.getElution().trim());
        }

        if(StringUtils.isBlank(sampleInfo.getSamplePlateSort())){
            sampleInfo.setSamplePlateSort(null);
        }else {
            sampleInfo.setSamplePlateSort(sampleInfo.getSamplePlateSort().trim());
        }

        if(StringUtils.isBlank(sampleInfo.getPreExperimentalMethod())){
            sampleInfo.setPreExperimentalMethod(null);
        }else {
            sampleInfo.setPreExperimentalMethod(sampleInfo.getPreExperimentalMethod().trim());
        }

        if(StringUtils.isBlank(sampleInfo.getConfirmatoryMethod())){
            sampleInfo.setConfirmatoryMethod(null);
        }else {
            sampleInfo.setConfirmatoryMethod(sampleInfo.getConfirmatoryMethod().trim());
        }

        if(StringUtils.isBlank(sampleInfo.getSampleTransferMethod())){
            sampleInfo.setSampleTransferMethod(null);
        }else {
            sampleInfo.setSampleTransferMethod(sampleInfo.getSampleTransferMethod().trim());
        }

        if(StringUtils.isBlank(sampleInfo.getSampleProperty())){
            sampleInfo.setSampleProperty(null);
        }else {
            sampleInfo.setSampleProperty(sampleInfo.getSampleProperty().trim());
        }

        if(sampleInfo.getSamplePlateLocation() == null){
            sampleInfo.setSamplePlateLocation(null);
        }else {
            sampleInfo.setSamplePlateLocation(sampleInfo.getSamplePlateLocation());
        }

        if(StringUtils.isBlank(sampleInfo.getCreatePerson())){
            Subject subject = SecurityUtils.getSubject();
            LoaUserInfo loaUserInfo = (LoaUserInfo) subject.getPrincipal();
            sampleInfo.setCreatePerson(loaUserInfo.getLoginName());
        }else {
            sampleInfo.setCreatePerson(sampleInfo.getCreatePerson().trim());
        }

        if (sampleInfo.getCreateDatetime() == null) {
            sampleInfo.setCreateDatetime(new Date());
        }else {
            sampleInfo.setCreateDatetime(sampleInfo.getCreateDatetime());
        }

        return sampleInfo;
    }

    public SampleInfo assignment(SampleInfo sampleInfo, String stage) {
        if (Constants.STAGE_TQ.equals(stage)) {
            sampleInfo.setExtractPlateId(sampleInfo.getExtractPlateId());
            sampleInfo.setExtractLocationSort(Constants.SYTABLE_POSTION_ARR_VER_NUM[sampleInfo.getExtractLocationSort()-1]);
            sampleInfo.setExtractPlateLocation(Constants.SYTABLE_POSTION_ARR_VER[sampleInfo.getExtractLocationSort()-1]);
            /*sampleInfo.setSamplePlateLocation(null);
            sampleInfo.setSamplePlateSort(null);*/
        }else if(Constants.STAGE_KZ.equals(stage)) {
            sampleInfo.setPcrPlateId(sampleInfo.getPcrPlateId());
            sampleInfo.setPcrLocationSort(Constants.SYTABLE_POSTION_ARR_VER_NUM[sampleInfo.getPcrLocationSort()-1]);
            sampleInfo.setPcrPlateLocation(Constants.SYTABLE_POSTION_ARR_VER[sampleInfo.getPcrLocationSort()-1]);
            /*sampleInfo.setSamplePlateLocation(null);
            sampleInfo.setSamplePlateSort(null);*/
        }else if(Constants.STAGE_SY.equals(stage)) {
            sampleInfo.setSyPlateId(sampleInfo.getSyPlateId());
            sampleInfo.setSyLocationSort(Constants.SYTABLE_POSTION_ARR_VER_NUM[sampleInfo.getSyLocationSort()-1]);
            sampleInfo.setSyPlateLocation(Constants.SYTABLE_POSTION_ARR_VER[sampleInfo.getSyLocationSort()-1]);
            /*sampleInfo.setSamplePlateLocation(null);
            sampleInfo.setSamplePlateSort(null);*/
        }else {
            sampleInfo.setSampleTableId(sampleInfo.getSampleTableId());
            sampleInfo.setSampleLocationSort(Constants.SYTABLE_POSTION_ARR_VER_NUM[sampleInfo.getSampleLocationSort()-1]);
            sampleInfo.setSamplePlateLocation(Constants.SYTABLE_POSTION_ARR_VER[sampleInfo.getSampleLocationSort()-1]);
        }
        return sampleInfo;
    }

    /**
     * 根据物证编号查询检材信息以及对应的峰图
     * @param request
     * @param paramMap
     * @return
     * @throws IException
     */
    @RequestMapping(value = "/getLimsSampleInfoDnaListData", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResultBean getCaseInfoByCaseNo(HttpServletRequest request, @RequestBody Map<String, String> paramMap)throws IException {
        try {
            logger.debug("|.获取检材信息已开始.|");
            Map<String, Object> dataMap = null;
            //根据物证编号查询检材信息
            List<LimsSampleInfoDna> limsSampleInfoDnaList = limsSampleInfoDnaService.selectCaseInfoListData(paramMap.get("evidenceNo"));
            if(null != limsSampleInfoDnaList && limsSampleInfoDnaList.size()>0){
                for(LimsSampleInfoDna limsSampleInfoDna:limsSampleInfoDnaList){
                    //根据检材id查询峰图
                    List<LimsSampleGene> limsSampleGeneList = limsSampleGeneService.selectListBySampleId(limsSampleInfoDna.getSampleId());
                    if(null != limsSampleGeneList && limsSampleGeneList.size()>0){
                        if(Strings.isNotBlank(limsSampleGeneList.get(0).getImagePath())){
                            limsSampleInfoDna.setImagePath(limsSampleGeneList.get(0).getImagePath());
                        }
                    }
                }
            }
            dataMap = new LinkedHashMap<>();
            dataMap.put("limsSampleInfoDnaList", limsSampleInfoDnaList);
            logger.debug("|.获取检材信息已结束.|");
            return new ResultBean(ResultBean.CODE_SUCCESS, 0, dataMap, ResultBean.NAME_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ResultBean(ResultBean.CODE_ERROR, ErrorCode.IBOAN_REQJSON_PARSEMSG_EXCEPTION, 1, "获取信息错误!");
        }
    }
}
