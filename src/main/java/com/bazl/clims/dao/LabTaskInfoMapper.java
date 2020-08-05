package com.bazl.clims.dao;

import com.bazl.clims.model.po.LabTaskInfo;
import com.bazl.clims.model.vo.LabTaskInfoVo;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2019/3/8.
 */
public interface LabTaskInfoMapper {

    /**
     * 根据taskId删除信息
     * @param taskId
     * @return
     */
    public int deleteByPrimaryKey(String taskId);

    /**
     * 插入信息
     * @param record
     * @return
     */
    public int insert(LabTaskInfo record);

    /**
     * 根据taskId查询信息
     * @param taskId
     * @return
     */
    public LabTaskInfo selectByPrimaryKey(String taskId);

    /**
     * 查询所有的信息
     * @return
     */
    public List<LabTaskInfoVo> selectAll(LabTaskInfoVo record);

    /**
     * 更新信息
     * @param record
     * @return
     */
    public int updateByPrimaryKey(LabTaskInfo record);

    /**
     * 查询检验任务列表页面
     * @param record
     * @return
     */
    public List<LabTaskInfo> selectLabTaskInfo(LabTaskInfo record);

    int selectVOCount(LabTaskInfoVo record);
}