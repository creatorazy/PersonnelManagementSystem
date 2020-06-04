package com.pms.dao;

import com.pms.entity.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface PermissionDao extends BaseDao<Permission>{

    /**
     * 根据登陆名查询拥有权限
     * @param name
     * @return
     */
    Set<String> queryPermissionNameByLoginName(String name);

    /**
     * 批量插入数据
     * @param data
     * @return
     */
    int insert(@Param("data")List<Permission> data);

    /**
     * 查询已存在权限名
     * @return
     */
    Set<String> queryPermissions();
}
