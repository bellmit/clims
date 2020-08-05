package com.bazl.clims.dao;

import com.bazl.clims.model.po.LabSyInfo;
import com.bazl.clims.model.vo.LabSyInfoVo;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2019/3/11.
 */
public interface LabSyInfoMapper {

    /**
     * 根据主键id删除信息
     * @param syId
     * @return
     */
    public int deleteByPrimaryKey(String syId);

    /**
     * 插入信息
     * @param record
     * @return
     */
    public int insert(LabSyInfo record);

    /**
     * 根据主键id查询信息
     * @param syId
     * @return
     */
    public LabSyInfo selectByPrimaryKey(String syId);

    /**
     * 查询所有信息
     * @return
     */
    public List<LabSyInfo> selectAll();

    /**
     * 更新信息
     * @param record
     * @return
     */
    public int updateByPrimaryKey(LabSyInfo record);

    /**
     * 根据任务id查询上样记录
     * @param taskId
     * @return
     */
    public List<LabSyInfo> selectByTaskId(String taskId);

    /**
     * 查询拼板板号
     * @return
     */
    public List<LabSyInfo> selectByBoarsNo(LabSyInfo record);

    /**
     * 根据条件查询信息
     * @param labSyInfo
     * @return
     */
    public List<LabSyInfo> selectList(LabSyInfo labSyInfo);


    /**
     * 根据条件分页查询
     * @param labSyInfoVo
     * @return
     */
    public List<LabSyInfoVo> selectVOPaginationList(LabSyInfoVo labSyInfoVo);

    /**
     * 根据条件查询数量
     * @param labSyInfoVo
     * @return
     */
    public int selectVOCnt(LabSyInfoVo labSyInfoVo);

    /**
     * 查询是否有重复板号
     * @return
     */
    public List<LabSyInfo> selectRepeatingByBoarsNo(LabSyInfo record);

    /**
     * 根据案件id查询实验信息
     * @param caseId
     * @return
     */
    public List<LabSyInfo> selectByCaseId(String caseId);
}