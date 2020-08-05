package com.bazl.clims.dao;

import com.bazl.clims.model.po.AlleleFrequency;

import java.util.List;

public interface AlleleFrequencyMapper {
    int deleteByPrimaryKey(String id);

    int insert(AlleleFrequency record);

    AlleleFrequency selectByPrimaryKey(String id);

    List<AlleleFrequency> selectAll();

    int updateByPrimaryKey(AlleleFrequency record);
}