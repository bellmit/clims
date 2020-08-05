package com.bazl.clims.service.impl;

import com.bazl.clims.service.WzService;
import com.bazl.clims.utils.DataFormat;
import com.bazl.clims.webservice.client.libMatch.*;
import com.bazl.clims.webservice.client.submitInfo.*;
import com.bazl.clims.webservice.client.submitInfo.WSGenotypeGeneral;
import com.bazl.clims.webservice.domain.CaseParentageMatchResult;
import com.bazl.clims.webservice.domain.LibMatchResultGroupDomain;
import com.bazl.clims.webservice.domain.SubmitInfoDomain;
import com.bazl.clims.webservice.domain.TotalRelativeMatchResult;
import com.bazl.clims.webservice.services.QueryDataServicePortType;
import com.bazl.clims.webservice.util.ConvertString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huawei on 2019/9/17.
 */
@Service
public class WzServiceImpl implements WzService {

    @Autowired
    private LibMatchPortType libMatchPortTypeService;
    @Autowired
    private SubmitInfoPortType submitInfoPortTypeService;

    @Autowired
    QueryDataServicePortType queryDataService;

    /**
     * 入库并提交亲缘类型基因型
     *
     * @param s1
     *            当前样本
     * @param s2
     *            同组样本
     * @param val1
     *            样本属性
     * @param val2
     *            目标类型
     * @return
     */
    @Override
    public Map<String, Object> parentageSubmitAndMatch(SubmitInfoDomain s1,
                                                       SubmitInfoDomain s2, int val1, int val2, int val0) {
        return parentageSubmitAndMatch_forword(s1, s2, val1, val2, val0);
    }


    private Map<String, Object> parentageSubmitAndMatch_forword(
            SubmitInfoDomain s1, SubmitInfoDomain s2, int val1, int val2,
            int val0) {
        com.bazl.clims.webservice.client.libMatch.WSGenotypeGeneral genotypeGeneral1 = new com.bazl.clims.webservice.client.libMatch.WSGenotypeGeneral();
        com.bazl.clims.webservice.client.libMatch.WSGenotypeGeneral genotypeGeneral2 = new com.bazl.clims.webservice.client.libMatch.WSGenotypeGeneral();

        genotypeGeneral1.setGenotypeString(ConvertString.convertString(s1
                .getMarkerElements(), "genotypeString"));
        genotypeGeneral1.setPanelName(ConvertString.convertString(s1
                .getPanelName(), "panelName"));
        genotypeGeneral1.setSampleID(ConvertString.convertString(s1
                .getSampleId(), "sampleID"));

        genotypeGeneral2.setGenotypeString(ConvertString.convertString(s2
                .getMarkerElements(), "genotypeString"));
        genotypeGeneral2.setPanelName(ConvertString.convertString(s2
                .getPanelName(), "panelName"));
        genotypeGeneral2.setSampleID(ConvertString.convertString(s2
                .getSampleId(), "sampleID"));

        WSParentageSubmitAndMatchResult wsSubmitResult = libMatchPortTypeService
                .parentageSubmitAndMatch(genotypeGeneral1, genotypeGeneral2,
                        val1, val2, val0);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("resultKey", wsSubmitResult.getReturnValue());
        map.put("resultValue", wsSubmitResult.getErrorInfo().getValue());
        return map;
    }

    /**
     * 入库并提交基因型
     *
     * @return 返回详细结果
     */
    @Override
    public Map<String, Object> submitAndMatch(SubmitInfoDomain s1, int val,
                                              int val0) {
        return submitAndMatch_forword(s1, val, val0);
    }

    private Map<String, Object> submitAndMatch_forword(SubmitInfoDomain s1,
                                                       int val, int val0) {
        com.bazl.clims.webservice.client.libMatch.WSGenotypeGeneral genotypeGeneral = new com.bazl.clims.webservice.client.libMatch.WSGenotypeGeneral();

        genotypeGeneral.setGenotypeString(ConvertString.convertString(s1
                .getMarkerElements(), "genotypeString"));
        genotypeGeneral.setPanelName(ConvertString.convertString(s1
                .getPanelName(), "panelName"));
        genotypeGeneral.setSampleID(ConvertString.convertString(s1
                .getSampleId(), "sampleID"));

        WSSubmitAndMatchResult wsSubmitResult = libMatchPortTypeService.submitAndMatch(
                genotypeGeneral, val, val0);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("resultKey", wsSubmitResult.getSubmitResult());
        map.put("resultValue", wsSubmitResult.getErrorInfo().getValue());
        return map;
    }

