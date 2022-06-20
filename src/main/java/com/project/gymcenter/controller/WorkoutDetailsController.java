package com.project.gymcenter.controller;

import com.project.gymcenter.dao.form.AddEditWorkoutForm;
import com.project.gymcenter.model.*;
import com.project.gymcenter.service.PeriodService;
import com.project.gymcenter.service.WorkoutIncludedTypesService;
import com.project.gymcenter.service.WorkoutService;
import com.project.gymcenter.service.WorkoutTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@Controller
public class WorkoutDetailsController {

    @Autowired
    private PeriodService periodService;

    @Autowired
    private WorkoutService workoutService;

    @Autowired
    private WorkoutIncludedTypesService workoutIncludedTypesService;

    @Autowired
    private WorkoutTypeService workoutTypeService;

    @Autowired
    NavBarController navBarController = new NavBarController();

    @RequestMapping(value="/workoutDetails")
    public String workoutDetails(@RequestParam Long id, Model model, HttpServletRequest request) {

        if(request.getSession().getAttribute("currentUserRole") != null) {


            if(Objects.equals(request.getSession().getAttribute("currentUserRole").toString(),
                    String.valueOf(UserRole.Customer))) {

                Long userId = Long.parseLong(String.valueOf(request.getSession().getAttribute("loggedInUserId")));
                model.addAttribute("workoutListPeriod", periodService.findAvailablePeriodsByWorkoutId(id, userId));

            }
        }

        model.addAttribute("workout", workoutService.findById(id));
        model.addAttribute("workoutIncludedTypes", workoutIncludedTypesService.findById(id));
        model.addAttribute("workoutTypeDetails", workoutTypeService.findAll());


        model.addAttribute("accessFromWorkoutDetailsPage", true);

        String navBarTitle = "Workout details";

        if(request.getSession().getAttribute("currentUserRole") == null) {

            navBarController.setNavBarGuest(navBarTitle, "/workoutDetails?id=" + id, false, model);

            model.addAttribute("showEditWorkoutButton", false);

        } else if (Objects.equals(request.getSession().getAttribute("currentUserRole").toString(),
                String.valueOf(UserRole.Customer))) {

            navBarController.setNavBarCustomer(navBarTitle, "/workoutDetails?id=" + id, false,
                    model);

            model.addAttribute("showReservePeriodButton", true);
            model.addAttribute("showAddToWishListButton", true);
            model.addAttribute("showCommentsButton", true);

        } else {

            navBarController.setNavBarAdministrator(navBarTitle, "/workoutDetails?id=" + id,
                    false, model);

            model.addAttribute("showEditWorkoutButton", true);
        }

        return "workoutDetails";
    }

    @RequestMapping (value="/editWorkoutDetails")
    public String editWorkoutDetails(@RequestParam Long id, Model model) {

        navBarController.setNavBarAdministrator("Edit workout", "/editWorkoutDetails?id=" + id,
                false, model);

        Workout workoutForEdit = workoutService.findById(id);

        List<WorkoutType> workoutTypes = workoutTypeService.findAll();

        List<WorkoutIncludedTypes> workoutIncludedTypes = workoutIncludedTypesService.findById(id);

        List<SelectedWorkoutTypes> selectedWorkoutIncludedTypes =
                workoutIncludedTypesService.getSelectedWorkoutIncludedTypes(workoutTypes, workoutIncludedTypes);
        
        model.addAttribute("workoutForEdit", workoutForEdit);
        model.addAttribute("workoutTypes", workoutTypes);
        model.addAttribute("workoutIncludedTypes", workoutIncludedTypes);

        model.addAttribute("selectedWorkoutIncludedTypes", selectedWorkoutIncludedTypes);

        model.addAttribute("individualWorkout", WorkoutOrganizationType.Individual);
        model.addAttribute("groupWorkout", WorkoutOrganizationType.Group);

        model.addAttribute("amateurLevel", WorkoutLevel.Amateur);
        model.addAttribute("mediumLevel", WorkoutLevel.Medium);
        model.addAttribute("advancedLevel", WorkoutLevel.Advanced);

        return "editWorkoutDetails";
    }

    @RequestMapping(value="/sendEditWorkoutData", method = RequestMethod.POST)

    public String sendEditWorkoutData(@RequestParam Long id,
                                      @ModelAttribute(name = "editWorkoutForm") AddEditWorkoutForm addEditWorkoutForm) {

        Workout workout = new Workout(
                workoutTypeService.parseWorkoutTypes(addEditWorkoutForm.getNewWorkoutIncludedTypes()),
                addEditWorkoutForm.getNewWorkoutCoaches(), addEditWorkoutForm.getNewWorkoutDescription(),
                addEditWorkoutForm.getNewWorkoutPrice(), addEditWorkoutForm.getNewWorkoutOrganizationType(),
                addEditWorkoutForm.getNewWorkoutLevel(), addEditWorkoutForm.getNewWorkoutLength(),
                addEditWorkoutForm.getNewWorkoutName(), addEditWorkoutForm.getNewWorkoutImageUrl());

        workoutService.update(workout, id);

        System.out.println(workout);

        workoutIncludedTypesService.remove(id);

        for (String newWorkoutIncludedType : addEditWorkoutForm.getNewWorkoutIncludedTypes())
            workoutIncludedTypesService.add(id, newWorkoutIncludedType);

        return "redirect:/workoutDetails?id=" + id.toString();
    }

}
