package com.pms.controller;

import com.pms.entity.AdminRole;
import com.pms.service.AdminRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (AdminRole)表控制层
 *
 * @author makejava
 * @since 2020-05-30 12:52:40
 */
@RestController
@RequestMapping("adminRole")
public class AdminRoleController {
    /**
     * 服务对象
     */
    @Resource
    private AdminRoleService adminRoleService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @RequiresPermissions("adminRole:query")
    @RequestMapping("role")
    public List<Integer> getRoleIdByAdminId(Integer id) {
        return this.adminRoleService.queryRoleIdByAdminId(id);
    }

    @RequiresPermissions("adminRole:del")
    @RequestMapping("del")
    public Map<String, Object> del(@RequestParam ArrayList<Integer> roleIds,Integer adminId) {
        Map<String, Object> res = new HashMap<>();
        if (adminRoleService.delByAdminIdAndRoleId(roleIds,adminId)>0) {
            res.put("code", 200);
            res.put("msg", "删除用户角色成功!");
        } else {
            res.put("code", 400);
            res.put("msg", "删除用户角色失败!");
        }
        return res;
    }

    @RequiresPermissions("adminRole:add")
    @RequestMapping("add")
    public Map<String, Object> add(AdminRole adminRole) {
        Map<String, Object> res = new HashMap<>();
        if (adminRoleService.insert(adminRole) > 0) {
            res.put("code", 200);
            res.put("msg", "添加用户角色成功!");
        } else {
            res.put("code", 400);
            res.put("msg", "添加用户角色失败!");
        }
        return res;
    }

}