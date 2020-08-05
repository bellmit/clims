package com.bazl.clims.compare;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.bazl.clims.common.Constants;
import com.bazl.clims.compare.relative.ParentageCalculator;
import com.bazl.clims.compare.relative.po.Marker;
import com.bazl.clims.compare.relative.po.ParentageMatchOptions;
import com.bazl.clims.compare.relative.po.ParentageMatchResult;
import com.bazl.clims.compare.relative.po.ParentageMatchResultRecord;
import com.bazl.clims.model.po.CompareRelativeResult;
import com.bazl.clims.utils.DataFormat;
import com.bazl.clims.utils.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Created by Sun on 2019/4/1.
 */
@Service
public class GeneRelativeCompareUtil {

    @Value("${RelativeMinSameCount}")
    private int relativeMinSameCount; //匹配下限
    @Value("${RelativeHalfDiffCount}")
    private int relativeHalfDiffCount;
    @Value("${PopulationId}")
    private String populationId;

    @Autowired
    ParentageCalculator parentageCalculator;

    /**
     * 日志对象
     */
    private static final Logger logger = LoggerFactory.getLogger(GeneRelativeCompareUtil.class);

    /**
     * 亲缘比对
     * @param fatherGeneInfo	     父亲基因型
     * @param motherGeneInfo	     母亲基因型
     * @param childGeneInfo	     孩子基因型
     * @return
     */
    public Map<String, Object> compare(String fatherGeneInfo, String motherGeneInfo, String childGeneInfo) {
        Map<String, List<String>> fatherResult = null;
        try {
            fatherResult =(Map) JSON.parse(fatherGeneInfo, Feature.OrderedField);
        } catch (Exception ex) {
            logger.error("解析比对基因分型信息错误！", ex);
            return null;
        }
        Map<String, List<String>> motherResult = null;
        try {
            motherResult =(Map) JSON.parse(motherGeneInfo, Feature.OrderedField);
        } catch (Exception ex) {
            logger.error("解析比对基因分型信息错误！", ex);
            return null;
        }
        Map<String, List<String>> childResult = null;
        try {
            childResult =(Map) JSON.parse(childGeneInfo, Feature.OrderedField);
        } catch (Exception ex) {
            logger.error("解析比对基因分型信息错误！", ex);
            return null;
        }

        if(fatherResult == null || motherResult == null || childResult == null){
            return null;
        }

        return compareResult(fatherResult, motherResult, childResult);
    }

