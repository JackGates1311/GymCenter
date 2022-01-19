package com.project.gymcenter.service.impl;

import com.project.gymcenter.dao.WorkoutIncludedTypesDAO;
import com.project.gymcenter.model.WorkoutIncludedTypes;
import com.project.gymcenter.service.WorkoutIncludedTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutIncludedTypesServiceImpl implements WorkoutIncludedTypesService {

    @Autowired
    private WorkoutIncludedTypesDAO workoutIncludedTypesDAO;

    @Override
    public List<WorkoutIncludedTypes> findById(Long workoutId) {

        return workoutIncludedTypesDAO.findById(workoutId);
    }
}
