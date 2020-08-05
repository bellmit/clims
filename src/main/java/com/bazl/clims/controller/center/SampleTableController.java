package com.bazl.clims.controller.center;

import com.bazl.clims.common.Constants;
import com.bazl.clims.controller.BaseController;
import com.bazl.clims.model.LoaUserInfo;
import com.bazl.clims.model.PageInfo;
import com.bazl.clims.model.po.ExtractPlate;
import com.bazl.clims.model.po.SampleInfo;
import com.bazl.clims.model.po.SampleInfoModel;
import com.bazl.clims.model.po.SampleTable;
import com.bazl.clims.model.vo.SampleInfoVo;
import com.bazl.clims.model.vo.SampleTableVo;
import com.bazl.clims.service.SampleInfoService;
import com.bazl.clims.service.SampleTableService;
import com.bazl.clims.service.SeqNoGenerateService;
import com.bazl.clims.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.*;

/**
 * @Author: chenzeliang
 * @Date: 2020/3/31 13:58
 * @Version: 1.0
 */
@Controller
@RequestMapping("/center/sample")
public class SampleTableController extends BaseController {
    @Autowired
    SampleTableService sampleTableService;
    @Autowired
    InitializationData initializationData;
    @Autowired
    SeqNoGenerateService seqNoGenerateService;
    @Autowired
    DownloadFileUtils downloadFileUtils;
    @Autowired
    SampleInfoService sampleInfoService;

    /**
     * 样本表列表
     * @param request
     * @param query
     * @param pageInfo
     * @return
     * @throws ParseException
     */
    @RequestMapping("/sampleTableList.html")
    public ModelAndView sampleTableList(HttpServletRequest request, SampleTableVo query, PageInfo pageInfo) throws ParseException {
        HttpSession session = request.getSession();//获取session
        ModelAndView modelAndView = initializationData.initData(Constants.DICR_TYPE);
        /*ModelAndView modelAndView = InitializationData.initDictList();*/
        if (StringUtils.isBlank(query.getTabValue())) {
            query.setTabValue("sampleTableTab");
        } else  {
            query.setTabValue(query.getTabValue().trim());
        }
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo loaUserInfo = (LoaUserInfo) subject.getPrincipal();
        query.getEntity().setCreatePerson(loaUserInfo.getLoginName());

        List<SampleTableVo> sampleTableVoList = sampleTableService.selectVoList(query, pageInfo);
        int totalCnt = sampleTableService.selectVOCount(query);

        modelAndView.addObject("sampleTableVoList", sampleTableVoList);
        modelAndView.addObject("pageInfo", pageInfo.updatePageInfo(totalCnt));
        modelAndView.addObject("query", query);
        modelAndView.setViewName("center/sampleTable/sampleTableList");
        return modelAndView;
    }