    /**
     * @param fatherGeneMap	     父亲基因型
     * @param motherGeneMap	     母亲基因型
     * @param childGeneMap	     孩子基因型
     * @return
     */
    public Map<String, Object> compareResult(Map<String, List<String>> fatherGeneMap, Map<String, List<String>> motherGeneMap, Map<String, List<String>> childGeneMap){
        Map<String, Object> resultMap = new HashMap<String, Object>();

        int sameCount = 0;  /*比中数*/
        int diffCount = 0;  /*容差数*/

        Map<String, List<String>> fGeneMap = new HashMap<>();
        Map<String, List<String>> mGeneMap = new HashMap<>();
        Map<String, List<String>> cGeneMap = new HashMap<>();
        //进行循环比对
        for (Map.Entry<String, List<String>> entry : childGeneMap.entrySet()) {
            String markerName = entry.getKey();
            List<String> cAlleleList = entry.getValue();
            String cAllele1 = null;
            String cAllele2 = null;
            //获取孩纸基因位点值
            if(ListUtils.isNotNullAndEmptyList(cAlleleList) && cAlleleList.size() == 1){
                cAllele1 = cAlleleList.get(0);
                cAllele2 = cAlleleList.get(0);
            }else if(ListUtils.isNotNullAndEmptyList(cAlleleList) && cAlleleList.size() > 1){
                cAllele1 = cAlleleList.get(0);
                cAllele2 = cAlleleList.get(1);
            }
            //获取父亲基因位点值
            List<String> fAlleleList = fatherGeneMap.get(markerName);
            //获取母亲基因位点值
            List<String> mAlleleList = motherGeneMap.get(markerName);
            if(ListUtils.isNotNullAndEmptyList(fAlleleList) && ListUtils.isNotNullAndEmptyList(mAlleleList)){
                if((fAlleleList.contains(cAllele1) && mAlleleList.contains(cAllele2)) || (fAlleleList.contains(cAllele2) && mAlleleList.contains(cAllele1))){
                    fGeneMap.put(markerName, fAlleleList);
                    mGeneMap.put(markerName, mAlleleList);
                    cGeneMap.put(markerName, cAlleleList);
                    sameCount++;
                }
            }
        }

        Map<String, Marker> fMarkerMap = convertGenotype2Map(fGeneMap);
        Map<String, Marker> mMarkerMap = convertGenotype2Map(mGeneMap);
        Map<String, Marker> cMarkerMap = convertGenotype2Map(cGeneMap);
        ParentageMatchOptions option = new ParentageMatchOptions();
        option.setPopulationId(populationId);
        option.setMatchThreshold(relativeMinSameCount);
        option.setHalfDiffCount(relativeHalfDiffCount);

        ParentageMatchResult parentageMatchResult = parentageCalculator.calculate(fMarkerMap, mMarkerMap, cMarkerMap, option);

        resultMap = getDataInformation(fMarkerMap, mMarkerMap, cMarkerMap, parentageMatchResult);

        resultMap.put("sameCount", sameCount);
        resultMap.put("diffCount", diffCount);
        if(sameCount < relativeMinSameCount){
            resultMap.put("matched", Boolean.FALSE);
        } else{
            resultMap.put("matched", Boolean.TRUE);
        }
        resultMap.put("parentageMatchResult", parentageMatchResult);

        return resultMap;
    }

    /**
     * 亲缘比对支持单亲和双亲比对
     * @param fatherGeneInfo
     * @param motherGeneInfo
     * @param childGeneInfo
     * @return
     */
    public Map<String, Object> relationCompare(String fatherGeneInfo, String motherGeneInfo, String childGeneInfo, CompareRelativeResult compareRelativeResult) {
        Map<String, List<String>> fatherResult = null;
        try {
            fatherResult =(Map) JSON.parse(fatherGeneInfo, Feature.OrderedField);
        } catch (Exception ex) {
            logger.error("解析父亲基因分型信息错误！", ex);
            return null;
        }
        Map<String, List<String>> motherResult = null;
        try {
            motherResult =(Map) JSON.parse(motherGeneInfo, Feature.OrderedField);
        } catch (Exception ex) {
            logger.error("解析母亲基因分型信息错误！", ex);
            return null;
        }
        Map<String, List<String>> childResult = null;
        try {
            childResult =(Map) JSON.parse(childGeneInfo, Feature.OrderedField);
        } catch (Exception ex) {
            logger.error("解析孩子基因分型信息错误！", ex);
            return null;
        }

        if(childResult == null){
            return null;
        }

        if(fatherResult == null && motherResult == null){
            return null;
        }


        return relationCompareResult(fatherResult, motherResult, childResult, compareRelativeResult);
    }

