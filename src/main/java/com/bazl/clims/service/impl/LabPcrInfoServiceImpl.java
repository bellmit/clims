package com.bazl.clims.service.impl;

import com.bazl.clims.dao.LabPcrInfoMapper;
import com.bazl.clims.model.PageInfo;
import com.bazl.clims.model.po.LabPcrInfo;
import com.bazl.clims.model.vo.LabPcrInfoVo;
import com.bazl.clims.service.LabPcrInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2019/3/11.
 */
@Service
public class LabPcrInfoServiceImpl extends BaseService implements LabPcrInfoService {

    @Autowired
    LabPcrInfoMapper labPcrInfoMapper;

    @Override
    public int deleteByPrimaryKey(String pcrId) {
        return labPcrInfoMapper.deleteByPrimaryKey(pcrId);
    }

    @Override
    public int insert(LabPcrInfo record) {
        return labPcrInfoMapper.insert(record);
    }

    @Override
    public LabPcrInfo selectByPrimaryKey(String pcrId) {
        return labPcrInfoMapper.selectByPrimaryKey(pcrId);
    }

    @Override
    public List<LabPcrInfo> selectAll() {
        return labPcrInfoMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(LabPcrInfo record) {
        return labPcrInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<LabPcrInfo> selectByTaskId(String taskId) {
        return labPcrInfoMapper.selectByTaskId(taskId);
    }

    @Override
    public List<LabPcrInfo> selectByBoarsNo(LabPcrInfo record) {
        return labPcrInfoMapper.selectByBoarsNo(record);
    }

    @Override
    public List<LabPcrInfo> selectListBySampleId(String sampleId) {
        return labPcrInfoMapper.selectListBySampleId(sampleId);
    }

    @Override
    public List<LabPcrInfo> selectAllList(LabPcrInfo record) {
        return labPcrInfoMapper.selectAllList(record);
    }

    @Override
    public List<LabPcrInfo> selectRepeatingByBoarsNo(LabPcrInfo record) {
        return labPcrInfoMapper.selectRepeatingByBoarsNo(record);
    }

    @Override
    public List<LabPcrInfoVo> selectVoList(LabPcrInfoVo labPcrInfoVo, PageInfo pageInfo) {
        List<LabPcrInfoVo> LabPcrInfoVoList = null;
        int pageNo;
        int pageSize;
        try {
            pageNo = pageInfo.getPage();
            pageSize = pageInfo.getEvePageRecordCnt();
            labPcrInfoVo.setOffset((pageNo - 1) * pageSize);
            labPcrInfoVo.setRows(labPcrInfoVo.getOffset() + pageSize);

            LabPcrInfoVoList = labPcrInfoMapper.selectVoList(labPcrInfoVo);
        } catch(Exception ex) {
            logger.info("查询手动提取报错："+ex);
            return null;
        }
        return LabPcrInfoVoList;
    }

    @Override
    public int selectVOCount(LabPcrInfoVo labPcrInfoVo) {
        return labPcrInfoMapper.selectVOCount(labPcrInfoVo);
    }

    @Override
    public List<LabPcrInfo> selectByCaseId(String caseId) {
        return labPcrInfoMapper.selectByCaseId(caseId);
    }

}
