package com.bazl.clims.dao;

import com.bazl.clims.model.po.UseInstruments;
import java.util.List;

public interface UseInstrumentsMapper {
    int deleteByPrimaryKey(String id);

    int insert(UseInstruments record);

    UseInstruments selectByPrimaryKey(String id);

    List<UseInstruments> selectAll();

    int updateByPrimaryKey(UseInstruments record);

    List<UseInstruments> findByExtractMentod(UseInstruments useInstrumentsEntity);
}