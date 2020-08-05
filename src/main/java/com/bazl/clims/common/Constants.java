package com.bazl.clims.common;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Sun on 2016/12/28.
 */
public class Constants {

    //用户无效状态
    public static final String user_active_flase = "1";
    //用户有效效状态
    public static final String user_active_true = "0";
    //菜单顶级节点
    public static final String permission_root_flase = "1";
    //委托人添加委托人职务
    public static final String POSITION_LIST="POSITION_LIST";
    //实验阶段
    public static final String EXPERIMENTAL_STAGE = "EXPERIMENTAL_STAGE";
    //实验任务状态
    public static final String TASK_STAGE = "TASK_STAGE";

    //比中参照
    //事主
    public final static String REFERENCE_TYPE_OWNER = "1";
    //嫌疑人
    public final static String REFERENCE_TYPE_SUSPECTS = "2";
    //其他人
    public final static String REFERENCE_TYPE_OTHER_PEOPLE = "3";
    //检材
    public final static String REFERENCE_TYPE_EVIDENCE = "4";

    // 表示都没匹配上
    public static int MATCHMODE_UNMATCHED_FM = 0;
    //表示匹配成功
    public static int MATCHMODE_MATCHED_FM = 1;
    //表示只匹配父亲
    public static int MATCHMODE_MATCHED_F = 2;
    //表示只匹配母亲
    public static int MATCHMODE_MATCHED_M = 3;
    //表示单亲都匹配，双亲综合不匹配
    public static int MATCHMODE_MATCHED_FM_UNMATCHEDALL = 4;
    //孩子混合样本
    public static int MATCHMODE_CHILD_MIX = -1;
    //父亲混合样本
    public static int MATCHMODE_FATHER_MIX = -2;
    //母亲混合样本
    public static int MATCHMODE_MOTHER_MIX = -3;

    // 表示都没匹配上
    public final static int MATCH_MODE_0 = 0;
    // 表示匹配成功双亲
    public final static int MATCH_MODE_1 = 1;
    // 表示只匹配父亲
    public final static int MATCH_MODE_2 = 2;
    // 表示只匹配母亲
    public final static int MATCH_MODE_3 = 3;
    // 表示单亲都匹配，双亲综合不匹配
    public final static int MATCH_MODE_4 = 4;

    //同一比对模式,物证比中
    public final static String SAME_MATCH_WW = "02";
    //同一比对模式,人员比中
    public final static String SAME_MATCH_RR = "03";
    //同一比对模式,人物比中
    public final static String SAME_MATCH_RW = "04";

    //亲缘比对模式
    public final static String RELATION_MATCH = "2";

    //混合比对模式
    public final static String MIX_MATCH = "3";

    //常规类型：国家库类型为：1
    public final static String GENE_NORMAL = "0";
    public final static String GENE_LADDER = "1";
    public final static String GENE_YANG = "2";
    public final static String GENE_YIN = "3";
    //混合类型：国家库类型为：1
    public final static String GENE_MIXED = "4";
    //Y基因型类型：国家库类型为：2
    public final static String GENE_YSTR = "5";
    //是否是Y的基因型
    public static final String GENE_IS_YSTR = "DYS";

    //geneMarker
    public final static String FROM_GENEMARKER = "1";
    //手工录入
    public final static String FROM_MANUALENTRY = "2";
    //已审核
    public final static String AUDIT_STATUS_AUDIT = "1";
    //未审核
    public final static String AUDIT_STATUS_NOTAUDIT = "0";
    //文件可修改
    public final static String FILE_CAN_EDIT = "1";
    //文件不可修改
    public final static String FILE_CAN_NOT_EDIT = "0";

    // 入库
    public final static  String IS_STORE = "1";
    // 未入库
    public final static  String NOT_STORE = "0";

    /** 被害人、身份不明人 */
    public final static String MEMBER_TYPE_VICTIM = "04";
    /** 嫌疑人 */
    public final static String MEMBER_TYPE_SUSPECT = "02";
    /** 前科 */
    public final static String MEMBER_TYPE_AGAIN = "01";
    /** 涉黑 */
    public final static String MEMBER_TYPE_GANGDOM = "03";
    /** 失踪人员亲属库 */
    public final static String MEMBER_TYPE_FAMILY = "05";
    /** 未破案现场 */
    public final static String MEMBER_TYPE_SCENE = "06";
    /** 待删除样本 */
    public final static String MEMBER_TYPE_DELETE  = "00";
    /** 被鉴定人 */
    public final static String MEMBER_TYPE_BYIDENTIFY  = "07";
    /** 鉴定人 */
    public final static String MEMBER_TYPE_IDENTIFY  = "08";
    /** 违法犯罪人员 */
    public final static String MEMBER_TYPE_CRIMINAL = "09";
    /** 质控人员 */
    public final static String MEMBER_TYPE_QUALITY_CONTROL = "10";
    //以下几个为天津版本使用。其他版本如有需要，也可使用
    /** 嫌疑人亲属 */
    public final static String MEMBER_TYPE_SUSPECT_FAMILY  = "11";
    /** 受害人亲属 */
    public final static String MEMBER_TYPE_VICTIM_FAMILY  = "12";
    /** 无名尸  */
    public final static String MEMBER_TYPE_UNDEAD  = "13";
    /** 失踪人口 */
    public final static String MEMBER_TYPE_MISSING  = "14";
    /** 其他 */
    public final static String MEMBER_TYPE_OTHER  = "15";

