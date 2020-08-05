package com.bazl.clims.utils;

import com.alibaba.fastjson.JSON;
import com.bazl.clims.common.Constants;
import com.bazl.clims.common.TransferConstants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/6/9.
 */
public class TransferHelper {


    /**
     * 日志对象
     */
    private static final Logger logger = LoggerFactory.getLogger(TransferHelper.class);

    /**
     * 将案件性质转换为oldlims对应案件性质
     * @param caseProperty
     * @return
     */
    public static String convertToCaseProperty(String caseProperty){
        if (Constants.CASE_PROPERTY_01.equals(caseProperty)) {//凶杀
            caseProperty = TransferConstants.CASE_PROPERTY_OLD_XIONGSHA;
        } else if (Constants.CASE_PROPERTY_02.equals(caseProperty)) {//伤害
            caseProperty = TransferConstants.CASE_PROPERTY_OLD_SHANGHAI;
        } else if (Constants.CASE_PROPERTY_03.equals(caseProperty)) {//其他盗窃
            caseProperty = TransferConstants.CASE_PROPERTY_OLD_QITADAOQIE;
        } else if (Constants.CASE_PROPERTY_04.equals(caseProperty)) {//抢夺抢劫
            caseProperty = TransferConstants.CASE_PROPERTY_OLD_QIANGDUOQIANGJIE;
        } else if (Constants.CASE_PROPERTY_05.equals(caseProperty)) {//强奸
            caseProperty = TransferConstants.CASE_PROPERTY_OLD_QIANGJIAN;
        } else if (Constants.CASE_PROPERTY_06.equals(caseProperty)) {//非正常死亡
            caseProperty = TransferConstants.CASE_PROPERTY_OLD_QITA;
        } else if (Constants.CASE_PROPERTY_07.equals(caseProperty)) {//伤害致死
            caseProperty = TransferConstants.CASE_PROPERTY_OLD_SHANGHAIZHISI;
        } else if (Constants.CASE_PROPERTY_08.equals(caseProperty)) {//治安
            caseProperty = TransferConstants.CASE_PROPERTY_OLD_ZHIAN;
        } else if (Constants.CASE_PROPERTY_09.equals(caseProperty)) {//走失人口
            caseProperty = TransferConstants.CASE_PROPERTY_OLD_ZOUSHIRENKOU;
        } else if (Constants.CASE_PROPERTY_10.equals(caseProperty)) {//打拐
            caseProperty = TransferConstants.CASE_PROPERTY_OLD_DAGUAI;
        } else if (Constants.CASE_PROPERTY_11.equals(caseProperty)) {//交通事故
            caseProperty = TransferConstants.CASE_PROPERTY_OLD_JIAOTONGSHIGU;
        } else if (Constants.CASE_PROPERTY_12.equals(caseProperty)) {//尸源认定
            caseProperty = TransferConstants.CASE_PROPERTY_OLD_SHIYUANRENDING;
        } else if (Constants.CASE_PROPERTY_13.equals(caseProperty)) {//入室盗窃
            caseProperty = TransferConstants.CASE_PROPERTY_OLD_RUSHIDAOQIE;
        } else if (Constants.CASE_PROPERTY_14.equals(caseProperty)) {//盗窃机动车
            caseProperty = TransferConstants.CASE_PROPERTY_OLD_DAOQIEJIDONGCHE;
        } else if (Constants.CASE_PROPERTY_15.equals(caseProperty)) {//盗窃车内财物
            caseProperty = TransferConstants.CASE_PROPERTY_OLD_DAOQIECHENEICAIWU;
        } else if (Constants.CASE_PROPERTY_16.equals(caseProperty)) {//盗抢工地
            caseProperty = TransferConstants.CASE_PROPERTY_OLD_DAOQIEGONGDI;
        } else if (Constants.CASE_PROPERTY_17.equals(caseProperty)) {//爬楼钻窗
            caseProperty = TransferConstants.CASE_PROPERTY_OLD_PALOUZUANCHUANG;
        } else if (Constants.CASE_PROPERTY_18.equals(caseProperty)) {//盗窃保险柜
            caseProperty = TransferConstants.CASE_PROPERTY_OLD_DAOQIEBAOXIANGUI;
        } else if (Constants.CASE_PROPERTY_19.equals(caseProperty)) {//亲缘
            caseProperty = TransferConstants.CASE_PROPERTY_OLD_QITA;
        } else if (Constants.CASE_PROPERTY_20.equals(caseProperty)) {//其它
            caseProperty = TransferConstants.CASE_PROPERTY_OLD_QITA;
        }else if (Constants.CASE_PROPERTY_21.equals(caseProperty)) {//诈骗
            caseProperty = TransferConstants.CASE_PROPERTY_OLD_ZHAPIAN;
        }else if (Constants.CASE_PROPERTY_22.equals(caseProperty)) {//抢夺
            caseProperty = TransferConstants.CASE_PROPERTY_OLD_QIANGDUOQIANGJIE;
        }

        return caseProperty;
    }


