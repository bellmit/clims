package com.bazl.clims.compare;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.bazl.clims.common.Constants;
import com.bazl.clims.datamodel.CaseCompareResultGroup;
import com.bazl.clims.model.po.CompareRelativeResult;
import com.bazl.clims.model.po.CompareSameResult;
import com.bazl.clims.model.po.LimsSampleGene;
import com.bazl.clims.model.po.MatchAuditedGene;
import com.bazl.clims.utils.CommonUtils;
import com.bazl.clims.utils.DataFormat;
import com.bazl.clims.utils.ListUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * Created by Sun on 2019/4/1.
 */
@Service
public class GeneSameCompareUtil {

    //入库匹配下限
    @Value("${minSameCount}")
    private int minSameCount;
    //本案匹配下限
    @Value("${minSameCaseCount}")
    private int minSameCaseCount;
    //亲缘匹配下限
    @Value("${minSameRelationCount}")
    private int minSameRelationCount;
    //混合匹配下限
    @Value("${minSameMixCount}")
    private int minSameMixCount;
    //容差
    @Value("${halfDiffCount}")
    private int halfDiffCount;

    @Autowired
    MarkerLRCalculation markerLRCalculation;
    @Autowired
    GeneSameCompareUtil geneSameCompareUtil;
    /**
     * 日志对象
     */
    private static final Logger logger = LoggerFactory.getLogger(GeneSameCompareUtil.class);

    /**
     * 同型比对
     *
     * @param srcGeneInfo 待比对基因型
     * @param tarGeneInfo 被比样本基因型
     * @return
     */
    public Map<String, Object> compare(String srcGeneInfo, String tarGeneInfo, int matchLimit) {
        Map<String, List<String>> srcResult = null;
        try {
            srcResult = (Map) JSON.parse(srcGeneInfo, Feature.OrderedField);
//            srcResult = removeAmel(srcResult);
        } catch (Exception ex) {
            logger.error("解析比对基因分型信息错误!", ex);
            return null;
        }
        Map<String, List<String>> tarResult = null;
        try {
            tarResult = (Map) JSON.parse(tarGeneInfo, Feature.OrderedField);
//            tarResult = removeAmel(tarResult);
        } catch (Exception ex) {
            logger.error("解析比对基因分型信息错误！ ", ex);
            return null;
        }
        if (srcResult == null || tarResult == null) {
            return null;
        }
        return compareResult(srcResult, tarResult, matchLimit);
    }

    /**
     * 删除性别基因座
     *
     * @param map
     * @return
     */
    private Map<String, List<String>> removeAmel(Map<String, List<String>> map) {
        if (map != null) {
            if (map.get("Am") != null) {
                map.remove("Am");
            }
            if (map.get("Amel") != null) {
                map.remove("Amel");
            }
            if (map.get("amel") != null) {
                map.remove("amel");
            }
            if (map.get("AMEL") != null) {
                map.remove("AMEL");
            }
        }
        return map;
    }

    /**
     * @param srcGeneMap 待比对基因型
     * @param tarGeneMap 被比样本基因型
     * @return
     */
    public Map<String, Object> compareResult(Map<String, List<String>> srcGeneMap, Map<String, List<String>> tarGeneMap, int matchLimit) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        if (matchLimit == 0) {
            matchLimit = minSameCaseCount;
        }

        int sameCount = 0;  /*比中数*/
        int diffCount = 0;  /*容差数*/
        Double probability = 1.0;

