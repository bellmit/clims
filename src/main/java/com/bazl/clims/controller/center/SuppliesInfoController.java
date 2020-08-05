package com.bazl.clims.controller.center;

import com.bazl.clims.common.Constants;
import com.bazl.clims.controller.BaseController;
import com.bazl.clims.model.DictItem;
import com.bazl.clims.model.LoaUserInfo;
import com.bazl.clims.model.PageInfo;
import com.bazl.clims.model.po.OrgInfo;
import com.bazl.clims.model.po.SuppliesInfo;
import com.bazl.clims.service.OrgInfoService;
import com.bazl.clims.service.SuppliesInfoService;
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
import java.util.*;

/**
 * Created by chaiyajie on 2019/5/5.
 *      耗材管理
 */


@Controller
@RequestMapping("/supplies")
public class SuppliesInfoController extends BaseController {

    @Autowired
    SuppliesInfoService suppliesInfoService;

    @Autowired
    OrgInfoService orgInfoService;

    /**
     * 试剂名称管理
     * 查看list
     * @param request
     * @return
     */
    @RequestMapping("/consumeName")
    public ModelAndView consumeName(HttpServletRequest request,SuppliesInfo suppliesInfo, PageInfo pageInfo){
        ModelAndView view = new ModelAndView();
        view.setViewName("materialManagement/consumeName");

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

        suppliesInfo.setOrgId(orgId);
        suppliesInfo = resetSuppliesInfo(suppliesInfo);
        //获取名称列表信息
        List<SuppliesInfo> suppliesInfoList = suppliesInfoService.selectOrgId(suppliesInfo);
        view.addObject("suppliesInfoList",suppliesInfoList);
        view.addObject("query", suppliesInfo);

        //获取实验阶段
        DictItem dictItem = new DictItem();
        dictItem.setDictTypeCode(Constants.EXPERIMENTAL_STAGE);
        List<DictItem> TypeList = DictUtil.getDictList(dictItem);
        view.addObject("sampleTypeList",TypeList);

        return view;
    }

    /**
     * 添加耗材
     * @param request
     * @param suppliesInfo
     * @return
     */
    @RequestMapping("/addconsume")
    @ResponseBody
    public Map<String,Object> addconsume(HttpServletRequest request, @RequestBody SuppliesInfo suppliesInfo){
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

        if (suppliesInfo.getId() !=null) {
            //修改试剂信息
            try {
                SuppliesInfo supplies = suppliesInfoService.selectByPrimaryKey(suppliesInfo.getId());
                supplies.setSuppliesName(suppliesInfo.getSuppliesName());
                supplies.setSuppliesNo(suppliesInfo.getSuppliesNo());
                supplies.setExperimentalStage(suppliesInfo.getExperimentalStage());
                supplies.setSuppliesBrand(suppliesInfo.getSuppliesBrand());
                supplies.setSuppliesModel(suppliesInfo.getSuppliesModel());
                supplies.setSuppliesPrice(suppliesInfo.getSuppliesPrice());
                supplies.setSuppliesFirm(suppliesInfo.getSuppliesFirm());
                supplies.setRemark(suppliesInfo.getRemark());
                supplies.setUpdatePerson(operateUser.getLoginName());
                supplies.setUpdateDatetime(new Date());
                suppliesInfoService.updateByPrimaryKey(supplies);
            }catch (Exception e) {
                logger.info("---插入试剂信息失败！---");
                logger.error("插入试剂信息失败！", e);
                map.put("success",false);
                throw e;
            }
        }else {
            //添加试剂信息
            try {
                suppliesInfo.setId(UUID.randomUUID().toString());
                suppliesInfo.setCreateDatetime(new Date());
                suppliesInfo.setCreatePerson(operateUser.getLoginName());
                if (operateUser !=null){
                    suppliesInfo.setOrgId(userOrgId);
                }
                suppliesInfoService.insert(suppliesInfo);
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

    public SuppliesInfo resetSuppliesInfo(SuppliesInfo suppliesInfo){

        if (StringUtils.isBlank(suppliesInfo.getOrgId())) {
            suppliesInfo.setOrgId(null);
        } else {
            suppliesInfo.setOrgId(suppliesInfo.getOrgId().trim());
        }

        if (StringUtils.isBlank(suppliesInfo.getSuppliesName())) {
            suppliesInfo.setSuppliesName(null);
        } else {
            suppliesInfo.setSuppliesName(suppliesInfo.getSuppliesName().trim());
        }

        if (StringUtils.isBlank(suppliesInfo.getSuppliesNo())) {
            suppliesInfo.setSuppliesNo(null);
        }else {
            suppliesInfo.setSuppliesNo(suppliesInfo.getSuppliesNo().trim());
        }

//        if (StringUtils.isBlank(suppliesInfo.getSuppliesBrand())) {
//            suppliesInfo.setSuppliesBrand(null);
//        } else {
//            suppliesInfo.setSuppliesBrand(suppliesInfo.getSuppliesBrand().trim());
//        }

        if (StringUtils.isBlank(suppliesInfo.getSuppliesModel())) {
            suppliesInfo.setSuppliesModel(null);
        } else {
            suppliesInfo.setSuppliesModel(suppliesInfo.getSuppliesModel().trim());
        }

//        if (StringUtils.isBlank(suppliesInfo.getSuppliesPrice())) {
//            suppliesInfo.setSuppliesPrice(null);
//        }else {
//            suppliesInfo.setSuppliesPrice(suppliesInfo.getSuppliesPrice().trim());
//        }

//        if (StringUtils.isBlank(suppliesInfo.getSuppliesFirm())) {
//            suppliesInfo.setSuppliesFirm(null);
//        }else {
//            suppliesInfo.setSuppliesFirm(suppliesInfo.getSuppliesFirm().trim());
//        }

        if (StringUtils.isBlank(suppliesInfo.getExperimentalStage())) {
            suppliesInfo.setExperimentalStage(null);
        }else {
            suppliesInfo.setExperimentalStage(suppliesInfo.getExperimentalStage());
        }

        return suppliesInfo;
    }
}
