package com.bazl.clims.controller.center;

import com.bazl.clims.common.Constants;
import com.bazl.clims.controller.BaseController;
import com.bazl.clims.model.DictItem;
import com.bazl.clims.model.LoaUserInfo;
import com.bazl.clims.model.PageInfo;
import com.bazl.clims.model.po.*;
import com.bazl.clims.model.vo.EquipmentInfoVo;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * Created by sixiru on 2019/4/9.
 */
@Controller
@RequestMapping("/equipmentInfo")
public class EquipmentInfoController extends BaseController{

    @Autowired
    EquipmentInfoService equipmentInfoService;

    @Autowired
    EquipmentTypeInfoService equipmentTypeInfoService;

    @Autowired
    EquipmentNameInfoService equipmentNameInfoService;
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

    @RequestMapping("equipmentInfoList")
    public ModelAndView equipmentInfoList(HttpServletRequest request, EquipmentInfoVo equipmentInfoVo, PageInfo pageInfo) {

        ModelAndView modelAndView = new ModelAndView();

        try {
            //设备状态
            DictItem dictItem = new DictItem();
            dictItem.setDictTypeCode("EQUIPMENT_STATUS");
            List<DictItem> equipmentStatusList = DictUtil.getDictList(dictItem);
            modelAndView.addObject("equipmentStatusList", equipmentStatusList);
            //初始化
            equipmentInfoVo = resetRquipmentInfoQuery(equipmentInfoVo);
            //查询设备名称
            List<EquipmentNameInfo> equipmentNameInfoList = equipmentNameInfoService.selectAllList();
            List<EquipmentInfoVo> equipmentInfoList = equipmentInfoService.selectEquipmentInfoList(equipmentInfoVo, pageInfo);
            int equipmentInfoCount = equipmentInfoService.selectEquipmentInfoCount(equipmentInfoVo);
            modelAndView.addObject("query", equipmentInfoVo);
            modelAndView.addObject("equipmentInfoList", equipmentInfoList);
            modelAndView.addObject("equipmentNameInfoList", equipmentNameInfoList);
            modelAndView.addObject("pageInfo", pageInfo.updatePageInfo(equipmentInfoCount));
            modelAndView.setViewName("equipment/equipmentInfoList");
        }catch (Exception e){
            logger.info("设备名称查询失败："+e);
        }
        return modelAndView;
    }

    public static EquipmentInfoVo resetRquipmentInfoQuery(EquipmentInfoVo query) {
        if (StringUtils.isBlank(query.getEntity().getEquipmentNo())) {
            query.getEntity().setEquipmentNo(null);
        } else {
            query.getEntity().setEquipmentNo(query.getEntity().getEquipmentNo());
        }
        return query;
    }

    /**
     * 设备添加or修改
     * @param request
     * @param equipmentInfo
     * @return
     */
    @RequestMapping(value = "/saveEquipmentInfo", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> saveEquipmentInfo(HttpServletRequest request, @RequestBody EquipmentInfo equipmentInfo) {
        Map<String, Object> result = new HashMap<>();

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if (operateUser == null) {
            result.put("date", "redirect:/login.html?timeoutFlag=1");
            return result;
        }
        try {
            //如果id不为空则修改 为空添加
            if(StringUtils.isNotBlank(equipmentInfo.getId())){
                //根据id查询设备表
                EquipmentInfo equipmentInfo1 = equipmentInfoService.selectByPrimaryKey(equipmentInfo.getId());
                equipmentInfo1.setEquipmentNameId(equipmentInfo.getEquipmentNameId());
                equipmentInfo1.setEquipmentNo(equipmentInfo.getEquipmentNo());
                equipmentInfo1.setEquipmentSpecification(equipmentInfo.getEquipmentSpecification());
                equipmentInfo1.setEquipmentStatus(equipmentInfo.getEquipmentStatus());
                equipmentInfo1.setEquipmentNum(equipmentInfo.getEquipmentNum());
                equipmentInfo1.setUseBlueWarn(equipmentInfo.getUseBlueWarn());
                equipmentInfo1.setUseRedWarn(equipmentInfo.getUseRedWarn());
                equipmentInfo1.setRepairBlueWarn(equipmentInfo.getRepairBlueWarn());
                equipmentInfo1.setRepairRedWarn(equipmentInfo.getRepairRedWarn());
                equipmentInfo1.setRemark(equipmentInfo.getRemark());
                equipmentInfo1.setUpdatePerson(operateUser.getLoginName());
                equipmentInfo1.setUpdateDatetime(new Date());
                equipmentInfoService.update(equipmentInfo1);
            }else{
                equipmentInfo.setId(UUID.randomUUID().toString());
                equipmentInfo.setCreatePerson(operateUser.getLoginName());
                equipmentInfo.setCreateDatetime(new Date());
                equipmentInfoService.insert(equipmentInfo);
            }
            result.put("success", true);
        }catch (Exception e) {
            result.put("success", false);
        }

        return result;
    }
}
