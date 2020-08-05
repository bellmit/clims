package com.bazl.clims.dao;


import com.bazl.clims.model.po.FugitivesInfo;
import com.bazl.clims.model.vo.FugitivesInfoVo;

import java.util.List;

/**
 * @author huawei
 * @date 2020/6/15.
 */
public interface FugitivesInfoMapper {

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
    public int insert(FugitivesInfo record);

    /**
     * 根据id查询信息
     * @param id
     * @return
     */
    public FugitivesInfo selectByPrimaryKey(String id);

    /**
     * 查询所有信息
     * @return
     */
    public List<FugitivesInfo> selectAll();

    /**
     * 更新信息
     * @return
     */
    public int updateByPrimaryKey(FugitivesInfo record);

    /**
     * 分页查询信息
     * @param query
     * @return
     */
    public List<FugitivesInfoVo> selectVOList(FugitivesInfoVo query);

    /**
     * 根据条件查询总数
     * @param query
     * @return
     */
    public int selectVOCnt(FugitivesInfoVo query);

    /**
     * 根据条件删除在逃人员信息
     * @param fugitivesInfo
     */
    public void deleteFugitivesInfo(FugitivesInfo fugitivesInfo);

    /**
     * 根据条件查询在逃人员信息
     * @param searchFugitives
     * @return
     */
    public List<FugitivesInfoVo> selectFugitivesList(String searchFugitives);

    /**
     * 根据条件查询在逃人员信息
     * @param fugitivesInfo
     * @return
     */
    public List<FugitivesInfo> selectList(FugitivesInfo fugitivesInfo);
}