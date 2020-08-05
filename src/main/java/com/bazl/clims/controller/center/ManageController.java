package com.bazl.clims.controller.center;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bazl.clims.common.Constants;
import com.bazl.clims.controller.BaseController;
import com.bazl.clims.model.*;
import com.bazl.clims.model.po.AmPersonalInfo;
import com.bazl.clims.model.vo.AmPersonalInfoVo;
import com.bazl.clims.service.*;
import com.bazl.clims.utils.DictUtil;
import com.bazl.clims.utils.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by Sun on 2018/12/20.
 */
@Controller
@RequestMapping("/manage")
public class ManageController extends BaseController{
    @Autowired
    AmPersonalInfoService amPersonalInfoService;
    @Autowired
    LoaUserInfoService loaUserInfoService;
    @Autowired
    LoaRoleService loaRoleService;
    @Autowired
    LoaRoleRelationService loaRoleRelationService;
    @Autowired
    DictItemService dictItemService;
    @Autowired
    DictInfoService dictInfoService;
    @Autowired
    LoaPermissionService loaPermissionService;

    /**
     * 个人信息管理
     * @return
     */
    @RequestMapping("/personalInformation")
    public ModelAndView personalInformation (HttpSession session){
        LoaUserInfo user = (LoaUserInfo) session.getAttribute("user");
        ModelAndView view = new ModelAndView();
        //根据用户主键ID查询回显到委托人个人中心
        AmPersonalInfo amPersonalInfo =amPersonalInfoService.queryAmPersonalInfo(user.getPersonalId());
        /**
         *查询字典信息表DICT_ITEM
         * 回显个人中心职务
         */
        DictItem dictItem = new DictItem();
        dictItem.setDictTypeCode(Constants.POSITION_LIST);
        List<DictItem> listDictItem = DictUtil.getDictList(dictItem);
        view.addObject("listDictItem",listDictItem);
        view.addObject("amObject",amPersonalInfo);
        view.setViewName("manage/personalInformation");
        return view;
    }
    /**
     * 个人信息管理
     * @return
     */
    @RequestMapping("/updateAmPersonalInfoAndLoaUserInfo")
    public String updatepersonalInformation (AmPersonalInfo amPersonalInfo){
        //根据用户主键ID查询修改委托人个人中心信息
        amPersonalInfoService.updateAmPersonalInfoById(amPersonalInfo);
        loaUserInfoService.updateloaUserInfoByUserId(amPersonalInfo);
        return "redirect:/manage/personalInformation";
    }

    /**
     * 受理人管理查询
     * @return
     */
    @RequestMapping("/accepterManage")
    public ModelAndView accepterManage (HttpServletRequest request){
        ModelAndView view = new ModelAndView();
        view.setViewName("manage/accepterManage");

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if(operateUser == null){
            view.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return  view;
        }

        /**
         *查询角色信息表Loa_Role
         * 回显委托人添加权限设定
         */
        List<LoaRole>LoaRoleList = loaRoleService.queryLoaRole();
        view.addObject("LoaRoleList",LoaRoleList);
        /**
         *查询字典信息表DICT_ITEM
         * 回显委托人添加职务
         */
        DictItem dictItem = new DictItem();
        dictItem.setDictTypeCode(Constants.POSITION_LIST);
        List<DictItem> listDictItem = DictUtil.getDictList(dictItem);
        view.addObject("listDictItem",listDictItem);
        /**
         * 查询Am_Personal_Info ap , Loa_User_Info lu  ,LOA_ROLE_RELATION lrr , Loa_Role lr
         * 返回到受理管理展示页面
         */
        List<AmPersonalInfoVo> amPersonalInfoVoList = new ArrayList<>();
        if(StringUtils.isBlank(operateUser.getOrgId()) || operateUser.getOrgId().contains("110023") || operateUser.getOrgId().contains("11023") ){
            amPersonalInfoVoList = amPersonalInfoService.queryAmPersonalInfoVoList(null);
        }else {

            amPersonalInfoVoList = amPersonalInfoService.queryAmPersonalInfoVoList(operateUser.getOrgId());
        }

        //获取受理人权限

        if(amPersonalInfoVoList.size()>0){
            for(int i = 0; i < amPersonalInfoVoList.size();i++){
                String personalId = amPersonalInfoVoList.get(i).getEntity().getPersonalId();
                List<LoaRole>  loaRoleList = loaRoleService.listRolesByPersonalId(personalId);
                StringBuilder stringBuilder  = new StringBuilder();
                if(loaRoleList.size()>0){
                    for(int j = 0;j < loaRoleList.size();j++){
                        String roleName = loaRoleList.get(j).getRoleName();
                        stringBuilder.append(roleName);
                    }
                }
                amPersonalInfoVoList.get(i).getEntity().setRoleName(stringBuilder.toString());
            }
        }

        view.addObject("amPersonalInfoVoList",amPersonalInfoVoList);
        return view;
    }


