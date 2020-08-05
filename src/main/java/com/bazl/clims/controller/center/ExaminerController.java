package com.bazl.clims.controller.center;

import com.bazl.clims.controller.BaseController;
import com.bazl.clims.model.LoaUserInfo;
import com.bazl.clims.model.po.AmPersonalAptitude;
import com.bazl.clims.model.po.Examiner;
import com.bazl.clims.model.po.LimsConsignmentInfo;
import com.bazl.clims.model.po.OrgInfo;
import com.bazl.clims.service.AmPersonalAptitudeService;
import com.bazl.clims.service.ExaminerService;
import com.bazl.clims.service.LimsConsignmentInfoService;
import com.bazl.clims.service.OrgInfoService;
import org.apache.logging.log4j.util.Strings;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.omg.CORBA.Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Administrator on 2019/5/5.
 */
@Controller
@RequestMapping("ExaminerController")
public class ExaminerController extends BaseController {
    @Autowired
    ExaminerService examinerService;
    @Autowired
    LimsConsignmentInfoService limsConsignmentInfoService;
    @Autowired
    AmPersonalAptitudeService  amPersonalAptitudeService;
    @Autowired
    OrgInfoService orgInfoService;

    /**
     * 根据案件id查询检验人
     * @param caseId
     * @return
     */
    @RequestMapping("queryExaminer")
    @ResponseBody
    public Map<String, java.lang.Object> queryExaminer(String caseId){
        Map<String, java.lang.Object> result = new HashMap<>();
        try {
            if(Strings.isNotBlank(caseId)){
                //获取到案件的三个检验人id
                List<Examiner> examinersList= examinerService.queryExaminer(caseId);
                if(examinersList.size()>0){
                    if(Strings.isNotBlank(examinersList.get(0).getInspector1())){
                        AmPersonalAptitude amPersonalAptitudeOne =  amPersonalAptitudeService.queryAmPersonalAptitudeById(examinersList.get(0).getInspector1());
                        if(Objects.nonNull(amPersonalAptitudeOne)){
                            //检验人1
                            result.put("amPersonalAptitudeOne",amPersonalAptitudeOne);
                        }else {
                            result.put("amPersonalAptitudeOne",amPersonalAptitudeOne);
                        }
                    }
                    if(Strings.isNotBlank(examinersList.get(0).getInspector2())){
                        AmPersonalAptitude amPersonalAptitudeTwo =  amPersonalAptitudeService.queryAmPersonalAptitudeById(examinersList.get(0).getInspector2());
                        if(Objects.nonNull(amPersonalAptitudeTwo)){
                            //检验人2
                            result.put("amPersonalAptitudeTwo",amPersonalAptitudeTwo);
                        }else {
                            result.put("amPersonalAptitudeTwo",amPersonalAptitudeTwo);
                        }
                    }
                    if(Strings.isNotBlank(examinersList.get(0).getInspector3())){
                        AmPersonalAptitude amPersonalAptitudeThree =  amPersonalAptitudeService.queryAmPersonalAptitudeById(examinersList.get(0).getInspector3());
                        if(Objects.nonNull(amPersonalAptitudeThree)){
                            //检验人3
                            result.put("amPersonalAptitudeThree",amPersonalAptitudeThree);
                        }else {
                            result.put("amPersonalAptitudeThree",amPersonalAptitudeThree);
                        }
                    }
                }
                //根据案件id查询委托表中的鉴定中心主键id
                List<LimsConsignmentInfo> limsConsignmentInfos = limsConsignmentInfoService.selectByCaseId(caseId);
                if(limsConsignmentInfos.size()>0){
                    OrgInfo orgInfo = orgInfoService.selectByPrimaryKey(limsConsignmentInfos.get(0).getAcceptOrgId());
                    //获取到分局图片路径
                    String path = orgInfo.getOrgCertificateurl() + orgInfo.getOrgCertificatename()+".jpg";
                    String orgImg ="data:image/jpg;base64,"+ this.getImageStr(path);
                    result.put("orgImg",orgImg);
                }else {
                    result.put("orgImg","");
                }
            }
        }catch (Exception ex){
            logger.error("查询案件检验人失败"+ex);
        }
        return result;
    }

    //根据图片路径转base64
    public static String getImageStr(String imgFile) {
        FileInputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(imgFile);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 加密
        sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
        return encoder.encode(data);
    }

    /**
     * 根据案件id查询案件所属分局
     * @param caseId
     * @return
     */
    @RequestMapping("queryByCaseId")
    @ResponseBody
    public String queryByCaseId(String caseId){
        Map<String,Object> result = new HashMap<>();
        try {
            //获得用户对象
            Subject subject = SecurityUtils.getSubject();
            LoaUserInfo user=(LoaUserInfo) subject.getPrincipal();
            if(Strings.isNotBlank(caseId)){
                List<LimsConsignmentInfo> limsConsignmentInfos = limsConsignmentInfoService.selectByCaseId(caseId);
                if(limsConsignmentInfos.size()>0){
                    if(user.getOrgId().equals(limsConsignmentInfos.get(0).getDelegateOrgCode())){
                        return "success";
                    }
                }
            }
        }catch (Exception ex){
            logger.error("根据案件id查询委托信息"+ex);
        }
        return "error";
    };
}
