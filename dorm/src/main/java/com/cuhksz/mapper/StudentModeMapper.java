package com.cuhksz.mapper;

import com.cuhksz.pojo.Adjustment;
import com.cuhksz.pojo.Fees;
import com.cuhksz.pojo.Maintenance;
import com.cuhksz.pojo.Roommate;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentModeMapper {

    /**
     * 获取学生所在宿舍信息
     *
     */
    @Select("select room_id from dorm_assignments where student_id = #{student_id}")
    Integer getRoomId(String studentId);

    @Select("select name from dorm_buildings where id = (select rooms.building_id from rooms where id = #{roomId}) ")
    String getBuildingName(Integer roomId);

    @Select("select room_number from rooms where id = #{room_id}")
    String getRoomNumber(Integer roomId);

    @Select("select student_id from dorm_assignments where room_id = #{room_id}")
    List<String> getRoommatesId(Integer roomId);

    @Select("<script>" +
            "SELECT name, student_id, gender, email, phone FROM users " +
            "WHERE student_id IN " +
            "<foreach collection='student_id' item='id' open='(' close=')' separator=','>" +
            "#{id}" +
            "</foreach>" +
            "</script>")
    List<Roommate> getRoommates(@Param("student_id") List<String> roommatesId);


    /**
     * 学生申请相关
     *
     */
    @Select("select name from dorm_buildings")
    List<String> getBuildings();

    @Insert("insert into adjustment_requests(student_id, reason, preferred_building_name, status, created_at, updated_at) values (#{student_id}, #{reason}, #{preferred_building_name}, #{status}, #{created_at}, #{updated_at})")
    void adjustmentRequest(Adjustment adjustment);

    @Select("select * from adjustment_requests where student_id = #{student_id}")
    List<Adjustment> getMyRequests(String student_id);

    @Select("select * from fee_records where student_id = #{student_id}")
    Fees getFees(String student_id);

    @Insert("insert into maintenance_requests(student_id, room_id, issue_type, description, status, maintenance_notes, estimated_completion, created_at, updated_at) values (#{student_id}, #{room_id}, #{issue_type}, #{description}, #{status}, #{maintenance_notes}, #{estimated_completion}, #{created_at}, #{updated_at})")
    void maintenance(Maintenance maintenance);

    @Select("select student_id, room_id, issue_type, description, status, maintenance_notes, estimated_completion, created_at, updated_at from maintenance_requests where student_id = #{student_id}")
    List<Maintenance> getMaintenance(String studentId);

}
