package com.pms.service;

import com.pms.entity.AdminRole;
import java.util.List;

/**
 * (AdminRole)表服务接口
 *
 * @author makejava
 * @since 2020-05-30 12:20:42
 */
public interface AdminRoleService extends BaseService<AdminRole>{

    List<Integer> queryRoleIdByAdminId(int id);

    Integer delByAdminIdAndRoleId(List<Integer> roleIds,Integer adminId);

}