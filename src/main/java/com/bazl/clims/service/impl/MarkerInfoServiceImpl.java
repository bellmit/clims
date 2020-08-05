package com.bazl.clims.service.impl;

import com.bazl.clims.model.po.MarkerInfo;
import com.bazl.clims.dao.MarkerInfoMapper;
import com.bazl.clims.service.MarkerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Sun on 2019/4/2.
 */
@Service
public class MarkerInfoServiceImpl implements MarkerInfoService {

    @Autowired
    MarkerInfoMapper  markerInfoMapper;

    @Override
    public List<MarkerInfo> selectAll() {
        return markerInfoMapper.selectAll();
    }
}
