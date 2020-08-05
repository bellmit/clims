package com.bazl.clims.service;

import com.bazl.clims.model.po.LimsPersonInfo;
import com.bazl.clims.model.vo.LimsPersonInfoVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LimsPersonInfoService {

    List<LimsPersonInfo> selectByCaseId(String caseId);

    List<LimsPersonInfo> selectByCaseIdAndConsignmentId(LimsPersonInfo limsPersonInfo);

    List<LimsPersonInfo> selectListByConsignmentId(String consignmentId);

    LimsPersonInfo selectPersonInfoById(String personId);

    /**
     * 更加案件id查询人员信息
     * @param caseId
     * @return
     */
    public List<LimsPersonInfo> selectAuditByCaseId(String caseId);

    /**
     * 根据检材编号获取人员信息
     * @param sampleNo
     * @return
     */
    public LimsPersonInfo selectPersonInfoBySampleNo(String sampleNo);



    /**
     * 混合库获取案件人员信息
     * @return
     */
    List<LimsPersonInfo> selectPersonInfoListData(LimsPersonInfoVo query, Integer page);
}