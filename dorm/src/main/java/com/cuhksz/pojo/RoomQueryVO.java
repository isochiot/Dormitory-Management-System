package com.cuhksz.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomQueryVO {
    private Integer building_id;
    private String building_name;
    private String room_number;
    private Integer current_occupants;
    private String student_id;
    private String student_name;
}