    //人员类型
    /** 嫌疑人 */
    public final static String PERSON_TYPE_01  = "01";
    /** 嫌疑人亲属 */
    public final static String PERSON_TYPE_02  = "02";
    /** 受害人 */
    public final static String PERSON_TYPE_03  = "03";
    /** 受害人亲属 */
    public final static String PERSON_TYPE_04  = "04";
    /** 失踪人 */
    public final static String PERSON_TYPE_05  = "05";
    /** 失踪人员亲属 */
    public final static String PERSON_TYPE_06  = "06";
    //在逃人员
    public static final String PERSON_TYPE_07 = "07";
    //在逃人员亲属
    public static final String PERSON_TYPE_08 = "08";
    /** 其它人员 */
    public final static String PERSON_TYPE_99  = "99";

    //检材类型
    /** 血样 */
    public final static String SAMPLE_TYPE_01  = "01";
    /** 精斑 */
    public final static String SAMPLE_TYPE_02  = "02";
    /** 脱落细胞 */
    public final static String SAMPLE_TYPE_03  = "03";
    /** 唾液斑 */
    public final static String SAMPLE_TYPE_04  = "04";
    /** 指甲 */
    public final static String SAMPLE_TYPE_05  = "05";
    /** 牙齿 */
    public final static String SAMPLE_TYPE_06  = "06";
    /** 骨骼 */
    public final static String SAMPLE_TYPE_07  = "07";
    /** 组织 */
    public final static String SAMPLE_TYPE_08  = "08";
    /** 毛发 */
    public final static String SAMPLE_TYPE_09  = "09";
    /** 其它 */
    public final static String SAMPLE_TYPE_99  = "99";
    //血痕
    public final static String SAMPLE_TYPE_21  = "21";

    //试验阶段
    //提取阶段
    public static final String EXTRACT_PHASE = "1";
    //扩增阶段
    public static final String PCR_PHASE = "2";
    //电泳阶段
    public static final String SY_PHASE = "3";
    //分析阶段
    public static final String ANALYSIS_PHASE = "4";

    //提取阶段
    public static final String EXPERIMENTAL_STAGE_01 = "01";
    //扩增阶段
    public static final String EXPERIMENTAL_STAGE_02 = "02";
    //电泳阶段
    public static final String EXPERIMENTAL_STAGE_03 = "03";
    //分析阶段
    public static final String EXPERIMENTAL_STAGE_04 = "04";
    //其他
    public static final String EXPERIMENTAL_STAGE_05 = "05";
    //胶液
    public static final String EXPERIMENTAL_STAGE_06 = "06";
    //检验类型
    //扩增体系
    public static final String EXPERIMENTAL_PCR_SYSTEM = "PCR_SYSTEM";
    //电泳试剂盒参数
    public static final String EXPERIMENTAL_KIT_PARAMETERS = "KIT_PARAMETERS";

    //添加
    public static final String OPERATE_TYPE_ADD = "1";
    //更改
    public static final String OPERATE_TYPE_EDIT = "2";

    //案件登记字典项----------------
    //案件性质
    public static final String CASE_PROPERTY = "CASE_PROPERTY";
    //凶杀
    public static final String CASE_PROPERTY_01 = "01";
    //伤害
    public static final String CASE_PROPERTY_02 = "02";
    //其他盗窃
    public static final String CASE_PROPERTY_03 = "03";
    //抢夺抢劫
    public static final String CASE_PROPERTY_04 = "04";
    //强奸
    public static final String CASE_PROPERTY_05 = "05";
    //非正常死亡
    public static final String CASE_PROPERTY_06 = "06";
    //伤害致死
    public static final String CASE_PROPERTY_07 = "07";
    //治安
    public static final String CASE_PROPERTY_08 = "08";
    //走失人口
    public static final String CASE_PROPERTY_09 = "09";
    //打拐
    public static final String CASE_PROPERTY_10 = "10";
    //交通事故
    public static final String CASE_PROPERTY_11 = "11";
    //尸源认定
    public static final String CASE_PROPERTY_12 = "12";
    //入室盗窃
    public static final String CASE_PROPERTY_13 = "13";
    //盗窃机动车
    public static final String CASE_PROPERTY_14 = "14";
    //盗窃车内财物
    public static final String CASE_PROPERTY_15 = "15";
    //盗抢工地
    public static final String CASE_PROPERTY_16 = "16";
    //爬楼钻窗
    public static final String CASE_PROPERTY_17 = "17";
    //盗窃保险柜
    public static final String CASE_PROPERTY_18 = "18";
    //亲缘
    public static final String CASE_PROPERTY_19 = "19";
    //其它
    public static final String CASE_PROPERTY_20 = "20";
    //诈骗
    public static final String CASE_PROPERTY_21 = "21";
    //抢夺
    public static final String CASE_PROPERTY_22 = "22";

