package com.project.gymcenter.service;

import com.project.gymcenter.model.CommentStatus;
import com.project.gymcenter.model.WorkoutComment;

import java.util.List;

public interface WorkoutCommentService {

    public List<WorkoutComment> findAllByWorkoutId(Long id);

    public boolean checkIfUserIsAlreadyCommentedOnWorkout(Long userId, Long workoutId);

    public void postComment(WorkoutComment workoutComment);

    public List<WorkoutComment> findOnWaitingByWorkoutId(Long id);

    public WorkoutComment findById(Long id);

    public void updateCommentStatus(Long id, CommentStatus commentStatus);
}
