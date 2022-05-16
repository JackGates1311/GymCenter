package com.project.gymcenter.controller;

import com.project.gymcenter.dao.form.AddNewPeriodForm;
import com.project.gymcenter.model.Period;
import com.project.gymcenter.service.AuditoriumService;
import com.project.gymcenter.service.PeriodService;
import com.project.gymcenter.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PeriodController {

    @Autowired
    WorkoutService workoutService;

    @Autowired
    AuditoriumService auditoriumService;

    @Autowired
    PeriodService periodService;

    MessageController messageController = new MessageController();

    @RequestMapping(value="/addNewPeriod", method = RequestMethod.POST)
    public String addNewPeriod(@ModelAttribute(name="addNewPeriod") AddNewPeriodForm addNewPeriodForm, Model model,
                               HttpServletRequest request) {

        if(request.getSession().getAttribute("currentUserRole") == null) {

            return "login";

        } else {

            LocalDateTime[] date = getPeriodDates(addNewPeriodForm);

            Period period = new Period(addNewPeriodForm.getAuditoriumId(), addNewPeriodForm.getWorkoutId(),
                    date[0], date[1]);
            if(periodService.checkForPeriodAvailability(period)) {

                periodService.add(period);

                messageController.newMessageTemplate(model, "MagicBoost Gym - Message",
                        "MagicBoost Gym message", "New period added successfully",false);

            } else {

                messageController.newMessageTemplate(model, "MagicBoost Gym - Message",
                        "MagicBoost Gym", "New period is not added successfully " +
                                "because of time overlapping with another one",true);

            }

            return "message";

        }
    }

    protected void configureAddNewPeriodModal(Model model) {

        model.addAttribute("workoutListForPeriod", workoutService.findAll());
        model.addAttribute("auditoriumListForPeriod", auditoriumService.findAll());

    }

    protected LocalDateTime[] getPeriodDates(AddNewPeriodForm addNewPeriodForm) {

        int workoutLength = workoutService.findById(addNewPeriodForm.getWorkoutId()).getWorkoutLength();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime periodDateTimeStart = LocalDateTime.parse(addNewPeriodForm.getPeriodDateTimeStart(),
                formatter);

        LocalDateTime periodDateTimeEnd = periodDateTimeStart.plusMinutes(workoutLength);

        return new LocalDateTime[] {periodDateTimeStart, periodDateTimeEnd};
    }

}
