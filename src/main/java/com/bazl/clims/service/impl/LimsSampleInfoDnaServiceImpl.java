package com.bazl.clims.service.impl;

import com.bazl.clims.dao.LimsSampleInfoDnaMapper;
import com.bazl.clims.model.PageInfo;
import com.bazl.clims.model.po.LimsSampleInfoDna;
import com.bazl.clims.model.vo.LimsCaseInfoVo;
import com.bazl.clims.model.vo.LimsSampleInfoDnaVo;
import com.bazl.clims.service.LimsSampleInfoDnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by hj on 2018/12/20.
 */
@Service
public class LimsSampleInfoDnaServiceImpl extends BaseService implements LimsSampleInfoDnaService {

    @Autowired
    LimsSampleInfoDnaMapper limsSampleInfoDnaMapper;

    /**
     * 查询人员样本
     * @param sampleInfoDna
     * @return
     */
    @Override
    public LinkedList<LimsSampleInfoDna> selectByCaseIdAndRy(LimsSampleInfoDna sampleInfoDna) {
        LinkedList<LimsSampleInfoDna> limsPersonInfoList = limsSampleInfoDnaMapper.selectByCaseIdAndRy(sampleInfoDna);
        return limsPersonInfoList;
    }

    /**
     * 查询检材
     * @param sampleInfoDna
     * @return
     */
    @Override
    public LinkedList<LimsSampleInfoDna> selectByCaseIdAndWz(LimsSampleInfoDna sampleInfoDna) {
        LinkedList<LimsSampleInfoDna> limsPersonInfoList = limsSampleInfoDnaMapper.selectByCaseIdAndWz(sampleInfoDna);
        return limsPersonInfoList;
    }

    @Override
    public List<LimsSampleInfoDna> selectChuanBinBySampleId(LimsSampleInfoDnaVo limsSampleInfoDnaVo) {
        return limsSampleInfoDnaMapper.selectChuanBinBySampleId(limsSampleInfoDnaVo);
    }

    @Override
    public int selectSampleCountByCaseId(LimsSampleInfoDna sampleInfoDna) {
        return limsSampleInfoDnaMapper.selectSampleCountByCaseId(sampleInfoDna);
    }

    /**
     * 根据案件id查询物证信息
     * @param caseId
     * @return
     */
    @Override
    public List<LimsSampleInfoDna> selectByCaseId(String caseId) {
        List<LimsSampleInfoDna> limsPersonInfoList = limsSampleInfoDnaMapper.selectByCaseId(caseId);
        return limsPersonInfoList;
    }

    @Override
    public List<LimsSampleInfoDna> selectListByConsignmentId(String consignmentId) {
        List<LimsSampleInfoDna> limsSampleInfoDnaList = limsSampleInfoDnaMapper.selectListByConsignmentId(consignmentId);
        return limsSampleInfoDnaList;
    }

    @Override
    public List<LimsSampleInfoDna> selectsampleListRefuseConsignmentId(String consignmentId) {
        List<LimsSampleInfoDna> limsSampleInfoDnaList = limsSampleInfoDnaMapper.selectsampleListRefuseConsignmentId(consignmentId);
        return limsSampleInfoDnaList;
    }

    @Override
    public List<LimsSampleInfoDna> selectPersonListRefuseConsignmentId(String consignmentId) {
        List<LimsSampleInfoDna> limsSampleInfoDnaList = limsSampleInfoDnaMapper.selectPersonListRefuseConsignmentId(consignmentId);
        return limsSampleInfoDnaList;
    }


    @Override
    public LimsSampleInfoDna selectSampleInfoDnaById(String sampleId) {
        LimsSampleInfoDna sampleInfoDna = limsSampleInfoDnaMapper.selectSampleInfoDnaById(sampleId);
        return sampleInfoDna;
    }

    @Override
    public int selectCount(LimsCaseInfoVo query){
        int result = limsSampleInfoDnaMapper.selectCount(query);
        return result;
    }

