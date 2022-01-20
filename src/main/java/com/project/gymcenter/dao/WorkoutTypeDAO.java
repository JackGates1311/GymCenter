package com.project.gymcenter.dao;

import com.project.gymcenter.model.WorkoutType;

import java.util.List;

public interface WorkoutTypeDAO {

    public List<WorkoutType> findAll();

    public WorkoutType findByWorkoutTypeName(String workoutTypeName);

    public void add(Long workoutId, String workoutTypeName);

}
