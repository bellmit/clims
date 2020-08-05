package com.bazl.clims.controller.caseAnalysis;

import com.bazl.clims.common.Constants;
import com.bazl.clims.controller.BaseController;
import com.bazl.clims.model.po.*;
import com.bazl.clims.service.*;
import com.bazl.clims.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wanghaiyang
 * @date 2019/7/8.
 */
@Controller
@RequestMapping("/CaseInfo")
public class GetCaseInfoController extends BaseController {

    @Autowired
    LimsCaseInfoService limsCaseInfoService;
    @Autowired
    MatchAuditedGeneService matchAuditedGeneService;
    @Autowired
    LimsSampleInfoDnaService limsSampleInfoDnaService;
    @Autowired
    LimsPersonInfoService limsPersonInfoService;
    @Autowired
    LimsSampleGeneService limsSampleGeneService;

    /**
     * 根据案件编号获取案件下的基本信息信息
     * @param
     * @return
     * @throws IException
     */
    @RequestMapping(value = "/getCaseInfoByCaseNo", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    @Transactional
    public ResultBean getCaseInfoByCaseNo(HttpServletRequest request, @RequestBody  String caseNo)throws IException {

        try {
            LimsCaseInfo limsCaseInfo = new LimsCaseInfo();
//            limsCaseInfo.setCaseNo(caseNo);
//            String caseNo = limsCaseInfo.getCaseNo();
            logger.debug("|.获取案件信息已开始|"+"方法名称:"+this.getClass().getName()+"."+Thread.currentThread().getStackTrace()[1].getMethodName()+".|");
            if (StringUtils.isNotBlank(caseNo)) {
                try {
                    Map<String, Object> dataMap = null;

                    limsCaseInfo = limsCaseInfoService.selectByCaseNo(caseNo);
                    if (limsCaseInfo != null) {
                        dataMap = new LinkedHashMap<>();
                        dataMap.put("caseInfo", limsCaseInfo);
                        String caseId= limsCaseInfo.getCaseId();
                        if (StringUtils.isNotBlank(caseId)) {
                            //获取案件下审核通过的检材信息
                            List<LimsSampleInfoDna> sampleInfoList = limsSampleInfoDnaService.selectAuditSampleByCaseId(caseId);
                            if (ListUtils.isNotNullAndEmptyList(sampleInfoList)) {
                                dataMap.put("sampleInfoList", sampleInfoList);
                                logger.debug("|.获取案件下审核通过的检材信息|结果result="+ sampleInfoList.toString() +".|");
                            }

                            //获取案件下人员信息
                            List<LimsPersonInfo> personInfoList = limsPersonInfoService.selectAuditByCaseId(caseId);
                            if (ListUtils.isNotNullAndEmptyList(personInfoList)) {
                                dataMap.put("personInfoList", personInfoList);
                                logger.debug("|.获取案件下审核通过的检材信息|结果result="+ sampleInfoList.toString() +".|");
                            }

                            //获取案件下审核通过的单一检材基因型信息
                            LimsSampleGene limsSampleGene = new LimsSampleGene();
                            limsSampleGene.setCaseId(caseId);
                            limsSampleGene.setGeneType(Constants.GENE_NORMAL);
                            List<LimsSampleGene> auditedGeneList = limsSampleGeneService.selectByGeneList(limsSampleGene);
                            if (ListUtils.isNotNullAndEmptyList(auditedGeneList)) {
                                dataMap.put("auditedGeneList", auditedGeneList);
                                logger.debug("|.获取案件下审核通过的单一检材基因型信息|结果result="+ auditedGeneList.toString() +".|");
                            }

                            //获取案件下审核通过的混合检材基因型信息
                            limsSampleGene.setCaseId(caseId);
                            limsSampleGene.setGeneType(Constants.GENE_MIXED);
                            List<LimsSampleGene> mixAuditedGeneList = limsSampleGeneService.selectByGeneList(limsSampleGene);
                            if (ListUtils.isNotNullAndEmptyList(mixAuditedGeneList)) {
                                dataMap.put("mixAuditedGeneList", mixAuditedGeneList);
                                logger.debug("|.获取案件下审核通过的混合检材基因型信息|结果result="+ mixAuditedGeneList.toString() +".|");
                            }
                        }
                    }else {
                        return new ResultBean(ResultBean.CODE_ERROR, ErrorCode.IBOAN_REQJSON_PARSEMSG_EXCEPTION, 1, ErrorMsgManager.GetErrorMsg(ErrorCode.IBOAN_REQJSON_PARSEMSG_EXCEPTION));
                    }

                    if (dataMap != null) {
                        logger.debug("|.获取案件信息已结束|结果result="+ dataMap.toString() +".|");
                        return new ResultBean(ResultBean.CODE_SUCCESS, 0, dataMap, ResultBean.NAME_SUCCESS);
                    }else {
                        return new ResultBean(ResultBean.CODE_ERROR, ErrorCode.IBOAN_REQJSON_PARSEMSG_EXCEPTION, 1, ErrorMsgManager.GetErrorMsg(ErrorCode.IBOAN_REQJSON_PARSEMSG_EXCEPTION));
                    }
                }catch (Exception e) {
                    logger.error("获取案件信息错误"+e);
                    return new ResultBean(ResultBean.CODE_ERROR, ErrorCode.IBOAN_REQJSON_PARSEMSG_EXCEPTION, 1, ErrorMsgManager.GetErrorMsg(ErrorCode.IBOAN_REQJSON_PARSEMSG_EXCEPTION));
                }
            }else {
                return new ResultBean(ResultBean.CODE_ERROR, ErrorCode.IBOAN_REQJSON_PARSEMSG_EXCEPTION, 1, ErrorMsgManager.GetErrorMsg(ErrorCode.IBOAN_REQJSON_PARSEMSG_EXCEPTION));
            }
        }catch (Exception e){
            logger.error("获取案件信息错误"+e);
            return new ResultBean(ResultBean.CODE_ERROR, ErrorCode.IBOAN_REQJSON_PARSEMSG_EXCEPTION, 1, ErrorMsgManager.GetErrorMsg(ErrorCode.IBOAN_REQJSON_PARSEMSG_EXCEPTION));

        }
    }
}
