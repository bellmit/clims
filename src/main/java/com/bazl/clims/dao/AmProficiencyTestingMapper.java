package com.bazl.clims.dao;

import com.bazl.clims.model.po.AmProficiencyTesting;
import java.util.List;

public interface AmProficiencyTestingMapper {
    int insert(AmProficiencyTesting record);

    List<AmProficiencyTesting> selectAll();
}