package com.bazl.clims.service.impl;

import com.bazl.clims.dao.SuppliesInfoMapper;
import com.bazl.clims.model.po.SuppliesInfo;
import com.bazl.clims.service.SuppliesInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cjaiyajie on 2019/5/5.
 */

@Service
public class SuppliesInfoServiceImpl extends BaseService implements SuppliesInfoService {

    @Autowired
    SuppliesInfoMapper suppliesInfoMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return suppliesInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SuppliesInfo record) {
        return suppliesInfoMapper.insert(record);
    }

    @Override
    public SuppliesInfo selectByPrimaryKey(String id) {
        return suppliesInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SuppliesInfo> selectAll() {
        return suppliesInfoMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(SuppliesInfo record) {
        return suppliesInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<SuppliesInfo> selectOrgId(SuppliesInfo suppliesInfo) {
        return suppliesInfoMapper.selectOrgId(suppliesInfo);
    }
}
