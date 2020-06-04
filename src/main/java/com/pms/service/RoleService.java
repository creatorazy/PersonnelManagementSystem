package com.pms.service;

import com.pms.entity.Permission;
import com.pms.entity.Role;
import com.pms.service.BaseService;

import java.util.List;
import java.util.Set;

public interface RoleService extends BaseService<Role> {
    /**
     * 根据登陆名查询拥有角色
     * @param name
     * @return
     */
    Set<String> queryRoleNameByLoginName(String name);

    /**
     * 查询所有角色
     *
     * @return
     */
    List<Role> queryAllRole();

    int insertRole(Role role,List<Integer> perms);

    int upRole(Role role,List<Integer> perms);


}
