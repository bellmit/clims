package com.bazl.clims.controller.center;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bazl.clims.model.LoaUserInfo;
import com.bazl.clims.model.po.AmPersonalInfo;
import com.bazl.clims.service.AmPersonalInfoService;
import com.bazl.clims.service.LoaRoleRelationService;
import com.bazl.clims.service.LoaUserInfoService;
import org.apache.logging.log4j.util.Strings;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Created by Administrator on 2018/12/21.
 */
@Controller
@RequestMapping("/AmPersonalInfoController")
public class AmPersonalInfoController {
    @Autowired
    private AmPersonalInfoService amPersonalInfoService;
    @Autowired
    private LoaUserInfoService loaUserInfoService;
    @Autowired
    private LoaRoleRelationService loaRoleRelationService;

    /**
     * 委托人管理，委托人删除
     * @param amPersonalInfo
     * @return
     */
    @RequestMapping("deleteAmPersonalInfo")
    public String deleteAmPersonalInfo(AmPersonalInfo amPersonalInfo){
        if (!Objects.isNull(amPersonalInfo)){
            //根据PersonalId查询LOA_USER_INFO用户信息表获取到UserID对用户权限关系表LOA_ROLE_RELATION表中的信息进行删除
            List<LoaUserInfo> list =loaUserInfoService.queryloaUserInfoByPersonalId(amPersonalInfo.getPersonalId());
            loaRoleRelationService.deleteloaRoleRelationByUserId(list.get(0).getUserId());
            //删除LOA_USER_INFO用户信息表信息
            loaUserInfoService.deleteloaUserInfoByUserId(list.get(0).getUserId());
            //删除LOA_ROLE_RELATION人员信息表信息
            amPersonalInfoService.deleteAmPersonalInfo(amPersonalInfo);
        }
        return "redirect:/manage/accepterManage";
    };


}
