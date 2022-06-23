package com.project.gymcenter.dao;

import com.project.gymcenter.model.Workout;

import java.time.LocalDateTime;
import java.util.List;

public interface WorkoutDAO {

    public Workout findById(Long workoutId);

    public List<Workout> findAll();

    public Long add(Workout workout);

    public void update(Workout workout, Long workoutId);

    public int delete(Workout workout);

    public List<Workout> find(String workoutName, String workoutCoaches, Double workoutPriceMin,
                              Double workoutPriceMax, String workoutTypeName, String workoutOrganizationType,
                              String workoutLevel, String workoutSortBy);

    public int generateWorkoutId();

    public List<Workout> count();

    public List<Workout> countBetweenDates(String summaryDateStart, String summaryDateEnd);

    public void updateAverageRate(Workout workout);
}
