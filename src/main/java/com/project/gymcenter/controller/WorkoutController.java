package com.project.gymcenter.controller;


import com.project.gymcenter.form.AddEditWorkoutForm;
import com.project.gymcenter.form.WorkoutSearchForm;
import com.project.gymcenter.model.Workout;
import com.project.gymcenter.model.WorkoutLevel;
import com.project.gymcenter.model.WorkoutOrganizatonType;
import com.project.gymcenter.model.WorkoutType;
import com.project.gymcenter.service.RegisteredUserService;
import com.project.gymcenter.service.WorkoutIncludedTypesService;
import com.project.gymcenter.service.WorkoutService;
import com.project.gymcenter.service.WorkoutTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller

public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;

    @Autowired
    private WorkoutTypeService workoutTypeService;

    @Autowired
    private RegisteredUserService registeredUserService;

    @Autowired
    private WorkoutIncludedTypesService workoutIncludedTypesService;

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

    @RequestMapping(value="/addNewWorkout")
    public String addNewWorkout(Model model) {

        List<WorkoutType> workoutTypes = workoutTypeService.findAll();
        List<Workout> workouts = workoutService.findAll();

        model.addAttribute("addNewWorkoutTypes", workoutTypes);

        return "addNewWorkout";
    }

    @RequestMapping(value="/sendAddWorkoutData", method = RequestMethod.POST)

    public String sendAddWorkoutData(@ModelAttribute(name="addWorkoutForm") AddEditWorkoutForm addEditWorkoutForm,
                                     Model model) throws Exception {

        String newWorkoutName = addEditWorkoutForm.getNewWorkoutName();
        List<String> newWorkoutIncludedTypes = addEditWorkoutForm.getNewWorkoutIncludedTypes();
        String newWorkoutCoaches = addEditWorkoutForm.getNewWorkoutCoaches();
        Double newWorkoutPrice = addEditWorkoutForm.getNewWorkoutPrice();
        WorkoutOrganizatonType newWorkoutOrganizationType = addEditWorkoutForm.getNewWorkoutOrganizationType();
        int newWorkoutLength = addEditWorkoutForm.getnewWorkoutLength();
        WorkoutLevel newWorkoutLevel = addEditWorkoutForm.getNewWorkoutLevel();
        String newWorkoutImage = null;
        String newWorkoutDescription = addEditWorkoutForm.getNewWorkoutDescription();

        String workoutTypeName = workoutTypeService.parseWorkoutTypes(addEditWorkoutForm.getNewWorkoutIncludedTypes());

        try {

            newWorkoutImage = workoutService.saveImage(addEditWorkoutForm.getNewWorkoutImage());

            System.out.println(newWorkoutImage);

            Workout workout = new Workout(workoutTypeName, newWorkoutCoaches, newWorkoutDescription, newWorkoutPrice,
                    newWorkoutOrganizationType, newWorkoutLevel, newWorkoutLength, newWorkoutName, newWorkoutImage);

            Long workoutId = workoutService.add(workout);

            System.out.println(workoutId);

            for(int i = 0; i < newWorkoutIncludedTypes.size(); i++)
                workoutIncludedTypesService.add(workoutId, newWorkoutIncludedTypes.get(i));

        } catch (Exception e) {

            model.addAttribute("workoutAddFailed", true);

            return "redirect:/workouts";
        }

        model.addAttribute("workoutAddSuccess", true);

        return "redirect:/workouts";

    }

}