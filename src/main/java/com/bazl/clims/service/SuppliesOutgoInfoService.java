package com.bazl.clims.service;

import com.bazl.clims.model.po.SuppliesOutgoInfo;

import java.util.List;

/**
 * Created by chaiyajie on 2019/5/5.
 */
public interface SuppliesOutgoInfoService {

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
