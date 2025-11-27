package com.cuhksz.mapper;

import com.cuhksz.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface StudentMapper {

    @Select("select * from users where role = 'STUDENT'")
    List<User> getStudentInfo();

    @Insert("insert into users (student_id, password, name, gender, email, phone, role) values (#{student_id}, #{password}, #{name}, #{gender}, #{email}, #{phone}, #{role})")
    void register(User user);

    @Select("select * from users where student_id = #{student_id} and password = #{password}")
    User selectByStuIdAndPassword(String student_id, String password);

    @Select("select password from users where student_id = #{student_id}")
    String getPassword(String student_id);

    @Select("select * from users where student_id = #{student_id}")
    User getUserByStudentId(String studentId);

    @Update("update users set password = #{password}, email = #{email}, phone = #{phone}, updated_at = #{updated_at} where student_id = #{student_id}")
    void update(User user);




}
