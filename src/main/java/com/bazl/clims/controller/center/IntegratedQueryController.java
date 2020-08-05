package com.bazl.clims.controller.center;

import com.bazl.clims.model.DictItem;
import com.bazl.clims.common.Constants;
import com.bazl.clims.model.LoaUserInfo;
import com.bazl.clims.model.PageInfo;
import com.bazl.clims.model.po.*;
import com.bazl.clims.service.*;
import com.bazl.clims.utils.DictUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sun on 2018/12/20.
 */
@Controller
@RequestMapping("/integratedQuery")
public class IntegratedQueryController {

    @Resource
    SolrClient client;

    @Autowired
    LimsConsignmentInfoService limsConsignmentInfoService;

    @Autowired
    LimsCaseInfoService limsCaseInfoService;

    @Autowired
    LimsPersonInfoService limsPersonInfoService;

    @Autowired
    LimsSampleInfoDnaService limsSampleInfoDnaService;

    @Autowired
    AmPersonalInfoService amPersonalInfoService;

    @Autowired
    MatchAuditedGeneService matchAuditedGeneService;

    @Autowired
    OrgInfoService orgInfoService;
    /**
     * 综合管理
     * @return
     */
    @RequestMapping("/integratedQueryList")
    public ModelAndView integratedManageList (){
        ModelAndView view = new ModelAndView();
        view.setViewName("query/integratedQueryList");
        return view;
    }

    /**
     * 鉴定状态详情查询
     * @return
     */
    @RequestMapping("/queryDetail")
    public ModelAndView queryDetail (String caseId, String consignmentId, String text, String caseXkNo, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("text", text);

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if(operateUser == null){
            modelAndView.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return  modelAndView;
        }
        modelAndView = initModelAndView();

        //根据委托id查询委托信息
        LimsConsignmentInfo consignatioInfo = limsConsignmentInfoService.selectByConsignmentId(consignmentId);
        //根据案件id查询案件信息
        LimsCaseInfo limsCaseInfo = limsCaseInfoService.selectByCaseId(caseId);
        //根据案件id查询人员信息
        LimsPersonInfo limsPersonInfo = new LimsPersonInfo();
        limsPersonInfo.setConsignmentId(consignmentId);
        limsPersonInfo.setCaseId(caseId);
        List<LimsPersonInfo> limsPersonInfoList = limsPersonInfoService.selectByCaseIdAndConsignmentId(limsPersonInfo);
        //根据案件id查询样本信息
        LimsSampleInfoDna sampleInfoDna = new LimsSampleInfoDna();
        sampleInfoDna.setCaseId(caseId);
        sampleInfoDna.setConsignmentId(consignmentId);
        List<LimsSampleInfoDna> sampleInfoDnaList = limsSampleInfoDnaService.selectByCaseIdAndRy(sampleInfoDna);
        if(null != limsPersonInfoList && limsPersonInfoList.size() > 0 && null != sampleInfoDnaList && sampleInfoDnaList.size() > 0){
            for(LimsPersonInfo limsPersonInfo1:limsPersonInfoList){
                for(LimsSampleInfoDna sampleInfoDna2:sampleInfoDnaList){
                    if(limsPersonInfo1.getPersonId().equals(sampleInfoDna2.getLinkId())){
                        sampleInfoDna2.setPersonName(limsPersonInfo1.getPersonName());
                    }
                }
            }
        }

        //根据案件id查询物证信息
        List<LimsSampleInfoDna> sampleInfoDnaList1 = limsSampleInfoDnaService.selectByCaseIdAndWz(sampleInfoDna);

        //查询是否有基因型
        for(LimsSampleInfoDna sampleInfo:sampleInfoDnaList){
            List<MatchAuditedGene> matchAuditedGeneList = matchAuditedGeneService.selectBySampleId(sampleInfo.getSampleId());
            if (matchAuditedGeneList.size()>0){
                for(MatchAuditedGene matchAuditedGene:matchAuditedGeneList){
                    sampleInfo.setAuditedGeneId(matchAuditedGene.getAuditedGeneId());
                }
            }
        }

        //查询物证是否有基因型
        for(LimsSampleInfoDna sampleInfoDna1:sampleInfoDnaList1){
            List<MatchAuditedGene> matchAuditedGeneList = matchAuditedGeneService.selectBySampleId(sampleInfoDna1.getSampleId());
            if (matchAuditedGeneList.size()>0){
                for(MatchAuditedGene matchAuditedGene:matchAuditedGeneList){
                    sampleInfoDna1.setAuditedGeneId(matchAuditedGene.getAuditedGeneId());
                }
            }
        }

        //分局
        List<OrgInfo> orgInfoList = new ArrayList<>();
        List<AmPersonalInfo> personalInfoList = new ArrayList<>();
        if(StringUtils.isBlank(operateUser.getOrgId()) || operateUser.getOrgId().contains("110023") || operateUser.getOrgId().contains("11023") ){
            orgInfoList = orgInfoService.selectAll();
            personalInfoList = amPersonalInfoService.queryAmPersonalInfoLIst(null);
        }else {
            orgInfoList = orgInfoService.selectDelegateByParentsId(operateUser.getOrgId());
            personalInfoList = amPersonalInfoService.queryAmPersonalInfoLIst(operateUser.getOrgId());
        }
        modelAndView.addObject("personalInfoList", personalInfoList);
        modelAndView.addObject("orgInfoList", orgInfoList);

        OrgInfo orgInfo = new OrgInfo();
        orgInfo.setOrgName(consignatioInfo.getDelegateOrgName());
        modelAndView.addObject("orgInfo", orgInfo);
        modelAndView.addObject("xkNo", limsCaseInfo.getCaseXkNo());
        modelAndView.addObject("consignatioInfo", consignatioInfo);
        modelAndView.addObject("limsCaseInfo", limsCaseInfo);
        modelAndView.addObject("xkNo", limsCaseInfo.getCaseXkNo());
        modelAndView.addObject("limsPersonInfoList", limsPersonInfoList);
        modelAndView.addObject("sampleInfoDnaList", sampleInfoDnaList);
        modelAndView.addObject("limsSampleInfoList", sampleInfoDnaList1);
        session.setAttribute("user", operateUser);
        if(StringUtils.isNotBlank(caseXkNo)){
            modelAndView.setViewName("query/integratedQueryCase");
        }else{
            modelAndView.setViewName("query/integratedQueryNonCase");
        }
        return modelAndView;
    }

