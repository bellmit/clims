package com.bazl.clims.dao;

import com.bazl.clims.model.LaboratoryInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaboratoryInfoMapper {
    List<LaboratoryInfo> queryList();

    LaboratoryInfo queryByLab(LaboratoryInfo laboratoryInfo);

    void updateLabInfo(LaboratoryInfo laboratoryInfo);
}
