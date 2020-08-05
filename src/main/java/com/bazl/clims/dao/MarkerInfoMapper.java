package com.bazl.clims.dao;

import com.bazl.clims.model.po.MarkerInfo;

import java.util.List;

public interface MarkerInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(MarkerInfo record);

    MarkerInfo selectByPrimaryKey(String id);

    List<MarkerInfo> selectAll();

    int updateByPrimaryKey(MarkerInfo record);
}