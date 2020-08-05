package com.bazl.clims.service;

import com.bazl.clims.model.po.ExperimentalParameter;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2019/5/21.
 */
public interface ExperimentalParameterService {


    /**
     * 根据extractId删除信息
     * @param id
     * @return
     */
    public int deleteByPrimaryKey(String id);

    /**
     * 插入信息
     * @param record
     * @return
     */
    public int insert(ExperimentalParameter record);

    /**
     * 根据extractId查询信息
     * @param id
     * @return
     */
    public ExperimentalParameter selectByPrimaryKey(String id);

    /**
     * 查询所有的信息
     * @return
     */
    public List<ExperimentalParameter> selectAll();

    /**
     * 更新信息
     * @param record
     * @return
     */
    public int updateByPrimaryKey(ExperimentalParameter record);

    /**
     * 根据条件查询信息
     * @param experimentalParameter
     * @return
     */
    public List<ExperimentalParameter> selectList(ExperimentalParameter experimentalParameter);

    List<ExperimentalParameter> selectListValue(ExperimentalParameter experimentalParameter);
}
