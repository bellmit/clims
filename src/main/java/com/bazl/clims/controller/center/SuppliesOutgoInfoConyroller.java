package com.bazl.clims.controller.center;

import com.bazl.clims.common.Constants;
import com.bazl.clims.controller.BaseController;
import com.bazl.clims.model.DictItem;
import com.bazl.clims.model.LoaUserInfo;
import com.bazl.clims.model.PageInfo;
import com.bazl.clims.model.po.*;
import com.bazl.clims.service.OrgInfoService;
import com.bazl.clims.service.SuppliesInfoService;
import com.bazl.clims.service.SuppliesOutgoInfoService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2019/5/5.
 */

@Controller
@RequestMapping("/supplies")
public class SuppliesOutgoInfoConyroller extends BaseController {

    @Autowired
    SuppliesOutgoInfoService suppliesOutgoInfoService;

    @Autowired
    OrgInfoService orgInfoService;

    @Autowired
    SuppliesInfoService suppliesInfoService;

    /**
     * 入库管理页面
     *
     * @param request
     * @param pageInfo
     * @return
     */
    @RequestMapping("/consumeInStock")
    public ModelAndView consumeInStock(HttpServletRequest request,SuppliesOutgoInfo suppliesOutgoInfo, PageInfo pageInfo) {
        ModelAndView view = new ModelAndView();
        view.setViewName("materialManagement/consumeInStock");

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if (operateUser == null) {
            view.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return view;
        }
        //将法医的orgid统一
        String orgId = operateUser.getOrgId();
        //将当前用户org_id设置进query
        if (StringUtils.isNotBlank(orgId) && orgId.contains("110023")) {
            orgId = "110230000000";
        }

        //获取所属单位名称
        List<OrgInfo> orgInfos = orgInfoService.selectAll();
        view.addObject("orgInfo", orgInfos);

        //获取入库列表信息
        suppliesOutgoInfo.setOrgId(orgId);
        suppliesOutgoInfo.setRecordType("0");
        suppliesOutgoInfo = resetSuppliesOutgoInfo(suppliesOutgoInfo);
        List<SuppliesOutgoInfo> suppliesOutgoInfoList = suppliesOutgoInfoService.selectByRecordType(suppliesOutgoInfo);
        view.addObject("suppliesOutgoInfoList", suppliesOutgoInfoList);
        view.addObject("query", suppliesOutgoInfo);

        //获取耗材名称
        SuppliesInfo suppliesInfo = new SuppliesInfo();
        suppliesInfo.setOrgId(orgId);
        List<SuppliesInfo> suppliesInfoList = suppliesInfoService.selectOrgId(suppliesInfo);
        view.addObject("suppliesInfoList", suppliesInfoList);

        return view;
    }

    /**
     * 添加入库
     *
     * @param request
     * @param pageInfo
     * @return
     */
    @RequestMapping("/addConsumeInStock")
    @ResponseBody
    public Map<String, Object> addConsumeInStock(HttpServletRequest request, PageInfo pageInfo, @RequestBody SuppliesOutgoInfo suppliesOutgoInfo) {
        Map<String, Object> result = new HashMap<String, Object>();

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

        if (suppliesOutgoInfo.getId() != null) {
            //修改数据
            SuppliesOutgoInfo suppliesIn = suppliesOutgoInfoService.selectByPrimaryKey(suppliesOutgoInfo.getId());
            suppliesIn.setSuppliesId(suppliesOutgoInfo.getSuppliesId());
            suppliesIn.setStorageNum(suppliesOutgoInfo.getStorageNum());
            suppliesIn.setStoragePerson(suppliesOutgoInfo.getStoragePerson());
            suppliesIn.setEffectiveDatetime(suppliesOutgoInfo.getEffectiveDatetime());
            suppliesIn.setStorageDatetime(suppliesOutgoInfo.getStorageDatetime());
            suppliesIn.setStorageRemark(suppliesOutgoInfo.getStorageRemark());
            try {
                suppliesOutgoInfoService.updateByPrimaryKey(suppliesIn);
            } catch (Exception e) {
                logger.info("---修改入库试剂失败！---");
                logger.error("修改入库试剂失败！", e);
                result.put("success", false);
                throw e;
            }
        } else {
            //添加数据
            suppliesOutgoInfo.setId(UUID.randomUUID().toString());
            suppliesOutgoInfo.setRecordType("0");
            if (operateUser != null) {
                suppliesOutgoInfo.setOrgId(userOrgId);
            }
            try {
                suppliesOutgoInfoService.insert(suppliesOutgoInfo);
            } catch (Exception e) {
                logger.info("---添加入库试剂失败！---");
                logger.error("添加入库试剂失败！", e);
                result.put("success", false);
                throw e;
            }
        }
        result.put("success", true);
        return result;
    }

