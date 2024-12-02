package com.fontys.fitquest.business.implementation;

import com.fontys.fitquest.business.DeleteExerciseUseCase;
import com.fontys.fitquest.persistence.ExerciseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteExerciseUseCaseImpl implements DeleteExerciseUseCase {
    private final ExerciseRepository exerciseRepository;

    @Override
    public void deleteExercise(int id) {
        exerciseRepository.deleteById(id);
    }
}