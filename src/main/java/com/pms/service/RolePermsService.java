package com.pms.service;

import com.pms.entity.RolePerms;
import java.util.List;

/**
 * (RolePerms)表服务接口
 *
 * @author makejava
 * @since 2020-05-30 12:20:40
 */
public interface RolePermsService extends BaseService<RolePerms>{

    int insertBitch(List<RolePerms> perms);
}