    /**
     * 提交基因型
     * @param sl
     * @param val
     * @param val0
     * @return
     */
    @Override
    public int submitGenotype(SubmitInfoDomain sl, int val, int val0) {
        return submitGenotype_forword(sl, val, val0);
    }

    /**
     * 提交人员信息
     * @param s	人员信息
     */
    @Override
    public void submitWsPerson(SubmitInfoDomain s) {
        submitWsPerson_forward(s);
    }

    /**
     * 提交案件信息
     * @param s	案件信息
     */
    @Override
    public void submitWsCase(SubmitInfoDomain s) {
        submitWsCase_forward(s);
    }

    /**
     * 提交样本信息
     * @param s	样本信息
     */
    @Override
    public void submitWsSampleInfo(SubmitInfoDomain s) {
        submitWsSampleInfo_forward(s);
    }

    @Override
    public List<LibMatchResultGroupDomain> libMatchRecordGroupList(
            String sampleID, String sampleName, String caseName,
            String sampleType, String matchType, String status ,String nameStatus,String groupType,
            String sameGender,String matchTimeStart ,String matchTimeEnd,
            String delegateOrgCode , String caseId ,String matchCount , int pageIndex, int recordCount) {

        WSSearchLibMatchRecordOption wsSearchLib = createOption(sampleID, sampleName, caseName,
                sampleType, matchType, status ,nameStatus,groupType, sameGender,matchTimeStart ,matchTimeEnd,
                delegateOrgCode ,caseId ,matchCount , pageIndex,recordCount);

        List<WSLibMatchGroupResult> libMatchGrouplist = (List<WSLibMatchGroupResult>) libMatchPortTypeService
                .getLibMathcResultGroup(wsSearchLib).getWSLibMatchGroupResult();

        List<LibMatchResultGroupDomain> result = libMatchRecordGroupList_forward(libMatchGrouplist);
        return result;
    }

    @Override
    public List<Map<String, Object>> libMatchRecordList(String status,
                                                        String barcode, String matchId, String sourceCaseName,
                                                        String sampleName, String submitOperator, String targetSampleType,
                                                        String groupID, int pageIndex, int recordCount) {
        return libMatchRecordList_forward(status, barcode, matchId,
                sourceCaseName, sampleName, submitOperator, targetSampleType,
                groupID, pageIndex, recordCount);
    }

    @Override
    public TotalRelativeMatchResult getLibMatchResult(String matchid, int piLength) {
        WSTotalRelativeMatchResult wsResult = libMatchPortTypeService.getLibMatchResult(matchid);
        TotalRelativeMatchResult result = libMatchResult_forward(wsResult,piLength);

        return result;
    }

    public static TotalRelativeMatchResult libMatchResult_forward(WSTotalRelativeMatchResult wsResult,int piLength) {

        TotalRelativeMatchResult totalRelativeMatchResult = new TotalRelativeMatchResult();
        if (wsResult != null) {
            CaseParentageMatchResult result = caseParentageMatchResult_forward(wsResult.getResult().getValue(),piLength);
            CaseParentageMatchResult fResult = caseParentageMatchResult_forward(wsResult.getFResult().getValue(),piLength);
            CaseParentageMatchResult mResult = caseParentageMatchResult_forward(wsResult.getMResult().getValue(),piLength);
            totalRelativeMatchResult.setMatchMode(wsResult.getMatchMode());
            totalRelativeMatchResult.setResult(result);
            totalRelativeMatchResult.setFResult(fResult);
            totalRelativeMatchResult.setMResult(mResult);
            totalRelativeMatchResult.setFBarcode(wsResult.getFBarcode().getValue());
            totalRelativeMatchResult.setMBarcode(wsResult.getMBarcode().getValue());
            totalRelativeMatchResult.setCBarcode(wsResult.getCBarcode().getValue());
        }
        return totalRelativeMatchResult;
    }

