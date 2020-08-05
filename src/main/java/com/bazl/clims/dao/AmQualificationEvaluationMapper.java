package com.bazl.clims.dao;

import com.bazl.clims.model.po.AmQualificationEvaluation;
import java.util.List;

public interface AmQualificationEvaluationMapper {
    int deleteByPrimaryKey(String id);

    int insert(AmQualificationEvaluation record);

    AmQualificationEvaluation selectByPrimaryKey(String id);

    List<AmQualificationEvaluation> selectAll();

    int updateByPrimaryKey(AmQualificationEvaluation record);
}