        Map<String, List<String>> sameGeneMap = new HashMap<>();
        Map<String, String> sameGeneLRMap = new HashMap<>();
        List<Map<String, Object>> resultList = new ArrayList<>();
        //进行循环比对
        for (Map.Entry<String, List<String>> srcEntry : srcGeneMap.entrySet()) {
            String markerName = srcEntry.getKey();
            List<String> srcAlleleList = srcEntry.getValue();
            List<String> tarAlleleList = tarGeneMap.get(markerName);

            if (ListUtils.isNotNullAndEmptyList(srcAlleleList) && ListUtils.isNotNullAndEmptyList(tarAlleleList)) {
                String allele1 = "";
                String allele2 = "";
                String geneStr1 = "";
                String geneStr2 = "";
                if (srcAlleleList.size() == 1) {
                    allele1 = srcAlleleList.get(0).toString();
                    allele2 = srcAlleleList.get(0).toString();
                } else if (srcAlleleList.size() > 1) {
                    allele1 = srcAlleleList.get(0).toString();
                    allele2 = srcAlleleList.get(1).toString();
                }

                if (StringUtils.isNotBlank(allele1) && StringUtils.isNotBlank(allele2)) {
                    if (allele1.equals(allele2)) {
                        geneStr1 = allele1;
                    } else {
                        geneStr1 = allele1 + "/" + allele2;
                    }
                }

                if (tarAlleleList.size() == 1) {
                    geneStr2 = tarAlleleList.get(0).toString();
                } else if (tarAlleleList.size() > 1) {
                    if (StringUtils.isNotBlank(tarAlleleList.get(0).toString()) && StringUtils.isNotBlank(tarAlleleList.get(1).toString())) {
                        if (tarAlleleList.get(0).toString().equals(tarAlleleList.get(1).toString())) {
                            geneStr2 = tarAlleleList.get(0).toString();
                        } else {
                            geneStr2 = tarAlleleList.get(0).toString() + "/" + tarAlleleList.get(1).toString();
                        }
                    }
                }

                Map<String, Object> map = new LinkedHashMap<>();

                Double alleleFreqs = Double.NaN;
                if (srcAlleleList.size() == tarAlleleList.size() && geneStr1.equals(geneStr2)) {
                    sameCount++;
                    sameGeneMap.put(markerName, srcAlleleList);

                    alleleFreqs = markerLRCalculation.calculateSingleMarkerLR(null, markerName, allele1, allele2);
                    if (!Double.isNaN(alleleFreqs)) {
                        probability = probability * alleleFreqs;
                        sameGeneLRMap.put(markerName, alleleFreqs.toString());
                    }

                } else {
                    diffCount++;
                    map.put("type", "abnormal");
                }

                map.put("markerName", markerName);
                map.put("geneStr1", geneStr1);
                map.put("geneStr2", geneStr2);
                map.put("alleleFreqs", String.format("%.3f", alleleFreqs));
                resultList.add(map);
            }
        }

