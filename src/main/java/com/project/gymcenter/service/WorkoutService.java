package com.project.gymcenter.service;

import com.project.gymcenter.model.Workout;

import java.util.List;

public interface WorkoutService {

    List<Workout> findAll();

    public int add(Workout workout);

    public int update(Workout workout);

    public int delete(Workout workout);

}