    /**
     * 获取创建的板号
     * @param request
     * @return
     */
    @RequestMapping(value="/createOrReturnNewBoardNo.html",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public Map<String,Object> createOrReturnNewBoardNo(HttpServletRequest request, String operate,String flag){
        Map<String,Object> result = new HashMap<>();
        String boardNo = "";
        try {
            if ("create".equals(operate)) {
                boardNo = seqNoGenerateService.getNextSampleBoardNoVal(DateUtils.dateToString(new Date(), DateUtils.DATE));
                result.put("boardNo",boardNo);
            }else if ("return".equals(operate)&&"create".equals(flag)) {
                seqNoGenerateService.returnCurrentValByCode(DateUtils.dateToString(new Date(), DateUtils.DATE));
            }
            result.put("success",true);
        }catch (Exception e){
            result.put("success",false);
            logger.error("getNewBoardNo error",e);
        }

        return result;
    }

    /**
     * 导入样本表
     * @param request
     * @param file
     * @return
     */
    @RequestMapping(value="/uploadSampleTable.html",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> uploadSampleTable(HttpServletRequest request, @RequestParam(value = "upFile", required = false) MultipartFile[] file, String elution){
        Map<String,Object> result = new HashMap<>();
        try {
            if (file.length > 0) {
                List<SampleInfoModel> allList = new ArrayList<>();
                for (int i = 0; i < file.length; i++) {
                    List<SampleInfoModel> sampleInfoModelList = downloadFileUtils.importSampleTable(request, file[i]);
                    allList.addAll(sampleInfoModelList);
                }
                //遍历解析出来的list
                if (ListUtils.isNotNullAndEmptyList(allList)) {
                    for (SampleInfoModel sampleInfoModel : allList) {
                        insertData(sampleInfoModel, elution);
                    }
                    result.put("success",true);
                }else {
                    result.put("success",false);
                }
            }else {
                result.put("success",false);
            }
        }catch (Exception e){
            result.put("success",false);
            logger.error("uploadSampleTable error",e);
        }

        return result;
    }

    /**
     * 插入数据
     * @param sampleInfoModel
     */
    public void insertData (SampleInfoModel sampleInfoModel, String elution) throws Exception {
        if (sampleInfoModel != null) {
            SampleTable sampleTable = sampleInfoModel.getSampleTable();
            sampleTable.setId(UUID.randomUUID().toString());
            sampleTable.setHoleNum(Constants.HOLE_NUM_24);
            sampleTable.setElutionDefault(elution);

            sampleTableService.insert(sampleTable);

            List<SampleInfo> sampleInfoList = sampleInfoModel.getSampleInfoList();
            if (ListUtils.isNotNullAndEmptyList(sampleInfoList)) {
                int count = 1;
                for (SampleInfo sampleInfo : sampleInfoList) {
                    sampleInfo.setId(UUID.randomUUID().toString());
                    sampleInfo.setSampleTableId(sampleTable.getId());
                    sampleInfo.setBoardNo(sampleTable.getBoardNo());
                    sampleInfo.setSamplePlateSort(Constants.SAMPLE_PLATE_SORT_1);
                    sampleInfo.setConfirmatoryMethod(Constants.DICT_01);
                    sampleInfo.setPreExperimentalMethod(Constants.DICT_01);
                    sampleInfo.setSampleTransferMethod(Constants.DICT_01);
                    sampleInfo.setSampleProperty(Constants.DICT_01);
                    sampleInfo.setElution(sampleTable.getElutionDefault());
                    sampleInfo.setCreatePerson(sampleTable.getCreatePerson());
                    sampleInfo.setCreateDatetime(DateUtils.addDate(count));
                    count++;

                    sampleInfoService.insert(sampleInfo);
                }
            }
        }
    }

    /**
     * 保存或更新样本板信息
     * @param request
     * @param sampleTableList
     * @return
     */
    @RequestMapping(value="/saveSampleTable.html",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public Map<String,Object> saveSampleTable(HttpServletRequest request,@RequestBody List<SampleTable> sampleTableList){
        Map<String,Object> result = new HashMap<>();

        try{
            if (ListUtils.isNotNullAndEmptyList(sampleTableList)) {
                for (SampleTable sampleTable : sampleTableList) {
                    sampleTable = resetSampleTable(sampleTable);
                    Subject subject = SecurityUtils.getSubject();
                    LoaUserInfo loaUserInfo = (LoaUserInfo) subject.getPrincipal();
                    String loginName = loaUserInfo.getLoginName();
                    if(StringUtils.isBlank(sampleTable.getId())){
                        sampleTable.setId(UUID.randomUUID().toString());
                        sampleTable.setCreatePerson(loginName);
                        sampleTable.setCreateDatetime(new Date());
                        sampleTable.setIsCreate("0");
                        sampleTableService.insert(sampleTable);
                    }else{
                        SampleTable sampleTableInfo = sampleTableService.selectByPrimaryKey(sampleTable.getId());
                        if (sampleTableInfo != null) {
                            sampleTableInfo.setBoardNo(sampleTable.getBoardNo());
                            sampleTableInfo.setHoleNum(sampleTable.getHoleNum());
                            sampleTableInfo.setElutionDefault(sampleTable.getElutionDefault());
                            sampleTableInfo.setOperationPerson(loginName);
                            sampleTableInfo.setOperationDatetime(new Date());
                            sampleTableService.updateByPrimaryKey(sampleTableInfo);
                        }
                    }
                }
                result.put("success",true);
            }else {
                result.put("success","no");
            }
        }catch (Exception e){
            result.put("success",false);
            logger.error("saveSampleTable error",e);
        }

        return result;
    }

    /**
     * 删除样本板信息
     * @param request
     * @param sampleTableList
     * @return
     */
    @RequestMapping(value="/deleteSampleTable.html",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public Map<String,Object> deleteSampleTable(HttpServletRequest request,@RequestBody List<SampleTable> sampleTableList){
        Map<String,Object> result = new HashMap<>();

        try{
            if (ListUtils.isNotNullAndEmptyList(sampleTableList)) {
                for (SampleTable sampleTable : sampleTableList) {
                    sampleTableService.deleteFlagById(sampleTable.getId());
                }
                result.put("success",true);
            }else {
                result.put("success","no");
            }
        }catch (Exception e){
            result.put("success",false);
            logger.error("deleteSampleTable error",e);
        }

        return result;
    }

    //获取样本表列表数据
    @RequestMapping(value = "/getSampleTableList.html", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> getSampleTableList(HttpServletRequest request, @RequestBody SampleTableVo query, PageInfo pageInfo) {
        Map<String,Object> result = new HashMap<>();

        try {
            Subject subject = SecurityUtils.getSubject();
            LoaUserInfo loaUserInfo = (LoaUserInfo) subject.getPrincipal();
            query.getEntity().setCreatePerson(loaUserInfo.getLoginName());
            //截取输入板号的空格
            if (query.getEntity().getBoardNo()!=null){
                query.getEntity().setBoardNo(query.getEntity().getBoardNo().trim());
            }
            List<SampleTableVo> sampleTableVoList = sampleTableService.selectVoList(query, pageInfo);
            int totalCnt = sampleTableService.selectVOCount(query);

            result.put("sampleList", sampleTableVoList);
            result.put("pageInfo", pageInfo.updatePageInfo(totalCnt));
            result.put("success",true);
        }catch (Exception e) {
            result.put("success",false);
            logger.error("getSampleTableList error",e);
        }

        return result;
    }

    public SampleTable resetSampleTable(SampleTable sampleTable){
        if(StringUtils.isBlank(sampleTable.getBoardNo())){
            sampleTable.setBoardNo(null);
        }else {
            sampleTable.setBoardNo(sampleTable.getBoardNo().trim());
        }

        if(StringUtils.isBlank(sampleTable.getHoleNum())){
            sampleTable.setHoleNum(null);
        }else {
            sampleTable.setHoleNum(sampleTable.getHoleNum().trim());
        }

        if(StringUtils.isBlank(sampleTable.getElutionDefault())){
            sampleTable.setElutionDefault(null);
        }else {
            sampleTable.setElutionDefault(sampleTable.getElutionDefault().trim());
        }

        if(StringUtils.isBlank(sampleTable.getCreatePerson())){
            Subject subject = SecurityUtils.getSubject();
            LoaUserInfo loaUserInfo = (LoaUserInfo) subject.getPrincipal();
            sampleTable.setCreatePerson(loaUserInfo.getLoginName());
        }else {
            sampleTable.setCreatePerson(sampleTable.getCreatePerson().trim());
        }

        return sampleTable;
    }
    /**
     * 导出csv文件
     * @param request
     * @param response
     * @param sampleInfoVo
     */
    @RequestMapping("/exportCSVFile2.html")
    public void exportCSVFile2(HttpServletRequest request, HttpServletResponse response, SampleInfoVo sampleInfoVo) {
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo loaUserInfo = (LoaUserInfo) subject.getPrincipal();
        sampleInfoVo.getEntity().setCreatePerson(loaUserInfo.getLoginName());
        if (sampleInfoVo.getEntity().getExtractPlateId()!=null){
            sampleInfoVo.getEntity().setSampleTableId(sampleInfoVo.getEntity().getExtractPlateId());
            sampleInfoVo.getEntity().setExtractPlateId(null);
        }
        List<SampleInfoVo> sampleInfoVoList = sampleInfoService.selectVoListBySampleInfo(sampleInfoVo);
        sampleInfoVoList = TestProcessUtils.boardSortAgain(sampleInfoVoList, Constants.STAGE_TQ);
        SampleTable sampleTable = sampleTableService.selectByPrimaryKey(sampleInfoVo.getEntity().getSampleTableId());
        ExtractPlate extractPlate=new ExtractPlate();
        extractPlate.setBoardNo(sampleTable.getBoardNo());


        downloadFileUtils.exportCSVFile(request, response, sampleInfoVoList, extractPlate);

    }
}
