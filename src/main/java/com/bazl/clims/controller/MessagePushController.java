package com.bazl.clims.controller;

import com.bazl.clims.service.LimsCaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sun on 2018/12/20.
 */
@Controller
@RequestMapping("/message")
public class MessagePushController extends BaseController{

    @Autowired
    LimsCaseInfoService caseInfoService;

    /**
     * 根据年份查询统计数据
     * @param year
     * @return
     */
    @RequestMapping("/getCount")
    @ResponseBody
    public Map<String,Object> getCount(String year){
        Map<String,Object> map = new HashMap<>();
        try {
            //根据年份获取各个月份的案件数
            HashMap<String, String> monthMap = caseInfoService.selectMonthCountByYear(year);
            map.put("monthMap", monthMap);
            map.put("code", 0);
            map.put("message", "获取成功！");
        }catch(Exception ex){
            logger.info("获取失败"+ex);
            map.put("code", 1);
            map.put("message", "获取失败！");
        }
        return map;
    }

}