    //鉴定要求
    public static final String IDENTIFICATION_TYPE = "IDENTIFICATION_TYPE";
    //人员类型
    public static final String PERSON_TYPE = "PERSON_TYPE";
    //性别
    public static final String GENDER = "GENDER";
    //检材类型
    public static final String SAMPLE_TYPE = "SAMPLE_TYPE";
    //包装方法
    public static final String PACK_METHOD = "PACK_METHOD";
    //案件级别
    public static final String CASE_LEVEL = "CASE_LEVEL";
    //案件状态
    public static final String CASE_STATUS = "CASE_STATUS";
    //案件状态_未受理
    public static final String CASE_STATUS_01 = "01";
    //案件状态_已完成
    public static final String CASE_STATUS_03 = "03";
    //案件状态_在检验
    public static final String CASE_STATUS_02 = "02";
    //案件状态_已退案
    public static final String CASE_STATUS_04 = "04";

    //案件类型
    public static final String CASE_TYPE = "CASE_TYPE";
    //刑事案件
    public static final String CASE_TYPE_01 = "01";
    //人员关系类型
    public static final String RELATION_TYPE = "RELATION_TYPE";
    //父亲
    public static final String RELATION_TYPE_01 = "01";
    //母亲
    public static final String RELATION_TYPE_02 = "02";
    //丈夫
    public static final String RELATION_TYPE_03 = "03";
    //妻子
    public static final String RELATION_TYPE_04 = "04";
    //儿子
    public static final String RELATION_TYPE_05 = "05";
    //女儿
    public static final String RELATION_TYPE_06 = "06";
    //兄
    public static final String RELATION_TYPE_07 = "07";
    //弟
    public static final String RELATION_TYPE_08 = "08";
    //姐
    public static final String RELATION_TYPE_09 = "09";
    //妹
    public static final String RELATION_TYPE_10 = "10";
    //提取方法
    public static final String EXTRACT_METHOD = "EXTRACT_METHOD";
    //提取实验方法
    public static final String EXTRACTION_TEST_METHOD = "EXTRACTION_TEST_METHOD";
    //入库类型
    public static final String INSTORED_TYPE = "INSTORED_TYPE";
    //现场物证
    public static final String INSTORED_TYPE_01 = "01";
    //混合物证
    public static final String INSTORED_TYPE_02 = "02";
    //违法犯罪人员
    public static final String INSTORED_TYPE_03 = "03";
    //嫌疑人
    public static final String INSTORED_TYPE_04 = "04";
    //受害人
    public static final String INSTORED_TYPE_05 = "05";
    //失踪人口
    public static final String INSTORED_TYPE_06 = "06";
    //无名尸
    public static final String INSTORED_TYPE_07 = "07";
    //嫌疑人亲属
    public static final String INSTORED_TYPE_08 = "08";
    //受害人亲属
    public static final String INSTORED_TYPE_09 = "09";
    //失踪人口亲属
    public static final String INSTORED_TYPE_10 = "10";
    //基础库
    public static final String INSTORED_TYPE_11 = "11";
    //YSTR
    public static final String INSTORED_TYPE_12 = "12";

    //材料提取使用方法 离
    public static final String USE_INSTRUMENT_LEAVE = "USE_INSTRUMENT_LEAVE";
    //材料提取使用方法 浴
    public static final String USE_INSTRUMENT_BATH = "USE_INSTRUMENT_BATH";
    //材料提取使用方法 干
    public static final String USE_INSTRUMENT_DRY = "USE_INSTRUMENT_DRY";
    //材料提取使用方法 摇
    public static final String USE_INSTRUMENT_SHAKE = "USE_INSTRUMENT_SHAKE";
    //材料提取使用方法 振
    public static final String USE_INSTRUMENT_EARTHQUAKE = "USE_INSTRUMENT_EARTHQUAKE";
    //纯化方法
    public static final String PURIFICATION_METHOD = "PURIFICATION_METHOD";

    //流水号类型
    //委托编号
    public static final String TYPE_CODE_CONSIGNMENT_NO = "CONSIGNMENT_NO";
    //案件编号
    public static final String TYPE_CODE_CASE_NO = "CASE_NO";
    //人员编号
    public static final String TYPE_CODE_PERSON_NO = "PERSON_NO";
    //案件编号前缀
    public static final String CASE_NO_PREFIX = "FYB";
    //人员编号前缀
    public static final String PERSON_NO_PREFIX = "BJ";

    public static final String ARCHIVES_TYPE_PROXY = "01";
    public static final String ARCHIVES_TYPE_APPOINT = "02";

    //物证编号（样本编号）
    public static final String TYPE_CODE_SAMPLE_NO = "SAMPLE_NO";

    //任务编号
    public static final String TYPE_CODE_TASK_NO = "TASK_NO";
    //提取任务编号
    public static final String TYPE_CODE_EXTRACT_NO = "EXTRACT_NO";
    //扩增任务编号
    public static final String TYPE_CODE_PCR_NO = "PCR_NO";
    //电泳任务编号
    public static final String TYPE_CODE_SY_NO = "SY_NO";
    //任务编号拼接
    public static final String TASK_NO_PREFIX = "TK";
    //提取任务编号拼接
    public static final String EXTRACT_NO_PREFIX = "TQ";
    //扩增任务编号拼接
    public static final String PCR_NO_PREFIX = "KZ";
    //电泳任务编号拼接
    public static final String SY_NO_PREFIX = "SY";

