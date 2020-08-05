package com.bazl.clims.webservice.services;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.0.11
 * 2019-09-23T10:35:56.761+08:00
 * Generated source version: 3.0.11
 * 
 */
@WebService(targetNamespace = "http://services.webservice.lims.dna.com", name = "QueryDataServicePortType")
@XmlSeeAlso({ObjectFactory.class})
public interface QueryDataServicePortType {

    @WebMethod
    @RequestWrapper(localName = "getCaseInfoByCaseId", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetCaseInfoByCaseId")
    @ResponseWrapper(localName = "getCaseInfoByCaseIdResponse", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetCaseInfoByCaseIdResponse")
    @WebResult(name = "out", targetNamespace = "http://services.webservice.lims.dna.com")
    public CaseInfo getCaseInfoByCaseId(
            @WebParam(name = "in0", targetNamespace = "http://services.webservice.lims.dna.com")
            String in0
    );

    @WebMethod
    @RequestWrapper(localName = "getCaseInfoByCaseSn", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetCaseInfoByCaseSn")
    @ResponseWrapper(localName = "getCaseInfoByCaseSnResponse", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetCaseInfoByCaseSnResponse")
    @WebResult(name = "out", targetNamespace = "http://services.webservice.lims.dna.com")
    public CaseInfo getCaseInfoByCaseSn(
            @WebParam(name = "in0", targetNamespace = "http://services.webservice.lims.dna.com")
            String in0
    );

    @WebMethod
    @RequestWrapper(localName = "getCaseInfoListByCaseSn", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetCaseInfoListByCaseSn")
    @ResponseWrapper(localName = "getCaseInfoListByCaseSnResponse", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetCaseInfoListByCaseSnResponse")
    @WebResult(name = "out", targetNamespace = "http://services.webservice.lims.dna.com")
    public ArrayOfCaseInfo getCaseInfoListByCaseSn(
            @WebParam(name = "in0", targetNamespace = "http://services.webservice.lims.dna.com")
            String in0,
            @WebParam(name = "in1", targetNamespace = "http://services.webservice.lims.dna.com")
            boolean in1
    );

    @WebMethod
    @RequestWrapper(localName = "getSampleGeneListByBarcode", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetSampleGeneListByBarcode")
    @ResponseWrapper(localName = "getSampleGeneListByBarcodeResponse", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetSampleGeneListByBarcodeResponse")
    @WebResult(name = "out", targetNamespace = "http://services.webservice.lims.dna.com")
    public ArrayOfSampleGene getSampleGeneListByBarcode(
            @WebParam(name = "in0", targetNamespace = "http://services.webservice.lims.dna.com")
            String in0,
            @WebParam(name = "in1", targetNamespace = "http://services.webservice.lims.dna.com")
            String in1
    );

    @WebMethod
    @RequestWrapper(localName = "querySampleCount", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.QuerySampleCount")
    @ResponseWrapper(localName = "querySampleCountResponse", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.QuerySampleCountResponse")
    @WebResult(name = "out", targetNamespace = "http://services.webservice.lims.dna.com")
    public int querySampleCount(
            @WebParam(name = "in0", targetNamespace = "http://services.webservice.lims.dna.com")
            String in0,
            @WebParam(name = "in1", targetNamespace = "http://services.webservice.lims.dna.com")
            String in1
    );

    @WebMethod
    @RequestWrapper(localName = "getInspectionRecordReport", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetInspectionRecordReport")
    @ResponseWrapper(localName = "getInspectionRecordReportResponse", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetInspectionRecordReportResponse")
    @WebResult(name = "out", targetNamespace = "http://services.webservice.lims.dna.com")
    public byte[] getInspectionRecordReport(
            @WebParam(name = "in0", targetNamespace = "http://services.webservice.lims.dna.com")
            String in0,
            @WebParam(name = "in1", targetNamespace = "http://services.webservice.lims.dna.com")
            String in1,
            @WebParam(name = "in2", targetNamespace = "http://services.webservice.lims.dna.com")
            String in2
    );

    @WebMethod
    @RequestWrapper(localName = "getLocalPentalCompareResult", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetLocalPentalCompareResult")
    @ResponseWrapper(localName = "getLocalPentalCompareResultResponse", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetLocalPentalCompareResultResponse")
    @WebResult(name = "out", targetNamespace = "http://services.webservice.lims.dna.com")
    public String getLocalPentalCompareResult(
            @WebParam(name = "in0", targetNamespace = "http://services.webservice.lims.dna.com")
            String in0,
            @WebParam(name = "in1", targetNamespace = "http://services.webservice.lims.dna.com")
            String in1,
            @WebParam(name = "in2", targetNamespace = "http://services.webservice.lims.dna.com")
            String in2
    );

    @WebMethod
    @RequestWrapper(localName = "getCombineRelatedCaseInfo", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetCombineRelatedCaseInfo")
    @ResponseWrapper(localName = "getCombineRelatedCaseInfoResponse", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetCombineRelatedCaseInfoResponse")
    @WebResult(name = "out", targetNamespace = "http://services.webservice.lims.dna.com")
    public ArrayOfMatchResultDna getCombineRelatedCaseInfo(
            @WebParam(name = "in0", targetNamespace = "http://services.webservice.lims.dna.com")
            String in0,
            @WebParam(name = "in1", targetNamespace = "http://services.webservice.lims.dna.com")
            int in1,
            @WebParam(name = "in2", targetNamespace = "http://services.webservice.lims.dna.com")
            int in2
    );

    @WebMethod
    @RequestWrapper(localName = "isExpertiseReportExists", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.IsExpertiseReportExists")
    @ResponseWrapper(localName = "isExpertiseReportExistsResponse", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.IsExpertiseReportExistsResponse")
    @WebResult(name = "out", targetNamespace = "http://services.webservice.lims.dna.com")
    public String isExpertiseReportExists(
            @WebParam(name = "in0", targetNamespace = "http://services.webservice.lims.dna.com")
            String in0,
            @WebParam(name = "in1", targetNamespace = "http://services.webservice.lims.dna.com")
            String in1,
            @WebParam(name = "in2", targetNamespace = "http://services.webservice.lims.dna.com")
            String in2
    );

    @WebMethod
    @RequestWrapper(localName = "getTestInfo", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetTestInfo")
    @ResponseWrapper(localName = "getTestInfoResponse", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetTestInfoResponse")
    @WebResult(name = "out", targetNamespace = "http://services.webservice.lims.dna.com")
    public String getTestInfo(
            @WebParam(name = "in0", targetNamespace = "http://services.webservice.lims.dna.com")
            String in0,
            @WebParam(name = "in1", targetNamespace = "http://services.webservice.lims.dna.com")
            String in1,
            @WebParam(name = "in2", targetNamespace = "http://services.webservice.lims.dna.com")
            String in2
    );

    @WebMethod
    @RequestWrapper(localName = "getMemberInfoByCaseId", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetMemberInfoByCaseId")
    @ResponseWrapper(localName = "getMemberInfoByCaseIdResponse", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetMemberInfoByCaseIdResponse")
    @WebResult(name = "out", targetNamespace = "http://services.webservice.lims.dna.com")
    public ArrayOfMemberInfo getMemberInfoByCaseId(
            @WebParam(name = "in0", targetNamespace = "http://services.webservice.lims.dna.com")
            String in0
    );

    @WebMethod
    @RequestWrapper(localName = "getSampleGeneByBoardBarcode", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetSampleGeneByBoardBarcode")
    @ResponseWrapper(localName = "getSampleGeneByBoardBarcodeResponse", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetSampleGeneByBoardBarcodeResponse")
    @WebResult(name = "out", targetNamespace = "http://services.webservice.lims.dna.com")
    public ArrayOfSampleGene getSampleGeneByBoardBarcode(
            @WebParam(name = "in0", targetNamespace = "http://services.webservice.lims.dna.com")
            String in0
    );

    @WebMethod
    @RequestWrapper(localName = "getTestReport", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetTestReport")
    @ResponseWrapper(localName = "getTestReportResponse", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetTestReportResponse")
    @WebResult(name = "out", targetNamespace = "http://services.webservice.lims.dna.com")
    public byte[] getTestReport(
            @WebParam(name = "in0", targetNamespace = "http://services.webservice.lims.dna.com")
            String in0,
            @WebParam(name = "in1", targetNamespace = "http://services.webservice.lims.dna.com")
            String in1,
            @WebParam(name = "in2", targetNamespace = "http://services.webservice.lims.dna.com")
            String in2,
            @WebParam(name = "in3", targetNamespace = "http://services.webservice.lims.dna.com")
            String in3
    );

    @WebMethod
    @RequestWrapper(localName = "getMemberInfoByBarcode", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetMemberInfoByBarcode")
    @ResponseWrapper(localName = "getMemberInfoByBarcodeResponse", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetMemberInfoByBarcodeResponse")
    @WebResult(name = "out", targetNamespace = "http://services.webservice.lims.dna.com")
    public MemberInfo getMemberInfoByBarcode(
            @WebParam(name = "in0", targetNamespace = "http://services.webservice.lims.dna.com")
            String in0
    );

    @WebMethod
    @RequestWrapper(localName = "getMemberInfoByMemberId", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetMemberInfoByMemberId")
    @ResponseWrapper(localName = "getMemberInfoByMemberIdResponse", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetMemberInfoByMemberIdResponse")
    @WebResult(name = "out", targetNamespace = "http://services.webservice.lims.dna.com")
    public MemberInfo getMemberInfoByMemberId(
            @WebParam(name = "in0", targetNamespace = "http://services.webservice.lims.dna.com")
            String in0
    );

    @WebMethod
    @RequestWrapper(localName = "getIncorporateCaseExpertiseReport", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetIncorporateCaseExpertiseReport")
    @ResponseWrapper(localName = "getIncorporateCaseExpertiseReportResponse", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetIncorporateCaseExpertiseReportResponse")
    @WebResult(name = "out", targetNamespace = "http://services.webservice.lims.dna.com")
    public byte[] getIncorporateCaseExpertiseReport(
            @WebParam(name = "in0", targetNamespace = "http://services.webservice.lims.dna.com")
            String in0,
            @WebParam(name = "in1", targetNamespace = "http://services.webservice.lims.dna.com")
            String in1,
            @WebParam(name = "in2", targetNamespace = "http://services.webservice.lims.dna.com")
            String in2,
            @WebParam(name = "in3", targetNamespace = "http://services.webservice.lims.dna.com")
            String in3,
            @WebParam(name = "in4", targetNamespace = "http://services.webservice.lims.dna.com")
            String in4
    );

    @WebMethod
    @RequestWrapper(localName = "changeStatusInMatchResult", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.ChangeStatusInMatchResult")
    @ResponseWrapper(localName = "changeStatusInMatchResultResponse", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.ChangeStatusInMatchResultResponse")
    @WebResult(name = "out", targetNamespace = "http://services.webservice.lims.dna.com")
    public int changeStatusInMatchResult(
            @WebParam(name = "in0", targetNamespace = "http://services.webservice.lims.dna.com")
            String in0,
            @WebParam(name = "in1", targetNamespace = "http://services.webservice.lims.dna.com")
            String in1,
            @WebParam(name = "in2", targetNamespace = "http://services.webservice.lims.dna.com")
            String in2
    );

    @WebMethod
    @RequestWrapper(localName = "getStoreSamples", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetStoreSamples")
    @ResponseWrapper(localName = "getStoreSamplesResponse", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetStoreSamplesResponse")
    @WebResult(name = "out", targetNamespace = "http://services.webservice.lims.dna.com")
    public ArrayOfSampleInfo getStoreSamples(
            @WebParam(name = "in0", targetNamespace = "http://services.webservice.lims.dna.com")
            String in0,
            @WebParam(name = "in1", targetNamespace = "http://services.webservice.lims.dna.com")
            String in1,
            @WebParam(name = "in2", targetNamespace = "http://services.webservice.lims.dna.com")
            String in2,
            @WebParam(name = "in3", targetNamespace = "http://services.webservice.lims.dna.com")
            String in3,
            @WebParam(name = "in4", targetNamespace = "http://services.webservice.lims.dna.com")
            String in4,
            @WebParam(name = "in5", targetNamespace = "http://services.webservice.lims.dna.com")
            String in5,
            @WebParam(name = "in6", targetNamespace = "http://services.webservice.lims.dna.com")
            String in6,
            @WebParam(name = "in7", targetNamespace = "http://services.webservice.lims.dna.com")
            String in7,
            @WebParam(name = "in8", targetNamespace = "http://services.webservice.lims.dna.com")
            String in8,
            @WebParam(name = "in9", targetNamespace = "http://services.webservice.lims.dna.com")
            String in9,
            @WebParam(name = "in10", targetNamespace = "http://services.webservice.lims.dna.com")
            String in10,
            @WebParam(name = "in11", targetNamespace = "http://services.webservice.lims.dna.com")
            int in11,
            @WebParam(name = "in12", targetNamespace = "http://services.webservice.lims.dna.com")
            int in12
    );

    @WebMethod
    @RequestWrapper(localName = "getTestReportBySamples", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetTestReportBySamples")
    @ResponseWrapper(localName = "getTestReportBySamplesResponse", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetTestReportBySamplesResponse")
    @WebResult(name = "out", targetNamespace = "http://services.webservice.lims.dna.com")
    public String getTestReportBySamples(
            @WebParam(name = "in0", targetNamespace = "http://services.webservice.lims.dna.com")
            ArrayOfString in0,
            @WebParam(name = "in1", targetNamespace = "http://services.webservice.lims.dna.com")
            String in1,
            @WebParam(name = "in2", targetNamespace = "http://services.webservice.lims.dna.com")
            String in2,
            @WebParam(name = "in3", targetNamespace = "http://services.webservice.lims.dna.com")
            String in3
    );

    @WebMethod
    @RequestWrapper(localName = "getDnaMatchInfoNoUnion", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetDnaMatchInfoNoUnion")
    @ResponseWrapper(localName = "getDnaMatchInfoNoUnionResponse", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetDnaMatchInfoNoUnionResponse")
    @WebResult(name = "out", targetNamespace = "http://services.webservice.lims.dna.com")
    public ArrayOfDnaMatchInfo getDnaMatchInfoNoUnion(
            @WebParam(name = "in0", targetNamespace = "http://services.webservice.lims.dna.com")
            String in0
    );

    @WebMethod
    @RequestWrapper(localName = "getSampleGeneById", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetSampleGeneById")
    @ResponseWrapper(localName = "getSampleGeneByIdResponse", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetSampleGeneByIdResponse")
    @WebResult(name = "out", targetNamespace = "http://services.webservice.lims.dna.com")
    public SampleGene getSampleGeneById(
            @WebParam(name = "in0", targetNamespace = "http://services.webservice.lims.dna.com")
            String in0
    );

    @WebMethod
    @RequestWrapper(localName = "getSampleInfoByBarCode", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetSampleInfoByBarCode2")
    @ResponseWrapper(localName = "getSampleInfoByBarCodeResponse", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetSampleInfoByBarCodeResponse2")
    @WebResult(name = "out", targetNamespace = "http://services.webservice.lims.dna.com")
    public SampleInfo getSampleInfoByBarCode(
            @WebParam(name = "in0", targetNamespace = "http://services.webservice.lims.dna.com")
            String in0
    );

    @WebMethod
    @RequestWrapper(localName = "isIncorporateCaseExists", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.IsIncorporateCaseExists")
    @ResponseWrapper(localName = "isIncorporateCaseExistsResponse", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.IsIncorporateCaseExistsResponse")
    @WebResult(name = "out", targetNamespace = "http://services.webservice.lims.dna.com")
    public String isIncorporateCaseExists(
            @WebParam(name = "in0", targetNamespace = "http://services.webservice.lims.dna.com")
            String in0,
            @WebParam(name = "in1", targetNamespace = "http://services.webservice.lims.dna.com")
            String in1,
            @WebParam(name = "in2", targetNamespace = "http://services.webservice.lims.dna.com")
            String in2
    );

    @WebMethod
    @RequestWrapper(localName = "getAccountList", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetAccountList")
    @ResponseWrapper(localName = "getAccountListResponse", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetAccountListResponse")
    @WebResult(name = "out", targetNamespace = "http://services.webservice.lims.dna.com")
    public ArrayOfAccount getAccountList();

    @WebMethod
    @RequestWrapper(localName = "isTestReportExists", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.IsTestReportExists")
    @ResponseWrapper(localName = "isTestReportExistsResponse", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.IsTestReportExistsResponse")
    @WebResult(name = "out", targetNamespace = "http://services.webservice.lims.dna.com")
    public String isTestReportExists(
            @WebParam(name = "in0", targetNamespace = "http://services.webservice.lims.dna.com")
            String in0,
            @WebParam(name = "in1", targetNamespace = "http://services.webservice.lims.dna.com")
            String in1,
            @WebParam(name = "in2", targetNamespace = "http://services.webservice.lims.dna.com")
            String in2
    );

    @WebMethod
    @RequestWrapper(localName = "getSampleInfoByCaseId", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetSampleInfoByCaseId")
    @ResponseWrapper(localName = "getSampleInfoByCaseIdResponse", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetSampleInfoByCaseIdResponse")
    @WebResult(name = "out", targetNamespace = "http://services.webservice.lims.dna.com")
    public ArrayOfSampleInfo getSampleInfoByCaseId(
            @WebParam(name = "in0", targetNamespace = "http://services.webservice.lims.dna.com")
            String in0
    );

    @WebMethod
    @RequestWrapper(localName = "getDictItemList", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetDictItemList")
    @ResponseWrapper(localName = "getDictItemListResponse", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetDictItemListResponse")
    @WebResult(name = "out", targetNamespace = "http://services.webservice.lims.dna.com")
    public ArrayOfDictItem getDictItemList(
            @WebParam(name = "in0", targetNamespace = "http://services.webservice.lims.dna.com")
            String in0
    );

    @WebMethod
    @RequestWrapper(localName = "getMatchSampleInfoByGroupId", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetMatchSampleInfoByGroupId")
    @ResponseWrapper(localName = "getMatchSampleInfoByGroupIdResponse", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetMatchSampleInfoByGroupIdResponse")
    @WebResult(name = "out", targetNamespace = "http://services.webservice.lims.dna.com")
    public ArrayOfMatchSampleInfo getMatchSampleInfoByGroupId(
            @WebParam(name = "in0", targetNamespace = "http://services.webservice.lims.dna.com")
            String in0
    );

    @WebMethod
    @RequestWrapper(localName = "changeSubmitStatusInMatchResult", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.ChangeSubmitStatusInMatchResult")
    @ResponseWrapper(localName = "changeSubmitStatusInMatchResultResponse", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.ChangeSubmitStatusInMatchResultResponse")
    @WebResult(name = "out", targetNamespace = "http://services.webservice.lims.dna.com")
    public int changeSubmitStatusInMatchResult(
            @WebParam(name = "in0", targetNamespace = "http://services.webservice.lims.dna.com")
            String in0,
            @WebParam(name = "in1", targetNamespace = "http://services.webservice.lims.dna.com")
            String in1,
            @WebParam(name = "in2", targetNamespace = "http://services.webservice.lims.dna.com")
            String in2,
            @WebParam(name = "in3", targetNamespace = "http://services.webservice.lims.dna.com")
            String in3
    );

    @WebMethod
    @RequestWrapper(localName = "getSampleInfoByBarcode", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetSampleInfoByBarcode")
    @ResponseWrapper(localName = "getSampleInfoByBarcodeResponse", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetSampleInfoByBarcodeResponse")
    @WebResult(name = "out", targetNamespace = "http://services.webservice.lims.dna.com")
    public SampleInfo getSampleInfoByBarcode(
            @WebParam(name = "in0", targetNamespace = "http://services.webservice.lims.dna.com")
            String in0
    );

    @WebMethod
    @RequestWrapper(localName = "getCaseSampleInfoList", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetCaseSampleInfoList")
    @ResponseWrapper(localName = "getCaseSampleInfoListResponse", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetCaseSampleInfoListResponse")
    @WebResult(name = "out", targetNamespace = "http://services.webservice.lims.dna.com")
    public ArrayOfSampleInfo getCaseSampleInfoList(
            @WebParam(name = "in0", targetNamespace = "http://services.webservice.lims.dna.com")
            String in0,
            @WebParam(name = "in1", targetNamespace = "http://services.webservice.lims.dna.com")
            javax.xml.datatype.XMLGregorianCalendar in1
    );

    @WebMethod
    @RequestWrapper(localName = "getStoreSamplesCnt", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetStoreSamplesCnt")
    @ResponseWrapper(localName = "getStoreSamplesCntResponse", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetStoreSamplesCntResponse")
    @WebResult(name = "out", targetNamespace = "http://services.webservice.lims.dna.com")
    public int getStoreSamplesCnt(
            @WebParam(name = "in0", targetNamespace = "http://services.webservice.lims.dna.com")
            String in0,
            @WebParam(name = "in1", targetNamespace = "http://services.webservice.lims.dna.com")
            String in1,
            @WebParam(name = "in2", targetNamespace = "http://services.webservice.lims.dna.com")
            String in2,
            @WebParam(name = "in3", targetNamespace = "http://services.webservice.lims.dna.com")
            String in3,
            @WebParam(name = "in4", targetNamespace = "http://services.webservice.lims.dna.com")
            String in4,
            @WebParam(name = "in5", targetNamespace = "http://services.webservice.lims.dna.com")
            String in5,
            @WebParam(name = "in6", targetNamespace = "http://services.webservice.lims.dna.com")
            String in6,
            @WebParam(name = "in7", targetNamespace = "http://services.webservice.lims.dna.com")
            String in7,
            @WebParam(name = "in8", targetNamespace = "http://services.webservice.lims.dna.com")
            String in8,
            @WebParam(name = "in9", targetNamespace = "http://services.webservice.lims.dna.com")
            String in9,
            @WebParam(name = "in10", targetNamespace = "http://services.webservice.lims.dna.com")
            String in10
    );

    @WebMethod
    @RequestWrapper(localName = "getSampleInfoById", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetSampleInfoById")
    @ResponseWrapper(localName = "getSampleInfoByIdResponse", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetSampleInfoByIdResponse")
    @WebResult(name = "out", targetNamespace = "http://services.webservice.lims.dna.com")
    public SampleInfo getSampleInfoById(
            @WebParam(name = "in0", targetNamespace = "http://services.webservice.lims.dna.com")
            String in0
    );

    @WebMethod
    @RequestWrapper(localName = "getCaseInfoByBarcode", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetCaseInfoByBarcode")
    @ResponseWrapper(localName = "getCaseInfoByBarcodeResponse", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetCaseInfoByBarcodeResponse")
    @WebResult(name = "out", targetNamespace = "http://services.webservice.lims.dna.com")
    public CaseInfo getCaseInfoByBarcode(
            @WebParam(name = "in0", targetNamespace = "http://services.webservice.lims.dna.com")
            String in0
    );

    @WebMethod
    @RequestWrapper(localName = "getExpertiseReport", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetExpertiseReport")
    @ResponseWrapper(localName = "getExpertiseReportResponse", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetExpertiseReportResponse")
    @WebResult(name = "out", targetNamespace = "http://services.webservice.lims.dna.com")
    public byte[] getExpertiseReport(
            @WebParam(name = "in0", targetNamespace = "http://services.webservice.lims.dna.com")
            String in0,
            @WebParam(name = "in1", targetNamespace = "http://services.webservice.lims.dna.com")
            String in1,
            @WebParam(name = "in2", targetNamespace = "http://services.webservice.lims.dna.com")
            String in2,
            @WebParam(name = "in3", targetNamespace = "http://services.webservice.lims.dna.com")
            String in3,
            @WebParam(name = "in4", targetNamespace = "http://services.webservice.lims.dna.com")
            String in4
    );

    @WebMethod
    @RequestWrapper(localName = "getSampleGeneByBarcode", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetSampleGeneByBarcode")
    @ResponseWrapper(localName = "getSampleGeneByBarcodeResponse", targetNamespace = "http://services.webservice.lims.dna.com", className = "com.bazl.clims.webservice.services.GetSampleGeneByBarcodeResponse")
    @WebResult(name = "out", targetNamespace = "http://services.webservice.lims.dna.com")
    public SampleGene getSampleGeneByBarcode(
            @WebParam(name = "in0", targetNamespace = "http://services.webservice.lims.dna.com")
            String in0
    );
}
