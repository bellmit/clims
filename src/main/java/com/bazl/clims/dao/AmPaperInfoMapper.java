package com.bazl.clims.dao;

import com.bazl.clims.model.po.AmPaperInfo;
import java.util.List;

public interface AmPaperInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(AmPaperInfo record);

    AmPaperInfo selectByPrimaryKey(String id);

    List<AmPaperInfo> selectAll();

    int updateByPrimaryKey(AmPaperInfo record);
}