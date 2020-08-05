package com.bazl.clims.service.impl;

import com.bazl.clims.dao.LabExtractSampleMapper;
import com.bazl.clims.model.po.LabExtractInfo;
import com.bazl.clims.model.po.LabExtractSample;
import com.bazl.clims.service.LabExtractSampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2019/3/8.
 */
@Service
public class LabExtractSampleServiceImpl extends BaseService implements LabExtractSampleService {


    @Autowired
    LabExtractSampleMapper labExtractSampleMapper;

    @Override
    public int deleteByPrimaryKey(String extractId) {
        return labExtractSampleMapper.deleteByPrimaryKey(extractId);
    }

    @Override
    public int insert(LabExtractSample record) {
        return labExtractSampleMapper.insert(record);
    }

    @Override
    public LabExtractSample selectByPrimaryKey(String extractId) {
        return labExtractSampleMapper.selectByPrimaryKey(extractId);
    }

    @Override
    public List<LabExtractSample> selectAll() {
        return labExtractSampleMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(LabExtractSample record) {
        return labExtractSampleMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<LabExtractSample> selectByExtractId (String extractId) {
        return labExtractSampleMapper.selectByExtractId(extractId);
    }

    @Override
    public List<LabExtractSample> selectByExtractIdAndInfoDna (String extractId){
        return labExtractSampleMapper.selectByExtractIdAndInfoDna (extractId);
    }

    @Override
    public List<LabExtractSample> selectLabExtractSampleBySampleId(String sampleId) {
        return labExtractSampleMapper.selectLabExtractSampleBySampleId(sampleId);
    }

    @Override
    public List<LabExtractSample> selectList(LabExtractSample labExtractSample) {
        return labExtractSampleMapper.selectList(labExtractSample);
    }

}
