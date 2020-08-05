package com.bazl.clims.dao;

import com.bazl.clims.model.po.LogRecordInfo;
import java.util.List;

public interface LogRecordMapper {
    int deleteByPrimaryKey(String logId);

    int insert(LogRecordInfo record);

    LogRecordInfo selectByPrimaryKey(String logId);

    List<LogRecordInfo> selectAll();

    int updateByPrimaryKey(LogRecordInfo record);
}