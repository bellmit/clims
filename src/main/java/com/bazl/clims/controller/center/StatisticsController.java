package com.bazl.clims.controller.center;

import com.bazl.clims.common.Constants;
import com.bazl.clims.controller.BaseController;
import com.bazl.clims.model.DictItem;
import com.bazl.clims.model.LoaUserInfo;
import com.bazl.clims.model.PageInfo;
import com.bazl.clims.model.bo.CaseStatisticsModel;
import com.bazl.clims.model.bo.SampleStatisticsModel;
import com.bazl.clims.model.po.AmPersonalInfo;
import com.bazl.clims.model.po.LimsSampleInfoDna;
import com.bazl.clims.model.vo.AmPersonalInfoVo;
import com.bazl.clims.model.vo.LimsCaseInfoVo;
import com.bazl.clims.model.vo.LimsSampleInfoDnaVo;
import com.bazl.clims.service.*;
import com.bazl.clims.utils.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by LX on 2019/9/17.
 */
@Controller
@RequestMapping("/statistics")
public class StatisticsController extends BaseController {

    @Autowired
    AmPersonalInfoService amPersonalInfoService;
    @Autowired
    LimsCaseInfoService limsCaseInfoService;
    @Autowired
    MatchSameResultService matchSameResultService;
    @Autowired
    LimsSampleInfoDnaService limsSampleInfoDnaService;
    @Autowired
    DictItemService dictItemService;

