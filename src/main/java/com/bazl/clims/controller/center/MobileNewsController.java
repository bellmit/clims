package com.bazl.clims.controller.center;

import com.bazl.clims.common.Constants;
import com.bazl.clims.controller.BaseController;
import com.bazl.clims.dao.LogRecordMapper;
import com.bazl.clims.dao.MobileNewsMapper;
import com.bazl.clims.model.LoaUserInfo;
import com.bazl.clims.model.po.HttpMobileNews;
import com.bazl.clims.model.po.LimsCaseInfo;
import com.bazl.clims.model.po.LimsConsignmentInfo;
import com.bazl.clims.model.po.MobileNews;
import com.bazl.clims.service.LimsCaseInfoService;
import com.bazl.clims.service.LimsConsignmentInfoService;
import com.bazl.clims.service.LoaUserInfoService;
import com.bazl.clims.utils.*;
import org.apache.jasper.tagplugins.jstl.core.If;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2018/12/24.
 */
@Controller
@RequestMapping("/MobileNews")
public class MobileNewsController extends BaseController {

    @Autowired
    private MobileNewsMapper mobileNewsMapper;

    @Autowired
    private LogRecordMapper logRecordMapper;

    @Autowired
    private LoaUserInfoService loaUserInfoService;


    @Value("${defaultDnaLabOrgId}")
    private String defaultDnaLabOrgId;

    @Autowired
    private LimsCaseInfoService limsCaseInfoService;
    @Autowired
    private LimsConsignmentInfoService limsConsignmentInfoService;

    /**
     * 查看信息详情
     */
    @RequestMapping(value="/getMobileNews", method = RequestMethod.POST, produces="application/json; charset=utf-8")
    @ResponseBody
    public Result getMobileNews(HttpServletRequest request,@RequestBody MobileNews mobileNews){
        logger.info("---查看信息接口开始"+mobileNews);
        String clientIp = IpAddressUtils.getIpAddr(request);
        MobileNews getmobileNews = mobileNewsMapper.selectByPrimaryKey(mobileNews.getId());
        if (null != getmobileNews && 0 == getmobileNews.getState() ){
            getmobileNews.setState(1);
            mobileNewsMapper.updateByPrimaryKey(getmobileNews);
        }
        logger.info("---查看信息接口结束"+mobileNews);
        return new Result(1, getmobileNews, "");
    }

    /**
     * 查看消息列表
     */
    @RequestMapping(value="/getMobileNewsList", method = RequestMethod.POST, produces="application/json; charset=utf-8")
    @ResponseBody
    public Result getMobileNewsList(HttpServletRequest request,@RequestBody MobileNews mobileNews){
        logger.info("---查看信息接口开始"+mobileNews);
        String clientIp = IpAddressUtils.getIpAddr(request);
        HashMap<String, Object> map = new HashMap<>();
        List<MobileNews> selectPageAll = mobileNewsMapper.selectPageAll(mobileNews);
        int selectPageCount = mobileNewsMapper.selectPageCount(mobileNews);
        map.put("selectPageAll",selectPageAll);
        map.put("selectPageCount",selectPageCount);
        logger.info("---查看信息接口结束"+mobileNews);
        return new Result(1, map, "");
    }


    /**
     * 根据消息id删除消息
     */
    @RequestMapping(value="/DeleteMobileNewsById", method = RequestMethod.POST, produces="application/json; charset=utf-8")
    @ResponseBody
    public Result DeleteMobileNewsById(HttpServletRequest request,@RequestBody MobileNews mobileNews){
        logger.info("---根据消息id删除消息开始"+mobileNews);
        String clientIp = IpAddressUtils.getIpAddr(request);
        int type =  mobileNewsMapper.deleteByPrimaryKey(mobileNews.getId());
        logger.info("---根据消息id删除消息结束"+mobileNews);
        return new Result(1, type, "");
    }

    /**
     * 查看用户已读未读数
     */
    @RequestMapping(value="/getUserMobileNewsNumber", method = RequestMethod.POST, produces="application/json; charset=utf-8")
    @ResponseBody
    public Result getUserMobileNewsNumber(HttpServletRequest request,@RequestBody MobileNews mobileNews){
        logger.info("---查看用户已读未读数开始"+mobileNews);
        LoaUserInfo loaUserInfo = loaUserInfoService.queryloaUserInfoByuserId(mobileNews.getUserid());
        mobileNews.setUserid(loaUserInfo.getPersonalId());
        HashMap getmobileNews = mobileNewsMapper.getUserMobileNewsNumber(mobileNews.getUserid());
        logger.info("---查看用户已读未读数结束"+mobileNews);
        return new Result(1, getmobileNews, "");
    }

