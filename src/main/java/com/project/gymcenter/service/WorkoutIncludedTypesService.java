package com.project.gymcenter.service;

import com.project.gymcenter.model.SelectedWorkoutTypes;
import com.project.gymcenter.model.WorkoutIncludedTypes;
import com.project.gymcenter.model.WorkoutType;

import java.util.List;

public interface WorkoutIncludedTypesService {

    public List<WorkoutIncludedTypes> findById(Long workoutId);

    public List<SelectedWorkoutTypes> getSelectedWorkoutIncludedTypes(List<WorkoutType> workoutTypes,
                                                                       List<WorkoutIncludedTypes> workoutIncludedTypes);

    public void add(Long workoutId, String workoutTypeName);

    public void remove(Long workoutId);

}