    /**
     * 权限分配：由分局管理员启用受理人账号
     * @return
     */
    @RequestMapping(value="/updateRole", method = RequestMethod.POST, produces="application/json; charset=utf-8")
    @ResponseBody
    public Map<String,Object> updateRole(@RequestParam(value = "params")String params){
        Map<String,Object> returnMap=new HashMap();

        JSONObject str = JSON.parseObject(params);
        List<AmPersonalInfo> amPersonalInfoList = new ArrayList<>();
        if(str.containsKey("amPersonalInfoList")) {
            amPersonalInfoList = (List<AmPersonalInfo>) JSON.parseArray(str.get("amPersonalInfoList").toString(), AmPersonalInfo.class);
        }
        //遍历前台传来的要分配权限的数据
        for (AmPersonalInfo amPersonalInfo : amPersonalInfoList) {
            //根据personalId查询用户表
            List<LoaUserInfo> loaUserInfoList = loaUserInfoService.queryloaUserInfoByPersonalId(amPersonalInfo.getPersonalId());
            if (null != loaUserInfoList && loaUserInfoList.size() > 0) {
                //删除权限
                loaRoleRelationService.deleteloaRoleRelationByUserId(loaUserInfoList.get(0).getUserId());
                //添加权限
                LoaRoleRelation loaRoleRelation = new LoaRoleRelation();
                loaRoleRelation.setId(UUID.randomUUID().toString());
                loaRoleRelation.setRoleId(amPersonalInfo.getRoleId());
                loaRoleRelation.setUserId(loaUserInfoList.get(0).getUserId());
                loaRoleRelationService.addLoaRoleRelation(loaRoleRelation);
            }
        }
        returnMap.put("success",true);
        return returnMap;
    }

    /**
     * 字典管理查询 DICT_INFO
     * @return
     */
    @RequestMapping("/dictionariesManage")
    public ModelAndView dictionariesManage (HttpServletRequest request){
        ModelAndView view = new ModelAndView();
        view.setViewName("manage/dictionariesManage");

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if(operateUser == null){
            view.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return  view;
        }

        //查询字典信息DICT_INFO
        List<DictInfo> dictInfoList = dictInfoService.selectAll();
        if (ListUtils.isNotNullAndEmptyList(dictInfoList)){
            view.addObject("dictInfoList",dictInfoList);
        }
        return view;
    }

