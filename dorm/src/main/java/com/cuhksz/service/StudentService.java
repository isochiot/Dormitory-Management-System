package com.cuhksz.service;

import com.cuhksz.pojo.LoginInfo;
import com.cuhksz.pojo.Result;
import com.cuhksz.pojo.User;

import java.util.List;

public interface StudentService {

    List<User> getStudentInfo();

    Result register(User user);

    LoginInfo login(User user);

    Result getUserByStudentId(String student_id);

    void update(User user);

}
