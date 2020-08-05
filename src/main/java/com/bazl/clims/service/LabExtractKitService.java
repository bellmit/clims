package com.bazl.clims.service;

import com.bazl.clims.model.po.LabExtractKit;
import com.bazl.clims.model.po.LabPcrInfo;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2019/3/11.
 */
public interface LabExtractKitService {

    /**
     * 根据主键id删除信息
     * @param kitId
     * @return
     */
    public int deleteByPrimaryKey(String kitId);

    /**
     * 插入信息
     * @param record
     * @return
     */
    public int insert(LabExtractKit record);

    /**
     * 根据主键id查询信息
     * @param kitId
     * @return
     */
    public LabPcrInfo selectByPrimaryKey(String kitId);

    /**
     * 查询所有信息
     * @return
     */
    public List<LabExtractKit> selectAll();

    /**
     * 更新信息
     * @param record
     * @return
     */
    public int updateByPrimaryKey(LabExtractKit record);

    /**
     * 试剂盒ID查询
     * @param panelid
     * @return
     */
    public LabExtractKit selectPanelId(String panelid);

    /**
     * 试剂盒批号查询
     * @param panelid
     * @return
     */
    public LabExtractKit selectPanelName(String panelid);
}