    /**
     * 删除字典管理查询 DICT_INFO
     */
    @RequestMapping("/deleteDictionariesManage")
    public ModelAndView deleteDictionariesManage (HttpServletRequest request,HttpSession session,String dictInfoId){
        ModelAndView view = new ModelAndView();
        view.setViewName("manage/dictionariesManage");

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if(operateUser == null){
            view.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return  view;
        }

        //根据dictinfoid查询dictinfo的信息
        DictInfo dictInfo = dictInfoService.selectByPrimaryKey(dictInfoId);
        if (dictInfo != null) {
            //获取dictTypeCode
            String dictTypeCode = dictInfo.getDictTypeCode();
            //根据 dictTypeCode 查询 dictitem信息
            List<DictItem> dictItems = dictItemService.selectListByDictTypeCode(dictTypeCode);
            if (ListUtils.isNotNullAndEmptyList(dictItems)){
                for (DictItem dictitem : dictItems){
                    //获取dictitemid
                    String dictItemId = dictitem.getDictItemId();
                    try {
                        //先将dictitem删除
                        dictItemService.deleteByPrimaryKey(dictItemId);
                    } catch (Exception e) {
                        logger.info("---删除dictitem字典信息失败！---");
                        logger.error("删除dictitem字典信息失败！", e);
                        throw e;
                    }
                }
            }

            try {
                //删除 dictinfo 信息
                dictInfoService.deleteByPrimaryKey(dictInfoId);
            } catch (Exception e) {
                logger.info("---删除dictinfo字典信息失败！---");
                logger.error("删除dictinfo字典信息失败！", e);
                throw e;
            }

            //查询出新的数据
            List<DictInfo> dictInfos = dictInfoService.selectAll();
            view.addObject("dictInfoList", dictInfos);

            //删除原来的字典项
            session.removeAttribute("listDictItem");
            //字典项所有数据存入session
            List<DictItem> listDictItem = dictItemService.selectAllCode();
            session.setAttribute("listDictItem", listDictItem);

        }
        return view;
    }

    /**
     * 修改添加字典管理 DICT_INFO
     * @return
     */
    @RequestMapping("/saveDictionariesManage")
    @ResponseBody
    public Map<String,Object> saveDictionariesManage (HttpServletRequest request,@RequestBody DictInfo dictInfo){
        Map<String, Object> map = new HashMap<>();

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if(operateUser != null){

            if (dictInfo.getDictInfoId() !=null ){
                //获取修改对象
                DictInfo dict = dictInfoService.selectByPrimaryKey(dictInfo.getDictInfoId());
                dict.setDictTypeCode(dictInfo.getDictTypeCode());
                dict.setDictTypeName(dictInfo.getDictTypeName());
                try {
                    //修改
                    dictInfoService.updateByPrimaryKey(dict);
                }catch (Exception e) {
                    logger.info("---更新字典信息失败！---");
                    logger.error("更新字典信息失败！", e);
                    map.put("success", false);
                    throw e;
                }
            }else {
                dictInfo.setDictInfoId(UUID.randomUUID().toString());
                dictInfo.setCreateDatetime(new Date());
                dictInfo.setCreatePerson(operateUser.getLoginName());
                try {
                    dictInfoService.insert(dictInfo);
                } catch (Exception e) {
                    logger.info("---添加字典信息失败！---");
                    logger.error("添加字典信息失败！", e);
                    map.put("success", false);
                    throw e;
                }
            }
            map.put("success",true);
        }

        return map;
    }

    /**
     * 刷新字典管理查询 DICT_INFO
     * @return
     */
    @RequestMapping("/refreshDictionariesManage")
    public ModelAndView refreshDictionariesManage (HttpServletRequest request,HttpSession session){
        ModelAndView view = new ModelAndView();
        view.setViewName("manage/dictionariesManage");

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if(operateUser == null){
            view.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return  view;
        }

        //查询字典信息DICT_INFO
        List<DictInfo> dictInfoList = dictInfoService.selectAll();
        if (ListUtils.isNotNullAndEmptyList(dictInfoList)){
            view.addObject("dictInfoList",dictInfoList);
        }

        //删除原来的字典项
        session.removeAttribute("listDictItem");
        //字典项所有数据存入session
        List<DictItem> listDictItem = dictItemService.selectAllCode();
        session.setAttribute("listDictItem", listDictItem);

        return view;
    }

    /**
     * 字典管理查询 DICT_ITEM
     * @return
     */
    @RequestMapping("/getChildrenDictItem")
    public ModelAndView getChildrenDictItem (HttpServletRequest request,String dictTypeCode){
        ModelAndView view = new ModelAndView();

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if(operateUser == null){
            view.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return  view;
        }

        //查询字典信息 DICT_ITEM
        List<DictItem> dictItemslist = dictItemService.selectListByDictTypeCode(dictTypeCode);
        if (ListUtils.isNotNullAndEmptyList(dictItemslist)){
            view.addObject("dictItemslist",dictItemslist);
        }

        view.addObject("dictTypeCode",dictTypeCode);
        view.setViewName("/manage/getChildrenDictItem");
        return view;
    }

