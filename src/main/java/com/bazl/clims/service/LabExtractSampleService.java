package com.bazl.clims.service;

import com.bazl.clims.model.po.LabExtractSample;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2019/3/8.
 */
public interface LabExtractSampleService {

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
    public int insert(LabExtractSample record);

    /**
     * 根据id查询信息
     * @param id
     * @return
     */
    public LabExtractSample selectByPrimaryKey(String id);

    /**
     * 查询所有的信息
     * @return
     */
    public List<LabExtractSample> selectAll();

    /**
     * 更新信息
     * @param record
     * @return
     */
    public int updateByPrimaryKey(LabExtractSample record);

    /**
     * 根据extractId查询提取阶段的检材信息
     * @param extractId
     * @return
     */
    public List<LabExtractSample> selectByExtractId (String extractId);

    /**
     * 根据extractId联查LIMS_SAMPLE_INFO_DNA表
     * @param extractId
     * @return
     */
    public List<LabExtractSample> selectByExtractIdAndInfoDna (String extractId);

    List<LabExtractSample> selectLabExtractSampleBySampleId(String sampleId);

    /**
     * 根据条件查询检材提取信息
     * @param labExtractSample
     * @return
     */
    List<LabExtractSample> selectList(LabExtractSample labExtractSample);
}
