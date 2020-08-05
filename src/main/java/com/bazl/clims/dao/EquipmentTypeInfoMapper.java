package com.bazl.clims.dao;

import com.bazl.clims.model.po.EquipmentNameInfo;
import com.bazl.clims.model.po.EquipmentTypeInfo;
import com.bazl.clims.model.vo.EquipmentTypeInfoVo;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2019/3/11.
 */
public interface EquipmentTypeInfoMapper {

    public List<EquipmentTypeInfo> selectAllList();

    public void insert(EquipmentTypeInfo equipmentTypeInfo);

    public EquipmentTypeInfo selectByPrimaryKey(String id);

    public void update(EquipmentTypeInfo equipmentTypeInfo);

    public List<EquipmentTypeInfo> selectBy(String orgId);

    public List<EquipmentTypeInfoVo> selectVOPaginationList(EquipmentTypeInfoVo equipmentTypeInfoVo);

    public int selectVOCount(EquipmentTypeInfoVo equipmentTypeInfoVo);

    /**
     * 根据条件查询信息
     * @param equipmentTypeInfo
     * @return
     */
    public List<EquipmentTypeInfo> selectList(EquipmentTypeInfo equipmentTypeInfo);

    List<EquipmentTypeInfo> selectLabEquipmentTypeList(EquipmentTypeInfo equipmentTypeInfo);

    /**
     * 根据设备类型id查询设备名称
     * @param id
     * @return
     */
    List<EquipmentTypeInfo> selectEquipmentById(String id);
}