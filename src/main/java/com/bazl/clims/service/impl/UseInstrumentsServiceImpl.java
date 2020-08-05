package com.bazl.clims.service.impl;

import com.bazl.clims.dao.LabExtractSampleMapper;
import com.bazl.clims.dao.UseInstrumentsMapper;
import com.bazl.clims.model.po.LabExtractSample;
import com.bazl.clims.model.po.UseInstruments;
import com.bazl.clims.service.LabExtractSampleService;
import com.bazl.clims.service.UseInstrumentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hj
 * @date 2019/9/2.
 */
@Service
public class UseInstrumentsServiceImpl extends BaseService implements UseInstrumentsService {


    @Autowired
    UseInstrumentsMapper useInstrumentsMapper;


    @Override
    public List<UseInstruments> findByExtractMentod(UseInstruments useInstrumentsEntity) {
        List<UseInstruments> useInstrumentsList = useInstrumentsMapper.findByExtractMentod(useInstrumentsEntity);
        return useInstrumentsList;
    }
}
