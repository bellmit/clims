package com.bazl.clims.dao;

import com.bazl.clims.model.po.ReagentOutgoInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReagentOutgoInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(ReagentOutgoInfo record);

    ReagentOutgoInfo selectByPrimaryKey(String id);

    List<ReagentOutgoInfo> selectAll();

    int updateByPrimaryKey(ReagentOutgoInfo record);

    //根据出入库类型查询
    List<ReagentOutgoInfo> selectByRecordType(@Param("recordType")String recordType, @Param("orgId")String orgId);

    //查询入库信息
    List<ReagentOutgoInfo> selectInList(ReagentOutgoInfo record);

    //查询出库信息
    List<ReagentOutgoInfo> selectOutList(ReagentOutgoInfo record);

    //出入库查询
    List<ReagentOutgoInfo> selectOutInList(ReagentOutgoInfo record);

    //查询当前分局
    public List<ReagentOutgoInfo> selectOrgId(ReagentOutgoInfo record);

    //查询条数
    public int selectCount(ReagentOutgoInfo record);

    //出库存
    List<ReagentOutgoInfo> selectByStorageNum(ReagentOutgoInfo record);

    //根据试剂名称，所属单位查询入库信息
    List<ReagentOutgoInfo> selectreagentOutgoList(ReagentOutgoInfo reagentOutgoInfo);

    // 修改试剂名称
      int updateByReagentOutgoInfo(ReagentOutgoInfo reagentOutgoInfo);
}