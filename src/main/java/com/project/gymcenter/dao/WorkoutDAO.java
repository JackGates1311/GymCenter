package com.project.gymcenter.dao;

import com.project.gymcenter.model.Workout;

import java.util.List;

public interface WorkoutDAO {

    public Workout findOne(Long workoutId);

    public List<Workout> findAll();


}
