package com.project.gymcenter.service;

import com.project.gymcenter.model.Workout;

import java.util.List;

public interface WorkoutService {

    public Workout findById(Long workoutId);

    List<Workout> findAll();

    public int add(Workout workout);

    public int update(Workout workout);

    public int delete(Workout workout);

    public List<Workout> find(String workoutName, String workoutworkoutCoaches, Double workoutPriceMin,
                              Double workoutPriceMax, String workoutTypeName, String workoutOrganizationType,
                              String workoutLevel, String workoutSortBy);

}
