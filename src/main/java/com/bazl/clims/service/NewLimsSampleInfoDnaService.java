package com.bazl.clims.service;

import com.bazl.clims.model.po.LimsSampleInfoDna;
import org.springframework.stereotype.Repository;

@Repository
public interface NewLimsSampleInfoDnaService {

    int selectSampleCountByCaseId(LimsSampleInfoDna sampleInfoDna);

    /**
     * 更新预实验记录
     * @param sampleInfoDna
     * @return
     */
    int updatePre(LimsSampleInfoDna sampleInfoDna);

}