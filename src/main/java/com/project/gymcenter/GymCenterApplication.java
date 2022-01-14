package com.project.gymcenter;

import com.project.gymcenter.model.Workout;
import com.project.gymcenter.service.impl.WorkoutServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class GymCenterApplication {

    public static void main(String[] args) {

        SpringApplication.run(GymCenterApplication.class, args);
    }

}
