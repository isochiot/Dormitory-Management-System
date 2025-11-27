package com.cuhksz.service.impl;

import com.cuhksz.mapper.StudentMapper;
import com.cuhksz.pojo.LoginInfo;
import com.cuhksz.pojo.Result;
import com.cuhksz.pojo.User;
import com.cuhksz.service.StudentService;
import com.cuhksz.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private PasswordUtil passwordUtil;

    @Override
    public List<User> getStudentInfo() {
        return studentMapper.getStudentInfo();
    }

    @Override
    public Result register(User user) {
        String student_id = user.getStudent_id();
        if (studentMapper.getUserByStudentId(student_id) != null) {
            return Result.error("用户已存在");
        }

        user.setRole("STUDENT");
        user.setCreated_at(LocalDateTime.now());
        user.setUpdated_at(LocalDateTime.now());

        String encodedPassword = passwordUtil.encodePassword(user.getPassword());
        user.setPassword(encodedPassword);

        studentMapper.register(user);
        return Result.success();
    }

    @Override
    public LoginInfo login(User user) {  // user 仅含用户名和密码
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
        return user == null ? Result.error("用户不存在") : Result.success(user);
    }

    @Override
    public void update(User user) {
        user.setUpdated_at(LocalDateTime.now());
        String encodedPassword = passwordUtil.encodePassword(user.getPassword());
        user.setPassword(encodedPassword);

        studentMapper.update(user);
    }
}
