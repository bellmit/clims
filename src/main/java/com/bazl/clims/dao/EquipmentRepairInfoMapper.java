package com.bazl.clims.dao;

import com.bazl.clims.model.po.EquipmentNameInfo;
import com.bazl.clims.model.po.EquipmentRepairInfo;
import com.bazl.clims.model.vo.EquipmentNameInfoVo;
import com.bazl.clims.model.vo.EquipmentRepairInfoVo;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2019/3/11.
 */
public interface EquipmentRepairInfoMapper {

    /**
     * 插入信息
     * @param record
     * @return
     */
    public int insert(EquipmentRepairInfo record);

    /**
     * 根据主键id查询信息
     * @param id
     * @return
     */
    public EquipmentRepairInfo selectByPrimaryKey(String id);

    /**
     * 更新信息
     * @param record
     * @return
     */
    public int updateByPrimaryKey(EquipmentRepairInfo record);

    /**
     * 查询设备名称分页
     * @param equipmentRepairInfoVo
     * @return
     */
    public List<EquipmentRepairInfoVo> selectVOPaginationList(EquipmentRepairInfoVo equipmentRepairInfoVo);

    /**
     * 查询设备名称count
     * @param equipmentRepairInfoVo
     * @return
     */
    public int selectVOCount(EquipmentRepairInfoVo equipmentRepairInfoVo);

}