package com.bazl.clims.service.impl;

import com.bazl.clims.dao.LabSyInfoMapper;
import com.bazl.clims.model.PageInfo;
import com.bazl.clims.model.po.LabSyInfo;
import com.bazl.clims.model.vo.LabSyInfoVo;
import com.bazl.clims.service.LabSyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2019/3/11.
 */
@Service
public class LabSyInfoServiceImpl extends BaseService implements LabSyInfoService {
    
    @Autowired
    LabSyInfoMapper labSyInfoMapper;

    @Override
    public int deleteByPrimaryKey(String pcrId) {
        return labSyInfoMapper.deleteByPrimaryKey(pcrId);
    }

    @Override
    public int insert(LabSyInfo record) {
        return labSyInfoMapper.insert(record);
    }

    @Override
    public LabSyInfo selectByPrimaryKey(String pcrId) {
        return labSyInfoMapper.selectByPrimaryKey(pcrId);
    }

    @Override
    public List<LabSyInfo> selectAll() {
        return labSyInfoMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(LabSyInfo record) {
        return labSyInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<LabSyInfo> selectByTaskId(String taskId) {
        return labSyInfoMapper.selectByTaskId(taskId);
    }

    @Override
    public List<LabSyInfo> selectByBoarsNo(LabSyInfo record) {
        return labSyInfoMapper.selectByBoarsNo(record);
    }

    @Override
    public List<LabSyInfo> selectList(LabSyInfo labSyInfo) {
        return labSyInfoMapper.selectList(labSyInfo);
    }

    @Override
    public List<LabSyInfoVo> selectVOPaginationList(LabSyInfoVo labSyInfoVo, PageInfo pageInfo) {
        List<LabSyInfoVo> labSyInfoVoList = null;
        int pageNo;
        int pageSize;
        try {
            pageNo = pageInfo.getPage();
            pageSize = pageInfo.getEvePageRecordCnt();
            labSyInfoVo.setOffset((pageNo - 1) * pageSize);
            labSyInfoVo.setRows(labSyInfoVo.getOffset() + pageSize);

            labSyInfoVoList = labSyInfoMapper.selectVOPaginationList(labSyInfoVo);
        } catch(Exception ex) {
            logger.info("查询电泳信息报错："+ex);
            return null;
        }

        return labSyInfoVoList;
    }

    @Override
    public int selectVOCnt(LabSyInfoVo labSyInfoVo) {
        return labSyInfoMapper.selectVOCnt(labSyInfoVo);
    }

    @Override
    public List<LabSyInfo> selectRepeatingByBoarsNo(LabSyInfo record) {
        return labSyInfoMapper.selectRepeatingByBoarsNo(record);
    }

    @Override
    public List<LabSyInfo> selectByCaseId(String caseId) {
        return labSyInfoMapper.selectByCaseId(caseId);
    }

}
