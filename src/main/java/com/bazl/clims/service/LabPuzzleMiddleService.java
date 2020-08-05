package com.bazl.clims.service;

import com.bazl.clims.model.po.LabPuzzleMiddle;

import java.util.List;

/**
 * Created by chaiyajie on 2019/4/22.
 */


public interface LabPuzzleMiddleService {

    int deleteByPrimaryKey(String id);

    int insert(LabPuzzleMiddle record);

    LabPuzzleMiddle selectByPrimaryKey(String id);

    List<LabPuzzleMiddle> selectAll();

    int updateByPrimaryKey(LabPuzzleMiddle record);

    //根据pcrid查询数据
    LabPuzzleMiddle selectByPcrId(String pcrId);

    //根据taskid查询数据
    List<LabPuzzleMiddle> selectByTaskId(String taskId);

    //根据syid查询数据
    LabPuzzleMiddle selectBySyId(String syId);
}
