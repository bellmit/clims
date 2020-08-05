package com.bazl.clims.dao;

import com.bazl.clims.model.po.AmTopicPerson;
import java.util.List;

public interface AmTopicPersonMapper {
    int deleteByPrimaryKey(String id);

    int insert(AmTopicPerson record);

    AmTopicPerson selectByPrimaryKey(String id);

    List<AmTopicPerson> selectAll();

    int updateByPrimaryKey(AmTopicPerson record);
}