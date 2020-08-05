package com.bazl.clims.controller.center;

import com.bazl.clims.common.Constants;
import com.bazl.clims.controller.BaseController;
import com.bazl.clims.model.DictItem;
import com.bazl.clims.model.LoaUserInfo;
import com.bazl.clims.model.PageInfo;
import com.bazl.clims.model.po.OrgInfo;
import com.bazl.clims.model.po.ReagentInfo;
import com.bazl.clims.model.po.ReagentOutgoInfo;
import com.bazl.clims.service.OrgInfoService;
import com.bazl.clims.service.ReagentInfoService;
import com.bazl.clims.service.ReagentOutgoInfoService;
import com.bazl.clims.utils.DateUtils;
import com.bazl.clims.utils.DictUtil;
import com.bazl.clims.utils.ListUtils;
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
 * @author hj
 * @date 2019/4/1
 */
@Controller
@RequestMapping("/center")
public class ReagentOutgoInfoController extends BaseController{

    @Autowired
    private ReagentOutgoInfoService reagentOutgoInfoService;
    @Autowired
    private ReagentInfoService reagentInfoService;
    @Autowired
    private OrgInfoService orgInfoService;

    /**
     * 出库管理页面
     * @param request
     * @param pageInfo
     * @return
     */
    @RequestMapping("/reagentOutStock")
    public ModelAndView reagentNameList(HttpServletRequest request, ReagentOutgoInfo reagentOutgoInfo, PageInfo pageInfo){
        ModelAndView view = new ModelAndView();
        view.setViewName("sysManagement/reagentOutStock");

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if(operateUser == null){
            view.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return  view;
        }
        String userOrgId = operateUser.getOrgId();
        //将当前用户org_id设置进query
        if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
            userOrgId = "110230000000";
        }

        //获取所属单位名称
        List<OrgInfo> orgInfo = orgInfoService.selectAll();
        view.addObject("orgInfo", orgInfo);

        //获取数据
        reagentOutgoInfo.setOrgId(userOrgId);
        reagentOutgoInfo = resetStorageRecordInfo(reagentOutgoInfo);
        List<ReagentOutgoInfo> reagentOutgoInfoList = reagentOutgoInfoService.selectOutList(reagentOutgoInfo,pageInfo);
        view.addObject("reagentOutgoInfoList",reagentOutgoInfoList);

        reagentOutgoInfo.setRecordType("1");
        //显示条数
        int totalCnt = reagentOutgoInfoService.selectCount(reagentOutgoInfo);
        view.addObject("pageInfo", pageInfo.updatePageInfo(totalCnt));

        //获取试剂名称
        ReagentInfo reagentInfo = new ReagentInfo();
        reagentInfo.setOrgId(userOrgId);
        List<ReagentInfo> nameList = reagentInfoService.selectList(reagentInfo);
        view.addObject("nameList",nameList);
        //获取入库列表信息  显示条码号
//        List<ReagentOutgoInfo> reagentInfoList = reagentOutgoInfoService.selectByRecordType("0",operateUser.getOrgId());
//        view.addObject("reagentInfoList",reagentInfoList);

        view.addObject("query", reagentOutgoInfo);

