package com.fontys.fitquest.business.implementation;

import com.fontys.fitquest.business.GetWorkoutUseCase;
import com.fontys.fitquest.business.exception.InvalidWorkoutPlanException;
import com.fontys.fitquest.domain.WorkoutPlan;
import com.fontys.fitquest.domain.requests.GetWorkoutRequest;
import com.fontys.fitquest.domain.responses.GetWorkoutResponse;
import com.fontys.fitquest.persistence.WorkoutPlanRepository;
import com.fontys.fitquest.persistence.entity.WorkoutPlanEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetWorkoutUseCaseImpl implements GetWorkoutUseCase {
    private final WorkoutPlanRepository workoutPlanRepository;

    @Override
    public GetWorkoutResponse getWorkout(GetWorkoutRequest request) {
        Optional<WorkoutPlanEntity> optionalWorkoutPlan = workoutPlanRepository.findById(request.getId());

        if (optionalWorkoutPlan.isEmpty()) {
            throw new InvalidWorkoutPlanException("Workout plan not found");
        }

        WorkoutPlan workoutPlan = WorkoutConverter.convert(optionalWorkoutPlan.get());

        return GetWorkoutResponse.builder()
                .workoutPlan(workoutPlan)
                .build();
    }
}