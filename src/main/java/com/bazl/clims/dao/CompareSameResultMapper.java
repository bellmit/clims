package com.bazl.clims.dao;

import com.bazl.clims.model.po.CompareSameResult;
import java.util.List;

/**
 * @author wanghaiyang
 * @date 2019/4/19.
 */
public interface CompareSameResultMapper {

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
    public int insert(CompareSameResult record);

    /**
     * 根据主键id查询信息
     * @param id
     * @return
     */
    public CompareSameResult selectByPrimaryKey(String id);

    /**
     * 查询所有信息
     * @return
     */
    public List<CompareSameResult> selectAll();

    /**
     * 更新信息
     * @param record
     * @return
     */
    public int updateByPrimaryKey(CompareSameResult record);

    /**
     * 根据条件删除信息
     * @param caseId
     * @return
     */
    public int deleteByCaseId(String caseId);

    /**
     * 根据案件id查询信息
     * @param caseId
     * @return
     */
    public List<CompareSameResult> selectListByCaseId(String caseId);

    /**
     * 根据案件id去除重复referenceId的结果
     * @param caseId
     * @return
     */
    public List<CompareSameResult> selectDistinctListByCaseId(String caseId);
}