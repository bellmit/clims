package com.bazl.clims.service;

import com.bazl.clims.model.po.SuppliesInfo;

import java.util.List;

/**
 * Created by Administrator on 2019/5/5.
 */
public interface SuppliesInfoService {

    int deleteByPrimaryKey(String id);

    int insert(SuppliesInfo record);

    SuppliesInfo selectByPrimaryKey(String id);

    List<SuppliesInfo> selectAll();

    int updateByPrimaryKey(SuppliesInfo record);

    //根据分局查看耗材信息
    List<SuppliesInfo> selectOrgId(SuppliesInfo suppliesInfo);
}
