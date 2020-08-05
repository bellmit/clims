package com.bazl.clims.service.impl;

import com.bazl.clims.dao.LabExtractInfoMapper;
import com.bazl.clims.model.PageInfo;
import com.bazl.clims.model.po.LabExtractInfo;
import com.bazl.clims.model.vo.LabExtractInfoVo;
import com.bazl.clims.service.LabExtractInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2019/3/8.
 */
@Service
public class LabExtractInfoServiceImpl  extends BaseService implements LabExtractInfoService {

    @Autowired
    LabExtractInfoMapper labExtractInfoMapper;

    @Override
    public int deleteByPrimaryKey(String extractId) {
        return labExtractInfoMapper.deleteByPrimaryKey(extractId);
    }

    @Override
    public int insert(LabExtractInfo record) {
        return labExtractInfoMapper.insert(record);
    }

    @Override
    public LabExtractInfo selectByPrimaryKey(String extractId) {
        return labExtractInfoMapper.selectByPrimaryKey(extractId);
    }

    @Override
    public List<LabExtractInfo> selectAll() {
        return labExtractInfoMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(LabExtractInfo record) {
        return labExtractInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<LabExtractInfo> selectByTaskId(String taskId) {
        return labExtractInfoMapper.selectByTaskId(taskId);
    }

    @Override
    public List<LabExtractInfoVo> selectVoList(LabExtractInfoVo query, PageInfo pageInfo) {
        List<LabExtractInfoVo> labExtractInfoVoList = null;
        int pageNo;
        int pageSize;
        try {
            pageNo = pageInfo.getPage();
            pageSize = pageInfo.getEvePageRecordCnt();
            query.setOffset((pageNo - 1) * pageSize);
            query.setRows(query.getOffset() + pageSize);

            labExtractInfoVoList = labExtractInfoMapper.selectVoList(query);
        } catch(Exception ex) {
            logger.info("查询手动提取报错："+ex);
            return null;
        }

        return labExtractInfoVoList;
    }

    @Override
    public int selectVOCount(LabExtractInfoVo labExtractInfoVo) {
        return labExtractInfoMapper.selectVOCount(labExtractInfoVo);
    }

    @Override
    public List<LabExtractInfo> selectByCaseId(String caseId) {
        return labExtractInfoMapper.selectByCaseId(caseId);
    }

}
