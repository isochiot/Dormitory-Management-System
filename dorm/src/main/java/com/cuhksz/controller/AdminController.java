package com.cuhksz.controller;

import com.cuhksz.pojo.LoginInfo;
import com.cuhksz.pojo.Result;
import com.cuhksz.pojo.User;
import com.cuhksz.service.AdminService;
import com.cuhksz.util.SessionUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/admin/register")
    public Result register(@RequestBody User user) {
        return adminService.register(user);
    }

    @PostMapping("/admin/login")
    public Result login(@RequestBody User user, HttpSession session) {
        LoginInfo loginInfo = adminService.login(user);
        if (loginInfo != null) {
            SessionUtil.setLoginUser(
                    session,
                    loginInfo.getId().toString(),
                    loginInfo.getStudent_id(),
                    loginInfo.getName(),
                    "ADMIN"
            );
            return Result.success(loginInfo);
        }
        return Result.error("用户名或密码错误");
    }

    @GetMapping("/admin/current")
    public Result getCurrentUser(HttpSession session) {
        String student_id = SessionUtil.getStudentId(session);
        if (student_id == null) {
            return Result.error("管理员未登录");
        }
        return adminService.getUserByStudentId(student_id);
    }

    @PutMapping("/admin/profile")
    public Result update(@RequestBody User user, HttpSession session) {
        String student_id = SessionUtil.getStudentId(session);
        if (student_id == null) {
            return Result.error("管理员未登录");
        }
        user.setStudent_id(student_id);
        adminService.update(user);
        return Result.success();
    }

}
