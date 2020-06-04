package com.pms.controller;

import com.pms.entity.Staff;
import com.pms.service.DepartmentService;
import com.pms.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Staff)表控制层
 *
 * @author makejava
 * @since 2020-05-15 11:06:41
 */
@RestController
@RequestMapping("/staff")
public class StaffController extends BaseController<Staff> {
    /**
     * 服务对象
     */
    @Resource
    private StaffService staffService;

    /**
     * 给父类注入BaseService
     * @param staffService
     */
    @Autowired
    public void setBaseService(StaffService staffService){
        super.baseService = staffService;
    }

}