    public static CaseParentageMatchResult caseParentageMatchResult_forward(WSCaseParentageMatchResult result,int piLength) {
        CaseParentageMatchResult matchResult = new CaseParentageMatchResult();
        if(result != null) {
            matchResult.setIsSuccessful(result.getIsSuccessful());
            matchResult.setMatchCount(result.getMatchCount());

            if(result.getTotalPossibility() != null){
                String totalPossibility = DataFormat.formatDecimal(result.getTotalPossibility(),8,1,true);
                matchResult.setTotalPossibility(totalPossibility);
            }
            List<WSSingleMarkerRate> wsSingleMarkerRateList = result.getSingleMarkers().getValue().getWSSingleMarkerRate();
            Map<String,Double> markerRates = new HashMap<String,Double>();
            for(WSSingleMarkerRate wsSingleMarkerRate :wsSingleMarkerRateList) {
                markerRates.put(wsSingleMarkerRate.getMarkerName().getValue(),wsSingleMarkerRate.getMarkerRate());
            }
            matchResult.setMarkerRates(markerRates);
        }
        return matchResult;
    }
    private List<Map<String, Object>> libMatchRecordList_forward(String status,
                                                                 String barcode, String matchId, String sourceCaseName,
                                                                 String sampleName, String submitOperator, String targetSampleType,
                                                                 String groupID, int pageIndex, int recordCount) {
        WSSearchLibMatchRecordOption wsSearchLib = new WSSearchLibMatchRecordOption();
        if (status != null)
            wsSearchLib.setState(Integer.parseInt(status));
        if (barcode != null)
            wsSearchLib.setSampleId(ConvertString.convertString(barcode,
                    "sampleId"));
        if (matchId != null)
            wsSearchLib.setMatchId(ConvertString.convertString(matchId,
                    "matchId"));
        if (sourceCaseName != null)
            wsSearchLib.setCaseName(ConvertString.convertString(sourceCaseName,
                    "caseName"));
        if (sampleName != null)
            wsSearchLib.setSampleName(ConvertString.convertString(sampleName,
                    "sampleName"));
        if (submitOperator != null)
            wsSearchLib.setSubmitOperator(ConvertString.convertString(
                    submitOperator, "submitOperator"));
        if (targetSampleType != null)
            wsSearchLib.setTargetSampleType(Integer.parseInt(targetSampleType));

        if (groupID != null && !"".equals(groupID))
            wsSearchLib.setGroupID(ConvertString.convertString(groupID,
                    "groupID"));

        wsSearchLib.setPageNum(pageIndex); // 页码
        wsSearchLib.setPageSize(recordCount); // 条数

        WSLibMatchResultAndTotalCount arrayWs = libMatchPortTypeService.getLibMatchRecordList(wsSearchLib);

        List<WSLibMatchResult> wsList = arrayWs.getLibMatchResults().getValue()
                .getWSLibMatchResult();
        List<Map<String, Object>> result = wsLibMatchResult(wsList);
        for (Map<String, Object> map : result) {
            map.put("recordCount", arrayWs.getTotalMatchResultCount());
        }
        return result;
    }

