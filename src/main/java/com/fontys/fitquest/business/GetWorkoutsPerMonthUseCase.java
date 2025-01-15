package com.fontys.fitquest.business;

import com.fontys.fitquest.domain.responses.WorkoutsPerMonthResponse;
import java.util.List;

public interface GetWorkoutsPerMonthUseCase {
    List<WorkoutsPerMonthResponse> getWorkoutsPerMonth();
}