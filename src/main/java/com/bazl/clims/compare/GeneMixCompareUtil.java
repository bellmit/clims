package com.bazl.clims.compare;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.bazl.clims.common.Constants;
import com.bazl.clims.datamodel.CaseCompareResultGroup;
import com.bazl.clims.model.po.MatchAuditedGene;
import com.bazl.clims.utils.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author wanghaiyang
 * @date 2019/4/1.
 */
@Service
public class GeneMixCompareUtil {

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

    /**
     * 日志对象
     */
    private static final Logger logger = LoggerFactory.getLogger(GeneMixCompareUtil.class);

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
            logger.error("解析比对基因分型信息错误！", ex);
            return null;
        }
        Map<String, List<String>> tarResult = null;
        try {
            tarResult = (Map) JSON.parse(tarGeneInfo, Feature.OrderedField);
//            tarResult = removeAmel(tarResult);
        } catch (Exception ex) {
            logger.error("解析比对基因分型信息错误！", ex);
            return null;
        }
        if (srcResult == null || tarResult == null) {
            return null;
        }
        return compareResult(srcResult, tarResult, matchLimit,0);
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
        }
        return map;
    }

    /**
     * @param srcGeneMap 待比对基因型
     * @param tarGeneMap 被比样本基因型
     * @return
     */    //容差

    public Map<String, Object> compareResult(Map<String, List<String>> srcGeneMap, Map<String, List<String>> tarGeneMap, int matchLimit,int halfDiffCount1) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (halfDiffCount1 == 0){
            halfDiffCount1 = halfDiffCount;
        }
        int sameCount = 0;  /*比中数*/
        int diffCount = 0;  /*容差数*/
        Double probability = 1.0;

        //进行循环比对
        for (Map.Entry<String, List<String>> srcEntry : srcGeneMap.entrySet()) {
            String markerName = srcEntry.getKey();
            List<String> srcAlleleList = srcEntry.getValue();
            List<String> tarAlleleList = tarGeneMap.get(markerName);
//            if (isContainsAll(srcAlleleList, tarAlleleList)) {
//                sameCount++;
//            } else {
//                diffCount++;
//            }
            if (srcAlleleList != null && tarAlleleList != null){
                if(isContainsAll(srcAlleleList, tarAlleleList)){
                    sameCount++;
                }else {
                    diffCount++;
                }

            }
        }

        resultMap.put("sameCount", sameCount);
        resultMap.put("diffCount", diffCount);
        if (sameCount >= matchLimit && diffCount <= halfDiffCount1) {
            resultMap.put("matched", Boolean.TRUE);
        } else {
            resultMap.put("matched", Boolean.FALSE);
        }
