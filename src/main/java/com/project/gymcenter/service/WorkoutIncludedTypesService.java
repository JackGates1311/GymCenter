package com.project.gymcenter.service;

import com.project.gymcenter.model.WorkoutIncludedTypes;

import java.util.List;

public interface WorkoutIncludedTypesService {

    public List<WorkoutIncludedTypes> findById(Long workoutId);
}
