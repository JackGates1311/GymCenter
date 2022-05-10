package com.project.gymcenter.controller;

import org.springframework.ui.Model;

public class NavBarController {

    protected void setNavBarAdministrator(String navBarTitle, String navBarLink, Boolean showSearchIconNavBar, Model model) {

        model.addAttribute("navBarTitle", navBarTitle);
        model.addAttribute("navBarTitlePath", navBarLink);

        model.addAttribute("showSearchIconNavBar", showSearchIconNavBar);

        model.addAttribute("showAllWorkoutsIconNavBar", true);
        model.addAttribute("showWorkoutsIconNavBar", true);
        model.addAttribute("showAddNewWorkoutIconNavBar", true);
        model.addAttribute("showFavoritesIconNavBar", true);
        model.addAttribute("showManageUsersIconNavBar", true);
        model.addAttribute("showManageUserIconNavBar", true);
        model.addAttribute("showLogoutIconNavBar", true);

        model.addAttribute("showUserRegisterIconNavBar", false);
        model.addAttribute("showLoginIconNavBar", false);
    }

    protected void setNavBarGuest(String navBarTitle, String navBarLink, Boolean showSearchIconNavBar, Model model) {

        model.addAttribute("navBarTitle", navBarTitle);
        model.addAttribute("navBarTitlePath", navBarLink);

        model.addAttribute("showSearchIconNavBar", showSearchIconNavBar);

        model.addAttribute("showAllWorkoutsIconNavBar", false);
        model.addAttribute("showWorkoutsIconNavBar", false);
        model.addAttribute("showAddNewWorkoutIconNavBar", false);
        model.addAttribute("showFavoritesIconNavBar", false);
        model.addAttribute("showManageUsersIconNavBar", false);
        model.addAttribute("showManageUserIconNavBar", false);
        model.addAttribute("showLogoutIconNavBar", false);

        model.addAttribute("showUserRegisterIconNavBar", true);
        model.addAttribute("showLoginIconNavBar", true);
    }
}
