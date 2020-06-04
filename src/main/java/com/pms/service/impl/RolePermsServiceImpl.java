package com.pms.service.impl;

import com.pms.entity.RolePerms;
import com.pms.dao.RolePermsDao;
import com.pms.service.RolePermsService;
import com.pms.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (RolePerms)表服务实现类
 *
 * @author makejava
 * @since 2020-05-30 12:20:40
 */
@Service("rolePermsService")
public class RolePermsServiceImpl extends BaseServiceImpl<RolePerms> implements RolePermsService {

    @Resource
    private RolePermsDao rolePermsDao;

    public void setBaseDao(RolePermsDao rolePermsDao){super.baseDao = rolePermsDao;}

    @Override
    public int insertBitch(List<RolePerms> perms) {
        return rolePermsDao.insertBitch(perms);
    }
}