package com.pms.dao;

import com.pms.entity.RolePerms;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (RolePerms)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-30 12:20:40
 */
public interface RolePermsDao extends BaseDao<RolePerms> {

    int insertBitch(@Param("perms") List<RolePerms> perms);
    int delByRoleId(int id);
}