    /**
     * 调用wsLibMatchResult 返回结果
     */
    private List<Map<String, Object>> wsLibMatchResult(
            List<WSLibMatchResult> wsList) {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        for (WSLibMatchResult w : wsList) {
            Map<String, Object> map = new HashMap<String, Object>();

            if (w.getGenotypeA().getValue() != null) { // 基因型A列表
                Map<String, Object> tempList = getMarkerAllelMap(w.getGenotypeA().getValue());
                map.put("genoTypeA", tempList);
            }
            if (w.getGenotypeB().getValue() != null) { // 基因型B列表
                Map<String, Object> tempList = getMarkerAllelMap(w.getGenotypeB().getValue());
                map.put("genoTypeB", tempList);
            }
            if (w.getGenotypeC().getValue() != null) { // 基因型C列表
                Map<String, Object> tempList = getMarkerAllelMap(w.getGenotypeC().getValue());
                map.put("genoTypeC", tempList);
            }

            map.put("isSampleBSource", w.getIsSampleBSource()); // 0: 对中 1:鉴定

            map.put("matchedTime", w.getMatchedTime().getValue()); // 比中时间
            map.put("matchId", w.getMatchId().getValue()); // 比中编号
            map.put("matchCount", w.getMatchCount()); // 比中位点数
            map.put("matchType", w.getMatchType().getValue()); // 匹配类型

            map.put("panelA", w.getPanelNameA().getValue()); // 鉴定panelNameA
            map.put("panelB", w.getPanelNameB().getValue()); // 对中panelNameB
            map.put("panelC", w.getPanelNameC().getValue()); // 对中panelNameC

            map.put("sampleAId", w.getSampleAId().getValue()); // 鉴定样本idA
            map.put("sampleBId", w.getSampleBId().getValue()); // 对中样本idB
            map.put("sampleCId", w.getSampleCId().getValue()); // 对中样本idC

            map.put("sampleAName", w.getSampleAName().getValue()); // 鉴定样本nameA
            map.put("sampleBName", w.getSampleBName().getValue()); // 对中样本nameB
            map.put("sampleCName", w.getSampleCName().getValue()); // 对中样本nameC

            map.put("submitOperA",w.getSubmitOperA().getValue());
            map.put("submitOperB", w.getSubmitOperB().getValue());
            map.put("submitOperC",w.getSubmitOperC().getValue());

            if (w.getSingleMarkerRates().getValue() != null) { // 基因座列表
                Map<String, Object> tempMap = new HashMap<String, Object>();
                for (WSSingleMarkerRate rate : w.getSingleMarkerRates()
                        .getValue().getWSSingleMarkerRate()) {
                    tempMap.put(rate.getMarkerName().getValue(),rate.getMarkerRate());
                }
                map.put("markerRates", tempMap);
            }
            map.put("sourceCaseId", w.getSourceCaseId().getValue()); // 鉴定案件id
            map.put("sourceCaseName", w.getSourceCaseName().getValue()); // 鉴定案件name
            map.put("sourceSampleType", w.getSourceSampleType().getValue()); // 鉴定案件样本类型

            map.put("operator", w.getOperator().getValue()); // 审核人
            map.put("status", w.getStatus().getValue()); // 审核状态
            map.put("statusId", w.getStatusCode()); // 状态id
            map.put("verifyTime", w.getVerifyTime().getValue()); // 审核时间

            map.put("submitOperator", w.getSubmitOperator().getValue()); // 提交人
            map.put("submitOrg", w.getSubmitOrg().getValue()); // 提交单位
            map.put("submitTime", w.getSubmitTime().getValue()); // 提交时间

            map.put("targetCaseId", w.getTargetCaseId().getValue()); // 对中案件id
            map.put("targetCaseName", w.getTargetCaseName().getValue()); // 对中案件name
            map.put("targetSampleType", w.getTargetSampleType().getValue()); // 对中案件样本类型
            map.put("caseMatched", w.getCaseMatched());
            double totalMatchPossibility = w.getTotalMatchPossibility();
            String temp = String.valueOf(totalMatchPossibility);
            if(temp.indexOf(".") != -1) {
                int plSize=Integer.parseInt("8");
                temp = DataFormat.formatDecimal(totalMatchPossibility, plSize, 1, true);
            }
            map.put("totalMatchPossibility", temp);

            result.add(map);
        }
        return result;
    }

    private Map<String, Object> getMarkerAllelMap(ArrayOfWSMarkerTypes tempMarkerTypes){
        Map<String, Object> result = new HashMap<String, Object>();
        if (tempMarkerTypes == null)
            return result;

        for (WSMarkerTypes MarkerType : tempMarkerTypes.getWSMarkerTypes()) {
            if(MarkerType.getMarkerName().getValue() == null)
                continue;

            List<WSAllele> allele = MarkerType.getAlleles()
                    .getValue().getWSAllele();
            String alleleLabel = "";
            String temp = "";
            for (WSAllele wsAllele : allele) {
                if(wsAllele == null || wsAllele.getAlleleLabel() == null ||  wsAllele.getAlleleLabel().getValue() == null)
                    continue;
                if(!temp.equals(wsAllele.getAlleleLabel().getValue()))
                    alleleLabel  += wsAllele.getAlleleLabel().getValue() + ",";

                temp = wsAllele.getAlleleLabel().getValue();
            }
            if (alleleLabel.length() > 0)
                alleleLabel = alleleLabel.substring(0, alleleLabel.length() - 1);

            result.put(MarkerType.getMarkerName().getValue(),alleleLabel);
        }
        return result;
    }