    @Override
    public int selectNotCount(String acceptOrgId) {
        return limsSampleInfoDnaMapper.selectNotCount(acceptOrgId);
    }

    /**
     * 查询案件数量
     */
    @Override
    public List<HashMap<String, String>> selectCountGroupSampleType(String acceptOrgId) {
        return limsSampleInfoDnaMapper.selectCountGroupSampleType(acceptOrgId);
    }

    /**
     * 根据案件编号查询物证信息
     * @param caseNo
     * @return
     */
    @Override
    public List<LimsSampleInfoDna> selectByCaseNo(String caseNo) {
        List<LimsSampleInfoDna> limsPersonInfoList = limsSampleInfoDnaMapper.selectByCaseNo(caseNo);
        return limsPersonInfoList;
    }

    /**
     * 根据检材编号查询物证信息
     * @param sampleNo
     * @return
     */
    @Override
    public List<LimsSampleInfoDna> selectBySampleNo(String sampleNo) {
        List<LimsSampleInfoDna> limsPersonInfoList = limsSampleInfoDnaMapper.selectBySampleNo(sampleNo);
        return limsPersonInfoList;
    }

    /**
     * 文书打印-检材流转专用方法
     * @param consignmentId
     * @return
     */
    @Override
    public List<LimsSampleInfoDna> selectSampleListByConsignmentId(String consignmentId) {
        List<LimsSampleInfoDna> limsSampleInfoDnaList = limsSampleInfoDnaMapper.selectSampleListByConsignmentId(consignmentId);
        return limsSampleInfoDnaList;
    }

    /**
     * 根据案件id以及委托id查询样本信息分页
     * @param limsSampleInfoDna
     * @param pageInfo
     * @return
     */
    @Override
    public List<LimsSampleInfoDnaVo> selectByCaseIdAndRyPagination(LimsSampleInfoDnaVo limsSampleInfoDna, PageInfo pageInfo) {
        List<LimsSampleInfoDnaVo> sampleInfoDnaVOList = null;
        int pageNo;
        int pageSize;
        try {
            pageNo = pageInfo.getPage();
            pageSize = pageInfo.getEvePageRecordCnt();
            limsSampleInfoDna.setOffset((pageNo - 1) * pageSize);
            limsSampleInfoDna.setRows(limsSampleInfoDna.getOffset() + pageSize);

            sampleInfoDnaVOList = limsSampleInfoDnaMapper.selectByCaseIdAndRyPagination(limsSampleInfoDna);
        } catch(Exception ex) {
            logger.info("入库比对查询样本列表报错："+ex);
            return null;
        }

        return sampleInfoDnaVOList;
    }

    /**
     * 根据案件id以及委托id查询样本信息count
     * @param limsSampleInfoDna
     * @return
     */
    @Override
    public int selectByCaseIdAndRyCount(LimsSampleInfoDnaVo limsSampleInfoDna) {
        return limsSampleInfoDnaMapper.selectByCaseIdAndRyCount(limsSampleInfoDna);
    }

    @Override
    public List<LimsSampleInfoDna> selectList(LimsSampleInfoDna limsSampleInfoDna) {
        return limsSampleInfoDnaMapper.selectList(limsSampleInfoDna);
    }

    @Override
    public List<LimsSampleInfoDnaVo> selectVoList(LimsSampleInfoDnaVo limsSampleInfoDnaVo) {
        return limsSampleInfoDnaMapper.selectVoList(limsSampleInfoDnaVo);
    }

    @Override
    public void updateInstoredFlag(LimsSampleInfoDna sampleInfo) {
        limsSampleInfoDnaMapper.updateInstoredFlag(sampleInfo);
    }

    @Override
    public List<LimsSampleInfoDnaVo> selectNotDetected(String caseId) {
        return limsSampleInfoDnaMapper.selectNotDetected(caseId);
    }

    @Override
    public List<LimsSampleInfoDnaVo> selectSampleInfos(String caseId) {
        return limsSampleInfoDnaMapper.selectSampleInfos(caseId);
    }