    /**
     * 鉴定状态查询
     * @return
     */
    @RequestMapping("/search")
    public ModelAndView search (String text, PageInfo pageInfo){

        ModelAndView view = new ModelAndView();
        view.addObject("text", text);
        view.setViewName("query/integratedQueryList");

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if(operateUser == null){
            view.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return  view;
        }

        //查询受理单位
        List<OrgInfo> acceptOrgList = orgInfoService.selectAcceptOrgList();
        //获取当前用户的id
        String userOrgId = operateUser.getOrgId();
        //将当前用户org_id设置进query
        if(StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")){
            userOrgId = "110230000000";
        }

        //创建查询语句
        SolrQuery query = new SolrQuery();
        //设置查询条件
        if(StringUtils.isNotBlank(text)){
            query.setQuery("case_name:"+"*"+text+"*"+" or "+"case_name:"+text
                    +" or "+ "case_no:"+"*"+text+"*"+" or "+"case_no:"+text
                    +" or "+ "identify_requirement:"+"*"+text+"*"+" or "+"identify_requirement:"+text
                    +" or "+"person:"+"*"+text+"*"+" or "+"person:"+text //设置查询关键字
                    +" or "+"case_id:"+"*"+text+"*"+" or "+"case_id:"+text
                    +" or "+"person_id_card:"+"*"+text+"*"+" or "+"person_id_card:"+text
                    +" or "+"person_name:"+"*"+text+"*"+" or "+"person_name:"+text
                    +" or "+"case_xk_no:"+"*"+text+"*"+" or "+"case_xk_no:"+text
                    +" or "+"case_status:"+"*"+text+"*"+" or "+"case_status:"+text
                    +" or "+ "consignment_id:"+"*"+text+"*"+" or "+"consignment_id:"+text);
            query.addFilterQuery("accept_org_id:"+userOrgId);
        }else{
            query.setQuery("accept_org_id:"+userOrgId);    //设置查询关键字
        }
        query.setFields("id, case_name, case_no, case_brief, delegate_datetime, identify_requirement, person, case_id, consignment_id, person_id_card, person_name, case_xk_no, case_status, case_property");//显示的字段 fl

        query.setHighlight(true);
        query.addHighlightField("case_name");
        query.setHighlightSimplePre("<font style='color:red'>");
        query.setHighlightSimplePost("</font>");

        int pageNo = pageInfo.getPage();
        int pageSize = pageInfo.getEvePageRecordCnt1();
        query.setStart((pageNo - 1) * pageSize); //offset
        query.setRows(6); //limit
        //执行查询(需传入code名)
        QueryResponse queryResponse = null;
        try {
            queryResponse = client.query(query);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取文档列表
       SolrDocumentList documentList = queryResponse.getResults();
        //获取总记录数
        long numFound = documentList.getNumFound();
//        System.out.println("总记录数:" + numFound);

        //k是id,内部的map的key是域名,其value是高亮的值集合
//        NamedList list = (NamedList) queryResponse.getResponse().get("highlighting");
//        System.out.println(list);//用于显示list中的值
//        for (SolrDocument solrDocument : documentList) {
//            //取各个文档信息
//            System.out.println(solrDocument);
//        }

        view.addObject("documentList", documentList);
        view.addObject("pageInfo", pageInfo.updatePageInfo1(numFound));
        return view;
    }

    /**
     * 查询字典项
     * @return
     */
    private ModelAndView  initModelAndView () {
        ModelAndView modelAndView = new ModelAndView();
        /*  字典 */

        DictItem dictItem = new DictItem();
        //案件类型
        dictItem.setDictTypeCode(Constants.CASE_TYPE);
        List<DictItem> caseTypeList = DictUtil.getDictList(dictItem);

        //案件性质
        //List<DictItem> casePropertyList1 = new LinkedList<>();
        dictItem.setDictTypeCode(Constants.CASE_PROPERTY);
        List<DictItem> casePropertyList = DictUtil.getDictList(dictItem);
        /*for(DictItem dictItem1:casePropertyList){
            if(("06").equals(dictItem1.getDictCode())){
                casePropertyList1.add(dictItem1);
            }else if(("09").equals(dictItem1.getDictCode())){
                casePropertyList1.add(dictItem1);
            } else if(("10").equals(dictItem1.getDictCode())){
                casePropertyList1.add(dictItem1);
            } else if(("11").equals(dictItem1.getDictCode())) {
                casePropertyList1.add(dictItem1);
            }else  if(("19").equals(dictItem1.getDictCode())) {
                casePropertyList1.add(dictItem1);
            }else if(("20").equals(dictItem1.getDictCode())){
                casePropertyList1.add(dictItem1);
            }
        }*/

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

        //亲缘关系
        dictItem.setDictTypeCode(Constants.RELATION_TYPE);
        List<DictItem> relationTypeList = DictUtil.getDictList(dictItem);

        //提取方法
        dictItem.setDictTypeCode(Constants.EXTRACT_METHOD);
        List<DictItem> extractMethodList = DictUtil.getDictList(dictItem);

        //载体
        dictItem.setDictTypeCode(Constants.SAMPLE_CARRIER);
        List<DictItem> sampleCarrierList = DictUtil.getDictList(dictItem);

        modelAndView.addObject("caseTypeList", caseTypeList);
        modelAndView.addObject("casePropertyList", casePropertyList);
        modelAndView.addObject("caseLevelList", caseLevelList);
        modelAndView.addObject("memberTypeList", memberTypeList);
        modelAndView.addObject("sampleTypeList", sampleTypeList);
        modelAndView.addObject("genderList", genderList);
        modelAndView.addObject("packMethodList", packMethodList);
        modelAndView.addObject("caseStatusList", caseStatusList);
        modelAndView.addObject("positionList", positionList);
        modelAndView.addObject("personTypeList",personTypeList);
        modelAndView.addObject("relationTypeList",relationTypeList);
        modelAndView.addObject("extractMethodList",extractMethodList);
        modelAndView.addObject("sampleCarrierList",sampleCarrierList);


        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if(operateUser == null){
            modelAndView.setViewName("redirect:/login.html?timeoutFlag=1");
            return  modelAndView;
        }

        String orgId = StringUtils.isBlank(operateUser.getSubOrgId()) ? operateUser.getOrgId() : operateUser.getSubOrgId();
        //查询委托单位
        OrgInfo subOrgInfo = orgInfoService.selectByPrimaryKey(orgId);
        modelAndView.addObject("subOrgInfo",subOrgInfo);

        //查询分局单位
        OrgInfo orgInfo1 = orgInfoService.selectByPrimaryKey(operateUser.getOrgId());
        modelAndView.addObject("orgInfo",orgInfo1);
        //查询法医中心
        OrgInfo forensicCenterorg = orgInfoService.selectByPrimaryKey(Constants.FORENSIC_CENTER_ORG_ID);
        modelAndView.addObject("forensicCenterorg",forensicCenterorg);
        //创建orgInfoList点击鉴定中心的选择，可以多选
        List<OrgInfo> orgInfos = new ArrayList<>();
        if(StringUtils.isNotBlank(orgInfo1.getOrgQualification())){
            orgInfos.add(orgInfo1);
            orgInfos.add(forensicCenterorg);
        }
        modelAndView.addObject("orgInfos",orgInfos);

        return modelAndView;
    }
}