    /**
     * @param fatherGeneMap	     父亲基因型
     * @param motherGeneMap	     母亲基因型
     * @param childGeneMap	     孩子基因型
     * @return
     */
    public Map<String, Object> relationCompareResult(Map<String, List<String>> fatherGeneMap, Map<String, List<String>> motherGeneMap,
                                                     Map<String, List<String>> childGeneMap, CompareRelativeResult compareRelativeResult){
        Map<String, Object> resultMap = new HashMap<String, Object>();

        if (compareRelativeResult.getMatchLimit() == null || compareRelativeResult.getMatchLimit() == 0) {
            compareRelativeResult.setMatchLimit(relativeMinSameCount);
        }
        if (compareRelativeResult.getTolerance() == null || compareRelativeResult.getTolerance() == 0) {
            compareRelativeResult.setTolerance(relativeHalfDiffCount);
        }

         /*比中数*/
        int sameCount = 0;
         /*容差数*/
        int diffCount = 0;

        Map<String, List<String>> fGeneMap = new HashMap<>();
        Map<String, List<String>> mGeneMap = new HashMap<>();
        Map<String, List<String>> cGeneMap = new HashMap<>();
        //进行循环比对
        for (Map.Entry<String, List<String>> entry : childGeneMap.entrySet()) {
            String markerName = entry.getKey();
            List<String> cAlleleList = entry.getValue();
            String cAllele1 = null;
            String cAllele2 = null;
            //获取孩纸基因位点值
            if(ListUtils.isNotNullAndEmptyList(cAlleleList) && cAlleleList.size() == 1){
                cAllele1 = cAlleleList.get(0);
                cAllele2 = cAlleleList.get(0);
            }else if(ListUtils.isNotNullAndEmptyList(cAlleleList) && cAlleleList.size() > 1){
                cAllele1 = cAlleleList.get(0);
                cAllele2 = cAlleleList.get(1);
            }

            List<String> fAlleleList = null;
            List<String> mAlleleList = null;
            if (fatherGeneMap != null && motherGeneMap!= null) {
                //获取父亲基因位点值
                fAlleleList = fatherGeneMap.get(markerName);
                //获取母亲基因位点值
                mAlleleList = motherGeneMap.get(markerName);
                int sum = 0;
                if (ListUtils.isNotNullAndEmptyList(fAlleleList) && ListUtils.isNotNullAndEmptyList(mAlleleList)) {
                    if (fAlleleList.contains(cAllele1) && mAlleleList.contains(cAllele2) ){
                        fGeneMap.put(markerName, fAlleleList);
                        mGeneMap.put(markerName, mAlleleList);
                        cGeneMap.put(markerName, cAlleleList);
                        sameCount++;
                    }else if (fAlleleList.contains(cAllele2) && mAlleleList.contains(cAllele1)) {
                        fGeneMap.put(markerName, fAlleleList);
                        mGeneMap.put(markerName, mAlleleList);
                        cGeneMap.put(markerName, cAlleleList);
                        sameCount++;
                    }else {
                        diffCount++;
                    }
                }

                /*if((fAlleleList.contains(cAllele1) && mAlleleList.contains(cAllele2)) || (fAlleleList.contains(cAllele2) && mAlleleList.contains(cAllele1))){
                    fGeneMap.put(markerName, fAlleleList);
                    mGeneMap.put(markerName, mAlleleList);
                    cGeneMap.put(markerName, cAlleleList);
                    sameCount++;
                }*/
            }else if (fatherGeneMap != null && motherGeneMap == null) {
                //获取父亲基因位点值
                fAlleleList = fatherGeneMap.get(markerName);
                if((fAlleleList.contains(cAllele1) || (fAlleleList.contains(cAllele2)))){
                    fGeneMap.put(markerName, fAlleleList);
                    cGeneMap.put(markerName, cAlleleList);
                    sameCount++;
                }else {
                    diffCount++;
                }
            }else if (fatherGeneMap == null && motherGeneMap != null) {
                //获取母亲基因位点值
                mAlleleList = motherGeneMap.get(markerName);
                if((mAlleleList.contains(cAllele1) || (mAlleleList.contains(cAllele2)))){
                    mGeneMap.put(markerName, mAlleleList);
                    cGeneMap.put(markerName, cAlleleList);
                    sameCount++;
                }else {
                    diffCount++;
                }
            }
        }

        Map<String, Marker> fMarkerMap = convertGenotype2Map(fGeneMap);
        Map<String, Marker> mMarkerMap = convertGenotype2Map(mGeneMap);
        Map<String, Marker> cMarkerMap = convertGenotype2Map(cGeneMap);
        ParentageMatchOptions option = new ParentageMatchOptions();
        option.setPopulationId(populationId);
        option.setMatchThreshold(compareRelativeResult.getMatchLimit());
        option.setHalfDiffCount(compareRelativeResult.getTolerance());

        ParentageMatchResult parentageMatchResult = parentageCalculator.calculate(fMarkerMap, mMarkerMap, cMarkerMap, option);

        resultMap = getDataInformation(fMarkerMap, mMarkerMap, cMarkerMap, parentageMatchResult);

        resultMap.put("sameCount", sameCount);
        resultMap.put("diffCount", diffCount);
        if(sameCount < compareRelativeResult.getMatchLimit()){
            resultMap.put("matched", Boolean.FALSE);
        } else{
            resultMap.put("matched", Boolean.TRUE);
        }
        resultMap.put("parentageMatchResult", parentageMatchResult);

        return resultMap;
    }