    public static final String APPEND_FLAG_0 = "0";
    public static final String APPEND_FLAG_1 = "1";

    //检材类别；0：物证：，1：人员
    public static final String SAMPLE_FLAG_0 = "0";
    public static final String SAMPLE_FLAG_1 = "1";

    //删除状态；0：未删除，1：已删除
    public static final String DELETE_FLAG_0 = "0";
    public static final String DELETE_FLAG_1 = "1";

    //审核状态；0：未审核，1：已审核
    public static final String AUDIT_STATUS_0 = "0";
    public static final String AUDIT_STATUS_1 = "1";

    //市局
    public static final String ORG_LEVEL_TOP = "1";
    //分局
    public static final String ORG_LEVEL_FENJU = "2";
    //派出所
    public static final String ORG_LEVEL_PAICHUSUO = "3";

    /**
     * 生成流水号
     * @param serialNumber
     * @return
     */
    public static final String serialNumber(String serialNumber) {
        if(Integer.parseInt(serialNumber) == 99999){
            serialNumber = "00000";
        }
        String  number = (Integer.parseInt(serialNumber)+1)+"" ;
        int num = 5 - number.length() ;
        for (int i = 0; i < num; i++) {
            number = "0"+number;
        }
        return number;
    }

    public static final String selectOrgNameSp(String orgCode) {
        if(StringUtils.isNotBlank(orgCode)&& orgCode.length()>6){
            orgCode = orgCode.substring(0,6);
        }
        String orgNameSp = "";
        if(StringUtils.isNotBlank(orgCode) && ("110101").equals(orgCode)){
            orgNameSp = "DC";//东城分局
        }else if(StringUtils.isNotBlank(orgCode) && ("110102").equals(orgCode)){
            orgNameSp = "XC";//西城分局
        }else if(StringUtils.isNotBlank(orgCode) && ("110105").equals(orgCode)){
            orgNameSp = "CY";//朝阳分局
        }else if(StringUtils.isNotBlank(orgCode) && ("110106").equals(orgCode)){
            orgNameSp = "FT";//丰台分局
        }else if(StringUtils.isNotBlank(orgCode) && ("110107").equals(orgCode)){
            orgNameSp = "SJS";//石景山分局
        }else if(StringUtils.isNotBlank(orgCode) && ("110108").equals(orgCode)){
            orgNameSp = "HD";//海淀分局
        }else if(StringUtils.isNotBlank(orgCode) && ("110109").equals(orgCode)){
            orgNameSp = "MTG";//门头沟分局
        }else if(StringUtils.isNotBlank(orgCode) && ("110111").equals(orgCode)){
            orgNameSp = "FS";//房山分局
        }else if(StringUtils.isNotBlank(orgCode) && ("110112").equals(orgCode)){
            orgNameSp = "TZ";//通州分局
        }else if(StringUtils.isNotBlank(orgCode) && ("110113").equals(orgCode)){
            orgNameSp = "SY";//顺义分局
        }else if(StringUtils.isNotBlank(orgCode) && ("110114").equals(orgCode)){
            orgNameSp = "CP";//昌平分局
        }else if(StringUtils.isNotBlank(orgCode) && ("110115").equals(orgCode)){
            orgNameSp = "DX";//大兴分局
        }else if(StringUtils.isNotBlank(orgCode) && ("110116").equals(orgCode)){
            orgNameSp = "HR";//怀柔分局
        }else if(StringUtils.isNotBlank(orgCode) && ("110117").equals(orgCode)){
            orgNameSp = "PG";//平谷分局
        }else if(StringUtils.isNotBlank(orgCode) && ("110228").equals(orgCode)){
            orgNameSp = "MY";//密云分局
        }else if(StringUtils.isNotBlank(orgCode) && ("110229").equals(orgCode)){
            orgNameSp = "YQ";//延庆分局
        }else if(StringUtils.isNotBlank(orgCode) && ("110230").equals(orgCode)){
            orgNameSp = "FYB";//法医中心
        }else{
            orgNameSp = "FYB";//法医中心
        }

        return orgNameSp;
    }

    //样本状态_未受理
    public static final String SAMPLE_STATUS_01 = "01";
    //样本状态_已完成
    public static final String SAMPLE_STATUS_03 = "03";
    //样本状态_在检验
    public static final String SAMPLE_STATUS_02 = "02";
    //样本状态_已退案
    public static final String SAMPLE_STATUS_04 = "04";


