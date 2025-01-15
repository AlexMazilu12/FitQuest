package com.fontys.fitquest.business.implementation;

import com.fontys.fitquest.business.GetAllWorkoutsUseCase;
import com.fontys.fitquest.domain.WorkoutPlan;
import com.fontys.fitquest.domain.requests.GetAllWorkoutsRequest;
import com.fontys.fitquest.domain.responses.GetAllWorkoutsResponse;
import com.fontys.fitquest.persistence.WorkoutPlanRepository;
import com.fontys.fitquest.persistence.entity.WorkoutPlanEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetAllWorkoutsUseCaseImpl implements GetAllWorkoutsUseCase {
    private final WorkoutPlanRepository workoutPlanRepository;

    @Override
    public GetAllWorkoutsResponse getAllWorkouts(GetAllWorkoutsRequest request) {
        List<WorkoutPlanEntity> workoutPlans = workoutPlanRepository.findByUserId(request.getUserId());
        List<WorkoutPlan> workoutPlanList = workoutPlans.stream()
                .map(WorkoutConverter::convert)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));

        return GetAllWorkoutsResponse.builder()
                .workoutPlans(workoutPlanList)
                .build();
    }
}