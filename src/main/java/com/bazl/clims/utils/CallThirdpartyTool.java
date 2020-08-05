package com.bazl.clims.utils;

import com.bazl.clims.model.po.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;

public class CallThirdpartyTool {

    public static Logger logger = LoggerFactory.getLogger(CallThirdpartyTool.class);

    public static LogRecordInfo CallThirdpartyTool (String url,CallThirdpartyInfo bean){

        //url = "http://localhost:8080/commonTool/updateQueueType";
        LogRecord logRecord = new LogRecord();
        FWQQ_NR fwqq_nr = new FWQQ_NR();
        Params params = new Params();

        String ip = null;
        if (null != bean.getIp()) {
            ip = DealIpUtil.addZeroqudian(bean.getIp());
        }
        String date = DateUtils.dateToString(new Date(),"yyyyMMddHHmmssSSS");
        String id = "SR"+ip+date+(int)((Math.random()*9+1)*1000);
        params.setPackageName("com.bazl.kssj");
        if (bean.getPhones().size() > 0) {
            params.setPhones(bean.getPhones());
        }
        Notification notification = new Notification();
        notification.setNotification(JsonUtils.obj2String(bean.getContent()));
        params.setMessage(notification);
        fwqq_nr.setMethod("notice.createPushByUsername");
        fwqq_nr.setParams(params);
        logRecord.setFWBS("S00111000000000006002");
        logRecord.setXXCZRY_XM(bean.getUserName());
        logRecord.setXXCZRY_GMSFHM(bean.getIdentityCard());//身份证号
        logRecord.setXXCZRY_GAJGJGDM(bean.getOrgid());//机构id
        logRecord.setFWQQ_RQSJ(DateUtils.dateToString(new Date(),"yyyyMMddHHmmss"));
        logRecord.setFWQQ_BWBH(id);//SR（2位）+报文来源IP（12位）+报文日期（8位：年月日）+报文时间（9位：时分秒毫秒）+流水号（4位）
        logRecord.setFFBS("FUN001");
        logRecord.setBWLYDKH("8081");
        logRecord.setFWQQZ_ZCXX("A00111000021000003903");
        logRecord.setFWQQ_SJSJLX("service_request");
        logRecord.setYYSBS("lt");
        logRecord.setBWLYIPDZ(ip);
        logRecord.setFWQQSB_BH(ip);
        logRecord.setFWQQ_NR(fwqq_nr);
        String map = JsonUtils.obj2String(logRecord);//.replaceAll("\\\\","")
        System.out.println("请求参数转Json"+map);
        String result = HttpRequestUtil.sendRequest(url,map);
        LogRecordInfo loginfo = new LogRecordInfo();
        HashMap MapResult = JsonUtils.string2Obj(result,HashMap.class);
        if (!MapResult.isEmpty() ){
            if (null != MapResult.get("fwtg_NR")){
                HashMap map1 = JsonUtils.string2Obj(MapResult.get("fwtg_NR").toString(), HashMap.class);
                if (!map1.isEmpty()){
                    if ("200".equals(map1.get("status"))){
                        loginfo.setCode("1");
                    }
                }
            }else{
                loginfo.setCode("2");
            }
        }else{
            loginfo.setCode("2");
        }
        loginfo.setLogId(UuidUtil.generateUUID());
        loginfo.setDataContent(map);
        loginfo.setUrl(url);
        loginfo.setOperator(bean.getUserid());
        loginfo.setCreateDatetime(DateUtils.dateToString(new Date(),"yyyy-MM-dd HH:mm:ss"));
        loginfo.setCallback(result);

        return loginfo;
    }

    public static  String Content(Integer type,String Username,String Orgname,String Orgnamew){
        logger.info("-------------------拼写内容开始");
        String date = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
        String dateChinese = DateUtils.dateToString(new Date(), " yyyy年M月d日");
        String Count = null;
        switch (type){
            case 1:   Count = Orgname+"于"+dateChinese+"受理本案件,受理人:"+Username+"。";//受理
                break;
            case 2:   Count = Orgname+"于"+dateChinese+"出具本案件鉴定书,鉴定人:"+Username+"。";//已出鉴定
                break;
            case 3:   Count = Orgname+"于"+dateChinese+"领取本案件鉴定书,领取人:"+Username+"。";//已领取
                break;
            case 4:   Count = Orgname+"于"+dateChinese+"将本委托至"+Orgnamew+",委托人:"+Username+"。";//委托成功
                break;
            case 5:   Count = Orgname+"于"+dateChinese+"将本补送案件委托至"+Orgnamew+",委托人:"+Username+"。";//新增补送通知
                break;
            case 6:   Count = Orgname+"于"+dateChinese+"已确认比中,比中人:"+Username+"。";//确认比中
                break;
        }
        logger.info("拼写内容为:"+Count);

        return Count;
    }

    public static void main(String[] args) {
        String url = "http://localhost:8080/commonTool/updateQueueType";
        String result = HttpRequestUtil.sendRequest(url, null);
        System.out.println(result);
        HashMap MapResult = JsonUtils.string2Obj(result,HashMap.class);
        if (!MapResult.isEmpty() ){
            if (null != MapResult.get("status") && "200".equals(MapResult.get("status"))){
                System.out.println(result);
            }else{
                System.out.println(result);
            }
        }else{
            System.out.println(result);
        }
    }


}
