package com.pms.shiro.realm;

import com.pms.controller.PageJump;
import com.pms.dao.AdminDao;
import com.pms.dao.PermissionDao;
import com.pms.dao.RoleDao;
import com.pms.entity.Admin;
import com.pms.entity.Permission;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    PermissionDao permissionDao;
    @Autowired
    RoleDao roleDao;
    @Autowired
    AdminDao adminDao;


    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        if(pc.toString().equals("admin")){
            System.out.println("管理员登陆");
            simpleAuthorizationInfo.setStringPermissions(permissionDao.queryPermissions());
            System.out.println(permissionDao.queryPermissions());
        }else{
            simpleAuthorizationInfo.setRoles(roleDao.queryRoleNameByLoginName(pc.toString()));
            simpleAuthorizationInfo.setStringPermissions(permissionDao.queryPermissionNameByLoginName(pc.toString()));
        }


        return simpleAuthorizationInfo;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken at) throws AuthenticationException {
        Admin admin = adminDao.queryByLoginName(at.getPrincipal().toString());
        System.out.println();
        if (admin == null || admin.getState().equals("0")) {
            throw new UnknownAccountException();
        } else {
            return new SimpleAuthenticationInfo(at.getPrincipal(), admin.getPassword(), this.getName());
        }
    }
}
