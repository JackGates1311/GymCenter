package com.project.gymcenter.controller;


import com.project.gymcenter.model.Workout;
import com.project.gymcenter.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

@Controller

public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;

    @RequestMapping(value="/workouts")
    public String workouts() {
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