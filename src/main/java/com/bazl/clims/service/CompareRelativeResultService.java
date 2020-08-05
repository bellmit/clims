package com.bazl.clims.service;

import com.bazl.clims.model.po.CompareRelativeResult;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2019/4/19.
 */
public interface CompareRelativeResultService {

    /**
     * 根据主键id删除信息
     * @param id
     * @return
     */
    public int deleteByPrimaryKey(String id);

    /**
     * 插入信息
     * @param record
     * @return
     */
    public int insert(CompareRelativeResult record);

    /**
     * 根据主键id查询信息
     * @param id
     * @return
     */
    public CompareRelativeResult selectByPrimaryKey(String id);

    /**
     * 查询所有信息
     * @return
     */
    public List<CompareRelativeResult> selectAll();

    /**
     * 更新信息
     * @param record
     * @return
     */
    public int updateByPrimaryKey(CompareRelativeResult record);

    /**
     * 根据案件编号删除信息
     * @param caseId
     * @return
     */
    public int deleteByCaseId(String caseId);

    /**
     * 根据案件id查询信息
     * @param caseId
     * @return
     */
    public List<CompareRelativeResult> selectListByCaseId(String caseId);

}
