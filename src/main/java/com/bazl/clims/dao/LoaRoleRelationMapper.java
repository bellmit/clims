package com.bazl.clims.dao;

import com.bazl.clims.model.LoaRoleRelation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoaRoleRelationMapper {

    void addLoaRoleRelation(LoaRoleRelation loaRoleRelation);

    void deleteloaRoleRelationByUserId(String userId);

    void updateloaRoleRelationByUserId(@Param("roleId") String roleId, @Param("userId")String userId);

    /**
     * 通过用户Id获取关联的角色
     * @param userId
     * @return
     */
    List<LoaRoleRelation> listByUserId(String userId);
}