package com.bazl.clims.service;

import com.bazl.clims.model.LoaRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoaRoleService {

    /**
     * 根据用户id查询所有角色
     * @param userId
     * @return
     */
    public List<LoaRole> listRolesByUserId(String userId);
    /**
     *查询角色信息表Loa_Role
     * 回显委托人添加权限设定
     */
    List<LoaRole> queryLoaRole();

    /**
     * 查询受理人角色
     * @param personalId
     * @return
     */
    List<LoaRole> listRolesByPersonalId(String personalId);

    int deleteByPrimaryKey(String roleId);

    int insert(LoaRole record);

    LoaRole selectByPrimaryKey(String roleId);

    List<LoaRole> selectAll();

    int updateByPrimaryKey(LoaRole record);

    // 查找出权限对应的菜单页面service层
    List<String> selectMenuInfo(LoaRole loaRole);

    //这里是添加  权限和对应的菜单的service层
    int insertRoleInfo(LoaRole loaRole,List<String> menus);


    //这里是页面的修改  权限和对应的菜单的service层
    int updateRoleInfo(LoaRole loaRole,List<String> menus);

    //删除
    int delRoleInfo(String roleId);





}