    /**
     * 将案件类型转换为oldlims对应案件类型
     * @param caseType
     * @return
     */
    public static String convertToCaseType(String caseType){
        if(Constants.CASE_TYPE_01.equals(caseType)) {
            caseType = TransferConstants.CASE_TYPE_CRIMINAL_OLD_2;
        }

        return caseType;
    }


    /**
     * 将入库类型转换为oldlims对应入库类型
     * @param instoredType
     * @return
     */
    public static Integer convertToInstoredType(String instoredType){
        if(Constants.INSTORED_TYPE_01.equals(instoredType)) {//现场物证
            instoredType = TransferConstants.INSTORE_SAMPLE_TYPE_EVIDENCE_OLD;
        }else if (Constants.INSTORED_TYPE_02.equals(instoredType)) {//混合物证
            instoredType = TransferConstants.INSTORE_SAMPLE_TYPE_MIX_EVIDENCE_OLD;
        }else if (Constants.INSTORED_TYPE_03.equals(instoredType)) {//违法犯罪人员
            instoredType = TransferConstants.INSTORE_SAMPLE_TYPE_OFFENDER_OLD;
        }else if (Constants.INSTORED_TYPE_04.equals(instoredType)) {//嫌疑人
            instoredType = TransferConstants.INSTORE_SAMPLE_TYPE_SUSPECT_OLD;
        }else if (Constants.INSTORED_TYPE_05.equals(instoredType)) {//受害人
            instoredType = TransferConstants.INSTORE_SAMPLE_TYPE_VICTIM_OLD;
        }else if (Constants.INSTORED_TYPE_06.equals(instoredType)) {//失踪人口
            instoredType = TransferConstants.INSTORE_SAMPLE_TYPE_MISSING_OLD;
        }else if (Constants.INSTORED_TYPE_07.equals(instoredType)) {//无名尸
            instoredType = TransferConstants.INSTORE_SAMPLE_TYPE_UNKNOWN_OLD;
        }else if (Constants.INSTORED_TYPE_08.equals(instoredType)) {//嫌疑人亲属
            instoredType = TransferConstants.INSTORE_SAMPLE_TYPE_SUSPECT_RELATIVE_OLD;
        }else if (Constants.INSTORED_TYPE_09.equals(instoredType)) {//受害人亲属
            instoredType = TransferConstants.INSTORE_SAMPLE_TYPE_VICTIM_RELATIVE_OLD;
        }else if (Constants.INSTORED_TYPE_10.equals(instoredType)) {//失踪人口亲属
            instoredType = TransferConstants.INSTORE_SAMPLE_TYPE_MISSING_RELATIVE_OLD;
        }else if (Constants.INSTORED_TYPE_11.equals(instoredType)) {//基础库
            instoredType = TransferConstants.INSTORE_SAMPLE_TYPE_BASE_OLD;
        }else if (Constants.INSTORED_TYPE_12.equals(instoredType)) {//YSTR
            instoredType = TransferConstants.INSTORE_SAMPLE_TYPE_YSTR_OLD;
        }

        return Integer.parseInt(instoredType);
    }

    public static String convertToComparisonCategory(String comparisonCategory) {
        String groupType = null;
        if (Constants.COMPARISON_CATEGORY_01.equals(comparisonCategory)) {
            groupType = "all";
        }else if (Constants.COMPARISON_CATEGORY_02.equals(comparisonCategory)) {
            groupType = "e";
        }else if (Constants.COMPARISON_CATEGORY_03.equals(comparisonCategory)) {
            groupType = "p";
        }else if (Constants.COMPARISON_CATEGORY_04.equals(comparisonCategory)) {
            groupType = "pe";
        }else if (Constants.COMPARISON_CATEGORY_05.equals(comparisonCategory)) {
            groupType = "all";
        }
        return groupType;
    }

