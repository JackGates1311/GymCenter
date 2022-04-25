package com.project.gymcenter.controller;


import com.project.gymcenter.dao.form.AddEditWorkoutForm;
import com.project.gymcenter.dao.form.WorkoutSearchForm;
import com.project.gymcenter.model.UserRole;
import com.project.gymcenter.model.Workout;
import com.project.gymcenter.model.WorkoutType;
import com.project.gymcenter.service.WorkoutIncludedTypesService;
import com.project.gymcenter.service.WorkoutService;
import com.project.gymcenter.service.WorkoutTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller

public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;

    @Autowired
    private WorkoutTypeService workoutTypeService;

    @Autowired
    private WorkoutIncludedTypesService workoutIncludedTypesService;

    private List<Workout> workoutList;
    private List<WorkoutType> workoutTypes;

    @RequestMapping(value="/workouts")
    public String workouts(Model model, HttpServletRequest request) {

        if(request.getSession().getAttribute("currentUserRole") == null) {

            return "login";

        } else {

            workoutList = workoutService.findAll();

            workoutTypes = workoutTypeService.findAll();

            System.out.println(workoutList);

            model.addAttribute("workouts", workoutList);
            model.addAttribute("workoutTypes", workoutTypes);

            return "workouts";
        }
    }

    @RequestMapping(value="/workoutsSearchResult", method= RequestMethod.POST)
    public String workoutsSearch(@ModelAttribute(name="workoutSearchForm") WorkoutSearchForm workoutSearchForm,
                                 Model model, HttpServletRequest request) {

        if(workoutSearchForm.getWorkoutPriceMin() == null)
            workoutSearchForm.setWorkoutPriceMin(0.0);

        if(workoutSearchForm.getWorkoutPriceMax() == null)
            workoutSearchForm.setWorkoutPriceMax(Math.pow(2, 31));

        List<Workout> workoutsFound = workoutService.find(workoutSearchForm.getWorkoutName(),
                workoutSearchForm.getWorkoutCoach(), workoutSearchForm.getWorkoutPriceMin(),
                workoutSearchForm.getWorkoutPriceMax(), workoutSearchForm.getWorkoutTypeName(),
                workoutSearchForm.getWorkoutOrganizationType(), workoutSearchForm.getWorkoutLevel(),
                workoutSearchForm.getWorkoutSortBy());

        System.out.println(workoutsFound);

        workoutTypes = workoutTypeService.findAll();

        model.addAttribute("workouts", workoutsFound);
        model.addAttribute("workoutTypes", workoutTypes);
        model.addAttribute("searchPerformed", true);

        if(request.getSession().getAttribute("currentUserRole") == null)
            return "index";
        else
            return "workouts";
    }

    @RequestMapping(value="/addNewWorkout")
    public String addNewWorkout(Model model) {

        workoutTypes = workoutTypeService.findAll();
        workoutList = workoutService.findAll();

        model.addAttribute("addNewWorkoutTypes", workoutTypes);

        return "addNewWorkout";
    }

    @RequestMapping(value="/sendAddWorkoutData", method = RequestMethod.POST)

    public String sendAddWorkoutData(@ModelAttribute(name="addWorkoutForm") AddEditWorkoutForm addEditWorkoutForm,
                                     Model model) {

        try {

            Workout workout = new Workout(
                    workoutTypeService.parseWorkoutTypes(addEditWorkoutForm.getNewWorkoutIncludedTypes()),
                    addEditWorkoutForm.getNewWorkoutCoaches(), addEditWorkoutForm.getNewWorkoutDescription(),
                    addEditWorkoutForm.getNewWorkoutPrice(), addEditWorkoutForm.getNewWorkoutOrganizationType(),
                    addEditWorkoutForm.getNewWorkoutLevel(), addEditWorkoutForm.getnewWorkoutLength(),
                    addEditWorkoutForm.getNewWorkoutName(),
                    workoutService.saveImage(addEditWorkoutForm.getNewWorkoutImage()));

            Long workoutId = workoutService.add(workout);

            for (String newWorkoutIncludedType : addEditWorkoutForm.getNewWorkoutIncludedTypes())
                workoutIncludedTypesService.add(workoutId, newWorkoutIncludedType);

        } catch (Exception e) {

            model.addAttribute("workoutAddFailed", true);

            return "redirect:/workouts";
        }

        model.addAttribute("workoutAddSuccess", true);

        return "redirect:/workouts";
    }

    @RequestMapping(value="/")

    public String index(Model model, HttpServletRequest request){

        workoutList = workoutService.findAll();

        workoutTypes = workoutTypeService.findAll();

        System.out.println(workoutList);

        model.addAttribute("workouts", workoutList);
        model.addAttribute("workoutTypes", workoutTypes);
        model.addAttribute("isLoggedIn", false);

        if(request.getSession().getAttribute("currentUserRole") == null)
            return "index";
        else
            return "redirect:/workouts";
    }

}