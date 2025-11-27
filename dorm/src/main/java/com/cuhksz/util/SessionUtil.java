package com.cuhksz.util;

import jakarta.servlet.http.HttpSession;

public class SessionUtil {

    // Session 中存储的键名
    public static final String SESSION_USER_ID = "user_id";
    public static final String SESSION_STUDENT_ID = "student_id";
    public static final String SESSION_USER_NAME = "user_name";
    public static final String SESSION_ROLE = "role";

    /**
     * 设置用户登录信息到 Session
     */
    public static void setLoginUser(HttpSession session, String userId, String studentId, String userName, String role) {
        session.setAttribute(SESSION_USER_ID, userId);
        session.setAttribute(SESSION_STUDENT_ID, studentId);
        session.setAttribute(SESSION_USER_NAME, userName);
        session.setAttribute(SESSION_ROLE, role);
        // 设置 Session 过期时间（30 分钟）
        session.setMaxInactiveInterval(30 * 60);
    }

    /**
     * 从 Session 获取用户 ID
     */
    public static String getUserId(HttpSession session) {
        return (String) session.getAttribute(SESSION_USER_ID);
    }

    /**
     * 从 Session 获取学号
     */
    public static String getStudentId(HttpSession session) {
        return (String) session.getAttribute(SESSION_STUDENT_ID);
    }

    /**
     * 从 Session 获取用户名
     */
    public static String getUserName(HttpSession session) {
        return (String) session.getAttribute(SESSION_USER_NAME);
    }

    /**
     * 从 Session 获取用户角色
     */
    public static String getRole(HttpSession session) {
        return (String) session.getAttribute(SESSION_ROLE);
    }

    /**
     * 检查用户是否已登录
     */
    public static boolean isLoggedIn(HttpSession session) {
        return getUserId(session) != null;
    }

    /**
     * 检查用户是否为学生
     */
    public static boolean isStudent(HttpSession session) {
        return "STUDENT".equals(getRole(session));
    }

    /**
     * 检查用户是否为管理员
     */
    public static boolean isAdmin(HttpSession session) {
        return "ADMIN".equals(getRole(session));
    }

    /**
     * 注销登录
     */
    public static void logout(HttpSession session) {
        session.invalidate();
    }
}