package com.cuhksz.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginInfo {
    private Integer id;
    private String student_id;
    private String name;
    private String gender;
    private String email;
    private String phone;
}
