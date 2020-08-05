package com.bazl.clims.service;

import com.bazl.clims.model.po.LabSySample;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2019/3/11.
 */
public interface LabSySampleService {

    /**
     * 根据主键id删除信息
     * @param id
     * @return
     */
    public int deleteByPrimaryKey(String id);

    /**
     * 插入信息
     * @param record
     * @return
     */
    public int insert(LabSySample record);

    /**
     * 根据主键id查询信息
     * @param id
     * @return
     */
    public LabSySample selectByPrimaryKey(String id);

    /**
     * 查询所有信息
     * @return
     */
    public List<LabSySample> selectAll();

    /**
     * 更新信息
     * @param record
     * @return
     */
    public int updateByPrimaryKey(LabSySample record);

    /**
     * 根据syId查询上样检材实验信息
     * @param syId
     * @return
     */
    public List<LabSySample> selectBySyId(String syId);

    List<LabSySample> selectLabSySampleBySampleId(String sampleId);

    /**
     * 根据条件查询检材 电泳实验信息
     * @param labSySample
     * @return
     */
    public List<LabSySample> selectList(LabSySample labSySample);
}