    @RequestMapping("/caseStatistics")
    public ModelAndView caseStatistics(HttpServletRequest request, LimsCaseInfoVo query, PageInfo pageInfo) {
        ModelAndView view = new ModelAndView();
        try {
            //获取当前登录用户
            Subject subject = SecurityUtils.getSubject();
            LoaUserInfo loaUserInfo = (LoaUserInfo) subject.getPrincipal();
            if (loaUserInfo == null) {
                view.setViewName("redirect:/login.html?timeoutFlag=1");
                return view;
            }
            //获取当前用户的id
            String userOrgId = loaUserInfo.getOrgId();
            //将当前用户org_id设置进query
            if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
                userOrgId = "110230000000";
            }
            query.setUserOrdId(userOrgId);

            //获取受理人信息
            List<AmPersonalInfoVo> amPersonalInfoVoList = amPersonalInfoService.queryAmPersonalInfoVoList(loaUserInfo.getOrgId());
            List<CaseStatisticsModel> list = new ArrayList<CaseStatisticsModel>();
            query = resetCaseInfoQuery(query);
            if (null == query.getAcceptorId()){
                if (ListUtils.isNotNullAndEmptyList(amPersonalInfoVoList)){
                    for (AmPersonalInfoVo infoVo : amPersonalInfoVoList){
                        CaseStatisticsModel model = new CaseStatisticsModel();
                        model.setName(infoVo.getEntity().getFullName());
                        //根据人员id查询案件数量
                        query.setAcceptorId(infoVo.getEntity().getPersonalId());
                        CaseStatisticsModel selectCount = selectCount(query, model);
                        list.add(selectCount);
                    }
                    view.addObject("CaseStatisticsList",list);
                    query.setAcceptorId(null);
                }
            }else{
                AmPersonalInfo amPersonalInfo = amPersonalInfoService.selectByPersonalId(query.getAcceptorId());
                CaseStatisticsModel model = new CaseStatisticsModel();
                model.setName(amPersonalInfo.getFullName());
                //根据人员id查询案件数量
                query.setAcceptorId(amPersonalInfo.getPersonalId());
                CaseStatisticsModel selectCount = selectCount(query, model);
                list.add(selectCount);
                view.addObject("CaseStatisticsList",list);
            }

            List<DictItem> dictItemList = dictItemService.selectListByDictTypeCode(Constants.CASE_PROPERTY);

            view.addObject("dictItemList",dictItemList);
            view.addObject("query", query);
            view.addObject("amPersonalInfoVoList",amPersonalInfoVoList);
            view.setViewName("statistics/caseStatistics");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("查询失败:" + e);
        }
        return view;
    }

    public CaseStatisticsModel selectCount(LimsCaseInfoVo query,CaseStatisticsModel model){
        //首检受理数量
        query.setAppendFlag("0");
        int caseCount = limsCaseInfoService.selectFirsInsCount(query);
        model.setCaseCount(caseCount);

        //补送受理数
        query.setAppendFlag("1");
        int complement = limsCaseInfoService.selectFirsInsCount(query);
        model.setComplement(complement);
        //已入库案件数
        int instoredCase = limsCaseInfoService.selectInstoredCount(query);
        model.setInstoredCase(instoredCase);
        //比中案件数量
        int mathCount = matchSameResultService.selectMathCount(query);
        model.setMathCount(mathCount);
        //受理检材数
        int acceptCount = limsSampleInfoDnaService.selectAcceptCount(query);
        model.setAcceptCount(acceptCount);
        //检出检材数
        int auditCount = limsSampleInfoDnaService.selectAuditCount(query);
        model.setAuditCount(auditCount);

        int instoredSampl = 0;
        List<LimsCaseInfoVo> limsCaseInfoVos = limsCaseInfoService.selectVOCaseInfoList(query);
        if (limsCaseInfoVos != null){
            for (LimsCaseInfoVo limsCaseInfoVo : limsCaseInfoVos) {
                List<LimsSampleInfoDnaVo> limsSampleInfoDnaVos = limsSampleInfoDnaService.selectSampleInfos(limsCaseInfoVo.getCaseId());
//                model.setInstoredSampl(limsSampleInfoDnaVos.size());
                instoredSampl = instoredSampl+limsSampleInfoDnaVos.size();
            }
        }

        //已入库检材数
//        int instoredSampl = limsSampleInfoDnaService.selectInstoredSampleCount(query);
        model.setInstoredSampl(instoredSampl);
        //检材比中数
        int sampleCount = matchSameResultService.selectSampleCount(query);
        model.setSampleCount(sampleCount);

        return model;
    }

    /**
     * 数据统计页面-样本统计--liuchang(Edit)
     * @param request
     * @param query
     * @param pageInfo
     * @return
     */
    @RequestMapping("/sampleStatistics")
    public ModelAndView caseManage(HttpServletRequest request, LimsCaseInfoVo query, PageInfo pageInfo) {
        ModelAndView view = new ModelAndView();


        try {
            //获取当前登录用户
            Subject subject = SecurityUtils.getSubject();
            LoaUserInfo loaUserInfo = (LoaUserInfo) subject.getPrincipal();
            if (loaUserInfo == null) {
                view.setViewName("redirect:/login.html?timeoutFlag=1");
                return view;
            }
            //获取当前用户的id
            String userOrgId = loaUserInfo.getOrgId();
            //将当前用户org_id设置进query
            if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
                userOrgId = "110230000000";
            }
            query.setUserOrdId(userOrgId);

            //获取受理人信息
            List<AmPersonalInfoVo> amPersonalInfoVoList = amPersonalInfoService.queryAmPersonalInfoVoList(loaUserInfo.getOrgId());
            List<DictItem> dictItems = dictItemService.selectListByDictTypeCode("CASE_PROPERTY");
            List<SampleStatisticsModel> list = new ArrayList<SampleStatisticsModel>();
            query = resetCaseInfoQuery(query); //查询去除空格（案件管理信息）
            if (null == query.getAcceptorId()){
                if (ListUtils.isNotNullAndEmptyList(amPersonalInfoVoList)){
                    for (AmPersonalInfoVo infoVo : amPersonalInfoVoList){
                        //根据人员id查询案件数量
                        query.setAcceptorId(infoVo.getEntity().getPersonalId()); //受理ID
                        query.setFullName(infoVo.getEntity().getFullName()); //  全名
                        //受理检材数
                        int acceptCount = limsSampleInfoDnaService.selectAcceptCount(query);
                        //已入库检材数 （入库总数）
                        int instoredSampl = limsSampleInfoDnaService.selectInstoredSampleCount(query);
                        SampleStatisticsModel model = new SampleStatisticsModel();
                        List<LimsSampleInfoDna> limsSampleInfoDnas = limsSampleInfoDnaService.selectAccept(query);
                        if (ListUtils.isNotNullAndEmptyList(limsSampleInfoDnas)){
                            model = getSampleCount(limsSampleInfoDnas,model);
                        }
                        model.setName(infoVo.getEntity().getFullName());//受理人
                        model.setAcceptCount(acceptCount);
                        model.setInstoredSampl(instoredSampl);
                        list.add(model);
                    }
                    view.addObject("sampletatisticsList",list);
                    query.setAcceptorId(null);
                }
            }else{
                 AmPersonalInfo amPersonalInfo = amPersonalInfoService.selectByPersonalId(query.getAcceptorId());
                //根据人员id查询案件数量
                    query.setAcceptorId(amPersonalInfo.getPersonalId());
                //受理检材数
                int acceptCount = limsSampleInfoDnaService.selectAcceptCount(query);
                //已入库检材数
                int instoredSampl = limsSampleInfoDnaService.selectInstoredSampleCount(query);
                List<LimsSampleInfoDna> limsSampleInfoDnas = limsSampleInfoDnaService.selectAccept(query);
                SampleStatisticsModel model = new SampleStatisticsModel();
                if (ListUtils.isNotNullAndEmptyList(limsSampleInfoDnas)){
                    model = getSampleCount(limsSampleInfoDnas,model);
                }
                model.setName(amPersonalInfo.getFullName());
                model.setAcceptCount(acceptCount);
                model.setInstoredSampl(instoredSampl);
                list.add(model);
                view.addObject("sampletatisticsList",list);
            }

            view.addObject("query", query);
            view.addObject("amPersonalInfoVoList",amPersonalInfoVoList);
            view.addObject("dictItems",dictItems);
            view.setViewName("statistics/sampleStatistics");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    public SampleStatisticsModel getSampleCount(List<LimsSampleInfoDna> limsSampleInfoDnas,SampleStatisticsModel model){
        int sampleType01 = 0;
        int sampleType02 = 0;
        int sampleType03 = 0;
        int sampleType04 = 0;
        int sampleType05 = 0;
        int sampleType06 = 0;
        int sampleType07 = 0;
        int sampleType08 = 0;
        int sampleType09 = 0;
        int sampleType10 = 0;
        int sampleType21 = 0;
        for (LimsSampleInfoDna infoDna : limsSampleInfoDnas){
            if (infoDna.getSampleType().equals(Constants.SAMPLE_TYPE_01)){
                sampleType01 ++ ;
            }else if (infoDna.getSampleType().equals(Constants.SAMPLE_TYPE_02)){
                sampleType02 ++ ;
            }else if (infoDna.getSampleType().equals(Constants.SAMPLE_TYPE_03)){
                sampleType03 ++ ;
            }else if (infoDna.getSampleType().equals(Constants.SAMPLE_TYPE_04)){
                sampleType04 ++ ;
            }else if (infoDna.getSampleType().equals(Constants.SAMPLE_TYPE_05)){
                sampleType05 ++ ;
            }else if (infoDna.getSampleType().equals(Constants.SAMPLE_TYPE_06)){
                sampleType06 ++ ;
            }else if (infoDna.getSampleType().equals(Constants.SAMPLE_TYPE_07)){
                sampleType07 ++ ;
            }else if (infoDna.getSampleType().equals(Constants.SAMPLE_TYPE_08)){
                sampleType08 ++ ;
            }else if (infoDna.getSampleType().equals(Constants.SAMPLE_TYPE_09)){
                sampleType09 ++ ;
            }else if (infoDna.getSampleType().equals(Constants.SAMPLE_TYPE_99)){
                sampleType10 ++ ;
            }else if (infoDna.getSampleType().equals(Constants.SAMPLE_TYPE_21)){
                sampleType21 ++ ;
            }
        }
        model.setSampleType01(sampleType01);
        model.setSampleType02(sampleType02);
        model.setSampleType03(sampleType03);
        model.setSampleType04(sampleType04);
        model.setSampleType05(sampleType05);
        model.setSampleType06(sampleType06);
        model.setSampleType07(sampleType07);
        model.setSampleType08(sampleType08);
        model.setSampleType09(sampleType09);
        model.setSampleType10(sampleType10);
        model.setSampleType21(sampleType21);
        return model;
    }

    private LimsCaseInfoVo resetCaseInfoQuery(LimsCaseInfoVo query) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        if (StringUtils.isBlank(query.getAcceptorId())) {
            query.setAcceptorId(null);
        } else {
            query.setAcceptorId(query.getAcceptorId().trim()); //受理ID
        }
        if (StringUtils.isBlank(query.getEntity().getCaseProperty())) {
            query.setCaseProperty(null);
        } else {
            query.setCaseProperty(query.getEntity().getCaseProperty().trim()); //案件性质
        }

        if (null == query.getAcceptStartDatetime()) {
            //query.setAcceptStartDatetime(null);
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH,1);
            query.setAcceptStartDatetime(calendar.getTime());
        } else {
            query.setAcceptStartDatetime(query.getAcceptStartDatetime()); //案件受理开始时间
        }

        if (null == query.getAcceptEndDatetime()) {
            //query.setAcceptEndDatetime(null);
            query.setAcceptEndDatetime(new Date());
        } else {
            Calendar instance = Calendar.getInstance();
            instance.setTime(query.getAcceptEndDatetime());
            instance.set(Calendar.HOUR,23);
            instance.set(Calendar.MINUTE,59);
            instance.set(Calendar.SECOND,59);
            query.setAcceptEndDatetime(instance.getTime());
//            query.setAcceptEndDatetime(query.getAcceptEndDatetime());
        }

        if (StringUtils.isBlank(query.getEntity().getCaseProperty())) {
            query.getEntity().setCaseProperty(null);
        } else {
            query.getEntity().setCaseProperty(query.getEntity().getCaseProperty());
        }

        return query;
    }
}
