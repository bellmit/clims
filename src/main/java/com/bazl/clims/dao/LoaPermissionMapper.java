package com.bazl.clims.dao;

import com.bazl.clims.model.LoaPermission;
import com.bazl.clims.model.TreeData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoaPermissionMapper {
    int deleteByPrimaryKey(String permissionId);

    int insert(LoaPermission record);

    LoaPermission selectByPrimaryKey(String permissionId);

    List<LoaPermission> selectAll();

    List<LoaPermission> selectBy(String roleId);

    int updateByPrimaryKey(LoaPermission record);

    /**
     *根据角色id获取权限
     * @param roleId
     * @return
     */
    public List<LoaPermission> listByRoleId(String roleId);


    List<TreeData> selectPermissionByParentId(@Param("parentId") String parentId);

    int updatePermission(@Param("permission") LoaPermission permission);

    int insertPermission(@Param("permission") LoaPermission permission);

    int delPermission(@Param("permission") LoaPermission permission);



}