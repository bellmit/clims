package com.bazl.clims.service.impl;

import com.bazl.clims.dao.LoaRoleMapper;
import com.bazl.clims.model.LoaRole;
import com.bazl.clims.service.LoaRoleService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class LoaRoleServiceImpl implements LoaRoleService{

    @Autowired
    LoaRoleMapper loaRoleMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<LoaRole> listRolesByUserId(String userId) {
          return loaRoleMapper.listRolesByUserId(userId);
//        List<LoaRole> loaRoless  = (List<LoaRole>) redisTemplate.boundHashOps("loaRoles").get(userId);
//        if(loaRoless==null){
//            System.out.println("没有此用户的角色信息");
//            loaRoless  = loaRoleMapper.listRolesByUserId(userId);
//            redisTemplate.boundHashOps("loaRoles").put(userId,loaRoless);
//        }else{
//            System.out.println("从缓存中读去用户的角色信息");
//        }
//
//        return loaRoless;
    }
    @Override
    public List<LoaRole> queryLoaRole() {
        return loaRoleMapper.queryLoaRole();
    }

    @Override
    public List<LoaRole> listRolesByPersonalId(String personalId) {
        return loaRoleMapper.listRolesByPersonalId(personalId);
    }

    @Override
    public int deleteByPrimaryKey(String roleId) {
        return loaRoleMapper.deleteByPrimaryKey(roleId);
    }

    @Override
    public int insert(LoaRole record) {
        return loaRoleMapper.insert(record);
    }

    @Override
    public LoaRole selectByPrimaryKey(String roleId) {
        return loaRoleMapper.selectByPrimaryKey(roleId);
    }

    @Override
    public List<LoaRole> selectAll() {
        return loaRoleMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(LoaRole record) {
        return loaRoleMapper.updateByPrimaryKey(record);
    }


    //这里是点击编辑的时候权限对应的菜单 的service.impl层
    @Override
    public List<String> selectMenuInfo(LoaRole amRole) {
        return loaRoleMapper.selectMenuInfoByRoleId(amRole);
    }

    //这里是添加的时候  权限和对应的菜单的service.impl层
    @Override
    public int insertRoleInfo(LoaRole loaRole,List<String> menus){
        //添加的时候  权限的名字和等级  这里sql写了下添加的id 和名字 还有等级 因为单表可以实现所以不用和下面那个一样
//        loaRoleMapper.insert(loaRole); 这里不能用这的原因应该是 他的id不是自增的需要生成 所以会报错要用下面的那个另写的sql
        loaRoleMapper.insertRoleInfo(loaRole);
        //这个是调用调用了下面的方法也就是 权限指定的菜单的天剑后者修改时候先删除再添加
        saveMenusInfo(loaRole,menus);
        return 0;
    }

    //修改的时候 权限和对应的菜单的service.impl层
    @Override
    public int updateRoleInfo(LoaRole loaRole, List<String> menus) {
        //这里直接根据反向生成的 sql就可以修改 权限表的名字和等级 以为在一个表内 所以不用很复杂
        loaRoleMapper.updateByPrimaryKey(loaRole);
        //然后这里去调用了下面的方法 先删除权限有的菜单 然后再添加进去
        saveMenusInfo(loaRole,menus);
        return 0;
    }

    //删除的方法
    @Override
    public int delRoleInfo(String roleId) {
        LoaRole loaRole = new LoaRole();
        loaRole.setRoleId(roleId);
        //这里直接调用反向生成的  根据id删除的权限 就可以了
        loaRoleMapper.deleteByPrimaryKey(roleId);
        //然后这里再把 这个权限id的菜单给删除掉  刚才在下面已经写了  直接可以调用
        loaRoleMapper.deleteOldMenus(new ArrayList<>(),loaRole);
        return 0;
    }


    //这是添加保存的时候  权限对应的菜单 的修改和添加 这里都是直接删除了再添加
    public void saveMenusInfo(LoaRole loaRole,List<String> menus){
        //查找权限对应的菜单
        List<String> oldMenus = loaRoleMapper.selectMenuInfoByRoleId(loaRole);
        if(Objects.isNull(menus)) menus= new ArrayList<>();
        if(Objects.isNull(oldMenus)) oldMenus= new ArrayList<>();
        oldMenus.retainAll(menus);
            loaRoleMapper.deleteOldMenus(oldMenus,loaRole);
        menus.removeAll(oldMenus);
        for (String menu:menus) {
            loaRoleMapper.insertMenuInfo(menu,loaRole);
        }
    }


}
