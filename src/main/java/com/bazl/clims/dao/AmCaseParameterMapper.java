package com.bazl.clims.dao;

import com.bazl.clims.model.po.AmCaseParameter;
import java.util.List;

public interface AmCaseParameterMapper {
    int insert(AmCaseParameter record);

    List<AmCaseParameter> selectAll();
}