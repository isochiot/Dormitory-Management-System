package com.cuhksz.service.impl;

import com.cuhksz.mapper.StudentModeMapper;
import com.cuhksz.pojo.*;
import com.cuhksz.service.StudentModeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentModeServiceImpl implements StudentModeService {

    @Autowired
    private StudentModeMapper studentModeMapper;

    @Override
    public DormInfo getDormInfo(String student_id) {
        Integer room_id = studentModeMapper.getRoomId(student_id);
        String building_name = studentModeMapper.getBuildingName(room_id);
        String room_number = studentModeMapper.getRoomNumber(room_id);
        List<String> roommatesId = studentModeMapper.getRoommatesId(room_id);
        List<Roommate> roommates = studentModeMapper.getRoommates(roommatesId);
        return new DormInfo(building_name, room_number, roommates);
    }

    @Override
    public Result getBuildings() {
        List<String> buildings = studentModeMapper.getBuildings();
        return buildings == null ? Result.error("没有宿舍信息") : Result.success(buildings);
    }

    @Override
    public Result adjustmentRequest(Adjustment adjustment) {
        adjustment.setStatus("PENDING");
        adjustment.setCreated_at(LocalDateTime.now());
        adjustment.setUpdated_at(LocalDateTime.now());
        studentModeMapper.adjustmentRequest(adjustment);
        return Result.success();
    }

    @Override
    public Result getMyRequests(String student_id) {
        List<Adjustment> requests = studentModeMapper.getMyRequests(student_id);

        return requests == null ? Result.error("没有申请信息") : Result.success(requests);
    }

    @Override
    public Result getFees(String student_id) {
        Fees fees = studentModeMapper.getFees(student_id);
        return fees == null ? Result.error("没有费用信息") : Result.success(fees);
    }

    @Override
    public Result maintenance(Maintenance maintenance) {
        maintenance.setCreated_at(LocalDateTime.now());
        maintenance.setUpdated_at(LocalDateTime.now());
        maintenance.setStatus("SUBMITTED");
        Integer room_id = studentModeMapper.getRoomId(maintenance.getStudent_id());
        maintenance.setRoom_id(room_id);
        studentModeMapper.maintenance(maintenance);
        return Result.success();
    }

    @Override
    public Result getMaintenance(String student_id) {
        List<Maintenance> maintenances = studentModeMapper.getMaintenance(student_id);
        return maintenances == null ? Result.error("没有维修信息") : Result.success(maintenances);
    }
}
