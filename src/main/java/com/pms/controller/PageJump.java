package com.pms.controller;

import com.pms.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 页面跳转层
 *
 * @author makejava
 * @since 2020-05-15 11:13:04
 */
@Controller
public class PageJump {

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

    @RequestMapping("/pages/welcome")
    public String jumpWelcome(){
        return "welcome";
    }

}
