package com.fontys.fitquest.business.implementation;

import com.fontys.fitquest.business.DeleteWorkoutPlanUseCase;
import com.fontys.fitquest.business.exception.InvalidWorkoutPlanException;
import com.fontys.fitquest.domain.requests.DeleteWorkoutPlanRequest;
import com.fontys.fitquest.domain.responses.DeleteWorkoutPlanResponse;
import com.fontys.fitquest.persistence.WorkoutPlanRepository;
import com.fontys.fitquest.persistence.entity.WorkoutPlanEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DeleteWorkoutPlanUseCaseImpl implements DeleteWorkoutPlanUseCase {
    private final WorkoutPlanRepository workoutPlanRepository;

    @Override
    public DeleteWorkoutPlanResponse deleteWorkoutPlan(DeleteWorkoutPlanRequest request) {
        Optional<WorkoutPlanEntity> optionalWorkoutPlan = workoutPlanRepository.findById(request.getId());

        if (optionalWorkoutPlan.isEmpty()) {
            throw new InvalidWorkoutPlanException("Workout plan not found");
        }

        workoutPlanRepository.deleteById(request.getId());

        return DeleteWorkoutPlanResponse.builder()
                .id(request.getId())
                .success(true)
                .build();
    }
}