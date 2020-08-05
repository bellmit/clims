package com.bazl.clims.dao;

import com.bazl.clims.model.po.SampleTable;
import com.bazl.clims.model.vo.SampleTableVo;

import java.util.List;

/**
 * @Author: lzn
 * @Date: 2020/3/31 13:09
 * @Version: 1.0
 */
public interface SampleTableMapper {
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
    public int insert(SampleTable record);

    /**
     * 根据id查询信息
     * @param id
     * @return
     */
    public SampleTable selectByPrimaryKey(String id);

    /**
     * 查询所有信息
     * @return
     */
    public List<SampleTable> selectAll();

    /**
     * 更新信息
     * @param record
     * @return
     */
    public int updateByPrimaryKey(SampleTable record);

    /**
     * 根据id删除信息
     * @param id
     * @return
     */
    public int deleteFlagById(String id);
    /**
     * 条件查询
     * @param sampleTableVo
     * @return
     */
    public List<SampleTableVo> selectPaginationList(SampleTableVo sampleTableVo);
    /**
     * 条件查询数量
     * @param sampleTableVo
     * @return
     */
    public int selectCount(SampleTableVo sampleTableVo);
}