//        if (sameCount < matchLimit) {
//            resultMap.put("matched", Boolean.FALSE);
//        } else {
//            resultMap.put("matched", Boolean.TRUE);
//        }
        return resultMap;
    }

    /**
     * 混合比对分组
     * @param mixAuditedGeneList
     * @param auditedGeneList
     * @return
     */
    public List<CaseCompareResultGroup> mixDoCompare(List<MatchAuditedGene> mixAuditedGeneList, List<MatchAuditedGene> auditedGeneList, int mixSameCount) {
        List<CaseCompareResultGroup> auditGroupList = new ArrayList<>();

        MatchAuditedGene mixAuditedGene = null;
        MatchAuditedGene auditedGene1 = null;
        MatchAuditedGene auditedGene2 = null;
        List<MatchAuditedGene> matchAuditedGeneList = null;
        int count = 0;
        for (int m = 0; m < mixAuditedGeneList.size(); m++) {
            mixAuditedGene = mixAuditedGeneList.get(m);

            List<MatchAuditedGene> auditedList = new ArrayList<>();
            for (int i = 0; i < auditedGeneList.size(); i++) {
                //混合样本比中只比人员样本
                if (auditedGeneList.get(i).getSampleFlag().equals(Constants.SAMPLE_FLAG_1)) {

                    auditedGene1 = auditedGeneList.get(i);
                    matchAuditedGeneList = new ArrayList<>();

                    //判断混合基因是否包含此基因
                    if (compareGeneInfo(mixAuditedGene, auditedGene1, mixSameCount)) {
                        matchAuditedGeneList.add(mixAuditedGene);
                        matchAuditedGeneList.add(auditedGene1);

                        if (auditedGeneList.size() == 1 || (i + 1) == auditedGeneList.size()) {
                            //只有一个贡献者
                            CaseCompareResultGroup resultGroup = new CaseCompareResultGroup();
                            count++;
                            resultGroup.setGroupId(count);
                            resultGroup.setMixMatchedSampleList(matchAuditedGeneList);
                            auditGroupList.add(resultGroup);
                        }else {
                            //判断是否存在另一个贡献者
                            for (int j = i + 1; j < auditedGeneList.size(); j++) {
                                auditedGene2 = auditedGeneList.get(j);

                                boolean isMixFlag = false;
                                List<MatchAuditedGene> matchAuditedGenes = null;
                                CaseCompareResultGroup resultGroup = new CaseCompareResultGroup();
                                //合并基因分型
                                MatchAuditedGene merGergeGene = arrayMerge(auditedGene1, auditedGene2);

                                matchAuditedGenes = new ArrayList<>();
                                matchAuditedGenes.addAll(matchAuditedGeneList);

                                //判断混合基因分型是否等于合并的基因分型
                                if (compareGeneInfo(mixAuditedGene, merGergeGene, mixSameCount)) {
                                    isMixFlag = true;
                                    if (!auditedGene1.getAuditedGeneId().equals(auditedGene2.getAuditedGeneId())) {
                                        matchAuditedGenes.add(auditedGene2);
                                    }
                                }
                                if (isMixFlag) {
                                    count++;
                                    resultGroup.setGroupId(count);
                                    resultGroup.setMixMatchedSampleList(matchAuditedGenes);
                                    auditGroupList.add(resultGroup);
                                } else {
                                    //只有一个贡献者
                                    if (j == auditedGeneList.size() - 1) {
                                        count++;
                                        resultGroup.setGroupId(count);
                                        resultGroup.setMixMatchedSampleList(matchAuditedGenes);
                                        auditGroupList.add(resultGroup);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        //去除重复分组
        auditGroupList = removeSameGroup(auditGroupList);

        return auditGroupList;
    }

    /**
     * 基因比对
     * @param s1
     * @param s2
     * @param mixSameCount
     * @return
     */
    public boolean compareGeneInfo(MatchAuditedGene s1, MatchAuditedGene s2, int mixSameCount) {
        String geneStr1 = null;
        String geneStr2 = null;
        if (null != s2 && StringUtils.isNotBlank(s2.getGeneInfo())) {
            geneStr2 = s2.getGeneInfo();
        }
        if (null != s1 && StringUtils.isNotBlank(s1.getGeneInfo())) {
            geneStr1 = s1.getGeneInfo();
        }

        if (mixSameCount == 0) {
//            mixSameCount = minSameMixCount;
            mixSameCount = s1.getGeneCount();
        }
        Map<String, Object> geneDetail = new HashMap<>();
        if (StringUtils.isNotBlank(geneStr1) && StringUtils.isNotBlank(geneStr2)) {
            geneDetail = compare(geneStr1, geneStr2, mixSameCount);
        }

        if (geneDetail.isEmpty()) {
            return false;
        }

        return (boolean) geneDetail.get("matched");
    }

    public MatchAuditedGene arrayMerge(MatchAuditedGene auditedGene1, MatchAuditedGene auditedGene2) {
        MatchAuditedGene matchAuditedGene = new MatchAuditedGene();
        if (auditedGene1 != null && auditedGene2 != null) {
            String geneStr1 = auditedGene1.getGeneInfo();
            String geneStr2 = auditedGene2.getGeneInfo();

            Map<String, List<String>> srcResult = null;
            try {
                srcResult = (Map) JSON.parse(geneStr1, Feature.OrderedField);
            } catch (Exception ex) {
                logger.error("解析基因分型1信息错误！", ex);
                return null;
            }
            Map<String, List<String>> tarResult = null;
            try {
                tarResult = (Map) JSON.parse(geneStr2, Feature.OrderedField);
            } catch (Exception ex) {
                logger.error("解析基因分型2信息错误！", ex);
                return null;
            }

            if (srcResult == null || tarResult == null) {
                return null;
            }

            Map<String, Object> newResult = new LinkedHashMap<>();
            ;
            //两个基因分型合并成一个混合分型
            for (Map.Entry<String, List<String>> srcEntry : srcResult.entrySet()) {
                String srcEntryKey = srcEntry.getKey();
                List<String> srcAlleleList = srcEntry.getValue();
                for (Map.Entry<String, List<String>> tarEntry : tarResult.entrySet()) {
                    String tarEntryKey = tarEntry.getKey();
                    List<String> tarAlleleList = tarEntry.getValue();
                    if (StringUtils.isNotBlank(srcEntryKey) && StringUtils.isNotBlank(tarEntryKey)) {
                        if (srcEntryKey.equalsIgnoreCase(tarEntryKey)) {
                            //合并分型
                            //此处指的是将与tarAlleleList重复的删除
                            srcAlleleList.removeAll(tarAlleleList);
                            ////此处指加上tarAlleleList
                            srcAlleleList.addAll(tarAlleleList);
                            //保证srcAlleleList本身不重复
                            List<String> newAlleleList = new ArrayList<String>(new HashSet<>(srcAlleleList));

                            newResult.put(srcEntryKey, newAlleleList);
                        }
                    }
                }
            }

            if (newResult != null) {
                JSONObject json = new JSONObject(newResult);
                matchAuditedGene.setGeneInfo(json.toJSONString());
            }
        }

        return matchAuditedGene;
    }


    /**
     * 获取混合与贡献者的基因座和基因位点
     * @param mixAuditedGene
     * @param contributor1Gene
     * @param contributor2Gene
     * @return
     */
    public List<Map<String, Object>> getGeneList(MatchAuditedGene mixAuditedGene, MatchAuditedGene contributor1Gene, MatchAuditedGene contributor2Gene) {
        List<Map<String, Object>> resultList = null;

        try {
            String mixGeneStr = "";
            String geneStr1 = "";
            String geneStr2 = "";
            if (mixAuditedGene != null) {
                mixGeneStr = mixAuditedGene.getGeneInfo();

                if (contributor1Gene != null) {
                    geneStr1 = contributor1Gene.getGeneInfo();
                }
                if (contributor2Gene != null) {
                    geneStr2 = contributor2Gene.getGeneInfo();
                }

                resultList = getGeneGroupList(mixGeneStr, geneStr1, geneStr2);

            }
        } catch (Exception e) {
            logger.error("分配基因列表报错！" + e.getStackTrace());
            return null;
        }

        return resultList;
    }

    /**
     * 解析基因分型
     * @param mixGeneStr
     * @param geneStr1
     * @param geneStr2
     * @return
     */
    public List<Map<String, Object>> getGeneGroupList(String mixGeneStr, String geneStr1, String geneStr2) {
        List<Map<String, Object>> resultList = null;

        Map<String, List<String>> mixResult = null;
        try {
            mixResult = (Map) JSON.parse(mixGeneStr, Feature.OrderedField);
        } catch (Exception ex) {
            logger.error("解析混合基因分型信息错误！", ex);
            return null;
        }

        Map<String, List<String>> srcResult = null;
        try {
            srcResult = (Map) JSON.parse(geneStr1, Feature.OrderedField);
        } catch (Exception ex) {
            logger.error("解析贡献者1基因分型信息错误！", ex);
            return null;
        }

        Map<String, List<String>> tarResult = null;
        try {
            tarResult = (Map) JSON.parse(geneStr2, Feature.OrderedField);
        } catch (Exception ex) {
            logger.error("解析贡献者2基因分型信息错误！", ex);
            return null;
        }

        if (mixResult == null) {
            return null;
        }

        if (srcResult == null && tarResult == null) {
            return null;
        }

        resultList = new ArrayList<>();
        for (Map.Entry<String, List<String>> mixEntry : mixResult.entrySet()) {
            String markerName = mixEntry.getKey();
            List<String> mixAlleleList = mixEntry.getValue();

            List<String> srcAlleleList = null;
            if (srcResult != null && srcResult.size() > 0) {
                srcAlleleList = srcResult.get(markerName);
            }

            List<String> tarAlleleList = null;
            if (tarResult != null && tarResult.size() > 0) {
                tarAlleleList = tarResult.get(markerName);
            }

            String mixAllele = getAllele(mixAlleleList);
            String allele1 = getAllele(srcAlleleList);
            String allele2 = getAllele(tarAlleleList);

            Map<String, Object> result = new LinkedHashMap<>();
            result.put("markerName", markerName);
            result.put("mixAllele", mixAllele);
            result.put("allele1", allele1);
            result.put("allele2", allele2);

            resultList.add(result);
        }

        return resultList;
    }

    /**
     * 获取基因位点
     * @param strList
     * @return
     */
    public String getAllele(List<String> strList) {
        String allele = "";

        if (ListUtils.isNotNullAndEmptyList(strList)) {
            for (String strGene : strList) {
                allele += strGene + ",";
            }
        }

        if (StringUtils.isNotBlank(allele)) {
            allele = allele.substring(0, allele.length() - 1);
        }

        return allele;
    }

    /**
     * 判断是否已经存在此分组的基因型
     * @param auditGroupList
     * @return
     */
    public List<CaseCompareResultGroup> removeSameGroup(List<CaseCompareResultGroup> auditGroupList) {

        if (ListUtils.isNotNullAndEmptyList(auditGroupList)) {
            for (int i = 0; i < auditGroupList.size() - 1; i++) {
                CaseCompareResultGroup resultGroup1 = auditGroupList.get(i);
                for (int j = auditGroupList.size() - 1; j > i; j--) {
                    CaseCompareResultGroup resultGroup2 = auditGroupList.get(j);
                    if (resultGroup1 != null && resultGroup2 != null) {
                        //判断是否包含此分组
                        if (isExistGroup(resultGroup1, resultGroup2)) {
                            auditGroupList.remove(j);
                        }
                    }
                }
            }
        }

        return auditGroupList;
    }
    public List<CaseCompareResultGroup> removeSameGroup2(List<CaseCompareResultGroup> auditGroupList) {

        if (ListUtils.isNotNullAndEmptyList(auditGroupList)) {
            for (int i = 0; i < auditGroupList.size() - 1; i++) {
                CaseCompareResultGroup resultGroup1 = auditGroupList.get(i);
                for (int j = auditGroupList.size() - 1; j > i; j--) {
                    CaseCompareResultGroup resultGroup2 = auditGroupList.get(j);
                    if (resultGroup1 != null && resultGroup2 != null) {
                        //判断是否包含此分组
                        if (isExistGroup(resultGroup1, resultGroup2)) {
                            auditGroupList.remove(i);
                        }
                    }
                }
            }
        }

        return auditGroupList;
    }

    /**
     * 判断是否包含此分组
     * @param resultGroup1
     * @param resultGroup2
     * @return
     */
    public boolean isExistGroup(CaseCompareResultGroup resultGroup1, CaseCompareResultGroup resultGroup2) {
        boolean flag = false;

        List<MatchAuditedGene> auditedGeneList1 = resultGroup1.getMixMatchedSampleList();
        List<MatchAuditedGene> auditedGeneList2 = resultGroup2.getMixMatchedSampleList();
        if (ListUtils.isNotNullAndEmptyList(auditedGeneList1) && ListUtils.isNotNullAndEmptyList(auditedGeneList2)) {
            int count1 = auditedGeneList1.size();
            int count2 = auditedGeneList2.size();

            List<String> list1 = new ArrayList<>();
            List<String> list2 = new ArrayList<>();
            for (MatchAuditedGene auditedGene : auditedGeneList1) {
                list1.add(auditedGene.getSampleId());
            }
            for (MatchAuditedGene auditedGene : auditedGeneList2) {
                list2.add(auditedGene.getSampleId());
            }

            if (ListUtils.isNotNullAndEmptyList(list1) && ListUtils.isNotNullAndEmptyList((list2))) {
                if (count2 > count1) {
                    if (list2.containsAll(list1)) {
                        flag = true;
                    }
                } else if (count1 > count2) {
                    if (list1.containsAll(list2)) {
                        flag = true;
                    }
                } else if (count1 == count2) {
                    if (list1.containsAll(list2)) {
                        flag = true;
                    }
                }
            }
        }

        return flag;
    }

    /**
     * 判断混合分型是否包含此分型
     * @param srcAlleleList
     * @param tarAlleleList
     * @return
     */
    public boolean isContainsAll(List<String> srcAlleleList, List<String> tarAlleleList) {
        boolean flag = true;

        String isExist = null;
        if (ListUtils.isNotNullAndEmptyList(srcAlleleList)) {
            for (String tarStr : tarAlleleList) {
                if (srcAlleleList.contains(tarStr)) {
                    //此处指的是将与tarAlleleList中重复的元素删除
//                    srcAlleleList.remove(tarStr);
                } else {
                    isExist = "notExist";
                    break;
                }
            }
        }

        if (StringUtils.isNotBlank(isExist)) {
            flag = false;
        }

        return flag;
    }

}
