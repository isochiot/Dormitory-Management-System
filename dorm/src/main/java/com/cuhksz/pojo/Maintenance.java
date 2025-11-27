package com.cuhksz.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Maintenance {
    private Integer id;
    private String student_id;
    private Integer room_id;
    private String issue_type;
    private String description;
    private String status;
    private String maintenance_notes;
    private LocalDate estimated_completion;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
