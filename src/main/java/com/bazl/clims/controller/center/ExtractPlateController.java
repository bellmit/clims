package com.bazl.clims.controller.center;

import com.alibaba.fastjson.JSONObject;
import com.bazl.clims.common.Constants;
import com.bazl.clims.controller.BaseController;
import com.bazl.clims.model.LoaUserInfo;
import com.bazl.clims.model.PageInfo;
import com.bazl.clims.model.po.*;
import com.bazl.clims.model.vo.AddSampleVo;
import com.bazl.clims.model.vo.ExtractPlateVo;
import com.bazl.clims.model.vo.SampleInfoVo;
import com.bazl.clims.service.*;
import com.bazl.clims.utils.*;
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
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.*;

/**
 * @author chenzeliang
 * @date 2020/04/02.
 */
@Controller
@RequestMapping("/center/sample")
public class ExtractPlateController extends BaseController {

    @Autowired
    ExtractPlateService extractPlateService;
    @Autowired
    InitializationData initializationData;
    @Autowired
    SampleTableService sampleTableService;
    @Autowired
    SampleInfoService sampleInfoService;
    @Autowired
    DownloadFileUtils downloadFileUtils;
    @Autowired
    SeqNoGenerateService seqNoGenerateService;
    @Autowired
    LimsSampleInfoDnaService limsSampleInfoDnaService;
    @Autowired
    LimsSampleGeneService limsSampleGeneService;
    /**
     * 提取列表
     * @param request
     * @param query
     * @param pageInfo
     * @return
     * @throws ParseException
     */
    @RequestMapping(value="/extractPlateList.html")
    public ModelAndView extractPlateList(HttpServletRequest request, ExtractPlateVo query, PageInfo pageInfo) throws ParseException {
        ModelAndView modelAndView = initializationData.initData(Constants.DICR_TYPE);
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo loaUserInfo = (LoaUserInfo) subject.getPrincipal();
        query.getEntity().setCreatePerson(loaUserInfo.getLoginName());

        List<ExtractPlateVo> extractPlateVoList = extractPlateService.selectVoList(query, pageInfo);
        int totalCnt = extractPlateService.selectVOCount(query);

        modelAndView.addObject("extractPlateVoList", extractPlateVoList);
        modelAndView.addObject("pageInfo", pageInfo.updatePageInfo(totalCnt));
        modelAndView.setViewName("center/extractPlate/extractPlateList");
        return modelAndView;
    }

