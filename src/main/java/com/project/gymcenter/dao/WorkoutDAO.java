package com.project.gymcenter.dao;

import com.project.gymcenter.model.Workout;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface WorkoutDAO {

    public Workout findById(Long workoutId);

    public List<Workout> findAll();

    public Long add(Workout workout);

    public int update(Workout workout);

    public int delete(Workout workout);

    public List<Workout> find(String workoutName, String workoutCoaches, Double workoutPriceMin,
                              Double workoutPriceMax, String workoutTypeName, String workoutOrganizationType,
                              String workoutLevel, String workoutSortBy);

    public String saveImage(MultipartFile imageFile) throws Exception;

    public int generateWorkoutId();
}
