package com.bazl.clims.controller;

import com.bazl.clims.model.LoaUserInfo;
import com.bazl.clims.model.po.LimsConsignmentInfo;
import com.bazl.clims.service.LimsConsignmentInfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Created by Sun on 2018/12/20.
 */
@Controller
@RequestMapping("/delegate")
public class DelegateController extends BaseController {

    @Autowired
    private LimsConsignmentInfoService limsConsignmentInfoService;


    /**
     * 添加非案件委托信息
     * @param consignmentNo
     * @return
     */
    @RequestMapping(value="/testConsignmentNo", method = RequestMethod.POST, produces="application/json; charset=utf-8")
    @ResponseBody
    public Map<String,Object> testConsignmentNo(String consignmentNo) {
        Map<String,Object> map=new HashMap();
        String url="";
        try {

            //获取当前登录人信息
            Subject subject = SecurityUtils.getSubject();
            LoaUserInfo operateUser = (LoaUserInfo) subject.getPrincipal();
            if(operateUser == null){
                map.put("success",false);
                map.put(url,"/login.html?timeoutFlag=1");
                return  map;
            }

            LimsConsignmentInfo limsConsignmentInfo = limsConsignmentInfoService.selectByConsignmentNo(consignmentNo);
            if (limsConsignmentInfo != null){
                map.put("success",true);
            }else{
                map.put("success",false);
            }

        }catch(Exception ex){
            map.put("error",false);
            logger.error("判断委托编号重复失败"+ex.getMessage());
        }
        return map;
    }


}
