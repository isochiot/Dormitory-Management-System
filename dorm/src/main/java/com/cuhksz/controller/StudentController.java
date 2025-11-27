package com.cuhksz.controller;

import com.cuhksz.pojo.LoginInfo;
import com.cuhksz.pojo.Result;
import com.cuhksz.pojo.User;
import com.cuhksz.service.StudentService;
import com.cuhksz.util.SessionUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 获取所有学生信息，仅初期测试使用
     */
    @GetMapping("/student")
    public List<User> getStudentInfo() {
        return studentService.getStudentInfo();
    }

    @PostMapping("/student/register")
    public Result register(@RequestBody User user) {
        return studentService.register(user);
    }

    @PostMapping("/student/login")
    public Result login(@RequestBody User user, HttpSession session) {
        LoginInfo loginInfo = studentService.login(user);
        if (loginInfo != null) {
            SessionUtil.setLoginUser(
                    session,
                    loginInfo.getId().toString(),
                    loginInfo.getStudent_id(),
                    loginInfo.getName(),
                    "STUDENT"
            );
            return Result.success(loginInfo);
        }
        return Result.error("用户名或密码错误");
    }

    /**
     * 退出登录，管理员和学生都使用这个接口
     */
    @PostMapping("/logout")
    public Result logout(HttpSession session) {
        SessionUtil.logout(session);
        return Result.success("退出登录成功");
    }

    @GetMapping("/student/current")
    public Result getCurrentUser(HttpSession session) {
        String student_id = SessionUtil.getStudentId(session);
        if (student_id == null) {
            return Result.error("用户未登录");
        }
        return studentService.getUserByStudentId(student_id);
    }

    /**
     * 学生修改个人信息
     */
    @PutMapping("/student/profile")
    public Result update(@RequestBody User user, HttpSession session) {
        String student_id = SessionUtil.getStudentId(session);
        if (student_id == null) {
            return Result.error("用户未登录");
        }
        user.setStudent_id(student_id);
        studentService.update(user);
        return Result.success();
    }
}
