package com.bazl.clims.service.impl;

import com.bazl.clims.dao.LoaPermissionMapper;
import com.bazl.clims.model.LoaPermission;
import com.bazl.clims.model.TreeData;
import com.bazl.clims.service.LoaPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sun on 2018/12/20.
 */
@Service
public class LoaPermissionServiceImpl implements LoaPermissionService {

    @Autowired
    LoaPermissionMapper loaPermissionMapper;

    @Override
    public int deleteByPrimaryKey(String permissionId) {
        return 0;
    }

    @Override
    public int insert(LoaPermission record) {
        return 0;
    }

    @Override
    public LoaPermission selectByPrimaryKey(String permissionId) {
        return null;
    }

    @Override
    public List<LoaPermission> selectAll() {
        return loaPermissionMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(LoaPermission record) {
        return 0;
    }

    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public List<LoaPermission> listByRoleId(String roleId) {
          return loaPermissionMapper.listByRoleId(roleId);
//        List<LoaPermission> permiss = (List<LoaPermission>) redisTemplate.boundHashOps("loaPermission").get(roleId);
//        if(permiss==null){
//            System.out.println("缓存中没有权限信息，权限信息加入缓存");
//            permiss =  loaPermissionMapper.listByRoleId(roleId);
//            redisTemplate.boundHashOps("loaPermission").put(roleId,permiss);
//
//        }else {
//            System.out.println("从缓存中读取权限信息");
//        }
//
//        return permiss;
    }

    @Override
    public List<LoaPermission> selectBy(String roleId) {
        return loaPermissionMapper.selectBy(roleId);
    }

    private String menuParentId;
    @Value("${permission.menu.menuParentPId}")
    private String menuParentPId;
    @Value("${permission.menu.menuParentName}")
    private String menuParentName;
    @Value("${permission.menu.menuParentEdit}")
    private Boolean menuParentEdit;
    public void setMenuParentId(String menuParentId) {
        this.menuParentId = menuParentId;
    }
    public void setMenuParentPId(String menuParentPId) {
        this.menuParentPId = menuParentPId;
    }
    public void setMenuParentName(String menuParentName) {
        this.menuParentName = menuParentName;
    }
    public void setMenuParentEdit(Boolean menuParentEdit) {
        this.menuParentEdit = menuParentEdit;
    }
    @Override
    public List<TreeData> selectPermissionList() {
        ArrayList<TreeData> treeData = new ArrayList<>();
        TreeData menuParent = new TreeData(menuParentId,menuParentPId,menuParentName,menuParentEdit);
        menuParent.setNocheck(true);
        treeData.add(menuParent);
        selectPermission(menuParent.getId(),treeData);
        return treeData;
    }
    private void selectPermission(String parentId,List<TreeData> list){
        List<TreeData> treeDataList =loaPermissionMapper.selectPermissionByParentId(parentId);
        for (TreeData treeData : treeDataList) {
            this.selectPermission(treeData.getId(),list);
            treeData.setpId(parentId);
        }
        list.addAll(treeDataList);
    }


    @Override
    public int saveMenu(LoaPermission permission) {
        if (Objects.nonNull(permission)) {
            if ("".equals(permission.getPermissionId())) {
                permission.setPermissionId(null);
            }
            if ("".equals(permission.getParentId())) {
                permission.setParentId(null);
                permission.setRootFlag("1");
            }else{
                permission.setRootFlag("0");
            }
        }
        if (permission.getPermissionId() != null) {
            return loaPermissionMapper.updatePermission(permission);
        }
        permission.setActiveFlag("0");
        permission.setDeleteflag("0");
        permission.setSubsystemFlag("3");
        return loaPermissionMapper.insertPermission(permission);
    }

    @Override
    public int delPermission(LoaPermission permission) {
        return loaPermissionMapper.delPermission(permission);
    }
}
