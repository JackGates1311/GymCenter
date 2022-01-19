package com.project.gymcenter.service.impl;

import com.project.gymcenter.dao.WorkoutTypeDAO;
import com.project.gymcenter.model.WorkoutType;
import com.project.gymcenter.service.WorkoutTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutTypeServiceImpl implements WorkoutTypeService {

    @Autowired
    private WorkoutTypeDAO workoutTypeDAO;

    @Override
    public List<WorkoutType> findAll() {

        return workoutTypeDAO.findAll();
    }

    @Override
    public WorkoutType findByWorkoutTypeName(String workoutTypeName) {

        return workoutTypeDAO.findByWorkoutTypeName(workoutTypeName);
    }
}