    private static Map<String, Marker> convertGenotype2Map(Map<String, List<String>> geneMap) {
        Map<String, Marker> result = new Hashtable<String, Marker>();
        if (geneMap == null) {
            return result;
        }
        for (Map.Entry<String, List<String>> entry : geneMap.entrySet()) {
            String markerName = entry.getKey();
            List<String> alleleList = entry.getValue();
            if(entry == null || entry.getKey() == null) {
                continue;
            }
            if (entry.getKey().equalsIgnoreCase("AMEL") || entry.getKey().equalsIgnoreCase("AM")) {
                continue;
            }

            String allele1 = null;
            String allele2 = null;
            if(ListUtils.isNotNullAndEmptyList(alleleList) && alleleList.size() == 1){
                allele1 = alleleList.get(0);
                allele2 = alleleList.get(0);
            }else if(ListUtils.isNotNullAndEmptyList(alleleList) && alleleList.size() > 1){
                allele1 = alleleList.get(0);
                allele2 = alleleList.get(1);
            }
            if(allele1 != null && allele2 != null) {
                Marker marker = new Marker();
                marker.add(allele1.toString());
                marker.add(allele2.toString());
                result.put(markerName, marker);
            }
        }
        return result;
    }

    private Map<String, Object> getDataInformation (Map<String, Marker> fMarkerMap, Map<String, Marker> mMarkerMap,
                                                    Map<String, Marker> cMarkerMap, ParentageMatchResult parentageMatchResult) {
        Map<String, Object> resultMap = new HashMap<>();

        ParentageMatchResultRecord result = parentageMatchResult.getResult();
        ParentageMatchResultRecord afResult = parentageMatchResult.getResultOfAf();
        ParentageMatchResultRecord mResult = parentageMatchResult.getResultOfM();

        int matchMode = 0;
        if (fMarkerMap.size() > 0 && mMarkerMap.size() > 0 && cMarkerMap.size() > 0) {
            if (result.isSuccessful() && afResult.isSuccessful() && mResult.isSuccessful()) {
                matchMode = Constants.MATCH_MODE_1;
            } else if (!result.isSuccessful() && !afResult.isSuccessful() && !mResult.isSuccessful()) {
                matchMode = Constants.MATCH_MODE_0;
            } else if (!result.isSuccessful() && afResult.isSuccessful() && !mResult.isSuccessful()) {
                matchMode = Constants.MATCH_MODE_2;
            } else if (!result.isSuccessful() && !afResult.isSuccessful() && mResult.isSuccessful()) {
                matchMode = Constants.MATCH_MODE_3;
            } else if (!result.isSuccessful() && afResult.isSuccessful() && mResult.isSuccessful()) {
                matchMode = Constants.MATCH_MODE_4;
            }

        } else if (fMarkerMap.size() > 0 && mMarkerMap.size() == 0 && cMarkerMap.size() > 0) {
            if (afResult.isSuccessful()) {
                matchMode = Constants.MATCH_MODE_2;
            } else {
                matchMode = Constants.MATCH_MODE_0;
            }

            parentageMatchResult.setResult(afResult);
        } else if (fMarkerMap.size() == 0 && mMarkerMap.size() > 0 && cMarkerMap.size() > 0) {
            if (mResult.isSuccessful()) {
                matchMode = Constants.MATCH_MODE_3;
            } else {
                matchMode = Constants.MATCH_MODE_0;
            }

            parentageMatchResult.setResult(mResult);
        }

        resultMap.put("matchMode", String.valueOf(matchMode));

        if(matchMode == Constants.MATCHMODE_UNMATCHED_FM) {
            resultMap.put("totalPi", DataFormat.formatDecimal(parentageMatchResult.getResult().getPi() == 0.0?0.0:parentageMatchResult.getResult().getPi(), 3, 1, true));
            resultMap.put("matchCount", parentageMatchResult.getResult().getMatchCount());
            resultMap.put("matchModeName", "无");
        }else if (matchMode ==  Constants.MATCHMODE_MATCHED_FM){
            resultMap.put("totalPi", DataFormat.formatDecimal(parentageMatchResult.getResult().getPi() == 0.0?0.0:parentageMatchResult.getResult().getPi(), 3, 1, true));
            resultMap.put("matchCount", parentageMatchResult.getResult().getMatchCount());
            resultMap.put("matchModeName", "父/母亲");
        } else if (matchMode == Constants.MATCHMODE_MATCHED_F) {
            resultMap.put("totalPi", DataFormat.formatDecimal(parentageMatchResult.getResultOfAf().getPi() == 0.0?0.0:parentageMatchResult.getResultOfAf().getPi(), 3, 1, true));;
            resultMap.put("matchCount", parentageMatchResult.getResultOfAf().getMatchCount());
            resultMap.put("matchModeName", "父");
        } else if (matchMode == Constants.MATCHMODE_MATCHED_M) {
            resultMap.put("totalPi", DataFormat.formatDecimal(parentageMatchResult.getResultOfM().getPi() == 0.0?0.0:parentageMatchResult.getResultOfM().getPi(), 3, 1, true));;
            resultMap.put("matchCount", parentageMatchResult.getResultOfM().getMatchCount());
            resultMap.put("matchModeName", "母亲");
        }else if (matchMode == Constants.MATCHMODE_MATCHED_FM_UNMATCHEDALL ){
            resultMap.put("totalPi", DataFormat.formatDecimal(parentageMatchResult.getResult().getPi() == 0.0?0.0:parentageMatchResult.getResult().getPi(), 3, 1, true));;
            resultMap.put("matchCount", parentageMatchResult.getResult().getMatchCount());
            resultMap.put("matchModeName", "无");
        }else if(matchMode == Constants.MATCHMODE_CHILD_MIX) {
            resultMap.put("totalPi", "--");
            resultMap.put("matchCount", "0");
            resultMap.put("matchModeName", "(孩子)混合样本");
        }else if(matchMode == Constants.MATCHMODE_FATHER_MIX) {
            resultMap.put("totalPi", "--");
            resultMap.put("matchCount", "0");
            resultMap.put("matchModeName",  "(父亲)混合样本");
        }else if(matchMode == Constants.MATCHMODE_MOTHER_MIX) {
            resultMap.put("totalPi", "--");
            resultMap.put("matchCount", "0");
            resultMap.put("matchModeName", "(母亲)混合样本");
        }else {
            resultMap.put("totalPi", " -- ");
            resultMap.put("matchCount", "0");
            resultMap.put("matchModeName", "无");
        }

        return resultMap;
    }

    /**
     * 拼接基因型
     * @param alleleList
     * @return
     */
    public String getAllele(List<String> alleleList) {
        String gene = "";

        for (String strGene : alleleList) {
            if (StringUtils.isBlank(gene)) {
                gene = strGene;
            }else {
                if (!gene.contains(strGene)) {
                    gene += "," + strGene;
                }
            }
        }

        return gene;
    }

}
