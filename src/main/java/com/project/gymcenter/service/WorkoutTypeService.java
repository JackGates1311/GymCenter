package com.project.gymcenter.service;

import com.project.gymcenter.model.WorkoutType;

import java.util.List;

public interface WorkoutTypeService {

    public List<WorkoutType> findAll();

    public WorkoutType findByWorkoutTypeName(String workoutTypeName);

    public String parseWorkoutTypes(List<String> newWorkoutIncludedTypes);

}
