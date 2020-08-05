package com.bazl.clims.dao;

import com.bazl.clims.model.po.XckyAddressInfo;
import java.util.List;

public interface XckyAddressInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(XckyAddressInfo record);

    XckyAddressInfo selectByPrimaryKey(String id);

    List<XckyAddressInfo> selectAll();

    int updateByPrimaryKey(XckyAddressInfo record);
}