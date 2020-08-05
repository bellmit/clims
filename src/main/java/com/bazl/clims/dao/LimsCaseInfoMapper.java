package com.bazl.clims.dao;


import com.bazl.clims.model.po.LimsCaseInfo;
import com.bazl.clims.model.vo.LimsCaseInfoVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface LimsCaseInfoMapper {

    void insertCaseInfo(LimsCaseInfo caseInfo);

    void deleteCaseInfo(LimsCaseInfo caseInfo);

    LimsCaseInfo selectByCaseId(String caseId);

    int selectCountByCaseStatus(@Param("status")String status, @Param("acceptOrgId")String acceptOrgId);

    HashMap selectMonthCountByYear(@Param("year")String year);

    void updateCaseInfoDna(LimsCaseInfo limsCaseInfo);

    List<LimsCaseInfoVo> selectVOPaginationList(LimsCaseInfoVo consignationInfoVo);
    /**
     * 导出查询与补送查询List
     */
    List<LimsCaseInfoVo> selectVOPaginationExportList(LimsCaseInfoVo query);


    int selectVOCount(LimsCaseInfoVo consignationInfoVo);

    void updateHasAppendFlagByCaseId(LimsCaseInfo limsCaseInfo);

    List<LimsCaseInfoVo> selectReplacementRecord(LimsCaseInfoVo limsCaseInfoVo);

    public LimsCaseInfo selectByConsignmentId(String consignmentId);

    public List<HashMap<String, String>> selectCountGroupProperty(@Param("acceptOrgId")String acceptOrgId);

    /**
     * 根据条件查询案件信息
     * @param limsCaseInfoVo
     * @return
     */
    public List<LimsCaseInfoVo> selectVOCaseInfoList(LimsCaseInfoVo limsCaseInfoVo);

    /**
     * 查询本案比对
     * @param limsCaseInfoVo
     * @return
     */
    public List<LimsCaseInfoVo> selectCaseComparePaginationList(LimsCaseInfoVo limsCaseInfoVo);

    /**
     * 查询本案比对count
     * @param limsCaseInfoVo
     * @return
     */
    public int selectCaseCompareCount(LimsCaseInfoVo limsCaseInfoVo);

    public LimsCaseInfoVo selectByCaseIdAndConsignmentId(@Param("caseId")String caseId, @Param("consignmentId")String consignmentId);

    int getExamine(HashMap map);

    /**
     * 查询入国家库
     * @param query
     * @return
     */
    List<LimsCaseInfoVo> selectFindstate(LimsCaseInfoVo query);

    /**
     * 查询国家库数据数量
     * @param query
     * @return
     */
    int selectStateCount(LimsCaseInfoVo query);

    List<LimsCaseInfoVo> selectCaseAll();

    List<LimsCaseInfoVo> selectCaseEntrust();

    /**
     * 查询检出基因型的数据
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
     * 根据案件编号查询案件信息
     * @param caseNo
     * @return
     */
    public List<LimsCaseInfo> selectByCaseNo(String caseNo);

    /**
     * 混合库获取案件信息
     * @return
     */
    public List<LimsCaseInfo> selectCaseInfoListData(LimsCaseInfoVo query);

    /**
     * 查询已入库的所有案件
     */

    List<LimsCaseInfo> selectAllCase();
}