        if (sameGeneLRMap != null) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                String geneLRStr = mapper.writeValueAsString(sameGeneLRMap);
                resultMap.put("geneLRStr", geneLRStr);
            } catch (JsonProcessingException e) {
                logger.error("转换基因分型比中频率信息错误！", e);
                e.printStackTrace();
            }
        }
        resultMap.put("resultList", resultList);
        resultMap.put("sameCount", sameCount);
        resultMap.put("diffCount", diffCount);
        resultMap.put("probability", probability);
        if (sameCount < matchLimit) {
            resultMap.put("matched", Boolean.FALSE);
        } else {
            resultMap.put("matched", Boolean.TRUE);
        }
        return resultMap;
    }

    /**
     * 同一比对分组
     *
     * @param personAuditedGeneList
     * @param evidenceAuditedGeneList
     * @return
     */
    public List<CaseCompareResultGroup> doCompare(List<MatchAuditedGene> personAuditedGeneList, List<MatchAuditedGene> evidenceAuditedGeneList, String referenceType, int matchLimit) {
        List<CaseCompareResultGroup> groupList = new ArrayList<>();

        int count = 0;
        if (referenceType.equals(Constants.REFERENCE_TYPE_EVIDENCE)) {
            count = 1;
        }

        MatchAuditedGene s1 = null;
        MatchAuditedGene s2 = null;
        List<MatchAuditedGene> matchedList = null;
        Set<String> sampleIdSet = new HashSet<>();
        for (int i = 0; i < personAuditedGeneList.size(); i++) {
            matchedList = new ArrayList<>();
            s1 = personAuditedGeneList.get(i);
            String sampleId1 = s1.getSampleId();
            if (sampleIdSet.contains(sampleId1)) {
                continue;
            }
            for (int j = count; j < evidenceAuditedGeneList.size(); j++) {
                s2 = evidenceAuditedGeneList.get(j);
                String sampleId2 = s2.getSampleId();
                if (StringUtils.isNotBlank(sampleId1) && StringUtils.isNotBlank(sampleId2) && !sampleId1.equals(sampleId2)) {
                    Map<String, Object> result = compareGeneInfo(s1, s2, matchLimit);
                    if ((boolean) result.get("matched")) {
                        Double fProb = (Double) result.get("probability");
                        String str = DataFormat.formatDecimal(fProb == null ? 0.0 : fProb, 3, 1, true);
                        s2.setTotalProbability(str);
                        s2.setSameCount(String.valueOf((Integer) result.get("sameCount")));
                        s2.setDiffCount(String.valueOf((Integer) result.get("diffCount")));
                        matchedList.add(s2);
                        sampleIdSet.add(sampleId2);
                    }
                }
            }

            if (matchedList.size() > 0) {
                CaseCompareResultGroup group = new CaseCompareResultGroup();
                matchedList.add(0, s1);

                group.setGroupId(i + 1);
                group.setReferenceType(referenceType);
                group.setMatchedSampleList(matchedList);
                groupList.add(group);
            }
        }

        return groupList;
    }

    public Map<String, Object> compareGeneInfo(MatchAuditedGene s1, MatchAuditedGene s2, int matchLimit) {
        String geneStr1 = s1.getGeneInfo();
        String geneStr2 = s2.getGeneInfo();
        if (CommonUtils.contain(s1.getPanelName(), "identifiler")) {
            geneStr1 = geneSameCompareUtil.getGeneInfoList(Constants.IdentifilerPlusList, s1.getGeneInfo());
        }
        if (CommonUtils.contain(s2.getPanelName(), "identifiler")) {
            geneStr2 = geneSameCompareUtil.getGeneInfoList(Constants.IdentifilerPlusList, s2.getGeneInfo());
        }

        if (matchLimit == 0) {
            matchLimit = minSameCaseCount;
        }

        Map<String, Object> geneDetail = compare(geneStr1, geneStr2, matchLimit);

        if (geneDetail.isEmpty()) {
            return null;
        }

        return geneDetail;
    }

    public ModelAndView getInitData() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("minSameCount", minSameCount);
        modelAndView.addObject("minSameCaseCount", minSameCaseCount);
        modelAndView.addObject("minSameMixCount", minSameMixCount);
        modelAndView.addObject("minSameRelationCount", minSameRelationCount);
        modelAndView.addObject("halfDiffCount", halfDiffCount);
        return modelAndView;
    }

    /**
     * 获取基因座
     *
     * @param geneInfo
     * @return
     */
    public List<String> getMarker(String geneInfo) {
        Map<String, List<String>> srcResult = null;
        try {
            srcResult = (Map) JSON.parse(geneInfo, Feature.OrderedField);
        } catch (Exception ex) {
            logger.error("解析比对基因分型信息错误!", ex);
            return null;
        }
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, List<String>> srcEntry : srcResult.entrySet()) {
            String markerName = srcEntry.getKey();

            list.add(markerName);
        }

        return list;
    }

    /**
     * 论证基因座显示，不显示性别
     *
     * @param geneInfo
     * @return
     */
    public List<String> getNewMarker(String geneInfo) {
        Map<String, List<String>> srcResult = null;
        try {
            srcResult = (Map) JSON.parse(geneInfo, Feature.OrderedField);
        } catch (Exception ex) {
            logger.error("解析比对基因分型信息错误!", ex);
            return null;
        }
        List<String> list = new ArrayList<>();
        if (srcResult != null) {
            for (Map.Entry<String, List<String>> srcEntry : srcResult.entrySet()) {
                String markerName = srcEntry.getKey();
                if (!CommonUtils.contain(markerName, "amel")) {
                    list.add(markerName);
                }
            }
        }

        return list;
    }

    /**
     * 基因型排序
     *
     * @param stringList
     * @param geneInfo
     * @return
     */
    public static String getGeneInfoList(List<String> stringList, String geneInfo) {
        Map<String, List<String>> srcResult = null;
        try {
            srcResult = (Map) JSON.parse(geneInfo, Feature.OrderedField);
        } catch (Exception ex) {
            logger.error("解析比对基因分型信息错误!", ex);
            return null;
        }

        String locusName;

        Map<String, Object> resultMap = new LinkedHashMap<>();
        for (int i = 0; i < stringList.size(); i++) {
            locusName = stringList.get(i);
            if (locusName.toUpperCase().equals("THO1") || locusName.toUpperCase().equals("TH01")) {
                locusName = "TH01";
            }

            if (srcResult != null) {
                for (Map.Entry<String, List<String>> srcEntry : srcResult.entrySet()) {
                    String keyValue = srcEntry.getKey();
                    if (StringUtils.isBlank(keyValue)) {
                        continue;
                    }
                    if (keyValue.toUpperCase().equals("THO1") || keyValue.toUpperCase().equals("TH01")) {
                        keyValue = "TH01";
                    }

                    if (locusName.equalsIgnoreCase(keyValue)) {
                        resultMap.put(keyValue, srcEntry.getValue());
                    }
                }
            }
        }

        if (resultMap != null) {
            JSONObject json = new JSONObject(resultMap);
            geneInfo = json.toJSONString();
        }

        return geneInfo;
    }

    public static String getBooksGeneInfoList(List<String> stringList, String geneInfo) {
        Map<String, List<String>> srcResult = null;
        try {
            srcResult = (Map) JSON.parse(geneInfo, Feature.OrderedField);
        } catch (Exception ex) {
            logger.error("解析比对基因分型信息错误!", ex);
            return null;
        }

        String locusName;

        Map<String, Object> resultMap = new LinkedHashMap<>();
        for (int i = 0; i < stringList.size(); i++) {
            locusName = stringList.get(i);
            if (locusName.toUpperCase().equals("THO1") || locusName.toUpperCase().equals("TH01")) {
                locusName = "TH01";
            }

            if (srcResult != null) {
                String makerName = null;
                for (Map.Entry<String, List<String>> srcEntry : srcResult.entrySet()) {
                    String keyValue = srcEntry.getKey();
                    if (StringUtils.isBlank(keyValue)) {
                        continue;
                    }
                    if (keyValue.toUpperCase().equals("THO1") || keyValue.toUpperCase().equals("TH01")) {
                        keyValue = "TH01";
                    }

                    if (keyValue.equalsIgnoreCase("Amel")){
                        makerName = keyValue;
                    }

                    if (locusName.equalsIgnoreCase(keyValue)) {
                        resultMap.put(keyValue, srcEntry.getValue());
                    }else if (locusName != "Amel"){
                        resultMap.put(locusName, "");
                    }else if (null == makerName){
                        resultMap.put("Amel", "");
                    }
                }
            }
        }

        if (resultMap != null) {
            JSONObject json = new JSONObject(resultMap);
            geneInfo = json.toJSONString();
        }

        return geneInfo;
    }


    /**
     * 解析基因型获取试剂盒
     *
     * @param geneInfo
     * @return
     */
    public static List<List<String>> analysisGeneInfo(String geneInfo) {
        Map<String, List<String>> srcResult = null;
        try {
            srcResult = (Map) JSON.parse(geneInfo, Feature.OrderedField);
        } catch (Exception ex) {
            logger.error("解析比对基因分型信息错误!", ex);
            return null;
        }
        List<String> list = new ArrayList<>();
        if (srcResult != null) {
            for (Map.Entry<String, List<String>> srcEntry : srcResult.entrySet()) {
                String markerName = srcEntry.getKey();

                list.add(markerName);
            }
        }


        List<List<String>> resultList = null;
        if (ListUtils.isNotNullAndEmptyList(list)) {
            resultList = new ArrayList<>();
            List<String> strList = new ArrayList<>();
            int count = list.size();
            for (int i = 0; i < count; i++) {
                strList.add(list.get(i));

                if ((i + 1) % 8 == 0) {
                    resultList.add(strList);
                    strList = new ArrayList<>();
                }
            }
            //余数小于8时也添加到resultList
            if (ListUtils.isNotNullAndEmptyList(strList)) {
                resultList.add(strList);
            }
        }

        return resultList;
    }

    /**
     * 获取基因型位点
     *
     * @param markerList
     * @param matchAuditedGene
     * @return
     */
    public Map<String, List<String>> analysisGeneList(List<String> markerList, MatchAuditedGene matchAuditedGene) {

        Map<String, List<String>> resultMap = new LinkedHashMap<>();
        List<String> geneList = new ArrayList<>();
        geneList.add(matchAuditedGene.getSampleNo());
        for (String markerName : markerList) {
            String geneStr = analysisGeneStr(markerName, matchAuditedGene.getGeneInfo());
            geneList.add(geneStr);
        }

        resultMap.put("geneVal", geneList);

        return resultMap;
    }

    /**
     * 获取基因座对应pi值
     *
     * @param markerList
     * @param geneInfo
     * @return
     */
    public Map<String, List<String>> analysisPIList(List<String> markerList, String geneInfo) {
        Map<String, List<String>> resultMap = new LinkedHashMap<>();
        Map<String, String> srcResult = null;
        try {
            srcResult = (Map) JSON.parse(geneInfo, Feature.OrderedField);
        } catch (Exception ex) {
            logger.error("解析比对基因分型信息错误!", ex);
            return null;
        }

        String pi = "";
        List<String> piList = new ArrayList<>();
        for (String markerName : markerList) {
            Object object = srcResult.get(markerName);
            String str = String.valueOf(object);
            if (srcResult.containsKey(markerName) && StringUtils.isNotBlank(str)) {
                Double pIValue = Double.parseDouble(str);
                if (!Double.isNaN(pIValue)) {
                    pi = String.format("%.3f", pIValue);
                }
            }
            piList.add(pi);
        }

        resultMap.put("piVal", piList);
        return resultMap;
    }

    /**
     * 根据基因座获取基因位点
     *
     * @param markerName
     * @param geneInfo
     * @return
     */
    public String analysisGeneStr(String markerName, String geneInfo) {
        Map<String, List<String>> srcResult = null;
        try {
            srcResult = (Map) JSON.parse(geneInfo, Feature.OrderedField);
        } catch (Exception ex) {
            logger.error("解析比对基因分型信息错误!", ex);
            return null;
        }

        String allele = null;
        for (Map.Entry<String, List<String>> srcEntry : srcResult.entrySet()) {
            allele = getNewAllele(srcResult.get(markerName));
        }

        return allele;
    }

    public String getNewAllele(List<String> strList) {
        String allele = "";

        if (ListUtils.isNotNullAndEmptyList(strList)) {
            for (String strGene : strList) {
                if (StringUtils.isBlank(allele)) {
                    allele = strGene;
                } else {
                    if (!allele.contains(strGene)) {
                        allele += "/" + strGene;
                    }
                }
            }
        }

        return allele;
    }

    /**
     * 解析基因型信息
     *
     * @param limsSampleGene
     * @return
     */
    public List<Map<String, Object>> analysisGene(LimsSampleGene limsSampleGene) {
        Map<String, List<String>> result = null;
        try {
//            result = (Map) JSON.parse(limsSampleGene.getGeneInfo(), Feature.OrderedField);
        } catch (Exception ex) {
            logger.error("解析基因分型信息错误!", ex);
            return null;
        }
        if (limsSampleGene.getGeneInfo() != null) {
            ArrayList<String> geneInfoList = new ArrayList<>();
            String geneInfo = limsSampleGene.getGeneInfo();
            //进行排序
            if (StringUtils.isNotBlank(limsSampleGene.getPanelName()) && CommonUtils.contain(limsSampleGene.getPanelName(), "identifiler")) {
                geneInfo = geneSameCompareUtil.getGeneInfoList(Constants.IdentifilerPlusList, geneInfo);
            }
            result = (Map) JSON.parse(geneInfo, Feature.OrderedField);
        }
        List<Map<String, Object>> list = new ArrayList<>();
        String allele = null;
        Map<String, Object> map = null;
        for (Map.Entry<String, List<String>> srcEntry : result.entrySet()) {
            map = new LinkedHashMap<>();
            allele = getAllele(srcEntry.getValue(), limsSampleGene.getGeneType());
            map.put("markerName", srcEntry.getKey());
            map.put("allele", allele);

            list.add(map);
        }

        return list;
    }

    /**
     * 获取基因位点
     *
     * @param strList
     * @return
     */
    public String getAllele(List<String> strList, String geneType) {
        String allele = "";

        if (ListUtils.isNotNullAndEmptyList(strList)) {
            for (String strGene : strList) {
                if (StringUtils.isBlank(allele)) {
                    allele = strGene;
                } else {
                    if (Constants.GENE_MIXED.equals(geneType)) {
                        allele += "/" + strGene;
                    } else {
                        if (!allele.contains(strGene)) {
                            allele += "/" + strGene;
                        }
                    }
                }
            }
        }

        return allele;
    }

    /**
     * 获取未比中的检材基因信息
     *
     * @param mag
     * @param compareSameResultList
     * @param compareRelativeResultList
     * @return
     */
    public MatchAuditedGene getNotMatchGene(MatchAuditedGene mag, List<CompareSameResult> compareSameResultList, List<CompareRelativeResult> compareRelativeResultList) {
        MatchAuditedGene auditedGene = null;
        if (mag != null && StringUtils.isNotBlank(mag.getSampleNo())) {
            //判断此检材是否在同一比对结果中
            boolean notInSameFlag = true;
            for (CompareSameResult csr : compareSameResultList) {
                if (mag.getSampleNo().equals(csr.getSampleNo())) {
                    notInSameFlag = false;
                    break;
                }
            }
            //判断此检材是否在亲缘比对结果中
            boolean notInRelativeFlag = true;
            for (CompareRelativeResult crr : compareRelativeResultList) {
                if (mag.getSampleNo().equals(crr.getFatherSampleNo()) || mag.getSampleNo().equals(crr.getMotherSampleNo())
                        || mag.getSampleNo().equals(crr.getChildSampleNo())) {
                    notInRelativeFlag = false;
                    break;
                }
            }

            //获取未比中的检材基因分型
            if (notInSameFlag && notInRelativeFlag) {
                auditedGene = mag;
            }
        }

        return auditedGene;
    }

}
