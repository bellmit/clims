package com.bazl.clims.service.impl;

import com.bazl.clims.dao.AmPersonalAptitudeMapper;
import com.bazl.clims.model.po.AmPersonalAptitude;
import com.bazl.clims.service.AmPersonalAptitudeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2019/5/5.
 */
@Service
public class AmPersonalAptitudeServiceImpl implements AmPersonalAptitudeService {
    @Autowired
    AmPersonalAptitudeMapper amPersonalAptitudeMapper;

    @Override
    public AmPersonalAptitude queryAmPersonalAptitudeById(String personalId) {
        return amPersonalAptitudeMapper.queryAmPersonalAptitudeById(personalId);
    }
}
