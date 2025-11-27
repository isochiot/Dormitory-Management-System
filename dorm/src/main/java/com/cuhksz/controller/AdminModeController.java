package com.cuhksz.controller;

import com.cuhksz.pojo.*;
import com.cuhksz.service.AdminModeService;
import com.cuhksz.util.SessionUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminModeController {

    @Autowired
    private AdminModeService adminModeService;

    @GetMapping("/admin/adjustment_requests")
    public Result getAdjustmentRequests(HttpSession session) {
        String admin_id = SessionUtil.getStudentId(session);
        if (admin_id == null) {
            return Result.error("用户未登录");
        }
        if (!SessionUtil.isAdmin(session)) {
            return Result.error("用户权限不足");
        }

        List<Adjustment> requests = adminModeService.getAdjustmentRequests();
        return requests == null ? Result.error("没有申请信息") : Result.success(requests);
    }

    @PostMapping("/admin/handle_adjustment/{request_id}")
    public Result handleAdjustment(HttpSession session, @PathVariable int request_id, @RequestBody HandleInfo handleInfo) {
        String admin_id = SessionUtil.getStudentId(session);
        if (admin_id == null) {
            return Result.error("用户未登录");
        }
        if (!SessionUtil.isAdmin(session)) {
            return Result.error("用户权限不足");
        }
        adminModeService.handleAdjustment(request_id, handleInfo);
        return Result.success();
    }

    @GetMapping("/admin/dorm_status")
    public Result getDormStatus(HttpSession session, @RequestParam(required = false, defaultValue = "0") int building_id) {
        String admin_id = SessionUtil.getStudentId(session);
        if (admin_id == null) {
            return Result.error("用户未登录");
        }
        if (!SessionUtil.isAdmin(session)) {
            return Result.error("用户权限不足");
        }

        List<RoomInfo> rooms;
        if (building_id == 0) {
            rooms = adminModeService.get_all_rooms_info();
        }
        else {
            rooms = adminModeService.get_rooms_info_by_building_id(building_id);
        }
        return Result.success(rooms);
    }

    @GetMapping("/admin/buildings")
    public Result getBuildings(HttpSession session) {
        String admin_id = SessionUtil.getStudentId(session);
        if (admin_id == null) {
            return Result.error("用户未登录");
        }
        if (!SessionUtil.isAdmin(session)) {
            return Result.error("用户权限不足");
        }
        return Result.success(adminModeService.getBuildings());
    }

    @GetMapping("/admin/fee_status")
    public Result getFeeStatus(HttpSession session) {
        String admin_id = SessionUtil.getStudentId(session);
        if (admin_id == null) {
            return Result.error("用户未登录");
        }
        if (!SessionUtil.isAdmin(session)) {
            return Result.error("用户权限不足");
        }
        return Result.success(adminModeService.getFeeStatus());
    }

    @GetMapping("/admin/maintenance_requests")
    public Result getMaintenanceRequests(HttpSession session, @RequestParam(required = false, defaultValue ="ALL") String status) {
        String admin_id = SessionUtil.getStudentId(session);
        if (admin_id == null) {
            return Result.error("用户未登录");
        }
        if (!SessionUtil.isAdmin(session)) {
            return Result.error("用户权限不足");
        }
        return Result.success(adminModeService.getMaintenanceRequests(status));
    }

    @PutMapping("/admin/maintenance_request/{id}")
    public Result handleMaintenanceRequest(HttpSession session, @PathVariable int id, @RequestBody MaintenanceInfo maintenanceInfo) {
        String admin_id = SessionUtil.getStudentId(session);
        if (admin_id == null) {
            return Result.error("用户未登录");
        }
        if (!SessionUtil.isAdmin(session)) {
            return Result.error("用户权限不足");
        }
        adminModeService.handleMaintenance(id, maintenanceInfo);
        return Result.success();
    }
}
