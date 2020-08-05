package com.bazl.clims.utils;

import com.bazl.clims.common.Constants;
import com.bazl.clims.controller.BaseController;
import com.bazl.clims.model.DictItem;
import com.bazl.clims.model.LoaPermission;
import com.bazl.clims.model.LoaRole;
import com.bazl.clims.model.LoaUserInfo;
import com.bazl.clims.model.po.ProgramRecord;
import com.bazl.clims.model.po.SampleInfo;
import com.bazl.clims.model.vo.LimsCaseInfoVo;
import com.bazl.clims.model.vo.SampleInfoVo;
import com.bazl.clims.service.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.servlet.ShiroHttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author chenzeliang
 * @date 2020/03/31.
 */
@Component
public class InitializationData extends BaseController {
    @Autowired
    DictItemService dictItemService;
    @Autowired
    LoaRoleService loaRoleService;
    @Autowired
    LoaPermissionService loaPermissionService;
    @Autowired
    ProgramRecordService programRecordService;
    @Autowired
    SampleInfoService sampleInfoService;
    /**
     * 初始化数据
     * @param query
     * @return
     */
    public static LimsCaseInfoVo resetCaseInfoQuery(LimsCaseInfoVo query) {
        if (StringUtils.isBlank(query.getEntity().getCaseStatus())) {
            query.getEntity().setCaseStatus(null);
        } else {
            query.getEntity().setCaseStatus(query.getEntity().getCaseStatus());
        }

        if (StringUtils.isBlank(query.getEntity().getCaseName())) {
            query.getEntity().setCaseName(null);
        } else {
            query.getEntity().setCaseName(query.getEntity().getCaseName().trim());
        }

        if (StringUtils.isBlank(query.getDelegateOrgCode())) {
            query.setDelegateOrgCode(null);
        } else {
            query.setDelegateOrgCode(query.getDelegateOrgCode().trim());
        }

        if (StringUtils.isBlank(query.getEntity().getCaseNo())) {
            query.getEntity().setCaseNo(null);
        } else {
            query.getEntity().setCaseNo(query.getEntity().getCaseNo().trim());
        }

        if (StringUtils.isBlank(query.getEntity().getCaseXkNo())) {
            query.getEntity().setCaseXkNo(null);
        } else {
            query.getEntity().setCaseXkNo(query.getEntity().getCaseXkNo().trim());
        }

        if (StringUtils.isBlank(query.getEntity().getCaseProperty())) {
            query.getEntity().setCaseProperty(null);
        } else {
            query.getEntity().setCaseProperty(query.getEntity().getCaseProperty().trim());
        }

        if (StringUtils.isBlank(query.getEntity().getCaseType())) {
            query.getEntity().setCaseType(null);
        } else {
            query.getEntity().setCaseType(query.getEntity().getCaseType().trim());
        }

        if (StringUtils.isBlank(query.getDelegator1Name())) {
            query.setDelegator1Name(null);
        } else {
            query.setDelegator1Name(query.getDelegator1Name().trim());
        }

        if (null == query.getDelegateStartDatetime()) {
            query.setDelegateStartDatetime(null);
        } else {
            query.setDelegateStartDatetime(query.getDelegateStartDatetime());
        }

        if (null == query.getDelegateEndDatetime()) {
            query.setDelegateEndDatetime(null);
        } else {
            query.setDelegateEndDatetime(query.getDelegateEndDatetime());
        }

        if (null == query.getAcceptStartDatetime()) {
            query.setAcceptStartDatetime(null);
        }else {
            query.setAcceptStartDatetime(query.getAcceptStartDatetime());
        }

        if (null == query.getAcceptEndDatetime()) {
            query.setAcceptEndDatetime(null);
        }else {
            query.setAcceptEndDatetime(query.getAcceptEndDatetime());
        }

        if (StringUtils.isBlank(query.getStatus())) {
            query.setStatus(null);
        }else{
            query.setStatus(query.getStatus());
        }

        if (StringUtils.isBlank(query.getPersonName())) {
            query.setPersonName(null);
        } else {
            query.setPersonName(query.getPersonName().trim());
        }

        if (StringUtils.isBlank(query.getPersonIdCard())) {
            query.setPersonIdCard(null);
        } else {
            query.setPersonIdCard(query.getPersonIdCard().trim());
        }

        return query;
    }
    /**
     * 初始化字典项
     * @return
     */
    public ModelAndView initData(String type) {
        ModelAndView modelAndView = new ModelAndView();

        //字典
        if (type.equals(Constants.DICR_TYPE)) {
            //默认洗脱体积
            List<DictItem> elutionDefaultList = dictItemService.selectListByDictTypeCode(Constants.DICT_TPYE_ELUTION_DEFAULT);
            modelAndView.addObject("elutionDefaultList", elutionDefaultList);
            //设备信息
            List<ProgramRecord> programRecordList = programRecordService.selectAll();
            modelAndView.addObject("programRecordList", programRecordList);
            //检材性质
            List<DictItem> samplePropertyList = dictItemService.selectListByDictTypeCode(Constants.DICT_TPYE_SAMPLE_PROPERTY);
            modelAndView.addObject("samplePropertyList", samplePropertyList);
            //设备类型
            List<DictItem> deviceTypeList = dictItemService.selectListByDictTypeCode(Constants.DICT_TPYE_DEVICE_TYPE);
            modelAndView.addObject("deviceTypeList", deviceTypeList);
            //确证方法
            List<DictItem> confirmatoryMethodList = dictItemService.selectListByDictTypeCode(Constants.DICT_TPYE_CONFIRMATORY_METHOD);
            modelAndView.addObject("confirmatoryMethodList", confirmatoryMethodList);
            //预实验方法
            List<DictItem> preExperimentalMethodList = dictItemService.selectListByDictTypeCode(Constants.DICT_TPYE_PRE_EXPERIMENTAL_METHOD);
            modelAndView.addObject("preExperimentalMethodList", preExperimentalMethodList);
            //检材转移方法
            List<DictItem> sampleTransferMethodList = dictItemService.selectListByDictTypeCode(Constants.DICT_TPYE_SAMPLE_TRANSFER_METHOD);
            modelAndView.addObject("sampleTransferMethodList", sampleTransferMethodList);
            //检材转移方法
            List<DictItem> programNameList = dictItemService.selectListByDictTypeCode(Constants.DICT_TPYE_PROGRAM_NAME);
            modelAndView.addObject("programNameList", programNameList);
        }
        //菜单


        Subject subject = SecurityUtils.getSubject();
        LoaUserInfo loaUserInfo = (LoaUserInfo) subject.getPrincipal();
        String loginName = loaUserInfo.getLoginName();
        List<LoaRole> userRoleList = loaRoleService.listRolesByPersonalId(loaUserInfo.getPersonalId());
        List<LoaPermission> labPermissionList = new ArrayList<>();
        Set<String> permissionIds = new HashSet<>();
        List<LoaPermission> rolePermissions = null;
        for (LoaRole role : userRoleList) {
            rolePermissions = loaPermissionService.selectBy(role.getRoleId());
            for (LoaPermission lp : rolePermissions) {
                if (!permissionIds.contains(lp.getPermissionId())) {
                    permissionIds.add(lp.getPermissionId());
                    labPermissionList.add(lp);
                }
            }
        }
        /*Collections.sort(labPermissionList);*/

        List<LoaPermission> rootPermissionList = new ArrayList<LoaPermission>();
        List<LoaPermission> childPermissionList = new ArrayList<LoaPermission>();
        for (LoaPermission lp : labPermissionList) {
            if (lp.getRootFlag().equals(Constants.FLAG_TRUE)) {
                rootPermissionList.add(lp);
            } else {
                childPermissionList.add(lp);
            }
        }
        modelAndView.addObject("loginName", loginName);
        modelAndView.addObject("rootPermissionList", rootPermissionList);
        modelAndView.addObject("childPermissionList", childPermissionList);
        modelAndView.addObject("loginTitleName", SystemUtil.getSystemConfig().getProperty("loginTitleName"));

        return modelAndView;
    }
        /**
         * 初始化字典项
         * @return
         */
    public static ModelAndView initDictList() {
        ModelAndView modelAndView = new ModelAndView();

        DictItem dictItem = new DictItem();
        //案件类型
        dictItem.setDictTypeCode(Constants.CASE_TYPE);
        List<DictItem> caseTypeList = DictUtil.getDictList(dictItem);

        //案件性质
        dictItem.setDictTypeCode(Constants.CASE_PROPERTY);
        List<DictItem> casePropertyList = DictUtil.getDictList(dictItem);

        //案件级别
        dictItem.setDictTypeCode(Constants.CASE_LEVEL);
        List<DictItem> caseLevelList = DictUtil.getDictList(dictItem);

        //人员类型
        dictItem.setDictTypeCode(Constants.PERSON_TYPE);
        List<DictItem> memberTypeList = DictUtil.getDictList(dictItem);

        //性别
        dictItem.setDictTypeCode(Constants.GENDER);
        List<DictItem> genderList = DictUtil.getDictList(dictItem);

        //检材类型
        dictItem.setDictTypeCode(Constants.SAMPLE_TYPE);
        List<DictItem> sampleTypeList = DictUtil.getDictList(dictItem);

        //包装方法PACK_METHOD
        dictItem.setDictTypeCode(Constants.PACK_METHOD);
        List<DictItem> packMethodList = DictUtil.getDictList(dictItem);

        //案件状态CASE_STATUS
        dictItem.setDictTypeCode(Constants.CASE_STATUS);
        List<DictItem> caseStatusList = DictUtil.getDictList(dictItem);

        //委托人添加委托人职务
        dictItem.setDictTypeCode(Constants.POSITION_LIST);
        List<DictItem> positionList = DictUtil.getDictList(dictItem);

        //载体
        dictItem.setDictTypeCode(Constants.SAMPLE_CARRIER);
        List<DictItem> sampleCarrierList = DictUtil.getDictList(dictItem);

        //提取方法
        dictItem.setDictTypeCode(Constants.EXTRACT_METHOD);
        List<DictItem> extractMethodList = DictUtil.getDictList(dictItem);

        //提取实验方法
        dictItem.setDictTypeCode(Constants.EXTRACTION_TEST_METHOD);
        List<DictItem> extractTestMethodList = DictUtil.getDictList(dictItem);

        //材料提取使用方法 离
        dictItem.setDictTypeCode(Constants.USE_INSTRUMENT_LEAVE);
        List<DictItem> leaveTestMethodList = DictUtil.getDictList(dictItem);
        //材料提取使用方法 浴
        dictItem.setDictTypeCode(Constants.USE_INSTRUMENT_BATH);
        List<DictItem> bathTestMethodList = DictUtil.getDictList(dictItem);
        //材料提取使用方法 干
        dictItem.setDictTypeCode(Constants.USE_INSTRUMENT_DRY);
        List<DictItem> dryTestMethodList = DictUtil.getDictList(dictItem);
        //材料提取使用方法 摇
        dictItem.setDictTypeCode(Constants.USE_INSTRUMENT_SHAKE);
        List<DictItem> shakeTestMethodList = DictUtil.getDictList(dictItem);
        //材料提取使用方法 振
        dictItem.setDictTypeCode(Constants.USE_INSTRUMENT_EARTHQUAKE);
        List<DictItem> earthquakeTestMethodList = DictUtil.getDictList(dictItem);
        //纯化方法
        dictItem.setDictTypeCode(Constants.PURIFICATION_METHOD);
        List<DictItem> purificationMethodList = DictUtil.getDictList(dictItem);
        //默认洗脱体积
        dictItem.setDictTypeCode(Constants.DICT_TPYE_ELUTION_DEFAULT);
        List<DictItem> elutionDefaultList = DictUtil.getDictList(dictItem);
        modelAndView.addObject("elutionDefaultList", elutionDefaultList);
        modelAndView.addObject("caseTypeList", caseTypeList);
        modelAndView.addObject("casePropertyList", casePropertyList);
        modelAndView.addObject("caseLevelList", caseLevelList);
        modelAndView.addObject("memberTypeList", memberTypeList);
        modelAndView.addObject("sampleTypeList", sampleTypeList);
        modelAndView.addObject("genderList", genderList);
        modelAndView.addObject("packMethodList", packMethodList);
        modelAndView.addObject("caseStatusList", caseStatusList);
        modelAndView.addObject("positionList", positionList);
        modelAndView.addObject("sampleCarrierList",sampleCarrierList);
        modelAndView.addObject("extractMethodList",extractMethodList);
        modelAndView.addObject("extractTestMethodList",extractTestMethodList);
        modelAndView.addObject("leaveTestMethodList",leaveTestMethodList);
        modelAndView.addObject("bathTestMethodList",bathTestMethodList);
        modelAndView.addObject("dryTestMethodList",dryTestMethodList);
        modelAndView.addObject("shakeTestMethodList",shakeTestMethodList);
        modelAndView.addObject("earthquakeTestMethodList",earthquakeTestMethodList);
        modelAndView.addObject("purificationMethodList",purificationMethodList);

        return modelAndView;
    }
    public void updateSampleInfo(List<SampleInfoVo> sampleInfoVoList, String stage){
        if (ListUtils.isNotNullAndEmptyList(sampleInfoVoList)) {
            for (int i = 0;i < sampleInfoVoList.size();i++) {
                SampleInfoVo sampleInfoVo = sampleInfoVoList.get(i);
                if (sampleInfoVo != null) {
                    if (StringUtils.isNotBlank(sampleInfoVo.getEntity().getId())) {
                        SampleInfo sampleInfo = sampleInfoService.selectByPrimaryKey(sampleInfoVo.getEntity().getId());

                        try {
                            if (Constants.STAGE_TQ.equals(stage)) {
                                sampleInfo.setExtractPlateLocation(sampleInfoVo.getEntity().getExtractPlateLocation());
                                sampleInfo.setExtractLocationSort(sampleInfoVo.getEntity().getExtractLocationSort());
                                sampleInfo.setExtractPlateSort(sampleInfoVo.getEntity().getExtractPlateSort());
                            }else if (Constants.STAGE_KZ.equals(stage)) {
                                sampleInfo.setPcrPlateLocation(sampleInfoVo.getEntity().getPcrPlateLocation());
                                sampleInfo.setPcrLocationSort(sampleInfoVo.getEntity().getPcrLocationSort());
                                sampleInfo.setPcrPlateSort(sampleInfoVo.getEntity().getPcrPlateSort());
                            }else if (Constants.STAGE_SY.equals(stage)) {
                                sampleInfo.setSyPlateLocation(sampleInfoVo.getEntity().getSyPlateLocation());
                                sampleInfo.setSyLocationSort(sampleInfoVo.getEntity().getSyLocationSort());
                                sampleInfo.setSyPlateSort(sampleInfoVo.getEntity().getSyPlateSort());
                            }
                            sampleInfoService.updateByPrimaryKey(sampleInfo);
                        }catch (Exception e) {
                            logger.error("updateSampleInfo error",e);
                            e.getStackTrace();
                        }
                    }
                }
            }
        }
    }
}
