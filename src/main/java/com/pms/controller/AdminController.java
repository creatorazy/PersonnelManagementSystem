package com.pms.controller;

import com.pms.entity.Admin;
import com.pms.service.AdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.*;

/**
 * (Admin)表控制层
 *
 * @author makejava
 * @since 2020-05-15 11:13:04
 */
@RestController
@RequestMapping("/admin")
public class AdminController extends BaseController<Admin> {

    /**
     * 服务对象
     */
    @Resource
    private AdminService adminService;

//    @Autowired
//    PermissionDao permissionDao;
//
//    @Autowired
//    ApplicationContext applicationContext;
//
//    public void onInit() {
//        Set<String> data = new HashSet<>();
//        Set<String> exits = permissionDao.queryPermissions();
//        Map<String,Object> map = applicationContext.getBeansWithAnnotation(Controller.class);
//        System.out.println(applicationContext.getBeansOfType(PageJump.class));
//        System.out.println("bean数量"+map.size());
//        scanPermissions(map,data,exits);
//        System.out.println("data数量"+data.size());
//        System.out.println(data);
//        if(data.size()>0){
//            List<Permission> list = new ArrayList<>();
//            for(String s:data){
//                System.out.println(s+".....");
//                Permission p = new Permission();
//                p.setName(s);
//                list.add(p);
//            }
//            permissionDao.insert(list);
//        }
//    }
//
//    public void scanPermissions(Map<String,Object> beans,Set<String> data,Set<String> exits){
//        for(Map.Entry<String,Object> entry :beans.entrySet()){
//            System.out.println(entry.getValue().getClass().getName());
//            Method[] methods = entry.getValue().getClass().getDeclaredMethods();
//            System.out.println(methods.length);
//            for(Method method:methods){
//                System.out.println("正在扫描"+method.getName()+"方法");
//                if(method.isAnnotationPresent(RequiresPermissions.class)){
//                    System.out.println("被注解过");
//                    RequiresPermissions res = method.getAnnotation(RequiresPermissions.class);
//                    System.out.println(Arrays.toString(res.value()));
//                    for(String s:res.value()){
//                        System.out.println(s);
//                        if(!exits.contains(s)){
//                            data.add(s);
//                        }
//                    }
//                }
//            }
//        }
//    }


    /**
     * 给父类注入BaseService
     *
     * @param adminService
     */
    @Autowired
    public void setBaseService(AdminService adminService) {
        super.baseService = adminService;
    }

    /**
     * 通过账户密码登陆
     *
     * @return
     */
    @PostMapping("/login")
    public Map<String, Object> login(Admin admin) {
        Map<String, Object> resp = new HashMap<>();
        UsernamePasswordToken upt = new UsernamePasswordToken(admin.getLoginName(), admin.getPassword());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(upt);
            resp.put("flag", 0);
            resp.put("msg", "登陆成功!");
        } catch (AuthenticationException e) {
            resp.put("flag", 1);
            resp.put("msg", "登陆失败!");
            e.printStackTrace();
        }
        return resp;
    }

    @RequiresPermissions("admin:query")
    @RequestMapping("/query")
    public Map<String, Object> queryAllByLimit(int page, int limit, Admin admin) {
        List<Admin> list = adminService.queryAllByLimit(page, limit, admin);
        Map<String, Object> res = new HashMap<>();
        res.put("code", 200);
        res.put("message", "获取数据成功");
        res.put("total", adminService.queryCount(admin));
        res.put("rows", list);
        return res;
    }

    @RequiresPermissions("admin:add")
    @RequestMapping("/add")
    public Map<String, Object> add(Admin admin) {
        admin.setCDate(new Date(System.currentTimeMillis()));
        if (admin.getState() == null || admin.getState().equals("")) {
            admin.setState("1");
        }
        Map<String, Object> res = new HashMap<>();
        if (adminService.insert(admin) > 0) {
            res.put("code", 200);
            res.put("msg", "添加用户成功!");
        } else {
            res.put("code", 400);
            res.put("msg", "添加用户失败!");
        }
        return res;
    }

    @RequiresPermissions("admin:up")
    @RequestMapping("/up")
    public Map<String, Object> up(Admin admin) {
        Map<String, Object> res = new HashMap<>();
        if (adminService.update(admin) > 0) {
            res.put("code", 200);
            res.put("msg", "修改用户信息成功!");
        } else {
            res.put("code", 400);
            res.put("msg", "修改用户信息失败!");
        }
        return res;
    }

    @RequiresPermissions("admin:del")
    @RequestMapping("/del")
    public Map<String, Object> del(@RequestParam ArrayList<Integer> ids) {
        Map<String, Object> res = new HashMap<>();
        if (adminService.deleteByIds(ids)) {
            res.put("code", 200);
            res.put("msg", "删除用户信息成功!");
        } else {
            res.put("code", 400);
            res.put("msg", "删除用户信息失败!");
        }
        return res;
    }

    @RequiresPermissions("admin:add")
    @RequestMapping("/isExist")
    public boolean isExist(String name){
        return adminService.isExist(name);
    }
}