    /**
     * 入本地库比中类型
     * @param role
     * @return
     */
    public static String convertToMatchType(String role) {
        if (Constants.RELATION_TYPE_01.equals(role) || Constants.RELATION_TYPE_02.equals(role)
                || Constants.RELATION_TYPE_03.equals(role) || Constants.RELATION_TYPE_04.equals(role)) {
            //配偶
            role = "2";
        }else {
            //子女
            role = "3";
        }

        return role;
    }
    /**
     * 将基因型信息转换成oldlims格式
     * @param geneInfo
     * @return
     */
    public static String convertToGeneInfo(String geneInfo) {
        String markerallels = "";
        Map<String, List<String>> srcResult = null;
        try {
            if (StringUtils.isNotBlank(geneInfo)) {
                srcResult = (Map) JSON.parse(geneInfo);

                if (srcResult != null) {
                    for (Map.Entry<String, List<String>> srcEntry : srcResult.entrySet()) {
                        String markerName = srcEntry.getKey();
                        List<String> srcAlleleList = srcEntry.getValue();
                        markerallels = markerallels + "[" + markerName + "]";
                        markerallels = markerallels + getAllele(srcAlleleList);
                    }
                }
            }
        } catch (Exception ex) {
            logger.error("解析比对基因分型信息错误!", ex);
            return null;
        }
        return markerallels;
    }

    public static String getAllele(List<String> srcAlleleList) {
        String alleleLabel = "";

        if (ListUtils.isNotNullAndEmptyList(srcAlleleList)) {
            for (String allele : srcAlleleList) {
                alleleLabel += "<" + allele + ",1,1,1>";
            }
        }

        return alleleLabel;
    }
    /**
     * 将案件状态转换为国家库对应案件状态
     * @param limsVersion
     * @param caseStatus
     * @param caseInfoConfigs
     * @return
     */
    /*public static String convertToNationCaseStatus(String limsVersion, String caseStatus, CaseInfoConfigs caseInfoConfigs){
        if(StringUtils.isBlank(caseStatus)){
            return caseInfoConfigs.getCaseStatusDefault();
        }

        if(limsVersion.equals(TransferConstants.LIMS_VERSION_OLD)){
            if(caseStatus.equals(TransferConstants.CASE_STATUS_OLD_PENDING)
                    || caseStatus.equals(TransferConstants.CASE_STATUS_OLD_ACCEPTED)
                    || caseStatus.equals(TransferConstants.CASE_STATUS_OLD_NOT_RECEIVE)
                    || caseStatus.equals(TransferConstants.CASE_STATUS_OLD_RECEIVED)
                    || caseStatus.equals(TransferConstants.CASE_STATUS_OLD_REFUSED)){
                return caseInfoConfigs.getCaseStatusNotSolved();
            }else if(caseStatus.equals(TransferConstants.CASE_STATUS_OLD_FINISHED)){
                return caseInfoConfigs.getCaseStatusMatched();
            }
        }else{
            if(caseStatus.equals(TransferConstants.CASE_STATUS_NEW_PENDING)
                    || caseStatus.equals(TransferConstants.CASE_STATUS_NEW_ACCEPTED)
                    || caseStatus.equals(TransferConstants.CASE_STATUS_NEW_REFUSED)){
                return caseInfoConfigs.getCaseStatusNotSolved();
            }else if(caseStatus.equals(TransferConstants.CASE_STATUS_NEW_FINISHED)){
                return caseInfoConfigs.getCaseStatusMatched();
            }
        }

        return caseInfoConfigs.getCaseStatusDefault();
    }


    *//**
     * 将案件级别转换为国家库对应案件级别
     * @param limsVersion
     * @param caseLevel
     * @param caseInfoConfigs
     * @return
     *//*
    public static String convertToNationCaseLevel(String limsVersion, String caseLevel, CaseInfoConfigs caseInfoConfigs){
        if(StringUtils.isBlank(caseLevel)){
            return caseInfoConfigs.getCaseLevelDefault();
        }

        if(limsVersion.equals(TransferConstants.LIMS_VERSION_SHUNYI)){
            if(caseLevel.equals(TransferConstants.CASE_LEVEL_01)){
                return caseInfoConfigs.getCaseLevelSerious();
            }else if(caseLevel.equals(TransferConstants.CASE_LEVEL_02)){
                return caseInfoConfigs.getCaseLevelLarge();
            }else if(caseLevel.equals(TransferConstants.CASE_LEVEL_03)){
                return caseInfoConfigs.getCaseLevelMajor();
            }else if(caseLevel.equals(TransferConstants.CASE_LEVEL_04)){
                return caseInfoConfigs.getCaseLevelGeneral();
            }else if(caseLevel.equals(TransferConstants.CASE_LEVEL_05)){
                return caseInfoConfigs.getCaseLevelSlight();
            }else if(caseLevel.equals(TransferConstants.CASE_LEVEL_06)){
                return caseInfoConfigs.getCaseLevelOther();
            }
        }

        return caseInfoConfigs.getCaseLevelDefault();
    }
*/
    /**
     * 判断当前样本入库类型是否为失踪人口亲属
     * @param limsVersion
     * @param sampleType
     * @return
     */
    public static boolean isSampleTypeMissingPeople(String limsVersion, String sampleType){
        if(limsVersion.equals(TransferConstants.LIMS_VERSION_OLD)){
            if(TransferConstants.INSTORE_SAMPLE_TYPE_MISSING_RELATIVE_OLD.equals(sampleType)){
                return true;
            }
        }else if (limsVersion.equals(TransferConstants.LIMS_VERSION_SHUNYI)) {
            if(TransferConstants.INSTORE_SAMPLE_TYPE_MISSING_RELATIVE_NEW.equals(sampleType)){
                return true;
            }
        }

        return false;
    }


