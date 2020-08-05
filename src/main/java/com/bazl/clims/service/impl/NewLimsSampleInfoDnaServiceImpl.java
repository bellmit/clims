package com.bazl.clims.service.impl;

import com.bazl.clims.dao.LimsSampleInfoDnaMapper;
import com.bazl.clims.model.po.LimsSampleInfoDna;
import com.bazl.clims.service.NewLimsSampleInfoDnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewLimsSampleInfoDnaServiceImpl extends BaseService implements NewLimsSampleInfoDnaService {
    @Autowired
    LimsSampleInfoDnaMapper limsSampleInfoDnaMapper;

//    @Autowired
//    NewLimsSampleInfoDnaMapper newLimsSampleInfoDnaMapper;

    @Override
    public int selectSampleCountByCaseId(LimsSampleInfoDna sampleInfoDna) {
        return this.limsSampleInfoDnaMapper.selectSampleCountByCaseId(sampleInfoDna);
    }

    @Override
    public int updatePre(LimsSampleInfoDna sampleInfoDna) {
//        return newLimsSampleInfoDnaMapper.updatePre(sampleInfoDna);
        return 1;
    }
}
