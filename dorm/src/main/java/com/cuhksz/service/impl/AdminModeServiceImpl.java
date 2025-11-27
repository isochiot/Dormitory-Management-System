package com.cuhksz.service.impl;

import com.cuhksz.mapper.AdminModeMapper;
import com.cuhksz.pojo.*;
import com.cuhksz.service.AdminModeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminModeServiceImpl implements AdminModeService {

    @Autowired
    private AdminModeMapper adminModeMapper;


    @Override
    public List<Adjustment> getAdjustmentRequests() {
        return adminModeMapper.getAdjustmentRequests();
    }

    @Override
    public void handleAdjustment(int id, HandleInfo handleInfo) {
        String status = handleInfo.getStatus();
        String admin_notes = handleInfo.getAdmin_notes();
        String assigned_room_number = handleInfo.getAssigned_room_number();
        adminModeMapper.handleAdjustment(id, status, admin_notes, assigned_room_number);
    }



    /**
     * 查询所有房间情况
     */
    @Override
    public List<RoomInfo> get_all_rooms_info() {
        // 1. 从数据库查询原始数据
        List<RoomQueryVO> query_results = adminModeMapper.select_all_rooms_with_students();

        // 2. 分组处理
        return group_room_info(query_results);
    }

    /**
     * 按楼栋查询房间情况
     */
    @Override
    public List<RoomInfo> get_rooms_info_by_building_id(Integer building_id) {
        // 1. 从数据库查询原始数据
        List<RoomQueryVO> query_results = adminModeMapper.select_rooms_by_building_with_students(building_id);

        // 2. 分组处理
        return group_room_info(query_results);
    }

    @Override
    public List<BuildingInfo> getBuildings() {
        List<BuildingInfo> buildings = adminModeMapper.getBuildings();
        return buildings == null ? List.of() : buildings;
    }

    @Override
    public List<FeeAdmin> getFeeStatus() {
        return adminModeMapper.select_all_fee_records();
    }

    @Override
    public List<Maintenance> getMaintenanceRequests(String status) {
        if (status.equals("ALL")) {
            return adminModeMapper.getMaintenanceRequests();
        }
        else {
            return adminModeMapper.getMaintenanceRequestsByStatus(status);
        }
    }

    @Override
    public void handleMaintenance(int id, MaintenanceInfo maintenanceInfo) {
        String status = maintenanceInfo.getStatus();
        String maintenance_notes = maintenanceInfo.getMaintenance_notes();
        LocalDate estimated_completion = maintenanceInfo.getEstimated_completion();
        adminModeMapper.handleMaintenanceRequest(id, status, maintenance_notes, estimated_completion);
    }

    /**
     * 通用的分组处理方法
     */
    private List<RoomInfo> group_room_info(List<RoomQueryVO> query_results) {
        // 使用Map来按房间分组，key为 building_id + room_number 的组合
        Map<String, RoomInfo> room_map = new LinkedHashMap<>();

        for (RoomQueryVO vo : query_results) {
            String room_key = vo.getBuilding_id() + "_" + vo.getRoom_number();

            // 如果这个房间还没有被处理过，创建新的RoomInfo
            if (!room_map.containsKey(room_key)) {
                RoomInfo room_info = new RoomInfo();
                room_info.setBuilding_id(vo.getBuilding_id());
                room_info.setBuilding_name(vo.getBuilding_name());
                room_info.setRoom_number(vo.getRoom_number());
                room_info.setCurrent_occupants(vo.getCurrent_occupants());
                room_info.setStudents(new ArrayList<>());

                room_map.put(room_key, room_info);
            }

            // 如果当前记录有学生信息，添加到对应的RoomInfo中
            RoomInfo current_room = room_map.get(room_key);
            if (vo.getStudent_id() != null && vo.getStudent_name() != null) {
                StudentBasic student = new StudentBasic();
                student.setStudent_id(vo.getStudent_id());
                student.setName(vo.getStudent_name());
                current_room.getStudents().add(student);
            }
        }

        // 返回分组后的结果
        return new ArrayList<>(room_map.values());
    }
}
