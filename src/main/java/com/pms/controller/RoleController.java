package com.pms.controller;

import com.pms.entity.Admin;
import com.pms.entity.Role;
import com.pms.service.DepartmentService;
import com.pms.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * (Role)表控制层
 *
 * @author makejava
 * @since 2020-05-30 11:36:57
 */
@RestController
@RequestMapping("role")
public class RoleController extends BaseController<Role> {
    /**
     * 服务对象
     */
    @Resource
    private RoleService roleService;

    @Autowired
    public void setBaseService(RoleService roleService) {
        super.baseService = roleService;
    }

    /**
     * 通过主键查询单条数据
     * @return 单条数据
     */
    @RequiresPermissions("role:query")
    @RequestMapping("all")
    public List<Role> queryAll() {
        return roleService.queryAllRole();
    }

    @RequiresPermissions("role:query")
    @RequestMapping("/query")
    public Map<String, Object> queryAllByLimit(int page, int limit, Role role) {
        List<Role> list = roleService.queryAllByLimit(page, limit, role);
        Map<String, Object> res = new HashMap<>();
        res.put("code", 200);
        res.put("message", "获取数据成功");
        res.put("total", roleService.queryCount(role));
        res.put("rows", list);
        return res;
    }

    @RequiresPermissions("role:add")
    @RequestMapping("/add")
    public Map<String, Object> add(Role role,@RequestParam("perms") ArrayList<Integer> perms) {
        System.out.println(perms);
        Map<String, Object> res = new HashMap<>();
        if (roleService.insertRole(role,perms) > 0) {
            res.put("code", 200);
            res.put("msg", "添加角色成功!");
        } else {
            res.put("code", 400);
            res.put("msg", "添加角色失败!");
        }
        return res;
    }

    @RequiresPermissions("role:up")
    @RequestMapping("/up")
    public Map<String, Object> up(Role role,@RequestParam("perms") ArrayList<Integer> perms) {
        Map<String, Object> res = new HashMap<>();
        if (roleService.upRole(role,perms) > 0) {
            res.put("code", 200);
            res.put("msg", "修改角色信息成功!");
        } else {
            res.put("code", 400);
            res.put("msg", "修改角色信息失败!");
        }
        return res;
    }

    @RequiresPermissions("role:del")
    @RequestMapping("/del")
    public Map<String, Object> del(@RequestParam ArrayList<Integer> ids) {
        Map<String, Object> res = new HashMap<>();
        if (roleService.deleteByIds(ids)) {
            res.put("code", 200);
            res.put("msg", "删除角色信息成功!");
        } else {
            res.put("code", 400);
            res.put("msg", "删除角色信息失败!");
        }
        return res;
    }

    @RequiresPermissions("role:add")
    @RequestMapping("/isExist")
    public boolean isExist(String name){
        return roleService.isExist(name);
    }

}