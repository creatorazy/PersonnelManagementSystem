package com.pms.controller;

import com.pms.service.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 页面跳转层
 *
 * @author makejava
 * @since 2020-05-15 11:13:04
 */
@Controller
public class PageJump {

    @Resource
    private  AdminService adminService;

    @RequestMapping("/login")
    public String jumpLogin(){
        return "forward:/login.jsp";
    }

    @RequestMapping("/exit")
    public String exit(HttpServletRequest request){
        request.getSession().removeAttribute("admin");
        return "redirect:/login";
    }

    @RequestMapping("/index")
    public String jumpIndex(){
        return "index";
    }

    @RequestMapping("/total")
    @ResponseBody
    public Map<String,Object> total(){
        return adminService.total();
    }


    @RequestMapping("/pages/welcome")
    public String jumpWelcome(){
        return "welcome";
    }

    @RequiresPermissions("department:index")
    @RequestMapping("/pages/department/dep")
    public String jumpDepartmentDep(){
        return "department/dep";
    }

    @RequiresPermissions("department:addPage")
    @RequestMapping("/pages/department/add")
    public String jumpDepartmentAdd(HttpServletRequest req){
        req.setAttribute("type","add");
        return "department/operation";
    }

    @RequiresPermissions("department:upPage")
    @RequestMapping("/pages/department/up")
    public String jumpDepartmentUp(HttpServletRequest req){
        req.setAttribute("type","up");
        return "department/operation";
    }


    @RequiresPermissions("admin:index")
    @RequestMapping("/pages/admin/index")
    public String jumpAdmin(){
        return "admin/index";
    }

    @RequiresPermissions("admin:adminRolePage")
    @RequestMapping("/pages/admin/role")
    public String jumpAdminRole(){
        return "admin/role";
    }

    @RequiresPermissions("admin:addPage")
    @RequestMapping("/pages/admin/add")
    public String jumpAdminAdd(HttpServletRequest req){
        req.setAttribute("type","add");
        return "admin/operation";
    }

    @RequiresPermissions("admin:upPage")
    @RequestMapping("/pages/admin/up")
    public String jumpAdminUp(HttpServletRequest req){
        req.setAttribute("type","up");
        return "admin/operation";
    }


    @RequiresPermissions("role:index")
    @RequestMapping("/pages/role/index")
    public String jumpRole(){
        return "role/index";
    }

    @RequiresPermissions("role:addPage")
    @RequestMapping("/pages/role/add")
    public String jumpRoleAdd(HttpServletRequest req){
        req.setAttribute("type","add");
        return "role/operation";
    }

    @RequiresPermissions("role:upPage")
    @RequestMapping("/pages/role/up")
    public String jumpRoleUp(HttpServletRequest req){
        req.setAttribute("type","up");
        return "role/operation";
    }
}
