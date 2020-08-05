package com.bazl.clims.service;

import com.bazl.clims.model.PageInfo;
import com.bazl.clims.model.po.ReagentInfo;
import com.bazl.clims.model.po.ReagentOutgoInfo;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by Sun on 2018/12/20.
 */
@Repository
public interface ReagentOutgoInfoService {

    int deleteByPrimaryKey(String id);

    int insert(ReagentOutgoInfo record);

    ReagentOutgoInfo selectByPrimaryKey(String id);

    List<ReagentOutgoInfo> selectAll();

    int updateByPrimaryKey(ReagentOutgoInfo record);

    //根据出入库类型查询
    List<ReagentOutgoInfo> selectByRecordType(String recordType,String orgId);

    //查询入库信息
    List<ReagentOutgoInfo> selectInList(ReagentOutgoInfo record, PageInfo pageInfo);

    //查询出库信息
    List<ReagentOutgoInfo> selectOutList(ReagentOutgoInfo record, PageInfo pageInfo);

    //出入库查询
    List<ReagentOutgoInfo> selectOutInList(ReagentOutgoInfo record, PageInfo pageInfo);

    //查询当前分局
    public List<ReagentOutgoInfo> selectOrgId(ReagentOutgoInfo record);

    //查询条数
    public int selectCount(ReagentOutgoInfo record);

    //出库存
    List<ReagentOutgoInfo> selectByStorageNum(ReagentOutgoInfo record);

    /**
     * 根据试剂名称，所属单位查询入库信息
     * @param reagentOutgoInfo
     * @return
     */
    List<ReagentOutgoInfo> selectreagentOutgoList(ReagentOutgoInfo reagentOutgoInfo);

    //修改试剂名称
    int updateByReagentOutgoInfo(ReagentOutgoInfo reagentOutgoInfo);
}
