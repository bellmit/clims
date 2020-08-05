package com.bazl.clims.dao;

import com.bazl.clims.model.po.LimsSampleInfoRest;
import java.util.List;

public interface LimsSampleInfoRestMapper {
    int deleteByPrimaryKey(String sampleId);

    int insert(LimsSampleInfoRest record);

    LimsSampleInfoRest selectByPrimaryKey(String sampleId);

    List<LimsSampleInfoRest> selectAll();

    int updateByPrimaryKey(LimsSampleInfoRest record);
}