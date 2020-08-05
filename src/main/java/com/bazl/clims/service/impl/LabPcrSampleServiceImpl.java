package com.bazl.clims.service.impl;

import com.bazl.clims.dao.LabPcrSampleMapper;
import com.bazl.clims.model.po.LabPcrSample;
import com.bazl.clims.service.LabPcrSampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2019/3/11.
 */
@Service
public class LabPcrSampleServiceImpl extends BaseService implements LabPcrSampleService {
    
    @Autowired
    LabPcrSampleMapper labPcrSampleMapper;
    @Override
    public int deleteByPrimaryKey(String pcrId) {
        return labPcrSampleMapper.deleteByPrimaryKey(pcrId);
    }

    @Override
    public int insert(LabPcrSample record) {
        return labPcrSampleMapper.insert(record);
    }

    @Override
    public LabPcrSample selectByPrimaryKey(String pcrId) {
        return labPcrSampleMapper.selectByPrimaryKey(pcrId);
    }

    @Override
    public List<LabPcrSample> selectAll() {
        return labPcrSampleMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(LabPcrSample record) {
        return labPcrSampleMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<LabPcrSample> selectByPcrId(String pcrId) {
        return labPcrSampleMapper.selectByPcrId(pcrId);
    }

    @Override
    public List<LabPcrSample> selectBySampleId(String sampleId) {
        return labPcrSampleMapper.selectBySampleId(sampleId);
    }

    @Override
    public List<LabPcrSample> selectList(LabPcrSample labPcrSample) {
        return labPcrSampleMapper.selectList(labPcrSample);
    }

}