    /**
     * 出库管理页面
     *
     * @param request
     * @param pageInfo
     * @return
     */
    @RequestMapping("/consumeOutStock")
    public ModelAndView consumeOutStock(HttpServletRequest request,SuppliesOutgoInfo suppliesOutgoInfo, PageInfo pageInfo) {
        ModelAndView view = new ModelAndView();
        view.setViewName("materialManagement/consumeOutStock");

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if (operateUser == null) {
            view.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return view;
        }
        //将法医的orgid统一
        String orgId = operateUser.getOrgId();
        //将当前用户org_id设置进query
        if (StringUtils.isNotBlank(orgId) && orgId.contains("110023")) {
            orgId = "110230000000";
        }

        //获取所属单位名称
        List<OrgInfo> orgInfos = orgInfoService.selectAll();
        view.addObject("orgInfo", orgInfos);

        //获取出库列表信息
        suppliesOutgoInfo.setOrgId(orgId);
        suppliesOutgoInfo.setRecordType("1");
        suppliesOutgoInfo = resetSuppliesOutgoInfo(suppliesOutgoInfo);
        List<SuppliesOutgoInfo> suppliesOutgoInfoList = suppliesOutgoInfoService.selectByRecordType(suppliesOutgoInfo);
        view.addObject("suppliesOutgoInfoList", suppliesOutgoInfoList);
        view.addObject("query", suppliesOutgoInfo);

        //获取耗材名称
        SuppliesInfo suppliesInfo = new SuppliesInfo();
        suppliesInfo.setOrgId(orgId);
        List<SuppliesInfo> suppliesInfoList = suppliesInfoService.selectOrgId(suppliesInfo);
        view.addObject("suppliesInfoList", suppliesInfoList);
//        SuppliesOutgoInfo goInfo = new SuppliesOutgoInfo();
//        goInfo.setOrgId(operateUser.getOrgId());
//        goInfo.setRecordType("0");
//        List<SuppliesOutgoInfo> intgoInfoList = suppliesOutgoInfoService.selectByRecordType(goInfo);
//        view.addObject("suppliesInfoList", intgoInfoList);

        return view;
    }

    /**
     * 添加出库
     *
     * @param request
     * @param pageInfo
     * @return
     */
    @RequestMapping("/addConsumeOutStock")
    @ResponseBody
    public Map<String, Object> addConsumeOutStock(HttpServletRequest request, PageInfo pageInfo, @RequestBody SuppliesOutgoInfo suppliesOutgoInfo) {
        Map<String, Object> result = new HashMap<String, Object>();

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

        if (suppliesOutgoInfo.getId() != null) {
            //修改数据
            SuppliesOutgoInfo suppliesIn = suppliesOutgoInfoService.selectByPrimaryKey(suppliesOutgoInfo.getId());
            suppliesIn.setSuppliesId(suppliesOutgoInfo.getSuppliesId());
            suppliesIn.setStorageNum(suppliesOutgoInfo.getStorageNum());
            suppliesIn.setStoragePerson(suppliesOutgoInfo.getStoragePerson());
            suppliesIn.setEffectiveDatetime(suppliesOutgoInfo.getEffectiveDatetime());
            suppliesIn.setStorageDatetime(suppliesOutgoInfo.getStorageDatetime());
            suppliesIn.setStorageRemark(suppliesOutgoInfo.getStorageRemark());
            try {
                suppliesOutgoInfoService.updateByPrimaryKey(suppliesIn);
            } catch (Exception e) {
                logger.info("---修改出库试剂失败！---");
                logger.error("修改出库试剂失败！", e);
                result.put("success", false);
                throw e;
            }
        } else {
            //添加数据
            suppliesOutgoInfo.setId(UUID.randomUUID().toString());
            suppliesOutgoInfo.setRecordType("1");
            if (operateUser != null) {
                suppliesOutgoInfo.setOrgId(userOrgId);
            }
            try {
                suppliesOutgoInfoService.insert(suppliesOutgoInfo);
            } catch (Exception e) {
                logger.info("---添加出库试剂失败！---");
                logger.error("添加出库试剂失败！", e);
                result.put("success", false);
                throw e;
            }
        }
        result.put("success", true);
        return result;
    }

    /**
     * 出入库管理页面
     * @param request
     * @param pageInfo
     * @return
     */
    @RequestMapping("/consumeOutInStock")
    public ModelAndView consumeOutInStock(HttpServletRequest request,SuppliesOutgoInfo suppliesOutgoInfo, PageInfo pageInfo){
        ModelAndView view = new ModelAndView();
        view.setViewName("materialManagement/queryOutInStock");

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if(operateUser == null){
            view.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return  view;
        }
        //将法医的orgid统一
        String userOrgId = operateUser.getOrgId();
        //将当前用户org_id设置进query
        if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
            userOrgId = "110230000000";
        }

        //获取列表信息
        suppliesOutgoInfo.setOrgId(userOrgId);
        suppliesOutgoInfo = resetSuppliesOutgoInfo(suppliesOutgoInfo);
        List<SuppliesOutgoInfo> outgoInfoList = suppliesOutgoInfoService.selectByRecordType(suppliesOutgoInfo);
        view.addObject("OutgoInfoList", outgoInfoList);
        view.addObject("query", suppliesOutgoInfo);

