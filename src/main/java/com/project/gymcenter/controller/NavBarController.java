package com.project.gymcenter.controller;

import com.project.gymcenter.model.Auditorium;
import com.project.gymcenter.model.Workout;
import com.project.gymcenter.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.Period;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

import static java.util.Locale.US;

@Controller
public class NavBarController {

    protected void setNavBarAdministrator(String navBarTitle, String navBarLink, String navBarLanguagePath,
                                          Boolean showSearchIconNavBar, Boolean showTranslateIconNavBar, Model model,
                                          List<Workout> workoutList, List<Auditorium> auditoriumList) {

        model.addAttribute("navBarTitle", navBarTitle);
        model.addAttribute("navBarTitlePath", navBarLink);

        model.addAttribute("showTranslateIconNavBar", showTranslateIconNavBar);
        model.addAttribute("translateNavBarPath", navBarLanguagePath);

        model.addAttribute("showSearchIconNavBar", showSearchIconNavBar);

        model.addAttribute("showLoyaltyCardRequestIconNavBar", true);
        model.addAttribute("showLoyaltyCardIconNavBar", false);
        model.addAttribute("showCommentsIconNavBar", true);
        model.addAttribute("showWorkoutSummaryIconNavBar", true);
        model.addAttribute("showAllWorkoutsIconNavBar", true);
        model.addAttribute("showAllWorkoutsIconNavBarLink", "/workouts");

        model.addAttribute("showWorkoutReservationsIconNavBar", true);

        model.addAttribute("addPeriodIconNavBar", true);

        model.addAttribute("workoutListForPeriod", workoutList);
        model.addAttribute("auditoriumListForPeriod", auditoriumList);

        model.addAttribute("showAuditoriumsIconNavBar", true);
        model.addAttribute("showAddNewWorkoutIconNavBar", true);
        model.addAttribute("showFavoritesIconNavBar", false);
        model.addAttribute("showManageUsersIconNavBar", true);

        model.addAttribute("showShoppingCartIconNavBar", false);

        model.addAttribute("showManageUserIconNavBar", true);
        model.addAttribute("showLogoutIconNavBar", true);

        model.addAttribute("showUserRegisterIconNavBar", false);
        model.addAttribute("showLoginIconNavBar", false);
    }

    protected void setNavBarCustomer(String navBarTitle, String navBarLink, String navBarLanguagePath,
                                     Boolean showSearchIconNavBar, Boolean showTranslateIconNavBar, Model model) {

        model.addAttribute("navBarTitle", navBarTitle);
        model.addAttribute("navBarTitlePath", navBarLink);

        model.addAttribute("showTranslateIconNavBar", showTranslateIconNavBar);
        model.addAttribute("translateNavBarPath", navBarLanguagePath);

        model.addAttribute("showSearchIconNavBar", showSearchIconNavBar);

        model.addAttribute("showLoyaltyCardRequestIconNavBar", false);
        model.addAttribute("showLoyaltyCardIconNavBar", true);
        model.addAttribute("showCommentsIconNavBar", false);
        model.addAttribute("showWorkoutSummaryIconNavBar", false);
        model.addAttribute("showAllWorkoutsIconNavBar", true);
        model.addAttribute("showAllWorkoutsIconNavBarLink", "/");

        model.addAttribute("showWorkoutReservationsIconNavBar", true);

        model.addAttribute("addPeriodIconNavBar", false);

       // periodController.configureAddNewPeriodModal(model);

        model.addAttribute("showAuditoriumsIconNavBar", false);
        model.addAttribute("showAddNewWorkoutIconNavBar", false);
        model.addAttribute("showFavoritesIconNavBar", true);
        model.addAttribute("showManageUsersIconNavBar", false);

        model.addAttribute("showShoppingCartIconNavBar", true);

        model.addAttribute("showManageUserIconNavBar", true);
        model.addAttribute("showLogoutIconNavBar", true);

        model.addAttribute("showUserRegisterIconNavBar", false);
        model.addAttribute("showLoginIconNavBar", false);
    }

    protected void setNavBarGuest(String navBarTitle, String navBarLink, String navBarLanguagePath,
                                  Boolean showSearchIconNavBar, Boolean showTranslateIconNavBar, Model model) {

        model.addAttribute("navBarTitle", navBarTitle);
        model.addAttribute("navBarTitlePath", navBarLink);

        model.addAttribute("showTranslateIconNavBar", showTranslateIconNavBar);
        model.addAttribute("translateNavBarPath", navBarLanguagePath);

        model.addAttribute("showSearchIconNavBar", showSearchIconNavBar);

        model.addAttribute("showLoyaltyCardRequestIconNavBar", false);
        model.addAttribute("showLoyaltyCardIconNavBar", false);
        model.addAttribute("showCommentsIconNavBar", false);
        model.addAttribute("showWorkoutSummaryIconNavBar", false);
        model.addAttribute("showAllWorkoutsIconNavBar", true);
        model.addAttribute("showAllWorkoutsIconNavBarLink", "/");

        model.addAttribute("showWorkoutReservationsIconNavBar", false);

        model.addAttribute("addPeriodIconNavBar", false);
        model.addAttribute("showAuditoriumsIconNavBar", false);
        model.addAttribute("showAddNewWorkoutIconNavBar", false);
        model.addAttribute("showFavoritesIconNavBar", false);
        model.addAttribute("showManageUsersIconNavBar", false);

        model.addAttribute("showShoppingCartIconNavBar", false);

        model.addAttribute("showManageUserIconNavBar", false);
        model.addAttribute("showLogoutIconNavBar", false);

        model.addAttribute("showUserRegisterIconNavBar", true);
        model.addAttribute("showLoginIconNavBar", true);
    }


    protected String getNavBarLanguagePath(String lang) {

        String navBarLanguagePath;

        if(Objects.equals(lang, "en")) {

            navBarLanguagePath = "lang=sr";

        } else if(Objects.equals(lang, "sr")) {

            navBarLanguagePath = "lang=en";

        } else {

            navBarLanguagePath = "lang=sr";
        }

        return navBarLanguagePath;
    }
}
