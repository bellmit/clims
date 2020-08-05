package com.bazl.clims.controller.center;


import com.bazl.clims.controller.BaseController;
import com.bazl.clims.utils.HttpRequestUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("gmIdxController")
public class GmIdxController extends BaseController {



    @RequestMapping("/getGmidxTokenInfo")
    public Map<String, Object> getGmidxTokenInfo(HttpServletRequest request,String templateName,String getSampleInfo){
        logger.info("templateName初始化"+templateName+"getSampleInfo初始化"+getSampleInfo);

        Map<String, Object> result = new HashMap<>();
        Map<String,String> hashMap = new HashMap<>();
        //获取token
        String url = "http://127.0.0.1:8083/gmidx/token";
        String sendRequest = HttpRequestUtil.sendRequest(url, null);
        if (sendRequest != null){
            logger.info("sendRequest"+sendRequest);
            String requestMethod = "post";
            if(StringUtils.isNotBlank(templateName) && StringUtils.isNotBlank(getSampleInfo)){
                hashMap.put("templateName",templateName);
                hashMap.put("getSampleInfo",getSampleInfo);
                hashMap.put("token",sendRequest);
            }
            String url2 = "http://127.0.0.1:8083/gmidx/query";
            String gmidxInfo = HttpRequestUtil.sendRequest(url, requestMethod,null,hashMap);

        }
        return result;
    }


}
