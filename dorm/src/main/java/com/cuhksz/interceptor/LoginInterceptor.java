package com.cuhksz.interceptor;

import com.cuhksz.util.SessionUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        HttpSession session = request.getSession(false); // 不创建新 session

        // 放行登录、注册等公开接口
        String requestURI = request.getRequestURI();
        if (requestURI.startsWith("/student/login") ||
                requestURI.startsWith("/student/register") ||
                requestURI.startsWith("/admin/login") ||

                requestURI.startsWith("/admin/register") ||  // 管理员注册接口，实际应用时可以移除权限

                requestURI.startsWith("/error")) {
            return true;
        }

        // 检查学生端接口
        if (requestURI.startsWith("/student/")) {
            if (session == null || !SessionUtil.isStudent(session)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write("{\"code\":0,\"message\":\"请先登录学生账号\"}");
                return false;
            }
        }

        // 检查管理员端接口
        if (requestURI.startsWith("/admin/")) {
            if (session == null || !SessionUtil.isAdmin(session)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write("{\"code\":0,\"message\":\"请先登录管理员账号\"}");
                return false;
            }
        }

        return true;
    }
}