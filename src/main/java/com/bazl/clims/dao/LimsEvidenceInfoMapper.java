package com.bazl.clims.dao;

import com.bazl.clims.model.po.LimsEvidenceInfo;
import java.util.List;

public interface LimsEvidenceInfoMapper {
    int deleteByPrimaryKey(String evidenceId);

    int insert(LimsEvidenceInfo record);

    LimsEvidenceInfo selectByPrimaryKey(String evidenceId);

    List<LimsEvidenceInfo> selectAll();

    int updateByPrimaryKey(LimsEvidenceInfo record);
}