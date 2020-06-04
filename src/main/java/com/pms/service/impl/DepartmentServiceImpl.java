package com.pms.service.impl;

import com.pms.entity.Department;
import com.pms.dao.DepartmentDao;
import com.pms.service.AdminService;
import com.pms.service.BaseService;
import com.pms.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Department)表服务实现类
 *
 * @author makejava
 * @since 2020-05-15 11:11:46
 */
@Service("departmentService")
public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements DepartmentService {
    @Resource
    private DepartmentDao departmentDao;
    @Autowired
    public void setBaseDao(DepartmentDao departmentDao){
        super.baseDao = departmentDao;
    }

}