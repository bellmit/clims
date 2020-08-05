package com.bazl.clims.service.impl;

import com.bazl.clims.dao.LabExtractKitMapper;
import com.bazl.clims.dao.PanelMapper;
import com.bazl.clims.model.po.LabExtractKit;
import com.bazl.clims.model.po.LabPcrInfo;
import com.bazl.clims.model.po.Panel;
import com.bazl.clims.service.LabExtractKitService;
import com.bazl.clims.service.PanelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2019/3/11.
 */
@Service
public class PanelServiceImpl extends BaseService implements PanelService {

    @Autowired
    PanelMapper panelMapper;

    @Override
    public int deleteByPrimaryKey(String panelid) {
        return panelMapper.deleteByPrimaryKey(panelid);
    }

    @Override
    public int insert(Panel record) {
        return panelMapper.insert(record);
    }

    @Override
    public Panel selectByPrimaryKey(String panelid) {
        return panelMapper.selectByPrimaryKey(panelid);
    }

    @Override
    public List<Panel> selectAll() {
        return panelMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Panel record) {
        return panelMapper.updateByPrimaryKey(record);
    }

}