    //获取提取列表数据
    @RequestMapping(value = "/getExtractPlateList.html", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> getExtractPlateList(HttpServletRequest request, @RequestBody ExtractPlateVo query, PageInfo pageInfo) {
        Map<String,Object> result = new HashMap<>();

        try {
            Subject subject = SecurityUtils.getSubject();
            LoaUserInfo loaUserInfo = (LoaUserInfo) subject.getPrincipal();
            query.getEntity().setCreatePerson(loaUserInfo.getLoginName());
            //截取输入板号的空格
            if (query.getEntity().getBoardNo()!=null){
                query.getEntity().setBoardNo(query.getEntity().getBoardNo().trim());
            }
            List<ExtractPlateVo> extractPlateVoList = extractPlateService.selectVoList(query, pageInfo);
            int totalCnt = extractPlateService.selectVOCount(query);

            result.put("sampleList", extractPlateVoList);
            result.put("pageInfo", pageInfo.updatePageInfo(totalCnt));
            result.put("success",true);
        }catch (Exception e) {
            result.put("success",false);
            logger.error("getExtractPlateList error",e);
        }

        return result;
    }

    /**
     * 创建或拼板创建提取列表
     * @param request
     * @param extractPlateVoList
     * @param isSpell
     * @return
     * @throws ParseException
     */
    @RequestMapping(value="/spellOrCreateExtractList.html",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public Map<String,Object> spellOrCreateExtractList(HttpServletRequest request,HttpServletResponse response, @RequestBody List<ExtractPlateVo> extractPlateVoList, String isSpell) throws ParseException {
        Map<String,Object> result = new HashMap<>();
        //获取要增加到待提取任务的样本。
        AddSampleVo addSampleVo=new AddSampleVo();
        boolean flag = false;
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo loaUserInfo = (LoaUserInfo) subject.getPrincipal();
        //实验室id
        addSampleVo.setOrgId(loaUserInfo.getOrgId());
        try{
            if (ListUtils.isNotNullAndEmptyList(extractPlateVoList)) {
                //不拼板
                if (isSpell.equals(Constants.IS_SPELL_NO)) {
                    for (ExtractPlateVo extractPlateVo : extractPlateVoList) {
                        SampleTable sampleTable = sampleTableService.selectByPrimaryKey(extractPlateVo.getSampleTableId());

                        if (sampleTable == null) {
                            continue;
                        }
                        //更新提取样本孔位
                        SampleInfoVo sampleInfoVo = new SampleInfoVo();
                        sampleInfoVo.getEntity().setSampleTableId(sampleTable.getId());
                        List<SampleInfoVo> sampleInfoVoList = sampleInfoService.selectVoListBySampleInfo(sampleInfoVo);
                        sampleInfoVoList = TestProcessUtils.getLocation(sampleInfoVoList, Constants.STAGE_TQ);
                        initializationData.updateSampleInfo(sampleInfoVoList, Constants.STAGE_TQ);

                        ExtractPlate extractPlate = initExtractPlate();
                        ExtractPlateVo plateVo = extractPlateVoList.get(0);
                        if (plateVo != null) {
                            extractPlate.setDeviceType(plateVo.getEntity().getDeviceType());
                            extractPlate.setKitName(plateVo.getEntity().getKitName());
                        }
                        extractPlate.setId(UUID.randomUUID().toString());
                        extractPlate.setHoleNum(sampleTable.getHoleNum());
                        extractPlateService.insert(extractPlate);
                        //更新样本板信息
                        sampleTable.setExtractPlateId(extractPlate.getId());
                        sampleTable.setExtractPlateName(extractPlate.getBoardNo());
                        sampleTable.setIsCreate(Constants.IS_CREATE_1);
                        flag = updateSampleTable(sampleTable);

                        //检材信息列表
                        List<LimsSampleInfoDna> allLimsSampleInfoDna=new ArrayList<LimsSampleInfoDna>();
                        List<LimsSampleGene> allLimsSampleGene=new ArrayList<LimsSampleGene>();
                        //板号
                        SampleTable sampleTable1 = sampleTableService.selectByPrimaryKey(sampleTable.getId());
                        addSampleVo.setBoardNo(sampleTable1.getBoardNo());
                        //检材数量
                        addSampleVo.setSampleCount(sampleInfoVoList.size());
                        //检材信息
                        for (SampleInfoVo sampleInfo:sampleInfoVoList){
                            String sampleNo = sampleInfo.getEntity().getSampleNo();
                            List<LimsSampleInfoDna> limsSampleInfoDnaList = limsSampleInfoDnaService.selectBySampleNo(sampleNo);
                            LimsSampleInfoDna limsSampleInfoDna = limsSampleInfoDnaList.get(0);
                            //发送时清空照片信息
                            limsSampleInfoDna.setSampleDnaPicture(null);
                            limsSampleInfoDna.setSampleDnaPicturePath(null);
                            //检材绑定孔位
                            limsSampleInfoDna.setBoardPosition(sampleInfo.getEntity().getSamplePlateLocation());
                            //样本板上检材信息存入list
                            allLimsSampleInfoDna.add(limsSampleInfoDna);
                            List<LimsSampleGene> limsSampleGenes = limsSampleGeneService.selectAuditedBySampleId(limsSampleInfoDna.getSampleId());
                            //lims分型信息存入板号 孔位信息
                            if (limsSampleGenes.size()>0) {
                                LimsSampleGene limsSampleGene = limsSampleGenes.get(0);
                                //板号
                                limsSampleGene.setBoardNo(sampleInfo.getEntity().getBoardNo());
                                //孔位
                                limsSampleGene.setBoardPosition(sampleInfo.getEntity().getSamplePlateLocation());
                                //修改分型表样本信息
                                limsSampleGeneService.updateByPrimaryKey(limsSampleGene);
                                allLimsSampleGene.add(limsSampleGene);
                            }
                        }
                        addSampleVo.setLimsSampleInfoDnaList(allLimsSampleInfoDna);
                        addSampleVo.setLimsSampleGeneList(allLimsSampleGene);
                        //发送样本信息
                        /*String url = "http://localhost:8888/clims/center/sample/uploadSampleInfo.html";*/
                        String url = "http://192.168.1.199:8083/dnaExtract/pretreatment";
                        String map = JsonUtils.obj2String(addSampleVo);
                        String str = URLEncoder.encode(map);
                        System.out.println("服务器返回的结果："+str);
                        System.out.println(str);
                        /*PrintWriter writer = response.getWriter();
                        writer.write(str);
                        writer.flush();*/
                        System.out.println("请求参数转Json"+map);
                        String sendResult = HttpRequestUtil.sendRequest(url,str);
                        if (sendResult != null){
                            logger.info("sendRequest"+sendResult);
                        }

                    }
                }else if (isSpell.equals(Constants.IS_SPELL_YES)) {
                    //拼板
                    //拼板首先创建提取记录信息
                    int holeNum = 0;
                    List<SampleInfoVo> allSampleInfoVoList = new ArrayList<>();
                    //列表展示时是倒序排列，在拼板时进行倒序循环
                    int plateSort = 1;
                    for (int i = extractPlateVoList.size() -1; i >= 0; i--) {
                        ExtractPlateVo extractPlateVo = extractPlateVoList.get(i);
                        if (extractPlateVo != null) {
                            SampleTable sampleTable = sampleTableService.selectByPrimaryKey(extractPlateVo.getSampleTableId());
                            if (sampleTable == null) {
                                continue;
                            }
                            if (StringUtils.isNotBlank(sampleTable.getHoleNum())) {
                                holeNum += Integer.parseInt(sampleTable.getHoleNum());
                            }

                            SampleInfoVo sampleInfoVo = new SampleInfoVo();
                            sampleInfoVo.getEntity().setSampleTableId(sampleTable.getId());
                            List<SampleInfoVo> sampleInfoVoList = sampleInfoService.selectVoListBySampleInfo(sampleInfoVo);
                            if (ListUtils.isNotNullAndEmptyList(sampleInfoVoList)) {
                                //按样本板孔位序号排序
                                sampleInfoVoList = TestProcessUtils.holeSort(sampleInfoVoList, Constants.STAGE_QT);
                                //去除重复样本表信息
                                List<SampleInfoVo> removeDuplicateList = sampleInfoService.selectListVo(sampleInfoVo);
                                for (SampleInfoVo siVo : removeDuplicateList) {
                                    allSampleInfoVoList.addAll(TestProcessUtils.tranferInfo(sampleInfoVoList, plateSort++, Constants.STAGE_TQ, siVo));
                                }
                            }
                        }
                    }

                    //更新检材提取孔位
//                    allSampleInfoVoList = TestProcessUtils.boardAgain(allSampleInfoVoList, Constants.STAGE_TQ);
                    initializationData.updateSampleInfo(allSampleInfoVoList, Constants.STAGE_TQ);

                    ExtractPlate extractPlate = initExtractPlate();
                    ExtractPlateVo plateVo = extractPlateVoList.get(0);
                    if (plateVo != null) {
                        extractPlate.setDeviceType(plateVo.getEntity().getDeviceType());
                        extractPlate.setKitName(plateVo.getEntity().getKitName());
                    }
                    extractPlate.setId(UUID.randomUUID().toString());
                    extractPlate.setHoleNum(String.valueOf(holeNum));
                    extractPlateService.insert(extractPlate);
                    int count = 1;
                    //列表展示时是倒序排列，在拼板时进行倒序循环
                    for (int i = extractPlateVoList.size() -1; i >= 0; i--) {
                        ExtractPlateVo extractPlateVo = extractPlateVoList.get(i);
                        if (extractPlateVo != null) {
                            SampleTable sampleTable = sampleTableService.selectByPrimaryKey(extractPlateVo.getSampleTableId());
                            //更新样本板信息
                            sampleTable.setExtractPlateId(extractPlate.getId());
                            sampleTable.setExtractPlateName(extractPlate.getBoardNo() + "["+(count++)+"]");
                            sampleTable.setIsCreate(Constants.IS_CREATE_1);
                            flag = updateSampleTable(sampleTable);
                        }
                    }
                }
            }
        }catch (Exception e){
            logger.error("spellOrCreateExtractList error",e);
        }

        if (flag) {
            result.put("success",true);
        }else {
            result.put("success",false);
        }
        return result;
    }
    /**
     * 测试接收样本信息
     * @param data
     */
    @RequestMapping(value="/uploadSampleInfo.html")
    @ResponseBody
    public String uploadSampleInfo(@RequestBody String data) {

        try {
            /*data = URLDecoder.decode(data) ;*/
            System.out.println("接收到:"+data);
            JsonUtils.obj2String(data);

            return "上传成功";
        }catch (Exception e) {
            logger.error("uploadSampleInfo error" + "上传样本信息错误",e);
            return "上传失败";
        }
    }
    /**
     * 更新样本板信息
     * @param sampleTable
     */
    public boolean updateSampleTable(SampleTable sampleTable) {

        try {
            sampleTableService.updateByPrimaryKey(sampleTable);
            //更新样本信息
            SampleInfo sampleInfo = new SampleInfo();
            sampleInfo.setSampleTableId(sampleTable.getId());
            sampleInfo.setExtractPlateId(sampleTable.getExtractPlateId());
            sampleInfoService.updateBySampleTableId(sampleInfo);
            return true;
        }catch (Exception e) {
            logger.error("updateSampleTable error" + "更新样本板信息错误",e);
            return false;
        }
    }

    /**
     * 删除提取信息
     * @param request
     * @param extractPlateList
     * @return
     */
    @RequestMapping(value="/deleteExtractPlate.html",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public Map<String,Object> deleteExtractPlate(HttpServletRequest request, @RequestBody List<ExtractPlate> extractPlateList){
        Map<String,Object> result = new HashMap<>();

        try{
            if (ListUtils.isNotNullAndEmptyList(extractPlateList)) {
                for (ExtractPlate extractPlate : extractPlateList) {
                    extractPlateService.deleteFlagById(extractPlate.getId());
                }
                result.put("success",true);
            }else {
                result.put("success","no");
            }
        }catch (Exception e){
            result.put("success",false);
            logger.error("deleteExtractPlate error",e);
        }

        return result;
    }

    /**
     * 导出csv文件
     * @param request
     * @param response
     * @param sampleInfoVo
     */
    @RequestMapping("/exportCSVFile.html")
    public void exportCSVFile(HttpServletRequest request, HttpServletResponse response, SampleInfoVo sampleInfoVo) {
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo loaUserInfo = (LoaUserInfo) subject.getPrincipal();
        sampleInfoVo.getEntity().setCreatePerson(loaUserInfo.getLoginName());

        List<SampleInfoVo> sampleInfoVoList = sampleInfoService.selectVoListBySampleInfo(sampleInfoVo);
        sampleInfoVoList = TestProcessUtils.boardSortAgain(sampleInfoVoList, Constants.STAGE_TQ);
        ExtractPlate extractPlate = extractPlateService.selectByPrimaryKey(sampleInfoVo.getEntity().getExtractPlateId());


        downloadFileUtils.exportCSVFile(request, response, sampleInfoVoList, extractPlate);

    }

    /**
     * 导出样本文件
     * @param request
     * @param response
     * @param sampleInfoVo
     */
    @RequestMapping("/exportSampleFile.html")
    public void exportSampleFile(HttpServletRequest request, HttpServletResponse response, SampleInfoVo sampleInfoVo) {
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo loaUserInfo = (LoaUserInfo) subject.getPrincipal();
        sampleInfoVo.getEntity().setCreatePerson(loaUserInfo.getLoginName());

        List<SampleInfoVo> sampleInfoVoList = sampleInfoService.selectVoListBySampleInfo(sampleInfoVo);
        ExtractPlate extractPlate = extractPlateService.selectByPrimaryKey(sampleInfoVo.getEntity().getExtractPlateId());

        downloadFileUtils.exportSampleFile(request, response, sampleInfoVoList, extractPlate);

    }

    /**
     * 导出提取样本表
     * @param request
     * @param response
     * @param sampleInfoVo
     */
    @RequestMapping("/exportExtractSampleTable.html")
    public void exportExtractSampleTable(HttpServletRequest request, HttpServletResponse response, SampleInfoVo sampleInfoVo) {
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo loaUserInfo = (LoaUserInfo) subject.getPrincipal();
        sampleInfoVo.getEntity().setCreatePerson(loaUserInfo.getLoginName());

        List<SampleInfoVo> sampleInfoVoList = sampleInfoService.selectVoListBySampleInfo(sampleInfoVo);
        sampleInfoVoList = TestProcessUtils.boardSortAgain(sampleInfoVoList, Constants.STAGE_TQ);
        ExtractPlate extractPlate = extractPlateService.selectByPrimaryKey(sampleInfoVo.getEntity().getExtractPlateId());

        downloadFileUtils.exportExtractSampleTable(request, response, sampleInfoVoList, extractPlate);

    }

    /**
     * 导出提取样本记录表
     * @param request
     * @param response
     * @param sampleInfoVo
     */
    @RequestMapping("/exportExtractSampleRecord.html")
    public void exportExtractSampleRecord(HttpServletRequest request, HttpServletResponse response, SampleInfoVo sampleInfoVo) {
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo loaUserInfo = (LoaUserInfo) subject.getPrincipal();
        sampleInfoVo.getEntity().setCreatePerson(loaUserInfo.getLoginName());

        List<SampleInfoVo> sampleInfoVoList = sampleInfoService.selectVoListBySampleInfo(sampleInfoVo);
        sampleInfoVoList = TestProcessUtils.holeSort(sampleInfoVoList, Constants.STAGE_TQ);
        ExtractPlate extractPlate = extractPlateService.selectByPrimaryKey(sampleInfoVo.getEntity().getExtractPlateId());

        downloadFileUtils.exportExtractSampleRecord(request, response, sampleInfoVoList, extractPlate);

    }

    public ExtractPlate initExtractPlate() {
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo loaUserInfo = (LoaUserInfo) subject.getPrincipal();
        String loginName = loaUserInfo.getLoginName();
        ExtractPlate extractPlate = new ExtractPlate();
        String boardNo = seqNoGenerateService.getNextBoardNoVal(DateUtils.dateToString(new Date(), DateUtils.DATE) + "-" + loginName);
        extractPlate.setBoardNo(boardNo);
        extractPlate.setCreatePerson(loginName);
        extractPlate.setCreateDatetime(new Date());
        extractPlate.setExtractMethod("磁珠法");
        extractPlate.setExtractDatetime(new Date());

        return extractPlate;
    }
}
