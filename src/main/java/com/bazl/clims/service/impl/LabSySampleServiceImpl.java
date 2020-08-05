package com.bazl.clims.service.impl;

import com.bazl.clims.dao.LabSySampleMapper;
import com.bazl.clims.model.po.LabSySample;
import com.bazl.clims.service.LabSySampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2019/3/11.
 */
@Service
public class LabSySampleServiceImpl extends BaseService implements LabSySampleService {
    
    @Autowired
    LabSySampleMapper labSySampleMapper;

    @Override
    public int deleteByPrimaryKey(String pcrId) {
        return labSySampleMapper.deleteByPrimaryKey(pcrId);
    }

    @Override
    public int insert(LabSySample record) {
        return labSySampleMapper.insert(record);
    }

    @Override
    public LabSySample selectByPrimaryKey(String pcrId) {
        return labSySampleMapper.selectByPrimaryKey(pcrId);
    }

    @Override
    public List<LabSySample> selectAll() {
        return labSySampleMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(LabSySample record) {
        return labSySampleMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<LabSySample> selectBySyId(String syId) {
        return labSySampleMapper.selectBySyId(syId);
    }

    @Override
    public List<LabSySample> selectLabSySampleBySampleId(String sampleId) {
        return labSySampleMapper.selectLabSySampleBySampleId(sampleId);
    }

    @Override
    public List<LabSySample> selectList(LabSySample labSySample) {
        return labSySampleMapper.selectList(labSySample);
    }

}