    public static final String selectPersonTypeSp(String personType) {
        String personTypeSq = "";
        if(StringUtils.isNotBlank(personType) && ("01").equals(personType)){
            personTypeSq = "S";//嫌疑人
        }else if(StringUtils.isNotBlank(personType) && ("02").equals(personType)){
            personTypeSq = "P";//嫌疑人亲属
        }else if(StringUtils.isNotBlank(personType) && ("03").equals(personType)){
            personTypeSq = "V";//受害人
        }else if(StringUtils.isNotBlank(personType) && ("04").equals(personType)){
            personTypeSq = "P";//受害人亲属
        }else if(StringUtils.isNotBlank(personType) && ("05").equals(personType)){
            personTypeSq = "Q";//失踪人员
        }else if(StringUtils.isNotBlank(personType) && ("06").equals(personType)){
            personTypeSq = "P";//失踪人员亲属
        }else if(StringUtils.isNotBlank(personType) && ("99").equals(personType)){
            personTypeSq = "Q";//其他
        }

        return personTypeSq;
    }

    //市局orgid
    public static final String TOP_ORG_ID = "110000000000";

    //法医中心id  forensicCenter
    public static final String FORENSIC_CENTER_ORG_ID = "110230000000";
    public static final String FORENSIC_CENTER_SHORT_ORG_ID = "110230";
    public static final String FORENSIC_CENTER_SHORT_ORG_ID_BAK = "110023";

    //检材载体
    public static final String SAMPLE_CARRIER = "SAMPLE_CARRIER";

    //鉴定要求
    //同一鉴定
    public static final String IDENTIFYREQUIREMENT_01 = "01";
    //亲缘鉴定
    public static final String IDENTIFYREQUIREMENT_02 = "02";
    //同一鉴定，亲缘鉴定
    public static final String IDENTIFYREQUIREMENT_03 = "01,02";

    public static final String selectOrgQNo(String orgCode) {
        String orgQNo = "";
        if(StringUtils.isNotBlank(orgCode) && ("110101000000").equals(orgCode)){
            orgQNo = "DCFSSBPSB-BG-02-WZ-2017";//东城分局
        }else if(StringUtils.isNotBlank(orgCode) && ("110102000000").equals(orgCode)){
            orgQNo = "XCFSSBPSB-BG-02-WZ-2016";//西城分局
        }else if(StringUtils.isNotBlank(orgCode) && ("110105000000").equals(orgCode)){
            orgQNo = "CYFSII-BG-02-WZ";//朝阳分局
        }else if(StringUtils.isNotBlank(orgCode) && ("110106000000").equals(orgCode)){
            orgQNo = "FSSFTB-ZD-08-A-WZ-2018";//丰台分局
        }else if(StringUtils.isNotBlank(orgCode) && ("110107000000").equals(orgCode)){
            orgQNo = "CX-05-C";//石景山分局
        }else if(StringUtils.isNotBlank(orgCode) && ("110108000000").equals(orgCode)){
            orgQNo = "HDGS-BG-02-WZ-2016";//海淀分局
        }else if(StringUtils.isNotBlank(orgCode) && ("110109000000").equals(orgCode)){
            orgQNo = "";//门头沟分局
        }else if(StringUtils.isNotBlank(orgCode) && ("110111000000").equals(orgCode)){
            orgQNo = "";//房山分局
        }else if(StringUtils.isNotBlank(orgCode) && ("110112000000").equals(orgCode)){
            orgQNo = "";//通州分局
        }else if(StringUtils.isNotBlank(orgCode) && ("110113000000").equals(orgCode)){
            orgQNo = "SYGJ-2020-CX11-F";//顺义分局
        }else if(StringUtils.isNotBlank(orgCode) && ("110114000000").equals(orgCode)){
            orgQNo = "CX-05-C";//昌平分局
        }else if(StringUtils.isNotBlank(orgCode) && ("110115000000").equals(orgCode)){
            orgQNo = "DXFSII-BG-02-FW";//大兴分局
        }else if(StringUtils.isNotBlank(orgCode) && ("110116000000").equals(orgCode)){
            orgQNo = "";//怀柔分局
        }else if(StringUtils.isNotBlank(orgCode) && ("110117000000").equals(orgCode)){
            orgQNo = "";//平谷分局
        }else if(StringUtils.isNotBlank(orgCode) && ("110228000000").equals(orgCode)){
            orgQNo = "";//密云分局
        }else if(StringUtils.isNotBlank(orgCode) && ("110229000000").equals(orgCode)){
            orgQNo = "";//延庆分局
        }else if(StringUtils.isNotBlank(orgCode) && ("110230000000").equals(orgCode)){
            orgQNo = "CX03-E";//法医中心
        }

        return orgQNo;
    }

