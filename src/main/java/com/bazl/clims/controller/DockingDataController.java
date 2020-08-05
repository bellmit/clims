package com.bazl.clims.controller;

import com.bazl.clims.common.Constants;
import com.bazl.clims.model.po.LimsCaseInfo;
import com.bazl.clims.model.po.LimsPersonInfo;
import com.bazl.clims.model.po.LimsSampleInfoDna;
import com.bazl.clims.model.po.MatchAuditedGene;
import com.bazl.clims.model.vo.LimsCaseInfoVo;
import com.bazl.clims.model.vo.LimsPersonInfoVo;
import com.bazl.clims.model.vo.LimsSampleInfoDnaVo;
import com.bazl.clims.model.vo.MatchAuditedGeneVo;
import com.bazl.clims.service.LimsCaseInfoService;
import com.bazl.clims.service.LimsPersonInfoService;
import com.bazl.clims.service.LimsSampleInfoDnaService;
import com.bazl.clims.service.MatchAuditedGeneService;
import com.bazl.clims.utils.*;
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
 * Created by Administrator on 2019/10/28.
 *      与混合库数据对接
 */
@Controller
@RequestMapping("/CaseInfoData")
public class DockingDataController extends BaseController {

    @Autowired
    private LimsCaseInfoService limsCaseInfoService;
    @Autowired
    private LimsSampleInfoDnaService limsSampleInfoDnaService;
    @Autowired
    private LimsPersonInfoService limsPersonInfoService;
    @Autowired
    MatchAuditedGeneService matchAuditedGeneService;

    /**
     * 获取案件下的基本信息
     * @param
     * @return
     * @throws IException
     */
    @RequestMapping(value = "/getCaseInfoListData", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    @Transactional
    public ResultBean getCaseInfoByCaseNo(HttpServletRequest request, @RequestBody Integer page)throws IException {
        try {
            logger.debug("|.获取案件信息已开始.|");
            Map<String, Object> dataMap = null;
            LimsCaseInfoVo query = new LimsCaseInfoVo();
            List<LimsCaseInfo> limsCaseInfos = limsCaseInfoService.selectCaseInfoListData(query, page);
            dataMap = new LinkedHashMap<>();
            dataMap.put("caseInfoList", limsCaseInfos);
            logger.debug("|.获取案件信息已结束.|");
            return new ResultBean(ResultBean.CODE_SUCCESS, 0, dataMap, ResultBean.NAME_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ResultBean(ResultBean.CODE_ERROR, ErrorCode.IBOAN_REQJSON_PARSEMSG_EXCEPTION, 1, "获取信息错误!");
        }
    }

    /**
     * 获取检材的基本信息
     * @param
     * @return
     * @throws IException
     */
    @RequestMapping(value = "/getSamplefoListData", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    @Transactional
    public ResultBean getSamplefoListData(HttpServletRequest request, @RequestBody Integer page)throws IException {
        try {
            logger.debug("|.获取检材信息已开始.|");
            Map<String, Object> dataMap = null;
            LimsSampleInfoDnaVo query = new LimsSampleInfoDnaVo();
            List<LimsSampleInfoDna> limsSampleInfoList = limsSampleInfoDnaService.selectSampleInfoListData(query, page);
            dataMap = new LinkedHashMap<>();
            dataMap.put("limsSampleInfoList", limsSampleInfoList);
            logger.debug("|.获取检材信息已结束.|");
            return new ResultBean(ResultBean.CODE_SUCCESS, 0, dataMap, ResultBean.NAME_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ResultBean(ResultBean.CODE_ERROR, ErrorCode.IBOAN_REQJSON_PARSEMSG_EXCEPTION, 1, "获取信息错误!");
        }
    }

    /**
     * 获取审核通过的混合样本信息
     * @param
     * @return
     * @throws IException
     */
    @RequestMapping(value = "/getMixedSampleGene", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    @Transactional
    public ResultBean getMixedSampleGene(HttpServletRequest request, @RequestBody Integer page)throws IException {
        try {
            logger.debug("|.审核通过的混合检材基因型信息已开始.|");
            Map<String, Object> dataMap = null;
            //获取案件下审核通过的混合检材基因型信息
            MatchAuditedGeneVo query = new MatchAuditedGeneVo();
            query.getEntity().setGeneType(Constants.GENE_LADDER);
            List<MatchAuditedGene> mixAuditedGeneList = matchAuditedGeneService.selectByMatchAuditedGeneList(query,page);
            dataMap = new LinkedHashMap<>();
            dataMap.put("mixedSampleGeneList", mixAuditedGeneList);
            logger.debug("|.审核通过的混合检材基因型信息已结束.|");
            return new ResultBean(ResultBean.CODE_SUCCESS, 0, dataMap, ResultBean.NAME_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ResultBean(ResultBean.CODE_ERROR, ErrorCode.IBOAN_REQJSON_PARSEMSG_EXCEPTION, 1, "获取信息错误!");
        }
    }

    /**
     * 获取审核通过的单一样本信息
     * @param
     * @return
     * @throws IException
     */
    @RequestMapping(value = "/getSingleSampleGene", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    @Transactional
    public ResultBean getSingleSampleGene(HttpServletRequest request, @RequestBody Integer page)throws IException {
        try {
            logger.debug("|.审核通过的混合检材基因型信息已开始.|");
            Map<String, Object> dataMap = null;
            //获取案件下审核通过的混合检材基因型信息
            MatchAuditedGeneVo query = new MatchAuditedGeneVo();
            query.getEntity().setGeneType(Constants.GENE_NORMAL);
            List<MatchAuditedGene> singleSampleGeneList = matchAuditedGeneService.selectByMatchAuditedGeneList(query,page);
            dataMap = new LinkedHashMap<>();
            dataMap.put("singleSampleGeneList", singleSampleGeneList);
            logger.debug("|.审核通过的混合检材基因型信息已结束.|");
            return new ResultBean(ResultBean.CODE_SUCCESS, 0, dataMap, ResultBean.NAME_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ResultBean(ResultBean.CODE_ERROR, ErrorCode.IBOAN_REQJSON_PARSEMSG_EXCEPTION, 1, "获取信息错误!");
        }
    }



    /**
     * 获取案件人员信息
     * @param
     * @return
     * @throws IException
     */
    @RequestMapping(value = "/getPersonInfo", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    @Transactional
    public ResultBean getPersonInfo(HttpServletRequest request, @RequestBody Integer page)throws IException {
        try {
            logger.debug("|.获取案件人员已开始.|");
            Map<String, Object> dataMap = null;
            LimsPersonInfoVo query = new LimsPersonInfoVo();
            List<LimsPersonInfo>  limsPersonInfoList = limsPersonInfoService.selectPersonInfoListData(query, page);
            dataMap = new LinkedHashMap<>();
            dataMap.put("personInfoList", limsPersonInfoList);
            logger.debug("|.获取案件人员已结束.|");
            return new ResultBean(ResultBean.CODE_SUCCESS, 0, dataMap, ResultBean.NAME_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ResultBean(ResultBean.CODE_ERROR, ErrorCode.IBOAN_REQJSON_PARSEMSG_EXCEPTION, 1, "获取信息错误!");
        }
    }

}
