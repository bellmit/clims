package com.bazl.clims.dao;

import com.bazl.clims.model.po.OrgInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrgInfoMapper {
    int insert(OrgInfo record);

    List<OrgInfo> selectAll();

    OrgInfo selectByPrimaryKey(String orgId);

    List<OrgInfo> selectDelegateByParentsId(String parentsId);

    List<OrgInfo> selectAcceptOrgList();

    String getUseridByOrgid(String userId);
}