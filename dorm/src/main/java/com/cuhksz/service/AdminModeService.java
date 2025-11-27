package com.cuhksz.service;

import com.cuhksz.pojo.*;

import java.util.List;

public interface AdminModeService {

    List<Adjustment> getAdjustmentRequests();

    void handleAdjustment(int id, HandleInfo handleInfo);

    List<RoomInfo> get_all_rooms_info();

    List<RoomInfo> get_rooms_info_by_building_id(Integer building_id);

    List<BuildingInfo> getBuildings();

    List<FeeAdmin> getFeeStatus();

    List<Maintenance> getMaintenanceRequests(String status);

    void handleMaintenance(int id, MaintenanceInfo maintenanceInfo);

}
