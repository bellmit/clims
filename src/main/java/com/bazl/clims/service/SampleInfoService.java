package com.bazl.clims.service;

import com.bazl.clims.model.PageInfo;
import com.bazl.clims.model.po.SampleInfo;
import com.bazl.clims.model.vo.SampleInfoVo;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: chenzelaing
 * @Date: 2020/3/30 17:10
 * @Version: 1.0
 */
@Repository
public interface SampleInfoService {
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
    public int insert(SampleInfo record);

    /**
     * 根据id查询信息
     * @param id
     * @return
     */
    public SampleInfo selectByPrimaryKey(String id);

    /**
     * 查询所有信息
     * @return
     */
    public List<SampleInfo> selectAll();

    /**
     * 更新信息
     * @param record
     * @return
     */
    public int updateByPrimaryKey(SampleInfo record);

    /**
     * 分页查询列表信息
     * @param query
     * @param pageInfo
     * @return
     */
    public List<SampleInfoVo> selectVoList(SampleInfoVo query, PageInfo pageInfo);

    /**
     * 根据条件查询数量
     * @param query
     * @return
     */
    public int selectVOCount(SampleInfoVo query);

    /**
     * 根据条件查询检材信息
     * @param query
     * @return
     */
    public List<SampleInfoVo> selectVoListBySampleInfo(SampleInfoVo query);

    /**
     * 根据条件查询不重复检材信息
     * @param query
     * @return
     */
    public List<SampleInfoVo> selectListVo(SampleInfoVo query);

    /**
     * 根据id删除信息
     * @param id
     * @return
     */
    public int deleteFlagById(String id);

    /**
     * 根据样本表id更新信息
     * @param sampleInfo
     * @return
     */
    public int updateBySampleTableId(SampleInfo sampleInfo);

    /**
     * 根据提取表id更新信息
     * @param sampleInfo
     * @return
     */
    public int updateByExtractPlateId(SampleInfo sampleInfo);

    /**
     * 根据扩增表id更新信息
     * @param sampleInfo
     * @return
     */
    public int updateByPcrPlateId(SampleInfo sampleInfo);
}
