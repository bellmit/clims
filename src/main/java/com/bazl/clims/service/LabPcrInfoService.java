package com.bazl.clims.service;

import com.bazl.clims.model.PageInfo;
import com.bazl.clims.model.po.LabPcrInfo;
import com.bazl.clims.model.vo.LabPcrInfoVo;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2019/3/11.
 */
public interface LabPcrInfoService {

    /**
     * 根据主键id删除信息
     * @param pcrId
     * @return
     */
    public int deleteByPrimaryKey(String pcrId);

    /**
     * 插入信息
     * @param record
     * @return
     */
    public int insert(LabPcrInfo record);

    /**
     * 根据主键id查询信息
     * @param pcrId
     * @return
     */
    public LabPcrInfo selectByPrimaryKey(String pcrId);

    /**
     * 查询所有信息
     * @return
     */
    public List<LabPcrInfo> selectAll();

    /**
     * 更新信息
     * @param record
     * @return
     */
    public int updateByPrimaryKey(LabPcrInfo record);

    /**
     * 根据任务id查询扩增实验信息
     * @param taskId
     * @return
     */
    public List<LabPcrInfo> selectByTaskId(String taskId);

    /**
     * 查询拼板板号
     * @return
     */
    public List<LabPcrInfo> selectByBoarsNo(LabPcrInfo record);

    /**
     * 根据检材id查询信息
     * @param sampleId
     * @return
     */
    public List<LabPcrInfo> selectListBySampleId(String sampleId);

    /**
     * 查询扩增阶段列表
     * @return
     */
    public List<LabPcrInfo> selectAllList(LabPcrInfo record);

    /**
     * 查询是否有重复板号
     * @return
     */
    public List<LabPcrInfo> selectRepeatingByBoarsNo(LabPcrInfo record);

    /**
     * 分页查询
     * @param labPcrInfoVo
     * @return
     */
    public List<LabPcrInfoVo> selectVoList(LabPcrInfoVo labPcrInfoVo, PageInfo pageInfo);

    /**
     * 分页数量查询
     * @param labPcrInfoVo
     * @return
     */
    public int selectVOCount(LabPcrInfoVo labPcrInfoVo);

    /**
     * 根据案件id获取实验信息
     * @param caseId
     * @return
     */
    public List<LabPcrInfo> selectByCaseId(String caseId);

}
