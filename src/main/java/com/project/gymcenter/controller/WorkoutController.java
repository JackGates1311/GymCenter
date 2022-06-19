package com.project.gymcenter.controller;


import com.project.gymcenter.dao.form.AddEditWorkoutForm;
import com.project.gymcenter.dao.form.AddWorkoutToShoppingCartForm;
import com.project.gymcenter.dao.form.WorkoutSearchForm;
import com.project.gymcenter.model.ShoppingCart;
import com.project.gymcenter.model.UserRole;
import com.project.gymcenter.model.Workout;
import com.project.gymcenter.model.WorkoutType;
import com.project.gymcenter.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@Controller

public class WorkoutController {


    @Autowired
    private WorkoutService workoutService;

    @Autowired
    private WorkoutTypeService workoutTypeService;

    @Autowired
    private WorkoutIncludedTypesService workoutIncludedTypesService;

    @Autowired
    private PeriodService periodService;

    @Autowired
    NavBarController navBarController = new NavBarController();

    private List<Workout> workoutList;
    private List<WorkoutType> workoutTypes;


    @RequestMapping(value="/workouts")
    public String workouts(Model model, HttpServletRequest request) {

        if(Objects.isNull(request.getSession().getAttribute("currentUserRole").toString()) ||
                Objects.equals(request.getSession().getAttribute("currentUserRole").toString(),
                        String.valueOf(UserRole.Customer))) {

            model.addAttribute("showCancelButton", true);
            model.addAttribute("permissionDenied", true);

            return "login";

        } else {

            workoutList = workoutService.findAll();

            workoutTypes = workoutTypeService.findAll();

            System.out.println(workoutList);

            model.addAttribute("workouts", workoutList);
            model.addAttribute("workoutTypes", workoutTypes);

            navBarController.setNavBarAdministrator("List of workouts", "/workouts", true, model);

            return "workouts";
        }
    }

    @RequestMapping(value="/workoutsSearchResult", method= RequestMethod.POST)
    public String workoutsSearchResult(@ModelAttribute(name="workoutSearchForm") WorkoutSearchForm workoutSearchForm,
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

        if(request.getSession().getAttribute("currentUserRole") == null) {

            workoutsFound.removeIf(workout ->
                            periodService.findAvailablePeriodsByWorkoutId(workout.getWorkoutId(), null).isEmpty());


            navBarController.setNavBarGuest("MagicBoost Gym Center", "/", true, model);

            return "index";

        } else if (Objects.equals(request.getSession().getAttribute("currentUserRole").toString(),
                String.valueOf(UserRole.Customer))) {

            workoutsFound.removeIf(workout ->
                    periodService.findAvailablePeriodsByWorkoutId(workout.getWorkoutId(), null).isEmpty());

            navBarController.setNavBarCustomer("MagicBoost Gym Center", "/", true,
                    model);

            return "index";
        }

        else {

            navBarController.setNavBarAdministrator("List of workouts", "/workouts", true, model);

            return "workouts";
        }


    }

    @RequestMapping(value="/addNewWorkout")
    public String addNewWorkout(Model model, HttpServletRequest request) {

        workoutTypes = workoutTypeService.findAll();
        workoutList = workoutService.findAll();

        model.addAttribute("addNewWorkoutTypes", workoutTypes);

        if(Objects.isNull(request.getSession().getAttribute("currentUserRole").toString()) ||
                Objects.equals(request.getSession().getAttribute("currentUserRole").toString(),
                        String.valueOf(UserRole.Customer))) {

            model.addAttribute("showCancelButton", true);
            model.addAttribute("permissionDenied", true);

            return "login";

        } else {

            navBarController.setNavBarAdministrator("Add new workout", "/addNewWorkout", false, model);

            return "addNewWorkout";
        }


    }

    @RequestMapping(value="/sendAddWorkoutData", method = RequestMethod.POST)

    public String sendAddWorkoutData(@ModelAttribute(name="addEditWorkoutForm") AddEditWorkoutForm addEditWorkoutForm,
                                     Model model) {

        try {

            Workout workout = new Workout(
                    workoutTypeService.parseWorkoutTypes(addEditWorkoutForm.getNewWorkoutIncludedTypes()),
                    addEditWorkoutForm.getNewWorkoutCoaches(), addEditWorkoutForm.getNewWorkoutDescription(),
                    addEditWorkoutForm.getNewWorkoutPrice(), addEditWorkoutForm.getNewWorkoutOrganizationType(),
                    addEditWorkoutForm.getNewWorkoutLevel(), addEditWorkoutForm.getNewWorkoutLength(),
                    addEditWorkoutForm.getNewWorkoutName(), addEditWorkoutForm.getNewWorkoutImageUrl());

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

        workoutList.removeIf(workout ->
                periodService.findAvailablePeriodsByWorkoutId(workout.getWorkoutId(), null).isEmpty());

        workoutTypes = workoutTypeService.findAll();

        System.out.println(workoutList);

        model.addAttribute("workouts", workoutList);
        model.addAttribute("workoutTypes", workoutTypes);
        model.addAttribute("isLoggedIn", false);

        model.addAttribute("accessFromIndexPage", true);

        if(request.getSession().getAttribute("currentUserRole") == null) {

            navBarController.setNavBarGuest("MagicBoost Gym Center", "/", true, model);

            //model.addAttribute("workoutListPeriod", periodService.findAllAvailablePeriods(null));

            return "index";

        } else if(Objects.equals(request.getSession().getAttribute("currentUserRole").toString(),
                String.valueOf(UserRole.Customer))) {

            Long userId = Long.parseLong(String.valueOf(request.getSession().getAttribute("loggedInUserId")));

            model.addAttribute("workoutListPeriod", periodService.findAllAvailablePeriods(userId));

            navBarController.setNavBarCustomer("MagicBoost Gym Center", "/", true,
                    model);

            model.addAttribute("showWorkoutReservationButton", true);
            model.addAttribute("showAddWorkoutToWishListButton", true);

            return "index";

        } else {

            return "redirect:/workouts";
        }

    }

}