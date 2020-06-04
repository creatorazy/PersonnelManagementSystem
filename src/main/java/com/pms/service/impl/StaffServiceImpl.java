package com.pms.service.impl;

import com.pms.dao.DepartmentDao;
import com.pms.entity.Staff;
import com.pms.dao.StaffDao;
import com.pms.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Staff)表服务实现类
 *
 * @author makejava
 * @since 2020-05-15 11:06:41
 */
@Service("staffService")
public class StaffServiceImpl extends BaseServiceImpl<Staff> implements StaffService {
    @Resource
    private StaffDao staffDao;

    @Autowired
    public void setBaseDao(StaffDao staffDao){
        super.baseDao = staffDao;
    }
}