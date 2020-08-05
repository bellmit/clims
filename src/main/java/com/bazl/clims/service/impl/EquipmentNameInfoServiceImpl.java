package com.bazl.clims.service.impl;

import com.bazl.clims.dao.EquipmentNameInfoMapper;
import com.bazl.clims.model.PageInfo;
import com.bazl.clims.model.po.EquipmentNameInfo;
import com.bazl.clims.model.vo.EquipmentNameInfoVo;
import com.bazl.clims.service.EquipmentNameInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2019/3/11.
 */
@Service
public class EquipmentNameInfoServiceImpl extends BaseService implements EquipmentNameInfoService {
    
    @Autowired
    EquipmentNameInfoMapper equipmentNameInfoMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return equipmentNameInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(EquipmentNameInfo record) {
        return equipmentNameInfoMapper.insert(record);
    }

    @Override
    public EquipmentNameInfo selectByPrimaryKey(String id) {
        return equipmentNameInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<EquipmentNameInfo> selectAll() {
        return equipmentNameInfoMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(EquipmentNameInfo record) {
        return equipmentNameInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<EquipmentNameInfo> selectEquipmentNo(String equipmentNo){
        return equipmentNameInfoMapper.selectEquipmentNo(equipmentNo);
    }

    /**
     * 查询设备名称分页
     * @param query
     * @param pageInfo
     * @return
     */
    @Override
    public List<EquipmentNameInfoVo> selectEquipmentNameList(EquipmentNameInfoVo query, PageInfo pageInfo) {
        List<EquipmentNameInfoVo> equipmentNameInfoVOList = null;
        int pageNo;
        int pageSize;
        try {
            pageNo = pageInfo.getPage();
            pageSize = pageInfo.getEvePageRecordCnt();
            query.setOffset((pageNo - 1) * pageSize);
            query.setRows(query.getOffset() + pageSize);

            equipmentNameInfoVOList = equipmentNameInfoMapper.selectVOPaginationList(query);
        } catch(Exception ex) {
            logger.info("查询设备名称报错："+ex);
            return null;
        }

        return equipmentNameInfoVOList;
    }

    /**
     * 查询设备名称count
     * @param equipmentTypeInfoVo
     * @return
     */
    @Override
    public int selectEquipmentNameCount(EquipmentNameInfoVo equipmentTypeInfoVo) {
        return equipmentNameInfoMapper.selectVOCount(equipmentTypeInfoVo);
    }

    /**
     * 修改设备名称
     * @param equipmentNameInfo
     */
    @Override
    public void update(EquipmentNameInfo equipmentNameInfo) {
        equipmentNameInfoMapper.update(equipmentNameInfo);
    }

    @Override
    public List<EquipmentNameInfo> selectAllList() {
        return equipmentNameInfoMapper.selectAllList();
    }

    @Override
    public List<EquipmentNameInfo> selectEquipmentNameInfoListByTypeNo(EquipmentNameInfo equipmentNameInfo) {
        return equipmentNameInfoMapper.selectEquipmentNameInfoListByTypeNo(equipmentNameInfo);
    }

    @Override
    public List<EquipmentNameInfo> selectList(String orgId, String experimentalStage) {
        return equipmentNameInfoMapper.selectList(orgId,experimentalStage);
    }

    @Override
    public List<EquipmentNameInfo> selectEquipmentTypeId(String id) {
        return equipmentNameInfoMapper.selectEquipmentTypeId(id);
    }


}
