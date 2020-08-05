package com.bazl.clims.dao;


import com.bazl.clims.model.DictItem;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DictItemMapper {


    List<DictItem> selectListByDictCode(String dictCode);

    /**
     * 查询所有字典项内容
     * @return
     */
    List<DictItem> selectAllCode();

    /**
     * 根据dictTypeCode查询出对应字典项内容
     * @param dictTypeCode
     * @return
     */
    List<DictItem> selectListByDictTypeCode(String dictTypeCode);

    int deleteByPrimaryKey(String dictItemId);

    int insert(DictItem record);

    DictItem selectByPrimaryKey(String dictItemId);

    int updateByPrimaryKey(DictItem record);
}