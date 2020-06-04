package com.pms.dao;

import com.pms.entity.Permission;
import com.pms.entity.Role;

import java.util.List;
import java.util.Set;

public interface RoleDao extends BaseDao<Role>{
    /**
     * 根据登陆名查询拥有角色
     * @param name
     * @return
     */
    Set<String> queryRoleNameByLoginName(String name);

    /**
     * 查询所有角色
     * @return
     */
    List<Role> queryAllRole();

    /**
     * 查询所有角色
     * @return
     */
    List<Role> queryRoleIdByAdminId();


}
