package com.bazl.clims.service;

import com.bazl.clims.model.LoaUserInfo;
import com.bazl.clims.model.PageInfo;
import com.bazl.clims.model.bo.DelegateDataModel;
import com.bazl.clims.model.po.LimsCaseInfo;
import com.bazl.clims.model.vo.LimsCaseInfoVo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface LimsCaseInfoService {

    LimsCaseInfo selectByCaseId(String caseId);

    /**
     * 查询案件数量
     */
    public int selectCountByCaseStatus(String status, String acceptOrgId);

    /**
     * 根据年份获取各个月份的案件数
     */
    public HashMap selectMonthCountByYear(String year);

    //查询与补送查询List
    public List<LimsCaseInfoVo> selectCaseInfoList(LimsCaseInfoVo query, PageInfo pageInfo);
    //导出查询与补送查询List
    List<LimsCaseInfoVo> selectVOPaginationExportList(LimsCaseInfoVo query);

    //查询与补送查询count
    public int selectVOCount(LimsCaseInfoVo query);

    //根据现勘编号查询现堪数据
    public Map<String, Object> findCaseSampleInfoByXkNo(String xkNo);

    //查询补送记录
    public List<LimsCaseInfoVo> selectReplacementRecord(LimsCaseInfoVo limsCaseInfoVo);

    public LimsCaseInfo selectByConsignmentId(String consignmentId);

    //确认受理
    public boolean submitAcceptCase(DelegateDataModel delegateDataModel, LoaUserInfo operateUser, String personIds, String sampleIdWzs, String sampleIds);

    /**
     * 查询案件数量根据案件性质分组
     */
    public List<HashMap<String, String>> selectCountGroupProperty(String acceptOrgId);

    //修改案件
    public boolean updateCase(DelegateDataModel delegateDataModel, LoaUserInfo operateUser, String personIds, String sampleIdWzs, String sampleIds);

    /**
     * 根据条件查询案件信息
     * @param limsCaseInfoVo
     * @return
     */
    public List<LimsCaseInfoVo> selectVOCaseInfoList(LimsCaseInfoVo limsCaseInfoVo);

    /**
     * 查询本案比对
     * @param query
     * @param pageInfo
     * @return
     */
    public List<LimsCaseInfoVo> selectCaseCompare(LimsCaseInfoVo query, PageInfo pageInfo);

    /**
     * 查询本案比对count
     * @param query
     * @return
     */
    public int selectCaseCompareCount(LimsCaseInfoVo query);

    public LimsCaseInfoVo selectByCaseIdAndConsignmentId(String caseId, String consignmentId);

    int getExamine(HashMap map);

    /**
     * 查询入国家库数据
     * @param query
     * @param pageInfo
     * @return
     */
    List<LimsCaseInfoVo> selectFindstate(LimsCaseInfoVo query, PageInfo pageInfo);

    /**
     *  查询入国家库数量
     * @param query
     * @return
     */
    int selectStateCount(LimsCaseInfoVo query);

    /**
     * 查询已受理案件信息
     * @return
     */
    List<LimsCaseInfoVo> selectCaseAll();

    /**
     * 查询已委托案件信息
     * @return
     */
    List<LimsCaseInfoVo> selectCaseEntrust();


    /**
     * 查询案件检出基因型
     * @return
     */
    List<LimsCaseInfoVo> selectCaseMatch();

    /*
    *   案件统计
    * */
    int selectFirsInsCount(LimsCaseInfoVo query);
    /*
    *   案件入库统计
    * */
    int selectInstoredCount(LimsCaseInfoVo query);

    /**
     * 根据案件编号获取信息
     * @param caseNo
     * @return
     */
    public LimsCaseInfo selectByCaseNo(String caseNo);

    /**
     * 混合库获取案件信息
     * @param page
     * @return
     */
    public List<LimsCaseInfo> selectCaseInfoListData(LimsCaseInfoVo query,int page);
    
}