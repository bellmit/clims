package com.bazl.clims.dao;


import com.bazl.clims.model.po.LimsConsignmentInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LimsConsignmentInfoMapper {

    void insertConsignatioInfo(LimsConsignmentInfo consignatioInfo);

    void deleteConsignationInfo(LimsConsignmentInfo consignatioInfo);

    LimsConsignmentInfo selectByConsignmentId(String consignmentId);

    void updateConsignatioInfo(LimsConsignmentInfo limsConsignmentInfo);

    /**
     * 根据案件id查询委托信息
     * @param caseId
     * @return
     */
    public List<LimsConsignmentInfo> selectByCaseId(String caseId);

    /**
     * 根据编号查重
     * @param consignmentNo
     * @return
     */
    public LimsConsignmentInfo selectByConsignmentNo(String consignmentNo);

    public LimsConsignmentInfo getCaseidByAppendflag(String caseId);

    /**
     * 根据案件编号查询委托信息
     * @param caseNo
     * @return
     */
    public List<LimsConsignmentInfo> selectByCaseNo(String caseNo);
}