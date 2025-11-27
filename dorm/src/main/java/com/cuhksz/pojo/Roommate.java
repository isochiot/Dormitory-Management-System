package com.cuhksz.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Roommate {
    private String name;
    private String student_id;
    private String gender;
    private String email;
    private String phone;
}
