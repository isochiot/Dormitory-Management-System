package com.cuhksz.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DormInfo {
    private String building_name;
    private String room_number;
    private List<Roommate> roommates;
}
