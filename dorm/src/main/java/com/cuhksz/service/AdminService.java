package com.cuhksz.service;

import com.cuhksz.pojo.LoginInfo;
import com.cuhksz.pojo.Result;
import com.cuhksz.pojo.User;

public interface AdminService {

    Result register(User user);

    LoginInfo login(User user);

    Result getUserByStudentId(String studentId);

    void update(User user);

}
