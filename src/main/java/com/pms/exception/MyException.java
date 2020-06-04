package com.pms.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MyException implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        System.out.println("异常处理");
        ex.printStackTrace();
        ModelAndView mv = new ModelAndView();
        if (ex instanceof UnauthorizedException) {
            mv.setViewName("redirect:/error/unPerms.jsp");
        } else{
            mv.setViewName("redirect:/error/error.jsp");
        }
        return mv;
    }
}
