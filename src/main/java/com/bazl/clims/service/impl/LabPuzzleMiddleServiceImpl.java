package com.bazl.clims.service.impl;

import com.bazl.clims.dao.LabPuzzleMiddleMapper;
import com.bazl.clims.model.po.LabPuzzleMiddle;
import com.bazl.clims.service.LabPuzzleMiddleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by  chaiyajie on 2019/4/22.
 */

@Service
public class LabPuzzleMiddleServiceImpl extends BaseService implements LabPuzzleMiddleService{

    @Autowired
    LabPuzzleMiddleMapper labPuzzleMiddleMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return labPuzzleMiddleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(LabPuzzleMiddle record) {
        return labPuzzleMiddleMapper.insert(record);
    }

    @Override
    public LabPuzzleMiddle selectByPrimaryKey(String id) {
        return labPuzzleMiddleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<LabPuzzleMiddle> selectAll() {
        return labPuzzleMiddleMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(LabPuzzleMiddle record) {
        return labPuzzleMiddleMapper.updateByPrimaryKey(record);
    }

    @Override
    public LabPuzzleMiddle selectByPcrId(String pcrId) {
        return labPuzzleMiddleMapper.selectByPcrId(pcrId);
    }

    @Override
    public List<LabPuzzleMiddle> selectByTaskId(String taskId) {
        return labPuzzleMiddleMapper.selectByTaskId(taskId);
    }

    @Override
    public LabPuzzleMiddle selectBySyId(String syId) {
        return labPuzzleMiddleMapper.selectBySyId(syId);
    }
}