    /**
     * 将委托类型转换为国家库对应委托类型
     * @param limsVersion
     * @param caseType
     * @param caseInfoConfigs
     * @return
     */
  /*  public static String convertToNationConsignmentType(String limsVersion, String caseType, CaseInfoConfigs caseInfoConfigs){
        if(StringUtils.isBlank(caseType)){
            return caseInfoConfigs.getConsignmentTypeDefault();
        }

        if(limsVersion.equals(TransferConstants.LIMS_VERSION_OLD)){
            if(caseType.equals(TransferConstants.CASE_TYPE_CRIMINAL_OLD_1)
                    || caseType.equals(TransferConstants.CASE_TYPE_CRIMINAL_OLD_2)
                    || caseType.equals(TransferConstants.CASE_TYPE_CIVIL_OLD_4)){
                return caseInfoConfigs.getConsignmentTypeCase();
            }else if(caseType.equals(TransferConstants.CASE_TYPE_NOT_CRIMINAL_OLD_3)
                    || caseType.equals(TransferConstants.CASE_TYPE_NOT_CRIMINAL_OLD_5)){
                return caseInfoConfigs.getConsignmentTypePerson();
            }
        }else if (limsVersion.equals(TransferConstants.LIMS_VERSION_SHUNYI)){
            if(caseType.equals(TransferConstants.CASE_TYPE_CRIMINAL_NEW_1)){
                return caseInfoConfigs.getConsignmentTypeCase();
            }
        }

        return caseInfoConfigs.getConsignmentTypeDefault();
    }

    *//**
     * 转换国家库证件类型
     * @param limsVersion
     * @param credentialType
     * @param caseInfoConfigs
     * @return
     *//*
    public static String convertToNationCredentialType(String limsVersion, String credentialType, CaseInfoConfigs caseInfoConfigs){
        if(StringUtils.isBlank(credentialType)){
            return caseInfoConfigs.getCredentialTypeDefault();
        }

        if(credentialType.equals("警官证")
                || credentialType.contains("警官")){
            return caseInfoConfigs.getCredentialTypePolicer();
        }


        return caseInfoConfigs.getCredentialTypeDefault();
    }

    *//**
     * 转换鉴定要求
     * @param limsVersion
     * @param identifyRequest
     * @return
     *//*
    public static String convertIdentifyRequest(String limsVersion, String identifyRequest){
        if(limsVersion.equals(TransferConstants.LIMS_VERSION_OLD)){
            if(identifyRequest.equals(TransferConstants.IDENTIFY_REQUIRED_SAME_OLD)){
                return TransferConstants.IDENTIFY_REQUEST_SAME_NAME;
            }else if(identifyRequest.equals(TransferConstants.IDENTIFY_REQUIRED_RELATIVE_OLD)){
                return TransferConstants.IDENTIFY_REQUEST_RELATIVE_NAME;
            }
        }else{
            if(identifyRequest.equals(TransferConstants.IDENTIFY_REQUIRED_SAME_NEW)){
                return TransferConstants.IDENTIFY_REQUEST_SAME_NAME;
            }else if(identifyRequest.equals(TransferConstants.IDENTIFY_REQUIRED_RELATIVE_NEW)){
                return TransferConstants.IDENTIFY_REQUEST_RELATIVE_NAME;
            }
        }

        return TransferConstants.IDENTIFY_REQUEST_SAME_NAME;
    }


    *//**
     * 转换物证承载类型
     * @param limsVersion
     * @param evidenceCarrierType
     * @param caseInfoConfigs
     * @return
     *//*
    public static String convertEvidenceCarrierType(String limsVersion, String evidenceCarrierType, CaseInfoConfigs caseInfoConfigs){
        if(StringUtils.isBlank(evidenceCarrierType)){
            return caseInfoConfigs.getEvidenceCarrierTypeDefault();
        }

        if(limsVersion.equals(TransferConstants.LIMS_VERSION_OLD)){
            if(evidenceCarrierType.equals(TransferConstants.SAMPLE_TYPE_BLOOD_OLD)){
                return caseInfoConfigs.getEvidenceCarrierTypeBlood();
            }else if(evidenceCarrierType.equals(TransferConstants.SAMPLE_TYPE_SALIVA_OLD)){
                return caseInfoConfigs.getEvidenceCarrierTypeSaliva();
            }else{
                return caseInfoConfigs.getEvidenceCarrierTypeOther();
            }
        }else{
            if(evidenceCarrierType.equals(TransferConstants.SAMPLE_TYPE_BLOOD_NEW)){
                return caseInfoConfigs.getEvidenceCarrierTypeBlood();
            }else if(evidenceCarrierType.equals(TransferConstants.SAMPLE_TYPE_SALIVA_NEW)){
                return caseInfoConfigs.getEvidenceCarrierTypeSaliva();
            }else{
                return caseInfoConfigs.getEvidenceCarrierTypeOther();
            }
        }
    }

    *//**
     * 转换性别
     * @param limsVersion
     * @param gender
     * @return
     *//*
    public static String convertNationGener(String limsVersion, String gender){
        if(limsVersion.equals(TransferConstants.LIMS_VERSION_SHUNYI)){
            if(TransferConstants.GENDER_01.equals(gender)){
                return TransferConstants.NATION_GENDER_MALE;
            }else if(TransferConstants.GENDER_02.equals(gender)){
                return TransferConstants.NATION_GENDER_FEMALE;
            }else if(TransferConstants.GENDER_03.equals(gender)){
                return TransferConstants.NATION_GENDER_UNKNOWN;
            }
        }

        return TransferConstants.NATION_GENDER_UNKNOWN;
    }

    *//**
     * 转换样本类型
     * @param limsVersion
     * @param sampleType
     * @param caseInfoConfigs
     * @return
     *//*
    public static String convertNationSampleType(String limsVersion, String sampleType, CaseInfoConfigs caseInfoConfigs){
        if(StringUtils.isBlank(sampleType)){
            return caseInfoConfigs.getSampleTypeDefault();
        }

        if(limsVersion.equals(TransferConstants.LIMS_VERSION_OLD)){
            if(sampleType.equals(TransferConstants.SAMPLE_TYPE_BLOOD_OLD)){
                return caseInfoConfigs.getSampleTypeBlood();
            }else if(sampleType.equals(TransferConstants.SAMPLE_TYPE_SALIVA_OLD)){
                return caseInfoConfigs.getSampleTypeSaliva();
            }else if(sampleType.equals(TransferConstants.SAMPLE_TYPE_CELLS_OLD)){
                return caseInfoConfigs.getSampleTypeCells();
            }else if(sampleType.equals(TransferConstants.SAMPLE_TYPE_SPERM_OLD)){
                return caseInfoConfigs.getSampleTypeSperm();
            }else if(sampleType.equals(TransferConstants.SAMPLE_TYPE_HARI_OLD)){
                return caseInfoConfigs.getSampleTypeHairs();
            }else if(sampleType.equals(TransferConstants.SAMPLE_TYPE_SKELETON_OLD)){
                return caseInfoConfigs.getSampleTypeSkeleton();
            }else{
                return caseInfoConfigs.getEvidenceCarrierTypeOther();
            }
        }else{
            if(sampleType.equals(TransferConstants.SAMPLE_TYPE_BLOOD_NEW)){
                return caseInfoConfigs.getSampleTypeBlood();
            }else if(sampleType.equals(TransferConstants.SAMPLE_TYPE_SALIVA_NEW)){
                return caseInfoConfigs.getSampleTypeSaliva();
            }else if(sampleType.equals(TransferConstants.SAMPLE_TYPE_CELLS_NEW)){
                return caseInfoConfigs.getSampleTypeCells();
            }else if(sampleType.equals(TransferConstants.SAMPLE_TYPE_SPERM_NEW)){
                return caseInfoConfigs.getSampleTypeSperm();
            }else if(sampleType.equals(TransferConstants.SAMPLE_TYPE_HARI_NEW)){
                return caseInfoConfigs.getSampleTypeHairs();
            }else if(sampleType.equals(TransferConstants.SAMPLE_TYPE_SKELETON_NEW)
                    || sampleType.equals(TransferConstants.SAMPLE_TYPE_TOOTH_NEW)){
                return caseInfoConfigs.getSampleTypeSkeleton();
            }else if(sampleType.equals(TransferConstants.SAMPLE_TYPE_TISSUE_NEW)){
                return caseInfoConfigs.getSampleTypeTissue();
            }else{
                return caseInfoConfigs.getEvidenceCarrierTypeOther();
            }
        }
    }

    public static final String convertNationSampleCategory(String limsVersion, String instoreSampleType){
        if(limsVersion.equals(TransferConstants.LIMS_VERSION_OLD)){
            if(TransferConstants.INSTORE_SAMPLE_TYPE_EVIDENCE_OLD.equals(instoreSampleType)
                    || TransferConstants.INSTORE_SAMPLE_TYPE_MIX_EVIDENCE_OLD.equals(instoreSampleType)){
                return TransferConstants.NATION_SAMPLE_CATEGORY_EVIDENCE;
            }else if(TransferConstants.INSTORE_SAMPLE_TYPE_OFFENDER_OLD.equals(instoreSampleType)){
                return TransferConstants.NATION_SAMPLE_CATEGORY_OFFENDER;
            }else if(TransferConstants.INSTORE_SAMPLE_TYPE_SUSPECT_OLD.equals(instoreSampleType)){
                return TransferConstants.NATION_SAMPLE_CATEGORY_SUSPECT;
            }else if(TransferConstants.INSTORE_SAMPLE_TYPE_VICTIM_OLD.equals(instoreSampleType)){
                return TransferConstants.NATION_SAMPLE_CATEGORY_VICTIM;
            }else if(TransferConstants.INSTORE_SAMPLE_TYPE_MISSING_OLD.equals(instoreSampleType)){
                return TransferConstants.NATION_SAMPLE_CATEGORY_MISSING;
            }else if (TransferConstants.INSTORE_SAMPLE_TYPE_UNKNOWN_OLD.equals(instoreSampleType)) {
                return TransferConstants.NATION_SAMPLE_CATEGORY_KNOWN;
            } else if(TransferConstants.INSTORE_SAMPLE_TYPE_SUSPECT_RELATIVE_OLD.equals(instoreSampleType)
                    || TransferConstants.INSTORE_SAMPLE_TYPE_VICTIM_RELATIVE_OLD.equals(instoreSampleType)
                    || TransferConstants.INSTORE_SAMPLE_TYPE_MISSING_RELATIVE_OLD.equals(instoreSampleType)){
                return TransferConstants.NATION_SAMPLE_CATEGORY_CASE_PERSONS;
            }else if(TransferConstants.INSTORE_SAMPLE_TYPE_YSTR_OLD.equals(instoreSampleType)){
                return TransferConstants.NATION_SAMPLE_CATEGORY_EVIDENCE;
            }
        }else if (limsVersion.equals(TransferConstants.LIMS_VERSION_SHUNYI)) {
            if(TransferConstants.INSTORE_SAMPLE_TYPE_EVIDENCE_NEW.equals(instoreSampleType)
                    || TransferConstants.INSTORE_SAMPLE_TYPE_MIX_EVIDENCE_NEW.equals(instoreSampleType)){
                return TransferConstants.NATION_SAMPLE_CATEGORY_EVIDENCE;
            }else if(TransferConstants.INSTORE_SAMPLE_TYPE_OFFENDER_NEW.equals(instoreSampleType)){
                return TransferConstants.NATION_SAMPLE_CATEGORY_OFFENDER;
            }else if(TransferConstants.INSTORE_SAMPLE_TYPE_SUSPECT_NEW.equals(instoreSampleType)){
                return TransferConstants.NATION_SAMPLE_CATEGORY_SUSPECT;
            }else if(TransferConstants.INSTORE_SAMPLE_TYPE_VICTIM_NEW.equals(instoreSampleType)){
                return TransferConstants.NATION_SAMPLE_CATEGORY_VICTIM;
            }else if(TransferConstants.INSTORE_SAMPLE_TYPE_MISSING_NEW.equals(instoreSampleType)){
                return TransferConstants.NATION_SAMPLE_CATEGORY_MISSING;
            }else if (TransferConstants.INSTORE_SAMPLE_TYPE_UNKNOWN_NEW.equals(instoreSampleType)) {
                return TransferConstants.NATION_SAMPLE_CATEGORY_KNOWN;
            } else if(TransferConstants.INSTORE_SAMPLE_TYPE_SUSPECT_RELATIVE_NEW.equals(instoreSampleType)
                    || TransferConstants.INSTORE_SAMPLE_TYPE_VICTIM_RELATIVE_NEW.equals(instoreSampleType)
                    || TransferConstants.INSTORE_SAMPLE_TYPE_MISSING_RELATIVE_NEW.equals(instoreSampleType)){
                return TransferConstants.NATION_SAMPLE_CATEGORY_CASE_PERSONS;
            }else if(TransferConstants.INSTORE_SAMPLE_TYPE_YSTR_NEW.equals(instoreSampleType)){
                return TransferConstants.NATION_SAMPLE_CATEGORY_EVIDENCE;
            }
        }

        return TransferConstants.NATION_SAMPLE_CATEGORY_EVIDENCE;
    }

    public static String getXckyCallbackUrlByServerNoAndDelegateOrgCode(String delegateOrgCode, TransferNationParamsConfig transferNationParamsConfig){
        if(TransferConstants.LAB_SERVER_NO_BEIJING.equals(transferNationParamsConfig.getLabServerNo())
                || TransferConstants.LAB_SERVER_NO_FYZHX.equals(transferNationParamsConfig.getLabServerNo())){
            if(delegateOrgCode.equals(TransferConstants.DELEGATE_ORG_DCH)
                    || (delegateOrgCode.length() > TransferConstants.DELEGATE_ORG_DCH.length() && delegateOrgCode.contains(TransferConstants.DELEGATE_ORG_DCH))){
                return transferNationParamsConfig.getXckyCallbackURL_DCH();
            }else if(delegateOrgCode.equals(TransferConstants.DELEGATE_ORG_XCH)
                    || (delegateOrgCode.length() > TransferConstants.DELEGATE_ORG_XCH.length() && delegateOrgCode.contains(TransferConstants.DELEGATE_ORG_XCH))){
                return transferNationParamsConfig.getXckyCallbackURL_XCH();
            }else if(delegateOrgCode.equals(TransferConstants.DELEGATE_ORG_CHY)
                    || (delegateOrgCode.length() > TransferConstants.DELEGATE_ORG_CHY.length() && delegateOrgCode.contains(TransferConstants.DELEGATE_ORG_CHY))){
                return transferNationParamsConfig.getXckyCallbackURL_CHY();
            }else if(delegateOrgCode.equals(TransferConstants.DELEGATE_ORG_FT)
                    || (delegateOrgCode.length() > TransferConstants.DELEGATE_ORG_FT.length() && delegateOrgCode.contains(TransferConstants.DELEGATE_ORG_FT))){
                return transferNationParamsConfig.getXckyCallbackURL_FT();
            }else if(delegateOrgCode.equals(TransferConstants.DELEGATE_ORG_SHJSH)
                    || (delegateOrgCode.length() > TransferConstants.DELEGATE_ORG_SHJSH.length() && delegateOrgCode.contains(TransferConstants.DELEGATE_ORG_SHJSH))){
                return transferNationParamsConfig.getXckyCallbackURL_SHJSH();
            }else if(delegateOrgCode.equals(TransferConstants.DELEGATE_ORG_HD)
                    || (delegateOrgCode.length() > TransferConstants.DELEGATE_ORG_HD.length() && delegateOrgCode.contains(TransferConstants.DELEGATE_ORG_HD))){
                return transferNationParamsConfig.getXckyCallbackURL_HD();
            }else if(delegateOrgCode.equals(TransferConstants.DELEGATE_ORG_MTG)
                    || (delegateOrgCode.length() > TransferConstants.DELEGATE_ORG_MTG.length() && delegateOrgCode.contains(TransferConstants.DELEGATE_ORG_MTG))){
                return transferNationParamsConfig.getXckyCallbackURL_MTG();
            }else if(delegateOrgCode.equals(TransferConstants.DELEGATE_ORG_FSH)
                    || (delegateOrgCode.length() > TransferConstants.DELEGATE_ORG_FSH.length() && delegateOrgCode.contains(TransferConstants.DELEGATE_ORG_FSH))){
                return transferNationParamsConfig.getXckyCallbackURL_FSH();
            }else if(delegateOrgCode.equals(TransferConstants.DELEGATE_ORG_TZ)
                    || (delegateOrgCode.length() > TransferConstants.DELEGATE_ORG_TZ.length() && delegateOrgCode.contains(TransferConstants.DELEGATE_ORG_TZ))){
                return transferNationParamsConfig.getXckyCallbackURL_TZH();
            }else if(delegateOrgCode.equals(TransferConstants.DELEGATE_ORG_SHY)
                    || (delegateOrgCode.length() > TransferConstants.DELEGATE_ORG_SHY.length() && delegateOrgCode.contains(TransferConstants.DELEGATE_ORG_SHY))){
                return transferNationParamsConfig.getXckyCallbackURL_SHY();
            }else if(delegateOrgCode.equals(TransferConstants.DELEGATE_ORG_CHP)
                    || (delegateOrgCode.length() > TransferConstants.DELEGATE_ORG_CHP.length() && delegateOrgCode.contains(TransferConstants.DELEGATE_ORG_CHP))){
                return transferNationParamsConfig.getXckyCallbackURL_CHP();
            }else if(delegateOrgCode.equals(TransferConstants.DELEGATE_ORG_DX)
                    || (delegateOrgCode.length() > TransferConstants.DELEGATE_ORG_DX.length() && delegateOrgCode.contains(TransferConstants.DELEGATE_ORG_DX))){
                return transferNationParamsConfig.getXckyCallbackURL_DX();
            }else if(delegateOrgCode.equals(TransferConstants.DELEGATE_ORG_HR)
                    || (delegateOrgCode.length() > TransferConstants.DELEGATE_ORG_HR.length() && delegateOrgCode.contains(TransferConstants.DELEGATE_ORG_HR))){
                return transferNationParamsConfig.getXckyCallbackURL_HR();
            }else if(delegateOrgCode.equals(TransferConstants.DELEGATE_ORG_PG)
                    || (delegateOrgCode.length() > TransferConstants.DELEGATE_ORG_PG.length() && delegateOrgCode.contains(TransferConstants.DELEGATE_ORG_PG))){
                return transferNationParamsConfig.getXckyCallbackURL_PG();
            }else if(delegateOrgCode.equals(TransferConstants.DELEGATE_ORG_MY)
                    || (delegateOrgCode.length() > TransferConstants.DELEGATE_ORG_MY.length() && delegateOrgCode.contains(TransferConstants.DELEGATE_ORG_MY))){
                return transferNationParamsConfig.getXckyCallbackURL_MY();
            }else if(delegateOrgCode.equals(TransferConstants.DELEGATE_ORG_YQ)
                    || (delegateOrgCode.length() > TransferConstants.DELEGATE_ORG_YQ.length() && delegateOrgCode.contains(TransferConstants.DELEGATE_ORG_YQ))){
                return transferNationParamsConfig.getXckyCallbackURL_YQ();
            }

            return transferNationParamsConfig.getXckyCallbackURL_BEIJING();
        }else if(TransferConstants.LAB_SERVER_NO_DCH.equals(transferNationParamsConfig.getLabServerNo())){
            return transferNationParamsConfig.getXckyCallbackURL_DCH();
        }else if(TransferConstants.LAB_SERVER_NO_XCH.equals(transferNationParamsConfig.getLabServerNo())){
            return transferNationParamsConfig.getXckyCallbackURL_XCH();
        }else if(TransferConstants.LAB_SERVER_NO_CHY.equals(transferNationParamsConfig.getLabServerNo())){
            return transferNationParamsConfig.getXckyCallbackURL_CHY();
        }else if(TransferConstants.LAB_SERVER_NO_FT.equals(transferNationParamsConfig.getLabServerNo())){
            return transferNationParamsConfig.getXckyCallbackURL_FT();
        }else if(TransferConstants.LAB_SERVER_NO_SHJSH.equals(transferNationParamsConfig.getLabServerNo())){
            return transferNationParamsConfig.getXckyCallbackURL_SHJSH();
        }else if(TransferConstants.LAB_SERVER_NO_HD.equals(transferNationParamsConfig.getLabServerNo())){
            return transferNationParamsConfig.getXckyCallbackURL_HD();
        }else if(TransferConstants.LAB_SERVER_NO_CHP.equals(transferNationParamsConfig.getLabServerNo())){
            return transferNationParamsConfig.getXckyCallbackURL_CHP();
        }else if(TransferConstants.LAB_SERVER_NO_DX.equals(transferNationParamsConfig.getLabServerNo())){
            return transferNationParamsConfig.getXckyCallbackURL_DX();
        }else if(TransferConstants.LAB_SERVER_NO_TZ.equals(transferNationParamsConfig.getLabServerNo())){
            return transferNationParamsConfig.getXckyCallbackURL_TZH();
        }else if(TransferConstants.LAB_SERVER_NO_SHY.equals(transferNationParamsConfig.getLabServerNo())){
            return transferNationParamsConfig.getXckyCallbackURL_SHY();
        }

        return transferNationParamsConfig.getXckyCallbackURL_BEIJING();
    }*/
}
