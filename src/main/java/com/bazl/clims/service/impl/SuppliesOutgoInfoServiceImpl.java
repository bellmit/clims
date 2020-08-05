package com.bazl.clims.service.impl;

import com.bazl.clims.dao.SuppliesOutgoInfoMapper;
import com.bazl.clims.model.po.SuppliesOutgoInfo;
import com.bazl.clims.service.SuppliesOutgoInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chaiyajie on 2019/5/5.
 */

@Service
public class SuppliesOutgoInfoServiceImpl extends BaseService implements SuppliesOutgoInfoService {


    @Autowired
    SuppliesOutgoInfoMapper suppliesOutgoInfoMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return suppliesOutgoInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SuppliesOutgoInfo record) {
        return suppliesOutgoInfoMapper.insert(record);
    }

    @Override
    public SuppliesOutgoInfo selectByPrimaryKey(String id) {
        return suppliesOutgoInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SuppliesOutgoInfo> selectAll() {
        return suppliesOutgoInfoMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(SuppliesOutgoInfo record) {
        return suppliesOutgoInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<SuppliesOutgoInfo> selectByRecordType(SuppliesOutgoInfo record) {
        return suppliesOutgoInfoMapper.selectByRecordType(record);
    }

    @Override
    public List<SuppliesOutgoInfo> selectByStorageNum(SuppliesOutgoInfo record) {
        return suppliesOutgoInfoMapper.selectByStorageNum(record);
    }
}
