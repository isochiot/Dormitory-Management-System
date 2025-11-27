package com.cuhksz.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeeAdmin {
    private String student_id;
    private Long room_id;
    private Integer building_id;
    private String building_name;
    private String room_number;
    private String academic_year;
    private String semester;
    private BigDecimal total_amount;
    private Boolean paid;
    private LocalDate due_date;
    private LocalDate paid_date;
}
