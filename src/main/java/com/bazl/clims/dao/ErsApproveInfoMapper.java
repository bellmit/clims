package com.bazl.clims.dao;

import com.bazl.clims.model.po.ErsApproveInfo;
import java.util.List;

public interface ErsApproveInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(ErsApproveInfo record);

    ErsApproveInfo selectByPrimaryKey(String id);

    List<ErsApproveInfo> selectAll();

    int updateByPrimaryKey(ErsApproveInfo record);
}