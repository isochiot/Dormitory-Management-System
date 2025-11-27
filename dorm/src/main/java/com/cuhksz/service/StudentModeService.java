package com.cuhksz.service;

import com.cuhksz.pojo.Adjustment;
import com.cuhksz.pojo.DormInfo;
import com.cuhksz.pojo.Maintenance;
import com.cuhksz.pojo.Result;

public interface StudentModeService {

    DormInfo getDormInfo(String student_id);

    Result getBuildings();

    Result adjustmentRequest(Adjustment adjustment);

    Result getMyRequests(String student_id);

    Result getFees(String student_id);

    Result maintenance(Maintenance maintenance);

    Result getMaintenance(String student_id);

}
