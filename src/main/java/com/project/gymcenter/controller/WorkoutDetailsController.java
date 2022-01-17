package com.project.gymcenter.controller;

import com.project.gymcenter.model.Workout;
import com.project.gymcenter.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WorkoutDetailsController {

    @Autowired
    private WorkoutService workoutService;

    @RequestMapping(value="/workoutDetails")
    public String workoutDetails(@RequestParam Long id, Model model) {


        System.out.println(id);

        Workout workout = workoutService.findById(id);

        System.out.println(workout);


        model.addAttribute("workout", workout);

        return "workoutDetails";
    }

}
