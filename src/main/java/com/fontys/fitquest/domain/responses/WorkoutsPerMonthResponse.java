package com.fontys.fitquest.domain.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WorkoutsPerMonthResponse {
    private int month;
    private long count;
}