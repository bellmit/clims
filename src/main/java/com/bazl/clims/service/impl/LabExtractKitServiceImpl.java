package com.bazl.clims.service.impl;

import com.bazl.clims.dao.LabExtractKitMapper;
import com.bazl.clims.model.po.LabExtractKit;
import com.bazl.clims.model.po.LabPcrInfo;
import com.bazl.clims.service.LabExtractKitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2019/3/11.
 */
@Service
public class LabExtractKitServiceImpl extends BaseService implements LabExtractKitService {

    @Autowired
    LabExtractKitMapper labExtractKitMapper;

    @Override
    public int deleteByPrimaryKey(String kitId) {
        return labExtractKitMapper.deleteByPrimaryKey(kitId);
    }

    @Override
    public int insert(LabExtractKit record) {
        return labExtractKitMapper.insert(record);
    }

    @Override
    public LabPcrInfo selectByPrimaryKey(String kitId) {
        return labExtractKitMapper.selectByPrimaryKey(kitId);
    }

    @Override
    public List<LabExtractKit> selectAll() {
        return labExtractKitMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(LabExtractKit record) {
        return labExtractKitMapper.updateByPrimaryKey(record);
    }

    @Override
    public LabExtractKit selectPanelId(String panelid){ return labExtractKitMapper.selectPanelId(panelid); }

    @Override
    public LabExtractKit selectPanelName(String panelid) {
        return labExtractKitMapper.selectPanelName(panelid);
    }

}