    /**
     * 删除字典管理查询 DICT_ITEM
     */
    @RequestMapping("/deleteChildrenDictItem")
    public ModelAndView deleteChildrenDictItem (HttpServletRequest request,HttpSession session,String dictItemId){
        ModelAndView view = new ModelAndView();
        view.setViewName("manage/getChildrenDictItem");

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if(operateUser == null){
            view.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return  view;
        }

        //获取数据信息
        DictItem dictItem = dictItemService.selectByPrimaryKey(dictItemId);
        if (dictItem != null){
            //将数据删除
            try {
                dictItemService.deleteByPrimaryKey(dictItemId);
            } catch (Exception e) {
                logger.info("---删除dictitem字典信息失败！---");
                logger.error("删除dictitem字典信息失败！", e);
                throw e;
            }
        }

        //查询出新的数据
        String dictTypeCode = dictItem.getDictTypeCode();
        List<DictItem> dictItems = dictItemService.selectListByDictTypeCode(dictTypeCode);
        view.addObject("dictItemslist", dictItems);

        //删除原来的字典项
        session.removeAttribute("listDictItem");
        //字典项所有数据存入session
        List<DictItem> listDictItem = dictItemService.selectAllCode();
        session.setAttribute("listDictItem", listDictItem);

        return view;
    }

    /**
     * 修改添加字典管理 DICT_ITEM
     * @return
     */
    @RequestMapping("/saveChildrenDictItem")
    @ResponseBody
    public Map<String,Object> saveChildrenDictItem (HttpServletRequest request, String dictTypeCode,@RequestBody DictItem dictItem){
        Map<String, Object> map = new HashMap<>();

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if(operateUser != null){

            //获取修改对象
            if (dictItem.getDictItemId() != null){
                //修改
                DictItem dict = dictItemService.selectByPrimaryKey(dictItem.getDictItemId());
                dict.setDictName(dictItem.getDictName());
                dict.setDictDesc(dictItem.getDictDesc());
                dict.setDictCode(dictItem.getDictCode());
                try {
                    dictItemService.updateByPrimaryKey(dict);
                }catch (Exception e) {
                    logger.info("---更新字典信息失败！---");
                    logger.error("更新字典信息失败！", e);
                    map.put("success",false);
                    throw e;
                }
            }else {
                //添加
                //获取dictinfoid
                DictInfo dictInfo = dictInfoService.selectByDictTypeCode(dictTypeCode);
                if (dictInfo != null) {
                    //赋值
                    dictItem.setDictItemId(UUID.randomUUID().toString());
                    dictItem.setDictInfoId(dictInfo.getDictInfoId());
                    dictItem.setCreateDatetime(new Date());
                    dictItem.setCreatePerson(operateUser.getLoginName());
                    dictItem.setDictTypeCode(dictTypeCode);
                    try {
                        dictItemService.insert(dictItem);
                    } catch (Exception e) {
                        logger.info("---添加字典信息失败！---");
                        logger.error("添加字典信息失败！", e);
                        map.put("success", false);
                        throw e;
                    }
                }
            }
            map.put("success",true);
        }
        return map;
    }

    /**
     * 刷新字典管理查询 DICT_ITEM
     * @return
     */
    @RequestMapping("/refreshChildrenDictItem")
    public ModelAndView refreshChildrenDictItem (HttpServletRequest request,HttpSession session,String dictTypeCode){
        ModelAndView view = new ModelAndView();

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if(operateUser == null){
            view.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return  view;
        }

        //查询字典信息 DICT_ITEM
        List<DictItem> dictItemslist = dictItemService.selectListByDictTypeCode(dictTypeCode);
        if (ListUtils.isNotNullAndEmptyList(dictItemslist)){
            view.addObject("dictItemslist",dictItemslist);
        }

        //删除原来的字典项
        session.removeAttribute("listDictItem");
        //字典项所有数据存入session
        List<DictItem> listDictItem = dictItemService.selectAllCode();
        session.setAttribute("listDictItem", listDictItem);

        view.addObject("dictTypeCode",dictTypeCode);
        view.setViewName("/manage/getChildrenDictItem");
        return view;
    }





}
