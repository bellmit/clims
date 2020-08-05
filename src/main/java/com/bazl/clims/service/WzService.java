package com.bazl.clims.service;

import com.bazl.clims.webservice.domain.LibMatchResultGroupDomain;
import com.bazl.clims.webservice.domain.SubmitInfoDomain;
import com.bazl.clims.webservice.domain.TotalRelativeMatchResult;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by huawei on 2019/9/17.
 */
@Repository
public interface WzService {

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
    public Map<String, Object> parentageSubmitAndMatch(SubmitInfoDomain s1,
                                                       SubmitInfoDomain s2, int val1, int val2, int val0);

    /**
     * 入库并提交基因型
     *
     * @return 返回详细结果
     */
    public Map<String, Object> submitAndMatch(SubmitInfoDomain s1, int val,
                                              int val0);

    /**
     * 提交基因型
     * @param sl
     * @param val
     * @param val0
     * @return
     */
    public int submitGenotype(SubmitInfoDomain sl, int val, int val0);

    /**
     * 提交人员信息
     * @param s	人员信息
     */
    public void submitWsPerson(SubmitInfoDomain s);

    /**
     * 提交案件信息
     * @param s	案件信息
     */
    public void submitWsCase(SubmitInfoDomain s);

    /**
     * 提交样本信息
     * @param s	样本信息
     */
    public void submitWsSampleInfo(SubmitInfoDomain s);

    /**
     * 查询跨区比对结果信息
     * @param sampleID
     * @param sampleName
     * @param caseName
     * @param sampleType
     * @param matchType
     * @param status
     * @param nameStatus
     * @param groupType
     * @param sameGender
     * @param matchTimeStart
     * @param matchTimeEnd
     * @param delegateOrgCode
     * @param caseId
     * @param matchCount
     * @param pageIndex
     * @param recordCount
     * @return
     */
    public List<LibMatchResultGroupDomain> libMatchRecordGroupList(
            String sampleID, String sampleName, String caseName,
            String sampleType, String matchType, String status ,String nameStatus,String groupType,
            String sameGender,String matchTimeStart ,String matchTimeEnd,
            String delegateOrgCode , String caseId ,String matchCount , int pageIndex, int recordCount);


    /**
     * 比对结果
     *
     * @param status
     *            状态
     * @param barcode
     *            条码号
     * @param matchId
     *            比中编号
     * @param sourceCaseName
     *            案件名称
     * @param sampleName
     *            检材名称
     * @param submitOperator
     *            检验人
     * @param targetSampleType
     *            匹配类型
     * @param pageIndex
     *            页码
     * @param recordCount
     *            每页条数
     * @return 返回详细结果列表
     */
    public List<Map<String, Object>> libMatchRecordList(String status,
                                                        String barcode, String matchId, String sourceCaseName,
                                                        String sampleName, String submitOperator, String targetSampleType,
                                                        String groupID, int pageIndex, int recordCount);

    /**
     * 获取亲缘比对结果信息
     * @param matchid
     * @param piLength
     * @return
     */
    public TotalRelativeMatchResult getLibMatchResult(String matchid, int piLength);
}
