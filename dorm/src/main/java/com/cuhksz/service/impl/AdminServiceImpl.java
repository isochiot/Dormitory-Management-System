package com.cuhksz.service.impl;

import com.cuhksz.mapper.StudentMapper;
import com.cuhksz.pojo.LoginInfo;
import com.cuhksz.pojo.Result;
import com.cuhksz.pojo.User;
import com.cuhksz.service.AdminService;
import com.cuhksz.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AdminServiceImpl implements AdminService {

    /**
     * 管理员注册登录信息和学生同表维护，使用同一个Mapper层
     */
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private PasswordUtil passwordUtil;

    @Override
    public Result register(User user) {
        // 管理员也采用student_id作为用户名
        String student_id = user.getStudent_id();
        if (studentMapper.getUserByStudentId(student_id) != null) {
            return Result.error("管理员已存在");
        }

        user.setRole("ADMIN");
        user.setCreated_at(LocalDateTime.now());
        user.setUpdated_at(LocalDateTime.now());

        String encodedPassword = passwordUtil.encodePassword(user.getPassword());
        user.setPassword(encodedPassword);

        studentMapper.register(user);
        return Result.success();
    }

    @Override
    public LoginInfo login(User user) {
        if (user.getStudent_id() == null || user.getPassword() == null) {
            return null;
        }

        String encodedPassword = studentMapper.getPassword(user.getStudent_id());
        if (encodedPassword == null) {
            return null;
        }
        if (!passwordUtil.matches(user.getPassword(), encodedPassword)) {
            return null;
        }

        User u = studentMapper.getUserByStudentId(user.getStudent_id());
        if (u != null) {
            LoginInfo loginInfo = new LoginInfo();
            loginInfo.setId(u.getId());
            loginInfo.setStudent_id(u.getStudent_id());
            loginInfo.setName(u.getName());
            loginInfo.setGender(u.getGender());
            loginInfo.setEmail(u.getEmail());
            loginInfo.setPhone(u.getPhone());
            return loginInfo;
        }
        return null;
    }

    @Override
    public Result getUserByStudentId(String student_id) {
        User user = studentMapper.getUserByStudentId(student_id);
        return user == null ? Result.error("管理员不存在") : Result.success(user);
    }

    @Override
    public void update(User user) {
        user.setUpdated_at(LocalDateTime.now());
        String encodedPassword = passwordUtil.encodePassword(user.getPassword());
        user.setPassword(encodedPassword);

        studentMapper.update(user);
    }


}
