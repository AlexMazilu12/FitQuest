package com.fontys.fitquest.business.implementation;

import com.fontys.fitquest.business.DeleteExerciseUseCase;
import com.fontys.fitquest.business.exception.ExerciseNotFoundException;
import com.fontys.fitquest.persistence.ExerciseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteExerciseUseCaseImpl implements DeleteExerciseUseCase {
    private final ExerciseRepository exerciseRepository;

    @Override
    public void deleteExercise(int id) {
        if (!exerciseRepository.existsById(id)) {
            throw new ExerciseNotFoundException("Exercise not found with id: " + id);
        }
        exerciseRepository.deleteById(id);
    }
}