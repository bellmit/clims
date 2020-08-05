package com.bazl.clims.controller.center;

import com.bazl.clims.controller.BaseController;
import com.bazl.clims.dao.EquipmentTypeInfoMapper;
import com.bazl.clims.model.DictItem;
import com.bazl.clims.model.LoaUserInfo;
import com.bazl.clims.model.PageInfo;
import com.bazl.clims.model.po.EquipmentNameInfo;
import com.bazl.clims.model.po.EquipmentTypeInfo;
import com.bazl.clims.model.po.OrgInfo;
import com.bazl.clims.model.vo.EquipmentNameInfoVo;
import com.bazl.clims.model.vo.EquipmentTypeInfoVo;
import com.bazl.clims.service.DictItemService;
import com.bazl.clims.service.EquipmentNameInfoService;
import com.bazl.clims.service.EquipmentTypeInfoService;
import com.bazl.clims.service.OrgInfoService;
import com.bazl.clims.utils.DictUtil;
import com.bazl.clims.utils.InitializationData;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.solr.client.solrj.SolrClient;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/equipmentNameInfo")
public class EquipmentNameInfoController extends BaseController {

    @Resource
    SolrClient client;

    @Autowired
    EquipmentTypeInfoService equipmentTypeInfoService;

    @Autowired
    DictItemService dictItemService;

    @Autowired
    EquipmentNameInfoService equipmentNameInfoService;

    @Autowired
    OrgInfoService orgInfoService;

    /**
     * 设备名称查询
     * @return
     */
    @RequestMapping("equipmentNameList")
    public ModelAndView equipmentNameList(HttpServletRequest request, EquipmentNameInfoVo equipmentNameInfoVo, PageInfo pageInfo) {
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

        //获取所属单位名称
        List<OrgInfo> orgInfos = orgInfoService.selectAll();
        modelAndView.addObject("orgInfo", orgInfos);

        //设备编号
        List<DictItem> dictItemslist = dictItemService.selectListByDictTypeCode("EQUIPMENT_NO");
        modelAndView.addObject("dictItemslist", dictItemslist);

        try {

            //查询设备类型
            EquipmentTypeInfoVo equipmentTypeInfoVo = new EquipmentTypeInfoVo();
            equipmentTypeInfoVo.getEntity().setOrgId(userOrgId);
            List<EquipmentTypeInfoVo> equipmentTypeInfoList = equipmentTypeInfoService.selectEquipmentTypeList(equipmentTypeInfoVo, pageInfo);
//            List<EquipmentTypeInfo> equipmentTypeInfos = equipmentTypeInfoService.selectAllList();
            if(operateUser.getOrgId()==null){
                List<EquipmentTypeInfo> equipmentTypeInfos = equipmentTypeInfoService.selectAllList();
                modelAndView.addObject("equipmentTypeInfos", equipmentTypeInfos);
            }else{
                List<EquipmentTypeInfo> equipmentTypeInfos = equipmentTypeInfoService.selectBy(userOrgId);
                modelAndView.addObject("equipmentTypeInfos", equipmentTypeInfos);
            }


            //初始化
            equipmentNameInfoVo = resetRquipmentNameInfoQuery(equipmentNameInfoVo);
            //设置设备所属单位为当前登录人单位
            equipmentNameInfoVo.getEntity().setOrgId(userOrgId);
            //查询设备名称
            List<EquipmentNameInfoVo> equipmentNameInfoList = equipmentNameInfoService.selectEquipmentNameList(equipmentNameInfoVo, pageInfo);
            int equipmentNameCount = equipmentNameInfoService.selectEquipmentNameCount(equipmentNameInfoVo);
            modelAndView.addObject("query", equipmentNameInfoVo);

            modelAndView.addObject("equipmentNameInfoList", equipmentNameInfoList);
            modelAndView.addObject("equipmentTypeInfoList", equipmentTypeInfoList);
            modelAndView.addObject("pageInfo", pageInfo.updatePageInfo(equipmentNameCount));
            modelAndView.setViewName("equipment/equipmentNameInfoList");
        }catch (Exception e){
            logger.info("设备名称查询失败："+e);
        }
        return modelAndView;
    }

    /**
     * 设备名称添加or修改
     * @param request
     * @param equipmentNameInfo
     * @return
     */
    @RequestMapping(value = "/saveEquipmentNameInfo", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> saveEquipmentNameInfo(HttpServletRequest request, @RequestBody EquipmentNameInfo equipmentNameInfo) {
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
            if(StringUtils.isNotBlank(equipmentNameInfo.getId())){
                //根据id查询设备名称表
                EquipmentNameInfo equipmentNameInfo1 = equipmentNameInfoService.selectByPrimaryKey(equipmentNameInfo.getId());
                equipmentNameInfo1.setEquipmentTypeId(equipmentNameInfo.getEquipmentTypeId());
                equipmentNameInfo1.setEquipmentNo(equipmentNameInfo.getEquipmentNo());
                equipmentNameInfo1.setEquipmentName(equipmentNameInfo.getEquipmentName());
                equipmentNameInfo1.setUpdatePerson(operateUser.getLoginName());
                equipmentNameInfo1.setUpdateDatetime(new Date());
                equipmentNameInfoService.update(equipmentNameInfo1);
            }else{
                equipmentNameInfo.setId(UUID.randomUUID().toString());
                equipmentNameInfo.setCreatePerson(operateUser.getLoginName());
                equipmentNameInfo.setCreateDatetime(new Date());
                equipmentNameInfo.setOrgId(userOrgId);
                equipmentNameInfoService.insert(equipmentNameInfo);
            }
            result.put("success", true);
        }catch (Exception e) {
            result.put("success", false);
        }

        return result;
    }

    /**
     * 删除设备名称
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("deleteEquipmentName")
    public Map<String, Object> deleteEquipmentName(HttpServletRequest request, String id) {
        Map<String, Object> result = new HashMap<>();

        try {
            //删除设备名称
            equipmentNameInfoService.deleteByPrimaryKey(id);
            result.put("success", true);
        }catch (Exception e){
            logger.info("删除设备名称失败："+e);
            result.put("success", false);
        }
        return result;
    }

    public static EquipmentNameInfoVo resetRquipmentNameInfoQuery(EquipmentNameInfoVo query) {
        if (StringUtils.isBlank(query.getEntity().getOrgId())) {
            query.getEntity().setOrgId(null);
        } else {
            query.getEntity().setOrgId(query.getEntity().getEquipmentName());
        }

        if (StringUtils.isBlank(query.getEntity().getEquipmentName())) {
            query.getEntity().setEquipmentName(null);
        } else {
            query.getEntity().setEquipmentName(query.getEntity().getEquipmentName());
        }

        if (StringUtils.isBlank(query.getEntity().getEquipmentNo())) {
            query.getEntity().setEquipmentNo(null);
        } else {
            query.getEntity().setEquipmentNo(query.getEntity().getEquipmentNo().trim());
        }

        if (StringUtils.isBlank(query.getEquipmentTypeName())) {
            query.getEntity().setEquipmentTypeId(null);
        } else {
            query.getEntity().setEquipmentTypeId(query.getEquipmentTypeName().trim());
        }

        return query;
    }
}
