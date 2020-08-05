package com.bazl.clims.service.impl;

import com.bazl.clims.dao.LimsPersonInfoMapper;
import com.bazl.clims.model.po.LimsPersonInfo;
import com.bazl.clims.model.vo.LimsPersonInfoVo;
import com.bazl.clims.service.LimsPersonInfoService;
import com.bazl.clims.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by hj on 2018/12/20.
 */
@Service
public class LimsPersonInfoServiceImpl extends BaseService  implements LimsPersonInfoService {

    @Autowired
    LimsPersonInfoMapper limsPersonInfoMapper;


    @Override
    public List<LimsPersonInfo> selectByCaseId(String caseId) {
        List<LimsPersonInfo> limsPersonInfoList = limsPersonInfoMapper.selectByCaseId(caseId);
        return limsPersonInfoList;
    }

    @Override
    public List<LimsPersonInfo> selectByCaseIdAndConsignmentId(LimsPersonInfo limsPersonInfo) {
        List<LimsPersonInfo> limsPersonInfoList = limsPersonInfoMapper.selectByCaseIdAndConsignmentId(limsPersonInfo);
        return limsPersonInfoList;
    }

    @Override
    public List<LimsPersonInfo> selectListByConsignmentId(String consignmentId) {
        List<LimsPersonInfo> limsPersonInfoList = limsPersonInfoMapper.selectListByConsignmentId(consignmentId);
        return limsPersonInfoList;
    }

    @Override
    public LimsPersonInfo selectPersonInfoById(String personId) {
        LimsPersonInfo personInfo = limsPersonInfoMapper.selectPersonInfoById(personId);
        return personInfo;
    }

    @Override
    public List<LimsPersonInfo> selectAuditByCaseId(String caseId) {
        try {
            return limsPersonInfoMapper.selectAuditByCaseId(caseId);
        }catch (Exception e) {
            e.getStackTrace();
            logger.error("查询信息失败！" + e);
            return null;
        }
    }

    @Override
    public LimsPersonInfo selectPersonInfoBySampleNo(String sampleNo) {
        List<LimsPersonInfo> limsPersonInfoList = null;
        LimsPersonInfo limsPersonInfo = null;
        try {
            limsPersonInfoList = limsPersonInfoMapper.selectPersonInfoBySampleNo(sampleNo);
            if (ListUtils.isNotNullAndEmptyList(limsPersonInfoList)) {
                limsPersonInfo = limsPersonInfoList.get(0);
            }
        }catch (Exception e) {
            e.getStackTrace();
            logger.error("查询信息失败！" + e);
        }

        return limsPersonInfo;
    }

    @Override
    public List<LimsPersonInfo> selectPersonInfoListData(LimsPersonInfoVo query, Integer page) {
        List<LimsPersonInfo> queuestateList = null;
        int pageNo;
        int pageSize;
        try {
            pageNo = page;
            pageSize = 1000;
            query.setOffset((pageNo - 1) * pageSize);
            query.setRows(query.getOffset() + pageSize);
            queuestateList = limsPersonInfoMapper.selectPersonInfoListData(query);
        } catch(Exception ex) {
            logger.info("查询入混合库数据报错："+ex);
            System.out.println("查询入混合库数据报错");
        }
        return queuestateList;
    }

}
