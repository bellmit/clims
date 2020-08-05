package com.bazl.clims.service;

import com.bazl.clims.model.DictInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DictInfoService {

    /**
     * 根据主键id删除信息
     * @param dictInfoId
     * @return
     */
    int deleteByPrimaryKey(String dictInfoId);

    /**
     * 插入信息
     * @param record
     * @return
     */
    int insert(DictInfo record);

    /**
     * 根据主键id查询信息
     * @param dictInfoId
     * @return
     */
    DictInfo selectByPrimaryKey(String dictInfoId);

    /**
     * 查询所有信息
     * @return
     */
    List<DictInfo> selectAll();

    /**
     * 更新信息
     * @param record
     * @return
     */
    int updateByPrimaryKey(DictInfo record);

    DictInfo selectByDictTypeCode(String dicttypecode);
}