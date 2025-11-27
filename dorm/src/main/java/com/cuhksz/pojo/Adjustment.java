package com.cuhksz.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Adjustment {
    private Integer id;
    private String student_id;
    private String reason;
    private String preferred_building_name;
    private String status;
    private String admin_notes;
    private String assigned_room_number;
    private LocalDateTime  created_at;
    private LocalDateTime  updated_at;
}
