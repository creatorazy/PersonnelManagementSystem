package com.pms.service.impl;

import com.pms.dao.AdminDao;
import com.pms.dao.PermissionDao;
import com.pms.entity.Permission;
import com.pms.service.AdminService;
import com.pms.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service("permissionService")
public class PermissionServiceImpl extends BaseServiceImpl<Permission> implements PermissionService {

    @Resource
    private PermissionDao permissionDao;

    @Autowired
    public void setBaseDao(PermissionDao permissionDao){
        super.baseDao = permissionDao;
    }

}