    private WSSearchLibMatchRecordOption createOption(String sampleID, String sampleName, String caseName, String sampleType, String matchType, String status, String nameStatus, String groupType, String sameGender, String matchTimeStart, String matchTimeEnd, String delegateOrgCode, String caseId, String matchCount, int pageIndex, int recordCount) {
        WSSearchLibMatchRecordOption wsSearchLib = new WSSearchLibMatchRecordOption();

        if ((sampleID != null) && (!"".equals(sampleID)))
            wsSearchLib.setSampleId(ConvertString.convertString(sampleID,
                    "sampleId"));
        if ((sampleName != null) && (!"".equals(sampleName)))
            wsSearchLib.setSampleId(ConvertString.convertString(sampleName,
                    "sampleName"));
        if ((caseName != null) && (!"".equals(caseName)))
            wsSearchLib.setMatchId(ConvertString.convertString(caseName,
                    "caseName"));
        if ((sampleType != null) && (!"".equals(sampleType)))
            wsSearchLib.setSourceSampleType(Integer.valueOf(Integer.parseInt(sampleType)));
        if ((matchType != null) && (!"".equals(matchType)));
        wsSearchLib.setMatchType(ConvertString.convertString(matchType,
                "matchType"));

        if ((status != null) && (!"".equals(status))) {
            wsSearchLib.setState(Integer.valueOf(Integer.parseInt(status)));
        }
        if ((nameStatus != null) && (!"".equals(nameStatus))) {
            wsSearchLib.setNameStatus(ConvertString.convertString(nameStatus, "nameStatus"));
        }
        if ((groupType != null) && (!"".equals(groupType))) {
            wsSearchLib.setGroupType(ConvertString.convertString(groupType, "groupType"));
        }
        if ((sameGender != null) && (!sameGender.equals(""))) {
            wsSearchLib.setSameGender(ConvertString.convertString(sameGender, "sameGender"));
        }

        if ((caseId != null) && (!caseId.equals(""))) {
            wsSearchLib.setCaseId(ConvertString.convertString(caseId, "caseId"));
        }
        ArrayOfWSAdditionOption arr = new ArrayOfWSAdditionOption();
        WSAdditionOption startTime = new WSAdditionOption();
        startTime.setName(ConvertString.convertString("matchTimeStart", "name"));
        startTime.setValue(ConvertString.convertString(matchTimeStart, "value"));
        arr.getWSAdditionOption().add(startTime);
        WSAdditionOption endTime = new WSAdditionOption();
        endTime.setName(ConvertString.convertString("matchTimeEnd", "name"));
        endTime.setValue(ConvertString.convertString(matchTimeEnd, "value"));
        arr.getWSAdditionOption().add(endTime);
        WSAdditionOption delegateOrg = new WSAdditionOption();
        delegateOrg.setName(ConvertString.convertString("delegateOrgCode", "name"));
        delegateOrg.setValue(ConvertString.convertString(delegateOrgCode, "value"));
        arr.getWSAdditionOption().add(delegateOrg);

        WSAdditionOption matchCountOption = new WSAdditionOption();
        matchCountOption.setName(ConvertString.convertString("matchCount", "name"));
        matchCountOption.setValue(ConvertString.convertString(matchCount, "value"));
        arr.getWSAdditionOption().add(matchCountOption);

        wsSearchLib.setAddtionOption(ConvertString.convertArr(arr, "addtionOption"));

        wsSearchLib.setPageNum(Integer.valueOf(pageIndex));
        wsSearchLib.setPageSize(Integer.valueOf(recordCount));
        return wsSearchLib;
    }

    private List<LibMatchResultGroupDomain> libMatchRecordGroupList_forward(List<WSLibMatchGroupResult> list){
        List<LibMatchResultGroupDomain> result = new ArrayList<LibMatchResultGroupDomain>();

        for (int i = 0; i < list.size(); i++) {
            WSLibMatchGroupResult wSLibMatchGroupResult =
                    (WSLibMatchGroupResult)list
                            .get(i);
            if (wSLibMatchGroupResult != null) {
                List tempSampleList =
                        ((ArrayOfWSLibMatchGroupRecord)wSLibMatchGroupResult
                                .getSampleList().getValue()).getWSLibMatchGroupRecord();

                List<Map<String, String>> sampleList = new ArrayList<Map<String, String>>();

                for (int j = 0; j < tempSampleList.size(); j++) {
                    WSLibMatchGroupRecord wSLibMatchGroupRecord =
                            (WSLibMatchGroupRecord)tempSampleList
                                    .get(j);
                    Map map = new HashMap();

                    map.put("caseid", (String)wSLibMatchGroupRecord.getCaseID().getValue());
                    map.put("casename",
                            (String)wSLibMatchGroupRecord.getCaseName()
                                    .getValue());
                    map.put("sampleid",
                            (String)wSLibMatchGroupRecord.getSampleID()
                                    .getValue());
                    map.put("samplename",
                            (String)wSLibMatchGroupRecord.getSampleName()
                                    .getValue());
                    map.put("sampletype",
                            (String)wSLibMatchGroupRecord.getSampleType()
                                    .getValue());
                    map.put("groupid",
                            (String)wSLibMatchGroupRecord.getGroupID()
                                    .getValue());
                    map.put("barcode",
                            (String)wSLibMatchGroupRecord.getBarcode()
                                    .getValue());
                    sampleList.add(map);
                }

                LibMatchResultGroupDomain libMatchResultGroupDomain = new LibMatchResultGroupDomain();
                libMatchResultGroupDomain.setGroupId(
                        (String)wSLibMatchGroupResult
                                .getGroupId().getValue());
                libMatchResultGroupDomain.setMatchCnt((String)wSLibMatchGroupResult.getMatchCnt().getValue());
                libMatchResultGroupDomain.setRowCnt(wSLibMatchGroupResult
                        .getRowCnt().intValue());
                libMatchResultGroupDomain.setSampleList(sampleList);
                libMatchResultGroupDomain.setMatchType(
                        (String)wSLibMatchGroupResult
                                .getMatchType().getValue());
                libMatchResultGroupDomain.setLastMatchTime((String)wSLibMatchGroupResult.getLastMatchTime().getValue());

                result.add(libMatchResultGroupDomain);
            }
        }
        return result;
    }