    @Override
    public List<LimsSampleInfoDna> selectByConsignmentId(String consignmentId) {
        return limsSampleInfoDnaMapper.selectByConsignmentId(consignmentId);
    }

    @Override
    public List<LimsSampleInfoDna> selectBySampleId(String sampleId) {
        return limsSampleInfoDnaMapper.selectBySampleId(sampleId);
    }

    @Override
    public List<LimsSampleInfoDna> selectSampleDnaByCaseId(String caseId) {
        return limsSampleInfoDnaMapper.selectSampleDnaByCaseId(caseId);
    }

    @Override
    public List<LimsSampleInfoDna> selectBySampleNoAlreadyStorage(String sampleNo) {
        return limsSampleInfoDnaMapper.selectBySampleNoAlreadyStorage(sampleNo);
    }

    @Override
    public boolean isStoredBySampleId(String sampleId) {
        LimsSampleInfoDna limsSampleInfoDna = limsSampleInfoDnaMapper.isStoredBySampleId(sampleId);
        if (limsSampleInfoDna == null) {
            return false;
        }else {
            return true;
        }
    }

    @Override
    public List<LimsSampleInfoDna> selectListByCaseIdInstored(String caseId) {
        return limsSampleInfoDnaMapper.selectListByCaseIdInstored(caseId);
    }

    @Override
    public List<LimsSampleInfoDna> selectMatchCaseId(String caseId) {
        return limsSampleInfoDnaMapper.selectMatchCaseId(caseId);
    }

    @Override
    public int selectAcceptCount(LimsCaseInfoVo query) {
        return limsSampleInfoDnaMapper.selectAcceptCount(query);
    }

    @Override
    public int selectAuditCount(LimsCaseInfoVo query) {
        return limsSampleInfoDnaMapper.selectAuditCount(query);
    }

    @Override
    public int selectInstoredSampleCount(LimsCaseInfoVo query) {
        return limsSampleInfoDnaMapper.selectInstoredSampleCount(query);
    }

    @Override
    public List<LimsSampleInfoDna> selectAccept(LimsCaseInfoVo query) {
        return limsSampleInfoDnaMapper.selectAccept(query);
    }

    @Override
    public List<LimsSampleInfoDna> selectAuditSampleByCaseId(String caseId) {
        try {
            return limsSampleInfoDnaMapper.selectAuditSampleByCaseId(caseId);
        }catch (Exception e) {
            e.getStackTrace();
            logger.error("查询审核通过检材信息错误！" + e);
            return null;
        }
    }

    @Override
    public List<LimsSampleInfoDna> selectSampleInfoListData(LimsSampleInfoDnaVo query,int page) {
        List<LimsSampleInfoDna> queuestateList = null;
        int pageNo;
        int pageSize;
        try {
            pageNo = page;
            pageSize = 1000;
            query.setOffset((pageNo - 1) * pageSize);
            query.setRows(query.getOffset() + pageSize);
            queuestateList = limsSampleInfoDnaMapper.selectSampleInfoListData(query);
        } catch(Exception ex) {
            logger.info("查询入混合库数据报错："+ex);
            System.out.println("查询入混合库数据报错");
        }
        return queuestateList;
    }

    @Override
    public int updatePre(LimsSampleInfoDna sampleInfoDna) {
        return limsSampleInfoDnaMapper.updatePre(sampleInfoDna);
    }

    /**
     * 根据物证编号查询检材信息
     * @param evidenceNo
     * @return
     */
    @Override
    public List<LimsSampleInfoDna> selectCaseInfoListData(String evidenceNo) {
        return limsSampleInfoDnaMapper.selectCaseInfoListData(evidenceNo);
    }

    @Override
    public int updateSamples(LimsSampleInfoDna limsSampleInfoDna) {
        return limsSampleInfoDnaMapper.updateSamples(limsSampleInfoDna);
    }
}
