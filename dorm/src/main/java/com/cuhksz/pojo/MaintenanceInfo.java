package com.cuhksz.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceInfo {
    private String status;
    private String maintenance_notes;
    private LocalDate estimated_completion;
}
