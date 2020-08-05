package com.bazl.clims.service.impl;

import com.bazl.clims.dao.EquipmentNameInfoMapper;
import com.bazl.clims.dao.EquipmentTypeInfoMapper;
import com.bazl.clims.model.PageInfo;
import com.bazl.clims.model.po.EquipmentNameInfo;
import com.bazl.clims.model.po.EquipmentTypeInfo;
import com.bazl.clims.model.vo.EquipmentTypeInfoVo;
import com.bazl.clims.service.EquipmentNameInfoService;
import com.bazl.clims.service.EquipmentTypeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sxr
 * @date 2019/4/9.
 */
@Service
public class EquipmentTypeInfoServiceImpl extends BaseService implements EquipmentTypeInfoService {

    @Autowired
    EquipmentTypeInfoMapper equipmentTypeInfoMapper;

    /**
     * 查询设备类型
     * @return
     */
    @Override
    public List<EquipmentTypeInfo> selectAllList() {
        return equipmentTypeInfoMapper.selectAllList();
    }

    /**
     * 添加设备类型
     * @param equipmentTypeInfo
     */
    @Override
    public void insert(EquipmentTypeInfo equipmentTypeInfo) {
        equipmentTypeInfoMapper.insert(equipmentTypeInfo);
    }

    /**
     * 根据id查询设备类型
     * @param id
     * @return
     */
    @Override
    public EquipmentTypeInfo selectByPrimaryKey(String id) {
        return equipmentTypeInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<EquipmentTypeInfo> selectBy(String orgId) {
        return equipmentTypeInfoMapper.selectBy(orgId);
    }

    @Override
    public void update(EquipmentTypeInfo equipmentTypeInfo) {
        equipmentTypeInfoMapper.update(equipmentTypeInfo);
    }

    /**
     * 查询设备类型分页
     * @param query
     * @param pageInfo
     * @return
     */
    @Override
    public List<EquipmentTypeInfoVo> selectEquipmentTypeList(EquipmentTypeInfoVo query, PageInfo pageInfo) {
        List<EquipmentTypeInfoVo> equipmentTypeInfoVOList = null;
        int pageNo;
        int pageSize;
        try {
            pageNo = pageInfo.getPage();
            pageSize = pageInfo.getEvePageRecordCnt();
            query.setOffset((pageNo - 1) * pageSize);
            query.setRows(query.getOffset() + pageSize);

            equipmentTypeInfoVOList = equipmentTypeInfoMapper.selectVOPaginationList(query);
        } catch(Exception ex) {
            logger.info("查询设备类型报错："+ex);
            return null;
        }

        return equipmentTypeInfoVOList;
    }

    /**
     * 查询设备类型count
     * @param equipmentTypeInfoVo
     * @return
     */
    @Override
    public int selectEquipmentTypeCount(EquipmentTypeInfoVo equipmentTypeInfoVo) {
        return equipmentTypeInfoMapper.selectVOCount(equipmentTypeInfoVo);
    }

    @Override
    public List<EquipmentTypeInfo> selectList(EquipmentTypeInfo equipmentTypeInfo) {
        return equipmentTypeInfoMapper.selectList(equipmentTypeInfo);
    }

    @Override
    public List<EquipmentTypeInfo> selectLabEquipmentTypeList(EquipmentTypeInfo equipmentTypeInfo) {
        return equipmentTypeInfoMapper.selectLabEquipmentTypeList(equipmentTypeInfo);
    }

    @Override
    public List<EquipmentTypeInfo> selectEquipmentById(String id) {
        return equipmentTypeInfoMapper.selectEquipmentById(id);
    }
}
