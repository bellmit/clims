package com.bazl.clims.service;

import com.bazl.clims.model.po.ProgramRecord;

import java.util.List;

/**
 * @Author: chenzeliang
 * @Date: 2020/4/2 10:23
 * @Version: 1.0
 */
public interface ProgramRecordService {

    /**
     * 根据id删除信息
     * @param id
     * @return
     */
    public int deleteByPrimaryKey(String id);

    /**
     * 插入信息
     * @param record
     * @return
     */
    public int insert(ProgramRecord record);

    /**
     * 根据id查询信息
     * @param id
     * @return
     */
    public ProgramRecord selectByPrimaryKey(String id);

    /**
     * 查询所有信息
     * @return
     */
    public List<ProgramRecord> selectAll();

    /**
     * 更新信息
     * @param record
     * @return
     */
    public int updateByPrimaryKey(ProgramRecord record);
}
