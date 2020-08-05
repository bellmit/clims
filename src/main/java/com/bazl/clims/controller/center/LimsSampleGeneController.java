
package com.bazl.clims.controller.center;

import com.bazl.clims.compare.GeneSameCompareUtil;
import com.bazl.clims.controller.BaseController;
import com.bazl.clims.model.po.LimsSampleGene;
import com.bazl.clims.service.LimsSampleGeneService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Administrator on 2019/5/7.
 */
@Controller
@RequestMapping("LimsSampleGeneController")
public class LimsSampleGeneController extends BaseController {
    @Autowired
    LimsSampleGeneService limsSampleGeneService;
    @Autowired
    GeneSameCompareUtil  geneSameCompareUtil;

    @RequestMapping("queryLimsSampleGeneByCeneId")
    @ResponseBody
    public ModelAndView queryLimsSampleGeneByCeneId(String ceneId){
        ModelAndView modelAndView = new ModelAndView();
        try {
            if(Strings.isNotBlank(ceneId)){
            LimsSampleGene limsSampleGene =limsSampleGeneService.selectByPrimaryKey(ceneId);
                if(null != limsSampleGene){
                    if(Strings.isNotBlank(limsSampleGene.getGeneInfo())){
                        List<Map<String, Object>> resultList = geneSameCompareUtil.analysisGene(limsSampleGene);
                        modelAndView.addObject("result",resultList);
                    }

                    if(Strings.isNotBlank(limsSampleGene.getImagePath())) {
                        modelAndView.addObject("imagePath", limsSampleGene.getImagePath());
                    }
                }
            }
        }catch (Exception ex){
            logger.error("查看基因信息失败"+ex);
        }
        modelAndView.setViewName("caseCompare/CommonComparison");
        return modelAndView;
    }

}
