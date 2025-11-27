package com.cuhksz.mapper;

import com.cuhksz.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface AdminModeMapper {

    @Select("select * from adjustment_requests")
    List<Adjustment> getAdjustmentRequests();

    @Update("update adjustment_requests set status = #{status}, admin_notes = #{admin_notes}, assigned_room_number = #{assigned_room_number} where id = #{id}")
    void handleAdjustment(int id, String status, String admin_notes, String assigned_room_number);

    @Select("SELECT \n" +
            "            r.building_id,\n" +
            "            db.name as building_name,\n" +
            "            r.room_number,\n" +
            "            r.current_occupants,\n" +
            "            u.student_id,\n" +
            "            u.name as student_name\n" +
            "        FROM rooms r\n" +
            "        LEFT JOIN dorm_buildings db ON r.building_id = db.id\n" +
            "        LEFT JOIN dorm_assignments da ON r.id = da.room_id\n" +
            "        LEFT JOIN users u ON da.student_id = u.student_id\n" +
            "        ORDER BY r.building_id, r.room_number")
    List<RoomQueryVO> select_all_rooms_with_students();

    @Select("SELECT \n" +
            "            r.building_id,\n" +
            "            db.name as building_name,\n" +
            "            r.room_number,\n" +
            "            r.current_occupants,\n" +
            "            u.student_id,\n" +
            "            u.name as student_name\n" +
            "        FROM rooms r\n" +
            "        LEFT JOIN dorm_buildings db ON r.building_id = db.id\n" +
            "        LEFT JOIN dorm_assignments da ON r.id = da.room_id\n" +
            "        LEFT JOIN users u ON da.student_id = u.student_id\n" +
            "        WHERE r.building_id = #{building_id}\n" +
            "        ORDER BY r.room_number")
    List<RoomQueryVO> select_rooms_by_building_with_students(Integer buildingId);

    @Select("SELECT id building_id, name building_name FROM dorm_buildings")
    List<BuildingInfo> getBuildings();

    @Select("SELECT " +
            "fr.student_id, " +
            "da.room_id, " +
            "r.building_id, " +
            "db.name as building_name, " +
            "r.room_number, " +
            "fr.academic_year, " +
            "fr.semester, " +
            "fr.total_amount, " +
            "fr.paid, " +
            "fr.due_date, " +
            "fr.paid_date " +
            "FROM fee_records fr " +
            "INNER JOIN dorm_assignments da ON fr.student_id = da.student_id " +
            "INNER JOIN rooms r ON da.room_id = r.id " +
            "INNER JOIN dorm_buildings db ON r.building_id = db.id " +
            "ORDER BY fr.academic_year DESC, fr.semester, r.building_id, r.room_number")
    List<FeeAdmin> select_all_fee_records();

    @Select("select * from maintenance_requests")
    List<Maintenance> getMaintenanceRequests();

    @Select("select * from maintenance_requests where status = #{status}")
    List<Maintenance> getMaintenanceRequestsByStatus(String status);

    @Update("update maintenance_requests set status = #{status}, maintenance_notes = #{maintenance_notes}, estimated_completion = #{estimated_completion} where id = #{id}")
    void handleMaintenanceRequest(int id, String status, String maintenance_notes, LocalDate estimated_completion);
}
