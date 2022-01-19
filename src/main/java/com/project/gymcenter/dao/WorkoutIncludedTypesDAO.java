package com.project.gymcenter.dao;

import com.project.gymcenter.model.WorkoutIncludedTypes;

import java.util.List;

public interface WorkoutIncludedTypesDAO {

    public List<WorkoutIncludedTypes> findById(Long workoutId);

}
