package com.project.gymcenter.service.impl;

import com.project.gymcenter.dao.WorkoutIncludedTypesDAO;
import com.project.gymcenter.model.SelectedWorkoutTypes;
import com.project.gymcenter.model.WorkoutIncludedTypes;
import com.project.gymcenter.model.WorkoutType;
import com.project.gymcenter.service.WorkoutIncludedTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkoutIncludedTypesServiceImpl implements WorkoutIncludedTypesService {

    @Autowired
    private WorkoutIncludedTypesDAO workoutIncludedTypesDAO;

    @Override
    public List<WorkoutIncludedTypes> findById(Long workoutId) {

        return workoutIncludedTypesDAO.findById(workoutId);
    }

    @Override
    public void add(Long workoutId, String workoutTypeName) {

        workoutIncludedTypesDAO.add(workoutId, workoutTypeName);
    }

    @Override
    public void remove(Long workoutId) {

        workoutIncludedTypesDAO.remove(workoutId);
    }

    @Override
    public List<SelectedWorkoutTypes> getSelectedWorkoutIncludedTypes(List<WorkoutType> workoutTypes,
                                                                      List<WorkoutIncludedTypes> workoutIncludedTypes) {

        List<SelectedWorkoutTypes> selectedWorkoutIncludedTypes = new ArrayList<>();

        for(int i = 0; i < workoutTypes.size(); i++) {

            String returnValue = "";

            for(int j = 0; j < workoutIncludedTypes.size(); j++) {

                if(workoutTypes.get(i).getWorkoutTypeName().equals(workoutIncludedTypes.get(j).getWorkoutTypeName())) {

                    returnValue = "true";

                    break;

                } else {

                    returnValue = "false";

                }
            }

            SelectedWorkoutTypes selectedWorkoutTypes =
                    new SelectedWorkoutTypes(workoutTypes.get(i).getWorkoutTypeName(), returnValue);

            selectedWorkoutIncludedTypes.add(i, selectedWorkoutTypes);

        }

        return selectedWorkoutIncludedTypes;

    }

}
