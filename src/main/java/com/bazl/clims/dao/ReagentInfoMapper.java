package com.bazl.clims.dao;

import com.bazl.clims.model.po.CompetencePost;
import com.bazl.clims.model.po.ReagentInfo;

import java.util.List;

public interface ReagentInfoMapper {

    /**
     * 添加试剂信息
     * @param reagentInfo
     */
    void addReagenInfo(ReagentInfo reagentInfo);

    /**
     * 查询试剂信息
     * @return
     */
    List<ReagentInfo> selectPaginationList(ReagentInfo reagentInfo);

    /**
     * 根基名称查询试剂信息
     * @return
     */
    ReagentInfo selectByReagentName(String reagentName);

    int deleteByPrimaryKey(String id);

    int insert(ReagentInfo record);

    ReagentInfo selectByPrimaryKey(String id);

    List<ReagentInfo> selectAll();

    int updateByPrimaryKey(ReagentInfo record);

    /**
     * 根据条件查询信息
     * @param reagentInfo
     * @return
     */
    public List<ReagentInfo> selectList(ReagentInfo reagentInfo);

    //查询当前分局
    public List<ReagentInfo> selectOrgId(ReagentInfo reagentInfo);

    //查询条数
    public int selectCount(ReagentInfo reagentInfo);

    //修改最新的试剂盒信息
    int updateByReagentInfo(ReagentInfo reagentInfo);
}