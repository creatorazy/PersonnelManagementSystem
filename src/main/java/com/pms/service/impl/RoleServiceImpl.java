package com.pms.service.impl;

import com.pms.dao.*;
import com.pms.entity.Permission;
import com.pms.entity.Role;
import com.pms.entity.RolePerms;
import com.pms.service.AdminService;
import com.pms.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {
    @Resource
    private RoleDao roleDao;

    @Resource
    private AdminRoleDao adminRoleDao;

    @Resource
    private RolePermsDao rolePermsDao;


    @Autowired
    public void setBaseDao(RoleDao roleDao) {
        super.baseDao = roleDao;
    }

    @Override
    public Set<String> queryRoleNameByLoginName(String name) {
        return null;
    }

    @Override
    public List<Role> queryAllRole() {
        return roleDao.queryAllRole();
    }

    @Override
    public int insertRole(Role role, List<Integer> perms) {
        roleDao.insert(role);
        List<RolePerms> list = new ArrayList<>();
        for (int i : perms) {
            list.add(new RolePerms(null, role.getId(), i));
        }
        return rolePermsDao.insertBitch(list);
    }

    @Override
    public int upRole(Role role, List<Integer> perms) {
        roleDao.update(role);
        List<RolePerms> list = new ArrayList<>();
        for (int i : perms) {
            list.add(new RolePerms(null, role.getId(), i));
        }
        rolePermsDao.delByRoleId(role.getId());
        return rolePermsDao.insertBitch(list);
    }

}
