package com.bazl.clims.dao;

import com.bazl.clims.model.po.AmLearnRecord;
import java.util.List;

public interface AmLearnRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(AmLearnRecord record);

    AmLearnRecord selectByPrimaryKey(String id);

    List<AmLearnRecord> selectAll();

    int updateByPrimaryKey(AmLearnRecord record);
}