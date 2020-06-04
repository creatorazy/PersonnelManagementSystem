package com.pms.controller;

import com.pms.entity.Admin;
import com.pms.entity.Permission;
import com.pms.service.PermissionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("permission")
public class PermissionController {

    @Resource
    private PermissionService permissionService;


    @RequiresPermissions("permission:query")
    @RequestMapping("/all")
    public Map<String, Object> queryAll() {
        List<Permission> list = permissionService.queryAll(null);
        Map<String, Object> res = new HashMap<>();
        res.put("code", 200);
        res.put("message", "获取数据成功");
        res.put("rows", list);
        return res;
    }
}