        return view;
    }

    /**
     * 添加修改出库
     * @param request
     * @param pageInfo
     * @return
     */
    @RequestMapping("/addReagentOutStock")
    @ResponseBody
    public Map<String, Object> addReagentOutStock(HttpServletRequest request, PageInfo pageInfo, @RequestBody ReagentOutgoInfo reagentOutgoInfo){
        Map<String, Object> result = new HashMap<String, Object>();

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        String userOrgId = operateUser.getOrgId();
        //将当前用户org_id设置进query
        if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
            userOrgId = "110230000000";
        }

        if (reagentOutgoInfo.getId() != null){
            //修改数据
            ReagentOutgoInfo reagentOutgo = reagentOutgoInfoService.selectByPrimaryKey(reagentOutgoInfo.getId());
            reagentOutgo.setBarcode(reagentOutgoInfo.getBarcode());
            reagentOutgo.setReagentName(reagentOutgoInfo.getReagentName());
            reagentOutgo.setStorageNum(reagentOutgoInfo.getStorageNum());
            reagentOutgo.setStoragePerson(reagentOutgoInfo.getStoragePerson());
            reagentOutgo.setEffectiveDatetime(reagentOutgoInfo.getEffectiveDatetime());
            reagentOutgo.setStorageRemark(reagentOutgoInfo.getStorageRemark());
            reagentOutgo.setStorageDatetime(reagentOutgoInfo.getStorageDatetime());
            try {
                reagentOutgoInfoService.updateByPrimaryKey(reagentOutgo);
            }catch (Exception e) {
                logger.info("---修改出库试剂失败！---");
                logger.error("修改出库试剂失败！", e);
                result.put("success", false);
                throw e;
            }
            result.put("success", true);
        }else {
            //添加数据
            reagentOutgoInfo.setId(UUID.randomUUID().toString());
            ReagentInfo reagentInfo = reagentInfoService.selectByReagentName(reagentOutgoInfo.getReagentName());
            if (reagentInfo != null){
                reagentOutgoInfo.setReagentId(reagentInfo.getId());
            }
            reagentOutgoInfo.setRecordType("1");
            if (operateUser != null){
                reagentOutgoInfo.setOrgId(userOrgId);
            }

            try {
//                reagentInfoService.updateByPrimaryKey(reagentInfo);
                reagentOutgoInfoService.insert(reagentOutgoInfo);
                result.put("success", true);
            }catch (Exception e) {
                logger.info("---添加出库试剂失败！---");
                logger.error("添加出库试剂失败！", e);
                result.put("success", false);
                throw e;
            }
            //减库存
            /*int count = reagentInfo.getReagentCount() - Integer.parseInt(reagentOutgoInfo.getStorageNum());
            if (count>=0){
                reagentInfo.setReagentCount((short) count);
                try {
                    reagentInfoService.updateByPrimaryKey(reagentInfo);
                    reagentOutgoInfoService.insert(reagentOutgoInfo);
                    result.put("success", true);
                }catch (Exception e) {
                    logger.info("---添加出库试剂失败！---");
                    logger.error("添加出库试剂失败！", e);
                    result.put("success", false);
                    throw e;
                }
            }else {
                logger.info("---添加出库试剂失败！---");
                result.put("success", false);
            }*/

        }
        return  result;
    }

    /**
     * 入库管理页面
     * 查看list
     * @param request
     * @return
     */
    @RequestMapping("/reagentInStock")
    public ModelAndView querytIn(HttpServletRequest request, ReagentOutgoInfo reagentOutgoInfo ,PageInfo pageInfo){
        ModelAndView view = new ModelAndView();
        view.setViewName("sysManagement/reagentInStock");

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if(operateUser == null){
            view.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return  view;
        }
        //获取当前用户的id
        String userOrgId = operateUser.getOrgId();
        //将当前用户org_id设置进query
        if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
            userOrgId = "110230000000";
        }

        //获取所属单位名称
        List<OrgInfo> orgInfos = orgInfoService.selectAll();
        view.addObject("orgInfo", orgInfos);

        //获取数据
        reagentOutgoInfo.setOrgId(userOrgId);
        reagentOutgoInfo = resetStorageRecordInfo(reagentOutgoInfo);
        List<ReagentOutgoInfo> reagentOutgoInfoList = reagentOutgoInfoService.selectInList(reagentOutgoInfo,pageInfo);
        view.addObject("reagentOutgoInfoList",reagentOutgoInfoList);

        //显示条数
        reagentOutgoInfo.setRecordType("0");
        int totalCnt = reagentOutgoInfoService.selectCount(reagentOutgoInfo);
        view.addObject("pageInfo", pageInfo.updatePageInfo(totalCnt));

        //获取试剂名称
        ReagentInfo reagentInfo = new ReagentInfo();
        reagentInfo.setOrgId(userOrgId);
        List<ReagentInfo> nameList = reagentInfoService.selectList(reagentInfo);
        view.addObject("nameList",nameList);

        //获取实验阶段
        DictItem dictItem = new DictItem();
        dictItem.setDictTypeCode(Constants.EXPERIMENTAL_STAGE);
        List<DictItem> TypeList = DictUtil.getDictList(dictItem);
        view.addObject("sampleTypeList",TypeList);
        view.addObject("query", reagentOutgoInfo);
        return view;
    }

    /**
     * 添加入库
     * @param request
     * @param pageInfo
     * @return
     */
    @RequestMapping("/addReagentInStock")
    @ResponseBody
    public Map<String, Object> addReagentInStock(HttpServletRequest request, PageInfo pageInfo, @RequestBody ReagentOutgoInfo reagentOutgoInfo){
        Map<String, Object> result = new HashMap<String, Object>();

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        //获取当前用户的id
        String userOrgId = operateUser.getOrgId();
        //将当前用户org_id设置进query
        if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
            userOrgId = "110230000000";
        }

        if (reagentOutgoInfo.getId() != null){
            //修改数据
            ReagentOutgoInfo reagentOutgo = reagentOutgoInfoService.selectByPrimaryKey(reagentOutgoInfo.getId());
            reagentOutgo.setBarcode(reagentOutgoInfo.getBarcode());
            reagentOutgo.setReagentName(reagentOutgoInfo.getReagentName());
            reagentOutgo.setStorageNum(reagentOutgoInfo.getStorageNum());
            reagentOutgo.setStoragePerson(reagentOutgoInfo.getStoragePerson());
            reagentOutgo.setEffectiveDatetime(reagentOutgoInfo.getEffectiveDatetime());
            reagentOutgo.setStorageRemark(reagentOutgoInfo.getStorageRemark());
            reagentOutgo.setStorageDatetime(reagentOutgoInfo.getStorageDatetime());
            reagentOutgo.setBatchNumber(reagentOutgoInfo.getBatchNumber());
            try {
                reagentOutgoInfoService.updateByPrimaryKey(reagentOutgo);
            }catch (Exception e) {
                logger.info("---修改入库试剂失败！---");
                logger.error("修改入库试剂失败！", e);
                result.put("success", false);
                throw e;
            }

            try {
                //修改最新的试剂盒信息
                ReagentInfo reagentInfo = new ReagentInfo();
                //有效期
                reagentInfo.setValidityTime(reagentOutgo.getEffectiveDatetime());
                //批号
                reagentInfo.setBatchNumber(reagentOutgo.getBatchNumber());
                //库存
                reagentInfo.setReagentCount(Short.valueOf(reagentOutgo.getStorageNum()));
                reagentInfo.setId(reagentOutgo.getReagentId());

                reagentInfoService.updateByReagentInfo(reagentInfo);
            } catch (Exception e) {
                logger.info("---修改最新试剂盒失败！---");
                logger.error("修改最新试剂盒失败！", e);
                result.put("success", false);
                throw e;
            }
        } else {
            //添加数据
            reagentOutgoInfo.setId(UUID.randomUUID().toString());
            ReagentInfo reagentInfo = reagentInfoService.selectByReagentName(reagentOutgoInfo.getReagentName());
            if (reagentInfo != null){
                reagentOutgoInfo.setReagentId(reagentInfo.getId());
            }
            reagentOutgoInfo.setRecordType("0");
            if (operateUser !=null){
                reagentOutgoInfo.setOrgId(userOrgId);
            }
            //试剂库存
//            reagentInfo.setReagentCount((short) Integer.parseInt(reagentOutgoInfo.getStorageNum()));
            try {
                reagentOutgoInfoService.insert(reagentOutgoInfo);
//                reagentInfoService.updateByPrimaryKey(reagentInfo);
            }catch (Exception e) {
                logger.info("---添加入库试剂失败！---");
                logger.error("添加入库试剂失败！", e);
                result.put("success", false);
                throw e;
            }

            try {
                //有效期
                reagentInfo.setValidityTime(reagentOutgoInfo.getEffectiveDatetime());
                //批号
                reagentInfo.setBatchNumber(reagentOutgoInfo.getBatchNumber());
                //库存
                reagentInfo.setReagentCount(Short.valueOf(reagentOutgoInfo.getStorageNum()));
                reagentInfo.setId(reagentOutgoInfo.getReagentId());

                reagentInfoService.updateByReagentInfo(reagentInfo);
            } catch (Exception e) {
                logger.info("---修改最新试剂盒失败！---");
                logger.error("修改最新试剂盒失败！", e);
                result.put("success", false);
                throw e;
            }
        }
        result.put("success", true);
        return  result;
    }

    /**
     * 出入库管理页面
     * @param request
     * @param pageInfo
     * @return
     */
    @RequestMapping("/reagentOutInStock")
    public ModelAndView reagentOutInStock(HttpServletRequest request, ReagentOutgoInfo reagentOutgoInfo, PageInfo pageInfo){
        ModelAndView view = new ModelAndView();
        view.setViewName("sysManagement/queryOutInStock");

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if(operateUser == null){
            view.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return  view;
        }
        //获取当前用户的id
        String userOrgId = operateUser.getOrgId();
        //将当前用户org_id设置进query
        if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
            userOrgId = "110230000000";
        }

        //获取数据
        reagentOutgoInfo.setOrgId(userOrgId);
        reagentOutgoInfo = resetStorageRecordInfo(reagentOutgoInfo);
        List<ReagentOutgoInfo> reagentOutgoInfoList = reagentOutgoInfoService.selectOutInList(reagentOutgoInfo,pageInfo);
        view.addObject("OutgoInfoList",reagentOutgoInfoList);

        //显示条数
        int totalCnt = reagentOutgoInfoService.selectCount(reagentOutgoInfo);
        view.addObject("pageInfo", pageInfo.updatePageInfo(totalCnt));
        view.addObject("query", reagentOutgoInfo);

        return view;
    }


    /**
     * 库存管理页面
     * @param request
     * @param pageInfo
     * @return
     */
    @RequestMapping("/reagentInventory")
    public ModelAndView reagentInventory(HttpServletRequest request,ReagentInfo reagentInfo, PageInfo pageInfo) {
        ModelAndView view = new ModelAndView();
        view.setViewName("sysManagement/reagentInventory");

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if(operateUser == null){
            view.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return  view;
        }
        String userOrgId = operateUser.getOrgId();
        //将当前用户org_id设置进query
        if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
            userOrgId = "110230000000";
        }

        //获取列表信息
        ReagentOutgoInfo reagentOutgoInfo = new ReagentOutgoInfo();
        reagentOutgoInfo.setOrgId(userOrgId);
        List<ReagentOutgoInfo> outgoInfoList = reagentOutgoInfoService.selectByStorageNum(reagentOutgoInfo);
        view.addObject("reagentInfoList", outgoInfoList);

        //获取实验阶段
        DictItem dictItem = new DictItem();
        dictItem.setDictTypeCode(Constants.EXPERIMENTAL_STAGE);
        List<DictItem> TypeList = DictUtil.getDictList(dictItem);
        view.addObject("sampleTypeList",TypeList);
        return view;
    }

    /**
     * 库存管理页面  查询
     * 查看list
     * @param request
     * @return
     */
    @RequestMapping("/querytInventoryName")
    public ModelAndView querytInventoryName(HttpServletRequest request, ReagentOutgoInfo reagentOutgoInfo,PageInfo pageInfo){
        ModelAndView view = new ModelAndView();
        view.setViewName("sysManagement/reagentInventory");

        //获取当前登录人信息
        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
        if(operateUser == null){
            view.addObject("date", "redirect:/login.html?timeoutFlag=1");
            return  view;
        }
        String userOrgId = operateUser.getOrgId();
        //将当前用户org_id设置进query
        if (StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")) {
            userOrgId = "110230000000";
        }
        //获取数据
        reagentOutgoInfo.setOrgId(userOrgId);
        reagentOutgoInfo = resetReagentSuppliesInfo(reagentOutgoInfo);
        List<ReagentOutgoInfo> outgoInfoList = reagentOutgoInfoService.selectByStorageNum(reagentOutgoInfo);
        view.addObject("reagentInfoList", outgoInfoList);
        //获取实验阶段
        DictItem dictItem = new DictItem();
        dictItem.setDictTypeCode(Constants.EXPERIMENTAL_STAGE);
        List<DictItem> TypeList = DictUtil.getDictList(dictItem);
        view.addObject("sampleTypeList",TypeList);
        view.addObject("query", reagentOutgoInfo);
        return view;
    }

    public ReagentOutgoInfo resetReagentSuppliesInfo(ReagentOutgoInfo reagentOutgoInfo){

        if (StringUtils.isBlank(reagentOutgoInfo.getOrgId())) {
            reagentOutgoInfo.setOrgId(null);
        } else {
            reagentOutgoInfo.setOrgId(reagentOutgoInfo.getOrgId().trim());
        }


        if (StringUtils.isBlank(reagentOutgoInfo.getReagentName())) {
            reagentOutgoInfo.setReagentName(null);
        } else {
            reagentOutgoInfo.setReagentName(reagentOutgoInfo.getReagentName().trim());
        }

        if (StringUtils.isBlank(reagentOutgoInfo.getReagentNo())) {
            reagentOutgoInfo.setReagentNo(null);
        }else {
            reagentOutgoInfo.setReagentNo(reagentOutgoInfo.getReagentNo().trim());
        }

        if (StringUtils.isBlank(reagentOutgoInfo.getReagentBrand())) {
        } else {
            reagentOutgoInfo.setReagentBrand(reagentOutgoInfo.getReagentBrand().trim());
        }

        if (StringUtils.isBlank(reagentOutgoInfo.getReagentModel())) {
            reagentOutgoInfo.setReagentModel(null);
        } else {
            reagentOutgoInfo.setReagentModel(reagentOutgoInfo.getReagentModel().trim());
        }

        if (StringUtils.isBlank(reagentOutgoInfo.getReagentPrice())) {
            reagentOutgoInfo.setReagentPrice(null);
        }else {
            reagentOutgoInfo.setReagentPrice(reagentOutgoInfo.getReagentPrice().trim());
        }

        if (StringUtils.isBlank(reagentOutgoInfo.getReagentFirm())) {
            reagentOutgoInfo.setReagentFirm(null);
        }else {
            reagentOutgoInfo.setReagentFirm(reagentOutgoInfo.getReagentFirm().trim());
        }

        if (StringUtils.isBlank(reagentOutgoInfo.getExperimentalStage())) {
            reagentOutgoInfo.setExperimentalStage(null);
        }else {
            reagentOutgoInfo.setExperimentalStage(reagentOutgoInfo.getExperimentalStage());
        }

        return reagentOutgoInfo;
    }

    public ReagentOutgoInfo resetStorageRecordInfo(ReagentOutgoInfo reagentOutgoInfo){

        if (StringUtils.isBlank(reagentOutgoInfo.getOrgId())) {
            reagentOutgoInfo.setOrgId(null);
        } else {
            reagentOutgoInfo.setOrgId(reagentOutgoInfo.getOrgId().trim());
        }

        if (StringUtils.isBlank(reagentOutgoInfo.getBarcode())) {
            reagentOutgoInfo.setBarcode(null);
        }else {
            reagentOutgoInfo.setBarcode(reagentOutgoInfo.getBarcode().trim());
        }

        if(StringUtils.isBlank(reagentOutgoInfo.getReagentName())){
            reagentOutgoInfo.setReagentName(null);
        }else {
            reagentOutgoInfo.setReagentName(reagentOutgoInfo.getReagentName().trim());
        }

        if (StringUtils.isBlank(reagentOutgoInfo.getStoragePerson())) {
            reagentOutgoInfo.setStoragePerson(null);
        }else {
            reagentOutgoInfo.setStoragePerson(reagentOutgoInfo.getStoragePerson().trim());
        }

        if (StringUtils.isBlank((CharSequence) reagentOutgoInfo.getEffectiveDatetime())) {
            reagentOutgoInfo.setEffectiveDatetime(null);
        }else {
            reagentOutgoInfo.setEffectiveDatetime(reagentOutgoInfo.getEffectiveDatetime());
        }

        if (reagentOutgoInfo.getStorageDatetime() == null) {
            reagentOutgoInfo.setStorageDatetime(null);
        }else {
            reagentOutgoInfo.setStorageDatetime(reagentOutgoInfo.getStorageDatetime());
        }

        if (StringUtils.isBlank(reagentOutgoInfo.getRecordType())) {
            reagentOutgoInfo.setRecordType(null);
        }else {
            reagentOutgoInfo.setRecordType(reagentOutgoInfo.getRecordType());
        }

        if (StringUtils.isBlank(reagentOutgoInfo.getStorageRemark())) {
            reagentOutgoInfo.setStorageRemark(null);
        }else {
            reagentOutgoInfo.setStorageRemark(reagentOutgoInfo.getStorageRemark().trim());
        }

        if (reagentOutgoInfo.getStorageDatetimeStart() == null) {
            reagentOutgoInfo.setStorageDatetimeStart(null);
        }else {
            reagentOutgoInfo.setStorageDatetimeStart(reagentOutgoInfo.getStorageDatetimeStart());
        }

        if (reagentOutgoInfo.getStorageDatetimeEnd() == null) {
            reagentOutgoInfo.setStorageDatetimeEnd(null);
        }else {
            reagentOutgoInfo.setStorageDatetimeEnd(reagentOutgoInfo.getStorageDatetimeEnd());
        }

        return reagentOutgoInfo;
    }
}
