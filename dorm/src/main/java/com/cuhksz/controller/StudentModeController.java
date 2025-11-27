package com.cuhksz.controller;

import com.cuhksz.pojo.Adjustment;
import com.cuhksz.pojo.DormInfo;
import com.cuhksz.pojo.Maintenance;
import com.cuhksz.pojo.Result;
import com.cuhksz.service.StudentModeService;
import com.cuhksz.util.SessionUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentModeController {

    @Autowired
    private StudentModeService studentModeService;

    /**
     * 查看我的宿舍分配详情：包含宿舍楼名称、房间号、室友
     */
    @GetMapping("/student/dorm_info")
    public Result getDormInfo(HttpSession session) {
        String student_id = SessionUtil.getStudentId(session);
        if (student_id == null) {
            return Result.error("用户未登录");
        }

        DormInfo dormInfo = studentModeService.getDormInfo(student_id);
        return dormInfo == null ? Result.error("没有宿舍信息") : Result.success(dormInfo);
    }

    /**
     * 获取所有宿舍楼名称
     */
    @GetMapping("/student/buildings")
    public Result getBuildings(HttpSession session) {
        String student_id = SessionUtil.getStudentId(session);
        if (student_id == null) {
            return Result.error("用户未登录");
        }

        return studentModeService.getBuildings();
    }

    /**
     * 提交调宿申请
     */
    @PostMapping("/student/adjustment_request")
    public Result adjustmentRequest(@RequestBody Adjustment adjustment, HttpSession session) {
        String student_id = SessionUtil.getStudentId(session);
        if (student_id == null) {
            return Result.error("用户未登录");
        }
        if (adjustment.getReason() == null) {
            return Result.error("请填写原因");
        }
        if (adjustment.getPreferred_building_name() == null) {
            return Result.error("请填写期望的宿舍楼名称");
        }
        adjustment.setStudent_id(student_id);
        return studentModeService.adjustmentRequest(adjustment);
    }

    /**
     * 获取我的调宿申请
     */
    @GetMapping("/student/my_requests")
    public Result getMyRequests(HttpSession session) {
        String student_id = SessionUtil.getStudentId(session);
        if (student_id == null) {
            return Result.error("用户未登录");
        }
        return studentModeService.getMyRequests(student_id);
    }

    /**
     * 获取我的费用信息
     */
    @GetMapping("/student/fees")
    public Result getFees(HttpSession session) {
        String student_id = SessionUtil.getStudentId(session);
        if (student_id == null) {
            return Result.error("用户未登录");
        }
        return studentModeService.getFees(student_id);
    }

    /**
     * 提交维修申请
     */
    @PostMapping("/student/maintenance")
    public Result maintenance(@RequestBody Maintenance maintenance, HttpSession session) {
        String student_id = SessionUtil.getStudentId(session);
        if (student_id == null) {
            return Result.error("用户未登录");
        }
        maintenance.setStudent_id(student_id);
        return studentModeService.maintenance(maintenance);
    }

    /**
     * 获取我的维修申请
     */
    @GetMapping("/student/maintenance")
    public Result getMaintenance(HttpSession session) {
        String student_id = SessionUtil.getStudentId(session);
        if (student_id == null) {
            return Result.error("用户未登录");
        }
        return studentModeService.getMaintenance(student_id);
    }

}
