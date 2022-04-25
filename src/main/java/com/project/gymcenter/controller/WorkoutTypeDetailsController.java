package com.project.gymcenter.controller;

import com.project.gymcenter.service.WorkoutTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WorkoutTypeDetailsController {

    @Autowired
    WorkoutTypeService workoutTypeService;

    @RequestMapping(value="/workoutTypeDetails")
    public String workoutTypeDetails(@RequestParam String type, Model model) {

        model.addAttribute("workoutTypeDetails", workoutTypeService.findByWorkoutTypeName(type));

        return "workoutTypeDetails";
    }


}