    //Rep补送的确认书质控号
    public static final String selectRepOrgQNo(String orgCode) {
        String orgQNo = "";
        if(StringUtils.isNotBlank(orgCode) && ("110101000000").equals(orgCode)){
            orgQNo = "DCFSSBPSB-BG-03-WZ-2017";//东城分局
        }else if(StringUtils.isNotBlank(orgCode) && ("110102000000").equals(orgCode)){
            orgQNo = "XCFSSBPSB-BG-03-WZ-2016";//西城分局
        }else if(StringUtils.isNotBlank(orgCode) && ("110105000000").equals(orgCode)){
            orgQNo = "CYFSII-BG-03-WZ";//朝阳分局
        }else if(StringUtils.isNotBlank(orgCode) && ("110106000000").equals(orgCode)){
            orgQNo = "FSSFTB-ZD-08-A-WZ-2018";//丰台分局
        }else if(StringUtils.isNotBlank(orgCode) && ("110107000000").equals(orgCode)){
            orgQNo = "CX-05-C";//石景山分局
        }else if(StringUtils.isNotBlank(orgCode) && ("110108000000").equals(orgCode)){
            orgQNo = "HDGS-BG-03-WZ-2016";//海淀分局
        }else if(StringUtils.isNotBlank(orgCode) && ("110109000000").equals(orgCode)){
            orgQNo = "";//门头沟分局
        }else if(StringUtils.isNotBlank(orgCode) && ("110111000000").equals(orgCode)){
            orgQNo = "";//房山分局
        }else if(StringUtils.isNotBlank(orgCode) && ("110112000000").equals(orgCode)){
            orgQNo = "";//通州分局
        }else if(StringUtils.isNotBlank(orgCode) && ("110113000000").equals(orgCode)){
            orgQNo = "";//顺义分局
        }else if(StringUtils.isNotBlank(orgCode) && ("110114000000").equals(orgCode)){
            orgQNo = "CX-05-C";//昌平分局
        }else if(StringUtils.isNotBlank(orgCode) && ("110115000000").equals(orgCode)){
            orgQNo = "DXFSII-BG-03-FW";//大兴分局
        }else if(StringUtils.isNotBlank(orgCode) && ("110116000000").equals(orgCode)){
            orgQNo = "";//怀柔分局
        }else if(StringUtils.isNotBlank(orgCode) && ("110117000000").equals(orgCode)){
            orgQNo = "";//平谷分局
        }else if(StringUtils.isNotBlank(orgCode) && ("110228000000").equals(orgCode)){
            orgQNo = "";//密云分局
        }else if(StringUtils.isNotBlank(orgCode) && ("110229000000").equals(orgCode)){
            orgQNo = "";//延庆分局
        }else if(StringUtils.isNotBlank(orgCode) && ("110230000000").equals(orgCode)){
            orgQNo = "CX03-E";//法医中心
        }

        return orgQNo;
    }




    //查询状态_非现堪
    public static final String QUERY_TYPE_01 = "01";
    //下载打开手写板程序名称
    public static final String SignBazl = "SignBazl.exe";

    public static final String QUERY_FLAG_1 = "1";

    public static final String[] SYTABLE_POSTION_ARR = new String[]{
            "A01","A02","A03","A04","A05","A06","A07","A08","A09","A10","A11","A12",
            "B01","B02","B03","B04","B05","B06","B07","B08","B09","B10","B11","B12",
            "C01","C02","C03","C04","C05","C06","C07","C08","C09","C10","C11","C12",
            "D01","D02","D03","D04","D05","D06","D07","D08","D09","D10","D11","D12",
            "E01","E02","E03","E04","E05","E06","E07","E08","E09","E10","E11","E12",
            "F01","F02","F03","F04","F05","F06","F07","F08","F09","F10","F11","F12",
            "G01","G02","G03","G04","G05","G06","G07","G08","G09","G10","G11","G12",
            "H01","H02","H03","H04","H05","H06","H07","H08","H09","H10","H11","H12"
    };

    public final static String[] HORIZONTAL_SYTABLE_POSTION_ARR = {
            "01",
            "02",
            "03",
            "04",
            "05",
            "06",
            "07",
            "08",
            "09",
            "10",
            "11",
            "12"
    };

    public static final String[] SYTABLE_POSTION_ARR_VER = new String[]{
            "A01","B01","C01","D01","E01","F01","G01","H01",
            "A02","B02","C02","D02","E02","F02","G02","H02",
            "A03","B03","C03","D03","E03","F03","G03","H03",
            "A04","B04","C04","D04","E04","F04","G04","H04",
            "A05","B05","C05","D05","E05","F05","G05","H05",
            "A06","B06","C06","D06","E06","F06","G06","H06",
            "A07","B07","C07","D07","E07","F07","G07","H07",
            "A08","B08","C08","D08","E08","F08","G08","H08",
            "A09","B09","C09","D09","E09","F09","G09","H09",
            "A10","B10","C10","D10","E10","F10","G10","H10",
            "A11","B11","C11","D11","E11","F11","G11","H11",
            "A12","B12","C12","D12","E12","F12","G12","H12"
    };

    //复合类型
    public final static String QUESTION_TYPES_01 = "0";
    //已审核
    public final static String QUESTION_TYPES_02 = "1";
    //比中复核
    public final static String QUESTION_TYPES_03 = "2";
    //复检
    public final static String QUESTION_TYPES_04 = "3";
    //无基因
    public final static String QUESTION_TYPES_05 = "4";
    //本板比中
    public final static String QUESTION_TYPES_06 = "5";
    //结果不好
    public final static String QUESTION_TYPES_07 = "6";

    //队列状态，未处理
    public final static String QUEUE_STATUS_0 = "0";
    //队列状态，已处理
    public final static String QUEUE_STATUS_1 = "1";
    //队列状态，2次已处理
    public final static String QUEUE_STATUS_2 = "2";

    //队列类型，本地库比对队列
    public final static String QUEUE_TYPE_1 = "1";

    //案件上报队列
    public final static String QUEUE_TYPE_CASE_TRANSFER = "15";
    //建库人员上报队列
    public final static String QUEUE_TYPE_PERSON_TRANSFER = "16";

