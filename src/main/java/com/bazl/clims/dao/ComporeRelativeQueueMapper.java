package com.bazl.clims.dao;

import com.bazl.clims.model.po.ComporeRelativeQueue;
import com.bazl.clims.model.vo.ComporeRelativeQueueVo;

import java.util.List;

public interface ComporeRelativeQueueMapper {
    int deleteByPrimaryKey(String id);

    int insert(ComporeRelativeQueue record);

    ComporeRelativeQueue selectByPrimaryKey(String id);

    List<ComporeRelativeQueue> selectAll();

    int updateByPrimaryKey(ComporeRelativeQueue record);

    //查询同一比对队列
    public List<ComporeRelativeQueueVo> selectVOPaginationList(ComporeRelativeQueueVo query);

    //查询队列数量
    public int selectVOCount(ComporeRelativeQueueVo query);
}