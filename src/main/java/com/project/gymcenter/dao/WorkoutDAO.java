package com.project.gymcenter.dao;

import com.project.gymcenter.model.Workout;

import java.util.List;

public interface WorkoutDAO {

    public Workout findOne(Long workoutId);

    public List<Workout> findAll();

    public int add(Workout workout);

    public int update(Workout workout);

    public int delete(Workout workout);

    public List<Workout> find(String workoutName, String workoutCoaches, Double workoutPriceMin,
                              Double workoutPriceMax, String workoutTypeName, String workoutOrganizationType,
                              String workoutLevel, String workoutSortBy);

}
