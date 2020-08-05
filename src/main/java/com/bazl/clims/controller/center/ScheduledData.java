package com.bazl.clims.controller.center;

import com.bazl.clims.model.LaboratoryInfo;
import com.bazl.clims.model.vo.AmPersonalInfoVo;
import com.bazl.clims.service.AmPersonalInfoService;
import com.bazl.clims.service.LaboratoryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduledData {
    @Autowired
    AmPersonalInfoService amPersonalInfoService;
    @Autowired
    LaboratoryInfoService laboratoryInfoService;

    //每隔5秒执行一次 0/5 * *  * * ?
    //每天中午12点    0 0 12 * * ?
    @Scheduled(cron = "0 0 12  * * ? ")
    public void timingObtainData(){
        String orgArr[]={"110108000000","110023000004","110114000000","110115000000","110106000000","110107000000","110105000000","110102000000","110101000000"};
        for(int i=0;i<orgArr.length;i++){
            List<AmPersonalInfoVo> amPersonalInfoVoList = amPersonalInfoService.queryAmPersonalInfoVoList(orgArr[i]);
            //更新实验室信息表 --实验人员数量字段值
            LaboratoryInfo laboratoryInfo=new LaboratoryInfo();
            laboratoryInfo.setLabPersonnel(String.valueOf(amPersonalInfoVoList.size()));
            laboratoryInfo.setOrgId(orgArr[i]);
            laboratoryInfoService.updateOne(laboratoryInfo);
        }
    }
}
