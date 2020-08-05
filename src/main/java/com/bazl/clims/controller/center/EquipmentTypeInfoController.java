package com.bazl.clims.controller.center;

import com.bazl.clims.controller.BaseController;
import com.bazl.clims.model.*;
import com.bazl.clims.model.po.*;
import com.bazl.clims.model.DictItem;
import com.bazl.clims.model.vo.EquipmentTypeInfoVo;
import com.bazl.clims.service.*;
import com.bazl.clims.utils.DictUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.solr.client.solrj.SolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by sixiru on 2019/4/9.
 */
@Controller
@RequestMapping("/equipmentTypeInfo")
public class EquipmentTypeInfoController extends BaseController {

    @Resource
    SolrClient client;

    @Autowired
    EquipmentTypeInfoService equipmentTypeInfoService;

    @Autowired
    DictItemService dictItemService;

    @Autowired
    OrgInfoService orgInfoService;
    /**
     * 设备类型查询
     * @return
     */
    @RequestMapping("equipmentTypeList")
    public ModelAndView equipmentTypeList(HttpServletRequest request, EquipmentTypeInfoVo equipmentTypeInfoVo, PageInfo pageInfo) {
        ModelAndView modelAndView = new ModelAndView();

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if(operateUser == null){
            modelAndView.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return  modelAndView;
        }
        //将法医的orgid统一
        String userOrgId = operateUser.getOrgId();
        //将当前用户org_id设置进query
        if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
            userOrgId = "110230000000";
        }
        //设置设备所属单位为当前登录人单位
        equipmentTypeInfoVo.getEntity().setOrgId(userOrgId);

        //获取所属单位名称
        List<OrgInfo> orgInfos = orgInfoService.selectAll();
        modelAndView.addObject("orgInfo", orgInfos);

        try {
            //实验阶段
//            DictItem dictItem = new DictItem();
//            dictItem.setDictTypeCode("EXPERIMENTAL_STAGE");
            List<DictItem> experimentalStageList = dictItemService.selectListByDictTypeCode("EXPERIMENTAL_STAGE");
//            List<DictItem> experimentalStageList = DictUtil.getDictList(dictItem);
            modelAndView.addObject("experimentalStageList", experimentalStageList);
            //查询设备类型
            List<EquipmentTypeInfoVo> equipmentTypeInfoList = equipmentTypeInfoService.selectEquipmentTypeList(equipmentTypeInfoVo, pageInfo);
            int equipmentTypeCount = equipmentTypeInfoService.selectEquipmentTypeCount(equipmentTypeInfoVo);
            modelAndView.addObject("equipmentTypeInfoList", equipmentTypeInfoList);
            modelAndView.addObject("pageInfo", pageInfo.updatePageInfo(equipmentTypeCount));
            modelAndView.setViewName("equipment/equipmentTypeInfoList");
        }catch (Exception e){
            logger.info("设备类型查询失败："+e);
        }
        return modelAndView;
    }

    /**
     * 设备类型添加or修改
     * @param request
     * @param equipmentTypeInfo
     * @return
     */
    @RequestMapping(value = "/saveEquipmentTypeInfo", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> saveEquipmentTypeInfo(HttpServletRequest request, @RequestBody EquipmentTypeInfo equipmentTypeInfo) {
        Map<String, Object> result = new HashMap<>();

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if (operateUser == null) {
            result.put("date", "redirect:/login.html?timeoutFlag=1");
            return result;
        }
        //将法医的orgid统一
        String userOrgId = operateUser.getOrgId();
        //将当前用户org_id设置进query
        if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
            userOrgId = "110230000000";
        }

        try {
            //如果id不为空则修改 为空添加
            if(StringUtils.isNotBlank(equipmentTypeInfo.getId())){
                //根据id查询设备类型表
                EquipmentTypeInfo equipmentTypeInfo1 = equipmentTypeInfoService.selectByPrimaryKey(equipmentTypeInfo.getId());
                equipmentTypeInfo1.setEquipmentTypeName(equipmentTypeInfo.getEquipmentTypeName());
                equipmentTypeInfo1.setEquipmentTypeNo(equipmentTypeInfo.getEquipmentTypeNo());
                equipmentTypeInfo1.setExperimentalStage(equipmentTypeInfo.getExperimentalStage());
                equipmentTypeInfo1.setUseBlueWarn(equipmentTypeInfo.getUseBlueWarn());
                equipmentTypeInfo1.setUseRedWarn(equipmentTypeInfo.getUseRedWarn());
                equipmentTypeInfo1.setRepairBlueWarn(equipmentTypeInfo.getRepairBlueWarn());
                equipmentTypeInfo1.setRepairRedWarn(equipmentTypeInfo.getRepairRedWarn());
                equipmentTypeInfo1.setRemark(equipmentTypeInfo.getRemark());
                equipmentTypeInfo1.setUpdatePerson(operateUser.getLoginName());
                equipmentTypeInfo1.setUpdateDatetime(new Date());
                equipmentTypeInfoService.update(equipmentTypeInfo1);
            }else{
                equipmentTypeInfo.setId(UUID.randomUUID().toString());
                equipmentTypeInfo.setCreatePerson(operateUser.getLoginName());
                equipmentTypeInfo.setCreateDatetime(new Date());
                equipmentTypeInfo.setOrgId(userOrgId);
                equipmentTypeInfoService.insert(equipmentTypeInfo);
            }
            result.put("success", true);
        }catch (Exception e) {
            result.put("success", false);
        }

        return result;
    }

}
