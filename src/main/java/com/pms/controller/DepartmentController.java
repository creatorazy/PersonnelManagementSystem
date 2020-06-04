package com.pms.controller;

import com.pms.entity.Department;
import com.pms.service.AdminService;
import com.pms.service.DepartmentService;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * (Department)表控制层
 *
 * @author makejava
 * @since 2020-05-15 11:11:46
 */
@RestController
@RequestMapping("/department")
public class DepartmentController extends BaseController<Department> {
    /**
     * 服务对象
     */
    @Resource
    private DepartmentService departmentService;

    /**
     * 给父类注入BaseService
     *
     * @param departmentService
     */
    @Autowired
    public void setBaseService(DepartmentService departmentService) {
        super.baseService = departmentService;
    }

    @RequiresPermissions("department:query")
    @RequestMapping("/query")
    public Map<String, Object> queryAllByLimit(int page, int limit, Department department) {
        List<Department> list = departmentService.queryAllByLimit(page, limit, department);
        Map<String, Object> res = new HashMap<>();
        res.put("code", 200);
        res.put("message", "获取数据成功");
        res.put("total", departmentService.queryCount(department));
        res.put("rows", list);
        return res;
    }

    @RequiresPermissions("department:add")
    @RequestMapping("/add")
    public Map<String, Object> add(Department department) {
        Map<String, Object> res = new HashMap<>();
        if (departmentService.insert(department) > 0) {
            res.put("code", 200);
            res.put("msg", "添加部门成功!");
        } else {
            res.put("code", 400);
            res.put("msg", "添加部门失败!");
        }
        return res;
    }

    @RequiresPermissions("department:up")
    @RequestMapping("/up")
    public Map<String, Object> up(Department department) {
        Map<String, Object> res = new HashMap<>();
        if (departmentService.update(department) > 0) {
            res.put("code", 200);
            res.put("msg", "修改部门信息成功!");
        } else {
            res.put("code", 400);
            res.put("msg", "修改部门信息失败!");
        }
        return res;
    }

    @RequiresPermissions("department:del")
    @RequestMapping("/del")
    public Map<String, Object> del(@RequestParam ArrayList<Integer> ids) {
        Map<String, Object> res = new HashMap<>();
        if (departmentService.deleteByIds(ids)) {
            res.put("code", 200);
            res.put("msg", "删除部门信息成功!");
        } else {
            res.put("code", 400);
            res.put("msg", "删除部门信息失败!");
        }
        return res;
    }
}