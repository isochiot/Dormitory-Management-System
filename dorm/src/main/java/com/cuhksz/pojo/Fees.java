package com.cuhksz.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fees {
    private String student_id;
    private String academic_year;
    private String semester;
    private BigDecimal accommodation_fee;
    private BigDecimal electricity_fee;
    private BigDecimal total_amount;
    private Boolean paid;
    private LocalDate due_date;
    private LocalDate paid_date;
}
