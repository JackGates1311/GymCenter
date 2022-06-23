package com.project.gymcenter.service.impl;

import com.project.gymcenter.dao.WorkoutCommentDAO;
import com.project.gymcenter.model.CommentStatus;
import com.project.gymcenter.model.WorkoutComment;
import com.project.gymcenter.service.WorkoutCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutCommentServiceImpl implements WorkoutCommentService {

    @Autowired
    WorkoutCommentDAO workoutCommentDAO;

    @Override
    public List<WorkoutComment> findAllByWorkoutId(Long id) {

        return workoutCommentDAO.findAllByByWorkoutId(id);
    }

    @Override
    public boolean checkIfUserIsAlreadyCommentedOnWorkout(Long userId, Long workoutId) {

        return workoutCommentDAO.checkIfUserIsAlreadyCommentedOnWorkout(userId, workoutId);
    }

    @Override
    public void postComment(WorkoutComment workoutComment) {

        workoutCommentDAO.postComment(workoutComment);
    }

    @Override
    public List<WorkoutComment> findOnWaitingByWorkoutId(Long id) {

        return workoutCommentDAO.findOnWaitingByWorkoutId(id);
    }

    @Override
    public WorkoutComment findById(Long id) {

        return workoutCommentDAO.findById(id);
    }

    @Override
    public void updateCommentStatus(Long id, CommentStatus commentStatus) {

        workoutCommentDAO.updateCommentStatus(id, commentStatus);
    }
}
