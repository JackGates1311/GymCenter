package com.project.gymcenter.controller;

import com.project.gymcenter.form.AddEditWorkoutForm;
import com.project.gymcenter.model.*;
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

import java.util.List;

@Controller
public class WorkoutDetailsController {

    @Autowired
    private WorkoutService workoutService;

    @Autowired
    private WorkoutIncludedTypesService workoutIncludedTypesService;

    @Autowired
    private WorkoutTypeService workoutTypeService;

    @RequestMapping(value="/workoutDetails")
    public String workoutDetails(@RequestParam Long id, Model model) {


        System.out.println(id);

        Workout workout = workoutService.findById(id);

        System.out.println(workout);

        List<WorkoutIncludedTypes> workoutIncludedTypes = workoutIncludedTypesService.findById(id);

        model.addAttribute("workout", workout);
        model.addAttribute("workoutIncludedTypes", workoutIncludedTypes);

        return "workoutDetails";
    }

    @RequestMapping (value="/editWorkoutDetails")
    public String editWorkoutDetails(@RequestParam Long id, Model model) {

        Workout workoutForEdit = workoutService.findById(id);

        List<WorkoutType> workoutTypes = workoutTypeService.findAll();

        List<WorkoutIncludedTypes> workoutIncludedTypes = workoutIncludedTypesService.findById(id);

        List<SelectedWorkoutTypes> selectedWorkoutIncludedTypes =
                workoutIncludedTypesService.getSelectedWorkoutIncludedTypes(workoutTypes, workoutIncludedTypes);
        
        model.addAttribute("workoutForEdit", workoutForEdit);
        model.addAttribute("workoutTypes", workoutTypes);
        model.addAttribute("workoutIncludedTypes", workoutIncludedTypes);

        model.addAttribute("selectedWorkoutIncludedTypes", selectedWorkoutIncludedTypes);

        model.addAttribute("individualWorkout", WorkoutOrganizatonType.Individual);
        model.addAttribute("groupWorkout", WorkoutOrganizatonType.Group);

        model.addAttribute("amateurLevel", WorkoutLevel.Amateur);
        model.addAttribute("mediumLevel", WorkoutLevel.Medium);
        model.addAttribute("advancedLevel", WorkoutLevel.Advanced);

        return "editWorkoutDetails";
    }

    @RequestMapping(value="/sendEditWorkoutData", method = RequestMethod.POST)

    public String sendEditWorkoutData(@RequestParam Long id,
                                      @ModelAttribute(name="editWorkoutForm") AddEditWorkoutForm addEditWorkoutForm,
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

        } catch (Exception e) {

            System.out.println("File not selected! Program will not perform changes to image...");

            newWorkoutImage = workoutService.findById(id).getWorkoutImage();
        }

        Workout workout = new Workout(workoutTypeName, newWorkoutCoaches, newWorkoutDescription, newWorkoutPrice,
                    newWorkoutOrganizationType, newWorkoutLevel, newWorkoutLength, newWorkoutName, newWorkoutImage);

        workoutService.update(workout, id);

        System.out.println(workout);

        workoutIncludedTypesService.remove(id);

        for(int i = 0; i < newWorkoutIncludedTypes.size(); i++)
            workoutIncludedTypesService.add(id, newWorkoutIncludedTypes.get(i));

        return "redirect:/workoutDetails?id=" + id.toString();
    }

}
