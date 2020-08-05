package com.bazl.clims.service;

import com.bazl.clims.model.PageInfo;
import com.bazl.clims.model.po.EquipmentNameInfo;
import com.bazl.clims.model.po.LabSyInfo;
import com.bazl.clims.model.vo.EquipmentNameInfoVo;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2019/3/11.
 */
public interface EquipmentNameInfoService {

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
    public int insert(EquipmentNameInfo record);

    /**
     * 根据主键id查询信息
     * @param id
     * @return
     */
    public EquipmentNameInfo selectByPrimaryKey(String id);

    /**
     * 查询所有信息
     * @return
     */
    public List<EquipmentNameInfo> selectAll();

    /**
     * 更新信息
     * @param record
     * @return
     */
    public int updateByPrimaryKey(EquipmentNameInfo record);

    public List<EquipmentNameInfo> selectEquipmentNo(String equipmentNo);

    /**
     * 查询设备名称分页
     * @param equipmentNameInfoVo
     * @param pageInfo
     * @return
     */
    public List<EquipmentNameInfoVo> selectEquipmentNameList(EquipmentNameInfoVo equipmentNameInfoVo, PageInfo pageInfo);

    /**
     * 查询设备名称count
     * @param equipmentNameInfoVo
     * @return
     */
    public int selectEquipmentNameCount(EquipmentNameInfoVo equipmentNameInfoVo);

    /**
     * 修改设备名称
     * @param equipmentNameInfo
     */
    public void update(EquipmentNameInfo equipmentNameInfo);

    public List<EquipmentNameInfo> selectAllList();

    /**
     * 查询设备名称
     * @param equipmentNameInfo
     * @return
     */
    public List<EquipmentNameInfo> selectEquipmentNameInfoListByTypeNo(EquipmentNameInfo equipmentNameInfo);

    /**
     * 根据各阶段设备名称
     * @param orgId
     * @return
     */
    public List<EquipmentNameInfo> selectList(String orgId,String experimentalStage);

    List<EquipmentNameInfo> selectEquipmentTypeId(String id);
}
