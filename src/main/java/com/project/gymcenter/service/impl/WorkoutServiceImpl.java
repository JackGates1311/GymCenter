package com.project.gymcenter.service.impl;

import com.project.gymcenter.dao.WorkoutDAO;
import com.project.gymcenter.model.Workout;
import com.project.gymcenter.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutServiceImpl implements WorkoutService {

    @Autowired
    private WorkoutDAO workoutDAO;

    @Override
    public Workout findById(Long workoutId) {
        return workoutDAO.findById(workoutId);
    }

    @Override
    public List<Workout> findAll() {
        return workoutDAO.findAll();
    }

    @Override
    public int add(Workout workout) {
        return 0;
    }

    @Override
    public int update(Workout workout) {
        return 0;
    }

    @Override
    public int delete(Workout workout) {
        return 0;
    }

    @Override
    public List<Workout> find(String workoutName, String workoutCoaches, Double workoutPriceMin, Double workoutPriceMax, String workoutTypeName, String workoutOrganizationType, String workoutLevel, String workoutSortBy) {
        return workoutDAO.find(workoutName,
                workoutCoaches, workoutPriceMin, workoutPriceMax, workoutTypeName, workoutOrganizationType,
                workoutLevel, workoutSortBy);
    }
}
