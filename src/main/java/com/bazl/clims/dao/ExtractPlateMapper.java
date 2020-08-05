package com.bazl.clims.dao;

import com.bazl.clims.model.po.ExtractPlate;
import com.bazl.clims.model.vo.ExtractPlateVo;
import com.bazl.clims.model.vo.SampleInfoVo;

import java.util.List;

/**
 * @Author: chenzeliang
 * @Date: 2020/4/2 15:43
 * @Version: 1.0
 */
public interface ExtractPlateMapper {
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
    public int insert(ExtractPlate record);

    /**
     * 根据id查询信息
     * @param id
     * @return
     */
    public ExtractPlate selectByPrimaryKey(String id);

    /**
     * 查询所有信息
     * @return
     */
    public List<ExtractPlate> selectAll();

    /**
     * 更新信息
     * @param record
     * @return
     */
    public int updateByPrimaryKey(ExtractPlate record);

    /**
     * 根据id删除信息
     * @param id
     * @return
     */
    public int deleteFlagById(String id);

    /**
     * 根据条件查询提取表信息
     * @param extractPlateVo
     * @return
     */
    public List<ExtractPlateVo> selectListVo(ExtractPlateVo extractPlateVo);
    /*
* 条件查询
* */
    public List<ExtractPlateVo> selectPaginationList(ExtractPlateVo extractPlateVo);
    /*
   * 条件查询数量
   * */
    public int selectCount(ExtractPlateVo extractPlateVo);
}
