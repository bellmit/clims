package com.bazl.clims.service;

import com.bazl.clims.model.PageInfo;
import com.bazl.clims.model.po.LabAnalysisInfo;
import com.bazl.clims.model.vo.LabAnalysisInfoVo;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2019/3/27.
 */
public interface LabAnalysisInfoService {
    /**
     * 根据id删除信息
     * @param id
     * @return
     */
    public int deleteByPrimaryKey(String id);

    /**
     * 插入信息
     * @param record
     * @return
     */
    public int insert(LabAnalysisInfo record);

    /**
     * 根据id查询信息
     * @param id
     * @return
     */
    public LabAnalysisInfo selectByPrimaryKey(String id);

    /**
     * 查询所有的信息
     * @return
     */
    public List<LabAnalysisInfo> selectAll();

    /**
     * 更新信息
     * @param record
     * @return
     */
    public int updateByPrimaryKey(LabAnalysisInfo record);

    /**
     * 查询上样本
     * @return
     */
    public List<LabAnalysisInfoVo> selectLabAnalysisInfoList(LabAnalysisInfoVo labAnalysisInfoVo, PageInfo query);

    /**
     * 查询上样本count
     * @param labAnalysisInfoVo
     * @return
     */
    public int selectLabAnalysisInfoCount(LabAnalysisInfoVo labAnalysisInfoVo);

    /**
     * 确认审核并上报
     * @param labAnalysisInfo
     */
    public void updateuSatus(LabAnalysisInfo labAnalysisInfo);

    /**
     * 板号去重
     * @param labAnalysisInfoVo
     * @return
     */
    public List<LabAnalysisInfoVo> selectVOByBoardNo(LabAnalysisInfoVo labAnalysisInfoVo);
}
