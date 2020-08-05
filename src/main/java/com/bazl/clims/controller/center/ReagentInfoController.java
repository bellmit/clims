package com.bazl.clims.controller.center;

import com.bazl.clims.common.Constants;
import com.bazl.clims.controller.BaseController;
import com.bazl.clims.model.DictItem;
import com.bazl.clims.model.LoaUserInfo;
import com.bazl.clims.model.PageInfo;
import com.bazl.clims.model.po.OrgInfo;
import com.bazl.clims.model.po.ReagentInfo;
import com.bazl.clims.model.po.ReagentOutgoInfo;
import com.bazl.clims.model.vo.LimsCaseInfoVo;
import com.bazl.clims.service.OrgInfoService;
import com.bazl.clims.service.ReagentInfoService;
import com.bazl.clims.service.ReagentOutgoInfoService;
import com.bazl.clims.utils.DictUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @author hj
 * @date 2019/4/1
 */
@Controller
@RequestMapping("/center")
public class ReagentInfoController extends BaseController{

    @Autowired
    private ReagentInfoService reagentInfoService;
    @Autowired
    private OrgInfoService orgInfoService;
    @Autowired
    private ReagentOutgoInfoService reagentOutgoInfoService;

    /**
     * 试剂名称管理
     * 查看list
     * @param request
     * @return
     */
    @RequestMapping("/reagentName")
    public ModelAndView reagentNameList(HttpServletRequest request, ReagentInfo reagentInfo,PageInfo pageInfo){
        ModelAndView view = new ModelAndView();
        view.setViewName("sysManagement/reagentName");

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if(operateUser == null){
            view.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return  view;
        }

        //获取所属单位名称
        String orgId = operateUser.getOrgId();
        List<OrgInfo> orgInfos = orgInfoService.selectAll();
        view.addObject("orgInfo", orgInfos);

        //将当前用户org_id设置进query
        if (StringUtils.isNotBlank(orgId) && orgId.contains("110023")) {
            orgId = "110230000000";
        }

        //获取名称列表信息
        reagentInfo.setOrgId(orgId);
        reagentInfo = resetReagentSuppliesInfo(reagentInfo);
        List<ReagentInfo> reagentInfoList = reagentInfoService.selectOrgId(reagentInfo,pageInfo);
        view.addObject("reagentInfoList",reagentInfoList);

        //显示条数
        int totalCnt = reagentInfoService.selectCount(reagentInfo);
        view.addObject("pageInfo", pageInfo.updatePageInfo(totalCnt));

        //获取实验阶段
        DictItem dictItem = new DictItem();
        dictItem.setDictTypeCode(Constants.EXPERIMENTAL_STAGE);
        List<DictItem> TypeList = DictUtil.getDictList(dictItem);
        view.addObject("sampleTypeList",TypeList);
        view.addObject("query", reagentInfo);

        return view;
    }

    /**
     * 添加试剂
     * @param request
     * @param reagentInfo
     * @return
     */
    @RequestMapping("/addReagents")
    @ResponseBody
    public Map<String,Object> addReagents(HttpServletRequest request, @RequestBody ReagentInfo reagentInfo){
        Map<String, Object> map = new HashMap<>();
        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if (operateUser == null) {
            map.put("date", "redirect:/login.html?timeoutFlag=1");
            return map;
        }
        //将法医的orgid统一
        String userOrgId = operateUser.getOrgId();
        //将当前用户org_id设置进query
        if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
            userOrgId = "110230000000";
        }

