package com.bazl.clims.shiro;

import com.bazl.clims.common.Constants;
import com.bazl.clims.model.LoaPermission;
import com.bazl.clims.model.LoaRole;
import com.bazl.clims.model.LoaUserInfo;
import com.bazl.clims.model.ToConfigure;
import com.bazl.clims.service.LoaPermissionService;
import com.bazl.clims.service.LoaRoleService;
import com.bazl.clims.service.LoaUserInfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by Sun on 2018/12/20.
 */
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private LoaUserInfoService userInfoService;
    @Autowired
    private LoaRoleService roleService;
    @Autowired
    private LoaPermissionService permissionService;
    @Autowired
    private ToConfigure bean;

    //认证.登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken utoken=(UsernamePasswordToken) token;//获取用户输入的token

        LoaUserInfo user = new LoaUserInfo();
        if("1".equals(bean.getLimsEdition())){
            String username = utoken.getUsername();
            user = userInfoService.selectByUserName2(username);

        }else{
            String[] userAreaStr=utoken.getUsername().split("-");
            user = userInfoService.selectByUserName(userAreaStr[0],userAreaStr[1]);
        }




        if (user == null) {
            throw new UnknownAccountException("账号不存在！");
        }
        if (user.getActiveFlag() != null && Constants.user_active_flase.equals(user.getActiveFlag())) {
            throw new LockedAccountException("帐号已被锁定，禁止登录！");
        }
        //放入shiro.调用CredentialsMatcher检验密码
        return new SimpleAuthenticationInfo(user, user.getLoginPassword(),this.getClass().getName());
    }
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        // 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        LoaUserInfo user = (LoaUserInfo) SecurityUtils.getSubject().getPrincipal();
        String userId = user.getUserId();

        // 赋予角色
        List<LoaRole> roleList = roleService.listRolesByUserId(userId);
        for (LoaRole role : roleList) {
            info.addRole(role.getRoleName());
        }

        // 赋予权限
        for (LoaRole role : roleList) {
            if(role != null && !StringUtils.isEmpty(role.getRoleId())){}
            List<LoaPermission> permissionList = permissionService.listByRoleId(role.getRoleId());
            if (!CollectionUtils.isEmpty(permissionList)) {
                for (LoaPermission permission : permissionList) {
                    if (!StringUtils.isEmpty(permission.getPermissionName())) {
                        info.addStringPermission(permission.getPermissionName());
                    }
                }
            }
        }
        return info;
    }
}
