package com.bazl.clims.service;

import com.bazl.clims.model.po.LimsPerosnRelation;
import org.springframework.stereotype.Repository;

@Repository
public interface LimsPerosnRelationService {


    LimsPerosnRelation selectPersonInfo(String sampleFlag);
}