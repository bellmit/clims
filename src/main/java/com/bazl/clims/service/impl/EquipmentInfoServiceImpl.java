package com.bazl.clims.service.impl;

import com.bazl.clims.dao.EquipmentInfoMapper;
import com.bazl.clims.dao.EquipmentNameInfoMapper;
import com.bazl.clims.model.PageInfo;
import com.bazl.clims.model.po.EquipmentInfo;
import com.bazl.clims.model.po.EquipmentNameInfo;
import com.bazl.clims.model.vo.EquipmentInfoVo;
import com.bazl.clims.model.vo.EquipmentNameInfoVo;
import com.bazl.clims.service.EquipmentInfoService;
import com.bazl.clims.service.EquipmentNameInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2019/3/11.
 */
@Service
public class EquipmentInfoServiceImpl extends BaseService implements EquipmentInfoService {
    
    @Autowired
    EquipmentInfoMapper equipmentInfoMapper;

    /**
     * 查询设备分页
     * @param query
     * @param pageInfo
     * @return
     */
    @Override
    public List<EquipmentInfoVo> selectEquipmentInfoList(EquipmentInfoVo query, PageInfo pageInfo) {
        List<EquipmentInfoVo> equipmentInfoVOList = null;
        int pageNo;
        int pageSize;
        try {
            pageNo = pageInfo.getPage();
            pageSize = pageInfo.getEvePageRecordCnt();
            query.setOffset((pageNo - 1) * pageSize);
            query.setRows(query.getOffset() + pageSize);

            equipmentInfoVOList = equipmentInfoMapper.selectVOPaginationList(query);
        } catch(Exception ex) {
            logger.info("查询设备名称报错："+ex);
            return null;
        }

        return equipmentInfoVOList;
    }

    /**
     * 查询设备count
     * @param equipmentInfoVo
     * @return
     */
    @Override
    public int selectEquipmentInfoCount(EquipmentInfoVo equipmentInfoVo) {
        return equipmentInfoMapper.selectVOCount(equipmentInfoVo);
    }

    @Override
    public EquipmentInfo selectByPrimaryKey(String id) {
        return equipmentInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(EquipmentInfo equipmentInfo) {
        equipmentInfoMapper.update(equipmentInfo);
    }

    @Override
    public void insert(EquipmentInfo equipmentInfo) {
        equipmentInfoMapper.insert(equipmentInfo);
    }

    @Override
    public List<EquipmentInfo> selectAll() {
        return equipmentInfoMapper.selectAll();
    }

    /**
     * 查询报废设备
     * @param query
     * @param pageInfo
     * @return
     */
    @Override
    public List<EquipmentInfoVo> selectEquipmentScrapInfoList(EquipmentInfoVo query, PageInfo pageInfo) {
        List<EquipmentInfoVo> equipmentInfoVOList = null;
        int pageNo;
        int pageSize;
        try {
            pageNo = pageInfo.getPage();
            pageSize = pageInfo.getEvePageRecordCnt();
            query.setOffset((pageNo - 1) * pageSize);
            query.setRows(query.getOffset() + pageSize);

            equipmentInfoVOList = equipmentInfoMapper.selectEquipmentScrapInfoList(query);
        } catch(Exception ex) {
            logger.info("查询查询报废设备报错："+ex);
            return null;
        }

        return equipmentInfoVOList;
    }

    /**
     * 查询报废设备
     * @param equipmentInfoVo
     * @return
     */
    @Override
    public int selectEquipmentScrapInfoCount(EquipmentInfoVo equipmentInfoVo) {
        return equipmentInfoMapper.selectEquipmentScrapInfoCount(equipmentInfoVo);
    }

    @Override
    public EquipmentInfoVo selectById(String id) {
        return equipmentInfoMapper.selectById(id);
    }
}
