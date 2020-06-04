package com.pms.service.impl;

import com.pms.dao.AdminDao;
import com.pms.entity.AdminRole;
import com.pms.dao.AdminRoleDao;
import com.pms.service.AdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (AdminRole)表服务实现类
 *
 * @author makejava
 * @since 2020-05-30 12:20:42
 */
@Service("adminRoleService")
public class AdminRoleServiceImpl extends BaseServiceImpl<AdminRole> implements AdminRoleService {

    @Resource
    private AdminRoleDao adminRoleDao;

    @Autowired
    public void setBaseDao(AdminRoleDao adminRoleDao){
        super.baseDao = adminRoleDao;
    }

    @Override
    public List<Integer> queryRoleIdByAdminId(int id) {
        return adminRoleDao.queryRoleIdByAdminId(id);
    }

    @Override
    public Integer delByAdminIdAndRoleId(List<Integer> roleIds, Integer adminId) {
        return adminRoleDao.delByAdminIdAndRoleId(roleIds,adminId);
    }
}