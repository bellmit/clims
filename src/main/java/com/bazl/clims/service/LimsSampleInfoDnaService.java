package com.bazl.clims.service;

import com.bazl.clims.model.PageInfo;
import com.bazl.clims.model.po.LimsSampleInfoDna;
import com.bazl.clims.model.vo.LimsCaseInfoVo;
import com.bazl.clims.model.vo.LimsSampleInfoDnaVo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Repository
public interface LimsSampleInfoDnaService {

    LinkedList<LimsSampleInfoDna> selectByCaseIdAndRy(LimsSampleInfoDna sampleInfoDna);

    LinkedList<LimsSampleInfoDna> selectByCaseIdAndWz(LimsSampleInfoDna sampleInfoDna);

    List<LimsSampleInfoDna> selectChuanBinBySampleId(LimsSampleInfoDnaVo limsSampleInfoDnaVo);

    /**
     * 查询样本数量
     * @param sampleInfoDna
     * @return
     */
    int selectSampleCountByCaseId(LimsSampleInfoDna sampleInfoDna);

    List<LimsSampleInfoDna> selectByCaseId(String caseId);

    List<LimsSampleInfoDna> selectListByConsignmentId(String consignmentId);

    List<LimsSampleInfoDna> selectsampleListRefuseConsignmentId(String consignmentId);

    List<LimsSampleInfoDna> selectPersonListRefuseConsignmentId(String consignmentId);

    LimsSampleInfoDna selectSampleInfoDnaById(String sampleId);

    int selectCount(LimsCaseInfoVo query);

    int selectNotCount(String acceptOrgId);

    /**
     * 查询案件数量根据案件性质分组
     */
    public List<HashMap<String, String>> selectCountGroupSampleType(String acceptOrgId);

    List<LimsSampleInfoDna> selectByCaseNo(String caseNo);

    List<LimsSampleInfoDna> selectBySampleNo(String sampleNo);

    /**
     * 文书打印-检材流转专用方法
     * @param consignmentId
     * @return
     */
    List<LimsSampleInfoDna> selectSampleListByConsignmentId(String consignmentId);

    /**
     * 根据案件id以及委托id查询样本信息分页
     * @param limsSampleInfoDna
     * @return
     */
    List<LimsSampleInfoDnaVo> selectByCaseIdAndRyPagination(LimsSampleInfoDnaVo limsSampleInfoDna, PageInfo pageInfo);

    /**
     * 根据案件id以及委托id查询样本信息count
     * @param limsSampleInfoDna
     * @return
     */
    int selectByCaseIdAndRyCount(LimsSampleInfoDnaVo limsSampleInfoDna);

    /**
     * 根据条件查询信息
     * @param limsSampleInfoDna
     * @return
     */
    public List<LimsSampleInfoDna> selectList(LimsSampleInfoDna limsSampleInfoDna);

    /**
     * 根据条件查询信息
     * @param limsSampleInfoDnaVo
     * @return
     */
    public List<LimsSampleInfoDnaVo> selectVoList(LimsSampleInfoDnaVo limsSampleInfoDnaVo);

    /**
     * 根据id更新入库状态
     * @param sampleInfo
     * @return
     */
    public void updateInstoredFlag(LimsSampleInfoDna sampleInfo);

    /**
     * 根据案件条件查询检材信息
     * @param caseId
     * @return
     */
    public List<LimsSampleInfoDnaVo> selectNotDetected(String caseId);

    public List<LimsSampleInfoDnaVo> selectSampleInfos(String caseId);

    /**
     * 根据委托id
     * @param consignmentId
     * @return
     */
    public List<LimsSampleInfoDna> selectByConsignmentId(String consignmentId);

    /**
     * 根据检材id获取检材数据
     * @param sampleId
     * @return
     */
    List<LimsSampleInfoDna> selectBySampleId(String sampleId);

    /**
     * 获取审核基因通过的检材
     * @param caseId
     * @return
     */
    List<LimsSampleInfoDna> selectSampleDnaByCaseId(String caseId);

    /**
     * 根据编号查询已经入库的检材信息
     * @param sampleNo
     * @return
     */
    public List<LimsSampleInfoDna> selectBySampleNoAlreadyStorage(String sampleNo);

    /**
     * 判断检材是否入库
     * @param sampleId
     * @return
     */
    public boolean isStoredBySampleId(String sampleId);

    /**
     * 根据案件id查询已经上报国家库检材信息
     * @param caseId
     * @return
     */
    public List<LimsSampleInfoDna> selectListByCaseIdInstored(String caseId);

    /**
     * 根据案件id查询物证查询
     * @param caseId
     * @return
     */
    List<LimsSampleInfoDna> selectMatchCaseId(String caseId);

    /*
    *   统计受理检材数
    * */
    int selectAcceptCount(LimsCaseInfoVo query);
    /*
   *   统计复核检材数
   * */
    int selectAuditCount(LimsCaseInfoVo query);
    /*
  *   统计已入库检材数
  * */
    int selectInstoredSampleCount(LimsCaseInfoVo query);
    /**
     * 查询受理检材
     * @return
     */
    List<LimsSampleInfoDna> selectAccept(LimsCaseInfoVo query);

    /**
     * 根据案件id获取以及审核通过的检材
     */
    public List<LimsSampleInfoDna> selectAuditSampleByCaseId(String caseId);

    /**
     * 混合库获取检材信息
     * @return
     */
    public List<LimsSampleInfoDna> selectSampleInfoListData(LimsSampleInfoDnaVo query,int page);

    /**
     * 更新预实验记录
     * @param sampleInfoDna
     * @return
     */
    int updatePre(LimsSampleInfoDna sampleInfoDna);

    /**
     * 根据物证编号查询检材信息
     * @param evidenceNo
     * @return
     */
    public List<LimsSampleInfoDna> selectCaseInfoListData(String evidenceNo);

    int updateSamples(LimsSampleInfoDna limsSampleInfoDna);
}