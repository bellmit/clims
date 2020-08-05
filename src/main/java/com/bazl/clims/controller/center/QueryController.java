package com.bazl.clims.controller.center;

import com.bazl.clims.common.Constants;
import com.bazl.clims.controller.BaseController;
import com.bazl.clims.model.LoaUserInfo;
import com.bazl.clims.model.PageInfo;
import com.bazl.clims.model.po.LimsCaseInfo;
import com.bazl.clims.model.po.OrgInfo;
import com.bazl.clims.model.vo.LimsCaseInfoVo;
import com.bazl.clims.service.LimsCaseInfoService;
import com.bazl.clims.service.LimsConsignmentInfoService;
import com.bazl.clims.service.OrgInfoService;
import com.bazl.clims.utils.InitializationData;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author wanghaiyang
 * @date 2019/2/28.
 */
@Controller
@RequestMapping("/center")
public class QueryController extends BaseController{

    @Autowired
    LimsCaseInfoService limsCaseInfoService;
    @Autowired
    OrgInfoService orgInfoService;
    @Autowired
    LimsConsignmentInfoService limsConsignmentInfoService;

    @RequestMapping("/query")
    public ModelAndView query(HttpServletRequest request, LimsCaseInfoVo query, PageInfo pageInfo){
        ModelAndView view = new ModelAndView();


        try {
            query = InitializationData.resetCaseInfoQuery(query);

            //查询委托单位（分局）
            String orgParentsId = "110000000000";
            List<OrgInfo> orgInfoList = orgInfoService.selectDelegateByParentsId(orgParentsId);

            //获取当前登录用户
            Subject subject = SecurityUtils.getSubject();
            LoaUserInfo loaUserInfo = (LoaUserInfo) subject.getPrincipal();
            //获取当前用户的id
            String userOrgId = loaUserInfo.getOrgId();
            //将当前用户org_id设置进query
            if(StringUtils.isNotBlank(userOrgId) && userOrgId.contains("110023")){
                userOrgId = "110230000000";
            }
            query.setUserOrdId(userOrgId);

            //查询主案件信息
            List<LimsCaseInfoVo> caseInfoList = limsCaseInfoService.selectCaseInfoList(query, pageInfo);
            //查询案件数量
            int caseInfoCount = limsCaseInfoService.selectVOCount(query);
            view = InitializationData.initDictList();
            view.addObject("query", query);
            view.addObject("caseInfoList", caseInfoList);
            view.addObject("orgInfoList", orgInfoList);
            view.addObject("pageInfo", pageInfo.updatePageInfo(caseInfoCount));
        } catch (Exception ex) {
            logger.info("查询失败:" + ex);
        }

        view.setViewName("query/query");
        return view;
    }

    //案件打印
    @RequestMapping("/caseFilePrint")
    public ModelAndView caseFilePrint(HttpServletRequest request, LimsCaseInfoVo query, PageInfo pageInfo){
        ModelAndView view = new ModelAndView();
        //委托id  consignmentId
        //预实验记录表下载 用到委托id
        //委托书下载 用到委托id
        String consignmentId = query.getConsignmentId();
        view.addObject("consignmentId",consignmentId);
        //根据委托id查询案件id
        try {
            if(Strings.isNotBlank(consignmentId)){
            LimsCaseInfo limsCaseInfo = limsCaseInfoService.selectByConsignmentId(consignmentId);
                if(Objects.nonNull(limsCaseInfo)){
                    //案件id  caseId
                    //生成鉴定书 用到案件id
                    view.addObject("limsCaseInfo",limsCaseInfo);
                }
            }
        }catch (Exception e){
            logger.error("案件打印"+e);
    }
        view.setViewName("query/caseFilePrint");
        return view;
    }

}