        //获取耗材名称
        SuppliesInfo suppliesInfo = new SuppliesInfo();
        suppliesInfo.setOrgId(userOrgId);
        List<SuppliesInfo> suppliesInfoList = suppliesInfoService.selectOrgId(suppliesInfo);
        view.addObject("suppliesInfoList", suppliesInfoList);


        return view;
    }

    /**
     * 库存管理页面
     * @param request
     * @param pageInfo
     * @return
     */
    @RequestMapping("/consumeInventory")
    public ModelAndView consumeInventory(HttpServletRequest request, SuppliesOutgoInfo suppliesOutgoInfo,PageInfo pageInfo) {
        ModelAndView view = new ModelAndView();
        view.setViewName("materialManagement/consumeInventory");

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if(operateUser == null){
            view.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return  view;
        }
        //将法医的orgid统一
        String userOrgId = operateUser.getOrgId();
        //将当前用户org_id设置进query
        if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
            userOrgId = "110230000000";
        }

//        //获取列表信息
//        SuppliesInfo suppliesInfo = new SuppliesInfo();
//        suppliesInfo.setOrgId(operateUser.getOrgId());
//        List<SuppliesInfo> suppliesInfoList = suppliesInfoService.selectOrgId(suppliesInfo);
//        view.addObject("suppliesInfoList", suppliesInfoList);

        //获取库存数
        suppliesOutgoInfo.setOrgId(userOrgId);
        suppliesOutgoInfo = resetSuppliesOutgoInfo(suppliesOutgoInfo);
        List<SuppliesOutgoInfo> suppliesList = suppliesOutgoInfoService.selectByStorageNum(suppliesOutgoInfo);
        view.addObject("suppliesInfoList", suppliesList);
        view.addObject("query", suppliesOutgoInfo);

        //获取实验阶段
        DictItem dictItem = new DictItem();
        dictItem.setDictTypeCode(Constants.EXPERIMENTAL_STAGE);
        List<DictItem> TypeList = DictUtil.getDictList(dictItem);
        view.addObject("sampleTypeList",TypeList);
        return view;
    }

    //查询
    public SuppliesOutgoInfo resetSuppliesOutgoInfo(SuppliesOutgoInfo suppliesOutgoInfo){

        if (StringUtils.isBlank(suppliesOutgoInfo.getOrgId())) {
            suppliesOutgoInfo.setOrgId(null);
        } else {
            suppliesOutgoInfo.setOrgId(suppliesOutgoInfo.getOrgId().trim());
        }

        if (StringUtils.isBlank(suppliesOutgoInfo.getRecordType())) {
            suppliesOutgoInfo.setRecordType(null);
        }else {
            suppliesOutgoInfo.setRecordType(suppliesOutgoInfo.getRecordType());
        }

        if(StringUtils.isBlank(suppliesOutgoInfo.getSuppliesName())){
            suppliesOutgoInfo.setSuppliesName(null);
        }else {
            suppliesOutgoInfo.setSuppliesName(suppliesOutgoInfo.getSuppliesName().trim());
        }

        if(StringUtils.isBlank(suppliesOutgoInfo.getSuppliesNo())){
            suppliesOutgoInfo.setSuppliesNo(null);
        }else {
            suppliesOutgoInfo.setSuppliesNo(suppliesOutgoInfo.getSuppliesNo().trim());
        }

        if(StringUtils.isBlank(suppliesOutgoInfo.getSuppliesModel())){
            suppliesOutgoInfo.setSuppliesModel(null);
        }else {
            suppliesOutgoInfo.setSuppliesModel(suppliesOutgoInfo.getSuppliesModel().trim());
        }

        if(StringUtils.isBlank(suppliesOutgoInfo.getExperimentalStage())){
            suppliesOutgoInfo.setExperimentalStage(null);
        }else {
            suppliesOutgoInfo.setExperimentalStage(suppliesOutgoInfo.getExperimentalStage().trim());
        }

        if (StringUtils.isBlank(suppliesOutgoInfo.getStoragePerson())) {
            suppliesOutgoInfo.setStoragePerson(null);
        }else {
            suppliesOutgoInfo.setStoragePerson(suppliesOutgoInfo.getStoragePerson().trim());
        }

        if (suppliesOutgoInfo.getStorageDatetime() == null) {
            suppliesOutgoInfo.setStorageDatetime(null);
        }else {
            suppliesOutgoInfo.setStorageDatetime(suppliesOutgoInfo.getStorageDatetime());
        }

        if (suppliesOutgoInfo.getStorageDatetimeStart() == null) {
            suppliesOutgoInfo.setStorageDatetimeStart(null);
        }else {
            suppliesOutgoInfo.setStorageDatetimeStart(suppliesOutgoInfo.getStorageDatetimeStart());
        }

        if (suppliesOutgoInfo.getStorageDatetimeEnd() == null) {
            suppliesOutgoInfo.setStorageDatetimeEnd(null);
        }else {
            suppliesOutgoInfo.setStorageDatetimeEnd(suppliesOutgoInfo.getStorageDatetimeEnd());
        }

        return suppliesOutgoInfo;
    }
}
