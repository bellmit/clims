package com.bazl.clims.dao;

import com.bazl.clims.model.LoaRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoaRoleMapper {
    /**
     * 根据用户id查询所有角色
     * @param userId
     * @return
     */
    public List<LoaRole> listRolesByUserId(String userId);

    List<LoaRole> queryLoaRole();

    /**
     * 根据人员Id查询受理人角色
     * @param personalId
     * @return
     */
    List<LoaRole> listRolesByPersonalId(String personalId);

    int deleteByPrimaryKey(String roleId);

    int insert(LoaRole record);

    LoaRole selectByPrimaryKey(String roleId);

    List<LoaRole> selectAll();

    int updateByPrimaryKey(LoaRole record);

//这是dao层 点击编辑的时候 选中 权限的菜单的方法  dao层
    List<String> selectMenuInfoByRoleId(@Param("loaRole") LoaRole loaRole);

    //这里是删除 权限对应的菜单的选项  dao层
    int deleteOldMenus(@Param("oldMenus") List<String> oldMenus, @Param("amRole")LoaRole amRole);
    //这里是 添加权限对应的菜单的选项 dao层
    int insertMenuInfo(@Param("menuId")String meunId,@Param("amRole") LoaRole amRole);

    //这里是添加权限的  dao层
    int insertRoleInfo(@Param("loaRole") LoaRole loaRole);



}