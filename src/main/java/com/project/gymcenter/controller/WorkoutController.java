package com.project.gymcenter.controller;


import com.project.gymcenter.form.WorkoutSearchForm;
import com.project.gymcenter.model.Workout;
import com.project.gymcenter.model.WorkoutType;
import com.project.gymcenter.service.WorkoutService;
import com.project.gymcenter.service.WorkoutTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller

public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;

    @Autowired
    private WorkoutTypeService workoutTypeService;

    @RequestMapping(value="/workouts")
    public String workouts(Model model) {

        List<Workout> workoutList = workoutService.findAll();

        List<WorkoutType> workoutTypes = workoutTypeService.findAll();

        System.out.println(workoutList);

        model.addAttribute("workouts", workoutList);
        model.addAttribute("workoutTypes", workoutTypes);

        return "workouts";
    }

    @RequestMapping(value="/workoutsSearchResult", method= RequestMethod.POST)
    public String workoutsSearch(@ModelAttribute(name="workoutSearchForm") WorkoutSearchForm workoutSearchForm,
                                 Model model) {

        String workoutName = workoutSearchForm.getWorkoutName();
        String workoutCoaches = workoutSearchForm.getWorkoutCoach();
        Double workoutPriceMin = workoutSearchForm.getWorkoutPriceMin();
        Double workoutPriceMax = workoutSearchForm.getWorkoutPriceMax();
        String workoutTypeName = workoutSearchForm.getWorkoutTypeName();
        String workoutOrganizationType = workoutSearchForm.getWorkoutOrganizationType();
        String workoutLevel = workoutSearchForm.getWorkoutLevel();
        String workoutSortBy = workoutSearchForm.getWorkoutSortBy();

        if(workoutPriceMin == null)
            workoutPriceMin = 0.0;
        if(workoutPriceMax == null)
            workoutPriceMax = Math.pow(2, 31);

        System.out.println(workoutLevel);

        List<Workout> workoutsFound = workoutService.find(workoutName, workoutCoaches, workoutPriceMin,
                workoutPriceMax, workoutTypeName, workoutOrganizationType, workoutLevel, workoutSortBy);

        System.out.println(workoutsFound);

        List<WorkoutType> workoutTypes = workoutTypeService.findAll();

        model.addAttribute("workouts", workoutsFound);
        model.addAttribute("workoutTypes", workoutTypes);
        model.addAttribute("refreshButton", true);

        return "workouts";
    }


    @GetMapping(value="/log")
    @ResponseBody
    public void log() throws IOException{

        List<Workout> workoutList = workoutService.findAll();

        System.out.println(workoutList.toString());

        return;
    }

}