    private int submitGenotype_forword(SubmitInfoDomain s, int val, int val0) {
        WSGenotypeGeneral genotypeGeneral = new WSGenotypeGeneral();

        genotypeGeneral.setDataType(s.getDataType());
        genotypeGeneral.setGenotypeString(ConvertString.convertString(s.getMarkerElements(), "genotypeString"));
        genotypeGeneral.setPanelName(ConvertString.convertString(s.getPanelName(), "panelName"));
        genotypeGeneral.setSampleID(ConvertString.convertString(s.getSampleId(), "sampleID"));

        return submitInfoPortTypeService.submitGenotype(genotypeGeneral, val, val0);
    }

    private void submitWsPerson_forward(SubmitInfoDomain s) {
        WSPerson wsPerson = new WSPerson();
        wsPerson.setCaseid(ConvertString.convertString(s.getCaseInfoId(), "caseid"));
        submitInfoPortTypeService.submitPersonInfo(wsPerson);
    }

    private void submitWsCase_forward(SubmitInfoDomain s) {
        WSCase wsCase = new WSCase();

        wsCase.setCaseid(ConvertString.convertString(s.getCaseInfoId(), "caseid"));
        wsCase.setCasename(ConvertString.convertString(s.getCaseName(), "casename"));
        wsCase.setOrgincharge(ConvertString.convertString(s.getCaseInfoOrg(), "orgincharge"));
        wsCase.setCaseinfo(ConvertString.convertString(s.getCaseDigest(), "caseinfo"));
        wsCase.setComments(ConvertString.convertString(s.getMemo(), "comments"));
        wsCase.setCasesn(ConvertString.convertString(s.getCaseSn(), "casesn"));
        wsCase.setCaseType(ConvertString.convertString(s.getCaseType(), "caseType"));
        wsCase.setDelegateOrgCode(ConvertString.convertString(s.getDelegateOrgCode(), "delegateOrgCode"));
        submitInfoPortTypeService.submitCase(wsCase);
    }

    private void submitWsSampleInfo_forward(SubmitInfoDomain s) {
        WSSampleInfo wsSampleInfo = new WSSampleInfo();

        wsSampleInfo.setCaseid(ConvertString.convertString(s.getCaseInfoId(), "caseid"));
        wsSampleInfo.setSampleid(ConvertString.convertString(s.getSampleId(), "sampleid"));
        wsSampleInfo.setSamplename(ConvertString.convertString(s.getSampleName(), "samplename"));
        wsSampleInfo.setSampleType(s.getSampleType());
        wsSampleInfo.setSubmitoperator(ConvertString.convertString(s.getOperator(), "submitoperator"));
        wsSampleInfo.setSubmitorg(ConvertString.convertString(s.getOperatorOrg(), "submitorg"));
        wsSampleInfo.setPanelid(ConvertString.convertString(s.getPanelName(), "panelid"));

        wsSampleInfo.setSampleNo(ConvertString.convertString(s.getSampleNo(), "sampleNo"));
        wsSampleInfo.setSampleLevel(s.getSampleLevel());
        submitInfoPortTypeService.submitSampleInfo(wsSampleInfo);
    }
}