    /**
     * 查看用户已读未读数
     */
    @RequestMapping(value="/getPcOrgMassage", method = RequestMethod.POST, produces="application/json; charset=utf-8")
    @ResponseBody
    public Result getPcOrgMassage(HttpServletRequest request,@RequestBody MobileNews mobileNews){
        logger.info("---查看用户已读未读数开始"+mobileNews);
        if (null != mobileNews  &&  !"".equals(mobileNews.getUserOrg())  ){
            List<MobileNews> getmobileNews = mobileNewsMapper.selectPcList(mobileNews);
            logger.info("---查看用户已读未读数结束"+mobileNews);
            return new Result(1, getmobileNews, "");
        }else{
            logger.info("---查看用户已读未读数结束"+mobileNews);
            return new Result(0, "", "机构id不可为空");
        }
    }


       /**
     * 顺义查看消息列表
     */
    @ResponseBody
    @RequestMapping(value="/getUserNewsList", method = RequestMethod.POST, produces="application/json; charset=utf-8")
    public Result getUserNewsList(HttpServletRequest request,@RequestBody MobileNews mobileNews){
        logger.info("---查看信息接口开始"+mobileNews);
        LoaUserInfo loaUserInfo = loaUserInfoService.queryloaUserInfoByuserId(mobileNews.getUserid());
        HashMap<String, Object> map = new HashMap<>();
        mobileNews.setType(10);
        mobileNews.setUserid(loaUserInfo.getPersonalId());
        List<MobileNews> selectPageAll = mobileNewsMapper.getUserNewsList(mobileNews);
        map.put("newsList",selectPageAll);
        logger.info("---查看信息接口结束"+mobileNews);
        return new Result(1, map, "");
    }

    /**
     * 顺义入国家库添加
     */
    @ResponseBody
    @RequestMapping(value="/insertNews", method = RequestMethod.POST, produces="application/json; charset=utf-8")
    public Result insertNews(HttpServletRequest request,@RequestBody HttpMobileNews bean){
        logger.info("---查看信息接口开始"+bean);
        String count = "案件编号" + bean.getCaseId() + " 检材" +bean.getSampleNo()+ "已入库成功!";
        boolean b = insetNews(bean.getUserid(), bean.getType(), count, bean.getCaseIdOrSampleNo());
        logger.info("---查看信息接口结束"+bean);
        return new Result(1, b, "");
    }

    public boolean insetNews(String userid,String type,String content,String caseid){
        /*LimsCaseInfo limsCaseInfo = limsCaseInfoService.select
        List<LimsConsignmentInfo> limsConsignmentInfoList = limsConsignmentInfoService.selectByCaseId(caseid);
        if(ListUtils.isNullOrEmptyList(limsConsignmentInfoList)){
            logger.warn("插入消息队列的案件为空！ caseId=" + caseid);
            return false;
        }*/

        String date = DateUtils.dateToString(new Date(),"yyyy-MM-dd HH:mm:ss");
        MobileNews bean = new  MobileNews();
        bean.setId(UuidUtil.generateUUID());
        if ("1".equals(type)){
            bean.setTitle("入库提醒");
            bean.setContent(content);
            bean.setType(10);
            bean.setMessageType(3);//代表入库类型
        }else if ("2".equals(type)){
            bean.setTitle("比对提醒");
            bean.setContent(content);
            bean.setType(10);
            bean.setMessageType(4);//代表比对类型
            //原1app,2pc
        }else if ("3".equals(type)){
            bean.setTitle("亲缘比对提醒");
            bean.setContent(content);
            bean.setType(10);
            bean.setMessageType(5);//代表比对类型
            //原1app,2pc
        }
        bean.setState(0);
        bean.setCreateDatetime(date);
        bean.setUpdateDatetime(date);
        bean.setUserid(userid);
        bean.setUserOrg(defaultDnaLabOrgId);//受理单位id

        bean.setCaseId(caseid);
        mobileNewsMapper.insert(bean);
        return true;
    }

}
