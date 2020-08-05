package com.bazl.clims.dao;

import com.bazl.clims.model.po.PoliceExperience;
import java.util.List;

public interface PoliceExperienceMapper {
    int insert(PoliceExperience record);

    List<PoliceExperience> selectAll();
}