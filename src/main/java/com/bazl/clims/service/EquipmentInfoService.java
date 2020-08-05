package com.bazl.clims.service;

import com.bazl.clims.model.PageInfo;
import com.bazl.clims.model.po.EquipmentInfo;
import com.bazl.clims.model.po.EquipmentNameInfo;
import com.bazl.clims.model.vo.EquipmentInfoVo;
import com.bazl.clims.model.vo.EquipmentNameInfoVo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2019/3/11.
 */
public interface EquipmentInfoService {

    public List<EquipmentInfoVo> selectEquipmentInfoList(EquipmentInfoVo equipmentInfoVo, PageInfo pageInfo);

    int selectEquipmentInfoCount(EquipmentInfoVo equipmentInfoVo);

    public EquipmentInfo selectByPrimaryKey(String id);

    public void update(EquipmentInfo equipmentInfo);

    public void insert(EquipmentInfo equipmentInfo);

    public List<EquipmentInfo> selectAll();

    public List<EquipmentInfoVo> selectEquipmentScrapInfoList(EquipmentInfoVo equipmentInfoVo, PageInfo pageInfo);

    int selectEquipmentScrapInfoCount(EquipmentInfoVo equipmentInfoVo);

    public EquipmentInfoVo selectById(String id);
}
