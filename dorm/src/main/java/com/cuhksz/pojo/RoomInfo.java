package com.cuhksz.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomInfo {  // 主要来自表rooms

    private Integer building_id;

    private String building_name;   // 来自表dorm_building

    private String room_number;
    private Integer current_occupants;

    private List<StudentBasic> students;  // 来自表dorm_assignment
}
