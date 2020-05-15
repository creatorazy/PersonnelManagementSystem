package com.pms.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri=request.getRequestURI();
        String str = request.getContextPath()+"/login";
        if(!uri.equals(str)){
            if(request.getSession().getAttribute("admin")==null){
                response.sendRedirect(request.getContextPath()+"/login.jsp");
                return false;
            }else{
                return true;
            }
        }else{
            return true;
        }
    }
}