    //比对类型
    public static final String COMPARISON_TYPE = "COMPARISON_TYPE";

    //比对类别
    public static final String COMPARISON_CATEGORY = "COMPARISON_CATEGORY";
    //亲缘比对结果
    public static final String COMPARISON_CATEGORY_01 = "01";
    //物证比对结果
    public static final String COMPARISON_CATEGORY_02 = "02";
    //人员比对结果
    public static final String COMPARISON_CATEGORY_03 = "03";
    //人员物证比对结果
    public static final String COMPARISON_CATEGORY_04 = "04";
    //全部比对结果
    public static final String COMPARISON_CATEGORY_05 = "05";

    //状态
    public static final String COMPARE_STATUS = "COMPARE_STATUS";
    //待复核
    public static final String COMPARE_STATUS_0 = "0";
    //复核
    public static final String COMPARE_STATUS_1 = "1";
    //确认比中
    public static final String COMPARE_STATUS_2 = "2";
    //解除关联
    public static final String COMPARE_STATUS_3 = "3";

    //获取技术任职职称
    public static final String TECHNICAL_TITLES = "TECHNICAL_TITLES";

    //---------------引入DNA上传编码开始-----------------
    public static final String REGENT_NAME_1 = "Identifiler Plus";
    public static final String REGENT_NAME_2 = "GlobalFiler";
    public static final String REGENT_NAME_3 = "YFILER PLUS";
    public static final String REGENT_NAME_4 = "TYPER 19";
    public static final String REGENT_NAME_5 = "华夏白金";

    public static final String FLAG_TRUE = "1";
    public static final String FLAG_FALSE= "0";
    public static final String FLAG_TWO= "2";

    //受理人全部查询
    public static final String SELECT_ACCEPTOR_ID= "1001";

    //入库成功
    public static final String INSTORED_SUCCESS = "1";
    //入库失败
    public static final String INSTORED_FAIL = "-1";

    //亲缘比对
    public static final String MATCH_TYPE_0 = "0";
    //同一比对
    public static final String MATCH_TYPE_1 = "1";

    //北京
    public static final int SAMPLE_LEVEL_1 = 1;
    //其他实验室
    public static final int SAMPLE_LEVEL_2 = 2;

    public static final List<String> IdentifilerPlusList = Arrays.asList("D8S1179","D21S11","D7S820","CSF1PO","D3S1358","TH01","D13S317","D16S539","D2S1338","D19S433","vWA","TPOX","D18S51","Amel","D5S818","FGA");
    //字典
    public static final String DICR_TYPE = "dict";
    //24孔
    public static final String HOLE_NUM_24 = "24";
    //96孔
    public static final String HOLE_NUM_96 = "96";

    public static final String[] SYTABLE_POSTION_HORIZONTAL_ARR = new String[]{
            "A01","E01","A02","E02","A03","E03","B01","F01","B02","F02","B03","F03",
            "C01","G01","C02","G02","C03","G03","D01","H01","D02","H02","D03","H03",
            "A04","E04","A05","E05","A06","E06","B04","F04","B05","F05","B06","F06",
            "C04","G04","C05","G05","C06","G06","D04","H04","D05","H05","D06","H06",
            "A07","E07","A08","E08","A09","E09","B07","F07","B08","F08","B09","F09",
            "C07","G07","C08","G08","C09","G09","D07","H07","D08","H08","D09","H09",
            "A10","E10","A11","E11","A12","E12","B10","F10","B11","F11","B12","F12",
            "C10","G10","C11","G11","C12","G12","D10","H10","D11","H11","D12","H12"
    };

    public static final Integer[] SYTABLE_POSTION_HORIZONTAL_NUMBER = new Integer[]{
            1,5,9,13,17,21,2,6,10,14,18,22,
            3,7,11,15,19,23,4,8,12,16,20,24,
            25,29,33,37,41,45,26,30,34,38,42,46,
            27,31,35,39,43,47,28,32,36,40,44,48,
            49,53,57,61,65,69,50,54,58,62,66,70,
            51,55,59,63,67,71,52,56,60,64,68,72,
            73,77,81,85,89,93,74,78,82,86,90,94,
            75,79,83,87,91,95,76,80,84,88,92,96
    };

    //实验阶段
    //提取
    public static final String STAGE_TQ = "tq";
    //扩增
    public static final String STAGE_KZ = "kz";
    //电泳
    public static final String STAGE_SY = "sy";
    //其他
    public static final String STAGE_QT = "qt";
    //是否已经创建实验,0：未创建，1已创建
    public static final String IS_CREATE_0 = "0";
    public static final String IS_CREATE_1 = "1";

    public static final String[] POSTION_ARR_ALL = new String[]{
            "A01","A02","A03","B01","B02","B03","C01","C02","C03","D01","D02","D03",
            "E01","E02","E03","F01","F02","F03","G01","G02","G03","H01","H02","H03",
            "A04","A05","A06","B04","B05","B06","C04","C05","C06","D04","D05","D06",
            "E04","E05","E06","F04","F05","F06","G04","G05","G06","H04","H05","H06",
            "A07","A08","A09","B07","B08","B09","C07","C08","C09","D07","D08","D09",
            "E07","E08","E09","F07","F08","F09","G07","G08","G09","H07","H08","H09",
            "A10","A11","A12","B10","B11","B12","C10","C11","C12","D10","D11","D12",
            "E10","E11","E12","F10","F11","F12","G10","G11","G12","H10","H11","H12"
    };

