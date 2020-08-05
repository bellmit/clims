package com.bazl.clims.service.impl;

import com.bazl.clims.dao.ExperimentalParameterMapper;
import com.bazl.clims.model.po.ExperimentalParameter;
import com.bazl.clims.service.ExperimentalParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2019/5/21.
 */
@Service
public class ExperimentalParameterServiceImpl extends BaseService implements ExperimentalParameterService{

    @Autowired
    ExperimentalParameterMapper experimentalParameterMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return experimentalParameterMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ExperimentalParameter record) {
        return experimentalParameterMapper.insert(record);
    }

    @Override
    public ExperimentalParameter selectByPrimaryKey(String id) {
        return experimentalParameterMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ExperimentalParameter> selectAll() {
        return experimentalParameterMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(ExperimentalParameter record) {
        return experimentalParameterMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<ExperimentalParameter> selectList(ExperimentalParameter experimentalParameter) {
        return experimentalParameterMapper.selectList(experimentalParameter);
    }

    @Override
    public List<ExperimentalParameter> selectListValue(ExperimentalParameter experimentalParameter) {
        return experimentalParameterMapper.selectListValue(experimentalParameter);
    }

}
