package com.pms.controller;

import com.pms.entity.Admin;
import com.pms.service.AdminService;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * (Admin)表控制层
 *
 * @author makejava
 * @since 2020-05-15 11:13:04
 */
@RestController
@RequestMapping("/admin")
@SessionAttributes({"admin"})
public class AdminController {
    /**
     * 服务对象
     */
    @Resource
    private AdminService adminService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public Admin selectOne(Integer id) {
        return this.adminService.queryById(id);
    }

    /**
     * 查询数据数量
     * @return 数据数量
     */
    @GetMapping("/count")
    public int selectCount(){
        return adminService.queryCount();
    }

    /**
     * 通过账户密码登陆
     * @return
     */
    @PostMapping("/login")
    public Map<String,Object> login(Admin admin, ModelMap modelMap){
        Map<String,Object> resp = new HashMap<>();
        Admin a = adminService.queryByLoginNameAndPwd(admin);
        if(a!=null){
            modelMap.addAttribute("admin",a);
            resp.put("flag",0);
            resp.put("msg","登陆成功!");
        }else{
            resp.put("flag",1);
            resp.put("msg","登陆失败,请假查账户密码是否正确!");
        }
        return resp;
    }



}