    public static final Integer[] POSTION_HORIZONTAL_NUMBER_ALL = new Integer[]{
            1,9,17,2,10,18,3,11,19,4,12,20,
            5,13,21,6,14,22,7,15,23,8,16,24,
            25,33,41,26,34,42,27,35,43,28,36,44,
            29,37,45,30,38,46,31,39,47,32,40,48,
            49,57,65,50,58,66,51,59,67,52,60,68,
            53,61,69,54,62,70,55,63,71,56,64,72,
            73,81,89,74,82,90,75,83,91,76,84,92,
            77,85,93,78,86,94,79,87,95,80,88,96
    };
    //表
    public final static String SAMPLE_TABLE = "SAMPLE_TABLE";
    public final static String SAMPLE_EXTRACT = "SAMPLE_EXTRACT";
    public final static String SAMPLE_PCR = "SAMPLE_PCR";
    public final static String SAMPLE_SY = "SAMPLE_SY";
    //微量、常量分界
    public final static int TRACE_CONSTANT_LIMIT = 50;
    //背景色
    public final static String COLOR_LIGHT_CORNFLOWER_BLUE = "LIGHT_CORNFLOWER_BLUE";
    public final static String COLOR_LIGHT_BLUE = "LIGHT_BLUE";

    public static final Integer[] SYTABLE_POSTION_ARR_VER_NUM = new Integer[]{
            1,2,3,4,5,6,7,8,
            9,10,11,12,13,14,15,16,
            17,18,19,20,21,22,23,24,
            25,26,27,28,29,30,31,32,
            33,34,35,36,37,38,39,40,
            41,42,43,44,45,46,47,48,
            49,50,51,52,53,54,55,56,
            57,58,59,60,61,62,63,64,
            65,66,67,68,69,70,71,72,
            73,74,75,76,77,78,79,80,
            81,82,83,84,85,86,87,88,
            89,90,91,92,93,94,95,96
    };
    //默认洗脱体积
    public static final String DICT_TPYE_ELUTION_DEFAULT = "ELUTION_DEFAULT";
    //检材属性
    public static final String DICT_TPYE_SAMPLE_PROPERTY = "SAMPLE_PROPERTY";
    //设备类型
    public static final String DICT_TPYE_DEVICE_TYPE = "DEVICE_TYPE";
    //确证方法
    public static final String DICT_TPYE_CONFIRMATORY_METHOD = "CONFIRMATORY_METHOD";
    //预实验方法
    public static final String DICT_TPYE_PRE_EXPERIMENTAL_METHOD = "PRE_EXPERIMENTAL_METHOD";
    //检材转移方法
    public static final String DICT_TPYE_SAMPLE_TRANSFER_METHOD = "SAMPLE_TRANSFER_METHOD";
    //程序名称方法
    public static final String DICT_TPYE_PROGRAM_NAME = "PROGRAM_NAME";
    //样本表序号
    public final static String SAMPLE_PLATE_SORT_1 = "1";

    public final static String DICT_01 = "01";
    //是否拼板
    public static final String IS_SPELL_YES = "YES";
    public static final String IS_SPELL_NO = "NO";
    //事主样本信息
    public  static  final String CORE_VICTIM_STATS_TRUE = "1"; //入库中为事主样本信息
    public  static  final String CORE_VICTIM_STATS_FLASE ="0"; //入库中不为事主样本信息
    public  static  final String CORE_VICTIM_STATS_TEST  ="2"; //人员类型为受害人时前台展示的事主样本信息

    //委托状态：0,现场；1，非现场；2，在逃人员
    public static final String ENTRUST_STATUS_0 = "0";
    public static final String ENTRUST_STATUS_1 = "1";
    public static final String ENTRUST_STATUS_2 = "2";

    public static final String DICT_TYPE_QC_SETTING = "QC_SETTINGS_TYPE";

    public static final String QUERENSHU_CONTROL_NO = "01"; //确认书模版

    public static final String BUSONG_QUERENSHU_CONTROL_NO = "02"; //补送确认书

    public static final String YUSHIYAN_CONTROL_NO = "03"; //预实验记录表

    public static final String ROUTINE_EXTRACTION_RECORDS_NO = "04"; //常规提取记录

    public static final String SEMINAL_EXTRACTION_NO = "05"; //精斑提取记录

    public static final String AMPLIFIED_RECORDS_NO = "06"; //扩增记录

    public static final String ELECTROPHORETIC_RECORDING_NO = "07"; //电泳记录

    public static final String WAREHOUSE_RECEIPT_NO = "08"; //入库单

    public static final String LABORATORY_RECORD_COVER = "09"; //实验室记录封皮

    public static final String ELECTROPHORETIC_AUTOMATIC_NO = "10"; //自动提取页面，生成电泳分离记录






}
