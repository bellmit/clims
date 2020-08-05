package com.bazl.clims.service;

import com.bazl.clims.model.PageInfo;
import com.bazl.clims.model.po.EquipmentNameInfo;
import com.bazl.clims.model.po.EquipmentRepairInfo;
import com.bazl.clims.model.vo.EquipmentNameInfoVo;
import com.bazl.clims.model.vo.EquipmentRepairInfoVo;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2019/3/11.
 */
public interface EquipmentRepairInfoService {

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
     * 查询设备维修分页
     * @param equipmentRepairInfoVo
     * @param pageInfo
     * @return
     */
    public List<EquipmentRepairInfoVo> selectEquipmentRepairList(EquipmentRepairInfoVo equipmentRepairInfoVo, PageInfo pageInfo);

    /**
     * 查询设备维修count
     * @param equipmentRepairInfoVo
     * @return
     */
    public int selectEquipmentRepairCount(EquipmentRepairInfoVo equipmentRepairInfoVo);
}
