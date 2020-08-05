package com.bazl.clims.dao;

import com.bazl.clims.model.po.SuppliesOutgoInfo;
import java.util.List;

public interface SuppliesOutgoInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(SuppliesOutgoInfo record);

    SuppliesOutgoInfo selectByPrimaryKey(String id);

    List<SuppliesOutgoInfo> selectAll();

    int updateByPrimaryKey(SuppliesOutgoInfo record);

    //查询出入库信息
    List<SuppliesOutgoInfo> selectByRecordType(SuppliesOutgoInfo record);

    //查询库存数
    List<SuppliesOutgoInfo> selectByStorageNum(SuppliesOutgoInfo record);
}