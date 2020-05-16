package com.pms.controller;

import com.pms.entity.Staff;
import com.pms.service.StaffService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Staff)表控制层
 *
 * @author makejava
 * @since 2020-05-15 11:06:41
 */
@RestController
@RequestMapping("staff")
public class StaffController {
    /**
     * 服务对象
     */
    @Resource
    private StaffService staffService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Staff selectOne(Integer id) {
        return this.staffService.queryById(id);
    }

    /**
     * 查询数据数量
     * @return 数据数量
     */
    @GetMapping("/count")
    public int selectCount(){
        return staffService.queryCount();
    }

}