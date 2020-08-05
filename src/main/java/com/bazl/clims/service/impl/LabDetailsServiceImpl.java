package com.bazl.clims.service.impl;

import com.bazl.clims.dao.LabDetailsMapper;
import com.bazl.clims.model.po.LabDetails;
import com.bazl.clims.service.LabDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2019/4/29.
 */
@Service
public class LabDetailsServiceImpl implements LabDetailsService {
    @Autowired
    LabDetailsMapper labDetailsMapper;

    @Override
    public List<LabDetails> querylabDetailsList(String labId) {
        return labDetailsMapper.querylabDetailsList(labId);
    }
}
