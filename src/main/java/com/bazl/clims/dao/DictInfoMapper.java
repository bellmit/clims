package com.bazl.clims.dao;

import com.bazl.clims.model.DictInfo;
import java.util.List;

public interface DictInfoMapper {
    int deleteByPrimaryKey(String dictInfoId);

    int insert(DictInfo record);

    DictInfo selectByPrimaryKey(String dictInfoId);

    List<DictInfo> selectAll();

    int updateByPrimaryKey(DictInfo record);

    DictInfo selectByDictTypeCode(String dicttypecode);
}