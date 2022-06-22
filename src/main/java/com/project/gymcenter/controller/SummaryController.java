package com.project.gymcenter.controller;

import com.project.gymcenter.dao.form.SummarySearchForm;
import com.project.gymcenter.model.UserRole;
import com.project.gymcenter.model.Workout;
import com.project.gymcenter.service.AuditoriumService;
import com.project.gymcenter.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class SummaryController {

    @Autowired
    AuditoriumController auditoriumController;

    @Autowired
    AuditoriumService auditoriumService;

    @Autowired
    WorkoutService workoutService;

    NavBarController navBarController = new NavBarController();

    @RequestMapping(value = "/summary")
    public String workoutsSummary(HttpServletRequest request, Model model, @RequestParam(required = false) String lang) {

        String navBarLanguagePath = request.getRequestURL().toString() + "?" +
                navBarController.getNavBarLanguagePath(lang);

        if(Objects.equals(request.getSession().getAttribute("currentUserRole").toString(),
                String.valueOf(UserRole.Administrator))) {

            navBarController.setNavBarAdministrator("Workouts summary",
                "/summary", navBarLanguagePath, true, true, model, workoutService.findAll(),
                    auditoriumService.findAll());

            getWorkouts(workoutService.count(), "workoutName ASC", model);

            return "summary";

        } else {

            model.addAttribute("showCancelButton", true);
            model.addAttribute("permissionDenied", true);

            return "login";
        }
    }

    @RequestMapping(value = "/summarySearchResult", method = RequestMethod.POST)
    public String summarySearchResult(@ModelAttribute(name="summarySearchForm") SummarySearchForm summarySearchForm, Model model, HttpServletRequest request, @RequestParam(required = false) String lang) {

        if(Objects.equals(request.getSession().getAttribute("currentUserRole").toString(),
                String.valueOf(UserRole.Administrator))) {

            if(Objects.equals(summarySearchForm.getSummaryDateStart(), "") &&
                    Objects.equals(summarySearchForm.getSummaryDateEnd(), "")) {

                getWorkouts(workoutService.count(), summarySearchForm.getSummarySortBy(), model);

            } else {

                getWorkouts(workoutService.countBetweenDates(summarySearchForm.getSummaryDateStart(),
                        summarySearchForm.getSummaryDateEnd()), summarySearchForm.getSummarySortBy(), model);
            }

            navBarController.setNavBarAdministrator("Workouts summary", "/summary",
                    "", true, false, model, workoutService.findAll(),
                    auditoriumService.findAll());

            return "summary";

        } else {

            model.addAttribute("showCancelButton", true);
            model.addAttribute("permissionDenied", true);

            return "login";
        }

    }

    public void getWorkouts(List<Workout> workoutReservationCount, String sortBy, Model model) {

        List<Workout> allWorkouts = workoutService.findAll();

        List<Workout> workoutSummaryList = new ArrayList<>();

        Long reservationCountTotal = 0L;

        Double salaryTotal = 0.0;

        for(Workout workout : allWorkouts) {

            Workout tempWorkout = new Workout(workout.getWorkoutId(), workout.getWorkoutName(),
                    workout.getWorkoutCoaches(), 0L, 0.0);

            for(Workout workoutCount: workoutReservationCount) {

                if(workout.getWorkoutId() == workoutCount.getWorkoutId()) {

                    tempWorkout.setReservationCount(workoutCount.getReservationCount());
                    tempWorkout.setSalary(workoutCount.getReservationCount() * workout.getWorkoutPrice());
                }
            }

            workoutSummaryList.add(tempWorkout);

            reservationCountTotal += tempWorkout.getReservationCount();
            salaryTotal += tempWorkout.getSalary();
        }

        model.addAttribute("workoutSummaryList", sortedList(workoutSummaryList, sortBy));
        model.addAttribute("reservationCountTotal", reservationCountTotal);
        model.addAttribute("salaryTotal", salaryTotal);
    }

    private List<Workout> sortedList (List<Workout> workoutSummaryList, String sortBy) {

        //.reserved() - DESC -> without that, ASC

        if(Objects.equals(sortBy, "workoutName ASC"))
            Collections.sort(workoutSummaryList, Comparator.comparing(Workout::getWorkoutName));
        if(Objects.equals(sortBy, "workoutName DESC"))
            Collections.sort(workoutSummaryList, Comparator.comparing(Workout::getWorkoutName).reversed());
        if(Objects.equals(sortBy, "workoutCoaches ASC"))
            Collections.sort(workoutSummaryList, Comparator.comparing(Workout::getWorkoutCoaches));
        if(Objects.equals(sortBy, "workoutCoaches DESC"))
            Collections.sort(workoutSummaryList, Comparator.comparing(Workout::getWorkoutCoaches).reversed());
        if(Objects.equals(sortBy, "reservationCount ASC"))
            Collections.sort(workoutSummaryList, Comparator.comparing(Workout::getReservationCount));
        if(Objects.equals(sortBy, "reservationCount DESC"))
            Collections.sort(workoutSummaryList, Comparator.comparing(Workout::getReservationCount).reversed());
        if(Objects.equals(sortBy, "workoutSalary ASC"))
            Collections.sort(workoutSummaryList, Comparator.comparing(Workout::getSalary));
        if(Objects.equals(sortBy, "workoutSalary DESC"))
            Collections.sort(workoutSummaryList, Comparator.comparing(Workout::getSalary).reversed());

        return workoutSummaryList;
    }

}
