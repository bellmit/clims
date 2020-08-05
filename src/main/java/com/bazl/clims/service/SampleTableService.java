package com.bazl.clims.service;


import com.bazl.clims.model.PageInfo;
import com.bazl.clims.model.po.SampleTable;
import com.bazl.clims.model.vo.SampleTableVo;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenzeliang
 * @date 2020/3/30.
 */
@Repository
public interface SampleTableService {

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
     * 分页查询列表信息
     * @param query
     * @param pageInfo
     * @return
     */
    public List<SampleTableVo> selectVoList(SampleTableVo query, PageInfo pageInfo);

    /**
     * 根据条件查询数量
     * @param query
     * @return
     */
    public int selectVOCount(SampleTableVo query);

    /**
     * 根据id删除信息
     * @param id
     * @return
     */
    public int deleteFlagById(String id);
}