        if (reagentInfo.getId() !=null) {
            //修改试剂信息
            try {
                ReagentInfo reagent = reagentInfoService.selectByPrimaryKey(reagentInfo.getId());
                reagent.setReagentName(reagentInfo.getReagentName());
                reagent.setReagentNo(reagentInfo.getReagentNo());
                reagent.setExperimentalStage(reagentInfo.getExperimentalStage());
                reagent.setReagentBrand(reagentInfo.getReagentBrand());
                reagent.setReagentModel(reagentInfo.getReagentModel());
                reagent.setReagentPrice(reagentInfo.getReagentPrice());
                reagent.setReagentFirm(reagentInfo.getReagentFirm());
                reagent.setRemark(reagentInfo.getRemark());
//                reagent.setReagentType(reagentInfo.getReagentType());
//                reagent.setBatchNumber(reagentInfo.getBatchNumber());
//                reagent.setValidityTime(reagentInfo.getValidityTime());
                reagentInfoService.updateByPrimaryKey(reagent);
            }catch (Exception e) {
                logger.info("---插入试剂信息失败！---");
                logger.error("插入试剂信息失败！", e);
                map.put("success",false);
                throw e;
            }
            //修改试剂名称
            try {
                ReagentOutgoInfo reagentOutgoInfo=new ReagentOutgoInfo();
                reagentOutgoInfo.setReagentName(reagentInfo.getReagentName());
                reagentOutgoInfo.setReagentId(reagentInfo.getId());
                reagentOutgoInfoService.updateByReagentOutgoInfo(reagentOutgoInfo);
            }catch (Exception e) {
                logger.info("---修改试剂名称失败！---");
                logger.error("修改试剂名称失败！", e);
                map.put("success",false);
                throw e;
            }
        }else {
            //添加试剂信息
            try {
                reagentInfo.setId(UUID.randomUUID().toString());
                reagentInfo.setCreateDatetime(new Date());
                reagentInfo.setCreatePerson(operateUser.getLoginName());
                if (operateUser !=null){
                    reagentInfo.setOrgId(userOrgId);
                }
                reagentInfoService.insert(reagentInfo);
            }catch (Exception e) {
                logger.info("---插入试剂信息失败！---");
                logger.error("插入试剂信息失败！", e);
                map.put("success",false);
                throw e;
            }
        }
        map.put("success",true);
        return map;
    }

    public ReagentInfo resetReagentSuppliesInfo(ReagentInfo reagentInfo){

        if (StringUtils.isBlank(reagentInfo.getReagentType())) {
            reagentInfo.setReagentType(null);
        } else {
            reagentInfo.setReagentType(reagentInfo.getReagentType().trim());
        }

        if (StringUtils.isBlank(reagentInfo.getOrgId())) {
            reagentInfo.setOrgId(null);
        } else {
            reagentInfo.setOrgId(reagentInfo.getOrgId().trim());
        }

        if (StringUtils.isBlank(reagentInfo.getReagentName())) {
            reagentInfo.setReagentName(null);
        } else {
            reagentInfo.setReagentName(reagentInfo.getReagentName().trim());
        }

        if (StringUtils.isBlank(reagentInfo.getReagentNo())) {
            reagentInfo.setReagentNo(null);
        }else {
            reagentInfo.setReagentNo(reagentInfo.getReagentNo().trim());
        }

        if (StringUtils.isBlank(reagentInfo.getReagentBrand())) {
            reagentInfo.setReagentBrand(null);
        } else {
            reagentInfo.setReagentBrand(reagentInfo.getReagentBrand().trim());
        }

        if (StringUtils.isBlank(reagentInfo.getReagentModel())) {
            reagentInfo.setReagentModel(null);
        } else {
            reagentInfo.setReagentModel(reagentInfo.getReagentModel().trim());
        }

        if (StringUtils.isBlank(reagentInfo.getReagentPrice())) {
            reagentInfo.setReagentPrice(null);
        }else {
            reagentInfo.setReagentPrice(reagentInfo.getReagentPrice().trim());
        }

        if (StringUtils.isBlank(reagentInfo.getReagentFirm())) {
            reagentInfo.setReagentFirm(null);
        }else {
            reagentInfo.setReagentFirm(reagentInfo.getReagentFirm().trim());
        }

        if (StringUtils.isBlank(reagentInfo.getExperimentalStage())) {
            reagentInfo.setExperimentalStage(null);
        }else {
            reagentInfo.setExperimentalStage(reagentInfo.getExperimentalStage());
        }

        return reagentInfo;
    }
}
