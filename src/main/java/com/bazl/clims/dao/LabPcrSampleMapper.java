package com.bazl.clims.dao;

import com.bazl.clims.model.po.LabPcrSample;
import java.util.List;

/**
 * @author wanghaiyang
 * @date 2019/3/11.
 */
public interface LabPcrSampleMapper {

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
    public int insert(LabPcrSample record);

    /**
     * 根据主键id查询信息
     * @param id
     * @return
     */
    public LabPcrSample selectByPrimaryKey(String id);

    /**
     * 查询所有信息
     * @return
     */
    public List<LabPcrSample> selectAll();

    /**
     * 更新信息
     * @param record
     * @return
     */
    public int updateByPrimaryKey(LabPcrSample record);

    /**
     * 根据pcrId查询扩增检材信息
     * @param pcrId
     * @return
     */
    public List<LabPcrSample> selectByPcrId(String pcrId);
    /**
     * 根据检材sampleId查询扩增检材信息
     * @param sampleId
     * @return
     */
    List<LabPcrSample> selectBySampleId(String sampleId);

    /**
     * 根据条件查询检材扩增实验信息
     * @param labPcrSample
     * @return
     */
    public List<LabPcrSample> selectList(LabPcrSample labPcrSample);
}