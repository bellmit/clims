package com.bazl.clims.dao;

import com.bazl.clims.model.po.SampleGeneInfo;
import java.util.List;

/**
 * @author wanghaiyang
 * @date 2019/3/31.
 */
public interface SampleGeneInfoMapper {

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
    public int insert(SampleGeneInfo record);

    /**
     * 根据id查询信息
     * @param id
     * @return
     */
    public SampleGeneInfo selectByPrimaryKey(String id);

    /**
     * 查询所有的信息
     * @return
     */
    public List<SampleGeneInfo> selectAll();

    /**
     * 更新信息
     * @param record
     * @return
     */
    public int updateByPrimaryKey(SampleGeneInfo record);
}