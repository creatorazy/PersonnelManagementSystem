package com.pms.dao;

import com.pms.entity.AdminRole;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (AdminRole)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-30 12:20:42
 */
public interface AdminRoleDao extends BaseDao<AdminRole>{

    List<Integer> queryRoleIdByAdminId(int id);

    Integer delByAdminIdAndRoleId(@Param("roleIds")List<Integer> roleIds,@Param("adminId")Integer adminId);
}