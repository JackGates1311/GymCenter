package com.project.gymcenter.controller;

import com.project.gymcenter.model.CommentStatus;
import com.project.gymcenter.model.UserRole;
import com.project.gymcenter.model.Workout;
import com.project.gymcenter.model.WorkoutComment;
import com.project.gymcenter.service.AuditoriumService;
import com.project.gymcenter.service.WorkoutCommentService;
import com.project.gymcenter.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@Controller
public class WorkoutCommentsController {

    @Autowired
    WorkoutCommentService workoutCommentService;

    @Autowired
    WorkoutService workoutService;

    @Autowired
    AuditoriumService auditoriumService;

    NavBarController navBarController = new NavBarController();

    @ResponseBody
    @RequestMapping("/api/workoutComments/{id}")
    public List<WorkoutComment> getWorkoutCommentsApi(@PathVariable Long id) {

        if(id == -1) {

            return workoutCommentService.findOnWaitingByWorkoutId(id);

        } else {

            return workoutCommentService.findAllByWorkoutId(id);
        }

    }

    @ResponseBody
    @RequestMapping(value="/api/workoutComments/{id}", method = RequestMethod.POST)
    public void postWorkoutComment(HttpServletRequest request, WorkoutComment workoutComment) {

        Long userId = Long.parseLong(String.valueOf(request.getSession().getAttribute("loggedInUserId")));

        workoutComment.setUserId(userId);

        workoutCommentService.postComment(workoutComment);
    }

    @ResponseBody
    @RequestMapping(value="/api/approveComment/{id}")
    public void approveComment(@PathVariable Long id) {

        workoutCommentService.updateCommentStatus(id, CommentStatus.Approved);

        WorkoutComment workoutComment = workoutCommentService.findById(id);

        Workout workout = workoutService.findById(workoutComment.getWorkoutId());

        Double oldRate = workout.getWorkoutAverageRate();

        Double newRate = (oldRate + workoutComment.getWorkoutRate()) / 2.0;

        workout.setWorkoutAverageRate(newRate);

        workoutService.updateAverageRate(workout);

        // workoutService.setWorkoutAverageRate
    }

    @ResponseBody
    @RequestMapping(value="/api/declineComment/{id}")
    public void declineComment(@PathVariable Long id) {

        workoutCommentService.updateCommentStatus(id, CommentStatus.Declined);
    }

    @RequestMapping("/workoutComments")
    public String getWorkoutComments(HttpServletRequest request, Model model, @RequestParam(required = false)
            String lang, @RequestParam(required = false) Long id) {

        if(id == null)
            id = -1L;

        String navBarLanguagePath = request.getRequestURL().toString() + "?" +
                navBarController.getNavBarLanguagePath(lang) + "&id=" + id;

        if(request.getSession().getAttribute("currentUserRole") == null) {

            navBarController.setNavBarGuest("Workout comments", "/workoutComments?id=" + id,
                    navBarLanguagePath, false, true, model);

        } else {

            if(Objects.equals(request.getSession().getAttribute("currentUserRole").toString(),
                    String.valueOf(UserRole.Administrator))) {

                navBarController.setNavBarAdministrator("Workout comments", "/workoutComments",
                        navBarLanguagePath, false, true, model,
                        workoutService.findAll(), auditoriumService.findAll());

            } else {

                if(Objects.equals(request.getSession().getAttribute("currentUserRole").toString(),
                        String.valueOf(UserRole.Customer))) {

                    navBarController.setNavBarCustomer("Workout comments", "/workoutComments?id=" + id,
                            navBarLanguagePath, false, true, model);


                }

            }

        }


        return "workoutComments";
    }

}
